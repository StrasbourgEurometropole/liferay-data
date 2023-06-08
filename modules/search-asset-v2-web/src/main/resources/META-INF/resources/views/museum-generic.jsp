<%@ include file="/search-asset-init.jsp" %>

<liferay-portlet:actionURL varImpl="searchActionURL" />

<div class="search-asset-portlet-page container agenda">
	<div class="search-asset-form">
		<aui:form action="${searchActionURL}" method="get" name="fm">
		 	<liferay-portlet:renderURLParams varImpl="searchActionURL" />
			<liferay-util:include page="/forms/${dc.searchForm}-form.jsp" servletContext="<%=application %>" />
		</aui:form>
	</div>

    <h2 class="search-asset-result-count">
        <c:if test="${not dc.hideResultsBeforeSearch or dc.userSearch or param.paginate}">
            ${dc.searchContainer.total }
            <c:choose>
                <c:when test="${dc.searchContainer.total le 1}">
                    <liferay-ui:message
                        key="result" />
                </c:when>
                <c:otherwise>
                    <liferay-ui:message
                        key="results" />
                </c:otherwise>
            </c:choose>
        </c:if>
    </h2>

	<div class="search-asset-search-container">
		<aui:form method="post" name="fm">
			<aui:input type="hidden" name="selectionIds" />
			<liferay-ui:search-container id="entriesSearchContainer"
				searchContainer="${dc.searchContainer}">
				<div class="search-asset-results">
					<liferay-ui:search-container-results results="${dc.entries}" />
			
					<liferay-ui:search-container-row
						className="com.liferay.asset.kernel.model.AssetEntry"
						modelVar="entry" keyProperty="entryId" rowIdProperty="entryId">
                        <c:choose>
                            <c:when test="${dc.isEntryFeatured(entry)}">
                                <div class="search-asset-thumbnail featured search-asset-result">
                            </c:when>
                            <c:otherwise>
                                <div class="search-asset-thumbnail">
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
                            <c:if test="${!entry.className.equals('Procedure')}">
                                <liferay-ddm:template-renderer
                                    className="${className}"
                                    contextObjects="${dc.getTemplateContextObjects(entry)}"
                                    displayStyle="${dc.templatesMap[entry.className]}"
                                    displayStyleGroupId="${themeDisplay.scopeGroupId}"
                                    entries="${dc.templateEntries}"
                                >
                                    <liferay-asset:asset-display
                                        assetEntry="${entry}"
                                        assetRenderer="${entry.assetRenderer}"
                                        assetRendererFactory="${entry.assetRendererFactory}"
                                        template="abstract"
                                    />
                                </liferay-ddm:template-renderer>
                            </c:if>
                        </div>
					</liferay-ui:search-container-row>
				</div>
				<liferay-ui:search-paginator searchContainer="${dc.searchContainer}" />
			</liferay-ui:search-container>
		</aui:form>
	</div>
</div>