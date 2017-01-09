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

<liferay-portlet:resourceURL var="placeAutocompleteURL">
</liferay-portlet:resourceURL>
<liferay-portlet:actionURL name="getPeriodRow" var="periodRowURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<liferay-portlet:param name="mvcPath" value="/includes/period-row.jsp" />
</liferay-portlet:actionURL>
<liferay-util:html-top>
	<script>
		var getPeriodRowJSPURL = '${periodRowURL}';
		var placeAutocompleteURL = '${placeAutocompleteURL}';
	</script>
</liferay-util:html-top>
<liferay-util:html-bottom>
	<script>
		define._amd = define.amd;
		define.amd = false;
	</script>
	<script src="/o/agendabo/js/vendors/moment.min.js"
		type="text/javascript"></script>
	<script
		src="/o/agendabo/js/vendors/daterangepicker.js"
		type="text/javascript"></script>
	<script>
		define.amd = define._amd;
	</script>
	<script
		src="/o/agendabo/js/agenda-bo-main.js"
		type="text/javascript"></script>
</liferay-util:html-bottom>
