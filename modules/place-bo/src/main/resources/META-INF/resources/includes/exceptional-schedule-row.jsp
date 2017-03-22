<%@ include file="/place-bo-init.jsp"%>
						
<div class="schedule-label"><label><liferay-ui:message key="date-exception" /> ${param.index}</label></div>
<div class="heure${param.index}" <c:if test="${not empty param and param.closed }">style="display: none;"</c:if>>
	<aui:input type="text" value="${param.startHour}" helpMessage="hour-help" name="startHour${param.index}" label="start-hour" >
		<aui:validator errorMessage="hour-required" 
		name="custom">
	        function(val, fieldNode, ruleValue) {
		        var regex = new RegExp(/([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/i);
		        return regex.test(val);
	        }
		</aui:validator>
		<aui:validator name="required"
			errorMessage="this-field-is-required" />
	</aui:input>
	<aui:input type="text" value="${param.endHour}" helpMessage="hour-help" name="endHour${param.index}" label="end-hour" >
		<aui:validator errorMessage="hour-required" 
		name="custom">
	        function(val, fieldNode, ruleValue) {
		        var regex = new RegExp(/([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/i);
		        return regex.test(val);
	        }
		</aui:validator>
		<aui:validator name="required"
			errorMessage="this-field-is-required" />
	</aui:input>
</div>
			
<aui:input name="scheduleExceptionDescription${param.index}" label="description" value="${param.comment}"  >
		<aui:validator name="required"
			errorMessage="this-field-is-required" />
</aui:input>

<aui:input type="date" name="dateScheduleException${param.index}" label="date" value="${param.date}" >
	<aui:validator name="required"
		errorMessage="this-field-is-required" />
</aui:input>
		

<aui:input name="closed${param.index}" label="closed" type="toggle-switch" 
	value="${not empty param ? param.closed : false}" onClick="affichageHeures(this, ${param.index})" />

