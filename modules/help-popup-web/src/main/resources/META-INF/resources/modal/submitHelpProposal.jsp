<%@ include file="/help-popup-init.jsp" %>

<portlet:resourceURL id="submitHelpProposal" var="submitHelpProposalURL">
</portlet:resourceURL>

<!-- DEPOSER UNE NOUVELLE AIDE -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalSubmitHelpProposal" tabindex="-1" role="dialog" aria-labelledby="modalSubmitHelpProposal"
	data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
        
        	<%-- Top titre du modal --%>
            <div class="pro-modal-top">
                <h3><liferay-ui:message key="modal.submit.help.proposal.title"/></h3>
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
                    <label>
                        <liferay-ui:message key="modal.show.info"/>
                    </label>

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

                    <%-- Groupe de champs : Information adresse  --%>
                    <div class="pro-row">

                    	<%-- Champ : Adresse --%>
                        <div class="form-group form-half">
                            <aui:input name="address" label="modal.user.address" required="true" maxlength="256" onInput="checkValuesHelpProposal();" />
                        </div>

                        <%-- Groupe de champs : (note : utilise pour la sous division d'une meme ligne en plus petit champ) --%>
                        <div class="form-group form-half">

                        	<%-- Champ : Ville --%>
                            <div class="form-city">
                                <aui:input name="city" label="modal.user.city" required="true" maxlength="256" onInput="checkValuesHelpProposal();" />
                            </div>

                            <%-- Champ : Code postal --%>
                            <div class="form-code">
                                <aui:input name="postalcode" label="modal.user.postalcode" required="true" maxlength="5" onInput="checkValuesHelpProposal();"/>
                            </div>

                        </div>

                    </div>

                    <%-- Champ : Telephone --%>
                    <div class="form-group">
                        <aui:input name="phone" label="modal.user.phone" required="true" maxlength="20" value="" onInput="checkValuesHelpProposal();"/>
                    </div>

                </div>
            
            	<%-- Groupe de champs : Information aide --%>
                <div class="pro-wrapper">
                	
					<%-- Label : Informations aide --%>
                    <h4><liferay-ui:message key="modal.submit.help.proposal.information"/></h4>
                    
                    <%-- Champ : Titre --%>
                    <div class="form-group">
                        <aui:input id="title" name="title" label="modal.submit.help.information.title" maxlength="256" value=""/>
                    </div>

                    <%-- Champ : Type de l'aide --%>
                    <div class="form-group">
                        <label>
                            <liferay-ui:message key="modal.submit.help.information.type"/>
                            <span class="reference-mark text-warning" id="sovq__column1__1">
                                <svg class="lexicon-icon lexicon-icon-asterisk" focusable="false" role="presentation" viewBox="0 0 512 512">
                                    <path class="lexicon-icon-outline" d="M323.6,190l146.7-48.8L512,263.9l-149.2,47.6l93.6,125.2l-104.9,76.3l-96.1-126.4l-93.6,126.4L56.9,435.3l92.3-123.9L0,263.8l40.4-122.6L188.4,190v-159h135.3L323.6,190L323.6,190z"></path>
                                </svg>
                            </span>
                        </label>
                        <c:forEach var="type" items="${types}">
                            <div class="form-checkbox">
                                <input type="checkbox"  id="<portlet:namespace />type-${type.categoryId}" value="${type.categoryId}">
                                <label for="<portlet:namespace />type-${type.categoryId}" class="fontWhite">
                                    ${type.name}
                                </label>
                            </div>
                        </c:forEach>
                    </div>

                    <%-- Champ : Présentation --%>
                    <div class="form-group">
                        <aui:input id="presentation" type="textarea" name="presentation" label="modal.submit.help.information.presentation" value=""/>
                    </div>

                    <%-- Groupe de champs : Je suis --%>
                    <div class="pro-row">

                    	<%-- Champ : Je suis --%>
                        <div class="form-group form-half">
                            <aui:select required="true" name="helper" label="modal.submit.help.information.helper">
                                <c:forEach var="helper" items="${helpers}">
                                    <aui:option value="${helper.categoryId}" label="${helper.name}" />
                                </c:forEach>
                            </aui:select>
                        </div>

                        <%-- Champ : Au nom de --%>
                        <div class="form-group form-half">
                            <aui:input id="helpProposalInTheNameOf" name="inTheNameOf" label="modal.submit.help.information.inTheNameOf" required="false" maxlength="400" value=""/>
                        </div>

                    </div>

                    <%-- Champ : Langue --%>
                    <div class="form-group">
                        <aui:input id="language" name="language" label="modal.submit.help.information.language" maxlength="256" value=""/>
                    </div>
                    	
                    <%-- Champ : Localisation --%>
                    <div class="form-group">
                        <aui:select required="true" name="localisation" label="modal.submit.help.information.territories">
                            <c:forEach var="localisation" items="${localisations}">
                                <aui:option value="${localisation.categoryId}" label="${localisation.name}" />
                            </c:forEach>
                        </aui:select>
                    </div>
                    
                    <%-- Champ : photo --%>
                    <div class="pro-row">
                        <div class="form-group form-two-tiers">
                            <span class="browsePicture input-group-btn">
                                <aui:input name="photo" type="file" label="modal.submit.help.information.picture"
                                    cssClass="btn btn-default btn-choose">
							        <aui:validator name="acceptFiles">'jpg,png,jpeg'</aui:validator>
                                </aui:input>
                            </span>
                        </div>
                    </div>
                    
                </div>

                <div class="pro-optin group-checkbox" >
                    <label class="fontWhite">
                        <liferay-ui:message key="modal.submit.help.information.legalage"/>
                    </label>
                </div>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox"  id="<portlet:namespace />legalage" value="legalage">
                        <label for="<portlet:namespace />legalage" class="fontWhite">
                            <liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_legalageSubmitHelpProposal"/>
                        </label>
                    </div>
                </div>

                <div class="pro-optin group-checkbox" >
                    <label class="fontWhite">
                        <liferay-ui:message key="modal.submit.help.information.security"/>
                    </label>
                </div>
                <div class="pro-optin form-checkbox">
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
                <div class="pro-info-supp">
                    <p><liferay-ui:message key="modal.submit.help.information.delete"/></p>
                </div>
                
                <div id="<portlet:namespace />alert" class="hidden pro-info-supp alertMessage"><liferay-ui:message key="modal.alert"/></div>
                
                <%-- Boutou de soumisson --%>
                <div class="pro-form-submit">
                    <button id="<portlet:namespace />buttonSubmit" type="submit" class="btn btn-default">
                    	<liferay-ui:message key="modal.submit.help.proposal.submit"/>
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
                <h3><liferay-ui:message key='confirm-proposal-help'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>
            <div class="pro-wrapper">
                <h4><liferay-ui:message key='submit-proposal-help-ok'/></h4>
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
                <h3><liferay-ui:message key='error-submit-proposal-help'/></h3>
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
	var namespaceHelpProposal = "<portlet:namespace />";
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
    	resetValuesHelpProposal();
        $("#<portlet:namespace />modalConfirm").modal('hide');
        $("#<portlet:namespace />modalError").modal('hide');
        $("#<portlet:namespace />checkboxSaveInfo").hide();
    });

    /*
	* Lors du click sur le bouton de vote
	*/
    $("#<portlet:namespace />buttonSubmit").click(function(event){
        event.preventDefault();
        var response = validateFormHelpProposal();
        if (response){
                        
            var request = new XMLHttpRequest();
            var formElement = $("#<portlet:namespace />uploadForm");
            request.open('POST', '${submitHelpProposalURL}', true);
            //request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');

            request.onload = function() {
                if (this.status >= 200 && this.status < 400) {
                    // Success!
                    var data = JSON.parse(this.response);
                    // var data = this.get('responseData');
                    //var data = JSON.parse(e.details[1].responseText);
                    if(data.result){
                        $("#modalSubmitHelpProposal").modal('hide');
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
                        resetValuesHelpProposal();
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
    function resetValuesHelpProposal(){
    	// Champs entite
        $("#<portlet:namespace />title").val("");
        $("#<portlet:namespace />description").val("");
        $("#<portlet:namespace />HelpProposalInTheNameOf").val("");
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
    function checkValuesHelpProposal(){
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
    function validateFormHelpProposal()
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