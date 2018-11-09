<%@ include file="/project-popup-init.jsp" %>

<portlet:resourceURL id="giveBudgetSupport" var="giveBudgetSupportURL">
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
                
                	<!-- Champs : nom, prénom, date de naissance -->
                    <div class="pro-row">
                        <div class="form-group form-triple">
                            <aui:input name="username" disabled="true" 
                            	label="modal.user.username" required="true" 
                            	value="${userConnected.get('last_name')}"
                            />
				            <aui:input type="hidden" name="lastname" value="${userConnected.get('last_name')}"/>
                        </div>
                        <div class="form-group form-triple">
                            <aui:input name="firstname" disabled="true" 
                            	label="modal.user.firstname" required="true"
                            	value="${userConnected.get('first_name')}"
                            />
                        </div>
                        <div class="form-group form-triple">
                            <c:if test="${userConnected.get('birthdate') ne 'null'}">
					            <fmt:parseDate pattern="yyyy-MM-dd" value="${userConnected.get('birthdate')}" var="parsedStatusDate" />
	                            <fmt:formatDate value="${parsedStatusDate}" var="formattedDate" type="date" pattern="dd/MM/yyyy" />
	                        </c:if>
                            <aui:input id="supportbirthday" name="birthday" 
                            	cssClass="frm_date" label="modal.user.birthday" 
                            	required="true" maxlength="10" placeholder="jj/mm/aaaa" 
                            	onInput="checkSignValues();" onChange="checkSignValues();"
                            />
                        </div>
                    </div>
                    
                    <!-- Champ : email -->
                    <div class="form-group">
                        <aui:input type="email" id="supportmail" 
                        	name="mail" disabled="true" 
                        	label="modal.user.mail"  required="true" 
                        	value="${userConnected.get('email')}"
                        />
                    </div>
                    
                    <!-- Champs : adresse -->
                    <div class="pro-row">
                        <div class="form-group form-half">
				            <aui:input id="supportaddress" name="address" 
					            label="modal.user.address" required="true" 
					            onInput="checkSignValues();"
					        />
                        </div>
                        <div class="form-group form-half">
                            <div class="form-city">
				                <aui:input id="supportcity" name="city" 
				                	label="modal.user.city" required="true" 
				                	placeholder="Strasbourg" maxlength="256" 
				                	onInput="checkSignValues();"
				                />
                            </div>
                            <div class="form-code">
                                <aui:input id="supportpostalcode" name="postalcode" 
                                	label="modal.user.postalcode" required="true" 
                                	placeholder="67XXX" maxlength="5" 
                                	onInput="checkSignValues();"
                                />
                            </div>
                        </div>
                    </div>
                    
                    <!-- Champs : telephones -->
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <aui:input type="number" id="supportphone" name="phone" 
                            	label="modal.user.phone" placeholder="0611111111" 
                            	maxlength="20" onInput="checkSignValues();"
                            />
                        </div>
                        <div class="form-group form-half">
                            <aui:input type="number" id="supportmobile" name="mobile" 
                            	label="modal.user.mobile" placeholder="0611111111" 
                            	maxlength="20" onInput="checkSignValues();"
                            />
                        </div>
                    </div>
                    
                    <!-- Champ : demande de la mise à jour des informations dans publik -->
                    <div class="form-group form-checkbox" id="checkboxSignSaveInfo">
                        <div>
                            <input type="checkbox" name="<portlet:namespace />saveinfo" id="signsave-info" value="save-info">
                            <label for="signsave-info"><liferay-ui:message key="modal.save.info"/></label>
                        </div>
                    </div>
                    
                </div>
                
                <!-- Condition de soumission -->
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="giveBudgetSupportCondition1" value="giveBudgetSupportCondition1">
                        <label for="giveBudgetSupportCondition1"  class="fontWhite">
                        </label>
                    </div>
                </div>
                
                <!-- Champ caché : ID -->
                <input type="hidden" name="<portlet:namespace />entryId" value="${entryId}"/>
                
                <!-- Alert d'erreur -->
                <div id="signalert" class="hidden pro-info-supp alertMessage"><liferay-ui:message key="modal.alert"/></div>
                <div id="signalertcity" class="hidden pro-info-supp alertMessage"><liferay-ui:message key="modal.alert.city"/></div>
                <div id="signalertPostalCode" class="hidden pro-info-supp alertMessage"><liferay-ui:message key="modal.alert.postalcode"/></div>
                
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
                <h3><liferay-ui:message key='confirm-support'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                	<span aria-hidden="true"><span class="icon-multiply"></span></span>
                </button>
            </div>
            <div class="pro-wrapper">
                <h4><liferay-ui:message key='give-support-ok'/></h4>
                <div class="centerButtonValidation">
                    <input id="buttonConfirm" type="submit" class="pro-btn-yellow" value=<liferay-ui:message key="button-support-ok"/> />
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
                <h3><liferay-ui:message key='error-support'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>
            <div class="pro-wrapper">
                <h4></h4>
                <div class="centerButtonValidation">
                    <input id="buttonConfirm" type="submit" class="pro-btn-yellow" value=<liferay-ui:message key="button-support-ok"/> />
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
                <h3><liferay-ui:message key='quit-support'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>
            <div class="pro-wrapper">
                <h4><liferay-ui:message key='give-budget-support-quit'/></h4>
                <div class="centerButtonValidation">
                    <input id="buttonConfirm" type="submit" class="pro-btn-yellow" value=<liferay-ui:message key="button-support-quit"/> />
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
		$("#"+namespace+"birthday").val(saved_dateNaiss);
        $("#"+namespace+"address").val(saved_address);
        $("#"+namespace+"city").val(saved_city);
        $("#"+namespace+"postalcode").val(saved_zipCode);
        $("#"+namespace+"phone").val(saved_phone);
        $("#"+namespace+"mobile").val(saved_mobile);
        
        // Chebox de conditions et de sauvegade des informations
        $('#checkboxSaveInfo #save-info').prop('checked', false);
        $('#checkboxSaveInfo').hide();
        $("#giveBudgetSupportCondition1").prop("checked", false);
    }
	
	/*
	* Affiche la demande de sauvegarde des informations dans Publik
	*/
	function checkValues(){
        if($("#"+namespace+"birthday").val() != saved_dateNaiss || $("#"+namespace+"address").val() != saved_address 
        		|| $("#"+namespace+"city").val() != saved_city || $("#"+namespace+"postalcode").val() != saved_zipCode 
        		|| $("#"+namespace+"phone").val() != saved_phone || $("#"+namespace+"mobile").val() != saved_mobile){
            $('#checkboxSaveInfo #save-info').prop('checked', true);
            $('#checkboxSaveInfo').show();
        } else {
            $('#checkboxSaveInfo #save-info').prop('checked', false);
            $('#checkboxSaveInfo').hide();
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
	function validateForm()
    {
		// Valeur de retour "juste" par defaut
        var isValid = true;

		// Recuperation des inforamtions du formulaire
        var birthday = $("#"+namespace+"birthday").val();
        var age = getAge(birthday);
        var city = $("#"+namespace+"city").val();
        var address = $("#"+namespace+"address").val();
        var postalcode = $("#"+namespace+"postalcode").val();
        var condition1 = $("#giveBudgetSupportCondition1").is(":checked");
        
        // Regex de confirmite des champs
        var regex = new RegExp("^(([0-8][0-9])|(9[0-5]))[0-9]{3}$");

        // Champ : date de naissance
        if (birthday==null || birthday=="") {
            $("#"+namespace+"birthday").css({ "box-shadow" : "0 0 10px #CC0000" });
            isValid = false;
        } else $("#"+namespace+"birthday").css({ "box-shadow" : "" });
        
        if(age<16){
            $("#filealertLegalage").removeClass("hidden");
            $("#"+namespaceSign+"birthday").css({ "box-shadow" : "0 0 10px #CC0000" });
            isValid = false;
        }
        else $("#filealertLegalage").addClass("hidden");

     	// Champ : ville
        if (city==null || city=="") {
            $("#"+namespace+"city").css({ "box-shadow" : "0 0 10px #CC0000" });
            isValid = false;
        } else $("#"+namespace+"city").css({ "box-shadow" : "" });

        if (city.toLowerCase()!=="strasbourg") {
            $("#filealertcity").removeClass("hidden");
            $("#"+namespaceSign+"city").css({ "box-shadow" : "0 0 10px #CC0000" });
            isValid = false;
        } else $("#filealertcity").addClass("hidden");

     	// Champ : adresse
        if (address==null || address=="") {
            $("#"+namespace+"address").css({ "box-shadow" : "0 0 10px #CC0000" });
            isValid = false;
        } else $("#"+namespace+"address").css({ "box-shadow" : "" });

     	// Champ : code postal
        if (postalcode==null || postalcode=="") {
            $("#"+namespace+"postalcode").css({ "box-shadow" : "0 0 10px #CC0000" });
            isValid = false;
        } else if (!regex.test(postalcode)) {
            $("#"+namespace+"postalcode").css({ "box-shadow" : "0 0 10px #CC0000" });
            alert("Merci de respecter la syntaxe d'un code postal");
            isValid = false;
        } else $("#"+namespace+"postalcode").css({ "box-shadow" : "" });

        if (postalcode!=="67000" && postalcode!=="67100" && postalcode!=="67200") {
            $("#filealertPostalCode").removeClass("hidden");
            $("#"+namespaceSign+"postalcode").css({ "box-shadow" : "0 0 10px #CC0000" });
            isValid = false;
        } else $("#filealertPostalCode").addClass("hidden");

        if (!condition1)
        	isValid = false;

        if (!result)
            $("#sendalert").removeClass("hidden");
        else $("#sendalert").addClass("hidden");
        
        return isValid;
    }
	
	/*
	* Lors du click sur  la soumission d'une demande de soutien
	*/
	 $("#submitBudgetSupport").click(function(event){
	        event.preventDefault();

	        // Verification du formulaire
	        var isValid = validateForm();
	        
	        if (isValid) {
	        	// Recuperation des informations
	            var birthdayValue = $("#"+namespace+"birthday").val();
	            var addressValue = $("#"+namespace+"address").val();
	            var cityValue = $("#"+namespace+"city").val();
	            var postalcodeValue = $("#"+namespace+"postalcode").val();
	            var phoneValue = $("#"+namespace+"phone").val();
	            var mobileValue = $("#"+namespace+"mobile").val();
	            var saveInfoValue = $("#save-info").is(":checked");
	            
	            // Requete Ajax
	            AUI().use('aui-io-request', function(A) {
	                A.io.request('${giveBudgetSupportURL}', {
	                    method : 'POST',
	                    dataType: 'json',
	                    data:{
	                        <portlet:namespace/>birthday:birthdayValue,
	                        <portlet:namespace/>address:addressValue,
	                        <portlet:namespace/>city:cityValue,
	                        <portlet:namespace/>postalcode:postalcodeValue,
	                        <portlet:namespace/>phone:phoneValue,
	                        <portlet:namespace/>mobile:mobileValue,
	                        <portlet:namespace />saveinfo:saveInfoValue,
	                    },
	                    on: {
	                        success: function(e) {
	                        	
	                            var data = this.get('responseData');
	                            
	                            // Succes de la requete
	                            if(data.result){
	                                $('#modalSupport').modal('hide');
	                                if(data.savedInfo){
	                                    saved_dateNaiss = $("#"+namespace+"birthday").val();
	                                    saved_city = $("#"+namespace+"city").val();
	                                    saved_address = $("#"+namespace+"address").val();
	                                    saved_zipCode = $("#"+namespace+"postalcode").val();
	                                    if($("#"+namespace+"phone").val() != "")
	                                        saved_phone = $("#"+namespace+"phone").val();
	                                    if($("#"+namespace+"mobile").val() != "")
	                                        saved_mobile = $("#"+namespace+"mobile").val();
	                                }
	                                $('#modalConfirmSupport').modal('show');
	                            } 
	                            
	                            // Erreur ou refus de la requete
	                            else{
	                                $("#modalErrorSupport h4").text(data.message);
	                                $('#modalErrorSupport').modal('show');
	                            }
	                            
	                        }
	                    }
	                });
	             });
	        }
	    });
	
</script>