package eu.strasbourg.portlet.form_send.panel;

import com.liferay.portal.kernel.portlet.BaseControlPanelEntry;
import com.liferay.portal.kernel.portlet.ControlPanelEntry;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.FORM_SEND_BO
        },
        service = ControlPanelEntry.class

)
public class FormSendBOControlPanelEntry extends BaseControlPanelEntry {
}
