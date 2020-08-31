<%@ include file="/ejob-bo-init.jsp"%>

<aui:input name="email${param.index}" label="" type="text"
    value="${param.email}" >
  <aui:validator name="email"/>
</aui:input>