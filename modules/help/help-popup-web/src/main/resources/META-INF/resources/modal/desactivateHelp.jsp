<%@ include file="/help-popup-init.jsp" %>

<portlet:resourceURL id="desactivateHelp" var="desactivateHelpURL">
</portlet:resourceURL>

<!-- DESACTIVER UNE AIDE -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalDesactivateHelp" tabindex="-1" role="dialog" aria-labelledby="modalDesactivateHelp"
	data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

        	<%-- Top titre du modal --%>
            <div class="pro-modal-top">
                <h3><liferay-ui:message key="modal.desactivate.help.title"/></h3>
                <button  type="button" class="close closefirstmodal" aria-label="Close">
                	<span aria-hidden="true"><span class="icon-multiply"></span></span>
                </button>
            </div>

			<%-- Formulaire --%>
            <aui:form name="uploadForm" enctype="multipart/form-data">

                <%-- Groupe de champs : Information utilisateur --%>
                <div class="pro-wrapper">
                    <label>
                        <liferay-ui:message key="modal.show.info.desactivate.help"/>
                    </label>

                    <%-- Champ cache : ID --%>
                    <aui:input type="hidden" name="entryId" value="${entryId}"/>
                </div>

                <div class="pro-form-submit">
                    <%-- Boutou de désactivation --%>
                    <button id="<portlet:namespace />buttonSubmit" class="btn btn-default">
                    	<liferay-ui:message key="yes"/>
                    </button>

                    <%-- Boutou de retour --%>
                    <button id="<portlet:namespace />buttonReset" class="btn btn-default">
                    	<liferay-ui:message key="no"/>
                    </button>
                </div>

            </aui:form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- CONFIRMATION NOUVELLE AIDE -->
<div class="pro-modal pro-bloc-pcs-form fade" id="<portlet:namespace />modalConfirm" tabindex="-1" role="dialog" aria-labelledby="<portlet:namespace />modalConfirm">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key='confirm-desactivated-help'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>
            <div class="pro-wrapper">
                <h4><liferay-ui:message key='desactivated-help-ok'/></h4>
                <div class="centerButtonValidation">
                    <input id="<portlet:namespace />buttonConfirm" type="submit" class="pro-btn" value=<liferay-ui:message key="button-submit-help-ok"/> />
                </div>
            </div>
        </div>
    </div>
</div>


<!-- ERREUR NOUVELLE AIDE -->
<div class="pro-modal pro-bloc-pcs-form fade" id="<portlet:namespace />modalError" tabindex="-1" role="dialog" aria-labelledby="<portlet:namespace />modalError">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key='error-desactivated-help'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                	<span aria-hidden="true"><span class="icon-multiply"></span></span>
                </button>
            </div>
            <div class="pro-wrapper">
                <h4></h4>
                <div class="centerButtonValidation">
                    <input id="<portlet:namespace />buttonConfirm" type="submit" class="pro-btn-yellow" value=<liferay-ui:message key="button-submit-help-ok"/> />
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Inclusion de la modal d'alerte d'une propostion d'aide désactivée -->
<jsp:include page="/include/inactive-help-proposal-modal.jsp"/>

<script type="text/javascript">
	
	/*
	* Lors du chargement de la page
	*/
    $(document).ready(function(){
        $("#<portlet:namespace />modalConfirm").modal('hide');
        $("#<portlet:namespace />modalError").modal('hide');
    });

	/*
	* Lors du click sur le bouton ok
	*/
    $("#<portlet:namespace />buttonSubmit").click(function(event){
        event.preventDefault();
        var request = new XMLHttpRequest();
        var formElement = $("#<portlet:namespace />uploadForm");
        request.open('POST', '${desactivateHelpURL}', true);

        request.onload = function() {
            if (this.status >= 200 && this.status < 400) {
                // Success!
                var data = JSON.parse(this.response);
                if(data.result){
                    $("#modalDesactivateHelp").modal('hide');
                    $("#<portlet:namespace />modalConfirm").modal('show');
                }else{
                    $("#<portlet:namespace />modalError h4").text(data.message);
                    $("#<portlet:namespace />modalError").modal('show');
                }
            } else {
                // We reached our target server, but it returned an error
            }
        };

        request.send(new FormData(formElement[0]));
    });

    $("#<portlet:namespace />buttonReset").click(function(event){
        event.preventDefault();
        $("#modalDesactivateHelp").modal('hide');
    });

    $("#<portlet:namespace />modalConfirm #<portlet:namespace />buttonConfirm").click(function(event){
        $("#<portlet:namespace />modalConfirm").modal('hide');
    });

    $("#<portlet:namespace />modalError #<portlet:namespace />buttonConfirm").click(function(event){
        $("#<portlet:namespace />modalError").modal('hide');
    });
</script>