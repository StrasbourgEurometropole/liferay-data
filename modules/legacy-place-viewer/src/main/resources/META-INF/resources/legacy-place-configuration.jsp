<%@ include file="/legacy-place-init.jsp"%>

<liferay-portlet:actionURL portletConfiguration="true"
	var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="true"
	var="configurationRenderURL" />
	
<liferay-portlet:resourceURL portletConfiguration="true" portletName="eu_strasbourg_portlet_legacy_place_LegacyPlaceViewerPortlet"
	var="placeAutocompleteURL">
</liferay-portlet:resourceURL>
	

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${configurationActionURL}" method="post" name="fm">
		<aui:input name="cmd" type="hidden"
			value="update" />
	
		<aui:input name="redirect" type="hidden"
			value="${configurationRenderURL}" />
	
		<aui:fieldset id="subplace-place" label="place">
			<div class="place-autocomplete">
				<div class="place-autocomplete-input-wrapper">
					<aui:input label="Choisir un lieu" type="text" name="place" value="" />
				</div>
				<span id="place-autocomplete-hidden-value">
					<aui:input type="hidden" name="SIGId" value="${SIGId}" />
				</span>
				<aui:input type="hidden" name="chosenPlaceName" value="${placeName}" cssClass="chosen-place" />
				<aui:input label="Lieu choisi" type="text" value="${placeName}" name="placeName" disabled="true" cssClass="selected-place" />
			</div>
		</aui:fieldset>
		
		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>
<script>
 	window.placeAutocompleteURL = '${placeAutocompleteURL}';
</script>
<style>
	.place-autocomplete-input-wrapper {
		position: relative;
	}
	.autocomplete-suggestions {
	    background: white;
	    border: 1px solid #d8d8d8;
	    top: 60px;
	    overflow: auto;
	    max-height: 150px;
	}
	
	.autocomplete-suggestion {
	    cursor: pointer;
	    padding: 2px 11px;
	}
</style>