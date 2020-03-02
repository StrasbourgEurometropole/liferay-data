package eu.strasbourg.portlet.oidc;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.oidc.display.context.EditPublikUserDisplayContext;
import eu.strasbourg.portlet.oidc.display.context.ViewPublikUsersDisplayContext;
import eu.strasbourg.service.agenda.model.EventParticipation;
import eu.strasbourg.service.agenda.service.EventParticipationLocalServiceUtil;
import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.service.CommentLocalServiceUtil;
import eu.strasbourg.service.like.model.Like;
import eu.strasbourg.service.like.service.LikeLocalServiceUtil;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.BudgetSupport;
import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.model.InitiativeHelp;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.model.ProjectFollowed;
import eu.strasbourg.service.project.model.Signataire;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.service.project.service.BudgetSupportLocalServiceUtil;
import eu.strasbourg.service.project.service.InitiativeHelpLocalServiceUtil;
import eu.strasbourg.service.project.service.InitiativeLocalServiceUtil;
import eu.strasbourg.service.project.service.PetitionLocalServiceUtil;
import eu.strasbourg.service.project.service.ProjectFollowedLocalServiceUtil;
import eu.strasbourg.service.project.service.SignataireLocalServiceUtil;

/**
 * @author cedric.henry
 */
@Component(
	immediate = true, 
	property = { 
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.footer-portlet-javascript=/js/oidc-bo-main.js",
		"com.liferay.portlet.header-portlet-css=/css/oidc-bo-main.css",
		"com.liferay.portlet.single-page-application=false", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/oidc-bo-view.jsp", "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" 
	}, 
	service = Portlet.class
)
public class OIDCBOPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		String cmd = ParamUtil.getString(renderRequest, "cmd");
		String tab = ParamUtil.getString(renderRequest, "tab");

		renderResponse.setTitle("Utilisateurs Publik");

		// Si on est sur la page d'ajout, on affiche un lien de retour
		String returnURL = ParamUtil.getString(renderRequest, "returnURL");
		boolean showBackButton = Validator.isNotNull(returnURL);
		if (showBackButton) {
			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(returnURL.toString());
		}

		// On set le displayContext selon la page sur laquelle on est
		if (cmd.equals("editPublikUser")) {
			EditPublikUserDisplayContext dc = new EditPublikUserDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		} else if (cmd.equals("anonymisedUser")) {
			String retour = "";
			long publikUserLiferayId = ParamUtil.getLong(renderRequest, "publikUserLiferayId");
			if (Validator.isNotNull(publikUserLiferayId)) {
				PublikUser publikUser = PublikUserLocalServiceUtil.fetchPublikUser(publikUserLiferayId);
				if (publikUser != null) {
					// récupération de l'utilisateur anonyme
					long anonymUserId = Long.parseLong( themeDisplay.getSiteGroup().getExpandoBridge()
							.getAttribute("publik_user_anonyme_id").toString());
					if (Validator.isNotNull(anonymUserId)) {
						PublikUser anonymUser = PublikUserLocalServiceUtil.fetchPublikUser(anonymUserId);
						if (anonymUser != null) {
							PublikUserLocalServiceUtil.anonymisedUserPlacit(anonymUser, publikUser);
						} else
							SessionMessages.add(renderRequest, "anonym-user-unfound");
					} else
						SessionMessages.add(renderRequest, "no-anonym-user-id");
				} else
					SessionMessages.add(renderRequest, "user-unfound");
			} else
				SessionMessages.add(renderRequest, "no-user-id");
			SessionMessages.add(renderRequest, "anonymised");
		}else if(cmd.equals("editAnonymisationHistoric")) {
			EditAnonymisationHistoricsDisplayContext dc = new EditAnonymisationHistoricsDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		}else if(tab.equals("anonymisationHistorics")) {
			ViewAnonymisationHistoricsDisplayContext dc = new ViewAnonymisationHistoricsDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
		}else{
			// Else, we are on the publik users list page
			ViewPublikUsersDisplayContext dc;
			try {
				dc = new ViewPublikUsersDisplayContext(renderRequest, renderResponse);
				renderRequest.setAttribute("dc", dc);
			} catch (PortalException e) {
				_log.error(e);
			}
		}

		// Admin ou pas
		renderRequest.setAttribute("isAdmin", themeDisplay.getPermissionChecker().isOmniadmin());

		super.render(renderRequest, renderResponse);
	}


	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}

