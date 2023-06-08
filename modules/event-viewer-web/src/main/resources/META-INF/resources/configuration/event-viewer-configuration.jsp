<%@ include file="/event-viewer-init.jsp"%>

<liferay-portlet:actionURL portletConfiguration="true"
	var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="true"
	var="configurationRenderURL" />

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${configurationActionURL}" method="post" name="fm">

		<aui:input name="cmd" type="hidden" value="update" />

		<aui:input name="redirect" type="hidden"
			value="${configurationRenderURL}" />

		<!-- Catégories et tags -->
		<h3>
			<liferay-ui:message key="categories-and-tags" />
		</h3>
		<p>
			<liferay-ui:message key="categories-explanations" />
		</p>
		<label><liferay-ui:message key="categories" /></label>
		<p>
			<liferay-ui:asset-categories-selector hiddenInput="categoriesIds"
				curCategoryIds="${categoriesIds}" />
		</p>
		<br>
		<p>
			<liferay-ui:message key="tags-explanations" />
		</p>
		<p>
			<label><liferay-ui:message key="tags" /></label>
			<liferay-ui:asset-tags-selector hiddenInput="tagsNames"
				curTags="${tagsNames}" />
		</p>

		<h3>
			<liferay-ui:message key="dates" />
		</h3>
		<p>
			<liferay-ui:message key="dates-explanations" />
		</p>
		<div class="date-selection">
			<!-- Date de début -->
			<div class="row">
				<div class="col-md-3">
					<p>
						<label><liferay-ui:message key="from-date" /></label>
						<liferay-ui:input-date name="fromDate" nullable="true"
							cssClass="date-selector" dayParam="fromDay" dayValue="${fromDay}"
							monthParam="fromMonth" monthValue="${fromMonth - 1}"
							yearParam="fromYear" yearValue="${fromYear}" disabled="${not hasDates}" />
					</p>
				</div>
			</div>

			<!-- Date de fin -->
			<div class="row">
				<div class="col-md-3">
					<p>
						<label><liferay-ui:message key="to-date" /></label>
						<liferay-ui:input-date name="toDate" nullable="true"
							cssClass="date-selector" dayParam="toDay" dayValue="${toDay}"
							monthParam="toMonth" monthValue="${toMonth - 1}"
							yearParam="toYear" yearValue="${toYear}" disabled="${not hasDates}"/>
					</p>
				</div>
			</div>
		</div>

		<!-- Template -->
		<h3>
			<liferay-ui:message key="display" />
		</h3>
		<p>
			<div class="display-template">
				<liferay-ddm:template-selector className="${className}"
					displayStyle="${displayStyle}"
					displayStyleGroupId="${displayStyleGroupId}"
					refreshURL="${refreshURL}" showEmptyOption="true" />
			</div>
		</p>
		<p>
			<p>
				<liferay-ui:message key="agenda-page-explanations" />
			</p>
			<div class="agenda-page">
				<strasbourg-picker:layout name="agendaPageUuid" label="page" multiple="false" required="false" value="${agendaPageUuid}" />
			</div>
			
			<p>
				<liferay-ui:message key="link-text-explanations" />
			</p>
			<aui:input type="text" name="agendaURLText" value="${agendaURLText}" label="link-text" />
		</p>
		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>

<style>
form h3 {
	margin-top: 20px;
	margin-bottom: 10px;
}
</style>
<script>
	$('.date-selection [type=checkbox]').on('change', function() {
		$('.date-selection [type=checkbox]').prop('checked', this.checked);
		$('.date-selection [type=text], .date-selection [type=hidden]').prop('disabled', this.checked);
	});
</script>