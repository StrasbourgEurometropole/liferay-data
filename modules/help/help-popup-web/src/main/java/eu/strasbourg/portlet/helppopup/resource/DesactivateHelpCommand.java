package eu.strasbourg.portlet.helppopup.resource;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.help.model.HelpProposal;
import eu.strasbourg.service.help.service.HelpProposalLocalService;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalService;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.HELP_POPUP_WEB,
                "mvc.command.name=desactivateHelp"
        },
        service = MVCResourceCommand.class
)
public class DesactivateHelpCommand implements MVCResourceCommand {

    // Id de recuperation des champs
    private static final String ENTRY_ID = "entryId";

    // Champs
    private long entryID;
    private HelpProposal helpProposal;

    // Gestion et contexte de la requete
    private String publikID;
    private PublikUser user;
    private String message;

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    @Override
    public boolean serveResource(ResourceRequest request, ResourceResponse response) throws PortletException {

        // Initialisations respectives de : resultat probant de la requete, sauvegarde ou non des informations Publik, message de retour, format de date
        boolean result = false;
        this.message = "";

        // Recuperation de l'utilsiteur Publik ayant lance la demande
        this.publikID = getPublikID(request);

        // Recuperation de l'aide
        this.entryID = ParamUtil.getLong(request, ENTRY_ID);

        // Verification de la validite des informations
        if (validate()) {

            // Envoi de la demande
            result = desactivateHelpProposal(request);
        }

        // Retour des informations de la requete en JSON
        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
        jsonResponse.put("result", result);
        jsonResponse.put("message", this.message);

        // Recuperation de l'élément d'écriture de la réponse
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.print(jsonResponse.toString());
        } catch (IOException e) {
            _log.error("erreur dans l'ecriture du budget : ", e);
        }
        return result;
    }

    private boolean desactivateHelpProposal(ResourceRequest request) throws PortletException {
        ServiceContext sc;

        try {
            sc = ServiceContextFactory.getInstance(request);
            // Mise du statut d'activité en inactive
            long[] ids = this.helpProposal.getCategories().stream()
                    .mapToLong(AssetCategory::getCategoryId).toArray();
            List<Long> idsLong = Arrays.stream(ids).boxed().collect(Collectors.toList());

            AssetCategory active = AssetVocabularyHelper.getCategory("Active", sc.getScopeGroupId());
            if (active != null && idsLong.indexOf(active.getCategoryId()) >= 0)
                idsLong.remove(idsLong.indexOf(active.getCategoryId()));

            AssetCategory inactive = AssetVocabularyHelper.getCategory("Inactive", sc.getScopeGroupId());
            if (inactive != null)
                idsLong.add(inactive.getCategoryId());
            sc.setAssetCategoryIds(idsLong.stream().mapToLong(w -> w).toArray());

            _helpProposalLocalService.updateHelpProposal(helpProposal, sc);

        } catch (PortalException e) {
            _log.error(e);
            throw new PortletException(e);
        }
        _log.info("Proposaition d'aide désactivée : " + this.helpProposal);
        return true;
    }

    /**
     * Validation des champs de la requete (excpet photo)
     * @return Valide ou pas
     */
    private boolean validate() {

        // utilisateur
        if (Validator.isNull(this.entryID)) {
            this.message = "Il manque l'entryId";
            return false;
        }

        // utilisateur
        if (this.publikID == null || this.publikID.isEmpty()) {
            this.message = "Utilisateur non reconnu";
            return false;
        } else {
            this.user = _publikUserLocalService.getByPublikUserId(this.publikID);
        }

        // HelpProposal
        try {
            AssetEntry assetEntry = _assetEntryLocalService.getAssetEntry(this.entryID);

            if (assetEntry == null) {
                this.message = "Erreur lors de la recherche de la proposition d'aide";
                return false;
            }else{
                // On vérifie que l'utilisateur est bien celui qui a créé l'aide
                this.helpProposal = _helpProposalLocalService.getByPublikIdAndHelpProposalId(this.user.getPublikId(), assetEntry.getClassPK());
                if (assetEntry == null) {
                    this.message = "Vous n'êtes pas le créateur de la proposition d'aide";
                    return false;
                }
            }
        } catch (PortalException e1) {
            _log.error(e1);
            this.message = "Erreur lors de la recherche de la proposition d'aide";
            return false;
        }


        return true;
    }

    /**
     * Recuperation du publik ID avec la session
     */
    private String getPublikID(PortletRequest request) {
        LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(request);
        HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
        return SessionParamUtil.getString(originalRequest, "publik_internal_id");
    }

    @Reference(unbind = "-")
    protected void setHelpProposalLocalService(HelpProposalLocalService helpProposalLocalService) {
        _helpProposalLocalService = helpProposalLocalService;
    }

    @Reference(unbind = "-")
    protected void setPublikUserLocalService(PublikUserLocalService publikUserLocalService) {
        _publikUserLocalService = publikUserLocalService;
    }


    @Reference(unbind = "-")
    protected void setAssetEntryLocalService(AssetEntryLocalService assetEntryLocalService) {
        _assetEntryLocalService = assetEntryLocalService;
    }

    private HelpProposalLocalService _helpProposalLocalService;

    private AssetEntryLocalService _assetEntryLocalService;

    private PublikUserLocalService _publikUserLocalService;

}