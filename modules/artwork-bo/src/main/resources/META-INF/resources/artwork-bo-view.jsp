<%@ include file="/artwork-bo-init.jsp"%>

<c:set var="tab"
	value="${not empty param.tab ? param.tab : 'artworks' }" />

<liferay-portlet:renderURL var="artworksURL">
	<portlet:param name="tab" value="artworks" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="collectionsURL">
	<portlet:param name="tab" value="collections" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="cmd" value="search" />
	<portlet:param name="tab" value="${param.tab}" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
</liferay-portlet:renderURL>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item href="${artworksURL}" label="artworks"
			selected="${tab eq 'artworks'}" />

		<aui:nav-item href="${collectionsURL}" label="collections"
			selected="${tab eq 'collections'}" />
	</aui:nav>

	<aui:nav-bar-search>
		<aui:form action="${searchURL}" name="searchFm">
			<liferay-ui:input-search markupView="lexicon" />
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>

<c:choose>
    <c:when test="${tab eq 'artworks'}">
		<liferay-util:include page="/artwork-bo-view-artworks.jsp" servletContext="<%=application %>">
		</liferay-util:include>
    </c:when>
    <c:when test="${tab eq 'collections'}">
		<liferay-util:include page="/artwork-bo-view-collections.jsp" servletContext="<%=application %>">
		</liferay-util:include>
    </c:when>
</c:choose>


