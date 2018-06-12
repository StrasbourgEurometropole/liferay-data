<%@ include file="/place-bo-init.jsp"%>	
					
<div class="slot-content" id="${param.indexPeriod}-${param.jour}-${param.indexSlot}">
	<label><liferay-ui:message key="slot" /> ${param.indexSlot + 1}</label><br/>
	<aui:input name="numSlot${param.indexSlot}" value="${param.indexSlot}" type="hidden" />		
	<aui:input type="time" value="${param.startHour}" name="startHour${param.indexPeriod}-${param.jour}-${param.indexSlot}" label="slot-start-hour" placeholder="HH:MM" >
		<aui:validator errorMessage="hour-format" 
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
	
	<div class="place-slot-incorrect-hour" style="display: none" id="slotIncorrectHour${param.indexPeriod}-${param.jour}-${param.indexSlot}" >
		<liferay-ui:message key="incorrect-hour" />
	</div>
					
	<aui:input type="time" value="${param.endHour}" name="endHour${param.indexPeriod}-${param.jour}-${param.indexSlot}" label="slot-end-hour" placeholder="HH:MM" >
		<aui:validator errorMessage="hour-format" 
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
	
	<aui:input name="comment${param.indexPeriod}-${param.jour}-${param.indexSlot}" label="comment" value="${param.comment}" localized="true" type="text"  />

	<aui:button cssClass="btn-icon icon icon-trash icon-2x" type="button" onClick="deleteSlot(${param.indexPeriod}, ${param.jour}, ${param.indexSlot}); return false;" />
</div> 
