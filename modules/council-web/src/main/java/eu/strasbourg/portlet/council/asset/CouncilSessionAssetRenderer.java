package eu.strasbourg.portlet.council.asset;

import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;


public class CouncilSessionAssetRenderer extends BaseJSPAssetRenderer<CouncilSession> {
	
	public static final String TYPE = "councilSession";
	private CouncilSession entry;
	
	public CouncilSessionAssetRenderer(CouncilSession entry){
		this.entry = entry;
	}
	
	@Override
	public CouncilSession getAssetObject() {
		return this.entry;
	}

	@Override
	public long getGroupId() {
		return this.entry.getGroupId();
	}

	@Override
	public long getUserId() {
		return this.entry.getUserId();
	}

	@Override
	public String getUserName() {
		return this.entry.getUserName();
	}

	@Override
	public String getUuid() {
		return this.entry.getUuid();
	}

	@Override
	public String getClassName() {
		return CouncilSession.class.getName();
	}

	@Override
	public long getClassPK() {
		return this.entry.getCouncilSessionId();
	}

	
	@Override
	public String getJspPath(HttpServletRequest request, String template) {
		return null;
	}
	
	@Override
	public boolean include(HttpServletRequest request, HttpServletResponse response, String template)
			throws Exception {
		request.setAttribute("entry", this.entry);
		request.setAttribute("detailPortletName", StrasbourgPortletKeys.ENTITY_DETAIL_WEB);
		
		return super.include(request, response, template);
	}

	public CouncilSession getCouncilSession() {
		return this.entry;
	}

	@Override
	public String getSummary(PortletRequest portletRequest, PortletResponse portletResponse) {
		return "Name: " + entry.getTitle();
	}

	@Override
	public String getTitle(Locale locale) {
		return this.entry.getTitle();
	}

}