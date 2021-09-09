<%@ include file="/csmap-bo-agenda-init.jsp" %>

<%@page import="eu.strasbourg.service.csmap.model.Agenda"%>

<%-- URL : definit le lien menant vers la sauvegarde de l'entite --%>
<liferay-portlet:actionURL name="saveAgendaPrincipal" varImpl="saveAgendaPrincipalURL">
	<portlet:param name="cmd" value="saveAgendaPrincipal" />
	<portlet:param name="tab" value="agenda" />
</liferay-portlet:actionURL>


<%-- Composant : Body --%>
<div class="container-fluid-1280 main-content-body">

	<%-- Composant : formulaire de saisie de l'entite --%>
	<aui:form action="${saveAgendaPrincipalURL}" method="post" name="fm" onSubmit="submitForm(event);">

		<%-- Propriete : definit l'entite de reference pour le formulaire--%>
		<aui:model-context bean="${dc.agendaPrincipal}" model="<%=Agenda.class %>" />
		<aui:fieldset-group markupView="lexicon">

            <%-- Champ : (cache) PK de l'entite --%>
            <aui:input name="agendaId" type="hidden" />

            <%-- Champ : CategoriesIds --%>
            <aui:fieldset collapsed="false" collapsible="false">
                <label><span>Liste de Campagnes</span></label>
                <select
                    id="campaigns"
                    class="choices-element"
                    name="<portlet:namespace />campaigns"
                    placeholder="<liferay-ui:message key="select-multiple" />"
                multiple>
                    <c:forEach var="campaign" items="${dc.campaings}">
                        <option
                            value="${campaign.campaignId}"
                            <c:if test="${dc.agendaPrincipalVerifId(campaign.campaignId)}">
                                selected="selected"
                            </c:if>
                        >
                            ${campaign.getTitle(locale)}
                        </option>
                    </c:forEach>
                </select>

                <c:forEach var="vocabulary" items="${dc.agendaThemes}" varStatus="status">
                    <label><span>${vocabulary.getTitle(locale)}</span></label>
                    <select
                        id="agendaThemes"
                        class="choices-element"
                        name="<portlet:namespace />agendaThemes"
                        placeholder="<liferay-ui:message key="select-multiple" />"
                    multiple>
                        <c:forEach var="category" items="${vocabulary.categories}">
                            <option
                                value="${category.categoryId}"
                                <c:if test="${dc.agendaPrincipalVerifId(category.categoryId)}">
                                    selected="selected"
                                </c:if>
                            >
                                ${category.getTitle(locale)}
                            </option>
                        </c:forEach>
                    </select>
                </c:forEach>

                <c:forEach var="vocabulary" items="${dc.agendaTypes}" varStatus="status">
                    <label><span>${vocabulary.getTitle(locale)}</span></label>
                    <select
                        id="agendaTypes"
                        class="choices-element"
                        name="<portlet:namespace />agendaTypes"
                        placeholder="<liferay-ui:message key="select-multiple" />"
                    multiple>
                        <c:forEach var="category" items="${vocabulary.categories}">
                            <option
                                value="${category.categoryId}"
                                <c:if test="${dc.agendaPrincipalVerifId(category.categoryId)}">
                                    selected="selected"
                                </c:if>
                            >
                                ${category.getTitle(locale)}
                            </option>
                        </c:forEach>
                    </select>
                </c:forEach>

                <label><span>Liste de Tags</span></label>
                <select
                    id="tags"
                    class="choices-element"
                    name="<portlet:namespace />tags"
                    placeholder="<liferay-ui:message key="select-multiple" />"
                multiple>
                    <c:forEach var="tag" items="${dc.assetTags}">
                        <option
                            value="${tag.tagId}"
                            <c:if test="${dc.agendaPrincipalVerifId(tag.tagId)}">
                                selected="selected"
                            </c:if>
                        >
                            ${tag.getName()}
                        </option>
                    </c:forEach>
                </select>
            </aui:fieldset>

		</aui:fieldset-group>

		<%-- Composant : Menu de gestion de l'entite --%>
		<aui:button-row>

			<aui:input type="hidden" name="workflowAction" value="" />

			<%-- Test : Verification des droits d'edition et de sauvegarde --%>
			<aui:button cssClass="btn-lg" type="submit" value="save" />

		</aui:button-row>

	</aui:form>

</div>

<liferay-util:html-bottom>
    <!-- Include Choices CSS -->
	<link rel="stylesheet" href="/o/csmapboagenda/css/vendors/choices.min.css">
	<!-- Include Choices JavaScript -->
    <script src="/o/csmapboagenda/js/vendors/choices.min.js"
            type="text/javascript"></script>
	<script	src="/o/csmapboagenda/js/vendors/jquery.autocomplete.js"
		type="text/javascript"></script>
	<script	src="/o/csmapboagenda/js/main.js"
		type="text/javascript"></script>
</liferay-util:html-bottom>