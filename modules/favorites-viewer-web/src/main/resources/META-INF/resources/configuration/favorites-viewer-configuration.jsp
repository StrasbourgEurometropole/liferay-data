<%@ include file="../favorites-viewer-init.jsp"%>

<liferay-portlet:actionURL portletConfiguration="true"
	var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="true"
	var="configurationRenderURL" />

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${configurationActionURL}" method="post" name="fm">
	
		<aui:input name="cmd" type="hidden"
			value="update" />
	
		<aui:input name="redirect" type="hidden"
			value="${configurationRenderURL}" />
			
		<aui:input name="showAllUrl" type="text" label="Voir tous url"
			value="${showAllURL}" />
		
		<aui:fieldset>
			<!-- Mode d'affichage -->
			<aui:select name="template" cssClass="template">
				<aui:option value="default" selected="${template eq 'default'}">
					<liferay-ui:message key="Menu" />
				</aui:option>
				<aui:option value="list" selected="${template eq 'list'}">
					<liferay-ui:message key="Tous" />
				</aui:option>
			</aui:select>
		</aui:fieldset>

        <aui:fieldset>
            <aui:input name="noFavoritesMap" value="${noFavorites}" localized="true" type="editor" label="no-favorites-text" />
        </aui:fieldset>


        <div class="widget">

            <aui:fieldset>
                <aui:input name="noFavoritesSelectedMap" value="${noFavoritesSelected}" localized="true" type="editor" label="no-favorites-selected-text" />
            </aui:fieldset>
        </div>

        <div class="list">
            <aui:fieldset>
                <aui:input name="texteMap" value="${texte}" localized="true" type="editor" label="chapo" />
            </aui:fieldset>
        </div>


        <script>
            var refreshConfigDisplay = function() {
               var template = $('.template').val();
               if (template === 'list') {
                   $('.widget').hide();
                   $('.list').show();
               } else {
                   $('.widget').show();
                   $('.list').hide();
               }
            }

            $('select').on('change', function() {
                refreshConfigDisplay();
            })
            $(function() {
                refreshConfigDisplay();
            })
        </script>
		
		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>