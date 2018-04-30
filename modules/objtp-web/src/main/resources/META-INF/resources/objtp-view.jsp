<%@ include file="/objtp-init.jsp" %>

<c:set var="numeroLabel"><liferay-ui:message key="objtp-numero" /></c:set>
<c:set var="dateLabel"><liferay-ui:message key="objtp-date" /></c:set>

<main class="seu-container">
    <h1>${title}</h1>
    
    <!-- Nombre de rÃ©sultats et items par page -->
    <div class="seu-view-agenda" style="border-top: solid 2px #f6f6f6;">
		<div class="seu-view-results">
		    <div class="seu-result-count"> 
		    	<c:choose>
		    		<c:when test="${dc.searchContainer.total == 0}">
		    			<liferay-ui:message key="no-found-objects" />
		    		</c:when>
		    		<c:when test="${dc.searchContainer.total gt 1}">
		    			${dc.searchContainer.total} <liferay-ui:message key="objtp-found-objects" />
		    		</c:when>
		    		<c:otherwise>
		    			${dc.searchContainer.total} <liferay-ui:message key="objtp-found-object" />
		    		</c:otherwise>
		    	</c:choose>
		   	</div>
		    <div class="seu-filler"></div>
		    <div class="seu-result-filter">
		        <span><liferay-ui:message key="results-per-page" /></span>
		        <select name="filter" id="" class="toCustomSelect silencedSelect" onchange="this.options[this.selectedIndex].value && (window.location = this.options[this.selectedIndex].value);">
		            <c:forEach var="delta" items="${[10, 20, 50, 100]}">
		            	<c:set var="selected" value="${delta eq dc.delta ? 'selected' : ''}" />
		            	<option value="${dc.getURLForDelta(delta)}" ${selected} >${delta}</option>
		            </c:forEach>
		        </select>
		    </div> 
		</div>
	</div>
    
    <aui:form method="post" name="fm">
		<!-- RÃ©sultats -->
		<liferay-ui:search-container id="entriesSearchContainer"
					searchContainer="${dc.searchContainer}">
		<div class="objtp-gallery">
		    <div id="objtp-detail-container"style="position: relative">
		    <div class="gutter-sizer"></div>
		    	<c:forEach items="${dc.paginatedResults}" var="object"> 
		    		<c:if test="${not empty object.imageUrl}">
		    		<fmt:formatDate value="${object.date}"
					var="formattedDate" type="date" pattern="dd/MM/yyyy" />
			    		<c:set var="legend" value="${title} : ${numeroLabel} ${object.number} ${dateLabel} ${formattedDate}"/>		    		
				        <div class="objtp-detail-item">
				        	<div class="objtp-image-container">
					        	<a href="${object.imageUrl}" title="${legend}">
					            	<img class="objtp-picture" src="${object.imageUrl}" title="${legend}"></img>
					            </a>
				            </div>
				            <div class="objtp-info">
				            	<p>${numeroLabel} ${object.number}</p>
				            	<p>${dateLabel} ${formattedDate}</p>
				            </div>
				        </div>
			        </c:if>
		        </c:forEach>
		    </div>
 		</div>
		    
		    <!-- Pagination -->
			<c:if test="${dc.pager.lastPage > 1}">
	            <ul class="seu-pagination unstyled">

	            	<!-- Page prÃÂ©cÃÂ©dente -->
	                <li class="seu-pagin-prev seu-pagin-item">
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
</main>