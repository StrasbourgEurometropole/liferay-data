package eu.strasbourg.webservice.numerique_responsable.service;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.webservice.numerique_responsable.constants.WSConstants;
import eu.strasbourg.webservice.numerique_responsable.utils.JournalArticleJSonHelper;
import eu.strasbourg.webservice.numerique_responsable.utils.WSSearchUtil;

public class WSSearch {

    /**
     * Appelle le WS Mediatheque et traite le retour
     */
    public static JSONObject getJournalArticles(String keywords, String locale, int start, int end) {

        JSONObject response = JSONFactoryUtil.createJSONObject();

        Hits hits = WSSearchUtil.getGlobalSearchHits(keywords, locale, start, end);

        // Pas de réponse
        if (Validator.isNull(hits)) {
            response.put(WSConstants.JSON_RESPONSE_CODE, 500);
            response.put(WSConstants.JSON_ERROR_DESCRIPTION, "Une erreur technique est survenue");
            response.put(WSConstants.JSON_ERROR_TECHNICAL, "Une erreur est survenue, veuillez contacter le webmestre.");
            return response;
        }

        // tout va bien :D
        Document[] documents = hits.getDocs();

        // Pas de résultats
        if (documents.length == 0) {
            response.put(WSConstants.JSON_RESPONSE_CODE, 201);
            return response;
        }

        response.put(WSConstants.JSON_RESPONSE_CODE, 200);
        JSONArray JournalArticles = JSONFactoryUtil.createJSONArray();
        for (Document document : documents) {
            AssetEntry entry = AssetEntryLocalServiceUtil.fetchEntry(
                    GetterUtil.getString(document.get(Field.ENTRY_CLASS_NAME)),
                    GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
            if (entry != null) {
                JournalArticles.put(JournalArticleJSonHelper.journalArticleJSON(entry));
            }
        }
        response.put(WSConstants.JSON_RESPONSE, JournalArticles);

        long nbJournalArticle = WSSearchUtil.getGlobalSearchCount(keywords, locale);
        response.put(WSConstants.JSON_WS_NB_RESULT, nbJournalArticle);

        if(nbJournalArticle > 12) {
            if (start > 0) {
                String previewURL = "/o/numerique-responsable-ws/search/get-journal-articles/" + keywords + "/" + locale + "/" + (start - 12) + "/" + start;
                response.put(WSConstants.JSON_WC_PREVIEW, previewURL);
            }

            int current = (start / 12);
            JSONArray pages = JSONFactoryUtil.createJSONArray();
            int nbPages = (int) Math.ceil((float) nbJournalArticle / 12);
            for (int i = 0; i < nbPages; i++) {
                JSONObject page = JSONFactoryUtil.createJSONObject();
                page.put(WSConstants.JSON_WC_LABEL, (i + 1));
                if(i != current) {
                    String url = "/o/numerique-responsable-ws/search/get-journal-articles/" + keywords + "/" + locale + "/" + (i * 12) + "/" + ((i * 12) + 12);
                    page.put(WSConstants.JSON_WC_URL, url);
                }
                pages.put(page);
            }
            response.put(WSConstants.JSON_WC_PAGES, pages);

            if (nbJournalArticle > end) {
                String nextURL = "/o/numerique-responsable-ws/search/get-journal-articles/" + keywords + "/" + locale + "/" + end + "/" + (end + 12);
                response.put(WSConstants.JSON_WC_NEXT, nextURL);
            }
        }

        return response;
    }
}
