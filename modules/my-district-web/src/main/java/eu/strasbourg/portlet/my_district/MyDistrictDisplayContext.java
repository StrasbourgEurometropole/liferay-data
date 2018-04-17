package eu.strasbourg.portlet.my_district;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import eu.strasbourg.portlet.my_district.configuration.MyDistrictConfiguration;
import eu.strasbourg.service.adict.AdictService;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.official.model.Official;
import eu.strasbourg.service.official.service.OfficialLocalServiceUtil;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.service.strasbourg.service.StrasbourgServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.utils.SearchHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

public class MyDistrictDisplayContext {

	private ThemeDisplay themeDisplay;
	private RenderRequest request;
	private MyDistrictConfiguration configuration;
	private String address;
	private AssetCategory district;
	private AdictService adictService;
	private List<AssetEntry> actuAndWebMag;
	private List<AssetEntry> events;

	public MyDistrictDisplayContext(ThemeDisplay themeDisplay, RenderRequest request, AdictService adict) {
		this.themeDisplay = themeDisplay;
		this.request = request;
		this.adictService = adict;
		try {
			this.configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(MyDistrictConfiguration.class);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	public MyDistrictConfiguration getConfiguration() {
		return configuration;
	}

	public String getVirtualHostName() {
		Group group = GroupLocalServiceUtil.fetchFriendlyURLGroup(this.themeDisplay.getCompanyId(), "/strasbourg.eu");
		return group.getPublicLayoutSet().getVirtualHostname();
	}

	// Récupération de l'id utilisateur
	private String getPublikID(PortletRequest resourceRequest) {

		LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(resourceRequest);
		HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();

		return SessionParamUtil.getString(originalRequest, "publik_internal_id");
	}

	// récupération de l'adresse de l'utilisateur
	public String getAddress() {
		if (address == null) {

			// Récupération du publik ID avec la session
			String internalId = getPublikID(request);
			if (Validator.isNotNull(internalId)) {
				JSONObject userDetail = PublikApiClient.getUserDetails(internalId);
				if (Validator.isNotNull(userDetail.get("address")) && Validator.isNotNull(userDetail.get("zipcode"))
						&& Validator.isNotNull(userDetail.get("city")))
					address = userDetail.get("address") + " " + userDetail.get("zipcode") + " "
							+ userDetail.get("city");
			}
		}

		return address;
	}

	// récupération du texte à afficher si l'utilisateur n'a pas renseigné son
	// adresse
	public String getNoAddressText() {
		String noAddress = "";
		Map<Locale, String> mapText = LocalizationUtil.getLocalizationMap(configuration.noAddressXML());
		for (Map.Entry<Locale, String> map : mapText.entrySet()) {
			if (themeDisplay.getLocale().toString().equals(map.getKey().toString())) {
				noAddress = HtmlUtil.unescape(map.getValue());
				break;
			}
		}
		if (Validator.isNull(noAddress)) {
			noAddress = "No configuration";
		}
		return noAddress;
	}

	// récupération de la catégorie "quartier" de l'utilisateur
	public AssetCategory getDistrict() {
		if (district == null) {
			JSONArray coordinates = null;
			if (Validator.isNotNull(getAddress())) {
				// TODO Angel : Lequel garder ?
				coordinates = StrasbourgServiceUtil.getCoordinateForAddress(address);
				coordinates = adictService.getCoordinateForAddress(address);
			}
			if (Validator.isNotNull(coordinates)) {
				String[] sectorTypes = { "quartier_elus" };
				List<String> sigIds = adictService.getSectorizedPlaceIdsForCoordinates(coordinates.get(0).toString(),
						coordinates.get(1).toString(), sectorTypes);
				if (!sigIds.isEmpty()) {
					AssetVocabulary territoryVocabulary;
					try {
						territoryVocabulary = AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.TERRITORY);
						district = AssetVocabularyHelper.getCategoryByExternalId(territoryVocabulary, sigIds.get(0));
					} catch (PortalException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return district;
	}

	// récupération de l'adjoint de quartier
	public Official getOfficial() {
		Official official = null;
		if (Validator.isNotNull(getDistrict())) {
			List<AssetEntry> assetEntries = AssetEntryLocalServiceUtil
					.getAssetCategoryAssetEntries(district.getCategoryId());
			// ne garde que les élus
			assetEntries.removeIf(e -> !(e.getClassName().equals(Official.class.getName())));
			if (!assetEntries.isEmpty()) {
				official = OfficialLocalServiceUtil.fetchOfficial(assetEntries.get(0).getClassPK());
			}
		}
		return official;
	}

	// récupération du texte à afficher pour la mairie de proximité
	public String getTownHallText() {
		String townHall = "";
		Map<Locale, String> mapText = LocalizationUtil.getLocalizationMap(configuration.townHallXML());
		for (Map.Entry<Locale, String> map : mapText.entrySet()) {
			if (themeDisplay.getLocale().toString().equals(map.getKey().toString())) {
				townHall = HtmlUtil.unescape(map.getValue());
				break;
			}
		}
		if (Validator.isNull(townHall)) {
			townHall = "No configuration";
		}
		return townHall;
	}

	// récupération de la mairie de proximité
	public Place getTownHall() {
		Place townHall = null;
		if (Validator.isNotNull(getDistrict())) {
			List<AssetEntry> assetEntries = AssetEntryLocalServiceUtil
					.getAssetCategoryAssetEntries(district.getCategoryId());
			// ne garde que les lieux
			assetEntries.removeIf(e -> !(e.getClassName().equals(Place.class.getName())));
			// ne garde que les mairies
			AssetVocabulary placeTypeVocabulary;
			try {
				placeTypeVocabulary = AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.PLACE_TYPE);
				AssetCategory townHallCategory = AssetVocabularyHelper.getCategoryByExternalId(placeTypeVocabulary,
						"Cat_12_07");
				assetEntries.removeIf(e -> !(e.getCategories().contains(townHallCategory)));
			} catch (PortalException e1) {
				e1.printStackTrace();
			}
			if (!assetEntries.isEmpty()) {
				townHall = PlaceLocalServiceUtil.fetchPlace(assetEntries.get(0).getClassPK());
			}
		}
		return townHall;
	}

	// récupération du texte à afficher pour la direction de territoire
	public String getTerritoryDirectionText() {
		String territoryDirection = "";
		Map<Locale, String> mapText = LocalizationUtil.getLocalizationMap(configuration.territoryDirectionXML());
		for (Map.Entry<Locale, String> map : mapText.entrySet()) {
			if (themeDisplay.getLocale().toString().equals(map.getKey().toString())) {
				territoryDirection = HtmlUtil.unescape(map.getValue());
				break;
			}
		}
		if (Validator.isNull(territoryDirection)) {
			territoryDirection = "No configuration";
		}
		return territoryDirection;
	}

	// récupération de la direction de territoire
	public Place getTerritoryDirection() {
		Place territoryDirection = null;
		if (Validator.isNotNull(getDistrict())) {
			List<AssetEntry> assetEntries = AssetEntryLocalServiceUtil
					.getAssetCategoryAssetEntries(district.getCategoryId());
			// ne garde que les lieux
			assetEntries.removeIf(e -> !(e.getClassName().equals(Place.class.getName())));
			// ne garde que les direction de territoire
			AssetVocabulary placeTypeVocabulary;
			try {
				placeTypeVocabulary = AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.PLACE_TYPE);
				AssetCategory territoryDirectionCategory = AssetVocabularyHelper
						.getCategoryByExternalId(placeTypeVocabulary, "Cat_12_05");
				assetEntries.removeIf(e -> !(e.getCategories().contains(territoryDirectionCategory)));
			} catch (PortalException e1) {
				e1.printStackTrace();
			}
			if (!assetEntries.isEmpty()) {
				territoryDirection = PlaceLocalServiceUtil.fetchPlace(assetEntries.get(0).getClassPK());
			}
		}
		return territoryDirection;
	}

	// récupération des écoles du secteur
	public List<Place> getSectorSchools() {
		List<Place> sectorSchools = new ArrayList<Place>();
		if (Validator.isNotNull(getDistrict())) {
			List<AssetEntry> assetEntries = AssetEntryLocalServiceUtil
					.getAssetCategoryAssetEntries(district.getCategoryId());
			// ne garde que les lieux
			assetEntries.removeIf(e -> !(e.getClassName().equals(Place.class.getName())));
			// ne garde que les écoles
			AssetVocabulary placeTypeVocabulary;
			try {
				placeTypeVocabulary = AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.PLACE_TYPE);
				// écoles élémentaires
				AssetCategory elementarySchoolCategory = AssetVocabularyHelper
						.getCategoryByExternalId(placeTypeVocabulary, "Cat_19_04");
				// écoles maternelles
				AssetCategory preSchoolCategory = AssetVocabularyHelper.getCategoryByExternalId(placeTypeVocabulary,
						"Cat_19_05");
				assetEntries.removeIf(e -> !(e.getCategories().contains(elementarySchoolCategory)
						|| e.getCategories().contains(preSchoolCategory)));
			} catch (PortalException e1) {
				e1.printStackTrace();
			}
			if (!assetEntries.isEmpty()) {
				for (AssetEntry assetEntry : assetEntries) {
					sectorSchools.add(PlaceLocalServiceUtil.fetchPlace(assetEntry.getClassPK()));
				}
			}
		}
		return sectorSchools;
	}

	// récupération des actus et webmag
	public List<AssetEntry> getActusAndWebmags() {
		if (actuAndWebMag == null) {
			List<AssetEntry> actuWebmag = new ArrayList<AssetEntry>();

			// Search context
			HttpServletRequest servletRequest = PortalUtil.getHttpServletRequest(request);
			SearchContext searchContext = SearchContextFactory.getInstance(servletRequest);

			// ClassNames
			String[] classNames = new String[1];
			classNames[0] = JournalArticle.class.getName();

			// GroupId
			Group group = GroupLocalServiceUtil.fetchFriendlyURLGroup(this.themeDisplay.getCompanyId(),
					"/strasbourg.eu");
			long groupId = group.getGroupId();

			// Catégories de la recherche utilisateur (non existantes pour ce
			// portlet)
			List<Long[]> categoriesRechercheIds = new ArrayList<Long[]>();
			Long[] categId = new Long[1];
			categId[0] = getDistrict().getCategoryId();
			categoriesRechercheIds.add(categId);

			// Recherche
			Hits hits = SearchHelper.getGlobalSearchHits(searchContext, classNames, groupId,
					this.themeDisplay.getCompanyGroupId(), true, "", false, "", null, null, categoriesRechercheIds,
					new ArrayList<Long[]>(), StringUtil.split("actu,webmag"), false, this.themeDisplay.getLocale(), 0,
					12, "modified_sortable", true);

			// On renvoie la liste des actualités classés par date de
			// publication
			for (Document document : hits.getDocs()) {
				AssetEntry entry = null;
				entry = AssetEntryLocalServiceUtil.fetchEntry(JournalArticle.class.getName(),
						GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
				if (entry != null) {
					actuWebmag.add(entry);
				}
			}
			actuAndWebMag = actuWebmag;
		}

		return actuAndWebMag;
	}

	private String getJournalArticleFieldValue(JournalArticle article, String field, Locale locale) {
		String content = article.getContentByLocale(locale.toString());

		String value = "";

		com.liferay.portal.kernel.xml.Document document = null;

		try {
			document = SAXReaderUtil.read(new StringReader(content));
			Node node = document.selectSingleNode("/root/dynamic-element[@name='" + field + "']/dynamic-content");
			if (node.getText().length() > 0) {
				value = node.getText();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return value;
	}
	
	public String getJournalArticleTitle(JournalArticle article, Locale locale) {
		return getJournalArticleFieldValue(article, "title", locale);
	}

	public String getJournalArticleCatcher(JournalArticle article, Locale locale) {
		return getJournalArticleFieldValue(article, "chapo", locale);
	}

	public String getJournalArticleImage(JournalArticle article, Locale locale) {
		return getJournalArticleFieldValue(article, "thumbnail", locale);
	}

	public String getJSONEncodedString(String source) {
		return HtmlUtil.escapeJS(source);
	}

	public boolean isMag(String[] tagNames) {
		if (Arrays.toString(tagNames).contains("euromag") || Arrays.toString(tagNames).contains("villemag")
				|| Arrays.toString(tagNames).contains("webmag"))
			return true;
		else
			return false;
	}
	
	// récupération des évènements
	public List<AssetEntry> getEvents() {
		if (events == null) {
			List<AssetEntry> entries = new ArrayList<AssetEntry>();
			entries.addAll(AssetEntryLocalServiceUtil.getAssetCategoryAssetEntries(getDistrict().getCategoryId()));
			List<Long> classPks = entries.stream().map(AssetEntry::getClassPK).collect(Collectors.toList());
			Criterion idCriterion = RestrictionsFactoryUtil.in("eventId", classPks);
			Criterion statusCriterion = RestrictionsFactoryUtil.eq("status", WorkflowConstants.STATUS_APPROVED);
			DynamicQuery eventQuery = EventLocalServiceUtil.dynamicQuery().add(idCriterion).add(statusCriterion);
			eventQuery.setLimit(0, 12);
			List<Event> listEvent = EventLocalServiceUtil.dynamicQuery(eventQuery);
			List<AssetEntry> result = new ArrayList<AssetEntry>();
			for (Event event : listEvent) {
				AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(Event.class.getName(),
						event.getPrimaryKey());
				if (assetEntry != null) {
					result.add(assetEntry);
				}
			}

			events = result;
		}
		return events;
	}

	public String DeleteTag(String html) {

		Pattern p = Pattern.compile("<[^>]*>");
		Matcher m = p.matcher(html); 
		return m.replaceAll("");
	}
}