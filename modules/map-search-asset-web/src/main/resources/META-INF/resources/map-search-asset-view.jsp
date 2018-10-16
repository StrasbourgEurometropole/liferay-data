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
	                        <label for="projects_printator_mk1"><liferay-ui:message key="projects" /></label>
	                        <input type="checkbox" id="projects_printator_mk1" checked />
	                        <div class="pro-switch pro-round"></div>
						
	                        <fieldset id="projects_fieldset" class="pro-wrapper-chk" >
	                            <legend aria-hidden="true" class="hide"><liferay-ui:message key="projects" /></legend>
	    						<c:forEach items="${projects}" var="project" varStatus="loop" >
 		                            <div>
		                                <input checked type="checkbox" id="project_${loop.index}" class="hide-checkbox" value="${project.getProjectId()}">
		                                <label for="project_${loop.index}">${project.getTitle()}</label>
		                            </div>
		                        </c:forEach>
	                        </fieldset>
	                    </div>
	                </div>
	                
	                <!-- Selecteur de participations -->
	                <div class="pro-group">
	                    <div class="pro-item">
	                        <label for="participations_printator_mk1"><liferay-ui:message key="participations" /></label>
	                        <input type="checkbox" id="participations_printator_mk1" checked />
	                        <div class="pro-switch pro-round"></div>
	
	                        <fieldset id="participations_fieldset" class="pro-wrapper-chk">
	                            <legend aria-hidden="true" class="hide"><liferay-ui:message key="participations" /></legend>
	                           	<c:forEach items="${participations}" var="participation" varStatus="loop" >
		                            <div>
		                                <input checked type="checkbox" id="participation_${loop.index}" class="hide-checkbox" value="${participation.getParticipationId()}">
		                                <label for="participation_${loop.index}">${participation.getTitle()}</label>
		                            </div>
		                        </c:forEach>
	                        </fieldset>
	                    </div>
	                </div>
	                
	                <!-- Selecteur de petitions -->
	                <div class="pro-group">
	                    <div class="pro-item">
	                        <label for="petitions_printator_mk1"><liferay-ui:message key="petitions" /></label>
	                        <input type="checkbox" id="petitions_printator_mk1" checked />
	                        <div class="pro-switch pro-round"></div>
	
	                        <fieldset id="petitions_fieldset" class="pro-wrapper-chk">
	                            <legend aria-hidden="true" class="hide"><liferay-ui:message key="petitions" /></legend>
	                           	<c:forEach items="${petitions}" var="petition" varStatus="loop" >
		                            <div>
		                                <input checked type="checkbox" id="petition_${loop.index}" class="hide-checkbox" value="${petition.getPetitionId()}">
		                                <label for="petition_${loop.index}">${petition.getTitle()}</label>
		                            </div>
		                        </c:forEach>
	                        </fieldset>
	                    </div>
	                </div>
					
					<!-- Selecteur d'evenements -->
	                <div class="pro-group">
	                    <div class="pro-item">
	                        <label for="events_printator_mk1"><liferay-ui:message key="events" /></label>
	                        <input type="checkbox" id="events_printator_mk1" checked />
	                        <div class="pro-switch pro-round"></div>
							
	                        <fieldset id="events_fieldset" class="pro-wrapper-chk">
	                            <legend aria-hidden="true" class="hide"><liferay-ui:message key="events" /></legend>
	                            <c:forEach items="${events}" var="event" varStatus="loop" >
		                            <div>
		                                <input checked type="checkbox" id="event_${loop.index}" class="hide-checkbox" value="${event.getEventId()}">
		                                <label for="event_${loop.index}">${event.getTitle(locale)}</label>
		                            </div>
		                        </c:forEach>
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