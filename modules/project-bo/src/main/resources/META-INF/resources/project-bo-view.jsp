<%@ include file="/project-bo-init.jsp"%>

<c:set var="tab" value="${not empty param.tab ? param.tab : 'projects' }" />

<!-- Declaration des URL de listing des entites -->
<liferay-portlet:renderURL var="projectsURL">
	<portlet:param name="tab" value="projects" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="participationsURL">
	<portlet:param name="tab" value="participations" />
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
		<aui:nav-item href="${projectsURL}" label="projects"
			selected="${tab eq 'projects'}" />
	</aui:nav>
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item href="${participationsURL}" label="participations"
			selected="${tab eq 'participations'}" />
	</aui:nav>

	<aui:nav-bar-search>
		<aui:form action="${searchURL}" name="searchFm">
			<liferay-ui:input-search markupView="lexicon" />
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>

<!-- Corps de la page et selection du listing a afficher -->
<c:choose>
    <c:when test="${tab eq 'projects'}">
		<liferay-util:include page="/project-bo-view-projects.jsp" servletContext="<%=application %>">
		</liferay-util:include>
    </c:when>
    <c:when test="${tab eq 'participations'}">
		<liferay-util:include page="/project-bo-view-participations.jsp" servletContext="<%=application %>">
		</liferay-util:include>
    </c:when>
</c:choose>