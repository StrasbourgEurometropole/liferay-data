package eu.strasbourg.panel.category.csmap.application.list;

import eu.strasbourg.panel.category.csmap.constants.CsmapBoPanelCategoryPanelCategoryKeys;
import eu.strasbourg.panel.category.csmap.constants.CsmapBoPanelCategoryPortletKeys;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author quentin.mayer
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=100",
		"panel.category.key=" + CsmapBoPanelCategoryPanelCategoryKeys.CONTROL_PANEL_CATEGORY
	},
	service = PanelApp.class
)
public class CsmapBoPanelCategoryPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return CsmapBoPanelCategoryPortletKeys.CSMAPBOPANELCATEGORY;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + CsmapBoPanelCategoryPortletKeys.CSMAPBOPANELCATEGORY + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

}