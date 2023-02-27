package eu.strasbourg.service.agenda.utils;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
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
import eu.strasbourg.service.opendata.geo.address.impl.OpenDataGeoAddressServiceImpl;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.MailHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.constants.VocabularyNames;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AgendaImporter {

	private final Log _log = LogFactoryUtil.getLog(this.getClass());

	private DateFormat dateFormat;
	private DateFormat dateTimeFormat;
	private long defaultUserId;
	private long companyId;
	private long globalGroupId;
	private List<AssetVocabulary> manifestationRequiredVocabularies;
	private List<AssetVocabulary> manifestationVocabularies;
	private List<AssetVocabulary> eventRequiredVocabularies;
	private List<AssetVocabulary> eventVocabularies;
	private ResourceBundle bundle = ResourceBundleUtil
		.getBundle("content.ImportErrors", this.getClass().getClassLoader());

	private OpenDataGeoAddressServiceImpl openDataGeoAddressService = new OpenDataGeoAddressServiceImpl();

	public AgendaImporter() {
		try {
			Company defaultCompany = CompanyLocalServiceUtil
				.getCompanyByWebId("liferay.com");
			this.companyId = defaultCompany.getCompanyId();
			this.globalGroupId = defaultCompany.getGroup().getGroupId();
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
		this.dateTimeFormat = DateFormatFactoryUtil
				.getSimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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

	public String getJSON(String url, int timeout) {
		HttpURLConnection c = null;
		try {
			URL u = new URL(url);
			c = (HttpURLConnection) u.openConnection();
			c.setRequestMethod("GET");
			c.setRequestProperty("Content-length", "0");
			c.setUseCaches(false);
			c.setAllowUserInteraction(false);
			c.setConnectTimeout(timeout);
			c.setReadTimeout(timeout);
			c.connect();
			int status = c.getResponseCode();

			switch (status) {
				case 200:
				case 201:
					BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
					StringBuilder sb = new StringBuilder();
					String line;
					while ((line = br.readLine()) != null) {
						sb.append(line+"\n");
					}
					br.close();
					return sb.toString();
			}

		} catch (MalformedURLException ex) {
			_log.error(ex.getMessage());
		} catch (IOException ex) {
			_log.error(ex.getMessage());
		} finally {
			if (c != null) {
				try {
					c.disconnect();
				} catch (Exception ex) {
					_log.error(ex.getMessage());
				}
			}
		}
		return null;
	}

	public boolean doImport() throws Exception {
		// On prépare le rapport, l'objet JSON et le répertoire qui
		// contient les fichiers à importer
		_log.info("Start import");
		ImportReport report = null;
		JSONObject json = null;
		File directory = new File(
			StrasbourgPropsUtil.getAgendaImportDirectory());

		File f = new File(Paths.get(directory.getAbsolutePath(),"coze.json").toString());
		f.createNewFile();

		String jsonCozeString = getJSON(StrasbourgPropsUtil.getUrlCozeJson(),StrasbourgPropsUtil.getWebServiceDefaultTimeout());

		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(f), "utf-8"));
			writer.write(jsonCozeString);
		} catch (IOException ex) {
			_log.error(ex.getMessage());
		} catch (Exception ex) {
			_log.error(ex.getMessage());
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
				_log.error(ex.getMessage());}
		}
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

			List<String> manifestationsImportExternalId = new ArrayList<>();
			// On s'occupe des manifestations
			for (int i = 0; i < manifestations.length(); i++) {
				_log.info("Import manifestation: " + (i + 1) + "/"
					+ manifestations.length());

				JSONObject jsonManifestation = manifestations.getJSONObject(i);
				manifestationsImportExternalId.add(jsonManifestation.getString("externalId"));
				ImportReportLine line = this
					.importManifestation(jsonManifestation, provider);
				if (line == null
					|| line.getStatus() == ImportReportLineStatus.FAILURE) {
					report.incrementErrorManifestations();
					report.setStatus(ImportReportStatus.SUCCESS_WITH_ERRORS);
				} else if (line
					.getStatus() == ImportReportLineStatus.SUCCESS_ADD) {
					report.incrementNewManifestations();
				} else if (line
						.getStatus() == ImportReportLineStatus.SUCCESS_MODIFIED){
					report.incrementModifiedManifestations();
				} else {
					report.incrementUnmodifiedManifestations();
				}
				line.setReportId(report.getReportId());
				ImportReportLineLocalServiceUtil.updateImportReportLine(line);
			}
			List<String> eventsImportExternalId = new ArrayList<>();
			// On passe maintenant aux événements !
			for (int i = 0; i < events.length(); i++) {
				_log.info("Import event: " + (i + 1) + "/" + events.length());
				JSONObject jsonEvent = events.getJSONObject(i);
				eventsImportExternalId.add(jsonEvent.getString("externalId"));
				ImportReportLine line = this.importEvent(jsonEvent, provider);
				if (line == null
					|| line.getStatus() == ImportReportLineStatus.FAILURE) {
					report.incrementErrorEvents();
					report.setStatus(ImportReportStatus.SUCCESS_WITH_ERRORS);
				} else if (line
					.getStatus() == ImportReportLineStatus.SUCCESS_ADD) {
					report.incrementNewEvents();
				} else if (line
						.getStatus() == ImportReportLineStatus.SUCCESS_MODIFIED) {
					report.incrementModifiedEvents();
				} else {
					report.incrementUnmodifiedEvents();
				}
				line.setReportId(report.getReportId());
				ImportReportLineLocalServiceUtil.updateImportReportLine(line);
			}

			List<Manifestation> manifestationsFromProvider = ManifestationLocalServiceUtil.getManifestations(-1,-1).stream().filter(m -> m.getSource().equals(provider)).distinct().collect(Collectors.toList());
			int j = 0;
			for(Manifestation manifestationFromProvider : manifestationsFromProvider){
				if(!manifestationsImportExternalId.contains(manifestationFromProvider.getIdSource())){
					j += 1;
					_log.info("Delete Manifestation: " + j + "/" + manifestationsFromProvider.size() + " id :" + manifestationFromProvider.getIdSource());
					ImportReportLine line;
					try {
						line = ImportReportLineLocalServiceUtil
								.createImportReportLine();
						line.setEntityExternalId(manifestationFromProvider.getIdSource());
						line.setEntityName(manifestationFromProvider.getTitle(Locale.FRANCE));
						line.setEntityId(manifestationFromProvider.getManifestationId());
						line.setType(Manifestation.class.getName());
						line.setStatus(ImportReportLineStatus.DELETED);
						line.setReportId(report.getReportId());
						ImportReportLineLocalServiceUtil.updateImportReportLine(line);
						ManifestationLocalServiceUtil.removeManifestation(manifestationFromProvider.getManifestationId());
						report.incrementDeletedManifestations();
					} catch (PortalException e) {
						_log.error(e);
					}
				}
			}

			List<Event> eventsFromProvider = EventLocalServiceUtil.getEvents(-1,-1).stream().filter(e -> e.getSource().equals(provider)).distinct().collect(Collectors.toList());
			int i = 0;
			for(Event eventFromProvider : eventsFromProvider){
				if(!eventsImportExternalId.contains(eventFromProvider.getIdSource())){
					i += 1;
					_log.info("Delete event: " + i + "/" + eventsFromProvider.size() + " id :" + eventFromProvider.getIdSource());
					ImportReportLine line;
					try {
						line = ImportReportLineLocalServiceUtil
								.createImportReportLine();
						line.setEntityExternalId(eventFromProvider.getIdSource());
						line.setEntityName(eventFromProvider.getTitle(Locale.FRANCE));
						line.setType(Event.class.getName());
						line.setStatus(ImportReportLineStatus.DELETED);
						line.setReportId(report.getReportId());
						ImportReportLineLocalServiceUtil.updateImportReportLine(line);
						EventLocalServiceUtil.removeEvent(eventFromProvider.getEventId());
						report.incrementDeletedEvents();
					} catch (PortalException e) {
						_log.error(e);
					}
				}
			}

			report.setEndDate(new Date());
			ImportReportLocalServiceUtil.updateImportReport(report);
			report.sendMail();
		}
		if (fileIndex == 0) {
			_log.info("No file to import");
			String environment = StrasbourgPropsUtil.getEnvironment();
			String from = "no-reply@no-reply.strasbourg.eu";
			String to = StrasbourgPropsUtil.getAgendaImportMails();
			String subject = "[" + environment + "] " + LanguageUtil.get(bundle, "no-file");
			String body = LanguageUtil.get(bundle, "no-file-text");
			MailHelper.sendMailWithPlainText(from, to, subject, body);
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
			reportLine.error(LanguageUtil.get(bundle, "no-manifestation-id"));
			ImportReportLineLocalServiceUtil.updateImportReportLine(reportLine);
			return reportLine;
		}
		_log.info("externalId: " + id);
		reportLine.setEntityExternalId(id);

		String createDateSourceString = jsonManifestation.getString("creation_date");
		Date createDateSource = null;
		if (createDateSourceString != null) {
			try {
				createDateSource = dateTimeFormat.parse(createDateSourceString);
				if (createDateSource == null) {
					throw new Exception();
				}
			} catch (Exception e) {
				reportLine.error(
						LanguageUtil.get(Locale.FRANCE, "wrong-creation-date-format"));
			}
		}else{
			reportLine.error(LanguageUtil.get(bundle, "no-creation-date"));
			ImportReportLineLocalServiceUtil.updateImportReportLine(reportLine);
			return reportLine;
		}

		String modifiedDateSourceString = jsonManifestation.getString("modification_date");
		Date modifiedDateSource = null;
		if (modifiedDateSourceString != null) {
			try {
				modifiedDateSource = dateTimeFormat.parse(modifiedDateSourceString);
				if (modifiedDateSource == null) {
					throw new Exception();
				}
			} catch (Exception e) {
				reportLine.error(
						LanguageUtil.get(Locale.FRANCE, "wrong-modification-date-format"));
			}
		}else{
			reportLine.error(LanguageUtil.get(bundle, "no-modification-date"));
			ImportReportLineLocalServiceUtil.updateImportReportLine(reportLine);
			return reportLine;
		}
		String imageURL = jsonManifestation.getString("imageURL");
		if (Validator.isNull(imageURL)) {
			reportLine.error(LanguageUtil.get(bundle, "no-image"));
		}
		String imageCopyright = jsonManifestation.getString("imageCopyright");
		if (Validator.isNull(imageCopyright)) {
			reportLine.error(LanguageUtil.get(bundle, "no-copyright"));
		}

		// Validation des champs multilingues
		List<Locale> locales = JSONHelper
			.getLocalesUsedInJSON(jsonManifestation);
		if(jsonManifestation.getString("title") != null){
			for (char c : jsonManifestation.getString("title").toCharArray()) {
				if((int) c > 5000){
					reportLine.error(LanguageUtil.format(bundle,
							"error-forbidden-char","title"));
					break;
				}
			}
		}
		JSONObject jsonTitle = jsonManifestation.getJSONObject("title");
		if (!JSONHelper.validateI18nField(jsonTitle, locales)) {
			reportLine.error(LanguageUtil.get(bundle, "no-title"));
		} else {
			String title = jsonTitle.getString("fr_FR", "[no-french-title]");
			reportLine.setEntityName(
				title.substring(0, title.length() > 200 ? 200 : title.length() - 1));
		}
		if(jsonManifestation.getString("description") != null){
			for (char c : jsonManifestation.getString("description").toCharArray()) {
				if((int) c > 5000){
					reportLine.error(LanguageUtil.format(bundle,
							"error-forbidden-char","description"));
					break;
				}
			}
		}
		JSONObject jsonDescription = jsonManifestation
			.getJSONObject("description");
		if (!JSONHelper.validateI18nField(jsonDescription, locales)) {
			reportLine.error(LanguageUtil.get(bundle, "no-description"));
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
				reportLine.error(
					LanguageUtil.get(Locale.FRANCE, "wrong-start-date-format"));
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
				reportLine.error(
					LanguageUtil.get(Locale.FRANCE, "wrong-end-date-format"));
			}
		}
		if (startDate != null && endDate != null && startDate.after(endDate)) {
			reportLine.error(
				LanguageUtil.get(Locale.FRANCE, "end-date-before-start-date"));
		}

		// Validation des catégories
		Map<AssetVocabulary, Boolean> vocabulariesValidationMap = new HashMap<AssetVocabulary, Boolean>();
		for (AssetVocabulary vocabulary : manifestationRequiredVocabularies) {
			vocabulariesValidationMap.put(vocabulary, false);
		}
		List<AssetCategory> categories = getCategoriesIdsFromFields(reportLine,
			jsonManifestation, "themes", "types", "publics", "services");
		for (AssetCategory category : categories) {
			boolean isFromValidVocabulary = manifestationVocabularies.stream()
				.anyMatch(
					v -> v.getVocabularyId() == category.getVocabularyId());
			if (!isFromValidVocabulary) {
				String wrongVocabularyError = LanguageUtil.format(bundle,
					"category-belongs-to-wrong-vocabulary",
					new String[] { category.getName(),
						String.valueOf(category.getCategoryId()) });
				reportLine.error(wrongVocabularyError);
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
				reportLine.error(
					LanguageUtil.format(bundle, "no-category-for-vocabulary",
						new String[] { entry.getKey().getName() }));
			}
		}

		// Ajout ou update
		if (!reportLine.hasError()) {
			// On initialise un ServiceContext
			ServiceContext sc = new ServiceContext();
			sc.setUserId(defaultUserId);
			sc.setCompanyId(companyId);
			sc.setScopeGroupId(this.globalGroupId);
			long[] categoriesIds = categories.stream()
				.mapToLong(AssetCategory::getCategoryId).toArray();
			sc.setAssetCategoryIds(categoriesIds);
			sc.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);
			sc.setModifiedDate(new Date());

			// On essaye de récupérer la manifestation existante
			Manifestation manifestation = ManifestationLocalServiceUtil
				.findBySourceAndIdSource(provider, id);

			// Si elle n'existe pas on la crée
			boolean isCreated = false;
			if (manifestation == null) {
				try {
					manifestation = ManifestationLocalServiceUtil
						.createManifestation(sc);
					reportLine.setEntityId(manifestation.getManifestationId());
					isCreated = true;
				} catch (PortalException e) {
					reportLine.error(LanguageUtil.get(bundle,
						"error-while-creating-manifestation"));
					return reportLine;
				}
			}
			// Si elle existe on récupère les tags
			else {
				sc.setAssetTagNames(
					manifestation.getAssetEntry().getTagNames());
			}

			if(manifestation.getModifiedDateSource().compareTo(modifiedDateSource) != 0) {
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

				// Puis les dates de la source
				manifestation.setCreateDateSource(createDateSource);
				manifestation.setModifiedDateSource(modifiedDateSource);

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
					if(isCreated)
						reportLine.setStatus(ImportReportLineStatus.SUCCESS_ADD);
					else
						reportLine.setStatus(ImportReportLineStatus.SUCCESS_MODIFIED);
				} catch (PortalException e) {
					reportLine.error(LanguageUtil.get(bundle,
							"error-while-saving-manifestation"));
				}
			}

		}

		return reportLine;
	}

	private ImportReportLine importEvent(JSONObject jsonEvent,
		String provider) throws Exception {
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
			reportLine.error(LanguageUtil.get(bundle, "no-event-id"));
			ImportReportLineLocalServiceUtil.updateImportReportLine(reportLine);
			return reportLine;
		}
		_log.info("externalId: " + id);
		reportLine.setEntityExternalId(id);

		String createDateSourceString = jsonEvent.getString("creation_date");
		Date createDateSource = null;
		if (Validator.isNotNull(createDateSourceString)) {
			try {
				createDateSource = dateTimeFormat.parse(createDateSourceString);
				if (createDateSource == null) {
					throw new Exception();
				}
			} catch (Exception e) {
				reportLine.error(
						LanguageUtil.get(Locale.FRANCE, "wrong-creation-date-format"));
			}
		}else{
			reportLine.error(LanguageUtil.get(bundle, "no-creation-date"));
			ImportReportLineLocalServiceUtil.updateImportReportLine(reportLine);
			return reportLine;
		}

		String modifiedDateSourceString = jsonEvent.getString("modification_date");
		Date modifiedDateSource = null;
		if (Validator.isNotNull(modifiedDateSourceString)) {
			try {
				modifiedDateSource = dateTimeFormat.parse(modifiedDateSourceString);
				if (modifiedDateSource == null) {
					throw new Exception();
				}
			} catch (Exception e) {
				reportLine.error(
						LanguageUtil.get(Locale.FRANCE, "wrong-modification-date-format"));
			}
		}else{
			reportLine.error(LanguageUtil.get(bundle, "no-modification-date"));
			ImportReportLineLocalServiceUtil.updateImportReportLine(reportLine);
			return reportLine;
		}

		String imageURL = jsonEvent.getString("imageURL");
		if (Validator.isNull(imageURL)) {
			reportLine.error(LanguageUtil.get(bundle, "no-image"));
		}
		String imageCopyright = jsonEvent.getString("imageCopyright");
		if (Validator.isNull(imageCopyright)) {
			reportLine.error(LanguageUtil.get(bundle, "no-copyright"));
		}
		String placeSIGId = jsonEvent.getString("placeSIGId");
		boolean isManualPlace = false;
		if (Validator.isNull(placeSIGId)) {
			JSONObject jsonPlace = jsonEvent.getJSONObject("place");
			if (jsonPlace == null) {
				reportLine.error(LanguageUtil.get(bundle, "no-place"));
			} else {
				isManualPlace = true;
				if (Validator.isNull(jsonPlace.getString("city"))) {
					reportLine.error(LanguageUtil.get(bundle, "no-city"));
				}
			}
		} else {
			Place place = PlaceLocalServiceUtil.getPlaceBySIGId(placeSIGId);
			if (place == null) {
				reportLine.error(LanguageUtil.format(bundle,
					"sig-place-does-not-exist", new String[] { placeSIGId }));

			}
		}
		int freeEntry = jsonEvent.getInt("freeEntry", -1);
		if (freeEntry < 0 || freeEntry > 2) {
			reportLine.error(LanguageUtil.get(bundle, "wrong-free-entry"));
		}

		// Validation des champs multilingues
		List<Locale> locales = JSONHelper.getLocalesUsedInJSON(jsonEvent);
		if(jsonEvent.getString("title") != null){
			for (char c : jsonEvent.getString("title").toCharArray()) {
				if((int) c > 5000){
					reportLine.error(LanguageUtil.format(bundle,
							"error-forbidden-char","title"));
					break;
				}
			}
		}
		JSONObject jsonTitle = jsonEvent.getJSONObject("title");
		if (!JSONHelper.validateI18nField(jsonTitle, locales)) {
			reportLine.error(LanguageUtil.get(bundle, "no-title"));
		} else {
			reportLine.setEntityName(
				jsonTitle.getString("fr_FR", "[no-french-title]"));
		}
		if(jsonEvent.getString("description") != null){
			for (char c : jsonEvent.getString("description").toCharArray()) {
				if((int) c > 5000){
					reportLine.error(LanguageUtil.format(bundle,
							"error-forbidden-char","description"));
					break;
				}
			}
		}
		JSONObject jsonDescription = jsonEvent.getJSONObject("description");
		if (!JSONHelper.validateI18nField(jsonDescription, locales)) {
			reportLine.error(LanguageUtil.get(bundle, "no-description"));
		}
		if (isManualPlace) {
			JSONObject jsonPlace = jsonEvent.getJSONObject("place");
			if (jsonPlace != null) {
				if(jsonPlace.getString("name") != null){
					for (char c : jsonPlace.getString("name").toCharArray()) {
						if((int) c > 5000){
							reportLine.error(LanguageUtil.format(bundle,
									"error-forbidden-char","name"));
							break;
						}
					}
				}
				JSONObject jsonPlaceName = jsonPlace.getJSONObject("name");
				if (!JSONHelper.validateI18nField(jsonPlaceName, locales)) {
					reportLine.error(LanguageUtil.get(bundle, "no-place-name"));
				}
			}
		}

		// Validation des périodes
		JSONArray jsonPeriods = jsonEvent.getJSONArray("periods");
		if (jsonPeriods == null || jsonPeriods.length() == 0) {
			reportLine.error(LanguageUtil.get(bundle, "no-periods"));
		}
		// On en profite pour créer directement nos objets "Period"
		List<EventPeriod> periods = new ArrayList<EventPeriod>();
		for (int j = 0; j < jsonPeriods.length(); j++) {
			JSONObject jsonPeriod = jsonPeriods.getJSONObject(j);
			String startDateString = jsonPeriod.getString("startDate");
			String endDateString = jsonPeriod.getString("endDate");
			if(jsonPeriod.getString("timeDetail") != null){
				for (char c : jsonPeriod.getString("timeDetail").toCharArray()) {
					if((int) c > 5000){
						reportLine.error(LanguageUtil.format(bundle,
								"error-forbidden-char","timeDetail"));
						break;
					}
				}
			}
			JSONObject jsonTimeDetail = jsonPeriod.getJSONObject("timeDetail");
			if (!JSONHelper.validateI18nField(jsonTimeDetail, locales)) {
				reportLine
					.error(LanguageUtil.get(bundle, "period-without-schedule"));
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
					reportLine.error(LanguageUtil.get(bundle,
						"bad-period-start-date-format"));
				}
				try {
					endDate = dateFormat.parse(endDateString);
					if (endDate == null) {
						throw new Exception();
					}
				} catch (Exception e) {
					reportLine.error(
						LanguageUtil.get(bundle, "bad-period-end-date-format"));
				}
				if (startDate != null && endDate != null) {
					if (startDate.after(endDate)) {
						reportLine.error(LanguageUtil.get(bundle,
							"period-starts-after-end"));
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
							reportLine.error(LanguageUtil.get(bundle,
								"error-while-creating-period"));
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
					reportLine
						.error(LanguageUtil.get(bundle, "wrong-manifestation"));
				}
			}
		}

		// Validation des catégories
		Map<AssetVocabulary, Boolean> vocabulariesValidationMap = new HashMap<AssetVocabulary, Boolean>();
		for (AssetVocabulary vocabulary : eventRequiredVocabularies) {
			vocabulariesValidationMap.put(vocabulary, false);
		}
		// Si on a affaire à un lieu manuel, le territoire est obligatoire
		if (isManualPlace) {
			AssetVocabulary territoryVocabulary = AssetVocabularyHelper
				.getVocabulary(VocabularyNames.TERRITORY, globalGroupId);
			vocabulariesValidationMap.put(territoryVocabulary, false);
		}
		List<AssetCategory> categories = getCategoriesIdsFromFields(reportLine,
			jsonEvent, "themes", "types", "publics", "territories", "services");
		for (AssetCategory category : categories) {
			boolean isFromValidVocabulary = eventVocabularies.stream().anyMatch(
				v -> v.getVocabularyId() == category.getVocabularyId());
			if (!isFromValidVocabulary) {
				reportLine.error(LanguageUtil.format(bundle,
					"category-belongs-to-wrong-vocabulary",
					new String[] { category.getName(),
						String.valueOf(category.getCategoryId()) }));
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
				reportLine.error(
					LanguageUtil.format(bundle, "no-category-for-vocabulary",
						new String[] { entry.getKey().getName() }));
			}
		}

		// Ajout ou update
		if (!reportLine.hasError()) {
			// On initialise un ServiceContext
			ServiceContext sc = new ServiceContext();
			sc.setUserId(defaultUserId);
			sc.setCompanyId(companyId);
			sc.setScopeGroupId(this.globalGroupId);
			long[] categoriesIds = categories.stream()
				.mapToLong(AssetCategory::getCategoryId).toArray();
			sc.setAssetCategoryIds(categoriesIds);
			sc.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);
			sc.setModifiedDate(new Date());

			// On essaye de récupérer l'événement existant
			Event event = EventLocalServiceUtil
				.findBySourceAndIdSource(provider, id);

			// Si il n'existe pas on la crée
			boolean isCreated = false;
			if (event == null) {
				try {
					event = EventLocalServiceUtil.createEvent(sc);
					isCreated = true;
				} catch (PortalException e) {
					reportLine.error(
						LanguageUtil.get(bundle, "error-while-creating-event"));
					ImportReportLineLocalServiceUtil
						.updateImportReportLine(reportLine);
					return reportLine;
				}
			}
			// Si il existe on récupère les tags
			else {
				AssetEntry entry = event.getAssetEntry();
				if (entry != null) {
					sc.setAssetTagNames(event.getAssetEntry().getTagNames());
				}
			}

			if(Validator.isNull(event.getModifiedDateSource()) || event.getModifiedDateSource().compareTo(modifiedDateSource) != 0) {
				// On set les champs
				event.setIdSource(id);
				event.setSource(provider);

				// les dates de la source
				event.setCreateDateSource(createDateSource);
				event.setModifiedDateSource(modifiedDateSource);
				event.setExternalImageURL(imageURL);
				event.setExternalImageCopyright(imageCopyright);
				event.setImageId((long) 0);
				event.setPublicationDate(new Date());
				event.setPromoter(jsonEvent.getString("promoter"));
				event.setPhone(jsonEvent.getString("phone"));
				event.setEmail(jsonEvent.getString("mail"));
				event.setFree(freeEntry);
				String jsonBookingURL = jsonEvent.getString("bookingURL");
				if(Validator.isNotNull(jsonBookingURL)){
					for (char c : jsonBookingURL.toCharArray()) {
						if((int) c > 5000){
							reportLine.error(LanguageUtil.format(bundle,
									"error-forbidden-char","bookingURL"));
							break;
						}
					}
				}
				event.setBookingURL(jsonBookingURL);

				JSONObject jsonRegistration = jsonEvent.getJSONObject("registration");
				if(Validator.isNotNull(jsonRegistration)){
					event.setRegistration(true);
					event.setMaxGauge(jsonRegistration.getLong("maxGauge"));
					String registrationStartDateString = jsonRegistration.getString("startDate");
					String registrationEndDateString = jsonRegistration.getString("endDate");
					Date registrationStartDate = null;
					Date registrationEndDate = null;
					try {
						registrationStartDate = dateFormat.parse(registrationStartDateString);
						registrationEndDate = dateFormat.parse(registrationEndDateString);
					} catch (ParseException e) {
						_log.error(e.getMessage(), e);
					}
					event.setRegistrationStartDate(registrationStartDate);
					event.setRegistrationEndDate(registrationEndDate);
				}
				else{
					event.setRegistration(false);
					event.setMaxGauge(0);
					event.setRegistrationStartDate(null);
					event.setRegistrationEndDate(null);
				}

				// Lieu
				if (Validator.isNotNull(placeSIGId)) {
					event.setPlaceSIGId(placeSIGId);
					event.setAccessForBlind(false);
					event.setAccessForDeaf(false);
					event.setAccessForWheelchair(false);
					event.setAccessForElder(false);
					event.setAccessForDeficient(false);

					// Dans le cas d'un lieu SIG, on ajoute automatiquement les
					// catégories territoires du lieu aux catégories à ajouter à
					// l'entité
					Place place = PlaceLocalServiceUtil.getPlaceBySIGId(placeSIGId);
					List<AssetCategory> territories = place.getTerritories();
					long[] newCategories = sc.getAssetCategoryIds();
					for (AssetCategory territory : territories) {
						if (!ArrayUtil.contains(newCategories,
								territory.getCategoryId())) {
							newCategories = ArrayUtil.append(newCategories,
									territory.getCategoryId());
						}
					}
					sc.setAssetCategoryIds(newCategories);

					// Récupération des coordonées X et Y
					event.setMercatorX(place.getMercatorX());
					event.setMercatorY(place.getMercatorY());

				} else {
					JSONObject jsonPlace = jsonEvent.getJSONObject("place");
					String placeStreetNumber = jsonPlace.getString("streetNumber");
					String placeStreetName = jsonPlace.getString("streetName");
					String placeCity = jsonPlace.getString("city");
					String placeZipCode = jsonPlace.getString("zipCode");

					event.setPlaceStreetNumber(placeStreetNumber);
					event.setPlaceStreetName(placeStreetName);
					event.setPlaceCity(placeCity);
					event.setPlaceCountry(jsonPlace.getString("country"));
					event.setPlaceZipCode(placeZipCode);

					// Récupération des coordonées X et Y
					String address = placeStreetNumber + " " + placeStreetName;
					try {
						JSONArray coordinateForAddress = openDataGeoAddressService.getCoordinateForAddress(address, placeZipCode, placeCity);
						if (coordinateForAddress.length() == 2) {
							event.setMercatorY(coordinateForAddress.get(0).toString());
							event.setMercatorX(coordinateForAddress.get(1).toString());
						} else {
							event.setMercatorY(null);
							event.setMercatorX(null);
						}
					} catch (Exception e) {
						//On a catché une erreur ou bien un time out
						// Mais la non récupération des coordonnées par Open Data ne doit pas empêcher l'enregistrement de l'event
						_log.error(e);
					}

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
				if (jsonEvent.getString("websiteURL") != null) {
					for (char c : jsonEvent.getString("websiteURL").toCharArray()) {
						if ((int) c > 5000) {
							reportLine.error(LanguageUtil.format(bundle,
									"error-forbidden-char", "websiteURL"));
							break;
						}
					}
				}
				JSONObject jsonWebsiteURL = jsonEvent.getJSONObject("websiteURL");
				if (jsonEvent.getString("websiteName") != null) {
					for (char c : jsonEvent.getString("websiteName").toCharArray()) {
						if ((int) c > 5000) {
							reportLine.error(LanguageUtil.format(bundle,
									"error-forbidden-char", "websiteName"));
							break;
						}
					}
				}
				JSONObject jsonWebsiteName = jsonEvent.getJSONObject("websiteName");
				if (jsonEvent.getString("price") != null) {
					for (char c : jsonEvent.getString("price").toCharArray()) {
						if ((int) c > 5000) {
							reportLine.error(LanguageUtil.format(bundle,
									"error-forbidden-char", "price"));
							break;
						}
					}
				}

				JSONObject jsonPrice = jsonEvent.getJSONObject("price");
				JSONObject jsonBookingDescription = jsonEvent.getJSONObject("bookingDescription");

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
							event.setPrice(price, locale);
						}
					}
					if (Validator.isNotNull(jsonBookingDescription)) {
						String bookingDescription = jsonBookingDescription.getString(locale.toString());
						if (Validator.isNotNull(bookingDescription)) {
							event.setBookingDescription(bookingDescription, locale);
						}
					}
				}

				// On enregistre les périodes de l'événement
				List<EventPeriod> oldPeriods = event.getEventPeriods();
				for (EventPeriod eventPeriod : oldPeriods) {
					EventPeriodLocalServiceUtil.deleteEventPeriod(eventPeriod);
				}
				for (EventPeriod period : periods) {
					period.setEventId(event.getEventId());
					EventPeriodLocalServiceUtil.updateEventPeriod(period);
				}

				// On enregistre l'événement
				try {
					EventLocalServiceUtil.updateEvent(event, sc);
					if (isCreated)
						reportLine.setStatus(ImportReportLineStatus.SUCCESS_ADD);
					else
						reportLine.setStatus(ImportReportLineStatus.SUCCESS_MODIFIED);
				} catch (Exception e) {
					reportLine.error(
							LanguageUtil.get(bundle, "error-while-saving-event"));
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
			}
		}

		return reportLine;
	}

	private List<AssetCategory> getCategoriesIdsFromFields(
		ImportReportLine reportLine, JSONObject json, String... fields) {
		List<AssetCategory> categories = new ArrayList<AssetCategory>();
		try {
			AssetVocabulary themeVocabulary = AssetVocabularyHelper
				.getGlobalVocabulary(VocabularyNames.EVENT_THEME);
			AssetVocabulary typeVocabulary = AssetVocabularyHelper
				.getGlobalVocabulary(VocabularyNames.EVENT_TYPE);
			AssetVocabulary publicVocabulary;
			publicVocabulary = AssetVocabularyHelper
				.getGlobalVocabulary(VocabularyNames.EVENT_PUBLIC);
			AssetVocabulary territoryVocabulary = AssetVocabularyHelper
				.getGlobalVocabulary(VocabularyNames.TERRITORY);
			AssetVocabulary serviceVocabulary = AssetVocabularyHelper
				.getGlobalVocabulary(VocabularyNames.EVENT_SERVICE);
			Map<String, AssetVocabulary> fieldVocabularies = new HashMap<String, AssetVocabulary>();
			fieldVocabularies.put("themes", themeVocabulary);
			fieldVocabularies.put("types", typeVocabulary);
			fieldVocabularies.put("publics", publicVocabulary);
			fieldVocabularies.put("territories", territoryVocabulary);
			fieldVocabularies.put("services", serviceVocabulary);
			for (String field : fields) {
				JSONArray categoryArray = json.getJSONArray(field);
				if (categoryArray != null) {
					for (int i = 0; i < categoryArray.length(); i++) {
						String oldExternalId = categoryArray.getString(i);
						AssetVocabulary fieldVocabulary = fieldVocabularies
								.get(field);

						// On transforme les anciens quartiers en nouveaux
						Map<String, String> districtMapping = new HashMap<String, String>() {
							{
								put("SQ_01", "SQ_120,SQ_107");
								put("SQ_02", "SQ_101,SQ_105");
								put("SQ_03", "SQ_106,SQ_107");
								put("SQ_04", "SQ_115");
								put("SQ_05", "SQ_118,SQ_119");
								put("SQ_06", "SQ_102,SQ_103,SQ_108,SQ_117");
								put("SQ_07", "SQ_110");
								put("SQ_08", "SQ_112,SQ_116");
								put("SQ_09", "SQ_104,SQ_109,SQ_111");
								put("SQ_10", "SQ_113,SQ_114");
							}
						};
						String newExternalId = districtMapping.get(oldExternalId);
						if(Validator.isNull(newExternalId)){
							newExternalId = oldExternalId;
						}

						String[] externalsId = newExternalId.split(",");
						for( String externalId : externalsId ) {
							AssetCategory category = AssetVocabularyHelper
									.getCategoryByExternalId(fieldVocabulary,
											externalId);
							if (category == null) {
								reportLine.error(LanguageUtil.format(bundle,
										"wrong-category",
										new String[] { String.valueOf(oldExternalId) }));
							} else {
								categories.add(category);
							}
						}
					}
				}
			}
		} catch (PortalException e) {
			_log.error(e.getMessage(), e);
		}
		return categories;
	}

}
