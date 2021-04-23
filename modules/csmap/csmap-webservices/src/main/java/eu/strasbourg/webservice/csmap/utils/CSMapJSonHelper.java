package eu.strasbourg.webservice.csmap.utils;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryPropertyLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.JournalArticleHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.webservice.csmap.constants.WSConstants;

import java.util.List;
import java.util.Locale;
import java.util.Map;

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
            jsonJournalArticle.put(WSConstants.JSON_WC_ID, breve.getId());
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

    // Fonction permettant la creation des parties dans le JSON
    public static JSONObject emergencyCSMapJSON(List<JournalArticle> emergencyNumbers, Map<AssetCategory, List<JournalArticle>> emergencyHelpsMap, boolean maj) throws PortalException {
        JSONObject jsonEmergency = JSONFactoryUtil.createJSONObject();
        JSONArray emergencyNumbersJSON = JSONFactoryUtil.createJSONArray();
        JSONArray emergencyHelpsJSON = JSONFactoryUtil.createJSONArray();
        for(JournalArticle emergencyNumber : emergencyNumbers){
            JSONObject emergencyNumberJson = JSONFactoryUtil.createJSONObject();
            emergencyNumberJson.put("id", emergencyNumber.getResourcePrimKey());
            // CategoryTitle en fonction des differentes langues
            JSONObject titleJSON = JSONFactoryUtil.createJSONObject();
            titleJSON.put("fr_FR", JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber, WSConstants.JSON_WC_TITLE, Locale.FRANCE));
            if (Validator.isNotNull(JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber, WSConstants.JSON_WC_TITLE, Locale.US))) {
                titleJSON.put("en_US", JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber, WSConstants.JSON_WC_TITLE, Locale.US));
            }
            if (Validator.isNotNull(JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber, WSConstants.JSON_WC_TITLE, Locale.GERMANY))) {
                titleJSON.put("de_DE", JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber, WSConstants.JSON_WC_TITLE, Locale.GERMANY));
            }
            emergencyNumberJson.put(WSConstants.JSON_WC_TITLE, titleJSON);

            emergencyNumberJson.put(WSConstants.JSON_WC_ORDER, JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber,WSConstants.JSON_WC_ORDER,Locale.FRANCE));
            emergencyNumberJson.put(WSConstants.JSON_WC_NUMBER, JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber,WSConstants.JSON_WC_NUMBER,Locale.FRANCE));
            JSONObject colorJSON = JSONFactoryUtil.createJSONObject();
            colorJSON.put(WSConstants.JSON_WC_FONT_COLOR, JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber,WSConstants.JSON_WC_FONT_COLOR,Locale.FRANCE));
            colorJSON.put(WSConstants.JSON_WC_BACKGROUND_COLOR, JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber,WSConstants.JSON_WC_BACKGROUND_COLOR,Locale.FRANCE));
            emergencyNumberJson.put(WSConstants.JSON_WC_COLOR, colorJSON);
            emergencyNumbersJSON.put(emergencyNumberJson);
        }
        for(Map.Entry emergencyHelpEntry : emergencyHelpsMap.entrySet()){
            JSONObject emergencyHelpJSON = JSONFactoryUtil.createJSONObject();
            // Recuperation des valeurs du MapEntry
            AssetCategory category = (AssetCategory) emergencyHelpEntry.getKey();
            List<JournalArticle> emergencyHelps = (List<JournalArticle>) emergencyHelpEntry.getValue();

            emergencyHelpJSON.put(WSConstants.JSON_WC_CATEGORY_ID, category.getCategoryId());
            emergencyHelpJSON.put(WSConstants.JSON_WC_CATEGORY_ORDER, AssetCategoryPropertyLocalServiceUtil.getCategoryProperty(category.getCategoryId(), "order").getValue());
            // CategoryTitle en fonction des differentes langues
            JSONObject categoryTitleJSON = JSONFactoryUtil.createJSONObject();
            categoryTitleJSON.put("fr_FR", category.getTitle(Locale.FRANCE));
            if (Validator.isNotNull(category.getTitle(Locale.US))) {
                categoryTitleJSON.put("en_US", category.getTitle(Locale.US));
            }
            if (Validator.isNotNull(category.getTitle(Locale.GERMANY))) {
                categoryTitleJSON.put("de_DE", category.getTitle(Locale.GERMANY));
            }
            emergencyHelpJSON.put(WSConstants.JSON_WC_CATEGORY_TITLE, categoryTitleJSON);
            JSONObject categoryContentJSON = JSONFactoryUtil.createJSONObject();
            for(JournalArticle emergencyHelp : emergencyHelps ) {
                categoryContentJSON.put(WSConstants.JSON_WC_TITLE, JournalArticleHelper.getJournalArticleFieldValue(emergencyHelp, WSConstants.JSON_WC_TITLE, Locale.FRANCE));
                categoryContentJSON.put(WSConstants.JSON_WC_NUMBER, JournalArticleHelper.getJournalArticleFieldValue(emergencyHelp, WSConstants.JSON_WC_NUMBER, Locale.FRANCE));
                categoryContentJSON.put(WSConstants.JSON_WC_ORDER, JournalArticleHelper.getJournalArticleFieldValue(emergencyHelp, WSConstants.JSON_WC_ORDER, Locale.FRANCE));
            }
            emergencyHelpJSON.put(WSConstants.JSON_WC_CATEGORY_CONTENT, categoryContentJSON);
            emergencyHelpsJSON.put(emergencyHelpJSON);
        }
        jsonEmergency.put(WSConstants.JSON_WC_EMERGENCY_NUMBERS, emergencyNumbersJSON);
        jsonEmergency.put(WSConstants.JSON_WC_EMERGENCY_HELPS, emergencyHelpsJSON);
        return jsonEmergency;
    }
}
