package eu.strasbourg.portlet.comment.panel;

import com.liferay.portal.kernel.portlet.BaseControlPanelEntry;
import com.liferay.portal.kernel.portlet.ControlPanelEntry;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.COMMENT_BO
        },
        service = ControlPanelEntry.class

)
public class CommentBOControlPanelEntry extends BaseControlPanelEntry {
}
