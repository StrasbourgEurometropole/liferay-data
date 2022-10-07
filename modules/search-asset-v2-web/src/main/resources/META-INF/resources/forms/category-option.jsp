<%@ include file="/search-asset-init.jsp"%>
<c:set var="prefix" value="" />
<c:forEach var="i" begin="1" end="${level}">
	<c:set var="prefix" value="${prefix} - " />
</c:forEach>
<aui:option value="${category.categoryId}"
	label="${prefix} ${category.getTitle(locale)}"
	selected="${fn:contains(dc.filterCategoriesIdsString, category.categoryId)}" />
<c:set var="level" value="${level + 1}" scope="request"/>
<c:forEach items="${vocabularyHelper.getChild(category.categoryId)}" var="children">
	<c:set var="category" value="${children}" scope="request"/>
	<c:set var="dc" value="${dc}" scope="request"/>
	<jsp:include page="/forms/category-option.jsp"/>
</c:forEach>
<c:set var="level" value="${level - 1}" scope="request"/>