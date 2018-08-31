<%@ include file="/dynamic-search-asset-init.jsp"%>
<%@page import="com.liferay.portal.kernel.security.permission.ResourceActionsUtil"%>
<%@page import="com.liferay.asset.kernel.model.AssetRendererFactory"%>

<liferay-portlet:actionURL portletConfiguration="true" varImpl="configurationActionURL" />

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
					<c:forEach var="assetRendererFactory" varStatus="assetStatus" items="${availableAssetRendererFactories}">
						<c:set var="classNameIsChecked" value="${fn:contains(assetClassNamesIds, assetRendererFactory.classNameId)}" />
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
							name="searchNews" 
							value="${searchNews}" 
							label="com.liferay.journal.model.JournalArticle" 
							inlineField="true" />
					</div>
					<div class="asset-type-configuration">
						<aui:input type="checkbox" 
							name="searchDocument" 
							value="${searchDocument}" 
							label="file" 
							inlineField="true" />
					</div>
				</div>

			</aui:fieldset>
			
			<!-- Portee -->
			<aui:fieldset collapsed="true" collapsible="true" label="scope">
				<liferay-ui:message key="scope-explanations" />
				<aui:input type="checkbox" name="globalScope" value="${globalScope}" label="global-scope" inlineField="true" />
			</aui:fieldset>
			
			<!-- Prefiltre -->
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
			
			<!-- Date de selection -->
			<aui:fieldset collapsed="true" collapsible="true" label="date-filter">
				
				<!-- Filtre par date -->
				<div>
					<liferay-ui:message key="date-explanations" />
					<aui:input type="checkbox" name="dateField" value="${dateField}" label="date-field" inlineField="true" />
				</div>
				
				<!-- Filtre par date par defaut --> 
				<aui:input type="number" name="defaultDateRange" min="-1000" max="+1000" cssClass="date-range" value="${defaultDateRange}" label="default-date-range" inlineField="true"/>
			
			</aui:fieldset>
			
			<!-- Affichage -->
			<aui:fieldset collapsed="true" collapsible="true"
				label="display">

				<!-- Nombre de resultats par page -->
				<aui:input type="number" name="delta" value="${delta}" />
				
				<!-- Formulaire de recherche -->
				<aui:select name="searchForm">
					<aui:option value="placit" selected="${searchForm eq 'placit'}">
						<liferay-ui:message key="placit" />
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