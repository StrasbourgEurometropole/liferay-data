package eu.strasbourg.porlet.comment;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.portlet.comment.configuration.CommentConfiguration;
import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.service.CommentLocalServiceUtil;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * @author romain.vergnais
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=false", "javax.portlet.display-name=Commentaires",
		"javax.portlet.init-param.add-process-action-success-action=false", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/comments-view.jsp",
		"javax.portlet.name=" + StrasbourgPortletKeys.COMMENT_WEB, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
		}, service = Portlet.class)
public class CommentPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		
		String userPublikId = getPublikID(request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			
		try {
			//Récupération de la configuration du portlet
			CommentConfiguration configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(CommentConfiguration.class);

			//récupération du paramètre de tri des commentaires
			String orderBy = configuration.orderBy();
			if (Validator.isNull(orderBy)) {
				orderBy = "asc";
			}
			
			// récupération de l'asset entry Id qui partagé par le portlet détail entité sur la même page.
			long entryID = 0;
			PortletSession portletSession = request.getPortletSession();
			if (portletSession.getAttribute("LIFERAY_SHARED_assetEntryID", PortletSession.APPLICATION_SCOPE) != null)
				entryID = (long) portletSession.getAttribute("LIFERAY_SHARED_assetEntryID",
						PortletSession.APPLICATION_SCOPE);
			else
				return; //Si on ne récupère rien --> return (On affiche rien)
			
			//Récupération du publik User pour savoir s'il est connecté et s'il a signé le pacte
			//Deux conditions pour pouvoir poster un commentaire
			if(Validator.isNotNull(userPublikId)) {
				PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(userPublikId);
				
				request.setAttribute("hasUserSigned", Validator.isNotNull(user.getPactSignature()) ? true : false);
				request.setAttribute("isUserloggedIn", true);
			}
			else {
				request.setAttribute("isUserloggedIn", false);
				request.setAttribute("hasUserSigned", false);
			}
			
			//Ici on filtre les commentaires qui ne sont pas au status approved car 
			//un administrateur peut désapprouver un commentaire
			List<Comment> comments = CommentLocalServiceUtil.getByAssetEntry(entryID,
					WorkflowConstants.STATUS_APPROVED);
			
			//Tri des commentaires sur la date de création
			if(orderBy.equals("desc"))
				comments = comments.stream().sorted((c1, c2) -> {
				return c2.getCreateDate().compareTo(c1.getCreateDate());}).collect(Collectors.toList());
			
			//Donne le droit à une administrateur de cacher un commentaire
			boolean isAdmin = themeDisplay.getPermissionChecker().isOmniadmin();
			
			request.setAttribute("comments", comments);
			request.setAttribute("isAdmin", isAdmin);
			request.setAttribute("entryID", entryID);
			
			super.render(request, response);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	public void postComment(ActionRequest request, ActionResponse response) throws PortalException, SystemException {
		try {
			
			String userPublikId = getPublikID(request);
			//Si l'utilisateur n'est pas connecté, on ne fait rien
				if (Validator.isNotNull(userPublikId)) {
				
				ServiceContext sc = ServiceContextFactory.getInstance(request);
	
				Comment comment = CommentLocalServiceUtil.createComment(sc);
	
				String message = ParamUtil.getString(request, "message");
				long entryID = ParamUtil.getLong(request, "entryID");
				
				if(message.length() > 0) {
					comment.setComment(message);
					comment.setAssetEntryId(entryID);
					comment.setPublikId(userPublikId);
					
					CommentLocalServiceUtil.addComment(comment);
				}
				// Redirection (évite double
				// requête POST si l'utilisateur actualise sa page)
				ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
				String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);
				PortletURL renderUrl = PortletURLFactoryUtil.create(request, portletName, themeDisplay.getPlid(),
						PortletRequest.RENDER_PHASE);
				response.sendRedirect(renderUrl.toString());
			}
		} catch (Exception e) {
			_log.error(e);
		}
	}

	//Méthode qui permet à l'administrateur de cacher un commentaire
	public void hideComment(ActionRequest request, ActionResponse response) throws PortalException, SystemException {
		try {

			Comment comment = CommentLocalServiceUtil.getComment(ParamUtil.getLong(request, "commentId"));
			comment.setStatus(WorkflowConstants.STATUS_DENIED);

			CommentLocalServiceUtil.updateComment(comment);
		} catch (Exception e) {
			_log.error(e);
		}
	}
	
	// Récupération du publik ID avec la session
	private String getPublikID(PortletRequest request) {

		LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(request);
		HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();

		return SessionParamUtil.getString(originalRequest, "publik_internal_id");
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}