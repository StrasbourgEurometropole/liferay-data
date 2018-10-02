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
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

    public String publikID;
    private PublikUser user;
    private Date birthday;
    private String address;
    private String city;
    private long postalcode;
    private String phone;
    private String mobile;
    private String lastname;
    private String email;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

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
                throw new PortletException("Une erreur est survenue avec cette p&eacute;tition");
            String publikID = getPublikID(request);
            if (publikID == null || publikID.isEmpty())
                throw new PortletException("veuillez vous identifier/enregistrer");

            user = PublikUserLocalServiceUtil.getByPublikUserId(publikID);
            birthday = ParamUtil.getDate(request, "birthday", dateFormat);
            address = ParamUtil.getString(request, "address");
            city = ParamUtil.getString(request, "city");
            postalcode = ParamUtil.getLong(request, "postalcode");
            phone = ParamUtil.getString(request, "phone");
            mobile = ParamUtil.getString(request, "mobile");
            lastname = ParamUtil.getString(request, "username");
            email = ParamUtil.getString(request, "mail");

            boolean isValid = validate(request);
            if (!isValid)
                throw new PortletException("la validation des champs n'est pas pass&eacute;e");

            // Sauvegarde des infos utilisateur
            String saveInfo = ParamUtil.getString(request, "saveinfo");
            if (saveInfo.equals("save-info")) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
                String dateNaiss = sdf.format(ParamUtil.getDate(request, "birthday", dateFormat));
                PublikApiClient.setAllUserDetails(publikID, user.getLastName(), address, "" + postalcode, city, dateNaiss, phone, mobile);
            }

            String message = signPetition(request, entryID, publikID);
            if (message.isEmpty()) {
                result = true;
            } else {
                _log.error(message);
            }

            try {
                response.sendRedirect(redirectURL);
            } catch (IOException e) {
                _log.error("erreur de redirection dans le sign petition : ", e);
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

        // birthday
        if (Validator.isNull(birthday)) {
            SessionErrors.add(request, "birthday-error");
            isValid = false;
        }

        // city
        if (Validator.isNull(city)) {
            SessionErrors.add(request, "city-error");
            isValid = false;
        }

        // address
        if (Validator.isNull(address)) {
            SessionErrors.add(request, "address-error");
            isValid = false;
        }

        // postalcode
        if (Validator.isNull(postalcode)) {
            SessionErrors.add(request, "postalcode-error");
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
    private String signPetition(ActionRequest actionRequest, long entryID, String publikID) throws PortletException {
        String message = "";
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
            message = "la p&eacute;tition est null";
        }
        List<Signataire> signataireList = SignataireLocalServiceUtil.
                findSignatairesByPetitionIdAndSignataireName(petition.getPetitionId(), user.getLastName());
        Signataire signataireTemp = signataireList.stream().filter(signataire -> user.getUserId() == signataire.getUserId()).findAny().orElse(null);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime birthTime = new Timestamp(birthday.getTime()).toLocalDateTime();
        long period = ChronoUnit.YEARS.between(birthTime,now);

        if (period<16)
            message = "vous devez avoir plus de 16 ans pour signer";
        else if (signataireTemp == null) {
            Signataire signataire = SignataireLocalServiceUtil.createSignataire(sc);
            signataire.setSignataireName(lastname);
            signataire.setUserName(user.getUserName());
            signataire.setUserId(user.getUserId());
            signataire.setBirthday(birthday);
            signataire.setAddress(address);
            signataire.setPostalCode(postalcode);
            signataire.setCity(city);
            signataire.setPublikUserId(user.getPublikId());
            signataire.setMail(email);
            signataire.setMobilePhone(mobile);
            signataire.setPhone(phone);
            signataire.setPetitionId(petition.getPetitionId());
            signataire = SignataireLocalServiceUtil.updateSignataire(signataire);
            _log.info("Signataire : " + signataire);
        } else {
            message = "Vous avez déjà signé la pétition";
        }

        return message;
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
