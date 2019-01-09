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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.service.PetitionLocalService;
import eu.strasbourg.service.project.service.PlacitPlaceLocalService;
import eu.strasbourg.service.project.service.SignataireLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
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

	private SignataireLocalServiceUtil _signataireLocalService;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) {

		// Défini le format de date à utiliser pour les champs temporels
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
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

			//récupération des informations :
			long quotasSignature = ParamUtil.getLong(request, "quotaSignature");
			Boolean mediaChoice = ParamUtil.getBoolean(request, "mediaChoice");
			String videoUrl = ParamUtil.getString(request, "videoUrl");
			Long imageId = ParamUtil.getLong(request, "imageId");
			String externalImageURL = ParamUtil.getString(request, "externalImageURL");
			String externalImageCopyright = ParamUtil.getString(request, "externalImageCopyright");
			String description = ParamUtil.getString(request, "description");
			Boolean isSupported = ParamUtil.getBoolean(request, "isSupported");
			String supportedBy = ParamUtil.getString(request, "supportedBy");
			
			String placeTextArea = ParamUtil.getString(request, "placeTextArea");
			String placitPlacesIndexesString = ParamUtil.getString(request, "placeIndexes");
			String filesIds = ParamUtil.getString(request, "filesIds");
			
			String publicationDateStr = ParamUtil.getString(request, "publicationDate");
			String publicationTimeStr = ParamUtil.getString(request, "publicationDateTime");
			Date publicationDate = GetterUtil.getDate(publicationDateStr + " " + publicationTimeStr, dateFormat);
			
			String expirationDateStr = ParamUtil.getString(request, "expirationDate");
			String expirationDateTimeStr = ParamUtil.getString(request, "expirationDateTime");
			Date expirationDate = GetterUtil.getDate(expirationDateStr + " " + expirationDateTimeStr, dateFormat);
			
			String title = ParamUtil.getString(request, "title");
			String prenomPetitionnaire = ParamUtil.getString(request, "petitionnaireFirstname");
			String nomPetitionnaire = ParamUtil.getString(request, "petitionnaireLastname");
			String inTheNameOf = ParamUtil.getString(request, "inTheNameOf");
			int fakeSignataire = ParamUtil.getInteger(request, "nbFakeSignataire");

			// ---------------------------------------------------------------
			// -------------------------- GENERALITES ------------------------
			// ---------------------------------------------------------------

			// Titre
			petition.setTitle(title);

			// Auteur
			petition.setPetitionnaireFirstname(prenomPetitionnaire);
			petition.setPetitionnaireLastname(nomPetitionnaire);
			
			// Au nom de ...
			petition.setInTheNameOf(inTheNameOf);
			
			// quota-signature
			petition.setQuotaSignature(quotasSignature);

			// ---------------------------------------------------------------
			// -------------------------- Video / Image ------------------------
			// ---------------------------------------------------------------
			
			petition.setMediaChoice(mediaChoice);
			petition.setVideoUrl(videoUrl);
			if (imageId>0){ //image interne
				petition.setImageId(imageId);
				petition.setExternalImageURL("");
				petition.setExternalImageCopyright("");
			}else if (!Validator.isNull(externalImageURL)){ // Image interne
				petition.setImageId(0L);
				petition.setExternalImageURL(externalImageURL);
				petition.setExternalImageCopyright(externalImageCopyright);
			} else {
				petition.setImageId(0L);
				petition.setExternalImageURL("");
				petition.setExternalImageCopyright("");
			}

			// ---------------------------------------------------------------
			// -------------------------- DESCRIPTION ------------------------
			// ---------------------------------------------------------------

			petition.setDescription(description);
			
			// ---------------------------------------------------------------
			// -------------------------- DESCRIPTION ------------------------
			// ---------------------------------------------------------------

			petition.setIsSupported(isSupported);
			petition.setSupportedBy(supportedBy);

			// ---------------------------------------------------------------
			// -------------------------- LIEUX DE CONSULTATIONS -------------
			// ---------------------------------------------------------------

			petition.setPlaceTextArea(placeTextArea);
			
			if (fakeSignataire>0)
				SignataireLocalServiceUtil.createFakeSignataire(petitionId,fakeSignataire);

			for (PlacitPlace placitPlace : petition.getPlacitPlaces()){
				_placitPlaceLocalService.removePlacitPlace(placitPlace.getPlacitPlaceId());
			}
			for (String placitPlacesIndexe : placitPlacesIndexesString.split(",")){
				String placeSIGId = ParamUtil.getString(request, "placeSIGId" + placitPlacesIndexe);
				String placeName = ParamUtil.getString(request, "placeName" + placitPlacesIndexe);
				long placeCityId = ParamUtil.getLong(request, "placeCityId" + placitPlacesIndexe);

				// Si il existe au moins un lieu SIG ou manuel
				if (Validator.isNotNull(placitPlacesIndexe)
						&&(Validator.isNotNull(placeSIGId)
						||(Validator.isNotNull(placeName)
						&&Validator.isNotNull(placeCityId)))){
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
                    placitPlace.setPetitionId(petition.getPetitionId());

                    // Mise à jour en base
                    _placitPlaceLocalService.updatePlacitPlace(placitPlace, sc);
				}
			}

            // ---------------------------------------------------------------
            // -------------------------- DOCUMENTS --------------------------
            // ---------------------------------------------------------------

            petition.setFilesIds(filesIds);

            // ---------------------------------------------------------------
            // -------------------------- AUTRES -----------------------------
            // ---------------------------------------------------------------

            petition.setPublicationDate(publicationDate);
            petition.setExpirationDate(expirationDate);

            _petitionLocalService.updatePetition(petition,sc);
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
		if (Validator.isNull(ParamUtil.getString(request, "petitionnaireFirstname")) && Validator.isNull(ParamUtil.getString(request, "petitionnaireLastname"))) {
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
