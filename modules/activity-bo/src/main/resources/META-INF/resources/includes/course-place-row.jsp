<%@ include file="/activity-bo-init.jsp"%>

<div id="place-${param.index}">
	<!-- Autocomplete lieu -->
	<div class="place-autocomplete" <c:if test="${empty coursePlace.placeSIGId and not empty coursePlace.placeName }">style="display: none;"</c:if>>
		<div class="row">
			<div class="col-md-6">
				<div class="place-autocomplete-input-wrapper" id="place-autocomplete-input-wrapper-${param.index}">
					<aui:input label="Choisir un lieu" type="text" name="place${param.index}" value="" />
				</div>
				<span id="place-autocomplete-hidden-value">
					<aui:input type="hidden" name="placeSIGId${param.index}" value="${coursePlace.placeSIGId}" />
				</span>
				<aui:input label="Lieu choisi" type="text" value="${coursePlace.getPlaceAlias(locale)}" name="selectedPlace${param.index}" disabled="true" cssClass="selected-place" >
					<aui:validator name="required"
						errorMessage="this-field-is-required">
						function(node) {
							return jQuery(node._node).closest('.place-autocomplete').css('display') !== 'none';
						}	
					</aui:validator>
				</aui:input>
			</div>
		</div>
		<aui:button id="showManualPlace" cssClass="show-manual-place" name="changeTimes" value="show-manual-place" />
	</div>
	
	<!-- Saisie manuelle -->
	<div class="place-manual" <c:if test="${not empty coursePlace.placeSIGId or empty coursePlace.placeName }">style="display: none;"</c:if>>
		<div class="row">
			<div class="col-md-4">
				<aui:input name="placeName${param.index}" value="${coursePlace.placeName}" label="place-name" required="true" >
					<aui:validator name="required"
						errorMessage="this-field-is-required">
						function(node) {
							return jQuery(node._node).closest('.place-manual').css('display') !== 'none';
						}	
					</aui:validator>
				</aui:input>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<aui:input name="placeStreetNumber${param.index}" label="place-street-number" value="${coursePlace.placeStreetNumber}"/>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<aui:input name="placeStreetName${param.index}" label="place-street-name" value="${coursePlace.placeStreetName}"/>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<aui:input name="placeZipCode${param.index}" label="place-zip-code" value="${coursePlace.placeZipCode}" />
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<aui:select name="placeCityId${param.index}" label="eu.city">
					<aui:option value="" label="select-city" />
					<c:forEach var="city" items="${dc.cities}">
						<aui:option value="${city.categoryId}"
							label="${city.getTitle(locale)}"
							selected="${city.categoryId eq coursePlace.placeCityId}" />
					</c:forEach>
				</aui:select>
			</div>
		</div>
		<aui:button id="showAutocompletePlace" cssClass="show-autocomplete-place" name="showAutocompletePlace" value="show-autocomplete-place" />
	</div>		
</div>

<c:if test="${not empty fromAjax}">
	<aui:script>
		$('#place-fields').trigger('placeCreated', ${param.index});
	</aui:script>
</c:if>
