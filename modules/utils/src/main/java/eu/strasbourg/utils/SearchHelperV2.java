package eu.strasbourg.utils;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
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
	 * @param assetTypes
	 *            Le JSON des assetTypes
	 * @param isDisplayField
	 *            Si la recherche se fait par rapport à des dates
	 * @param filterField
	 *            Champs au format date sur lequel le filtre se fait le filtre
	 * @param seed
	 *            int pour le tri aléatoire
	 * @param sortingFieldsAndTypes
	 *            Map contenant les champs et les types des tri
	 * @param groupBy
	 *            Id du vocabulaire sur lequel se fait le regroupement
	 * @param keywords
	 *            Mots clés de recherche
	 * @param fromDate
	 *            Date de début, sous le format "yyyyMMdd000000"
	 * @param toDate
	 *            Date de fin, sous le format "yyyyMMdd000000"
	 * @param categoriesIds
	 *            Liste de tableaux d'ids de catégories (provenant de la
	 *            recherche utilisateur) - un OU est effectué entre chaque id de
	 *            chaque tableau, et UN entre chaque liste
	 * @param idSIGPlace
	 * 			  L'id SIG du lieu
	 * @param locale
	 *            Locale
	 * @param start
	 *            Pagination : début
	 * @param end
	 *            Pagination : fin
	 *
	 * @return Les hits renvoyés par le moteur de recherche
	 */
	public SearchHits  getGlobalSearchHitsV2(SearchContext searchContext, JSONArray assetTypes, Boolean isDisplayField,
											 String filterField, int seed, Map<String, String> sortingFieldsAndTypes,
											 long[] groupBy, String keywords, LocalDate fromDate, LocalDate toDate,
											 List<Long[]> categoriesIds, String idSIGPlace, List<String> classNamesSelected, Locale locale, int start, int end) {

		// Query
		Query query = getGlobalSearchV2Query(assetTypes, isDisplayField, filterField,
				seed, keywords, fromDate, toDate, categoriesIds, idSIGPlace, classNamesSelected, locale);

		SearchRequestBuilder searchRequestBuilder = searchRequestBuilderFactory.builder();

		// Pagination
		searchRequestBuilder.emptySearchEnabled(true);
		searchRequestBuilder.withSearchContext(
			sc -> {
				sc.setCompanyId(searchContext.getCompanyId());
				sc.setStart(start);
				sc.setEnd(end);
			}
		);

		searchRequestBuilder.from(start);
		searchRequestBuilder.size(end - start);

		// Ordre
		if(Validator.isNull(seed) || seed == 0) {
			Sort[] sortArray = new Sort[sortingFieldsAndTypes.size()];
			Iterator iterator = sortingFieldsAndTypes.entrySet().iterator();
			int i = 0;
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
		// Recherche
		SearchHits searchHits = searchResponse.getSearchHits();
		_log.info("Recherche front-end : " + searchHits.getSearchTime() * 1000 + "ms");
		return searchHits;
	}

	/**
	 * Retourne la requête à exécuter correspondant aux paramètres pour le searchAssetV2
	 */
	private Query getGlobalSearchV2Query(JSONArray assetTypes,
										 Boolean isDisplayField, String filterField,
										 int seed, String keywords, LocalDate fromDate,
										 LocalDate toDate, List<Long[]> categoriesIds,
										 String placeSigId, List<String> filterClassNames, Locale locale) {
		// Construction de la requète
		BooleanQuery superQuery = queries.booleanQuery();
		BooleanQuery query = queries.booleanQuery();

		//Asset type
		BooleanQuery assetTypesQuery = queries.booleanQuery();
		if(filterClassNames.size() > 0) {
			for (Object assetTypeObject : assetTypes) {
				JSONObject assetType = (JSONObject) assetTypeObject;
				if (Validator.isNotNull(assetType)) {
					if (Validator.isNotNull(assetType.getString("classname"))) {
						// on vérifie si le className est sélectionné par l'utilisateur
						if (filterClassNames.contains(assetType.getString("classname"))) {
							BooleanQuery assetTypeQuery = queries.booleanQuery();
							// ClassNames
							if (assetType.getString("classname").equals("searchJournalArticle")) {
								// Cas d'un journalArticle
								TermQuery journalArticleClassNameQuery = queries.term(Field.ENTRY_CLASS_NAME, JournalArticle.class.getName());
								// on vérifie que c'est la dernière version
								TermQuery journalArticleHeadQuery = queries.term("head", true);
								assetTypeQuery.addMustQueryClauses(journalArticleClassNameQuery, journalArticleHeadQuery);
								// ajout de la structure du contenu web
								if (Validator.isNotNull(assetType.getLong("structureId"))) {
									TermQuery structureQuery = queries.term(Field.CLASS_TYPE_ID, assetType.getString("structureId"));
									assetTypeQuery.addMustQueryClauses(structureQuery);
								}

							} else {
								if (assetType.getString("classname").equals("searchDocument")) {
									// Cas d'un fichier
									TermQuery classNameQuery = queries.term(Field.ENTRY_CLASS_NAME, DLFileEntry.class.getName());
									assetTypeQuery.addMustQueryClauses(classNameQuery);
								} else {
									if (assetType.getString("classname").equals("searchDemarche")) {
										// Cas d'une procédures/démarches
										BooleanQuery procedureQuery = queries.booleanQuery();
										TermQuery typeQuery = queries.term("type", "procedure");
										TermQuery titleQuery = queries.term("title", keywords);
										procedureQuery.addShouldQueryClauses(typeQuery, titleQuery);
										// On rajoute la condition à la requête principale
										superQuery.addShouldQueryClauses(procedureQuery);
									} else {
										// Cas général
										TermQuery classNameQuery = queries.term(Field.ENTRY_CLASS_NAME, assetType.getString("classname"));
										assetTypeQuery.addMustQueryClauses(classNameQuery);
									}
								}
							}

							// Groups
							if (assetType.getJSONArray("scopeIds").length() > 0) {
								BooleanQuery groupsQuery = queries.booleanQuery();
								for (Object groupObject : assetType.getJSONArray("scopeIds")) {
									Long groupId = (Long) groupObject;
									TermQuery groupQuery = queries.term(Field.GROUP_ID, groupId);
									groupsQuery.addShouldQueryClauses(groupQuery);
								}
								assetTypeQuery.addMustQueryClauses(groupsQuery);
							}

							// Préfiltres
							if (assetType.getJSONArray("prefilters").length() > 0) {
								BooleanQuery prefiltersQuery = queries.booleanQuery();
								for (Object prefilterObject : assetType.getJSONArray("prefilters")) {
									JSONObject prefilter = (JSONObject) prefilterObject;
									if (prefilter.getString("type").equals("tags")) {
										BooleanQuery tagsQuery = queries.booleanQuery();
										for (Object tagObject : prefilter.getJSONArray("selection")) {
											Long tagId = (Long) tagObject;
											TermQuery tagQuery = queries.term(Field.ASSET_TAG_IDS, String.valueOf(tagId));
											if (prefilter.getString("operator").equals("all")) {
												tagsQuery.addMustQueryClauses(tagQuery);
											} else {
												tagsQuery.addShouldQueryClauses(tagQuery);
											}
										}
										// si true alors contient
										if (prefilter.getBoolean("contains")) {
											prefiltersQuery.addMustQueryClauses(tagsQuery);
										} else {
											prefiltersQuery.addMustNotQueryClauses(tagsQuery);
										}
									} else {
										BooleanQuery categoriesQuery = queries.booleanQuery();
										for (Object categoryObject : prefilter.getJSONArray("selection")) {
											Long categoryId = (Long) categoryObject;
											TermQuery categoryQuery = queries.term(Field.ASSET_CATEGORY_IDS, String.valueOf(categoryId));
											if (prefilter.getString("operator").equals("all")) {
												categoriesQuery.addMustQueryClauses(categoryQuery);
											} else {
												categoriesQuery.addShouldQueryClauses(categoryQuery);
											}
										}
										// si true alors contient
										if (prefilter.getBoolean("contains")) {
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
			keywordQuery.addShouldQueryClauses(titleQueryWithoutAnalyzer);

			// Wildcard sur titre
			WildcardQuery titleWildcardQuery = queries.wildcard(Field.TITLE + "_" + locale,
					"*" + keywords + "*");
			titleWildcardQuery.setBoost(30.0f);
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

		// Tri aléatoire
		if(Validator.isNotNull(seed) && seed > 0) {
			RandomScoreFunction randomScoreFunction = scoreFunctions.random();
			randomScoreFunction.setSeed(seed);
			randomScoreFunction.setField("entryClassPK");
			FunctionScoreQuery functionScoreQuery = queries.functionScore(query);
			functionScoreQuery.addFilterQueryScoreFunctionHolder(query, randomScoreFunction);
		}

		superQuery.addShouldQueryClauses(query);

		return superQuery;
	}

	private static final Log _log = LogFactoryUtil.getLog(SearchHelperV2.class.getName());

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
