package eu.strasbourg.webservice.csmap.utils;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.JournalArticleHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.webservice.csmap.constants.WSConstants;

import java.util.Locale;

public class CSMapJSonHelper {
    static public JSONObject categoryCSMapJSON(AssetCategory category, String urlPicto, boolean maj) {
        JSONObject jsonCategory = JSONFactoryUtil.createJSONObject();
        if (category != null) {
            String externalId = AssetVocabularyHelper.getExternalId(category);
            jsonCategory.put(WSConstants.JSON_CATEG_ID, externalId);
            String parentExternalId = AssetVocabularyHelper.getExternalId(category.getParentCategory());
            if (Validator.isNotNull(parentExternalId)) {
                jsonCategory.put(WSConstants.JSON_PARENT_ID, parentExternalId);
            }
            JSONObject nameJSON = JSONFactoryUtil.createJSONObject();
            nameJSON.put(WSConstants.JSON_LANGUAGE_FRANCE, category.getTitle(Locale.FRANCE));
            if (Validator.isNotNull(category.getTitle(Locale.US))) {
                nameJSON.put(WSConstants.JSON_LANGUAGE_US, category.getTitle(Locale.US));
            }
            if (Validator.isNotNull(category.getTitle(Locale.GERMANY))) {
                nameJSON.put(WSConstants.JSON_LANGUAGE_GERMANY, category.getTitle(Locale.GERMANY));
            }
            jsonCategory.put(WSConstants.JSON_NAME, nameJSON);
            JSONObject jsonPicto = JSONFactoryUtil.createJSONObject();
            jsonPicto.put(WSConstants.JSON_PICTO_URL, StrasbourgPropsUtil.getURL() + urlPicto);
            jsonPicto.put(WSConstants.JSON_MAJ, maj);
            jsonCategory.put(WSConstants.JSON_PICTO, jsonPicto);
        }
        return  jsonCategory;
    }

    public static JSONObject getBreveCSMapJSON(JournalArticle breve) {
        JSONObject jsonJournalArticle = JSONFactoryUtil.createJSONObject();
        if (breve != null) {
            // Various-Data
            jsonJournalArticle.put(WSConstants.JSON_WC_ID, breve.getResourcePrimKey());
            jsonJournalArticle.put(WSConstants.JSON_DATE, breve.getLastPublishDate());
            jsonJournalArticle.put(WSConstants.JSON_WC_URL, StrasbourgPropsUtil.getURL() + "/-/" + breve.getUrlTitle());

            String titleFR = JournalArticleHelper.getJournalArticleFieldValue(breve, "title", Locale.FRANCE);
            JSONObject titles = JSONFactoryUtil.createJSONObject();
            titles.put(WSConstants.JSON_LANGUAGE_FRANCE, titleFR);
            jsonJournalArticle.put(WSConstants.JSON_TITLE, titles);

            String subTitleFR = JournalArticleHelper.getJournalArticleFieldValue(breve, "chapo", Locale.FRANCE);
            JSONObject subTitles = JSONFactoryUtil.createJSONObject();
            subTitles.put(WSConstants.JSON_LANGUAGE_FRANCE, subTitleFR);
            jsonJournalArticle.put(WSConstants.JSON_SUBTITLE, subTitles);

            String descriptionFR = JournalArticleHelper.getJournalArticleFieldValue(breve, "content", Locale.FRANCE);
            JSONObject descriptions = JSONFactoryUtil.createJSONObject();
            descriptions.put(WSConstants.JSON_LANGUAGE_FRANCE, descriptionFR);
            jsonJournalArticle.put(WSConstants.JSON_DESCRIPTION, descriptions);
        }
        return  jsonJournalArticle;
    }
}
