<%@ include file="/init.jsp" %>

<!-- Article -->
<strasbourg-picker:slider name="articleId" value="" />

<!-- Commentaire -->
<strasbourg-picker:entity label="comment" name="commentId"
    value=""
    type="eu.strasbourg.service.comment.model.Comment"
    multiple="false" />

<!-- Signalement -->
<strasbourg-picker:entity label="signalement" name="signalementId"
    value=""
    type="eu.strasbourg.service.comment.model.Signalement"
    multiple="false" />