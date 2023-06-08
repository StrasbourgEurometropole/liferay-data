package eu.strasbourg.portlet.place.display.context;

import java.util.Locale;
import java.util.Set;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import eu.strasbourg.service.place.model.PublicHoliday;
import eu.strasbourg.service.place.service.PublicHolidayLocalServiceUtil;

public class EditPublicHolidayDisplayContext {
	public EditPublicHolidayDisplayContext(RenderRequest request,
			RenderResponse response) {
		this._request = request;
	}

	public PublicHoliday getpublicHoliday() throws PortalException {
		long publicHolidayId = ParamUtil.getLong(_request, "publicHolidayId");
		if (_publicHoliday == null && publicHolidayId > 0) {
			_publicHoliday = PublicHolidayLocalServiceUtil.getPublicHoliday(publicHolidayId);
		}
		return _publicHoliday;
	}

	public Locale[] getAvailableLocales() {
		Set<Locale> availableLocalesSet = LanguageUtil.getSupportedLocales();
		Locale[] availableLocales = availableLocalesSet
				.toArray(new Locale[availableLocalesSet.size()]);
		return availableLocales;
	}

	private PublicHoliday _publicHoliday;

	private final RenderRequest _request;

}
