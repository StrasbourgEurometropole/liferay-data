<%@ include file="/search-asset-init.jsp"%>
<%@page
	import="com.liferay.portal.kernel.security.permission.ResourceActionsUtil"%>
<%@page import="com.liferay.asset.kernel.model.AssetRendererFactory"%>

<liferay-portlet:actionURL portletConfiguration="true"
	varImpl="configurationActionURL" />

<div class="container-fluid-1280 main-content-body">
	<liferay-ui:error key="wrong-friendly-url" message="wrong-friendly-url" />
	<aui:form action="${configurationActionURL}" method="post" name="fm" >

		<aui:input name="cmd" type="hidden" value="update" />

		<aui:fieldset-group markupView="lexicon">
			<!-- Type d'asset -->
			<aui:fieldset collapsed="false" collapsible="true"
				label="asset-type">
				<liferay-ui:message key="asset-types-explanations" />
				<div class="asset-types">
					<aui:input type="hidden" name="assetClassNamesCount"
						value="${fn:length(availableAssetRendererFactories)}" />
					<c:set var="i" value="${0}" />
					<c:forEach var="assetRendererFactory" varStatus="assetStatus"
						items="${availableAssetRendererFactories}">
						<c:set var="classNameIsChecked"
							value="${fn:contains(assetClassNamesIds, assetRendererFactory.classNameId)}" />
						<div class="asset-type-configuration">
							<%
								AssetRendererFactory assetRendererFactory = (AssetRendererFactory) pageContext
													.getAttribute("assetRendererFactory");
							%>
							<aui:input type="checkbox"
								name="assetClassNameId_${assetStatus.index}"
								label="<%= ResourceActionsUtil.getModelResource(locale, assetRendererFactory.getClassName()) %>"
								checked="${classNameIsChecked}"
								value="${assetRendererFactory.classNameId}" inlineField="true" />

							<aui:select name="templateKey_${assetStatus.index}"
								inlineField="true">
								<aui:option value="0"><liferay-ui:message key="select-a-template" /></aui:option>
								<c:forEach var="template"
									items="${templatesList[assetStatus.index]}">
									<aui:option value="${template.templateKey}"
										selected="${fn:contains(templatesKeys, template.templateKey)}">${template.getName(locale)}</aui:option>
								</c:forEach>
							</aui:select>


							<aui:input type="text"
								placeholder="detail-friendly-url"
								name="layoutFriendlyURL_${assetStatus.index}" inlineField="true"
								value="${classNameIsChecked ? layoutsFriendlyURLs[i] : ''}" />

							<c:if test="${classNameIsChecked}">
								<c:set var="i" value="${i + 1}" />
							</c:if>
						</div>
					</c:forEach>
					<div class="asset-type-configuration">
						<aui:input type="checkbox" 
							name="searchJournalArticle" 
							value="${searchJournalArticle}" 
							label="web-content" 
							inlineField="true" />
						<aui:select name="journalArticleTemplateKey"
							inlineField="true">
							<aui:option value="0"><liferay-ui:message key="select-a-template" /></aui:option>
							<c:forEach var="template"
								items="${assetEntryTemplatesList}">
								<aui:option value="${template.templateKey}"
									selected="${journalArticleTemplateKey eq template.templateKey}">
									${template.getName(locale)}
								</aui:option>
							</c:forEach>
						</aui:select>
					</div>
					<div class="asset-type-configuration">
						<aui:input type="checkbox" 
							name="searchDocument" 
							value="${searchDocument}" 
							label="file" 
							inlineField="true" />
						<aui:select name="documentTemplateKey"
							inlineField="true">
							<aui:option value="0"><liferay-ui:message key="select-a-template" /></aui:option>
							<c:forEach var="template"
								items="${documentTemplatesList}">
								<aui:option value="${template.templateKey}"
									selected="${documentTemplateKey eq template.templateKey}">
									${template.getName(locale)}
								</aui:option>
							</c:forEach>
						</aui:select>
					</div>
				</div>
				<div>
					
				</div>
			</aui:fieldset>
			
			<!-- PortÃÂÃÂÃÂÃÂ©e -->
			<aui:fieldset collapsed="true" collapsible="true" label="scope">
				<liferay-ui:message key="scope-explanations" />
				<aui:input type="checkbox" name="globalScope" value="${globalScope}" label="global-scope" inlineField="true" />
			</aui:fieldset>
			
			<!-- PrÃÂÃÂÃÂÃÂ©filtre -->
			<aui:fieldset collapsed="true" collapsible="true" label="prefilter">
				<liferay-ui:message key="prefilter-explanations" />
				<label><liferay-ui:message key="categories" /></label>
				<p>
					<liferay-ui:asset-categories-selector
						hiddenInput="prefilterCategoriesIds"
						curCategoryIds="${prefilterCategoriesIds}" />
				</p>
				<br>
				<p>
					<liferay-ui:message key="prefilter-tag-explanations" />
					<label><liferay-ui:message key="tags" /></label>
					<liferay-ui:asset-tags-selector
						hiddenInput="prefilterTagsNames"
						curTags="${prefilterTagsNames}" />
				</p>
			</aui:fieldset>

			<!-- CritÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ¨res de recherche -->
			<aui:fieldset collapsed="true" collapsible="true"
				label="search-criterias">
				<liferay-ui:message key="vocabularies-explanations" />
				<aui:input type="hidden" name="vocabulariesCount"
					value="${fn:length(vocabularies)}" />

				<c:set var="i" value="${0}" />
				<c:forEach var="vocabulary" items="${vocabularies}"
					varStatus="vocStatus">

					<c:set var="vocabularyIsChecked"
						value="${fn:contains(vocabulariesIds, vocabulary.vocabularyId)}" />

					<div class="vocabulary-configuration">
						<c:set var="vocabularyLabel" value="${vocabulary.name}" />
						<c:if test="${vocabulary.groupId eq themeDisplay.companyGroupId}">
							<c:set var="vocabularyLabel" value="${vocabularyLabel.concat(' (global)')}" />
						</c:if>
						<aui:input type="checkbox" name="vocabularyId_${vocStatus.index}"
							label="${vocabularyLabel}" value="${vocabulary.vocabularyId}"
							checked="${fn:contains(vocabulariesIds, vocabulary.vocabularyId)}"
							inlineField="true" />

						<aui:select name="vocabularyControlType_${vocStatus.index}"
							inlineField="true">
							<aui:option value="list"
								selected="${vocabularyIsChecked ? vocabulariesControlTypes[i] eq 'list' : 'false'}">Liste d&eacute;roulante</aui:option>
							<aui:option value="radio"
								selected="${vocabularyIsChecked ? vocabulariesControlTypes[i] eq 'radio' : 'false'}">Boutons radios</aui:option>
							<aui:option value="check"
								selected="${vocabularyIsChecked ? vocabulariesControlTypes[i] eq 'check' : 'false'}">Checkboxes</aui:option>
						</aui:select>
					</div>

					<c:if test="${vocabularyIsChecked}">
						<c:set var="i" value="${i + 1}" />
					</c:if>
				</c:forEach>
				
				<!-- Filtre par date -->
				<div>
					<liferay-ui:message key="date-explanations" />
					<aui:input type="checkbox" name="dateField" value="${dateField}" label="date-field" inlineField="true" />
				</div>
				
				<!-- Tri par date -->
				<div>
					<liferay-ui:message key="date-sorting-explanations" />
					<aui:input type="checkbox" name="displayDateSorting" value="${displayDateSorting}" label="date-sorting-field" inlineField="true" />
				</div>
			</aui:fieldset>
			
			<!-- Boosts -->
			<aui:fieldset collapsed="true" collapsible="true" label="boosts">
				<liferay-ui:message key="boost-explanations" />
				<p>
					<label><liferay-ui:message key="tags" /></label>
					<liferay-ui:asset-tags-selector
						hiddenInput="boostTagsNames"
						curTags="${boostTagsNames}" />
				</p>
			</aui:fieldset>
			
			<!-- Affichage par dÃÂÃÂÃÂÃÂ©faut -->
			<aui:fieldset collapsed="true" collapsible="true" label="default-display">
				<liferay-ui:message key="default-display-explanations" />
				<!-- Ne pas afficher de rÃÂÃÂÃÂÃÂ©sultats avant que l'utilisateur n'ait fait de recherche -->
				<aui:input type="checkbox" name="hideResultsBeforeSearch" value="${hideResultsBeforeSearch}" label="hide-results-before-search" />
				
				<!-- Tri par dÃÂÃÂÃÂÃÂ©faut -->
				<aui:select name="defaultSortField">
					<aui:option value="modified_sortable" selected="${defaultSortField eq 'modified_sortable'}">
						<liferay-ui:message key="modification-date" />
					</aui:option>
					<aui:option value="publishDate_sortable" selected="${defaultSortField eq 'publishDate_sortable'}">
						<liferay-ui:message key="publication-date" />
					</aui:option>
					<aui:option value="dates_Number_sortable" selected="${defaultSortField eq 'dates_Number_sortable'}">
						<liferay-ui:message key="entity-date" />
					</aui:option>
					<aui:option value="order_city_Number_sortable" selected="${defaultSortField eq 'order_city_Number_sortable'}">
						<liferay-ui:message key="city-order-property" />
					</aui:option>
					<aui:option value="order_ems_Number_sortable" selected="${defaultSortField eq 'order_ems_Number_sortable'}">
						<liferay-ui:message key="ems-order-property" />
					</aui:option>
				</aui:select>
				


				<!-- Type de tri par defaut -->
				<aui:select name="defaultSortType">
					<aui:option value="asc" selected="${defaultSortType eq 'asc'}">
						<liferay-ui:message key="asc" />
					</aui:option>
					<aui:option value="desc" selected="${defaultSortType eq 'desc'}">
						<liferay-ui:message key="desc" />
					</aui:option>
				</aui:select>
				
				<!-- Filtre par date par dÃÂÃÂÃÂÃÂ©faut --> 
				<aui:input type="number" name="defaultDateRange" min="-1000" max="+1000" cssClass="date-range" value="${defaultDateRange}" label="default-date-range" inlineField="true"/>
			</aui:fieldset>
			
			<!-- Affichage -->
			<aui:fieldset collapsed="true" collapsible="true"
				label="display">

				<!-- Nombre de resultats par page -->
				<aui:input type="number" name="delta" value="${delta}" />
				
				<!-- Formulaire de recherche -->
				<aui:select name="searchForm">
					<aui:option value="museum" selected="${searchForm eq 'museum'}">
						<liferay-ui:message key="museum" />
					</aui:option>
					<aui:option value="summer" selected="${searchForm eq 'summer'}">
						<liferay-ui:message key="summer" />
					</aui:option>
					<aui:option value="official" selected="${searchForm eq 'official'}">
						<liferay-ui:message key="official" />
					</aui:option>
					<aui:option value="videos" selected="${searchForm eq 'videos'}">
						<liferay-ui:message key="videos" />
					</aui:option>
					<aui:option value="christmas-agenda" selected="${searchForm eq 'christmas-agenda'}">
						<liferay-ui:message key="christmas-agenda" />
					</aui:option>
					<aui:option value="christmas-general" selected="${searchForm eq 'christmas-general'}">
						<liferay-ui:message key="christmas-general" />
					</aui:option>
					<aui:option value="strasbourg-agenda" selected="${searchForm eq 'strasbourg-agenda'}">
						<liferay-ui:message key="strasbourg-agenda" />
					</aui:option>
					<aui:option value="strasbourg-generic" selected="${searchForm eq 'strasbourg-generic'}">
						<liferay-ui:message key="strasbourg-generic" />
					</aui:option>
					<aui:option value="summer-2018-agenda" selected="${searchForm eq 'summer-2018-agenda'}">
						<liferay-ui:message key="summer-2018-agenda" />
					</aui:option>
					<aui:option value="placit-projects" selected="${searchForm eq 'placit-projects'}">
						<liferay-ui:message key="placit-projects" />
					</aui:option>
					<aui:option value="placit-participations" selected="${searchForm eq 'placit-participations'}">
						<liferay-ui:message key="placit-participations" />
					</aui:option>
					<aui:option value="placit-agenda" selected="${searchForm eq 'placit-agenda'}">
						<liferay-ui:message key="placit-agenda" />
					</aui:option>
                    <aui:option value="placit-news" selected="${searchForm eq 'placit-news'}">
                       	<liferay-ui:message key="placit-news" />
                    </aui:option>
                    <aui:option value="placit-videos" selected="${searchForm eq 'placit-videos'}">
                       	<liferay-ui:message key="placit-videos" />
                    </aui:option>
				</aui:select>
				
				<!-- Bouton exporter -->
				<aui:input type="checkbox" name="displayExport" value="${displayExport}" 
					label="display-export" inlineField="true" />
				
				<!-- Type d'export -->
				<aui:select name="exportType" showEmptyOption="true">
					<aui:option value="city" selected="${exportType eq 'city'}">
						<liferay-ui:message key="city" />
					</aui:option>
					<aui:option value="ems" selected="${exportType eq 'ems'}">
						<liferay-ui:message key="ems" />
					</aui:option>
				</aui:select>
			</aui:fieldset>

		</aui:fieldset-group>
		
		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>

<style>
.asset-types .asset-type-configuration .input-select-wrapper label,
	.asset-types .input-text-wrapper label, .vocabulary-configuration .input-select-wrapper label
	{
	display: none;
	float: right;
}

.asset-types .input-select-wrapper, .vocabulary-configuration .input-select-wrapper
	{
	width: 250px;
}

.asset-types .input-text-wrapper {
	margin-bottom: 10px;
	margin-left: 50px;
	width: 250px;
}

.asset-types .asset-type-configuration select, .vocabulary-configuration select
	{
	margin-left: 20px;
	min-width: 150px;
	margin-bottom: -5px;
}

.asset-types .input-checkbox-wrapper, .vocabulary-configuration .input-checkbox-wrapper
	{
	width: 250px;
}
p {
	max-width: 800px;
}
p.date {
	margin-top: 20px;
}

.date-range {
	width: 100px;
}
</style>