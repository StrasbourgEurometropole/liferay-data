package eu.strasbourg.utils;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.search.aggregation.Aggregations;
import com.liferay.portal.search.aggregation.bucket.FiltersAggregation;
import com.liferay.portal.search.groupby.GroupByRequest;
import com.liferay.portal.search.groupby.GroupByRequestFactory;
import com.liferay.portal.search.groupby.GroupByResponse;
import com.liferay.portal.search.hits.SearchHits;
import com.liferay.portal.search.query.BooleanQuery;
import com.liferay.portal.search.query.FunctionScoreQuery;
import com.liferay.portal.search.query.MatchQuery;
import com.liferay.portal.search.query.Operator;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.query.Query;
import com.liferay.portal.search.query.RangeTermQuery;
import com.liferay.portal.search.query.TermQuery;
import com.liferay.portal.search.query.WildcardQuery;
import com.liferay.portal.search.query.function.score.RandomScoreFunction;
import com.liferay.portal.search.query.function.score.ScoreFunctions;
import com.liferay.portal.search.searcher.SearchRequest;
import com.liferay.portal.search.searcher.SearchRequestBuilder;
import com.liferay.portal.search.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.searcher.SearchResponse;
import com.liferay.portal.search.searcher.Searcher;
import com.liferay.portal.search.sort.FieldSort;
import com.liferay.portal.search.sort.Sort;
import com.liferay.portal.search.sort.SortOrder;
import com.liferay.portal.search.sort.Sorts;
import eu.strasbourg.utils.bean.AssetPrefilter;
import eu.strasbourg.utils.bean.AssetType;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component(
		immediate = true,
		property = {},
		service = SearchHelperV2.class)
public class SearchHelperV2{

	/**
	 * Retourne les Hits correspondant aux paramètres pour les moteurs de
	 * recherche d'assets V2
	 *
	 * @param searchContext
	 *            Utilisé pour récupérer le companyId
	 * @param assetTypes
	 *            Liste d'AssetType _className, scopeGroupIDs, structureID, assetPrefilterList_ (utilisé dans la query)
	 * @param isDisplayField
	 *            Si la recherche se fait par rapport à des dates (utilisé dans la query)
	 * @param filterField
	 *            Champs au format date sur lequel le filtre se fait (utilisé dans la query)
	 * @param seed
	 *            int random pour le tri aléatoire
	 * @param sortingFieldsAndTypes
	 *            Map contenant les champs et les types des tri
//	 * @param categoriesIdsForGroupBy
//	 *            Catégories du vocabulaire sur lequel on veut le regroupement
	 * @param keywords
	 *            Mots clés de recherche (utilisé dans la query)
	 * @param fromDate
	 *            Date de début, sous le format "yyyyMMdd000000" (utilisé dans la query)
	 * @param toDate
	 *            Date de fin, sous le format "yyyyMMdd000000" (utilisé dans la query)
	 * @param categoriesIds
	 *            Liste de tableaux d'ids de catégories (provenant de la
	 *            recherche utilisateur) - un OU est effectué entre chaque id de
	 *            chaque tableau, et UN entre chaque liste (utilisé dans la query)
	 * @param idSIGPlace
	 * 			  L'id SIG du lieu (utilisé dans la query)
	 * @param classNamesSelected
	 *            Liste des classNames sélectionnés par l'utilisateur (utilisé dans la query)
	 * @param locale
	 *            Locale utile pour la recherche de mots clé (utilisé dans la query)
	 * @param start
	 *            Pagination : début
	 * @param end
	 *            Pagination : fin
	 *
	 * @return Les hits renvoyés par le moteur de recherche
	 */
	public SearchHits  getGlobalSearchHitsV2(SearchContext searchContext, List<AssetType> assetTypes, Boolean isDisplayField,
											 String filterField, int seed, Map<String, String> sortingFieldsAndTypes,
											 /*long[] categoriesIdsForGroupBy, */String keywords, LocalDate fromDate,
											 LocalDate toDate, List<Long[]> categoriesIds, String idSIGPlace,
											 List<String> classNamesSelected, Locale locale, int start, int end) {

		// Query
		Query query = getGlobalSearchV2Query(assetTypes, isDisplayField, filterField, /*categoriesIdsForGroupBy,*/
				keywords, fromDate, toDate, categoriesIds, idSIGPlace, classNamesSelected, locale);

		SearchRequestBuilder searchRequestBuilder = searchRequestBuilderFactory.builder();

		// Pagination
		searchRequestBuilder.emptySearchEnabled(true);
		searchRequestBuilder.withSearchContext(
			sc -> {
				sc.setCompanyId(searchContext.getCompanyId());
				sc.setStart(start);
				sc.setEnd(end);
				/*if(Validator.isNotNull(groupBy) && groupBy > 0) {
					// Regroupement
					GroupBy groupBy1 = new GroupBy(Field.ENTRY_CLASS_NAME);
					sc.setGroupBy(groupBy1);
				}else {
					// Tri
					com.liferay.portal.kernel.search.Sort[] sortArray = new com.liferay.portal.kernel.search.Sort[sortingFieldsAndTypes.size()];
					Iterator iterator = sortingFieldsAndTypes.entrySet().iterator();
					int i = 0;
					while (iterator.hasNext()) {
						Map.Entry entry = (Map.Entry) iterator.next();
						com.liferay.portal.kernel.search.Sort fieldSort = new com.liferay.portal.kernel.search.Sort((String) entry.getKey(), entry.getValue().equals("DESC"));
						sortArray[i] = fieldSort;
						i++;
					}
					sc.setSorts(sortArray);
				}*/
			}
		);

		searchRequestBuilder.from(start);
		searchRequestBuilder.size(end - start);

		// Regroupement
//		if(categoriesIdsForGroupBy[0] != 0){
//			GroupByRequest groupByRequest;
//			if(categoriesIdsForGroupBy[0] > 0){
//				// regroupement sur les catégories
//				groupByRequest = groupByRequestFactory.getGroupByRequest(Field.ASSET_CATEGORY_IDS);
//			}else{
//				// regroupement par type d'asset
//				groupByRequest = groupByRequestFactory.getGroupByRequest(Field.ENTRY_CLASS_NAME);
//			}
//			searchRequestBuilder = searchRequestBuilder.groupByRequests(groupByRequest);
//		}

		// Tri
		if (Validator.isNotNull(seed) && seed > 0) {
			// Tri aléatoire
			RandomScoreFunction randomScoreFunction = scoreFunctions.random();
			randomScoreFunction.setSeed(seed);
			randomScoreFunction.setField("entryClassPK");
			FunctionScoreQuery functionScoreQuery = queries.functionScore(query);
			functionScoreQuery.addFilterQueryScoreFunctionHolder(query, randomScoreFunction);
			query = functionScoreQuery;
		}else{
			Sort[] sortArray = new Sort[sortingFieldsAndTypes.size()];
			int i = 0;
			Iterator iterator = sortingFieldsAndTypes.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry entry = (Map.Entry) iterator.next();
				FieldSort fieldSort = sorts.field((String)entry.getKey(), SortOrder.valueOf(String.valueOf(entry.getValue()).toUpperCase()));
				sortArray[i] = fieldSort;
				i++;
			}
			searchRequestBuilder = searchRequestBuilder.sorts(sortArray);
		}

		SearchRequest searchRequest = searchRequestBuilder.query(query).build();
		SearchResponse searchResponse = searcher.search(searchRequest);

		/*
		Tentative d'exploitation du GroupBy

		List<GroupByResponse> buckets = searchResponse.getGroupByResponses();
		Le problème c'est que le groupByRequests prend harbitrairement 10 catégories pour former des "bucket" de 3 documents
		en dehors de la query principale.
		Inexploitable en l'état.

		if(buckets.size() >= 1){
			GroupByResponse gbr = buckets.get(0);
			Map<String, Hits> hits = gbr.getHitsMap();

			for (String key : hits.keySet()) {
				if(key.equals("11043552") || key.equals("11043559")) {
					Hits groupHits = hits.get(key);
					Document[] docs = groupHits.getDocs();

					_log.info(key + " " + groupHits.getLength());
				}
			}
		}

		//To debug query
		String queryString = searchResponse.getRequestString();*/

		// Recherche
		SearchHits searchHits = searchResponse.getSearchHits();
		_log.info("Recherche front-end : " + searchHits.getSearchTime() * 1000 + "ms");
		return searchHits;
	}

	/**
	 * Retourne la requête à exécuter correspondant aux paramètres pour le searchAssetV2
	 */
	private Query getGlobalSearchV2Query(List<AssetType> assetTypes,
										 Boolean isDisplayField, String filterField, /*long[] categoriesIdsForGroupBy,*/
										 String keywords, LocalDate fromDate, LocalDate toDate, List<Long[]> categoriesIds,
										 String placeSigId, List<String> filterClassNames, Locale locale) {
		// Construction de la requète
		BooleanQuery superQuery = queries.booleanQuery();
		BooleanQuery query = queries.booleanQuery();

		//Asset type
		BooleanQuery assetTypesQuery = queries.booleanQuery();
		if(filterClassNames.size() > 0) {
			for (AssetType assetType : assetTypes) {
				if (Validator.isNotNull(assetType)) {
					if (Validator.isNotNull(assetType.getClassName())) {
						// on vérifie si le className est sélectionné par l'utilisateur
						if (filterClassNames.contains(assetType.getClassName())) {
							BooleanQuery assetTypeQuery = queries.booleanQuery();
							// ClassNames
							if (assetType.getClassName().equals("searchJournalArticle")) {
								// Cas d'un journalArticle
								TermQuery journalArticleClassNameQuery = queries.term(Field.ENTRY_CLASS_NAME, JournalArticle.class.getName());
								// on vérifie que c'est la dernière version
								TermQuery journalArticleHeadQuery = queries.term("head", true);
								assetTypeQuery.addMustQueryClauses(journalArticleClassNameQuery, journalArticleHeadQuery);
								// ajout de la structure du contenu web
								if (Validator.isNotNull(assetType.getStructureID())) {
									TermQuery structureQuery = queries.term(Field.CLASS_TYPE_ID, assetType.getStructureID());
									assetTypeQuery.addMustQueryClauses(structureQuery);
								}

							} else {
								if (assetType.getClassName().equals("searchDocument")) {
									// Cas d'un fichier
									TermQuery classNameQuery = queries.term(Field.ENTRY_CLASS_NAME, DLFileEntry.class.getName());
									assetTypeQuery.addMustQueryClauses(classNameQuery);
								} else {
									if (assetType.getClassName().equals("searchDemarche")) {
										// Cas d'une procédures/démarches
										BooleanQuery procedureQuery = queries.booleanQuery();
										TermQuery typeQuery = queries.term("type", "procedure");
										TermQuery titleQuery = queries.term("title", keywords);
										procedureQuery.addShouldQueryClauses(typeQuery, titleQuery);
										// On rajoute la condition à la requête principale
										superQuery.addShouldQueryClauses(procedureQuery);
									} else {
										// Cas général
										TermQuery classNameQuery = queries.term(Field.ENTRY_CLASS_NAME, assetType.getClassName());
										assetTypeQuery.addMustQueryClauses(classNameQuery);
									}
								}
							}

							// Groups
							if (assetType.getScopeGroupIDs().size() > 0) {
								BooleanQuery groupsQuery = queries.booleanQuery();
								for (Long groupId : assetType.getScopeGroupIDs()) {
									TermQuery groupQuery = queries.term(Field.GROUP_ID, groupId);
									groupsQuery.addShouldQueryClauses(groupQuery);
								}
								assetTypeQuery.addMustQueryClauses(groupsQuery);
							}

							// Préfiltres
							if (assetType.getAssetPrefilterList().size() > 0) {
								BooleanQuery prefiltersQuery = queries.booleanQuery();
								for (AssetPrefilter prefilter : assetType.getAssetPrefilterList()) {
									if (prefilter.getType().equals("tags")) {
										BooleanQuery tagsQuery = queries.booleanQuery();
										for (Long tagId : prefilter.getCategoryOrTagIdList()) {
											TermQuery tagQuery = queries.term(Field.ASSET_TAG_IDS, String.valueOf(tagId));
											if (prefilter.getOperator().equals("all")) {
												tagsQuery.addMustQueryClauses(tagQuery);
											} else {
												tagsQuery.addShouldQueryClauses(tagQuery);
											}
										}
										// si true alors contient
										if (prefilter.isIncludeOrExclude()) {
											prefiltersQuery.addMustQueryClauses(tagsQuery);
										} else {
											prefiltersQuery.addMustNotQueryClauses(tagsQuery);
										}
									} else {
										BooleanQuery categoriesQuery = queries.booleanQuery();
										for (Long categoryId : prefilter.getCategoryOrTagIdList()) {
											TermQuery categoryQuery = queries.term(Field.ASSET_CATEGORY_IDS, String.valueOf(categoryId));
											if (prefilter.getOperator().equals("all")) {
												categoriesQuery.addMustQueryClauses(categoryQuery);
											} else {
												categoriesQuery.addShouldQueryClauses(categoryQuery);
											}
										}
										// si true alors contient
										if (prefilter.isIncludeOrExclude()) {
											prefiltersQuery.addMustQueryClauses(categoriesQuery);
										} else {
											prefiltersQuery.addMustNotQueryClauses(categoriesQuery);
										}
									}
								}
								assetTypeQuery.addMustQueryClauses(prefiltersQuery);
							}
							assetTypesQuery.addShouldQueryClauses(assetTypeQuery);
						}
					}
				}
			}
		}else{
			BooleanQuery assetTypeQuery = queries.booleanQuery();
			TermQuery classNameQuery = queries.term(Field.ENTRY_CLASS_NAME, "");
			assetTypeQuery.addMustQueryClauses(classNameQuery);
			assetTypesQuery.addShouldQueryClauses(assetTypeQuery);
		}
		query.addMustQueryClauses(assetTypesQuery);

		// Statut et visibilité
		TermQuery statusQuery = queries.term(Field.STATUS, WorkflowConstants.STATUS_APPROVED);
		TermQuery visibilityQuery = queries.term("visible", true);
		query.addMustQueryClauses(statusQuery, visibilityQuery);


		// Date de publication
		RangeTermQuery publicationDateQuery = queries.rangeTerm(Field.PUBLISH_DATE + "_sortable", true, true, 0, Timestamp.valueOf(LocalDateTime.now()).toInstant().toEpochMilli());
		query.addMustQueryClauses(publicationDateQuery);

		// Mots-clés
		if (Validator.isNotNull(keywords)) {
			BooleanQuery keywordQuery = queries.booleanQuery();

			// Fuzzy sur titre
			MatchQuery titleQuery = queries.match(Field.TITLE + '_' + locale, keywords);
			titleQuery.setOperator(Operator.OR);
			titleQuery.setAnalyzer("strasbourg_analyzer");
			titleQuery.setPrefixLength(0);
			titleQuery.setMaxExpansions(50);
			titleQuery.setFuzzyTranspositions(true);
			titleQuery.setLenient(false);
			titleQuery.setZeroTermsQuery(com.liferay.portal.search.query.MatchQuery.ZeroTermsQuery.NONE);
//					"auto_generate_synonyms_phrase_query" : true,
			keywordQuery.addShouldQueryClauses(titleQuery);

			// Titre sans analyzer
			MatchQuery titleQueryWithoutAnalyzer = queries.match(Field.TITLE + "_" + locale, keywords);
			titleQueryWithoutAnalyzer.setOperator(Operator.OR);
			titleQueryWithoutAnalyzer.setFuzziness(2f);
			titleQueryWithoutAnalyzer.setPrefixLength(0);
			titleQueryWithoutAnalyzer.setMaxExpansions(50);
			titleQueryWithoutAnalyzer.setFuzzyTranspositions(true);
			titleQueryWithoutAnalyzer.setLenient(false);
			titleQueryWithoutAnalyzer.setZeroTermsQuery(com.liferay.portal.search.query.MatchQuery.ZeroTermsQuery.NONE);
//					"auto_generate_synonyms_phrase_query" : true,
			titleQueryWithoutAnalyzer.setBoost(3.0f);
			FunctionScoreQuery functionScoreQueryTitleQueryWithoutAnalyzer =
					queries.functionScore(titleQueryWithoutAnalyzer);
			functionScoreQueryTitleQueryWithoutAnalyzer.setBoost(3.0f);
			keywordQuery.addShouldQueryClauses(functionScoreQueryTitleQueryWithoutAnalyzer);
			keywordQuery.addShouldQueryClauses(titleQueryWithoutAnalyzer);

			// Wildcard sur titre
			WildcardQuery titleWildcardQuery = queries.wildcard(Field.TITLE + "_" + locale,
					"*" + keywords + "*");
			titleWildcardQuery.setBoost(30.0f);
			FunctionScoreQuery functionScoreQueryTitleWildcard =
					queries.functionScore(titleWildcardQuery);
			functionScoreQueryTitleWildcard.setBoost(30.0f);
			keywordQuery.addShouldQueryClauses(functionScoreQueryTitleWildcard);
			keywordQuery.addShouldQueryClauses(titleWildcardQuery);

			// Description
			MatchQuery descriptionQuery = queries.match(Field.DESCRIPTION + "_" + locale, keywords);
			descriptionQuery.setOperator(Operator.OR);
			descriptionQuery.setPrefixLength(0);
			descriptionQuery.setMaxExpansions(50);
			descriptionQuery.setFuzzyTranspositions(true);
			descriptionQuery.setLenient(false);
			descriptionQuery.setZeroTermsQuery(com.liferay.portal.search.query.MatchQuery.ZeroTermsQuery.NONE);
//					"auto_generate_synonyms_phrase_query" : true,
			keywordQuery.addShouldQueryClauses(descriptionQuery);

			// Pour les fichiers on recherche dans le champ "title" sans la
			// locale car il est indexé uniquement comme cela
			MatchQuery fileTitleQuery = queries.match(Field.TITLE, keywords);
			fileTitleQuery.setOperator(Operator.OR);
			// fileTitleQuery.setFuzziness(10f);
			fileTitleQuery.setPrefixLength(0);
			fileTitleQuery.setMaxExpansions(50);
			fileTitleQuery.setFuzzyTranspositions(true);
			fileTitleQuery.setLenient(false);
			fileTitleQuery.setZeroTermsQuery(com.liferay.portal.search.query.MatchQuery.ZeroTermsQuery.NONE);
//			"auto_generate_synonyms_phrase_query" : true,

			TermQuery classNameQuery = queries.term(Field.ENTRY_CLASS_NAME, DLFileEntry.class.getName());

			BooleanQuery fileQuery = queries.booleanQuery();
			fileQuery.addMustQueryClauses(fileTitleQuery, classNameQuery);
			keywordQuery.addShouldQueryClauses(fileQuery);

			// Fuzzy sur content (tous les champs indexables des structures
			// de CW et de D&M sont dans ce champ)
			MatchQuery contentQuery = queries.match(Field.CONTENT + "_" + locale, keywords);
			contentQuery.setOperator(Operator.OR);
			// contentQuery.setFuzziness(1f);
			contentQuery.setPrefixLength(0);
			contentQuery.setMaxExpansions(50);
			contentQuery.setFuzzyTranspositions(true);
			contentQuery.setLenient(false);
			contentQuery.setZeroTermsQuery(com.liferay.portal.search.query.MatchQuery.ZeroTermsQuery.NONE);
//			"auto_generate_synonyms_phrase_query" : true,
			keywordQuery.addShouldQueryClauses(contentQuery);

			// Fuzzy sur catégorie
			MatchQuery categoryKeywordQuery = queries.match(Field.ASSET_CATEGORY_TITLES, keywords);
			categoryKeywordQuery.setOperator(Operator.OR);
			// categoryKeywordQuery.setFuzziness(10f);
			categoryKeywordQuery.setPrefixLength(0);
			categoryKeywordQuery.setMaxExpansions(50);
			categoryKeywordQuery.setFuzzyTranspositions(true);
			categoryKeywordQuery.setLenient(false);
			categoryKeywordQuery.setZeroTermsQuery(com.liferay.portal.search.query.MatchQuery.ZeroTermsQuery.NONE);
//			"auto_generate_synonyms_phrase_query" : true,
			keywordQuery.addShouldQueryClauses(categoryKeywordQuery);

			// Fuzzy sur tags
			MatchQuery tagKeywordQuery = queries.match(Field.ASSET_TAG_NAMES, keywords);
			tagKeywordQuery.setOperator(Operator.OR);
			// tagKeywordQuery.setFuzziness(10f);
			tagKeywordQuery.setPrefixLength(0);
			tagKeywordQuery.setMaxExpansions(50);
			tagKeywordQuery.setFuzzyTranspositions(true);
			tagKeywordQuery.setLenient(false);
			tagKeywordQuery.setZeroTermsQuery(com.liferay.portal.search.query.MatchQuery.ZeroTermsQuery.NONE);
//			"auto_generate_synonyms_phrase_query" : true,
			keywordQuery.addShouldQueryClauses(tagKeywordQuery);

			query.addMustQueryClauses(keywordQuery);
		} else {
			// Si on n'a pas de keyword : on ne veut que les entités de la
			// langue en cours tout de même
			// Pour cela on vérifie que le champ "title_{locale}" n'est pas
			// vide On ne fait pas cela pour les fichiers car ils n'ont pas
			// de champ titre traduit
			BooleanQuery anyKeywordQuery = queries.booleanQuery();
			TermQuery classNameQuery = queries.term(Field.ENTRY_CLASS_NAME, DLFileEntry.class.getName());
			anyKeywordQuery.addShouldQueryClauses(classNameQuery);


			WildcardQuery anyKeywordWildcardQuery = queries.wildcard("title_" + locale, "*");
			anyKeywordQuery.addShouldQueryClauses(anyKeywordWildcardQuery);

			query.addMustQueryClauses(anyKeywordQuery);
		}

		// Catégories
		// On fait un "ou" entre les catégories d'un même vocabulaire et un
		// "et" entre les différents vocabulaires
		for (Long[] categoriesIdsGroupByVocabulary : categoriesIds) {
			BooleanQuery vocabularyQuery = queries.booleanQuery();
			for (long categoryId : categoriesIdsGroupByVocabulary) {
				TermQuery categoryQuery = queries.term(Field.ASSET_CATEGORY_IDS, String.valueOf(categoryId));
				vocabularyQuery.addShouldQueryClauses(categoryQuery);
			}
			query.addMustQueryClauses(vocabularyQuery);
		}

		// Regroupement
		// On fait un "ou" entre les catégories
//		if (categoriesIdsForGroupBy[0] > 0) {
//			BooleanQuery vocabularyQuery = queries.booleanQuery();
//			for (long categoryId : categoriesIdsForGroupBy) {
//				TermQuery categoryQuery = queries.term(Field.ASSET_CATEGORY_IDS, String.valueOf(categoryId));
//				vocabularyQuery.addShouldQueryClauses(categoryQuery);
//			}
//			query.addMustQueryClauses(vocabularyQuery);
//		}

		// Dates
		if (isDisplayField) {
			if (filterField.equals("dates_Number_sortable")) {
				String fromDateString = String.format("%04d", fromDate.getYear())
						+ String.format("%02d", fromDate.getMonth().getValue())
						+ String.format("%02d", fromDate.getDayOfMonth()) + "000000";
				String toDateString = String.format("%04d", toDate.getYear())
						+ String.format("%02d", toDate.getMonth().getValue())
						+ String.format("%02d", toDate.getDayOfMonth()) + "000000";

				RangeTermQuery datesQuery = queries.dateRangeTerm("dates", true, true, fromDateString, toDateString);
				query.addMustQueryClauses(datesQuery);
			} else {
				long fromDateEpoch = fromDate.atStartOfDay().toEpochSecond(ZoneOffset.UTC) * 1000;
				long toDateEpoch = toDate.plusDays(1).atStartOfDay().toEpochSecond(ZoneOffset.UTC) * 1000;

				RangeTermQuery datesQuery = queries.rangeTerm(filterField, true, true, fromDateEpoch, toDateEpoch);
				query.addMustQueryClauses(datesQuery);
			}

		}

		// Si le id SIG du lieu est renseigné on rajoute la condition à la requête
		if(Validator.isNotNull(placeSigId)) {
			TermQuery placeQuery = queries.term("idSIGPlace", placeSigId);
			query.addMustQueryClauses(placeQuery);
		}

		superQuery.addShouldQueryClauses(query);

		return superQuery;
	}

	private static final Log _log = LogFactoryUtil.getLog(SearchHelperV2.class.getName());

	@Reference
	GroupByRequestFactory groupByRequestFactory;

	@Reference
	protected Sorts sorts;

	@Reference
	protected ScoreFunctions scoreFunctions;

	@Reference
	protected Queries queries;

	@Reference
	protected Searcher searcher;

	@Reference
	protected SearchRequestBuilderFactory searchRequestBuilderFactory;
}
