<%@ include file="/council-bo-init.jsp" %>

<liferay-portlet:renderURL varImpl="deliberationsURL">
	<portlet:param name="tab" value="deliberations" />
	<portlet:param name="orderByCol" value="${dc.orderByCol}" />
	<portlet:param name="orderByType" value="${dc.orderByType}" />
	<portlet:param name="filterCategoriesIds" value="${dc.filterCategoriesIds}" />
	<portlet:param name="keywords" value="${dc.keywords}" />
	<portlet:param name="delta" value="${dc.searchContainer.delta}" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL varImpl="addDeliberationURL">
	<portlet:param name="cmd" value="editDeliberation" />
	<portlet:param name="mvcPath" value="/council-bo-edit-deliberation.jsp" />
	<portlet:param name="returnURL" value="${deliberationsURL}" />
</liferay-portlet:renderURL>


<liferay-frontend:management-bar includeCheckBox="true" searchContainerId="deliberationsSearchContainer">
		<liferay-frontend:management-bar-filters>
			<liferay-frontend:management-bar-sort orderByCol="${dc.orderByCol}"
				orderByType="${dc.orderByType}"
				orderColumns='<%= new String[] {"title", "modified-date", "publication-date", "status"} %>'
				portletURL="${deliberationsURL}" />
		</liferay-frontend:management-bar-filters>
</liferay-frontend:management-bar>


<div class="container-fluid-1280 main-content-body">
	<aui:form method="post" name="fm">
		<aui:input type="hidden" name="selectionIds" />
		<liferay-ui:search-container id="deliberationsSearchContainer"
			searchContainer="${dc.searchContainer}">
			<liferay-ui:search-container-results results="${dc.deliberations}" />

			<liferay-ui:search-container-row
				className="eu.strasbourg.service.council.model.Deliberation"
				modelVar="deliberation" keyProperty="deliberationId" rowIdProperty="deliberationId">
				<liferay-portlet:renderURL varImpl="editDeliberationURL">
					<portlet:param name="cmd" value="editDeliberation" />
					<portlet:param name="sessionId" value="${deliberation.deliberationId}" />
					<portlet:param name="returnURL" value="${deliberationsURL}" />
					<portlet:param name="mvcPath" value="/council-bo-edit-deliberation.jsp" />
				</liferay-portlet:renderURL>

                <liferay-ui:search-container-column-text cssClass="content-column"
                name="order" truncate="true"
                orderable="true" value="${deliberation.order}" />

				<liferay-ui:search-container-column-text cssClass="content-column"
					href="${editDeliberationURL}" name="title" truncate="true"
					orderable="true" value="${deliberation.title}" />

                <liferay-ui:search-container-column-text cssClass="content-column"
                    name="councilSession" truncate="true"
                    orderable="true" value="${deliberation.councilSession.title}" />

                <liferay-ui:search-container-column-text cssClass="content-column"
                    name="stage" truncate="true"
                    orderable="true" value="${deliberation.stage}" />

				<liferay-ui:search-container-column-text>
					<liferay-ui:icon-menu markupView="lexicon">
						<c:if test="${dc.hasPermission('EDIT_DELIBERATION') and empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="edit" url="${editDeliberationURL}" />
						</c:if>

						<liferay-portlet:actionURL name="examinateDeliberation"
							var="examinateDeliberationURL">
							<portlet:param name="cmd" value="stageChangeDeliberation" />
							<portlet:param name="tab" value="deliberations" />
							<portlet:param name="deliberationId" value="${deliberation.deliberationId}" />
							<portlet:param name="stage" value="${StageDeliberation.get(2).name}" />
						</liferay-portlet:actionURL>
						<c:if test="${empty themeDisplay.scopeGroup.getStagingGroup()}">
							<liferay-ui:icon message="examinate" url="${examinateDeliberationURL}" />
						</c:if>

						<liferay-portlet:actionURL name="openDeliberation"
                            var="openDeliberationURL">
                            <portlet:param name="cmd" value="stageChangeDeliberation" />
                            <portlet:param name="tab" value="deliberations" />
                            <portlet:param name="deliberationId" value="${deliberation.deliberationId}" />
                            <portlet:param name="stage" value="${StageDeliberation.get(3).name}" />
                        </liferay-portlet:actionURL>
                        <c:if test="${empty themeDisplay.scopeGroup.getStagingGroup()}">
                            <liferay-ui:icon message="open" url="${openDeliberationURL}" />
                        </c:if>

						<liferay-portlet:actionURL name="closeDeliberation"
                            var="closeDeliberationURL">
                            <portlet:param name="cmd" value="closeDeliberation" />
                            <portlet:param name="tab" value="deliberations" />
                            <portlet:param name="deliberationId" value="${deliberation.deliberationId}" />
                        </liferay-portlet:actionURL>
                        <c:if test="${empty themeDisplay.scopeGroup.getStagingGroup()}">
                            <liferay-ui:icon message="close" url="${closeDeliberationURL}" />
                        </c:if>

						<liferay-portlet:actionURL name="adoptDeliberation"
                            var="adoptDeliberationURL">
                            <portlet:param name="cmd" value="stageChangeDeliberation" />
                            <portlet:param name="tab" value="deliberations" />
                            <portlet:param name="deliberationId" value="${deliberation.deliberationId}" />
                            <portlet:param name="stage" value="${StageDeliberation.get(4).name}" />
                        </liferay-portlet:actionURL>
                        <c:if test="${empty themeDisplay.scopeGroup.getStagingGroup()}">
                            <liferay-ui:icon message="adopt" url="${adoptDeliberationURL}" />
                        </c:if>

						<liferay-portlet:actionURL name="rejectDeliberation"
                            var="rejectDeliberationURL">
                            <portlet:param name="cmd" value="stageChangeDeliberation" />
                            <portlet:param name="tab" value="deliberations" />
                            <portlet:param name="deliberationId" value="${deliberation.deliberationId}" />
                            <portlet:param name="stage" value="${StageDeliberation.get(5).name}" />
                        </liferay-portlet:actionURL>
                        <c:if test="${empty themeDisplay.scopeGroup.getStagingGroup()}">
                            <liferay-ui:icon message="reject" url="${rejectDeliberationURL}" />
                        </c:if>

						<liferay-portlet:actionURL name="communicateDeliberation"
                            var="communicateDeliberationURL">
                            <portlet:param name="cmd" value="stageChangeDeliberation" />
                            <portlet:param name="tab" value="deliberations" />
                            <portlet:param name="deliberationId" value="${deliberation.deliberationId}" />
                            <portlet:param name="stage" value="${StageDeliberation.get(6).name}" />
                        </liferay-portlet:actionURL>
                        <c:if test="${empty themeDisplay.scopeGroup.getStagingGroup()}">
                            <liferay-ui:icon message="communicate" url="${communicateDeliberationURL}" />
                        </c:if>

						<liferay-portlet:actionURL name="pullOutDeliberation"
                            var="pullOutDeliberationURL">
                            <portlet:param name="cmd" value="stageChangeDeliberation" />
                            <portlet:param name="tab" value="deliberations" />
                            <portlet:param name="deliberationId" value="${deliberation.deliberationId}" />
                            <portlet:param name="stage" value="${StageDeliberation.get(7).name}" />
                        </liferay-portlet:actionURL>
                        <c:if test="${empty themeDisplay.scopeGroup.getStagingGroup()}">
                            <liferay-ui:icon message="pull-out" url="${pullOutDeliberationURL}" />
                        </c:if>
					</liferay-ui:icon-menu>
				</liferay-ui:search-container-column-text>

			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="true" displayStyle="list"
				markupView="lexicon" searchContainer="${dc.searchContainer}" />
		</liferay-ui:search-container>
	</aui:form>
</div>

<c:if test="${dc.hasPermission('ADD_DELIBERATION') and empty themeDisplay.scopeGroup.getStagingGroup()}">
	<liferay-frontend:add-menu>
		<liferay-frontend:add-menu-item title="Ajouter une deliberation" url="${addDeliberationURL}" />
	</liferay-frontend:add-menu>
</c:if>
