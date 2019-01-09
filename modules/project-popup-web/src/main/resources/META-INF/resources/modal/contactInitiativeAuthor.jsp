<%@ include file="/project-popup-init.jsp" %>

<portlet:resourceURL id="ContactInitiativeAuthor" var="ContactInitiativeAuthorURL">
</portlet:resourceURL>

<!-- MODAL CONTACTER LE PORTEUR -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalInitiativeContact" data-backdrop="static" data-keyboard="false"
	tabindex="-1" role="dialog" aria-labelledby="modalInitiativeContact">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="pro-modal-top">
				<h3><liferay-ui:message key="modal.initiative.contact.author.title"/></h3>
				<button type="button" class="close closefirstmodal"
					aria-label="Close">
					<span aria-hidden="true"><span class="icon-multiply"></span></span>
				</button>
			</div>
			<form>
				<div class="pro-wrapper">
					<div class="pro-row">
						<div class="form-group form-half">
							<aui:input name="username" disabled="true" label="modal.user.username" required="true" value="${userConnected.get('last_name')}"/>
						</div>
						<div class="form-group form-half">
							<aui:input name="firstname" disabled="true" label="modal.user.firstname" required="true" value="${userConnected.get('first_name')}"/>
						</div>
					</div>
					<div class="pro-row">
						<div class="form-group form-half">
							<aui:input type="email" name="mail" disabled="true" label="modal.user.mail" required="true" value="${userConnected.get('email')}"/>
						</div>
						<div class="form-group form-half">
							<aui:input id="mobile" name="mobile" label="modal.user.mobile" placeholder="0611111111" maxlength="20"/>
						</div>
					</div>
					<div class="form-group">
						<aui:input id="initiativeContactAuthorSubject" name="initiativeContactAuthorSubject" required="true" 
	                        	label="modal.initiative.contact.author.subject" value=""
	                     />
					</div>
					<div class="form-group">
						<aui:input id="initiativeContactAuthorMessage" name="initiativeContactAuthorMessage" required="true" 
	                        	label="modal.initiative.contact.author.message" value="" type="textarea"
	                     />
					</div>
				</div>
				 <%-- Champ : Demande de comprehension de l'utilisation des donnees --%>
                <div class="pro-optin form-checkbox" >
                    <div>
                        <input type="checkbox" id="<portlet:namespace />cnil" value="cnil">
                        <label for="<portlet:namespace />cnil" class="fontWhite">
                            <liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_cnilInitiative"/>
                        </label>
                    </div>
                </div>
                <%-- Champ cache : ID --%>
                <input type="hidden" id="<portlet:namespace />entryId" name="entryId" value="${entryId}"/>
                <div id="<portlet:namespace />alert" class="hidden pro-info-supp alertMessage"><liferay-ui:message key="modal.alert"/></div>
				<div class="pro-form-submit">
					<button id="<portlet:namespace />submitContactButton" type="submit" class="btn btn-default"><liferay-ui:message key="modal.initiative.contact.author.submit.button"/></button>
				</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<!-- CONFIRMATION MESSAGE ENVOYE -->
<div class="pro-modal pro-bloc-pcs-form fade" id="<portlet:namespace />modalConfirm" tabindex="-1" role="dialog" aria-labelledby="<portlet:namespace />modalConfirm">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key='modal.initiative.contact.author.confirm'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>
            <div class="pro-wrapper">
                <h4><liferay-ui:message key='modal.initiative.contact.author.confirm.message'/></h4>
                <div class="centerButtonValidation">
                    <input id="<portlet:namespace />buttonConfirm" type="submit" class="pro-btn" value=<liferay-ui:message key="modal.initiative.contact.author.confirm.ok"/> />
                </div>
            </div>
        </div>
    </div>
</div>


<!-- ERREUR MESSAGE ENVOYE -->
<div class="pro-modal pro-bloc-pcs-form fade" id="<portlet:namespace />modalError" tabindex="-1" role="dialog" aria-labelledby="<portlet:namespace />modalError">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key='modal.initiative.contact.author.error.title'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                	<span aria-hidden="true"><span class="icon-multiply"></span></span>
                </button>
            </div>
            <div class="pro-wrapper">
                <h4></h4>
                <div class="centerButtonValidation">
                    <input id="<portlet:namespace />buttonConfirm" type="submit" class="pro-btn-yellow" value=<liferay-ui:message key="modal.initiative.contact.author.error.ok"/> />
                </div>
            </div>
        </div>
    </div>
</div>

<script>

var namespaceContactAuthor = "<portlet:namespace />";
var mobile = "${userConnected.get('mobile')}" != 'null' ? "${userConnected.get('mobile')}" : "";

$(document).ready(function () {
	$("#"+namespaceContactAuthor+"mobile").val(mobile);
});

$('#buttonContactInitiativeAuthor').click(function(event){
    contactAuthorResetValues();
});

$("#<portlet:namespace />modalConfirm #<portlet:namespace />buttonConfirm").click(function(event){
    $("#<portlet:namespace />modalConfirm").modal('hide');
});

$("#<portlet:namespace />modalError #<portlet:namespace />buttonConfirm").click(function(event){
    $("#<portlet:namespace />modalError").modal('hide');
});

$('#submitContactButton').click( function(e){
	e.preventDefault();
	AUI().use('aui-io-request', function(A) {
		A.io.request('${ContactInitiativeAuthorURL}', {
			method : 'post',
			dataType: 'json',
			on: {
	            success: function(e) {
	            	$('#modalInitiativeContact').modal('hide')
			 	}
			 }
		});
	});
});

function contactAuthorResetValues()
{
    $("#"+namespaceContactAuthor+"initiativeContactAuthorSubject").val("");
    $("#"+namespaceContactAuthor+"initiativeContactAuthorMessage").val("");
    $("#<portlet:namespace />cnil").prop("checked", false);
}

/**
 * Lors du click sur le bouton envoie
 */
$("#" + namespaceContactAuthor + "submitContactButton").click(function(event){
	sendInitiativeAuthorMessage();
});


/*
* Lors du click sur le bouton envoie
*/
function sendInitiativeAuthorMessage () {
	
    event.preventDefault();
    var response = validateForm();
    
    if (response){
    	var entryId = $("#<portlet:namespace />entryId").val();
    	var message = $("#<portlet:namespace />initiativeContactAuthorMessage").val();
        var subject = $("#<portlet:namespace />initiativeContactAuthorSubject").val();
        var lastname = $("#<portlet:namespace />username").val();
        var firstname = $("#<portlet:namespace />firstname").val();
        var mobile = $("#<portlet:namespace />mobile").val();
        var email = $("#<portlet:namespace />mail").val();
        
        AUI().use('aui-io-request', function(A) {
            try {
                A.io.request('${ContactInitiativeAuthorURL}', {
                    method : 'POST',
                    dataType: 'json',
                    data:{
                    	<portlet:namespace/>entryId: 		entryId,
                        <portlet:namespace/>message: 		message,
                        <portlet:namespace/>subject: 		subject,
                        <portlet:namespace/>lastname:		lastname,
                        <portlet:namespace/>firstname: 		firstname,
                        <portlet:namespace/>mobile: 		mobile,
                        <portlet:namespace/>email: 			email
                    },
                    on: {
                        complete: function(e) {
                            // var data = this.get('responseData');
                            var data = JSON.parse(e.details[1].responseText);
                            if(data.result){
                                $("#modalInitiativeContact").modal('hide');
                                $("#<portlet:namespace />modalConfirm").modal('show');
                                contactAuthorResetValues();
                            }else{
                                $("#<portlet:namespace />modalError h4").text(data.message);
                                $("#<portlet:namespace />modalError").modal('show');
                            }
                        }
                    }
                });
            }
            catch(error) {
                if(!(error instanceof TypeError)){
                    console.log(error);
                } else console.log("petite erreur sans importance")
            }
        });
    }
}

/*
* Verifie la conformite des elements avant l'envoie du formulaire
*/
function validateForm()
{
    var result = true;
    
    var message = $("#<portlet:namespace />initiativeContactAuthorMessage").val();
    var subject = $("#<portlet:namespace />initiativeContactAuthorSubject").val();
    var cnil = $("#<portlet:namespace />cnil").is(":checked");

    if (message===null || message===""){
        $("#<portlet:namespace />initiativeContactAuthorMessage").css({ "box-shadow" : "0 0 10px #CC0000" });
        result = false;
    }else $("#<portlet:namespace />initiativeContactAuthorMessage").css({ "box-shadow" : "" });
    
    if (subject===null || subject===""){
        $("#<portlet:namespace />initiativeContactAuthorSubject").css({ "box-shadow" : "0 0 10px #CC0000" });
        result = false;
    }else $("#<portlet:namespace />initiativeContactAuthorSubject").css({ "box-shadow" : "" });
    
    if (!cnil)
        result = false;

    if (!result)
        $("#<portlet:namespace />alert").removeClass("hidden");
    else $("#<portlet:namespace />alert").addClass("hidden");
    
    return result;
}

</script>