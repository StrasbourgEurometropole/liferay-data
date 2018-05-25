package eu.strasbourg.portlet.place_schedule;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.place_schedule.configuration.PlaceScheduleConfiguration;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.model.PlaceSchedule;
import eu.strasbourg.service.place.model.SubPlace;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;

/**
 * @author 01i454
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=false", "com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.css-class-wrapper=place-schedule-portlet", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.config-template=/configuration/place-schedule-configuration.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class PlaceSchedulePortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			Locale locale = themeDisplay.getLocale();

			PlaceScheduleConfiguration configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(PlaceScheduleConfiguration.class);

			String template = configuration.template();
			
			// récupère le texte de la configuration
			String text = "";
			Map<Locale, String> mapText = LocalizationUtil.getLocalizationMap(configuration.textScheduleXML());
			for (Map.Entry<Locale, String> map : mapText.entrySet()) {
				if (themeDisplay.getLocale().toString().equals(map.getKey().toString())) {
					text = HtmlUtil.unescape(map.getValue());
					break;
				}
			}
			request.setAttribute("textSchedule", text);

			// récupère le plid
			long plId = 0;
			String layoutUuid = configuration.linksUuids();
			if (Validator.isNotNull(layoutUuid)) {
				Layout layout = LayoutLocalServiceUtil.fetchLayoutByUuidAndGroupId(layoutUuid,
						themeDisplay.getScopeGroupId(), false);
				if (Validator.isNotNull(layout)) {
					plId = layout.getPlid();
				}
			}
			request.setAttribute("plId", plId);

			// réupère le jour voulu
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			GregorianCalendar jourChoisi = new GregorianCalendar();
			jourChoisi.set(this.getSelectedYear(request), this.getSelectedMonthIndex(request),
					this.getSelectedDay(request));
			jourChoisi.set(Calendar.HOUR_OF_DAY, 0);
			jourChoisi.clear(Calendar.MINUTE);
			jourChoisi.clear(Calendar.SECOND);
			jourChoisi.clear(Calendar.MILLISECOND);
			String dateFromParam = ParamUtil.getString(request, "date");
			if (Validator.isNotNull(dateFromParam)) {
				try {
					jourChoisi.setTime(sf.parse(dateFromParam));
				} catch (Exception ex) {

				}
			}
			request.setAttribute("jourChoisi", jourChoisi.getTime());
			request.setAttribute("selectedDate", jourChoisi.getTime());
			GregorianCalendar selectedCalendar = new GregorianCalendar();
			selectedCalendar.setTime(jourChoisi.getTime());
			request.setAttribute("selectedCalendar", selectedCalendar);
			request.setAttribute("selectedDay", jourChoisi.get(Calendar.DAY_OF_MONTH));
			request.setAttribute("selectedMonth", jourChoisi.get(Calendar.MONTH));
			request.setAttribute("selectedYear", jourChoisi.get(Calendar.YEAR));

			// récupère la semaine passée et future

			GregorianCalendar jourSemaine = new GregorianCalendar();
			jourSemaine.setTime(jourChoisi.getTime());
			GregorianCalendar previous = new GregorianCalendar();
			GregorianCalendar next = new GregorianCalendar();
			previous.setTime(jourSemaine.getTime());
			next.setTime(jourSemaine.getTime());
			int lengthOfWeek = configuration.template() != null && configuration.template().equals("strasbourg-table")
					? 5 : 7;
			previous.add(Calendar.DAY_OF_YEAR, -lengthOfWeek);
			next.add(Calendar.DAY_OF_YEAR, lengthOfWeek);
			request.setAttribute("previous", sf.format(previous.getTime()));
			request.setAttribute("next", sf.format(next.getTime()));

			// récupère les jours de la semaine voulue
			DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, locale);
			DateFormat df2 = DateFormat.getDateInstance(DateFormat.SHORT, locale);
			List<String[]> week = new ArrayList<String[]>();
			int delta = configuration.template() != null && configuration.template().equals("strasbourg-table") ? 0
					: -jourSemaine.get(GregorianCalendar.DAY_OF_WEEK) + 2;
			jourSemaine.add(Calendar.DAY_OF_MONTH, delta);
			List<Date> weekDates = new ArrayList<Date>(); // Liste des jours à
															// afficher en front
			for (int jour = 0; jour < lengthOfWeek; jour++) {
				StringBuilder date = new StringBuilder(df.format(jourSemaine.getTime()));
				date.replace(0, 1, date.substring(0, 1).toUpperCase());
				String[] dates = { date.toString(), df2.format(jourSemaine.getTime()) };
				week.add(dates);
				weekDates.add(jourSemaine.getTime());
				jourSemaine.add(Calendar.DAY_OF_MONTH, 1);
			}
			request.setAttribute("semaine", week);
			request.setAttribute("weekDates", weekDates);

			// récupère la catégorie
			long categoryIdFromConfiguration = configuration.categoryId();
			long categoryIdFromParam = ParamUtil.getLong(request, "categoryId");
			if (Validator.isNull(categoryIdFromConfiguration) && Validator.isNull(categoryIdFromParam)) {
				request.setAttribute("noconfig", true);
			
				if (Validator.isNull(template)) {
					template = "default";
				}
				include("/templates/" + template + ".jsp", request, response);
				
				return;
			}
			Long categoryId;
			if (Validator.isNull(categoryIdFromParam)) {
				categoryId = categoryIdFromConfiguration;
			} else {
				categoryId = categoryIdFromParam;
			}
			AssetCategory category = AssetCategoryLocalServiceUtil.fetchAssetCategory(categoryId);
			if (Validator.isNull(category)) {
				request.setAttribute("noconfig", true);
				
				if (Validator.isNull(template)) {
					template = "default";
				}
				include("/templates/" + template + ".jsp", request, response);
				
				return;
			}
			request.setAttribute("category", category);
			request.setAttribute("piscine", AssetVocabularyHelper.isSwimmingPool(category));
			request.setAttribute("parking", AssetVocabularyHelper.isParking(category));
			request.setAttribute("mairie", AssetVocabularyHelper.isMairie(category));

			List<Place> selectedPlaces = new ArrayList<Place>();

			List<ObjectValuePair<String[], PlaceSchedule>> exceptions = new ArrayList<ObjectValuePair<String[], PlaceSchedule>>();
			long placeId = ParamUtil.getLong(request, "placeId");
			// Récupère le lieu choisi
			if (Validator.isNotNull(placeId)) {
				request.setAttribute("placeId", placeId);
				Place place = PlaceLocalServiceUtil.fetchPlace(placeId);
				selectedPlaces.add(place);
				// récupération des ouvertures et fermetures exceptionnelles du
				// lieu sur 2 mois à partir du lundi de la semaine choisie
				List<PlaceSchedule> placeSchedules = place.getPlaceScheduleException(jourChoisi, true, locale);
				if (!placeSchedules.isEmpty()) {
					for (PlaceSchedule schedule : placeSchedules) {
						ObjectValuePair<String[], PlaceSchedule> placeName_Exception = new ObjectValuePair<>(
								new String[] {place.getAlias(locale)}, schedule);
						exceptions.add(placeName_Exception);
					}
				}
				// récupération des ouvertures et fermetures exceptionnelles des
				// sous lieux du lieu sur 2 mois à partir du lundi de la semaine choisie
				List<SubPlace> subPlaces = place.getSubPlaces();
				for (SubPlace subPlace : subPlaces) {
					placeSchedules = subPlace.getSubPlaceScheduleException(jourChoisi, true, locale);
					if (!placeSchedules.isEmpty()) {
						for (PlaceSchedule schedule : placeSchedules) {
							ObjectValuePair<String[], PlaceSchedule> placeName_Exception = new ObjectValuePair<>(
									new String[] {place.getAlias(locale), subPlace.getName(locale)}, schedule);
							exceptions.add(placeName_Exception);
						}
					}
				}
			}

			// Récupère tous les lieux publiés de la catégorie
			List<Place> places = new ArrayList<Place>();
			if (Validator.isNotNull(category)) {
				List<AssetEntry> assetEntries = AssetEntryLocalServiceUtil.getAssetCategoryAssetEntries(categoryId);
				for (AssetEntry assetEntry : assetEntries) {
					if (Validator.isNotNull(assetEntry)) {
						Place place = PlaceLocalServiceUtil.fetchPlaceByUuidAndGroupId(assetEntry.getClassUuid(),
								themeDisplay.getCompanyGroupId());
						if (Validator.isNotNull(place) && place.isApproved()) {
							places.add(place);
							if (Validator.isNull(placeId)) {
								selectedPlaces.add(place);
								// récupération des ouvertures et fermetures
								// exceptionnelles des lieux sur 2 mois à partir du lundi de la semaine choisie
								List<PlaceSchedule> placeSchedules = place.getPlaceScheduleException(jourChoisi, true,
										locale);
								if (!placeSchedules.isEmpty()) {
									for (PlaceSchedule schedule : placeSchedules) {
										ObjectValuePair<String[], PlaceSchedule> placeName_Exception = new ObjectValuePair<>(
												new String[] {place.getAlias(locale)}, schedule);
										exceptions.add(placeName_Exception);
									}
								}
								// récupération des ouvertures et fermetures
								// exceptionnelles des
								// sous lieux du lieu sur 2 mois à partir du lundi de la semaine choisie
								List<SubPlace> subPlaces = place.getSubPlaces();
								for (SubPlace subPlace : subPlaces) {
									placeSchedules = subPlace.getSubPlaceScheduleException(jourChoisi, true, locale);
									if (!placeSchedules.isEmpty()) {
										for (PlaceSchedule schedule : placeSchedules) {
											ObjectValuePair<String[], PlaceSchedule> placeName_Exception = new ObjectValuePair<>(
													new String[] {place.getAlias(locale), subPlace.getName(locale)}, schedule);
											exceptions.add(placeName_Exception);
										}
									}
								}
							}
						}
					}
				}
			}

			exceptions = exceptions.stream()
					.sorted((p1, p2) -> p1.getValue().getStartDate().compareTo(p2.getValue().getStartDate()))
					.filter(e -> e.getValue().getEndDate().compareTo(jourChoisi.getTime()) >= 0)
					.collect(Collectors.toList());

			
			// On retire les fermetures exceptionnelles des sous lieux si le lieu est fermé
			ObjectValuePair<String[], PlaceSchedule> oldException = null;
			for (int i = 0; i < exceptions.size(); i++) {
				ObjectValuePair<String[], PlaceSchedule> exception = exceptions.get(i);
				if (oldException != null && exception.getKey()[0].equals(oldException.getKey()[0])
						&& exception.getValue().getPeriod().equals(oldException.getValue().getPeriod())) {
						if (oldException.getValue().isClosed()) {
							exceptions.remove(i);
							i--;
						}
				} else {
					oldException = exception;
				}
			}
			request.setAttribute("exceptions", exceptions);
			request.setAttribute("selectedPlaces", selectedPlaces);
			request.setAttribute("places", places);

			// request.setAttribute("detailURL",
			// StrasbourgPropsUtil.getPlaceDetailURL());

			
			if (Validator.isNull(template)) {
				template = "default";
			}
			include("/templates/" + template + ".jsp", request, response);
		} catch (Exception e) {
			_log.error(e);
		}

	}

	/**
	 * Retourne la jour du mois de la date de recherche. Soit depuis les
	 * paramètres de la requête soit de la date du jour
	 */
	private int getSelectedDay(PortletRequest request) {
		int fromParam = ParamUtil.getInteger(request, "day");
		if (fromParam > 0) {
			return fromParam;
		} else {
			return LocalDate.now().getDayOfMonth();
		}
	}

	/**
	 * Retourne le mois de la date dz recherche depuis les paramètres de la
	 * requête ou de la date du jour
	 */
	private int getSelectedMonthIndex(PortletRequest request) {
		return getSelectedMonthValue(request) - 1;
	}

	/**
	 * Retourne le mois de la date de recherche depuis les paramètres de la
	 * requête ou de la date du jour [1;12]
	 */
	private int getSelectedMonthValue(PortletRequest request) {
		String fromMonthString = ParamUtil.getString(request, "month");
		if (Validator.isNull(fromMonthString)) {
			return LocalDate.now().getMonthValue();

		} else {
			return ParamUtil.getInteger(request, "month") + 1;
		}
	}

	/**
	 * Retourne l'année de la date de recherche depuis les paramètres de la
	 * requête ou de la date du jour
	 */
	private int getSelectedYear(PortletRequest request) {
		int fromParam = ParamUtil.getInteger(request, "year");
		if (fromParam > 0) {
			return fromParam;
		} else {
			return LocalDate.now().getYear();
		}
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}