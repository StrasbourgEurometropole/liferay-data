<%@ include file="/notif-bo-init.jsp"%>

<aui:input name="name{param.index}" label="" type="text"
    value="${nature.name}" >
  <aui:validator name="nature"/>
</aui:input>

<c:if test="${not empty fromAjaxNature}">
	<aui:script>
		$('#nature-fields').trigger('natureCreated', ${param.index});
	</aui:script>
</c:if>