package eu.strasbourg.portlet.council.resource;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.council.model.Deliberation;
import eu.strasbourg.service.council.model.Procuration;
import eu.strasbourg.service.council.model.Vote;
import eu.strasbourg.service.council.service.*;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.COUNCIL_WEB,
                "mvc.command.name=submitVotes"
        },
        service = MVCResourceCommand.class
)
public class SubmitVotesResourceCommand  implements MVCResourceCommand {

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());
    private ResourceBundle bundle = ResourceBundleUtil.getBundle(
            "content.Language", this.getClass().getClassLoader());

    /** Paramètres de la requête **/
    private static final String PARAM_SESSION_ID = "sessionId";
    private static final String PARAM_DELIBERATION_ID = "deliberationId";
    private static final String PARAM_OFFICIAL_ID = "officialId";
    private static final String PARAM_OFFICIAL_VOTE = "officialVote";
    private static final String PARAM_OFFICIAL_PROCURATION_ID_1 = "officialProcurationId_1";
    private static final String PARAM_OFFICIAL_PROCURATION_VOTE_1 = "officialProcurationVote_1";
    private static final String PARAM_OFFICIAL_PROCURATION_ID_2 = "officialProcurationId_2";
    private static final String PARAM_OFFICIAL_PROCURATION_VOTE_2 = "officialProcurationVote_2";

    /** Services **/
    private DeliberationLocalService deliberationLocalService;
    private ProcurationLocalService procurationLocalService;
    private VoteLocalService voteLocalService;

    /** Tempons **/
    private long sessionId;
    private long deliberationId;
    private long officialId;
    private String officialVote;
    private long officialProcurationId_1;
    private String officialProcurationVote_1;
    private long officialProcurationId_2;
    private String officialProcurationVote_2;
    private String message;

    @Override
    public boolean serveResource(ResourceRequest request, ResourceResponse response) throws PortletException {
        boolean result = false;
        try {
            // Récupération des paramètres
            this.sessionId = ParamUtil.getLong(request, PARAM_SESSION_ID);
            this.deliberationId = ParamUtil.getLong(request, PARAM_DELIBERATION_ID);
            this.officialId = ParamUtil.getLong(request, PARAM_OFFICIAL_ID);
            this.officialVote = ParamUtil.getString(request, PARAM_OFFICIAL_VOTE);
            this.officialProcurationId_1 = ParamUtil.getLong(request, PARAM_OFFICIAL_PROCURATION_ID_1);
            this.officialProcurationVote_1 = ParamUtil.getString(request, PARAM_OFFICIAL_PROCURATION_VOTE_1);
            this.officialProcurationId_2 = ParamUtil.getLong(request, PARAM_OFFICIAL_PROCURATION_ID_2);
            this.officialProcurationVote_2 = ParamUtil.getString(request, PARAM_OFFICIAL_PROCURATION_VOTE_2);

            // Verification du business de la requête
            if (this.validate()) {
                ServiceContext sc = ServiceContextFactory.getInstance(request);


                // Si exite, enregistrement du vote de l'élu
                if (Validator.isNotNull(this.officialVote)) {
                    Vote officialVote = this.voteLocalService.createVote(sc);
                    officialVote.setOfficialId(this.officialId);
                    officialVote.setDeliberationId(this.deliberationId);
                    officialVote.setResult(this.officialVote);
                    this.voteLocalService.updateVote(officialVote, sc);
                }

                // Si exite, enregistrement de la 1ere procuration
                if (this.officialProcurationId_1 > 0 && Validator.isNotNull(this.officialProcurationVote_1)) {
                    Vote officialProcurationVote1 = this.voteLocalService.createVote(sc);
                    officialProcurationVote1.setOfficialId(this.officialProcurationId_1);
                    officialProcurationVote1.setResult(this.officialProcurationVote_1);
                    officialProcurationVote1.setOfficialProcurationId(this.officialId);
                    officialProcurationVote1.setDeliberationId(this.deliberationId);
                    this.voteLocalService.updateVote(officialProcurationVote1, sc);
                }

                // Si exite, enregistrement de la 2ème procuration
                if (this.officialProcurationId_2 > 0 && Validator.isNotNull(this.officialProcurationVote_2)) {
                    Vote officialProcurationVote2 = this.voteLocalService.createVote(sc);
                    officialProcurationVote2.setOfficialId(this.officialProcurationId_2);
                    officialProcurationVote2.setResult(this.officialProcurationVote_2);
                    officialProcurationVote2.setOfficialProcurationId(this.officialId);
                    officialProcurationVote2.setDeliberationId(this.deliberationId);
                    this.voteLocalService.updateVote(officialProcurationVote2, sc);
                }

                result = true;
            }

            // Complétion du JSON de retour
            JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
            jsonResponse.put("result", result);
            jsonResponse.put("message", this.message);

            // Recuperation de l'élément d'écriture de la réponse et envoie
            PrintWriter writer = response.getWriter();
            writer.print(jsonResponse.toString());
        } catch (IOException | PortalException e) {
            log.error(e);
        }

        return result;
    }

    /**
     * Validation des informations de la requête
     */
    private boolean validate() {
        // Vérification de l'existance de la délibération
        Deliberation deliberation = this.deliberationLocalService.fetchDeliberation(this.deliberationId);
        if (deliberation ==  null) {
            this.message = LanguageUtil.get(bundle, "deliberation-not-exist-error");
            return false;
        }

        // Vérification du statut de la délibération
        if (!deliberation.isVoteOuvert()) {
            this.message = LanguageUtil.get(bundle, "deliberation-not-open-for-vote-error");
            return false;
        }

        // Vérification qu'au moins un vote est rempli
        if (Validator.isNull(this.officialVote)
                && Validator.isNull(this.officialProcurationVote_1)
                && Validator.isNull(this.officialProcurationVote_2)) {
            this.message = LanguageUtil.get(bundle, "vote-empty-error");
            return false;
        }

        // Vérification que le vote n'existe pas déjà
        Vote vote = this.voteLocalService.findByDeliberationIdandOfficialId(
                this.deliberationId, this.officialId);
        if (vote != null) {
            this.message = LanguageUtil.get(bundle, "vote-already-register");
            return false;
        }

        // Vérification les procurations si elles existent
        Procuration procuration;
        if (this.officialProcurationId_1 > 0 && Validator.isNotNull(this.officialProcurationVote_1)) {
            procuration = this.procurationLocalService.findByCouncilSessionIdAndOfficialVotersAndUnavailableIds(
                    this.sessionId, this.officialId, this.officialProcurationId_1);
            if (procuration == null) {
                this.message = LanguageUtil.get(bundle, "procuration-voted-not-found-error");
                return false;
            }
        }
        if (this.officialProcurationId_2 > 0 && Validator.isNotNull(this.officialProcurationVote_2)) {
           procuration = this.procurationLocalService.findByCouncilSessionIdAndOfficialVotersAndUnavailableIds(
                    this.sessionId, this.officialId, this.officialProcurationId_2);
            if (procuration == null) {
                this.message = LanguageUtil.get(bundle, "procuration-voted-not-found-error");
                return false;
            }
        }

        return true;
    }

    @Reference(unbind = "-")
    protected void setDeliberationLocalService(DeliberationLocalService deliberationLocalService) {
        this.deliberationLocalService = deliberationLocalService;
    }

    @Reference(unbind = "-")
    protected void setProcurationLocalService(ProcurationLocalService procurationLocalService) {
        this.procurationLocalService = procurationLocalService;
    }

    @Reference(unbind = "-")
    protected void setVoteLocalService(VoteLocalService voteLocalService) {
        this.voteLocalService = voteLocalService;
    }

}
