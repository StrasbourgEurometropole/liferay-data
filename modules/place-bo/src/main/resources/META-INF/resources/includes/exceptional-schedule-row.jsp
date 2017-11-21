<%@ include file="/place-bo-init.jsp"%>
						
<div class="schedule-label" id="${param.index}"><label><liferay-ui:message key="date-exception" /> ${param.index}</label></div>
			
<aui:input name="scheduleExceptionDescription${param.index}" label="description-schedule" value="${param.comment}" localized="true" type="text"  /> 
<div class="place-schedule-description" style="display: none">
	<liferay-ui:message key="this-field-is-required" />
</div>

<aui:input type="date" name="startDateScheduleException${param.index}" label="start-date-schedule" value="${param.startDate}" />
<div class="place-schedule-start-date" style="display: none">
	<liferay-ui:message key="this-field-is-required" />
</div>

<div class="place-schedule-incorrect-date" style="display: none">
	<liferay-ui:message key="incorrect-date" />
</div>

<aui:input type="date" name="endDateScheduleException${param.index}" label="end-date-schedule" value="${param.endDate}" />
<div class="place-schedule-end-date" style="display: none">
	<liferay-ui:message key="this-field-is-required" />
</div>
		

<aui:input name="closed${param.index}" label="closed" type="toggle-switch" 
	value="${not empty param ? param.closed : false}" onClick="affichageHeures(this, ${param.index})" />
	
<div class="heure${param.index}" <c:if test="${not empty param and param.closed }">style="display: none;"</c:if>>
	<div class="row">
		<div class="col-md-6">
			<aui:input type="time" value="${param.startHour1}" name="startHour1_${param.index}" label="start-hour" placeholder="HH:MM" >
				<aui:validator errorMessage="hour-format" 
				name="custom">
			        function(val, fieldNode, ruleValue) {
				        var regex = new RegExp(/([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/i);
				        return regex.test(val);
			        }
				</aui:validator>
			</aui:input>
			<div class="place-schedule-start-hour" style="display: none">
				<liferay-ui:message key="this-field-is-required" />
			</div>
			
			<div class="place-schedule-incorrect-hour" style="display: none">
				<liferay-ui:message key="incorrect-hour" />
			</div>
		</div>
		<div class="col-md-6">
			<aui:input type="time" value="${param.endHour1}" name="endHour1_${param.index}" label="end-hour" placeholder="HH:MM" >
				<aui:validator errorMessage="hour-format" 
				name="custom">
			        function(val, fieldNode, ruleValue) {
				        var regex = new RegExp(/([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/i);
				        return regex.test(val);
			        }
				</aui:validator>
			</aui:input>
			<div class="place-schedule-end-hour" style="display: none">
				<liferay-ui:message key="this-field-is-required" />
			</div>
		</div>	
	</div>
</div>


<div class="heure${param.index}" <c:if test="${not empty param and param.closed }">style="display: none;"</c:if>>
	<div class="row">
		<div class="col-md-6">
			<aui:input type="time" value="${param.startHour2}" name="startHour2_${param.index}" label="start-hour" placeholder="HH:MM" >
				<aui:validator errorMessage="hour-format" 
				name="custom">
			        function(val, fieldNode, ruleValue) {
				        var regex = new RegExp(/([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/i);
				        return regex.test(val);
			        }
				</aui:validator>
			</aui:input>
			<div class="place-schedule-start-hour" style="display: none">
				<liferay-ui:message key="this-field-is-required" />
			</div>
			
			<div class="place-schedule-incorrect-hour" style="display: none">
				<liferay-ui:message key="incorrect-hour" />
			</div>
		</div>
		<div class="col-md-6">
			<aui:input type="time" value="${param.endHour2}" name="endHour2_${param.index}" label="end-hour" placeholder="HH:MM" >
				<aui:validator errorMessage="hour-format" 
				name="custom">
			        function(val, fieldNode, ruleValue) {
				        var regex = new RegExp(/([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/i);
				        return regex.test(val);
			        }
				</aui:validator>
			</aui:input>
		</div>
	</div>
</div>

<div class="heure${param.index}" <c:if test="${not empty param and param.closed }">style="display: none;"</c:if>>
	<div class="row">
		<div class="col-md-6">
			<aui:input type="time" value="${param.startHour3}" name="startHour3_${param.index}" label="start-hour" placeholder="HH:MM" >
				<aui:validator errorMessage="hour-format" 
				name="custom">
			        function(val, fieldNode, ruleValue) {
				        var regex = new RegExp(/([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/i);
				        return regex.test(val);
			        }
				</aui:validator>
			</aui:input>
			<div class="place-schedule-start-hour" style="display: none">
				<liferay-ui:message key="this-field-is-required" />
			</div>
			
			<div class="place-schedule-incorrect-hour" style="display: none">
				<liferay-ui:message key="incorrect-hour" />
			</div>
		</div>
		<div class="col-md-6">
			<aui:input type="time" value="${param.endHour3}" name="endHour3_${param.index}" label="end-hour" placeholder="HH:MM" >
				<aui:validator errorMessage="hour-format" 
				name="custom">
			        function(val, fieldNode, ruleValue) {
				        var regex = new RegExp(/([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/i);
				        return regex.test(val);
			        }
				</aui:validator>
			</aui:input>
		</div>
	</div>
</div>

<div class="heure${param.index}" <c:if test="${not empty param and param.closed }">style="display: none;"</c:if>>
	<div class="row">
		<div class="col-md-6">
			<aui:input type="time" value="${param.startHour4}" name="startHour4_${param.index}" label="start-hour" placeholder="HH:MM" >
				<aui:validator errorMessage="hour-format" 
				name="custom">
			        function(val, fieldNode, ruleValue) {
				        var regex = new RegExp(/([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/i);
				        return regex.test(val);
			        }
				</aui:validator>
			</aui:input>
			<div class="place-schedule-start-hour" style="display: none">
				<liferay-ui:message key="this-field-is-required" />
			</div>
			
			<div class="place-schedule-incorrect-hour" style="display: none">
				<liferay-ui:message key="incorrect-hour" />
			</div>
		</div>
		<div class="col-md-6">
			<aui:input type="time" value="${param.endHour4}" name="endHour4_${param.index}" label="end-hour" placeholder="HH:MM" >
				<aui:validator errorMessage="hour-format" 
				name="custom">
			        function(val, fieldNode, ruleValue) {
				        var regex = new RegExp(/([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/i);
				        return regex.test(val);
			        }
				</aui:validator>
			</aui:input>
		</div>
	</div>
</div>

<div class="heure${param.index}" <c:if test="${not empty param and param.closed }">style="display: none;"</c:if>>
	<div class="row">
		<div class="col-md-6">
			<aui:input type="time" value="${param.startHour5}" name="startHour5_${param.index}" label="start-hour" placeholder="HH:MM" >
				<aui:validator errorMessage="hour-format" 
				name="custom">
			        function(val, fieldNode, ruleValue) {
				        var regex = new RegExp(/([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/i);
				        return regex.test(val);
			        }
				</aui:validator>
			</aui:input>
			<div class="place-schedule-start-hour" style="display: none">
				<liferay-ui:message key="this-field-is-required" />
			</div>
			
			<div class="place-schedule-incorrect-hour" style="display: none">
				<liferay-ui:message key="incorrect-hour" />
			</div>
		</div>
		<div class="col-md-6">
			<aui:input type="time" value="${param.endHour5}" name="endHour5_${param.index}" label="end-hour" placeholder="HH:MM" >
				<aui:validator errorMessage="hour-format" 
				name="custom">
			        function(val, fieldNode, ruleValue) {
				        var regex = new RegExp(/([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/i);
				        return regex.test(val);
			        }
				</aui:validator>
			</aui:input>
		</div>
	</div>
</div>


