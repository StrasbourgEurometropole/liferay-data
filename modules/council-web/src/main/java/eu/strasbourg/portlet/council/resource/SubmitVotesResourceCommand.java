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
import org.hibernate.exception.ConstraintViolationException;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

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

    @Override
    public boolean serveResource(ResourceRequest request, ResourceResponse response) {
        boolean result = false;
        String message = "";
        Vote officialVote = null;
        Vote officialProcurationVote1 = null;
        Vote officialProcurationVote2 = null;
        try {

            // Récupération des paramètres
            long paramDeliberationId = ParamUtil.getLong(request, PARAM_DELIBERATION_ID);
            long paramOfficialId = ParamUtil.getLong(request, PARAM_OFFICIAL_ID);
            String paramOfficialVote = ParamUtil.getString(request, PARAM_OFFICIAL_VOTE);
            long paramOfficialProcurationId_1 = ParamUtil.getLong(request, PARAM_OFFICIAL_PROCURATION_ID_1);
            String paramOfficialProcurationVote_1 = ParamUtil.getString(request, PARAM_OFFICIAL_PROCURATION_VOTE_1);
            long paramOfficialProcurationId_2 = ParamUtil.getLong(request, PARAM_OFFICIAL_PROCURATION_ID_2);
            String paramOfficialProcurationVote_2 = ParamUtil.getString(request, PARAM_OFFICIAL_PROCURATION_VOTE_2);

            this.log.info(
                "Informations de vote avant enregistrement : [ " +
                        "id deliberation : " + paramDeliberationId + ", " +
                        "id official : " + paramOfficialId + ", " +
                        "vote official : " + paramOfficialVote +
                        (paramOfficialProcurationId_1 != 0 ? ", id official procuration 1 : " + paramOfficialProcurationId_1 : "") +
                        (!paramOfficialProcurationVote_1.equals("") ? ", vote official procuration 1 : " + paramOfficialProcurationVote_1 : "") +
                        (paramOfficialProcurationId_2 != 0 ? ", id official procuration 2 : " + paramOfficialProcurationId_2 : "") +
                        (!paramOfficialProcurationVote_2.equals("") ? ", vote official procuration 2 : " + paramOfficialProcurationVote_2 : "") +
                        " ]"
            );

            // Verification du business de la requête
            if (validate(request).equals("")) {
                ServiceContext sc = ServiceContextFactory.getInstance(request);

                try {
                    // Si exite, enregistrement du vote de l'élu
                    if (Validator.isNotNull(paramOfficialVote)) {
                        officialVote = this.voteLocalService.createVote(
                                paramOfficialId, paramDeliberationId, sc);
                        officialVote.setResult(paramOfficialVote);
                        officialVote = this.voteLocalService.updateVote(officialVote, sc);
                    }

                    // Si exite, enregistrement de la 1ere procuration
                    if (paramOfficialProcurationId_1 > 0 && Validator.isNotNull(paramOfficialProcurationVote_1)) {
                        officialProcurationVote1 = this.voteLocalService.createVote(
                                paramOfficialProcurationId_1, paramDeliberationId, sc);
                        officialProcurationVote1.setResult(paramOfficialProcurationVote_1);
                        officialProcurationVote1.setOfficialProcurationId(paramOfficialId);
                        officialProcurationVote1 = this.voteLocalService.updateVote(officialProcurationVote1, sc);
                    }

                    // Si exite, enregistrement de la 2ème procuration
                    if (paramOfficialProcurationId_2 > 0 && Validator.isNotNull(paramOfficialProcurationVote_2)) {
                        officialProcurationVote2 = this.voteLocalService.createVote(
                                paramOfficialProcurationId_2, paramDeliberationId, sc);
                        officialProcurationVote2.setResult(paramOfficialProcurationVote_2);
                        officialProcurationVote2.setOfficialProcurationId(paramOfficialId);
                        officialProcurationVote2 = this.voteLocalService.updateVote(officialProcurationVote2, sc);
                    }

                    result = true;

                } catch (ConstraintViolationException e) {
                    message = "";
                    this.log.error(e);
                }
            }

            this.log.info(
                "Informations de vote enregistees : [ " +
                        (officialVote != null ? "id deliberation : " + officialVote.getDeliberationId() : "") +
                        (officialVote != null ? ", id official : " + officialVote.getOfficialId() : "") +
                        (officialVote != null ? ", vote official : " + officialVote.getResult() : "") +
                        (officialProcurationVote1 != null ? ", id official procuration 1 : " + officialProcurationVote1.getOfficialId() : "") +
                        (officialProcurationVote1 != null ? ", vote official procuration 1 : " + officialProcurationVote1.getResult() : "") +
                        (officialProcurationVote2 != null ? ", id official procuration 2 : " + officialProcurationVote2.getOfficialId() : "") +
                        (officialProcurationVote2 != null ? ", vote official procuration 2 : " + officialProcurationVote2.getResult() : "") +
                        " ]"
            );

            // Complétion du JSON de retour
            JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
            jsonResponse.put("result", result);
            jsonResponse.put("message", message);

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
     * @note si message rempli alors erreur
     */
    private String validate(ResourceRequest request) {

        // Récupération des paramètres
        long paramSessionId = ParamUtil.getLong(request, PARAM_SESSION_ID);
        long paramDeliberationId = ParamUtil.getLong(request, PARAM_DELIBERATION_ID);
        long paramOfficialId = ParamUtil.getLong(request, PARAM_OFFICIAL_ID);
        String paramOfficialVote = ParamUtil.getString(request, PARAM_OFFICIAL_VOTE);
        long paramOfficialProcurationId_1 = ParamUtil.getLong(request, PARAM_OFFICIAL_PROCURATION_ID_1);
        String paramOfficialProcurationVote_1 = ParamUtil.getString(request, PARAM_OFFICIAL_PROCURATION_VOTE_1);
        long paramOfficialProcurationId_2 = ParamUtil.getLong(request, PARAM_OFFICIAL_PROCURATION_ID_2);
        String paramOfficialProcurationVote_2 = ParamUtil.getString(request, PARAM_OFFICIAL_PROCURATION_VOTE_2);

        // Vérification de l'existance de la délibération
        Deliberation deliberation = this.deliberationLocalService.fetchDeliberation(paramDeliberationId);
        if (deliberation ==  null) {
            return LanguageUtil.get(this.bundle, "deliberation-not-exist-error");
        }

        // Vérification du statut de la délibération
        if (!deliberation.isVoteOuvert()) {
            return LanguageUtil.get(bundle, "deliberation-not-open-for-vote-error");
        }

        // Vérification qu'au moins un vote est rempli
        if (Validator.isNull(paramOfficialVote)
                && Validator.isNull(paramOfficialProcurationVote_1)
                && Validator.isNull(paramOfficialProcurationVote_2)) {
            return LanguageUtil.get(this.bundle, "vote-empty-error");
        }

        // Vérification que le vote n'existe pas déjà
        Vote vote = this.voteLocalService.findByDeliberationIdandOfficialId(
                paramDeliberationId, paramOfficialId);
        if (vote != null) {
            return LanguageUtil.get(this.bundle, "vote-already-register");
        }

        // Vérification qu'il n'existe pas une procuration définissant l'absence de l'élu
        Procuration absenceProcuration = this.procurationLocalService.findAbsenceForCouncilSession(
                paramSessionId, paramOfficialId);
        if (absenceProcuration != null) {
            return LanguageUtil.get(this.bundle, "defined.as.absent.error");
        }

        // Vérification de l'exitence des procurations
        Procuration procuration;
        if (paramOfficialProcurationId_1 > 0 && Validator.isNotNull(paramOfficialProcurationVote_1)) {
            procuration = this.procurationLocalService.findByCouncilSessionIdAndOfficialVotersAndUnavailableIds(
                    paramSessionId, paramOfficialId, paramOfficialProcurationId_1);
            if (procuration == null) {
                return LanguageUtil.get(bundle, "procuration-voted-not-found-error");
            }
        }
        if (paramOfficialProcurationId_2 > 0 && Validator.isNotNull(paramOfficialProcurationVote_2)) {
           procuration = this.procurationLocalService.findByCouncilSessionIdAndOfficialVotersAndUnavailableIds(
                    paramSessionId, paramOfficialId, paramOfficialProcurationId_2);
            if (procuration == null) {
                return LanguageUtil.get(bundle, "procuration-voted-not-found-error");
            }
        }

        return "";
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
