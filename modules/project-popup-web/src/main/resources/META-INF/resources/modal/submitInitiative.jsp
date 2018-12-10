<%@ include file="/project-popup-init.jsp" %>

<portlet:resourceURL id="submitInitiative" var="submitInitiativeURL">
</portlet:resourceURL>

<!-- DEPOSER UNE NOUVELLE INITIATIVE -->
<div class="pro-modal pro-bloc-pcs-form fade" id="<portlet:namespace />modalSubmit" tabindex="-1" role="dialog" aria-labelledby="<portlet:namespace />modalSubmit">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
        
        	<%-- Top titre du modal --%>
            <div class="pro-modal-top">
                <h3><liferay-ui:message key="modal.submit.initiative.title"/></h3>
                <button id="closingButton" type="button" class="close" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>
			
			<%-- Formulaire --%>
            <aui:form name="uploadForm" enctype="multipart/form-data">
            
            	<%-- Groupe de champs : Information initiative --%>
                <div class="pro-wrapper">
                	
					<%-- Label : Informations initiative --%>
                    <h4><liferay-ui:message key="modal.submit.initiative.information"/></h4>
                    
                    <%-- Champ : Titre --%>
                    <div class="form-group">
                        <aui:input id="title" name="title" label="modal.submit.initiative.information.title" maxlength="256" required="true" value=""/>
                    </div>
                    
                    <%-- Champ : Description --%>
                    <div class="form-group">
                        <aui:input id="description" type="textarea" name="description" required="true" label="modal.submit.initiative.information.description" value=""/>
                    </div>
                    
                    <%-- Groupe de champs : Quartiers --%>
                    <div class="pro-row">
                    	
                    	<%-- Champ : Description --%>
                        <div class="form-group form-half">
                            <label for="quartiers"><liferay-ui:message key="modal.submit.initiative.information.territoire"/></label>
                            <select id="<portlet:namespace />quartier" name="<portlet:namespace />quartier">
                                <option value="0" selected><liferay-ui:message key="modal.submit.initiative.information.territoire.town"/></option>
                                <c:forEach var="quartier" items="${quartiers}">
                                    <option value="${quartier.categoryId}">${quartier.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        
                        <%-- Champ : Lieu libre --%>
                        <div class="form-group form-half">
                            <aui:input id="place" name="place" label="modal.submit.initiative.information.place" maxlength="256" value=""/>
                        </div>
                        
                    </div>
                    
                    <%-- Groupe de champs : Lieux --%>
                    <div class="pro-row">
                    
                    	<%-- Champ : Thematique --%>
                        <div class="form-group form-half">
                            <label for="thematiques"><liferay-ui:message key="modal.submit.initiative.information.thematique"/></label>
                            <select id="<portlet:namespace />theme" name="<portlet:namespace />theme">
                                <option value="0" selected></option>
                                <c:forEach var="theme" items="${thematics}">
                                    <option value="${theme.categoryId}">${theme.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        
                        <%-- Champ : Projet --%>
                        <div class="form-group form-half">
                            <label for="projets"><liferay-ui:message key="modal.submit.initiative.information.projet"/></label>
                            <select id="<portlet:namespace />project" name="<portlet:namespace />project">
                                <option value="0" selected ></option>
                                <c:forEach var="project" items="${projects}">
                                    <option value="${project.categoryId}">${project.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        
                    </div>
                    
                    <%-- Champ : Image --%>
                    <div class="pro-row">
                        <div class="form-group form-two-tiers">
                            <span class="browsePicture input-group-btn">
                                <aui:input name="initiativePhoto" type="file" label="modal.submit.initiative.information.picture"
                                    cssClass="btn btn-default btn-choose">
							        <aui:validator name="acceptFiles">'jpg,png,jpeg'</aui:validator>
                                </aui:input>
                                <!-- Permet de recuperer l'id de l'image postee par l'utilisateur -->
                                <aui:input type="hidden" name="webImageId" />
                            </span>
                        </div>
                    </div>
                    
                    <%-- Champ : Video --%>
                    <div class="pro-row">
                        <div class="form-group form-two-tiers">
                            <aui:input id="video" name="video" label="modal.submit.initiative.information.video" maxlength="256" value=""/>
                        </div>
                    </div>
                    
                </div>
                
                <%-- Groupe de champs : Information initiative --%>
                <div class="pro-wrapper">
                
                	<%-- Label : Informations utilisateur --%>
                    <h4><liferay-ui:message key="modal.submit.initiative.user"/></h4>
                    
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
                            <liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_cnil2"/>
                        </label>
                    </div>
                </div>
                
                <div id="<portlet:namespace />alert" class="hidden pro-info-supp alertMessage"><liferay-ui:message key="modal.alert"/></div>
                
                <%-- Boutou de soumisson --%>
                <div class="pro-form-submit">
                    <button id="<portlet:namespace />buttonSubmit" type="submit" class="btn btn-default">
                    	<liferay-ui:message key="modal.submit.initiative.submit"/>
                    </button>
                </div>
                
            </aui:form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- CONFIRMATION NOUVELLE INITIATIVE -->
<div class="pro-modal pro-bloc-pcs-form fade" id="<portlet:namespace />modalConfirm" tabindex="-1" role="dialog" aria-labelledby="<portlet:namespace />modalConfirm">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key='confirm-initiative'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>
            <div class="pro-wrapper">
                <h4><liferay-ui:message key='submit-initiative-ok'/></h4>
                <div class="centerButtonValidation">
                    <input id="<portlet:namespace />buttonConfirm" type="submit" class="pro-btn" value=<liferay-ui:message key="button-petition-ok"/> />
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
                <h3><liferay-ui:message key='error-submit-initiative'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                	<span aria-hidden="true"><span class="icon-multiply"></span></span>
                </button>
            </div>
            <div class="pro-wrapper">
                <h4></h4>
                <div class="centerButtonValidation">
                    <input id="<portlet:namespace />buttonConfirm" type="submit" class="pro-btn-yellow" value=<liferay-ui:message key="button-submit-initiative-ok"/> />
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
                <h3><liferay-ui:message key='quit-submit-initiative'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                	<span aria-hidden="true"><span class="icon-multiply"></span></span>
                </button>
            </div>
            <div class="pro-wrapper">
                <h4><liferay-ui:message key='submit-initiative-quit'/></h4>
                <div class="centerButtonValidation">
                    <input id="<portlet:namespace />buttonConfirm" type="submit" class="pro-btn-yellow" value=<liferay-ui:message key="button-submit-initiative-quit"/> />
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
        $("#" + namespace + "modalConfirm").modal('hide');
        $("#" + namespace + "modalError").modal('hide');
        $("#" + namespace + "checkboxSaveInfo").hide();
    });

    /*
	* Lors du click sur le bouton de vote
	*/
    $("#"+namespace+"buttonSubmit").click(function(event){
        event.preventDefault();
        var response = validateForm();
        if (response){
            var title = $("#" + namespace + "title").val();
            var descriptionValue = $("#" + namespace + "description").val();
            var photo = $("#" + namespace + "photo").val();
            var video = $("#" + namespace + "video").val();
            var project = $("#" + namespace + "project").val();
            var district = $("#" + namespace + "quartier").val();
            var thematic = $("#" + namespace + "theme").val();
            var place = $("#" + namespace + "place").val();
            var lastname = $("#" + namespace + "astname").val();
            var firstname = $("#" + namespace + "firstname").val();
            var address = $("#" + namespace + "address").val();
            var city = $("#" + namespace + "city").val();
            var postalCode = $("#" + namespace + "postalcode").val();
            var birthday = $("#" + namespace + "birthday").val();
            var phone = $("#" + namespace + "phone").val();
            var mobile = $("#" + namespace + "mobile").val();
            var email = $("#" + namespace + "mail").val();
            var saveInfo = $("#" + namespace + "saveInfo").is(":checked");
            
            AUI().use('aui-io-request', function(A) {
                var uploadForm = A.one("#" + namespace + "uploadForm");
                try {
                    A.io.request('${submitInitiativeURL}', {
                        method : 'POST',
                        form: {
                            id: uploadForm,
                            upload: true
                        },
                        sync: true,
                        dataType: 'json',
                        data:{
                            <portlet:namespace/>title: 			title,
                            <portlet:namespace/>description: 	description,
                            <portlet:namespace/>address:		address,
                            <portlet:namespace/>city: 			city,
                            <portlet:namespace/>postalcode: 	postalCode,
                            <portlet:namespace/>phone: 			phone,
                            <portlet:namespace/>mobile:	 		mobile,
                            <portlet:namespace/>birthday: 		birthday,
                            <portlet:namespace />project: 		project,
                            <portlet:namespace />district: 		district,
                            <portlet:namespace />thematic: 		thematic,
                            <portlet:namespace />photo: 		photo,
                            <portlet:namespace />video: 		video,
                            <portlet:namespace />place: 		place,
                            <portlet:namespace />saveinfo: 		saveInfo,
                            <portlet:namespace />lastname: 		lastname,
                            <portlet:namespace />firstname: 	firstname,
                            <portlet:namespace />email: 		email
                        },
                        on: {
                            complete: function(e) {
                                // var data = this.get('responseData');
                                var data = JSON.parse(e.details[1].responseText);
                                if(data.result){
                                    $("#" + namespace + "modalSubmit").modal('hide');
                                    if(data.savedInfo){
                                        saved_dateNaiss = birthday;
                                        saved_city = $("#"+namespace+"city").val();
                                        saved_address = $("#"+namespace+"address").val();
                                        saved_zipCode = $("#"+namespace+"postalcode").val();
                                        if($("#"+namespace+"phone").val() != "")
                                            saved_phone = $("#"+namespace+"phone").val();
                                        if($("#"+namespace+"mobile").val() != "")
                                            saved_mobile = $("#"+namespace+"mobile").val();
                                    }
                                    $("#" + namespace + "modalConfirm").modal('show');
                                    resetValues();
                                }else{
                                    $("#" + namespace + "modalError h4").text(data.message);
                                    $("#" + namespace + "modalError").modal('show');
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
    });

    $("#" + namespace + "modalConfirm #" + namespace + "buttonConfirm").click(function(event){
        $("#" + namespace + "modalConfirm").modal('hide');
    });

    $("#" + namespace + "modalError #" + namespace + "buttonConfirm").click(function(event){
        $("#" + namespace + "modalError").modal('hide');
    });

    /*
	* Reinitialise le formulaire avec les informations les plus fraiches
	*/
    function resetValues(){
    	// Champs entite
        $("#" + namespace + "title").val("");
        $("#" + namespace + "description").val("");
        $("#" + namespace + "place").val("");
        $("#" + namespace + "project option[value='0']").prop('selected', true);
        $("#" + namespace + "project").selectric();
        $("#" + namespace + "district option[value='0']").prop('selected', true);
        $("#" + namespace + "district").selectric();
        $("#" + namespace + "thematic option[value='0']").prop('selected', true);
        $("#" + namespace + "thematic").selectric();
        
     	// Champs informations utilisateur
        $("#" + namespace + "address").val(saved_address);
        $("#" + namespace + "photo").val("");
        $("#" + namespace + "video").val("");
        $("#" + namespace + "postalcode").val(saved_zipCode);
        $("#" + namespace + "phone").val(saved_phone);
        $("#" + namespace + "mobile").val(saved_mobile);
        $("#" + namespace + "birthday").val(saved_dateNaiss);
        
     	// Chebox de conditions et de sauvegade des informations
     	$("#" + namespace + "checkboxSaveInfo #" + namespace + "saveInfo").prop('checked', false);
        $("#" + namespace + "checkboxSaveInfo').hide();
        $("#" + namespace + "legalage").prop("checked", false);
        $("#" + namespace + "cnil").prop("checked", false);
        $("#" + namespace + "city").val(saved_city);
    }

    /*
	* Affiche la demande de sauvegarde des informations dans Publik
	*/
    function checkValues(){
        if($("#" + namespace + "birthday").val() != saved_dateNaiss 
        		|| $("#" + namespace + "address").val() != saved_address 
        		|| $("#" + namespace + "city").val() != saved_city 
        		|| $("#" + namespace + "postalcode").val() != saved_zipCode 
        		|| $("#" + namespace + "phone").val() != saved_phone 
        		|| $("#" + namespace + "mobile").val() != saved_mobile) {
            $("#" + namespace + "checkboxSaveInfo #" + namespace + "saveInfo").prop('checked', true);
            $("#" + namespace + "checkboxSaveInfo").show();
        }else{
            $("#" + namespace + "checkboxSaveInfo #" + namespace + "saveInfo").prop('checked', false);
            $("#" + namespace + "checkboxSaveInfo").hide();
        }
    }

    /*
	* Verifie la conformite des elements avant l'envoie du formulaire
	*/
    function validateForm()
    {
        var result = true;
        
        var title = $("#" + namespace + "title").val();
        var description = $("#" + namespace + "description").val();
        var city = $("#" + namespace + "city").val();
        var address = $("#" + namespace + "address").val();
        var postalcode = $("#" + namespace + "postalcode").val();
        var legalage = $("#" + namespace + "legalage").is(":checked");
        var cnil = $("#" + namespace + "cnil").is(":checked");
        var photo = $("#" + namespace + "photo").val();
        var regex = new RegExp("^(([0-8][0-9])|(9[0-5]))[0-9]{3}$");

        if (photo!=null && photo!==""){
            var ext = photo.split(".").pop().toLowerCase();
            if($.inArray(ext, ['png','jpg','jpeg']) == -1) {
            $("#" + namespace + "photo").css({ "box-shadow" : "0 0 10px #CC0000" });
                result = false;
            }else $("#" + namespace + "photo").css({ "box-shadow" : "" });
        }

        if (title===null || title===""){
            $("#" + namespace + "title").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#" + namespace + "title").css({ "box-shadow" : "" });

        if (description===null || description===""){
            $("#" + namespace + "description").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#" + namespace + "description").css({ "box-shadow" : "" });

        if (city===null || city===""){
            $("#" + namespace + "city").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#" + namespace + "city").css({ "box-shadow" : "" });

        if (address===null || address===""){
            $("#" + namespace + "address").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#" + namespace + "address").css({ "box-shadow" : "" });

        if (postalcode===null || postalcode===""){
            $("#" + namespace + "postalcode").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else if(!regex.test(postalcode)){
            $("#" + namespace + "postalcode").css({ "box-shadow" : "0 0 10px #CC0000" });
            alert("Merci de respecter la syntaxe d'un code postal");
            result = false;
        }
        else $("#" + namespace + "postalcode").css({ "box-shadow" : "" });

        if (!legalage)
            result = false;

        if (!cnil)
            result = false;

        if (!result)
            $("#sendalert").removeClass("hidden");
        else $("#sendalert").addClass("hidden");
        
        return result;
    }
</script>