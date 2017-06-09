package eu.strasbourg.portlet.activity.asset;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;

import eu.strasbourg.service.activity.model.ActivityOrganizer;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

public class ActivityOrganizerAssetRenderer extends BaseJSPAssetRenderer<ActivityOrganizer> {
	
	public static final String TYPE = "activityOrganizer";
	private ActivityOrganizer _entry;
	
	public ActivityOrganizerAssetRenderer(ActivityOrganizer entry) {
		_entry = entry;
	}
	
	@Override
	public ActivityOrganizer getAssetObject() {
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
		return ActivityOrganizer.class.getName();
	}

	@Override
	public long getClassPK() {
		return _entry.getPrimaryKey();
	}

	@Override
	public String getSummary(PortletRequest portletRequest,
		PortletResponse portletResponse) {
		return _entry.getName(portletRequest.getLocale());
	}

	@Override
	public String getTitle(Locale locale) {
		return _entry.getName(locale);
	}
	
	@Override
	public String getJspPath(HttpServletRequest request, String template) {
		if (template.equals(TEMPLATE_ABSTRACT) ||
			template.equals(TEMPLATE_FULL_CONTENT)) {

			return "/activity-organizer/asset/" + template + ".jsp";
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

	public ActivityOrganizer getGallery() {
		return this._entry;
	}

}
