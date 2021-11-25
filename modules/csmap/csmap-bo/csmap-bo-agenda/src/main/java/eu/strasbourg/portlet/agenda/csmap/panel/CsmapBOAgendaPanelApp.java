package eu.strasbourg.portlet.agenda.csmap.panel;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.portal.kernel.model.Portlet;
import eu.strasbourg.panel.category.csmap.constants.CsmapBoPanelCategoryPanelCategoryKeys;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		property = {
				"panel.app.order:Integer=260",
				"panel.category.key=" + CsmapBoPanelCategoryPanelCategoryKeys.CONTROL_PANEL_CATEGORY
		},
		service = PanelApp.class
)
public class CsmapBOAgendaPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return StrasbourgPortletKeys.CSMAP_BO_AGENDA;
	}

}