<%@ include file="/social-init.jsp"%>

<liferay-portlet:actionURL portletConfiguration="true"
	var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="true"
	var="configurationRenderURL" />
	
<liferay-portlet:resourceURL portletConfiguration="true" portletName="eu_strasbourg_portlet_legacy_place_LegacyPlaceViewerPortlet"
	var="placeAutocompleteURL">
</liferay-portlet:resourceURL>
	

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${configurationActionURL}" method="post" name="fm">
		<aui:input name="cmd" type="hidden"
			value="update" />
	
		<aui:input name="redirect" type="hidden"
			value="${configurationRenderURL}" />
	
		<aui:fieldset id="twitter" label="twitter">
			<aui:input type="text" name="twitterAccount" value="${twitterAccount}" label="twitter-account" />
		</aui:fieldset>
		
		<aui:fieldset id="instagram" label="instagram">
			<aui:input type="text" name="instagramAccount" value="${instagramAccount}" label="instagram-account" />
		</aui:fieldset>
		
		<aui:fieldset id="dailymotion" label="dailymotion">
			<aui:input type="text" name="dailymotionAccountId" value="${dailymotionAccountId}" label="dailymotion-account-id" />
		</aui:fieldset>
		
		<aui:fieldset id="facebook" label="facebook">
			<aui:input type="text" name="facebookToken" value="${facebookToken}" label="facebook-token" />
		</aui:fieldset>
		
		<aui:fieldset id="general" label="general">
			<aui:input type="number" name="postCount" value="${postCount}" label="post-count" />
		</aui:fieldset>
		
		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>