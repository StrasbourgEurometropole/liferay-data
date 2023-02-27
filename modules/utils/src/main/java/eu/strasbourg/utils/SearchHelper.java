package eu.strasbourg.utils;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Locale;

public class SearchHelper {

	/**
	 * Retourne les Hits correspondant aux paramètres pour les portlets du BO
	 */
	public static Hits getBOSearchHits(SearchContext searchContext, int start, int end, String className, long groupId,
									   String categoriesIds, String keywords, String sortField, boolean isSortDesc) {
		return SearchHelper.getBOSearchHits(searchContext, start, end, className, groupId, categoriesIds, keywords,
				sortField, isSortDesc, BooleanClauseOccur.MUST);
	}

	/**
	 * Retourne les Hits correspondant aux paramètres pour les portlets du BO
	 */
	public static Hits getBOSearchHits(SearchContext searchContext, int start, int end, String className, long groupId,
									   String categoriesIds, String keywords, String sortField, boolean isSortDesc,
									   BooleanClauseOccur categoriesBooleanClause) {
		try {
			// Pagination
			searchContext.setStart(start);
			searchContext.setEnd(end);

			// Query
			Query query = SearchHelper.getBOSearchQuery(className, groupId, categoriesIds, keywords,
					categoriesBooleanClause);

			// Ordre
			// Si il y a une recherche par mot clé on trie par pertinence
			if (Validator.isNotNull(keywords)) {
				sortField = "_score";
				isSortDesc = false;
			}
			Sort sort = SortFactoryUtil.create(sortField, isSortDesc);

			searchContext.setSorts(sort);
			// Recherche
			Hits hits = IndexSearcherHelperUtil.search(searchContext, query);
			_log.info("Recherche : " + hits.getSearchTime() * 1000 + "ms");
			return hits;
		} catch (SearchException e) {
			_log.error(e);
			return null;
		}
	}

	/**
	 * Recherche un élu par Nom ou/et prénom
	 */
	public static Hits getCouncilOfficialSearchHits(SearchContext searchContext, int start, int end, String className, long groupId, String keywords ) {
		try {
			// Pagination
			searchContext.setStart(start);
			searchContext.setEnd(end);

			// Query
			Query query = SearchHelper.getCouncilOfficialSearchQuery(className, groupId, keywords);

			// Ordre
			// on trie par pertinence
			Sort sort = SortFactoryUtil.create("_score", false);
			searchContext.setSorts(sort);
			// Recherche
			Hits hits = IndexSearcherHelperUtil.search(searchContext, query);
			_log.info("Recherche : " + hits.getSearchTime() * 1000 + "ms");
			return hits;
		} catch (SearchException e) {
			_log.error(e);
			return null;
		}
	}
	/**
	 * Retourne le nombre de résultats correspondant aux paramètres pour les
	 * portlets du BO
	 */
	public static long getBOSearchCount(SearchContext searchContext, String className, long groupId,
										String categoriesIds, String keywords) {
		return SearchHelper.getBOSearchCount(searchContext, className, groupId, categoriesIds, keywords,
				BooleanClauseOccur.MUST);
	}

	/**
	 * Retourne le nombre de résultats correspondant aux paramètres pour les
	 * portlets du BO
	 */
	public static long getBOSearchCount(SearchContext searchContext, String className, long groupId,
										String categoriesIds, String keywords, BooleanClauseOccur categoriesBooleanClause) {
		try {
			Query query = SearchHelper.getBOSearchQuery(className, groupId, categoriesIds, keywords,
					categoriesBooleanClause);

			return IndexSearcherHelperUtil.searchCount(searchContext, query);
		} catch (SearchException e) {
			_log.error(e);
			return 0;
		}
	}

	/**
	 * Retourne la requête à exécuter correspondant aux paramètres pour les
	 * portlets du BO
	 */
	private static Query getBOSearchQuery(String className, long groupId, String categoriesIds, String keywords,
										  BooleanClauseOccur categoriesBooleanClause) {
		try {
			// Construction de la requète
			BooleanQuery query = new BooleanQueryImpl();

			// ClassName
			BooleanQuery classNameQuery = new BooleanQueryImpl();
			classNameQuery.addExactTerm(Field.ENTRY_CLASS_NAME, className);
			query.add(classNameQuery, BooleanClauseOccur.MUST);

			// Group
			BooleanQuery groupIdQuery = new BooleanQueryImpl();
			groupIdQuery.addExactTerm(Field.GROUP_ID, groupId);
			query.add(groupIdQuery, BooleanClauseOccur.MUST);

			// Categories
			BooleanQuery categoriesQuery = new BooleanQueryImpl();
			for (String categoryId : categoriesIds.split(",")) {
				if (Validator.isNotNull(categoryId)) {
					BooleanQuery categoryQuery = new BooleanQueryImpl();
					categoryQuery.addRequiredTerm(Field.ASSET_CATEGORY_IDS, categoryId, false);
					categoriesQuery.add(categoryQuery, categoriesBooleanClause);
				}
			}
			query.add(categoriesQuery, BooleanClauseOccur.MUST);

			// Mots-clés
			if (Validator.isNotNull(keywords)) {
				BooleanQuery keywordQuery = new BooleanQueryImpl();
				MatchQuery titleQuery = new MatchQuery(Field.TITLE, keywords);
				titleQuery.setFuzziness(new Float(10));
				keywordQuery.add(titleQuery, BooleanClauseOccur.SHOULD);

				WildcardQuery titleWildcardQuery = new WildcardQueryImpl(Field.TITLE, "*" + keywords + "*");
				keywordQuery.add(titleWildcardQuery, BooleanClauseOccur.SHOULD);


				MatchQuery frTitleQuery = new MatchQuery("title_fr_FR", keywords);
				frTitleQuery.setFuzziness(new Float(10));
				keywordQuery.add(frTitleQuery, BooleanClauseOccur.SHOULD);

				WildcardQuery frTitleWildcardQuery = new WildcardQueryImpl("title_fr_FR", "*" + keywords + "*");
				keywordQuery.add(frTitleWildcardQuery, BooleanClauseOccur.SHOULD);

				MatchQuery descriptionQuery = new MatchQuery(Field.DESCRIPTION, keywords);
				descriptionQuery.setFuzziness(new Float(10));
				keywordQuery.add(descriptionQuery, BooleanClauseOccur.SHOULD);

				query.add(keywordQuery, BooleanClauseOccur.MUST);
			}
			return query;
		} catch (ParseException e) {
			_log.error(e);
			return null;
		}
	}

	/**
	 * Recherche un élu par Nom ou/et prénom
	 */
	private static Query getCouncilOfficialSearchQuery(String className, long groupId, String keywords) {
		try {
			// Construction de la requète
			BooleanQuery query = new BooleanQueryImpl();

			// ClassName
			BooleanQuery classNameQuery = new BooleanQueryImpl();
			classNameQuery.addExactTerm(Field.ENTRY_CLASS_NAME, className);
			query.add(classNameQuery, BooleanClauseOccur.MUST);

			// Group
			BooleanQuery groupIdQuery = new BooleanQueryImpl();
			groupIdQuery.addExactTerm(Field.GROUP_ID, groupId);
			query.add(groupIdQuery, BooleanClauseOccur.MUST);

			// Mots-clés
			if (Validator.isNotNull(keywords)) {
				BooleanQuery keywordQuery = new BooleanQueryImpl();
				MatchQuery titleQuery = new MatchQuery(Field.TITLE, keywords);
				titleQuery.setFuzziness(new Float(1));

				keywordQuery.add(titleQuery, BooleanClauseOccur.SHOULD);

				WildcardQuery titleWildcardQuery = new WildcardQueryImpl(Field.TITLE, "*" + keywords + "*");
				keywordQuery.add(titleWildcardQuery, BooleanClauseOccur.SHOULD);
				titleWildcardQuery.setBoost(new Float(10));
				query.add(keywordQuery, BooleanClauseOccur.MUST);

			}
			return query;
		} catch (ParseException e) {
			_log.error(e);
			return null;
		}
	}
	/**
	 * Retourne les Hits correspondant aux paramètres pour les moteurs de
	 * recherche d'assets
	 *
	 * @param searchContext
	 * @param classNames
	 *            La liste des classNames concernés par la recherche
	 * @param groupId
	 *            Le groupId des entités à rechercher
	 * @param globalGroupId
	 *            Le group id global (companyGroupId)
	 * @param globalScope
	 *            "true" si on prend en compte les entités du groupe global
	 * @param keywords
	 *            Mots clés de recherche
	 * @param dateField
	 *            "true" si on prend en compte le champ date
	 * @param dateFieldName
	 *            Nom du champs date dans lequel s'effectue la recherche
	 * @param fromDate
	 *            Date de début, sous le format "yyyyMMdd000000"
	 * @param toDate
	 *            Date de fin, sous le format "yyyyMMdd000000"
	 * @param categoriesIds
	 *            Liste de tableaux d'ids de catégories (provenant de la
	 *            recherche utilisateur) - un OU est effectué entre chaque id de
	 *            chaque tableau, et UN entre chaque liste
	 * @param prefilterCategoriesIds
	 *            Liste de tableaux d'ids de catégories (provenant de la
	 *            configuration du préfiltre par l'administrateur) - un OU est
	 *            effectué entre chaque id de chaque tableau, et UN entre chaque
	 *            liste
	 * @param prefilterTagsNames
	 *            Liste de tags
	 * @param locale
	 *            Locale
	 * @param start
	 *            Pagination : début
	 * @param end
	 *            Pagination : fin
	 * @param sortField
	 *            Champ sur lequel on veut effectuer le classement
	 * @param isSortDesc
	 *            Classement descendant par défaut, ascendant si "true"
	 * @return Les hits renvoyés par le moteur de recherche
	 */
	public static Hits getGlobalSearchHits(SearchContext searchContext, String[] classNames, long groupId,
										   long globalGroupId, boolean globalScope, String keywords, boolean dateField, String dateFieldName,
										   LocalDate fromDate, LocalDate toDate, List<Long[]> categoriesIds, List<Long[]> prefilterCategoriesIds,
										   String[] prefilterTagsNames, Locale locale, int start, int end, String sortField, boolean isSortDesc) {
		try {
			// Pagination
			searchContext.setStart(start);
			searchContext.setEnd(end);

			// Query
			Query query = SearchHelper.getGlobalSearchQuery(classNames, groupId, globalGroupId, globalScope, keywords,
					dateField, dateFieldName, fromDate, toDate, categoriesIds, prefilterCategoriesIds,
					prefilterTagsNames, null, false, false, locale);

			// Ordre
			Sort sort = SortFactoryUtil.create(sortField, isSortDesc);
			searchContext.setSorts(sort);

			// Recherche
			Hits hits = IndexSearcherHelperUtil.search(searchContext, query);
			_log.info("Recherche front-end : " + hits.getSearchTime() * 1000 + "ms");
			return hits;
		} catch (SearchException e) {
			_log.error(e);
			return null;
		}
	}

	/**
	 * Retourne les Hits correspondant aux paramètres pour les moteurs de
	 * recherche d'assets
	 *
	 * @param searchContext
	 * @param classNames
	 *            La liste des classNames concernés par la recherche
	 * @param groupId
	 *            Le groupId des entités à rechercher
	 * @param globalGroupId
	 *            Le group id global (companyGroupId)
	 * @param globalScope
	 *            "true" si on prend en compte les entités du groupe global
	 * @param keywords
	 *            Mots clés de recherche
	 * @param dateField
	 *            "true" si on prend en compte le champ date
	 * @param dateFieldName
	 *            Nom du champs date dans lequel s'effectue la recherche
	 * @param fromDate
	 *            Date de début, sous le format "yyyyMMdd000000"
	 * @param toDate
	 *            Date de fin, sous le format "yyyyMMdd000000"
	 * @param categoriesIds
	 *            Liste de tableaux d'ids de catégories (provenant de la
	 *            recherche utilisateur) - un OU est effectué entre chaque id de
	 *            chaque tableau, et UN entre chaque liste
	 * @param prefilterCategoriesIds
	 *            Liste de tableaux d'ids de catégories (provenant de la
	 *            configuration du préfiltre par l'administrateur) - un OU est
	 *            effectué entre chaque id de chaque tableau, et UN entre chaque
	 *            liste
	 * @param prefilterTagsNames
	 *            Liste de tags
	 * @param andOnTags
	 *            True si on souhaite faire un "ET" sur les tags, false sinon
	 * @param locale
	 *            Locale
	 * @param start
	 *            Pagination : début
	 * @param end
	 *            Pagination : fin
	 * @param sortField
	 *            Champ sur lequel on veut effectuer le classement
	 * @param isSortDesc
	 *            Classement descendant par défaut, ascendant si "true"
	 * @return Les hits renvoyés par le moteur de recherche
	 */
	public static Hits getGlobalSearchHits(SearchContext searchContext, String[] classNames, long groupId,
										   long globalGroupId, boolean globalScope, String keywords, boolean dateField, String dateFieldName,
										   LocalDate fromDate, LocalDate toDate, List<Long[]> categoriesIds, List<Long[]> prefilterCategoriesIds,
										   String[] prefilterTagsNames, boolean andOnTags, Locale locale, int start, int end, String sortField, boolean isSortDesc) {
		try {
			// Pagination
			searchContext.setStart(start);
			searchContext.setEnd(end);

			// Query
			Query query = SearchHelper.getGlobalSearchQuery(classNames, groupId, globalGroupId, globalScope, keywords,
					dateField, dateFieldName, fromDate, toDate, categoriesIds, prefilterCategoriesIds,
					prefilterTagsNames, null, false, andOnTags, locale);

			// Ordre
			Sort sort = SortFactoryUtil.create(sortField, isSortDesc);
			if (sortField.startsWith("order_ems") || sortField.startsWith("order_city")) {
				Sort alphabeticalSort = SortFactoryUtil.create("localized_title_fr_FR_sortable", Sort.STRING_TYPE, false);
				searchContext.setSorts(sort, alphabeticalSort);
			} else {
				searchContext.setSorts(sort);
			}

			// Recherche
			Hits hits = IndexSearcherHelperUtil.search(searchContext, query);
			_log.info("Recherche front-end : " + hits.getSearchTime() * 1000 + "ms");
			return hits;
		} catch (SearchException e) {
			_log.error(e);
			return null;
		}
	}


	/**
	 * Retourne les Hits correspondant aux paramètres pour les moteurs de
	 * recherche d'assets contenant un champ de filtre "Lieu" (Agenda)
	 *
	 * @param searchContext
	 * @param classNames
	 *            La liste des classNames concernés par la recherche
	 * @param groupId
	 *            Le groupId des entités à rechercher
	 * @param globalGroupId
	 *            Le group id global (companyGroupId)
	 * @param globalScope
	 *            "true" si on prend en compte les entités du groupe global
	 * @param keywords
	 *            Mots clés de recherche
	 * @param dateField
	 *            "true" si on prend en compte le champ date
	 * @param dateFieldName
	 *            Nom du champs date dans lequel s'effectue la recherche
	 * @param fromDate
	 *            Date de début, sous le format "yyyyMMdd000000"
	 * @param toDate
	 *            Date de fin, sous le format "yyyyMMdd000000"
	 * @param categoriesIds
	 *            Liste de tableaux d'ids de catégories (provenant de la
	 *            recherche utilisateur) - un OU est effectué entre chaque id de
	 *            chaque tableau, et UN entre chaque liste
	 * @param prefilterCategoriesIds
	 *            Liste de tableaux d'ids de catégories (provenant de la
	 *            configuration du préfiltre par l'administrateur) - un OU est
	 *            effectué entre chaque id de chaque tableau, et UN entre chaque
	 *            liste
	 * @param prefilterTagsNames
	 *            Liste de tags
	 * @param idSIGPlace
	 * 			  L'id SIG du lieu
	 * @param locale
	 *            Locale
	 * @param start
	 *            Pagination : début
	 * @param end
	 *            Pagination : fin
	 * @param sortField
	 *            Champ sur lequel on veut effectuer le classement
	 * @param isSortDesc
	 *            Classement descendant par défaut, ascendant si "true"
	 * @return Les hits renvoyés par le moteur de recherche
	 */
	public static Hits getGlobalSearchHits(SearchContext searchContext, String[] classNames, long groupId,
										   long globalGroupId, boolean globalScope, String keywords, boolean dateField, String dateFieldName,
										   LocalDate fromDate, LocalDate toDate, List<Long[]> categoriesIds, List<Long[]> prefilterCategoriesIds,
										   String[] prefilterTagsNames, String idSIGPlace, Locale locale, int start, int end, String sortField, boolean isSortDesc) {
		try {
			// Pagination
			searchContext.setStart(start);
			searchContext.setEnd(end);

			// Query
			Query query = SearchHelper.getGlobalSearchQuery(classNames, groupId, globalGroupId, globalScope, keywords,
					dateField, dateFieldName, fromDate, toDate, categoriesIds, prefilterCategoriesIds,
					prefilterTagsNames, idSIGPlace, false, false, locale);

			// Ordre
			Sort sort = SortFactoryUtil.create(sortField, isSortDesc);
			if (sortField.startsWith("order_ems") || sortField.startsWith("order_city")) {
				Sort alphabeticalSort = SortFactoryUtil.create("localized_title_fr_FR_sortable", Sort.STRING_TYPE, false);
				searchContext.setSorts(sort, alphabeticalSort);
			} else {
				searchContext.setSorts(sort);
			}

			// Recherche
			Hits hits = IndexSearcherHelperUtil.search(searchContext, query);
			_log.info("Recherche front-end : " + hits.getSearchTime() * 1000 + "ms");
			return hits;
		} catch (SearchException e) {
			_log.error(e);
			return null;
		}
	}

	/**
	 * Retourne les Hits correspondant aux paramètres pour les moteurs de
	 * recherche d'assets
	 *
	 * @param searchContext
	 * @param classNames
	 *            La liste des classNames concernés par la recherche
	 * @param groupId
	 *            Le groupId des entités à rechercher
	 * @param globalGroupId
	 *            Le group id global (companyGroupId)
	 * @param globalScope
	 *            "true" si on prend en compte les entités du groupe global
	 * @param keywords
	 *            Mots clés de recherche
	 * @param dateField
	 *            "true" si on prend en compte le champ date
	 * @param dateFieldName
	 *            Nom du champs date dans lequel s'effectue la recherche
	 * @param fromDate
	 *            Date de début, sous le format "yyyyMMdd000000"
	 * @param toDate
	 *            Date de fin, sous le format "yyyyMMdd000000"
	 * @param categoriesIds
	 *            Liste de tableaux d'ids de catégories (provenant de la
	 *            recherche utilisateur) - un OU est effectué entre chaque id de
	 *            chaque tableau, et UN entre chaque liste
	 * @param prefilterCategoriesIds
	 *            Liste de tableaux d'ids de catégories (provenant de la
	 *            configuration du préfiltre par l'administrateur) - un OU est
	 *            effectué entre chaque id de chaque tableau, et UN entre chaque
	 *            liste
	 * @param prefilterTagsNames
	 *            Liste de tags
	 * @param idSIGPlace
	 * 			  L'id SIG du lieu
	 * @param searchProcedure
	 *            True si on souhaite faire une recherche de procédures
	 * @param locale
	 *            Locale
	 * @param start
	 *            Pagination : début
	 * @param end
	 *            Pagination : fin
	 * @param sortField
	 *            Champ sur lequel on veut effectuer le classement
	 * @param isSortDesc
	 *            Classement descendant par défaut, ascendant si "true"
	 * @return Les hits renvoyés par le moteur de recherche
	 */
	public static Hits getGlobalSearchHits(SearchContext searchContext, String[] classNames, long groupId,
										   long globalGroupId, boolean globalScope, String keywords, boolean dateField, String dateFieldName,
										   LocalDate fromDate, LocalDate toDate, List<Long[]> categoriesIds, List<Long[]> prefilterCategoriesIds,
										   String[] prefilterTagsNames, String idSIGPlace, boolean searchProcedure, Locale locale, int start,
										   int end, String sortField, boolean isSortDesc) {
		try {
			// Pagination
			searchContext.setStart(start);
			searchContext.setEnd(end);

			// Query
			Query query = SearchHelper.getGlobalSearchQuery(classNames, groupId, globalGroupId, globalScope, keywords,
					dateField, dateFieldName, fromDate, toDate, categoriesIds, prefilterCategoriesIds,
					prefilterTagsNames, idSIGPlace, searchProcedure, false, locale);

			// Ordre
			Sort sort = SortFactoryUtil.create(sortField, isSortDesc);
			if (sortField.startsWith("order_ems") || sortField.startsWith("order_city")) {
				Sort alphabeticalSort = SortFactoryUtil.create("localized_title_fr_FR_sortable", Sort.STRING_TYPE, false);
				searchContext.setSorts(sort, alphabeticalSort);
			} else {
				searchContext.setSorts(sort);
			}

			//DEBUG ONLY. Pour voir la requete envoyee a elastic search
			//String queryS = IndexSearcherHelperUtil.getQueryString(searchContext, query);
			//_log.error(queryS);

			// Recherche
			Hits hits = IndexSearcherHelperUtil.search(searchContext, query);
			_log.info("Recherche front-end : " + hits.getSearchTime() * 1000 + "ms");
			return hits;
		} catch (SearchException e) {
			_log.error(e);
			return null;
		}
	}


	/**
	 * Retourne le nombre de résultats correspondant aux paramètres pour les
	 * moteurs de recherche globaux
	 */
	public static long getGlobalSearchCount(SearchContext searchContext, String[] classNames, long groupId,
											long globalGroupId, boolean globalScope, String keywords, boolean dateField, String dateFieldName,
											LocalDate fromDate, LocalDate toDate, List<Long[]> categoriesIds, List<Long[]> prefilterCategoriesIds,
											String[] prefilterTagsNames, Locale locale) {
		try {
			// Query
			Query query = SearchHelper.getGlobalSearchQuery(classNames, groupId, globalGroupId, globalScope, keywords,
					dateField, dateFieldName, fromDate, toDate, categoriesIds, prefilterCategoriesIds,
					prefilterTagsNames, null, false, false, locale);
			return IndexSearcherHelperUtil.searchCount(searchContext, query);
		} catch (SearchException e) {
			_log.error(e);
			return 0;
		}
	}


	/**
	 * Retourne le nombre de résultats correspondant aux paramètres pour les
	 * moteurs de recherche globaux
	 */
	public static long getGlobalSearchCount(SearchContext searchContext, String[] classNames, long groupId,
											long globalGroupId, boolean globalScope, String keywords, boolean dateField, String dateFieldName,
											LocalDate fromDate, LocalDate toDate, List<Long[]> categoriesIds, List<Long[]> prefilterCategoriesIds,
											String[] prefilterTagsNames, boolean andOnTags, Locale locale) {
		try {
			// Query
			Query query = SearchHelper.getGlobalSearchQuery(classNames, groupId, globalGroupId, globalScope, keywords,
					dateField, dateFieldName, fromDate, toDate, categoriesIds, prefilterCategoriesIds,
					prefilterTagsNames, null, false, andOnTags, locale);
			return IndexSearcherHelperUtil.searchCount(searchContext, query);
		} catch (SearchException e) {
			_log.error(e);
			return 0;
		}
	}

	/**
	 * Retourne le nombre de résultats correspondant aux paramètres pour les
	 * moteurs de recherche globaux contenant un filtre "Lieu" (Agenda)
	 */
	public static long getGlobalSearchCount(SearchContext searchContext, String[] classNames, long groupId,
											long globalGroupId, boolean globalScope, String keywords, boolean dateField, String dateFieldName,
											LocalDate fromDate, LocalDate toDate, List<Long[]> categoriesIds, List<Long[]> prefilterCategoriesIds,
											String[] prefilterTagsNames,String idSIGPlace, Locale locale) {
		try {
			// Query
			Query query = SearchHelper.getGlobalSearchQuery(classNames, groupId, globalGroupId, globalScope, keywords,
					dateField, dateFieldName, fromDate, toDate, categoriesIds, prefilterCategoriesIds,
					prefilterTagsNames,idSIGPlace, false, false, locale);
			return IndexSearcherHelperUtil.searchCount(searchContext, query);
		} catch (SearchException e) {
			_log.error(e);
			return 0;
		}
	}

	/**
	 * Retourne le nombre de résultats correspondant aux paramètres pour les
	 * moteurs de recherche globaux contenant un filtre "Lieu" (Agenda) et les procédures
	 */
	public static long getGlobalSearchCount(SearchContext searchContext, String[] classNames, long groupId,
											long globalGroupId, boolean globalScope, String keywords, boolean dateField, String dateFieldName,
											LocalDate fromDate, LocalDate toDate, List<Long[]> categoriesIds, List<Long[]> prefilterCategoriesIds,
											String[] prefilterTagsNames,String idSIGPlace, boolean searchProcedure, Locale locale) {
		try {
			// Query
			Query query = SearchHelper.getGlobalSearchQuery(classNames, groupId, globalGroupId, globalScope, keywords,
					dateField, dateFieldName, fromDate, toDate, categoriesIds, prefilterCategoriesIds,
					prefilterTagsNames,idSIGPlace, searchProcedure, false, locale);
			return IndexSearcherHelperUtil.searchCount(searchContext, query);
		} catch (SearchException e) {
			_log.error(e);
			return 0;
		}
	}

	/**
	 * Retourne la requête à exécuter correspondant aux paramètres pour les
	 * moteurs de recherche globaux
	 */
	private static Query getGlobalSearchQuery(String[] classNames, long groupId, long globalGroupId,
											  boolean globalScope, String keywords, boolean dateField, String dateFieldName, LocalDate fromDate,
											  LocalDate toDate, List<Long[]> categoriesIds, List<Long[]> prefilterCategoriesIds,
											  String[] prefilterTagsNames, String placeSigId, boolean searchProcedure, boolean andOnTags, Locale locale) {
		try {
			// Construction de la requète
			BooleanQuery superQuery = new BooleanQueryImpl();
			BooleanQuery query = new BooleanQueryImpl();

			// ClassNames
			BooleanQuery classNamesQuery = new BooleanQueryImpl();
			for (String className : classNames) {
				if (Validator.isNotNull(className)) {
					// Cas général
					if (!className.contains("JournalArticle")) {
						BooleanQuery classNameQuery = new BooleanQueryImpl();
						// classNameQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME,
						// className);
						// classNameQuery.addTerm(Field.ENTRY_CLASS_NAME,
						// className, false, BooleanClauseOccur.MUST);
						classNameQuery.addExactTerm(Field.ENTRY_CLASS_NAME, className);
						classNamesQuery.add(classNameQuery, BooleanClauseOccur.SHOULD);
					}
					// Cas où on a un journalArticle (on vérifie que c'est la
					// dernière version)
					else {
						BooleanQuery journalArticleQuery = new BooleanQueryImpl();
						journalArticleQuery.addTerm(Field.ENTRY_CLASS_NAME, className, false, BooleanClauseOccur.MUST);
						journalArticleQuery.addRequiredTerm("head", true);
						classNamesQuery.add(journalArticleQuery, BooleanClauseOccur.SHOULD);
					}
				}
			}
			query.add(classNamesQuery, BooleanClauseOccur.MUST);

			// Group
			if (globalScope) {
				// Si la configuration demande que le groupe global soit inclu
				// On crée une query faisant un "ou" entre le groupe courant et
				// le
				// groupe global
				BooleanQuery groupQuery = new BooleanQueryImpl();
				BooleanQuery scopeGroupQuery = new BooleanQueryImpl();
				BooleanQuery globalGroupQuery = new BooleanQueryImpl();
				scopeGroupQuery.addRequiredTerm(Field.GROUP_ID, groupId);
				globalGroupQuery.addRequiredTerm(Field.GROUP_ID, globalGroupId);
				groupQuery.add(scopeGroupQuery, BooleanClauseOccur.SHOULD);
				groupQuery.add(globalGroupQuery, BooleanClauseOccur.SHOULD);
				query.add(groupQuery, BooleanClauseOccur.MUST);
			} else {
				// Sinon on se contente d'ajouter le groupe courant à la requête
				query.addRequiredTerm(Field.GROUP_ID, groupId);
			}

			// Statut et visibilité
			query.addRequiredTerm(Field.STATUS, WorkflowConstants.STATUS_APPROVED);
			query.addRequiredTerm("visible", true);

			BooleanQuery publicationDateQuery = new BooleanQueryImpl();
			publicationDateQuery.addRangeTerm(Field.PUBLISH_DATE + "_sortable", 0,
					Timestamp.valueOf(LocalDateTime.now()).toInstant().toEpochMilli());
			query.add(publicationDateQuery, BooleanClauseOccur.MUST);

			// Mots-clés
			if (Validator.isNotNull(keywords)) {
				BooleanQuery keywordQuery = new BooleanQueryImpl();

				// Fuzzy sur titre
				MatchQuery titleQuery = new MatchQuery(Field.TITLE + '_' + locale, keywords);
				titleQuery.setAnalyzer("strasbourg_analyzer");
				keywordQuery.add(titleQuery, BooleanClauseOccur.SHOULD);

				// Titre sans analyzer
				MatchQuery titleQueryWithoutAnalyzer = new MatchQuery(Field.TITLE + "_" + locale, keywords);
				titleQueryWithoutAnalyzer.setFuzziness(new Float(2));
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
				// Si on n'a pas de keyword : on ne veut que les entités de la
				// langue en cours tout de même
				// Pour cela on vérifie que le champ "title_{locale}" n'est pas
				// vide
				// On ne fait pas cela pour les fichiers car ils n'ont pas de
				// champ
				// titre traduit
				BooleanQuery anyKeywordQuery = new BooleanQueryImpl();

				WildcardQuery anyKeywordWildcardQuery = new WildcardQueryImpl("title_" + locale, "*");

				anyKeywordQuery.addTerm(Field.ENTRY_CLASS_NAME, DLFileEntry.class.getName(), false,
						BooleanClauseOccur.SHOULD);
				anyKeywordQuery.add(anyKeywordWildcardQuery, BooleanClauseOccur.SHOULD);

				query.add(anyKeywordQuery, BooleanClauseOccur.MUST);
			}

			// Catégories
			// On fait un "ou" entre les catégories d'un même vocabulaire et un
			// "et" entre les différents vocabulaires
			for (Long[] categoriesIdsGroupByVocabulary : categoriesIds) {
				BooleanQuery vocabularyQuery = new BooleanQueryImpl();
				for (long categoryId : categoriesIdsGroupByVocabulary) {
					BooleanQuery categoryQuery = new BooleanQueryImpl();
					categoryQuery.addRequiredTerm(Field.ASSET_CATEGORY_IDS, String.valueOf(categoryId));
					vocabularyQuery.add(categoryQuery, BooleanClauseOccur.SHOULD);
				}
				query.add(vocabularyQuery, BooleanClauseOccur.MUST);
			}

			// Préfiltre catégories
			// On fait un "ou" entre les catégories d'un même vocabulaire et un
			// "et" entre les différents vocabulaires
			for (Long[] categoriesIdsGroupByVocabulary : prefilterCategoriesIds) {
				BooleanQuery vocabularyQuery = new BooleanQueryImpl();
				for (long categoryId : categoriesIdsGroupByVocabulary) {
					if (Validator.isNotNull(categoryId)) {
						BooleanQuery categoryQuery = new BooleanQueryImpl();
						categoryQuery.addRequiredTerm(Field.ASSET_CATEGORY_IDS, String.valueOf(categoryId));
						vocabularyQuery.add(categoryQuery, BooleanClauseOccur.SHOULD);
					}
				}
				query.add(vocabularyQuery, BooleanClauseOccur.MUST);
			}

			// Préfiltre tags
			if (Validator.isNotNull(prefilterTagsNames) && prefilterTagsNames.length > 0) {
				BooleanQuery tagsQuery = new BooleanQueryImpl();
				for (String tagName : prefilterTagsNames) {
					BooleanQuery tagQuery = new BooleanQueryImpl();
					tagQuery.addExactTerm(Field.ASSET_TAG_NAMES, String.valueOf(tagName));
					// TODO ATTENTION : La recherche se faisait par SHOULD et non MUST avant
					if (andOnTags) {
						tagsQuery.add(tagQuery, BooleanClauseOccur.MUST);
					} else {
						tagsQuery.add(tagQuery, BooleanClauseOccur.SHOULD);
					}
				}
				query.add(tagsQuery, BooleanClauseOccur.MUST);
			}

			// Dates
			if (dateField) {
				if (dateFieldName.equals("dates_Number_sortable")) {
					BooleanQuery datesQuery = new BooleanQueryImpl();

					String fromDateString = String.format("%04d", fromDate.getYear())
							+ String.format("%02d", fromDate.getMonth().getValue())
							+ String.format("%02d", fromDate.getDayOfMonth()) + "000000";
					String toDateString = String.format("%04d", toDate.getYear())
							+ String.format("%02d", toDate.getMonth().getValue())
							+ String.format("%02d", toDate.getDayOfMonth()) + "000000";

					datesQuery.addRangeTerm("dates", fromDateString, toDateString);
					query.add(datesQuery, BooleanClauseOccur.MUST);
				} else {
					BooleanQuery datesQuery = new BooleanQueryImpl();
					long fromDateEpoch = fromDate.atStartOfDay().toEpochSecond(ZoneOffset.UTC) * 1000;
					long toDateEpoch = toDate.plusDays(1).atStartOfDay().toEpochSecond(ZoneOffset.UTC) * 1000;
					datesQuery.addRangeTerm(dateFieldName, fromDateEpoch, toDateEpoch);
					query.add(datesQuery, BooleanClauseOccur.MUST);
				}

			}

			// Si le id SIG du lieu est renseigné on rajoute la condition à la requête
			if(Validator.isNotNull(placeSigId)) {
				BooleanQuery placeQuery = new BooleanQueryImpl();
				placeQuery.addRequiredTerm("idSIGPlace", placeSigId);
				query.add(placeQuery, BooleanClauseOccur.MUST);
			}

			// Si on veut les procédures/démarches, on rajoute la condition à la requête
			if (searchProcedure) {
				BooleanQuery procedureQuery = new BooleanQueryImpl();
				procedureQuery.addRequiredTerm("type", "procedure");
				procedureQuery.addRequiredTerm("title", keywords);
				superQuery.add(procedureQuery, BooleanClauseOccur.SHOULD);
			}

			superQuery.add(query, BooleanClauseOccur.SHOULD);

			return superQuery;
		} catch (ParseException e) {
			_log.error(e);
			return null;
		}
	}

	/**
	 * Retourne les Hits correspondant aux paramètres pour le webservice des
	 * événements
	 */
	public static Hits getEventWebServiceSearchHits(String className, LocalDate date, long categoryId, Locale locale) {
		try {
			SearchContext searchContext = new SearchContext();
			searchContext.setCompanyId(PortalUtil.getDefaultCompanyId());
			// Query
			Query query = SearchHelper.getEventWebServiceQuery(className, date, categoryId, locale);

			// Recherche
			Hits hits = IndexSearcherHelperUtil.search(searchContext, query);
			_log.info("Recherche : " + hits.getSearchTime() * 1000 + "ms");
			return hits;
		} catch (SearchException e) {
			_log.error(e);
			return null;
		}
	}

	/**
	 * Retourne la requête pour le webservice des événements
	 */
	private static Query getEventWebServiceQuery(String className, LocalDate date, long categoryId, Locale locale) {

		try {
			BooleanQuery query = new BooleanQueryImpl();

			query.addRequiredTerm(Field.ENTRY_CLASS_NAME, className, false);
			query.addRequiredTerm(Field.STATUS, WorkflowConstants.STATUS_APPROVED);

			// Dates
			if (date != null) {
				BooleanQuery datesQuery = new BooleanQueryImpl();
				String dateString = String.format("%04d", date.getYear())
						+ String.format("%02d", date.getMonth().getValue())
						+ String.format("%02d", date.getDayOfMonth()) + "000000";
				datesQuery.addTerm("dates", dateString);
				query.add(datesQuery, BooleanClauseOccur.MUST);
			}

			// Catégorie
			if (categoryId > 0) {
				query.addRequiredTerm(Field.ASSET_CATEGORY_IDS, String.valueOf(categoryId));
			}

			// Locale
			if (locale != null) {
				WildcardQuery anyKeywordQuery = new WildcardQueryImpl("title_" + locale, "*");
				query.add(anyKeywordQuery, BooleanClauseOccur.MUST);
			}

			return query;
		} catch (ParseException e) {
			_log.error(e);
			return null;
		}
	}

	/**
	 * Retourne les Hits correspondant aux paramètres pour le webservice des
	 * lieux
	 */
	public static Hits getPlaceWebServiceSearchHits(String className, long[] categoriesIds, String keywords,
													Locale locale) {
		return getPlaceWebServiceSearchHits(className, categoriesIds, keywords,locale, true);
	}

	/**
	 * Retourne les Hits correspondant aux paramètres pour le webservice des
	 * lieux
	 */
	public static Hits getPlaceWebServiceSearchHits(String className, long[] categoriesIds, String keywords,
													Locale locale, boolean isAndQuery) {
		try {
			SearchContext searchContext = new SearchContext();
			searchContext.setCompanyId(PortalUtil.getDefaultCompanyId());

			// Query
			Query query = SearchHelper.getPlaceWebServiceQuery(className, categoriesIds, keywords, locale, isAndQuery);

			// Recherche
			Hits hits = IndexSearcherHelperUtil.search(searchContext, query);
			_log.info("Recherche : " + hits.getSearchTime() * 1000 + "ms");
			return hits;
		} catch (SearchException e) {
			_log.error(e);
			return null;
		}
	}

	/**
	 * Retourne la requête pour le webservice des lieux
	 */
	private static Query getPlaceWebServiceQuery(String className, long[] categoriesIds, String keywords,
												 Locale locale, boolean isAndQuery) {

		try {
			BooleanQuery query = new BooleanQueryImpl();

			query.addRequiredTerm(Field.ENTRY_CLASS_NAME, className, false);
			query.addRequiredTerm(Field.STATUS, WorkflowConstants.STATUS_APPROVED);

			// Mots-clés
			if (Validator.isNotNull(keywords)) {
				BooleanQuery keywordQuery = new BooleanQueryImpl();
				MatchQuery titleQuery = new MatchQuery("title_fr_FR", keywords);
				titleQuery.setFuzziness(new Float(10));
				keywordQuery.add(titleQuery, BooleanClauseOccur.SHOULD);

				WildcardQuery titleWildcardQuery = new WildcardQueryImpl("title_fr_FR", "*" + keywords + "*");
				keywordQuery.add(titleWildcardQuery, BooleanClauseOccur.SHOULD);

				query.add(keywordQuery, BooleanClauseOccur.MUST);
			}

			// Catégories
			if (isAndQuery) {
				if (categoriesIds != null) {
					for (long categoryId : categoriesIds) {
						if (Validator.isNotNull(categoryId)) {
							BooleanQuery categoryQuery = new BooleanQueryImpl();
							categoryQuery.addRequiredTerm(Field.ASSET_CATEGORY_IDS, categoryId);
							query.add(categoryQuery, BooleanClauseOccur.MUST);
						}
					}
				}
			}
			else {
				BooleanQuery categoriesQuery = new BooleanQueryImpl();
				if (categoriesIds != null) {
					for (long categoryId : categoriesIds) {
						if (Validator.isNotNull(categoryId)) {
							BooleanQuery categoryQuery = new BooleanQueryImpl();
							categoryQuery.addRequiredTerm(Field.ASSET_CATEGORY_IDS, categoryId);
							categoriesQuery.add(categoryQuery, BooleanClauseOccur.SHOULD);
						}
					}
				}
				query.add(categoriesQuery, BooleanClauseOccur.MUST);
			}

			return query;
		} catch (

				ParseException e) {
			_log.error(e);
			return null;
		}
	}

	/**
	 * Retourne les Hits des offres en cours, qui n'ont pas encore été envoyées (emailSend=0)
	 * et qui ne sont pas uniquement internes, correspondant aux paramètres pour le scheduler offres
	 */
	public static Hits getOfferWebServiceSearchHits(String className, long[] categoriesIds, String keywords,
					Locale locale) {
		try {
			SearchContext searchContext = new SearchContext();
			searchContext.setCompanyId(PortalUtil.getDefaultCompanyId());

			// Query
			Query query = SearchHelper.getOfferWebServiceQuery(className, categoriesIds, keywords, locale);

			// Recherche
			Hits hits = IndexSearcherHelperUtil.search(searchContext, query);
			_log.info("Recherche : " + hits.getSearchTime() * 1000 + "ms");
			return hits;
		} catch (SearchException e) {
			_log.error(e);
			return null;
		}
	}

	/**
	 * Retourne la requête pour le scheduler des offres
	 */
	private static Query getOfferWebServiceQuery(String className, long[] categoriesIds, String keywords,
					Locale locale) {

		try {
			BooleanQuery query = new BooleanQueryImpl();

			query.addRequiredTerm(Field.ENTRY_CLASS_NAME, className, false);
			query.addRequiredTerm(Field.STATUS, WorkflowConstants.STATUS_APPROVED);

			// Mots-clés
			if (Validator.isNotNull(keywords)) {
				BooleanQuery keywordQuery = new BooleanQueryImpl();

				// Fuzzy sur titre
				MatchQuery titleQuery = new MatchQuery(Field.TITLE + '_' + locale, keywords);
				titleQuery.setAnalyzer("strasbourg_analyzer");
				keywordQuery.add(titleQuery, BooleanClauseOccur.SHOULD);

				// Titre sans analyzer
				MatchQuery titleQueryWithoutAnalyzer = new MatchQuery(Field.TITLE + "_" + locale, keywords);
				titleQueryWithoutAnalyzer.setFuzziness(new Float(2));
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

				// Content
				MatchQuery contentQuery = new MatchQuery(Field.CONTENT + "_" + locale, keywords);
				keywordQuery.add(contentQuery, BooleanClauseOccur.SHOULD);

				// Fuzzy sur catégorie
				MatchQuery categoryKeywordQuery = new MatchQuery(Field.ASSET_CATEGORY_TITLES, keywords);
				// titleQuery.setFuzziness(new Float(10));
				keywordQuery.add(categoryKeywordQuery, BooleanClauseOccur.SHOULD);

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
			}

			// Catégories
			if (categoriesIds != null) {
				for (long categoryId : categoriesIds) {
					if (Validator.isNotNull(categoryId)) {
						BooleanQuery categoryQuery = new BooleanQueryImpl();
						categoryQuery.addRequiredTerm(Field.ASSET_CATEGORY_IDS, categoryId);
						query.add(categoryQuery, BooleanClauseOccur.MUST);
					}
				}
			}

			// Dates actives
			LocalDateTime today = LocalDateTime.now();
			BooleanQuery datesQuery = new BooleanQueryImpl();
			String dateString = String.format("%04d", today.getYear())
					+ String.format("%02d", today.getMonth().getValue())
					+ String.format("%02d", today.getDayOfMonth()) + "000000";
			datesQuery.addRangeTerm("dates", dateString, dateString);
			query.add(datesQuery, BooleanClauseOccur.MUST);

			// pas encore envoyées
			query.addRequiredTerm("emailSend", 0);

			// on ne veut pas les offres internes uniquement
			BooleanQuery categoryQuery = new BooleanQueryImpl();
			categoryQuery.addRequiredTerm(Field.ASSET_CATEGORY_TITLES, "Interne uniquement");
			query.add(categoryQuery, BooleanClauseOccur.MUST_NOT);


			return query;
		} catch (ParseException e) {
			_log.error(e);
			return null;
		}
	}

	/**
	 * Retourne les Hits correspondant aux paramètres pour le webservice des
	 * événements
	 */
	public static Hits getEventsAgendaWebServiceSearchHits(String className, List<Long[]> categoriesIds,
														   String[] tagsNames) {
		try {
			SearchContext searchContext = new SearchContext();
			searchContext.setCompanyId(PortalUtil.getDefaultCompanyId());
			// Query
			Query query = SearchHelper.getEventsAgendaWebServiceQuery(className, categoriesIds, tagsNames);

			// Recherche
			Hits hits = IndexSearcherHelperUtil.search(searchContext, query);
			_log.info("Recherche : " + hits.getSearchTime() * 1000 + "ms");
			return hits;
		} catch (SearchException e) {
			_log.error(e);
			return null;
		}
	}


	/**
	 * Retourne la requête pour le webservice des agendas
	 */
	private static Query getEventsAgendaWebServiceQuery(String className, List<Long[]> categoriesIds,
														String[] tagsNames) {

		try {
			// Construction de la requète
			BooleanQuery superQuery = new BooleanQueryImpl();
			BooleanQuery query = new BooleanQueryImpl();

			// ClassNames
			BooleanQuery classNameQuery = new BooleanQueryImpl();
			classNameQuery.addExactTerm(Field.ENTRY_CLASS_NAME, className);
			query.add(classNameQuery, BooleanClauseOccur.MUST);

			// Statut et visibilité
			query.addRequiredTerm(Field.STATUS, WorkflowConstants.STATUS_APPROVED);
			query.addRequiredTerm("visible", true);

			// Catégories
			// On fait un "ou" entre les catégories d'un même vocabulaire et un
			// "et" entre les différents vocabulaires
			for (Long[] categoriesIdsGroupByVocabulary : categoriesIds) {
				BooleanQuery vocabularyQuery = new BooleanQueryImpl();
				for (long categoryId : categoriesIdsGroupByVocabulary) {
					BooleanQuery categoryQuery = new BooleanQueryImpl();
					categoryQuery.addRequiredTerm(Field.ASSET_CATEGORY_IDS, String.valueOf(categoryId));
					vocabularyQuery.add(categoryQuery, BooleanClauseOccur.SHOULD);
				}
				query.add(vocabularyQuery, BooleanClauseOccur.MUST);
			}

			//  tags
			// On fait un "ou" entre les tags
			if (Validator.isNotNull(tagsNames) && tagsNames.length > 0) {
				BooleanQuery tagsQuery = new BooleanQueryImpl();
				for (String tagName : tagsNames) {
					BooleanQuery tagQuery = new BooleanQueryImpl();
					tagQuery.addExactTerm(Field.ASSET_TAG_NAMES, String.valueOf(tagName));
					tagsQuery.add(tagQuery, BooleanClauseOccur.SHOULD);
				}
				query.add(tagsQuery, BooleanClauseOccur.MUST);
			}

			superQuery.add(query, BooleanClauseOccur.SHOULD);

			return superQuery;
		} catch (ParseException e) {
			_log.error(e);
			return null;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(SearchHelper.class.getName());
}
