package eu.strasbourg.portlet.comment.asset;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;

import eu.strasbourg.service.comment.model.Signalement;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

public class SignalementAssetRenderer extends BaseJSPAssetRenderer<Signalement> {
	
	public static final String TYPE = "signalement";
	private Signalement _entry;
	
	public SignalementAssetRenderer(Signalement entry){	
		_entry = entry;
	}
	
	@Override
	public Signalement getAssetObject() {
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
		return Signalement.class.getName();
	}

	@Override
	public long getClassPK() {
		return _entry.getSignalementId();
	}

	
	@Override
	public String getJspPath(HttpServletRequest request, String template) {
		return null;
	}
	
	@Override
	public boolean include(HttpServletRequest request,
		HttpServletResponse response, String template) throws Exception {

		request.setAttribute("entry", this._entry);
		request.setAttribute("detailPortletName", StrasbourgPortletKeys.COMMENT_WEB);
		
		return super.include(request, response, template);
	}

	public Signalement getSignalement() {
		return this._entry;
	}

	@Override
	public String getSummary(PortletRequest portletRequest, PortletResponse portletResponse) {
		return "Name: " + _entry.getSignalementId();
	}

	@Override
	public String getTitle(Locale locale) {
		return "" + _entry.getSignalementId();
	}

}
