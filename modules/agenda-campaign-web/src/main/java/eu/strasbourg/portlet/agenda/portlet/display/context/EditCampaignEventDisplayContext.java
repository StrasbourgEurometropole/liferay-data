package eu.strasbourg.portlet.agenda.portlet.display.context;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.service.agenda.model.Campaign;
import eu.strasbourg.service.agenda.model.CampaignEvent;
import eu.strasbourg.service.agenda.model.EventPeriod;
import eu.strasbourg.service.agenda.model.Manifestation;
import eu.strasbourg.service.agenda.service.CampaignEventLocalServiceUtil;
import eu.strasbourg.service.agenda.service.CampaignLocalServiceUtil;
import eu.strasbourg.service.agenda.service.ManifestationLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;
import eu.strasbourg.utils.display.context.BaseDisplayContext;

public class EditCampaignEventDisplayContext extends BaseDisplayContext {

	private CampaignEvent campaignEvent;
	private List<AssetCategory> EMSServices;
	private List<AssetCategory> themes;
	private List<AssetCategory> types;
	private List<AssetCategory> publics;
	private List<AssetCategory> cities;

	public EditCampaignEventDisplayContext(RenderRequest request,
		RenderResponse response) {
		super(request, response);
	}

	public CampaignEvent getCampaignEvent() throws PortalException {
		long campaignEventId = ParamUtil.getLong(_request, "campaignEventId");
		if (campaignEvent == null && campaignEventId > 0) {
			campaignEvent = CampaignEventLocalServiceUtil
				.getCampaignEvent(campaignEventId);
			
			if(campaignEvent != null) {
				Map<Locale, String> mapDescription = campaignEvent.getDescriptionMap();
				Map<Locale, String> mapPrice = campaignEvent.getPriceMap();
				
				for(Map.Entry<Locale, String> description : mapDescription.entrySet()) {
					if(description.getValue() != null) {
						String valueBr = description.getValue().replaceAll( "<br/>","\n");
						description.setValue(valueBr);
					}
				}
				
				for(Map.Entry<Locale, String> price : mapPrice.entrySet()) {
					if(price.getValue() != null) {
						String valueBr = price.getValue().replaceAll( "<br/>","\n");
						price.setValue(valueBr);
					}
				}
				
				campaignEvent.setDescriptionMap(mapDescription);
				campaignEvent.setPriceMap(mapPrice);
			}
			
		}

		return campaignEvent;
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
			CampaignEvent.class.getName());
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
			this._themeDisplay.getCompanyGroupId(),
			StrasbourgPortletKeys.AGENDA_BO, StrasbourgPortletKeys.AGENDA_BO,
			actionId);
	}

	/**
	 * Retourne la liste des campagnes en cours
	 */
	public List<Campaign> getCampaigns() {
		return CampaignLocalServiceUtil.getCampaigns(-1, -1).stream()
			.filter(c -> c.isApproved()
				&& c.getGroupId() == this._themeDisplay.getScopeGroupId())
			.collect(Collectors.toList());
	}

	/**
	 * Retourne la liste des services
	 */
	public List<AssetCategory> getEMSServices() throws PortalException {
		if (Validator.isNull(EMSServices)) {
			AssetVocabulary vocabulary = AssetVocabularyHelper
				.getGlobalVocabulary(VocabularyNames.EVENT_SERVICE);
			if (vocabulary != null) {
				EMSServices = vocabulary.getCategories();
			}
		}
		return EMSServices;
	}

	/**
	 * Retourne la map campaignId/themesIds (ou themesIds est la liste des ids
	 * des thèmes séparés par des virgules
	 */
	public String getCampaignThemes() {
		JSONObject campaignThemes = JSONFactoryUtil.createJSONObject();
		for (Campaign campaign : this.getCampaigns()) {
			String themesIds = "";
			for (AssetCategory theme : campaign.getThemes()) {
				if (themesIds.length() > 0) {
					themesIds += ",";
				}
				themesIds += theme.getCategoryId();
			}
			campaignThemes.put(String.valueOf(campaign.getCampaignId()), themesIds);
		}
		return campaignThemes.toJSONString();
	}
	
	/**
	 * Retourne la map themeId/themeLabel
	 */
	public String getThemeLabels() throws PortalException {
		JSONObject themeLabels = JSONFactoryUtil.createJSONObject();
		
		for (AssetCategory theme : this.getThemes()) {
			themeLabels.put(String.valueOf(theme.getCategoryId()), theme.getName());
		}
		return themeLabels.toJSONString();
	}

	/**
	 * Retourne la liste des themes
	 */
	public List<AssetCategory> getThemes() throws PortalException {
		if (Validator.isNull(themes)) {
			AssetVocabulary vocabulary = AssetVocabularyHelper
				.getGlobalVocabulary(VocabularyNames.EVENT_THEME);
			if (vocabulary != null) {
				themes = vocabulary.getCategories();
			}
		}
		return themes;
	}

	/**
	 * Retourne la liste des types
	 */
	public List<AssetCategory> getTypes() throws PortalException {
		if (Validator.isNull(types)) {
			AssetVocabulary vocabulary = AssetVocabularyHelper
				.getGlobalVocabulary(VocabularyNames.EVENT_TYPE);
			if (vocabulary != null) {
				types = vocabulary.getCategories();
			}
		}
		return types;
	}

	/**
	 * Retourne la liste des publics
	 */
	public List<AssetCategory> getPublics() throws PortalException {
		if (Validator.isNull(publics)) {
			AssetVocabulary vocabulary = AssetVocabularyHelper
				.getGlobalVocabulary(VocabularyNames.EVENT_PUBLIC);
			if (vocabulary != null) {
				publics = vocabulary.getCategories();
			}
		}
		return publics;
	}

	/**
	 * Retourne la liste des villes
	 */
	public List<AssetCategory> getCities() throws PortalException {
		if (Validator.isNull(cities)) {
			AssetVocabulary vocabulary = AssetVocabularyHelper
				.getGlobalVocabulary(VocabularyNames.TERRITORY);
			if (vocabulary != null) {
				cities = vocabulary.getCategories().stream().filter(c -> {
					try {
						return c.getAncestors().size() == 1;
					} catch (Exception e) {
						return false;
					}
				}).collect(Collectors.toList());
			}
		}
		return cities;
	}

	/**
	 * Retourne la liste des manifestations
	 */
	public List<Manifestation> getManifestations() throws PortalException {
		return ManifestationLocalServiceUtil.getManifestations(-1, -1).stream()
			.filter(m -> m.isApproved()).collect(Collectors.toList());
	}

	/**
	 * Retourne les indexes par défaut de l'autofield des périodes
	 */
	public String getDefaultPeriodIndexes() throws PortalException {
		if (this.getCampaignEvent() != null) {
			List<EventPeriod> periods = this.getCampaignEvent().getPeriods();
			String indexes = "0";
			for (int i = 1; i <= periods.size(); i++) {
				indexes += "," + i;
			}
			return indexes;
		}
		return "";
	}

}
