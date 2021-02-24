package eu.strasbourg.portlet.search_asset;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import eu.strasbourg.portlet.search_asset.action.ExportPDF;
import eu.strasbourg.portlet.search_asset.configuration.SearchAssetConfiguration;
import eu.strasbourg.portlet.search_asset.display.context.SearchAssetDisplayContext;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.service.*;
import eu.strasbourg.service.help.model.HelpProposal;
import eu.strasbourg.service.help.service.*;
import eu.strasbourg.service.video.model.Video;
import eu.strasbourg.service.video.service.VideoLocalServiceUtil;
import eu.strasbourg.utils.*;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component(
        immediate = true,
        configurationPid = "eu.strasbourg.portlet.page_header.configuration.PageHeaderConfiguration",
        property = {
            "com.liferay.portlet.display-category=Strasbourg",
            "com.liferay.portlet.instanceable=false",
            "com.liferay.portlet.css-class-wrapper=search-asset-portlet",
            "com.liferay.portlet.single-page-application=false",
            "javax.portlet.version=3.0",
            "javax.portlet.init-param.template-path=/",
            "javax.portlet.init-param.view-template=/search-asset-view.jsp",
            "javax.portlet.init-param.check-auth-token=false",
            "javax.portlet.name=" + StrasbourgPortletKeys.SEARCH_ASSET_WEB,
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
    public final static String AIDE = "eu.strasbourg.service.help.model.HelpProposal";

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) {
        try {
            ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
            long groupId = themeDisplay.getLayout().getGroupId();
            SearchAssetConfiguration configuration = themeDisplay
                    .getPortletDisplay().getPortletInstanceConfiguration(
                            SearchAssetConfiguration.class);
            List<String> classNameList = getClassNames(configuration);
            String userPublikId = getPublikID(renderRequest);

            // On set le DisplayContext
            SearchAssetDisplayContext dc = new SearchAssetDisplayContext(
                    renderRequest, renderResponse);
            renderRequest.setAttribute("dc", dc);

            // Boolean pour dire qu'on vient du portlet de recherche et non d'un
            // asset publisher (pour être utilisé dans les ADT si besoin
            renderRequest.setAttribute("fromSearchPortlet", true);

            // On envoie a la jsp la map className / layout qui fait
            // correspondre à chaque type d'asset une page de détail
            int i = 0;
            Map<String, Long> className_layoutId = new HashMap<>();
            for (String className : configuration.assetClassNames()
                    .split(",")) {
                String layoutFriendlyURL = configuration.layoutsFriendlyURLs()
                        .split(",")[i];
                Layout layout = LayoutLocalServiceUtil.fetchLayoutByFriendlyURL(
                        themeDisplay.getScopeGroupId(), false,
                        layoutFriendlyURL);
                if (layout != null) {
                    className_layoutId.put(className, layout.getPlid());
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

            if(Validator.isNotNull(classNameList) && classNameList.size() > 0) {
                String className = classNameList.get(0);

                if (className.equals(PARTICIPATION)) {
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
                	for (String id : Arrays.asList(configuration.prefilterCategoriesIds().replace(';',',').split(","))) {
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
                }
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
    public void serveResource(ResourceRequest request, ResourceResponse response)
            throws IOException, PortletException {

        try {
            ThemeDisplay themeDisplay = (ThemeDisplay) request
                    .getAttribute(WebKeys.THEME_DISPLAY);
            SearchAssetConfiguration configuration = themeDisplay
                    .getPortletDisplay().getPortletInstanceConfiguration(
                            SearchAssetConfiguration.class);

            String resourceID = request.getResourceID();
            
            String publikUserId =  this.getPublikID(request);

            // Verifions qu'il n'y ait pas d'entourloupe dans la solicitation
            // et réaction au type de la demande
            if (resourceID != null && resourceID.startsWith("entrySelection")) { // Nouvelle sélection de videos

                // Recherche des vidéos
                List<AssetEntry> entries = searchEntries(request, themeDisplay, configuration);

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
                            json = participation.toJSON(themeDisplay);
                            json.put("todayExpirationDifferenceDays", participation.getTodayExpirationDifferenceDays());
                            json.put("todayPublicationDifferenceDays", participation.getTodayPublicationDifferenceDays());
                            json.put("isJudgeable", participation.isJudgeable());
                            json.put("groupId", participation.getGroupId());
                            LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(request);
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
                        case "eu.strasbourg.service.help.model.HelpProposal":
                            HelpProposal helpProposal = HelpProposalLocalServiceUtil.fetchHelpProposal(entry.getClassPK());
                            JSONObject helpProposalJson = JSONFactoryUtil.createJSONObject();
                            helpProposalJson.put("class", className);
                            helpProposalJson.put("json", helpProposal.toJSON());
                            jsonEntries.put(helpProposalJson);
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
                            json.put("detailURL", LayoutHelper.getJournalArticleLayoutURL(
                                    journalArticle.getGroupId(), journalArticle.getArticleId(), themeDisplay));
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
                            List<AssetCategory> listVocabulary = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(
                                    asset, "territoire");
                            for (AssetCategory assetCategory : listVocabulary) {
                                jsonVocabulariesTitle.put(JSONHelper.getJSONFromI18nMap(assetCategory.getTitleMap()));
                            }
                            json.put("jsonVocabulariesTitle", jsonVocabulariesTitle);
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
                            json.put("modifiedDate", dateFormat.format(journalArticle.getModifiedDate()));
                            String chapo = docXML.valueOf("//dynamic-element[@name='chapo']/dynamic-content/text()");
                            json.put("chapo", chapo.replaceAll("<[^>]*>", "")
                                    .substring(0, chapo.length() > 100 ? 100 : chapo.length()));
                            jsonJournalArticle.put("json", json);
                            jsonEntries.put(jsonJournalArticle);
                            break;
                    }
                }
                jsonResponse.put("entries", jsonEntries);

                // Recuperation de l'élément d'écriture de la réponse
                PrintWriter writer = response.getWriter();
                writer.print(jsonResponse.toString());

            } else { // pour l'export PDF
                String exportType = configuration.exportType();
                ExportPDF.printPDF(request, response, exportType);
            }
        } catch (Exception e2) {
            _log.error(e2);
        }
        super.serveResource(request, response);
    }

    /**
     * Effectue concrètement la recherche
     */
    private List<AssetEntry> searchEntries(ResourceRequest request, ThemeDisplay themeDisplay,
                                           SearchAssetConfiguration configuration) {
        HttpServletRequest servletRequest = PortalUtil.getHttpServletRequest(request);

        SearchContext searchContext = SearchContextFactory.getInstance(servletRequest);

        String resourceID = request.getResourceID();

        // Initialisation des paramètre de requête
        String keywords = "";
        int startDay = -1;
        String startMonth = null;
        int startYear = -1;
        int endDay = -1;
        String endMonth = null;
        int endYear = -1;
        long[] states = new long[]{};
        long[] statuts = new long[]{};
        long[] bpStatus  = new long[]{};
        long[] initiativeStatus = new long[]{};
        long[] helpProposalStatus = new long[]{};
        long[] helpType = new long[]{};
        long[] projects = new long[]{};
        long[] districts = new long[]{};
        long[] thematics = new long[]{};
        long[] types = new long[]{};
        String sortFieldAndType = null;

        if (resourceID.equals("entrySelectionVideo")) {
            keywords = ParamUtil.getString(request, "selectedKeyWords");
            startDay = ParamUtil.getInteger(request, "selectedStartDay");
            startMonth = ParamUtil.getString(request, "selectedStartMonth");
            startYear = ParamUtil.getInteger(request, "selectedStartYear");
            endDay = ParamUtil.getInteger(request, "selectedEndDay");
            endMonth = ParamUtil.getString(request, "selectedEndMonth");
            endYear = ParamUtil.getInteger(request, "selectedEndYear");
            projects = ParamUtil.getLongValues(request, "selectedProject");
            districts = ParamUtil.getLongValues(request, "selectedDistricts");
            thematics = ParamUtil.getLongValues(request, "selectedThematics");
            sortFieldAndType = ParamUtil.getString(request, "sortFieldAndType");
        }

        if (resourceID.equals("entrySelectionProject")) {
            keywords = ParamUtil.getString(request, "selectedKeyWords");
            statuts = ParamUtil.getLongValues(request, "selectedStatut");
            districts = ParamUtil.getLongValues(request, "selectedDistricts");
            thematics = ParamUtil.getLongValues(request, "selectedThematics");
            sortFieldAndType = ParamUtil.getString(request, "sortFieldAndType");
        }

        if (resourceID.equals("entrySelectionParticipation")) {
            keywords = ParamUtil.getString(request, "selectedKeyWords");
            startDay = ParamUtil.getInteger(request, "selectedStartDay");
            startMonth = ParamUtil.getString(request, "selectedStartMonth");
            startYear = ParamUtil.getInteger(request, "selectedStartYear");
            endDay = ParamUtil.getInteger(request, "selectedEndDay");
            endMonth = ParamUtil.getString(request, "selectedEndMonth");
            endYear = ParamUtil.getInteger(request, "selectedEndYear");
            states = ParamUtil.getLongValues(request, "selectedStates");
            districts = ParamUtil.getLongValues(request, "selectedDistricts");
            thematics = ParamUtil.getLongValues(request, "selectedThematics");
            types = ParamUtil.getLongValues(request, "selectedTypes");
            sortFieldAndType = ParamUtil.getString(request, "sortFieldAndType");
        }

        if (resourceID.equals("entrySelectionAgenda")) {
            keywords = ParamUtil.getString(request, "selectedKeyWords");
            startDay = ParamUtil.getInteger(request, "selectedStartDay");
            startMonth = ParamUtil.getString(request, "selectedStartMonth");
            startYear = ParamUtil.getInteger(request, "selectedStartYear");
            endDay = ParamUtil.getInteger(request, "selectedEndDay");
            endMonth = ParamUtil.getString(request, "selectedEndMonth");
            endYear = ParamUtil.getInteger(request, "selectedEndYear");
            projects = ParamUtil.getLongValues(request, "selectedProject");
            districts = ParamUtil.getLongValues(request, "selectedDistricts");
            thematics = ParamUtil.getLongValues(request, "selectedThematics");
            sortFieldAndType = ParamUtil.getString(request, "sortFieldAndType");
        }

        if (resourceID.equals("entrySelectionPetition")) {
            keywords = ParamUtil.getString(request, "selectedKeyWords");
            startDay = ParamUtil.getInteger(request, "selectedStartDay");
            startMonth = ParamUtil.getString(request, "selectedStartMonth");
            startYear = ParamUtil.getInteger(request, "selectedStartYear");
            endDay = ParamUtil.getInteger(request, "selectedEndDay");
            endMonth = ParamUtil.getString(request, "selectedEndMonth");
            endYear = ParamUtil.getInteger(request, "selectedEndYear");
            states = ParamUtil.getLongValues(request, "selectedStates");
            districts = ParamUtil.getLongValues(request, "selectedDistricts");
            thematics = ParamUtil.getLongValues(request, "selectedThematics");
            sortFieldAndType = ParamUtil.getString(request, "sortFieldAndType");
        }

        if (resourceID.equals("entrySelectionBudgetParticipatif")) {
            keywords = ParamUtil.getString(request, "selectedKeyWords");
            startDay = ParamUtil.getInteger(request, "selectedStartDay");
            startMonth = ParamUtil.getString(request, "selectedStartMonth");
            startYear = ParamUtil.getInteger(request, "selectedStartYear");
            endDay = ParamUtil.getInteger(request, "selectedEndDay");
            endMonth = ParamUtil.getString(request, "selectedEndMonth");
            endYear = ParamUtil.getInteger(request, "selectedEndYear");
            bpStatus = ParamUtil.getLongValues(request, "selectedBPStatus");
            districts = ParamUtil.getLongValues(request, "selectedDistricts");
            thematics = ParamUtil.getLongValues(request, "selectedThematics");
            sortFieldAndType = ParamUtil.getString(request, "sortFieldAndType");
        }

        if (resourceID.equals("entrySelectionInitiative")) {
            keywords = ParamUtil.getString(request, "selectedKeyWords");
            startDay = ParamUtil.getInteger(request, "selectedStartDay");
            startMonth = ParamUtil.getString(request, "selectedStartMonth");
            startYear = ParamUtil.getInteger(request, "selectedStartYear");
            endDay = ParamUtil.getInteger(request, "selectedEndDay");
            endMonth = ParamUtil.getString(request, "selectedEndMonth");
            endYear = ParamUtil.getInteger(request, "selectedEndYear");
            initiativeStatus = ParamUtil.getLongValues(request, "selectedInitiativeStatus");
            districts = ParamUtil.getLongValues(request, "selectedDistricts");
            thematics = ParamUtil.getLongValues(request, "selectedThematics");
            sortFieldAndType = ParamUtil.getString(request, "sortFieldAndType");
        }

        if (resourceID.equals("entrySelectionHelpProposal")) {
            keywords = ParamUtil.getString(request, "selectedKeyWords");
            startDay = ParamUtil.getInteger(request, "selectedStartDay");
            startMonth = ParamUtil.getString(request, "selectedStartMonth");
            startYear = ParamUtil.getInteger(request, "selectedStartYear");
            endDay = ParamUtil.getInteger(request, "selectedEndDay");
            endMonth = ParamUtil.getString(request, "selectedEndMonth");
            endYear = ParamUtil.getInteger(request, "selectedEndYear");
            helpProposalStatus = ParamUtil.getLongValues(request, "selectedHelpProposalStatus");
            helpType = ParamUtil.getLongValues(request, "selectedHelpType");
            districts = ParamUtil.getLongValues(request, "selectedDistricts");
            sortFieldAndType = ParamUtil.getString(request, "sortFieldAndType");
        }

        if (resourceID.equals("entrySelectionNews")) {
            keywords = null;
            startDay = ParamUtil.getInteger(request, "selectedStartDay");
            startMonth = ParamUtil.getString(request, "selectedStartMonth");
            startYear = ParamUtil.getInteger(request, "selectedStartYear");
            endDay = ParamUtil.getInteger(request, "selectedEndDay");
            endMonth = ParamUtil.getString(request, "selectedEndMonth");
            endYear = ParamUtil.getInteger(request, "selectedEndYear");
            states = ParamUtil.getLongValues(request, "selectedStates");
            districts = ParamUtil.getLongValues(request, "selectedDistricts");
            thematics = ParamUtil.getLongValues(request, "selectedThematics");
            sortFieldAndType = ParamUtil.getString(request, "sortFieldAndType");
        }

        // ClassNames de la configuration
        String[] classNames = ArrayUtil.toStringArray(getClassNames(configuration));

        // Inclusion ou non du scope global
        boolean globalScope = configuration.globalScope();
        long globalGroupId = themeDisplay.getCompanyGroupId();

        // Group ID courant
        long groupId = themeDisplay.getScopeGroupId();

        // Catégories sélectionnées par l'utilisateur
        List<Long[]> categoriesIds = this.getFilterCategoriesIds(
                states, statuts, bpStatus, initiativeStatus, projects, districts, thematics, types, helpProposalStatus, helpType
        );

        // Préfiltre catégories
        String prefilterCategoriesIdsString = configuration.prefilterCategoriesIds();
        List<Long[]> prefilterCategoriesIds = new ArrayList<>();
        for (String prefilterCategoriesIdsGroupByVocabulary : prefilterCategoriesIdsString.split(";")) {
            Long[] prefilterCategoriesIdsForVocabulary = ArrayUtil
                    .toLongArray(StringUtil.split(prefilterCategoriesIdsGroupByVocabulary, ",", 0));
            prefilterCategoriesIds.add(prefilterCategoriesIdsForVocabulary);
        }

        // Préfiltre tags
        String prefilterTagsNamesString = configuration.prefilterTagsNames();
        String[] prefilterTagsNames = StringUtil.split(prefilterTagsNamesString);

        // Champ date
        boolean dateField = startDay != -1 && configuration.dateField();
        String dateFieldName = configuration.defaultSortField();
        LocalDate fromDate = null;
        LocalDate toDate = null;
        if (dateField) {
            fromDate = LocalDate.of(this.getFromYear(configuration, startYear),
                    this.getFromMonthValue(configuration, startMonth),
                    this.getFromDay(configuration, startDay));
            toDate = LocalDate.of(this.getToYear(configuration, endYear),
                    this.getToMonthValue(configuration, endMonth),
                    this.getToDay(configuration, endDay));
        }

        // Ordre
        String sortField = this.getSortField(configuration, sortFieldAndType, keywords);
        boolean isSortDesc = "desc".equals(this.getSortType(configuration, sortFieldAndType, keywords));

        // Permet de remonter la hiérarchie des Request
        HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(servletRequest);

        // Lieu (pour la recherche agenda)
        String idSIGPlace = ParamUtil.getString(originalRequest, "idSIGPlace");

        // Recherche
        Hits hits = SearchHelper.getGlobalSearchHits(searchContext, classNames, groupId, globalGroupId, globalScope,
                keywords, dateField, dateFieldName, fromDate, toDate, categoriesIds, prefilterCategoriesIds,
                prefilterTagsNames, idSIGPlace, themeDisplay.getLocale(), -1,
                -1, sortField, isSortDesc);

        List<AssetEntry> results = new ArrayList<>();
        if (hits != null) {
            int i = 0;
            for (float s : hits.getScores()) {
                _log.info(GetterUtil.getString(hits.getDocs()[i].get(Field.TITLE)) + " : " + s);
                i++;
                if (i > 10)
                    break;
            }

            for (Document document : hits.getDocs()) {
                AssetEntry entry = AssetEntryLocalServiceUtil.fetchEntry(
                        GetterUtil.getString(document.get(Field.ENTRY_CLASS_NAME)),
                        GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
                if (entry != null) {
                    results.add(entry);
                }
            }
            SearchHelper.getGlobalSearchCount(searchContext, classNames, groupId, globalGroupId,
                    globalScope, keywords, dateField, dateFieldName, fromDate, toDate, categoriesIds,
                    prefilterCategoriesIds, prefilterTagsNames, idSIGPlace, themeDisplay.getLocale());
        }

        return results;
    }

    /**
     * Retourne la liste des class names sur lesquelles on recherche
     */
    public List<String> getClassNames(SearchAssetConfiguration configuration) {
        List<String> classNames = new ArrayList<String>();
        for (String className : configuration.assetClassNames().split(",")) {
            if (Validator.isNotNull(className)) {
                classNames.add(className);
            }
        }
        if (configuration.searchJournalArticle()) {
            classNames.add("com.liferay.journal.model.JournalArticle");
        }
        if (configuration.searchDocument()) {
            classNames.add(DLFileEntry.class.getName());
        }

        return classNames;
    }

    /**
     * Renvoie la liste des catégories sur lesquelles on souhaite filtrer les
     * entries. L'opérateur entre chaque id de catégorie d'un array est un "OU", celui entre chaque liste d'array est un "ET"
     */
    private List<Long[]> getFilterCategoriesIds(long[] states, long[] statuts, long[] bpStatus, long[] initiativeStatus,
                                                long[] projects, long [] districts, long[] thematics, long[] types,
                                                long[] helpProposalStatus, long[] helpTypes) {
        List<Long[]> filterCategoriesIds = new ArrayList<>();
        List<Long> categoriesIds = new ArrayList<>();

        // On récupère les états s'il y en a
        for (long state : states) {
            if (state > 0) {
                categoriesIds.add(state);
            }
        }
        if (categoriesIds.size() > 0) {
            filterCategoriesIds.add(ArrayUtil.toLongArray(categoriesIds.stream().mapToLong(l -> l).toArray()));
        }

        // On récupère les statuts s'il y en a
        for (long statut : statuts) {
            if (statut > 0) {
                categoriesIds.add(statut);
            }
        }
        if (categoriesIds.size() > 0) {
            filterCategoriesIds.add(ArrayUtil.toLongArray(categoriesIds.stream().mapToLong(l -> l).toArray()));
        }
        
        // On recupere les statuts BP s'il y en a
        for (long bpStatu : bpStatus) {
            if (bpStatu > 0) {
                categoriesIds.add(bpStatu);
            }
        }
        if (categoriesIds.size() > 0) {
            filterCategoriesIds.add(ArrayUtil.toLongArray(categoriesIds.stream().mapToLong(l -> l).toArray()));
        }
        
        // On recupere les statuts initiative s'il y en a
        for (long initiativeStatu : initiativeStatus) {
        	if (initiativeStatu > 0) {
        		categoriesIds.add(initiativeStatu);
        	}
        }
        if (categoriesIds.size() > 0) {
        	filterCategoriesIds.add(ArrayUtil.toLongArray(categoriesIds.stream().mapToLong(l -> l).toArray()));
        }

        // On récupère les projets s'il y en a
        for (long project : projects) {
            if (project > 0) {
                categoriesIds.add(project);
            }
        }
        if (categoriesIds.size() > 0) {
            filterCategoriesIds.add(ArrayUtil.toLongArray(categoriesIds.stream().mapToLong(l -> l).toArray()));
        }

        // On récupère les quartiers s'il y en a
        categoriesIds = new ArrayList<>();
        for (long district : districts) {
            if (district > 0) {
                categoriesIds.add(district);
            }
        }
        if (categoriesIds.size() > 0) {
            filterCategoriesIds.add(ArrayUtil.toLongArray(categoriesIds.stream().mapToLong(l -> l).toArray()));
        }

        // On récupère les thématiques s'il y en a
        categoriesIds = new ArrayList<>();
        for (long thematic : thematics) {
            if (thematic > 0) {
                categoriesIds.add(thematic);
            }
        }
        if (categoriesIds.size() > 0) {
            filterCategoriesIds.add(ArrayUtil.toLongArray(categoriesIds.stream().mapToLong(l -> l).toArray()));
        }

        // On récupère les types s'il y en a
        for (long type : types) {
            if (type > 0) {
                categoriesIds.add(type);
            }
        }
        if (categoriesIds.size() > 0) {
            filterCategoriesIds.add(ArrayUtil.toLongArray(categoriesIds.stream().mapToLong(l -> l).toArray()));
        }

        // On recupere les statuts aides s'il y en a
        for (long helpProposalStatu : helpProposalStatus) {
            if (helpProposalStatu > 0) {
                categoriesIds.add(helpProposalStatu);
            }
        }
        if (categoriesIds.size() > 0) {
            filterCategoriesIds.add(ArrayUtil.toLongArray(categoriesIds.stream().mapToLong(l -> l).toArray()));
        }

        // On recupere les types d'aide s'il y en a
        for (long helpType : helpTypes) {
            if (helpType > 0) {
                categoriesIds.add(helpType);
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
    public int getFromDay(SearchAssetConfiguration configuration, int startDay) {
        if (startDay > 0) {
            return startDay;
        } else {
            if (configuration.defaultDateRange() < 0) {
                return LocalDate.now().plusDays(configuration.defaultDateRange()).getDayOfMonth();
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
    public int getFromMonthIndex(SearchAssetConfiguration configuration, String startMonth) {
        return getFromMonthValue(configuration, startMonth) - 1;
    }

    /**
     * Retourne le mois de la date de début de la recherche depuis les
     * paramètres de la requête ou depuis la configuration, dans l'interval
     * [1;12]
     */
    public int getFromMonthValue(SearchAssetConfiguration configuration, String startMonth) {
        if (Validator.isNull(startMonth)) {
            if (configuration.defaultDateRange() < 0) {
                return LocalDate.now().plusDays(configuration.defaultDateRange()).getMonthValue();
            } else {
                return LocalDate.now().getMonthValue();
            }
        } else {
            return Integer.parseInt(startMonth) + 1;
        }
    }

    /**
     * Retourne l'année de la date de début de la recherche depuis les
     * paramètres de la requête ou depuis la configuration
     */
    public int getFromYear(SearchAssetConfiguration configuration, int startYear) {
        if (startYear > 0) {
            return startYear;
        } else {
            if (configuration.defaultDateRange() < 0) {
                return LocalDate.now().plusDays(configuration.defaultDateRange()).getYear();
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
    public int getToDay(SearchAssetConfiguration configuration, int endDay) {
        if (endDay > 0) {
            return endDay;
        } else {
            if (configuration.defaultDateRange() > 0) {
                return LocalDate.now().plusDays(configuration.defaultDateRange()).getDayOfMonth();
            } else {
                return LocalDate.now().getDayOfMonth();
            }
        }
    }

    /**
     * Retourne le mois de la date de fin de la recherche depuis les paramètres
     * de la requête ou depuis la configuration, dans l'interval [0;11]
     */
    public int getToMonthIndex(SearchAssetConfiguration configuration, String endMonth) {
        return getToMonthValue(configuration, endMonth) - 1;
    }

    /**
     * Retourne le mois de la date de fin de la recherche depuis les paramètres
     * de la requête ou depuis la configuration, dans l'interval [1;12]
     */
    public int getToMonthValue(SearchAssetConfiguration configuration, String endMonth) {
        if (Validator.isNull(endMonth)) {
            if (configuration.defaultDateRange() > 0) {
                return LocalDate.now().plusDays(configuration.defaultDateRange()).getMonthValue();
            } else {
                return LocalDate.now().getMonthValue();
            }
        } else {
            return Integer.parseInt(endMonth) + 1;
        }
    }

    /**
     * Retourne l'année de la date de fin de la recherche depuis les paramètres
     * de la requête ou depuis la configuration
     */
    public int getToYear(SearchAssetConfiguration configuration, int endYear) {
        if (endYear > 0) {
            return endYear;
        } else {
            if (configuration.defaultDateRange() > 0) {
                return LocalDate.now().plusDays(configuration.defaultDateRange()).getYear();
            } else {
                return LocalDate.now().getYear();
            }
        }
    }

    /**
     * Retourne le champ sur lequel on classe les résultats
     */
    public String getSortField(SearchAssetConfiguration configuration, String sortFieldAndType, String keywords) {
        if (Validator.isNull(sortFieldAndType)) {
            if (Validator.isNull(keywords)) {
                return Validator.isNotNull(configuration.defaultSortField())
                        ? configuration.defaultSortField() : "modified_sortable";
            } else {
                return "_score";
            }
        } else {
            return sortFieldAndType.split(",")[0];
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
    public String getSortType(SearchAssetConfiguration configuration, String sortFieldAndType, String keywords) {
        if (this.getSortField(configuration, sortFieldAndType, keywords).equals("_score")) {
            // Avec la FP9, on veut reverse = false, parce que Reverse donne ASC, mais que pour _score
            return "descmaispasceluila";
        } else {
            if (Validator.isNull(sortFieldAndType)) {
                return Validator.isNotNull(configuration.defaultSortType())
                        ? configuration.defaultSortType() : "desc";
            } else {
                return sortFieldAndType.split(",")[1];
            }
        }
    }

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
    
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
     * interface des propositions d'aides ? Pas utile ?
     */
    private HelpProposalLocalService _helpProposalLocalService;

    /**
     * interface des participations
     */
    private ParticipationLocalService _participationLocalService;

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
    protected void setHelpProposalLocalService(HelpProposalLocalService helpProposalLocalService) {
        _helpProposalLocalService = helpProposalLocalService;
    }

    @Reference(unbind = "-")
    protected void setParticipationLocalService(ParticipationLocalService participationLocalService) {
        _participationLocalService = participationLocalService;
    }

}