<%@ include file="/csmap-bo-agenda-init.jsp"%>

<c:set var="tab"
	value="${not empty param.tab ? param.tab : 'agendaPrincipal' }" />

<liferay-portlet:renderURL var="agendaPrincipalURL">
	<portlet:param name="tab" value="agendaPrincipal" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="agendaThematiqueURL">
	<portlet:param name="tab" value="agendaThematique" />
</liferay-portlet:renderURL>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item href="${agendaPrincipalURL}" label="agendaPrincipal"
			selected="${tab eq 'agendaPrincipal'}" />

		<aui:nav-item href="${agendaThematiqueURL}" label="agendaThematique"
			selected="${tab eq 'agendaThematique'}" />
	</aui:nav>
</aui:nav-bar>
<c:choose>
    <c:when test="${tab eq 'agendaPrincipal'}">
		<liferay-util:include page="/csmap-bo-agenda-edit-principal.jsp" servletContext="<%=application %>">
		</liferay-util:include>
    </c:when>
    <c:when test="${tab eq 'agendaThematique'}">
		<liferay-util:include page="/csmap-bo-agenda-view-thematiques.jsp" servletContext="<%=application %>">
		</liferay-util:include>
    </c:when>
</c:choose>


