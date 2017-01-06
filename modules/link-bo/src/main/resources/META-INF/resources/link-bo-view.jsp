<%@ include file="/link-bo-init.jsp"%>

<c:set var="tab"
	value="${not empty param.tab ? param.tab : 'links' }" />

<liferay-portlet:renderURL var="linksURL">
	<portlet:param name="tab" value="links" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="cmd" value="search" />
	<portlet:param name="tab" value="${param.tab}" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
</liferay-portlet:renderURL>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item href="${linksURL}" label="links"
			selected="${tab eq 'links'}" />
	</aui:nav>

	<aui:nav-bar-search>
		<aui:form action="${searchURL}" name="searchFm">
			<liferay-ui:input-search markupView="lexicon" />
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>

<c:choose>
    <c:when test="${tab eq 'links'}">
		<liferay-util:include page="/link-bo-view-links.jsp" servletContext="<%=application %>">
		</liferay-util:include>
    </c:when>
</c:choose>


