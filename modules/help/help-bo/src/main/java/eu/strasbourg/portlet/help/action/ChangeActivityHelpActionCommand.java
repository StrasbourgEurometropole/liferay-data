package eu.strasbourg.portlet.help.action;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.service.help.model.HelpProposal;
import eu.strasbourg.service.help.service.HelpProposalLocalService;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
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
                "mvc.command.name=changeActivityHelpProposal"
        },
        service = MVCActionCommand.class
)
public class ChangeActivityHelpActionCommand implements MVCActionCommand {

    @Override
    public boolean processAction(ActionRequest request, ActionResponse response)
            throws PortletException {

        try {
            long helpProposalId = ParamUtil.getLong(request, "helpProposalId");
            HelpProposal helpProposal = _helpProposalLocalService.getHelpProposal(helpProposalId);
            long helpAssetId = helpProposal.getAssetEntry().getEntryId();
            ServiceContext sc = ServiceContextFactory.getInstance(request);

            // Update category
            List<AssetCategory> categories = helpProposal.getCategories();
            AssetCategory active = AssetVocabularyHelper.getCategory("Active", sc.getScopeGroupId());
            AssetCategory inactive = AssetVocabularyHelper.getCategory("Inactive", sc.getScopeGroupId());
            // Active --> Inactive
            if (active != null && inactive != null && categories.contains(active)) {
                AssetCategoryLocalServiceUtil.deleteAssetEntryAssetCategory(helpAssetId, active);
                AssetCategoryLocalServiceUtil.addAssetEntryAssetCategory(helpAssetId, inactive);
            }
            // Inactive --> Active
            else if (active != null && inactive != null && categories.contains(inactive)) {
                AssetCategoryLocalServiceUtil.deleteAssetEntryAssetCategory(helpAssetId, inactive);
                AssetCategoryLocalServiceUtil.addAssetEntryAssetCategory(helpAssetId, active);
            }
        } catch (PortalException e) {
            _log.error(e);
        }
        return true;
    }

    @Reference(unbind = "-")
    protected void setHelpProposalLocalService(HelpProposalLocalService helpProposalLocalService) {
        _helpProposalLocalService = helpProposalLocalService;
    }

    private HelpProposalLocalService _helpProposalLocalService;

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
