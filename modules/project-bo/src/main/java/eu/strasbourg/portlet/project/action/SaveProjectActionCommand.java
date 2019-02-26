package eu.strasbourg.portlet.project.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.model.ProjectFollowed;
import eu.strasbourg.service.project.model.ProjectFollowedModel;
import eu.strasbourg.service.project.model.ProjectTimeline;
import eu.strasbourg.service.project.service.PlacitPlaceLocalService;
import eu.strasbourg.service.project.service.ProjectFollowedLocalServiceUtil;
import eu.strasbourg.service.project.service.ProjectLocalService;
import eu.strasbourg.service.project.service.ProjectTimelineLocalService;
import eu.strasbourg.utils.MailHelper;
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component(immediate = true, property = { "javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_BO,
		"mvc.command.name=saveProject" }, service = MVCActionCommand.class)
public class SaveProjectActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) {

		try {
			ServiceContext sc = ServiceContextFactory.getInstance(request);

			// Validation
			boolean isValid = validate(request);
			if (!isValid) {
				// Si pas valide : on reste sur la page d'édition
				PortalUtil.copyRequestParameters(request, response);

				ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
				String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);
				PortletURL returnURL = PortletURLFactoryUtil.create(request, portletName, themeDisplay.getPlid(),
						PortletRequest.RENDER_PHASE);

				response.setRenderParameter("returnURL", returnURL.toString());
				response.setRenderParameter("cmd", "editProject");
				response.setRenderParameter("mvcPath", "/project-bo-edit-project.jsp");
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
				String externalImageURL = ParamUtil.getString(request, "externalImageURL");
				project.setExternalImageURL(externalImageURL);

				String externalImageCopyright = ParamUtil.getString(request, "externalImageCopyright");
				project.setExternalImageCopyright(externalImageCopyright);
			}

			// opacité de l'image
			Double opacityImage = ParamUtil.getDouble(request, "opacityImage");
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
				if (Validator.isNotNull(placitPlacesIndexe) && (Validator.isNotNull(placeSIGId)
						|| (Validator.isNotNull(placeName) && Validator.isNotNull(placeCityId)))) {

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
						String placeStreetName = ParamUtil.getString(request, "placeStreetName" + placitPlacesIndexe);
						placitPlace.setPlaceStreetName(placeStreetName);

						// Code postal
						String placeZipCode = ParamUtil.getString(request, "placeZipCode" + placitPlacesIndexe);
						placitPlace.setPlaceZipCode(placeZipCode);

						// Ville
						placitPlace.setPlaceCityId(placeCityId);

						// Image du lieu
						long placeImageId = ParamUtil.getLong(request, "placeImageId" + placitPlacesIndexe);
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
			String timelineIndexesString = ParamUtil.getString(request, "projectTimelineIndexes");
			for (String timelineIndex : timelineIndexesString.split(",")) {
				DateFormat paramDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				if (Validator.isNotNull(timelineIndex)
						&& Validator.isNotNull(ParamUtil.getString(request, "date" + timelineIndex))) {

					// Spacing
					Integer spacing = ParamUtil.getInteger(request, "spacing" + timelineIndex);

					// Date
					Date date = ParamUtil.getDate(request, "date" + timelineIndex, paramDateFormat);

					// Titre
					String timelineTitle = ParamUtil.getString(request, "title" + timelineIndex);

					// Lien
					String link = ParamUtil.getString(request, "link" + timelineIndex);
					
					// Format de date
					String dateFormat = ParamUtil.getString(request, "dateFormat" + timelineIndex);

					ProjectTimeline projectTimeline = _projectTimelineLocalService.createProjectTimeline();
					projectTimeline.setDate(date);
					projectTimeline.setSpacing(spacing);
					projectTimeline.setTitle(timelineTitle);
					projectTimeline.setLink(link);
					projectTimeline.setProjectId(project.getProjectId());
					projectTimeline.setDateFormat(dateFormat);
					this._projectTimelineLocalService.updateProjectTimeline(projectTimeline);
				}
			}

			// ---------------------------------------------------------------
			// -------------------------- Suivi de projet --------------------
			// ---------------------------------------------------------------
			
			// envoi de mail de suivi du projet
			boolean sendMail = ParamUtil.getBoolean(request, "cheboxMail");
			if (sendMail) {
				_log.info("envoi de mail utilisateur");

				// Récupération des personnes qui suivent le projet
				List<ProjectFollowed> projectFolloweds = ProjectFollowedLocalServiceUtil
						.getByProjectId(project.getProjectId());
				List<String> publikUserIds = projectFolloweds.stream().map(ProjectFollowedModel::getPublikUserId)
						.collect(Collectors.toList());
				List<JSONObject> publikUsers = publikUserIds.stream()
						.map(publikID -> PublikApiClient.getUserDetails(publikID)).collect(Collectors.toList());
				List<String> mails = publikUsers.stream().map(user -> user.getString("email"))
						.collect(Collectors.toList());
				if (!mails.isEmpty()) {
					// récupération de l'url du projet
					StringBuilder urlBuilded = new StringBuilder(getHomeURL(request));
					urlBuilded.append(project.getDetailURL());

					// récupération des images
					StringBuilder hostUrl = new StringBuilder("http://");
					hostUrl.append(request.getServerName()).append(":").append(request.getServerPort());
					StringBuilder headerImage = new StringBuilder(hostUrl)
							.append("/o/plateforme-citoyenne-theme/images/logos/mail-img-header-pcs.png");
					StringBuilder btnImage = new StringBuilder(hostUrl)
							.append("/o/plateforme-citoyenne-theme/images/logos/mail-btn-knowmore.png");

					// préparation du template de mail
					Map<String, Object> context = new HashMap<>();
					context.put("link", urlBuilded.toString());
					context.put("headerImage", headerImage.toString());
					context.put("footerImage", btnImage.toString());
					context.put("projectTitle", project.getTitle());
					context.put("projectDescription", project.getDescription());
					Configuration configuration = new Configuration(Configuration.getVersion());
					configuration.setClassForTemplateLoading(this.getClass(), "/META-INF/resources/templates/");
					configuration.setTagSyntax(Configuration.ANGLE_BRACKET_TAG_SYNTAX);
					boolean success = false;
					Template subjectTemplate = configuration.getTemplate("contact-mail-copy-subject-fr_FR.ftl");
					Template bodyTemplate = configuration.getTemplate("contact-mail-copy-body-fr_FR.ftl");
					StringWriter subjectWriter = new StringWriter();
					StringWriter bodyWriter = new StringWriter();
					subjectTemplate.process(context, subjectWriter);
					bodyTemplate.process(context, bodyWriter);
					ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
					InternetAddress fromAddress = new InternetAddress("no-reply@no-reply.strasbourg.eu",
							themeDisplay.getScopeGroup().getName(request.getLocale()));
					InternetAddress[] toAddresses = new InternetAddress[0];
					for (String mail : mails) {
						InternetAddress address = new InternetAddress(mail);
						toAddresses = ArrayUtil.append(toAddresses, address);
					}
					// envoi du mail aux utilisateurs
					success = MailHelper.sendMailWithHTML(fromAddress, toAddresses, subjectWriter.toString(),
							bodyWriter.toString());
					if (success) {
						SessionMessages.add(request, "mail-success");
					} else {
						SessionErrors.add(request, "unknown-error");
						return false;
					}
				}

			}

			_projectLocalService.updateProject(project, sc);

		} catch (PortalException | TemplateException | IOException | AddressException e) {
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

	/**
	 * Récupération de l'URL de base du site pour le lien vers les pages des entités
	 */
	private String getHomeURL(PortletRequest request) {
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		StringBuilder result = new StringBuilder(request.getServerName());
		result.append(":").append(request.getServerPort()).append("/");
		if (themeDisplay.getScopeGroup().getPublicLayoutSet().getVirtualHostname().isEmpty()
				|| themeDisplay.getScopeGroup().isStagingGroup()) {
			try {
				result.append("web").append(themeDisplay.getLayout().getGroup().getFriendlyURL()).append("/");
			} catch (PortalException e) {
				_log.error("erreur lors de la creation de l'url : ", e);
				result.append("web/");
			}
		}
		return result.toString();
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
