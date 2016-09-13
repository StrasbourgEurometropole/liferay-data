package eu.strasbourg.utils;

import java.util.Locale;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.util.TextFormatter;

public class DLFileEntryHelper {
	public static String getFileEntryURL(long fileEntryId) {
		DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(fileEntryId);
		return getFileEntryURL(fileEntry);
	}
	
	public static String getFileEntryURL (DLFileEntry fileEntry) {
		String url = "";
		if (fileEntry != null) {
			url = "/documents/" + fileEntry.getGroupId() + "/" + fileEntry.getFolderId() + "/0/" + fileEntry.getUuid();
		}
		return url;
	}
	
	public static String getReadableFileEntrySize(long fileEntryId, Locale locale) {
		DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(fileEntryId);
		return getReadableFileEntrySize(fileEntry, locale);
	}
	
	public static String getReadableFileEntrySize(DLFileEntry fileEntry, Locale locale) {
		return TextFormatter.formatStorageSize(fileEntry.getSize(), locale);
	}
}
