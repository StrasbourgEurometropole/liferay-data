package eu.strasbourg.utils;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;

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
}
