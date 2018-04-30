<%@ include file="/project-bo-init.jsp"%>

<div class="timeline-label"><label><liferay-ui:message key="enter-a-timeline" /></label></div>

<aui:input type="text" value="${param.startDay}" name="startDay${param.index}" label="J + XX" inlineField="true" />

<aui:input type="text" value="${param.date}" name="date${param.index}" label="Date" inlineField="true" />

<aui:input type="text" value="${param.title}" name="title${param.index}" label="Titre" inlineField="true" />

