package eu.strasbourg.portlet.project.display.context;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;

import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.service.ProjectLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

public class ViewProjectsDisplayContext extends ViewListBaseDisplayContext<Project> {
	
	private List <Project> _projects;
	
	public ViewProjectsDisplayContext(RenderRequest request, RenderResponse response) {
		super(Project.class, request, response);
	}

	public List<Project> getProjects() throws PortalException {
		if (this._projects == null) {
			Hits hits = getHits(this._themeDisplay.getScopeGroupId());

			// Création de la liste d'objet
			List<Project> results = new ArrayList<Project>();
			if (hits != null) {
				for (Document document : hits.getDocs()) {
					Project project = ProjectLocalServiceUtil.fetchProject(
						GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
					if (project != null) {
						results.add(project);
					}
				}
			}
			this._projects = results;
		}
		return this._projects;
	}
	
	
	/**
	 * Retourne la liste des projets correspondant à la recherche lancée en ignorant la pagination
	 */
	private List<Project> getAllProjects() throws PortalException {
		Hits hits = getAllHits(this._themeDisplay.getCompanyGroupId());

		// Création de la liste d'objet
		List<Project> results = new ArrayList<Project>();
		if (hits != null) {
			for (Document document : hits.getDocs()) {
				Project project = ProjectLocalServiceUtil.fetchProject(GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
				if (project != null) {
					results.add(project);
				}
			}
		}
		return results;
	}
	
	/**
	 * Retourne la liste des PK de tous les projets
	 * @return liste de PK (ex: "1,5,7,8")
	 */
	public String getAllProjectIds() throws PortalException {
		String projectIds = "";
		for (Project project : this.getAllProjects()) {
			if (projectIds.length() > 0) {
				projectIds += ",";
			}
			projectIds += project.getProjectId();
		}
		return projectIds;
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
