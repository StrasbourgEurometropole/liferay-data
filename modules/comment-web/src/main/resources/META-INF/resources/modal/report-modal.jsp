<portlet:actionURL var="reportComment" name="reportComment">
	<portlet:param name="redirectURL" value="${redirectURL}"></portlet:param>
</portlet:actionURL>

<!-- SIGNALER LE COMMENTAIRE -->
<!-- HTML pour la modal de budget participatif -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalSignaler" tabindex="-1" role="dialog" aria-labelledby="modalSignaler">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
        
            <div class="pro-modal-top">
                <h3><liferay-ui:message key='comment-report-comment'/></h3>
                <button id="closingButton" type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>
            
            <form id="form-signalements" method="post" action="${reportComment}">
            
                <div class="pro-wrapper">
                    <h4><liferay-ui:message key='comment-select-report-reason'/></h4>
                    <div class="form-group">
                    	<c:set var="groupID" value="${themeDisplay.scopeGroupId}" />
                        <label for="signalement"><liferay-ui:message key='Motif de signalement'/></label>
                        <select name="<portlet:namespace />categorie">
                            <c:forEach var="categorie" items="${categories}">
                                <option value="${categorie.categoryId}">${categorie.name}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" id="commentId" name="<portlet:namespace />commentId"/>
                    </div>
                </div>
                
                <div class="pro-form-submit">
                    <button type="submit" class="btn btn-default">Signaler</button>
                </div>
                
            </form>
        </div>
    </div>
</div>