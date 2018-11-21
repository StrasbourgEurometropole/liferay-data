<%@ include file="/search-asset-init.jsp"%>

<c:set var="groupID" value="${themeDisplay.scopeGroupId}" />

<div class="pro-sort pro-dropdown">
    <select class="" id="district" name="<portlet:namespace />vocabulary_1">
    	<option><liferay-ui:message key="all-districts" /></option>
		<c:set var="districtVocabulary" value="${vocabularyAccessor.getTerritories()}" />
		<c:forEach
			items="${dc.getDropdownRootCategories(districtVocabulary)}"
			var="category">
			<c:set var="category" value="${category}" scope="request" />
			<c:set var="dc" value="${dc}" scope="request" />
			<c:set var="level" value="0" scope="request" />
			<jsp:include page="/forms/category-option.jsp" />
		</c:forEach>
	</select>
 </div>
 
 <div class="pro-sort pro-dropdown">
    <select class="" id="thematic" name="<portlet:namespace />vocabulary_2">
    	<option><liferay-ui:message key="all-thematics" /></option>
		<c:set var="thematicVocabulary" value="${vocabularyAccessor.getThematics(groupID)}" />
		<c:forEach
			items="${dc.getDropdownRootCategories(thematicVocabulary)}"
			var="category">
			<c:set var="category" value="${category}" scope="request" />
			<c:set var="dc" value="${dc}" scope="request" />
			<c:set var="level" value="0" scope="request" />
			<jsp:include page="/forms/category-option.jsp" />
		</c:forEach>
	</select>
 </div>