package eu.strasbourg.service.agenda.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.agenda.model.Campaign;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.EventPeriod;
import eu.strasbourg.service.agenda.model.ImportReport;
import eu.strasbourg.service.agenda.model.ImportReportLine;
import eu.strasbourg.service.agenda.model.Manifestation;
import eu.strasbourg.service.agenda.service.CampaignLocalServiceUtil;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.agenda.service.EventPeriodLocalServiceUtil;
import eu.strasbourg.service.agenda.service.ImportReportLineLocalServiceUtil;
import eu.strasbourg.service.agenda.service.ImportReportLocalServiceUtil;
import eu.strasbourg.service.agenda.service.ManifestationLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;

public class AgendaImporter {

	private final Log _log = LogFactoryUtil.getLog(this.getClass());

	private DateFormat dateFormat;
	private long defaultUserId;
	private long companyId;
	private long globalGroupId;
	private List<AssetVocabulary> manifestationRequiredVocabularies;
	private List<AssetVocabulary> manifestationVocabularies;
	private List<AssetVocabulary> eventRequiredVocabularies;
	private List<AssetVocabulary> eventVocabularies;

	public AgendaImporter() {
		this.companyId = PortalUtil.getDefaultCompanyId();
		try {
			this.globalGroupId = CompanyLocalServiceUtil.getCompany(companyId)
				.getGroup().getGroupId();
		} catch (PortalException e) {
			_log.error(e);
		}
		try {
			this.defaultUserId = UserLocalServiceUtil
				.getDefaultUserId(companyId);
		} catch (PortalException e) {
			_log.error(e);
		}
		this.dateFormat = DateFormatFactoryUtil
			.getSimpleDateFormat("yyyy-MM-dd");

		// On récupère les vocabulaires obligatoires et facultatifs
		long manifestationClassNameId = ClassNameLocalServiceUtil
			.getClassName(Manifestation.class.getName()).getClassNameId();
		this.manifestationRequiredVocabularies = AssetVocabularyHelper
			.getRequiredVocabulariesForAssetType(this.globalGroupId,
				manifestationClassNameId);
		this.manifestationVocabularies = AssetVocabularyHelper
			.getVocabulariesForAssetType(this.globalGroupId,
				manifestationClassNameId);
		long eventClassNameId = ClassNameLocalServiceUtil
			.getClassName(Event.class.getName()).getClassNameId();
		this.eventRequiredVocabularies = AssetVocabularyHelper
			.getRequiredVocabulariesForAssetType(this.globalGroupId,
				eventClassNameId);
		this.eventVocabularies = AssetVocabularyHelper
			.getVocabulariesForAssetType(this.globalGroupId, eventClassNameId);
	}

	public boolean doImport() {
		// On prépare le rapport, l'objet JSON et le répertoire qui
		// contient les fichiers à importer
		_log.info("Start import");
		ImportReport report = null;
		JSONObject json = null;
		File directory = new File(
			StrasbourgPropsUtil.getAgendaImportDirectory());
		int fileIndex = 0;
		// On boucle sur les fichiers du répertoire
		for (File file : directory.listFiles()) {
			// Si ce n'est pas un fichier, on passe
			if (!file.isFile()) {
				continue;
			}

			// On initialise le rapport
			try {
				report = ImportReportLocalServiceUtil.createImportReport();
				report.setFilename(file.getName());
				report.setStartDate(new Date());
			} catch (PortalException e) { // Erreur de création du rapport
				report.globalError("Erreur lors de la création du rapport");
				ImportReportLocalServiceUtil.updateImportReport(report);
				continue;
			}

			// On récupère et on parse le fichier
			try {
				byte[] encoded = Files.readAllBytes(Paths.get(file.getPath()));
				String jsonString = new String(encoded,
					Charset.defaultCharset());
				_log.info("Import file: " + file.getPath());
				json = JSONFactoryUtil.createJSONObject(jsonString);
			} catch (IOException e) { // Erreur du fichier
				report.globalError(
					"Pas de fichier à l'emplacement : " + file.getPath());
				ImportReportLocalServiceUtil.updateImportReport(report);
				continue;
			} catch (JSONException e) { // Erreur de parse du JSON
				report.globalError("Format JSON invalide");
				ImportReportLocalServiceUtil.updateImportReport(report);
				continue;
			}
			// On déplace immédiatement le fichier dans les archives
			String provider = json.getString("provider");
			this.moveFile(file, provider, fileIndex, directory);
			fileIndex++;

			// Tout d'abord on check la présence des champs "provider",
			// "manifestations" et "events"
			JSONArray manifestations = json.getJSONArray("manifestations");
			JSONArray events = json.getJSONArray("events");
			boolean rootFieldMissing = false;
			String rootFieldMissingError = "";
			if (provider == null) {
				rootFieldMissingError += "le champ 'provider' est manquant";
				rootFieldMissing = true;
			} else {
				report.setProvider(provider);
			}
			if (manifestations == null) {
				if (rootFieldMissingError.length() > 0) {
					rootFieldMissingError += " - ";
				}
				rootFieldMissingError += "le champ 'manifestations' est manquant";
				rootFieldMissing = true;
			}
			if (events == null) {
				if (rootFieldMissingError.length() > 0) {
					rootFieldMissingError += " - ";
				}
				rootFieldMissingError += "le champ 'events' est manquant";
				rootFieldMissing = true;
			}
			if (rootFieldMissing) {
				report.globalError(rootFieldMissingError);
				ImportReportLocalServiceUtil.updateImportReport(report);
				continue;
			}

			// On vérifie que le provider est autorisé
			String allowedProviders = PropsUtil
				.get("eu.strasbourg.agenda.providers");
			String campaignNames = "";
			for (Campaign campaign : CampaignLocalServiceUtil.getCampaigns(-1,
				-1)) {
				if (campaignNames.length() > 0) {
					campaignNames += ",";
				}
				campaignNames += FriendlyURLNormalizerUtil
					.normalize(campaign.getTitleCurrentValue());
			}
			if (!ArrayUtil.contains(allowedProviders.split(","), provider, true)
				&& !ArrayUtil.contains(campaignNames.split(","), provider,
					true)) {
				report.globalError(
					"Le provider '" + provider + "' n'est pas autorisé");
				ImportReportLocalServiceUtil.updateImportReport(report);
				continue;
			}

			// On s'occupe des manifestations
			for (int i = 0; i < manifestations.length(); i++) {
				_log.info("Import manifestation: " + (i + 1) + "/"
					+ manifestations.length());

				JSONObject jsonManifestation = manifestations.getJSONObject(i);
				ImportReportLine line = this
					.importManifestation(jsonManifestation, provider);
				if (line == null
					|| line.getStatus() == ImportReportLineStatus.FAILURE) {
					report.incrementErrorManifestations();
					report.setStatus(ImportReportStatus.SUCCESS_WITH_ERRORS);
				} else if (line
					.getStatus() == ImportReportLineStatus.SUCCESS_ADD) {
					report.incrementNewManifestations();
				} else {
					report.incrementModifiedManifestations();
				}
				line.setReportId(report.getReportId());
				ImportReportLineLocalServiceUtil.updateImportReportLine(line);
			}

			// On passe maintenant aux événements !
			for (int i = 0; i < events.length(); i++) {
				_log.info("Import event: " + (i + 1) + "/" + events.length());
				JSONObject jsonEvent = events.getJSONObject(i);
				ImportReportLine line = this.importEvent(jsonEvent, provider);
				if (line == null
					|| line.getStatus() == ImportReportLineStatus.FAILURE) {
					report.incrementErrorEvents();
					report.setStatus(ImportReportStatus.SUCCESS_WITH_ERRORS);
				} else if (line
					.getStatus() == ImportReportLineStatus.SUCCESS_ADD) {
					report.incrementNewEvents();
				} else {
					report.incrementModifiedEvents();
				}
				line.setReportId(report.getReportId());
				ImportReportLineLocalServiceUtil.updateImportReportLine(line);
			}

			report.setEndDate(new Date());
			ImportReportLocalServiceUtil.updateImportReport(report);
			report.sendMail();
		}
		if (fileIndex == 0) {
			_log.info("No file to import");
		}

		// On purge les plus anciens des rapports d'import
		ImportReportLocalServiceUtil.purgeReports();
		_log.info("End import");
		return true;
	}

	private void moveFile(File file, String provider, int fileIndex,
		File directory) {
		try {
			DateFormat fileNameDateFormat = DateFormatFactoryUtil
				.getSimpleDateFormat("yyyy-MM-dd hh-mm");
			String suffix = Validator.isNotNull(provider) ? provider
				: String.valueOf(fileIndex);
			Files.move(Paths.get(file.getPath()),
				Paths.get(directory.getPath() + "/archives/"
					+ fileNameDateFormat.format(new Date()) + "." + suffix
					+ ".json"),
				StandardCopyOption.REPLACE_EXISTING);
			fileIndex++;
		} catch (IOException e) {
			_log.error(e);
		}
	}

	private ImportReportLine importManifestation(JSONObject jsonManifestation,
		String provider) {
		// On initialise la ligne d'import
		ImportReportLine reportLine;
		try {
			reportLine = ImportReportLineLocalServiceUtil
				.createImportReportLine();
		} catch (PortalException e) {
			_log.error(e);
			return null;
		}
		reportLine.setType(Manifestation.class.getName());
		// Vérification des champs obligatoires
		// Tout d'abord les champs non multilingues
		String id = jsonManifestation.getString("externalId");
		if (Validator.isNull(id)) {
			reportLine.error("Une manifestation n'a pas d'id");
			ImportReportLineLocalServiceUtil.updateImportReportLine(reportLine);
			return reportLine;
		}
		_log.info("externalId: " + id);
		reportLine.setEntityExternalId(id);
		String imageURL = jsonManifestation.getString("imageURL");
		if (Validator.isNull(imageURL)) {
			reportLine.error("pas d'image");
		}
		String imageCopyright = jsonManifestation.getString("imageCopyright");
		if (Validator.isNull(imageCopyright)) {
			reportLine.error("pas de copyright d'image");
		}

		// Validation des champs multilingues
		List<Locale> locales = JSONHelper
			.getLocalesUsedInJSON(jsonManifestation);
		JSONObject jsonTitle = jsonManifestation.getJSONObject("title");
		if (!JSONHelper.validateI18nField(jsonTitle, locales)) {
			reportLine.error("pas de titre ou langue manquante");
		} else {
			reportLine.setEntityName(
				jsonTitle.getString("fr_FR", "[no-french-title]"));
		}
		JSONObject jsonDescription = jsonManifestation
			.getJSONObject("description");
		if (!JSONHelper.validateI18nField(jsonDescription, locales)) {
			reportLine.error("pas de description ou langue manquante");
		}

		// Validation du format des dates
		String startDateString = jsonManifestation.getString("startDate");
		Date startDate = null;
		if (startDateString != null) {
			try {
				startDate = dateFormat.parse(startDateString);
				if (startDate == null) {
					throw new Exception();
				}
			} catch (Exception e) {
				reportLine.error("le format de la date de début n'est pas bon");
			}
		}
		String endDateString = jsonManifestation.getString("endDate");
		Date endDate = null;
		if (endDateString != null) {
			try {
				endDate = dateFormat.parse(endDateString);
				if (endDate == null) {
					throw new Exception();
				}
			} catch (Exception e) {
				reportLine.error("le format de la date de fin n'est pas bon");
			}
		}
		if (startDate != null && endDate != null && startDate.after(endDate)) {
			reportLine.error("la date de fin est avant la date de début");
		}

		// Validation des catégories
		Map<AssetVocabulary, Boolean> vocabulariesValidationMap = new HashMap<AssetVocabulary, Boolean>();
		for (AssetVocabulary vocabulary : manifestationRequiredVocabularies) {
			vocabulariesValidationMap.put(vocabulary, false);
		}
		List<Long> categoriesIds = JSONHelper.getCategoriesIdsFromFields(
			jsonManifestation, "themes", "types", "publics", "services");
		for (Long categoryId : categoriesIds) {
			AssetCategory category = AssetCategoryLocalServiceUtil
				.fetchAssetCategory(categoryId);
			if (category == null) {
				reportLine.error("la catégorie ayant pour id " + categoryId
					+ " n'existe pas");
				continue;
			}
			boolean isFromValidVocabulary = manifestationVocabularies.stream()
				.anyMatch(
					v -> v.getVocabularyId() == category.getVocabularyId());
			if (!isFromValidVocabulary) {
				reportLine.error("la catégorie '" + category.getName() + "' ("
					+ category.getCategoryId()
					+ ") appartient à un mauvais vocabulaire");
			}
			for (Map.Entry<AssetVocabulary, Boolean> entry : vocabulariesValidationMap
				.entrySet()) {
				if (entry.getKey().getVocabularyId() == category
					.getVocabularyId()) {
					entry.setValue(true);
				}
			}
		}
		for (Map.Entry<AssetVocabulary, Boolean> entry : vocabulariesValidationMap
			.entrySet()) {
			if (entry.getValue() == false) {
				reportLine.error("pas de valeur pour le vocabulaire "
					+ entry.getKey().getName());
			}
		}

		// Ajout ou update
		if (!reportLine.hasError()) {
			// On initialise un ServiceContext
			ServiceContext sc = new ServiceContext();
			sc.setUserId(defaultUserId);
			sc.setCompanyId(companyId);
			sc.setScopeGroupId(this.globalGroupId);
			sc.setAssetCategoryIds(ArrayUtil.toLongArray(categoriesIds));
			sc.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);
			sc.setModifiedDate(new Date());

			// On essaye de récupérer la manifestation existante
			Manifestation manifestation = ManifestationLocalServiceUtil
				.findBySourceAndIdSource(provider, id);

			// Si elle n'existe pas on la crée
			if (manifestation == null) {
				try {
					manifestation = ManifestationLocalServiceUtil
						.createManifestation(sc);
					reportLine.setEntityId(manifestation.getManifestationId());
				} catch (PortalException e) {
					reportLine.error(
						"erreur lors de la création de la manifestation");
					return reportLine;
				}
			}
			// Si elle existe on récupère les tags
			else {
				reportLine.setStatus(ImportReportLineStatus.SUCCESS_MODIFIED);
				sc.setAssetTagNames(
					manifestation.getAssetEntry().getTagNames());
			}

			// On set les champs obligatoires
			manifestation.setIdSource(id);
			manifestation.setSource(provider);
			manifestation.setExternalImageURL(imageURL);
			manifestation.setExternalImageCopyright(imageCopyright);
			manifestation.setImageId((long) 0);
			manifestation.setPublicationDate(new Date());

			// Puis les dates
			if (startDate != null && endDate != null) {
				manifestation.setStartDate(startDate);
				manifestation.setEndDate(endDate);
			}

			// Et enfin les champs multilingues
			for (Locale locale : locales) {
				manifestation.setTitle(jsonTitle.getString(locale.toString()),
					locale);
				manifestation.setDescription(
					jsonDescription.getString(locale.toString()), locale);
			}

			// Et enfin on enregistre la manifestation
			try {
				ManifestationLocalServiceUtil.updateManifestation(manifestation,
					sc);
			} catch (PortalException e) {
				reportLine.error(
					"erreur lors de l'enregistrement de la manifestation");
			}

		}

		return reportLine;
	}

	private ImportReportLine importEvent(JSONObject jsonEvent,
		String provider) {
		ImportReportLine reportLine;
		try {
			reportLine = ImportReportLineLocalServiceUtil
				.createImportReportLine();
		} catch (PortalException e) {
			_log.error(e);
			return null;
		}
		reportLine.setType(Event.class.getName());

		// Vérification des champs obligatoires
		// Tout d'abord les champs non multilingues
		String id = jsonEvent.getString("externalId");
		if (Validator.isNull(id)) {
			reportLine.error("un event n'a pas d'id");
			ImportReportLineLocalServiceUtil.updateImportReportLine(reportLine);
			return reportLine;
		}
		_log.info("externalId: " + id);
		reportLine.setEntityExternalId(id);
		String imageURL = jsonEvent.getString("imageURL");
		if (Validator.isNull(imageURL)) {
			reportLine.error("pas d'image");
		}
		String imageCopyright = jsonEvent.getString("imageCopyright");
		if (Validator.isNull(imageCopyright)) {
			reportLine.error("pas de copyright d'image");
		}
		String placeSIGId = jsonEvent.getString("placeSIGId");
		boolean isManualPlace = false;
		if (Validator.isNull(placeSIGId)) {
			JSONObject jsonPlace = jsonEvent.getJSONObject("place");
			if (jsonPlace == null) {
				reportLine.error("lieu manquant");
			} else {
				isManualPlace = true;
				if (Validator.isNull(jsonPlace.getString("city"))) {
					reportLine.error("ville du lieu manquante");
				}
			}
		}
		int freeEntry = jsonEvent.getInt("freeEntry", -1);
		if (freeEntry < 0 || freeEntry > 2) {
			reportLine
				.error("la gratuité doit être 0, 1 ou 2 (non communiquée)");
		}

		// Validation des champs multilingues
		List<Locale> locales = JSONHelper.getLocalesUsedInJSON(jsonEvent);
		JSONObject jsonTitle = jsonEvent.getJSONObject("title");
		if (!JSONHelper.validateI18nField(jsonTitle, locales)) {
			reportLine.error("pas de titre ou langue manquante");
		} else {
			reportLine.setEntityName(
				jsonTitle.getString("fr_FR", "[no-french-title]"));
		}
		JSONObject jsonDescription = jsonEvent.getJSONObject("description");
		if (!JSONHelper.validateI18nField(jsonDescription, locales)) {
			reportLine.error("pas de description ou langue manquante");
		}
		if (isManualPlace) {
			JSONObject jsonPlace = jsonEvent.getJSONObject("place");
			if (jsonPlace != null) {
				JSONObject jsonPlaceName = jsonPlace.getJSONObject("name");
				if (!JSONHelper.validateI18nField(jsonPlaceName, locales)) {
					reportLine.error("pas de nom de lieu ou langue manquante");
				}
			}
		}

		// Validation des périodes
		JSONArray jsonPeriods = jsonEvent.getJSONArray("periods");
		if (jsonPeriods == null || jsonPeriods.length() == 0) {
			reportLine.error("pas de périodes");
		}
		// On en profite pour créer directement nos objets "Period"
		List<EventPeriod> periods = new ArrayList<EventPeriod>();
		for (int j = 0; j < jsonPeriods.length(); j++) {
			JSONObject jsonPeriod = jsonPeriods.getJSONObject(j);
			String startDateString = jsonPeriod.getString("startDate");
			String endDateString = jsonPeriod.getString("endDate");
			JSONObject jsonTimeDetail = jsonPeriod.getJSONObject("timeDetail");
			if (!JSONHelper.validateI18nField(jsonTimeDetail, locales)) {
				reportLine.error("une période n'a pas d'horaires");
			}
			Date startDate = null;
			Date endDate = null;
			if (startDateString != null && endDateString != null) {
				try {
					startDate = dateFormat.parse(startDateString);
					if (startDate == null) {
						throw new Exception();
					}
				} catch (Exception e) {
					reportLine.error(
						"le format de la date de début d'une période n'est pas bon");
				}
				try {
					endDate = dateFormat.parse(endDateString);
					if (endDate == null) {
						throw new Exception();
					}
				} catch (Exception e) {
					reportLine.error(
						"le format de la date de fin d'une période n'est pas bon");
				}
				if (startDate != null && endDate != null) {
					if (startDate.after(endDate)) {
						reportLine.error(
							"une période a une date de début ultérieure à sa date de fin");
					} else if (!reportLine.hasError()) {
						EventPeriod period;
						try {
							period = EventPeriodLocalServiceUtil
								.createEventPeriod();
							period.setStartDate(startDate);
							period.setEndDate(endDate);
							for (Locale locale : locales) {
								period.setTimeDetail(
									jsonTimeDetail.getString(locale.toString()),
									locale);
							}
							periods.add(period);
						} catch (PortalException e) {
							reportLine.error(
								"erreur lors de la création d'une période");
						}
						if (EventPeriodLocalServiceUtil
							.checkForOverlappingPeriods(periods)) {
							reportLine.error(
								"des périodes ont des dates qui se chevauchent");
						}
					}
				}
			}
		}

		// Validation du lien avec les manifestations
		JSONArray jsonManifestations = jsonEvent.getJSONArray("manifestations");
		if (jsonManifestations != null) {
			for (int j = 0; j < jsonManifestations.length(); j++) {
				String manifestationId = jsonManifestations.getString(j);
				Manifestation manifestation = ManifestationLocalServiceUtil
					.findByIdSource(manifestationId);
				if (manifestation == null) {
					reportLine.error("une des manifestation n'existe pas");
				}
			}
		}

		// Validation des catégories
		Map<AssetVocabulary, Boolean> vocabulariesValidationMap = new HashMap<AssetVocabulary, Boolean>();
		for (AssetVocabulary vocabulary : eventRequiredVocabularies) {
			vocabulariesValidationMap.put(vocabulary, false);
		}
		List<Long> categoriesIds = JSONHelper.getCategoriesIdsFromFields(
			jsonEvent, "themes", "types", "publics", "territories", "services");
		for (Long categoryId : categoriesIds) {
			AssetCategory category = AssetCategoryLocalServiceUtil
				.fetchAssetCategory(categoryId);
			if (category == null) {
				reportLine.error("la catégorie ayant pour id " + categoryId
					+ " n'existe pas");
				continue;
			}
			boolean isFromValidVocabulary = eventVocabularies.stream().anyMatch(
				v -> v.getVocabularyId() == category.getVocabularyId());
			if (!isFromValidVocabulary) {
				reportLine.error("la catégorie '" + category.getName() + "' ("
					+ category.getCategoryId()
					+ ") appartient à un mauvais vocabulaire");
			}
			for (Map.Entry<AssetVocabulary, Boolean> entry : vocabulariesValidationMap
				.entrySet()) {
				if (entry.getKey().getVocabularyId() == category
					.getVocabularyId()) {
					entry.setValue(true);
				}
			}
		}
		for (Map.Entry<AssetVocabulary, Boolean> entry : vocabulariesValidationMap
			.entrySet()) {
			if (entry.getValue() == false) {
				reportLine.error("pas de valeur pour le vocabulaire "
					+ entry.getKey().getName());
			}
		}

		// Ajout ou update
		if (!reportLine.hasError()) {
			// On initialise un ServiceContext
			ServiceContext sc = new ServiceContext();
			sc.setUserId(defaultUserId);
			sc.setCompanyId(companyId);
			sc.setScopeGroupId(this.globalGroupId);
			sc.setAssetCategoryIds(ArrayUtil.toLongArray(categoriesIds));
			sc.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);
			sc.setModifiedDate(new Date());

			// On essaye de récupérer l'événement existant
			Event event = EventLocalServiceUtil
				.findBySourceAndIdSource(provider, id);

			// Si il n'existe pas on la crée
			if (event == null) {
				try {
					event = EventLocalServiceUtil.createEvent(sc);
				} catch (PortalException e) {
					reportLine.error("erreur lors de la création de l'event");
					ImportReportLineLocalServiceUtil
						.updateImportReportLine(reportLine);
					return reportLine;
				}
			}
			// Si il existe on récupère les tags
			else {
				reportLine.setStatus(ImportReportLineStatus.SUCCESS_MODIFIED);
				sc.setAssetTagNames(event.getAssetEntry().getTagNames());
			}

			// On set les champs
			event.setIdSource(id);
			event.setSource(provider);
			event.setExternalImageURL(imageURL);
			event.setExternalImageCopyright(imageCopyright);
			event.setImageId((long) 0);
			event.setPublicationDate(new Date());
			event.setPromoter(jsonEvent.getString("promoter"));
			event.setPhone(jsonEvent.getString("phone"));
			event.setEmail(jsonEvent.getString("mail"));
			event.setFree(freeEntry);

			// Lieu
			if (Validator.isNotNull(placeSIGId)) {
				event.setPlaceSIGId(placeSIGId);
				event.setAccessForBlind(false);
				event.setAccessForDeaf(false);
				event.setAccessForWheelchair(false);
				event.setAccessForElder(false);
				event.setAccessForDeficient(false);
			} else {
				JSONObject jsonPlace = jsonEvent.getJSONObject("place");
				event.setPlaceStreetNumber(jsonPlace.getString("streetNumber"));
				event.setPlaceStreetName(jsonPlace.getString("streetName"));
				event.setPlaceCity(jsonPlace.getString("city"));
				event.setPlaceCountry(jsonPlace.getString("country"));
				event.setPlaceZipCode(jsonPlace.getString("zipCode"));

				JSONObject jsonPlaceName = jsonPlace.getJSONObject("name");
				JSONObject jsonPlaceAccess = jsonPlace.getJSONObject("access");
				JSONObject jsonPlaceAccessForDisabled = jsonPlace
					.getJSONObject("accessForDisabled");
				for (Locale locale : locales) {
					if (jsonPlaceName != null) {
						String placeName = jsonPlaceName
							.getString(locale.toString());
						if (Validator.isNotNull(placeName)) {
							event.setPlaceName(placeName, locale);
						}
					}
					if (jsonPlaceAccess != null) {
						String placeAccess = jsonPlaceAccess
							.getString(locale.toString());
						if (Validator.isNotNull(placeAccess)) {
							event.setAccess(placeAccess, locale);
						}
					}
					if (jsonPlaceAccessForDisabled != null) {

						String placeAccessForDisabled = jsonPlaceAccessForDisabled
							.getString(locale.toString());
						if (Validator.isNotNull(placeAccessForDisabled)) {
							event.setAccessForDisabled(placeAccessForDisabled,
								locale);
						}
					}
				}
				event.setAccessForBlind(jsonPlace.getBoolean("accessForBlind"));
				event.setAccessForDeaf(jsonPlace.getBoolean("accessForDeaf"));
				event.setAccessForWheelchair(
					jsonPlace.getBoolean("accessForWheelchair"));
				event.setAccessForElder(jsonPlace.getBoolean("accessForElder"));
				event.setAccessForDeficient(
					jsonPlace.getBoolean("accessForDeficient"));
			}

			// Les champs multilingues
			JSONObject jsonSubtitle = jsonEvent.getJSONObject("subtitle");
			JSONObject jsonWebsiteURL = jsonEvent.getJSONObject("websiteURL");
			JSONObject jsonWebsiteName = jsonEvent.getJSONObject("websiteName");
			JSONObject jsonPrice = jsonEvent.getJSONObject("price");

			for (Locale locale : locales) {
				event.setTitle(jsonTitle.getString(locale.toString()), locale);
				event.setDescription(
					jsonDescription.getString(locale.toString()), locale);

				if (Validator.isNotNull(jsonSubtitle)) {
					String subtitle = jsonSubtitle.getString(locale.toString());
					if (Validator.isNotNull(subtitle)) {
						event.setSubtitle(subtitle, locale);
					}
				}

				if (Validator.isNotNull(jsonWebsiteURL)) {
					String websiteURL = jsonWebsiteURL
						.getString(locale.toString());
					if (Validator.isNotNull(websiteURL)) {
						event.setWebsiteURL(websiteURL, locale);
					}
				}
				if (Validator.isNotNull(jsonWebsiteName)) {
					String websiteName = jsonWebsiteName
						.getString(locale.toString());
					if (Validator.isNotNull(websiteName)) {
						event.setWebsiteName(websiteName, locale);
					}
				}
				if (Validator.isNotNull(jsonPrice)) {
					String price = jsonPrice.getString(locale.toString());
					if (Validator.isNotNull(price)) {
						event.setWebsiteName(price, locale);
					}
				}

			}

			// On enregistre l'événement
			try {
				EventLocalServiceUtil.updateEvent(event, sc);
			} catch (PortalException e) {
				reportLine.error("erreur lors de l'enregistrement de l'event");
			}

			// On enregistre le lien avec les manifestations
			List<Manifestation> oldManifestations = event.getManifestations();
			for (Manifestation manifestation : oldManifestations) {
				EventLocalServiceUtil.deleteManifestationEvent(
					manifestation.getManifestationId(), event);
			}
			if (jsonManifestations != null) {
				for (int j = 0; j < jsonManifestations.length(); j++) {
					String manifestationExternalId = jsonManifestations
						.getString(j);
					Manifestation manifestation = ManifestationLocalServiceUtil
						.findByIdSource(manifestationExternalId);
					if (Validator.isNotNull(manifestation)) {
						EventLocalServiceUtil.addManifestationEvent(
							manifestation.getManifestationId(), event);
					}
				}
			}

			// Et le lien avec les périodes
			List<EventPeriod> oldPeriods = event.getEventPeriods();
			for (EventPeriod eventPeriod : oldPeriods) {
				EventPeriodLocalServiceUtil.deleteEventPeriod(eventPeriod);
			}
			for (EventPeriod period : periods) {
				period.setEventId(event.getEventId());
				EventPeriodLocalServiceUtil.updateEventPeriod(period);
			}
		}

		return reportLine;
	}

}
