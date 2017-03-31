package eu.strasbourg.portlet.place.action;

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

import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalService;
import eu.strasbourg.service.place.service.PriceLocalService;
import eu.strasbourg.service.place.service.PublicHolidayLocalService;
import eu.strasbourg.service.place.service.SubPlaceLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(immediate = true, property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.PLACE_BO,
		"mvc.command.name=selectionAction" }, service = MVCActionCommand.class)
public class SelectionActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest actionRequest,
			ActionResponse actionResponse) throws PortletException {
		String tab = ParamUtil.getString(actionRequest, "tab");

		try {
			long[] selectionIds = StringUtil.split(
					ParamUtil.getString(actionRequest, "selectionIds"), 0L);

			for (long entryId : selectionIds) {
				switch (ParamUtil.getString(actionRequest, "cmd")) {
				case "delete":
					if (tab.equals("places")) {
						_placeLocalService.removePlace(entryId);
					} else if (tab.equals("prices")) {
						_priceLocalService.removePrice(entryId);
					} else if (tab.equals("publicHolidays")) {
						_publicHolidayLocalService.deletePublicHoliday(entryId);
					} else if (tab.equals("subPlaces")) {
						_subPlaceLocalService.removeSubPlace(entryId);
					}
					break;
				case "publish":
					if (tab.equals("places")) {
						Place place = _placeLocalService.getPlace(entryId);
						_placeLocalService.updateStatus(place,
								WorkflowConstants.STATUS_APPROVED);
					}
					break;
				case "unpublish":
					if (tab.equals("places")) {
						Place place = _placeLocalService.getPlace(entryId);
						_placeLocalService.updateStatus(place,
								WorkflowConstants.STATUS_DRAFT);
					}
					break;
				}
			}
		} catch (PortalException e) {
			_log.error(e);
		}
		return false;
	}

	private PlaceLocalService _placeLocalService;

	@Reference(unbind = "-")
	protected void setPlaceLocalService(PlaceLocalService placeLocalService) {

		_placeLocalService = placeLocalService;
	}

	private PriceLocalService _priceLocalService;

	@Reference(unbind = "-")
	protected void setPriceLocalService(PriceLocalService priceLocalService) {

		_priceLocalService = priceLocalService;
	}

	private PublicHolidayLocalService _publicHolidayLocalService;

	@Reference(unbind = "-")
	protected void setPublicHolidayLocalService(PublicHolidayLocalService publicHolidayLocalService) {

		_publicHolidayLocalService = publicHolidayLocalService;
	}

	private SubPlaceLocalService _subPlaceLocalService;

	@Reference(unbind = "-")
	protected void setSubPlaceLocalService(SubPlaceLocalService subPlaceLocalService) {

		_subPlaceLocalService = subPlaceLocalService;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
