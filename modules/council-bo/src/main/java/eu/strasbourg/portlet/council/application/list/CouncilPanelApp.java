package eu.strasbourg.portlet.council.application.list;

import eu.strasbourg.portlet.council.constants.CouncilPanelCategoryKeys;
import eu.strasbourg.portlet.council.constants.CouncilPortletKeys;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author jeremy.zwickert
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=100",
		"panel.category.key=" + CouncilPanelCategoryKeys.CONTROL_PANEL_CATEGORY
	},
	service = PanelApp.class
)
public class CouncilPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return CouncilPortletKeys.COUNCIL;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + CouncilPortletKeys.COUNCIL + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

}