package eu.strasbourg.portlet.comment.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

@Component(
        immediate = true,
        property = {
        "javax.portlet.name=" + StrasbourgPortletKeys.COMMENT_BO,
        "mvc.command.name=saveComment"
        },
        service = MVCActionCommand.class
        )
public class SaveCommentActionCommand implements MVCActionCommand{

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
        return false;
    }
}
