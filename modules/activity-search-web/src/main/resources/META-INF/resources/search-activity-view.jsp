<%@ include file="/search-activity-init.jsp"%>

<liferay-portlet:renderURL var="searchURL" />

<aui:form name="fm" action="${searchURL}">
	<aui:select name="activityId">
		<aui:option value="" label="" />
		<c:forEach var="activity" items="${allActivities}">
			<aui:option value="${activity.activityId}"
				label="${activity.getTitle(locale)}" />
		</c:forEach>
	</aui:select>
	<aui:select name="typeId">
		<aui:option value="" label="" />
		<c:forEach var="type" items="${types}">
			<aui:option value="${type.categoryId}" label="${type.getTitle(locale)}" />
		</c:forEach>
	</aui:select>
	<aui:select name="publicId">
		<aui:option value="" label="" />
		<c:forEach var="public" items="${publics}">
			<aui:option value="${public.categoryId}"
				label="${public.getTitle(locale)}" />
		</c:forEach>
	</aui:select>
	<aui:select name="territoryId">
		<aui:option value="" label="" />
		<c:forEach items="${territories}" var="category">
			<c:if test="${category.rootCategory}">
				<c:set var="category" value="${category}" scope="request" />
				<c:set var="level" value="0" scope="request" />
				<jsp:include page="/category-option.jsp" />
			</c:if>
		</c:forEach>
	</aui:select>
	
	<div class="place-autocomplete" <c:if test="${empty coursePlace.placeSIGId and not empty coursePlace.placeName }">style="display: none;"</c:if>>
		<div class="place-autocomplete-input-wrapper" id="place-autocomplete-input-wrapper-${param.index}">
			<aui:input label="Choisir un lieu" type="text" name="place" />
		</div>
		<span id="place-autocomplete-hidden-value">
			<aui:input type="hidden" name="placeSIGId" value="${param.placeSIGId}"/>
		</span>
	</div>
	
	<aui:input type="checkbox" name="monday" />
	<aui:input type="checkbox" name="tuesday" />
	<aui:input type="checkbox" name="wednesday" />
	<aui:input type="checkbox" name="thursday" />
	<aui:input type="checkbox" name="friday" />
	<aui:input type="checkbox" name="saturday" />
	<aui:input type="checkbox" name="sunday" />

	<aui:input type="time" name="startTime" value="${param.startTime}" />
	<aui:input type="time" name="endTime" value="${param.endTime}"/>

	<aui:button type="submit" />
</aui:form>

<div class="activities">
	<c:forEach var="activityEntry" items="${dc.results}">
		<liferay-ddm:template-renderer
			className="<%= Activity.class.getName() %>"
			contextObjects="${dc.getTemplateContextObjects(activityEntry.key)}"
			displayStyle="${displayStyle}"
			displayStyleGroupId="${displayStyleGroupId}"
			entries="${templateEntries}">
				Veuillez sélectionner un template dans la configuration du portlet.
		</liferay-ddm:template-renderer>
	</c:forEach>
</div>

<liferay-util:html-bottom>
	<script>
		define._amd = define.amd;
		define.amd = false;
	</script>
	<script	src="/o/agendabo/js/vendors/jquery.autocomplete.js"></script>
	<script	src="/o/activitysearchweb/js/search-activity-main.js"></script>
	<script>
		define.amd = define._amd;
	</script>
</liferay-util:html-bottom>