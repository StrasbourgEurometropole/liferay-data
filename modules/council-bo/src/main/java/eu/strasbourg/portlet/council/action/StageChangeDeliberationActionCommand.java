package eu.strasbourg.portlet.council.action;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.council.constants.StageDeliberation;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Deliberation;
import eu.strasbourg.service.council.model.Procuration;
import eu.strasbourg.service.council.service.CouncilSessionLocalService;
import eu.strasbourg.service.council.service.CouncilSessionLocalServiceUtil;
import eu.strasbourg.service.council.service.DeliberationLocalService;
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
                "mvc.command.name=stageChangeDeliberation" },
        service = MVCActionCommand.class)
public class StageChangeDeliberationActionCommand extends BaseMVCActionCommand {

    private DeliberationLocalService deliberationLocalService;

    @Reference(unbind = "-")
    protected void setDeliberationLocalService(
            DeliberationLocalService deliberationLocalService) {
        this.deliberationLocalService = deliberationLocalService;
    }

    private CouncilSessionLocalService councilSessionLocalService;

    @Reference(unbind = "-")
    protected void setCouncilSessionLocalService(
            CouncilSessionLocalService councilSessionLocalService) {
        this.councilSessionLocalService = councilSessionLocalService;
    }

    @Override
    protected void doProcessAction(ActionRequest request,
                                   ActionResponse response) throws Exception {
        ServiceContext sc = ServiceContextFactory.getInstance(request);
        ThemeDisplay themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);
        String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);

        long deliberationId = ParamUtil.getLong(request, "deliberationId");
        String stage = ParamUtil.getString(request, "stage");

        Deliberation deliberation = deliberationLocalService.getDeliberation(deliberationId);

        // Si on essaie de passer à "Affichage en cours"
        if(stage.equals(StageDeliberation.AFFICHAGE_EN_COURS.getName())) {
            // On vérifie qu'il n'y ait pas d'autres délibs en "Affichae en cours" ou "Vote ouvert" pour la session
            List<Deliberation> delibsCouncilSession = deliberationLocalService.findByCouncilSessionId(deliberation.getCouncilSessionId());
            if(delibsCouncilSession.stream().anyMatch(x-> x.isVoteOuvert() || x.isAffichageEnCours())) {
                SessionErrors.add(request, "council.deliberation-already-open");
                // Post / Redirect / Get si tout est bon
                PortletURL renderURL = PortletURLFactoryUtil.create(request,
                        portletName, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
                renderURL.setParameter("tab", request.getParameter("tab"));
                response.sendRedirect(renderURL.toString());
                return;
            }
        }

        // Partie procuration
        // Récupération des procurations du conseil de la deliberation
        if(stage.equals(StageDeliberation.VOTE_OUVERT.getName())) {
            List<Procuration> procurations = councilSessionLocalService.getCouncilSession(deliberation.getCouncilSessionId()).getProcurations();
            for (Procuration procuration : procurations) {
                if (procuration.getIsAfterVote()) {
                    procuration.setStartDelib(deliberation.getDeliberationId());
                    procuration.setIsAfterVote(false);
                }
            }
        }

        deliberation.setStage(stage);
        deliberation.setStatusDate(new Date());

        AssetCategory stageCategory = AssetVocabularyHelper.getCategory(stage, themeDisplay.getScopeGroupId());
        //Récupère les anciennes catégories liées au statut pour les effacer (on veut qu'un seul abonnement à une catégorie de statut, celui en cours)
        List<AssetCategory> existingStageCategories = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(deliberation.getAssetEntry(), "Statut");
        for (AssetCategory existingCat : existingStageCategories) {
            AssetEntryLocalServiceUtil.deleteAssetCategoryAssetEntry(existingCat.getCategoryId(), deliberation.getAssetEntry().getEntryId());
        }
        if(stageCategory != null)
            AssetEntryLocalServiceUtil.addAssetCategoryAssetEntry(stageCategory.getCategoryId(), deliberation.getAssetEntry().getEntryId());
        // Update de l'entité
        deliberationLocalService.updateDeliberation(deliberation);

        // Post / Redirect / Get si tout est bon
        PortletURL renderURL = PortletURLFactoryUtil.create(request,
                portletName, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
        renderURL.setParameter("tab", request.getParameter("tab"));
        response.sendRedirect(renderURL.toString());
    }

}
