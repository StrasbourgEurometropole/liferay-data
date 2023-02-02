<%@ include file="/search-asset-init.jsp" %>
<liferay-portlet:actionURL varImpl="searchActionURL" />

<main class="search-asset-portlet-page container">
	<div class="search-asset-form">
		<!-- Formulaire -->
		<aui:form action="${searchActionURL}" method="get" name="fm" id="search-asset-form" cssClass="view-filters">
		 	<liferay-portlet:renderURLParams varImpl="searchActionURL" />
			<liferay-util:include page="/forms/${dc.searchForm}-form.jsp" servletContext="<%=application %>" />
		</aui:form>

        <div class="search-asset-search-container">
            <aui:form method="post" name="fm">
                <liferay-ui:search-container id="entriesSearchContainer"
                            searchContainer="${dc.searchContainer}">
                    <!-- Résultats -->
				    <div class="search-asset-results">
                        <liferay-ui:search-container-results results="${dc.entries}" />
                        <liferay-ui:search-container-row
                            className="com.liferay.asset.kernel.model.AssetEntry"
                            modelVar="entry" keyProperty="entryId" rowIdProperty="entryId">

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
                                    entries="${dc.templateEntries }"
                                >
                                    <liferay-asset:asset-display
                                        assetEntry="${entry}"
                                        assetRenderer="${entry.assetRenderer}"
                                        assetRendererFactory="${entry.assetRendererFactory}"
                                        template="abstract"
                                    />
                                </liferay-ddm:template-renderer>
                            </c:if>
                        </liferay-ui:search-container-row>
                    </div>

                    <!-- Pagination -->
                    <div class="search-asset-paginate">
                        <liferay-ui:search-paginator searchContainer="${dc.searchContainer}" />
                    </div>

                </liferay-ui:search-container>
            </aui:form>
        </div>
    </div>

</main>