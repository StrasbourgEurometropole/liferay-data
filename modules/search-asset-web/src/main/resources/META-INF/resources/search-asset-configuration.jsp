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
			
			<!-- PortÃ©e -->
			<aui:fieldset collapsed="true" collapsible="true" label="scope">
				<liferay-ui:message key="scope-explanations" />
				<aui:input type="checkbox" name="globalScope" value="${globalScope}" label="global-scope" inlineField="true" />
			</aui:fieldset>
			
			<!-- PrÃ©filtre -->
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

			<!-- Vocabulaires -->
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
				<liferay-ui:message key="date-explanations" />
				<aui:input type="checkbox" name="dateField" value="${dateField}" label="date-field" inlineField="true" />
			</aui:fieldset>
			
			<!-- Affichage -->
			<aui:fieldset collapsed="true" collapsible="true"
				label="display">
				<!-- Ne pas afficher de rÃ©sultats avant que l'utilisateur n'ait fait de recherche -->
				<aui:input type="checkbox" name="hideResultsBeforeSearch" value="${hideResultsBeforeSearch}" label="hide-results-before-search" />
				
				<!-- Nombre de rÃ©sultats par page -->
				<aui:input type="number" name="delta" value="${delta}" />
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
</style>