<%@ include file="/search-asset-init.jsp"%>

<c:set var="groupID" value="${themeDisplay.scopeGroupId}" />

<div class="pro-sort pro-dropdown">
     <a href="#" title="Voir tous les quartiers"><liferay-ui:message key="all-districts" /></a>
     <ul>
		<c:set var="districtVocabulary" value="${vocabularyAccessor.getTerritories()}" />
		<c:forEach
				items="${dc.getDropdownRootCategories(districtVocabulary)}"
				var="category">
				<c:set var="category" value="${category}" scope="request" />
				<c:set var="dc" value="${dc}" scope="request" />
				<c:set var="level" value="0" scope="request" />
			<li><jsp:include page="/forms/category-option.jsp" /></li>
		</c:forEach>
     </ul>
 </div>
 <div class="pro-sort pro-dropdown">
     <a href="#" title="Voir toutes les thematiques"><liferay-ui:message key="all-thematics" /></a>
     <ul>
		<c:set var="thematicVocabulary" value="${vocabularyAccessor.getThematics(groupID)}" />
		<c:forEach
				items="${dc.getDropdownRootCategories(thematicVocabulary)}"
				var="category">
				<c:set var="category" value="${category}" scope="request" />
				<c:set var="dc" value="${dc}" scope="request" />
				<c:set var="level" value="0" scope="request" />
			<li><jsp:include page="/forms/category-option.jsp" /></li>
		</c:forEach>
     </ul>
 </div>