<%@ include file="/project-popup-init.jsp" %>

<portlet:resourceURL id="giveInitiativeHelp" var="giveInitiativeHelpURL">
</portlet:resourceURL>

<!-- DEPOSER UNE NOUVELLE INITIATIVE -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalGiveInitiativeHelp" tabindex="-1" role="dialog" aria-labelledby="modalGiveInitiativeHelp">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
        
        	<%-- Top titre du modal --%>
            <div class="pro-modal-top">
                <h3><liferay-ui:message key="modal.give.initiative.help.title"/></h3>
                <button id="closingButton" type="button" class="close" aria-label="Close">
                	<span aria-hidden="true"><span class="icon-multiply"></span></span>
                </button>
            </div>
			
			<%-- Formulaire --%>
            <aui:form id="form-give-initiative-help">
            	
            	<%-- Groupe de champs : Information aide initiative --%>
                <div class="pro-wrapper">
                	
					<%-- Label : Informations aide initiative --%>
                    <h4><liferay-ui:message key="modal.give.initiative.help.information"/></h4>
                    
                    <%-- Champ : Type d'aide --%>
                    <div id="<portlet:namespace />helpTypes" class="form-checkbox">
                        <span class="label">Quelle(s) aide voulez-vous proposer <span>*</span></span>
                        <div class="checkbox-inline">
                            <input type="checkbox" id="<portlet:namespace />helpType1" value="1">
                            <label for="<portlet:namespace />helpType1">Du temps</label>
                        </div>
                        <div class="checkbox-inline">
                            <input type="checkbox" id="<portlet:namespace />helpType2" value="2">
                            <label for="<portlet:namespace />helpType2" class="checkbox-inline">De l'argent</label>
                        </div>
                        <div class="checkbox-inline">
                            <input type="checkbox" id="<portlet:namespace />helpType3" value="3">
                            <label for="<portlet:namespace />helpType3" class="checkbox-inline">Un lieu</label>
                        </div>
                        <div class="checkbox-inline">
                            <input type="checkbox" id="<portlet:namespace />helpType4" value="4">
                            <label for="<portlet:namespace />helpType4" class="checkbox-inline">Une expertise</label>
                        </div>
                    </div>
                    
                    <%-- Champ : Message --%>
                    <div class="form-group">
                        <aui:input id="initiativeHelpMessage" type="textarea" 
	                        	name="initiativeHelpMessage" required="true" 
	                        	label="modal.give.initiative.help.information.message" value=""
	                    />
	                </div> 
                    
                </div>
                
                <%-- Groupe de champs : Information utilisateur --%>
                <div class="pro-wrapper">
                
                	<%-- Label : Informations utilisateur --%>
                    <h4><liferay-ui:message key="modal.give.initiative.help.user"/></h4>
                    
                    <%-- Groupe de champs : Informations generales --%>
                    <div class="pro-row">
                    	
                    	<%-- Champ : Nom --%>
                        <div class="form-group form-triple">
                            <aui:input name="lastname" disabled="true" label="modal.user.lastname" required="true" value="${userConnected.get('last_name')}"/>
                        </div>
                        
                        <%-- Champ : Prenom --%>
                        <div class="form-group form-triple">
                            <aui:input name="firstname" disabled="true" label="modal.user.firstname" required="true" value="${userConnected.get('first_name')}"/>
                        </div>
                        
                        <%-- Champ : Date de naissance --%>
                        <div class="form-group form-triple">
	                        <c:if test="${userConnected.get('birthdate') ne 'null'}">
	                            <fmt:parseDate pattern="yyyy-MM-dd" value="${userConnected.get('birthdate')}" var="parsedStatusDate" />
					            <fmt:formatDate value="${parsedStatusDate}" var="formattedDate" type="date" pattern="dd/MM/yyyy" />
	                        </c:if>
                            <aui:input id="birthday" name="birthday" cssClass="frm_date" label="modal.user.birthday" required="true" placeholder="jj/mm/aaaa" maxlength="10" onInput="checkValues();" onChange="checkValues();"/>
                        </div>
                        
                    </div>
                    
                    <%-- Groupe de champs : Information adresse  --%>
                    <div class="pro-row">
                    
                    	<%-- Champ : Adresse --%>
                        <div class="form-group form-half">
                            <aui:input name="address" label="modal.user.address" required="true" maxlength="256" onInput="checkValues();" />
                        </div>
                        
                        <%-- Groupe de champs : (note : utilise pour la sous division d'une meme ligne en plus petit champ) --%>
                        <div class="form-group form-half">
                        
                        	<%-- Champ : Ville --%>
                            <div class="form-city">
                                <aui:input name="city" label="modal.user.city" required="true" maxlength="256" onInput="checkValues();" />
                            </div>
                            
                            <%-- Champ : Code postal --%>
                            <div class="form-code">
                                <aui:input name="postalcode" label="modal.user.postalcode" required="true" maxlength="5" onInput="checkValues();"/>
                            </div>
                            
                        </div>
                        
                    </div>
                    
                    <%-- Champ : Email --%>
                    <div class="form-group">
                        <aui:input name="mail" label="modal.user.mail" required="true" disabled="true" value="${userConnected.get('email')}"/>
                    </div>
                    
                    <%-- Groupe de champs : Contacts --%>
                    <div class="pro-row">
                    
                    	<%-- Champ : Telephone --%>
                        <div class="form-group form-half">
                            <aui:input name="phone" label="modal.user.phone" maxlength="20" value="" onInput="checkValues();"/>
                        </div>
                        
                        <%-- Champ : Mobile --%>
                        <div class="form-group form-half">
                            <aui:input name="mobile" label="modal.user.mobile" maxlength="20" value="" onInput="checkValues();"/>
                        </div>
                    </div>
                    
                    <%-- Champ : Demande de sauvegarde des inforamtions --%>
                    <div class="form-group form-checkbox" id="<portlet:namespace />checkboxSaveInfo" >
                        <div>
                            <input type="checkbox" id="<portlet:namespace />saveInfo" value="info">
                            <label for="<portlet:namespace />saveInfo">
                            	<liferay-ui:message key="modal.save.info"/>
                            </label>
                        </div>
                    </div>
                    
                </div>
                
                <%-- Champ : Demande de verification de l'age legal --%>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="<portlet:namespace />legalage" value="legalage">
                        <label for="<portlet:namespace />legalage" class="fontWhite">
                            <liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_legalage"/>
                        </label>
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
                
                <div id="<portlet:namespace />alert" class="hidden pro-info-supp alertMessage"><liferay-ui:message key="modal.alert"/></div>
                
                <%-- Champ cache : ID --%>
                <input type="hidden" id="<portlet:namespace />entryId" name="entryId" value="${entryId}"/>
                
                <%-- Boutou de soumisson --%>
                <div class="pro-form-submit">
                    <button id="<portlet:namespace />buttonSubmit" type="submit" class="btn btn-default">
                    	<liferay-ui:message key="modal.give.initiative.help.submit"/>
                    </button>
                </div>
                
            </aui:form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- CONFIRMATION NOUVELLE AIDE INITIATIVE -->
<div class="pro-modal pro-bloc-pcs-form fade" id="<portlet:namespace />modalConfirm" tabindex="-1" role="dialog" aria-labelledby="<portlet:namespace />modalConfirm">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key='give.initiative.help.confirm.title'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>
            <div class="pro-wrapper">
                <h4><liferay-ui:message key='give.initiative.help.confirm.text'/></h4>
                <div class="centerButtonValidation">
                    <input id="<portlet:namespace />buttonConfirm" type="submit" class="pro-btn" value=<liferay-ui:message key="button.give.initiative.help.ok"/> />
                </div>
            </div>
        </div>
    </div>
</div>


<!-- ERREUR NOUVELLE INITIATIVE -->
<div class="pro-modal pro-bloc-pcs-form fade" id="<portlet:namespace />modalError" tabindex="-1" role="dialog" aria-labelledby="<portlet:namespace />modalError">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key='error.give.initiative.help.title'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                	<span aria-hidden="true"><span class="icon-multiply"></span></span>
                </button>
            </div>
            <div class="pro-wrapper">
                <h4></h4>
                <div class="centerButtonValidation">
                    <input id="<portlet:namespace />buttonConfirm" type="submit" class="pro-btn-yellow" value=<liferay-ui:message key="button.give.initiative.help.ok"/> />
                </div>
            </div>
        </div>
    </div>
</div>

<!-- CONFIRMATION QUITTER SOUMISSION INITIATIVE -->
<div class="pro-modal pro-bloc-pcs-form fade" id="<portlet:namespace />modalQuit" tabindex="-1" role="dialog" aria-labelledby="<portlet:namespace />modalQuit">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key='give.initiative.help.quit.title'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                	<span aria-hidden="true"><span class="icon-multiply"></span></span>
                </button>
            </div>
            <div class="pro-wrapper">
                <h4><liferay-ui:message key='give.initiative.help.quit.text'/></h4>
                <div class="centerButtonValidation">
                    <input id="<portlet:namespace />buttonConfirm" type="submit" class="pro-btn-yellow" value=<liferay-ui:message key="give.initiative.help.quit.button"/> />
                </div>
            </div>
        </div>
    </div>
</div>

<!-- CONFIRMATION SUPPRESSION D'AIDE INITIATIVE -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalRemoveInitiativeHelp" tabindex="-1" role="dialog" aria-labelledby="modalRemoveInitiativeHelp">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key='remove.initiative.help.quit.title'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                	<span aria-hidden="true"><span class="icon-multiply"></span></span>
                </button>
            </div>
            <div class="pro-wrapper">
                <h4><liferay-ui:message key='remove.initiative.help.quit.text'/></h4>
                <div class="centerButtonValidation">
                    <input id="<portlet:namespace />buttonSubmitRemove" type="submit" class="pro-btn-yellow" value=<liferay-ui:message key="remove.initiative.help.quit.button"/> />
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
	
	// Variables tempons des informations utilisateur et contexte namespace
	var namespace = "<portlet:namespace />";
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
    	resetValues();
        $("#<portlet:namespace />modalConfirm").modal('hide');
        $("#<portlet:namespace />modalError").modal('hide');
        $("#<portlet:namespace />checkboxSaveInfo").hide();
    });

    /*
	* Lors du click sur le bouton de vote
	*/
	function sendInitiativeHelp () {
    	
        event.preventDefault();
        var response = validateForm();
        
        if (response){
        	var entryId = $("#<portlet:namespace />entryId").val();
            var initiativeHelpMessage = $("#<portlet:namespace />initiativeHelpMessage").val();
            var initiativeHelpTypeIds = getHelpTypeIds();
            var lastname = $("#<portlet:namespace />lastname").val();
            var firstname = $("#<portlet:namespace />firstname").val();
            var address = $("#<portlet:namespace />address").val();
            var city = $("#<portlet:namespace />city").val();
            var postalCode = $("#<portlet:namespace />postalcode").val();
            var birthday = $("#<portlet:namespace />birthday").val();
            var phone = $("#<portlet:namespace />phone").val();
            var mobile = $("#<portlet:namespace />mobile").val();
            var email = $("#<portlet:namespace />mail").val();
            var saveInfo = $("#<portlet:namespace />saveInfo").is(":checked");
            
            AUI().use('aui-io-request', function(A) {
                var uploadForm = A.one("#<portlet:namespace />uploadForm");
                try {
                    A.io.request('${giveInitiativeHelpURL}', {
                        method : 'POST',
                        dataType: 'json',
                        data:{
                        	<portlet:namespace/>entryId : 					entryId,
                            <portlet:namespace/>initiativeHelpMessage: 		initiativeHelpMessage,
                            <portlet:namespace/>initiativeHelpTypeIds: 		initiativeHelpTypeIds,
                            <portlet:namespace/>address:					address,
                            <portlet:namespace/>city: 						city,
                            <portlet:namespace/>postalcode: 				postalCode,
                            <portlet:namespace/>phone: 						phone,
                            <portlet:namespace/>mobile:	 					mobile,
                            <portlet:namespace/>birthday: 					birthday,
                            <portlet:namespace />saveinfo: 					saveInfo,
                            <portlet:namespace />lastname: 					lastname,
                            <portlet:namespace />firstname: 				firstname,
                            <portlet:namespace />email: 					email
                        },
                        on: {
                            complete: function(e) {
                                // var data = this.get('responseData');
                                var data = JSON.parse(e.details[1].responseText);
                                if(data.result){
                                    $("#modalGiveInitiativeHelp").modal('hide');
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
                                    
                                    if(data.cmd === "send-initiative-help") {
                                    	$('a[data-target="#modalGiveInitiativeHelp"]').toggleClass("active")
                                    													.text("Aide proposee")
                                    													.attr("data-target", "#modalRemoveInitiativeHelp");
                                    } else {
                                    	$('a[data-target="#modalRemoveInitiativeHelp"]').toggleClass("active")
                                    													.text("Proposer mon aide")
                                    													.attr("data-target", "#modalGiveInitiativeHelp");
                                    }
                                    
                                    $("#<portlet:namespace />modalConfirm").modal('show');
                                    resetValues();
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

    $("#<portlet:namespace />modalConfirm #<portlet:namespace />buttonConfirm").click(function(event){
        $("#<portlet:namespace />modalConfirm").modal('hide');
    });
	
    $("#<portlet:namespace />modalError #<portlet:namespace />buttonConfirm").click(function(event){
        $("#<portlet:namespace />modalError").modal('hide');
    });

    /*
	* Reinitialise le formulaire avec les informations les plus fraiches
	*/
    function resetValues(){
    	// Champs entite
        $("#<portlet:namespace />initiativeHelpMessage").val("");
        $("#<portlet:namespace />helpType1").prop('checked', true);
        $("#<portlet:namespace />helpType2").prop('checked', false);
        $("#<portlet:namespace />helpType3").prop('checked', false);
        $("#<portlet:namespace />helpType4").prop('checked', false);
        
     	// Champs informations utilisateur
        $("#<portlet:namespace />address").val(saved_address);
        $("#<portlet:namespace />photo").val("");
        $("#<portlet:namespace />video").val("");
        $("#<portlet:namespace />postalcode").val(saved_zipCode);
        $("#<portlet:namespace />phone").val(saved_phone);
        $("#<portlet:namespace />mobile").val(saved_mobile);
        $("#<portlet:namespace />birthday").val(saved_dateNaiss);
        $("#<portlet:namespace />city").val(saved_city);
        
     	// Chebox de conditions et de sauvegade des informations
     	$("#<portlet:namespace />checkboxSaveInfo #<portlet:namespace />saveInfo").prop('checked', false);
        $("#<portlet:namespace />checkboxSaveInfo").hide();
        $("#<portlet:namespace />legalage").prop("checked", false);
        $("#<portlet:namespace />cnil").prop("checked", false);
       
    }
    
    /**
    * Retourne la liste des id de types d'aide selectionnes
    */
    function getHelpTypeIds() {
    	var typeIds = "";
    	var id = "";
    	$('#<portlet:namespace />helpTypes input:checked').each(function() {
    		id = $(this).val()
    		typeIds += typeIds === "" ? id : "," + id;
    	});
    	return typeIds;
    }
	
    /*
	* Affiche la demande de sauvegarde des informations dans Publik
	*/
    function checkValues(){
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
    function validateForm()
    {
        var result = true;
        
        var message = $("#<portlet:namespace />message").val();
        var typeHelpIds = getHelpTypeIds();
        var city = $("#<portlet:namespace />city").val();
        var address = $("#<portlet:namespace />address").val();
        var postalcode = $("#<portlet:namespace />postalcode").val();
        var legalage = $("#<portlet:namespace />legalage").is(":checked");
        var cnil = $("#<portlet:namespace />cnil").is(":checked");
        var photo = $("#<portlet:namespace />photo").val();
        var regex = new RegExp("^(([0-8][0-9])|(9[0-5]))[0-9]{3}$");

        if (message===null || message===""){
            $("#<portlet:namespace />message").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#<portlet:namespace />message").css({ "box-shadow" : "" });
        
        if (message===null || message===""){
            $("#<portlet:namespace />message").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#<portlet:namespace />message").css({ "box-shadow" : "" });

        if (city===null || city===""){
            $("#<portlet:namespace />city").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#<portlet:namespace />city").css({ "box-shadow" : "" });

        if (address===null || address===""){
            $("#<portlet:namespace />address").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#<portlet:namespace />address").css({ "box-shadow" : "" });

        if (postalcode===null || postalcode===""){
            $("#<portlet:namespace />postalcode").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else if(!regex.test(postalcode)){
            $("#<portlet:namespace />postalcode").css({ "box-shadow" : "0 0 10px #CC0000" });
            alert("Merci de respecter la syntaxe d'un code postal");
            result = false;
        }
        else $("#<portlet:namespace />postalcode").css({ "box-shadow" : "" });

        if (!legalage)
            result = false;

        if (!cnil)
            result = false;

        if (!result)
            $("#sendalert").removeClass("hidden");
        else $("#sendalert").addClass("hidden");
        
        return result;
    }
    
    /**
     * Lors de la demande de soumission d'une aide
     */
    $("#<portlet:namespace />buttonSubmit").click(function(event){
    	sendInitiativeHelp();
    });
    
    /**
    * Lors de la soumission d'une demande de retrait d'une aide
    */
    $("#<portlet:namespace />buttonSubmitRemove").click(function(event){
    	resetValues();
    	
    	$("#<portlet:namespace />initiativeHelpMessage").val("Remove");
    	$("#<portlet:namespace />legalage").prop("checked", true);
        $("#<portlet:namespace />cnil").prop("checked", true);
    	
        $("#modalRemoveInitiativeHelp").modal('hide');
    	sendInitiativeHelp();
    });
    
</script>