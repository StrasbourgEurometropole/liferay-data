package eu.strasbourg.webservice.csmap.utils;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryPropertyLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.utils.AssetPublisherTemplateHelper;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.JournalArticleHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.webservice.csmap.constants.WSConstants;

import java.text.DateFormat;
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
            jsonCategory.put(WSConstants.JSON_NAME, nameJSON);
            JSONObject jsonPicto = JSONFactoryUtil.createJSONObject();
            jsonPicto.put(WSConstants.JSON_PICTO_URL, StrasbourgPropsUtil.getURL() + urlPicto);
            jsonPicto.put(WSConstants.JSON_MAJ, maj);
            jsonCategory.put(WSConstants.JSON_PICTO, jsonPicto);
            JSONObject colorJSON = JSONFactoryUtil.createJSONObject();
            String gradient_start = "#939393";
            String gradient_end = "#CECFCF";
            try {
                gradient_start = "#"+AssetCategoryPropertyLocalServiceUtil.getCategoryProperty(category.getCategoryId(), "csmap_gradient_start").getValue();
            } catch(PortalException e){/* Using the default value */}
            try {
                gradient_end = "#"+AssetCategoryPropertyLocalServiceUtil.getCategoryProperty(category.getCategoryId(), "csmap_gradient_end").getValue();
            } catch(PortalException e){/* Using the default value */}
            colorJSON.put(WSConstants.JSON_COLOR_GRADIENT_START, gradient_start);
            colorJSON.put(WSConstants.JSON_COLOR_GRADIENT_END, gradient_end);
            jsonCategory.put(WSConstants.JSON_COLOR_GRADIENT, colorJSON);


        }
        return  jsonCategory;
    }

    public static JSONObject getBreveCSMapJSON(JournalArticle breve) {
        JSONObject jsonJournalArticle = JSONFactoryUtil.createJSONObject();
        if (breve != null) {
            // Various-Data
            jsonJournalArticle.put(WSConstants.JSON_WC_ID, breve.getResourcePrimKey());
            DateFormat dateFormat = DateFormatFactoryUtil
                    .getSimpleDateFormat("dd/MM/yyyy");
            jsonJournalArticle.put(WSConstants.JSON_DATE, dateFormat.format(breve.getModifiedDate()));
            jsonJournalArticle.put(WSConstants.JSON_WC_URL, StrasbourgPropsUtil.getURL() + "/-/" + breve.getUrlTitle());

            JSONObject titles = JSONFactoryUtil.createJSONObject();
            String titleFR = JournalArticleHelper.getJournalArticleFieldValue(breve, "title", Locale.FRANCE);
            titles.put(WSConstants.JSON_LANGUAGE_FRANCE, titleFR);
            jsonJournalArticle.put(WSConstants.JSON_WC_TITLE, titles);

            JSONObject subTitles = JSONFactoryUtil.createJSONObject();
            String subTitleFR = JournalArticleHelper.getJournalArticleFieldValue(breve, "chapo", Locale.FRANCE);
            subTitles.put(WSConstants.JSON_LANGUAGE_FRANCE, subTitleFR);
            jsonJournalArticle.put(WSConstants.JSON_SUBTITLE, subTitles);

            JSONObject descriptions = JSONFactoryUtil.createJSONObject();
            String descriptionFR = JournalArticleHelper.getJournalArticleFieldValue(breve, "content", Locale.FRANCE);
            descriptions.put(WSConstants.JSON_LANGUAGE_FRANCE, descriptionFR);
            jsonJournalArticle.put(WSConstants.JSON_DESCRIPTION, descriptions);
        }
        return  jsonJournalArticle;
    }

    // Fonction permettant la creation des parties dans le JSON
    public static JSONObject emergencyCSMapJSON(List<JournalArticle> emergencyNumbers, Map<AssetCategory, List<JournalArticle>> emergencyHelpsMap) throws PortalException {
        JSONObject jsonEmergency = JSONFactoryUtil.createJSONObject();
        JSONArray emergencyNumbersJSON = JSONFactoryUtil.createJSONArray();
        JSONArray emergencyHelpsJSON = JSONFactoryUtil.createJSONArray();
        for(JournalArticle emergencyNumber : emergencyNumbers){
            JSONObject emergencyNumberJson = JSONFactoryUtil.createJSONObject();
            emergencyNumberJson.put("id", emergencyNumber.getResourcePrimKey());
            // CategoryTitle en fonction des differentes langues
            JSONObject titleJSON = JSONFactoryUtil.createJSONObject();
            titleJSON.put("fr_FR", JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber, WSConstants.JSON_WC_TITLE, Locale.FRANCE));
            emergencyNumberJson.put(WSConstants.JSON_WC_TITLE, titleJSON);

            emergencyNumberJson.put(WSConstants.JSON_WC_ORDER, JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber,"number",Locale.FRANCE));
            emergencyNumberJson.put(WSConstants.JSON_WC_NUMBER, JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber,"order",Locale.FRANCE));
            JSONObject colorJSON = JSONFactoryUtil.createJSONObject();
            colorJSON.put(WSConstants.JSON_WC_FONT_COLOR, JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber,"fontColor",Locale.FRANCE));
            colorJSON.put(WSConstants.JSON_WC_BACKGROUND_COLOR, JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber,"backgroundColor",Locale.FRANCE));
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
            emergencyHelpJSON.put(WSConstants.JSON_WC_CATEGORY_TITLE, categoryTitleJSON);
            JSONArray categoryContentsJSON = JSONFactoryUtil.createJSONArray();
            for(JournalArticle emergencyHelp : emergencyHelps ) {
                JSONObject categoryContentJSON = JSONFactoryUtil.createJSONObject();
                JSONObject titleJSON = JSONFactoryUtil.createJSONObject();
                titleJSON.put("fr_FR", JournalArticleHelper.getJournalArticleFieldValue(emergencyHelp, WSConstants.JSON_WC_TITLE, Locale.FRANCE));
                categoryContentJSON.put(WSConstants.JSON_WC_TITLE, titleJSON);
                categoryContentJSON.put(WSConstants.JSON_WC_NUMBER, JournalArticleHelper.getJournalArticleFieldValue(emergencyHelp, "number", Locale.FRANCE));
                categoryContentJSON.put(WSConstants.JSON_WC_ORDER, JournalArticleHelper.getJournalArticleFieldValue(emergencyHelp, "order", Locale.FRANCE));
                categoryContentsJSON.put(categoryContentJSON);
            }
            emergencyHelpJSON.put(WSConstants.JSON_WC_CATEGORY_CONTENT, categoryContentsJSON);
            emergencyHelpsJSON.put(emergencyHelpJSON);
        }
        jsonEmergency.put(WSConstants.JSON_WC_EMERGENCY_NUMBERS, emergencyNumbersJSON);
        jsonEmergency.put(WSConstants.JSON_WC_EMERGENCY_HELPS, emergencyHelpsJSON);
        return jsonEmergency;
    }


    public static JSONObject getSocialNetworkCSMapJSON(JournalArticle socialNetwork) {
        JSONObject jsonJournalArticle = JSONFactoryUtil.createJSONObject();
        if (socialNetwork != null) {
            // Various-Data
            jsonJournalArticle.put(WSConstants.JSON_WC_ID, socialNetwork.getResourcePrimKey());

            JSONObject titles = JSONFactoryUtil.createJSONObject();
            String titleFR = JournalArticleHelper.getJournalArticleFieldValue(socialNetwork, "title", Locale.FRANCE);
            titles.put(WSConstants.JSON_LANGUAGE_FRANCE, titleFR);
            jsonJournalArticle.put(WSConstants.JSON_WC_TITLE, titles);

            jsonJournalArticle.put(WSConstants.JSON_WC_ORDER, JournalArticleHelper.getJournalArticleFieldValue(socialNetwork, "order", Locale.FRANCE));

            jsonJournalArticle.put(WSConstants.JSON_WC_URL, JournalArticleHelper.getJournalArticleFieldValue(socialNetwork, "url", Locale.FRANCE));


            String picto = JournalArticleHelper.getJournalArticleFieldValue(socialNetwork, "picto", Locale.FRANCE);
            if(Validator.isNotNull(picto)) {
                String pictoURL = AssetPublisherTemplateHelper.getDocumentUrl(picto);
                jsonJournalArticle.put(WSConstants.JSON_WC_PICTO, StrasbourgPropsUtil.getBaseURL() + pictoURL);
            }

            jsonJournalArticle.put(WSConstants.JSON_WC_COLOR, JournalArticleHelper.getJournalArticleFieldValue(socialNetwork, "socialNetworkColor", Locale.FRANCE));
        }
        return  jsonJournalArticle;
    }

    public static JSONObject generalConditionsCSMapJSON(List<JournalArticle> generalConditions) throws PortalException {
        JSONObject json = JSONFactoryUtil.createJSONObject();
        for (JournalArticle generalCondition : generalConditions) {
            // CategoryTitle en fonction des differentes langues
            JSONObject titleJSON = JSONFactoryUtil.createJSONObject();
            titleJSON.put("fr_FR", JournalArticleHelper.getJournalArticleFieldValue(generalCondition, WSConstants.JSON_WC_CONTENT, Locale.FRANCE));
            json.put(WSConstants.JSON_WC_TEXT, titleJSON);
        }
        return json;
    }
}
