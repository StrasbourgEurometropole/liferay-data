package eu.strasbourg.utils;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryPropertyLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.io.StringReader;
import java.util.List;
import java.util.Locale;

/**
 * Classe helper pour tout ce qui concerne les layouts
 *
 * @author Benjamin Bini
 *
 */
public class JournalArticleHelper {

    public static String getJournalArticleFieldValue(JournalArticle article, String field, Locale locale) {
        String content = article.getContentByLocale(locale.toString());

        String value = "";

        com.liferay.portal.kernel.xml.Document document = null;

        try {
            document = SAXReaderUtil.read(new StringReader(content));
            Node node = document.selectSingleNode("/root/dynamic-element[@name='" + field + "']/dynamic-content");
            if (node != null && node.getText().length() > 0) {
                value = node.getText();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return value;
    }



    static public JSONObject emergencyCSMapJSON(List<JournalArticle> emergencyNumbers, List<JournalArticle> emergencyHelps, boolean maj) throws PortalException {
        JSONObject jsonEmergency = JSONFactoryUtil.createJSONObject();
        JSONObject emergencyNumbersJSON = JSONFactoryUtil.createJSONObject();
        JSONObject emergencyHelpsJSON = JSONFactoryUtil.createJSONObject();
        for(JournalArticle emergencyNumber : emergencyNumbers){
            emergencyNumbersJSON.put("id", emergencyNumber.getId());
            emergencyNumbersJSON.put("title", JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber,"Titre",Locale.FRANCE));
            emergencyNumbersJSON.put("order", JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber,"Ordre",Locale.FRANCE));
            emergencyNumbersJSON.put("number", JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber,"Numéro",Locale.FRANCE));
            JSONObject colorJSON = JSONFactoryUtil.createJSONObject();
            colorJSON.put("fontColor", JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber,"CouleurDeLaPolice",Locale.FRANCE));
            colorJSON.put("backgroundColor", JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber,"CouleurDuFond",Locale.FRANCE));
            emergencyNumbersJSON.put("color", colorJSON);
        }
        for(JournalArticle emergencyHelp : emergencyHelps){
            AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(JournalArticle.class.getName(), emergencyHelp.getResourcePrimKey());
            List<AssetCategory> emergencyHelpCategories = AssetVocabularyHelper.getAssetEntryCategories(assetEntry);
            AssetCategory emergencyHelpCategory = emergencyHelpCategories.get(0);
            emergencyHelpsJSON.put("categoryId", emergencyHelpCategory.getCategoryId());
            emergencyHelpsJSON.put("categoryOrder", AssetCategoryPropertyLocalServiceUtil.getCategoryProperty(emergencyHelpCategory.getCategoryId(), "order").getValue());
            emergencyHelpsJSON.put("CategoryTitle", emergencyHelpCategory.getTitle());
            JSONObject categoryContentJSON = JSONFactoryUtil.createJSONObject();
            categoryContentJSON.put("title", JournalArticleHelper.getJournalArticleFieldValue(emergencyHelp,"Titre",Locale.FRANCE));
            categoryContentJSON.put("number", JournalArticleHelper.getJournalArticleFieldValue(emergencyHelp,"Numéro",Locale.FRANCE));
            categoryContentJSON.put("order", JournalArticleHelper.getJournalArticleFieldValue(emergencyHelp,"Ordre",Locale.FRANCE));
            emergencyHelpsJSON.put("categoryContent", categoryContentJSON);
        }
        jsonEmergency.put("emergencyNumbers", emergencyNumbersJSON);
        jsonEmergency.put("emergencyNumbers", emergencyHelpsJSON);
        return jsonEmergency;
    }

    public static boolean listJournalArticleContainId(List<JournalArticle> journalArticleList, String id) throws PortalException {
        boolean result = false;

        if(journalArticleList.contains(JournalArticleLocalServiceUtil.getArticle(Long.parseLong(id)))){
            result = true;
        }

        return result;
    }

    private static final Log _log = LogFactoryUtil.getLog(LayoutHelper.class.getName());
}
