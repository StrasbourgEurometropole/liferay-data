package eu.strasbourg.portlet.official.asset;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;

import eu.strasbourg.service.official.model.Official;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

public class OfficialAssetRenderer extends BaseJSPAssetRenderer<Official> {
	
	public static final String TYPE = "official";
	private Official _entry;
	
	public OfficialAssetRenderer(Official entry) {
		_entry = entry;
	}
	
	@Override
	public Official getAssetObject() {
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
		return Official.class.getName();
	}

	@Override
	public long getClassPK() {
		return _entry.getOfficialId();
	}

	@Override
	public String getSummary(PortletRequest portletRequest,
		PortletResponse portletResponse) {
		return _entry.getLastName() + " " + _entry.getFirstName();
	}

	@Override
	public String getTitle(Locale locale) {
		return _entry.getLastName() + " " + _entry.getFirstName();
	}
	
	@Override
	public String getJspPath(HttpServletRequest request, String template) {
		if (template.equals(TEMPLATE_ABSTRACT) ||
			template.equals(TEMPLATE_FULL_CONTENT)) {

			return "/official/asset/" + template + ".jsp";
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

	public Official getOfficial() {
		return this._entry;
	}
	
}
