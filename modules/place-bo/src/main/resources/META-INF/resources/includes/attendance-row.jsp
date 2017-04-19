<%@ include file="/place-bo-init.jsp"%>	

<div class="lfr-form-row lfr-form-row-inline" id="attendance${param.indexPeriod}">
	<div class="row-fields">
		<div class="attendance-label" id="${param.indexPeriod}" onCLick="$('.attendance-content' + ${param.indexPeriod}).toggle();">
			<label><liferay-ui:message key="period" /> ${param.indexPeriod + 1} : ${param.name}</label>
		</div>
		<div class="attendance-content${param.indexPeriod}" >
			<aui:input type="text" name="RTGreenThreshold${param.count}" label="rtgreen-threshold" value="${param.green}" />
			<aui:input type="text" name="RTOrangeThreshold${param.count}" label="rtorange-threshold" value="${param.orange}" />
			<aui:input type="text" name="RTRedThreshold${param.count}" label="rtred-threshold" value="${param.red}" />
			<aui:input type="text" name="RTMaxThreshold${param.count}" label="rtmax-threshold" value="${param.max}" />
		</div>
	</div>
</div> 
