<%@ include file="/project-popup-init.jsp" %>

<!-- DEPOSER UNE NOUVELLE PETITION DESACTIVEE -->
<!-- HTML pour la modal de petition désavtivée -->
<div class="pro-modal fade" id="modalPetition" tabindex="-1" role="dialog" aria-labelledby="modalPetition"
	data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="pro-reagir">
                    <div>
                        <h3><liferay-ui:message key="modal.disable"/></h3>
                        <input id="buttonConfirm" type="submit" class="pro-btn-yellow" value=<liferay-ui:message key="button-petition-ok"/> />
                    </div>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script type="text/javascript">

    $('#modalPetition #buttonConfirm').click(function(event){
        $('#modalPetition').modal('hide');
    });
</script>