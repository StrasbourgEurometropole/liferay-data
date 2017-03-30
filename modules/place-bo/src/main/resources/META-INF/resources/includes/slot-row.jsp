<%@ include file="/place-bo-init.jsp"%>	
					
<div class="slot-content">
	<label><liferay-ui:message key="slot" /> ${param.indexSlot + 1}</label><br/>		
	<aui:input type="text" value="${param.startHour}" name="startHour${param.indexPeriod}-${param.jour}-${param.indexSlot}" label="start-hour" >
		<aui:validator errorMessage="hour-required" 
		name="custom">
	        function(val, fieldNode, ruleValue) {
		        var regex = new RegExp(/([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/i);
		        return regex.test(val);
	        }
		</aui:validator>
	</aui:input>
	<div class="place-slot-start-hour" style="display: none" id="slotStartHour${param.indexPeriod}-${param.jour}-${param.indexSlot}" >
		<liferay-ui:message key="this-field-is-required" />
	</div>
					
	<aui:input type="text" value="${param.endHour}" name="endHour${param.indexPeriod}-${param.jour}-${param.indexSlot}" label="end-hour" >
		<aui:validator errorMessage="hour-required" 
		name="custom">
	        function(val, fieldNode, ruleValue) {
		        var regex = new RegExp(/([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/i);
		        return regex.test(val);
	        }
		</aui:validator>
	</aui:input>
	<div class="place-slot-end-hour" style="display: none" id="slotEndHour${param.indexPeriod}-${param.jour}-${param.indexSlot}" >
		<liferay-ui:message key="this-field-is-required" />
	</div>
</div> 
