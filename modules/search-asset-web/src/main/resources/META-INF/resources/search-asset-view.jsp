<%@ include file="/search-asset-init.jsp" %>

<liferay-portlet:actionURL varImpl="searchActionURL" />

<aui:form action="${searchActionURL}" method="post" name="fm">
	<aui:input type="text" name="keywords" />

	<aui:input type="hidden" name="vocabulariesCount" value="${fn:length(dc.vocabularies)}" />
	<c:forEach items="${dc.vocabularies}" var="vocabulary" varStatus="vocStatus">
		<aui:select name="vocabulary_${vocStatus.index}" label="${vocabulary.getTitle(locale)}" showEmptyOption="true">
			<c:forEach items="${vocabulary.categories}" var="category">
				<aui:option value="${category.categoryId}" label="${category.getTitle(locale)}" selected="${fn:contains(dc.filterCategoriesIdsString, category.categoryId)}" />
			</c:forEach>
		</aui:select>
	</c:forEach>
		
	<aui:button-row>
		<aui:button type="submit" value="search" />
	</aui:button-row>
</aui:form>


<aui:form method="post" name="fm">
	<aui:input type="hidden" name="selectionIds" />
	<liferay-ui:search-container id="entriesSearchContainer"
		searchContainer="${dc.searchContainer}">
		<liferay-ui:search-container-results results="${dc.entries}" />

		<liferay-ui:search-container-row
			className="com.liferay.asset.kernel.model.AssetEntry"
			modelVar="entry" keyProperty="entryId" rowIdProperty="entryId">
			<p>${entry.getTitle(locale)}</p>
			<liferay-ui:asset-display
				assetEntry="${entry}"
				assetRenderer="${dc.assetRendererFactory.getAssetRenderer(entry.classPK) }"
				assetRendererFactory="${dc.assetRendererFactory}"
				template="full_content"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator paginate="true" searchContainer="${dc.searchContainer}" />
	</liferay-ui:search-container>
</aui:form>