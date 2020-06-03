<%@ include file="/council-bo-init.jsp" %>

<c:set var="tab" value="${not empty param.tab ? param.tab : 'councilSessions' }" />

<liferay-portlet:renderURL var="typesURL">
	<portlet:param name="tab" value="types" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="councilSessionsURL">
	<portlet:param name="tab" value="councilSessions" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="deliberationsURL">
	<portlet:param name="tab" value="deliberations" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="officialsURL">
	<portlet:param name="tab" value="officials" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="officialsConnectionURL">
	<portlet:param name="tab" value="officialsConnection" />
</liferay-portlet:renderURL>

<c:if test = "${tab ne 'officialsConnection'}">
	<liferay-portlet:renderURL varImpl="searchURL">
		<portlet:param name="cmd" value="search" />
		<portlet:param name="tab" value="${param.tab}" />
		<portlet:param name="orderByCol" value="${dc.orderByCol}" />
		<portlet:param name="orderByType" value="${dc.orderByType}" />
		<portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
	</liferay-portlet:renderURL>
</c:if>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item href="${typesURL}" label="types"
			selected="${tab eq 'types'}" />
		<aui:nav-item href="${councilSessionsURL}" label="councilSessions"
			selected="${tab eq 'councilSessions'}" />

		<aui:nav-item href="${deliberationsURL}" label="deliberations"
			selected="${tab eq 'deliberations'}" />

        <aui:nav-item href="${officialsURL}" label="officials"
        	selected="${tab eq 'officials'}" />
        	
       	<aui:nav-item href="${officialsConnectionURL}" label="officialsConnection"
        	selected="${tab eq 'officialsConnexion'}" />

	</aui:nav>

	<c:if test = "${tab ne 'officialsConnection'}">
		<aui:nav-bar-search>
	        <aui:form action="${searchURL}" name="searchFm">
	            <liferay-ui:input-search markupView="lexicon" />
	        </aui:form>
	    </aui:nav-bar-search>
    </c:if>
</aui:nav-bar>

<c:choose>
    <c:when test="${tab eq 'types'}">
		<liferay-util:include page="/council-bo-view-types.jsp" servletContext="<%=application %>">
		</liferay-util:include>
    </c:when>
    <c:when test="${tab eq 'councilSessions'}">
		<liferay-util:include page="/council-bo-view-council-sessions.jsp" servletContext="<%=application %>">
		</liferay-util:include>
    </c:when>
    <c:when test="${tab eq 'deliberations'}">
		<liferay-util:include page="/council-bo-view-deliberations.jsp" servletContext="<%=application %>">
		</liferay-util:include>
    </c:when>
    <c:when test="${tab eq 'officials'}">
        <liferay-util:include page="/council-bo-view-officials.jsp" servletContext="<%=application %>">
        </liferay-util:include>
    </c:when>
    <c:when test="${tab eq 'officialsConnection'}">
        <liferay-util:include page="/council-bo-view-officials-connection.jsp" servletContext="<%=application %>">
        </liferay-util:include>
    </c:when>
</c:choose>