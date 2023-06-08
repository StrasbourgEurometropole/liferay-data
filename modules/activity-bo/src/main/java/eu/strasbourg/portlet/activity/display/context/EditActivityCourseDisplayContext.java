package eu.strasbourg.portlet.activity.display.context;

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
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.activity.model.ActivityCourse;
import eu.strasbourg.service.activity.model.ActivityCoursePlace;
import eu.strasbourg.service.activity.service.ActivityCourseLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;

public class EditActivityCourseDisplayContext {

	private ActivityCourse activityCourse;
	private final RenderRequest request;
	private final ThemeDisplay themeDisplay;
	private List<AssetCategory> cities;
	private List<AssetCategory> periods;

	public EditActivityCourseDisplayContext(RenderRequest request,
		RenderResponse response) {
		this.request = request;
		this.themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
	}

	/**
	 * Retourne l'entité éditée
	 */
	public ActivityCourse getActivityCourse() throws PortalException {
		long activityCourseId = ParamUtil.getLong(request, "activityCourseId");
		if (activityCourse == null && activityCourseId > 0) {
			activityCourse = ActivityCourseLocalServiceUtil
				.getActivityCourse(activityCourseId);
		}
		
		return activityCourse;
	}

	/**
	 * Retourne la liste des locales disponibles
	 */
	public Locale[] getAvailableLocales() {
		Set<Locale> availableLocalesSet = LanguageUtil
			.getAvailableLocales(themeDisplay.getScopeGroupId());
		Locale[] availableLocales = availableLocalesSet
			.toArray(new Locale[availableLocalesSet.size()]);
		for (int i = 0; i < availableLocales.length; i++) {
			if (availableLocales[i].equals(Locale.FRANCE)) {
				Locale buffer = availableLocales[0];
				availableLocales[0] = availableLocales[i];
				availableLocales[i] = buffer;
				break;
			}
		}
		return availableLocales;
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return themeDisplay.getPermissionChecker().hasPermission(
			this.themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.ACTIVITY_BO,
			StrasbourgPortletKeys.ACTIVITY_BO, actionId);
	}

	/**
	 * Renvoie les indexes des lieux par défaut
	 */
	public String getDefaultPlaceIndexes() throws PortalException {
		if (this.getActivityCourse() != null) {
			List<ActivityCoursePlace> places = this.getActivityCourse()
				.getActivityCoursePlaces();
			String indexes = "0";
			for (int i = 1; i <= places.size(); i++) {
				indexes += "," + i;
			}
			return indexes;
		}
		return "";
	}

	/**
	 * Retourne la liste des villes
	 */
	public List<AssetCategory> getCities() throws PortalException {
		if (Validator.isNull(cities)) {
			AssetVocabulary territoriesVocabulary = AssetVocabularyHelper
				.getGlobalVocabulary(VocabularyNames.TERRITORY);
			if (territoriesVocabulary != null) {
				cities = territoriesVocabulary.getCategories().stream().filter(c -> {
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
	 * Retourne la liste des périodes d'activité
	 */
	public List<AssetCategory> getPeriods() throws PortalException {
		if (Validator.isNull(periods)) {
			AssetVocabulary periodsVocabulary = AssetVocabularyHelper.getVocabulary(
				VocabularyNames.ACTIVITY_PERIOD,
				themeDisplay.getScopeGroupId());
			periods = periodsVocabulary.getCategories();
		}
		return periods;
	}
}
