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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.council.constants.StageDeliberation;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Deliberation;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.model.Procuration;
import eu.strasbourg.service.council.service.CouncilSessionLocalService;
import eu.strasbourg.service.council.service.DeliberationLocalService;
import eu.strasbourg.service.council.service.OfficialLocalService;
import eu.strasbourg.service.council.service.OfficialLocalServiceUtil;
import eu.strasbourg.service.council.service.OfficialTypeCouncilLocalServiceUtil;
import eu.strasbourg.service.council.service.ProcurationLocalService;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component(
        immediate = true,
        property = { "javax.portlet.name=" + StrasbourgPortletKeys.COUNCIL_BO,
                "mvc.command.name=openDeliberation" },
        service = MVCActionCommand.class)
public class OpenDeliberationActionCommand extends BaseMVCActionCommand {

    private DeliberationLocalService deliberationLocalService;
    private ProcurationLocalService procurationLocalService;
    private OfficialLocalService officialLocalService;
    private CouncilSessionLocalService councilSessionLocalService;

    @Reference(unbind = "-")
    protected void setDeliberationLocalService(
            DeliberationLocalService deliberationLocalService) {
        this.deliberationLocalService = deliberationLocalService;
    }

    @Reference(unbind = "-")
    protected void setProcurationLocalService(
            ProcurationLocalService procurationLocalService) {
        this.procurationLocalService = procurationLocalService;
    }

    @Reference(unbind = "-")
    protected void setOfficialLocalService(
            OfficialLocalService officialLocalService) {
        this.officialLocalService = officialLocalService;
    }

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

        String stage = ParamUtil.getString(request, "stage");
        long deliberationId = ParamUtil.getLong(request, "deliberationId");
        Deliberation deliberation = deliberationLocalService.getDeliberation(deliberationId);
        CouncilSession councilSession = councilSessionLocalService.fetchCouncilSession(deliberation.getCouncilSessionId());

        List<Procuration> procurations = procurationLocalService.findByCouncilSessionId(deliberation.getCouncilSessionId());

        // Partie procuration
        // Récupération des procurations du conseil de la deliberation
        if(stage.equals(StageDeliberation.VOTE_OUVERT.getName())) {
            for (Procuration procuration : procurations) {
                if (procuration.getIsAfterVote()) {
                    procuration.setStartDelib(deliberation.getDeliberationId());
                    procuration.setIsAfterVote(false);
                    procurationLocalService.updateProcuration(procuration);
                }
            }
        }

        long typeId = councilSession.getTypeId();

        List<Long> officialsType = OfficialTypeCouncilLocalServiceUtil.findByTypeId(typeId).stream()
                .filter(o -> o.getGroupId() == sc.getScopeGroupId())
                .map(o -> o.getOfficialId()).collect(Collectors.toList());

        List<Official> officials = new ArrayList<>();

        for (Long officialId : officialsType) {
            Official official = OfficialLocalServiceUtil.fetchOfficial(officialId);
            if (official != null && official.isIsActive()) {
                officials.add(official);
            }
        }

        int countOfficialActive = officials.size();
        // List des elus qui sont absents avec ou sans procuration
        List<Procuration> absents = new ArrayList<>(procurations.stream().filter(x -> Validator.isNull(x.getEndHour())).collect(Collectors.toList()));
        // List d'id des elus qui sont absents avec ou sans procuration
        List<Long> absentsIds = new ArrayList<>();
        // Verifie qu'il n'y pas plusieurs fois le meme elu
        for(Procuration absent : absents){
            if(!absentsIds.contains(absent.getOfficialUnavailableId())){
                absentsIds.add(absent.getOfficialUnavailableId());
            }
        }
        int countAbsent = absentsIds.size();
        int countOfficialVoting = countOfficialActive - countAbsent;

        deliberation.setCountOfficialsActive(countOfficialActive);
        deliberation.setCountOfficialsVoting(countOfficialVoting);

        //  Set de la date de début de vote
        deliberation.setBeginningVoteDate(new Date());

        //  Calcule la valeur du quorum
        int quorum = (int)Math.floor(((double) countOfficialActive / 2) + 1);
        deliberation.setQuorum(quorum);

        //  Vérifie la présence du quorum
        if(countOfficialVoting >= quorum) {
            //  Passe au statut "Vote ouvert"
            deliberation.setStage(stage);
            deliberation.setStatusDate(new Date());

            AssetCategory stageCategory = AssetVocabularyHelper.getCategory(deliberation.getStage(), themeDisplay.getScopeGroupId());
            // Récupère les anciennes catégories liées au statut pour les effacer (on veut qu'un seul abonnement à une catégorie de statut, celui en cours)
            List<AssetCategory> existingStageCategories = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(deliberation.getAssetEntry(), "Statut");
            for (AssetCategory existingCat : existingStageCategories) {
                AssetEntryLocalServiceUtil.deleteAssetCategoryAssetEntry(existingCat.getCategoryId(), deliberation.getAssetEntry().getEntryId());
            }
            if(stageCategory != null)
                AssetEntryLocalServiceUtil.addAssetCategoryAssetEntry(stageCategory.getCategoryId(), deliberation.getAssetEntry().getEntryId());

        } else {
            //  Pas de quorum, on avertit
            String[] args = new String[]{String.valueOf(countOfficialVoting), String.valueOf(countOfficialActive), String.valueOf(quorum)};
            SessionErrors.add(request, "quorum-error", args);
        }

        //  Update de l'entité (même si pas de quorum on enregistre les chiffres pour que les gens puissent vérifier
        deliberationLocalService.updateDeliberation(deliberation);

        //  Post / Redirect / Get si tout est bon
        PortletURL renderURL = PortletURLFactoryUtil.create(request,
                portletName, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
        renderURL.setParameter("tab", request.getParameter("tab"));
        response.sendRedirect(renderURL.toString());

    }
}
