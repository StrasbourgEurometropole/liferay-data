package eu.strasbourg.portlet.projectpopup.action.petition;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.service.PetitionLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author alexandre.quere
 */

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_POPUP_WEB,
                "mvc.command.name=filePetition"
        },
        service = MVCActionCommand.class
)
public class filePetitionActionCommand implements MVCActionCommand {


    /**
     * le log
     */
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    /**
     * En attendant de faire un fichier properties, on utilise cette variable
     */
    public static final long QUOTA = 7000;

    @Override
    public boolean processAction(ActionRequest request, ActionResponse response) throws PortletException {
        String userName = ParamUtil.getString(request, "username");
        String publikID = getPublikID(request);
        boolean result = false;
        if (publikID == null || publikID.isEmpty())
            throw new PortletException("veuillez vous identifier/enregistrer");
        boolean isValid = validate(request);
        if (!isValid) {
            throw new PortletException("la validation des champs n'est pas passée");
        } else
            result = sendPetition(request, publikID);
        _log.info(userName);
        return result;
    }

    private boolean sendPetition(ActionRequest request, String publikID) throws PortletException {
        PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(publikID);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthday = ParamUtil.getDate(request, "birthday", dateFormat);
        String address = ParamUtil.getString(request, "address");
        String city = ParamUtil.getString(request, "city");
        long postalcode = ParamUtil.getLong(request, "postalcode");
        String phone = ParamUtil.getString(request, "phone");
        String title = ParamUtil.getString(request, "title");
        String description = ParamUtil.getString(request, "description");
        long projectId = ParamUtil.getLong(request, "project");
        long quartierId = ParamUtil.getLong(request, "quartier");
        long themeId = ParamUtil.getLong(request, "theme");
        ServiceContext sc;
        Petition petition;
        try {
            sc = ServiceContextFactory.getInstance(request);
            sc.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);
            petition = PetitionLocalServiceUtil.createPetition(sc);
            petition.setTitle(title);
            petition.setDescription(description);
            petition.setUserName(user.getUserName());
            petition.setQuotaSignature(QUOTA);
            petition.setUserId(user.getUserId());
            petition.setPetitionnaireAdresse(address);
            petition.setPetitionnaireBirthday(birthday);
            petition.setPetitionnaireCity(city);
            petition.setPetitionnaireFirstname(user.getFirstName());
            petition.setPetitionnaireLastname(user.getLastName());
            petition.setPetitionnairePostalCode(postalcode);
            petition.setPetitionnairePhone(phone);
            petition.setPetitionnaireEmail(user.getEmail());
            petition = PetitionLocalServiceUtil.updatePetition(petition, sc);
            AssetEntry assetEntry = petition.getAssetEntry();
            if (assetEntry == null)
                throw new PortalException("aucune assetCategory pour la pétition"
                        + petition.getPetitionId());
            long entryId = assetEntry.getEntryId();
            AssetCategoryLocalServiceUtil
                    .addAssetEntryAssetCategory(entryId, projectId);
            AssetEntryLocalServiceUtil
                    .addAssetCategoryAssetEntry(entryId,quartierId);
            AssetEntryLocalServiceUtil
                    .addAssetCategoryAssetEntry(entryId,themeId);
        } catch (PortalException e) {
            _log.error(e);
            throw new PortletException(e);
        }
        _log.info("pétition créé : " + petition);
        return true;
    }

    private boolean validate(ActionRequest request) {
        boolean isValid = true;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // title
        if (Validator.isNull(ParamUtil.getString(request, "title"))) {
            isValid = false;
        }

        // description
        if (Validator.isNull(ParamUtil.getString(request, "description"))) {
            isValid = false;
        }

        // birthday
        if (Validator.isNull(ParamUtil.getDate(request, "birthday", dateFormat))) {
            isValid = false;
        }

        // city
        if (Validator.isNull(ParamUtil.getString(request, "city"))) {
            isValid = false;
        }

        // address
        if (Validator.isNull(ParamUtil.getString(request, "address"))) {
            isValid = false;
        }

        // postalcode
        if (Validator.isNull(ParamUtil.getLong(request, "postalcode"))) {
            isValid = false;
        }

        // phone
        if (Validator.isNull(ParamUtil.getString(request, "phone"))) {
            isValid = false;
        }

        return isValid;
    }

    /**
     * Récupération du publik ID avec la session
     */
    private String getPublikID(PortletRequest request) {
        LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(request);
        HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
        return SessionParamUtil.getString(originalRequest, "publik_internal_id");
    }

}
