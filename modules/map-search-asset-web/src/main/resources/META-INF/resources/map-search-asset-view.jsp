<%@ include file="/map-search-asset-init.jsp" %>

<portlet:resourceURL id="changeMapSelection" var="changeMapSelectionURL">
</portlet:resourceURL>

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
	                        <label for="Projets"><liferay-ui:message key="projects" /></label>
	                        <input type="checkbox" id="Projets"/>
	                        <div class="pro-switch pro-round"></div>
	
	                        <fieldset class="pro-wrapper-chk" >
	                            <legend aria-hidden="true" class="hide"><liferay-ui:message key="projects" /></legend>
	    						<c:forEach items="${projects}" var="project" varStatus="loop" >
		                            <div>
		                                <input type="checkbox" id="cat_${loop.index}" class="hide-checkbox" name="type_val[]" value="${project.getProjectId()}">
		                                <label for="cat_${loop.index}">${project.getTitle()}</label>
		                            </div>
		                        </c:forEach>
	                        </fieldset>
	                    </div>
	                </div>
					
					<!-- Selecteur d'evenements -->
	                <div class="pro-group">
	                    <div class="pro-item">
	                        <label for="Evenements"><liferay-ui:message key="events" /></label>
	                        <input type="checkbox" id="Evenements"/>
	                        <div class="pro-switch pro-round"></div>
	
	                        <fieldset class="pro-wrapper-chk">
	                            <legend aria-hidden="true" class="hide"><liferay-ui:message key="events" /></legend>
	                            <div>
	                                <input type="checkbox" id="cat_1_1" class="hide-checkbox" name="type_val[]" value="value">
	                                <label for="cat_1_1">Nom de l'item</label>
	                            </div>
	                            <div>
	                                <input type="checkbox" id="cat_2_1" class="hide-checkbox" name="type_val[]" value="value">
	                                <label for="cat_2_1">Nom de l'item</label>
	                            </div>
	                            <div>
	                                <input type="checkbox" id="cat_3_1" class="hide-checkbox" name="type_val[]" value="value">
	                                <label for="cat_3_1">Nom de l'item</label>
	                            </div>
	                            <div>
	                                <input type="checkbox" id="cat_4_1" class="hide-checkbox" name="type_val[]" value="value">
	                                <label for="cat_4_1">Nom de l'item</label>
	                            </div>
	                        </fieldset>
	                    </div>
	                </div>
					
					<!-- Selecteur de participations -->
	                <div class="pro-group">
	                    <div class="pro-item">
	                        <label for="participation"><liferay-ui:message key="participations" /></label>
	                        <input type="checkbox" id="participation"/>
	                        <div class="pro-switch pro-round"></div>
	
	                        <fieldset class="pro-wrapper-chk">
	                            <legend aria-hidden="true" class="hide"><liferay-ui:message key="participations" /></legend>
	                            <div>
	                                <input type="checkbox" id="cat_1_2" class="hide-checkbox" name="type_val[]" value="value">
	                                <label for="cat_1_2">Nom de l'item</label>
	                            </div>
	                            <div>
	                                <input type="checkbox" id="cat_2_2" class="hide-checkbox" name="type_val[]" value="value">
	                                <label for="cat_2_2">Nom de l'item</label>
	                            </div>
	                            <div>
	                                <input type="checkbox" id="cat_3_2" class="hide-checkbox" name="type_val[]" value="value">
	                                <label for="cat_3_2">Nom de l'item</label>
	                            </div>
	                            <div>
	                                <input type="checkbox" id="cat_4_2" class="hide-checkbox" name="type_val[]" value="value">
	                                <label for="cat_4_2">Nom de l'item</label>
	                            </div>
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
		var changeDistrictURL = '${changeDistrictURL}';
		var porletNamespace = '<portlet:namespace/>';
	</script>
</liferay-util:html-top>

<liferay-util:html-bottom>
	<script src="/o/mapsearchassetweb/js/map-search-asset-view.js"></script>
</liferay-util:html-bottom>