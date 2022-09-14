<%@ include file="/search-asset-init.jsp" %>

<liferay-portlet:actionURL varImpl="searchActionURL" />

<div class="search-asset-portlet-page">
	<div class="search-asset-form">
		<aui:form action="${searchActionURL}" method="get" name="fm" id="search-asset-form">
		 	<liferay-portlet:renderURLParams varImpl="searchActionURL" />
			<liferay-util:include page="/forms/${dc.searchForm}-form.jsp" servletContext="<%=application %>" />
		</aui:form>
	</div>
	<div class="search-asset-search-container">
		<liferay-util:include page="/form-headers/${dc.searchForm}-form-header.jsp" servletContext="<%=application %>" />
		<aui:form method="post" name="fm">
			<aui:input type="hidden" name="selectionIds" />
			<liferay-ui:search-container id="entriesSearchContainer"
				searchContainer="${dc.searchContainer}">
				<div class="search-asset-results row" id="result">
					<liferay-ui:search-container-results results="${dc.entries}" />
			
					<liferay-ui:search-container-row
						className="com.liferay.asset.kernel.model.AssetEntry"
						modelVar="entry" keyProperty="entryId" rowIdProperty="entryId">
							<c:choose>
								<c:when test="${dc.isEntryFeatured(entry)}">
									<div class="featured search-asset-result">
								</c:when>
								<c:otherwise>
									<div>
								</c:otherwise>
							</c:choose>
							
								<c:set var="className" value="${entry.className}" />
								<c:choose>
									<c:when test="${fn:contains(className, 'JournalArticle')}">
										<c:set var="className" value="com.liferay.asset.kernel.model.AssetEntry" />
									</c:when>
									<c:when test="${fn:contains(className, 'DLFileEntry')}">
										<c:set var="className" value="com.liferay.portal.kernel.repository.model.FileEntry" />
									</c:when>
								</c:choose>
								<liferay-ddm:template-renderer
								    className="${className}"
								    contextObjects="${dc.getTemplateContextObjects(entry)}"
								    displayStyle="${dc.templatesMap[entry.className]}"
								    displayStyleGroupId="${themeDisplay.scopeGroupId}"
								    entries="${dc.templateEntries }"
								>
									<liferay-asset:asset-display
										assetEntry="${entry}"
										assetRenderer="${entry.assetRenderer}"
										assetRendererFactory="${entry.assetRendererFactory}"
										template="abstract"
									/>
								</liferay-ddm:template-renderer>
							</div>
					</liferay-ui:search-container-row>
				</div>
				<liferay-ui:search-paginator searchContainer="${dc.searchContainer}" />
			</liferay-ui:search-container>
		</aui:form>
		<liferay-util:include page="/form-footers/${dc.searchForm}-form-footer.jsp" servletContext="<%=application %>" />
	</div>
</div>