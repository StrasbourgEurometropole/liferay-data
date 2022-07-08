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
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryMetadataLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryTypeLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileVersionLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.dynamic.data.mapping.kernel.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.dynamic.data.mapping.kernel.DDMStructure;
import com.liferay.dynamic.data.mapping.kernel.LocalizedValue;
import com.liferay.dynamic.data.mapping.kernel.StorageEngineManagerUtil;
import com.liferay.dynamic.data.mapping.kernel.Value;
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
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.auth.PrincipalException;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

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
			e.printStackTrace();
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
	 * @param  fileContent le fichier en base 64
	 * @param  fileName le nom du fichier
	 * @param  commissionName le nom de la commission
	 * @param  publicationDate la date de publication au format yyyy-MM-ddThh:mm:ss
	 * @param  endPublicationDate la date de fin de publication au format yyyy-MM-ddThh:mm:ss
	 * @param  documentType Le type de document (Strasbourg, Eurométropole)
	 * @param  documentName Le nom du document
	 * @return <code>succes</code> un document de commission, sinon <code>error</code>.
	 */
	@Override
	public JSONObject addDocument(String fileContent, String fileName, String commissionName,
								  String publicationDate, String endPublicationDate, String documentType, String documentName) {
		if (!isAuthorized()) {
			return error("not authorized");
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
			return error("wrong date format");
		}
		if (Validator.isNull(endPublicationDate)) {
			return error("endPublicationDate is empty");
		}
		LocalDateTime endPublicationLocalDate;
		try {
			endPublicationLocalDate = LocalDateTime.parse(endPublicationDate);
		} catch (DateTimeParseException e) {
			return error("wrong date format");
		}
		if (!LocalDateTime.now().isBefore(endPublicationLocalDate)) {
			return error("Document expired");
		}
		if (Validator.isNull(documentType)) {
			return error("documentType is empty");
		}
		if(!documentType.equals("Strasbourg") && !documentType.equals(LanguageUtil.get(Locale.FRANCE, "eu.eurometropole"))){
			return error("documentType is incorrect (should be Strasbourg or " +
					LanguageUtil.get(Locale.FRANCE, "eu.eurometropole") + ")");
		}
		if (Validator.isNull(documentName)) {
			return error("documentName is empty");
		}

		// Tout est ok, on peut enregistrer
		// transforme le fichier base 64 en fichier
		byte[] decoder = Base64.decode(fileContent);
		File document = new File(System.getProperty("java.io.tmpdir") + fileName);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(document);
		} catch (FileNotFoundException e) {
			return error("file not found");
		}
		try {
			fos.write(decoder);
		} catch (IOException e) {
			return error("document wrinting problem");
		}
		try {
			fos.close();
		} catch (IOException e) {
			return error("document closing problem");
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
				return error("rep 'Article-Rubrique' recovery problem");
			}

			// Dossier Ville et Eurométropole
			DLFolder folderVilleEuro;
			try {
				folderVilleEuro = DLFolderLocalServiceUtil.getFolder(groupId,
						folderArtRub.getFolderId(),
						LanguageUtil.get(Locale.FRANCE, "eu.rep-ville-euro"));
			} catch (PortalException e) {
				return error("rep 'Ville et Eurométropole' recovery problem");
			}

			ServiceContext sc = new ServiceContext();
			sc.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);
			long userId;
			try {
				userId = getUserId();
			} catch (PrincipalException e) {
				return error("userId recovery problem");
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
					return error("'Actes réglementaires et normatifs' folder adding problem");
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
					return error("'" + documentType + "' folder adding problem");
				}
			}

			// Enregistrement du fichier
			FileEntry fileEntry;
			try {
				fileEntry = DLAppServiceUtil.getFileEntry(groupId,
						folder.getFolderId(),
						fileName);
				if(Validator.isNotNull(fileEntry))
					return error("document already existe");
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
								return error("commission category adding problem");
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
					/* Si je le laisse, il me dit que le dlFileEntry existe déjà au niveau du
					DLFileEntryLocalServiceUtil.addFileEntry
					try {
						fileEntry = DLAppLocalServiceUtil.addFileEntry(
								userId, folder.getRepositoryId(),
								folder.getFolderId(), fileName,
								MimeTypesUtil.getContentType(document),
								fileName, documentName,
								"", decoder, sc);
					} catch (PortalException ex) {
						return error("document adding problem");
					}*/

					// renseigner les dates de publication
					Map<String, DDMFormValues> ddmFormValuesMap = new HashMap<>();
					List<DDMStructure> ddmStructures = fileType.getDDMStructures();
					for(DDMStructure ddmStructure : ddmStructures) {

						if(ddmStructure.getClassName().equals(DLFileEntryMetadata.class.getName())) {

							/*
							C'est ce que j'ai trouvé dans
							https://liferay.dev/fr/ask/questions/development/how-to-access-custom-metadata-values-programattically-1
							A répondu 12/02/2020 11:39
							Mais que je n'ai pas réussi à faire fonctionner
							sc.setAttribute("fileEntryTypeId", dlFileEntryType.getFileEntryTypeId());
							sc.setAttribute(ddmStructure.getStructureId() + "publicationDate", publicationLocalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
							sc.setAttribute(ddmStructure.getStructureId() + "endPublicationDate", endPublicationLocalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
							DLFileEntryLocalServiceUtil.updateFileEntryType(userId, dlFileEntry.getFileEntryId(), dlFileEntryType.getFileEntryTypeId(), sc);
							*/

							/* trouvé dans
							https://liferay.dev/fr/ask/questions/development/how-do-you-create-dlfileentrymetadata-object--1
							que j'ai dû revoir avec l'aide de
							https://liferay.dev/fr/ask/questions/development/liferay-7-2-how-to-create-a-ddmformvalues-to-add-a-ddlrecord--1
							car dlFileEntry ne prend plus des Map<String, Fields>  mais des Map<String, DDMFormValues>*/
							DDMFormValues ddmFormValues =  new DDMFormValues(ddmStructure.getDDMForm());

							/*
							Invalid available locales set for field name publicationDate
							DDMFormFieldValue publicationDateFormFieldValue = new DDMFormFieldValue();
							publicationDateFormFieldValue.setName("publicationDate");
							Value publicationDateValue = new LocalizedValue();
							publicationDateValue.addString(Locale.FRANCE, publicationLocalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
							publicationDateFormFieldValue.setValue(publicationDateValue);
							ddmFormValues.addDDMFormFieldValue(publicationDateFormFieldValue);

							Invalid available locales set for field name publicationDate
							DDMFormFieldValue endPublicationDateFormFieldValue = new DDMFormFieldValue();
							endPublicationDateFormFieldValue.setName("endPublicationDate");
							Value endPublicationDateValue = new LocalizedValue();
							endPublicationDateValue.addString(Locale.forLanguageTag("fr-FR"), endPublicationLocalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
							endPublicationDateFormFieldValue.setValue(endPublicationDateValue);
							ddmFormValues.addDDMFormFieldValue(endPublicationDateFormFieldValue);*/

							/*
							Suite au message de David dans
							https://liferay.dev/fr/ask/questions/development/invalid-available-locales-set-for-field-name-businessid-1,
							J'ai testé en mettant un champs date sans le multilangue :
							Invalid value set for field name date
							*/
							DDMFormFieldValue publicationDateFormFieldValue = new DDMFormFieldValue();
							publicationDateFormFieldValue.setName("date");
							Value publicationDateValue = new LocalizedValue();
							publicationDateValue.addString(null, publicationLocalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
							publicationDateFormFieldValue.setValue(publicationDateValue);
							ddmFormValues.addDDMFormFieldValue(publicationDateFormFieldValue);

							ddmFormValuesMap.put(ddmStructure.getStructureKey(), ddmFormValues);
						}
					}

					DLFileEntry dlFileEntry;
					try {

						dlFileEntry = DLFileEntryLocalServiceUtil.addFileEntry(userId, groupId, folder.getRepositoryId(),
								folder.getFolderId(), fileName, MimeTypesUtil.getContentType(document), fileName,
								StringPool.BLANK, StringPool.BLANK, fileType.getFileEntryTypeId(), ddmFormValuesMap, document,
								null, document.length(), sc);

						/*
						Remplacer par la ligne au dessus pour permettre l'ajout des valeurs des champs du type de document
						dlFileEntry = DLFileEntryLocalServiceUtil.updateFileEntryType(userId, dlFileEntry.getFileEntryId(),
								fileType.getFileEntryTypeId(), sc);
								*/


					} catch (PortalException ex) {
						return error("document type change problem");
					}

					// j'ai l'impression que c'est cette ligne qui permet de mettre un document en publié, car
					// s'il passe dans l'erreur de la ligne 601 le doc est créé mais en brouillon (alors qu'il ne passe pas par la suite)
					DLFileEntryLocalServiceUtil.updateDLFileEntry(dlFileEntry);

					// mise en brouillon si pas encore publié
					if(LocalDateTime.now().isBefore(publicationLocalDate)) {
						try {
							DLFileVersion version = dlFileEntry.getFileVersion();
							version.setStatus(WorkflowConstants.STATUS_DRAFT);
							DLFileVersionLocalServiceUtil.updateDLFileVersion(version);
						} catch (PortalException ex) {
							ex.printStackTrace();
						}
					}
				}else{
					return error("document type not found");
				}

			}
			return success();
		}else{
			return error("file inexistant");
		}
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
		return JSONFactoryUtil.createJSONObject().put("error", message);
	}

	private Log log = LogFactoryUtil.getLog(this.getClass());
}
