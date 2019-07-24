<%@ include file="/activity-bo-init.jsp"%>

<div id="activity-${param.index}">
	<aui:input name="associationActivityId${param.index}" value="${param.associationActivityId}" type="hidden" />
    <!-- Catégories -->
    <liferay-ui:asset-categories-selector
        hiddenInput="activityId-${param.index}"
        curCategoryIds="${param.categoriesIds}" className="eu.strasbourg.service.activity.model.AssociationActivity"/>
</div>

<c:if test="${not empty fromAjax}">
	<aui:script>
		$('#activity-fields').trigger('activityCreated', ${param.index});
	</aui:script>
</c:if>
