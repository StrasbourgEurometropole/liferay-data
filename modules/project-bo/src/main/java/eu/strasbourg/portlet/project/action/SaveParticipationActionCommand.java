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

import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.service.ParticipationLocalService;
import eu.strasbourg.service.project.service.PlacitPlaceLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_BO,
		"mvc.command.name=saveParticipation" 
	},
	service = MVCActionCommand.class
)
public class SaveParticipationActionCommand implements MVCActionCommand {

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
				response.setRenderParameter("cmd", "editParticipation");
				response.setRenderParameter("mvcPath","/project-bo-edit-participation.jsp");
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
			
			// ---------------------------------------------------------------
			// -------------------------- GENERALITES ------------------------
			// ---------------------------------------------------------------

			// Titre
			String title = ParamUtil.getString(request, "title");
			participation.setTitle(title);

			// Auteur
			String author = ParamUtil.getString(request, "author");
			participation.setAuthor(author);
			
			// ---------------------------------------------------------------
			// -------------------------- CONTACT ----------------------------
			// ---------------------------------------------------------------
			
			// Contact : nom
			String contactName = ParamUtil.getString(request, "contactName");
			participation.setContactName(contactName);
						
			// Contact : ligne 1
			String contactLine1 = ParamUtil.getString(request, "contactLine1");
			participation.setContactLine1(contactLine1);
						
			// Contact : ligne 2
			String contactLine2 = ParamUtil.getString(request, "contactLine2");
			participation.setContactLine2(contactLine2);
						
			// Contact : numéro de téléphone
			String contactPhoneNumber = ParamUtil.getString(request, "contactPhoneNumber");
			participation.setContactPhoneNumber(contactPhoneNumber);
			
			// ---------------------------------------------------------------
			// -------------------------- MEDIAS -----------------------------
			// ---------------------------------------------------------------
			
			// Choix de la vidéo ou de l'image
			Boolean mediaChoice = ParamUtil.getBoolean(request, "mediaChoice");
			participation.setMediaChoice(mediaChoice);
			
			// URL de la vidéo
			String videoUrl = ParamUtil.getString(request, "videoUrl");
			participation.setVideoUrl(videoUrl);
			
			// Image
			Long imageId = ParamUtil.getLong(request, "imageId");
			String externalImageURL = ParamUtil.getString(request, "externalImageURL");
			if (imageId > 0) { // Image interne
				participation.setImageId(imageId);
				participation.setExternalImageURL("");
				participation.setExternalImageCopyright("");
			} else if (!Validator.isNull(externalImageURL)) { // Image interne
				participation.setImageId((long) 0);
				
				participation.setExternalImageURL(externalImageURL);

				String externalImageCopyright = ParamUtil.getString(request,
					"externalImageCopyright");
				participation.setExternalImageCopyright(externalImageCopyright);
			} else {
				participation.setImageId((long) 0);
				participation.setExternalImageURL("");
				participation.setExternalImageCopyright("");
			}
			
			// ---------------------------------------------------------------
			// -------------------------- DESCRIPTION ------------------------
			// ---------------------------------------------------------------
			
			// Chapeau de la description
			String descriptionChapeau = ParamUtil.getString(request, "descriptionChapeau");
			participation.setDescriptionChapeau(descriptionChapeau);
			
			// Corps de la description
			String descriptionBody = ParamUtil.getString(request, "descriptionBody");
			participation.setDescriptionBody(descriptionBody);
			
			// ---------------------------------------------------------------
			// -------------------------- LIEUX DE CONSULTATIONS -------------
			// ---------------------------------------------------------------
			
			// Corps de la description des lieux de consultation
			String consultationPlacesBody = ParamUtil.getString(request, "consultationPlacesBody");
			participation.setConsultationPlacesBody(consultationPlacesBody);
			
			// Lieux
			for (PlacitPlace placitPlace : participation.getPlacitPlaces()) {
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

					// Rattachement a la participation
					placitPlace.setParticipationId(participation.getParticipationId());

					// Mise à jour en base
					_placitPlaceLocalService.updatePlacitPlace(placitPlace, sc);
				}
			}
			
			// ---------------------------------------------------------------
			// -------------------------- DOCUMENTS --------------------------
			// ---------------------------------------------------------------
			
			// Documents associés
			String filesIds = ParamUtil.getString(request, "filesIds");
			participation.setFilesIds(filesIds);
			
			// ---------------------------------------------------------------
			// -------------------------- AUTRES -----------------------------
			// ---------------------------------------------------------------
			
			// Evenements
			String eventsIds = ParamUtil.getString(request, "eventsIds");
			participation.setEventsIds(eventsIds);
			
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
	
	@Reference(unbind = "-")
	protected void setPlacitPlaceLocalService(PlacitPlaceLocalService placitPlaceLocalService) {
		_placitPlaceLocalService = placitPlaceLocalService;
	}
	
	private ParticipationLocalService _participationLocalService;
	
	private PlacitPlaceLocalService _placitPlaceLocalService;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
