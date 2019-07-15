<%@ include file="/search-asset-init.jsp" %>

<liferay-portlet:actionURL varImpl="searchActionURL" />

<main class="container">
	<div class="search-asset-portlet-page">
		<div class="search-asset-form">
			<aui:form action="${searchActionURL}" method="get" name="fm" id="search-asset-form" cssClass="mns-view-filters">
			 	<liferay-portlet:renderURLParams varImpl="searchActionURL" />
				<liferay-util:include page="/forms/${dc.searchForm}-form.jsp" servletContext="<%=application %>" />
			</aui:form>
		</div>
		
		
		<div class="mns-view-results">
            <div class="mns-result-count">${dc.searchContainer.total} 
            	<c:choose>
            		<c:when test="${dc.searchContainer.total gt 1}">
            			<liferay-ui:message key="results" />
            		</c:when>
            		<c:otherwise>
            			<liferay-ui:message key="result" />
            		</c:otherwise>
            	</c:choose>
            </div>
            <div class="mns-filler"></div>
            <div class="mns-result-filter">
                <span><liferay-ui:message key="results-per-page" /></span>
                <select name="filter" id="" class="toCustomSelect" onchange="this.options[this.selectedIndex].value && (window.location = this.options[this.selectedIndex].value);">
                    <c:forEach var="delta" items="${[5, 10, 20, 50, 100]}">
                    	<c:set var="selected" value="${delta eq dc.delta ? 'selected' : ''}" />
                    	<option value="${dc.getURLForDelta(delta)}" ${selected} >${delta}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
		
		<div class="search-asset-search-container">
			<liferay-util:include page="/form-headers/${dc.searchForm}-form-header.jsp" servletContext="<%=application %>" />
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
										<liferay-ui:asset-display
											assetEntry="${entry}"
											assetRenderer="${entry.assetRenderer}"
											assetRendererFactory="${entry.assetRendererFactory}"
											template="abstract"
										/>
									</liferay-ddm:template-renderer>
								</div>
						</liferay-ui:search-container-row>
					</div>
					
					<!-- Pagination -->
					<c:if test="${dc.pager.lastPage > 1}">
			            <ul class="mns-pagination unstyled">
			            	<!-- Page précédente -->
			                <li class="mns-pagin-prev disabled mns-pagin-item">
								<c:if test="${not dc.pager.onFirstPage}">
				                    <a class="mns-btn-square mns-bordered mns-core" data-action="prev" title="<liferay-ui:message key="previous" />"
										href="${dc.getURLForPage(dc.pager.currentPage - 1)}">
				                        <span class="mns-flexbox">
				                            <span class="mns-btn-text"><liferay-ui:message key="previous" /></span>
				                            <span class="mns-btn-arrow"></span>
				                        </span>
				                    </a>
			               		</c:if>
			                </li>
			                <c:forEach var="page" items="${dc.pager.pages}">
			                	<c:choose>
			                		<c:when test="${page.isALink() and not (page.index eq dc.pager.currentPage)}">
			                			<!-- Lien vers page -->
				                		<li class="mns-pagin-item">
						                    <a data-page="${page.index}" href="${dc.getURLForPage(page.index)}">
						                        <span class="mns-flexbox">
						                            <span class="mns-btn-text">${page.label}</span>
						                        </span>
						                    </a>
						                </li>
			                		</c:when>
			                		<c:when test="${page.isALink() and (page.index eq dc.pager.currentPage)}">
			                			<!-- Page en cours -->
				                		<li class="mns-pagin-item mns-is-active">
					                        <span class="mns-flexbox">
					                            <span class="mns-btn-text">${page.label}</span>
					                        </span>
					                    </li>
					                </c:when>
			                		<c:otherwise>
					                	<!-- Texte -->
				                		<li class="mns-pagin-item">
					                        <span class="mns-flexbox">
					                            <span class="mns-btn-text">${page.label}</span>
					                        </span>
					                    </li>
			                		</c:otherwise>
			                	</c:choose>
			                </c:forEach>
			                
			                <!-- Page suivante -->
			                <li class="mns-pagin-next seu-pagin-item">
								<c:if test="${not dc.pager.onLastPage}">
				                    <a class="mns-btn-square mns-bordered mns-core" title="<liferay-ui:message key="next" />"
				                    	data-action="next" href="${dc.getURLForPage(dc.pager.currentPage + 1)}">
				                        <span class="mns-flexbox">
				                            <span class="mns-btn-text"><liferay-ui:message key="next" /></span>
				                            <span class="mns-btn-arrow"></span>
				                        </span>
				                    </a>
			              	 	</c:if>
			                </li>
			            </ul>
			    	</c:if>
					
				</liferay-ui:search-container>
			</aui:form>
			<liferay-util:include page="/form-footers/${dc.searchForm}-form-footer.jsp" servletContext="<%=application %>" />
		</div>
	</div>
</main>