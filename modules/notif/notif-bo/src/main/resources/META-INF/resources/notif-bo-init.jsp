<%-- pour c:set, c:choose, c:when, c:if, c:forEach... --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- pour fn:length, fn:contains... --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%-- pour fmt:formatDate... --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%-- pour liferay-portlet:renderURL, liferay-portlet:actionURL... --%>
<%@taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%-- pour portlet:param --%>
<%@ taglib uri="http://xmlns.jcp.org/portlet_3_0" prefix="portlet" %>
<portlet:defineObjects />

<%-- pour aui:nav-bar, aui:nav, aui:nav-item, aui:nav-bar-search, aui:form, aui:input, aui:model-context,
 aui:fieldset-group, aui:fieldset, aui:select, aui:option, aui:button-row, aui:button, aui:script... --%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%-- pour liferay-ui:input-search, liferay-ui:error, liferay-ui:search-container, liferay-ui:search-container-results,
liferay-ui:search-container-row, liferay-ui:search-container-column-text, liferay-ui:icon-menu,
liferay-ui:search-iterator, liferay-ui:message... --%>
<%@taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%-- pour liferay-util:include, liferay-util:param, liferay-util:html-top, liferay-util:html-bottom... --%>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%-- pour liferay-frontend:add-menu... --%>
<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>

<%-- pour strasbourg-picker:image --%>
<%@ taglib uri="http://strasbourg.eu/tld/picker" prefix="strasbourg-picker"%>

<%-- pour windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" de l'actionURL --%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>