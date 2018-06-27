<%@ include file="/search-asset-init.jsp"%>
<!-- File d'ariane -->
<div class="pro-wrapper-top-listing">
	<div class="container">
		<div id="breadcrumb">
			<span> <span><a href="${dc.getHomeURL()}"><liferay-ui:message
							key="eu.breadcrumb-home" /></a> <span class="breadcrumb_last"><liferay-ui:message
							key="eu.breadcrumb-news" /></span> </span>
			</span>
		</div>
	</div>
</div>
<!-- Listing -->
<section class="container pro-listing" data-egalize=" > *">
	<aui:form method="post" name="fm">
		<liferay-ui:search-container id="entriesSearchContainer"
			searchContainer="${dc.searchContainer}">
			<liferay-ui:search-container-results results="${dc.entries}" />
			<liferay-ui:search-container-row
				className="com.liferay.asset.kernel.model.AssetEntry"
				modelVar="entry" keyProperty="entryId" rowIdProperty="entryId">
				<c:set var="className" value="${entry.className}" />
				<c:choose>
					<c:when test="${fn:contains(className, 'JournalArticle')}">
						<c:set var="className"
							value="com.liferay.asset.kernel.model.AssetEntry" />
					</c:when>
					<c:when test="${fn:contains(className, 'DLFileEntry')}">
						<c:set var="className"
							value="com.liferay.portal.kernel.repository.model.FileEntry" />
					</c:when>
				</c:choose>
				<liferay-ddm:template-renderer className="${className}"
					contextObjects="${dc.getTemplateContextObjects(entry)}"
					displayStyle="${dc.templatesMap[entry.className]}"
					displayStyleGroupId="${themeDisplay.scopeGroupId}"
					entries="${dc.templateEntries }">
					<liferay-ui:asset-display assetEntry="${entry}"
						assetRenderer="${entry.assetRenderer}"
						assetRendererFactory="${entry.assetRendererFactory}"
						template="abstract" />
				</liferay-ddm:template-renderer>
			</liferay-ui:search-container-row>
		</liferay-ui:search-container>
	</aui:form>
</section>
<!-- Pagination -->
<div class="pro-pagination">
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-xs-4 pull-left">

				<!-- Pagination : selecteur de page -->
            	<c:if test="${dc.pager.lastPage > 1}">
            		<form action="/" method="get">
		                <label for="change-page" class="hide" aria-labelledby="change-page" aria-hidden="true" aria-label="change-page">Changer de page</label>
		                <select id="change-page" name="change-page" onchange="location = this.value;">
		                    <c:forEach var="pageIndex" begin="1" end="${dc.pager.lastPage}">
		                    	<c:choose>
		                    		<c:when test="${pageIndex != dc.pager.lastPage}">
			                			<option value="${dc.getURLForPage(pageIndex)}">
											<liferay-ui:message key="eu.page" /> ${pageIndex} ( ${dc.pager.delta} )
			                			</option>
			                		</c:when>
			                		<c:otherwise>
			                			<option value="${dc.getURLForPage(pageIndex)}">
			                				<liferay-ui:message key="eu.page" /> ${pageIndex} ( ${dc.pager.delta - ( dc.pager.lastPage * dc.pager.delta - dc.pager.count)} )
			                			</option>
			                		</c:otherwise>
			                	</c:choose>
			                </c:forEach>
		                </select>
		        	</form>
	            </c:if>

				<!-- Pagination : label -->
				<p class="hidden-xs">
					<liferay-ui:message key="eu.show-results" />
					<c:choose>
						<c:when test="${dc.pager.count > 0}">
		                		${dc.pager.currentPage * dc.pager.delta - (dc.pager.delta - 1)}
		                	</c:when>
						<c:otherwise>
		                		0
		                	</c:otherwise>
					</c:choose>
					-
					<c:choose>
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
					<liferay-ui:message key="eu.among" />
					${dc.pager.count}
					<liferay-ui:message key="eu.project-minus-global" />
				</p>
			</div>

			<!-- Pagination : liens de navigation -->
			<div class="col-sm-6 col-xs-8 pull-right">

				<c:if test="${dc.pager.lastPage > 1}">
					<ul>

						<!-- Lien vers la premiere page -->
						<c:if test="${not dc.pager.onFirstPage}">
							<li><a href="${dc.getURLForPage(1)}"
								class="hidden-sm hidden-xs pro-first"
								title="<liferay-ui:message key="eu.listing-link-to-first-page" />">
									<liferay-ui:message key="eu.first" />
							</a></li>
						</c:if>

						<!-- Lien vers la page precedente page -->
						<c:if test="${not dc.pager.onFirstPage}">
							<li><a href="${dc.getURLForPage(dc.pager.currentPage - 1)}"
								title="<liferay-ui:message key="eu.listing-link-to-previous-pag" />">
									<liferay-ui:message key="eu.previous" />
							</a></li>
						</c:if>

						<!-- Lien vers la page suivante -->
						<c:if test="${not dc.pager.onLastPage}">
							<li><a href="${dc.getURLForPage(dc.pager.currentPage + 1)}"
								title="<liferay-ui:message key="eu.listing-link-to-next-page" />">
									<liferay-ui:message key="eu.next" />
							</a></li>
						</c:if>

						<!-- Lien vers la derniere page -->
						<c:if test="${not dc.pager.onLastPage}">
							<li><a href="${dc.getURLForPage(dc.pager.lastPage)}"
								class="hidden-sm hidden-xs pro-last"
								title="<liferay-ui:message key="eu.listing-link-to-last-page" />">
									<liferay-ui:message key="eu.last" />
							</a></li>
						</c:if>

					</ul>
				</c:if>

			</div>
		</div>
	</div>
</div>
<aui:script>
	$(function() {
		//Force la premiere tuille a  prendre deux fois plus de place en hauteur de largeur
		$('.col-md-3.col-sm-6.col-xs-12').first().removeClass('col-md-3').removeClass('col-sm-6').addClass('col-md-6').addClass('col-sm-12');
		$('.pro-bloc-actu').first().addClass('pro-bloc-actu-large');
		
		// Change la valeur du selecteur de page par la valeur courante
		$('#change-page').prop('selectedIndex', ${dc.pager.currentPage - 1}).selectric('refresh');
	});
	  
	$(document).ready(function() {
		
	});
</aui:script>