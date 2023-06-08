<%@ include file="/entity-detail-init.jsp"%>

<strasbourg-picker:entity label="${classNameLabel}" name="classPK"
	value="${classPK}"
	type="${className}"
	multiple="false" />


<div class="display-template">
	<liferay-ddm:template-selector
		className="${className}"
		displayStyle="${displayStyle}"
		displayStyleGroupId="${displayStyleGroupId}"
		refreshURL="${refreshURL}"
		showEmptyOption="${true}"
	/>
</div>