package eu.strasbourg.webservice.csmap.utils;

import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalFolderLocalServiceUtil;

import java.util.List;

public class WebContentHelper {

    static public long getFolderId(String folderName, long groupId){
        // Folder Aides urgence
        JournalFolder emergencyFolder = null;
        // Recuperation des folders
        List<JournalFolder> folders = JournalFolderLocalServiceUtil.getFolders(groupId);
        for (JournalFolder folder : folders) {
            if (folder.getName().equals(folderName)) {
                emergencyFolder = folder;
            }
        }
        assert emergencyFolder != null;
        return emergencyFolder.getFolderId();
    }
}
