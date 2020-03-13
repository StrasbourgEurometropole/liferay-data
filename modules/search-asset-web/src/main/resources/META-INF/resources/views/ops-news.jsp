<%@ include file="/search-asset-init.jsp"%>

<liferay-portlet:actionURL varImpl="searchActionURL" />

<!-- Listing des resultats -->
<aui:form method="post" name="fm" cssClass="ops-col-wrapper ops-listing-articles">
	<liferay-ui:search-container id="entriesSearchContainer" searchContainer="${dc.searchContainer}">
	
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
		
	</liferay-ui:search-container>
</aui:form>


<!-- Pagination -->
<div class="ops-content-wrapper ops-content-wrapper-large ops-pagination">
	<div class="taglib-page-iterator">
		<div class="clearfix lfr-pagination">
			<!-- Pagination : selecteur de page -->
				<c:if test="${dc.pager.lastPage > 1}">
				<div class="lfr-pagination-config">
					<div class="lfr-pagination-page-selector">
						<div class="btn-group lfr-icon-menu current-page-menu dropdown">
							<a href="#" class="dropdown-toggle direction-down max-display-items-15 btn btn-default" 
								title="<liferay-ui:message key='eu.search.asset.web.ops.general.page' /> ${dc.pager.currentPage} <liferay-ui:message key='eu.search.asset.web.ops.general.on' /> ${dc.pager.lastPage}" 
								data-toggle="dropdown"
							> 
								<span class="lfr-icon-menu-text">
									<liferay-ui:message key="eu.search.asset.web.ops.general.page" /> ${dc.pager.currentPage} <liferay-ui:message key="eu.search.asset.web.ops.general.on" /> ${dc.pager.lastPage}
								</span> 
								<i class="lfr-icon-menu-arrow caret"></i>
							</a>
							<ul class="dropdown-menu lfr-menu-list direction-down" id="change-page" name="change-page" onchange="location = this.value;">
								
								<c:forEach var="pageIndex" begin="1" end="${dc.pager.lastPage}">
									<c:choose>
										<c:when test="${pageIndex != dc.pager.lastPage}">
											<li class="" role="presentation" value="${dc.getURLForPage(pageIndex)}"><a href="${dc.getURLForPage(pageIndex)}" target="_self"
												class=" lfr-icon-item taglib-icon" role="menuitem"> <span class="taglib-text-icon">${pageIndex}</span>
											</a></li>
										</c:when>
										<c:otherwise>
											<li class="" role="presentation" value="${dc.getURLForPage(pageIndex)}"><a href="${dc.getURLForPage(pageIndex)}" target="_self"
												class=" lfr-icon-item taglib-icon" role="menuitem"> <span class="taglib-text-icon">${pageIndex}</span>
											</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								
							</ul>
						</div>
					</div>
				</div>
			</c:if>
			<small <c:if test="${dc.pager.lastPage <= 1}">class="search-results"</c:if>> 
				<liferay-ui:message key="eu.search.asset.web.ops.general.show.results" /> 
				<c:choose>
					<c:when test="${dc.pager.count > 0}">
		                ${dc.pager.currentPage * dc.pager.delta - (dc.pager.delta - 1)}
		            </c:when>
					<c:otherwise>
		               	0
		            </c:otherwise>
				</c:choose> - <c:choose>
					<c:when test="${dc.pager.count < 1}">
		                0
		            </c:when>
					<c:when test="${not dc.pager.onLastPage}">
		                ${dc.pager.currentPage * dc.pager.delta}
		            </c:when>
					<c:when test="${dc.pager.onLastPage}">
		               	${dc.pager.currentPage * dc.pager.delta - (dc.pager.currentPage * dc.pager.delta - dc.pager.count)}
		            </c:when>
				</c:choose> 
				<liferay-ui:message key="eu.search.asset.web.ops.general.among" /> ${dc.pager.count} <liferay-ui:message key="eu.search.asset.web.ops.general.elements" />
			</small>
			
			<!-- Boutons de navigation -->
			<c:if test="${dc.pager.lastPage > 1}">
				<ul class="lfr-pagination-buttons pager">

					<!-- Lien vers la premiere page -->
					<c:choose>
						<c:when test="${not dc.pager.onFirstPage}">
							<li>
								<a href="${dc.getURLForPage(1)}" tabindex="-1" title="<liferay-ui:message key="eu.listing-link-to-first-page" />"> 
									<liferay-ui:message key="eu.search.asset.web.ops.general.first" />
								</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="first">
								<a tabindex="-1" title="<liferay-ui:message key="eu.listing-link-to-first-page" />"> 
									<liferay-ui:message key="eu.search.asset.web.ops.general.first" />
								</a>
							</li>
						</c:otherwise>
					</c:choose>

					<!-- Lien vers la page precedente page -->
					<c:choose>
						<c:when test="${not dc.pager.onFirstPage}">
							<li class="">
								<a href="${dc.getURLForPage(dc.pager.currentPage - 1)}" tabindex="-1"
								title="<liferay-ui:message key="eu.listing-link-to-previous-page" />"> 
									<liferay-ui:message key="eu.search.asset.web.ops.general.previous" />
								</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="disabled">
								<a tabindex="-1" title="<liferay-ui:message key="eu.listing-link-to-previous-page" />">
									<liferay-ui:message key="eu.search.asset.web.ops.general.previous" />
								</a>
							</li>
						</c:otherwise>
					</c:choose>

					<!-- Lien vers la page suivante -->
					<c:choose>
						<c:when test="${not dc.pager.onLastPage}">
							<li>
								<a href="${dc.getURLForPage(dc.pager.currentPage + 1)}" tabindex="0" title="<liferay-ui:message key="eu.listing-link-to-next-page" />">
									<liferay-ui:message key="eu.search.asset.web.ops.general.next" />
								</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="disabled">
								<a tabindex="0" title="<liferay-ui:message key="eu.listing-link-to-next-page" />"> 
									<liferay-ui:message key="eu.search.asset.web.ops.general.next" />
								</a>
							</li>
						</c:otherwise>
					</c:choose>

					<!-- Lien vers la derniere page -->
					<c:choose>
						<c:when test="${not dc.pager.onLastPage}">
							<li>
								<a href="${dc.getURLForPage(dc.pager.lastPage)}" tabindex="0" title="<liferay-ui:message key="eu.listing-link-to-last-page" />"> 
									<liferay-ui:message key="eu.search.asset.web.ops.general.last" />
								</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="last">
								<a tabindex="0" title="<liferay-ui:message key="eu.listing-link-to-last-page" />"> 
									<liferay-ui:message key="eu.search.asset.web.ops.general.last" />
								</a>
							</li>
						</c:otherwise>
					</c:choose>
					
				</ul>
			</c:if>
			
		</div>
	</div>
</div>

<style>
.open>.dropdown-menu {
	display: block;
	position: absolute;
}

.taglib-page-iterator {
	overflow: visible;
}

.taglib-page-iterator .lfr-pagination-config .lfr-pagination-page-selector
	{
	display: block;
}
.taglib-page-iterator .search-results {
	 line-height: 17px;
	 margin-left: 0px;
}

@media (max-width: 979px) { 
	.ops-pagination .lfr-pagination-buttons li, .taglib-page-iterator .lfr-pagination-buttons > li > a {
		width : auto;
		float: inherit;
	}
	.taglib-page-iterator .lfr-pagination-buttons {
		width : auto;
	}
}

@media (max-width: 767px) {
	.taglib-page-iterator .lfr-pagination-config .current-page-menu {
		display : inline-block;
	}
}
</style>