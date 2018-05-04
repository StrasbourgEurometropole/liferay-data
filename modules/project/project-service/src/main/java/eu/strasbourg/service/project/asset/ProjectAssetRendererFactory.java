package eu.strasbourg.service.project.asset;

import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.service.ProjectLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

public class ProjectAssetRendererFactory extends BaseAssetRendererFactory<Project> {
	
	public static final String TYPE = "project";

	public ProjectAssetRendererFactory() {
		setClassName(Project.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.PROJECT_BO);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<Project> getAssetRenderer(long classPK, int type)
		throws PortalException {
		Project entry = _projectLocalService.getProject(classPK);

		ProjectAssetRenderer projectAssetRenderer =
			new ProjectAssetRenderer(entry);

		projectAssetRenderer.setAssetRendererType(type);

		return projectAssetRenderer;

	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	
	private ProjectLocalService _projectLocalService;

	@Reference(unbind = "-")
	protected void setInterestLocalService(ProjectLocalService projectLocalService) {
		_projectLocalService = projectLocalService;
	}
}
