package eu.strasbourg.portlet.activity.asset;

import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import eu.strasbourg.service.activity.model.AssociationActivity;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class AssociationActivityAssetRenderer extends BaseJSPAssetRenderer<AssociationActivity> {

	public static final String TYPE = "associationActivity";
	private AssociationActivity _entry;

	public AssociationActivityAssetRenderer(AssociationActivity entry) {
		_entry = entry;
	}
	
	@Override
	public AssociationActivity getAssetObject() {
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
		return AssociationActivity.class.getName();
	}

	@Override
	public long getClassPK() {return _entry.getAssociationActivityId();}

	@Override
	public String getSummary(PortletRequest portletRequest,
		PortletResponse portletResponse) {
		return "Activité de " + _entry.getAssociation().getNameCurrentValue();
	}

	@Override
	public String getTitle(Locale locale) {
		return "";
	}
	
	@Override
	public String getJspPath(HttpServletRequest request, String template) {
		if (template.equals(TEMPLATE_ABSTRACT) ||
			template.equals(TEMPLATE_FULL_CONTENT)) {

			return "/association-activity/asset/" + template + ".jsp";
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

	public AssociationActivity getAssociationActivity() {
		return this._entry;
	}

}
