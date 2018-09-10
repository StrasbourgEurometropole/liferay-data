package eu.strasbourg.portlet.project.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.service.ProjectLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		property = {"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_WEB},
		service = AssetRendererFactory.class
	)
public class ProjectAssetRendererFactory extends BaseAssetRendererFactory<Project> {

	private ProjectLocalService _projectLocalService;

	public static final String TYPE = "project";


	public ProjectAssetRendererFactory() {
		setClassName(Project.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.PROJECT_WEB);
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


	@Reference(unbind = "-")
	protected void setInterestLocalService(ProjectLocalService projectLocalService) {
		_projectLocalService = projectLocalService;
	}
}
