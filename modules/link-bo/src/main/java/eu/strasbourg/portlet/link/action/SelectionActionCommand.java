package eu.strasbourg.portlet.link.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.link.model.Link;
import eu.strasbourg.service.link.service.LinkLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.LINK_BO,
		"mvc.command.name=selectionAction" },
	service = MVCActionCommand.class)
public class SelectionActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest actionRequest,
		ActionResponse actionResponse) throws PortletException {
		String tab = ParamUtil.getString(actionRequest, "tab");

		ServiceContext sc;
		try {
			sc = ServiceContextFactory.getInstance(actionRequest);

			long[] selectionIds = StringUtil
				.split(ParamUtil.getString(actionRequest, "selectionIds"), 0L);

			for (long entryId : selectionIds) {
				switch (ParamUtil.getString(actionRequest, "cmd")) {
				case "delete":
					if (tab.equals("links")) {
						_linkLocalService.removeLink(entryId);
					}
					break;
				case "publish":
					if (tab.equals("links")) {
						Link link = _linkLocalService.getLink(entryId);
						sc.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);
						_linkLocalService.updateLink(link, sc);
					}
					break;
				case "unpublish":
					if (tab.equals("links")) {
						Link link = _linkLocalService.getLink(entryId);
						sc.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);
						_linkLocalService.updateLink(link, sc);
					}
					break;
				}
			}
		} catch (PortalException e) {
			e.printStackTrace();
		}
		return false;
	}

	private LinkLocalService _linkLocalService;

	@Reference(unbind = "-")
	protected void setLinkLocalService(LinkLocalService linkLocalService) {

		_linkLocalService = linkLocalService;
	}

}
