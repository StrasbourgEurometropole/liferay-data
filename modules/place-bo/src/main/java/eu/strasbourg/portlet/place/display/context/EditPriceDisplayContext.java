package eu.strasbourg.portlet.place.display.context;

import java.util.Locale;
import java.util.Set;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import eu.strasbourg.service.place.model.Price;
import eu.strasbourg.service.place.service.PriceLocalServiceUtil;

public class EditPriceDisplayContext {
	public EditPriceDisplayContext(RenderRequest request,
			RenderResponse response) {
		this._request = request;
	}

	public Price getPrice() throws PortalException {
		long priceId = ParamUtil.getLong(_request, "priceId");
		if (_price == null && priceId > 0) {
			_price = PriceLocalServiceUtil.getPrice(priceId);
		}
		return _price;
	}

	public Locale[] getAvailableLocales() {
		Set<Locale> availableLocalesSet = LanguageUtil.getSupportedLocales();
		Locale[] availableLocales = availableLocalesSet
				.toArray(new Locale[availableLocalesSet.size()]);
		return availableLocales;
	}

	private Price _price;

	private final RenderRequest _request;

}
