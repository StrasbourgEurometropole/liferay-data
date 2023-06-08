package eu.strasbourg.portlet.place.display.context;

import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.place.model.PublicHoliday;
import eu.strasbourg.service.place.service.PublicHolidayLocalServiceUtil;

public class ViewPublicHolidaysDisplayContext {

	public ViewPublicHolidaysDisplayContext(RenderRequest request,
		RenderResponse response) {

		this._request = request;
		this._response = response;
	}

	public SearchContainer<PublicHoliday> getSearchContainer()
			throws PortalException {

		PortletURL iteratorURL = this._response.createRenderURL();
		iteratorURL.setParameter("tab", "publicHolidays");

		if (this._searchContainer == null) {
			this._searchContainer = new SearchContainer<PublicHoliday>(
					this._request, iteratorURL, null, "no-entries-were-found");

			this._searchContainer.setEmptyResultsMessageCssClass(
					"taglib-empty-result-message-header-has-plus-btn");
			this._searchContainer
					.setRowChecker(new EmptyOnClickRowChecker(this._response));
		}
		return _searchContainer;
	}

	public List<PublicHoliday> getPublicHolidays() throws PortalException {
		if (this._publicHolidays == null) {
			// Total
			this.getSearchContainer().setTotal(
					PublicHolidayLocalServiceUtil.getPublicHolidaiesCount());

			// Récupération de la liste des prix
			List<PublicHoliday> publicHolidays = PublicHolidayLocalServiceUtil
					.getPublicHolidaies(this.getSearchContainer().getStart(),
							this.getSearchContainer().getEnd());

			this._publicHolidays = publicHolidays;
		}
		return this._publicHolidays;
	}

	public String getOrderByCol() {
		return null;
	}

	public String getOrderByType() {
		return null;
	}

	public String getFilterCategoriesIds() throws PortalException {
		return null;
	}

	private final RenderRequest _request;
	private final RenderResponse _response;

	private SearchContainer<PublicHoliday> _searchContainer;
	private List<PublicHoliday> _publicHolidays;

}
