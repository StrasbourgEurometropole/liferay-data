<%@ include file="/project-popup-init.jsp" %>

<portlet:resourceURL id="giveBudgetSupport" var="giveBudgetSupportURL">
</portlet:resourceURL>

<portlet:resourceURL id="removeBudgetSupport" var="removeBudgetSupportURL">
</portlet:resourceURL>

<!-- MODAL DE SOUTIEN D'UN BUDGET PARTICIPATIF -->
<!-- HTML pour la modal de soutien d'un budget participatif -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalSupport" tabindex="-1" role="dialog" aria-labelledby="modalSupport">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
        
            <div class="pro-modal-top">
                <h3><liferay-ui:message key="modal.givebudgetsupport.title"/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                	<span aria-hidden="true"><span class="icon-multiply"></span></span>
                </button>
            </div>

            <form id="form-give-budget-support">
                <div class="pro-wrapper">
                
                	<!-- Champs : nom, prenom, date de naissance -->
                    <div class="pro-row">
                        <div class="form-group form-triple">
                            <aui:input name="username" id="supportUsername"
                            	label="modal.user.username" disabled="true" 
                            	value="${userConnected.get('last_name')}"
                            />
				            <aui:input type="hidden" name="lastname" value="${userConnected.get('last_name')}"/>
                        </div>
                        <div class="form-group form-triple">
                            <aui:input name="firstname" id="supportFirstname" 
                            	label="modal.user.firstname" disabled="true" 
                            	value="${userConnected.get('first_name')}"
                            />
                        </div>
                        <div class="form-group form-triple">
                            <c:if test="${userConnected.get('birthdate') ne 'null'}">
					            <fmt:parseDate pattern="yyyy-MM-dd" value="${userConnected.get('birthdate')}" var="parsedStatusDate" />
	                            <fmt:formatDate value="${parsedStatusDate}" var="formattedDate" type="date" pattern="dd/MM/yyyy" />
	                        </c:if>
                            <aui:input id="supportBirthday" name="birthday" 
                            	cssClass="frm_date" label="modal.user.birthday" 
                            	required="true" maxlength="10" placeholder="jj/mm/aaaa" 
                            	onInput="checkSupportValues();" onChange="checkSupportValues();"
                            />
                        </div>
                    </div>
                    
                    <!-- Champ : email -->
                    <div class="form-group">
                        <aui:input type="email" id="supportEmail" 
                        	name="mail" disabled="true" 
                        	label="modal.user.mail" 
                        	value="${userConnected.get('email')}"
                        />
                    </div>
                    
                    <!-- Champs : adresse -->
                    <div class="pro-row">
                        <div class="form-group form-half">
				            <aui:input id="supportAddress" name="address" 
					            label="modal.user.address" required="true" 
					            onInput="checkSupportValues();"
					        />
                        </div>
                        <div class="form-group form-half">
                            <div class="form-city">
				                <aui:input id="supportCity" name="city" 
				                	label="modal.user.city" required="true" 
				                	placeholder="Strasbourg" maxlength="256" 
				                	onInput="checkSupportValues();"
				                />
                            </div>
                            <div class="form-code">
                                <aui:input type="number" id="supportPostalCode" name="postalcode" 
                                	label="modal.user.postalcode" required="true" 
                                	placeholder="67XXX" maxlength="5" 
                                	onInput="checkSupportValues();"
                                />
                            </div>
                        </div>
                    </div>
                    
                    <!-- Champs : telephones -->
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <aui:input type="text" id="supportPhone" name="phone" 
                            	label="modal.user.phone" placeholder="0611111111" 
                            	maxlength="20" onInput="checkSupportValues();"
                            />
                        </div>
                        <div class="form-group form-half">
                            <aui:input type="text" id="supportMobile" name="mobile" 
                            	label="modal.user.mobile" placeholder="0611111111" 
                            	maxlength="20" onInput="checkSupportValues();"
                            />
                        </div>
                    </div>
                    
                    <!-- Champ : demande de la mise ÃÂÃÂÃÂÃÂ  jour des informations dans publik -->
                    <div class="form-group form-checkbox" id="checkboxSupportSaveInfo">
                        <div>
                            <input type="checkbox" name="<portlet:namespace />saveinfo" id="supportsave-info" value="save-info">
                            <label for="supportsave-info"><liferay-ui:message key="modal.save.info"/></label>
                        </div>
                    </div>
                    
                </div>
                
                <!-- Condition de soumission -->
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="giveSupportLegalAge" value="legalage">
                        <label for="giveSupportLegalAge" class="fontWhite">
                            <liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_legalage"/>
                        </label>
                    </div>
                </div>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="giveBudgetSupportCondition1" value="giveBudgetSupportCondition1">
                        <label for="giveBudgetSupportCondition1"  class="fontWhite">
                        	<liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_supportCondition1"/>
                        </label>
                    </div>
                </div>
                
                <!-- Champ cache : ID -->
                <input type="hidden" id="<portlet:namespace />entryId" name="entryId" value="${entryId}"/>
                
                <!-- Alert d'erreur -->
                <div id="supportAlert" class="hidden pro-info-supp alertMessage"><liferay-ui:message key="modal.alert"/></div>
                <div id="supportAlertCity" class="hidden pro-info-supp alertMessage"><liferay-ui:message key="modal.alert.city"/></div>
                <div id="supportAlertPostalCode" class="hidden pro-info-supp alertMessage"><liferay-ui:message key="modal.alert.postalcode"/></div>
                <div id="supportAlertLegalAge" class="hidden pro-info-supp alertMessage"><liferay-ui:message key="modal.alert.legalage"/></div>
                
                <!-- Bonton de soumission -->
                <div class="pro-form-submit">
                    <button id="submitBudgetSupport" type="submit" class="btn btn-default">Voter</button>
                </div>
                
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- CONFIRMATION NOUVEAU SOUTIEN -->
<!-- HTML pour la modal de confirmation d'un nouveau soutien -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalConfirmSupport" tabindex="-1" role="dialog" aria-labelledby="modalConfirmSuppot">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key='modal.givebudgetsupport.confirm-support'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                	<span aria-hidden="true"><span class="icon-multiply"></span></span>
                </button>
            </div>
            <div class="pro-wrapper">
                <h4><liferay-ui:message key='modal.givebudgetsupport.give-support-ok'/></h4>
                <div class="centerButtonValidation">
                    <input id="buttonConfirm" type="submit" class="pro-btn-yellow" value=<liferay-ui:message key="modal.givebudgetsupport.button-support-ok"/> />
                </div>
            </div>
        </div>
    </div>
</div>

<!-- ERREUR NOUVEAU SOUTIEN -->
<!-- HTML pour la modal d'erreur d'un nouveau soutien -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalErrorSupport" tabindex="-1" role="dialog" aria-labelledby="modalErrorSupport">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key='modal.givebudgetsupport.error-support'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>
            <div class="pro-wrapper">
                <h4></h4>
                <div class="centerButtonValidation">
                    <input id="buttonConfirm" type="submit" class="pro-btn-yellow" value=<liferay-ui:message key="modal.givebudgetsupport.button-support-ok"/> />
                </div>
            </div>
        </div>
    </div>
</div>

<!-- CONFIRMATION QUITTER SOUTIEN -->
<!-- HTML pour la modal de quitter le formulaire de budget -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalQuitSupport" tabindex="-1" role="dialog" aria-labelledby="modalQuitSupport">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key='modal.givebudgetsupport.quit-support'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>
            <div class="pro-wrapper">
                <h4><liferay-ui:message key='modal.givebudgetsupport.give-budget-support-quit'/></h4>
                <div class="centerButtonValidation">
                    <input id="buttonConfirm" type="submit" class="pro-btn-yellow" value=<liferay-ui:message key="modal.givebudgetsupport.button-support-quit"/> />
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
	
	var namespace = "<portlet:namespace />";
	
	// Sauvegarde des informations de base de l'utilisateur courrant
	var saved_dateNaiss = "${formattedDate}";
	var saved_address = "${userConnected.get('address')}";
	var saved_city = "${userConnected.get('city')}";
	var saved_zipCode = "${userConnected.get('zipcode')}";
	var saved_phone = "${userConnected.get('phone')}" != 'null' ? "${userConnected.get('phone')}" : " ";
	var saved_mobile = "${userConnected.get('mobile')}" != 'null' ? "${userConnected.get('mobile')}" : " ";
	
	/*
	* Reinitialise le formulaire avec les informations les plus fraiches
	*/
	function resetValues() {
		// Champs informations utilisateur
		$("#"+namespace+"supportBirthday").val(saved_dateNaiss);
        $("#"+namespace+"supportAddress").val(saved_address);
        $("#"+namespace+"supportCity").val(saved_city);
        $("#"+namespace+"supportPostalCode").val(saved_zipCode);
        $("#"+namespace+"supportPhone").val(saved_phone);
        $("#"+namespace+"supportMobile").val(saved_mobile);
        
        // Chebox de conditions et de sauvegade des informations
        $('#checkboxSupportSaveInfo #save-info').prop('checked', false);
        $('#checkboxSupportSaveInfo').hide();
        $("#giveSupportLegalAge").prop("checked", false);
        $("#giveBudgetSupportCondition1").prop("checked", false);
    }
	
	/*
	* Affiche la demande de sauvegarde des informations dans Publik
	*/
	function checkSupportValues(){
        if($("#"+namespace+"supportBirthday").val() != saved_dateNaiss || $("#"+namespace+"supportAddress").val() != saved_address 
        		|| $("#"+namespace+"supportCity").val() != saved_city || $("#"+namespace+"supportPostalCode").val() != saved_zipCode
        		|| $("#"+namespace+"supportPhone").val() != saved_phone || $("#"+namespace+"supportMobile").val() != saved_mobile){
            $('#checkboxSupportSaveInfo #save-info').prop('checked', true);
            $('#checkboxSupportSaveInfo').show();
        } else {
            $('#checkboxSupportSaveInfo #save-info').prop('checked', false);
            $('#checkboxSupportSaveInfo').hide();
        }
    }
	
	/*
	* Retourne l'age a partir d'une date donnee
	*/
	function getAge(dateString) {
        var from = dateString.split("/");
        var today = new Date();
        var birthDate = new Date(from[2],from[1]-1,from[0]);
        var age = today.getFullYear() - birthDate.getFullYear();
        var m = today.getMonth() - birthDate.getMonth();
        if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
          age--;
        }
        return age;
    }
	
	/*
	* Verifie la conformite des elements avant l'envoie du formulaire
	*/
	function validateForm() {
		// Valeur de retour "juste" par defaut
        var isValid = true;

		// Recuperation des inforamtions du formulaire
        var birthday = $("#"+namespace+"supportBirthday").val();
        var age = getAge(birthday);
        var city = $("#"+namespace+"supportCity").val();
        var address = $("#"+namespace+"supportAddress").val();
        var postalcode = $("#"+namespace+"supportPostalCode").val();
        var legalAge = $("#giveSupportLegalAge").is(":checked");
        var condition1 = $("#giveBudgetSupportCondition1").is(":checked");
        
        // Regex de confirmite des champs
        var regex = new RegExp("^(([0-8][0-9])|(9[0-5]))[0-9]{3}$");

        // Champ : date de naissance
        if (birthday==null || birthday=="") {
            $("#"+namespace+"supportBirthday").css({ "box-shadow" : "0 0 10px #CC0000" });
            isValid = false;
        } else $("#"+namespace+"supportBirthday").css({ "box-shadow" : "" });
        
        if(age<16){
            $("#supportAlertLegalAge").removeClass("hidden");
            $("#"+namespace+"birthday").css({ "box-shadow" : "0 0 10px #CC0000" });
            isValid = false;
        }
        else $("#supportAlertLegalAge").addClass("hidden");

     	// Champ : ville
        if (city==null || city=="") {
            $("#"+namespace+"supportCity").css({ "box-shadow" : "0 0 10px #CC0000" });
            isValid = false;
        } else $("#"+namespace+"supportCity").css({ "box-shadow" : "" });

     	// Champ : adresse
        if (address==null || address=="") {
            $("#"+namespace+"supportAddress").css({ "box-shadow" : "0 0 10px #CC0000" });
            isValid = false;
        } else $("#"+namespace+"supportAddress").css({ "box-shadow" : "" });

     	// Champ : code postal
        if (postalcode==null || postalcode=="") {
            $("#"+namespace+"supportPostalCode").css({ "box-shadow" : "0 0 10px #CC0000" });
            isValid = false;
        } else if (!regex.test(postalcode)) {
            $("#"+namespace+"supportPostalCode").css({ "box-shadow" : "0 0 10px #CC0000" });
            alert("Merci de respecter la syntaxe d'un code postal");
            isValid = false;
        } else $("#"+namespace+"supportPostalCode").css({ "box-shadow" : "" });

        if (!legalAge)
        	isValid = false;
        
        if (!condition1)
        	isValid = false;

        if (!isValid)
            $("#supportAlert").removeClass("hidden");
        else $("#supportAlert").addClass("hidden");
        
        return isValid;
    }
	
	/*
	* Appel a la soumission du formulaire d'un nouveau soutien
	*/
	function sendSupport() {
		// Verification du formulaire
        var isValid = validateForm();
        
        if (isValid) {
        	// Recuperation des informations
        	var entryId = $("#"+namespace+"entryId").val();
            var birthdayValue = $("#"+namespace+"supportBirthday").val();
            var addressValue = $("#"+namespace+"supportAddress").val();
            var cityValue = $("#"+namespace+"supportCity").val();
            var postalcodeValue = $("#"+namespace+"supportPostalCode").val();
            var phoneValue = $("#"+namespace+"supportPhone").val();
            var mobileValue = $("#"+namespace+"supportMobile").val();
            var saveInfoValue = $("#save-info").is(":checked");
            
            // Requete Ajax
            AUI().use('aui-io-request', function(A) {
                A.io.request('${giveBudgetSupportURL}', {
                    method : 'POST',
                    dataType: 'json',
                    data:{
                    	<portlet:namespace/>entryId : entryId,
                        <portlet:namespace/>birthday : birthdayValue,
                        <portlet:namespace/>address : addressValue,
                        <portlet:namespace/>city : cityValue,
                        <portlet:namespace/>postalcode : postalcodeValue,
                        <portlet:namespace/>phone : phoneValue,
                        <portlet:namespace/>mobile : mobileValue,
                        <portlet:namespace />saveinfo : saveInfoValue,
                    },
                    on: {
                        success: function(e) {
                        	
                            var data = this.get('responseData');
                            
                            // Succes de la requete
                            if (data.result) {
                            	
                            	// Cache du formulaire
                                $('#modalSupport').modal('hide');
                            	
                            	// Sauvegarde des nouvelles informations de l'utilisateur
                                if (data.savedInfo) {
                                    saved_dateNaiss = $("#"+namespace+"supportBirthday").val();
                                    saved_city = $("#"+namespace+"supportCity").val();
                                    saved_address = $("#"+namespace+"supportAddress").val();
                                    saved_zipCode = $("#"+namespace+"supportPostalCode").val();
                                    if($("#"+namespace+"supportPhone").val() != "")
                                        saved_phone = $("#"+namespace+"supportPhone").val();
                                    if($("#"+namespace+"supportMobile").val() != "")
                                        saved_mobile = $("#"+namespace+"supportMobile").val();
                                }
                            	
                             	// Recuperation des informations de vote de l'utilisateur et modifications de l'interface
                                if (data.updatedSupportsInfo) {
                                	// Modification des textes des labels et bouton
                                	$('#nbUserSupports').text(5 - data.updatedSupportsInfo.nbUserSupports);
                                	$('#nbUserEntrySupports').text(data.updatedSupportsInfo.nbUserEntrySupports);
                                	$('#nbEntrySupports').text(data.updatedSupportsInfo.nbEntrySupports);
                                	
                                	// Cache du bouton si plus aucun vote disponible
                                	if (data.updatedSupportsInfo.nbUserSupports >= 5) {
                                		$("[href='#Support']").hide();
                                	}
                                	
                                	// Mise a jour du tempon data dans la balise href de la demande du vote 
                                	// notes : permet l'affichage ou non du formulaire
                                	$("[href='#Support']").data('nbsupports', data.updatedSupportsInfo.nbUserEntrySupports);
                                	
                                	// Affichage dans tous les cas du boutons de retrait
                                	$("[href='#RemoveSupport']").show();
                                }
                                resetValues();
                             	// Modal de confirmation de succes
                                $('#modalConfirmSupport').modal('show');
                            }
                          
                            // Erreur ou refus de la requete
                            else {
                                $("#modalErrorSupport h4").text(data.message);
                                $('#modalErrorSupport').modal('show');
                            }
                            
                        }
                    }
                });
            });
        }
	}
	
	/*
	* Appel a la soumission d'un retrait de soutient
	*/
	function removeSupport() {
        
		// Recuperation des informations
		var entryId = $("#"+namespace+"entryId").val();
       
		// Requete Ajax
		AUI().use('aui-io-request', function(A) {
			A.io.request('${removeBudgetSupportURL}', {
				method : 'POST',
				dataType: 'json',
				data:{
					<portlet:namespace/>entryId : entryId,
				},
				on: {
					success: function(e) {
                       var data = this.get('responseData');
                       
                       // Succes de la requete
                       if (data.result) {
                  
                        	// Recuperation des informations de vote de l'utilisateur et modifications de l'interface
                           if (data.updatedSupportsInfo) {
								// Modification des textes des labels et bouton
	                           	$('#nbUserSupports').text(5 - data.updatedSupportsInfo.nbUserSupports);
	                           	$('#nbUserEntrySupports').text(data.updatedSupportsInfo.nbUserEntrySupports);
	                           	$('#nbEntrySupports').text(data.updatedSupportsInfo.nbEntrySupports);
	                           	
	                           	// Cache du bouton de retrait si aucun vote pour cette entite
	                           	if (data.updatedSupportsInfo.nbUserEntrySupports < 1) {
	                           		$("[href='#RemoveSupport']").hide();
	                           	}
	                           	
	                           	// Mise a jour du tempon data dans la balise href de la demande du vote 
	                           	// notes : permet l'affichage ou non du formulaire
	                           	$("[href='#Support']").data('nbsupports', data.updatedSupportsInfo.nbUserEntrySupports);
	                           	
	                           	// Affichage dans tous les cas d'ajout
	                           	$("[href='#Support']").show();
							}
                        	
                           resetValues();
                        	
                        	// Modal de confirmation de succes
							$('#modalConfirmSupport').modal('show');
						}
                     
						// Erreur ou refus de la requete
						else {
							$("#modalErrorSupport h4").text(data.message);
							$('#modalErrorSupport').modal('show');
                       }
                       
                   }
               }
           });
        });
	}
	
	/*
	* Lors du chargement de la page
	*/
	$(document).ready(function(){
        $('#modalConfirmSupport').modal('hide');
        $('#modalErrorSupport').modal('hide');
        $('#modalQuitSupport').modal('hide');
        $('#checkboxSupportSaveInfo').hide();
    });
	
	/*
	* Lors du click sur le bouton de vote
	*/
	$(document).on("click", "[href='#Support']", function(event) {
		event.preventDefault();
		resetValues();
		// Si il y'a deja eu un vote de l'utilisateur, pas de formulaire
		if (parseInt($("[href='#Support']").data('nbsupports'), 10)  >= 1) {
			// Autocoche des checkbox pour valider la soumission
			$('#checkboxSupportSaveInfo #save-info').prop('checked', false);
			$('#giveSupportLegalAge').prop('checked', true);
			$('#giveBudgetSupportCondition1').prop('checked', true);
			
			sendSupport();
		} else {
			$("#modalSupport").modal('show');
		}
	});
	
	/*
	* Lors du click sur le bouton de retrait de vote
	*/
	$(document).on("click", "[href='#RemoveSupport']", function(event) {
		event.preventDefault();
		
		removeSupport();
	});

	/*
	* Lors d'un click sur les boutons confirmer des modals
	*/
    $('#modalQuitSupport #buttonConfirm, #modalConfirmSupport #buttonConfirm, #modalErrorSupport #buttonConfirm').click(function(event){
        $(this).parents('.pro-modal').modal('hide');
    });
	
	/*
	* Lors du click sur la soumission d'une demande de soutien
	*/
	 $("#submitBudgetSupport").click(function(event){
	 	event.preventDefault();
	 	sendSupport();
	 });
	
</script>