package eu.strasbourg.portlet.projectpopup.action.petition;

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
                "mvc.command.name=signPetition"
        },
        service = MVCActionCommand.class
)
public class signPetitionActionCommand implements MVCActionCommand {
    /**
     * le log
     */
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
        String action = ParamUtil.getString(actionRequest, "cmd");
        if ("signPetition".equals(action)) {
            long entryID = ParamUtil.getLong(actionRequest, "entryID");
            if (entryID == -1)
                throw new PortletException("Une erreur est survenue avec cette pétition");
            String publikID = getPublikID(actionRequest);
            if (publikID == null || publikID.isEmpty())
                throw new PortletException("veuillez vous identifier/enregistrer");

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(publikID);
            Date birthday = ParamUtil.getDate(actionRequest, "birthday",dateFormat);
            String address = ParamUtil.getString(actionRequest, "address");
            String city = ParamUtil.getString(actionRequest, "city");
            long postalcode = ParamUtil.getLong(actionRequest, "postalcode");
            String mail = ParamUtil.getString(actionRequest, "mail");
            String phone = ParamUtil.getString(actionRequest, "phone");
            Petition petition = null;
            ServiceContext sc = null;
            try {
                petition = PetitionLocalServiceUtil.getPetition(entryID);
                sc = ServiceContextFactory.getInstance(actionRequest);
            } catch (PortalException e) {
                _log.error(e);
            }
            if (petition == null || sc == null) {
                throw new PortletException("la pétition est null");
            }
            Signataire signataire = SignataireLocalServiceUtil.createSignataire(sc);
            signataire.setSignataireName(user.getLastName());
            signataire.setUserName(user.getUserName());
            signataire.setUserId(user.getUserId());
            signataire.setBirthday(birthday);
            signataire.setAddress(address);
            signataire.setPostalCode(postalcode);
            signataire.setCity(city);
            signataire.setPublikUserId(user.getPublikId());
            signataire.setMail(mail);
            signataire.setMobilePhone(phone);
            signataire.setPhone(phone);
            signataire = SignataireLocalServiceUtil.addSignataire(signataire);
            return true;
        }
        return false;
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
