package eu.strasbourg.portlet.project.action;

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
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.service.PetitionLocalService;
import eu.strasbourg.service.project.service.PlacitPlaceLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_BO,
		"mvc.command.name=savePetition"
	},
	service = MVCActionCommand.class
)
public class SavePetitionActionCommand implements MVCActionCommand {

	private PetitionLocalService _petitionLocalService;

	private PlacitPlaceLocalService _placitPlaceLocalService;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) 
			throws PortletException {
		
		try {
			ServiceContext sc = ServiceContextFactory.getInstance(request);
			
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
				response.setRenderParameter("cmd", "editPetition");
				response.setRenderParameter("mvcPath","/project-bo-edit-petition.jsp");
				return false;
			}

			// Edition de la petition
			long petitionId = ParamUtil.getLong(request, "petitionId");
			Petition petition;
			if (petitionId == 0) {
				// Si elle n'existe pas (add/save), on la créé
				petition = _petitionLocalService.createPetition(sc);
			} else {
				// Si elle existe (edit), on la cherche
				petition = _petitionLocalService.getPetition(petitionId);
			}
			
			// ---------------------------------------------------------------
			// -------------------------- GENERALITES ------------------------
			// ---------------------------------------------------------------

			// Titre
			String title = ParamUtil.getString(request, "title");
			petition.setTitle(title);

			// Auteur
			String author = ParamUtil.getString(request, "author");
			petition.setUserName(author);

			// Choix de la vidéo ou de l'image
			Boolean mediaChoice = ParamUtil.getBoolean(request, "mediaChoice");
			petition.setMediaChoice(mediaChoice);
			
			// URL de la vidéo
			String videoUrl = ParamUtil.getString(request, "videoUrl");
			petition.setVideoUrl(videoUrl);

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

		return isValid;
	}
	
	@Reference(unbind = "-")
	protected void setPetitionLocalService(PetitionLocalService petitionLocalService) {
		_petitionLocalService = petitionLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setPlacitPlaceLocalService(PlacitPlaceLocalService placitPlaceLocalService) {
		_placitPlaceLocalService = placitPlaceLocalService;
	}

}
