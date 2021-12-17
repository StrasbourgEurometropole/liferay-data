package eu.strasbourg.webservice.numerique_responsable.utils;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.utils.StringHelper;
import eu.strasbourg.webservice.numerique_responsable.constants.WSConstants;

import java.util.Locale;
import java.util.NoSuchElementException;

public class WSSearchUtil {
    /**
     * Retourne les Hits correspondant aux news et ressources
     *
     * @param keywords                Mots clés de recherche (utilisé dans la query)
     * @param start                   Pagination : début
     * @param end                     Pagination : fin
     * @return Les hits renvoyés par le moteur de recherche
     */
    static public Hits getGlobalSearchHits(String keywords, String locale, int start, int end) {
        try {
            SearchContext searchContext = new SearchContext();

            // Pagination
            searchContext.setStart(start);
            searchContext.setEnd(end);

            // Query
            Query query = getGlobalSearchQuery(keywords, locale);

            // Recherche
            return  IndexSearcherHelperUtil.search(searchContext, query);
        } catch (SearchException e) {
            return null;
        }
    }

    /**
     * Retourne le nombre total de Hits correspondant aux news et ressources
     *
     * @param keywords                Mots clés de recherche (utilisé dans la query)
     * @return Le nombre de hits renvoyés par le moteur de recherche
     */
    static public long getGlobalSearchCount(String keywords, String locale) {
        try {
            SearchContext searchContext = new SearchContext();

            // Query
            Query query = getGlobalSearchQuery(keywords, locale);

            // Recherche
            return IndexSearcherHelperUtil.searchCount(searchContext, query);
        } catch (SearchException e) {
            return 0;
        }
    }

    /**
     * Retourne la requête à exécuter correspondant aux paramètres pour le searchAssetV2
     */
    static private Query getGlobalSearchQuery(String keywords, String locale) {
        try {
            // Construction de la requète
            BooleanQuery superQuery = new BooleanQueryImpl();
            BooleanQuery query = new BooleanQueryImpl();

            /*// journalArticle
            BooleanQuery journalArticleQuery = new BooleanQueryImpl();
            journalArticleQuery.addTerm(Field.ENTRY_CLASS_NAME, JournalArticle.class.getName(), false, BooleanClauseOccur.MUST);
            // on vérifie que c'est la dernière version)
            journalArticleQuery.addRequiredTerm("head", true);
            query.add(journalArticleQuery, BooleanClauseOccur.MUST);*/

            /*// Structures
            // On crée une query faisant un "ou" entre les news et les ressources
            BooleanQuery structuresQuery = new BooleanQueryImpl();
            BooleanQuery newsQuery = new BooleanQueryImpl();
            BooleanQuery ressourcesQuery = new BooleanQueryImpl();
            newsQuery.addRequiredTerm(Field.CLASS_TYPE_ID, getNewsStructureId());
            ressourcesQuery.addRequiredTerm(Field.CLASS_TYPE_ID, getRessourcesStructureId());
            structuresQuery.add(newsQuery, BooleanClauseOccur.SHOULD);
            structuresQuery.add(ressourcesQuery, BooleanClauseOccur.SHOULD);
            query.add(structuresQuery, BooleanClauseOccur.MUST);*/

            // Group
            //query.addRequiredTerm(Field.GROUP_ID, getNumRespGroupId());

            /*// Statut et visibilité
            query.addRequiredTerm(Field.STATUS, WorkflowConstants.STATUS_APPROVED);
            query.addRequiredTerm("visible", true);*/

            /*// Mots-clés
            if (Validator.isNotNull(keywords)) {
                BooleanQuery keywordQuery = new BooleanQueryImpl();

                // Fuzzy sur titre
                MatchQuery titleQuery = new MatchQuery(Field.TITLE + '_' + locale, keywords);
                titleQuery.setAnalyzer("strasbourg_analyzer");
                keywordQuery.add(titleQuery, BooleanClauseOccur.SHOULD);

                // Titre sans analyzer
                MatchQuery titleQueryWithoutAnalyzer = new MatchQuery(Field.TITLE + "_" + locale, keywords);
                titleQueryWithoutAnalyzer.setFuzziness(2f);
                titleQueryWithoutAnalyzer.setBoost(3);
                keywordQuery.add(titleQueryWithoutAnalyzer, BooleanClauseOccur.SHOULD);

                // Wildcard sur titre
                WildcardQuery titleWildcardQuery = new WildcardQueryImpl(Field.TITLE + "_" + locale,
                        "*" + keywords + "*");
                titleWildcardQuery.setBoost(30);
                keywordQuery.add(titleWildcardQuery, BooleanClauseOccur.SHOULD);

                // Description
                MatchQuery descriptionQuery = new MatchQuery(Field.DESCRIPTION + "_" + locale, keywords);
                keywordQuery.add(descriptionQuery, BooleanClauseOccur.SHOULD);

                // Pour les fichiers on recherche dans le champ "title" sans la
                // locale car il est indexé uniquement comme cela
                BooleanQuery fileQuery = new BooleanQueryImpl();
                MatchQuery fileTitleQuery = new MatchQuery(Field.TITLE, keywords);
                // fileTitleQuery.setFuzziness(new Float(10));
                fileQuery.add(fileTitleQuery, BooleanClauseOccur.MUST);
                fileQuery.addTerm(Field.ENTRY_CLASS_NAME, DLFileEntry.class.getName(), false, BooleanClauseOccur.MUST);
                keywordQuery.add(fileQuery, BooleanClauseOccur.SHOULD);

                // Fuzzy sur content (tous les champs indexables des structures
                // de
                // CW et de D&M sont dans ce champ)
                MatchQuery contentQuery = new MatchQuery(Field.CONTENT + "_" + locale, keywords);
                // contentQuery.setFuzziness(new Float(1));
                keywordQuery.add(contentQuery, BooleanClauseOccur.SHOULD);

                // Fuzzy sur catégorie
                MatchQuery categoryKeywordQuery = new MatchQuery(Field.ASSET_CATEGORY_TITLES, keywords);
                // titleQuery.setFuzziness(new Float(10));
                keywordQuery.add(categoryKeywordQuery, BooleanClauseOccur.SHOULD);

                // Fuzzy sur tags
                MatchQuery tagKeywordQuery = new MatchQuery(Field.ASSET_TAG_NAMES, keywords);
                // titleQuery.setFuzziness(new Float(10));
                keywordQuery.add(tagKeywordQuery, BooleanClauseOccur.SHOULD);

                query.add(keywordQuery, BooleanClauseOccur.MUST);
            } else {
                // Si on n'a pas de keyword : on ne veut que les entités de la langue en cours tout de même
                // Pour cela on vérifie que le champ "title_{locale}" n'est pas vide
                // On ne fait pas cela pour les fichiers car ils n'ont pas de champ titre traduit
                BooleanQuery anyKeywordQuery = new BooleanQueryImpl();

                WildcardQuery anyKeywordWildcardQuery = new WildcardQueryImpl("title_" + locale, "*");

                anyKeywordQuery.addTerm(Field.ENTRY_CLASS_NAME, DLFileEntry.class.getName(), false,
                        BooleanClauseOccur.SHOULD);
                anyKeywordQuery.add(anyKeywordWildcardQuery, BooleanClauseOccur.SHOULD);

                query.add(anyKeywordQuery, BooleanClauseOccur.MUST);
            }*/

            superQuery.add(query, BooleanClauseOccur.SHOULD);

            return superQuery;
        } catch (ParseException e) {
            return null;
        }
    }

    static private long getGlobalGroupId(){
        Group group = getGroupByKey(WSConstants.GROUP_KEY_GLOBAL);
        return group.getGroupId();
    }

    static private long getNumRespGroupId(){
        Group group = getGroupByKey(WSConstants.GROUP_KEY_NUM_RESP);
        return group.getGroupId();
    }

    static private long getNewsStructureId(){
        DDMStructure structure = getStructureByGroupAndName(WSConstants.STRUCTURE_NEWS);
        return structure.getStructureId();
    }

    static private long getRessourcesStructureId(){
        DDMStructure structure = getStructureByGroupAndName(WSConstants.STRUCTURE_RESSOURCES);
        return structure.getStructureId();
    }

    // récupération d'un group
    static private Group getGroupByKey(String name){
        Group group = GroupLocalServiceUtil.getGroups(-1, -1).stream()
                .filter(g -> StringHelper.compareIgnoringAccentuation(g.getGroupKey().toLowerCase(), name))
                .findFirst().orElse(null);
        if(Validator.isNull(group))
            throw new NoSuchElementException("Group " + name + " introuvable");
        return group;
    }

    // récupération d'une structure
    static private DDMStructure getStructureByGroupAndName(String name){
        DDMStructure structure = DDMStructureLocalServiceUtil.getStructures(getNumRespGroupId()).stream()
                .filter(s -> StringHelper.compareIgnoringAccentuation(s.getName(Locale.FRANCE).toLowerCase(), name))
                .findFirst().orElse(null);
        if(Validator.isNull(structure))
            throw new NoSuchElementException("Structure " + name + " introuvable");
        return structure;
    }
}

