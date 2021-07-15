<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
<jsp:useBean id="vocabularyHelper" class="eu.strasbourg.utils.AssetVocabularyHelper" />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<c:set var="prefix" value="" />
<c:forEach var="i" begin="1" end="${level}">
	<c:set var="prefix" value="${prefix} - " />
</c:forEach>
<c:if test="${prefix != '' || showParents == '1'}">
    <aui:option value="${category.categoryId}"
        label="${prefix} ${category.getTitle(locale)}" />
</c:if>
<c:set var="level" value="${level + 1}" scope="request"/>
<c:forEach items="${vocabularyHelper.getChild(category.categoryId)}" var="children">
	<c:set var="category" value="${children}" scope="request"/>
	<jsp:include page="/include/category-option.jsp"/>
</c:forEach>
<c:set var="level" value="${level - 1}" scope="request"/>