package eu.strasbourg.portlet.council.asset;

import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class OfficialAssetRenderer extends BaseJSPAssetRenderer<Official> {

    public static final String TYPE = "official";
    private Official entry;

    public OfficialAssetRenderer(Official entry){
        this.entry = entry;
    }

    @Override
    public Official getAssetObject() {
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
        return Official.class.getName();
    }

    @Override
    public long getClassPK() {
        return this.entry.getOfficialId();
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

    public Official getOfficial() {
        return this.entry;
    }

    @Override
    public String getSummary(PortletRequest portletRequest, PortletResponse portletResponse) {
        return "Name: " + entry.getFullName();
    }

    @Override
    public String getTitle(Locale locale) {
        return this.entry.getFullName();
    }

}
