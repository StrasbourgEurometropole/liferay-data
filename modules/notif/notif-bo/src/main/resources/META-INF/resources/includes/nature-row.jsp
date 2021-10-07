<%@ include file="/notif-bo-init.jsp"%>

<aui:input name="natureName${param.index}" label="eu.strasbourg.notif.nature.name" value="${nature.name}" required="true" localized="true" type="text" />
<div class="form-validator-stack help-block" style="display: none">
	<liferay-ui:message key="this-field-is-required" />
</div>

<c:if test="${not empty fromAjaxNature}">
	<aui:script>
		$('#nature-fields').trigger('natureCreated', ${param.index});
	</aui:script>
</c:if>