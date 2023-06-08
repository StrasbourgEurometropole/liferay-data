package eu.strasbourg.portlet.place.display.context;

import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.place.model.SubPlace;
import eu.strasbourg.service.place.service.SubPlaceLocalServiceUtil;

public class ViewSubPlacesDisplayContext {

	public ViewSubPlacesDisplayContext(RenderRequest request,
			RenderResponse response) {

		this._request = request;
		this._response = response;
	}

	public SearchContainer<SubPlace> getSearchContainer()
			throws PortalException {

		PortletURL iteratorURL = this._response.createRenderURL();
		iteratorURL.setParameter("tab", "subPlaces");

		if (this._searchContainer == null) {
			this._searchContainer = new SearchContainer<SubPlace>(this._request,
					iteratorURL, null, "no-entries-were-found");

			this._searchContainer.setEmptyResultsMessageCssClass(
					"taglib-empty-result-message-header-has-plus-btn");
			this._searchContainer
					.setRowChecker(new EmptyOnClickRowChecker(this._response));
		}
		return _searchContainer;
	}

	public List<SubPlace> getSubPlaces() throws PortalException {
		if (this._subPlaces == null) {
			// Total
			this.getSearchContainer()
					.setTotal(SubPlaceLocalServiceUtil.getSubPlacesCount());

			// Création de la liste d'objet
			List<SubPlace> subPlaces = SubPlaceLocalServiceUtil.getSubPlaces(
					this.getSearchContainer().getStart(),
					this.getSearchContainer().getEnd());
			this._subPlaces = subPlaces;
		}
		return this._subPlaces;
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

	private SearchContainer<SubPlace> _searchContainer;
	private List<SubPlace> _subPlaces;

}
