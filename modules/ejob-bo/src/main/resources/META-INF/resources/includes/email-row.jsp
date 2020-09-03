<%@ include file="/ejob-bo-init.jsp"%>

<aui:input name="email${param.index}" label="" type="text"
    value="${param.email}" >
  <aui:validator name="email"/>
</aui:input>

<c:if test="${not empty fromAjax}">
	<aui:script>
		$('#email-fields').trigger('emailCreated', ${param.index});
	</aui:script>
</c:if>