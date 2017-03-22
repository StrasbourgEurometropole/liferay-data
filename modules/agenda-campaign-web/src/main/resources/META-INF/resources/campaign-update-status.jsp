<%@ include file="/campaign-init.jsp"%>

<liferay-portlet:renderURL varImpl="eventsCampaignURL">
</liferay-portlet:renderURL>
<liferay-portlet:actionURL name="updateStatus"
	varImpl="updateCampaignStatusURL">
</liferay-portlet:actionURL>

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${updateCampaignStatusURL}" method="post" name="fm">
		<aui:fieldset-group markupView="lexicon">
			<aui:input name="campaignEventId" type="hidden" value="${param.campaignEventId}" />
			<aui:input name="selectionIds" type="hidden" value="${param.selectionIds}" />
			<aui:input name="statusId" type="hidden" value="${param.statusId}" />
			<aui:input name="newStatus" type="hidden" value="${param.newStatus}" />
			<aui:fieldset collapsed="false" collapsible="true" label="status-change">
				<aui:input type="text" name="comment" />
			</aui:fieldset>

		</aui:fieldset-group>


		<aui:button-row>
			<aui:button cssClass="btn-lg" type="submit" name="submit" value="submit" />
			<aui:button cssClass="btn-lg" href="${eventsCampaignURL}" type="cancel" value="${empty param.statusId ? 'cancel' : 'no-comment'}" />
		</aui:button-row>
	</aui:form>
</div>