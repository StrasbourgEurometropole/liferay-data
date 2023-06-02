<%@ include file="/csmap-bo-agenda-init.jsp" %>

<%@page import="eu.strasbourg.service.csmap.model.Agenda"%>

<%-- URL : definit le lien menant vers la sauvegarde de l'entite --%>
<liferay-portlet:actionURL name="saveAgendaThematique" varImpl="saveAgendaThematiqueURL">
	<portlet:param name="cmd" value="saveAgendaThematique" />
	<portlet:param name="tab" value="agendaThematique" />
</liferay-portlet:actionURL>


<%-- Composant : Body --%>
<div class="container-fluid-1280 main-content-body">
	<liferay-ui:error key="title-error" message="title-error" />
	<liferay-ui:error key="editorial-title-error" message="editorial-title-error" />

	<%-- Composant : formulaire de saisie de l'entite --%>
	<aui:form action="${saveAgendaThematiqueURL}" method="post" name="fm" onSubmit="submitForm(event);">

		<%-- Propriete : definit l'entite de reference pour le formulaire--%>
		<aui:model-context bean="${dc.agendaThematique}" model="<%=Agenda.class %>" />
		<aui:fieldset-group markupView="lexicon">

            <%-- Champ : (cache) PK de l'entite --%>
            <aui:input name="agendaId" type="hidden" />

            <%-- Champ : (cache) Si active ou non --%>
            <aui:input name="isActive" type="hidden" />

			<!-- Informations générale -->
			<aui:fieldset collapsed="false" collapsible="true"
				label="general-information">

				<aui:input name="title" label="title">
					<aui:validator name="required"
						errorMessage="this-field-is-required" />
				</aui:input>

				<aui:input name="editorialTitle" label="editorialTitle">
					<aui:validator name="required"
						errorMessage="this-field-is-required" />
				</aui:input>

				<aui:input name="subtitle" label="subtitle">
				</aui:input>

				<strasbourg-picker:image label="image" name="imageId"
					required="false" value="${dc.agendaThematique.imageId}" global="true" />

				<aui:input name="labelLink" label="labelLink">
				</aui:input>

				<aui:input name="link" label="link">
				</aui:input>

                <aui:input name="publicationStartDate" required="false" />

                <aui:input name="publicationEndDate" required="false" />
            </aui:fieldset>

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
                            <c:if test="${fn:contains(dc.agendaThematique.campaignsIds,campaign.campaignId)}">
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
                    curTags="${dc.agendaThematique.tags}" />
            </aui:fieldset>

		</aui:fieldset-group>

		<%-- Composant : Menu de gestion de l'entite --%>
		<aui:button-row>

			<aui:input type="hidden" name="workflowAction" value="" />

			<%-- Test : Verification des droits d'edition et de sauvegarde --%>
			<aui:button cssClass="btn-lg" type="submit" name="save" value="save" />
			<aui:button cssClass="btn-lg" type="submit" name="save-and-active"
			value="save-and-active" />

		</aui:button-row>

	</aui:form>

</div>

<liferay-util:html-bottom>
	<aui:script>
		var namespace = '<portlet:namespace />';

		$('button[name=' + namespace + 'save-and-active]').on('click', function(){
            if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-activate-this-agenda" />')) {
			    $('input[name=' + namespace + 'isActive]').val(true);
            }
		});
	</aui:script>

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

	    #<portlet:namespace />assetCategoriesLabel_${dc.territoryVocabularyId}{
	        display:block;
        }
	    #<portlet:namespace />sxvx___assetCategoriesSelector_${dc.territoryVocabularyId}{
	        display:block;
        }

	    .lfr-input-time { display: none;}
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