<%@ include file="/campaign-init.jsp"%>

<liferay-portlet:renderURL varImpl="eventsCampaignURL">
</liferay-portlet:renderURL>
<liferay-portlet:actionURL name="updateStatus"
	varImpl="updateCampaignStatusURL">
	<liferay-portlet:param name="statusFilterId"
		value="${param.statusFilterId}" />
	<liferay-portlet:param name="themeId"
		value="${param.themeId}" />
</liferay-portlet:actionURL>

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${updateCampaignStatusURL}" method="post" name="fm">
		<aui:fieldset-group markupView="lexicon">
			<aui:input name="campaignEventId" type="hidden" value="${param.campaignEventId}" />
			<aui:input name="selectionIds" type="hidden" value="${param.selectionIds}" />
			<aui:input name="statusId" type="hidden" value="${param.statusId}" />
			<aui:input name="newStatus" type="hidden" value="${param.newStatus}" />
			<aui:fieldset collapsed="false" collapsible="false" label="status-change-${param.newStatus}">
				<aui:input type="text" name="comment" label="status-change-detail-${param.newStatus}" required="${param.newStatus eq 4 or param.newStatus eq 8}" />
			</aui:fieldset>
		</aui:fieldset-group>

		<aui:button-row>
			<aui:button cssClass="btn-lg" type="submit" name="submit" value="submit" />
			<c:if test="${empty param.statusId}">
				<aui:button cssClass="btn-lg" href="${eventsCampaignURL}" type="cancel" value="cancel" />
			</c:if>
		</aui:button-row>
	</aui:form>
</div>