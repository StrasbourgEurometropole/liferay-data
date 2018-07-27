package eu.strasbourg.porlet.comment;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.portlet.comment.configuration.CommentConfiguration;
import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.model.Signalement;
import eu.strasbourg.service.comment.service.CommentLocalService;
import eu.strasbourg.service.comment.service.CommentLocalServiceUtil;
import eu.strasbourg.service.comment.service.SignalementLocalServiceUtil;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.service.ParticipationLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyAccessor;
import eu.strasbourg.utils.constants.FriendlyURLs;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author romain.vergnais
 */
@Component(immediate = true, property = {
        "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=false",
        "javax.portlet.display-name=Commentaires",
		"javax.portlet.init-param.add-process-action-success-action=false",
        "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/comments-view.jsp",
		"javax.portlet.name=" + StrasbourgPortletKeys.COMMENT_WEB,
        "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" },
        service = Portlet.class)
public class CommentPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {

		String userPublikId = getPublikID(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        AssetVocabularyAccessor assetVocabularyAccessor = new AssetVocabularyAccessor();

		try {
			// Récupération de la configuration du portlet
			CommentConfiguration configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(CommentConfiguration.class);

			// Récupération du paramètre de tri des commentaires
			String orderBy = configuration.orderBy();
			if (Validator.isNull(orderBy)) {
				orderBy = "asc";
			}

			// Récupération de l'asset entry Id qui est partagé par le portlet détail
			// entité sur la même page.
			long entryID = this.getPortletAssetEntryId(request);
			
			// Si on ne récupère rien --> return (On affiche rien)
			if (entryID == -1)
				return; // Si on ne récupère rien --> return (On affiche rien)

			// Récupération du publik User pour savoir s'il est connecté et s'il a signé
			// le pacte
			// Deux conditions pour pouvoir poster un commentaire
			if (Validator.isNotNull(userPublikId)) {
				request.setAttribute("isUserloggedIn", true);
			} else {
				request.setAttribute("isUserloggedIn", false);
			}

			// Ici on filtre les commentaires qui ne sont pas au status approved car
			// un administrateur peut désapprouver un commentaire
			List<Comment> comments = _commentLocalService.getByAssetEntryAndLevel(entryID, 1,
					WorkflowConstants.STATUS_APPROVED);
			long groupId = themeDisplay.getLayout().getGroupId();
			// Tri des commentaires sur la date de création
			if (orderBy.equals("desc"))
				comments = comments.stream().sorted((c1, c2) -> c2.getCreateDate()
                        .compareTo(c1.getCreateDate()))
                        .collect(Collectors.toList());

			//récupération des catégories
            List<AssetCategory> assetCategories = assetVocabularyAccessor.getCategoriesSignalement(groupId).getCategories();
			// Donne le droit à un administrateur de cacher un commentaire
			boolean isAdmin = themeDisplay.getPermissionChecker().isOmniadmin();
		
			request.setAttribute("categories",assetCategories);
			request.setAttribute("comments", comments);
			request.setAttribute("isAdmin", isAdmin);
			request.setAttribute("entryID", entryID);
			request.setAttribute("userPublikId", userPublikId);
			
			this.initGroupAttributes(request);

			super.render(request, response);
		} catch (PortalException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Action d'ajout/edition d'un commentaire
	 */
	public void postComment(ActionRequest request, ActionResponse response) throws Exception, SystemException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

			String userPublikId = getPublikID(request);

			boolean isValid = validate(request);

			boolean isValidInGroup = validateGroupCondition(request);
			
			// Si l'utilisateur n'est pas connecte, on ne fait rien
			if (Validator.isNotNull(userPublikId) && isValid && isValidInGroup) {

				ServiceContext sc = ServiceContextFactory.getInstance(request);
				
				// Recuperation du potentiel id du commentaire parent
				Long parentCommentId = ParamUtil.getLong(request, "parentCommentId");

				// Recuperation du potentiel id du commentaire a editer
				Long editCommentId = ParamUtil.getLong(request, "editCommentId");


				// Recuperation du message du commentaire
				String message = ParamUtil.getString(request, "message");

				Comment comment;

				if (editCommentId <= 0) { // Creation d'un nouveau commentaire
					comment = _commentLocalService.createComment(sc);

					// Construction de l'URL du commentaire
					String urlTemp = themeDisplay.getURLPortal();
					String urlSuite = themeDisplay.getURLCurrent();
					StringBuilder url = new StringBuilder(urlTemp).append(urlSuite);

					//insertion du lien vers le commentaire
				    url.append("#");
				    url.append(comment.getCommentId());

				// Recuperation de l'ID de l'AssetEntry commente
					long entryID = ParamUtil.getLong(request, "entryID");

					// Edition des attributs
					comment.setAssetEntryId(entryID);
					comment.setUrlProjectCommentaire(url.toString());
					comment.setPublikId(userPublikId);
					comment.setComment(message);

					// Si le message est une reponse
					if (parentCommentId != 0) {
						comment.setParentCommentId(parentCommentId);
						comment.setLevel(2);
					} else {
						comment.setLevel(1);
					}

					_commentLocalService.addComment(comment);

				} else { // Modification d'un commentaire
					comment = _commentLocalService.getComment(editCommentId);

					// Verifie si c'est bien le posteur original
					if (comment.getPublikId().equals(userPublikId)) {
						comment.setComment(message);
						_commentLocalService.updateComment(comment, sc);
					} else {
						SessionErrors.add(request, "unauthorized");
					}

				}

				// Redirection (évite double requête POST si l'utilisateur actualise sa page)
				String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);
				PortletURL renderUrl = PortletURLFactoryUtil.create(request, portletName, themeDisplay.getPlid(),
						PortletRequest.RENDER_PHASE);
				response.sendRedirect(renderUrl.toString());
				
			}
		} catch (Exception e) {
			_log.error(e);
		}
	}

	/**
	 *  Méthode qui permet à l'administrateur de cacher un commentaire
	 */
	public void hideComment(ActionRequest request, ActionResponse response) throws PortalException, SystemException {
		try {
			Comment comment = _commentLocalService.getComment(ParamUtil.getLong(request, "commentId"));
			comment.setStatus(WorkflowConstants.STATUS_DENIED);

			_commentLocalService.updateComment(comment);
		} catch (Exception e) {
			_log.error(e);
		}
	}

    /**
     * Méthode permettant de signaler un commentaire.
	 * @param request request
	 * @param response response
	 * @throws PortalException PortalException
     */
	public void reportComment(ActionRequest request, ActionResponse response) throws PortalException, IOException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long result = ParamUtil.getLong(request, "commentId");
        long categoryId = ParamUtil.getLong(request, "categorie");
        Comment comment = CommentLocalServiceUtil.getComment(result);
        ServiceContext sc = ServiceContextFactory.getInstance(request);
        Signalement signalement = SignalementLocalServiceUtil.createSignalement(sc, comment.getCommentId());
        AssetCategoryLocalServiceUtil.addAssetEntryAssetCategory(signalement.getSignalementId(),categoryId);
        SignalementLocalServiceUtil.updateSignalement(signalement,sc);
        String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);
        PortletURL renderUrl = PortletURLFactoryUtil.create(request, portletName, themeDisplay.getPlid(),
                PortletRequest.RENDER_PHASE);
        response.sendRedirect(renderUrl.toString());
    }

	/**
	 * Méthode de suppression d'un commentaire.
	 * @param request request
	 * @param response response
	 * @throws PortalException PortalException
	 * @throws IOException Exception
	 */
    public void deleteComment(ActionRequest request, ActionResponse response) throws PortalException, IOException {
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long result = ParamUtil.getLong(request,"commentId");
		Comment comment = CommentLocalServiceUtil.removeComment(result);
		_log.info(comment);
        String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);
        PortletURL renderUrl = PortletURLFactoryUtil.create(request, portletName, themeDisplay.getPlid(),
                PortletRequest.RENDER_PHASE);
        response.sendRedirect(renderUrl.toString());
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		try {
			String resourceID = resourceRequest.getResourceID();
			String publikUserID = getPublikID(resourceRequest);
			Comment comment = _commentLocalService.getComment(ParamUtil.getLong(resourceRequest, "commentId"));

		} catch (Exception e) {
			_log.error(e);
		}

		super.serveResource(resourceRequest, resourceResponse);
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
	 * Recupere l'ID de l'assetEntry du detail de la page
	 */
	private long getPortletAssetEntryId(PortletRequest request) {
		PortletSession portletSession = request.getPortletSession();
		
		if (portletSession.getAttribute("LIFERAY_SHARED_assetEntryID", PortletSession.APPLICATION_SCOPE) != null) {
			return (long) portletSession.getAttribute("LIFERAY_SHARED_assetEntryID",
					PortletSession.APPLICATION_SCOPE);
		} else { 
			return -1;
		}
	}
	
	/**
	 * Initialise les attributs du contexte selon le site
	 * @throws PortalException 
	 */
	private void initGroupAttributes(RenderRequest request) throws PortalException {
		
		// Recuperation de la friendly URL du site
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long companyId = themeDisplay.getCompanyId();
		long groupId = new Long(themeDisplay.getLayout().getGroupId());
		
		// Recuperation des informations de l'utilisateur
		String publikUserId =  this.getPublikID(request);
		PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(publikUserId);
		
		// Recuperation des informations de l'assetEntry
		long entryId = this.getPortletAssetEntryId(request);
		AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(entryId);
		String assetType = assetEntry.getClassName();
		
		// Par defaut l'asset est "commentable" 
		request.setAttribute("isAssetCommentable", true);
		
		// GroupId des sites utilisant les commentaires
		long placitGroupId =  GroupLocalServiceUtil.getFriendlyURLGroup(companyId, FriendlyURLs.PLACIT_URL).getGroupId();

		if (groupId == placitGroupId) {/// RESTRICTIONS : PLATEFORME-CITOYENNE
			
			// Initialise l'attribut de signature du pacte
			if (Validator.isNotNull(publikUserId)) {
				request.setAttribute("hasUserSigned", Validator.isNotNull(user.getPactSignature()) ? true : false);
				request.setAttribute("isUserBanned", user.isBanned());
			} else {
				request.setAttribute("hasUserSigned", false);
				request.setAttribute("isUserBanned", false);
			}
			
			// Verification d'une participation ou l'on peut reagir
			if (assetType.equals("eu.strasbourg.service.project.model.Participation")) {
				Participation participation = ParticipationLocalServiceUtil.getParticipation(assetEntry.getClassPK());
				
				if (participation == null || !participation.isJudgeable()) {
					request.setAttribute("isAssetCommentable", false);
				}
			}
		}
	}
	
	/**
	 * Validation des champs obligatoires
	 */
	private boolean validate(ActionRequest request) {
		boolean isValid = true;

		if (Validator.isNull(ParamUtil.getString(request, "message"))) {
			SessionErrors.add(request, "message");
			isValid = false;
		}

		return isValid;
	}
	
	/**
	 * Validation des conditions selon le site
	 * (ex: les participations sur placit ne peuvent etre commenteesbque si 
	 * l'utilisateur signe le pacte et que l'entite est en status 'en cours')
	 * @throws PortalException 
	 */
	private boolean validateGroupCondition(ActionRequest request) throws PortalException {
		
		// Recuperation de la friendly URL du site
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long companyId = themeDisplay.getCompanyId();
		long groupId = new Long(themeDisplay.getLayout().getGroupId());
		
		// Recuperation des informations de l'asset
		long entryId = ParamUtil.getLong(request, "entryID");
		AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(entryId);
		String assetType = assetEntry.getClassName();
		
		//Recuperation des informations de l'utilisateur
		String publikUserId =  this.getPublikID(request);
		PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(publikUserId);
		
		// groupId des sites utilisant les commentaires
		long placitGroupId =  GroupLocalServiceUtil.getFriendlyURLGroup(companyId, FriendlyURLs.PLACIT_URL).getGroupId();
		
		if (groupId == placitGroupId) {/// RESTRICTIONS : PLATEFORME-CITOYENNE

			// Verification de la signature du pacte et de la sainteté de l'utilisateur 
			if (user.getPactSignature() == null || user.isBanned()) {
				return false;
			}
			
			// Verification d'une participation ou l'on peut reagir
			if (assetType.equals("eu.strasbourg.service.project.model.Participation")) {
				Participation participation = ParticipationLocalServiceUtil.getParticipation(assetEntry.getClassPK());
				
				if (participation == null || !participation.isJudgeable()) {
					return false;
				}
			}
		
		}
		
		return true;
	}
	
	@Reference(unbind = "-")
	protected void setCommentLocalService(CommentLocalService commentLocalService) {
		_commentLocalService = commentLocalService;
	}

	private CommentLocalService _commentLocalService;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}