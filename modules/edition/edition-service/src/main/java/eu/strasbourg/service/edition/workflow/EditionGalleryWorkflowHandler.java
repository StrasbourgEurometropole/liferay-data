package eu.strasbourg.service.edition.workflow;

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

import eu.strasbourg.service.edition.model.EditionGallery;
import eu.strasbourg.service.edition.service.EditionGalleryLocalService;

@Component(
	property = { "model.class.name=eu.strasbourg.service.edition.model.EditionGallery" },
	service = WorkflowHandler.class)
public class EditionGalleryWorkflowHandler extends BaseWorkflowHandler<EditionGallery> {

	@Override
	public String getClassName() {
		return EditionGallery.class.getName();
	}

	@Override
	public String getType(Locale locale) {
		return ResourceActionsUtil.getModelResource(locale, getClassName());
	}

	@Override
	public EditionGallery updateStatus(int status,
		Map<String, Serializable> workflowContext) throws PortalException {

		long userId = GetterUtil.getLong(
			(String) workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));
		long classPK = GetterUtil.getLong((String) workflowContext
			.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
		ServiceContext serviceContext = (ServiceContext) workflowContext
			.get("serviceContext");

		return _editionGalleryLocalService.updateStatus(userId, classPK, status,
			serviceContext, workflowContext);
	}

	@Reference(unbind = "-")
	protected void setEditionGalleryLocalService(EditionGalleryLocalService editionGalleryLocalService) {

		_editionGalleryLocalService = editionGalleryLocalService;
	}

	private EditionGalleryLocalService _editionGalleryLocalService;
}
