<%@ include file="/sectorized-init.jsp" %>

<div class="seu-container seu-view-places">
	
	<liferay-portlet:actionURL var="searchURL" name="search">
	</liferay-portlet:actionURL>
	<aui:form name="fm" action="${searchURL}" cssClass="seu-view-filters">
		<div class="seu-filter-line" style="align-items: flex-end;">
			<!-- Adresse -->
			<div class="widget widget-big" style="width: 100%;">
				<div class="title">
					<label for="query"><liferay-ui:message key="sect.query" /></label>
				</div>
				<div class="content">
					<input type="text" id="query" name="query" 
						placeholder="<liferay-ui:message key="sect.address-placeholder" />" value="${not empty streetName ? streetName : query}">
				</div>
			</div>
			<!-- Validation -->
			<div class="widget">
				<button type="submit" class="seu-btn-square seu-filled seu-core">
					<span class="seu-flexbox">
						<span class="seu-btn-text"><liferay-ui:message key="search" /></span>
						<span class="seu-btn-arrow"></span>
					</span>
				</button>
			</div>
		</div>
	</aui:form>
	
	<!-- Erreurs -->
	<div class="seu-error-messages">
		<liferay-ui:error key="unknown-error" message="eu.unknown-error" targetNode=".seu-error-messages" />
	   	<liferay-ui:error key="no-result" message="eu.no-result" targetNode=".seu-error-messages" />
	   	<liferay-ui:error key="too-many-results" message="eu.too-many-results" targetNode=".seu-error-messages" />
	</div>
	
	<!-- RÃ©sultats -->
	<c:if test="${not empty places}">
		<div class="seu-view-results">
			<div class="seu-result-count">${fn:length(places)} 
				<c:choose>
					<c:when test="${fn:length(places) gt 1}">
						<liferay-ui:message key="results" />
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="result" />
					</c:otherwise>
				</c:choose>
			</div>
			<div class="seu-filler"></div>
		</div>

		<c:forEach var="place" items="${places}">
			<div id="seu-grid--list01">
				<liferay-ddm:template-renderer
				    className="${className}"
				    contextObjects="${dc.getContextObjects(place)}"
				    displayStyle="${displayStyle}"
				    displayStyleGroupId="${displayStyleGroupId}"
				    entries="${entries}"
				>
					no-config
				</liferay-ddm:template-renderer>
			</div>
		</c:forEach>
	</c:if>

</div>

<liferay-util:html-bottom>
	<aui:script>
		define._amd = define.amd;
		define.amd = false;
	</aui:script>
	<script	src="/o/agendabo/js/vendors/jquery.autocomplete.js"></script>
	<script>
		define.amd = define._amd;
		<%--
			Si la configuration demande qu'on force la recherche sur Strasbourg
		 	on set cette variable globale utilisÃ©e dans le JS
		--%>		
		<c:choose>
			<c:when test="${forceStrasbourg}">
				window.forcedCity = 'Strasbourg';
			</c:when>
			<c:otherwise>
				window.forcedCity = '';
			</c:otherwise>
		</c:choose>
	</script>
	<script
		src="/o/sectorized/js/sectorized.js"
		type="text/javascript"></script>
		
	<style>
		.alert-success {
			display: none;
		}
	</style>
</liferay-util:html-bottom>