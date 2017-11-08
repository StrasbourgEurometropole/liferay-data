<%@ include file="/search-activity-init.jsp"%>


<liferay-portlet:actionURL portletConfiguration="<%=true%>"
	var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%=true%>"
	var="configurationRenderURL" />

<div class="container-fluid-1280 main-content-body">
	<aui:form action="<%=configurationActionURL%>" method="post" name="fm">
	
		<aui:input name="cmd" type="hidden"
			value="update" />
	
		<aui:input name="redirect" type="hidden"
			value="<%=configurationRenderURL%>" />
			
		<aui:fieldset>
			<!-- Affichage -->
			<aui:select name="template" label="display">
				<aui:option value="default" label="default" />
				<aui:option value="strasbourg" label="strasbourg" selected="${template eq 'strasbourg'}" />
				<aui:option value="strasbourg-no-schedule" label="strasbourg-no-schedule" selected="${template eq 'strasbourg-no-schedule'}" />
			</aui:select>
		
			<!-- ADT vignette -->
		    <div class="display-template">
		        <liferay-ddm:template-selector
		            className="<%= Activity.class.getName() %>"
		            displayStyle="${displayStyle}"
		            displayStyleGroupId="${displayStyleGroupId}"
		            refreshURL="${refreshURL}"
		            showEmptyOption="<%= true %>"
		        />
		    </div>
		    
		    <!-- Page de détail d'activité -->
			<strasbourg-picker:layout name="detailPageUuid" label="detail-page" 
				multiple="false" required="false" value="${detailPageUuid}" />
				
		    <!-- Page de détail de cours -->
			<strasbourg-picker:layout name="courseDetailPageUuid" label="course-detail-page" 
				multiple="false" required="false" value="${courseDetailPageUuid}" />
				
			<!-- Type d'activité -->
			<div class="categorySelector">
				<div id="activityTypeSelectorLabel">
					<label><liferay-ui:message key="activity-type" /></label>
				</div>
				<div id="activityTypeSelector"></div>
				<aui:input type="hidden" name="activityTypeIds" />
			</div>
			
			<!-- Type de cours-->
			<div class="categorySelector">
				<div id="courseTypeSelectorLabel">
					<label><liferay-ui:message key="course-type" /></label>
				</div>
				<div id="courseTypeSelector"></div>
				<aui:input type="hidden" name="courseTypeIds" />
			</div>
			
			<!-- Public -->
			<div class="categorySelector">
				<div id="publicSelectorLabel">
					<label><liferay-ui:message key="public" /></label>
				</div>
				<div id="publicSelector"></div>
				<aui:input type="hidden" name="publicIds" />
			</div>
			
			<!-- Territoire -->
			<div class="categorySelector">
				<div id="territorySelectorLabel">
					<label><liferay-ui:message key="territory" /></label>
				</div>
				<div id="territorySelector"></div>
				<aui:input type="hidden" name="territoryIds" />
			</div>
			
			<!-- Texte à afficher sous le moteur de recherche et avant les résultats -->
			<aui:input name="text" value="${textXML}" localized="true" type="editor" label="text" />
			
		</aui:fieldset>
	
		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>

	<aui:script use="liferay-asset-categories-selector">
		// Type d'activité
		new Liferay.AssetCategoriesSelector(
			{
				curEntryIds: '${activityTypeIds}',
				curEntries: '${activityTypeNames}',
				hiddenInput: '#<portlet:namespace />activityTypeIds',
				contentBox: '#activityTypeSelector',
				label: '<liferay-ui:message key="select" />',
				labelNode: '#activityTypeSelectorLabel',
				singleSelect: false,
				vocabularyGroupIds: ${themeDisplay.scopeGroupId},
				vocabularyIds: '${activityTypeVocabularyId}'
			}
		).render();

		// Type de cours
		new Liferay.AssetCategoriesSelector(
			{
				curEntryIds: '${courseTypeIds}',
				curEntries: '${courseTypeNames}',
				hiddenInput: '#<portlet:namespace />courseTypeIds',
				contentBox: '#courseTypeSelector',
				label: '<liferay-ui:message key="select" />',
				labelNode: '#courseTypeSelectorLabel',
				singleSelect: false,
				vocabularyGroupIds: ${themeDisplay.scopeGroupId},
				vocabularyIds: '${courseTypeVocabularyId}'
			}
		).render();

		// Public
		new Liferay.AssetCategoriesSelector(
			{
				curEntryIds: '${publicIds}',
				curEntries: '${publicNames}',
				hiddenInput: '#<portlet:namespace />publicIds',
				contentBox: '#publicSelector',
				label: '<liferay-ui:message key="select" />',
				labelNode: '#publicSelectorLabel',
				singleSelect: false,
				vocabularyGroupIds: ${themeDisplay.scopeGroupId},
				vocabularyIds: '${publicVocabularyId}'
			}
		).render();

		// Territoire
		new Liferay.AssetCategoriesSelector(
			{
				curEntryIds: '${territoryIds}',
				curEntries: '${territoryNames}',
				hiddenInput: '#<portlet:namespace />territoryIds',
				contentBox: '#territorySelector',
				label: '<liferay-ui:message key="select" />',
				labelNode: '#territorySelectorLabel',
				singleSelect: false,
				vocabularyGroupIds: ${themeDisplay.companyGroupId},
				vocabularyIds: '${territoryVocabularyId}'
			}
		).render();
	</aui:script>
</div>
<style>
	.categorySelector {
		margin-bottom: 20px;
	}
</style>