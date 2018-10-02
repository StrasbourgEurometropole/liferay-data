<%@ include file="/search-asset-init.jsp" %>

<liferay-portlet:actionURL varImpl="searchActionURL" />


<!-- Formulaire -->
<div class="container mns-filtres row">
	<div>
		<aui:form action="${searchActionURL}" method="get" name="fm" id="search-asset-form" cssClass="seu-view-filters">
		 	<liferay-portlet:renderURLParams varImpl="searchActionURL" />
			<liferay-util:include page="/forms/christmas-2018-experience-form.jsp" servletContext="<%=application %>" />
		</aui:form>
	</div>
</div>

<aui:form method="post" name="fm">
	<!-- RÃ©sultats -->
	<liferay-ui:search-container id="entriesSearchContainer"
				searchContainer="${dc.searchContainer}">
		<div class="mns-page-experience">
			<div class="container mns-listing-exp">
	            <div class="row" data-egalize=".mns-bloc-exp">
	        	
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
					</liferay-ui:search-container-row>
				</div>
			</div>
		</div>
					
		<!-- Export -->
		<c:if test="${dc.displayExport}">
			<div class="btn-line">
				<a href="${dc.exportResourceURL}" title="<liferay-ui:message key="print" />" target="_blank">
					<button type="button" class="seu-btn-square--filled--second">
						<span class="seu-flexbox">
							<span class="seu-btn-text"><liferay-ui:message key="print" /></span>
							<span class="seu-btn-arrow"></span>
						</span> 
					</button>
				</a>
			</div>
		</c:if>
		
		<!-- Pagination -->
		<c:if test="${dc.pager.lastPage > 1}">
            <ul class="seu-pagination unstyled">
            	<!-- Page prÃ©cÃ©dente -->
                <li class="seu-pagin-prev disabled seu-pagin-item">
					<c:if test="${not dc.pager.onFirstPage}">
	                    <a class="seu-btn-square seu-bordered seu-core" data-action="prev" title="<liferay-ui:message key="go-to-previous-page" />"
							href="${dc.getURLForPage(dc.pager.currentPage - 1)}">
	                        <span class="seu-flexbox">
	                            <span class="seu-btn-text"><liferay-ui:message key="previous" /></span>
	                            <span class="seu-btn-arrow"></span>
	                        </span>
	                    </a>
               		</c:if>
                </li>
                <c:forEach var="page" items="${dc.pager.pages}">
                	<c:choose>
                		<c:when test="${page.isALink() and not (page.index eq dc.pager.currentPage)}">
                			<!-- Lien vers page -->
	                		<li class="seu-pagin-item">
			                    <a data-page="${page.index}" href="${dc.getURLForPage(page.index)}">
			                        <span class="seu-flexbox">
			                            <span class="seu-btn-text">${page.label}</span>
			                        </span>
			                    </a>
			                </li>
                		</c:when>
                		<c:when test="${page.isALink() and (page.index eq dc.pager.currentPage)}">
                			<!-- Page en cours -->
	                		<li class="seu-pagin-item seu-is-active">
		                        <span class="seu-flexbox">
		                            <span class="seu-btn-text">${page.label}</span>
		                        </span>
		                    </li>
		                </c:when>
                		<c:otherwise>
		                	<!-- Texte -->
	                		<li class="seu-pagin-item">
		                        <span class="seu-flexbox">
		                            <span class="seu-btn-text">${page.label}</span>
		                        </span>
		                    </li>
                		</c:otherwise>
                	</c:choose>
                </c:forEach>
                
                <!-- Page suivante -->
                <li class="seu-pagin-next seu-pagin-item">
					<c:if test="${not dc.pager.onLastPage}">
	                    <a class="seu-btn-square seu-bordered seu-core" title="<liferay-ui:message key="go-to-next-page" />" 
	                    	data-action="next" href="${dc.getURLForPage(dc.pager.currentPage + 1)}">
	                        <span class="seu-flexbox">
	                            <span class="seu-btn-text"><liferay-ui:message key="next" /></span>
	                            <span class="seu-btn-arrow"></span>
	                        </span>
	                    </a>
              	 	</c:if>
                </li>
            </ul>
    	</c:if>
           
	</liferay-ui:search-container>
</aui:form>

