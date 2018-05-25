<%@ include file="/search-asset-init.jsp" %>

<liferay-portlet:actionURL varImpl="searchActionURL" />

 <div class="container">
    <div class="row">
        <div class="col-md-8 pro-bloc-listing-participation">
            <div id="breadcrumb">
        <span>
            <span><a href="index.html"><liferay-ui:message key="eu.home" /></a>
                <span class="breadcrumb_last"><liferay-ui:message key="eu.breadcrumb-participation" /></span>
            </span>
        </span>
            </div>

            <div class="pro-wrapper-sort">				
                <span class="pro-legend"><liferay-ui:message key="eu.legend-participations" /></span>
            </div>

            <div class="row pro-wrapper-listing-participation">

                <div class="col-xs-12">
                
                    <aui:form method="post" name="fm">
                    
						<!-- Resultats -->
						<liferay-ui:search-container id="entriesSearchContainer"
									searchContainer="${dc.searchContainer}">

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
				            
				            <liferay-ui:search-paginator searchContainer="${dc.searchContainer}" />
				            
						</liferay-ui:search-container>
					</aui:form>

                </div>
            </div>
        </div>

        <div class="col-md-4 pro-wrapper-aside">
            <div class="pro-bloc-facette pro-bloc-facette-participation">
                <span class="pro-affiner"><liferay-ui:message key="eu.show-research" /> <span class="icon-ico-chevron-down"></span></span>

                <!-- Formulaire -->
				<aui:form action="${searchActionURL}" method="get" name="fm"
					id="search-asset-form">
					<liferay-portlet:renderURLParams varImpl="searchActionURL" />
					<liferay-util:include page="/forms/placit-participations-form.jsp"
						servletContext="<%=application%>" />
				</aui:form>
                
            </div>

            <div class="pro-widget-participation">
                <h4><liferay-ui:message key="eu.most-searched" /></h4>
                
                <!-- 
                <a href="detail-event.html" title="Lien vers Titre de l'evenement">
                    <div class="pro-meta">
                        <span>Quartier</span>
                        <span>ThÃÂ©matique</span>
                    </div>
                    <h3>Titre de lÃ¢ÂÂÃÂvÃÂ¨nement<br>Sur deux lignes</h3>

                    <div class="pro-meta-footer">
                        <span class="pro-comments"><strong>37 </strong>Commentaire(s)</span>
                        <div class="pro-avis">
                            <span class="pro-like"><span class="icon-ico-like"></span> 1808</span>
                            <span class="pro-dislike"><span class="icon-ico-like"></span> 404</span>
                        </div>
                    </div>
                </a>
                -->

            </div>


            <div class="pro-widget-participation">
                <h4><liferay-ui:message key="eu.least-noticed" /></h4>
                
                <!-- 
                <a href="detail-event.html" title="Lien vers Titre de l'evenement">
                    <div class="pro-meta">
                        <span>Quartier</span>
                        <span>ThÃÂ©matique</span>
                    </div>
                    <h3>Titre de lÃ¢ÂÂÃÂvÃÂ¨nement<br>Sur deux lignes</h3>

                    <div class="pro-meta-footer">
                        <span class="pro-comments"><strong>9 </strong>Commentaire(s)</span>
                        <div class="pro-avis">
                            <span class="pro-like"><span class="icon-ico-like"></span> 1808</span>
                            <span class="pro-dislike"><span class="icon-ico-like"></span> 404</span>
                        </div>
                    </div>
                </a>
                -->
                
            </div>

        </div>
    </div>
</div>

<!-- Pagination -->
<div class="pro-pagination">
    <div class="container">
        <div class="row">
            <div class="col-sm-6 col-xs-4 pull-left">
                <form action="/" method="get">
                    <label for="change-page" class="hide" aria-labelledby="change-page" aria-hidden="true" aria-label="change-page">Changer de page</label>
                    <select id="change-page" name="change-page">
                    	<c:forEach var="page" items="${dc.pager.pages}">
	                        <option><a href="${dc.getURLForPage(page.index)}"><liferay-ui:message key="eu.page" /> ${page.index}</a></option>
	                    </c:forEach>
                    </select>
                </form>
                <p class="hidden-xs">
                	<liferay-ui:message key="eu.show-results" /> ?-? <liferay-ui:message key="eu.among" /> ${dc.pager.count} <liferay-ui:message key="eu.participations" />
                </p>
            </div>

            <div class="col-sm-6 col-xs-8 pull-right">
                <ul>
                
                	<!-- Lien vers la premiere  page -->
                	<c:if test="${not dc.pager.onFirstPage}">
                    	<li>
                    		<a href="${dc.getURLForPage(1)}" class="hidden-sm hidden-xs pro-first" title="Lien vers la premiere page du Listing">
                    			<liferay-ui:message key="eu.first" />
                    		</a>
                    	</li>
                    </c:if>
                    
                	<!-- Lien vers la page suivante -->
                	<c:if test="${not dc.pager.onFirstPage}">
                    	<li>
                    		<a href="${dc.getURLForPage(dc.pager.currentPage - 1)}" class="hidden-sm hidden-xs pro-first" title="Lien vers la page prÃ©cÃ©dente du Listing">
                    			<liferay-ui:message key="eu.previous" />
                    		</a>
                    	</li>
                    </c:if>
                    
                    <!-- Lien vers la page precedente page -->
                    <c:if test="${not dc.pager.onLastPage}">
                    	<li>
                    		<a href="${dc.getURLForPage(dc.pager.currentPage + 1)}" title="Lien vers la page suivante du Listing">
                    			<liferay-ui:message key="eu.next" />
                    		</a>
                    	</li>
                    </c:if>
                    
                    <!-- Lien vers la derniere page -->
                    <c:if test="${not dc.pager.onLastPage}">
                    	<li>
                    		<a href="${dc.getURLForPage(dc.pager.lastPage)}" class="hidden-sm hidden-xs pro-last" title="Lien vers la derniere page du Listing">
                    			<liferay-ui:message key="eu.last" />
                    		</a>
                    	</li>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</div>