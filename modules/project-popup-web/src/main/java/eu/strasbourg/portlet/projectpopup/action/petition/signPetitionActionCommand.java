package eu.strasbourg.portlet.projectpopup.action.petition;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

/**
 * @author alexandre.quere
 */

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_POPUP_WEB,
                "mvc.command.name=signPetition"
        },
        service = MVCActionCommand.class
)
public class signPetitionActionCommand implements MVCActionCommand {
    /**le log*/
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
        String action = ParamUtil.getString(actionRequest,"cmd");
        if ("signPetition".equals(action)){
            String userName = ParamUtil.getString(actionRequest,"username");
            _log.info(userName);

        }
        return false;
    }

    public void petitionPopup(ActionRequest actionRequest, ActionResponse actionResponse){
        String test = ParamUtil.getString(actionRequest,"username");
        _log.info(test);

    }

    public void filePetition(ActionRequest actionRequest, ActionResponse actionResponse){
        String test = ParamUtil.getString(actionRequest,"username");
        _log.info(test);

    }
}
