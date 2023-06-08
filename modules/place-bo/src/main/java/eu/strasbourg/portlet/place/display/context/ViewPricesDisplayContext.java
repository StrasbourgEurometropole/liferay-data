package eu.strasbourg.portlet.place.display.context;

import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.place.model.Price;
import eu.strasbourg.service.place.service.PriceLocalServiceUtil;

public class ViewPricesDisplayContext {

	public ViewPricesDisplayContext(RenderRequest request,
		RenderResponse response) {

		this._request = request;
		this._response = response;
	}

	public SearchContainer<Price> getSearchContainer() throws PortalException {

		PortletURL iteratorURL = this._response.createRenderURL();
		iteratorURL.setParameter("tab", "prices");

		if (this._searchContainer == null) {
			this._searchContainer = new SearchContainer<Price>(this._request,
				iteratorURL, null, "no-entries-were-found");

			this._searchContainer.setEmptyResultsMessageCssClass(
				"taglib-empty-result-message-header-has-plus-btn");
			this._searchContainer
				.setRowChecker(new EmptyOnClickRowChecker(this._response));
		}
		return _searchContainer;
	}

	public List<Price> getPrices() throws PortalException {
		if (this._prices == null) {
			// Total
			this.getSearchContainer().setTotal(PriceLocalServiceUtil.getPricesCount());

			// Récupération de la liste des prix			
			List<Price> prices = PriceLocalServiceUtil.getPrices(this.getSearchContainer().getStart(), this.getSearchContainer().getEnd());
			
			this._prices = prices;
		}
		return this._prices;
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

	private SearchContainer<Price> _searchContainer;
	private List<Price> _prices;

}
