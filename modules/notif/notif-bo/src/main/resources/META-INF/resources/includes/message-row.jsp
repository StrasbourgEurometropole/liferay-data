<%@ include file="/notif-bo-init.jsp"%>

<aui:input name="content${param.index}" label="eu.strasbourg.notif.message.content" value="${message.content}" required="true" localized="true" type="textarea" />
<div class="form-validator-stack help-block" style="display: none">
	<liferay-ui:message key="this-field-is-required" />
</div>

<c:if test="${not empty fromAjaxMessage}">
	<aui:script>
		$('#message-fields').trigger('messageCreated', ${param.index});
	</aui:script>
</c:if>