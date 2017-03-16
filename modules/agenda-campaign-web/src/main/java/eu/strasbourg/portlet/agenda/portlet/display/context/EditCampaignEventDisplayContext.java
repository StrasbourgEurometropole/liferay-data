package eu.strasbourg.portlet.agenda.portlet.display.context;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;
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
				.getGlobalVocabulary("service gestionnaire");
			if (vocabulary != null) {
				EMSServices = vocabulary.getCategories();
			}
		}
		return EMSServices;
	}

	/**
	 * Retourne la liste des themes
	 */
	public List<AssetCategory> getThemes() throws PortalException {
		if (Validator.isNull(themes)) {
			AssetVocabulary vocabulary = AssetVocabularyHelper
				.getGlobalVocabulary("theme agenda");
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
				.getGlobalVocabulary("type agenda");
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
				.getGlobalVocabulary("public agenda");
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
				.getGlobalVocabulary("territoire");
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
    			indexes +=  "," + i;
    		}
    		return indexes;
		}
		return "";
	}

}
