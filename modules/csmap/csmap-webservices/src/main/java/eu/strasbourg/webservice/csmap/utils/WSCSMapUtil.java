package eu.strasbourg.webservice.csmap.utils;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Locale;
import java.util.NoSuchElementException;

public class WSCSMapUtil{

    // récupération du group
    static public Group getGroupByName(String name){
        Group group = GroupLocalServiceUtil.getGroups(-1, -1).stream().filter(g -> g.getGroupKey().equals(name)).findFirst().orElse(null);
        if(Validator.isNull(group))
            throw new NoSuchElementException("Group " + name + " introuvable");
        return group;
    }

    // récupération du tag
    static public AssetTag getTagByGroupAndName(long groupId, String name) {
        AssetTag tag = AssetTagLocalServiceUtil.getGroupTags(groupId).stream().filter(t -> t.getName().equals(name)).findFirst().orElse(null);
        if(Validator.isNull(tag))
            throw new NoSuchElementException("Tag " + name + " introuvable");
        return tag;
    }

    // récupération de la structure
    static public DDMStructure getStructureByGroupAndName(long groupId, String name){
        DDMStructure structure = DDMStructureLocalServiceUtil.getStructures(groupId).stream().filter(s -> s.getName(Locale.FRANCE).equals(name)).findFirst().orElse(null);
        if(Validator.isNull(structure))
            throw new NoSuchElementException("Structure " + name + " introuvable");
        return structure;
    }

    // récupération du dossier
    static public JournalFolder getJournalFolderByGroupAndName(long groupId, String name){
        JournalFolder folder = JournalFolderLocalServiceUtil.getFolders(groupId).stream().filter(f -> f.getName().equals(name)).findFirst().orElse(null);
        if(Validator.isNull(folder))
            throw new NoSuchElementException("Dossier " + name + " introuvable");
        return folder;
    }
}
