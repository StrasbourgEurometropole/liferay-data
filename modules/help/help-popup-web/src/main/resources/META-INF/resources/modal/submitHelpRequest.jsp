<%@ include file="/help-popup-init.jsp" %>

<portlet:resourceURL id="submitHelpRequest" var="submitHelpRequestURL">
</portlet:resourceURL>

<!-- DEPOSER UNE NOUVELLE AIDE -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalSubmitHelpRequest" tabindex="-1" role="dialog" aria-labelledby="modalSubmitHelpRequest"
	data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
        
        	<%-- Top titre du modal --%>
            <div class="pro-modal-top">
                <h3><liferay-ui:message key="modal.submit.help.request.title"/></h3>
                <button  type="button" class="close closefirstmodal" aria-label="Close">
                	<span aria-hidden="true"><span class="icon-multiply"></span></span>
                </button>
            </div>
			
			<%-- Formulaire --%>
            <aui:form name="uploadForm" enctype="multipart/form-data">

                <%-- Groupe de champs : Information utilisateur --%>
                <div class="pro-wrapper">

                	<%-- Label : Informations utilisateur --%>
                    <h4><liferay-ui:message key="modal.submit.help.user"/></h4>

                    <%-- Groupe de champs : Informations generales --%>
                    <div class="pro-row">

                        <%-- Champ : Nom --%>
                        <div class="form-group form-half">
                            <aui:input name="lastname" disabled="true" label="modal.user.lastname" required="true" value="${userConnected.get('last_name')}"/>
                        </div>

                        <%-- Champ : Prenom --%>
                        <div class="form-group form-half">
                            <aui:input name="firstname" disabled="true" label="modal.user.firstname" required="true" value="${userConnected.get('first_name')}"/>
                        </div>

                    </div>

                    <%-- Champ : Email --%>
                    <div class="form-group">
                        <aui:input name="mail" label="modal.user.mail" required="true" disabled="true" value="${userConnected.get('email')}"/>
                    </div>

                    <%-- Champ : Telephone --%>
                    <div class="form-group">
                        <aui:input name="phone" label="modal.user.phone" required="true" maxlength="20" value="" onInput="checkValuesHelpRequest();"/>
                    </div>

                </div>
            
            	<%-- Groupe de champs : Information aide --%>
                <div class="pro-wrapper">
                	
					<%-- Label : Informations aide --%>
                    <h4><liferay-ui:message key="modal.submit.help.request.information"/></h4>
                    
                    <%-- Champ : Message --%>
                    <div class="form-group">
                        <aui:input id="message" type="textarea" name="message" label="modal.submit.help.information.message" value=""/>
                    </div>
                    
                    <%-- Champ : Allergies --%>
                    <div class="form-group">
                        <aui:input id="allergie" type="textarea" name="allergie" required="true" label="modal.submit.help.information.allergie" value="" cssClass="allergie"/>
                    </div>
                    
                    <%-- Champ : Image --%>
                    <div class="pro-row">
                        <div class="form-group form-two-tiers">
                            <span class="browsePicture input-group-btn">
                                <aui:input name="photo" type="file" label="modal.submit.help.information.card"
                                    cssClass="btn btn-default btn-choose" required="true">
							        <aui:validator name="acceptFiles">'jpg,png,jpeg'</aui:validator>
                                </aui:input>
                            </span>
                        </div>
                    </div>

                </div>

                <div class="pro-optin group-checkbox" >
                    <label class="fontWhite">
                        <liferay-ui:message key="modal.submit.help.information.consent"/>
                    </label>
                </div>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox"  id="<portlet:namespace />consentement" value="consentement">
                        <label for="<portlet:namespace />consentement" class="fontWhite">
                            <liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_consentementSubmitHelpRequest"/>
                        </label>
                    </div>
                </div>

                <div class="pro-optin group-checkbox" >
                    <label class="fontWhite">
                        <liferay-ui:message key="modal.submit.help.information.data"/>
                    </label>
                </div>
                <div class="pro-optin form-checkbox" >
                    <div>
                        <input type="checkbox" id="<portlet:namespace />data" value="data">
                        <label for="<portlet:namespace />data" class="fontWhite">
                            <liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_dataSubmitHelpRequest"/>
                        </label>
                    </div>
                </div>

                <div class="pro-optin group-checkbox" >
                    <label class="fontWhite">
                        <liferay-ui:message key="modal.submit.help.information.security"/>
                    </label>
                </div>
                <div class="pro-optin form-checkbox" >
                    <div>
                        <input type="checkbox" id="<portlet:namespace />security" value="security">
                        <label for="<portlet:namespace />security" class="fontWhite">
                            <liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_securitySubmitHelp"/>
                        </label>
                    </div>
                    <div>
                        <input type="checkbox" id="<portlet:namespace />security2" value="security2">
                        <label for="<portlet:namespace />security2" class="fontWhite">
                            <liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_security2SubmitHelp"/>
                        </label>
                    </div>
                </div>

                <div class="pro-optin group-checkbox" >
                    <label class="fontWhite">
                        <liferay-ui:message key="modal.submit.help.information.responsability"/>
                    </label>
                </div>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox"  id="<portlet:namespace />responsability" value="responsability">
                        <label for="<portlet:namespace />responsability" class="fontWhite">
                            <liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_responsabilitySubmitHelp"/>
                        </label>
                    </div>
                </div>
                
                <div id="<portlet:namespace />alert" class="hidden pro-info-supp alertMessage"><liferay-ui:message key="modal.alert"/></div>
                
                <%-- Boutou de soumisson --%>
                <div class="pro-form-submit">
                    <button id="<portlet:namespace />buttonSubmit" type="submit" class="btn btn-default">
                    	<liferay-ui:message key="modal.submit.help.request.submit"/>
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
                <h3><liferay-ui:message key='confirm-request-help'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>
            <div class="pro-wrapper">
                <h4><liferay-ui:message key='submit-request-help-ok'/></h4>
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
                <h3><liferay-ui:message key='error-submit-request-help'/></h3>
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

<script type="text/javascript">
	
	// Variables tempons des informations utilisateur et contexte namespace
	var namespaceHelpRequest = "<portlet:namespace />";
	var saved_address = "${userConnected.get('address')}";
	var saved_zipCode = "${userConnected.get('zipcode')}";
	var saved_city = "${userConnected.get('city')}";
	var saved_dateNaiss = "${formattedDate}";
	var saved_phone = "${userConnected.get('phone')}" != 'null' ? "${userConnected.get('phone')}" : " ";
	var saved_mobile = "${userConnected.get('mobile')}" != 'null' ? "${userConnected.get('mobile')}" : " ";

	/*
	* Lors du chargement de la page
	*/
    $(document).ready(function(){
    	resetValuesHelpRequest();
        $("#<portlet:namespace />modalConfirm").modal('hide');
        $("#<portlet:namespace />modalError").modal('hide');
        $("#<portlet:namespace />checkboxSaveInfo").hide();
    });

    /*
	* Lors du click sur le bouton de vote
	*/
    $("#<portlet:namespace />buttonSubmit").click(function(event){
        event.preventDefault();
        var response = validateFormHelpRequest();
        if (response){
                        
            var request = new XMLHttpRequest();
            var formElement = $("#<portlet:namespace />uploadForm");
            request.open('POST', '${submitHelpRequestURL}', true);
            //request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');

            request.onload = function() {
                if (this.status >= 200 && this.status < 400) {
                    // Success!
                    var data = JSON.parse(this.response);
                    // var data = this.get('responseData');
                    //var data = JSON.parse(e.details[1].responseText);
                    if(data.result){
                        $("#modalSubmitHelpRequest").modal('hide');
                        if(data.savedInfo){
                            saved_dateNaiss = birthday;
                            saved_city = $("#<portlet:namespace />city").val();
                            saved_address = $("#<portlet:namespace />address").val();
                            saved_zipCode = $("#<portlet:namespace />postalcode").val();
                            if($("#<portlet:namespace />phone").val() != "")
                                saved_phone = $("#<portlet:namespace />phone").val();
                            if($("#<portlet:namespace />mobile").val() != "")
                                saved_mobile = $("#<portlet:namespace />mobile").val();
                        }
                        $("#<portlet:namespace />modalConfirm").modal('show');
                        resetValuesHelpRequest();
                    }else{
                        $("#<portlet:namespace />modalError h4").text(data.message);
                        $("#<portlet:namespace />modalError").modal('show');
                    }
                } else {
                    // We reached our target server, but it returned an error
                }
            };

            request.send(new FormData(formElement[0]));
          
        }
    });

    $("#<portlet:namespace />modalConfirm #<portlet:namespace />buttonConfirm").click(function(event){
        $("#<portlet:namespace />modalConfirm").modal('hide');
    });

    $("#<portlet:namespace />modalError #<portlet:namespace />buttonConfirm").click(function(event){
        $("#<portlet:namespace />modalError").modal('hide');
    });

    /*
	* Reinitialise le formulaire avec les informations les plus fraiches
	*/
    function resetValuesHelpRequest(){
    	// Champs entite
        $("#<portlet:namespace />title").val("");
        $("#<portlet:namespace />description").val("");
        $("#<portlet:namespace />helpRequestInTheNameOf").val("");
        $("#<portlet:namespace />place").val("");
        $("#<portlet:namespace />project option[value='0']").prop('selected', true);
        $("#<portlet:namespace />project").selectric();
        $("#<portlet:namespace />district option[value='0']").prop('selected', true);
        $("#<portlet:namespace />district").selectric();
        $("#<portlet:namespace />thematic option[value='0']").prop('selected', true);
        $("#<portlet:namespace />thematic").selectric();
        
     	// Champs informations utilisateur
        $("#<portlet:namespace />address").val(saved_address);
        $("#<portlet:namespace />photo").val("");
        $("#<portlet:namespace />video").val("");
        $("#<portlet:namespace />postalcode").val(saved_zipCode);
        $("#<portlet:namespace />phone").val(saved_phone);
        $("#<portlet:namespace />mobile").val(saved_mobile);
        $("#<portlet:namespace />birthday").val(saved_dateNaiss);
        
     	// Chebox de conditions et de sauvegade des informations
     	$("#<portlet:namespace />checkboxSaveInfo #<portlet:namespace />saveInfo").prop('checked', false);
        $("#<portlet:namespace />checkboxSaveInfo").hide();
        $("#<portlet:namespace />legalage").prop("checked", false);
        $("#<portlet:namespace />cnil").prop("checked", false);
        $("#<portlet:namespace />city").val(saved_city);
    }

    /*
	* Affiche la demande de sauvegarde des informations dans Publik
	*/
    function checkValuesHelpRequest(){
        if($("#<portlet:namespace />birthday").val() != saved_dateNaiss 
        		|| $("#<portlet:namespace />address").val() != saved_address 
        		|| $("#<portlet:namespace />city").val() != saved_city 
        		|| $("#<portlet:namespace />postalcode").val() != saved_zipCode 
        		|| $("#<portlet:namespace />phone").val() != saved_phone 
        		|| $("#<portlet:namespace />mobile").val() != saved_mobile) {
            $("#<portlet:namespace />checkboxSaveInfo #<portlet:namespace />saveInfo").prop('checked', true);
            $("#<portlet:namespace />checkboxSaveInfo").show();
        }else{
            $("#<portlet:namespace />checkboxSaveInfo #<portlet:namespace />saveInfo").prop('checked', false);
            $("#<portlet:namespace />checkboxSaveInfo").hide();
        }
    }

    /*
	* Verifie la conformite des elements avant l'envoie du formulaire
	*/
    function validateFormHelpRequest()
    {
        var result = true;
        
        var title = $("#<portlet:namespace />title").val();
        var description = $("#<portlet:namespace />description").val();
        var legalage = $("#<portlet:namespace />legalage").is(":checked");
        var cnil = $("#<portlet:namespace />cnil").is(":checked");
        var photo = $("#<portlet:namespace />photo").val();
        
        <%-- desactivation de la verification de certains champs obligatoires
        var regex = new RegExp("^(([0-8][0-9])|(9[0-5]))[0-9]{3}$");
        var city = $("#<portlet:namespace />city").val();
        var address = $("#<portlet:namespace />address").val();
        var postalcode = $("#<portlet:namespace />postalcode").val();
        --%>

        if (photo!=null && photo!==""){
            var ext = photo.split(".").pop().toLowerCase();
            if($.inArray(ext, ['png','jpg','jpeg']) == -1) {
            $("#<portlet:namespace />photo").css({ "box-shadow" : "0 0 10px #CC0000" });
                result = false;
            }else $("#<portlet:namespace />photo").css({ "box-shadow" : "" });
        }

        if (title===null || title===""){
            $("#<portlet:namespace />title").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#<portlet:namespace />title").css({ "box-shadow" : "" });

        if (description===null || description===""){
            $("#<portlet:namespace />description").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#<portlet:namespace />description").css({ "box-shadow" : "" });

        <%-- desactivation de la verification de certains champs obligatoires
        if (city===null || city===""){
            $("#<portlet:namespace />city").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#<portlet:namespace />city").css({ "box-shadow" : "" });

        if (address===null || address===""){
            $("#<portlet:namespace />address").css({ "box-shadow" : "0 0 10px #CC0000" });
            //result = false;
        }else $("#<portlet:namespace />address").css({ "box-shadow" : "" });

        if (postalcode===null || postalcode===""){
            $("#<portlet:namespace />postalcode").css({ "box-shadow" : "0 0 10px #CC0000" });
            //result = false;
        }else if(!regex.test(postalcode)){
            $("#<portlet:namespace />postalcode").css({ "box-shadow" : "0 0 10px #CC0000" });
            alert("Merci de respecter la syntaxe d'un code postal");
            result = false;
        }
        else $("#<portlet:namespace />postalcode").css({ "box-shadow" : "" });
        --%>

        if (!legalage)
            result = false;

        if (!cnil)
            result = false;

        if (!result)
            $("#<portlet:namespace />alert").removeClass("hidden");
        else $("#<portlet:namespace />alert").addClass("hidden");
        
        return result;
    }
</script>