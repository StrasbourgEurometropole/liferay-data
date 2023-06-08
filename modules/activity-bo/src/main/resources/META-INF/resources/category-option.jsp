<%@ include file="/activity-bo-init.jsp"%>
<c:set var="prefix" value="" />
<c:forEach var="i" begin="1" end="${level}">
	<c:set var="prefix" value="${prefix} - " />
</c:forEach>
<option value="${category.categoryId}"
	<c:if test="${categoryId eq  category.categoryId}">
		selected
	</c:if>
>
	${prefix} ${category.getTitle(locale)}
</option>
<c:set var="level" value="${level + 1}" scope="request"/>
<c:forEach items="${vocabularyHelper.getChild(category.categoryId)}" var="children">
	<c:set var="category" value="${children}" scope="request"/>
	<c:set var="dc" value="${dc}" scope="request"/>
	<jsp:include page="/category-option.jsp"/>
</c:forEach>
<c:set var="level" value="${level - 1}" scope="request"/>