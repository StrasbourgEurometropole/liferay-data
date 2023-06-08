<portlet:actionURL var="deleteComment" name="deleteComment">
	<portlet:param name="redirectURL" value="${redirectURL}"></portlet:param>
</portlet:actionURL>

<!-- SUPPRIMER UN COMMENTAIRE -->
<!-- HTML pour la modal de supprimer un commentaire-->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalSupprimer" tabindex="-1" role="dialog" aria-labelledby="modalSupprimer">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key='Supprimer un commentaire'/></h3>
                <button id="closingButton" type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>
            <div class="pro-wrapper">
                <h4><liferay-ui:message key='comment-are-you-sure'/></h4>
                <form id="form-delete" method="post" action="${deleteComment}">
                    <input type="hidden" id="commentId" name="<portlet:namespace />commentId"/>
                    <input type="submit" class="pro-btn" value="Supprimer" />
                </form>
            </div>
        </div>
    </div>
</div>