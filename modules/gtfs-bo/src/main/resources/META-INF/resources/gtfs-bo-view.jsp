<%@ include file="/gtfs-bo-init.jsp"%>

<c:set var="tab" value="${not empty param.tab ? param.tab : 'import-historics' }" />

<!-- Declaration des URL de listing des entites -->
<liferay-portlet:renderURL var="importHistoricsURL">
	<portlet:param name="tab" value="import-historics" />
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
		<aui:nav-item href="${importHistoricsURL}" label="import-historics"
			selected="${tab eq 'import-historics'}" />
	</aui:nav>
	<aui:nav-bar-search>
		<aui:form action="${searchURL}" name="searchFm">
			<liferay-ui:input-search markupView="lexicon" />
		</aui:form>
	</aui:nav-bar-search>
	
</aui:nav-bar>

<!-- Corps de la page et selection du listing a afficher -->
<c:choose>
    <c:when test="${tab eq 'import-historics'}">
		<liferay-util:include page="/gtfs-bo-view-import-historics.jsp" servletContext="<%=application %>">
		</liferay-util:include>
    </c:when>
</c:choose>