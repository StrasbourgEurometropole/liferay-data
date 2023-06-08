package eu.strasbourg.portlet.place.panel;

import org.osgi.service.component.annotations.Component;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
    immediate = true,
    property = {
        "panel.app.order:Integer=260",
    	"panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT
    },
    service = PanelApp.class
)
public class PlaceBOPanelApp extends BasePanelApp {
	@Override
	public String getPortletId() {
		return StrasbourgPortletKeys.PLACE_BO;
	}
}