package eu.strasbourg.portlet.edition.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.edition.model.Edition;
import eu.strasbourg.service.edition.model.EditionGallery;
import eu.strasbourg.service.edition.service.EditionGalleryLocalService;
import eu.strasbourg.service.edition.service.EditionLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.EDITION_BO,
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
					if (tab.equals("editions")) {
						_editionLocalService.removeEdition(entryId);
					} else if (tab.equals("galleries")) {
						_galleryLocalService.removeGallery(entryId);
					}
					break;
				case "publish":
					if (tab.equals("editions")) {
						Edition edition = _editionLocalService.getEdition(entryId);
						_editionLocalService.updateStatus(edition, WorkflowConstants.STATUS_APPROVED);
					} else if (tab.equals("galleries")) {
						EditionGallery gallery = _galleryLocalService.getEditionGallery(entryId);
						_galleryLocalService.updateStatus(gallery, WorkflowConstants.STATUS_APPROVED);
					}
					break;
				case "unpublish":
					if (tab.equals("editions")) {
						Edition edition = _editionLocalService.getEdition(entryId);
						_editionLocalService.updateStatus(edition, WorkflowConstants.STATUS_DRAFT);
					} else if (tab.equals("galleries")) {
						EditionGallery gallery = _galleryLocalService.getEditionGallery(entryId);
						_galleryLocalService.updateStatus(gallery, WorkflowConstants.STATUS_DRAFT);
					}
					break;
				}
			} catch (PortalException e) {
				_log.error(e);
			}
		}
		return false;
	}


	private EditionLocalService _editionLocalService;
	private EditionGalleryLocalService _galleryLocalService;

	@Reference(unbind = "-")
	protected void setEditionLocalService(
		EditionLocalService editionLocalService) {

		_editionLocalService = editionLocalService;
	}

	@Reference(unbind = "-")
	protected void setGalleryLocalService(
		EditionGalleryLocalService galleryLocalService) {

		_galleryLocalService = galleryLocalService;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
