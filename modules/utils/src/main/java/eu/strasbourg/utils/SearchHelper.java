package eu.strasbourg.utils;

import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.search.WildcardQuery;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.MatchQuery;
import com.liferay.portal.kernel.search.generic.WildcardQueryImpl;
import com.liferay.portal.kernel.util.Validator;

public class SearchHelper {

	/**
	 * Renvoie les Hits correspondant aux paramètres pour les portlets du BO
	 */
	public static Hits getBOSearchHits(SearchContext searchContext, int start,
		int end, String className, long groupId, String categoriesIds,
		String keywords, String sortField, boolean isSortDesc) {
		try {
			// Pagination
			searchContext.setStart(start);
			searchContext.setEnd(end);

			// Query
			Query query = SearchHelper.getBOSearchQuery(className, groupId,
				categoriesIds, keywords);

			// Ordre
			Sort sort = SortFactoryUtil.create(sortField, isSortDesc);
			searchContext.setSorts(sort);

			// Recherche
			long startTime = System.currentTimeMillis();
			Hits hits = IndexSearcherHelperUtil.search(searchContext, query);
			long endTime = System.currentTimeMillis();
			float duration = (endTime - startTime);
			System.out.println("Recherche : " + duration + "ms");
			return hits;
		} catch (SearchException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retourne le nombre de résultats correspondant aux paramètres pour les
	 * portlets du BO
	 */
	public static long getBOSearchCount(SearchContext searchContext,
		String className, long groupId, String categoriesIds, String keywords) {
		try {
			Query query = SearchHelper.getBOSearchQuery(className, groupId,
				categoriesIds, keywords);
			return IndexSearcherHelperUtil.searchCount(searchContext, query);
		} catch (SearchException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Retourne la requête à exécuter correspondant aux paramètres pour les
	 * portlets du BO
	 */
	private static Query getBOSearchQuery(String className, long groupId,
		String categoriesIds, String keywords) {
		try {
			// Construction de la requète
			BooleanQuery query = new BooleanQueryImpl();

			// ClassName
			query.addTerm(Field.ENTRY_CLASS_NAME, className, false,
				BooleanClauseOccur.MUST);

			// Group
			query.addRequiredTerm(Field.GROUP_ID, groupId);

			// Categories
			for (String categoryId : categoriesIds.split(",")) {
				if (Validator.isNotNull(categoryId)) {
					BooleanQuery categoryQuery = new BooleanQueryImpl();
					categoryQuery.addRequiredTerm(Field.ASSET_CATEGORY_IDS,
						categoryId);
					query.add(categoryQuery, BooleanClauseOccur.MUST);
				}
			}

			// Mots-clés
			if (Validator.isNotNull(keywords)) {
				BooleanQuery keywordQuery = new BooleanQueryImpl();
				MatchQuery titleQuery = new MatchQuery(Field.TITLE, keywords);
				titleQuery.setFuzziness(new Float(10));
				keywordQuery.add(titleQuery, BooleanClauseOccur.SHOULD);

				WildcardQuery titleWildcardQuery = new WildcardQueryImpl(
					Field.TITLE, "*" + keywords + "*");
				keywordQuery.add(titleWildcardQuery, BooleanClauseOccur.SHOULD);

				MatchQuery descriptionQuery = new MatchQuery(Field.DESCRIPTION,
					keywords);
				descriptionQuery.setFuzziness(new Float(10));
				keywordQuery.add(descriptionQuery, BooleanClauseOccur.SHOULD);

				query.add(keywordQuery, BooleanClauseOccur.MUST);
			}
			return query;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
