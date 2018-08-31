package eu.strasbourg.portlet.project.asset;

import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author alexandre.quere
 */
public class PetitionAssetRenderer extends BaseJSPAssetRenderer<Petition> {

    public static final String TYPE = "petition";
    private Petition _entry;

    public PetitionAssetRenderer(Petition _entry) {
        this._entry = _entry;
    }

    @Override
    public Petition getAssetObject() {
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
        return Petition.class.getName();
    }

    @Override
    public long getClassPK() {
        return _entry.getPetitionId();
    }

    @Override
    public String getJspPath(HttpServletRequest request, String template) {
        return null;
    }

    @Override
    public boolean include(HttpServletRequest request, HttpServletResponse response, String template) throws Exception {
        request.setAttribute("entry",this._entry);
        request.setAttribute("detailPortletName", StrasbourgPortletKeys.ENTITY_DETAIL_WEB);
        return super.include(request, response, template);
    }

    public Petition getPetition(){
        return this._entry;
    }

    @Override
    public String getSummary(PortletRequest portletRequest, PortletResponse portletResponse) {
        return "Name : "+ _entry.getDescription();
    }

    @Override
    public String getTitle(Locale locale) {
        return _entry.getTitle();
    }
}
