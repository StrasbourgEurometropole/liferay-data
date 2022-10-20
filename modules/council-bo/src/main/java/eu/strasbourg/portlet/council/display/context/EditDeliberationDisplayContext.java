package eu.strasbourg.portlet.council.display.context;

import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.council.bean.VoteBean;
import eu.strasbourg.portlet.council.utils.UserRoleType;
import eu.strasbourg.service.council.constants.StageDeliberation;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Deliberation;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.model.Vote;
import eu.strasbourg.service.council.service.CouncilSessionLocalServiceUtil;
import eu.strasbourg.service.council.service.DeliberationLocalServiceUtil;
import eu.strasbourg.service.council.service.OfficialLocalServiceUtil;
import eu.strasbourg.service.council.service.VoteLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class EditDeliberationDisplayContext {

    final static private String POUR="Pour";
    final static private String CONTRE="Contre";
    final static private String ABSTENTION="Abstention";
    final static private String TOTAL = "Total des votes";

    private Deliberation deliberation;
    private final RenderRequest request;
    private final ThemeDisplay themeDisplay;
    private List<VoteBean> voteBeans;
    private List<Long> typeCouncilIds;

    public EditDeliberationDisplayContext(RenderRequest request, RenderResponse response) {
        this.request = request;
        this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        // Pour initialiser la liste des votes
        this.initVotes();
        typeCouncilIds = new ArrayList<>();
        initAuthorizedTypeCouncilsIds();
    }

    public Deliberation getDeliberation() {
        long deliberationId = ParamUtil.getLong(this.request, "deliberationId");
        if (deliberation == null && deliberationId > 0) {
            deliberation = DeliberationLocalServiceUtil.fetchDeliberation(deliberationId);
        }
        return deliberation;
    }

    /**
     * Récupère la liste de tous les conseils à venir (et on rajoute le conseil déjà enregistré pour la délib à la liste)
     * @return
     */
    public List<CouncilSession> getAvailableCouncilSessions() {

        List<CouncilSession> availableCouncilSessions = new ArrayList<>();
        List<CouncilSession>  otherList= new ArrayList<>();

        // Calcul de la date sur laquelle on recherche les conseils
        GregorianCalendar gregorianCalendar = CouncilSessionLocalServiceUtil.calculDateForFindCouncil();

        //On filtre la liste des futurs conseils en fonction des rôles du User
        Date date = gregorianCalendar.getTime();
        List<CouncilSession> councilSessions = CouncilSessionLocalServiceUtil.getFutureCouncilSessions(date);
        for (CouncilSession council : councilSessions) {
            if(typeCouncilIds.contains(council.getTypeId())) {
                otherList.add(council);
            }
        }

        if (deliberation != null) {
            CouncilSession councilDelib = CouncilSessionLocalServiceUtil.fetchCouncilSession(deliberation.getCouncilSessionId());

            // Rajoute la session de la délib à la liste si elle n'y est pas (une déblib d'une ancienne session par exemple)
            if (councilDelib != null && !otherList.contains(councilDelib)) {
                availableCouncilSessions.add(0, councilDelib);
            }
        }
        availableCouncilSessions.addAll(otherList);

        return availableCouncilSessions;
    }

    private void initAuthorizedTypeCouncilsIds() {
        if(typeCouncilIds.size() == 0) {
            typeCouncilIds = UserRoleType.typeIdsForUser(themeDisplay);
        }
    }

    /**
     * Retourne la liste des enum des statuts d'une délibération
     * @return
     */
    public List<StageDeliberation> getStages() {
        return StageDeliberation.getAll();
    }

    /**
     * @return True si le framework workflow est actif pour ce type d'entité
     */
    @SuppressWarnings("unused")
    public boolean isWorkflowEnabled() {
        return WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
                this.themeDisplay.getCompanyId(), this.themeDisplay.getCompanyGroupId(),
                CouncilSession.class.getName());
    }

    /**
     * Wrapper autour du permission checker pour les permissions de module
     */
    @SuppressWarnings("unused")
    public boolean hasPermission(String actionId) {
        return this.themeDisplay.getPermissionChecker().hasPermission(
                this.themeDisplay.getScopeGroupId(), StrasbourgPortletKeys.COUNCIL_BO,
                StrasbourgPortletKeys.COUNCIL_BO, actionId);
    }

    /**
     * Récupère les votes de la délibération
     */
    public void initVotes() {
        if(this.getDeliberation() != null) {
            List<Vote> votes = VoteLocalServiceUtil.findByDeliberationId(deliberation.getDeliberationId());
            voteBeans = new ArrayList<>();
            for (Vote vote : votes) {
                Official voter = OfficialLocalServiceUtil.fetchOfficial(vote.getOfficialId());
                Official procurationVoter = OfficialLocalServiceUtil.fetchOfficial(vote.getOfficialProcurationId());

                VoteBean voteBean = new VoteBean();
                if(voter != null) {
                    voteBean.setVoter(voter.getFullName());
                }
                if(procurationVoter != null) {
                    voteBean.setProcurationVoter(procurationVoter.getFullName());
                }
                voteBean.setResult(vote.getResult());
                voteBean.setCssClass(this.getCSSClassColor(vote));

                voteBeans.add(voteBean);
            }
            if (voteBeans.size() > 1) {
                // Tri par nom complet des résultats
                this.voteBeans = this.voteBeans.stream()
                        .sorted(Comparator.comparing(VoteBean::getVoter))
                        .collect(Collectors.toList());
            }
        }
    }

    /**
     * Récupère le nombre de votes d'un résultat (Pour, Contre, Abstention)
     */
    public long getVoteCountForAResult(String result) {
        long count = 0;
        if(voteBeans != null) {
            count = voteBeans.stream().filter(x -> x.getResult().toLowerCase().equals(result.toLowerCase())).count();
        }
        return count;
    }

    public String getStageDeliberationName(long id) {
        return StageDeliberation.get(id).getName();
    }

    /**
     * Class CSS de la couleur du Statut
     * @return
     */
    public String getCSSClassColor(Vote vote) {
        String cssClass="";
        if(vote.getResult().toLowerCase().equals(POUR.toLowerCase())) {
            cssClass="green";
        } else if (vote.getResult().toLowerCase().equals(CONTRE.toLowerCase())) {
            cssClass="red";
        } else if (vote.getResult().toLowerCase().equals(ABSTENTION.toLowerCase())){
            cssClass="orange";
        }

        return cssClass;
    }

    public Date getBeginningVoteDate() {

        Deliberation deliberation = this.getDeliberation();
        if (deliberation != null) {
            return deliberation.getBeginningVoteDate();
        } else {
            return null;
        }
    }

    public Date getEndVoteDate() {

        Deliberation deliberation = this.getDeliberation();
        if (deliberation != null) {
            return deliberation.getEndVoteDate();
        } else {
            return null;
        }
    }

    public boolean isAdopteOrRejeteOrCommunique() {

        if (deliberation != null) {
            String deliberationStage = deliberation.getStage();
            return deliberationStage.equals(StageDeliberation.ADOPTE.getName()) || deliberationStage.equals(StageDeliberation.REJETE.getName()) || deliberationStage.equals(StageDeliberation.COMMUNIQUE.getName());
        }
        return false;
    }

    public static String getPOUR() {
        return POUR;
    }

    public static String getCONTRE() {
        return CONTRE;
    }

    public static String getABSTENTION() {
        return ABSTENTION;
    }

    public static String getTOTAL() {
        return TOTAL;
    }

    public List<VoteBean> getVoteBeans() {
        return voteBeans;
    }
}
