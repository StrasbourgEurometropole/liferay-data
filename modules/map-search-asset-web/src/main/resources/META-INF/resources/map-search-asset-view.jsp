<%@ include file="/map-search-asset-init.jsp" %>

<portlet:resourceURL id="changeDistrictSelection" var="changeDistrictSelectionURL">
</portlet:resourceURL>

<link rel="stylesheet" href="/o/mapsearchassetweb/css/map-search-asset-view.css" />

<!-- Conteneur de la page de carte interactive  -->
<div class="pro-page-carte">

	<!-- Vignette de formulaire -->
	<div class="pro-wrapper-facette-carte">
	    <form id="map-search-asset-form" action="page-carte.html"  method="get">
	        <div class="pro-overflow">
	            <span class="pro-affiner"><liferay-ui:message key="refine-your-search" /></span>
	        </div>
	
	        <div class="pro-wrapper-group">
	            <div>
	            
	            	<!-- Selecteur de quartiers -->
	                <div>
	                    <label for="quartiers"  aria-hidden="true" class="hide"><liferay-ui:message key="districts" /></label>
	                    <select id="district">
	                        <option value="-1"><liferay-ui:message key="all-districts" /></option>
	                        <c:forEach items="${districtCategories}" var="districtCategory"  >
	                        	<option value="${districtCategory.getCategoryId()}">${districtCategory.getTitle(locale)}</option>
	                        </c:forEach>
	                    </select>
	                </div>
					
					<!-- Selecteur de projets -->
	                <div class="pro-group">
	                    <div class="pro-item">
	                    	<span class="icon-ico-map-projet"></span>
	                        <label for="projects_printator_mk1"><liferay-ui:message key="projects" /></label>
	                        <input type="checkbox" id="projects_printator_mk1" value="project" checked />
	                        <div class="pro-switch pro-round"></div>
						
	                        <fieldset id="projects_fieldset" class="pro-wrapper-chk" >
	                        	<span class="pro-remove-chk">effacer</span>
	                            <legend aria-hidden="true" class="hide"><liferay-ui:message key="projects" /></legend>
	                        </fieldset>
	                    </div>
	                </div>
	                
	                <!-- Selecteur de participations -->
	                <div class="pro-group">
	                    <div class="pro-item">
	                    	<span class="icon-ico-map-participation"></span>
	                        <label for="participations_printator_mk1"><liferay-ui:message key="participations" /></label>
	                        <input type="checkbox" id="participations_printator_mk1" value="participation" checked  />
	                        <div class="pro-switch pro-round"></div>
	
	                        <fieldset id="participations_fieldset" class="pro-wrapper-chk">
	                        	<span class="pro-remove-chk">effacer</span>
	                            <legend aria-hidden="true" class="hide"><liferay-ui:message key="participations" /></legend>
	                        </fieldset>
	                    </div>
	                </div>
	                
	                <!-- Selecteur de petitions -->
	                <div class="pro-group">
	                    <div class="pro-item">
	                    	<span class="icon-ico-map-petition"></span>
	                        <label for="petitions_printator_mk1"><liferay-ui:message key="petitions" /></label>
	                        <input type="checkbox" id="petitions_printator_mk1" value="petition" checked />
	                        <div class="pro-switch pro-round"></div>
	                        
	                        <fieldset id="petitions_fieldset" class="pro-wrapper-chk">
	                        	<span class="pro-remove-chk">effacer</span>
	                            <legend aria-hidden="true" class="hide"><liferay-ui:message key="petitions" /></legend>
	                        </fieldset>
	                    </div>
	                </div>
	                
	                <!-- Selecteur de budgets participatifs -->
	                <div class="pro-group">
	                    <div class="pro-item">
	                    	<span class="icon-ico-map-budget-participatif"></span>
	                        <label for="budgets_printator_mk1"><liferay-ui:message key="budgets-participatifs" /></label>
	                        <input type="checkbox" id="budgets_printator_mk1" value="budget" checked />
	                        <div class="pro-switch pro-round"></div>
	                        
	                        <fieldset id="budgets_fieldset" class="pro-wrapper-chk">
	                        	<span class="pro-remove-chk">effacer</span>
	                            <legend aria-hidden="true" class="hide"><liferay-ui:message key="budgets-participatifs" /></legend>
	                        </fieldset>
	                    </div>
	                </div>
	                
	                <!-- Selecteur d'initiatives -->
	                <div class="pro-group">
	                    <div class="pro-item">
	                    	<span class="icon-ico-map-initiative"></span>
	                        <label for="initiatives_printator_mk1"><liferay-ui:message key="atelier-quartier" /></label>
	                        <input type="checkbox" id="initiatives_printator_mk1" value="initiative" checked />
	                        <div class="pro-switch pro-round"></div>
	                        
	                        <fieldset id="initiatives_fieldset" class="pro-wrapper-chk">
	                        	<span class="pro-remove-chk">effacer</span>
	                            <legend aria-hidden="true" class="hide"><liferay-ui:message key="initiatives" /></legend>
	                        </fieldset>
	                    </div>
	                </div>
					
					<!-- Selecteur d'evenements -->
	                <div class="pro-group">
	                    <div class="pro-item">
	                    	<span class="icon-ico-map-evenements"></span>
	                        <label for="events_printator_mk1"><liferay-ui:message key="events" /></label>
	                        <input type="checkbox" id="events_printator_mk1" value="event" checked />
	                        <div class="pro-switch pro-round"></div>
							
	                        <fieldset id="events_fieldset" class="pro-wrapper-chk">
	                        	<span class="pro-remove-chk">effacer</span>
	                            <legend aria-hidden="true" class="hide"><liferay-ui:message key="events" /></legend>
	                        </fieldset>
	                    </div>
	                </div>
	                
	            </div>
	        </div>
	    </form>
	</div>
	
	<!-- Map Leaflet -->
	<div class="bloc-iframe pro-wrapper-map leaflet-map" id="mapid" ></div>

	<!-- Affichage du menu en format mobile -->
	<div class="pro-menu-carte-mobile">
        <span id="pro-btn-menu-map" class="icon-menu"></span>
    </div>
    
</div>

<style>
	<c:choose>
		<c:when test="<%= themeDisplay.isSignedIn() %>">
			main#content {
				padding-top: 64px !important;
			}
		</c:when>
	    <c:otherwise>
	    	main#content {
				padding-top: 0px;
			}
		</c:otherwise>
	</c:choose>
	.pro-page-carte {
		padding-top: 167px;
	}
	footer#pro-footer {
	    display: none;
	}
</style>

<liferay-util:html-top>
	<script>
		var homeURL = '${homeURL}';
		var porletNamespace = '<portlet:namespace/>';
		
		var changeDistrictSelectionURL = '${changeDistrictSelectionURL}';
	</script>
</liferay-util:html-top>

<liferay-util:html-bottom>
	<script src="/o/mapsearchassetweb/js/map-search-asset-view.js"></script>
</liferay-util:html-bottom>