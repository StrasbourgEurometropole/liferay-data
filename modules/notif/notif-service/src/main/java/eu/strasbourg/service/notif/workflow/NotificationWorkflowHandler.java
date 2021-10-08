package eu.strasbourg.service.notif.workflow;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ResourceActions;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.BaseWorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandler;
import eu.strasbourg.service.notif.model.Notification;
import eu.strasbourg.service.notif.service.NotificationLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

@Component(
	property = { "model.class.name=eu.strasbourg.service.notif.model.Notification" },
	immediate = true,
	service = WorkflowHandler.class)
public class NotificationWorkflowHandler extends BaseWorkflowHandler<Notification> {

	@Override
	public String getClassName() {
		return Notification.class.getName();
	}

	@Override
	public String getType(Locale locale) {
		return _resourceActions.getModelResource(locale, getClassName());
	}

	@Override
	public Notification updateStatus(int status,
		Map<String, Serializable> workflowContext) throws PortalException {

		long userId = GetterUtil.getLong(
			(String) workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));
		long resourcePrimKey = GetterUtil.getLong(
				(String)workflowContext.get(
						WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
		ServiceContext serviceContext = (ServiceContext) workflowContext
			.get("serviceContext");

		return _notificationLocalService.updateStatus(userId, resourcePrimKey, status,
			serviceContext, workflowContext);
	}

	@Reference(unbind = "-")
	protected void setOfferLocalService(NotificationLocalService notificationLocalService) {

		_notificationLocalService = notificationLocalService;
	}

	private NotificationLocalService _notificationLocalService;

	@Reference(unbind = "-")
	protected void setResourceActions(ResourceActions resourceActions) {

		_resourceActions = resourceActions;
	}

	private ResourceActions _resourceActions;
}
