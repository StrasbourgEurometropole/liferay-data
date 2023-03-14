package eu.strasbourg.portlet.help.action;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.portlet.help.constants.HelpBOConstants;
import eu.strasbourg.service.help.model.HelpRequest;
import eu.strasbourg.service.help.service.HelpRequestLocalService;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.HELP_BO,
                "mvc.command.name=changeStatusHelpRequest"
        },
        service = MVCActionCommand.class
)
public class ChangeStatusHelpRequestActionCommand implements MVCActionCommand {

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {


        try {
            long helpRequestId = ParamUtil.getLong(actionRequest, "helpRequestId");
            String newModerationStatus = ParamUtil.getString(actionRequest, "requestModerationStatus");
            HelpRequest helpRequest = _helpRequestLocalService.getHelpRequest(helpRequestId);
            long[] ids = helpRequest.getCategories().stream()
                    .mapToLong(AssetCategory::getCategoryId).toArray();
            List<Long> idsLong = Arrays.stream(ids).boxed().collect(Collectors.toList());
            ServiceContext sc = ServiceContextFactory.getInstance(actionRequest);
            AssetVocabulary requestModerationVocab =
                    AssetVocabularyHelper.getVocabulary(VocabularyNames.HELP_REQUEST_MODERATION_STATUS, sc.getScopeGroupId());
            long newStatusCategoryId = 0;
            long currentCategoryId = 0;
            for (AssetCategory category : requestModerationVocab.getCategories()) {
                if (idsLong.contains(category.getCategoryId())) {
                    currentCategoryId = category.getCategoryId();
                }
                if (category.getName().equalsIgnoreCase(newModerationStatus)) {
                    newStatusCategoryId = category.getCategoryId();
                }
            }
            if (newStatusCategoryId != 0 && currentCategoryId != 0) {
                idsLong.remove(idsLong.indexOf(currentCategoryId));
                idsLong.add(newStatusCategoryId);
            }

            sc.setAssetCategoryIds(idsLong.stream().mapToLong(w -> w).toArray());

            _helpRequestLocalService.updateHelpRequest(helpRequest, sc);
        } catch (PortalException e) {
            _log.error(e.getMessage(), e);
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
