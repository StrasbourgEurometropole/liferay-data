package eu.strasbourg.portlet.search_asset;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetLinkLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.util.*;
import eu.strasbourg.service.activity.model.Activity;
import eu.strasbourg.service.activity.model.ActivityCourse;
import eu.strasbourg.service.activity.model.ActivityOrganizer;
import eu.strasbourg.service.activity.service.ActivityCourseLocalServiceUtil;
import eu.strasbourg.service.activity.service.ActivityLocalServiceUtil;
import eu.strasbourg.service.activity.service.ActivityOrganizerLocalServiceUtil;
import eu.strasbourg.service.agenda.model.Campaign;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.Manifestation;
import eu.strasbourg.service.agenda.service.CampaignLocalServiceUtil;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.agenda.service.ManifestationLocalServiceUtil;
import eu.strasbourg.service.artwork.model.Artwork;
import eu.strasbourg.service.artwork.model.ArtworkCollection;
import eu.strasbourg.service.artwork.service.ArtworkCollectionLocalServiceUtil;
import eu.strasbourg.service.artwork.service.ArtworkLocalServiceUtil;
import eu.strasbourg.service.edition.model.Edition;
import eu.strasbourg.service.edition.model.EditionGallery;
import eu.strasbourg.service.edition.service.EditionGalleryLocalServiceUtil;
import eu.strasbourg.service.edition.service.EditionLocalServiceUtil;
import eu.strasbourg.service.interest.model.Interest;
import eu.strasbourg.service.interest.service.InterestLocalServiceUtil;
import eu.strasbourg.service.link.model.Link;
import eu.strasbourg.service.link.service.LinkLocalServiceUtil;
import eu.strasbourg.service.official.model.Official;
import eu.strasbourg.service.official.service.OfficialLocalServiceUtil;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.service.ParticipationLocalServiceUtil;
import eu.strasbourg.service.project.service.ProjectLocalServiceUtil;
import eu.strasbourg.service.video.model.VideoGallery;
import eu.strasbourg.service.video.service.VideoGalleryLocalServiceUtil;
import eu.strasbourg.service.video.service.VideoLocalServiceUtil;
import eu.strasbourg.utils.SearchHelper;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import eu.strasbourg.portlet.search_asset.action.ExportPDF;
import eu.strasbourg.portlet.search_asset.configuration.SearchAssetConfiguration;
import eu.strasbourg.portlet.search_asset.display.context.SearchAssetDisplayContext;

import eu.strasbourg.service.video.model.Video;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;

@Component(immediate = true, configurationPid = "eu.strasbourg.portlet.page_header.configuration.PageHeaderConfiguration", property = {
		"com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.css-class-wrapper=search-asset-portlet",
		"com.liferay.portlet.single-page-application=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/search-asset-view.jsp",
		"javax.portlet.init-param.check-auth-token=false",
		"javax.portlet.init-param.config-template=/search-asset-configuration.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class SearchAssetPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest,
			RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest
					.getAttribute(WebKeys.THEME_DISPLAY);
			SearchAssetConfiguration configuration = themeDisplay
					.getPortletDisplay().getPortletInstanceConfiguration(
							SearchAssetConfiguration.class);

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
			Map<String, Long> className_layoutId = new HashMap<String, Long>();
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
            this._request = resourceRequest;
            this._response = resourceResponse;
            this._configuration = this._themeDisplay.getPortletDisplay()
                    .getPortletInstanceConfiguration(SearchAssetConfiguration.class);

			String resourceID = resourceRequest.getResourceID();

			// Verifions qu'il n'y ait pas d'entourloupe dans la solicitation
			// et réaction au type de la demande
			if (resourceID.equals("videosSelection")) { // Nouvelle sélection de videos

				this._keywords = ParamUtil.getString(resourceRequest, "selectedKeyWords");
				this._startDay = ParamUtil.getInteger(resourceRequest, "selectedStartDay");
                this._startMonth = ParamUtil.getString(resourceRequest, "selectedStartMonth");
                this._startYear = ParamUtil.getInteger(resourceRequest, "selectedStartYear");
                this._endDay = ParamUtil.getInteger(resourceRequest, "selectedEndDay");
                this._endMonth = ParamUtil.getString(resourceRequest, "selectedEndMonth");
                this._endYear = ParamUtil.getInteger(resourceRequest, "selectedEndYear");
				this._projects = ParamUtil.getLongValues(resourceRequest, "selectedProject");
				this._districts = ParamUtil.getLongValues(resourceRequest, "selectedDistricts");
				this._thematics = ParamUtil.getLongValues(resourceRequest, "selectedThematics");
				this._sortFieldAndType = ParamUtil.getString(resourceRequest,"sortFieldAndType");

				// Recherche des vidéos
                this.initSearchContainer();
				List<AssetEntry> entries = searchEntries();

				// Récupération du json des entités
                JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
                JSONArray jsonEntries = JSONFactoryUtil.createJSONArray();
				for (AssetEntry entry : entries) {
				    String className = entry.getClassName();

				    switch (className){
                        /*case "eu.strasbourg.service.activity.model.Activity":
                            Activity activity = ActivityLocalServiceUtil.fetchActivity(entry.getClassPK());
                            jsonEntries.put(activity.toJSON());
                            break;
                        case "eu.strasbourg.service.activity.model.ActivityCourse":
                            ActivityCourse activityCourse = ActivityCourseLocalServiceUtil.fetchActivityCourse(entry.getClassPK());
                            jsonEntries.put(activityCourse.toJSON());
                            break;
                        case "eu.strasbourg.service.activity.model.ActivityOrganizer":
                            ActivityOrganizer activityOrganizer = ActivityOrganizerLocalServiceUtil.fetchActivityOrganizer(entry.getClassPK());
                            jsonEntries.put(activityOrganizer.toJSON());
                            break;
                        case "eu.strasbourg.service.agenda.model.Campaign":
                            Campaign campaign = CampaignLocalServiceUtil.fetchCampaign(entry.getClassPK());
                            jsonEntries.put(campaign.toJSON());
                            break;*/
                        case "eu.strasbourg.service.agenda.model.Event":
                            Event event = EventLocalServiceUtil.fetchEvent(entry.getClassPK());
                            jsonEntries.put(event.toJSON());
                            break;
                        /*case "eu.strasbourg.service.agenda.model.Manifestation":
                            Manifestation manifestation = ManifestationLocalServiceUtil.fetchManifestation(entry.getClassPK());
                            jsonEntries.put(manifestation.toJSON());
                            break;
                        case "eu.strasbourg.service.artwork.model.Artwork":
                            Artwork artwork = ArtworkLocalServiceUtil.fetchArtwork(entry.getClassPK());
                            jsonEntries.put(artwork.toJSON());
                            break;
                        case "eu.strasbourg.service.artwork.model.ArtworkCollection":
                            ArtworkCollection artworkCollection = ArtworkCollectionLocalServiceUtil.fetchArtworkCollection(entry.getClassPK());
                            jsonEntries.put(artworkCollection.toJSON());
                            break;
                        case "eu.strasbourg.service.edition.model.Edition":
                            Edition edition = EditionLocalServiceUtil.fetchEdition(entry.getClassPK());
                            jsonEntries.put(edition.toJSON());
                            break;
                        case "eu.strasbourg.service.edition.model.EditionGallery":
                            EditionGallery editionGallery = EditionGalleryLocalServiceUtil.fetchEditionGallery(entry.getClassPK());
                            jsonEntries.put(editionGallery.toJSON());
                            break;
                        case "eu.strasbourg.service.interest.model.Interest":
                            Interest interest = InterestLocalServiceUtil.fetchInterest(entry.getClassPK());
                            jsonEntries.put(interest.toJSON());
                            break;
                        case "eu.strasbourg.service.link.model.Link":
                            Link link = LinkLocalServiceUtil.fetchLink(entry.getClassPK());
                            jsonEntries.put(link.toJSON());
                            break;
                        case "eu.strasbourg.service.official.model.Official":
                            Official official = OfficialLocalServiceUtil.fetchOfficial(entry.getClassPK());
                            jsonEntries.put(official.toJSON());
                            break;
                        case "eu.strasbourg.service.place.model.Place":
                            Place place = PlaceLocalServiceUtil.fetchPlace(entry.getClassPK());
                            jsonEntries.put(place.toJSON());
                            break;*/
                        case "eu.strasbourg.service.project.model.Project":
                            Project project = ProjectLocalServiceUtil.fetchProject(entry.getClassPK());
                            jsonEntries.put(project.toJSON());
                            break;
                        case "eu.strasbourg.service.project.model.Participation":
                            Participation participation = ParticipationLocalServiceUtil.fetchParticipation(entry.getClassPK());
                            jsonEntries.put(participation.toJSON());
                            break;
                        case "eu.strasbourg.service.video.model.Video":
                            Video video = VideoLocalServiceUtil.fetchVideo(entry.getClassPK());
                            jsonEntries.put(video.toJSON());
                            break;
                        /*case "eu.strasbourg.service.video.model.VideoGallery":
                            VideoGallery videoGallery = VideoGalleryLocalServiceUtil.fetchVideoGallery(entry.getClassPK());
                            jsonEntries.put(videoGallery.toJSON());
                            break;*/
                        case "com.liferay.journal.model.JournalArticle":
                            JournalArticle  journalArticle = JournalArticleLocalServiceUtil.fetchJournalArticle(entry.getClassPK());
                            /*jsonEntries.put(journalArticle.toJSON());*/
                            break;
                        /*case "com.liferay.document.library.kernel.model.DLFileEntry":
                            DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(entry.getClassPK());
                            jsonEntries.put(dlFileEntry.toJSON());
                            break;*/
                    }
				}
                jsonResponse.put("entries", jsonEntries);

				// Recuperation de l'élément d'écriture de la réponse
				PrintWriter writer = resourceResponse.getWriter();
				writer.print(jsonResponse.toString());

			} else { // pour l'export PDF
                long groupId = new Long(this._themeDisplay.getLayout().getGroupId());
				String exportType = this._configuration.exportType();
				ExportPDF.printPDFWithXMLWorker(resourceRequest, resourceResponse, exportType);
			}
		} catch (Exception e2) {
			_log.error(e2);
		}
		super.serveResource(resourceRequest, resourceResponse);
	}

    private void initSearchContainer() {
        PortletURL iteratorURL = this._response.createRenderURL();
        iteratorURL.setParameter("orderByCol", this.getSortField());
        iteratorURL.setParameter("orderByType", this.getSortType());
        int i = 0;
        for (Long[] categoriesIds : this.getFilterCategoriesIds()) {
            iteratorURL.setParameter("vocabulary_" + i, ArrayUtil.toStringArray(categoriesIds));
            i++;
        }
        iteratorURL.setParameter("paginate", String.valueOf(true));
        iteratorURL.setParameter("vocabulariesCount", String.valueOf(i));

        iteratorURL.setParameter("className", this.getFilterClassNames());

        iteratorURL.setParameter("keywords", String.valueOf(this._keywords));
        if (this._configuration.dateField()) {
            iteratorURL.setParameter("fromDay", String.valueOf(this.getFromDay()));
            iteratorURL.setParameter("fromMonth", String.valueOf(this.getFromMonthIndex()));
            iteratorURL.setParameter("fromYear", String.valueOf(this.getFromYear()));
            iteratorURL.setParameter("toDay", String.valueOf(this.getToDay()));
            iteratorURL.setParameter("toMonth", String.valueOf(this.getToMonthIndex()));
            iteratorURL.setParameter("toYear", String.valueOf(this.getToYear()));
        }

        if (this._searchContainer == null) {
            this._searchContainer = new SearchContainer<AssetEntry>(this._request, iteratorURL, null,
                    "no-entries-were-found");
            this._searchContainer.getIteratorURL().setParameter("delta", String.valueOf(this.getDelta()));

            this._searchContainer.setDelta((int) this.getDelta());
        }
    }

	/**
	 * Effectue concrètement la recherche
	 */
	private List<AssetEntry> searchEntries() throws PortalException {
		HttpServletRequest servletRequest = PortalUtil.getHttpServletRequest(this._request);

		SearchContext searchContext = SearchContextFactory.getInstance(servletRequest);

		// Mots clés
		String keywords = this._keywords;

		// ClassNames de la configuration
		String[] classNames = this.getFilterClassNames();

		// Inclusion ou non du scope global
		boolean globalScope = this._configuration.globalScope();
		long globalGroupId = this._themeDisplay.getCompanyGroupId();

		// Group ID courant
		long groupId = this._themeDisplay.getScopeGroupId();

		// Catégories sélectionnées par l'utilisateur
		List<Long[]> categoriesIds = this.getFilterCategoriesIds();

		// Préfiltre catégories
		String prefilterCategoriesIdsString = this._configuration.prefilterCategoriesIds();
		List<Long[]> prefilterCategoriesIds = new ArrayList<Long[]>();
		for (String prefilterCategoriesIdsGroupByVocabulary : prefilterCategoriesIdsString.split(";")) {
			Long[] prefilterCategoriesIdsForVocabulary = ArrayUtil
					.toLongArray(StringUtil.split(prefilterCategoriesIdsGroupByVocabulary, ",", 0));
			prefilterCategoriesIds.add(prefilterCategoriesIdsForVocabulary);
		}

		// Préfiltre tags
		String prefilterTagsNamesString = this._configuration.prefilterTagsNames();
		String[] prefilterTagsNames = StringUtil.split(prefilterTagsNamesString);

		// Champ date
		boolean dateField = this._configuration.dateField();
		String dateFieldName = this._configuration.defaultSortField();
		LocalDate fromDate = LocalDate.of(this.getFromYear(), this.getFromMonthValue(), this.getFromDay());
		LocalDate toDate = LocalDate.of(this.getToYear(), this.getToMonthValue(), this.getToDay());

		// Ordre
		String sortField = this.getSortField();
		boolean isSortDesc = "desc".equals(this.getSortType());

		// Permet de remonter la hiérarchie des Request
		HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(servletRequest);

		// Lieu (pour la recherche agenda)
		String idSIGPlace = ParamUtil.getString(originalRequest, "idSIGPlace");

		// Recherche
		this._hits = SearchHelper.getGlobalSearchHits(searchContext, classNames, groupId, globalGroupId, globalScope,
				keywords, dateField, dateFieldName, fromDate, toDate, categoriesIds, prefilterCategoriesIds,
				prefilterTagsNames,idSIGPlace, this._themeDisplay.getLocale(), getSearchContainer().getStart(),
				getSearchContainer().getEnd(), sortField, isSortDesc);

		List<AssetEntry> results = new ArrayList<AssetEntry>();
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
			long count = SearchHelper.getGlobalSearchCount(searchContext, classNames, groupId, globalGroupId,
					globalScope, keywords, dateField, dateFieldName, fromDate, toDate, categoriesIds,
					prefilterCategoriesIds, prefilterTagsNames,idSIGPlace, this._themeDisplay.getLocale());
			this.getSearchContainer().setTotal((int) count);
		}

		return results;
	}

    /**
     * Renvoie la liste des types d'entités sur lesquels on souhaite rechercher
     * les entries
     */
    private String[] getFilterClassNames() {
        if (_filterClassNames == null) {
            this._filterClassNames = ParamUtil.getStringValues(this._request, "className");
        }
        // Si la liste est vide, on renvoie la liste complète paramétrée via la
        // configuration (on ne recherche pas sur rien !)
        if (_filterClassNames.length == 0) {
            _filterClassNames = ArrayUtil.toStringArray(this.getClassNames());
        }
        return _filterClassNames;
    }

    /**
     * Retourne la liste des class names sur lesquelles on recherche
     */
    public List<String> getClassNames() {
        if (this._classNames == null) {
            List<String> classNames = new ArrayList<String>();
            for (String className : this._configuration.assetClassNames().split(",")) {
                if (Validator.isNotNull(className)) {
                    classNames.add(className);
                }
            }
            if (this._configuration.searchJournalArticle()) {
                classNames.add("com.liferay.journal.model.JournalArticle");
            }
            if (this._configuration.searchDocument()) {
                classNames.add(DLFileEntry.class.getName());
            }
            this._classNames = classNames;
        }
        return this._classNames;
    }

    /**
     * Renvoie la liste des catégories sur lesquelles on souhaite filtrer les
     * entries. L'opérateur entre chaque id de catégorie d'un array est un "OU", celui entre chaque liste d'array est un "ET"
     */
    private List<Long[]> getFilterCategoriesIds() {
        if (_filterCategoriesIds == null) {
            List<Long[]> filterCategoriesIds = new ArrayList<Long[]>();
            List<Long> categoriesIds = new ArrayList<Long>();

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
            for (long district : this._districts) {
                if (district > 0) {
                    categoriesIds.add(district);
                }
            }
            if (categoriesIds.size() > 0) {
                filterCategoriesIds.add(ArrayUtil.toLongArray(categoriesIds.stream().mapToLong(l -> l).toArray()));
            }

            // On récupère les thématiques s'il y en a
            for (long thematic : this._thematics) {
                if (thematic > 0) {
                    categoriesIds.add(thematic);
                }
            }
            if (categoriesIds.size() > 0) {
                filterCategoriesIds.add(ArrayUtil.toLongArray(categoriesIds.stream().mapToLong(l -> l).toArray()));
            }

            this._filterCategoriesIds = filterCategoriesIds;
        }
        return this._filterCategoriesIds;
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
            if (this._configuration.defaultDateRange() < 0) {
                return LocalDate.now().plusDays(this._configuration.defaultDateRange()).getDayOfMonth();
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
            if (this._configuration.defaultDateRange() < 0) {
                return LocalDate.now().plusDays(this._configuration.defaultDateRange()).getMonthValue();
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
            if (this._configuration.defaultDateRange() < 0) {
                return LocalDate.now().plusDays(this._configuration.defaultDateRange()).getYear();
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
            if (this._configuration.defaultDateRange() > 0) {
                return LocalDate.now().plusDays(this._configuration.defaultDateRange()).getDayOfMonth();
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
            if (this._configuration.defaultDateRange() > 0) {
                return LocalDate.now().plusDays(this._configuration.defaultDateRange()).getMonthValue();
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
            if (this._configuration.defaultDateRange() > 0) {
                return LocalDate.now().plusDays(this._configuration.defaultDateRange()).getYear();
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
                return Validator.isNotNull(this._configuration.defaultSortField())
                        ? this._configuration.defaultSortField() : "modified_sortable";
            } else {
                return "score";
            }
        } else {
            return sortFieldFromParam.split(",")[0];
        }
    }

    /**
     * Retourne le type de classement des résultats (croissant ou décroissant)
     */
    public String getSortType() {
        if (this.getSortField() == "score") {
            return "desc";
        } else {
            String sortTypeFromParam = this._sortFieldAndType;
            if (Validator.isNull(sortTypeFromParam)) {
                return Validator.isNotNull(this._configuration.defaultSortType())
                        ? this._configuration.defaultSortType() : "desc";
            } else {
                return sortTypeFromParam.split(",")[1];
            }
        }
    }

    /**
     * Retourne le searchContainer des entités
     */
    public SearchContainer<AssetEntry> getSearchContainer() {
        return this._searchContainer;
    }

    /**
     * Retourne le nombre d'items par page à afficher
     */
    public long getDelta() {
        long deltaFromParam = this._delta;
        if (deltaFromParam > 0) {
            return deltaFromParam;
        }
        if (this._configuration.delta() > 0) {
            return this._configuration.delta();
        }
        return 12;
    }

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    private ResourceRequest _request;
    private ResourceResponse _response;
    private ThemeDisplay _themeDisplay;
    private SearchAssetConfiguration _configuration;

    private String _keywords;
    private String[] _filterClassNames;
    private List<String> _classNames;
    private int _startDay;
    private String _startMonth;
    private int _startYear;
    private int _endDay;
    private String _endMonth;
    private int _endYear;
    private List<Long[]> _filterCategoriesIds;
    private long[] _projects;
    private long[] _districts;
    private long[] _thematics;
    private String _sortFieldAndType;

    private Hits _hits;
    private SearchContainer<AssetEntry> _searchContainer;
    private int _delta;
}