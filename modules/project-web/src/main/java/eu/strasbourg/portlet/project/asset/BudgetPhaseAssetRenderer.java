package eu.strasbourg.portlet.project.asset;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;

import eu.strasbourg.service.project.model.BudgetPhase;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

public class BudgetPhaseAssetRenderer extends BaseJSPAssetRenderer<BudgetPhase> {
	
	public static final String TYPE = "budgetPhase";
    private BudgetPhase _entry;

    public BudgetPhaseAssetRenderer(BudgetPhase _entry) {
        this._entry = _entry;
    }

    @Override
    public BudgetPhase getAssetObject() {
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
        return BudgetPhase.class.getName();
    }

    @Override
    public long getClassPK() {
        return _entry.getBudgetPhaseId();
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
    
    public BudgetPhase getBudgetPhase(){
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
