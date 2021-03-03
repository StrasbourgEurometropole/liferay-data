package eu.strasbourg.portlet.help.action;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
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
import eu.strasbourg.utils.AssetVocabularyHelper;
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
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

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

			// Image
			Long imageId = ParamUtil.getLong(request, "imageId");
			helpProposal.setImageId(imageId);

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
			// ------------------------ MODERATEUR ---------------------------
			// ---------------------------------------------------------------

			// Commentaire
			Map<Locale, String> comment = LocalizationUtil.getLocalizationMap(request, "comment");
			helpProposal.setCommentMap(comment);

			long[] ids = sc.getAssetCategoryIds();
			// Mise du statut de modération en lue
			Boolean isRead = ParamUtil.getBoolean(request, "read");
			if(isRead) {
				List<Long> idsLong = Arrays.stream(ids).boxed().collect(Collectors.toList());

				AssetCategory nonLu = AssetVocabularyHelper.getCategory("Non Lue", sc.getScopeGroupId());
				if (nonLu != null && idsLong.indexOf(nonLu.getCategoryId()) >= 0)
					idsLong.remove(idsLong.indexOf(nonLu.getCategoryId()));

				AssetCategory lu = AssetVocabularyHelper.getCategory("Lue", sc.getScopeGroupId());
				if (lu != null)
					idsLong.add(lu.getCategoryId());

				sc.setAssetCategoryIds(idsLong.stream().mapToLong(w -> w).toArray());
			}

			// Mise de la ville de strasbourg si c'est un quartier
			for (long id : ids) {
				AssetCategory categ = AssetCategoryLocalServiceUtil.fetchAssetCategory(id).getParentCategory();
				if(categ != null && categ.getName().equals("Strasbourg")) {
					List<Long> idsLong = Arrays.stream(ids).boxed().collect(Collectors.toList());
					idsLong.add(categ.getCategoryId());
					sc.setAssetCategoryIds(idsLong.stream().mapToLong(w -> w).toArray());
					break;
				}
			}

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

		// Titre
		if (Validator.isNull(ParamUtil.getString(request, "title"))) {
			SessionErrors.add(request, "title-error");
			isValid = false;
		}

		// Description
		if (Validator.isNull(ParamUtil.getString(request, "descriptionEditor"))) {
			SessionErrors.add(request, "description-error");
			isValid = false;
		}

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
