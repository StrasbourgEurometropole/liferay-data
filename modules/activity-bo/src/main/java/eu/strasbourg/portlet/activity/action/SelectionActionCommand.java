package eu.strasbourg.portlet.activity.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import eu.strasbourg.service.activity.model.*;
import eu.strasbourg.service.activity.service.*;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import java.util.List;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.ACTIVITY_BO,
		"mvc.command.name=selectionAction" },
	service = MVCActionCommand.class)
public class SelectionActionCommand implements MVCActionCommand {

	private ActivityLocalService activityLocalService;

	private ActivityCourseLocalService activityCourseLocalService;

	private ActivityOrganizerLocalService activityOrganizerLocalService;

	private AssociationLocalService associationLocalService;

	private PracticeLocalService practiceLocalService;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

	@Reference(unbind = "-")
	protected void setActivityLocalService(
		ActivityLocalService activityLocalService) {

		this.activityLocalService = activityLocalService;
	}

	@Reference(unbind = "-")
	protected void setActivityCourseLocalService(
		ActivityCourseLocalService activityCourseLocalService) {

		this.activityCourseLocalService = activityCourseLocalService;
	}

	@Reference(unbind = "-")
	protected void setActivityOrganizerLocalService(
			ActivityOrganizerLocalService activityOrganizerLocalService) {

		this.activityOrganizerLocalService = activityOrganizerLocalService;
	}

	@Reference(unbind = "-")
	protected void setAssociationLocalService(
			AssociationLocalService associationLocalService) {

		this.associationLocalService = associationLocalService;
	}

	@Reference(unbind = "-")
	protected void setPracticeLocalService(
			PracticeLocalService practiceLocalService) {

		this.practiceLocalService = practiceLocalService;
	}

	@Override
	public boolean processAction(ActionRequest actionRequest,
		ActionResponse actionResponse) throws PortletException {
		String tab = ParamUtil.getString(actionRequest, "tab");
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
			.getAttribute(WebKeys.THEME_DISPLAY);

		long[] selectionIds = StringUtil
			.split(ParamUtil.getString(actionRequest, "selectionIds"), 0L);

		for (long entryId : selectionIds) {
			try {
				switch (ParamUtil.getString(actionRequest, "cmd")) {
				case "delete":
					if (tab.equals("activities")) {
						activityLocalService.removeActivity(entryId);
					} else if (tab.equals("activityCourses")) {
						activityCourseLocalService
							.removeActivityCourse(entryId);
					} else if (tab.equals("activityOrganizers")) {
						activityOrganizerLocalService
							.removeActivityOrganizer(entryId);
					}else if (tab.equals("associations")) {
						Association association = associationLocalService
								.getAssociation(entryId);

						// supprime les pratiques de l'association
						List<Practice> practices = association.getPractices();
						for (Practice practice : practices) {
							practiceLocalService.removePractice(
									practice.getPrimaryKey());
						}

						associationLocalService
								.removeAssociation(entryId);
					}
					break;
				case "publish":
					if (tab.equals("activities")) {
						Activity activity = activityLocalService
							.getActivity(entryId);
						activityLocalService.updateStatus(
							themeDisplay.getUserId(), activity.getPrimaryKey(),
							WorkflowConstants.STATUS_APPROVED);
					} else if (tab.equals("activityCourses")) {
						ActivityCourse activityCourse = activityCourseLocalService
							.getActivityCourse(entryId);
						activityCourseLocalService.updateStatus(
							themeDisplay.getUserId(),
							activityCourse.getPrimaryKey(),
							WorkflowConstants.STATUS_APPROVED);
					} else if (tab.equals("activityOrganizers")) {
						ActivityOrganizer activityOrganizer = activityOrganizerLocalService
							.getActivityOrganizer(entryId);
						activityOrganizerLocalService.updateStatus(
							themeDisplay.getUserId(),
							activityOrganizer.getPrimaryKey(),
							WorkflowConstants.STATUS_APPROVED);
					}else if (tab.equals("associations")) {
						Association association = associationLocalService
								.getAssociation(entryId);

						// publi les pratiques de l'association
						List<Practice> practices = association.getPractices();
						for (Practice practice : practices) {
							practiceLocalService.updateStatus(
									themeDisplay.getUserId(),
									practice.getPrimaryKey(),
									WorkflowConstants.STATUS_APPROVED);
						}

						associationLocalService.updateStatus(
								themeDisplay.getUserId(),
								association.getPrimaryKey(),
								WorkflowConstants.STATUS_APPROVED);
					}
					break;
				case "unpublish":
					if (tab.equals("activities")) {
						Activity activity = activityLocalService
							.getActivity(entryId);
						activityLocalService.updateStatus(
							themeDisplay.getUserId(), activity.getPrimaryKey(),
							WorkflowConstants.STATUS_DRAFT);
					} else if (tab.equals("activityCourses")) {
						ActivityCourse activityCourse = activityCourseLocalService
							.getActivityCourse(entryId);
						activityCourseLocalService.updateStatus(
							themeDisplay.getUserId(),
							activityCourse.getPrimaryKey(),
							WorkflowConstants.STATUS_DRAFT);
					} else if (tab.equals("activityOrganizers")) {
						ActivityOrganizer activityOrganizer = activityOrganizerLocalService
							.getActivityOrganizer(entryId);
						activityOrganizerLocalService.updateStatus(
							themeDisplay.getUserId(),
							activityOrganizer.getPrimaryKey(),
							WorkflowConstants.STATUS_DRAFT);
					} else if (tab.equals("associations")) {
						Association association = associationLocalService
								.getAssociation(entryId);

						// dépubli les pratiques de l'association
						List<Practice> practices = association.getPractices();
						for (Practice practice : practices) {
							practiceLocalService.updateStatus(
									themeDisplay.getUserId(),
									practice.getPrimaryKey(),
									WorkflowConstants.STATUS_DRAFT);
						}

						associationLocalService.updateStatus(
								themeDisplay.getUserId(),
								association.getPrimaryKey(),
								WorkflowConstants.STATUS_DRAFT);
					}
					break;
				}
			} catch (PortalException e) {
				_log.error(e);
			}
		}
		return false;
	}

}
