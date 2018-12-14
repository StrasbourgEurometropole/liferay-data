<%@ include file="/search-asset-init.jsp" %>

<liferay-portlet:actionURL varImpl="searchActionURL" />

<div class="pro-search-listing">
	<div class="container">
	    <div class="row">
	        <div class="col-md-8 pro-bloc-listing-participation">
	            <div id="breadcrumb">
			        <span>
			            <span><a href="${dc.getHomeURL()}"><liferay-ui:message key="eu.breadcrumb-home" /></a>
			                <span class="breadcrumb_last"><liferay-ui:message key="eu.breadcrumb-initiative" /></span>
			            </span>
			        </span>
	            </div>
	            
	            <div class="pro-wrapper-sort" style="padding-top:30px;">
	            	<c:choose>
	                    <c:when test='${isUserloggedIn && hasUserPactSign && !isUserBanned}'>
	                        <a id="buttonSubmitInitiative" href="" class="pro-btn-yellow" data-toggle="modal" data-target="#modalSubmitInitiative">
	                        	<liferay-ui:message key="submit-initiative" />
	                        </a>
	                    </c:when>
	                    <c:when test='${!hasUserPactSign && !isUserBanned}'>
	                        <a id="buttonSubmitInitiative" href="" class="pro-btn-yellow" name="#Pact-sign">
	                        	<liferay-ui:message key="submit-initiative" />
	                        </a>
	                    </c:when>
	                    <c:when test='${isUserBanned}'>
	                        <a id="buttonSubmitInitiative" href="" class="pro-btn-yellow" name="#IsBanned">
	                        	<liferay-ui:message key="submit-initiative" />
	                        </a>
	                    </c:when>
	                </c:choose>
	            </div>
	
	            <div class="row pro-wrapper-listing-general">
	
	                <div class="col-xs-12">
	                
	                    <div class="row pro-wrapper-listing-participation">
		                    <div class="col-xs-12">
		                    	
		                        <!-- Resultats -->
		                        <aui:form method="post" name="fm">
		                            <div class="pro-listing-initiative"></div>
		                        </aui:form>
		                        
		                    </div>
	                	</div>
	
	                </div>
	            </div>
	        </div>
	        
	        <!-- Blocs lateraux -->
	        <div class="col-md-4 pro-wrapper-aside">
	           
	           	<!-- Bloc : formulaire -->
	            <div class="pro-bloc-facette pro-bloc-facette-participation">
	                <span class="pro-affiner"><liferay-ui:message key="eu.refine-research" /> <span class="icon-ico-chevron-down"></span></span>
	
	                <!-- Formulaire -->
					<aui:form action="${searchActionURL}" method="get" name="fm" id="search-asset-form">
						<liferay-portlet:renderURLParams varImpl="searchActionURL" />
						<liferay-util:include page="/forms/placit-initiatives-form.jsp" servletContext="<%=application%>" />
					</aui:form>
	                
	            </div>
	            
	            <!-- Bloc : Les plus populaires -->
	            <div class="pro-widget-participation">
	                <h4><liferay-ui:message key="eu.most.popular" /></h4>
	                <%-- <c:forEach var="bpAside" items="${budgetsMostSupported}">
	                    <a href="${dc.getHomeURL()}detail-budget-participatif/-/entity/id/${bpAside.budgetParticipatifId}" title="Lien vers le detail du budget">
	                        <div class="pro-meta">
	                            <span>${bpAside.getDistrictLabel(locale)}</span>
	                            <span>${bpAside.getThematicsLabel(locale)}</span>
	                        </div>
	                        <h3>${bpAside.title}</h3>
	
	                        <div class="pro-meta-footer">
	                            <span class="pro-comments"><strong>${bpAside.getNbApprovedComments()}</strong>Commentaire(s)</span>
	                        </div>
	                    </a>
	                </c:forEach> --%>
	            </div>
	            
	            <!-- Bloc : Les plus commentes -->
	            <div class="pro-widget-participation">
	                <h4><liferay-ui:message key="eu.most.commented" /></h4>
	                <%-- <c:forEach var="bpAside" items="${budgetsMostCommented}">
	                    <a href="${dc.getHomeURL()}detail-budget-participatif/-/entity/id/${bpAside.budgetParticipatifId}" title="Lien vers le detail du budget">
	                        <div class="pro-meta">
	                            <span>${bpAside.getDistrictLabel(locale)}</span>
	                            <span>${bpAside.getThematicsLabel(locale)}</span>
	                        </div>
	                        <h3>${bpAside.title}</h3>
	
	                        <div class="pro-meta-footer">
	                            <span class="pro-comments"><strong>${bpAside.getNbApprovedComments()}</strong>Commentaire(s)</span>
	                        </div>
	                    </a>
	                </c:forEach> --%>
	            </div>
	            
	            <!-- Bloc : Les plus recents coups de coeur du conseil -->
	            <div class="pro-widget-participation">
	                <h4><liferay-ui:message key="eu.most.iscrush" /></h4>
	                <%-- <c:forEach var="bpAside" items="${budgetsIsCrush}">
	                    <a href="${dc.getHomeURL()}detail-budget-participatif/-/entity/id/${bpAside.budgetParticipatifId}" title="Lien vers le detail du budget">
	                        <div class="pro-meta">
	                            <span>${bpAside.getDistrictLabel(locale)}</span>
	                            <span>${bpAside.getThematicsLabel(locale)}</span>
	                        </div>
	                        <h3>${bpAside.title}</h3>
	
	                        <div class="pro-meta-footer">
	                            <span class="pro-comments"><strong>${bpAside.getNbApprovedComments()}</strong>Commentaire(s)</span>
	                        </div>
	                    </a>
	                </c:forEach> --%>
	            </div>
	               
	    	</div>
	        
	    </div>
	</div>
	
	<!-- Pagination -->
	<div class="pro-pagination">
	    <div class="container">
	        <div class="row">
	            <div class="col-sm-6 col-xs-4 pull-left">
	                <p class="hidden-xs"></p>
	            </div>
	
	            <!-- Pagination : liens de navigation -->
	            <div class="col-sm-6 col-xs-8 pull-right"></div>
	        </div>
	    </div>
	</div>
	
</div>