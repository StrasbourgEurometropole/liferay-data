<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>

<%@ taglib uri="http://strasbourg.eu/tld/picker" prefix="strasbourg-picker" %>

<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<liferay-util:html-bottom>
	<script src="/o/projectbo/js/project-bo-main.js" type="text/javascript"></script>
</liferay-util:html-bottom>

<liferay-portlet:actionURL name="getTimelineRow" var="timelineRowURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<liferay-portlet:param name="mvcPath" value="/includes/timeline-row.jsp" />
</liferay-portlet:actionURL>

