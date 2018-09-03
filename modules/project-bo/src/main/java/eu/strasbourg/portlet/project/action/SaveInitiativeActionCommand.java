package eu.strasbourg.portlet.project.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
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

import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.service.InitiativeLocalService;
import eu.strasbourg.service.project.service.PlacitPlaceLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_BO,
		"mvc.command.name=saveInitiative" 
	},
	service = MVCActionCommand.class
)
public class SaveInitiativeActionCommand implements MVCActionCommand {

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
				response.setRenderParameter("cmd", "editInitiative");
				response.setRenderParameter("mvcPath","/project-bo-edit-initiative.jsp");
				return false;
			}

			// Edition de la initiative
			long initiativeId = ParamUtil.getLong(request, "initiativeId");
			Initiative initiative;
			if (initiativeId == 0) {
				// Si elle n'existe pas (add/save), on la créé
				initiative = _initiativeLocalService.createInitiative(sc);
			} else {
				// Si elle existe (edit), on la cherche
				initiative = _initiativeLocalService.getInitiative(initiativeId);
			}
			
			// ---------------------------------------------------------------
			// -------------------------- GENERALITES ------------------------
			// ---------------------------------------------------------------

			// Titre
			String title = ParamUtil.getString(request, "title");
			initiative.setTitle(title);

			// Auteur
			String author = ParamUtil.getString(request, "author");
			initiative.setAuthor(author);
									
			// ---------------------------------------------------------------
			// -------------------------- MEDIAS -----------------------------
			// ---------------------------------------------------------------
			
			// Choix de la vidéo ou de l'image
			Boolean mediaChoice = ParamUtil.getBoolean(request, "mediaChoice");
			initiative.setMediaChoice(mediaChoice);
			
			// URL de la vidéo
			String videoUrl = ParamUtil.getString(request, "videoUrl");
			initiative.setVideoUrl(videoUrl);
			
			// Image
			Long imageId = ParamUtil.getLong(request, "imageId");
			String externalImageURL = ParamUtil.getString(request, "externalImageURL");
			if (imageId > 0) { // Image interne
				initiative.setImageId(imageId);
				initiative.setExternalImageURL("");
				initiative.setExternalImageCopyright("");
			} else if (!Validator.isNull(externalImageURL)) { // Image interne
				initiative.setImageId((long) 0);
				
				initiative.setExternalImageURL(externalImageURL);

				String externalImageCopyright = ParamUtil.getString(request,
					"externalImageCopyright");
				initiative.setExternalImageCopyright(externalImageCopyright);
			} else {
				initiative.setImageId((long) 0);
				initiative.setExternalImageURL("");
				initiative.setExternalImageCopyright("");
			}
			
			// ---------------------------------------------------------------
			// -------------------------- DESCRIPTION ------------------------
			// ---------------------------------------------------------------
						
			// Corps de la description
			String description = ParamUtil.getString(request, "description");
			initiative.setDescription(description);
			
			// ---------------------------------------------------------------
			// -------------------------- LIEUX DE CONSULTATIONS -------------
			// ---------------------------------------------------------------
			
			// Corps de la description des lieux de consultation
			String consultationPlacesBody = ParamUtil.getString(request, "consultationPlacesBody");
			initiative.setConsultationPlacesBody(consultationPlacesBody);
			
			// Lieux
			for (PlacitPlace placitPlace : initiative.getPlacitPlaces()) {
				// On supprime d'abord les lieux existants
				_placitPlaceLocalService.removePlacitPlace(placitPlace.getPlacitPlaceId());
			}
			// Puis on crée les nouveaux
			String placitPlacesIndexesString = ParamUtil.getString(request, "placeIndexes");
			for (String placitPlacesIndexe : placitPlacesIndexesString.split(",")) {
				
				// Recupere les valeurs de test pour savoir si il existe des lieux placit
				String placeSIGId = ParamUtil.getString(request, "placeSIGId" + placitPlacesIndexe);
				String placeName = ParamUtil.getString(request, "placeName" + placitPlacesIndexe);
				long placeCityId = ParamUtil.getLong(request, "placeCityId" + placitPlacesIndexe);
				
				// Si il existe au moins un lieu SIG ou manuel
				if (Validator.isNotNull(placitPlacesIndexe) 
						&& (Validator.isNotNull(placeSIGId) 
						|| (Validator.isNotNull(placeName) 
						&& Validator.isNotNull(placeCityId)))) {
					// Initialisation de l'entité
					PlacitPlace placitPlace = _placitPlaceLocalService.createPlacitPlace(sc);				
					
					if (Validator.isNotNull(placeSIGId)) {
						// Lieu SIG
						placitPlace.setPlaceSIGId(placeSIGId);
					} else {
						// Nom du lieu
						placitPlace.setPlaceName(placeName);

						// Numéro de rue
						String placeStreetNumber = ParamUtil.getString(request,
							"placeStreetNumber" + placitPlacesIndexe);
						placitPlace.setPlaceStreetNumber(placeStreetNumber);

						// Nom de la rue
						String placeStreetName = ParamUtil.getString(request,
							"placeStreetName" + placitPlacesIndexe);
						placitPlace.setPlaceStreetName(placeStreetName);

						// Code postal
						String placeZipCode = ParamUtil.getString(request,
							"placeZipCode" + placitPlacesIndexe);
						placitPlace.setPlaceZipCode(placeZipCode);

						// Ville
						placitPlace.setPlaceCityId(placeCityId);
						
						// Image du lieu
						long placeImageId = ParamUtil.getLong(request,
							"placeImageId" + placitPlacesIndexe);
						placitPlace.setImageId(placeImageId);
					}

					// Rattachement a la initiative
					placitPlace.setInitiativeId(initiative.getInitiativeId());

					// Mise à jour en base
					_placitPlaceLocalService.updatePlacitPlace(placitPlace, sc);
				}
			}
			
			// ---------------------------------------------------------------
			// -------------------------- AUTRES -----------------------------
			// ---------------------------------------------------------------
						
			
			// Défini le format de date à utiliser pour les champs temporels 
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			// Date de publication
			Date publicationDate = ParamUtil.getDate(request, "publicationDate", dateFormat);
			initiative.setPublicationDate(publicationDate);

			_initiativeLocalService.updateInitiative(initiative, sc);

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
	protected void setInitiativeLocalService(InitiativeLocalService initiativeLocalService) {
		_initiativeLocalService = initiativeLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setPlacitPlaceLocalService(PlacitPlaceLocalService placitPlaceLocalService) {
		_placitPlaceLocalService = placitPlaceLocalService;
	}
	
	private InitiativeLocalService _initiativeLocalService;
	
	private PlacitPlaceLocalService _placitPlaceLocalService;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
