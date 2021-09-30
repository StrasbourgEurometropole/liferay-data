<%@ include file="/notif-bo-init.jsp"%>

<aui:input name="content{param.index}" label="" type="text"
    value="${message.content}" >
  <aui:validator name="message"/>
</aui:input>

<c:if test="${not empty fromAjaxMessage}">
	<aui:script>
		$('#message-fields').trigger('messageCreated', ${param.index});
	</aui:script>
</c:if>