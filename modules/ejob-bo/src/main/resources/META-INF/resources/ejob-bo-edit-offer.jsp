<%@ include file="/ejob-bo-init.jsp"%>

<%@page import="eu.strasbourg.service.ejob.model.Offer"%>

<%-- URL : definit le lien menant vers la page de listage de l'entite --%>
<liferay-portlet:renderURL varImpl="offersURL">
	<portlet:param name="tab" value="offers" />
</liferay-portlet:renderURL>

<%-- URL : definit le lien menant vers la suppression de l'entite --%>
<liferay-portlet:actionURL name="deleteOffer" var="deleteOfferURL">
	<portlet:param name="cmd" value="deleteOffer" />
	<portlet:param name="tab" value="offers" />
	<portlet:param name="offerId"
	    value="${not empty dc.offer ? dc.offer.offerId : ''}" />
</liferay-portlet:actionURL>

<%-- URL : definit le lien menant vers la sauvegarde de l'entite --%>
<liferay-portlet:actionURL name="saveOffer" varImpl="saveOfferURL">
	<portlet:param name="cmd" value="saveOffer" />
	<portlet:param name="tab" value="offers" />
</liferay-portlet:actionURL>

<%-- Composant : Body --%>
<div class="container-fluid-1280 main-content-body council-bo">

	<%-- Composant : definit la liste des messages d'erreur  (voir methode "validate" dans le saveAction de l'entite) --%>
	<liferay-ui:error key="title-error" message="title-error" />
	<liferay-ui:error key="title-already-used-error" message="title-already-used-error" />
	<liferay-ui:error key="date-error" message="date-error" />
	<liferay-ui:error key="date-already-used-error" message="date-already-used-error" />

	<%-- Composant : definit la liste des messages d'erreur  (voir methode "doProcessAction" dans le deleteAction de l'entite) --%>
	<liferay-ui:error key="council-has-delib-error" message="council-has-delib-error" />

	<%-- Composant : formulaire de saisie de l'entite --%>
	<aui:form action="${saveOfferURL}" method="post" name="fm" onSubmit="submitForm(event);">

		<%-- Propriete : definit l'entite de reference pour le formulaire--%>
		<aui:model-context bean="${dc.offer}" model="<%=Offer.class %>" />
		<aui:fieldset-group markupView="lexicon">

			<%-- Champ : (cache) PK de l'entite --%>
			<aui:input name="offer-id" type="hidden" />

			<%-- Groupe de champs : Generalites --%>
			<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="general">

                <%-- Champ : Type de recrutement  --%>
                     <%-- Liste déroulante (Permanent – Non permanent- Stage – Vacataire – Saisonnier  + Apprentissage) attention si permanent voir champ en dessous affichage conditionnel --%>

                <%-- Champ : Filières   --%>
                    <aui:select cssClass="toCustomSelect" id="ejobFiliere" name="ejobFiliere" label="ejobFiliere">
                        <c:forEach items="${dc.filieres}" var="filiere">
                            <aui:option value="${filiere.categoryId}">${filiere.name}</aui:option>
                        </c:forEach>
                    </aui:select>

                <%-- Champ : Catégories   --%>
                    <aui:select cssClass="toCustomSelect" id="ejobCategorie" name="ejobCategorie" label="ejobCategorie">
                        <c:forEach items="${dc.categories}" var="categorie">
                            <aui:option value="${categorie.categoryId}">${categorie.name}</aui:option>
                        </c:forEach>
                    </aui:select>

                <%-- Champ : Choix du grade   --%>
                    <aui:select cssClass="toCustomSelect" id="ejobGrade" name="ejobGrade" label="ejobGrade">
                        <c:forEach items="${dc.grades}" var="grade">
                            <aui:option data-filiere-id="${grade.parentCategory.parentCategoryId}" data-categorie-id ="${grade.parentCategoryId}" value="${grade.categoryId}">${grade.name}</aui:option>
                        </c:forEach>
                    </aui:select>

                <%-- Champ : Numero de publication --%>
                    <aui:input name="publicationId" required="false" />

                <%-- Champ : Numero de poste --%>
                    <aui:input name="postNumber" required="false" />

                <%-- Champ : Nom de la personne remplacée ou création de poste --%>
                     <aui:input name="jobCreationDescription" required="false" />

                <%-- Champ : Date de départ  --%>
                     <aui:input name="startDate" required="false" />

                <%-- Champ : Motif  --%>
                     <aui:input name="motif" required="false" />

                    <%-- Champ : Si contrat permanent  --%>
                         <aui:input name="permanentDescription" required="false" />

                <%-- Champ : Durée du contrat  --%>
                     <aui:input name="duration" required="false" />

                <%-- Champ : Intitulé du poste  --%>
                     <aui:input name="post" required="true" />

                <%-- Champ : Temps complet ou non   --%>
                     <aui:input name="isFullTime" id="isFullTime" required="false"/>

                <%-- Champ : Si temps complet   --%>
                     <aui:input name="fullTimeDescription" id="fullTimeDescription" required="false" />

                <script type="text/javascript">
                     var isFullTime = document.getElementById("_eu_strasbourg_portlet_ejob_EjobBOPortlet_isFullTime");
                     var fullTimeDescription = document.getElementById("_eu_strasbourg_portlet_ejob_EjobBOPortlet_fullTimeDescriptionBoundingBox");
                     fullTimeDescription.parentNode.style.display='none';
                     isFullTime.click(function(){
                         if($(this).is(':checked')){
                             fullTimeDescription.parentNode.style.display='block';
                         } else {
                             fullTimeDescription.parentNode.style.display='none';
                         }
                     });

                     var filiere = document.getElementById("_eu_strasbourg_portlet_ejob_EjobBOPortlet_ejobFiliere");
                     var grades = document.getElementById("_eu_strasbourg_portlet_ejob_EjobBOPortlet_ejobGrade");

                     grades.children[0].attributes["data-categorie-id"]
                     Array.prototype.forEach.call(grades.children, function(child, i){
                         if(child.attributes["data-filiere-id"].value == filiere.value){
                             child.style.display="block";
                         }
                         else{
                             child.style.display="none";
                         }
                     });

                </script>

                <%-- Champ : Introduction --%>
                     <aui:input name="introduction" required="true" />

                <%-- Champ : Activités --%>
                     <aui:input name="activities" required="true" />

                <%-- Champ : Profil --%>
                     <aui:input name="profil" required="true" />

                <%-- Champ : Condition d'exercice --%>
                     <aui:input name="conditions" required="true" />

                <%-- Champ : Avantages liés au poste --%>
                     <aui:input name="avantages" required="true" />

                <%-- Champ : Date limite de candidatures --%>
                     <aui:input name="limitDate" required="true" />

                <%-- Champ : Nom du RRH + contact téléphonique --%>
                     <aui:input name="emails" required="true" />

                <%-- Champ : Champ partage Linkedin  --%>
                     <aui:input name="shareLinkedin" required="true" />

                <%-- Champ : Export vers TOTEM --%>
                     <aui:input name="exportTotem" required="false" />

                <%-- Champ : Date de publication d’une offre ( programmation) --%>
                     <aui:input name="publicationDate" required="false" />

            </aui:fieldset>

        <%-- Groupe de champs : Categorisation --%>
        <aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="categorisation">
            <%-- Champ : Selection des categories (gere par le portail dans l'onglet "Categories" du BO) --%>
                <c:choose>
                    <c:when test="${empty defaultAssetCategoryIds}">
                        <aui:input name="categories" type="assetCategories" wrapperCssClass="categories-selectors" />
                        <!-- Hack pour ajouter une validation sur les vocabulaires obligatoires -->
                        <div class="has-error">
                            <aui:input type="hidden" name="assetCategoriesValidatorInputHelper" value="placeholder">
                                <aui:validator name="custom" errorMessage="requested-vocabularies-error">
                                    function (val, fieldNode, ruleValue) {
                                        var validated = true;
                                        var fields = document.querySelectorAll('.categories-selectors > .field-content');
                                        alert(fields.length);
                                        for (var i = 0; i < fields.length; i++) {
                                            fieldContent = fields[i];alert('toto');
                                            if ($(fieldContent).find('.icon-asterisk').length > 0
                                                && $(fieldContent).find('input[type="hidden"]')[0].value.length == 0) {
                                                validated = false;
                                                event.preventDefault();
                                                break;
                                            }
                                        }
                                        return validated;
                                    }
                                </aui:validator>
                            </aui:input>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="vocabularies">
                            <liferay-ui:asset-categories-selector
                                    className="<%= Offer.class.getName() %>"
                                    curCategoryIds="${defaultAssetCategoryIds}"
                            />
                            <!-- Hack pour ajouter une validation sur les vocabulaires obligatoires -->
                            <div class="has-error">
                                <aui:input type="hidden" name="assetCategoriesValidatorInputHelper" value="placeholder">
                                    <aui:validator name="custom" errorMessage="requested-vocabularies-error">
                                        function (val, fieldNode, ruleValue) {
                                            var validated = true;
                                            var fields = document.querySelectorAll('.vocabularies > .field-content');
                                            for (var i = 0; i < fields.length; i++) {
                                                fieldContent = fields[i];
                                                if ($(fieldContent).find('.icon-asterisk').length > 0
                                                    && $(fieldContent).find('input[type="hidden"]')[0].value.length == 0) {
                                                    validated = false;
                                                    event.preventDefault();
                                                    break;
                                                }
                                            }
                                            return validated;
                                        }
                                    </aui:validator>
                                </aui:input>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>

            <%-- Champ : Selection des etiquettes (gere par le portail dans l'onglet "Etiquettes" du BO) --%>
                <aui:input name="tags" type="assetTags" />

            <%-- Champ : Direction  --%>
                 <%-- Liste déroulante  gérée par des catégories créees en BO --%>

            <%-- Champ : Service  --%>
                 <%-- Liste déroulante  gérée par des catégories créees en BO --%>

            <%-- Champ : Niveau d'étude   --%>
                 <%-- Liste à choix à définir --%>

            <%-- Champ : Famille de métiers --%>
                 <%-- Liste déroulante  gérée par des catégories créees en BO --%>

            <%-- Champ : Nom du RE + contact téléphonique  --%>
                 <aui:input name="contact" required="false" />

        </aui:fieldset>



		</aui:fieldset-group>

		<%-- Composant : Menu de gestion de l'entite --%>
		<aui:button-row>

			<aui:input type="hidden" name="workflowAction" value="" />

			<%-- Test : Verification des droits d'edition et de sauvegarde --%>
			<c:if test="${(dc.hasPermission('ADD_OFFER') and empty dc.offer or dc.hasPermission('EDIT_OFFER') and not empty dc.offer) and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<c:if test="${dc.workflowEnabled}">
					<aui:button cssClass="btn-lg" type="submit" value="save" />
				</c:if>
                <c:if test="${not dc.workflowEnabled}">
                    <aui:button cssClass="btn-lg" type="submit" name="publish" value="save" />
                </c:if>
			</c:if>

			<%-- Test : Verification des droits de supression --%>
			<c:if test="${not empty dc.offer && dc.hasPermission('DELETE_OFFER') and empty themeDisplay.scopeGroup.getStagingGroup()}">
				<aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "deleteEntity();"%>' type="cancel" value="delete" />
			</c:if>

			<%-- Composant : bouton de retour a la liste des entites --%>
			<aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />

		</aui:button-row>

	</aui:form>

</div>

<liferay-util:html-top>
	<script>
		var editOffer = true;
		var currentGroupId = ${dc.groupId}
	</script>
</liferay-util:html-top>

<liferay-util:html-bottom>
	<script src="/o/councilbo/js/council-bo-edit-council-session.js" type="text/javascript"></script>
</liferay-util:html-bottom>

<%-- Script : permet l'affichage des alertes de validation d'action --%>
<aui:script>
	function <portlet:namespace />deleteEntity() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
			window.location = '${deleteOfferURL}';
		}
	}
</aui:script>