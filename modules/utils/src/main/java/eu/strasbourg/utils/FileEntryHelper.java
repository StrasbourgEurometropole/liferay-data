package eu.strasbourg.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.dynamic.data.mapping.kernel.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.dynamic.data.mapping.kernel.Value;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.documentlibrary.lar.FileEntryUtil;

/**
 * Classe Helper pour tout ce qui concerne les fichiers
 * 
 * @author Benjamin Bini
 *
 */
public class FileEntryHelper {
	public static String getFileTitle(DLFileEntry file, Locale locale) {
		String titleFromStructure = getStructureFieldValue(file.getFileEntryId(), "Titre", locale);
		if (Validator.isNotNull(titleFromStructure)) {
			return titleFromStructure;
		}
		String titleFromAnotherStricture = getStructureFieldValue(file.getFileEntryId(), "title", locale);
		if (Validator.isNotNull(titleFromAnotherStricture)) {
			return titleFromAnotherStricture;
		}
		String titleFromFile = file.getTitle();
		if (Validator.isNotNull(titleFromFile)) {
			return titleFromFile;
		}
		return "";
	}

	public static String getFileEntryURL(long fileEntryId) {
		DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(fileEntryId);
		if (fileEntry != null) {
			return getFileEntryURL(fileEntry);
		} else {
			return "";
		}
	}

	public static String getFileEntryURL(DLFileEntry fileEntry) {
		String url = "";
		FileEntryHelper.getImageCopyright(fileEntry.getFileEntryId(), null);
		if (fileEntry != null) {
			url = "/documents/" + fileEntry.getGroupId() + "/" + fileEntry.getFolderId() + "/0/" + fileEntry.getUuid();
		}
		return url;
	}

	public static String getReadableFileEntrySize(long fileEntryId, Locale locale) {
		DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(fileEntryId);
		if (fileEntry != null) {
			return getReadableFileEntrySize(fileEntry, locale);
		} else {
			return "";
		}
	}

	public static String getReadableFileEntrySize(DLFileEntry fileEntry, Locale locale) {
		return TextFormatter.formatStorageSize(fileEntry.getSize(), locale);
	}

	/**
	 * Prends en paramètre une map avec comme clés des locales et comme valeurs
	 * des IDs de fichiers, une map contenant les anciens et nouveaux ids de
	 * fichiers, retourne cette même map après avoir remplacé les IDs des
	 * fichiers par leurs potentiels IDs live
	 *
	 * @param locale_fileId
	 *            Map avec comme clé les locales et comme valeurs les ids des
	 *            fichiers en staging
	 * @param previousId_newId
	 *            Map avec comme clé les ids des fichiers staging et en valeur
	 *            les ids live
	 * @return Map avec comme clé les locales et comme valeurs les ids des
	 *         fichiers live s'ils existent, staging sinon
	 */
	public static Map<Locale, String> getLiveFileEntryIdMap(Map<Locale, String> locale_fileId,
			Map<Long, Long> previousId_newId) {
		// On initialize la map de résultat en recopiant la map passée en
		// paramètre
		Map<Locale, String> live_locale_fileId = new HashMap<Locale, String>(locale_fileId);
		// Pour chaque entrée de la map, on modifie la valeur avec la nouvelle
		// valeur
		for (Map.Entry<Locale, String> locale_fileIdEntry : live_locale_fileId.entrySet()) {
			try {
				Long fileId = Long.parseLong(locale_fileIdEntry.getValue());
				Long liveFileId = previousId_newId.get(fileId);
				if (Validator.isNotNull(liveFileId)) {
					locale_fileIdEntry.setValue(String.valueOf(liveFileId));
				}
			} catch (Exception ex) {
			}
		}
		return live_locale_fileId;
	}

	/**
	 * @param stagingFileEntryId
	 *            Id d'un fichier
	 * @param previousId_newId
	 *            Map contenant en clé des ids de fichiers staging et en valeur
	 *            des ids live
	 * @return L'id live correspondant à l'id passé en paramètre - si il
	 *         n'existe pas, retourne l'id staging
	 */
	public static Long getLiveFileEntryId(Long stagingFileEntryId, Map<Long, Long> previousId_newId) {
		Long newImageId = previousId_newId.get(stagingFileEntryId);
		if (Validator.isNotNull(newImageId)) {
			return newImageId;
		} else {
			return stagingFileEntryId;
		}
	}

	/**
	 * @param fileEntryId
	 *            ID d'une image
	 * @param locale
	 *            Locale
	 * @return Copyright de l'image dans la langue désirée
	 */
	public static String getImageCopyright(Long fileEntryId, Locale locale) {
		return FileEntryHelper.getStructureFieldValue(fileEntryId, "copyright", locale);
	}

	/**
	 * @param fileEntryId
	 *            ID d'une image
	 * @param locale
	 *            Locale
	 * @return Légende de l'image dans la langue désirée
	 */
	public static String getImageLegend(Long fileEntryId, Locale locale) {
		return FileEntryHelper.getStructureFieldValue(fileEntryId, "legend", locale);
	}

	/**
	 * @param fileEntryId
	 *            ID du fichier
	 * @param fieldName
	 *            Nom du champ personnalisé
	 * @param locale
	 *            Locale
	 * @return La valeur du champ personnalisé dans la langue désirée
	 */
	public static String getStructureFieldValue(Long fileEntryId, String fieldName, Locale locale) {
		String fieldValue = "";
		DLFileEntry file = DLFileEntryLocalServiceUtil.fetchDLFileEntry(fileEntryId);
		if (file != null) {
			try {
				Map<String, DDMFormValues> map = file
						.getDDMFormValuesMap(file.getLatestFileVersion(true).getFileVersionId());
				Collection<DDMFormValues> formValuesList = map.values();
				for (DDMFormValues formValues : formValuesList) {
					List<DDMFormFieldValue> formFieldValues = formValues.getDDMFormFieldValues();
					for (DDMFormFieldValue formFieldValue : formFieldValues) {
						if (formFieldValue.getName().equals(fieldName)) {
							Value value = formFieldValue.getValue();
							fieldValue = value.getString(locale);
							break;
						}
					}
					if (Validator.isNotNull(fieldValue)) {
						break;
					}
				}

			} catch (PortalException e) {
				_log.error(e);
			}
		}
		return fieldValue;
	}

	public static String getFileThumbnail(Long fileEntryId, ThemeDisplay themeDisplay) {
		FileEntry fileEntry = FileEntryUtil.fetchByPrimaryKey(fileEntryId);
		try {
			return DLUtil.getThumbnailSrc(fileEntry, themeDisplay);
		} catch (Exception e) {
			_log.error(e);
			return "";
		}

	}

	/**
	 * Retourne le fileEntry via l'URL relative de l'image
	 */
	public static DLFileEntry getFileEntryByRelativeURL(String url) {
		try {
			String urlInterstingPart = url.split("/documents/")[1];
			String groupIdString = urlInterstingPart.split("/")[0];
			String[] urlParts = urlInterstingPart.split("/");
			String uuid = urlParts[urlParts.length - 1].substring(0, 36);
			DLFileEntry file = DLFileEntryLocalServiceUtil.fetchDLFileEntryByUuidAndGroupId(uuid,
					Long.parseLong(groupIdString));
			return file;
		} catch (Exception ex) {
			return null;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(FileEntryHelper.class.getName());
}
