package eu.strasbourg.portlet.projectpopup.resource;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.BudgetSupport;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.service.project.service.BudgetSupportLocalServiceUtil;
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component(
	immediate = true,
    property = {
            "javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_POPUP_WEB,
            "mvc.command.name=giveBudgetSupport"
    },
    service = MVCResourceCommand.class
)
public class GiveBudgetSupportResourceCommand implements MVCResourceCommand {

	// Constantes des ID de recuperation d'informations de la requete
	private static final String BIRTHDAY = "birthday";
    private static final String ADDRESS = "address";
    private static final String CITY = "city";
    private static final String POSTALCODE = "postalcode";
    private static final String PHONE = "phone";
    private static final String MOBILE = "mobile";
    private static final String SAVEINFO = "saveinfo";
    private static final String FORMATTED_DATE_PATTERN = "dd/MM/yyyy";

    @Override
    public boolean serveResource(ResourceRequest request, ResourceResponse response) throws PortletException {

        // Initialisations respectives de : resultat probant de la requete, sauvegarde ou non des informations Publik, message de retour
        boolean result = false;
        boolean saveInfo = false;

        // Initialisations respectives de : nombre de votes pour l'entite courante, nombre de votes de l'utilisateur pour l'entite courante, nombre de votes de l'utilisateur
        int nbUserSupports = 0;
        int nbUserEntrySupports = 0;
        int nbEntrySupports = 0;

        // Recuperation de l'utilsiteur Publik ayant lance la demande
        PublikUser user = null;
        String publikID = getPublikID(request);
        if (publikID != null && !publikID.isEmpty()) {
            user = PublikUserLocalServiceUtil.getByPublikUserId(publikID);
        }

        // Recuperation du budget participatif en question
        BudgetParticipatif budgetParticipatif = null;
        long entryID = ParamUtil.getLong(request, "entryId");
        try {
            AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(entryID);
            budgetParticipatif = BudgetParticipatifLocalServiceUtil.getBudgetParticipatif(assetEntry.getClassPK());
            // Recuperation du nombre de votes de l'utilisateur
            nbUserSupports = BudgetParticipatifLocalServiceUtil.countBudgetSupportedByPublikUserInPhase(
                    publikID,
                    budgetParticipatif.getPhase().getBudgetPhaseId());
            // Recuperation du nombre de votes de l'utilisateur pour l'entite courante
            nbUserEntrySupports = budgetParticipatif.getNbSupportOfUser(publikID);
            // Recuperation du nombre de votes pour l'entite courante
            nbEntrySupports = (int) budgetParticipatif.getNbSupports();
        } catch (PortalException e1) {
            _log.error(e1);
        }

        // Verification de la validite des informations
        String message = validate(publikID, user,  budgetParticipatif, nbUserSupports);
        if (message.equals("")) {

            // Mise a jour des informations du compte Publik si requete valide et demande par l'utilisateur
            saveInfo = ParamUtil.getBoolean(request, SAVEINFO);

            // Recuperation des informations du formulaire
            String address = HtmlUtil.stripHtml(ParamUtil.getString(request, ADDRESS));
            long postalcode = ParamUtil.getLong(request, POSTALCODE);
            String city = HtmlUtil.stripHtml(ParamUtil.getString(request, CITY));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat dateFormat = new SimpleDateFormat(FORMATTED_DATE_PATTERN);
            Date birthday = ParamUtil.getDate(request, BIRTHDAY, dateFormat);
            String dateNaiss = sdf.format(birthday);
            String phone = HtmlUtil.stripHtml(ParamUtil.getString(request, PHONE));
            String mobile = HtmlUtil.stripHtml(ParamUtil.getString(request, MOBILE));

            if (saveInfo) {
                PublikApiClient.setAllUserDetails(
                        publikID,
                        user.getLastName(),
                        address,
                        "" + postalcode,
                        city,
                        dateNaiss,
                        phone,
                        mobile
                );
            }

            BudgetSupport budgetSupport = BudgetSupportLocalServiceUtil.createBudgetSupport(getServiceContext(request));
            budgetSupport.setCitoyenAddress(address);
            budgetSupport.setCitoyenBirthday(birthday);
            budgetSupport.setCitoyenCity(city);
            budgetSupport.setCitoyenFirstname(user.getFirstName());
            budgetSupport.setCitoyenLastName(user.getLastName());
            budgetSupport.setCitoyenPostalCode(postalcode);
            budgetSupport.setCitoyenPhone(phone);
            if (!mobile.isEmpty())
                budgetSupport.setCitoyenMobilePhone(mobile);
            budgetSupport.setCitoyenMail(user.getEmail());
            budgetSupport.setPublikUserId(publikID);
            budgetSupport.setBudgetParticipatifId(budgetParticipatif.getBudgetParticipatifId());
            budgetSupport = BudgetSupportLocalServiceUtil.updateBudgetSupport(budgetSupport);
            _log.info("Soutien cree : " + budgetSupport);
            result = true;
            // MaJ des compteurs
            nbUserSupports++;
            nbUserEntrySupports++;
            nbEntrySupports++;

        }

        // Retour des informations de la requete en JSON
        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
        jsonResponse.put("result", result);
        jsonResponse.put("message", message);
        jsonResponse.put("savedInfo", saveInfo);

        JSONObject updatedSupportsInfo = JSONFactoryUtil.createJSONObject();

        updatedSupportsInfo.put("nbUserSupports", nbUserSupports);
        updatedSupportsInfo.put("nbUserEntrySupports", nbUserEntrySupports);
        updatedSupportsInfo.put("nbEntrySupports", nbEntrySupports);
        updatedSupportsInfo.put("nbSupportForActivePhase", budgetParticipatif.getPhase().getNumberOfVote());

        jsonResponse.put("updatedSupportsInfo", updatedSupportsInfo);

        // Recuperation de l'élément d'écriture de la réponse
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            _log.error(e);
        }
        writer.print(jsonResponse.toString());

        return result;
    }

    /**
     * Envoi de la demande de soutien
     * @return Si la demande s'est bien passee
     */
    private ServiceContext getServiceContext(ResourceRequest request) throws PortletException {
        ServiceContext sc = null;

        try {
            sc = ServiceContextFactory.getInstance(request);
            sc.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);
        } catch (PortalException e) {
            _log.error(e);
            throw new PortletException(e);
        }

        return sc;
    }

    /**
     * Verification des information du formulaire issu de la requete et validation
     * du contexte fonctionnel de la requete (ex: vote possible, entite perime, etc)
     * @return le message d'erreur s'il y a une erreur
     */
    private String validate(String publikID, PublikUser user, BudgetParticipatif budgetParticipatif, int nbUserSupports) {
        // utilisateur
        if (publikID == null || publikID.isEmpty()) {
            return "Utilisateur non reconnu";
        } else {
            if (user.isBanned()) {
                return "Vous ne pouvez soutenir ce projet";
            } else if (user.getPactSignature() == null) {
                return "Vous devez signer le Pacte pour soutenir ce projet";
            }
        }

        // budget participatif
        if (budgetParticipatif != null) {
            if (!budgetParticipatif.isVotable()) {
                return "Ce budget participatif n'est pas en phase de vote";
            }
        } else {
            return "Erreur lors de la recherche du budget participatif";
        }

        // nombre de votes de l'utilisateur
        if (nbUserSupports >= budgetParticipatif.getPhase().getNumberOfVote()) {
            return "Vous ne pouvez plus voter pour cette phase";
        }

        return ""; //désactivation du controle des champs  de l'usager suite à la désactivation de la popup au premier vote

    }

    /**
     * Recuperation du publik ID avec la session
     */
    private String getPublikID(PortletRequest request) {
        LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(request);
        HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
        return SessionParamUtil.getString(originalRequest, "publik_internal_id");
    }

    /**
     * Le log
     */
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}