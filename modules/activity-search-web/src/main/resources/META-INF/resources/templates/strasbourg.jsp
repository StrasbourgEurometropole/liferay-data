<%@ include file="/search-activity-init.jsp"%>

<liferay-portlet:renderURL var="searchURL" />
    <script>
        $(document).ready(
        	function() { 
        		$("#activity").select2(); 
        	}
        );
    </script>

<div class="seu-container">
    <div class="seu-view-activity">
    	<!-- Formulaire de recherche -->
        <form class="seu-view-filters" method="post" action="${searchURL}">
            <div class="seu-filter-line">
            
            	<!-- ActivitÃ©s -->
                <div class="widget">
                    <div class="title">
                        <label for="activity"><liferay-ui:message key="activity" /></label>
                    </div>
                    <div class="content">
                        <select cssClass="toCustomSelect" id="activity" name="activityId" >
                            <aui:option value="" label="all-activities" selected="${empty param.activityId}" />
                        	<c:forEach items="${dc.activitiesForDropdown}" var="activity">
                            	<aui:option value="${activity.activityId}" label="${activity.getTitle(locale)}" 
                            		selected="${param.activityId eq activity.activityId}" />
                        	</c:forEach>
                        </select>
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
            
            <div class="seu-filter-line">
            
            	<!-- Heure de dÃ©but -->
                <div class="widget type-heure">
                    <div class="title">
                        <label for="hour_start"><liferay-ui:message key="start-time" /></label>
                    </div>
                    <div class="content">
                        <select class="toCustomSelect" id="hour_start" name="startTimeHour">
                            <c:forEach var="hour" begin="0" end="23">
                            	<fmt:formatNumber minIntegerDigits="2" value="${hour}" var="twoDigitsHour" />
                                <aui:option value="${twoDigitsHour}" label="${twoDigitsHour}" selected="${twoDigitsHour eq param.startTimeHour}" />
                            </c:forEach>
                        </select>
                        <select class="toCustomSelect" id="minute_start" name="startTimeMinute">
                            <aui:option value="00" label=":00" selected="${param.startTimeMinute eq '00'}" />
                            <aui:option value="15" label=":15" selected="${param.startTimeMinute eq '15'}" />
                            <aui:option value="30" label=":30" selected="${param.startTimeMinute eq '30'}" />
                            <aui:option value="45" label=":45" selected="${param.startTimeMinute eq '45'}" />
                        </select>
                    </div>
                </div>

                <!-- Heure de fin -->
                <div class="widget type-heure">
                    <div class="title">
                        <label for="hour_end"><liferay-ui:message key="end-time" /></label>
                    </div>
                    <div class="content">
                        <select class="toCustomSelect" id="hour_end" name="endTimeHour">
                            <c:forEach var="hour" begin="0" end="23">
                            	<fmt:formatNumber minIntegerDigits="2" value="${hour}" var="twoDigitsHour" />
                                <aui:option value="${twoDigitsHour}" label="${twoDigitsHour}"
                                	selected="${twoDigitsHour eq param.endTimeHour or (empty param.endTimeHour and twoDigitsHour eq '23')}" />
                            </c:forEach>
                        </select>
                        <select class="toCustomSelect" id="minute_end" name="endTimeMinute">
                            <aui:option value="00" label=":00" selected="${param.endTimeMinute eq '00'}" />
                            <aui:option value="15" label=":15" selected="${param.endTimeMinute eq '15'}" />
                            <aui:option value="30" label=":30" selected="${param.endTimeMinute eq '30'}" />
                            <aui:option value="45" label=":45" selected="${param.endTimeMinute eq '45' or empty param.endTimeMinute}" />
                        </select>
                    </div>
                </div>

                <div class="widget">
                    <div class="title">
                        <label for="days"><liferay-ui:message key="days" /></label>
                    </div>
                    <div class="content">
                        <select id="days" multiple="multiple" name="days">
                            <aui:option value="monday" label="monday" selected="${dc.isDaySelected('monday')}" />
                            <aui:option value="tuesday" label="tuesday" selected="${dc.isDaySelected('tuesday')}" />
                            <aui:option value="wednesday" label="wednesday" selected="${dc.isDaySelected('wednesday')}" />
                            <aui:option value="thursday" label="thursday" selected="${dc.isDaySelected('thursday')}" />
                            <aui:option value="friday" label="friday" selected="${dc.isDaySelected('friday')}" />
                            <aui:option value="saturday" label="saturday" selected="${dc.isDaySelected('saturday')}" />
                            <aui:option value="sunday" label="sunday" selected="${dc.isDaySelected('sunday')}" />
                        </select>
                    </div>
                </div>
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

		<c:if test="${not empty dc.text}">
			<div class="activities-postform">${dc.text}</div>
		</c:if>
        
        <!-- Nombre de rÃ©sultats et items par page -->
        <div class="seu-view-results">
            <div class="seu-result-count">${dc.searchContainer.total} 
            	<c:choose>
            		<c:when test="${dc.searchContainer.total gt 1}">
            			<liferay-ui:message key="results" />
            		</c:when>
            		<c:otherwise>
            			<liferay-ui:message key="result" />
            		</c:otherwise>
            	</c:choose>
           	</div>
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
        
        <!-- Liste des rÃ©sultats -->
        <aui:form method="post" name="fm">
			<!-- RÃ©sultats -->
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
								Veuillez sÃ©lectionner un template dans la configuration du portlet.
						</liferay-ddm:template-renderer>
					</c:forEach>
				</ul>
				
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
        
    </div>
</div>

<style>
	.select2-container--default .select2-selection--single .select2-selection__rendered {
		padding-left: 7.5px;
		padding-top: 8px;
	    font-family: "MontSerrat", arial;
	    font-size: 1.4rem;
	    color: #7e7e7e;
	    text-transform: uppercase;
	}
	
	.select2-selection__arrow{
		display: none;
	}
</style>
<!--Fin contenu  -->