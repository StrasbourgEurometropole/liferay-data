<%@ include file="/place-bo-init.jsp"%>

<c:set var="tab"
	value="${not empty param.tab ? param.tab : 'places' }" />

<liferay-portlet:renderURL var="placesURL">
	<portlet:param name="tab" value="places" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="pricesURL">
	<portlet:param name="tab" value="prices" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="publicHolidaysURL">
	<portlet:param name="tab" value="publicHolidays" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="subPlacesURL">
	<portlet:param name="tab" value="subPlaces" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="importURL">
	<portlet:param name="tab" value="import" />
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
		<aui:nav-item href="${placesURL}" label="places"
			selected="${tab eq 'places'}" />
	</aui:nav>
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item href="${pricesURL}" label="prices"
			selected="${tab eq 'prices'}" />
	</aui:nav>
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item href="${publicHolidaysURL}" label="public-holidays"
			selected="${tab eq 'publicHolidays'}" />
	</aui:nav>
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item href="${subPlacesURL}" label="sub-places"
			selected="${tab eq 'subPlaces'}" />
	</aui:nav>
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item href="${importURL}" label="place-import"
			selected="${tab eq 'import'}" />
	</aui:nav>

	<c:if test="${tab eq 'places'}">
		<aui:nav-bar-search>
			<aui:form action="${searchURL}" name="searchFm">
				<liferay-ui:input-search markupView="lexicon" />
			</aui:form>
		</aui:nav-bar-search>
	</c:if>
</aui:nav-bar>

<c:choose>
    <c:when test="${tab eq 'places'}">
		<liferay-util:include page="/place-bo-view-places.jsp" servletContext="<%=application %>">
		</liferay-util:include>
    </c:when>
    <c:when test="${tab eq 'prices'}">
		<liferay-util:include page="/place-bo-view-prices.jsp" servletContext="<%=application %>">
		</liferay-util:include>
    </c:when>
    <c:when test="${tab eq 'publicHolidays'}">
		<liferay-util:include page="/place-bo-view-public-holidays.jsp" servletContext="<%=application %>">
		</liferay-util:include>
    </c:when>
    <c:when test="${tab eq 'subPlaces'}">
		<liferay-util:include page="/place-bo-view-subplaces.jsp" servletContext="<%=application %>">
		</liferay-util:include>
    </c:when>
    <c:when test="${tab eq 'import'}">
		<liferay-util:include page="/place-bo-view-import.jsp" servletContext="<%=application %>">
		</liferay-util:include>
    </c:when>
</c:choose>
