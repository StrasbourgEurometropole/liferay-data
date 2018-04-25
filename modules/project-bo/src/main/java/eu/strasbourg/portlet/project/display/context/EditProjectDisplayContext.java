package eu.strasbourg.portlet.project.display.context;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.model.ProjectTimeline;
import eu.strasbourg.service.project.service.ProjectLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

public class EditProjectDisplayContext {
	
	private Project _project;
	
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
		
	public String getProjectTimelinesIndexes() {
		if (this.getProject() != null) {
    		List<ProjectTimeline> projectTimelines = this.getProject().getProjectTimelines();
    		String indexes = "0";
    		for (int i = 1; i <= projectTimelines.size(); i++) {
    			indexes +=  "," + i;
    		}
    		return indexes;
		}
		return "";
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
		this._themeDisplay.getCompanyGroupId(),
			StrasbourgPortletKeys.PROJECT_BO, StrasbourgPortletKeys.PROJECT_BO,
			actionId);
	}

}
