package eu.strasbourg.portlet.official.display.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.official.model.Official;
import eu.strasbourg.service.official.service.OfficialLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;

public class EditOfficialDisplayContext {
	public EditOfficialDisplayContext(RenderRequest request,
			RenderResponse response) {
		this._request = request;
		this._themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);
	}

	public Official getOfficial() throws PortalException {
		long officialId = ParamUtil.getLong(_request, "officialId");
		if (_official == null && officialId > 0) {
			_official = OfficialLocalServiceUtil.getOfficial(officialId);
		}
		return _official;
	}

	public Locale[] getAvailableLocales() {
		Set<Locale> availableLocalesSet = LanguageUtil
				.getAvailableLocales(_themeDisplay.getScopeGroupId());
		Locale[] availableLocales = availableLocalesSet
				.toArray(new Locale[availableLocalesSet.size()]);
		return availableLocales;
	}

	/**
	 * @return True si le framework workflow est actif pour ce type d'entité
	 */
	public boolean isWorkflowEnabled() {
		return WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
				_themeDisplay.getCompanyId(), _themeDisplay.getScopeGroupId(),
				Official.class.getName());
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
				this._themeDisplay.getScopeGroupId(),
				StrasbourgPortletKeys.OFFICIAL_BO,
				StrasbourgPortletKeys.OFFICIAL_BO, actionId);
	}

	/**
	 * Retourne les territoires
	 */
	public List<AssetCategory> getTerritories() {
		AssetVocabulary vocabulary = AssetVocabularyHelper.getVocabulary(
				VocabularyNames.TERRITORY,
				this._themeDisplay.getCompanyGroupId());
		if(Validator.isNotNull(vocabulary))
			return vocabulary.getCategories();
		return null;
	}

	/**
	 * Retourne les catégories Fonction ville
	 */
	public List<AssetCategory> getFonctionsCity() {
		AssetVocabulary vocabulary = AssetVocabularyHelper.getVocabulary(
				VocabularyNames.FONCTIONS_CITY,
				this._themeDisplay.getScopeGroupId());
		if(Validator.isNotNull(vocabulary))
			return vocabulary.getCategories();
		return null;
	}

	/**
	 * Retourne la liste des quartiers
	 */
	public List<AssetCategory> getDistricts() {
		List<AssetCategory> districts = new ArrayList<AssetCategory>();
		List<AssetCategory> territories = this.getTerritories();
		for (AssetCategory territory : territories) {
			try {
				if (territory.getAncestors().size() == 2) {
					districts.add(territory);
				}
			} catch (PortalException e) {
				continue;
			}
		}
		return districts;
	}

	/**
	 * Retourne les catégories Groupe Politique ville
	 */
	public List<AssetCategory> getPoliticalsGroupsCity() {
		AssetVocabulary vocabulary = AssetVocabularyHelper.getVocabulary(
				VocabularyNames.POLITICAL_GROUP_CITY,
				this._themeDisplay.getScopeGroupId());
		if(Validator.isNotNull(vocabulary))
			return vocabulary.getCategories();
		return null;
	}

	/**
	 * Retourne les catégories Fonction eurometropole
	 */
	public List<AssetCategory> getFonctionsEurometropole() {
		AssetVocabulary vocabulary = AssetVocabularyHelper.getVocabulary(
				VocabularyNames.FONCTIONS_EUROMETROPOLE,
				this._themeDisplay.getScopeGroupId());
		if(Validator.isNotNull(vocabulary))
			return vocabulary.getCategories();
		return null;
	}

	/**
	 * Retourne les catégories Fonction commune
	 */
	public List<AssetCategory> getFonctionsTown() {
		AssetVocabulary vocabulary = AssetVocabularyHelper.getVocabulary(
				VocabularyNames.FONCTIONS_TOWN,
				this._themeDisplay.getScopeGroupId());
		if(Validator.isNotNull(vocabulary))
			return vocabulary.getCategories();
		return null;
	}

	/**
	 * Retourne les communes
	 */
	public List<AssetCategory> getTowns() {
		List<AssetCategory> towns = new ArrayList<AssetCategory>();
		List<AssetCategory> territories = this.getTerritories();
		for (AssetCategory territory : territories) {
			try {
				List<AssetCategory> ancestors = territory.getAncestors();
				if (ancestors.size() == 1) {
					AssetCategory ancestor = ancestors.get(0);
					if(ancestor.getName().equals("France")){
						towns.add(territory);
					}
				}
			} catch (PortalException e) {
				continue;
			}
		}
		return towns;
	}

	/**
	 * Retourne les catégories Groupe Politique eurométropole
	 */
	public List<AssetCategory> getPoliticalsGroupsEurometropole() {
		AssetVocabulary vocabulary = AssetVocabularyHelper.getVocabulary(
				VocabularyNames.POLITICAL_GROUP_EUROMETROPOLE,
				this._themeDisplay.getScopeGroupId());
		if(Validator.isNotNull(vocabulary))
			return vocabulary.getCategories();
		return null;
	}

	/**
	 * Retourne les catégories Autres mandats
	 */
	public List<AssetCategory> getOthersMandates() {
		AssetVocabulary vocabulary = AssetVocabularyHelper.getVocabulary(
				VocabularyNames.OTHERS_MANDATES,
				this._themeDisplay.getScopeGroupId());
		if(Validator.isNotNull(vocabulary))
			return vocabulary.getCategories();
		return null;
	}

	private Official _official;

	private final RenderRequest _request;
	private final ThemeDisplay _themeDisplay;

}
