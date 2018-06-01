package eu.strasbourg.porlet.comment;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
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
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * @author romain.vergnais
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=false", "javax.portlet.display-name=Commentaires",
		"javax.portlet.init-param.add-process-action-success-action=false", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/comments-view.jsp",
		"javax.portlet.name=" + StrasbourgPortletKeys.COMMENT_WEB, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.supported-public-render-parameter=message" }, service = Portlet.class)
public class CommentPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		
		String userPublikId = getPublikID(request);
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			CommentConfiguration configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(CommentConfiguration.class);

			String orderBy = configuration.orderBy();
			if (Validator.isNull(orderBy)) {
				orderBy = "asc";
			}
			
			long entryID = 0;
			PortletSession portletSession = request.getPortletSession();

			if (portletSession.getAttribute("LIFERAY_SHARED_assetEntryID", PortletSession.APPLICATION_SCOPE) != null)
				entryID = (long) portletSession.getAttribute("LIFERAY_SHARED_assetEntryID",
						PortletSession.APPLICATION_SCOPE);
			else
				return;

			List<Comment> comments = CommentLocalServiceUtil.getByAssetEntry(entryID,
					WorkflowConstants.STATUS_APPROVED);
			
			if(orderBy.equals("desc"))
				comments = comments.stream().sorted((c1, c2) -> {
				return c2.getCreateDate().compareTo(c1.getCreateDate());}).collect(Collectors.toList());
			
			request.setAttribute("comments", comments);
			request.setAttribute("entryID", entryID);
			super.render(request, response);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	public void postComment(ActionRequest request, ActionResponse response) throws PortalException, SystemException {
		try {
			
			String userPublikId = getPublikID(request);
			
			ServiceContext sc = ServiceContextFactory.getInstance(request);

			Comment comment = CommentLocalServiceUtil.createComment(sc);

			String message = ParamUtil.getString(request, "message");
			long entryID = ParamUtil.getLong(request, "entryID");

			comment.setComment(message);
			comment.setAssetEntryId(entryID);
			comment.setPublikId(userPublikId);
			
			CommentLocalServiceUtil.addComment(comment);
		} catch (Exception e) {
			_log.error(e);
		}
	}

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