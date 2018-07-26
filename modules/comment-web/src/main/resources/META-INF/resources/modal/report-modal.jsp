<portlet:actionURL var="reportComment" name="reportComment">
	<portlet:param name="mvcPath" value="/report-modal.jsp"></portlet:param>
	<portlet:param name="entryID" value="${entryID}"></portlet:param>
</portlet:actionURL>

<nav class="modal_connexion">
    <!-- HTML pour la modal de signalement -->
    <div class="pro-modal fade" id="signalementModal" tabindex="-1" role="dialog" aria-labelledby="signalementModal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">
                            <span class="icon-ico-close"/>
                        </span>
                    </button>
                </div>
                <div class="modal-body">
                        <div class="col-sm-6">
                            <div class="pro-reagir">
                                <div>
                                    <h3>Type de signalement</h3>
                                    <p>Veuillez selectionner le type de signalement correspondant :</p>
                                    <c:set var="groupID" value="${themeDisplay.scopeGroupId}" />
                                    <form id="form-signalements" method="post" action="${reportComment}"
                                        class="pro-user-connected">
                                        <select name="<portlet:namespace />categorie">
                                            <c:forEach var="categorie" items="${categories}">
                                                <option value="${categorie.categoryId}">${categorie.name}</option>
                                            </c:forEach>
                                        </select>
                                        <input type="hidden" id="commentId" name="<portlet:namespace />commentId"/>
                                        <input type="submit" class="pro-btn-yellow" value="Signaler" />
                                    </form>
                                </div>
                            </div>
                        </div>
                    </#if>
                </div>
                <div class="modal-header"/>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</nav>