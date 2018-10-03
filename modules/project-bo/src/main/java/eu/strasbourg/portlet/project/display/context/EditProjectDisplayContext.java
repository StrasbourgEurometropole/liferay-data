package eu.strasbourg.portlet.project.display.context;

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
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.model.ProjectTimeline;
import eu.strasbourg.service.project.service.ProjectLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;

public class EditProjectDisplayContext {
	
	private Project _project;
	private List<AssetCategory> _cities;
	private final RenderRequest _request;
	private final ThemeDisplay _themeDisplay;
	
	public EditProjectDisplayContext(RenderRequest request,
		RenderResponse response) {
		this._request = request;
		this._themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
	}

	public Project getProject() {
		long projectId = ParamUtil.getLong(_request, "projectId");
		if (_project == null && projectId > 0) {
			_project = ProjectLocalServiceUtil.fetchProject(projectId);
		}
		return _project;
	}
		
	public String getDefaultTimelineIndexes() throws PortalException {
		if (this.getProject() != null) {
			List<ProjectTimeline> timelines = this.getProject().getProjectTimelines();
			String indexes = "0";
			for (int i = 1; i <= timelines.size(); i++) {
				indexes += "," + i;
			}
			return indexes;
		}
		return "";
	}
	
	/**
	 * Renvoie les indexes des lieux par défaut
	 */
	public String getDefaultPlaceIndexes() throws PortalException {
		if (this.getProject() != null) {
			List<PlacitPlace> places = this.getProject().getPlacitPlaces();
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
		if (Validator.isNull(this._cities)) {
			AssetVocabulary territoriesVocabulary = AssetVocabularyHelper
				.getGlobalVocabulary(VocabularyNames.TERRITORY);
			if (territoriesVocabulary != null) {
				this._cities = territoriesVocabulary.getCategories().stream().filter(c -> {
					try {
						return c.getAncestors().size() == 1;
					} catch (Exception e) {
						return false;
					}
				}).collect(Collectors.toList());
			}
		}
		return this._cities;
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
			Project.class.getName());
	}
		
	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
		this._themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.PROJECT_BO, StrasbourgPortletKeys.PROJECT_BO,
			actionId);
	}

}
