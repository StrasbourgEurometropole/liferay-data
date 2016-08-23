package eu.strasbourg.portlet.artwork.panel;

import org.osgi.service.component.annotations.Component;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
    immediate = true,
    property = {
    	"panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT,
        "panel.category.order:Integer=1"
    },
    service = PanelApp.class
)
public class ArtworkBOPanelApp extends BasePanelApp {
		
	@Override
	public String getPortletId() {
		// TODO Auto-generated method stub
		return StrasbourgPortletKeys.ARTWORK_BO;
	}

}