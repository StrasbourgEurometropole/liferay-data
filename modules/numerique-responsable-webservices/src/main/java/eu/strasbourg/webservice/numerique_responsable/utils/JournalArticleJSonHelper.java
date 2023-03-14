package eu.strasbourg.webservice.numerique_responsable.utils;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.utils.AssetPublisherTemplateHelper;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.JournalArticleHelper;
import eu.strasbourg.webservice.numerique_responsable.constants.WSConstants;

import java.util.List;
import java.util.Locale;

public class JournalArticleJSonHelper {
    public static JSONObject journalArticleJSON(AssetEntry entry, String localeString) {
        JournalArticle journalArticle = null;
        Locale locale = LocaleUtil.fromLanguageId(localeString);
        JSONObject json = JSONFactoryUtil.createJSONObject();
        try {
            journalArticle = JournalArticleLocalServiceUtil.getLatestArticle(entry.getClassPK());
            if (journalArticle != null) {
                String title = JournalArticleHelper.getJournalArticleFieldValue(journalArticle, "title", locale);
                if (Validator.isNull(title)) {
                    title = journalArticle.getTitle(locale);
                }
                json.put(WSConstants.JSON_WC_TITLE, title);

                json.put(WSConstants.JSON_WC_SLUG, journalArticle.getUrlTitle(locale));

                String shortDescription = JournalArticleHelper.getJournalArticleFieldValue(journalArticle, "shortDescription", locale);
                json.put(WSConstants.JSON_WC_SHORT_DESRIPTION, shortDescription);

                String thumbnailImage = JournalArticleHelper.getJournalArticleFieldValue(journalArticle, "thumbnailImage", locale);
                String imageURL = "";
                if (!thumbnailImage.isEmpty()) {
                    imageURL = AssetPublisherTemplateHelper.getDocumentUrl(thumbnailImage);
                }
                json.put(WSConstants.JSON_WC_THUMBNAIL_IMAGE, imageURL);

                JSONArray categories = JSONFactoryUtil.createJSONArray();
                List<AssetCategory> assetCategories = AssetVocabularyHelper.getAssetEntryCategories(entry);
                for(AssetCategory assetCategory : assetCategories){
                    categories.put(assetCategory.getTitle(locale));
                }
                json.put(WSConstants.JSON_CATEGORIES, categories);
            }
        } catch (PortalException e) {
            _log.error(e.getMessage(), e);
        }
        return  json;
    }

    private static final Log _log = LogFactoryUtil.getLog(JournalArticleJSonHelper.class.getName());

}