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
                        <c:set var="phoneNumber" value="${userConnected.get('phone')}" />
                        <c:if test="${not empty userConnected.get('mobile')}">
                            <c:if test="${not empty phoneNumber}">
                                <c:set var="phoneNumber" value="${phoneNumber} / ${userConnected.get('mobile')}" />
                            </c:if>
                            <c:if test="${empty phoneNumber}">
                                <c:set var="phoneNumber" value="${userConnected.get('mobile')}" />
                            </c:if>
                        </c:if>
                        <aui:input name="phoneNumber" label="modal.user.phone" required="true" maxlength="20" value="${phoneNumber}"/>
                    </div>

                </div>
            
            	<%-- Groupe de champs : Information aide --%>
                <div class="pro-wrapper">
                	
					<%-- Label : Informations aide --%>
                    <h4><liferay-ui:message key="modal.submit.help.request.information"/></h4>
                    
                    <%-- Champ : Message --%>
                    <div class="form-group">
                        <aui:input id="message" type="textarea" name="message" required="true" label="modal.submit.help.information.message" value=""/>
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
                    <%-- Champ cache : ID --%>
                    <aui:input type="hidden" name="entryId" value="${entryId}"/>

                </div>

                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="<portlet:namespace />agreement1" name="<portlet:namespace />agreement1" value="agreement1">
                        <label for="<portlet:namespace />agreement1" class="fontWhite">
                            <liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_SubmitHelpRequestAgreement1"/>
                        </label>
                        <span class="reference-mark text-warning" id="agreement1_mark">
                            <svg class="lexicon-icon lexicon-icon-asterisk" focusable="false" role="presentation" viewBox="0 0 512 512">
                                <path class="lexicon-icon-outline" d="M323.6,190l146.7-48.8L512,263.9l-149.2,47.6l93.6,125.2l-104.9,76.3l-96.1-126.4l-93.6,126.4L56.9,435.3l92.3-123.9L0,263.8l40.4-122.6L188.4,190v-159h135.3L323.6,190L323.6,190z"></path>
                            </svg>
                        </span>
                    </div>
                </div>

                <div class="pro-optin form-checkbox" >
                    <div>
                        <input type="checkbox" id="<portlet:namespace />agreement2" name="<portlet:namespace />agreement2" value="agreement2">
                        <label for="<portlet:namespace />agreement2" class="fontWhite">
                            <liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_SubmitHelpRequestAgreement2"/>
                        </label>
                        <span class="reference-mark text-warning" id="agreement2_mark">
                            <svg class="lexicon-icon lexicon-icon-asterisk" focusable="false" role="presentation" viewBox="0 0 512 512">
                                <path class="lexicon-icon-outline" d="M323.6,190l146.7-48.8L512,263.9l-149.2,47.6l93.6,125.2l-104.9,76.3l-96.1-126.4l-93.6,126.4L56.9,435.3l92.3-123.9L0,263.8l40.4-122.6L188.4,190v-159h135.3L323.6,190L323.6,190z"></path>
                            </svg>
                        </span>
                    </div>
                </div>

                <div class="pro-optin form-checkbox" >
                    <div>
                        <input type="checkbox" id="<portlet:namespace />agreement3" name="<portlet:namespace />agreement3" value="agreement3">
                        <label for="<portlet:namespace />agreement3" class="fontWhite">
                            <liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_SubmitHelpRequestAgreement3"/>
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

<!-- Inclusion de la modal d'alerte d'une propostion d'aide désactivée -->
<jsp:include page="/include/inactive-help-proposal-modal.jsp"/>

<script type="text/javascript">
	
	// Variables tempons des informations utilisateur et contexte namespace
	var namespaceHelpRequest = "<portlet:namespace />";

	/*
	* Lors du chargement de la page
	*/
    $(document).ready(function(){
    	resetValuesHelpRequest();
        $("#<portlet:namespace />modalConfirm").modal('hide');
        $("#<portlet:namespace />modalError").modal('hide');
    });

    /*
	* Lors du click sur le bouton de submit
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
                    if(data.result){
                        $("#modalSubmitHelpRequest").modal('hide');
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
        $("#<portlet:namespace />message").val("");
        $("#<portlet:namespace />photo").val("");

     	// Chebox de conditions et de sauvegade des informations
        $("#<portlet:namespace />agreement1").prop("checked", false);
        $("#<portlet:namespace />agreement2").prop("checked", false);
        $("#<portlet:namespace />agreement3").prop("checked", false);
    }

    /*
	* Verifie la conformite des elements avant l'envoie du formulaire
	*/
    function validateFormHelpRequest()
    {
        var result = true;

        var phoneNumber = $("#<portlet:namespace />phoneNumber").val();
        if (phoneNumber===null || phoneNumber===""){
            $("#<portlet:namespace />phoneNumber").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#<portlet:namespace />phoneNumber").css({ "box-shadow" : "" });

        var message = $("#<portlet:namespace />message").val();
        if (message===null || message===""){
            $("#<portlet:namespace />message").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#<portlet:namespace />message").css({ "box-shadow" : "" });

        var photo = $("#<portlet:namespace />photo").val();
        if (photo!=null && photo!==""){
            var ext = photo.split(".").pop().toLowerCase();
            if($.inArray(ext, ['png','jpg','jpeg']) == -1) {
                $("#<portlet:namespace />photo").css({ "box-shadow" : "0 0 10px #CC0000" });
                result = false;
            }else $("#<portlet:namespace />photo").css({ "box-shadow" : "" });
        }else{
            $("#<portlet:namespace />photo").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }

        var agreement1 = $("#<portlet:namespace />agreement1").is(":checked");
        if (!agreement1){
            $("#<portlet:namespace />agreement1").closest('div').css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#<portlet:namespace />agreement1").closest('div').css({ "box-shadow" : "" });

        var agreement2 = $("#<portlet:namespace />agreement2").is(":checked");
        if (!agreement2){
            $("#<portlet:namespace />agreement2").closest('div').css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#<portlet:namespace />agreement2").closest('div').css({ "box-shadow" : "" });

        if (!result)
            $("#<portlet:namespace />alert").removeClass("hidden");
        else
            $("#<portlet:namespace />alert").addClass("hidden");
        
        return result;
    }
</script>