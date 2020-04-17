<%@ include file="/council-init.jsp" %>


<c:set var="tab" value="${not empty param.tab ? param.tab : 'councilSessions' }" />

<liferay-portlet:renderURL var="councilSessionsURL">
	<portlet:param name="tab" value="councilSessions" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="deliberationsURL">
	<portlet:param name="tab" value="deliberations" />
</liferay-portlet:renderURL>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item href="${councilSessionsURL}" label="councilSessions"
			selected="${tab eq 'councilSessions'}" />

		<aui:nav-item href="${deliberationsURL}" label="deliberations"
			selected="${tab eq 'deliberations'}" />

	</aui:nav>
</aui:nav-bar>

<c:choose>
    <c:when test="${tab eq 'councilSessions'}">
		<liferay-util:include page="/council-bo-view-sessions.jsp" servletContext="<%=application %>">
		</liferay-util:include>
    </c:when>
    <c:when test="${tab eq 'deliberations'}">
		<liferay-util:include page="/council-bo-view-deliberations.jsp" servletContext="<%=application %>">
		</liferay-util:include>
    </c:when>
</c:choose>