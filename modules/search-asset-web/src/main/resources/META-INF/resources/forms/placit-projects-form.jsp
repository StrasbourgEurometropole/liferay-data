<%@ include file="/search-asset-init.jsp"%>

<div class="pro-group">
	<div class="pro-header">
		<h4>
			<liferay-ui:message key="eu.projet-status" />
		</h4>
		<span class="pro-remove"><liferay-ui:message
				key="eu.projet-erase" /></span>
	</div>
	<fieldset>
		<legend aria-hidden="true" class="hide">Choix par nom de
			projet</legend>
		<div>
			<select class="" id="statut-projet"
				name="<portlet:namespace />vocabulary_0">
				<option><liferay-ui:message key="eu.projet-choose-status" /></option>
				<c:set var="groupID" value="${themeDisplay.scopeGroupId}" />
				<c:set var="projectStatusVocabulary"
					value="${vocabularyAccessor.getProjectStatus(groupID)}" />
				<c:forEach
					items="${dc.getDropdownRootCategories(projectStatusVocabulary)}"
					var="category">
					<c:set var="category" value="${category}" scope="request" />
					<c:set var="dc" value="${dc}" scope="request" />
					<c:set var="level" value="0" scope="request" />
					<jsp:include page="/forms/category-option.jsp" />
				</c:forEach>
			</select>
		</div>
	</fieldset>
</div>
<br />
<button type="submit">
	<span><liferay-ui:message key="search" /> </span>
</button>
<aui:input type="hidden" name="vocabulariesCount" value="1" />
