<%@page import="java.util.Date"%>
<%@ include file="/gtfs-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.gtfs.model.Arret"%>

<liferay-portlet:renderURL varImpl="arretsURL">
	<portlet:param name="tab" value="arrets" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="saveArret" varImpl="saveArretURL">
	<portlet:param name="cmd" value="saveArret" />
	<portlet:param name="tab" value="arrets" />
</liferay-portlet:actionURL>

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${saveArretURL}" method="post" name="fm">
		<aui:translation-manager availableLocales="${dc.availableLocales}"
			changeableDefaultLanguage="false" defaultLanguageId="${locale}"
			id="translationManager" />

		<aui:model-context bean="${dc.arret}" model="<%=Arret.class %>" />
		<aui:fieldset-group markupView="lexicon">
			<aui:input name="arretId" type="hidden" />

			<!-- Informations générale -->
			<aui:fieldset collapsed="false" collapsible="true"
				label="general-information">

                    <aui:input name="title" disabled="true" />

                    <aui:input name="code" disabled="true"/>
				
			</aui:fieldset>

			<!-- Lignes et directions -->
			<aui:fieldset collapsed="false" collapsible="true"
				label="lignes-directions">

                <c:forEach items="${dc.arret.directions}" var="direction" varStatus="status">
                    <div class="ligne-direction">
                        <p class="tram-destination-letter">
                            <span class="transport-letters-icon" style="background:#${direction.ligne.backgroundColor}; color:#${direction.ligne.textColor};">
                                ${direction.ligne.shortName}
                            </span>
                        </p>
                        <p class="tram-destination-name">
                            ${direction.destinationName}
                        </p>
                    </div>
				</c:forEach>

			</aui:fieldset>
			
			<!-- Categorisation -->
			<aui:fieldset collapsed="true" collapsible="true"
				label="categorization">
					
				<aui:input name="categories" type="assetCategories" wrapperCssClass="categories-selectors" />
				
				<!-- Hack pour ajouter une validation sur les vocabulaires obligatoires -->
				<div class="has-error">
					<aui:input type="hidden" name="assetCategoriesValidatorInputHelper" value="placeholder">
						<aui:validator name="custom" errorMessage="requested-vocabularies-error">
							function (val, fieldNode, ruleValue) {
								var validated = true;
								var fields = document.querySelectorAll('.categories-selectors > .field-content');
								for (var i = 0; i < fields.length; i++) {
									fieldContent = fields[i];
								    if ($(fieldContent).find('.icon-asterisk').length > 0
								    	&& $(fieldContent).find('input[type="hidden"]')[0].value.length == 0) {
								    	validated = false;
								    	break;
								    }
								}
								return validated;
							}
						</aui:validator>
					</aui:input>
				</div>

				<aui:input name="tags" type="assetTags" />

			</aui:fieldset>
				
			<!-- Alertes -->
			<aui:fieldset collapsed="false" collapsible="true"
				label="alerts">
				
                <div id="alert-fields">
                    <c:if test="${empty dc.arret.alerts}">
                        <div class="lfr-form-row lfr-form-row-inline">
                            <div class="row-fields">
                                <liferay-util:include page="/includes/alert-row.jsp" servletContext="<%=application %>">
                                    <liferay-util:param name="index" value="1" />
                                </liferay-util:include>
                            </div>
                        </div>
                    </c:if>

                    <c:forEach items="${dc.arret.alerts}" var="alert" varStatus="status">
                        <div class="lfr-form-row lfr-form-row-inline">
                            <div class="row-fields">
                                <fmt:formatDate value="${alert.startDate}" pattern="yyyy-MM-dd" type="date" var="formattedStartDate"/>
                                <fmt:formatDate value="${alert.endDate}" pattern="yyyy-MM-dd" type="date" var="formattedEndDate"/>
                                <liferay-util:include page="/includes/alert-row.jsp" servletContext="<%=application %>">
                                    <liferay-util:param name="index" value="${status.count}" />
                                    <liferay-util:param name="startDate" value="${formattedStartDate}" />
                                    <liferay-util:param name="endDate" value="${formattedEndDate}" />
                                    <liferay-util:param name="ligneAndDirection" value="${alert.ligneAndDirection}" />
                                    <liferay-util:param name="perturbation" value="${alert.perturbation}" />
                                </liferay-util:include>
                            </div>
                        </div>
                    </c:forEach>
                    <c:if test="${empty dc.arret.alerts}">
                        <aui:input type="hidden" name="alertsIndexes" value="1" />
                    </c:if>
                    <c:if test="${not empty dc.arret.alerts}">
                        <aui:input type="hidden" name="alertsIndexes" value="${dc.getDefaultIndexes(fn:length(dc.arret.alerts))}" />
                    </c:if>
                </div>
					
			</aui:fieldset>

		</aui:fieldset-group>

		<aui:button-row>
			<aui:input type="hidden" name="workflowAction" value="" />
			<c:if test="${(dc.hasPermission('ADD_ARRET') and empty dc.arret or dc.hasPermission('EDIT_ARRET') and not empty dc.arret) and empty themeDisplay.scopeGroup.getStagingGroup()}">
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
			<c:if test="${not empty dc.arret && dc.hasPermission('DELETE_ARRET') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" href="${deleteArretURL}"
					type="cancel" value="delete" />
			</c:if>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />
		</aui:button-row>

	</aui:form>
</div>

<liferay-util:html-top>
	<script>
	var getalertRowJSPURL = '${alertRowURL}';
	</script>
</liferay-util:html-top>
<liferay-util:html-bottom>
	<script
		src="/o/gtfsbo/js/gtfs-bo-edit-arret.js"
		type="text/javascript"></script>
</liferay-util:html-bottom>
