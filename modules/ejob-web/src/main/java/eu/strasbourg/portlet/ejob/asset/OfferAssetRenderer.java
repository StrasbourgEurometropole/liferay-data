package eu.strasbourg.portlet.ejob.asset;

import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import eu.strasbourg.service.ejob.model.Offer;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class OfferAssetRenderer extends BaseJSPAssetRenderer<Offer> {
	
	public static final String TYPE = "offer";
	private Offer _entry;
	
	public OfferAssetRenderer(Offer entry) {
		_entry = entry;
	}
	
	@Override
	public Offer getAssetObject() {
		return _entry;
	}

	@Override
	public long getGroupId() {
		return _entry.getGroupId();
	}

	@Override
	public long getUserId() {
		return _entry.getUserId();
	}

	@Override
	public String getUserName() {
		return _entry.getUserName();
	}

	@Override
	public String getUuid() {
		return _entry.getUuid();
	}

	@Override
	public String getClassName() {
		return Offer.class.getName();
	}

	@Override
	public long getClassPK() {
		return _entry.getOfferId();
	}

	@Override
	public String getJspPath(HttpServletRequest request, String template) {
		if (template.equals(TEMPLATE_ABSTRACT) ||
				template.equals(TEMPLATE_FULL_CONTENT)) {

			return "/offer/asset/" + template + ".jsp";
		}
		else {
			return null;
		}
	}

	@Override
	public boolean include(HttpServletRequest request,
						   HttpServletResponse response, String template) throws Exception {

		request.setAttribute("entry", this._entry);
		request.setAttribute("detailPortletName", StrasbourgPortletKeys.ENTITY_DETAIL_WEB);

		return super.include(request, response, template);
	}

	public Offer getOffer() {
		return this._entry;
	}

	@Override
	public String getSummary(PortletRequest portletRequest,
		PortletResponse portletResponse) {
		return "Name: " + _entry.getPost(portletRequest.getLocale());
	}

	@Override
	public String getTitle(Locale locale) {
		return _entry.getPost(locale);
	}
	
}
