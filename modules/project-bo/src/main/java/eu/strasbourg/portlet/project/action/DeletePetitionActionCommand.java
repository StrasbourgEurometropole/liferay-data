package eu.strasbourg.portlet.project.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.service.project.service.PetitionLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

/**
 * @author alexandre.quere
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_BO,
                "mvc.command.name=deletePetition"
        },
        service = MVCActionCommand.class
)
public class DeletePetitionActionCommand implements MVCActionCommand{

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    private PetitionLocalService _petitionLocalService;
    @Override
    public boolean processAction(ActionRequest request, ActionResponse response) throws PortletException {

        long petitionId = ParamUtil.getLong(request, "petitionId");
        try {
            _petitionLocalService.removePetition(petitionId);
        } catch (PortalException e) {
            _log.error("erreur dans le delete : ",e);
            throw new PortletException(e);
        }
        return false;
    }


    @Reference(unbind = "-")
    protected void setParticipationLocalService(PetitionLocalService participationLocalService) {
        _petitionLocalService = participationLocalService;
    }


}
