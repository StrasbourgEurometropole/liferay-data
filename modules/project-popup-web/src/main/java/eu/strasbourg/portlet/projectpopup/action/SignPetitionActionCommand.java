package eu.strasbourg.portlet.projectpopup.action;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.model.Signataire;
import eu.strasbourg.service.project.service.PetitionLocalServiceUtil;
import eu.strasbourg.service.project.service.SignataireLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static eu.strasbourg.portlet.projectpopup.ProjectPopupPortlet.REDIRECT_URL_PARAM;

/**
 * @author alexandre.quere
 */

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_POPUP_WEB,
                "mvc.command.name=signPetition"
        },
        service = MVCActionCommand.class
)
public class SignPetitionActionCommand implements MVCActionCommand {


    /**
     * le log
     */
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    @Override
    public boolean processAction(ActionRequest request, ActionResponse response) throws PortletException {
        String action = ParamUtil.getString(request, "cmd");
        // Recuperation de l'URL de redirection
        String redirectURL = ParamUtil.getString(request, REDIRECT_URL_PARAM);
        boolean result = false;
        //test
        if ("signPetition".equals(action)) {
            long entryID = ParamUtil.getLong(request, "entryId");
            if (entryID == 0)
                throw new PortletException("Une erreur est survenue avec cette pétition");
            String publikID = getPublikID(request);
            if (publikID == null || publikID.isEmpty())
                throw new PortletException("veuillez vous identifier/enregistrer");
            boolean isValid = validate(request);
            if (!isValid) {
                throw new PortletException("la validation des champs n'est pas passée");
            } else
                result = signPetition(request, entryID, publikID);
            try {
                response.sendRedirect(redirectURL);
            } catch (IOException e) {
                _log.error("erreur de redirection dans le sign petition : ",e);
                throw new PortletException(e);
            }
        }
        return result;
    }

    /**
     * méthode de validation des champs.
     *
     * @param request la request
     * @return la réponse.
     */
    private boolean validate(ActionRequest request) {
        boolean isValid = true;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // birthday
        if (Validator.isNull(ParamUtil.getDate(request, "birthday", dateFormat))) {
            SessionErrors.add(request, "birthday-error");
            isValid = false;
        }

        // city
        if (Validator.isNull(ParamUtil.getString(request, "city"))) {
            SessionErrors.add(request, "city-error");
            isValid = false;
        }

        // address
        if (Validator.isNull(ParamUtil.getString(request, "address"))) {
            SessionErrors.add(request, "address-error");
            isValid = false;
        }

        // postalcode
        if (Validator.isNull(ParamUtil.getLong(request, "postalcode"))) {
            SessionErrors.add(request, "postalcode-error");
            isValid = false;
        }

        // phone
        if (Validator.isNull(ParamUtil.getString(request, "phone"))) {
            SessionErrors.add(request, "phone-error");
            isValid = false;
        }

        return isValid;
    }

    /**
     * méthode de signature d'une pétition.
     *
     * @param actionRequest la request
     * @param entryID       l'identifiant
     * @param publikID      l'identifiant publique.
     * @return la réponse
     * @throws PortletException PortletException
     */
    private boolean signPetition(ActionRequest actionRequest, long entryID, String publikID) throws PortletException {
        PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(publikID);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthday = ParamUtil.getDate(actionRequest, "birthday", dateFormat);
        String address = ParamUtil.getString(actionRequest, "address");
        String city = ParamUtil.getString(actionRequest, "city");
        long postalcode = ParamUtil.getLong(actionRequest, "postalcode");
        if (0==postalcode)
            throw new PortletException("le code postal n'est pas compatible");
        String phone = ParamUtil.getString(actionRequest, "phone");
        Petition petition = null;
        ServiceContext sc = null;
        try {
            AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(entryID);
            petition = PetitionLocalServiceUtil.getPetition(assetEntry.getClassPK());
            sc = ServiceContextFactory.getInstance(actionRequest);
        } catch (PortalException e) {
            _log.error(e);
        }
        if (petition == null || sc == null) {
            throw new PortletException("la pétition est null");
        }
        List<Signataire> signataireList = SignataireLocalServiceUtil.
                findSignatairesByPetitionIdAndSignataireName(petition.getPetitionId(), user.getLastName());
        Signataire signataireTemp = signataireList.stream().filter(signataire -> user.getUserId() == signataire.getUserId()).findAny().orElse(null);
        if (signataireTemp == null) {
            Signataire signataire = SignataireLocalServiceUtil.createSignataire(sc);
            signataire.setSignataireName(user.getLastName());
            signataire.setUserName(user.getUserName());
            signataire.setUserId(user.getUserId());
            signataire.setBirthday(birthday);
            signataire.setAddress(address);
            signataire.setPostalCode(postalcode);
            signataire.setCity(city);
            signataire.setPublikUserId(user.getPublikId());
            signataire.setMail(user.getEmail());
            signataire.setMobilePhone(phone);
            signataire.setPhone(phone);
            signataire.setPetitionId(petition.getPetitionId());
            signataire = SignataireLocalServiceUtil.updateSignataire(signataire);
            _log.info("Signataire : "+signataire);
            return true;
        }else return false;
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
