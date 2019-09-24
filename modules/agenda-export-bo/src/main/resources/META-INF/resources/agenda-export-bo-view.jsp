<%@ include file="/META-INF/resources/agenda-export-bo-init.jsp"%>

<c:set var="tab" value="${not empty param.tab ? param.tab : 'agendaExports' }" />

<!-- Declaration des URL de listing des entites -->
<liferay-portlet:renderURL var="agendaExportsURL">
	<portlet:param name="tab" value="agendaExports" />
</liferay-portlet:renderURL>

<!-- Declaration de l'URL de recherche dans le listing de l'entite courrante -->
<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="cmd" value="search" />
	<portlet:param name="tab" value="${param.tab}" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
</liferay-portlet:renderURL>

<!-- Barre de navigation -->
<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">

	<!-- Liste des onglet -->
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item href="${agendaExportsURL}" label="agendaExports"
			selected="${tab eq 'agendaExports'}" />
	</aui:nav>

	<aui:nav-bar-search>
		<aui:form action="${searchURL}" name="searchFm">
			<liferay-ui:input-search markupView="lexicon" />
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>

<!-- Corps de la page et selection du listing a afficher -->
<c:choose>
    <c:when test="${tab eq 'agendaExports'}">
        <liferay-util:include page="/agenda-export-bo-view-agenda-export.jsp" servletContext="<%=application %>">
        </liferay-util:include>
    </c:when>
</c:choose>
