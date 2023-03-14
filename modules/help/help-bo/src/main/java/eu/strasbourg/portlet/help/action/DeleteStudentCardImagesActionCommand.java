package eu.strasbourg.portlet.help.action;

import com.liferay.document.library.kernel.exception.NoSuchFileEntryException;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.service.help.model.HelpRequest;
import eu.strasbourg.service.help.service.HelpRequestLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import java.util.HashSet;
import java.util.List;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.HELP_BO,
                "mvc.command.name=deleteStudentCardImages"
        },
        service = MVCActionCommand.class
)
public class DeleteStudentCardImagesActionCommand implements MVCActionCommand {

    @Override
    public boolean processAction(ActionRequest request, ActionResponse response)
            throws PortletException {

        String studentPublikId = ParamUtil.getString(request, "studentPublikId");
        List<HelpRequest> studentRequests = _helpRequestLocalService.getByPublikId(studentPublikId);

        try {
            HashSet<Long> imageEntryIds = new HashSet<>();
            for (HelpRequest helpRequest : studentRequests) {
                long studentCardImageId = helpRequest.getStudentCardImageId();
                if (studentCardImageId > 0 && !imageEntryIds.contains(studentCardImageId)) {
                    DLFileEntry imageEntry = null;
                     try {
                         imageEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(studentCardImageId);
                         DLFileEntryLocalServiceUtil.deleteDLFileEntry(imageEntry);
                     } catch (NoSuchFileEntryException e) { }
                    helpRequest.setStudentCardImageId(0);
                    imageEntryIds.add(studentCardImageId);
                    _helpRequestLocalService.updateHelpRequest(helpRequest);
                }
                else if (studentCardImageId > 0) {
                    helpRequest.setStudentCardImageId(0);
                    _helpRequestLocalService.updateHelpRequest(helpRequest);
                }
            }

        } catch (PortalException e) {
            _log.error(e.getMessage() + " : " + studentRequests.toString());
        }

        return true;
    }

    @Reference(unbind = "-")
    protected void setHelpRequestLocalService(HelpRequestLocalService helpRequestLocalService) {
        _helpRequestLocalService = helpRequestLocalService;
    }

    private HelpRequestLocalService _helpRequestLocalService;

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
