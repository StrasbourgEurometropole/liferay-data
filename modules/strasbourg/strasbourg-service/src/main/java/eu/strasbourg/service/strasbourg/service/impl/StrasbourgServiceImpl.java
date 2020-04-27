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
import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.*;
import com.liferay.journal.model.JournalArticleDisplay;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
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
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.adict.AdictService;
import eu.strasbourg.service.adict.AdictServiceTracker;
import eu.strasbourg.service.adict.Street;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
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
import java.util.List;
import java.util.Locale;
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

	@Override
	public JSONObject getPois(String interests, long groupId) {
		return getPoiService().getPois(interests, groupId);
	}

	@Override
	public JSONObject getPois(String interests, long groupId, String localeId) {
		return getPoiService().getPois(interests, groupId, localeId);
	}

	@Override
	public JSONObject getFavoritesPois(long groupId) {
		HttpServletRequest request = ServiceContextThreadLocal.getServiceContext().getRequest();
		boolean isLoggedIn = SessionParamUtil.getBoolean(request, "publik_logged_in");
		String userId = null;
		if (isLoggedIn) {
			userId = SessionParamUtil.getString(request, "publik_internal_id");
		}

		return getPoiService().getFavoritesPois(userId, groupId);
	}

	@Override
	public JSONObject getPois(String interests, String categories, String prefilters, long groupId, String typeContenu) {
		return getPoiService().getPois(interests, categories, prefilters, groupId, typeContenu);
	}

	@Override
	public JSONObject getPois(String interests, String categories, String prefilters, long groupId, String typeContenu, String localeId) {
		return getPoiService().getPois(interests, categories, prefilters, groupId, typeContenu, localeId);
	}

	@Override
	public int getPoisCategoryCount(long idCategory, String prefilters, long groupId, String typeContenu) {
		return getPoiService().getPoisCategoryCount(idCategory, prefilters, groupId, typeContenu);
	}

	@Override
	public int getPoisInterestCount(long idCategory, long groupId, String typeContenu) {
		return getPoiService().getPoisInterestCount(idCategory, groupId, typeContenu);
	}

	@Override
	public JSONObject getFavoritesPois(long groupId, String typeContenu) {
		HttpServletRequest request = ServiceContextThreadLocal.getServiceContext().getRequest();
		boolean isLoggedIn = SessionParamUtil.getBoolean(request, "publik_logged_in");
		String userId = null;
		if (isLoggedIn) {
			userId = SessionParamUtil.getString(request, "publik_internal_id");
		}

		return getPoiService().getFavoritesPois(userId, groupId, typeContenu);
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
	public int getFavoritesPoisCount(long groupId, String typeContenu) {
		HttpServletRequest request = ServiceContextThreadLocal.getServiceContext().getRequest();
		boolean isLoggedIn = SessionParamUtil.getBoolean(request, "publik_logged_in");
		String userId = null;
		if (isLoggedIn) {
			userId = SessionParamUtil.getString(request, "publik_internal_id");
		}
		return getPoiService().getFavoritesPoisCount(userId, groupId, typeContenu);
	}

	@Override
	public void hidePortlet(String portletId) {
		HttpServletRequest request = ServiceContextThreadLocal.getServiceContext().getRequest();
		PortletHelper.hidePortlet(portletId);
	}

	@Override
	public JSONArray getCoordinateForAddress(String address) {
		try {
			return getAdictService().getCoordinateForAddress(address);
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
	 * @param  documentType Le type de docuemnt (Strasbourg, Eurométropole)
	 * @param  documentName Le nom du document
	 * @return <code>succes</code> un document de commission, sinon <code>error</code>.
	 */
	@Override
	public JSONObject addDocument(String fileContent, String fileName, String commissionName,
								  String publicationDate, String documentType, String documentName) {
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
		if (Validator.isNull(commissionName)) {
			return error("commissionName is empty");
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
				// récupération de la catégorie de la commission (création si elle n'existe pas)
				AssetVocabulary commissionVocabulary = AssetVocabularyHelper
						.getVocabulary("Commission des actes reglementaires et normatifs", groupId);
				AssetCategory commissionCateg = null;
				if(Validator.isNotNull(commissionVocabulary)) {
					Optional<AssetCategory> commissionCategOptional = commissionVocabulary.getCategories().stream()
							.filter(c -> StringHelper.compareIgnoringAccentuation(c.getName(),commissionName)).findFirst();
					if(commissionCategOptional.isPresent()) {
						commissionCateg = commissionCategOptional.get();
					}else{
						try {
							commissionCateg = AssetCategoryLocalServiceUtil.addCategory(userId, groupId,
									commissionName, commissionVocabulary.getVocabularyId(), sc);
						} catch (PortalException e2) {
							return error("commission category adding problem");
						}
					}
				}

				// changement du type de document
				Optional<DLFileEntryType> fileTypeOptional = DLFileEntryTypeLocalServiceUtil.getDLFileEntryTypes(-1, -1).stream()
						.filter(t -> t.getGroupId() == groupId && t.getName(Locale.FRANCE).equals(LanguageUtil.get(Locale.FRANCE, "eu.rep-commission")))
						.findFirst();
				if(fileTypeOptional.isPresent()){
					DLFileEntryType fileType = fileTypeOptional.get();

					//lier à la catégorie de la commission
					assert commissionCateg != null;
					sc.setAssetCategoryIds(new long[]{commissionCateg.getCategoryId()});
					//lier au tag Strasbourg ou Eurométropole
					sc.setAssetTagNames(new String[]{documentType});
					try {
						fileEntry = DLAppLocalServiceUtil.addFileEntry(
								userId, folder.getRepositoryId(),
								folder.getFolderId(), fileName,
								MimeTypesUtil.getContentType(document),
								fileName, documentName,
								"", decoder, sc);
					} catch (PortalException ex) {
						return error("document adding problem");
					}

					DLFileEntry dlFileEntry;
					try {
						dlFileEntry = DLFileEntryLocalServiceUtil.updateFileEntryType(userId, fileEntry.getFileEntryId(),
							fileType.getFileEntryTypeId(), sc);
					} catch (PortalException ex) {
						return error("document type change problem");
					}

					//mise à jour du champs expando
					dlFileEntry.getExpandoBridge().setAttribute("publication-date", publicationLocalDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
					DLFileEntryLocalServiceUtil.updateDLFileEntry(dlFileEntry);
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
