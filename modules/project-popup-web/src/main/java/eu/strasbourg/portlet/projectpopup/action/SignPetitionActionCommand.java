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
import com.liferay.portal.kernel.util.HtmlUtil;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
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
	
    public String publikId;
    public long entryId;
    public Petition petition;
    private PublikUser user;
    private Date birthday;
    private String address;
    private String city;
    private long postalcode;
    private String phone;
    private String mobile;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * le log
     */
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    @Override
    public boolean processAction(ActionRequest request, ActionResponse response) throws PortletException {

        // Recuperation de l'URL de redirection
        String redirectURL = ParamUtil.getString(request, REDIRECT_URL_PARAM);

        // Recuperation des identifiants assujetis a la requete
        this.entryId = ParamUtil.getLong(request, "entryId");
        this.publikId = getPublikID(request);
        
        if (this.entryId == 0)
            throw new PortletException("Une erreur est survenue avec cette p&eacute;tition");
        
        if (publikId == null || publikId.isEmpty())
            throw new PortletException("veuillez vous identifier/enregistrer");
        
        // Recuperation des champs
        this.birthday = ParamUtil.getDate(request, "birthday", dateFormat);
        this.address = HtmlUtil.stripHtml(ParamUtil.getString(request, "address"));
        this.city = HtmlUtil.stripHtml(ParamUtil.getString(request, "city"));
        this.postalcode = ParamUtil.getLong(request, "postalcode");
        this.phone = HtmlUtil.stripHtml(ParamUtil.getString(request, "phone"));
        this.mobile = HtmlUtil.stripHtml(ParamUtil.getString(request, "mobile"));

        // Validation de la requete
        if (!validate(request)) {
        	_log.error("Demande de signature non valide de la petition d'asset ID '" +
            							this.entryId +
            							"' par l'utilisateur a publik ID '" + 
            							this.publikId);
        }
        
        // Sauvegarde des infos utilisateur
        String saveInfo = ParamUtil.getString(request, "saveinfo");
        if (saveInfo.equals("save-info")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateNaiss = sdf.format(this.birthday);
            PublikApiClient.setAllUserDetails(
            		this.publikId, 
            		this.user.getLastName(), 
            		this.address, 
            		"" + this.postalcode, 
            		this.city, 
            		dateNaiss, 
            		this.phone,
            		this.mobile
            );
        }

        try {
			this.signPetition(request);
		} catch (PortalException e) {
			_log.error("Erreur lors de la signature de la petition " + this.petition.getPetitionId() + " : ", e);
		}

        try {
            response.sendRedirect(redirectURL);
        } catch (IOException e) {
            _log.error("erreur de redirection dans le sign petition : ", e);
            throw new PortletException(e);
        }

        return true;
    }  
    
    /**
     * Signature d'une petition
     * @param actionRequest la request
     * @throws PortletException PortletException
     * @throws PortalException 
     */
    private void signPetition(ActionRequest actionRequest) throws PortletException, PortalException {
        
        ServiceContext sc = ServiceContextFactory.getInstance(actionRequest);

        Signataire signataire = SignataireLocalServiceUtil.createSignataire(sc);
        
        signataire.setSignataireFirstname(this.user.getFirstName());
        signataire.setSignataireName(this.user.getLastName());
        signataire.setBirthday(this.birthday);
        signataire.setAddress(this.address);
        signataire.setPostalCode(this.postalcode);
        signataire.setCity(this.city);
        signataire.setMail(this.user.getEmail());
        signataire.setMobilePhone(this.mobile);
        signataire.setPhone(this.phone);
        signataire.setPublikUserId(this.user.getPublikId());
        signataire.setPetitionId(this.petition.getPetitionId());
        
        signataire = SignataireLocalServiceUtil.updateSignataire(signataire);
        
        _log.info("Signataire : " + signataire);

    }

    /**
     * méthode de validation des champs.
     *
     * @param request la request
     * @return la réponse.
     */
    private boolean validate(ActionRequest request) {
    	
    	// petition
    	try {
            AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(this.entryId);
            this.petition = PetitionLocalServiceUtil.getPetition(assetEntry.getClassPK());
        } catch (PortalException e) {
        	SessionErrors.add(request, "unfound-petition-error");
            _log.error(e);
            return false;
        }
    	if (this.petition == null) {
    		SessionErrors.add(request, "unfound-petition-error");
    		return false;
        }
        
        // utilisateur
        if (this.publikId == null ||this.publikId.isEmpty()) {
        	SessionErrors.add(request, "unfound-user-error");
        	return false;
        } else {
        	this.user = PublikUserLocalServiceUtil.getByPublikUserId(this.publikId);
        	
        	if (this.user.isBanned()) {
        		SessionErrors.add(request, "is-banned-error");
        		return false;
        	} else if (this.user.getPactSignature() == null) {
        		SessionErrors.add(request, "pact-unsigned-error");
        		return false;
        	}
        }
        
        // signature de l'utilisateur
        List<Signataire> signataireList;
		try {
			signataireList = SignataireLocalServiceUtil.findSignatairesByPetitionIdAndPublikUserId(
					this.petition.getPetitionId(), 
					this.user.getPublikId());
			
			Signataire signataireTemp = signataireList.stream().filter(signataire -> user.getPublikId().equals(signataire.getPublikUserId())).findAny().orElse(null);
			
			if (signataireTemp != null) {
				SessionErrors.add(request, "user-already-sign-error");
        		return false;
			}
		} catch (PortletException e) {
			SessionErrors.add(request, "unfound-sign-error");
    		return false;
		}
        
        // birthday
        if (Validator.isNull(birthday)) {
            SessionErrors.add(request, "birthday-error");
            return false;
        }

        // city
        if (Validator.isNull(city)) {
            SessionErrors.add(request, "city-error");
            return false;
        }
        
        // age legal de vote
        if (!checkLegalAge(birthday)) {
        	SessionErrors.add(request, "legalage-error");
        	return false;
        }
        
        return true;
        
    }
    
    /**
     * Verification d'une date de naissance correspondant a un age superieur a 16 ans
     * @return true --> superieur a 16 ans
     */
    private boolean checkLegalAge(Date birthday) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Instant instant = birthday.toInstant();
        LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
        LocalDate current = LocalDate.now(defaultZoneId);
        int result = Period.between(localDate, current).getYears();
        return result >= 16;
    }
    
    /**
     * Verification d'un code postal correspondant a strasbourg
     * @param postalcode
     * @return
     * @deprecated Controle supprime (pour le moment : 26/11/2018)
     */
    @Deprecated 
    private boolean checkPostalCode(long postalcode) {
        int param = Math.toIntExact(postalcode);
        return param >= 67000 && param <= 67100;
    }

    /**
     * 
     * @param city
     * @return
     * @deprecated Controle supprime (pour le moment : 26/11/2018)
     */
    @Deprecated
    private boolean checkCity(String city) {
        return "strasbourg".equals(city.toLowerCase());
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
