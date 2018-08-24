<%@ include file="/demarches-init.jsp" %>
 <div class="wi-wrapper">
    <section id="demandes">
		<c:if test="${showDeleteButton}">
			<button class="delete-wi" data-portlet-id="${themeDisplay.portletDisplay.id}"></button>
		</c:if>
        <h2>${title}</h2>
	   	<c:if test="${not empty error}">
			<div class="error"><liferay-ui:message key="eu.webservice-indispo" /></div>
	   	</c:if>
	   	<c:if test="${empty error}">
			<c:if test="${not empty demarches}">
				<ul class="demandes-list unstyled">
		            <c:forEach var="demarche" items="${demarches}">
			            <li class="demandes-item">
			            	<a href="${demarche.url}" class="btn-square--bordered--core">
			            		<span class="flexbox"><span class="btn-text">${demarche.name} - ${demarche.status}</span><span class="btn-arrow"></span></span>
			            	</a>
			            </li>
		            </c:forEach>
		        </ul>
			</c:if>
			<c:if test="${empty demarches}">
	            <span class="text-noelt"><liferay-ui:message key="no-step" /></span><br>
	        </c:if>
	   	</c:if>
	       
	    <a href="<c:if test='${not empty toutesLesDemarches}'>${toutesLesDemarches}</c:if><c:if test='${empty toutesLesDemarches}'>#</c:if>" class="btn-square--filled--core">
	    	<span class="flexbox"><span class="btn-text"><liferay-ui:message key="all-steps" /></span><span class="btn-arrow"></span></span>
	    </a>
    </section>
</div>

<style>
	#portlet_eu_strasbourg_portlet_demarches_portlet_DemarchesWebPortlet .serial {
		width: 100%;
	}
	#portlet_eu_strasbourg_portlet_demarches_portlet_DemarchesWebPortlet .serial h2 {
		max-width: none;
	} 
	#portlet_eu_strasbourg_portlet_demarches_portlet_DemarchesWebPortlet #demandes {
		justify-content: flex-start;
	}
	#portlet_eu_strasbourg_portlet_demarches_portlet_DemarchesWebPortlet .text-noelt {
	    padding: 15px 20px;
	    color: #31455d;
	    font-family: "MontSerrat", arial;
	    font-size: 1.4rem;
	    text-transform: uppercase;
    }
</style>
