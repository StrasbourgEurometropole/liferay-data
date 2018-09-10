<%@ include file="/project-bo-init.jsp"%>

<div id="place-${param.index}">

	<!-- Autocomplete lieu -->
	<div class="place-autocomplete" <c:if test="${empty placitPlace.placeSIGId and not empty placitPlace.placeName }">style="display: none;"</c:if>>
		<div class="row">
			<div class="col-md-6">
				<div class="place-autocomplete-input-wrapper" id="place-autocomplete-input-wrapper-${param.index}">
					<aui:input label="Choisir un lieu" type="text" name="place${param.index}" value="" />
				</div>
				<span id="place-autocomplete-hidden-value">
					<aui:input type="hidden" name="placeSIGId${param.index}" value="${placitPlace.placeSIGId}" />
				</span>
				<aui:input label="Lieu choisi" type="text" value="${placitPlace.getSIGPlaceAlias(locale)}" name="selectedPlace${param.index}" disabled="true" cssClass="selected-place" />
			</div>
		</div>
		<aui:button id="showManualPlace" cssClass="show-manual-place" name="changeTimes" value="show-manual-place" />
	</div>
	
	<!-- Saisie manuelle -->
	<div class="place-manual" <c:if test="${not empty placitPlace.placeSIGId or empty placitPlace.placeName }">style="display: none;"</c:if>>
		<div class="row">
			<div class="col-md-4">
				<aui:input name="placeName${param.index}" value="${placitPlace.placeName}" label="place-name" />
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<strasbourg-picker:image label="image" name="placeImageId${param.index}" value="${placitPlace.imageId}" multiple="false"/>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<aui:input name="placeStreetNumber${param.index}" label="place-street-number" value="${placitPlace.placeStreetNumber}"/>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<aui:input name="placeStreetName${param.index}" label="place-street-name" value="${placitPlace.placeStreetName}"/>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<aui:input name="placeZipCode${param.index}" label="place-zip-code" value="${placitPlace.placeZipCode}" />
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<aui:select name="placeCityId${param.index}" label="eu.city">
					<aui:option value="" label="select-city" />
					<c:forEach var="city" items="${dc.cities}">
						<aui:option value="${city.categoryId}"
							label="${city.getTitle(locale)}"
							selected="${city.categoryId eq placitPlace.placeCityId}" />
					</c:forEach>
				</aui:select>
			</div>
		</div>
		<aui:button id="showAutocompletePlace" cssClass="show-autocomplete-place" name="showAutocompletePlace" value="show-autocomplete-place" />
	</div>
	
</div>

<c:if test="${not empty fromAjaxParticipation or not empty fromAjaxProject}">
	<aui:script>
		$('#place-fields').trigger('placeCreated', ${param.index});
	</aui:script>
</c:if>
