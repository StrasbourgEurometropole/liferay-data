<!-- Horaire d'un lieu -->
<%@ include file="/activity-bo-init.jsp"%>

<div class="schedule new-schedule" <c:if test="${param.hidden eq 'true'}">style="display:none"</c:if>>
	
	<div class="row">
		<div class="col-md-8">
			<!-- Périodes -->
			<div class="periods">
				<aui:select multiple="true" label="periods" name="periods_${param.placeIndex}_${param.scheduleIndex}">
					<c:forEach items="${dc.periods}" var="period">
						<aui:option value="${period.categoryId}" label="${period.getTitle(locale)}" 
							selected="${fn:contains(courseSchedule.periodsIds, period.categoryId)}"/>
					</c:forEach>
				</aui:select>
			</div>
		</div>
		<div class="col-md-4 time">
		<!-- Horaires -->
			<aui:input type="time" name="startTime_${param.placeIndex}_${param.scheduleIndex}" label="start" value="${courseSchedule.startTime}" />
			<aui:input type="time" name="endTime_${param.placeIndex}_${param.scheduleIndex}" label="end" value="${courseSchedule.endTime}"/>
		</div>
	</div>
	
	<!-- Jours concernés par l'horaire -->
	<div class="days">
		<aui:input type="checkbox" value="1" name="monday_${param.placeIndex}_${param.scheduleIndex}" 
			label="L" inlineField="true" inlineLabel="left" checked="${courseSchedule.hasScheduleOnDay(0)}" />
			
		<aui:input type="checkbox" value="1" name="tuesday_${param.placeIndex}_${param.scheduleIndex}" 
			label="M" inlineField="true" inlineLabel="left" checked="${courseSchedule.hasScheduleOnDay(1)}" />
			
		<aui:input type="checkbox" value="1" name="wednesday_${param.placeIndex}_${param.scheduleIndex}" 
			label="M" inlineField="true" inlineLabel="left" checked="${courseSchedule.hasScheduleOnDay(2)}" />
			
		<aui:input type="checkbox" value="1" name="thursday_${param.placeIndex}_${param.scheduleIndex}" 
			label="J" inlineField="true" inlineLabel="left" checked="${courseSchedule.hasScheduleOnDay(3)}" />
			
		<aui:input type="checkbox" value="1" name="friday_${param.placeIndex}_${param.scheduleIndex}" 
			label="V" inlineField="true" inlineLabel="left" checked="${courseSchedule.hasScheduleOnDay(4)}" />
			
		<aui:input type="checkbox" value="1" name="saturday_${param.placeIndex}_${param.scheduleIndex}" 
			label="S" inlineField="true" inlineLabel="left" checked="${courseSchedule.hasScheduleOnDay(5)}" />
			
		<aui:input type="checkbox" value="1" name="sunday_${param.placeIndex}_${param.scheduleIndex}" 
			label="D" inlineField="true" inlineLabel="left" checked="${courseSchedule.hasScheduleOnDay(6)}" />
	</div>
	
	<div class="comments">
		<c:forEach var="currentLocale" items="${dc.availableLocales}" varStatus="localeStatus">
			<div class="comment" 
				<c:if test="${localeStatus.index gt 0}">style="display: none"</c:if>
				<c:if test="${localeStatus.index gt 0}">data-french="false"</c:if>
			>
				<aui:input type="text" value="${courseSchedule.getComments(currentLocale)}" 
					name="comments_${currentLocale}_${param.placeIndex}_${param.scheduleIndex}"
					label="comments_${currentLocale}" />
				<c:if test="${localeStatus.index eq 0}">
					<a href="#" class="toggle-comments see-all-comments"><liferay-ui:message key="see-other-languages" /></a>
					<a href="#" class="toggle-comments hide-comments" style="display:none"><liferay-ui:message key="hide-comments" /></a>
				</c:if>
			</div>
		</c:forEach>
		
	</div>
	
	<!-- Ajout, suppression, duplication -->
	<button class="add-schedule btn btn-default btn-icon-only toolbar-first toolbar-item" title="" type="button">
		<span class="btn-icon icon icon-plus"></span>
	</button>
	<button class="remove-schedule btn btn-default btn-icon-only toolbar-first toolbar-item" title="" type="button">
		<span class="btn-icon icon icon-minus"></span>
	</button>
	<button class="duplicate-schedule btn btn-default btn-icon-only toolbar-first toolbar-item" title="" type="button">
		<span class="btn-icon icon icon-copy"></span>
	</button>
</div>
