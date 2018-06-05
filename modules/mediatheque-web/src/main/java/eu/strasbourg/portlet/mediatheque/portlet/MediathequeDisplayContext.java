package eu.strasbourg.portlet.mediatheque.portlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.portlet.mediatheque.association.AssociationResponse;
import eu.strasbourg.portlet.mediatheque.association.AssociationWebService;
import eu.strasbourg.portlet.mediatheque.borrower.BorrowerResponse;
import eu.strasbourg.portlet.mediatheque.configuration.MediathequeConfiguration;
import eu.strasbourg.portlet.mediatheque.dissociate.DissociateResponse;
import eu.strasbourg.portlet.mediatheque.dissociate.DissociateWebService;

public class MediathequeDisplayContext {

	private ThemeDisplay themeDisplay;
	private MediathequeConfiguration configuration;
	private String email;
	private String cardNumber;
	private String publikId;
	private BorrowerResponse borrower;

	public MediathequeDisplayContext(ThemeDisplay themeDisplay) {
		this.themeDisplay = themeDisplay;
		try {
			this.configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(MediathequeConfiguration.class);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	public MediathequeConfiguration getConfiguration() {
		return configuration;
	}

	public String getErrorText() {
		String error = "";
		Map<Locale, String> mapText = LocalizationUtil.getLocalizationMap(configuration.errorXML());
		for (Map.Entry<Locale, String> map : mapText.entrySet()) {
			if (themeDisplay.getLocale().toString().equals(map.getKey().toString())) {
				error = HtmlUtil.unescape(map.getValue());
				break;
			}
		}
		if (Validator.isNull(error)) {
			error = "No configuration";
		}
		return error;
	}

	public String getDemarcheText() {
		String demarche = "";
		Map<Locale, String> mapText = LocalizationUtil.getLocalizationMap(configuration.demarcheXML());
		for (Map.Entry<Locale, String> map : mapText.entrySet()) {
			if (themeDisplay.getLocale().toString().equals(map.getKey().toString())) {
				demarche = HtmlUtil.unescape(map.getValue());
				break;
			}
		}
		if (Validator.isNull(demarche)) {
			demarche = "No configuration";
		}
		return demarche;
	}

	public String getRetouURL() {
		String retourURL = configuration.retourURL();
		if (Validator.isNull(retourURL)) {
			retourURL = "#";
		}
		return retourURL;
	}

	public String getContactURL() {
		String contactURL = configuration.contactURL();
		if (Validator.isNull(contactURL)) {
			contactURL = "#";
		}
		return contactURL;
	}

	public String getMediathequeURL() {
		String mediathequeURL = configuration.mediathequeURL();
		if (Validator.isNull(mediathequeURL)) {
			mediathequeURL = "#";
		}
		return mediathequeURL;
	}

	public void setBorrower(BorrowerResponse borrower) {
		this.borrower = borrower;
		this.email = borrower.getEmail();
		this.cardNumber = borrower.getCardNumber();
	}

	public BorrowerResponse getBorrower() {
		return this.borrower;
	}
	
	public String getCardNumber() {
		return this.cardNumber;
	}

	public String getTransformEmail() {
		// transformation de l'email : xx******@xx****.fr
		String[] emailOriginal = this.email.split("@");
		String nom = emailOriginal[0];
		String[] domaine = emailOriginal[1].split("\\.");
		String transformEmail = nom.substring(0, 2);
		for (int i = 2; i < nom.length(); i++) {
			transformEmail += "*";
		}
		transformEmail += "@" + domaine[0].substring(0, 2);
		for (int i = 2; i < domaine[0].length(); i++) {
			transformEmail += "*";
		}
		transformEmail += "." + domaine[1];
		return transformEmail;
	}

	public String link(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		String template = "etape1";
		String number = ParamUtil.getString(request, "number");

		String error = null;
		if (Validator.isNull(number)) {
			error = "number-required";
		}
		if (number.contains("<")) {
			error = "invalid-characters";
		}
		if (Validator.isNull(error)) {
			AssociationResponse association = AssociationWebService.getResponse(this.getPublikID(request), number, getRetouURL());
			if (Validator.isNull(association.getCode_erreur())) {
				// attente d'activation de la part de l'utilisateur
				template = "etape2C";
				this.email = association.getResult();
			} else {
				switch (association.getCode_erreur()) {
				case "AUCUN_EMAIL":
					// son email n'est pas renseigné
					template = "etape2B";
					break;
				case "CARTE_DEJA_ASSOCIEE":
					// sa carte est déjà lié à un compte
					template = "etape2A";
					break;
				case "CARTE_DEJA_ASSOCIEE_MEME_COMPTE":
					// sa carte est déjà lié à son compte
					template = "etape2A";
					break;
				default:
					// erreur technique -> TECHNIQUE
					// le numéro de carte n'existe pas -> AUCUNE_CARTE
					// Le compte a déjà une carte associée. -> MEME_COMPTE_ASSOCIE
					template = "etape1";
					error = association.getErreur();
					request.setAttribute("error", error);
					request.setAttribute("number", HtmlUtil.escape(number));
					break;
				}
			}
		} else {
			request.setAttribute("error", error);
			request.setAttribute("number", HtmlUtil.escape(number));
		}
		return template;
	}

	public String dissociate(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		String template = "etape1";

		DissociateResponse dissociate = DissociateWebService.getResponse(this.getPublikID(request));
		if (Validator.isNull(dissociate.getCode_erreur())) {
			// attente d'activation de la part de l'utilisateur
			template = "etape1";
			request.setAttribute("deleteAssociation", "ok");
		} else {
			// erreur technique -> TECHNIQUE
			template = "etape4";
			String error = dissociate.getErreur();
			request.setAttribute("error", error);
		}
		return template;
	}

	public LocalDate getToday() {
		return LocalDate.now();
	}

	public String getVirtualHostName() {
		Group group = GroupLocalServiceUtil.fetchFriendlyURLGroup(this.themeDisplay.getCompanyId(), "/strasbourg.eu");
		return group.getPublicLayoutSet().getVirtualHostname();
	}

	// Récupération de l'id utilisateur
	public String getPublikID(PortletRequest resourceRequest) {
		if (Validator.isNull(this.publikId)) {
			LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(resourceRequest);
			HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();

			this.publikId = SessionParamUtil.getString(originalRequest, "publik_internal_id");
		}

		return this.publikId;
	}
}
