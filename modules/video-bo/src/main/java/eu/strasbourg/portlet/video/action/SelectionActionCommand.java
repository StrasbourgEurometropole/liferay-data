package eu.strasbourg.portlet.video.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.video.model.Video;
import eu.strasbourg.service.video.model.VideoGallery;
import eu.strasbourg.service.video.service.VideoGalleryLocalService;
import eu.strasbourg.service.video.service.VideoLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.VIDEO_BO,
		"mvc.command.name=selectionAction" },
	service = MVCActionCommand.class)
public class SelectionActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest actionRequest,
		ActionResponse actionResponse) throws PortletException {
		String tab = ParamUtil.getString(actionRequest, "tab");

		long[] selectionIds = StringUtil
			.split(ParamUtil.getString(actionRequest, "selectionIds"), 0L);

		for (long entryId : selectionIds) {
			try {
				switch (ParamUtil.getString(actionRequest, "cmd")) {
				case "delete":
					if (tab.equals("videos")) {
						_videoLocalService.removeVideo(entryId);
					} else if (tab.equals("galleries")) {
						_galleryLocalService.removeGallery(entryId);
					}
					break;
				case "publish":
					if (tab.equals("videos")) {
						Video video = _videoLocalService.getVideo(entryId);
						_videoLocalService.updateStatus(video, WorkflowConstants.STATUS_APPROVED);
					} else if (tab.equals("galleries")) {
						VideoGallery gallery = _galleryLocalService.getVideoGallery(entryId);
						_galleryLocalService.updateStatus(gallery, WorkflowConstants.STATUS_APPROVED);
					}
					break;
				case "unpublish":
					if (tab.equals("videos")) {
						Video video = _videoLocalService.getVideo(entryId);
						_videoLocalService.updateStatus(video, WorkflowConstants.STATUS_DRAFT);
					} else if (tab.equals("galleries")) {
						VideoGallery gallery = _galleryLocalService.getVideoGallery(entryId);
						_galleryLocalService.updateStatus(gallery, WorkflowConstants.STATUS_DRAFT);
					}
					break;
				}
			} catch (PortalException ex) {
				ex.printStackTrace();
			}
		}
		return false;
	}


	private VideoLocalService _videoLocalService;
	private VideoGalleryLocalService _galleryLocalService;

	@Reference(unbind = "-")
	protected void setVideoLocalService(
		VideoLocalService videoLocalService) {

		_videoLocalService = videoLocalService;
	}

	@Reference(unbind = "-")
	protected void setGalleryLocalService(
		VideoGalleryLocalService galleryLocalService) {

		_galleryLocalService = galleryLocalService;
	}

}
