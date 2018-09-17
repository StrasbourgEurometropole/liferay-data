package eu.strasbourg.portlet.project.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.model.ProjectTimeline;
import eu.strasbourg.service.project.service.PlacitPlaceLocalService;
import eu.strasbourg.service.project.service.ProjectLocalService;
import eu.strasbourg.service.project.service.ProjectTimelineLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { 
		"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_BO,
		"mvc.command.name=saveProject"
	},
	service = MVCActionCommand.class
)
public class SaveProjectActionCommand implements MVCActionCommand {

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
				response.setRenderParameter("cmd","editProject");
				response.setRenderParameter("mvcPath","/project-bo-edit-project.jsp");
				return false;
			}

			// Edition du projet
			long projectId = ParamUtil.getLong(request, "projectId");
			Project project;
			if (projectId == 0) {
				project = _projectLocalService.createProject(sc);
			} else {
				project = _projectLocalService.getProject(projectId);
			}
			
			// ---------------------------------------------------------------
			// -------------------------- GENERALITES ------------------------
			// ---------------------------------------------------------------

			// Titre
			String title = ParamUtil.getString(request, "title");
			project.setTitle(title);

			// Description
			String description = ParamUtil.getString(request, "description");
			project.setDescription(description);

			// Image
			Long imageId = ParamUtil.getLong(request, "imageId");
			if (imageId > 0) { // Image interne
				project.setImageId(imageId);
				project.setExternalImageURL("");
				project.setExternalImageCopyright("");
			} else { // Image interne
				project.setImageId((long) 0);
				String externalImageURL = ParamUtil.getString(request,
					"externalImageURL");
				project.setExternalImageURL(externalImageURL);

				String externalImageCopyright = ParamUtil.getString(request,
					"externalImageCopyright");
				project.setExternalImageCopyright(externalImageCopyright);
			}

			// opacité de l'image
			Long opacityImage = ParamUtil.getLong(request, "opacityImage");
			project.setOpacityImage(opacityImage);

			// URL détail du projet
			String detailURL = ParamUtil.getString(request, "detailURL");
			project.setDetailURL(detailURL);
			
			// ---------------------------------------------------------------
			// -------------------------- EN BREF ----------------------------
			// ---------------------------------------------------------------
			
			// Budget
			String budget = ParamUtil.getString(request, "budget");
			project.setBudget(budget);
			
			// Label
			String label = ParamUtil.getString(request, "label");
			project.setLabel(label);
			
			// Durée
			String duration = ParamUtil.getString(request, "duration");
			project.setDuration(duration);
			
			// Partenaires
			String partners = ParamUtil.getString(request, "partners");
			project.setPartners(partners);
			
			// ---------------------------------------------------------------
			// -------------------------- CONTACT ----------------------------
			// ---------------------------------------------------------------
			
			// Contact : nom
			String contactName = ParamUtil.getString(request, "contactName");
			project.setContactName(contactName);
			
			// Contact : ligne 1
			String contactLine1 = ParamUtil.getString(request, "contactLine1");
			project.setContactLine1(contactLine1);
			
			// Contact : ligne 2
			String contactLine2 = ParamUtil.getString(request, "contactLine2");
			project.setContactLine2(contactLine2);
			
			// Contact : numéro de téléphone
			String contactPhoneNumber = ParamUtil.getString(request, "contactPhoneNumber");
			project.setContactPhoneNumber(contactPhoneNumber);
			
			// ---------------------------------------------------------------
			// -------------------------- LIEUX DE CONSULTATIONS -------------
			// ---------------------------------------------------------------
			
			// Lieux
			for (PlacitPlace placitPlace : project.getPlacitPlaces()) {
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
					placitPlace.setProjectId(project.getProjectId());

					// Mise à jour en base
					_placitPlaceLocalService.updatePlacitPlace(placitPlace, sc);
				}
			}
			
			// ---------------------------------------------------------------
			// -------------------------- TIMELINE ---------------------------
			// ---------------------------------------------------------------
			
			// Suppression des anciennes entrées de timeline
			List<ProjectTimeline> oldTimelines = project.getProjectTimelines();
			for (ProjectTimeline projectTimeline : oldTimelines) {
				_projectTimelineLocalService.deleteProjectTimeline(projectTimeline);
			}
			// Ajout des nouvelles
			String timelineIndexesString = ParamUtil.getString(request,
				"projectTimelineIndexes");
			for (String timelineIndex : timelineIndexesString.split(",")) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				if (Validator.isNotNull(timelineIndex)
					&& Validator.isNotNull(ParamUtil.getString(request, "date" + timelineIndex))) {

					// Spacing
					Integer spacing = ParamUtil.getInteger(request,
							"spacing" + timelineIndex);

					// Date
					Date date = ParamUtil.getDate(request,
							"date" + timelineIndex, dateFormat);
					
					// Titre
					String timelineTitle = ParamUtil.getString(request,
							"title" + timelineIndex);
					
					// Lien
					String link = ParamUtil.getString(request,
							"link" + timelineIndex);

					ProjectTimeline projectTimeline = _projectTimelineLocalService
						.createProjectTimeline();
					projectTimeline.setDate(date);
					projectTimeline.setSpacing(spacing);
					projectTimeline.setTitle(timelineTitle);
					projectTimeline.setLink(link);
					projectTimeline.setProjectId(project.getProjectId());
					this._projectTimelineLocalService
						.updateProjectTimeline(projectTimeline);
				}
			}

			_projectLocalService.updateProject(project, sc);

		} catch (PortalException e) {
			_log.error(e);
		}
		return true;
	}
	
	/**
	 * Validation des champs obligatoires
	 */
	private boolean validate(ActionRequest request) {
		boolean isValid = true;

		// Titre
		if (Validator.isNull(ParamUtil.getString(request, "title"))) {
			SessionErrors.add(request, "title-error");
			isValid = false;
		}

		// Description
		if (Validator.isNull(ParamUtil.getString(request, "description"))) {
			SessionErrors.add(request, "description-error");
			isValid = false;
		}

		// Image
		long imageId = ParamUtil.getLong(request, "imageId");
		String imageURL = ParamUtil.getString(request, "externalImageURL");
		if (imageId == 0 && Validator.isNull(imageURL)) {
			SessionErrors.add(request, "image-error");
			isValid = false;
		}
		
		return isValid;
	}
	
	@Reference(unbind = "-")
	protected void setProjectLocalService(ProjectLocalService projectLocalService) {
		_projectLocalService = projectLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setProjectTimelineLocalService(ProjectTimelineLocalService projectTimelineLocalService) {
		_projectTimelineLocalService = projectTimelineLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setPlacitPlaceLocalService(PlacitPlaceLocalService placitPlaceLocalService) {
		_placitPlaceLocalService = placitPlaceLocalService;
	}
	
	private ProjectLocalService _projectLocalService;
	
	private ProjectTimelineLocalService _projectTimelineLocalService;
	
	private PlacitPlaceLocalService _placitPlaceLocalService;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
