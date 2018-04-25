package eu.strasbourg.portlet.participation.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.service.ParticipationLocalService;

public class SaveParticipationActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) 
			throws PortletException {
		
		try {
			ServiceContext sc = ServiceContextFactory.getInstance(request);
			sc.setScopeGroupId(
				((ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY))
					.getCompanyGroupId());
			
			// Validation
			boolean isValid = validate(request);
			if (!isValid) {
				// Si pas valide : on reste sur la page d'édition
				PortalUtil.copyRequestParameters(request, response);

				ThemeDisplay themeDisplay = (ThemeDisplay) request
					.getAttribute(WebKeys.THEME_DISPLAY);
				String portletName = (String) request
					.getAttribute(WebKeys.PORTLET_ID);
				PortletURL returnURL = PortletURLFactoryUtil.create(request,
					portletName, themeDisplay.getPlid(),
					PortletRequest.RENDER_PHASE);

				response.setRenderParameter("returnURL", returnURL.toString());
				response.setRenderParameter("mvcPath","/participation-bo-edit-participation.jsp");
				return false;
			}

			// Edition de la participation
			long participationId = ParamUtil.getLong(request, "participationId");
			Participation participation;
			if (participationId == 0) {
				// Si elle n'existe pas (add/save), on la créé
				participation = _participationLocalService.createParticipation(sc);
			} else {
				// Si elle existe (edit), on la cherche
				participation = _participationLocalService.getParticipation(participationId);
			}

			// Titre
			String title = ParamUtil.getString(request, "title");
			participation.setTitle(title);

			// Auteur
			String author = ParamUtil.getString(request, "author");
			participation.setAuthor(author);
			
			// Défini le format de date à utiliser pour les champs temporels 
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			// Date de publication
			Date publicationDate = ParamUtil.getDate(request, "publicationDate", dateFormat);
			participation.setPublicationDate(publicationDate);
			
			// Date d'expiration
			Date expirationDate = ParamUtil.getDate(request, "expirationDate", dateFormat);
			participation.setExpirationDate(expirationDate);

			_participationLocalService.updateParticipation(participation, sc);

		} catch (PortalException e) {
			_log.error(e);
		}

		return true;
	}
	
	/**
	 * Validation des champs obligatoires
	 */
	private boolean validate(ActionRequest request) {
		// Variable à mettre à false quand une erreur est détectée
		boolean isValid = true;
		// Défini le format de date à utiliser pour les champs temporels 
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		// Titre
		if (Validator.isNull(ParamUtil.getString(request, "title"))) {
			SessionErrors.add(request, "title-error");
			isValid = false;
		}

		// Auteur
		if (Validator.isNull(ParamUtil.getString(request, "author"))) {
			SessionErrors.add(request, "author-error");
			isValid = false;
		}
		
		// Date de publication
		if (Validator.isNull(ParamUtil.getDate(request, "publicationDate", dateFormat))) {
			SessionErrors.add(request, "publicationDate-error");
			isValid = false;
		}
		
		// Date d'expiration
		if (Validator.isNull(ParamUtil.getDate(request, "expirationDate", dateFormat))) {
			SessionErrors.add(request, "expirationDate");
			isValid = false;
		}

		return isValid;
	}
	
	@Reference(unbind = "-")
	protected void setParticipationLocalService(ParticipationLocalService participationLocalService) {
		_participationLocalService = participationLocalService;
	}
	
	private ParticipationLocalService _participationLocalService;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
