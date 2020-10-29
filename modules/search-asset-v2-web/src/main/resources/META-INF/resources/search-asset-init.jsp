<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://xmlns.jcp.org/portlet_3_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/asset" prefix="liferay-asset" %>
<%@ taglib uri="http://liferay.com/tld/ddm" prefix="liferay-ddm" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Objects" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Calendar" %>

<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<liferay-portlet:renderURL varImpl="assetTypeRowURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
    <portlet:param name="mvcPath" value="/includes/asset-type-row.jsp" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL varImpl="prefilterRowURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
    <portlet:param name="mvcPath" value="/includes/prefilter-row.jsp" />
</liferay-portlet:renderURL>

<jsp:useBean id="vocabularyHelper" class="eu.strasbourg.utils.AssetVocabularyHelper" />
<jsp:useBean id="vocabularyAccessor" class="eu.strasbourg.utils.AssetVocabularyAccessor" />
