<%@ include file="/project-bo-init.jsp"%>

<aui:input type="number" value="${param.startDay}" name="startDay${param.index}" label="J + XX" inlineField="true" readonly="true"/>

<aui:input type="date" value="${param.date}" name="date${param.index}" label="Date" inlineField="true" />

<aui:select type="text" value="${param.dateFormat}" name="dateFormat${param.index}" label = "date-format" inlineField="true" >
	<aui:option value="JJ/MM/AAAA" selected="<% param.dateFormat.equals('JJ/MM/AAAA') ? 'true' : 'false' %>" >JJ/MM/AAAA</aui:option>
    <aui:option value="MM/AAAA" selected="<% param.dateFormat.equals('MM/AAAA') ? 'true' : 'false' %>" >MM/AAAA</aui:option>
    <aui:option value="AAAA" selected="<% param.dateFormat.equals('AAAA') ? 'true' : 'false' %>" >AAAA</aui:option>
</aui:select>

<aui:input type="text" value="${param.title}" name="title${param.index}" label="Titre" inlineField="true" />

<aui:input type="text" value="${param.link}" name="link${param.index}" label = "Lien" inlineField="true" helpMessage="urlHelp"/>

<aui:input type="number" value="${param.spacing}" name="spacing${param.index}" label="Espacement" inlineField="true" helpMessage="spacing"/>
