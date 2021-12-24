package eu.strasbourg.webservice.numerique_responsable.service;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
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
import org.osgi.service.component.annotations.Component;

/**
 * Service s'occuppant de retourner les elements d'une recherche
 */
@Component(
        immediate = true,
        service = WSSearch.class
)
public class WSSearch {


    /**
     * Retourne les résultats de la recherche
     *
     * @param keywords                Mots clés de recherche (utilisé dans la query)
     * @param locale                  Langue de la recherche
     * @param start                   Pagination : début
     * @param delta                   Pagination : nombre d'éléments par page
     * @return Un JSON des résultats
     * @throws PortalException Problème à la récupération d'un journalArticle
     */
    public JSONObject getJournalArticles(String keywords, String locale, int start, int delta) throws PortalException {

        JSONObject response = JSONFactoryUtil.createJSONObject();

        Hits hits = WSSearchUtil.getGlobalSearchHits(keywords, locale, start, start + delta);

        // Pas de réponse
        if (Validator.isNull(hits)) {
            response.put(WSConstants.JSON_RESPONSE_CODE, 500);
            response.put(WSConstants.JSON_ERROR_DESCRIPTION, "Une erreur technique est survenue");
            response.put(WSConstants.JSON_ERROR_TECHNICAL, "Une erreur est survenue, veuillez contacter le webmestre.");
            return response;
        }

        // tout va bien :D
        response.put(WSConstants.JSON_RESPONSE_CODE, 200);
        long nbJournalArticle = hits.getLength();
        response.put(WSConstants.JSON_WS_NB_RESULT, nbJournalArticle);

        Document[] documents = hits.getDocs();
        JSONArray JournalArticles = JSONFactoryUtil.createJSONArray();
        for (Document document : documents) {
            AssetEntry entry = AssetEntryLocalServiceUtil.fetchEntry(
                    GetterUtil.getString(document.get(Field.ENTRY_CLASS_NAME)),
                    GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
            if (entry != null) {
                JournalArticles.put(JournalArticleJSonHelper.journalArticleJSON(entry, locale));
            }
        }
        response.put(WSConstants.JSON_RESPONSE, JournalArticles);

        if(nbJournalArticle > delta) {
            JSONObject paginate = JSONFactoryUtil.createJSONObject();
            if (start > 0) {
                String previewURL = "/o/numerique-responsable-ws/search/get-journal-articles/" + keywords + "/" + locale + "/" + (start - delta) + "/" + delta;
                paginate.put(WSConstants.JSON_WC_PREVIEW, previewURL);
            }

            int current = (start / delta);
            JSONArray pages = JSONFactoryUtil.createJSONArray();
            int nbPages = (int) Math.ceil((float) nbJournalArticle / delta);
            for (int i = 0; i < nbPages; i++) {
                JSONObject page = JSONFactoryUtil.createJSONObject();
                page.put(WSConstants.JSON_WC_LABEL, (i + 1));
                if(i != current) {
                    String url = "/o/numerique-responsable-ws/search/get-journal-articles/" + keywords + "/" + locale + "/" + (i * delta) + "/" + delta;
                    page.put(WSConstants.JSON_WC_URL, url);
                }
                pages.put(page);
            }
            paginate.put(WSConstants.JSON_WC_PAGES, pages);

            if (nbJournalArticle > (start + delta)) {
                String nextURL = "/o/numerique-responsable-ws/search/get-journal-articles/" + keywords + "/" + locale + "/" + (start + delta) + "/" + delta;
                paginate.put(WSConstants.JSON_WC_NEXT, nextURL);
            }
            response.put(WSConstants.JSON_WC_PAGINATE, paginate);
        }

        return response;
    }
}
