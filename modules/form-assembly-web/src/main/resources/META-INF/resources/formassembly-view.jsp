<%@ include file="/formassembly-init.jsp" %>

<div class="no-form-error">
	<liferay-ui:error key="no-form" message="no-form" />
</div>

${formHtml}

<style>
.formassembly-portlet label{
    color: black !important;
    text-align: left;
}
.formassembly-portlet .field-hint {
    float: none;
    font-size: 14px;
    width: auto;
    line-height: inherit;
}
</style>