<%@ include file="/notif-bo-init.jsp" %>

<c:set var="tab" value="${not empty param.tab ? param.tab : 'notifications' }" />
<c:if test="${isAdminNotification}">
    <c:set var="tab" value="${not empty param.tab ? param.tab : 'services' }" />
</c:if>

<liferay-portlet:renderURL var="servicesURL">
	<portlet:param name="tab" value="services" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="notificationsURL">
	<portlet:param name="tab" value="notifications" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="cmd" value="search" />
	<portlet:param name="tab" value="${param.tab}" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
</liferay-portlet:renderURL>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
    <c:if test="${isAdminNotification}">
        <aui:nav cssClass="navbar-nav">
            <aui:nav-item href="${servicesURL}" label="eu.strasbourg.notif.services"
                selected="${tab eq 'services'}" />
        </aui:nav>
    </c:if>
    <aui:nav cssClass="navbar-nav">
        <aui:nav-item href="${notificationsURL}" label="eu.strasbourg.notif.notifications"
            selected="${tab eq 'notifications'}" />
    </aui:nav>

	<aui:nav-bar-search>
		<aui:form action="${searchURL}" name="searchFm">
			<liferay-ui:input-search markupView="lexicon" />
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>
<c:choose>
    <c:when test="${tab eq 'services'}">
        <liferay-util:include page="/notif-bo-view-services.jsp" servletContext="<%=application %>">
        </liferay-util:include>
    </c:when>
    <c:when test="${tab eq 'notifications'}">
		<liferay-util:include page="/notif-bo-view-notifications.jsp" servletContext="<%=application %>">
		</liferay-util:include>
    </c:when>
</c:choose>


