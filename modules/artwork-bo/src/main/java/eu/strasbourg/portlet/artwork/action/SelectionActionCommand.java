package eu.strasbourg.portlet.artwork.action;

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

import eu.strasbourg.service.artwork.model.Artwork;
import eu.strasbourg.service.artwork.model.ArtworkCollection;
import eu.strasbourg.service.artwork.service.ArtworkCollectionLocalService;
import eu.strasbourg.service.artwork.service.ArtworkLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.ARTWORK_BO,
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
					if (tab.equals("artworks")) {
						_artworkLocalService.removeArtwork(entryId);
					} else if (tab.equals("collections")) {
						_artworkCollectionLocalService
							.removeArtworkCollection(entryId);
					}
					break;
				case "publish":
					if (tab.equals("artworks")) {
						Artwork artwork = _artworkLocalService
							.getArtwork(entryId);
						_artworkLocalService.updateStatus(artwork, WorkflowConstants.STATUS_APPROVED);
					} else if (tab.equals("collections")) {
						ArtworkCollection collection = _artworkCollectionLocalService
							.getArtworkCollection(entryId);
						_artworkCollectionLocalService.updateStatus(collection, WorkflowConstants.STATUS_APPROVED);
					}
					break;
				case "unpublish":
					if (tab.equals("artworks")) {
						Artwork artwork = _artworkLocalService
							.getArtwork(entryId);
						_artworkLocalService.updateStatus(artwork, WorkflowConstants.STATUS_DRAFT);
					} else if (tab.equals("collections")) {
						ArtworkCollection collection = _artworkCollectionLocalService
							.getArtworkCollection(entryId);
						_artworkCollectionLocalService.updateStatus(collection, WorkflowConstants.STATUS_DRAFT);
					}
					break;
				}
			} catch (PortalException ex) {
				ex.printStackTrace();
			}
		}
		return false;
	}

	private ArtworkLocalService _artworkLocalService;
	private ArtworkCollectionLocalService _artworkCollectionLocalService;

	@Reference(unbind = "-")
	protected void setArtworkLocalService(
		ArtworkLocalService artworkLocalService) {

		_artworkLocalService = artworkLocalService;
	}

	@Reference(unbind = "-")
	protected void setArtworkCollectionLocalService(
		ArtworkCollectionLocalService artworkCollectionLocalService) {

		_artworkCollectionLocalService = artworkCollectionLocalService;
	}

}
