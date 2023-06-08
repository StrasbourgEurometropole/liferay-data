package eu.strasbourg.portlet.council.action;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.council.constants.StageDeliberation;
import eu.strasbourg.service.council.model.Deliberation;
import eu.strasbourg.service.council.service.DeliberationLocalService;
import eu.strasbourg.service.council.service.VoteLocalService;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import java.util.Date;
import java.util.List;

@Component(
        immediate = true,
        property = { "javax.portlet.name=" + StrasbourgPortletKeys.COUNCIL_BO,
                "mvc.command.name=resetDeliberation" },
        service = MVCActionCommand.class)
public class ResetDeliberationActionCommand extends BaseMVCActionCommand {

    private VoteLocalService voteLocalService;
    private DeliberationLocalService deliberationLocalService;

    @Reference(unbind = "-")
    protected void setVoteLocalService(
            VoteLocalService voteLocalService) {
        this.voteLocalService = voteLocalService;
    }

    @Reference(unbind = "-")
    protected void setDeliberationLocalService(
            DeliberationLocalService deliberationLocalService) {
        this.deliberationLocalService = deliberationLocalService;
    }

    @Override
    protected void doProcessAction(ActionRequest request,
                                   ActionResponse response) throws Exception {
        ServiceContext sc = ServiceContextFactory.getInstance(request);
        ThemeDisplay themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);
        String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);

        long deliberationId = ParamUtil.getLong(request, "deliberationId");

        Deliberation deliberation = deliberationLocalService.getDeliberation(deliberationId);

        // Passe au statut Créé
        deliberation.setStage(StageDeliberation.get(1).getName());
        deliberation.setStatusDate(new Date());
        //Vide les données lié au quorum
        deliberation.setCountOfficialsVoting(0);
        deliberation.setCountOfficialsActive(0);
        deliberation.setQuorum(0);

        // Set de la date de début et de fin de vote
        deliberation.setBeginningVoteDate(null);
        deliberation.setEndVoteDate(null);

        AssetCategory stageCategory = AssetVocabularyHelper.getCategory(deliberation.getStage(), themeDisplay.getScopeGroupId());
        //Récupère les anciennes catégories liées au statut pour les effacer (on veut qu'un seul abonnement à une catégorie de statut, celui en cours)
        List<AssetCategory> existingStageCategories = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(deliberation.getAssetEntry(), "Statut");
        for (AssetCategory existingCat : existingStageCategories) {
            AssetEntryLocalServiceUtil.deleteAssetCategoryAssetEntry(existingCat.getCategoryId(), deliberation.getAssetEntry().getEntryId());
        }
        if(stageCategory != null)
            AssetEntryLocalServiceUtil.addAssetCategoryAssetEntry(stageCategory.getCategoryId(), deliberation.getAssetEntry().getEntryId());
        // Update de l'entité
        deliberationLocalService.updateDeliberation(deliberation);

        //Suppression des votes associés
        voteLocalService.removeVotesFromDeliberation(deliberationId);

        // Post / Redirect / Get si tout est bon
        PortletURL renderURL = PortletURLFactoryUtil.create(request,
                portletName, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
        renderURL.setParameter("tab", request.getParameter("tab"));
        response.sendRedirect(renderURL.toString());
    }

}
