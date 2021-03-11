<%@ include file="/help-bo-init.jsp"%>

<c:set var="tab" value="${not empty param.tab ? param.tab : 'helpProposals' }" />

<!-- Declaration des URL de listing des entites -->
<liferay-portlet:renderURL var="helpProposalsURL">
	<portlet:param name="tab" value="helpProposals" />
</liferay-portlet:renderURL>
<liferay-portlet:renderURL var="helpSeekersURL">
	<portlet:param name="tab" value="helpSeekers" />
</liferay-portlet:renderURL>

<!-- Declaration de l'URL de recherche dans le listing de l'entite courrante -->
<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="cmd" value="search" />
	<portlet:param name="tab" value="${param.tab}" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
</liferay-portlet:renderURL>

<div class="help-bo">

    <!-- Barre de navigation -->
    <aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">

        <!-- Liste des onglet -->
        <aui:nav cssClass="navbar-nav">
            <aui:nav-item href="${helpProposalsURL}" label="helpProposals" selected="${tab eq 'helpProposals'}" />
        </aui:nav>
        <aui:nav cssClass="navbar-nav">
            <aui:nav-item href="${helpSeekersURL}" label="helpSeekers" selected="${tab eq 'helpSeekers'}" />
        </aui:nav>
        <aui:nav-bar-search>
            <aui:form action="${searchURL}" name="searchFm">
                <liferay-ui:input-search markupView="lexicon" />
            </aui:form>
        </aui:nav-bar-search>
    </aui:nav-bar>

    <!-- Corps de la page et selection du listing a afficher -->
    <c:choose>
        <c:when test="${tab eq 'helpProposals'}">
            <liferay-util:include page="/help-bo-view-help-proposals.jsp" servletContext="<%=application %>">
            </liferay-util:include>
        </c:when>
        <c:when test="${tab eq 'helpSeekers'}">
            <liferay-util:include page="/help-bo-view-help-seekers.jsp" servletContext="<%=application %>">
            </liferay-util:include>
        </c:when>
    </c:choose>

</div>