package eu.strasbourg.portlet.place.csmap.panel;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.model.Portlet;
import eu.strasbourg.portlet.place.csmap.constants.CsmapBoPlacePortletKeys;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		property = {
				"panel.app.order:Integer=259",
				"panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT
		},
		service = PanelApp.class
)
public class CsmapBOPlacePanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return StrasbourgPortletKeys.CSMAP_BO_PLACE;
	}

	@Override
	@Reference(
			target = "(javax.portlet.name=" + CsmapBoPlacePortletKeys.CSMAPBOPLACE + ")",
			unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}
}