package eu.strasbourg.portlet.projectpopup.action;

import static eu.strasbourg.portlet.projectpopup.ProjectPopupPortlet.REDIRECT_URL_PARAM;

import java.io.IOException;
import java.text.DateFormat;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * @author romain.vergnais
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_POPUP_WEB,
                "mvc.command.name=editBudget"
        },
        service = MVCActionCommand.class
)
public class EditBudgetActionCommand implements MVCActionCommand {
	
    private static final String BUDGETTITLE = "title";
    private static final String BUDGETDESCRIPTION = "description";
    private static final String LIEU = "budgetLieux";
    private static final String PROJECT = "project";
    private static final String QUARTIER = "quartier";
    private static final String THEME = "theme";
    private static final String PHOTO = "budgetPhoto";
    private static final String VIDEO = "video";
    private static final String PATTERN = "dd/MM/yyyy";

    private String publikID;
    private PublikUser user;
    private DateFormat dateFormat;
    private String video;
    private String title;
    private String description;
    private String lieu;
    private long projectId;
    private long quartierId;
    private long themeId;
    private String message;
    private long entryId;


	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) throws PortletException {

		boolean result = false;
		
		// Recuperation des identifiants assujetis a la requete
        this.entryId = ParamUtil.getLong(request, "entryId");
        this.publikID = getPublikID(request);
        
		// Recuperation de l'URL de redirection
        String redirectURL = ParamUtil.getString(request, REDIRECT_URL_PARAM);
        
        // Recuperation des informations du budget participatif du formulaire
        this.lieu = HtmlUtil.stripHtml(ParamUtil.getString(request, LIEU));
        this.video = HtmlUtil.stripHtml(ParamUtil.getString(request, VIDEO));
        this.title = HtmlUtil.stripHtml(ParamUtil.getString(request, BUDGETTITLE));
        this.description = HtmlUtil.stripHtml(ParamUtil.getString(request, BUDGETDESCRIPTION));
        this.projectId = ParamUtil.getLong(request, PROJECT);
        this.quartierId = ParamUtil.getLong(request, QUARTIER);
        this.themeId = ParamUtil.getLong(request, THEME);
        
        // Verification de la validite des informations
        if (validate(request)) {
            // Envoi de la demande de modification du budget
            result = editBudget(request);
        }
        
        try {
            response.sendRedirect(redirectURL);
        } catch (IOException e) {
            _log.error("erreur de redirection dans la command action editBudget : ", e);
            throw new PortletException(e);
        }

        
		return result;
	}
	
	private boolean editBudget(ActionRequest request) throws PortletException {
        ServiceContext sc;
        
        try {
        	AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(this.entryId);
        	BudgetParticipatif bp = BudgetParticipatifLocalServiceUtil.getBudgetParticipatif(assetEntry.getClassPK());
            sc = ServiceContextFactory.getInstance(request);
            
//            List<Long> idCategories = new ArrayList<>();
//            if (this.quartierId == 0) {
//                List<AssetCategory> districts = AssetVocabularyHelper.getAllDistrictsFromCity(CITY_NAME);
//                
//                idCategories = districts.stream()
//                        .map(AssetCategoryModel::getCategoryId)
//                        .collect(Collectors.toList());
//            } else {
//                idCategories.add(quartierId);
//            }
//            if (this.projectId != 0) {
//                idCategories.add(projectId);
//            }
//            if (this.themeId != 0) {
//                idCategories.add(themeId);
//            }
//            long[] ids = new long[idCategories.size()];
//            for (int i = 0; i < idCategories.size(); i++) {
//                ids[i] = idCategories.get(i);
//            }
//            sc.setAssetCategoryIds(ids);

            bp.setTitle(this.title);
            bp.setDescription(this.description);
            bp.setVideoUrl(this.video);
            bp.setPlaceTextArea(this.lieu);
            bp = BudgetParticipatifLocalServiceUtil.updateBudgetParticipatif(bp);
            
        } catch (PortalException e) {
            _log.error(e);
            throw new PortletException(e);
        }
        return true;
    }
	
	/**
	 * Validation des champs de la requete (excpet photo)
	 * @return Valide ou pas
	 */
	private boolean validate(PortletRequest request) {
        
        // utilisateur 
        if (this.publikID == null || this.publikID.isEmpty()) {
            this.message = "Utilisateur non reconnu";
            return false;
        } else {
        	this.user = PublikUserLocalServiceUtil.getByPublikUserId(this.publikID);
        	
        	if (this.user.isBanned()) {
        		this.message = "Vous ne pouvez soumettre une initiative";
        		return false;
        	} else if (this.user.getPactSignature() == null) {
        		this.message = "Vous devez signer le Pacte pour soumettre une initiative";
        		return false;
        	}
        }

        // title
        if (Validator.isNull(this.title)) {
        	this.message = "Titre non valide";
            return false;
        }

        // description
        if (Validator.isNull(this.description)) {
        	this.message = "Description non valide";
            return false;
        }


        return true;
    }
	
	/**
     * Récupération du publik ID avec la session
     */
    private String getPublikID(PortletRequest request) {
        LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(request);
        HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
        return SessionParamUtil.getString(originalRequest, "publik_internal_id");
    }
	
	/**
     * le log
     */
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
