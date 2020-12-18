package eu.strasbourg.portlet.search_asset_v2;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import eu.strasbourg.portlet.search_asset_v2.action.ExportPDF;
import eu.strasbourg.portlet.search_asset_v2.configuration.SearchAssetConfiguration;
import eu.strasbourg.portlet.search_asset_v2.configuration.bean.ConfigurationAssetData;
import eu.strasbourg.portlet.search_asset_v2.constants.SearchAssetPortletKeys;
import eu.strasbourg.portlet.search_asset_v2.context.SearchAssetDisplayContext;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalService;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.service.project.service.InitiativeLocalService;
import eu.strasbourg.service.project.service.InitiativeLocalServiceUtil;
import eu.strasbourg.service.project.service.ParticipationLocalService;
import eu.strasbourg.service.project.service.ParticipationLocalServiceUtil;
import eu.strasbourg.service.project.service.PetitionLocalService;
import eu.strasbourg.service.project.service.PetitionLocalServiceUtil;
import eu.strasbourg.service.project.service.ProjectLocalServiceUtil;
import eu.strasbourg.service.video.model.Video;
import eu.strasbourg.service.video.service.VideoLocalServiceUtil;
import eu.strasbourg.utils.AssetPublisherTemplateHelper;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.LayoutHelper;
import eu.strasbourg.utils.SearchHelper;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jeremy.zwickert
 */

@Component(
		immediate = true,
		configurationPid = "eu.strasbourg.portlet.search_asset_v2.configuration.SearchAssetConfiguration",
		property = {
				"com.liferay.portlet.display-category=Strasbourg",
				"com.liferay.portlet.instanceable=false",
				"com.liferay.portlet.css-class-wrapper=search-asset-portlet",
				"com.liferay.portlet.single-page-application=false",
				"javax.portlet.version=3.0",
				"javax.portlet.init-param.template-path=/",
				"javax.portlet.init-param.view-template=/search-asset-view.jsp",
				"javax.portlet.init-param.check-auth-token=false",
				"javax.portlet.name=" + SearchAssetPortletKeys.SEARCHASSET,
				"javax.portlet.init-param.config-template=/search-asset-configuration.jsp",
				"javax.portlet.resource-bundle=content.Language",
				"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class SearchAssetPortlet extends MVCPortlet {

	public final static String PETITION = "eu.strasbourg.service.project.model.Petition";
	public final static String PARTICIPATION = "eu.strasbourg.service.project.model.Participation";
	public final static String BUDGET = "eu.strasbourg.service.project.model.BudgetParticipatif";
	public final static String INITIATIVE = "eu.strasbourg.service.project.model.Initiative";

	@Override
	public void render(RenderRequest renderRequest,
					   RenderResponse renderResponse) {
		try {
			// On set le DisplayContext
			this._dc = new SearchAssetDisplayContext(
					renderRequest, renderResponse);
			renderRequest.setAttribute("dc", getDisplayContext());

			this._configuration = this._dc.getConfiguration();


			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			long groupId = themeDisplay.getLayout().getGroupId();
			String userPublikId = getPublikID(renderRequest);

			// Boolean pour dire qu'on vient du portlet de recherche et non d'un
			// asset publisher (pour être utilisé dans les ADT si besoin
			renderRequest.setAttribute("fromSearchPortlet", true);

			// On envoie a la jsp la map className / layout qui fait
			// correspondre à chaque type d'asset une page de détail
			int i = 0;
			Map<String, Long> className_layoutId = new HashMap<>();
			List<ConfigurationAssetData> assetTypes = getDisplayContext().getConfigurationData().getAssetTypeDataList();
			for (ConfigurationAssetData assetType : assetTypes) {
				Layout layout = LayoutLocalServiceUtil.fetchLayoutByFriendlyURL(
						themeDisplay.getScopeGroupId(), false,
						assetType.getFriendlyURL());
				if (layout != null) {
					className_layoutId.put(assetType.getClassName(), layout.getPlid());
				}
				i++;
			}

			renderRequest.setAttribute("classNameLayoutId", className_layoutId);

			// Recuperation de l'URL de "base" du site
			String homeURL = "/";
			if (themeDisplay.getScopeGroup().getPublicLayoutSet().getVirtualHostname().isEmpty() || themeDisplay.getScopeGroup().isStagingGroup()) {
				homeURL = "/web" + themeDisplay.getLayout().getGroup().getFriendlyURL() + "/";
			}
			renderRequest.setAttribute("homeURL", homeURL);

			if(Validator.isNotNull(assetTypes) && assetTypes.size() > 0) {
				String className = assetTypes.get(0).getClassName();

				/*if (className.equals(PARTICIPATION)) {
					List<Participation> participationListMostCommented = _participationLocalService.getMostCommented(groupId);
					List<Participation> participationListLessCommented = _participationLocalService.getLessCommented(themeDisplay.getScopeGroupId());

					renderRequest.setAttribute("participationListMostCommented", participationListMostCommented);
					renderRequest.setAttribute("participationListLessCommented", participationListLessCommented);

				} else if (className.equals(PETITION)) {
					// Recuperer des objets des champs les plus/les moins.
					List<Petition> petitionListMostSigned = _petitionLocalService.getTheThreeMostSigned(themeDisplay.getScopeGroupId());
					List<Petition> petitionListLessSigned = _petitionLocalService.getTheThreeLessSigned(themeDisplay.getScopeGroupId());
					List<Petition> petitionListMostCommented = _petitionLocalService.getTheMostCommented(themeDisplay.getScopeGroupId());

					renderRequest.setAttribute("petitionListMostSigned", petitionListMostSigned);
					renderRequest.setAttribute("petitionListLessSigned", petitionListLessSigned);
					renderRequest.setAttribute("petitionListMostCommented", petitionListMostCommented);

				} else if (className.equals(BUDGET)) {

					//Recuperation de la categorie "Phase du budget participatif" configuree
					AssetCategory phase = null;
					for (String id : Arrays.asList(this._configuration.prefilterCategoriesIds().replace(';',',').split(","))) {
						phase = AssetCategoryLocalServiceUtil.getCategory(Long.parseLong(id));
						if(AssetVocabularyLocalServiceUtil.getVocabulary(phase.getVocabularyId()).getName().equals(VocabularyNames.PLACIT_BUDGET_PARTICIPATIF_PHASE))
							break;
						else
							phase = null;
					}

					//On recupere les bp des classements seulement si une phase est configuree
					if(phase != null) {
						List<BudgetParticipatif> budgetsMostSupported = _budgetParticipatifLocalService.getMostSupported(groupId, 3, phase);
						List<BudgetParticipatif> budgetsMostCommented = _budgetParticipatifLocalService.getMostCommented(groupId, 3, phase);
						List<BudgetParticipatif> budgetsIsCrush = _budgetParticipatifLocalService.getRecentIsCrushed(groupId, 3, phase);

						renderRequest.setAttribute("budgetsMostSupported", budgetsMostSupported);
						renderRequest.setAttribute("budgetsMostCommented", budgetsMostCommented);
						renderRequest.setAttribute("budgetsIsCrush", budgetsIsCrush);
					}

				} else if (className.equals(INITIATIVE)) {
					List<Initiative> initiativesMostLiked = _initiativeLocalService.getMostLiked(groupId, 3);
					List<Initiative> initiativesMostCommented = _initiativeLocalService.getMostCommented(groupId, 3);
					List<Initiative> initiativesMostHelped = _initiativeLocalService.getMostHelped(groupId, 3);

					renderRequest.setAttribute("initiativesMostLiked", initiativesMostLiked);
					renderRequest.setAttribute("initiativesMostCommented", initiativesMostCommented);
					renderRequest.setAttribute("initiativesMostHelped", initiativesMostHelped);
				}*/
			}

			renderRequest.setAttribute("isUserloggedIn", false);
			renderRequest.setAttribute("hasUserPactSign", false);
			renderRequest.setAttribute("isUserBanned", false);

			if (Validator.isNotNull(userPublikId)) {
				PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(userPublikId);
				if (user != null) {
					renderRequest.setAttribute("isUserloggedIn", true);
					renderRequest.setAttribute("hasUserPactSign", user.getPactSignature() != null);
					renderRequest.setAttribute("isUserBanned", user.isBanned());
				}
			}

			//Suppression des attributs de session
			HttpServletRequest request = PortalUtil.getLiferayPortletRequest(renderRequest).getHttpServletRequest();
			HttpSession session = request.getSession();
			ConcurrentHashMap portlet_render_parameters_ = ((ConcurrentHashMap) session.getAttribute("PORTLET_RENDER_PARAMETERS_"));
			portlet_render_parameters_.forEach((key,attribute)->{
				ConcurrentHashMap attributes = (ConcurrentHashMap) attribute;
				ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
				attributes.remove(td.getPpid());
			});
			super.render(renderRequest, renderResponse);
		} catch (Exception e) {
			_log.error(e);
		}
	}

	/**
	 * L'utilisateur a fait une recherche, on en profite pour set un attribut
	 */
	@Override
	public void processAction(ActionRequest actionRequest,
							  ActionResponse actionResponse)
			throws IOException, PortletException {

		actionRequest.setAttribute("userSearch", true);

		super.processAction(actionRequest, actionResponse);
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest,
							  ResourceResponse resourceResponse)
			throws IOException, PortletException {

		try {
			this._themeDisplay = (ThemeDisplay) resourceRequest
					.getAttribute(WebKeys.THEME_DISPLAY);
			this._configuration = this._themeDisplay
					.getPortletDisplay().getPortletInstanceConfiguration(
							SearchAssetConfiguration.class);

			this._request = resourceRequest;
			this._response = resourceResponse;

			String resourceID = resourceRequest.getResourceID();

			String publikUserId =  this.getPublikID(resourceRequest);

			// Verifions qu'il n'y ait pas d'entourloupe dans la solicitation
			// et réaction au type de la demande
			if (resourceID != null && resourceID.startsWith("entrySelection")) { // Nouvelle sélection de videos

				if (resourceID.equals("entrySelectionVideo")) {
					this._keywords = ParamUtil.getString(resourceRequest, "selectedKeyWords");
					this._startDay = ParamUtil.getInteger(resourceRequest, "selectedStartDay");
					this._startMonth = ParamUtil.getString(resourceRequest, "selectedStartMonth");
					this._startYear = ParamUtil.getInteger(resourceRequest, "selectedStartYear");
					this._endDay = ParamUtil.getInteger(resourceRequest, "selectedEndDay");
					this._endMonth = ParamUtil.getString(resourceRequest, "selectedEndMonth");
					this._endYear = ParamUtil.getInteger(resourceRequest, "selectedEndYear");
					this._states = new long[]{};
					this._statuts = new long[]{};
					this._bpStatus = new long[] {};
					this._initiativeStatus = new long[] {};
					this._projects = ParamUtil.getLongValues(resourceRequest, "selectedProject");
					this._districts = ParamUtil.getLongValues(resourceRequest, "selectedDistricts");
					this._thematics = ParamUtil.getLongValues(resourceRequest, "selectedThematics");
					this._types = new long[]{};
					this._sortFieldAndType = ParamUtil.getString(resourceRequest, "sortFieldAndType");
				}

				if (resourceID.equals("entrySelectionProject")) {
					this._keywords = ParamUtil.getString(resourceRequest, "selectedKeyWords");
					this._startDay = -1;
					this._startMonth = null;
					this._startYear = -1;
					this._endDay = -1;
					this._endMonth = null;
					this._endYear = -1;
					this._states = new long[]{};
					this._statuts = ParamUtil.getLongValues(resourceRequest, "selectedStatut");
					this._bpStatus = new long[] {};
					this._initiativeStatus = new long[] {};
					this._projects = new long[]{};
					this._districts = ParamUtil.getLongValues(resourceRequest, "selectedDistricts");
					this._thematics = ParamUtil.getLongValues(resourceRequest, "selectedThematics");
					this._types = new long[]{};
					this._sortFieldAndType = ParamUtil.getString(resourceRequest, "sortFieldAndType");
				}

				if (resourceID.equals("entrySelectionParticipation")) {
					this._keywords = ParamUtil.getString(resourceRequest, "selectedKeyWords");
					this._startDay = ParamUtil.getInteger(resourceRequest, "selectedStartDay");
					this._startMonth = ParamUtil.getString(resourceRequest, "selectedStartMonth");
					this._startYear = ParamUtil.getInteger(resourceRequest, "selectedStartYear");
					this._endDay = ParamUtil.getInteger(resourceRequest, "selectedEndDay");
					this._endMonth = ParamUtil.getString(resourceRequest, "selectedEndMonth");
					this._endYear = ParamUtil.getInteger(resourceRequest, "selectedEndYear");
					this._states = ParamUtil.getLongValues(resourceRequest, "selectedStates");
					this._statuts = new long[]{};
					this._bpStatus = new long[] {};
					this._initiativeStatus = new long[] {};
					this._projects = new long[]{};
					this._districts = ParamUtil.getLongValues(resourceRequest, "selectedDistricts");
					this._thematics = ParamUtil.getLongValues(resourceRequest, "selectedThematics");
					this._types = ParamUtil.getLongValues(resourceRequest, "selectedTypes");
					this._sortFieldAndType = ParamUtil.getString(resourceRequest, "sortFieldAndType");
				}

				if (resourceID.equals("entrySelectionAgenda")) {
					this._keywords = ParamUtil.getString(resourceRequest, "selectedKeyWords");
					this._startDay = ParamUtil.getInteger(resourceRequest, "selectedStartDay");
					this._startMonth = ParamUtil.getString(resourceRequest, "selectedStartMonth");
					this._startYear = ParamUtil.getInteger(resourceRequest, "selectedStartYear");
					this._endDay = ParamUtil.getInteger(resourceRequest, "selectedEndDay");
					this._endMonth = ParamUtil.getString(resourceRequest, "selectedEndMonth");
					this._endYear = ParamUtil.getInteger(resourceRequest, "selectedEndYear");
					this._states = new long[]{};
					this._statuts = new long[]{};
					this._bpStatus = new long[] {};
					this._initiativeStatus = new long[] {};
					this._projects = ParamUtil.getLongValues(resourceRequest, "selectedProject");
					this._districts = ParamUtil.getLongValues(resourceRequest, "selectedDistricts");
					this._thematics = ParamUtil.getLongValues(resourceRequest, "selectedThematics");
					this._types = new long[]{};
					this._sortFieldAndType = ParamUtil.getString(resourceRequest, "sortFieldAndType");
				}

				if (resourceID.equals("entrySelectionPetition")) {
					this._keywords = ParamUtil.getString(resourceRequest, "selectedKeyWords");
					this._startDay = ParamUtil.getInteger(resourceRequest, "selectedStartDay");
					this._startMonth = ParamUtil.getString(resourceRequest, "selectedStartMonth");
					this._startYear = ParamUtil.getInteger(resourceRequest, "selectedStartYear");
					this._endDay = ParamUtil.getInteger(resourceRequest, "selectedEndDay");
					this._endMonth = ParamUtil.getString(resourceRequest, "selectedEndMonth");
					this._endYear = ParamUtil.getInteger(resourceRequest, "selectedEndYear");
					this._states = ParamUtil.getLongValues(resourceRequest, "selectedStates");
					this._statuts = new long[]{};
					this._bpStatus = new long[] {};
					this._initiativeStatus = new long[] {};
					this._projects = new long[]{};
					this._districts = ParamUtil.getLongValues(resourceRequest, "selectedDistricts");
					this._thematics = ParamUtil.getLongValues(resourceRequest, "selectedThematics");
					this._types = new long[]{};
					this._sortFieldAndType = ParamUtil.getString(resourceRequest, "sortFieldAndType");
				}

				if (resourceID.equals("entrySelectionBudgetParticipatif")) {
					this._keywords = ParamUtil.getString(resourceRequest, "selectedKeyWords");
					this._startDay = ParamUtil.getInteger(resourceRequest, "selectedStartDay");
					this._startMonth = ParamUtil.getString(resourceRequest, "selectedStartMonth");
					this._startYear = ParamUtil.getInteger(resourceRequest, "selectedStartYear");
					this._endDay = ParamUtil.getInteger(resourceRequest, "selectedEndDay");
					this._endMonth = ParamUtil.getString(resourceRequest, "selectedEndMonth");
					this._endYear = ParamUtil.getInteger(resourceRequest, "selectedEndYear");
					this._states = new long[]{};
					this._statuts = new long[]{};
					this._bpStatus = ParamUtil.getLongValues(resourceRequest, "selectedBPStatus");
					this._initiativeStatus = new long[] {};
					this._projects = new long[]{};
					this._districts = ParamUtil.getLongValues(resourceRequest, "selectedDistricts");
					this._thematics = ParamUtil.getLongValues(resourceRequest, "selectedThematics");
					this._types = new long[]{};
					this._sortFieldAndType = ParamUtil.getString(resourceRequest, "sortFieldAndType");
				}

				if (resourceID.equals("entrySelectionInitiative")) {
					this._keywords = ParamUtil.getString(resourceRequest, "selectedKeyWords");
					this._startDay = ParamUtil.getInteger(resourceRequest, "selectedStartDay");
					this._startMonth = ParamUtil.getString(resourceRequest, "selectedStartMonth");
					this._startYear = ParamUtil.getInteger(resourceRequest, "selectedStartYear");
					this._endDay = ParamUtil.getInteger(resourceRequest, "selectedEndDay");
					this._endMonth = ParamUtil.getString(resourceRequest, "selectedEndMonth");
					this._endYear = ParamUtil.getInteger(resourceRequest, "selectedEndYear");
					this._states = new long[]{};
					this._statuts = new long[]{};
					this._bpStatus = new long[]{};
					this._initiativeStatus = ParamUtil.getLongValues(resourceRequest, "selectedInitiativeStatus");
					this._projects = new long[]{};
					this._districts = ParamUtil.getLongValues(resourceRequest, "selectedDistricts");
					this._thematics = ParamUtil.getLongValues(resourceRequest, "selectedThematics");
					this._types = new long[]{};
					this._sortFieldAndType = ParamUtil.getString(resourceRequest, "sortFieldAndType");
				}

				if (resourceID.equals("entrySelectionNews")) {
					this._keywords = null;
					this._startDay = ParamUtil.getInteger(resourceRequest, "selectedStartDay");
					this._startMonth = ParamUtil.getString(resourceRequest, "selectedStartMonth");
					this._startYear = ParamUtil.getInteger(resourceRequest, "selectedStartYear");
					this._endDay = ParamUtil.getInteger(resourceRequest, "selectedEndDay");
					this._endMonth = ParamUtil.getString(resourceRequest, "selectedEndMonth");
					this._endYear = ParamUtil.getInteger(resourceRequest, "selectedEndYear");
					this._states = ParamUtil.getLongValues(resourceRequest, "selectedStates");
					this._statuts = new long[]{};
					this._bpStatus = new long[] {};
					this._initiativeStatus = new long[] {};
					this._projects = new long[]{};
					this._districts = ParamUtil.getLongValues(resourceRequest, "selectedDistricts");
					this._thematics = ParamUtil.getLongValues(resourceRequest, "selectedThematics");
					this._types = new long[]{};
					this._sortFieldAndType = ParamUtil.getString(resourceRequest, "sortFieldAndType");
				}

				// Recherche des vidéos
				List<AssetEntry> entries = searchEntries();

				// Récupération du json des entités
				JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
				JSONArray jsonEntries = JSONFactoryUtil.createJSONArray();
				for (AssetEntry entry : entries) {
					String className = entry.getClassName();

					switch (className) {
						case "eu.strasbourg.service.agenda.model.Event":
							Event event = EventLocalServiceUtil.fetchEvent(entry.getClassPK());
							JSONObject jsonEvent = JSONFactoryUtil.createJSONObject();
							jsonEvent.put("class", className);
							JSONObject json = event.toJSON();
							json.put("eventScheduleDisplay", event.getCurrentOrFuturePeriodStringDate());
							json.put("placeAlias", event.getPlaceAlias(Locale.FRANCE));
							jsonEvent.put("json", json);
							jsonEntries.put(jsonEvent);
							break;
						case "eu.strasbourg.service.project.model.Project":
							Project project = ProjectLocalServiceUtil.fetchProject(entry.getClassPK());
							JSONObject jsonProject = JSONFactoryUtil.createJSONObject();
							jsonProject.put("class", className);
							json = project.toJSON(publikUserId);
							json.put("nbParticipations", project.getParticipations().size());
							json.put("nbEvents", project.getEvents().size());
							JSONArray jsonThematicCategoriesTitle = JSONFactoryUtil.createJSONArray();
							List<AssetCategory> thematicCategories = project.getThematicCategories();
							for (AssetCategory assetCategory : thematicCategories) {
								jsonThematicCategoriesTitle.put(JSONHelper.getJSONFromI18nMap(assetCategory.getTitleMap()));
							}
							json.put("jsonThematicCategoriesTitle", jsonThematicCategoriesTitle);
							jsonProject.put("json", json);
							jsonEntries.put(jsonProject);
							break;
						case "eu.strasbourg.service.project.model.Participation":
							Participation participation = ParticipationLocalServiceUtil.fetchParticipation(entry.getClassPK());
							JSONObject jsonParticipation = JSONFactoryUtil.createJSONObject();
							jsonParticipation.put("class", className);
							json = participation.toJSON(this._themeDisplay);
							json.put("todayExpirationDifferenceDays", participation.getTodayExpirationDifferenceDays());
							json.put("isJudgeable", participation.isJudgeable());
							json.put("groupId", participation.getGroupId());
							LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(resourceRequest);
							HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
							json.put("hasPactSigned", originalRequest.getSession().getAttribute("has_pact_signed"));
							jsonThematicCategoriesTitle = JSONFactoryUtil.createJSONArray();
							thematicCategories = participation.getThematicCategories();
							for (AssetCategory assetCategory : thematicCategories) {
								jsonThematicCategoriesTitle.put(JSONHelper.getJSONFromI18nMap(assetCategory.getTitleMap()));
							}
							json.put("jsonThematicCategoriesTitle", jsonThematicCategoriesTitle);
							jsonParticipation.put("json", json);
							jsonEntries.put(jsonParticipation);
							break;
						case "eu.strasbourg.service.project.model.Petition":
							Petition petition = PetitionLocalServiceUtil.fetchPetition(entry.getClassPK());
							JSONObject jsonPetition = JSONFactoryUtil.createJSONObject();
							jsonPetition.put("class", className);
							json = petition.toJSON(publikUserId);
							jsonThematicCategoriesTitle = JSONFactoryUtil.createJSONArray();
							thematicCategories = petition.getThematicCategories();
							for (AssetCategory assetCategory : thematicCategories) {
								jsonThematicCategoriesTitle.put(JSONHelper.getJSONFromI18nMap(assetCategory.getTitleMap()));
							}
							json.put("jsonThematicCategoriesTitle", jsonThematicCategoriesTitle);
							json.put("jsonProjectCategoryTitle", JSONHelper.getJSONFromI18nMap((Validator.isNull(petition.getProjectCategory())? LocalizationUtil.getLocalizationMap("") : petition.getProjectCategory().getTitleMap())));
							jsonPetition.put("json", json);
							jsonEntries.put(jsonPetition);
							break;
						case "eu.strasbourg.service.project.model.BudgetParticipatif":
							BudgetParticipatif budgetParticipatif = BudgetParticipatifLocalServiceUtil.fetchBudgetParticipatif(entry.getClassPK());
							JSONObject jsonBudget = JSONFactoryUtil.createJSONObject();
							jsonBudget.put("class", className);
							jsonBudget.put("json", budgetParticipatif.toJSON(publikUserId));
							jsonEntries.put(jsonBudget);
							break;
						case "eu.strasbourg.service.project.model.Initiative":
							Initiative initiative = InitiativeLocalServiceUtil.fetchInitiative(entry.getClassPK());
							JSONObject jsonInitiative = JSONFactoryUtil.createJSONObject();
							jsonInitiative.put("class", className);
							jsonInitiative.put("json", initiative.toJSON());
							jsonEntries.put(jsonInitiative);
							break;
						case "eu.strasbourg.service.video.model.Video":
							Video video = VideoLocalServiceUtil.fetchVideo(entry.getClassPK());
							JSONObject jsonVideo = JSONFactoryUtil.createJSONObject();
							jsonVideo.put("class", className);
							jsonVideo.put("json", video.toJSON());
							jsonEntries.put(jsonVideo);
							break;
						case "com.liferay.journal.model.JournalArticle":
							JournalArticle journalArticle = JournalArticleLocalServiceUtil.getLatestArticle(entry.getClassPK());
							JSONObject jsonJournalArticle = JSONFactoryUtil.createJSONObject();
							jsonJournalArticle.put("class", className);
							json = JSONFactoryUtil.createJSONObject();
							json.put("detailURL", LayoutHelper.getJournalArticleLayoutURL(journalArticle.getGroupId(), journalArticle.getArticleId(), this._themeDisplay));
							String document = journalArticle.getContentByLocale(LocaleUtil.toLanguageId(Locale.FRANCE));
							com.liferay.portal.kernel.xml.Document docXML = SAXReaderUtil.read(document);
							String title = docXML.valueOf("//dynamic-element[@name='title']/dynamic-content/text()");
							if (Validator.isNull(title)) {
								title = journalArticle.getTitle(Locale.FRANCE);
							}
							json.put("title", title);
							String thumbnail = docXML.valueOf("//dynamic-element[@name='thumbnail']/dynamic-content/text()");
							String imageURL ="";
							if(!thumbnail.isEmpty()){
								imageURL = AssetPublisherTemplateHelper.getDocumentUrl(thumbnail);
							}
							json.put("thumbnail", imageURL);
							JSONArray jsonVocabulariesTitle = JSONFactoryUtil.createJSONArray();
							AssetEntry asset = AssetEntryLocalServiceUtil.getAssetEntry(entry.getEntryId());
							List<AssetCategory> listVocabulary = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(asset, "territoire");
							for (AssetCategory assetCategory : listVocabulary) {
								jsonVocabulariesTitle.put(JSONHelper.getJSONFromI18nMap(assetCategory.getTitleMap()));
							}
							json.put("jsonVocabulariesTitle", jsonVocabulariesTitle);
							SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
							json.put("modifiedDate", dateFormat.format(journalArticle.getModifiedDate()));
							String chapo = docXML.valueOf("//dynamic-element[@name='chapo']/dynamic-content/text()");
							json.put("chapo", chapo.replaceAll("<[^>]*>", "").substring(0, chapo.length() > 100 ? 100 : chapo.length()));
							jsonJournalArticle.put("json", json);
							jsonEntries.put(jsonJournalArticle);
							break;
					}
				}
				jsonResponse.put("entries", jsonEntries);

				// Recuperation de l'élément d'écriture de la réponse
				PrintWriter writer = resourceResponse.getWriter();
				writer.print(jsonResponse.toString());

			} else { // pour l'export PDF
				String exportType = this._configuration.exportType();
				ExportPDF.printPDF(resourceRequest, resourceResponse, exportType);
			}
		} catch (Exception e2) {
			_log.error(e2);
		}
		super.serveResource(resourceRequest, resourceResponse);
	}

	/**
	 * Effectue concrètement la recherche
	 */
	private List<AssetEntry> searchEntries() throws ConfigurationException {
		HttpServletRequest servletRequest = PortalUtil.getHttpServletRequest(this._request);

		SearchContext searchContext = SearchContextFactory.getInstance(servletRequest);

		// Mots clés
		String keywords = this._keywords;

		/*******************/
		/* Type de contenu */
		/*******************/
		// ClassNames de la configuration
		String[] classNames = ArrayUtil.toStringArray(getDisplayContext().getClassNames());

		// Inclusion ou non du scope global
		boolean globalScope = true;
		//boolean globalScope = this._configuration.globalScope();
		long globalGroupId = this._themeDisplay.getCompanyGroupId();

		// Group ID courant
		long groupId = this._themeDisplay.getScopeGroupId();

		// Préfiltre catégories
		List<Long[]> prefilterCategoriesIds = new ArrayList<>();
		/*String prefilterCategoriesIdsString = this._configuration.prefilterCategoriesIds();
		for (String prefilterCategoriesIdsGroupByVocabulary : prefilterCategoriesIdsString.split(";")) {
			Long[] prefilterCategoriesIdsForVocabulary = ArrayUtil
					.toLongArray(StringUtil.split(prefilterCategoriesIdsGroupByVocabulary, ",", 0));
			prefilterCategoriesIds.add(prefilterCategoriesIdsForVocabulary);
		}*/

		// Préfiltre tags
		String[] prefilterTagsNames = null;
		/*String prefilterTagsNamesString = this._configuration.prefilterTagsNames();
		String[] prefilterTagsNames = StringUtil.split(prefilterTagsNamesString);*/

		/**********************************/
		/* Critères de recherche et Filres*/
		/**********************************/
		// affiche le filtre par date
		boolean dateField = this._startDay != -1 && this._configuration.displayDateField();

		// Filtre
		String dateFieldName = this._configuration.filterField();
		LocalDate fromDate = null;
		LocalDate toDate = null;
		if (dateField) {
			fromDate = LocalDate.of(getFromYear(), getFromMonthValue(), getFromDay());
			toDate = LocalDate.of(getToYear(), getToMonthValue(), getToDay());
		}

		/********/
		/* Tris */
		/********/
		// Ordre
		String sortField = getSortField();
		boolean isSortDesc = getSortType().contains("desc");

		// Catégories sélectionnées par l'utilisateur
		List<Long[]> categoriesIds = this.getFilterCategoriesIds();


		// Permet de remonter la hiérarchie des Request
		HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(servletRequest);

		// Lieu (pour la recherche agenda)
		String idSIGPlace = ParamUtil.getString(originalRequest, "idSIGPlace");

		// Recherche
		this._hits = SearchHelper.getGlobalSearchHits(searchContext, classNames, groupId, globalGroupId, globalScope,
				keywords, dateField, dateFieldName, fromDate, toDate, categoriesIds, prefilterCategoriesIds,
				prefilterTagsNames, idSIGPlace, false, this._themeDisplay.getLocale(), -1,
				-1, sortField, isSortDesc);

		List<AssetEntry> results = new ArrayList<>();
		if (this._hits != null) {
			int i = 0;
			for (float s : this._hits.getScores()) {
				_log.info(GetterUtil.getString(this._hits.getDocs()[i].get(Field.TITLE)) + " : " + s);
				i++;
				if (i > 10)
					break;
			}

			for (Document document : this._hits.getDocs()) {
				AssetEntry entry = AssetEntryLocalServiceUtil.fetchEntry(
						GetterUtil.getString(document.get(Field.ENTRY_CLASS_NAME)),
						GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
				if (entry != null) {
					results.add(entry);
				}
			}
			SearchHelper.getGlobalSearchCount(searchContext, classNames, groupId, globalGroupId,
					globalScope, keywords, dateField, dateFieldName, fromDate, toDate, categoriesIds,
					prefilterCategoriesIds, prefilterTagsNames, idSIGPlace, this._themeDisplay.getLocale());
		}

		return results;
	}

	public SearchAssetDisplayContext getDisplayContext() {
		return this._dc;
	}

	/**
	 * Renvoie la liste des catégories sur lesquelles on souhaite filtrer les
	 * entries. L'opérateur entre chaque id de catégorie d'un array est un "OU", celui entre chaque liste d'array est un "ET"
	 */
	private List<Long[]> getFilterCategoriesIds() {
		List<Long[]> filterCategoriesIds = new ArrayList<>();
		List<Long> categoriesIds = new ArrayList<>();

		// On récupère les états s'il y en a
		for (long state : this._states) {
			if (state > 0) {
				categoriesIds.add(state);
			}
		}
		if (categoriesIds.size() > 0) {
			filterCategoriesIds.add(ArrayUtil.toLongArray(categoriesIds.stream().mapToLong(l -> l).toArray()));
		}

		// On récupère les statuts s'il y en a
		for (long statut : this._statuts) {
			if (statut > 0) {
				categoriesIds.add(statut);
			}
		}
		if (categoriesIds.size() > 0) {
			filterCategoriesIds.add(ArrayUtil.toLongArray(categoriesIds.stream().mapToLong(l -> l).toArray()));
		}

		// On recupere les statuts BP s'il y en a
		for (long bpStatus : this._bpStatus) {
			if (bpStatus > 0) {
				categoriesIds.add(bpStatus);
			}
		}
		if (categoriesIds.size() > 0) {
			filterCategoriesIds.add(ArrayUtil.toLongArray(categoriesIds.stream().mapToLong(l -> l).toArray()));
		}

		// On recupere les statuts initiative s'il y en a
		for (long initiativeStatus : this._initiativeStatus) {
			if (initiativeStatus > 0) {
				categoriesIds.add(initiativeStatus);
			}
		}
		if (categoriesIds.size() > 0) {
			filterCategoriesIds.add(ArrayUtil.toLongArray(categoriesIds.stream().mapToLong(l -> l).toArray()));
		}

		// On récupère les projets s'il y en a
		for (long project : this._projects) {
			if (project > 0) {
				categoriesIds.add(project);
			}
		}
		if (categoriesIds.size() > 0) {
			filterCategoriesIds.add(ArrayUtil.toLongArray(categoriesIds.stream().mapToLong(l -> l).toArray()));
		}

		// On récupère les quartiers s'il y en a
		categoriesIds = new ArrayList<>();
		for (long district : this._districts) {
			if (district > 0) {
				categoriesIds.add(district);
			}
		}
		if (categoriesIds.size() > 0) {
			filterCategoriesIds.add(ArrayUtil.toLongArray(categoriesIds.stream().mapToLong(l -> l).toArray()));
		}

		// On récupère les thématiques s'il y en a
		categoriesIds = new ArrayList<>();
		for (long thematic : this._thematics) {
			if (thematic > 0) {
				categoriesIds.add(thematic);
			}
		}
		if (categoriesIds.size() > 0) {
			filterCategoriesIds.add(ArrayUtil.toLongArray(categoriesIds.stream().mapToLong(l -> l).toArray()));
		}

		// On récupère les types s'il y en a
		for (long type : this._types) {
			if (type > 0) {
				categoriesIds.add(type);
			}
		}
		if (categoriesIds.size() > 0) {
			filterCategoriesIds.add(ArrayUtil.toLongArray(categoriesIds.stream().mapToLong(l -> l).toArray()));
		}

		return filterCategoriesIds;
	}

	/**
	 * Retourne la jour du mois de la date de début de la recherche. Soit depuis
	 * les paramètres de la requête soit le réglage par défaut via la
	 * configuration (date du jour, ou si la période de recherche par défaut par
	 * défaut est négative, X jour dans le passé)
	 */
	public int getFromDay() {
		int fromParam = this._startDay;
		if (fromParam > 0) {
			return fromParam;
		} else {
			if (this._configuration.defaultFilterDateRange() < 0) {
				return LocalDate.now().plusDays(this._configuration.defaultFilterDateRange()).getDayOfMonth();
			} else {
				return LocalDate.now().getDayOfMonth();
			}
		}

	}

	/**
	 * Retourne le mois de la date de début de la recherche depuis les
	 * paramètres de la requête ou depuis la configuration, dans l'interval
	 * [0;11]
	 */
	public int getFromMonthIndex() {
		return getFromMonthValue() - 1;
	}

	/**
	 * Retourne le mois de la date de début de la recherche depuis les
	 * paramètres de la requête ou depuis la configuration, dans l'interval
	 * [1;12]
	 */
	public int getFromMonthValue() {
		String fromMonthString = this._startMonth;
		if (Validator.isNull(fromMonthString)) {
			if (this._configuration.defaultFilterDateRange() < 0) {
				return LocalDate.now().plusDays(this._configuration.defaultFilterDateRange()).getMonthValue();
			} else {
				return LocalDate.now().getMonthValue();
			}
		} else {
			return Integer.parseInt(fromMonthString) + 1;
		}
	}

	/**
	 * Retourne l'année de la date de début de la recherche depuis les
	 * paramètres de la requête ou depuis la configuration
	 */
	public int getFromYear() {
		int fromParam = this._startYear;
		if (fromParam > 0) {
			return fromParam;
		} else {
			if (this._configuration.defaultFilterDateRange() < 0) {
				return LocalDate.now().plusDays(this._configuration.defaultFilterDateRange()).getYear();
			} else {
				return LocalDate.now().getYear();
			}
		}
	}

	/**
	 * Retourne la jour du mois de la date de fin de la recherche. Soit depuis
	 * les paramètres de la requête soit le réglage par défaut via la
	 * configuration (date du jour + config, ou si la période de recherche par
	 * défaut par défaut est négative, date du jour)
	 */
	public int getToDay() {
		int toParam = this._endDay;
		if (toParam > 0) {
			return toParam;
		} else {
			if (this._configuration.defaultFilterDateRange() > 0) {
				return LocalDate.now().plusDays(this._configuration.defaultFilterDateRange()).getDayOfMonth();
			} else {
				return LocalDate.now().getDayOfMonth();
			}
		}
	}

	/**
	 * Retourne le mois de la date de fin de la recherche depuis les paramètres
	 * de la requête ou depuis la configuration, dans l'interval [0;11]
	 */
	public int getToMonthIndex() {
		return getToMonthValue() - 1;
	}

	/**
	 * Retourne le mois de la date de fin de la recherche depuis les paramètres
	 * de la requête ou depuis la configuration, dans l'interval [1;12]
	 */
	public int getToMonthValue() {
		String toMonthString = this._endMonth;
		if (Validator.isNull(toMonthString)) {
			if (this._configuration.defaultFilterDateRange() > 0) {
				return LocalDate.now().plusDays(this._configuration.defaultFilterDateRange()).getMonthValue();
			} else {
				return LocalDate.now().getMonthValue();
			}
		} else {
			return Integer.parseInt(toMonthString) + 1;
		}
	}

	/**
	 * Retourne l'année de la date de fin de la recherche depuis les paramètres
	 * de la requête ou depuis la configuration
	 */
	public int getToYear() {
		int toParam = this._endYear;
		if (toParam > 0) {
			return toParam;
		} else {
			if (this._configuration.defaultFilterDateRange() > 0) {
				return LocalDate.now().plusDays(this._configuration.defaultFilterDateRange()).getYear();
			} else {
				return LocalDate.now().getYear();
			}
		}
	}

	/**
	 * Retourne le champ sur lequel on classe les résultats
	 */
	public String getSortField() {
		String sortFieldFromParam = this._sortFieldAndType;
		if (Validator.isNull(sortFieldFromParam)) {
			if (Validator.isNull(this._keywords)) {
				String firstSortingField =  Validator.isNotNull(this._configuration.firstSortingField())
						? this._configuration.firstSortingField() : "modified_sortable";
				String secondSortingField =  Validator.isNotNull(this._configuration.secondSortingField())
						? this._configuration.secondSortingField() : "modified_sortable";
				return firstSortingField + "," + secondSortingField;
			} else {
				return "score";
			}
		} else {
			return sortFieldFromParam.split(",")[0];
		}
	}

	/**
	 * Récupération du publik ID avec la session
	 */
	private String getPublikID(PortletRequest request) {
		LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(request);
		HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
		return SessionParamUtil.getString(originalRequest, "publik_internal_id");
	}

	/**
	 * Retourne le type de classement des résultats (croissant ou décroissant)
	 */
	public String getSortType() {
		if (this.getSortField().equals("score")) {
			return "desc";
		} else {
			String sortTypeFromParam = this._sortFieldAndType;
			if (Validator.isNull(sortTypeFromParam)) {
				String firstSortingType =  Validator.isNotNull(this._configuration.firstSortingType())
						? this._configuration.firstSortingType() : "desc";
				String secondSortingType =  Validator.isNotNull(this._configuration.secondSortingType())
						? this._configuration.secondSortingType() : "desc";
				return firstSortingType + "," + secondSortingType;
			} else {
				return sortTypeFromParam.split(",")[1];
			}
		}
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

	private ThemeDisplay _themeDisplay;
	private ResourceRequest _request;
	private ResourceResponse _response;
	private SearchAssetDisplayContext _dc;
	private SearchAssetConfiguration _configuration;

	private String _keywords;
	private int _startDay;
	private String _startMonth;
	private int _startYear;
	private int _endDay;
	private String _endMonth;
	private int _endYear;
	private long[] _states;
	private long[] _statuts;
	private long[] _bpStatus;
	private long[] _initiativeStatus;
	private long[] _projects;
	private long[] _districts;
	private long[] _thematics;
	private long[] _types;
	private String _sortFieldAndType;

	/**
	 * interface des petitions
	 */
	private PetitionLocalService _petitionLocalService;

	/**
	 * interface des budgets
	 */
	private BudgetParticipatifLocalService _budgetParticipatifLocalService;

	/**
	 * interface des initiatives
	 */
	private InitiativeLocalService _initiativeLocalService;

	/**
	 * interface des participations
	 */
	private ParticipationLocalService _participationLocalService;

	private Hits _hits;

	@Reference(unbind = "-")
	protected void setPetitionLocalService(PetitionLocalService petitionLocalService) {
		_petitionLocalService = petitionLocalService;
	}

	@Reference(unbind = "-")
	protected void setBudgetParticipatifLocalService(BudgetParticipatifLocalService budgetParticipatifLocalService) {
		_budgetParticipatifLocalService = budgetParticipatifLocalService;
	}

	@Reference(unbind = "-")
	protected void setInitiativeLocalService(InitiativeLocalService initiativeLocalService) {
		_initiativeLocalService = initiativeLocalService;
	}

	@Reference(unbind = "-")
	protected void setParticipationLocalService(ParticipationLocalService participationLocalService) {
		_participationLocalService = participationLocalService;
	}

}