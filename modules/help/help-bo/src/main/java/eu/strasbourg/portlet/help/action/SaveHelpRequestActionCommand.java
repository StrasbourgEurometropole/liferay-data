package eu.strasbourg.portlet.help.action;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.portlet.help.constants.HelpBOConstants;
import eu.strasbourg.service.help.model.HelpRequest;
import eu.strasbourg.service.help.service.HelpRequestLocalService;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.HELP_BO,
                "mvc.command.name=saveHelpRequest"
        },
        service = MVCActionCommand.class
)
public class SaveHelpRequestActionCommand implements MVCActionCommand {

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    @Override
    public boolean processAction(ActionRequest request, ActionResponse response) {

        try {
            // Edition de la demande
            long helpRequestId = ParamUtil.getLong(request, "helpRequestId");
            HelpRequest helpRequest = _helpRequestLocalService.getHelpRequest(helpRequestId);
            Long imageId = ParamUtil.getLong(request, "seeker-proof");
            helpRequest.setStudentCardImageId(imageId);

            // Commentaire
            Map<Locale, String> comment = LocalizationUtil.getLocalizationMap(request, HelpBOConstants.FIELD_COMMENT);
            helpRequest.setCommentMap(comment);

            String newModerationStatus = ParamUtil.getString(request, "newStatus");
            long[] ids = helpRequest.getCategories().stream()
                    .mapToLong(AssetCategory::getCategoryId).toArray();
            List<Long> idsLong = Arrays.stream(ids).boxed().collect(Collectors.toList());
            ServiceContext sc = ServiceContextFactory.getInstance(request);
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

            String redirectURL =  ParamUtil.getString(request, HelpBOConstants.PARAM_REDIRECT_URL);
            response.sendRedirect(redirectURL);

        } catch (PortalException | IOException e) {
            log.error(e);
        }

        return true;
    }


    @Reference(unbind = "-")
    protected void setHelpRequestLocalService(HelpRequestLocalService helpRequestLocalService) {
        _helpRequestLocalService = helpRequestLocalService;
    }

    private HelpRequestLocalService _helpRequestLocalService;

}