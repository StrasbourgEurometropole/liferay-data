<%@ include file="/project-bo-init.jsp"%>

<c:set var="tab" value="${not empty param.tab ? param.tab : 'projects' }" />

<!-- Declaration des URL de listing des entites -->
<liferay-portlet:renderURL var="projectsURL">
	<portlet:param name="tab" value="projects" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="participationsURL">
	<portlet:param name="tab" value="participations" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="petitionsURL">
	<portlet:param name="tab" value="petitions" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="budgetParticipatifURL">
	<portlet:param name="tab" value="budgets-participatifs" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="budgetPhasesURL">
	<portlet:param name="tab" value="budget-phases" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="initiativesURL">
	<portlet:param name="tab" value="initiatives" />
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
		<aui:nav-item href="${participationsURL}" label="participations" selected="${tab eq 'participations'}" />
	</aui:nav>
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item href="${petitionsURL}" label="petitions" selected="${tab eq 'petitions'}" />
	</aui:nav>
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item href="${budgetParticipatifURL}" label="budgets-participatifs" selected="${tab eq 'budgets-participatifs'}" />
	</aui:nav>
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item href="${budgetPhasesURL}" label="budget-phases" selected="${tab eq 'budget-phases'}" />
	</aui:nav>
 	<aui:nav cssClass="navbar-nav">
 		<aui:nav-item href="${initiativesURL}" label="initiatives" selected="${tab eq 'initiatives'}" /> 
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
    <c:when test="${tab eq 'petitions'}">
		<liferay-util:include page="/project-bo-view-petitions.jsp" servletContext="<%=application %>">
		</liferay-util:include>
    </c:when>
    <c:when test="${tab eq 'budgets-participatifs'}">
		<liferay-util:include page="/project-bo-view-budgets-participatifs.jsp" servletContext="<%=application %>">
		</liferay-util:include>
    </c:when>
    <c:when test="${tab eq 'budget-phases'}">
		<liferay-util:include page="/project-bo-view-budget-phases.jsp" servletContext="<%=application %>">
		</liferay-util:include>
    </c:when>
    <c:when test="${tab eq 'initiatives'}">
		<liferay-util:include page="/project-bo-view-initiatives.jsp" servletContext="<%=application %>">
		</liferay-util:include>
    </c:when>
</c:choose>