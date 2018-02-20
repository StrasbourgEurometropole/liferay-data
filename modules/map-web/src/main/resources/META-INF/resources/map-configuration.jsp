<%@ include file="./map-init.jsp"%>

<liferay-portlet:actionURL portletConfiguration="true"
	var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="true"
	var="configurationRenderURL" />

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${configurationActionURL}" method="post" name="fm">

		<aui:input name="cmd" type="hidden" value="update" />
		
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
		
		<!-- Centres d'intérêts actifs -->
		<aui:fieldset collapsed="false" collapsible="true"
				label="interest-choice-filter">
			<div>
				<liferay-ui:message key="interest-choice-explanations"/>
				<aui:input type="hidden" name="interestsCount" value="${fn:length(interests)}" />
				
				<c:forEach var="interest" items="${interests}" varStatus="intStatus">
					<aui:input type="checkbox" name="interestId_${intStatus.index}" label="${interest.getTitle(locale)}" 
					value="${interest.interestId}" checked="${fn:contains(interestsIds, interest.interestId) || !hasConfig}"></aui:input>
				</c:forEach>
			</div>
		</aui:fieldset>
		
		
		<!-- Choix de l'affichage des favoris -->
		<div>
			<liferay-ui:message key="favorites-choice" />
			<aui:input type="checkbox" name="showFavorites" value="${showFavorites}" label="show-favorites" />
		</div>

		<!-- Centres d'intérêts config par défaut -->
		<aui:fieldset collapsed="false" collapsible="true"
				label="interest-choice-default">
			<div>
				<liferay-ui:message key="interest-choice-default-explanations" />
				<aui:input type="hidden" name="interestsDefaultCount" value="${fn:length(interests)}" />
				
				<c:forEach var="interest" items="${interests}" varStatus="intStatus">
					<aui:input type="checkbox" name="interestDefaultId_${intStatus.index}" label="${interest.getTitle(locale)}" 
					value="${interest.interestId}" checked="${fn:contains(interestsDefaultsIds, interest.interestId) || !hasConfig}"></aui:input>
				</c:forEach>
			</div>
		</aui:fieldset>

		<!-- config par défaut -->
		<div>
			<liferay-ui:message key="default-configuration-explanations" />
			<aui:input type="checkbox" name="defaultConfig" value="${defaultConfig}" label="default-configuration" />
		</div>

		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>