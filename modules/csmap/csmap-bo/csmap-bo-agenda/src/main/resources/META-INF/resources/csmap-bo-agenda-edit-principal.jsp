<%@ include file="/csmap-bo-agenda-init.jsp" %>

<%@page import="eu.strasbourg.service.csmap.model.Agenda"%>

<%-- URL : definit le lien menant vers la sauvegarde de l'entite --%>
<liferay-portlet:actionURL name="saveAgendaPrincipal" varImpl="saveAgendaPrincipalURL">
	<portlet:param name="cmd" value="saveAgendaPrincipal" />
	<portlet:param name="tab" value="agendaPrincipal" />
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
                            <c:if test="${fn:contains(dc.agendaPrincipal.campaignsIds,campaign.campaignId)}}">
                                selected="selected"
                            </c:if>
                        >
                            ${campaign.getTitle(locale)}
                        </option>
                    </c:forEach>
                </select>

                <liferay-ui:asset-categories-selector
                    className="${dc.className}"
                    hiddenInput="Vocabulary"
                    curCategoryIds="${dc.allCategoriesAgenda}" />

                <label><span>Tags</span></label>
                <liferay-ui:asset-tags-selector
                    hiddenInput="tags"
                    curTags="${dc.agendaPrincipal.tags}" />
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

	    #<portlet:namespace />assetCategoriesLabel_${dc.themeVocabularyId}{
	        display:block;
        }
	    #<portlet:namespace />sxvx___assetCategoriesSelector_${dc.themeVocabularyId}{
	        display:block;
        }

	    #<portlet:namespace />assetCategoriesLabel_${dc.typeVocabularyId}{
	        display:block;
        }
	    #<portlet:namespace />sxvx___assetCategoriesSelector_${dc.typeVocabularyId}{
	        display:block;
        }
	</style>

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