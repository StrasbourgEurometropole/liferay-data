package eu.strasbourg.portlet.help.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.help.model.HelpProposal;
import eu.strasbourg.service.help.service.HelpProposalLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.HELP_BO,
		"mvc.command.name=saveHelpProposal"
	},
	service = MVCActionCommand.class
)
public class SaveHelpActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) 
			throws PortletException {
		
		try {
			ServiceContext sc = ServiceContextFactory.getInstance(request);
			
			// Validation
			boolean isValid = validate(request);
			if (!isValid) {
				// Si pas valide : on reste sur la page d'édition
				PortalUtil.copyRequestParameters(request, response);

				ThemeDisplay themeDisplay = (ThemeDisplay) request
					.getAttribute(WebKeys.THEME_DISPLAY);
				String portletName = (String) request
					.getAttribute(WebKeys.PORTLET_ID);
				PortletURL returnURL = PortletURLFactoryUtil.create(request,
					portletName, themeDisplay.getPlid(),
					PortletRequest.RENDER_PHASE);
				
				response.setRenderParameter("returnURL", returnURL.toString());
				response.setRenderParameter("cmd", "editHelpProposal");
				response.setRenderParameter("mvcPath","/help-bo-edit-help-proposal.jsp");
				return false;
			}

			// Edition de la initiative
			long helpProposalId = ParamUtil.getLong(request, "helpProposalId");
			HelpProposal helpProposal;
			if (helpProposalId == 0) {
				// Si elle n'existe pas (add/save), on la créé
				helpProposal = _helpProposalLocalService.createHelpProposal(sc);
			} else {
				// Si elle existe (edit), on la cherche
				helpProposal = _helpProposalLocalService.getHelpProposal(helpProposalId);
			}
			
			// ---------------------------------------------------------------
			// -------------------------- GENERALITES ------------------------
			// ---------------------------------------------------------------

			// Titre
			Map<Locale, String> title = LocalizationUtil.getLocalizationMap(request, "title");
			helpProposal.setTitleMap(title);

			// Détail de l'aide
			Map<Locale, String> description = LocalizationUtil.getLocalizationMap(request, "description");
			helpProposal.setDescriptionMap(description);

			// ---------------------------------------------------------------
			// -------------------------- DEPOSITAIRE ------------------------
			// ---------------------------------------------------------------

			// Adresse
			String address = ParamUtil.getString(request, "address");
			helpProposal.setAddress(address);

			// Ville
			String city = ParamUtil.getString(request, "city");
			helpProposal.setCity(city);

			// Code Postal
			long postalCode = ParamUtil.getLong(request, "postalCode");
			helpProposal.setPostalCode(postalCode);

			// Téléphone
			String phoneNumber = ParamUtil.getString(request, "phoneNumber");
			helpProposal.setPhoneNumber(phoneNumber);

			// Au nom de
			String inTheNameOf = ParamUtil.getString(request, "inTheNameOf");
			helpProposal.setInTheNameOf(inTheNameOf);

			// Langues parlées
			Map<Locale, String> spokenLanguages = LocalizationUtil.getLocalizationMap(request, "spokenLanguages");
			helpProposal.setSpokenLanguagesMap(spokenLanguages);
									
			// ---------------------------------------------------------------
			// -------------------------- MEDIAS -----------------------------
			// ---------------------------------------------------------------
			
			// Image
			Long imageId = ParamUtil.getLong(request, "imageId");
			helpProposal.setImageId(imageId);

			_helpProposalLocalService.updateHelpProposal(helpProposal, sc);

		} catch (PortalException e) {
			_log.error(e);
		}

		return true;
	}
	
	/**
	 * Validation des champs obligatoires
	 */
	private boolean validate(ActionRequest request) {
		// Variable à mettre à false quand une erreur est détectée
		boolean isValid = true;
		// Défini le format de date à utiliser pour les champs temporels 
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		// Adresse
		if (Validator.isNull(ParamUtil.getString(request, "address"))) {
			SessionErrors.add(request, "address-error");
			isValid = false;
		}

		// Ville
		if (Validator.isNull(ParamUtil.getString(request, "city"))) {
			SessionErrors.add(request, "city-error");
			isValid = false;
		}

		// Code postal
		if (Validator.isNull(ParamUtil.getString(request, "postalCode"))) {
			SessionErrors.add(request, "postal-code-error");
			isValid = false;
		}

		// Téléphone
		if (Validator.isNull(ParamUtil.getString(request, "phoneNumber"))) {
SessionErrors.add(request, "phone-number-error");
			isValid = false;
		}

		// Au nom de
		if (Validator.isNull(ParamUtil.getString(request, "inTheNameOf"))) {
			SessionErrors.add(request, "in-the-name-of-error");
			isValid = false;
		}

		return isValid;
	}
	
	@Reference(unbind = "-")
	protected void setHelpProposalLocalService(HelpProposalLocalService helpProposalLocalService) {
		_helpProposalLocalService = helpProposalLocalService;
	}
	
	private HelpProposalLocalService _helpProposalLocalService;
	
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
