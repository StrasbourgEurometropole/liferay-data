<%@ include file="/search-asset-init.jsp"%>

<label> 
	<liferay-ui:message key="select-vocabularies" />
</label>

<aui:input type="hidden" name="vocabulariesCount"
	value="${fn:length(vocabularies)}" />

<c:forEach var="vocabulary" items="${vocabularies}"
	varStatus="vocStatus">
	<aui:input type="checkbox" name="vocabularyId_${vocStatus.index}"
		label="${vocabulary.name}" value="${vocabulary.vocabularyId}"
		checked="${fn:contains(vocabulariesIds, vocabulary.vocabularyId)}" />
</c:forEach>