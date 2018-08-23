<%--
  Created by IntelliJ IDEA.
  User: alexandre.quere
  Date: 04/07/2018
  Time: 09:31
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/comment-bo-init.jsp"%>
<%@page import="eu.strasbourg.service.comment.model.Comment"%>

<%-- URL : definit le lien menant vers la page de listage de l'entite --%>
<liferay-portlet:renderURL varImpl="commentsURL">
    <portlet:param name="tab" value="comments"/>
</liferay-portlet:renderURL>


<%-- URL : definit le lien menant vers la suppression de l'entite --%>
<liferay-portlet:actionURL name="deleteComment" var="deleteCommentURL">
    <portlet:param name="cmd" value="deleteComment" />
    <portlet:param name="tab" value="Comments" />
    <portlet:param name="commentId" value="${not empty dc.comment ? dc.comment.commentId : ''}" />
</liferay-portlet:actionURL>

<%-- URL : definit le lien menant vers la sauvegarde de l'entite --%>
<liferay-portlet:actionURL name="saveComment" varImpl="saveCommentURL">
    <portlet:param name="cmd" value="saveComment" />
</liferay-portlet:actionURL>


<%-- Composant : Body --%>
<div class="container-fluid-1280 main-content-body">

    <%-- Composant : definit la liste des messages d'erreur 
    (voir methode "validate" dans le saveAction de l'entite) --%>
    <liferay-ui:error key="userName-error" message="userName-error" />
    <liferay-ui:error key="commentaire-error" message="commentaire-error" />
    <liferay-ui:error key="image-error" message="image-error" />

    <%-- Composant : formulaire de saisie de l'entite --%>
    <aui:form action="${saveCommentURL}" method="post" name="fm">

        <%-- Propriete : definit l'entite de reference pour le formulaire--%>
        <aui:model-context bean="${dc.comment}" model="<%=Comment.class %>" />
        <aui:fieldset-group markupView="lexicon">

            <%-- Champ : (cache) PK de l'entite --%>
            <aui:input name="commentId" type="hidden" />

            <%-- Groupe de champs : Generalites --%>
            <aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" label="general">

                <%-- Champ : Titre --%>
                <aui:input name="userName" disabled="true" value="userName"/>

                <%-- Champ : adresse mail --%>
                <div class="form-group input-int-wrapper">
                    <label class="control-label"> Email </label>
                    <input class="field disabled form-control lfr-input-text" disabled="disabled" id="_eu_strasbourg_portlet_comment_CommentBOPortlet_email" name="_eu_strasbourg_portlet_comment_CommentBOPortlet_email"
                           style="" type="text" value="${dc.comment.publikUser.email}" maxlength="75" aria-describedby="">
                </div>

                <%-- Champ : date de création --%>
                <aui:input name="createDate" disabled="true" />

                <%-- Champ : date de modification --%>
                <aui:input name="modifiedDate" disabled="true" />

                <%-- Champ : status --%>
                <aui:input name="status" label="approvedStatus" type="toggle-switch" value="${dc.comment.status==0?true:false}"/>

                <aui:input name="signalementCheckBox" type="checkbox" label="signalementCheckBoxTitle"/>

                <%-- Champ : lien vers la page du commentaire --%>
                <aui:button href="${dc.comment.urlProjectCommentaire}" value="link"/>

            </aui:fieldset>

             <%-- Champ : liste des signalements --%>
            <aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>" label="signalementList">

            <div class="container-fluid-1280 main-content-body">
            <aui:input type="hidden" name="selectionIds" />
                <liferay-ui:search-container id="commentsSearchContainer">
                    <liferay-ui:search-container-results results="${dc.comment.findSignalements()}" />

                        <liferay-ui:search-container-row
                        className="eu.strasbourg.service.comment.model.Signalement" modelVar="reporting"
                        keyProperty="reportingId" rowIdProperty="reportingId">

                            <%-- Colonne : userName --%>
                            <liferay-ui:search-container-column-text cssClass="content-column"
                            name="userName" truncate="true" orderable="true" value="${reporting.userName}" />

                            <%-- Colonne : Date de modification--%>
                            <fmt:formatDate value="${reporting.createDate}"
                                var="formattedCreatedDate" type="date" pattern="dd/MM/yyyy HH:mm" />
                            <liferay-ui:search-container-column-text cssClass="content-column"
                                name="reporting-date" truncate="true" orderable="true"
                                value="${formattedCreatedDate}" />

                            <%-- Colonne : Statut--%>
                            <liferay-ui:search-container-column-text name="status">
                                <aui:workflow-status markupView="lexicon" showIcon="false"
                                    showLabel="false" status="${reporting.status}" />
                            </liferay-ui:search-container-column-text>

                            <%-- Colonne : Type--%>
                            <liferay-ui:search-container-column-text cssClass="content-column"
                                name="Type" truncate="true" orderable="true" value="${reporting.getCategorieName()}" />

                        </liferay-ui:search-container-row>

                    <%-- Iterateur --%>
                    <liferay-ui:search-iterator paginate="true" displayStyle="list" markupView="lexicon" />

                </liferay-ui:search-container>
            </div>
            </aui:fieldset>

            <aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>" label="Contenu">
                <%-- Champ : Commentaire --%>
                <aui:input name="comment"/>
            </aui:fieldset>

			<%-- Groupe de champs : Bannissement --%>
			<aui:fieldset collapsed="<%=true%>" collapsible="<%=true%>" label="banishment">
				<%-- Champ : Date de bannissement--%>
                <div class="form-group input-Date-wrapper">
                    <label class="control-label" for="_eu_strasbourg_portlet_comment_CommentBOPortlet_createDate">Date de Bannissement</label>
                    <liferay-ui:input-date name="banishDate" cssClass="content-column" autoFocus="true" yearValue="${dc.year}" dayValue="${dc.day}" monthValue="${dc.month}"/>
                </div >

				<%-- Champ : Description / Motifs du bannissement --%>
                <div class="form-group input-Date-wrapper">
                    <label class="control-label">Description / Motifs du bannissement</label>
				    <liferay-ui:input-editor name="banishDescription" toolbarSet="liferay-article" initMethod="initEditor" width="200" contents="${dc.banishment}"/>
                </div >
			</aui:fieldset>

        </aui:fieldset-group>

        <%-- Composant : Menu de gestion de l'entite --%>
        <aui:button-row>

            <aui:input type="hidden" name="workflowAction" value="" />

            <%-- Test : Verification des droits d'edition et de sauvegarde --%>
            <c:if test="${(dc.hasPermission('ADD_COMMENT') and empty dc.comment or dc.hasPermission('EDIT_COMMENT') and not empty dc.comment) and empty themeDisplay.scopeGroup.getStagingGroup()}">
                <c:if test="${dc.workflowEnabled}">
                    <aui:button cssClass="btn-lg" type="submit" value="save" />
                </c:if>
                <c:if test="${not dc.workflowEnabled}">
                    <aui:button cssClass="btn-lg" type="submit" name="publish" value="save" />
                </c:if>
            </c:if>

            <%-- Composant : bouton de retour a la liste des entites --%>
            <aui:button cssClass="btn-lg" href="${param.returnURL}" type="cancel" />

        </aui:button-row>

    </aui:form>

    <%-- Variable : definit la phase de l'entite (utile pour l'action Jquery) --%>
    <liferay-util:html-top>
        <script>
            var editComment = true;
        </script>
    </liferay-util:html-top>

</div>

<%-- Script : permet l'affichage des alertes de validation d'action --%>
<aui:script>
    function <portlet:namespace />deleteEntity() {
    if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
    window.location = '${deleteCommentURL}';
    }
    }
</aui:script>