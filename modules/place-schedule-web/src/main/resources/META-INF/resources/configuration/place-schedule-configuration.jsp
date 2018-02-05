<%@ include file="/place-schedule-init.jsp"%>

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
		
		<aui:fieldset>
			<div id="categorySelectorLabel"></div>
			<div id="categorySelector"></div>
			<aui:input type="hidden" name="categoryId" />
		</aui:fieldset>
		
		<aui:fieldset>
			<aui:input name="textMap" value="${textSchedule}" localized="true" type="editor" label="text" />
		</aui:fieldset>
	
		<aui:fieldset>
			<strasbourg-picker:layout name="linksUuids" label="detail-page" multiple="false" required="true" value="${linksUuids}" />
		</aui:fieldset>
		<aui:fieldset>
			<!-- Mode d'affichage -->
			<aui:select name="template">
				<aui:option value="default" selected="${template eq 'default'}">
					<liferay-ui:message key="default" />
				</aui:option>
				<aui:option value="strasbourg" selected="${template eq 'strasbourg'}">
					<liferay-ui:message key="strasbourg.eu" />
				</aui:option>
				<aui:option value="strasbourg-table" selected="${template eq 'strasbourg-table'}">
					<liferay-ui:message key="strasbourg-table" />
				</aui:option>
			</aui:select>
		</aui:fieldset>
		
		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>

<aui:script use="liferay-asset-categories-selector">
	new Liferay.AssetCategoriesSelector(
		{
			curEntryIds: "${categoryId}",
			curEntries: "${categoryTitle}",
			hiddenInput: "#<portlet:namespace />categoryId",
			contentBox: "#categorySelector",
			label: "Type de lieu",
			labelNode: "#categorySelectorLabel",
			singleSelect: true,
			vocabularyGroupIds: ${themeDisplay.companyGroupId},
			vocabularyIds: "${placeTypeVocabularyId}"
		}
	).render();
</aui:script>
<style>
	fieldset {
		margin-bottom: 20px;
	}
</style>