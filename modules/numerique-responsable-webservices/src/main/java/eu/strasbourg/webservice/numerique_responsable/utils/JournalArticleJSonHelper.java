package eu.strasbourg.webservice.numerique_responsable.utils;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import eu.strasbourg.webservice.numerique_responsable.constants.WSConstants;

public class JournalArticleJSonHelper {
    static public JSONObject journalArticleJSON(AssetEntry entry) {
        JSONObject json = JSONFactoryUtil.createJSONObject();
        json.put(WSConstants.JSON_WC_TITLE, entry.getTitleCurrentValue());
        json.put(WSConstants.JSON_WC_SLUG, entry.getClassName());
        json.put(WSConstants.JSON_WC_SHORT_DESRIPTION, entry.getSummaryCurrentValue());
        json.put(WSConstants.JSON_WC_TYPE_ARTICLE, entry.getGroupId());
        json.put(WSConstants.JSON_CATEGORIES, "");
        json.put(WSConstants.JSON_WC_TAGS, "");
        json.put(WSConstants.JSON_WC_HTML, "");
        json.put(WSConstants.JSON_WC_THUMBNAIL_IMAGE, "");
        json.put(WSConstants.JSON_WC_HEADER_IMAGE, "");
        json.put(WSConstants.JSON_WC_FEATURED_ARTICLES, "");
        json.put(WSConstants.JSON_WC_LANGUAGE, entry.getDefaultLanguageId());
        json.put(WSConstants.JSON_WC_DATE_PUBLISHED, entry.getPublishDate());
        json.put(WSConstants.JSON_WC_NEWS_HEADLINE, "");
        json.put(WSConstants.JSON_WC_DESCRIPTION, entry.getDescriptionCurrentValue());

        return  json;
    }

}
