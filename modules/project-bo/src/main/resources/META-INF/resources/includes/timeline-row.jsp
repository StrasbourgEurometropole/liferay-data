<%@ include file="/project-bo-init.jsp"%>

<aui:input type="number" value="${param.startDay}" name="startDay${param.index}" label="J + XX" inlineField="true" readonly="true"/>
		
<aui:input type="date" value="${param.date}" name="date${param.index}" label="Date" inlineField="true" />
		
<aui:input type="text" value="${param.title}" name="title${param.index}" label="Titre" inlineField="true" />

<aui:input type="text" value="${param.link}" name="link${param.index}" label = "Lien" inlineField="true" helpMessage="urlHelp"/>