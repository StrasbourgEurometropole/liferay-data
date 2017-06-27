package eu.strasbourg.portlet.place_schedule;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
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
import com.liferay.portal.kernel.util.Tuple;
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
@Component(
	immediate = true,
	property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.header-portlet-css=/css/style.css",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.css-class-wrapper=place-schedule-portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/place-schedule-view.jsp",
		"javax.portlet.init-param.config-template=/configuration/place-schedule-configuration.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" },
	service = Portlet.class)
public class PlaceSchedulePortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest request, RenderResponse response)
		throws IOException, PortletException {
		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);
			Locale locale = themeDisplay.getLocale();

			PlaceScheduleConfiguration configuration = themeDisplay
				.getPortletDisplay().getPortletInstanceConfiguration(
					PlaceScheduleConfiguration.class);

			// récupère le texte de la configuration
			String text = "";
			Map<Locale, String> mapText = LocalizationUtil
				.getLocalizationMap(configuration.textScheduleXML());
			for (Map.Entry<Locale, String> map : mapText.entrySet()) {
				if (themeDisplay.getLocale().toString()
					.equals(map.getKey().toString())) {
					text = HtmlUtil.unescape(map.getValue());
					break;
				}
			}
			request.setAttribute("textSchedule", text);

			// récupère le plid
			long plId = 0;
			String layoutUuid = configuration.linksUuids();
			if (Validator.isNotNull(layoutUuid)) {
				Layout layout = LayoutLocalServiceUtil
					.fetchLayoutByUuidAndGroupId(layoutUuid,
						themeDisplay.getScopeGroupId(), false);
				if (Validator.isNotNull(layout)) {
					plId = layout.getPlid();
				}
			}
			request.setAttribute("plId", plId);

			// réupère le jour voulue
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String dateChoisie = ParamUtil.getString(request, "date");
			GregorianCalendar jourChoisi = new GregorianCalendar();
			if (Validator.isNotNull(dateChoisie)) {
				jourChoisi.set(Integer.parseInt(dateChoisie.substring(0, 4)),
					Integer.parseInt(dateChoisie.substring(5, 7)) - 1,
					Integer.parseInt(dateChoisie.substring(8, 10)));
			}
			jourChoisi.set(Calendar.HOUR_OF_DAY, 0);
			jourChoisi.clear(Calendar.MINUTE);
			jourChoisi.clear(Calendar.SECOND);
			jourChoisi.clear(Calendar.MILLISECOND);
			request.setAttribute("jourChoisi", jourChoisi.getTime());

			// récupère la semaine passée et future
			GregorianCalendar jourSemaine = new GregorianCalendar();
			jourSemaine.setTime(jourChoisi.getTime());
			GregorianCalendar previous = new GregorianCalendar();
			GregorianCalendar next = new GregorianCalendar();
			previous.setTime(jourSemaine.getTime());
			next.setTime(jourSemaine.getTime());
			previous.add(Calendar.DAY_OF_YEAR, -7);
			next.add(Calendar.DAY_OF_YEAR, 7);
			request.setAttribute("previous", sf.format(previous.getTime()));
			request.setAttribute("next", sf.format(next.getTime()));

			// récupère les jours de la semaine voulue
			DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, locale);
			DateFormat df2 = DateFormat.getDateInstance(DateFormat.SHORT,
				locale);
			List<String[]> week = new ArrayList<String[]>();
			int delta = -jourSemaine.get(GregorianCalendar.DAY_OF_WEEK) + 2;
			jourSemaine.add(Calendar.DAY_OF_MONTH, delta);
			for (int jour = 0; jour < 7; jour++) {
				StringBuilder date = new StringBuilder(
					df.format(jourSemaine.getTime()));
				date.replace(0, 1, date.substring(0, 1).toUpperCase());
				String[] dates = { date.toString(),
					df2.format(jourSemaine.getTime()) };
				week.add(dates);
				jourSemaine.add(Calendar.DAY_OF_MONTH, 1);
			}
			request.setAttribute("semaine", week);

			// récupère la catégorie
			long categoryIdFromConfiguration = configuration.categoryId();
			long categoryIdFromParam = ParamUtil.getLong(request, "categoryId");
			if (Validator.isNull(categoryIdFromConfiguration)
				&& Validator.isNull(categoryIdFromParam)) {
				request.setAttribute("noconfig", true);
				super.render(request, response);
				return;
			}
			Long categoryId;
			if (Validator.isNull(categoryIdFromParam)) {
				categoryId = categoryIdFromConfiguration;
			} else {
				categoryId = categoryIdFromParam;
			}
			AssetCategory category = AssetCategoryLocalServiceUtil
				.fetchAssetCategory(categoryId);
			if (Validator.isNull(category)) {
				request.setAttribute("noconfig", true);
				super.render(request, response);
				return;
			}
			request.setAttribute("category", category);
			request.setAttribute("piscine",
				AssetVocabularyHelper.isSwimmingPool(category));
			request.setAttribute("parking",
				AssetVocabularyHelper.isParking(category));

			List<Place> selectedPlaces = new ArrayList<Place>();

			List<ObjectValuePair<String, List<PlaceSchedule>>> exceptions = new ArrayList<ObjectValuePair<String, List<PlaceSchedule>>>();
			long placeId = ParamUtil.getLong(request, "placeId");
			// Récupère le lieu choisi
			if (Validator.isNotNull(placeId)) {
				request.setAttribute("placeId", placeId);
				Place place = PlaceLocalServiceUtil.fetchPlace(placeId);
				selectedPlaces.add(place);
				// récupération des ouvertures et fermetures exceptionnelles du
				// lieu sur 2 mois
				List<PlaceSchedule> placeSchedules = place
					.getPlaceScheduleException(jourChoisi, true, locale);
				if (!placeSchedules.isEmpty()) {
					ObjectValuePair<String, List<PlaceSchedule>> placeName_Exceptions = new ObjectValuePair<>(
						place.getAlias(locale), placeSchedules);
					exceptions.add(placeName_Exceptions);
				}
				// récupération des ouvertures et fermetures exceptionnelles des
				// sous lieux du lieu sur 2 mois
				List<SubPlace> subPlaces = place.getSubPlaces();
				for (SubPlace subPlace : subPlaces) {
					placeSchedules = subPlace
						.getSubPlaceScheduleException(jourChoisi, true, locale);
					if (!placeSchedules.isEmpty()) {
						ObjectValuePair<String, List<PlaceSchedule>> placeName_Exceptions = new ObjectValuePair<>(
							subPlace.getName(locale), placeSchedules);
						exceptions.add(placeName_Exceptions);
					}
				}
			}

			// Récupère tous les lieux publiés de la catégorie
			List<Place> places = new ArrayList<Place>();
			if (Validator.isNotNull(category)) {
				List<AssetEntry> assetEntries = AssetEntryLocalServiceUtil
					.getAssetCategoryAssetEntries(categoryId);
				for (AssetEntry assetEntry : assetEntries) {
					if (Validator.isNotNull(assetEntry)) {
						Place place = PlaceLocalServiceUtil
							.fetchPlaceByUuidAndGroupId(
								assetEntry.getClassUuid(),
								themeDisplay.getCompanyGroupId());
						if (Validator.isNotNull(place) && place.isApproved()) {
							places.add(place);
							if (Validator.isNull(placeId)) {
								selectedPlaces.add(place);
								// récupération des ouvertures et fermetures
								// exceptionnelles des lieux sur 2 mois
								List<PlaceSchedule> placeSchedules = place
									.getPlaceScheduleException(jourChoisi, true,
										locale);
								if (!placeSchedules.isEmpty()) {
									ObjectValuePair<String, List<PlaceSchedule>> placeName_Exceptions = new ObjectValuePair<>(
										place.getAlias(locale), placeSchedules);
									exceptions.add(placeName_Exceptions);
								}
								// récupération des ouvertures et fermetures
								// exceptionnelles des
								// sous lieux du lieu sur 2 mois
								List<SubPlace> subPlaces = place.getSubPlaces();
								for (SubPlace subPlace : subPlaces) {
									placeSchedules = subPlace
										.getSubPlaceScheduleException(
											jourChoisi, true, locale);
									if (!placeSchedules.isEmpty()) {
										ObjectValuePair<String, List<PlaceSchedule>> placeName_Exceptions = new ObjectValuePair<>(
											subPlace.getName(locale),
											placeSchedules);
										exceptions.add(placeName_Exceptions);
									}
								}
							}
						}
					}
				}
			}

			exceptions.stream()
				.sorted((p1, p2) -> p1.getValue().get(0).getStartDate()
					.compareTo(p2.getValue().get(0).getEndDate()))
				.collect(Collectors.toList());
			request.setAttribute("exceptions", exceptions);
			request.setAttribute("selectedPlaces", selectedPlaces);
			request.setAttribute("places", places);

			// request.setAttribute("detailURL",
			// StrasbourgPropsUtil.getPlaceDetailURL());

			super.render(request, response);
		} catch (Exception e) {
			_log.error(e);
		}

	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}