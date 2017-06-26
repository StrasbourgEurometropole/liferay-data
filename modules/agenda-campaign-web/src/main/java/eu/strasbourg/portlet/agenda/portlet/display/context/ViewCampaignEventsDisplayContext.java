package eu.strasbourg.portlet.agenda.portlet.display.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.agenda.model.Campaign;
import eu.strasbourg.service.agenda.model.CampaignEvent;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.CampaignEventLocalServiceUtil;
import eu.strasbourg.service.agenda.service.CampaignLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.display.context.BaseDisplayContext;

public class ViewCampaignEventsDisplayContext extends BaseDisplayContext {

	protected SearchContainer<CampaignEvent> _searchContainer;
	private String _keywords;
	private Integer _status;
	private Long _themeId;
	private List<CampaignEvent> _campaignEvents;
	private List<AssetCategory> _themes;
	private Map<Integer, String> _statuses;

	public ViewCampaignEventsDisplayContext(RenderRequest request,
		RenderResponse response) {
		super(request, response);
	}

	/**
	 * Retourne le SearchContainer
	 */
	public SearchContainer<CampaignEvent> getSearchContainer()
		throws PortalException {
		if (this._searchContainer == null) {
			PortletURL iteratorURL = this._response.createRenderURL();
			iteratorURL.setParameter("keywords", this.getKeywords());
			iteratorURL.setParameter("themeId", String.valueOf(this.getThemeId()));
			iteratorURL.setParameter("statusId", String.valueOf(this.getStatusId()));
			
			this._searchContainer = new SearchContainer<CampaignEvent>(
				this._request, iteratorURL, null, "no-entries-were-found");

			this._searchContainer.setEmptyResultsMessageCssClass(
				"taglib-empty-result-message-header-has-plus-btn");
			this._searchContainer
				.setRowChecker(new EmptyOnClickRowChecker(this._response));
			this._searchContainer.setOrderByColParam("orderByCol");
			this._searchContainer.setOrderByTypeParam("orderByType");
		}
		return _searchContainer;
	}

	/**
	 * Retourne la liste des thèmes
	 */
	public List<AssetCategory> getThemes() throws PortalException {
		if (Validator.isNull(_themes)) {
			long companyId = PortalUtil.getDefaultCompanyId();
			long companyGroupId = CompanyLocalServiceUtil.getCompany(companyId)
				.getGroup().getGroupId();
			AssetVocabulary vocabulary = AssetVocabularyHelper
				.getEntityVocabulary(Event.class.getName(), "theme agenda",
					companyGroupId);
			if (vocabulary != null) {
				_themes = vocabulary.getCategories();
			}
		}
		return _themes;
	}

	/**
	 * Retourne la liste des statuts possibles
	 */
	public Map<Integer, String> getStatuses() {
		if (Validator.isNull(_statuses)) {
			Map<Integer, String> statuses = new HashMap<Integer, String>();
			statuses.put(WorkflowConstants.STATUS_DRAFT, "draft");
			statuses.put(WorkflowConstants.STATUS_APPROVED, "approved");
			statuses.put(WorkflowConstants.STATUS_DENIED, "denied");
			statuses.put(WorkflowConstants.STATUS_PENDING, "submitted");
			statuses.put(WorkflowConstants.STATUS_IN_TRASH,
				"deletion-requested");
			_statuses = statuses;
		}
		return _statuses;
	}

	/**
	 * Retourne les mots clés de recherche saisis
	 */
	public String getKeywords() {
		if (Validator.isNull(_keywords)) {
			_keywords = ParamUtil.getString(_request, "keywords");
		}
		return _keywords;
	}

	/**
	 * Retourne le filtre "thème"
	 */
	public long getThemeId() {
		if (Validator.isNull(_themeId)) {
			_themeId = ParamUtil.getLong(_request, "themeId");
		}
		return _themeId;
	}

	/**
	 * Retourne le nom du filtre "thème" sélectionné
	 * @throws PortalException 
	 */
	public String getThemeLabel() throws PortalException {
		Optional<AssetCategory> optionalTheme = this.getThemes().stream()
			.filter(t -> t.getCategoryId() == this.getThemeId()).findFirst();
		if(optionalTheme.isPresent()) {
			return optionalTheme.get().getTitleCurrentValue();
		} else {
			return "";
		}
	}

	/**
	 * Retourne le filtre "statut"
	 */
	public int getStatusId() {
		if (Validator.isNull(_status)) {
			_status = ParamUtil.getInteger(_request, "statusId", -1);
		}
		return _status;
	}

	public List<CampaignEvent> getCampaignEvents() throws PortalException {
		if (_campaignEvents == null) {
			_campaignEvents = CampaignEventLocalServiceUtil
				.findByKeywordThemeAndStatus(this.getKeywords(),
					this.getThemeId(), this.getStatusId(),
					this._themeDisplay.getUserId(),
					this._themeDisplay.getScopeGroupId(),
					this.getSearchContainer().getStart(),
					this.getSearchContainer().getEnd());
			long total = CampaignEventLocalServiceUtil
				.findByKeywordThemeAndStatusCount(this.getKeywords(),
					this.getThemeId(), this.getStatusId(),
					this._themeDisplay.getUserId(),
					this._themeDisplay.getScopeGroupId());
			this.getSearchContainer().setTotal((int) total);
		}
		return _campaignEvents;
	}

	public boolean isUserAManager() {
		List<Campaign> campaigns = CampaignLocalServiceUtil.getCampaigns(-1,
			-1);
		for (Campaign campaign : campaigns) {
			if (campaign.isManagedByUser(this._themeDisplay.getUserId())) {
				return true;
			}
		}
		return false;
	}

}
