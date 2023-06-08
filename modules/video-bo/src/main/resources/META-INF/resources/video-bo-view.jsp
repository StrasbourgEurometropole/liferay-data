<%@ include file="/video-bo-init.jsp"%>

<c:set var="tab"
	value="${not empty param.tab ? param.tab : 'videos' }" />

<liferay-portlet:renderURL var="galleriesURL">
	<portlet:param name="tab" value="galleries" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="videosURL">
	<portlet:param name="tab" value="videos" />
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
		<aui:nav-item href="${videosURL}" label="videos"
			selected="${tab eq 'videos'}" />

		<aui:nav-item href="${galleriesURL}" label="galleries"
			selected="${tab eq 'galleries'}" />
	</aui:nav>

	<aui:nav-bar-search>
		<aui:form action="${searchURL}" name="searchFm">
			<liferay-ui:input-search markupView="lexicon" />
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>

<c:choose>
    <c:when test="${tab eq 'videos'}">
		<liferay-util:include page="/video-bo-view-videos.jsp" servletContext="<%=application %>">
		</liferay-util:include>
    </c:when>
    <c:when test="${tab eq 'galleries'}">
		<liferay-util:include page="/video-bo-view-galleries.jsp" servletContext="<%=application %>">
		</liferay-util:include>
    </c:when>
</c:choose>


