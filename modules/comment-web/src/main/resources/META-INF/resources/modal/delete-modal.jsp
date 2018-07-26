<portlet:actionURL var="reportComment" name="reportComment">
    <portlet:param name="mvcPath" value="/report-modal.jsp"></portlet:param>
    <portlet:param name="entryID" value="${entryID}"></portlet:param>
</portlet:actionURL>

<nav class="modal_connexion">
    <!-- HTML pour la modal de suppression -->
    <div class="pro-modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModal">
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
                                    <form id="form-delete" method="post" action="${deleteComment}"
                                        class="pro-user-connected">
                                        <h3>Attention</h3>
                                        <p>&Ecirc;tes-vous s&ucirc;r de vouloir supprimer votre message ?</p>
                                        <input type="submit" class="pro-btn-yellow" value="supprimer" />
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-header"/>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
</nav>