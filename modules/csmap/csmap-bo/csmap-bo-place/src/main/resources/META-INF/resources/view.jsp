<%@ include file="/init.jsp" %>

<%@page import="eu.strasbourg.service.csmap.model.PlaceCategories"%>

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

                <liferay-ui:asset-categories-selector
                    className="${dc.className}"
                    hiddenInput="Vocabulary"
                    curCategoryIds="${dc.placeCategories.getCategoriesIds()}" />

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

	<style>
	    label[id^='<portlet:namespace />assetCategoriesLabel_']{
	        display:none;
        }
	    div[id^='<portlet:namespace />sxvx___assetCategoriesSelector_']{
	        display:none;
	        margin-bottom: 30px;
        }

        label[id^='<portlet:namespace />assetCategoriesLabel_'] .icon-asterisk {
            display: none;
        }

	    #<portlet:namespace />assetCategoriesLabel_${dc.typeVocabularyId}{
	        display:block;
        }
	    #<portlet:namespace />sxvx___assetCategoriesSelector_${dc.typeVocabularyId}{
	        display:block;
        }
	</style>

	<script	src="/o/csmapboplace/js/main.js" type="text/javascript"></script>
</liferay-util:html-bottom>