<%@ include file="/search-activity-init.jsp"%>

<liferay-portlet:renderURL var="searchURL" />

<div class="seu-container">
    <div class="seu-view-activity">
    	<!-- Formulaire de recherche -->
        <form class="seu-view-filters" method="post" action="${searchURL}">
            <div class="seu-filter-line">
            
            	<!-- Activités -->
                <div class="widget">
                    <div class="title content">
                        <aui:select cssClass="toCustomSelect" id="activity" name="activityId" label="activity">
                        	<aui:option value="" label="all-activities" />
                        	<c:forEach items="${dc.activitiesForDropdown}" var="activity">
                        		<aui:option value="${activity.activityId}" label="${activity.getTitle(locale)}" 
                        			selected="${param.activityId eq activity.activityId}" />
                        	</c:forEach>
                        </aui:select>
                   	</div>
                </div>
                
                <!-- Types -->
                <c:if test="${not empty dc.activityTypes}">
	                <div class="widget">
	                    <div class="title content">
	                        <aui:select cssClass="toCustomSelect" id="typeId" name="typeId" label="type">
	                            <aui:option value="" label="all-types" />
                                <c:forEach items="${dc.activityTypes}" var="category">
									<c:set var="category" value="${category}" scope="request" />
									<c:set var="level" value="0" scope="request" />
									<jsp:include page="/category-option.jsp" />
                                </c:forEach>
	                        </aui:select>
	                    </div>
	                </div>
	            </c:if>
	            
	            <!-- Publics -->
                <c:if test="${not empty dc.publics}">
	                <div class="widget">
	                    <div class="title content">
	                        <aui:select cssClass="toCustomSelect" id="publicId" name="publicId" label="public">
	                            <aui:option value="" label="all-publics" />
                                <c:forEach items="${dc.publics}" var="category">
									<c:set var="category" value="${category}" scope="request" />
									<c:set var="level" value="0" scope="request" />
									<jsp:include page="/category-option.jsp" />
                                </c:forEach>
	                        </aui:select>
	                    </div>
	                </div>
	            </c:if>
	            
	            <!-- Territoires -->
                <c:if test="${not empty dc.territories}">
	                <div class="widget">
	                    <div class="title content">
	                        <aui:select cssClass="toCustomSelect" id="territoryId" name="territoryId" label="territory">
	                            <aui:option value="" label="all-territories" />
                                <c:forEach items="${dc.territories}" var="category">
									<c:set var="category" value="${category}" scope="request" />
									<c:set var="level" value="0" scope="request" />
									<jsp:include page="/category-option.jsp" />
                                </c:forEach>
	                        </aui:select>
	                    </div>
	                </div>
	            </c:if>
            </div>
            
            <div class="seu-btn-line">
				<liferay-portlet:renderURL var="cancelURL" />
				<button type="button" onclick="window.location.href = '${cancelURL}'" class="seu-btn-square seu-bordered seu-core"> 
				    <span class="seu-flexbox">            
				        <span class="seu-btn-text" style="margin-right: 0"><liferay-ui:message key="cancel" /></span>
				    </span>
				</button>
                <button class="seu-btn-square seu-filled seu-core" type="submit"><span class="seu-flexbox"><span class="seu-btn-text"><liferay-ui:message key="search" /></span> <span class="seu-btn-arrow"></span></span></button>
            </div>
        </form>
        
        <!-- Nombre de résultats et items par page -->
        <div class="seu-view-results">
            <div class="seu-result-count">${dc.searchContainer.total} <liferay-ui:message key="results" /></div>
            <div class="seu-filler"></div>
            <div class="seu-result-filter">
                <span><liferay-ui:message key="results-per-page" /></span>
                <select name="filter" id="" class="toCustomSelect" onchange="this.options[this.selectedIndex].value && (window.location = this.options[this.selectedIndex].value);">
                    <c:forEach var="delta" items="${[5, 10, 20, 50, 100]}">
                    	<c:set var="selected" value="${delta eq dc.delta ? 'selected' : ''}" />
                    	<option value="${dc.getURLForDelta(delta)}" ${selected} >${delta}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        
        <!-- Liste des résultats -->
        <aui:form method="post" name="fm">
			<!-- Résultats -->
			<liferay-ui:search-container id="entriesSearchContainer"
						searchContainer="${dc.searchContainer}">
		        <ul id="seu-grid--list01">		        
					<c:forEach var="activityEntry" items="${dc.paginatedResults}">
						<liferay-ddm:template-renderer
							className="<%= Activity.class.getName() %>"
							contextObjects="${dc.getTemplateContextObjects(activityEntry.key)}"
							displayStyle="${displayStyle}"
							displayStyleGroupId="${displayStyleGroupId}"
							entries="${templateEntries}">
								Veuillez sélectionner un template dans la configuration du portlet.
						</liferay-ddm:template-renderer>
					</c:forEach>
				</ul>
				
				<!-- Pagination -->
				<c:if test="${dc.pager.lastPage > 1}">
		            <ul class="seu-pagination unstyled">
		            	<!-- Page précédente -->
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
        
    </div>
</div>
<!--Fin contenu  -->