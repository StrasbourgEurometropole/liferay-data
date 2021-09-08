package eu.strasbourg.portlet.place.csmap.panel;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import eu.strasbourg.panel.category.csmap.constants.CsmapBoPanelCategoryPanelCategoryKeys;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
				"panel.app.order:Integer=260",
				"panel.category.key=" + CsmapBoPanelCategoryPanelCategoryKeys.CONTROL_PANEL_CATEGORY
		},
		service = PanelApp.class
)
public class CsmapBOPlacePanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return StrasbourgPortletKeys.CSMAP_BO_PLACE;
	}

}