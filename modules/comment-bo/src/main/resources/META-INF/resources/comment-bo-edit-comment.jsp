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
                <aui:input name="userName" required="true" />

                <%-- Champ : adresse mail --%>
                <div class="form-group input-int-wrapper">
                    <label class="control-label"> email </label>
                    <input class="field form-control lfr-input-text" id="_eu_strasbourg_portlet_comment_CommentBOPortlet_email" name="_eu_strasbourg_portlet_comment_CommentBOPortlet_email"
                           style="" type="text" value="${dc.comment.publikUser.email}" maxlength="75" aria-describedby="">
                </div>

                <%-- Champ : date de création --%>
                <aui:input name="createDate" />

                <%-- Champ : date de modification --%>
                <aui:input name="modifiedDate"/>

                <%-- Champ : status --%>
                <aui:input name="status"/>

                <%-- Champ : lien vers la page du commentaire --%>
                <aui:input href="${comment.urlProjectCommentaire}" name="urlProjectCommentaire"/>

                <%-- Champ : liste des signalements --%>
                <aui:input name="status"/>

                <%-- Champ : Commentaire --%>
                <aui:input name="comment"/>

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
                    <aui:button cssClass="btn-lg" type="submit" name="publish" value="eu.publish" />
                    <aui:button cssClass="btn-lg btn-default" type="submit" name="save-as-draft" value="save-as-draft" />
                </c:if>
            </c:if>

            <%-- Test : Verification des droits de supression --%>
            <c:if test="${not empty dc.comment && dc.hasPermission('DELETE_COMMENT') and empty themeDisplay.scopeGroup.getStagingGroup()}">
                <aui:button cssClass="btn-lg" onClick='<%=renderResponse.getNamespace() + "deleteEntity();"%>' type="cancel" value="delete" />
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

<liferay-util:html-bottom>
    <script src="/o/commentbo/js/comment-bo-edit-comment.js" type="text/javascript"></script>
</liferay-util:html-bottom>

<%-- Script : permet l'affichage des alertes de validation d'action --%>
<aui:script>
    function <portlet:namespace />deleteEntity() {
    if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this-entry" />')) {
    window.location = '${deleteCommentURL}';
    }
    }
</aui:script>