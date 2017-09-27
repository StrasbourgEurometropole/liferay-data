<%@ include file="/experimental-search-init.jsp"%>

<liferay-portlet:actionURL portletConfiguration="true"
	var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="true"
	var="configurationRenderURL" />

<div class="container-fluid-1280 main-content-body">
	<liferay-ui:error key="wrong-friendly-url" message="wrong-friendly-url" />
	<aui:form action="${configurationActionURL}" method="post" name="fm">
		<aui:input name="cmd" type="hidden"
			value="update" />
	
		<aui:input name="redirect" type="hidden"
			value="${configurationRenderURL}" />
	
		<aui:fieldset id="general" label="general">
			<aui:select name="template" label="display">
				<aui:option value="" label="" />
				<aui:option value="christmas-home" label="christmas-home" selected="${template eq 'christmas-home'}"/>
				<aui:option value="christmas-agenda" label="christmas-agenda" selected="${template eq 'christmas-agenda'}"/>
			</aui:select>
			<aui:input type="text" name="agendaFriendlyURL" value="${agendaFriendlyURL}" label="agenda-friendly-url" />
		</aui:fieldset>
		
	
		<aui:fieldset id="criteria-1" label="criteria-1">
			<aui:input type="text" name="criteria1" value="${criteria1}" label="criteria-1" localized="true"/>
			
			<c:forEach begin="0" end="5" step="1" varStatus="loop">
				<div class="col-md-4 option">
					<aui:input type="text" localized="true" name="criteria1Option${loop.index}" 
						value="${fn:length(criteria1Options) >= loop.count ? criteria1Options[loop.index] : ''}" 
						label="option-${loop.count}" />
					
					<label><liferay-ui:message key="option-${loop.count}-categories" />
						<liferay-ui:asset-categories-selector
							hiddenInput="criteria1Option${loop.index}Categories"
							curCategoryIds="${criteria1OptionCategories[loop.index]}" />
					</label>
				</div> 
			</c:forEach>
		</aui:fieldset>
	
		<aui:fieldset id="criteria-2" label="criteria-2">
			<aui:input type="text" name="criteria2" value="${criteria2}" label="criteria-2" localized="true" />
			
			<c:forEach begin="0" end="5" step="1" varStatus="loop">
				<div class="col-md-4 option">
					<aui:input type="text" localized="true" name="criteria2Option${loop.index}" 
						value="${fn:length(criteria2Options) >= loop.count ? criteria2Options[loop.index] : ''}" 
						label="option-${loop.count}" />
					
					<label><liferay-ui:message key="option-${loop.count}-categories" />
						<liferay-ui:asset-categories-selector
							hiddenInput="criteria2Option${loop.index}Categories"
							curCategoryIds="${criteria2OptionCategories[loop.index]}" />
					</label>
				</div>
			</c:forEach>
		</aui:fieldset>
		
		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>
<style>
	.option {
		border: 1px solid grey;
	    padding: 20px;
	    margin-left: -1px;
	    margin-top: -1px;
	    height: 250px;
	}
	fieldset {
		margin-bottom: 20px;
	}
</style>