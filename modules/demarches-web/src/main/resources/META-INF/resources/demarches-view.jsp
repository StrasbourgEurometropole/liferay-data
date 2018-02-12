<%@ include file="/demarches-init.jsp" %>
 <div class="wi-wrapper">
    <section id="demandes">
        <h2><liferay-ui:message key="follow-up-request" /></h2>
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
	       
		    <a href="<c:if test='${not empty toutesLesDemarches}'>${toutesLesDemarches}</c:if><c:if test='${empty toutesLesDemarches}'>#</c:if>" class="btn-square--filled--core">
		    	<span class="flexbox"><span class="btn-text"><liferay-ui:message key="all-steps" /></span><span class="btn-arrow"></span></span>
		    </a>
    </section>

    <section id="serial">
        <h2><liferay-ui:message key="tracking-code" /></h2>
        <liferay-portlet:runtime
            portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
            instanceId="code_suivi" />
        <form id="formSuivi">
            <input id="codeSuivi" type="text" placeholder="EX: CNPHNTFB " required aria-required="true">
	        <button data-url-suivi="${urlSuivi}" type="submit" class="btn-square--filled--core"><span class="flexbox"><span class="btn-text"><liferay-ui:message key="submit" /></span><span class="btn-arrow"></span></span></button>
	    </form>
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
