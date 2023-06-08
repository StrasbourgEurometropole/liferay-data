<%@ include file="/place-bo-init.jsp"%>
<div role="tabpanel" 
	<c:if test="${param.index == 0}">
		class="tab-pane active fade in"
	</c:if>
	<c:if test="${param.index > 0}">
		class="tab-pane fade in"
	</c:if>
id="period${param.index}">
	<aui:input name="numPeriod${param.index}" value="${param.index}" type="hidden" />
	<aui:input name="periodId${param.index}" value="${param.periodId}" type="hidden" />

	<aui:input name="namePeriod${param.index}" label="name-period" value="${param.name}" localized="true" type="text" disabled="${param.disabled}" />
	<div class="place-period-name" style="display: none">
		<liferay-ui:message key="this-field-is-required" />
	</div>
				
	<aui:input name="defaultPeriod${param.index}" label="default-period" type="toggle-switch" 
		value="${not empty param ? param.defaultPeriod : false}" onClick="affichageDates(this, ${param.index})" disabled="${param.disabled}" />
	<div class="place-period-default" style="display: none">
		<liferay-ui:message key="can-not-checked" />
	</div>
	
	<div class="dates${param.index}" <c:if test="${not empty param and param.defaultPeriod }">style="display: none;"</c:if>>
		<aui:input type="date" name="startDatePeriod${param.index}" label="start-date" value="${param.startDate}" disabled="${param.disabled}" />
		<div class="place-period-start-date" style="display: none">
			<liferay-ui:message key="this-field-is-required" />
		</div>
		
		<div class="place-period-incorrect-date" style="display: none">
			<liferay-ui:message key="incorrect-date" />
		</div>
		
		<aui:input type="date" name="endDatePeriod${param.index}" label="end-date" value="${param.endDate}" disabled="${param.disabled}" />
		<div class="place-period-end-date" style="display: none">
			<liferay-ui:message key="this-field-is-required" />
		</div>
	</div>
				
	<aui:input name="alwaysOpen${param.index}" label="always-open" type="toggle-switch" 
	value="${not empty param ? param.alwaysOpen : false}" onClick="affichageOuverture(this, ${param.index})" disabled="${param.disabled}" />
	
	<div class="ouvertures${param.index}" <c:if test="${not empty param and param.alwaysOpen }">style="display: none;"</c:if>>
		<c:forEach var="jour" begin="0" end="6">
			<div class="slot-label">
				<label><liferay-ui:message key="jour-semaine${jour}" /></label>
				
				<aui:input name="slotsIndexes${param.index}-${jour}" type="hidden" />
				<br />
				<c:set var="nbSlot" value="0"/>
				<c:set var="jourSlot" value="${fn:split(param.slotJours, ',')}" />
				<c:set var="startHourSlot" value="${fn:split(param.slotStartHours, ',')}" />
				<c:set var="endHourSlot" value="${fn:split(param.slotEndHours, ',')}" />
				<c:set var="commentSlot" value="${fn:split(param.slotComment, '|')}" />
				<c:forEach var="slot" begin="0" end="${param.nbSlot}">
					<c:if test="${jourSlot[slot] == jour}">
						<liferay-util:include page="/includes/slot-row.jsp" servletContext="<%=application %>">
							<liferay-util:param name="indexPeriod" value="${param.index}" />
							<liferay-util:param name="jour" value="${jour}" />
							<liferay-util:param name="indexSlot" value="${nbSlot}" />
							<liferay-util:param name="startHour" value="${startHourSlot[slot]}" />
							<liferay-util:param name="endHour" value="${endHourSlot[slot]}" />
							<liferay-util:param name="comment" value="${commentSlot[slot]}" />
						</liferay-util:include>
						<c:set var="nbSlot" value="${nbSlot + 1}"/>
					</c:if>  
				</c:forEach>
				<section id="${param.index}-${jour}">
					<aui:button cssClass="btn-icon icon icon-plus icon-2x" type="button" onClick="addSlot(${param.index}, ${jour}); return false;"/>
					<liferay-ui:message key="new-slot" /><br>
					<c:if test="${jour == 0}">
						<a onclick="copyHoraire(${param.index}); return false;" style="cursor:pointer;" ><span class="btn-icon icon icon-copy"></span>  <liferay-ui:message key="copie-horaire" /></a>
					</c:if> 
				</section>
				<aui:input name="nbSlot${param.index}-${jour}" type="hidden" value="${nbSlot}" />
			</div>
		</c:forEach>
	</div>
</div>