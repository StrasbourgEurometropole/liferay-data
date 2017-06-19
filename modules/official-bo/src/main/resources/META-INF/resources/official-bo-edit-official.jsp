<%@ include file="/official-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.official.model.Official"%>

<liferay-portlet:renderURL varImpl="officialsURL">
	<portlet:param name="tab" value="officials" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="deleteOfficial" var="deleteOfficialURL">
	<portlet:param name="cmd" value="deleteOfficial" />
	<portlet:param name="tab" value="officials" />
	<portlet:param name="officialId"
		value="${not empty dc.official ? dc.official.officialId : ''}" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL name="saveOfficial" varImpl="saveOfficialURL">
	<portlet:param name="cmd" value="saveOfficial" />
</liferay-portlet:actionURL>

<div class="container-fluid-1280 main-content-body">

	<aui:form action="${saveOfficialURL}" method="post" name="fm">
		
		<aui:translation-manager availableLocales="${dc.availableLocales}"
			changeableDefaultLanguage="false" defaultLanguageId="${locale}"
			id="translationManager" />
			
		<aui:model-context bean="${dc.official}" model="<%=Official.class %>" />
		
		<aui:fieldset-group markupView="lexicon">
		
			<aui:input name="officialId" type="hidden" />

			<aui:fieldset collapsed="true" collapsible="true"
				label="identity">
				
				<aui:select class="form-control" name="gender" >
					<aui:option value="1" label="male"  />
		        	<aui:option value="2" label="female" />
			    </aui:select>

				<aui:input name="lastName">
					<aui:validator name="required"
						errorMessage="this-field-is-required" />
				</aui:input>

				<aui:input name="firstName">
					<aui:validator name="required"
						errorMessage="this-field-is-required" />
				</aui:input>

				<strasbourg-picker:image label="photography" name="imageId"
					required="true" value="${dc.official.imageId}" />	
			
			</aui:fieldset>

			<aui:fieldset collapsed="true" collapsible="true"
				label="municipal-official">				
				
				<aui:select class="form-control" name="fonction-city" >
        			<aui:option value="" />
					<c:forEach items="${dc.fonctionsCity}" var="category" varStatus="status">
						<c:if test="${dc.official.isCategoryOfficial(category.categoryId)}">
        					<aui:option value="${category.categoryId}" label="${category.getTitle(locale)}" selected="true" />
						</c:if>
						<c:if test="${!dc.official.isCategoryOfficial(category.categoryId)}">
        					<aui:option value="${category.categoryId}" label="${category.getTitle(locale)}" />
						</c:if>
					</c:forEach>
			    </aui:select>
									
				<aui:input name="thematicDelegation" />
				
				<aui:select class="form-control" name="districts2" multiple="true" size="6" >
        			<aui:option value="" />
					<c:forEach items="${dc.districts}" var="category" varStatus="status">
						<c:if test="${dc.official.isCategoryOfficial(category.categoryId)}">
        					<aui:option value="${category.categoryId}" label="${category.getTitle(locale)}" selected="true" />
						</c:if>
						<c:if test="${!dc.official.isCategoryOfficial(category.categoryId)}">
        					<aui:option value="${category.categoryId}" label="${category.getTitle(locale)}" />
						</c:if>
					</c:forEach>
			    </aui:select>
				
				<aui:select class="form-control" name="political-group-city" >
        			<aui:option value="" />
					<c:forEach items="${dc.politicalsGroupsCity}" var="category" varStatus="status">
						<c:if test="${dc.official.isCategoryOfficial(category.categoryId)}">
        					<aui:option value="${category.categoryId}" label="${category.getTitle(locale)}" selected="true" />
						</c:if>
						<c:if test="${!dc.official.isCategoryOfficial(category.categoryId)}">
        					<aui:option value="${category.categoryId}" label="${category.getTitle(locale)}" />
						</c:if>
					</c:forEach>
			    </aui:select>
			
			</aui:fieldset>

			<aui:fieldset collapsed="true" collapsible="true"
				label="community-official">	
				
				<aui:select class="form-control" name="fonction-eurometropole" >
        			<aui:option value="" />
					<c:forEach items="${dc.fonctionsEurometropole}" var="category" varStatus="status">
						<c:if test="${dc.official.isCategoryOfficial(category.categoryId)}">
        					<aui:option value="${category.categoryId}" label="${category.getTitle(locale)}" selected="true" />
						</c:if>
						<c:if test="${!dc.official.isCategoryOfficial(category.categoryId)}">
        					<aui:option value="${category.categoryId}" label="${category.getTitle(locale)}" />
						</c:if>
					</c:forEach>
			    </aui:select>
				
				<aui:input name="missions" />
				
				<c:if test="${not empty dc.fonctionsTown}">
					<div class="form-group input-String-wrapper">
						<label><liferay-ui:message key="fonctions-town" /></label>
						<c:forEach items="${dc.fonctionsTown}" var="category" varStatus="status">
								<c:if test="${dc.official.isCategoryOfficial(category.categoryId)}">
		        					<aui:input name="fonction-town" value="${category.categoryId}" label="${category.getTitle(locale)}" type="radio" checked="true" />
								</c:if>
								<c:if test="${!dc.official.isCategoryOfficial(category.categoryId)}">
		        					<aui:input name="fonction-town" value="${category.categoryId}" label="${category.getTitle(locale)}" type="radio" />
								</c:if>
						</c:forEach>
					</div>
				</c:if>
				
				<aui:select class="form-control" name="town2" >
        			<aui:option value="" />
					<c:forEach items="${dc.towns}" var="category" varStatus="status">
						<c:if test="${dc.official.isCategoryOfficial(category.categoryId) && dc.official.isEluEurometropole()}">
        					<aui:option value="${category.categoryId}" label="${category.getTitle(locale)}" selected="true" />
						</c:if>
						<c:if test="${!dc.official.isCategoryOfficial(category.categoryId) || !dc.official.isEluEurometropole()}">
        					<aui:option value="${category.categoryId}" label="${category.getTitle(locale)}" />
						</c:if>
					</c:forEach>
			    </aui:select>
				
				<c:set var="categoryPoliticalGroupEurometropole" value="${dc.official.politicalGroupEurometropole.categoryId}"/>
				<aui:select class="form-control" name="political-group-eurometropole" >
        			<aui:option value="" />
					<c:forEach items="${dc.politicalsGroupsEurometropole}" var="category" varStatus="status">
						<c:if test="${dc.official.isCategoryOfficial(category.categoryId)}">
        					<aui:option value="${category.categoryId}" label="${category.getTitle(locale)}" selected="true" />
						</c:if>
						<c:if test="${!dc.official.isCategoryOfficial(category.categoryId)}">
        					<aui:option value="${category.categoryId}" label="${category.getTitle(locale)}" />
						</c:if>
					</c:forEach>
			    </aui:select>
			
			</aui:fieldset>

			<aui:fieldset collapsed="true" collapsible="true"
				label="additionnals-informations">
				
				<c:if test="${not empty dc.othersMandates}">
					<aui:select class="form-control" name="others-mandates" multiple="true" >
	        			<aui:option value="" />
						<c:forEach items="${dc.othersMandates}" var="category" varStatus="status">
							<c:if test="${dc.official.isCategoryOfficial(category.categoryId)}">
	        					<aui:option value="${category.categoryId}" label="${category.getTitle(locale)}" selected="true" />
							</c:if>
							<c:if test="${!dc.official.isCategoryOfficial(category.categoryId)}">
	        					<aui:option value="${category.categoryId}" label="${category.getTitle(locale)}" />
							</c:if>
						</c:forEach>
				    </aui:select>
				</c:if>	
				
				<div class="form-group input-String-wrapper">
					<label><liferay-ui:message key="olds-mandats" /></label>
					<aui:input name="wasMinister" type="toggle-switch" 
						value="${not empty dc.official ? dc.official.wasMinister : false}" helpMessage="was-minister-help" />
				</div>
			
			</aui:fieldset>

			<aui:fieldset collapsed="true" collapsible="true"
				label="contact-official">
					
				<aui:input name="contact"/>
						
			</aui:fieldset>
				
		</aui:fieldset-group>
		
		<aui:button-row>
		
			<c:if test="${(dc.hasPermission('ADD_OFFICIAL') and empty dc.official or dc.hasPermission('EDIT_OFFICIAL') and not empty dc.official) and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:input type="hidden" name="workflowAction" value="" />
				<c:if test="${dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" value="save" />
				</c:if>
				<c:if test="${not dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" name="publish"
							value="eu.publish" />
					<aui:button cssClass="btn-lg btn-default" type="submit" name="save-as-draft"
							value="save-as-draft" />
				</c:if>
			</c:if>
			
			<c:if test="${not empty dc.official and dc.hasPermission('DELETE_OFFICIAL') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "deleteEntity();"%>' type="cancel"
					value="delete" />
			</c:if>
			
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
			
		</aui:button-row>

	</aui:form>
				
</div>

<aui:script>
	function <portlet:namespace />deleteEntity() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			window.location = '${deleteOfficialURL}';
		}
	}
</aui:script>