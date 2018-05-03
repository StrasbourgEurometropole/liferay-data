<%@ include file="./map-init.jsp"%>

<liferay-portlet:actionURL portletConfiguration="true"
	var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="true"
	var="configurationRenderURL" />

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${configurationActionURL}" method="post" name="fm">

		<aui:input name="cmd" type="hidden" value="update" />
		
		<!-- Widget mod -->
		<div>
			<liferay-ui:message key="widget-mod-explanations" />
			<aui:input type="checkbox" name="widgetMod" value="${widgetMod}" label="widget-mod" />
		</div>
		
		<div>
			<aui:input type="text" name="widgetIntro" value="${widgetIntro}" label="widget-intro" />
		</div>
		
		<div>
			<aui:input type="text" name="widgetLink" value="${widgetLink}" label="widget-link" />
		</div>
		
		<!-- Choix du site pour la cible des lien -->
		<aui:select name="groupId" label="detail-target-site">
			<c:forEach var="site" items="${sites}">
				<aui:option value="${site.groupId}" selected="${site.groupId eq selectedGroupId}">${site.name}</aui:option>
			</c:forEach>
		</aui:select>

		<!-- Choix de l'ouverture d'un lien  -->
		<div>
			<liferay-ui:message key="redirection-link-choice" />
			<aui:input type="checkbox" name="openInNewTab" value="${openInNewTab}" label="new-tab" />
		</div>
		
		<!-- Type de contenu -->
		<aui:fieldset collapsed="true" collapsible="true"
				label="type-contenu">
			<aui:input type="checkbox" name="typeContenu" value="eu.strasbourg.service.place.model.Place" label="eu.places" 
				checked="${fn:contains(typesContenu, 'eu.strasbourg.service.place.model.Place') || !hasConfig}" ></aui:input>
			<aui:input type="checkbox" name="typeContenu" value="eu.strasbourg.service.agenda.model.Event" label="eu.events" 
				checked="${fn:contains(typesContenu, 'eu.strasbourg.service.agenda.model.Event') || !hasConfig}" ></aui:input>
		</aui:fieldset>
		
		<!-- Préfiltres -->
		<aui:fieldset collapsed="true" collapsible="true"
				label="prefilters">
			<div>
				<label><liferay-ui:message key="prefilter-categories" /></label>
				<p>
					<liferay-ui:asset-categories-selector
						hiddenInput="prefilterCategoriesIds"
						curCategoryIds="${prefilterCategoriesIds}" />
				</p><br>
			</div>
		</aui:fieldset>
		
		<!-- Filtres -->
		<aui:fieldset collapsed="true" collapsible="true"
				label="filters">
			<div>
				<label><liferay-ui:message key="filter-categories"/></label>
				<p>
					<liferay-ui:asset-categories-selector
						hiddenInput="categoriesIds"
						curCategoryIds="${categoriesIds}" />
				</p>
				<label><liferay-ui:message key="default-categories" /></label>
				<p>
					<liferay-ui:asset-categories-selector
						hiddenInput="categoriesDefaultsIds"
						curCategoryIds="${categoriesDefaultsIds}" />
				</p><br>
			</div>
		</aui:fieldset>
		
		<!-- Choix de l'affichage des favoris -->
		<div>
			<liferay-ui:message key="favorites-choice" />
			<aui:input type="checkbox" name="showFavorites" value="${showFavorites || !hasConfig}" label="show-favorites" />
		</div>
		
		<!-- Choix de l'affichage de la zone de configuration -->
		<div>
			<liferay-ui:message key="config-choice" />
			<aui:input type="checkbox" name="showConfig" value="${showConfig || !hasConfig}" label="show-config" />
		</div>
		
		<!-- Choix de l'affichage de la liste -->
		<div>
			<liferay-ui:message key="list-choice" />
			<aui:input type="checkbox" name="showList" value="${showList || !hasConfig}" label="show-list" />
		</div>
		
		<!-- config par défaut -->
		<div>
			<liferay-ui:message key="default-configuration-explanations" />
			<aui:input type="checkbox" name="defaultConfig" value="${defaultConfig}" label="default-configuration" />
		</div>
		
		<!-- MonStrabourg -->
		<aui:fieldset collapsed="true" collapsible="true"
				label="Mon Strasbourg">
			<div>
				<aui:input type="checkbox" name="districtUser" value="${districtUser}" label="district-user" />
			</div>
			<label><liferay-ui:message key="interest-choice"/></label>
			<br>
			<br>
			<div style="height: 500px; overflow-y: scroll; overflow-x: hidden;">
				<aui:input type="hidden" name="interestsCount" value="${fn:length(interests)}" />
				<table style="text-align: center; width: 100%;">
					<tr style="border-bottom: solid 3px;">
						<td width="100px"><liferay-ui:message key="interest-choice-explanations"/></td>
						<td width="100px"><liferay-ui:message key="interest-choice-default-explanations" /></td>
						<td></td>
					</tr>
					<c:forEach var="interest" items="${interests}" varStatus="intStatus">
						<c:if test="${intStatus.count % 2 == 0}">
							<tr>
						</c:if>
						<c:if test="${intStatus.count % 2 != 0}">
							<tr style="background-color: #f9f9f9">
						</c:if>
							<td style="padding-top: 10px">
								<aui:input type="checkbox" name="interestId_${intStatus.index}" value="${interest.interestId}" label="" 
									checked="${fn:contains(interestsIds, interest.interestId) || !hasConfig}" ></aui:input>
							</td>
							<td style="padding-top: 10px">
								<aui:input type="checkbox" name="interestDefaultId_${intStatus.index}" value="${interest.interestId}" label="" 
									checked="${fn:contains(interestsDefaultsIds, interest.interestId) || !hasConfig}" ></aui:input>
							</td>
							<td style="text-align: left">${interest.getTitle(locale)}</td>
						</tr>
						
					</c:forEach>
				</table>
			</div>
		</aui:fieldset>

		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>