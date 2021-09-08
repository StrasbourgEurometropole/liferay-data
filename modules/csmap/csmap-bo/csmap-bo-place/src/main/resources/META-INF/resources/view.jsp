<%@ include file="/init.jsp" %>

<%@page import="eu.strasbourg.service.csmap.model.PlaceCategories"%>

<%-- URL : definit le lien menant vers la suppression de l'entite --%>

<%-- URL : definit le lien menant vers la sauvegarde de l'entite --%>
<liferay-portlet:actionURL name="savePlaceCategories" varImpl="savePlaceCategoriesURL">
	<portlet:param name="cmd" value="savePlaceCategories" />
	<portlet:param name="tab" value="placeCategories" />
</liferay-portlet:actionURL>


<%-- Composant : Body --%>
<div class="container-fluid-1280 main-content-body">

	<%-- Composant : formulaire de saisie de l'entite --%>
	<aui:form action="${savePlaceCategoriesURL}" method="post" name="fm" onSubmit="submitForm(event);">

		<%-- Propriete : definit l'entite de reference pour le formulaire--%>
		<aui:model-context bean="${dc.placeCategories}" model="<%=PlaceCategories.class %>" />
		<aui:fieldset-group markupView="lexicon">

            <%-- Champ : (cache) PK de l'entite --%>
            <aui:input name="placeCategoriesId" type="hidden" />

            <%-- Champ : CategoriesIds --%>
            <aui:fieldset collapsed="false" collapsible="false">
                <c:forEach var="vocabulary" items="${dc.placeVocabularies}" varStatus="status">
                    <label><span>${vocabulary.getTitle(locale)}</span></label>
                    <select
                        id="vocabulary${status.index}"
                        class="choices-element"
                        name="<portlet:namespace />vocabulary_${status.index}_select"
                        placeholder="<liferay-ui:message key="select-multiple" />"
                    multiple>
                        <c:forEach var="category" items="${vocabulary.categories}">
                            <option
                                value="${category.categoryId}"
                                <c:if test="${dc.verifId(category.categoryId)}">
                                    selected="selected"
                                </c:if>
                            >
                                ${category.getTitle(locale)}
                            </option>
                        </c:forEach>
                    </select>
                    <aui:input
                        type="hidden"
                        name="vocabulary_${status.index}_id"
                        value="${vocabulary.vocabularyId}"
                    />
                </c:forEach>
                <aui:input type="hidden" name="vocabulary_number" value="${dc.placeVocabularies.size()}"/>
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
	<link rel="stylesheet" href="/o/csmapboplace/css/vendors/choices.min.css">
	<!-- Include Choices JavaScript -->
    <script src="/o/csmapboplace/js/vendors/choices.min.js"
            type="text/javascript"></script>
	<script	src="/o/csmapboplace/js/vendors/jquery.autocomplete.js"
		type="text/javascript"></script>
	<script	src="/o/csmapboplace/js/main.js"
		type="text/javascript"></script>
</liferay-util:html-bottom>