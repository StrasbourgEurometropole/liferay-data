package eu.strasbourg.portlet.help.action;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.service.help.model.HelpRequest;
import eu.strasbourg.service.help.service.HelpProposalLocalService;
import eu.strasbourg.service.help.service.HelpRequestLocalService;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.HELP_BO,
                "mvc.command.name=saveHelpRequest"
        },
        service = MVCActionCommand.class
)
public class SaveRequestActionCommand implements MVCActionCommand {

    @Override
    public boolean processAction(ActionRequest request, ActionResponse actionResponse) throws PortletException {

        try {
            // Edition de la demande
            long helpRequestId = ParamUtil.getLong(request, "helpRequestId");
            HelpRequest helpRequest = _helpRequestLocalService.getHelpRequest(helpRequestId);
            Long imageId = ParamUtil.getLong(request, "seeker-proof");
            helpRequest.setStudentCardImageId(imageId);

            // Commentaire
            Map<Locale, String> comment = LocalizationUtil.getLocalizationMap(request, "comment");
            helpRequest.setCommentMap(comment);

            //Modif categories
            long helpAssetId = helpRequest.getAssetEntry().getEntryId();
            ServiceContext sc = ServiceContextFactory.getInstance(request);
            Boolean isValid = ParamUtil.getBoolean(request, "valid");
            Boolean isAlert = ParamUtil.getBoolean(request, "alert");
            AssetCategory nonLu = AssetVocabularyHelper.getCategory("Non Lu", sc.getScopeGroupId());
            AssetCategory conforme = AssetVocabularyHelper.getCategory("Conforme", sc.getScopeGroupId());
            AssetCategory alerte = AssetVocabularyHelper.getCategory("Alerte", sc.getScopeGroupId());
            // Active --> Inactive
            if (conforme != null && alerte != null && nonLu != null && isValid) {
                AssetCategoryLocalServiceUtil.deleteAssetEntryAssetCategory(helpAssetId, nonLu);
                AssetCategoryLocalServiceUtil.addAssetEntryAssetCategory(helpAssetId, conforme);
            }
            // Inactive --> Active
            else if (conforme != null && alerte != null && nonLu != null && isAlert) {
                AssetCategoryLocalServiceUtil.deleteAssetEntryAssetCategory(helpAssetId, nonLu);
                AssetCategoryLocalServiceUtil.addAssetEntryAssetCategory(helpAssetId, alerte);
            }

            _helpRequestLocalService.updateHelpRequest(helpRequest);
            return true;

        } catch (PortalException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Reference(unbind = "-")
    protected void setHelpProposalLocalService(HelpProposalLocalService helpProposalLocalService) {
        _helpProposalLocalService = helpProposalLocalService;
    }

    @Reference(unbind = "-")
    protected void setHelpRequestLocalService(HelpRequestLocalService helpRequestLocalService) {
        _helpRequestLocalService = helpRequestLocalService;
    }

    private HelpProposalLocalService _helpProposalLocalService;

    private HelpRequestLocalService _helpRequestLocalService;

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}