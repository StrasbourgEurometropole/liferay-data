package eu.strasbourg.service.artwork.workflow;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.BaseWorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandler;

import eu.strasbourg.service.artwork.model.Artwork;
import eu.strasbourg.service.artwork.service.ArtworkLocalService;

@Component(
	property = { "model.class.name=eu.strasbourg.service.artwork.model.Artwork" },
	service = WorkflowHandler.class)
public class ArtworkWorkflowHandler extends BaseWorkflowHandler<Artwork> {

	@Override
	public String getClassName() {
		return Artwork.class.getName();
	}

	@Override
	public String getType(Locale locale) {
		return ResourceActionsUtil.getModelResource(locale, getClassName());
	}

	@Override
	public Artwork updateStatus(int status,
		Map<String, Serializable> workflowContext) throws PortalException {

		long userId = GetterUtil.getLong(
			(String) workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));
		long classPK = GetterUtil.getLong((String) workflowContext
			.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
		ServiceContext serviceContext = (ServiceContext) workflowContext
			.get("serviceContext");

		return _artworkLocalService.updateStatus(userId, classPK, status,
			serviceContext, workflowContext);
	}

	@Reference(unbind = "-")
	protected void setArtworkLocalService(ArtworkLocalService artworkLocalService) {

		_artworkLocalService = artworkLocalService;
	}

	private ArtworkLocalService _artworkLocalService;
}
