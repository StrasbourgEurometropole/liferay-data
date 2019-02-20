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
		} else {
			// On veut anonymiser l'utilisateur
			if (cmd.equals("anonymisedUser")) {
				long publikUserLiferayId = ParamUtil.getLong(renderRequest, "publikUserLiferayId");
				anonymised(renderRequest, themeDisplay, publikUserLiferayId);
			}
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

	public void anonymised(RenderRequest renderRequest, ThemeDisplay themeDisplay, long publikUserIdsStr) {
		if (Validator.isNotNull(publikUserIdsStr)) {
			PublikUser publikUser = PublikUserLocalServiceUtil.fetchPublikUser(publikUserIdsStr);
			if (publikUser != null) {
				// récupération de l'utilisateur anonyme
				long anonymUserId = Long.parseLong( themeDisplay.getSiteGroup().getExpandoBridge()
						.getAttribute("publik_user_anonyme_id").toString());
				if (Validator.isNotNull(anonymUserId)) {
					PublikUser anonymUser = PublikUserLocalServiceUtil.fetchPublikUser(anonymUserId);
					if (anonymUser != null) {
						anonymUser = null;
						// Anonymisation des informations utilisateur dans projets suivis
						List<ProjectFollowed> projectsFollowed = ProjectFollowedLocalServiceUtil
								.getByPublikId(publikUser.getPublikId());
						if (!projectsFollowed.isEmpty()) {
							for (ProjectFollowed projectFollowed : projectsFollowed) {
								projectFollowed.setPublikUserId(anonymUser.getPublikId());
								// Mise à jour en base
								ProjectFollowedLocalServiceUtil.updateProjectFollowed(projectFollowed);
							}
						}

						// Anonymisation des informations utilisateur dans participation à des
						// évènements
						List<EventParticipation> eventParticipations = EventParticipationLocalServiceUtil
								.getByPublikUser(publikUser.getPublikId());
						if (!eventParticipations.isEmpty()) {
							for (EventParticipation eventParticipation : eventParticipations) {
								eventParticipation.setPublikUserId(anonymUser.getPublikId());
								// Mise à jour en base
								EventParticipationLocalServiceUtil.updateEventParticipation(eventParticipation);
							}
						}

						// Anonymisation des informations utilisateur dans pétition
						List<Petition> petitions = PetitionLocalServiceUtil.getByPublikUserID(publikUser.getPublikId());
						if (!petitions.isEmpty()) {
							for (Petition petition : petitions) {
								petition.setPetitionnaireFirstname(anonymUser.getFirstName());
								petition.setPetitionnaireLastname(anonymUser.getLastName());
								petition.setPetitionnaireAdresse("");
								petition.setPetitionnairePostalCode(0);
								petition.setPetitionnaireCity("");
								petition.setPetitionnaireBirthday(null);
								petition.setPetitionnairePhone("");
								petition.setPetitionnaireEmail(anonymUser.getEmail());
								petition.setPublikId(anonymUser.getPublikId());
								// Mise à jour en base
								PetitionLocalServiceUtil.updatePetition(petition);
							}
						}
						
						// Anonymisation des informations utilisateur dans les signatures des pétitions
						List<Signataire> signataires = SignataireLocalServiceUtil
								.getSignataireByPublikId(publikUser.getPublikId());
						if (!signataires.isEmpty()) {
							for (Signataire signataire : signataires) {
								signataire.setSignataireFirstname(anonymUser.getFirstName());
								signataire.setSignataireName(anonymUser.getLastName());
								signataire.setAddress("");
								signataire.setPostalCode(0);
								signataire.setCity("");
								signataire.setBirthday(null);
								signataire.setPhone("");
								signataire.setMobilePhone("");
								signataire.setMail(anonymUser.getEmail());
								signataire.setPublikUserId(anonymUser.getPublikId());
								// Mise à jour en base
								SignataireLocalServiceUtil.updateSignataire(signataire);
							}
						}
						
						// Anonymisation des informations utilisateur dans les budgets participatifs
						List<BudgetParticipatif> budgetParticipatifs = BudgetParticipatifLocalServiceUtil.
								getByPublikUserID(publikUser.getPublikId());
						if (!budgetParticipatifs.isEmpty()) {
							for (BudgetParticipatif budgetParticipatif : budgetParticipatifs) {
								budgetParticipatif.setCitoyenFirstname(anonymUser.getFirstName());
								budgetParticipatif.setCitoyenLastname(anonymUser.getLastName());
								budgetParticipatif.setCitoyenAdresse("");
								budgetParticipatif.setCitoyenPostalCode(0);
								budgetParticipatif.setCitoyenCity("");
								budgetParticipatif.setCitoyenBirthday(null);
								budgetParticipatif.setCitoyenPhone("");
								budgetParticipatif.setCitoyenMobile("");
								budgetParticipatif.setCitoyenEmail(anonymUser.getEmail());
								budgetParticipatif.setPublikId(anonymUser.getPublikId());
								// Mise à jour en base
								BudgetParticipatifLocalServiceUtil.updateBudgetParticipatif(budgetParticipatif);
							}
						}
						
						// Anonymisation des inforamtions utilisateur dans les soutiens des budget participatif
						List<BudgetSupport> budgetSupports = BudgetSupportLocalServiceUtil.
								getBudgetSupportByPublikId(publikUser.getPublikId());
						if (!budgetSupports.isEmpty()) {
							for (BudgetSupport budgetSupport : budgetSupports) {
								budgetSupport.setCitoyenFirstname(anonymUser.getFirstName());
								budgetSupport.setCitoyenLastName(anonymUser.getLastName());
								budgetSupport.setCitoyenAddress("");
								budgetSupport.setCitoyenPostalCode(0);
								budgetSupport.setCitoyenCity("");
								budgetSupport.setCitoyenBirthday(null);
								budgetSupport.setCitoyenPhone("");
								budgetSupport.setCitoyenMobilePhone("");
								budgetSupport.setCitoyenMail(anonymUser.getEmail());
								budgetSupport.setPublikUserId(anonymUser.getPublikId());
								// Mise à jour en base
								BudgetSupportLocalServiceUtil.updateBudgetSupport(budgetSupport);
							}
						}
						
						// Anonymisation des informations utilisateur dans les initiatives
						List<Initiative> initiatives = InitiativeLocalServiceUtil.
								getByPublikUserID(publikUser.getPublikId());
						if (!initiatives.isEmpty()) {
							for (Initiative initiative : initiatives) {
								initiative.setPublikId(anonymUser.getPublikId());
								// Mise à jour en base
								InitiativeLocalServiceUtil.updateInitiative(initiative);
							}
						}
						
						// Anonymisation des informations utilisateur dans les aides aux initiatives
						List<InitiativeHelp> helps = InitiativeHelpLocalServiceUtil.
								getByPublikUserId(publikUser.getPublikId());
						if (!helps.isEmpty()) {
							for (InitiativeHelp help : helps) {
								help.setPublikUserId(anonymUser.getPublikId());
								// Mise à jour en base
								InitiativeHelpLocalServiceUtil.updateInitiativeHelp(help);
							}
						}
						
						// Anonymisation des informations utilisateur dans les commentaires
						List<Comment> comments = CommentLocalServiceUtil.getByPublikId(publikUser.getPublikId());
						if (!comments.isEmpty()) {
							for (Comment comment : comments) {
								comment.setPublikId(anonymUser.getPublikId());
								// Mise à jour en base
								CommentLocalServiceUtil.updateComment(comment);
							}
						}

						// Anonymisation des informations utilisateur dans les likes/dislikes
						List<Like> likes = LikeLocalServiceUtil.getByPublikUser(publikUser.getPublikId());
						if (!likes.isEmpty()) {
							for (Like like : likes) {
								like.setPublikUserId(anonymUser.getPublikId());
								// Mise à jour en base
								LikeLocalServiceUtil.updateLike(like);
							}
						}
					} else
						SessionErrors.add(renderRequest, "anonym-user-unfound");
				} else
					SessionErrors.add(renderRequest, "no-anonym-user-id");
			} else
				SessionErrors.add(renderRequest, "user-unfound");
		} else
			SessionErrors.add(renderRequest, "no-user-id");
		SessionMessages.add(renderRequest, "anonymised");
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}