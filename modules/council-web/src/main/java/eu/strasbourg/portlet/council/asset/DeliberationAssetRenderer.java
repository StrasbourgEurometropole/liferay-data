package eu.strasbourg.portlet.council.asset;

import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import eu.strasbourg.service.council.model.Deliberation;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class DeliberationAssetRenderer extends BaseJSPAssetRenderer<Deliberation> {

    public static final String TYPE = "deliberation";
    private Deliberation entry;

    public DeliberationAssetRenderer(Deliberation entry){
        this.entry = entry;
    }

    @Override
    public Deliberation getAssetObject() {
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
        return Deliberation.class.getName();
    }

    @Override
    public long getClassPK() {
        return this.entry.getDeliberationId();
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

    public Deliberation getDeliberation() {
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
