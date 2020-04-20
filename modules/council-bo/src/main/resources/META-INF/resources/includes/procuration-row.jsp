<%@ include file="/council-bo-init.jsp"%>

<aui:input type="text" value="${param.officialVoters}" name="officialVoters${param.index}"
    label="official-voters" inlineField="true" />

<aui:input type="text" value="${param.officialUnavailable}" name="officialUnavailable${param.index}"
    label="official-unavailable" inlineField="true" />

<c:if test="${not empty fromAjax}">
	<aui:script>
		$('#procuration-fields').trigger('procurationCreated', ${param.index});
	</aui:script>
</c:if>