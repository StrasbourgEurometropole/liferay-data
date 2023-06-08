<%@ include file="/search-association-init.jsp"%>
<c:set var="prefix" value="" />
<c:forEach var="i" begin="1" end="${level}">
	<c:set var="prefix" value="${prefix} - " />
</c:forEach>
<aui:option value="${category.categoryId}">${prefix} ${category.getTitle(locale)}</aui:option>
<c:set var="level" value="${level + 1}" scope="request"/>
<c:forEach items="${vocabularyHelper.getChild(category.categoryId)}" var="children">
	<c:set var="category" value="${children}" scope="request"/>
	<jsp:include page="/category-option.jsp"/>
</c:forEach>
<c:set var="level" value="${level - 1}" scope="request"/>