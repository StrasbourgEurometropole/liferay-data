package eu.strasbourg.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Locale;

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

public class SearchHelper {

	/**
	 * Retourne les Hits correspondant aux paramètres pour les portlets du BO
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
			// Si il y a une recherche par mot clé on trie par pertinence
			if (Validator.isNotNull(keywords)) {
				sortField = "_score";
				isSortDesc = true;
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
			_log.error(e);
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
	public static Hits getGlobalSearchHits(SearchContext searchContext,
		String[] classNames, long groupId, long globalGroupId,
		boolean globalScope, String keywords, boolean dateField,
		String dateFieldName, LocalDate fromDate, LocalDate toDate,
		List<Long[]> categoriesIds, List<Long[]> prefilterCategoriesIds,
		String[] prefilterTagsNames, Locale locale, int start, int end,
		String sortField, boolean isSortDesc) {
		try {
			// Pagination
			searchContext.setStart(start);
			searchContext.setEnd(end);

			// Query
			Query query = SearchHelper.getGlobalSearchQuery(classNames, groupId,
				globalGroupId, globalScope, keywords, dateField, dateFieldName,
				fromDate, toDate, categoriesIds, prefilterCategoriesIds,
				prefilterTagsNames, locale);

			// Ordre
			Sort sort = SortFactoryUtil.create(sortField, isSortDesc);
			System.out.println(sort);
			searchContext.setSorts(sort);

			// Recherche
			Hits hits = IndexSearcherHelperUtil.search(searchContext, query);
			_log.info(
				"Recherche front-end : " + hits.getSearchTime() * 1000 + "ms");
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
	public static long getGlobalSearchCount(SearchContext searchContext,
		String[] classNames, long groupId, long globalGroupId,
		boolean globalScope, String keywords, boolean dateField,
		String dateFieldName, LocalDate fromDate, LocalDate toDate,
		List<Long[]> categoriesIds, List<Long[]> prefilterCategoriesIds,
		String[] prefilterTagsNames, Locale locale) {
		try {
			// Query
			Query query = SearchHelper.getGlobalSearchQuery(classNames, groupId,
				globalGroupId, globalScope, keywords, dateField, dateFieldName,
				fromDate, toDate, categoriesIds, prefilterCategoriesIds,
				prefilterTagsNames, locale);
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
	private static Query getGlobalSearchQuery(String[] classNames, long groupId,
		long globalGroupId, boolean globalScope, String keywords,
		boolean dateField, String dateFieldName, LocalDate fromDate,
		LocalDate toDate, List<Long[]> categoriesIds,
		List<Long[]> prefilterCategoriesIds, String[] prefilterTagsNames,
		Locale locale) {
		try {
			// Construction de la requète
			BooleanQuery query = new BooleanQueryImpl();

			// ClassNames
			BooleanQuery classNameQuery = new BooleanQueryImpl();
			for (String className : classNames) {
				if (Validator.isNotNull(className)) {
					// Cas général
					if (!className.contains("JournalArticle")) {
						classNameQuery.addTerm(Field.ENTRY_CLASS_NAME,
							className, false, BooleanClauseOccur.SHOULD);
					}
					// Cas où on a un journalArticle (on vérifie que c'est la
					// dernière version)
					else {
						BooleanQuery journalArticleQuery = new BooleanQueryImpl();
						journalArticleQuery.addTerm(Field.ENTRY_CLASS_NAME,
							className, false, BooleanClauseOccur.MUST);
						journalArticleQuery.addRequiredTerm("head", true);
						classNameQuery.add(journalArticleQuery,
							BooleanClauseOccur.SHOULD);
					}
				}
			}
			query.add(classNameQuery, BooleanClauseOccur.MUST);

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
			query.addRequiredTerm(Field.STATUS,
				WorkflowConstants.STATUS_APPROVED);
			query.addRequiredTerm("visible", true);

			BooleanQuery publicationDateQuery = new BooleanQueryImpl();
			publicationDateQuery.addRangeTerm(Field.PUBLISH_DATE + "_sortable",
				0, Timestamp.valueOf(LocalDateTime.now()).toInstant()
					.toEpochMilli());
			query.add(publicationDateQuery, BooleanClauseOccur.MUST);

			// Mots-clés
			if (Validator.isNotNull(keywords)) {
				BooleanQuery keywordQuery = new BooleanQueryImpl();

				// Fuzzy sur titre
				MatchQuery titleQuery = new MatchQuery(
					Field.TITLE + '_' + locale, keywords);
				titleQuery.setFuzziness(new Float(10));
				titleQuery.setAnalyzer("strasbourg_analyzer");
				keywordQuery.add(titleQuery, BooleanClauseOccur.SHOULD);

				// Wildcard sur titre
				WildcardQuery titleWildcardQuery = new WildcardQueryImpl(
					Field.TITLE + "_" + locale, "*" + keywords + "*");
				keywordQuery.add(titleWildcardQuery, BooleanClauseOccur.SHOULD);

				// Fuzzy sur description (tous les champs indexables de nos
				// entités
				// sont dans ce champ)
				MatchQuery descriptionQuery = new MatchQuery(
					Field.DESCRIPTION + "_" + locale, keywords);
				descriptionQuery.setFuzziness(new Float(10));
				descriptionQuery.setAnalyzer("strasbourg_analyzer");
				keywordQuery.add(descriptionQuery, BooleanClauseOccur.SHOULD);

				// Pour les fichiers on recherche dans le champ "title" sans la
				// locale car il est indexé uniquement comme cela
				BooleanQuery fileQuery = new BooleanQueryImpl();
				MatchQuery fileTitleQuery = new MatchQuery(Field.TITLE,
					keywords);
				fileTitleQuery.setFuzziness(new Float(10));
				fileQuery.add(fileTitleQuery, BooleanClauseOccur.MUST);
				fileQuery.addTerm(Field.ENTRY_CLASS_NAME,
					DLFileEntry.class.getName(), false,
					BooleanClauseOccur.MUST);
				keywordQuery.add(fileQuery, BooleanClauseOccur.SHOULD);

				// Fuzzy sur content (tous les champs indexables des structures
				// de
				// CW et de D&M sont dans ce champ)
				MatchQuery contentQuery = new MatchQuery(
					Field.CONTENT + "_" + locale, keywords);
				contentQuery.setFuzziness(new Float(10));
				keywordQuery.add(contentQuery, BooleanClauseOccur.SHOULD);

				// Fuzzy sur catégorie
				MatchQuery categoryKeywordQuery = new MatchQuery(
					Field.ASSET_CATEGORY_TITLES, keywords);
				titleQuery.setFuzziness(new Float(10));
				keywordQuery.add(categoryKeywordQuery,
					BooleanClauseOccur.SHOULD);

				// Fuzzy sur tags
				MatchQuery tagKeywordQuery = new MatchQuery(
					Field.ASSET_TAG_NAMES, keywords);
				titleQuery.setFuzziness(new Float(10));
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

				WildcardQuery anyKeywordWildcardQuery = new WildcardQueryImpl(
					"title_" + locale, "*");

				anyKeywordQuery.addTerm(Field.ENTRY_CLASS_NAME,
					DLFileEntry.class.getName(), false,
					BooleanClauseOccur.SHOULD);
				anyKeywordQuery.add(anyKeywordWildcardQuery,
					BooleanClauseOccur.SHOULD);

				query.add(anyKeywordQuery, BooleanClauseOccur.MUST);
			}

			// Catégories
			// On fait un "ou" entre les catégories d'un même vocabulaire et un
			// "et" entre les différents vocabulaires
			for (Long[] categoriesIdsGroupByVocabulary : categoriesIds) {
				BooleanQuery vocabularyQuery = new BooleanQueryImpl();
				for (long categoryId : categoriesIdsGroupByVocabulary) {
					BooleanQuery categoryQuery = new BooleanQueryImpl();
					categoryQuery.addRequiredTerm(Field.ASSET_CATEGORY_IDS,
						String.valueOf(categoryId));
					vocabularyQuery.add(categoryQuery,
						BooleanClauseOccur.SHOULD);
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
						categoryQuery.addRequiredTerm(Field.ASSET_CATEGORY_IDS,
							String.valueOf(categoryId));
						vocabularyQuery.add(categoryQuery,
							BooleanClauseOccur.SHOULD);
					}
				}
				query.add(vocabularyQuery, BooleanClauseOccur.MUST);
			}

			// Préfiltre tags
			if (Validator.isNotNull(prefilterTagsNames)
				&& prefilterTagsNames.length > 0) {
				BooleanQuery tagsQuery = new BooleanQueryImpl();
				for (String tagName : prefilterTagsNames) {
					BooleanQuery tagQuery = new BooleanQueryImpl();
					tagQuery.addRequiredTerm(Field.ASSET_TAG_NAMES,
						String.valueOf(tagName));
					tagsQuery.add(tagQuery, BooleanClauseOccur.SHOULD);
				}
				query.add(tagsQuery, BooleanClauseOccur.MUST);
			}

			// Dates
			if (dateField) {
				if (dateFieldName.equals("dates_Number_sortable")) {
					BooleanQuery datesQuery = new BooleanQueryImpl();

					String fromDateString = String.format("%04d",
						fromDate.getYear())
						+ String.format("%02d", fromDate.getMonth().getValue())
						+ String.format("%02d", fromDate.getDayOfMonth())
						+ "000000";
					String toDateString = String.format("%04d",
						toDate.getYear())
						+ String.format("%02d", toDate.getMonth().getValue())
						+ String.format("%02d", toDate.getDayOfMonth())
						+ "000000";

					datesQuery.addRangeTerm("dates", fromDateString,
						toDateString);
					query.add(datesQuery, BooleanClauseOccur.MUST);
				} else {
					BooleanQuery datesQuery = new BooleanQueryImpl();
					long fromDateEpoch = fromDate.atStartOfDay()
						.toEpochSecond(ZoneOffset.UTC) * 1000;
					long toDateEpoch = toDate.plusDays(1).atStartOfDay()
						.toEpochSecond(ZoneOffset.UTC) * 1000;
					datesQuery.addRangeTerm(dateFieldName, fromDateEpoch,
						toDateEpoch);
					query.add(datesQuery, BooleanClauseOccur.MUST);
				}

			}

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
	public static Hits getEventWebServiceSearchHits(String className,
		LocalDate date, long categoryId, Locale locale) {
		try {
			SearchContext searchContext = new SearchContext();
			searchContext.setCompanyId(PortalUtil.getDefaultCompanyId());
			// Query
			Query query = SearchHelper.getEventWebServiceQuery(className, date,
				categoryId, locale);

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
	private static Query getEventWebServiceQuery(String className,
		LocalDate date, long categoryId, Locale locale) {

		try {
			BooleanQuery query = new BooleanQueryImpl();

			query.addRequiredTerm(Field.ENTRY_CLASS_NAME, className, false);
			query.addRequiredTerm(Field.STATUS,
				WorkflowConstants.STATUS_APPROVED);

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
				query.addRequiredTerm(Field.ASSET_CATEGORY_IDS,
					String.valueOf(categoryId));
			}

			// Locale
			if (locale != null) {
				WildcardQuery anyKeywordQuery = new WildcardQueryImpl(
					"title_" + locale, "*");
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
	public static Hits getPlaceWebServiceSearchHits(String className,
		long[] categoriesIds, String keywords, Locale locale) {
		try {
			SearchContext searchContext = new SearchContext();
			searchContext.setCompanyId(PortalUtil.getDefaultCompanyId());

			// Query
			Query query = SearchHelper.getPlaceWebServiceQuery(className,
				categoriesIds, keywords, locale);

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
	private static Query getPlaceWebServiceQuery(String className,
		long[] categoriesIds, String keywords, Locale locale) {

		try {
			BooleanQuery query = new BooleanQueryImpl();

			query.addRequiredTerm(Field.ENTRY_CLASS_NAME, className, false);
			query.addRequiredTerm(Field.STATUS,
				WorkflowConstants.STATUS_APPROVED);

			// Mots clés
			if (Validator.isNotNull(keywords)) {
				WildcardQuery titleWildcardQuery = new WildcardQueryImpl(
					Field.TITLE + '_' + locale, "*" + keywords + "*");
				query.add(titleWildcardQuery, BooleanClauseOccur.MUST);
			}

			// Catégories
			if (categoriesIds != null) {
				for (long categoryId : categoriesIds) {
					if (Validator.isNotNull(categoryId)) {
						BooleanQuery categoryQuery = new BooleanQueryImpl();
						categoryQuery.addRequiredTerm(Field.ASSET_CATEGORY_IDS,
							categoryId);
						query.add(categoryQuery, BooleanClauseOccur.MUST);
					}
				}
			}

			return query;
		} catch (

		ParseException e) {
			_log.error(e);
			return null;
		}
	}

	private static final Log _log = LogFactoryUtil
		.getLog(SearchHelper.class.getName());
}
