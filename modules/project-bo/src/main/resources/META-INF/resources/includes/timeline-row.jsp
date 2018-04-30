<%@ include file="/project-bo-init.jsp"%>

<div id="timeline${param.indexTimeline}">	

	<aui:input type="text" value="${param.startDay}" name="startDay${param.indexTimeline}" label="J + XX" inlineField="true" />
		
	<aui:input type="text" value="${param.date}" name="date${param.indexTimeline}" label="Date" inlineField="true" />
		
	<aui:input type="text" value="${param.title}" name="title${param.indexTimeline}" label="Titre" inlineField="true" />
	
	<br />
	
	<aui:button cssClass="btn-icon icon icon-trash icon-2x" type="button" onClick="deleteTimeline(${param.indexTimeline}); return false;" />
	
</div>