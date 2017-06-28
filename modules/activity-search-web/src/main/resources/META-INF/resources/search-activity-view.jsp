<%@ include file="/search-activity-init.jsp"%>

<liferay-portlet:renderURL var="searchURL" />

<!-- Formulaire de recherche -->
<aui:form name="fm" action="${searchURL}">

	<!-- Activité -->
	<div class="activity-autocomplete">
		<div class="activity-autocomplete-input-wrapper" id="activity-autocomplete-input-wrapper">
			<aui:input label="Choisir une activit&eacute;" type="text" name="activity" />
		</div>
		<span id="activity-autocomplete-hidden-value">
			<aui:input type="hidden" name="activityId" value="${param.activityId}"/>
		</span>
	</div>
	
	<!-- Type -->
	<c:if test="${not empty dc.activityTypes}">
		<aui:select name="activityTypeId" label="type">
			<aui:option value="" label="" />
			<c:forEach items="${dc.activityTypes}" var="category">
				<c:set var="category" value="${category}" scope="request" />
				<c:set var="level" value="0" scope="request" />
				<jsp:include page="/category-option.jsp" />
			</c:forEach>
		</aui:select>
	</c:if>
	
	<!-- Public -->
	<c:if test="${not empty dc.publics}">
		<aui:select name="publicId" label="eu.activity.public">
			<aui:option value="" label="" />
			<c:forEach items="${dc.publics}" var="category">
				<c:set var="category" value="${category}" scope="request" />
				<c:set var="level" value="0" scope="request" />
				<jsp:include page="/category-option.jsp" />
			</c:forEach>
		</aui:select>
	</c:if>
	
	<!-- Territoire -->
	<c:if test="${not empty dc.territories}">
		<aui:select name="territoryId" label="eu.territory">
			<aui:option value="" label="" />
			<c:forEach items="${dc.territories}" var="category">
				<c:set var="category" value="${category}" scope="request" />
				<c:set var="level" value="0" scope="request" />
				<jsp:include page="/category-option.jsp" />
			</c:forEach>
		</aui:select>
	</c:if>
	
	<!-- Lieu -->
	<div class="place-autocomplete">
		<div class="place-autocomplete-input-wrapper" id="place-autocomplete-input-wrapper">
			<aui:input label="Choisir un lieu" type="text" name="place" />
		</div>
		<span id="place-autocomplete-hidden-value">
			<aui:input type="hidden" name="placeSIGId" value="${param.placeSIGId}"/>
		</span>
	</div>
	
	
	<!-- Jours -->
	<aui:input type="checkbox" name="monday" label="monday" />
	<aui:input type="checkbox" name="tuesday" label="tuesday" />
	<aui:input type="checkbox" name="wednesday" label="wednesday" />
	<aui:input type="checkbox" name="thursday" label="thursday" />
	<aui:input type="checkbox" name="friday" label="friday" />
	<aui:input type="checkbox" name="saturday" label="saturday" />
	<aui:input type="checkbox" name="sunday" label="sunday" />

	
	<!-- Heures -->
	<aui:input type="time" name="startTime" value="${param.startTime}" label="start" />
	<aui:input type="time" name="endTime" value="${param.endTime}" label="end "/>

	<aui:button type="submit" value="search" />
</aui:form>

<c:if test="${not empty dc.text}">
	<div class="activities-postform">${dc.text}</div>
</c:if>
	
<!-- Liste des résultats -->
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