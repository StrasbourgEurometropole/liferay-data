<%@page import="eu.strasbourg.portlet.notification.model.display.NotificationDisplay"%>
<%@ include file="./notification-viewer-init.jsp"%>

<div class="seu-container">
    <div class="generic-form">
		<h1>Mes notifications</h1>
		        
		<!-- Nombre de résultats et items par page -->
		<div class="seu-view-results">
		    <div class="seu-result-count"> 
		    	<c:choose>
		    		<c:when test="${dc.searchContainer.total == 0}">
		    			<liferay-ui:message key="no-notification" />
		    		</c:when>
		    		<c:when test="${dc.searchContainer.total gt 1}">
		    			${dc.searchContainer.total} <liferay-ui:message key="results" />
		    		</c:when>
		    		<c:otherwise>
		    			${dc.searchContainer.total} <liferay-ui:message key="result" />
		    		</c:otherwise>
		    	</c:choose>
		   	</div>
		    <!-- <div class="seu-filler"></div>
		    <div class="seu-result-filter">
		        <span><liferay-ui:message key="results-per-page" /></span>
		        <select name="filter" id="" class="toCustomSelect" onchange="this.options[this.selectedIndex].value && (window.location = this.options[this.selectedIndex].value);">
		            <c:forEach var="delta" items="${[5, 10, 20, 50, 100]}">
		            	<c:set var="selected" value="${delta eq dc.delta ? 'selected' : ''}" />
		            	<option value="${dc.getURLForDelta(delta)}" ${selected} >${delta}</option>
		            </c:forEach>
		        </select>
		    </div> -->
		</div>
		
		<!-- Liste des résultats -->
		<aui:form method="post" name="fm">
			<!-- Résultats -->
			<liferay-ui:search-container id="entriesSearchContainer"
						searchContainer="${dc.searchContainer}">
				<ul class="notification-list">	        
					<c:forEach var="notif" items="${dc.paginatedResults}">
						<portlet:actionURL name="showNotification" var="showNotification">
							<portlet:param name="notificationId" value="${notif.notificationId}"></portlet:param>
						</portlet:actionURL>
						<c:choose>
							<c:when test="${notif.isRead()}">
								<li class="notification-list__item notification-list__item--read">
							</c:when>
							<c:otherwise>
								<li class="notification-list__item">
							</c:otherwise>
						</c:choose>
				
							<a href="${showNotification}" class="notification-item" >
								<div class="notification-item__date">
									<fmt:formatDate type="date" value="${notif.date}" pattern = "dd.MM.yyyy"/>
								</div>
								<div class="notification-item__lead">
									<c:out value="${notif.title}" />
								</div>
							</a>
					
							<portlet:resourceURL id="toggleNotification" var="notificationURL">
								<portlet:param name="notificationId" value="${notif.notificationId}" />
							</portlet:resourceURL>
					
							<div class="notification-list__toggle">
								<div class="flexbox">
									<div class="notification-list__toggle-trigger">
										<input type="checkbox" id="${notif.notificationId}"
											onclick="callServeResource('${notificationURL}');"> <label
											for="${notif.notificationId}"></label>
									</div>
									<div class="notification-list__state notification-list__state--new">Non
										lue</div>
									<div
										class="notification-list__state notification-list__state--read">Lue</div>
								</div>
							</div>
						</li>
					</c:forEach>
				</ul>
						
				<!-- Pagination -->
				<c:if test="${dc.pager.lastPage > 1}">
		            <ul class="mseu-pagination unstyled">
		            	<!-- Page précédente -->
		                <li class="pagin-prev disabled pagin-item">
							<c:if test="${not dc.pager.onFirstPage}">
			                    <a class="seu-btn-square seu-bordered seu-core" data-action="prev" title="<liferay-ui:message key="go-to-previous-page" />"
									href="${dc.getURLForPage(dc.pager.currentPage - 1)}">
			                        <span class="flexbox">
			                            <span class="btn-text"><liferay-ui:message key="previous" /></span>
			                            <span class="btn-arrow"></span>
			                        </span>
			                    </a>
		               		</c:if>
		                </li>
		                <c:forEach var="page" items="${dc.pager.pages}">
		                	<c:choose>
		                		<c:when test="${page.isALink() and not (page.index eq dc.pager.currentPage)}">
		                			<!-- Lien vers page -->
			                		<li class="pagin-item">
					                    <a data-page="${page.index}" href="${dc.getURLForPage(page.index)}">
					                        <span class="flexbox">
					                            <span class="btn-text">${page.label}</span>
					                        </span>
					                    </a>
					                </li>
		                		</c:when>
		                		<c:when test="${page.isALink() and (page.index eq dc.pager.currentPage)}">
		                			<!-- Page en cours -->
			                		<li class="pagin-item is-active">
				                        <span class="flexbox">
				                            <span class="btn-text">${page.label}</span>
				                        </span>
				                    </li>
				                </c:when>
		                		<c:otherwise>
				                	<!-- Texte -->
			                		<li class="pagin-item">
				                        <span class="flexbox">
				                            <span class="btn-text">${page.label}</span>
				                        </span>
				                    </li>
		                		</c:otherwise>
		                	</c:choose>
		                </c:forEach>
		                
		                <!-- Page suivante -->
		                <li class="pagin-next pagin-item">
							<c:if test="${not dc.pager.onLastPage}">
			                    <a class="seu-btn-square seu-bordered seu-core" title="<liferay-ui:message key="go-to-next-page" />" 
			                    	data-action="next" href="${dc.getURLForPage(dc.pager.currentPage + 1)}">
			                        <span class="flexbox">
			                            <span class="btn-text"><liferay-ui:message key="next" /></span>
			                            <span class="btn-arrow"></span>
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
	.seu-view-results{
		display: -webkit-box;
	    display: -ms-flexbox;
	    display: flex;
	    -webkit-box-align: center;
	    -ms-flex-align: center;
	    align-items: center;
	    margin-top: 40px;
	    margin-bottom: 30px;
	    
	    .seu-filler{
	        -webkit-box-flex: 1;
		    -ms-flex: 1;
		    flex: 1;
	    }
    }
</style>
        
<aui:script>
function callServeResource(notificationURL){
    AUI().use('aui-io-request', function(A){
        A.io.request(notificationURL, {
               method: 'post'
        });
     });
}
</aui:script>
