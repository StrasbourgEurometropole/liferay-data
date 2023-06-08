/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package eu.strasbourg.service.strasbourg.service.impl;

import aQute.bnd.annotation.ProviderType;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryTypeLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileVersionLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.dynamic.data.mapping.kernel.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.dynamic.data.mapping.kernel.DDMStructure;
import com.liferay.dynamic.data.mapping.kernel.LocalizedValue;
import com.liferay.dynamic.data.mapping.kernel.Value;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleDisplay;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.adict.AdictService;
import eu.strasbourg.service.adict.AdictServiceTracker;
import eu.strasbourg.service.adict.Street;
import eu.strasbourg.service.opendata.geo.address.OpenDataGeoAddressService;
import eu.strasbourg.service.opendata.geo.address.OpenDataGeoAddressServiceTracker;
import eu.strasbourg.service.poi.PoiService;
import eu.strasbourg.service.poi.PoiServiceTracker;
import eu.strasbourg.service.strasbourg.service.base.StrasbourgServiceBaseImpl;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.PortletHelper;
import eu.strasbourg.utils.StringHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * The implementation of the strasbourg remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.strasbourg.service.StrasbourgService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have
 * security checks based on the propagated JAAS credentials because this service
 * can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StrasbourgServiceBaseImpl
 * @see eu.strasbourg.service.strasbourg.service.StrasbourgServiceUtil
 */
@ProviderType
public class StrasbourgServiceImpl extends StrasbourgServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.strasbourg.service.StrasbourgServiceUtil} to access
	 * the strasbourg remote service.
	 */

	private AdictService adictService;
	private AdictServiceTracker adictServiceTracker;

	private AdictService getAdictService() {
		if (adictService == null) {
			adictServiceTracker = new AdictServiceTracker(this);
			adictServiceTracker.open();
			adictService = adictServiceTracker.getService();
		}
		return adictService;
	}

	private OpenDataGeoAddressService openDataGeoAddressService;
	private OpenDataGeoAddressServiceTracker openDataGeoAddressServiceTracker;

	private OpenDataGeoAddressService getOpenDataGeoAddressService() {
		if (openDataGeoAddressService == null) {
			openDataGeoAddressServiceTracker = new OpenDataGeoAddressServiceTracker(this);
			openDataGeoAddressServiceTracker.open();
			openDataGeoAddressService = openDataGeoAddressServiceTracker.getService();
		}
		return openDataGeoAddressService;
	}

	private PoiService poiService;
	private PoiServiceTracker poiServiceTracker;

	private PoiService getPoiService() {
		if (poiService == null) {
			poiServiceTracker = new PoiServiceTracker(this);
			poiServiceTracker.open();
			poiService = poiServiceTracker.getService();
		}
		return poiService;
	}

	@Override
	public JSONObject getCopyright(long groupId, String uuid, String language) {
		DLFileEntry file = DLFileEntryLocalServiceUtil.fetchDLFileEntryByUuidAndGroupId(uuid, groupId);
		Locale locale = Locale.forLanguageTag(language);
		String copyright = FileEntryHelper.getImageCopyright(file.getFileEntryId(), locale);
		return JSONFactoryUtil.createJSONObject().put("copyright", copyright);
	}

	@Override
	public JSONObject getFileDetails(long groupId, String uuid, String language) {
		DLFileEntry file = DLFileEntryLocalServiceUtil.fetchDLFileEntryByUuidAndGroupId(uuid, groupId);

		Locale locale = Locale.forLanguageTag(language);

		JSONObject jsonDetail = JSONFactoryUtil.createJSONObject();
		jsonDetail.put("name", file.getName());
		jsonDetail.put("title", FileEntryHelper.getFileTitle(file.getFileEntryId(), locale));
		jsonDetail.put("size", TextFormatter.formatStorageSize(file.getSize(), locale));
		jsonDetail.put("type", file.getExtension());

		return jsonDetail;
	}

	@Override
	public JSONObject searchStreets(String query) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		List<Street> streets = getAdictService().searchStreetNumbers(query);
		JSONArray jsonStreets = JSONFactoryUtil.createJSONArray();
		for (Street street : streets) {
			jsonStreets.put(street.toJSON());
		}
		result.put("streets", jsonStreets);
		return result;
	}

	@Override
	public JSONObject searchStreets(String query, String city) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		List<Street> streets = getAdictService().searchStreetNumbers(query, city);
		JSONArray jsonStreets = JSONFactoryUtil.createJSONArray();
		for (Street street : streets) {
			jsonStreets.put(street.toJSON());
		}
		result.put("streets", jsonStreets);
		return result;
	}

	@Override
	public String getArticleHTMLContent(long groupId, String articleId) {
		try {
			JournalArticleDisplay display = JournalArticleLocalServiceUtil.getArticleDisplay(groupId, articleId,
					"exclusive", "fr_FR", null);
			return display.getContent();
		} catch (Exception ex) {
			return "";
		}

	}

	//AngelTODO à réintégrer un fois que la gestion du territoire et des coordonnées de tous les events physiques sans exception sera faite
	/*@Override
	public int getPoisCategoryCount(long idCategory, String prefilters, String tags, long groupId, String typeContenu,
									boolean dateField, String fromDate, String toDate, String localeId, long globalGroupId) {
		return getPoiService().getPoisCategoryCount(idCategory, prefilters, tags, groupId, typeContenu,
				dateField, fromDate, toDate, localeId, globalGroupId);
	}

	@Override
	public int getPoisInterestCount(long idInterest, long groupId, String typeContenu, String localeId, long globalGroupId) {
		return getPoiService().getPoisInterestCount(idInterest, groupId, typeContenu, localeId, globalGroupId);
	}

	@Override
	public int getFavoritesPoisCount(long groupId, String typeContenu) {
		HttpServletRequest request = ServiceContextThreadLocal.getServiceContext().getRequest();
		boolean isLoggedIn = SessionParamUtil.getBoolean(request, "publik_logged_in");
		String userId = null;
		if (isLoggedIn) {
			userId = SessionParamUtil.getString(request, "publik_internal_id");
		}
		return getPoiService().getFavoritesPoisCount(userId, groupId, typeContenu);
	}*/

	@Override
	public JSONObject getInterestsPois(String interests, long groupId, String typeContenu, String localeId, long globalGroupId) {
		return getPoiService().getPois(interests, "", "", "", "",  groupId, typeContenu,
				true, "", "", localeId, globalGroupId);
	}

	@Override
	public JSONObject getCategoriesPois(String categories, String vocabulariesEmptyIds, String prefilters, String tags,
			long groupId, String typeContenu, boolean dateField, String fromDate, String toDate, String localeId, long globalGroupId) {
		return getPoiService().getPois("", categories, vocabulariesEmptyIds, prefilters, tags, groupId, typeContenu,
				dateField,  fromDate, toDate, localeId, globalGroupId);
	}
	
	@Override
	public JSONObject getFavoritesPois(long groupId, String typeContenu, String localeId) {
		HttpServletRequest request = ServiceContextThreadLocal.getServiceContext().getRequest();
		boolean isLoggedIn = SessionParamUtil.getBoolean(request, "publik_logged_in");
		String userId = null;
		if (isLoggedIn) {
			userId = SessionParamUtil.getString(request, "publik_internal_id");
		}

		return getPoiService().getFavoritesPois(userId, groupId, typeContenu, localeId);
	}

	@Override
	public void hidePortlet(String portletId) {
		HttpServletRequest request = ServiceContextThreadLocal.getServiceContext().getRequest();
		PortletHelper.hidePortlet(portletId);
	}

	@Override
	public JSONArray getCoordinateForAddress(String address, String zipCode, String city) {
		try {
			return getOpenDataGeoAddressService().getCoordinateForAddress(address, zipCode, city);
		} catch (Exception e) {
			log.error(e.getMessage() + " : address -> " + address + ", zipCode -> " + zipCode + ", city -> " + city);
		}
		return null;
	}

	@Override
	public JSONObject getTraffic() {
		return getAdictService().getTraffic();
	}

	@Override
	public JSONObject getAlerts() {
		return getAdictService().getAlerts();
	}

	@Override
	public void unfoldPortlet(String portletId) {
		PortletHelper.unfoldPortlet(portletId);
	}

	@Override
	public void foldPortlet(String portletId) {
		PortletHelper.foldPortlet(portletId);
	}

	@Override
	public JSONArray getPracticeCategories(long parentCategoryId, String localeId) {
		Locale locale = LocaleUtil.fromLanguageId(localeId);

		// récupère les catégoies ayant pour parent parentCategoryId
		List<AssetCategory> categories = AssetVocabularyHelper.getChild(parentCategoryId);
		JSONArray result = JSONFactoryUtil.createJSONArray();
		for (AssetCategory category : categories) {
			JSONObject jsonCategory = JSONFactoryUtil.createJSONObject();
			jsonCategory.put("title",category.getTitle(locale));
			jsonCategory.put("id",category.getCategoryId());
			result.put(jsonCategory);
		}
		return result;
	}

	/**
	 * Envoie <code>error</code> si le document n'a pas été envoyé.
	 *
	 * Returns <code>succes</code> un document de commission.
	 *
	 * @param  fileContent le fichier
	 * @param  fileName le nom du fichier
	 * @param  commissionName le nom de la commission
	 * @param  publicationDate la date de publication au format yyyy-MM-ddThh:mm:ss
	 * @param  publicationDateFin la date de fin de publication au format yyyy-MM-ddThh:mm:ss
	 * @param  documentType Le type de document (Strasbourg, Eurométropole)
	 * @param  documentName Le nom du document
	 * @return <code>succes</code> un document de commission, sinon <code>error</code>.
	 */
	@Override
	public JSONObject addActe(File fileContent, String fileName, String commissionName,
								  String publicationDate, String publicationDateFin, String documentType, String documentName) {
		if (!isAuthorized()) {
			return error("Not authorized");
		}

		// Validation
		if (Validator.isNull(fileContent)) {
			return error("fileContent is empty");
		}
		if (Validator.isNull(fileName)) {
			return error("fileName is empty");
		}
		if (Validator.isNull(publicationDate)) {
			return error("publicationDate is empty");
		}
		LocalDateTime publicationLocalDate;
		try {
			publicationLocalDate = LocalDateTime.parse(publicationDate);
		} catch (DateTimeParseException e) {
			return error("Wrong date format for publicationDate");
		}
		if (Validator.isNull(publicationDateFin)) {
			return error("publicationDateFin is empty");
		}
		LocalDateTime endPublicationLocalDate;
		try {
			endPublicationLocalDate = LocalDateTime.parse(publicationDateFin);
		} catch (DateTimeParseException e) {
			return error("Wrong date format for publicationDateFin");
		}
		if (!LocalDateTime.now().isBefore(endPublicationLocalDate)) {
			return error("Document expired");
		}
		if (Validator.isNull(documentType)) {
			return error("documentType is empty");
		}
		if(!documentType.equals("Strasbourg") && !documentType.equals(LanguageUtil.get(Locale.FRANCE, "eu.eurometropole"))){
			return error("documentType is incorrect : must be Strasbourg or " +
					LanguageUtil.get(Locale.FRANCE, "eu.eurometropole"));
		}
		if (Validator.isNull(documentName)) {
			return error("documentName is empty");
		}

		// On normalise le nom du fichier suite à des erreurs de Path UNIX sur les serveurs lorsque que le nom du document comporte des accents
		String documentNameNormalized = Normalizer.normalize(documentName, Normalizer.Form.NFD).replaceAll("\\p{M}", "");

		File document = fileContent;
		byte[] decoder = new byte[0];
		try {
			decoder = Files.readAllBytes(document.toPath());
		} catch (IOException e) {
			log.error(e);
			return error("Error reading the document");
		}

		if (document.exists()) {
			long groupId = GroupLocalServiceUtil.fetchFriendlyURLGroup(PortalUtil.getDefaultCompanyId(),
					"/strasbourg.eu").getGroupId();

			// Dossier Article-Rubrique
			DLFolder folderArtRub;
			try {
				folderArtRub = DLFolderLocalServiceUtil.getFolder(groupId,
						DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,"Article-Rubrique");
			} catch (PortalException e) {
				log.error(e);
				return error("Error finding 'Article-Rubrique' folder");
			}

			// Dossier Ville et Eurométropole
			DLFolder folderVilleEuro;
			try {
				folderVilleEuro = DLFolderLocalServiceUtil.getFolder(groupId,
						folderArtRub.getFolderId(),
						LanguageUtil.get(Locale.FRANCE, "eu.rep-ville-euro"));
			} catch (PortalException e) {
				log.error(e);
				return error("Error finding 'Ville et Eurométropole' folder");
			}

			ServiceContext sc = new ServiceContext();
			sc.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);
			long userId;
			try {
				userId = getUserId();
			} catch (PrincipalException e) {
				log.error(e);
				return error("Error finding userId");
			}

			// Dossier Actes réglementaires et normatifs
			Folder folderActe;
			long repositoryId = DLFolderConstants.getDataRepositoryId(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);
			try {
				folderActe = DLAppServiceUtil.getFolder(repositoryId,
						folderVilleEuro.getFolderId(),
						LanguageUtil.get(Locale.FRANCE, "eu.rep-commission"));
			}catch(PortalException e) {
				try {
					folderActe = DLAppLocalServiceUtil.addFolder(
							userId, repositoryId,
							folderVilleEuro.getFolderId(), LanguageUtil.get(Locale.FRANCE, "eu.rep-commission"),
							"", sc);
				} catch (PortalException ex) {
					log.error(ex);
					return error("Error creating 'Actes réglementaires et normatifs' folder");
				}
			}

			// Dossier Strasbourg ou Eurométropole
			Folder folder;
			try {
				folder = DLAppServiceUtil.getFolder(repositoryId,
						folderActe.getFolderId(),
						documentType);
			}catch(PortalException e) {
				try {
					folder = DLAppLocalServiceUtil.addFolder(
							userId, repositoryId,
							folderActe.getFolderId(),documentType,
							"", sc);
				} catch (PortalException ex) {
					log.error(e);
					return error("Error creating '" + documentType + "' folder");
				}
			}

			// Enregistrement du fichier
			FileEntry fileEntry;
			try {
				fileEntry = DLAppServiceUtil.getFileEntry(groupId,
						folder.getFolderId(),
						fileName);
				if(Validator.isNotNull(fileEntry))
					return error("Document already exist");
			}catch(PortalException e) {
				AssetCategory commissionCateg = null;
				if(Validator.isNotNull(commissionName)) {
					// récupération de la catégorie de la commission (création si elle n'existe pas)
					AssetVocabulary commissionVocabulary = AssetVocabularyHelper
							.getVocabulary("Commission des actes reglementaires et normatifs", groupId);
					if (Validator.isNotNull(commissionVocabulary)) {
						Optional<AssetCategory> commissionCategOptional = commissionVocabulary.getCategories().stream()
								.filter(c -> StringHelper.compareIgnoringAccentuation(c.getTitle(Locale.FRANCE), commissionName)).findFirst();
						if (commissionCategOptional.isPresent()) {
							commissionCateg = commissionCategOptional.get();
						} else {
							try {
								commissionCateg = AssetCategoryLocalServiceUtil.addCategory(userId, groupId,
										commissionName, commissionVocabulary.getVocabularyId(), sc);
							} catch (PortalException e2) {
								log.error(e2);
								return error("Error adding the commision category");
							}
						}
					}
				}

				// changement du type de document
				Optional<DLFileEntryType> fileTypeOptional = DLFileEntryTypeLocalServiceUtil.getDLFileEntryTypes(-1, -1).stream()
						.filter(t -> t.getGroupId() == groupId && t.getName(Locale.FRANCE).equals(LanguageUtil.get(Locale.FRANCE, "eu.rep-commission")))
						.findFirst();
				if(fileTypeOptional.isPresent()){
					DLFileEntryType fileType = fileTypeOptional.get();

					if(Validator.isNotNull(commissionName)) {
						//lier à la catégorie de la commission
						assert commissionCateg != null;
						sc.setAssetCategoryIds(new long[]{commissionCateg.getCategoryId()});
					}

					//lier au tag Strasbourg ou Eurométropole
					sc.setAssetTagNames(new String[]{documentType});


					Map<String, DDMFormValues> ddmFormValuesMap = new HashMap<>();
					List<DDMStructure> ddmStructures = fileType.getDDMStructures();
					// Définition des locales disponibles pour DDMFormValues (obligatoire)
					Set<Locale> availableLocales = new HashSet<>();
					availableLocales.add(Locale.FRANCE);
					Map fieldsmap = new HashMap<>();
					for(DDMStructure ddmStructure : ddmStructures) {

						if(ddmStructure.getClassName().equals(DLFileEntryMetadata.class.getName())) {

							// Récupération de DDMFormValues
							DDMFormValues ddmFormValues =  new DDMFormValues(ddmStructure.getDDMForm());
							ddmFormValues.setAvailableLocales(availableLocales);
							ddmFormValues.setDefaultLocale(Locale.FRANCE);

							// Création de la métadonnées "Date de publication"
							DDMFormFieldValue publicationDateFormFieldValue = new DDMFormFieldValue();
							publicationDateFormFieldValue.setName("publicationDate");
							Value publicationDateValue = new LocalizedValue();
							publicationDateValue.addString(Locale.FRANCE, publicationLocalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
							publicationDateFormFieldValue.setValue(publicationDateValue);

							// Création de la métadonnées "Date de fin de publication"
							DDMFormFieldValue endPublicationDateFormFieldValue = new DDMFormFieldValue();
							endPublicationDateFormFieldValue.setName("endPublicationDate");
							Value endPublicationDateValue = new LocalizedValue();
							endPublicationDateValue.addString(Locale.FRANCE, endPublicationLocalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
							endPublicationDateFormFieldValue.setValue(endPublicationDateValue);

							// AJout des métadonnée aux DDMFormValues récupérées
							ddmFormValues.addDDMFormFieldValue(publicationDateFormFieldValue);
							ddmFormValues.addDDMFormFieldValue(endPublicationDateFormFieldValue);
							ddmFormValuesMap.put(ddmStructure.getStructureKey(), ddmFormValues);
						}
					}

					// AJout du type de document dans le service Context (pris en compte alors dans le addFileEntry)
					sc.setAttribute("fileEntryTypeId", fileType.getFileEntryTypeId());
					sc.setUserId(userId);
					try {
						fileEntry = DLAppLocalServiceUtil.addFileEntry(
								userId, folder.getRepositoryId(),
								folder.getFolderId(), fileName,
								MimeTypesUtil.getContentType(document),
								fileName, documentName,
								"", decoder, sc);
					} catch (PortalException ex) {
						log.error(e);
						return error("Error adding the document");
					}

					DLFileEntry dlFileEntry = null;
					try {
						//Réenregistrement du DLFileEntry pour mettre à jour les métadonnées
						dlFileEntry = DLFileEntryLocalServiceUtil.updateFileEntry(userId, fileEntry.getFileEntryId(), fileName,
								MimeTypesUtil.getContentType(document), fileName,
								StringPool.BLANK, StringPool.BLANK,DLVersionNumberIncrease.NONE, fileType.getFileEntryTypeId(), ddmFormValuesMap, document,
								null, document.length(), sc);

					} catch (Exception ex) {
						log.error(ex);
						return error("Error updating metadata for the document");
					}

					// mise en brouillon si pas encore publié
					if(LocalDateTime.now().isBefore(publicationLocalDate)) {
						try {
							DLFileVersion version = dlFileEntry.getFileVersion();
							version.setStatus(WorkflowConstants.STATUS_DRAFT);
							DLFileVersionLocalServiceUtil.updateDLFileVersion(version);
						} catch (PortalException ex) {
							log.error(ex);
							return error("Error updating document status");
						}
					}
				}else{
					return error("Error finding the type of document (Liferay)");
				}

			}
			return success();
		}else{
			return error("Document does not exist after retrieving the Outputstream");
		}
	}

	/**
	 * @deprecated Remplacé par addActes qui gèrent l'envoi de fichier via multipart/form-data
	 * Envoie <code>error</code> si le document n'a pas été envoyé.
	 *
	 * Returns <code>succes</code> un document de commission.
	 *
	 * @param  fileContent le fichier en base 64
	 * @param  fileName le nom du fichier
	 * @param  commissionName le nom de la commission
	 * @param  publicationDate la date de publication au format yyyy-MM-ddThh:mm:ss
	 * @param  publicationDateFin la date de fin de publication au format yyyy-MM-ddThh:mm:ss
	 * @param  documentType Le type de document (Strasbourg, Eurométropole)
	 * @param  documentName Le nom du document
	 * @return <code>succes</code> un document de commission, sinon <code>error</code>.
	 */
	@Override
	public JSONObject addDocument(String fileContent, String fileName, String commissionName,
								  String publicationDate, String publicationDateFin, String documentType, String documentName) {
		if (!isAuthorized()) {
			return error("Not authorized");
		}

		// Validation
		if (Validator.isNull(fileContent)) {
			return error("fileContent is empty");
		}
		if (Validator.isNull(fileName)) {
			return error("fileName is empty");
		}
		if (Validator.isNull(publicationDate)) {
			return error("publicationDate is empty");
		}
		LocalDateTime publicationLocalDate;
		try {
			publicationLocalDate = LocalDateTime.parse(publicationDate);
		} catch (DateTimeParseException e) {
			return error("Wrong date format for publicationDate");
		}
		if (Validator.isNull(publicationDateFin)) {
			return error("publicationDateFin is empty");
		}
		LocalDateTime endPublicationLocalDate;
		try {
			endPublicationLocalDate = LocalDateTime.parse(publicationDateFin);
		} catch (DateTimeParseException e) {
			return error("Wrong date format for publicationDateFin");
		}
		if (!LocalDateTime.now().isBefore(endPublicationLocalDate)) {
			return error("Document expired");
		}
		if (Validator.isNull(documentType)) {
			return error("documentType is empty");
		}
		if(!documentType.equals("Strasbourg") && !documentType.equals(LanguageUtil.get(Locale.FRANCE, "eu.eurometropole"))){
			return error("documentType is incorrect : must be Strasbourg or " +
					LanguageUtil.get(Locale.FRANCE, "eu.eurometropole"));
		}
		if (Validator.isNull(documentName)) {
			return error("documentName is empty");
		}

		// Tout est ok, on peut enregistrer
		// transforme le fichier base 64 en fichier
		byte[] decoder = Base64.decode(fileContent);

		// On normalise le nom du fichier suite à des erreurs de Path UNIX sur les serveurs lorsque que le nom du document comporte des accents
		String documentNameNormalized = Normalizer.normalize(documentName, Normalizer.Form.NFD).replaceAll("\\p{M}", "");

		File document = new File(System.getProperty("java.io.tmpdir") + "/" + documentNameNormalized);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(document);
		} catch (FileNotFoundException e) {
			log.error(e);
			return error("Error while creating the document");
		}
		try {
			fos.write(decoder);
		} catch (IOException e) {
			log.error(e);
			return error("Error while writing the document");
		}
		try {
			fos.close();
		} catch (IOException e) {
			log.error(e);
			return error("Error while closing the document");
		}

		if (document.exists()) {
			long groupId = GroupLocalServiceUtil.fetchFriendlyURLGroup(PortalUtil.getDefaultCompanyId(),
					"/strasbourg.eu").getGroupId();

			// Dossier Article-Rubrique
			DLFolder folderArtRub;
			try {
				folderArtRub = DLFolderLocalServiceUtil.getFolder(groupId,
						DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,"Article-Rubrique");
			} catch (PortalException e) {
				log.error(e);
				return error("Error finding 'Article-Rubrique' folder");
			}

			// Dossier Ville et Eurométropole
			DLFolder folderVilleEuro;
			try {
				folderVilleEuro = DLFolderLocalServiceUtil.getFolder(groupId,
						folderArtRub.getFolderId(),
						LanguageUtil.get(Locale.FRANCE, "eu.rep-ville-euro"));
			} catch (PortalException e) {
				log.error(e);
				return error("Error finding 'Ville et Eurométropole' folder");
			}

			ServiceContext sc = new ServiceContext();
			sc.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);
			long userId;
			try {
				userId = getUserId();
			} catch (PrincipalException e) {
				log.error(e);
				return error("Error finding userId");
			}

			// Dossier Actes réglementaires et normatifs
			Folder folderActe;
			long repositoryId = DLFolderConstants.getDataRepositoryId(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);
			try {
				folderActe = DLAppServiceUtil.getFolder(repositoryId,
						folderVilleEuro.getFolderId(),
						LanguageUtil.get(Locale.FRANCE, "eu.rep-commission"));
			}catch(PortalException e) {
				try {
					folderActe = DLAppLocalServiceUtil.addFolder(
							userId, repositoryId,
							folderVilleEuro.getFolderId(), LanguageUtil.get(Locale.FRANCE, "eu.rep-commission"),
							"", sc);
				} catch (PortalException ex) {
					log.error(ex);
					return error("Error creating 'Actes réglementaires et normatifs' folder");
				}
			}

			// Dossier Strasbourg ou Eurométropole
			Folder folder;
			try {
				folder = DLAppServiceUtil.getFolder(repositoryId,
						folderActe.getFolderId(),
						documentType);
			}catch(PortalException e) {
				try {
					folder = DLAppLocalServiceUtil.addFolder(
							userId, repositoryId,
							folderActe.getFolderId(),documentType,
							"", sc);
				} catch (PortalException ex) {
					log.error(e);
					return error("Error creating '" + documentType + "' folder");
				}
			}

			// Enregistrement du fichier
			FileEntry fileEntry;
			try {
				fileEntry = DLAppServiceUtil.getFileEntry(groupId,
						folder.getFolderId(),
						fileName);
				if(Validator.isNotNull(fileEntry))
					return error("Document already exist");
			}catch(PortalException e) {
				AssetCategory commissionCateg = null;
				if(Validator.isNotNull(commissionName)) {
					// récupération de la catégorie de la commission (création si elle n'existe pas)
					AssetVocabulary commissionVocabulary = AssetVocabularyHelper
							.getVocabulary("Commission des actes reglementaires et normatifs", groupId);
					if (Validator.isNotNull(commissionVocabulary)) {
						Optional<AssetCategory> commissionCategOptional = commissionVocabulary.getCategories().stream()
								.filter(c -> StringHelper.compareIgnoringAccentuation(c.getTitle(Locale.FRANCE), commissionName)).findFirst();
						if (commissionCategOptional.isPresent()) {
							commissionCateg = commissionCategOptional.get();
						} else {
							try {
								commissionCateg = AssetCategoryLocalServiceUtil.addCategory(userId, groupId,
										commissionName, commissionVocabulary.getVocabularyId(), sc);
							} catch (PortalException e2) {
								log.error(e2);
								return error("Error adding the commision category");
							}
						}
					}
				}

				// changement du type de document
				Optional<DLFileEntryType> fileTypeOptional = DLFileEntryTypeLocalServiceUtil.getDLFileEntryTypes(-1, -1).stream()
						.filter(t -> t.getGroupId() == groupId && t.getName(Locale.FRANCE).equals(LanguageUtil.get(Locale.FRANCE, "eu.rep-commission")))
						.findFirst();
				if(fileTypeOptional.isPresent()){
					DLFileEntryType fileType = fileTypeOptional.get();

					if(Validator.isNotNull(commissionName)) {
						//lier à la catégorie de la commission
						assert commissionCateg != null;
						sc.setAssetCategoryIds(new long[]{commissionCateg.getCategoryId()});
					}

					//lier au tag Strasbourg ou Eurométropole
					sc.setAssetTagNames(new String[]{documentType});


					Map<String, DDMFormValues> ddmFormValuesMap = new HashMap<>();
					List<DDMStructure> ddmStructures = fileType.getDDMStructures();
					// Définition des locales disponibles pour DDMFormValues (obligatoire)
					Set<Locale> availableLocales = new HashSet<>();
					availableLocales.add(Locale.FRANCE);
					Map fieldsmap = new HashMap<>();
					for(DDMStructure ddmStructure : ddmStructures) {

						if(ddmStructure.getClassName().equals(DLFileEntryMetadata.class.getName())) {

							// Récupération de DDMFormValues
							DDMFormValues ddmFormValues =  new DDMFormValues(ddmStructure.getDDMForm());
							ddmFormValues.setAvailableLocales(availableLocales);
							ddmFormValues.setDefaultLocale(Locale.FRANCE);

							// Création de la métadonnées "Date de publication"
							DDMFormFieldValue publicationDateFormFieldValue = new DDMFormFieldValue();
							publicationDateFormFieldValue.setName("publicationDate");
							Value publicationDateValue = new LocalizedValue();
							publicationDateValue.addString(Locale.FRANCE, publicationLocalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
							publicationDateFormFieldValue.setValue(publicationDateValue);

							// Création de la métadonnées "Date de fin de publication"
							DDMFormFieldValue endPublicationDateFormFieldValue = new DDMFormFieldValue();
							endPublicationDateFormFieldValue.setName("endPublicationDate");
							Value endPublicationDateValue = new LocalizedValue();
							endPublicationDateValue.addString(Locale.FRANCE, endPublicationLocalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
							endPublicationDateFormFieldValue.setValue(endPublicationDateValue);

							// AJout des métadonnée aux DDMFormValues récupérées
							ddmFormValues.addDDMFormFieldValue(publicationDateFormFieldValue);
							ddmFormValues.addDDMFormFieldValue(endPublicationDateFormFieldValue);
							ddmFormValuesMap.put(ddmStructure.getStructureKey(), ddmFormValues);
						}
					}

					// AJout du type de document dans le service Context (pris en compte alors dans le addFileEntry)
					sc.setAttribute("fileEntryTypeId", fileType.getFileEntryTypeId());
					sc.setUserId(userId);
					try {
						fileEntry = DLAppLocalServiceUtil.addFileEntry(
								userId, folder.getRepositoryId(),
								folder.getFolderId(), fileName,
								MimeTypesUtil.getContentType(document),
								fileName, documentName,
								"", decoder, sc);
					} catch (PortalException ex) {
						log.error(e);
						return error("Error adding the document");
					}

					DLFileEntry dlFileEntry = null;
					try {
						//Réenregistrement du DLFileEntry pour mettre à jour les métadonnées
						dlFileEntry = DLFileEntryLocalServiceUtil.updateFileEntry(userId, fileEntry.getFileEntryId(), fileName,
								MimeTypesUtil.getContentType(document), fileName,
								StringPool.BLANK, StringPool.BLANK,DLVersionNumberIncrease.NONE, fileType.getFileEntryTypeId(), ddmFormValuesMap, document,
								null, document.length(), sc);

					} catch (Exception ex) {
						log.error(ex);
						return error("Error updating metadata for the document");
					}

					// mise en brouillon si pas encore publié
					if(LocalDateTime.now().isBefore(publicationLocalDate)) {
						try {
							DLFileVersion version = dlFileEntry.getFileVersion();
							version.setStatus(WorkflowConstants.STATUS_DRAFT);
							DLFileVersionLocalServiceUtil.updateDLFileVersion(version);
						} catch (PortalException ex) {
							log.error(ex);
							return error("Error updating document status");
						}
					}
				}else{
					return error("Error finding the type of document (Liferay)");
				}

			}
			return success();
		}else{
			return error("Document does not exist after retrieving the Outputstream");
		}
	}


	@Override
	public JSONArray getStructuresByGroupIds(long[] groupIds) {
		JSONArray structuresJson = JSONFactoryUtil.createJSONArray();

		if(groupIds.length > 0) {
			// récupère le classNameId des contenu web
			long classNameId = ClassNameLocalServiceUtil.getClassNameId(JournalArticle.class.getName());

			// récupère les structures du group
			List<com.liferay.dynamic.data.mapping.model.DDMStructure> structures = DDMStructureLocalServiceUtil.getStructures(groupIds, classNameId);
			for (com.liferay.dynamic.data.mapping.model.DDMStructure structure : structures) {
				Group group = GroupLocalServiceUtil.fetchGroup(structure.getGroupId());
				JSONObject structureJson = JSONFactoryUtil.createJSONObject();
				structureJson.put("id", structure.getStructureId());
				structureJson.put("value", structure.getNameCurrentValue() + " (" + group.getNameCurrentValue() + ")");
				structuresJson.put(structureJson);
			}
		}
		return structuresJson;
	}

	@Override
	public JSONObject getTagsAndCategoriesByGroupIdsAndClassName(long[] groupIds,
																String className) {
		JSONObject json = JSONFactoryUtil.createJSONObject();
		json.put("tags", getTagsByGroupIds(groupIds));
		json.put("categories", getCategoriesByClassNameAndGroupIds(groupIds, className));
		return json;
	}

	@Override
	public JSONArray getTagsByGroupIds(long[] groupIds) {
		JSONArray tagsJson = JSONFactoryUtil.createJSONArray();
		Boolean hasGlobalScope = false;
		long companyId = PortalUtil.getDefaultCompanyId();
		long globalScopeId = -1;
		try {
			globalScopeId = CompanyLocalServiceUtil.getCompany(companyId).getGroupId();
		} catch (PortalException e) {
			e.printStackTrace();
		}

		// récupère les groups
		for (long groupId : groupIds) {
			if(groupId == globalScopeId)
				hasGlobalScope = true;

			tagsJson.put(getTagsByGroupId(groupId));
		}

		// on ajoute le group global s'il n'y est pas déjà
		if(!hasGlobalScope){
			tagsJson.put(getTagsByGroupId(globalScopeId));
		}
		return tagsJson;
	}

	private JSONObject getTagsByGroupId(long groupId) {
		// récupère le groupe
		Group group = GroupLocalServiceUtil.fetchGroup(groupId);

		JSONObject groupJson = JSONFactoryUtil.createJSONObject();
		groupJson.put("label", "<font style='color: #00bcd4;'><strong>" + group.getNameCurrentValue() + "</strong></font>");
		JSONArray choicesJson = JSONFactoryUtil.createJSONArray();

		// récupère les tags du group
		List<AssetTag> tags = AssetTagLocalServiceUtil.getGroupTags(groupId);
		for (AssetTag tag : tags) {
			JSONObject tagJson = JSONFactoryUtil.createJSONObject();
			tagJson.put("value", tag.getTagId());
			tagJson.put("label", "<strong>" + tag.getName() + "</strong><i> (" + group.getNameCurrentValue() + ")</i>");
			JSONObject customPropertiesJson = JSONFactoryUtil.createJSONObject();
			customPropertiesJson.put("random", group.getNameCurrentValue() + " " + tag.getName());
			tagJson.put("customProperties", customPropertiesJson);
			choicesJson.put(tagJson);
		}
		groupJson.put("choices", choicesJson);
		return groupJson;
	}

	@Override
	public JSONArray getCategoriesByClassNameAndGroupIds(long[] groupIds,
														 String className) {
		JSONArray categoriesJson = JSONFactoryUtil.createJSONArray();

		// on ajoute le groupe global aux groupes
		long companyId = PortalUtil.getDefaultCompanyId();
		try {
			long globalScopeId = CompanyLocalServiceUtil.getCompany(companyId).getGroupId();
			groupIds = Arrays.copyOf(groupIds, groupIds.length + 1);
			System.arraycopy(new long[] {globalScopeId}, 0, groupIds, groupIds.length - 1, 1);
		} catch (PortalException e) {
			e.printStackTrace();
		}

		if(Validator.isNotNull(className)) {
			if(className.equals("searchJournalArticle")) className = JournalArticle.class.getName();

			// récupère les vocabulaires d'un className et des groupIds
			List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil.getGroupsVocabularies(groupIds, className);
			for (AssetVocabulary vocabulary : vocabularies) {
				// récupère le groupe de la catégorie
				Group group = GroupLocalServiceUtil.fetchGroup(vocabulary.getGroupId());

				JSONObject vocabularyJson = JSONFactoryUtil.createJSONObject();
				vocabularyJson.put("label", "<font style='color: #00bcd4;'><strong>" + vocabulary.getName() + "</strong> (" + group.getNameCurrentValue() + ")</font>");
				// récupère les catégories d'un vocabulaire
				JSONArray choicesJson = JSONFactoryUtil.createJSONArray();
				List<AssetCategory> categories = vocabulary.getCategories();
				for (AssetCategory category : categories) {
					JSONObject categoryJson = JSONFactoryUtil.createJSONObject();
					categoryJson.put("value", category.getCategoryId());
					String label = "";
					// récupères les ancêtres s'il y en a
					String ancestors = "";
					try {
						List<AssetCategory> ancestorList = category.getAncestors();
						for (AssetCategory ancestor : ancestorList) {
							label += " - ";
							ancestors = ancestor.getName() + (ancestors.length() > 0 ? " > " : "") + ancestors;
						}
					} catch (PortalException e) {
						log.error(e);
					}
					label += "<strong>" + category.getName() + "</strong>";
					label += "<i> (" + group.getNameCurrentValue() + " : " + vocabulary.getName() + (ancestors.length() > 0 ? " > " : "") + ancestors + ")</i>";
					categoryJson.put("label", label);
					JSONObject customPropertiesJson = JSONFactoryUtil.createJSONObject();
					customPropertiesJson.put("random", group.getNameCurrentValue() + " " + vocabulary.getName() + " " + ancestors.replaceAll(" > ", " ") + (ancestors.length() > 0 ? " " : "") + category.getName());
					categoryJson.put("customProperties", customPropertiesJson);
					choicesJson.put(categoryJson);
				}
				vocabularyJson.put("choices", choicesJson);
				categoriesJson.put(vocabularyJson);
			}
		}
		return categoriesJson;
	}


	@Override
	public JSONArray getVocabulariesByGroupIds(long[] groupIds) {
		JSONArray vocabulariesJson = JSONFactoryUtil.createJSONArray();
		Boolean hasGlobalScope = false;
		long companyId = PortalUtil.getDefaultCompanyId();
		long globalScopeId = -1;
		try {
			globalScopeId = CompanyLocalServiceUtil.getCompany(companyId).getGroupId();
		} catch (PortalException e) {
			e.printStackTrace();
		}

		// récupère les groups
		for (long groupId : groupIds) {
			if(groupId == globalScopeId)
				hasGlobalScope = true;

			vocabulariesJson.put(getVocabulariesByGroupId(groupId));
		}

		// on ajoute le group global s'il n'y est pas déjà
		if(!hasGlobalScope){
			vocabulariesJson.put(getVocabulariesByGroupId(globalScopeId));
		}
		return vocabulariesJson;
	}

	private JSONObject getVocabulariesByGroupId(long groupId) {
		// récupère le groupe
		Group group = GroupLocalServiceUtil.fetchGroup(groupId);

		JSONObject groupJson = JSONFactoryUtil.createJSONObject();
		groupJson.put("label", "<font style='color: #00bcd4;'><strong>" + group.getNameCurrentValue() + "</strong></font>");
		JSONArray choicesJson = JSONFactoryUtil.createJSONArray();

		// récupère les vocabulaires du group
		try {
			List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil.getGroupVocabularies(groupId);
			for (AssetVocabulary vocabulary : vocabularies) {
				JSONObject vocabularyJson = JSONFactoryUtil.createJSONObject();
				vocabularyJson.put("value", vocabulary.getVocabularyId());
				vocabularyJson.put("label", "<strong>" + vocabulary.getTitleCurrentValue() + "</strong><i> (" + group.getNameCurrentValue() + ")</i>");
				choicesJson.put(vocabularyJson);
			}
			groupJson.put("choices", choicesJson);
		} catch (PortalException e) {
			e.printStackTrace();
		}

		return groupJson;
	}

	private boolean isAuthorized() {
		try {
			Company defaultCompany = CompanyLocalServiceUtil.getCompanyByWebId("liferay.com");
			long globalGroupId = defaultCompany.getGroup().getGroupId();
			return this.getPermissionChecker().hasPermission(globalGroupId, StrasbourgPortletKeys.OFFICIAL_BO,
					StrasbourgPortletKeys.OFFICIAL_BO, "API_ADD_DOCUMENT");
		} catch (PortalException e) {
			log.error(e);
			return false;
		}
	}

	private JSONObject success() {
		return JSONFactoryUtil.createJSONObject().put("success", "document received");
	}

	private JSONObject error(String message) {
	    log.error(message);
		return JSONFactoryUtil.createJSONObject().put("error", message);
	}

	private Log log = LogFactoryUtil.getLog(this.getClass());
}
