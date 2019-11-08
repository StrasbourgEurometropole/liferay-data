package eu.strasbourg.portlet.form_send.panel;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

@Component(
        immediate = true,
        property = {
                "panel.app.order:Integer=260",
                "panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT
        },
        service = PanelApp.class
)
public class FormSendBOPanelApp extends BasePanelApp {
    @Override
    public String getPortletId() {
        return StrasbourgPortletKeys.FORM_SEND_BO;
    }
}
