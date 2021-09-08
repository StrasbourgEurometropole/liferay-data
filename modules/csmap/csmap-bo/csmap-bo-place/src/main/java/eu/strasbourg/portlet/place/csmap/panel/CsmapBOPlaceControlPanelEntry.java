package eu.strasbourg.portlet.place.csmap.panel;

import com.liferay.portal.kernel.portlet.BaseControlPanelEntry;
import com.liferay.portal.kernel.portlet.ControlPanelEntry;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.CSMAP_BO_PLACE
	},
	service = ControlPanelEntry.class
)
public class CsmapBOPlaceControlPanelEntry extends BaseControlPanelEntry {

}
