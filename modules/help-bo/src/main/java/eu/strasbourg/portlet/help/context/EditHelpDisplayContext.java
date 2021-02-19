package eu.strasbourg.portlet.help.context;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.service.InitiativeLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class EditHelpDisplayContext {
	
	private Initiative _help;
	private List<AssetCategory> _cities;
	private final RenderRequest _request;
	private final ThemeDisplay _themeDisplay;
	
	public EditHelpDisplayContext(RenderRequest request,
								  RenderResponse response) {
		this._request = request;
		this._themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
	}

	public Initiative getHelp() {
		long helpId = ParamUtil.getLong(_request, "helpId");
		if (_help == null && helpId > 0) {
			_help = InitiativeLocalServiceUtil.fetchInitiative(helpId);
		}
		return _help;
	}
	
	/**
	 * Renvoie les indexes des lieux par défaut
	 */
	public String getDefaultPlaceIndexes() throws PortalException {
		if (this.getHelp() != null) {
			List<PlacitPlace> places = this.getHelp().getPlacitPlaces();
			String indexes = "0";
			for (int i = 1; i <= places.size(); i++) {
				indexes += "," + i;
			}
			return indexes;
		}
		return "";
	}
	
	/**
	 * Retourne la liste des villes
	 */
	public List<AssetCategory> getCities() throws PortalException {
		if (Validator.isNull(this._cities)) {
			AssetVocabulary territoriesVocabulary = AssetVocabularyHelper
				.getGlobalVocabulary(VocabularyNames.TERRITORY);
			if (territoriesVocabulary != null) {
				this._cities = territoriesVocabulary.getCategories().stream().filter(c -> {
					try {
						return c.getAncestors().size() == 1;
					} catch (Exception e) {
						return false;
					}
				}).collect(Collectors.toList());
			}
		}
		return this._cities;
	}

	public Locale[] getAvailableLocales() {
		Set<Locale> availableLocalesSet = LanguageUtil.getSupportedLocales();
		Locale[] availableLocales = availableLocalesSet
			.toArray(new Locale[availableLocalesSet.size()]);
		return availableLocales;
	}

	/**
	 * @return True si le framework workflow est actif pour ce type d'entité
	 */
	public boolean isWorkflowEnabled() {
		return WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
			_themeDisplay.getCompanyId(), _themeDisplay.getCompanyGroupId(),
			Initiative.class.getName());
	}
		
	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
		this._themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.PROJECT_BO, StrasbourgPortletKeys.PROJECT_BO,
			actionId);
	}

}
