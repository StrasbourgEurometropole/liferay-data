<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<jsp:useBean id="strasbourgPropsUtil" class="eu.strasbourg.utils.StrasbourgPropsUtil"/>

<liferay-util:body-top>
    <aui:script>
        window.userAddress = '${fn:escapeXml(address)}';
        window.groupId = ${groupId};
        window.newTab = ${openInNewTab};
        window.typesContenu = "${typesContenu}";
        window.categoriesCheckedIds = "${fn:replace(categoriesCheckedIds, '"', '')}";
        window.prefilterCategoriesIds = "${fn:replace(prefilterCategoriesIds,'"','')}";
        window.interestsCheckedIds = "${fn:replace(interestsCheckedIds, '"', '')}";
        window.showFavoritesByDefault = ${showFavorites};
        window.isWidgetMode = ${widgetMod};
        window.aroundMePortletNamespace = '<portlet:namespace />';
        window.publikProfileURL = '${strasbourgPropsUtil.getPublikProfileURL()}';
        window.publikInternalId = '${internalId}';
    </aui:script>
</liferay-util:body-top>
