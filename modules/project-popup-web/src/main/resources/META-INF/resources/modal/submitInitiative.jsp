<%@ include file="/project-popup-init.jsp" %>

<portlet:resourceURL id="submitInitiative" var="submitInitiativeURL">
</portlet:resourceURL>

<!-- DEPOSER UNE NOUVELLE INITIATIVE -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalSubmitInitiative" tabindex="-1" role="dialog" aria-labelledby="modalSubmitInitiative"
	data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
        
        	<%-- Top titre du modal --%>
            <div class="pro-modal-top">
                <h3><liferay-ui:message key="modal.submit.initiative.title"/></h3>
                <button  type="button" class="close closefirstmodal" aria-label="Close">
                	<span aria-hidden="true"><span class="icon-multiply"></span></span>
                </button>
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
                    
                    <%-- Champ : Au nom de --%>
                    <div class="form-group">
                        <aui:input id="initiativeInTheNameOf" name="inTheNameOf" label="modal.submit.initiative.information.inTheNameOf" required="false" maxlength="400" value=""/>
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
                                <aui:input name="photo" type="file" label="modal.submit.initiative.information.picture"
                                    cssClass="btn btn-default btn-choose">
							        <aui:validator name="acceptFiles">'jpg,png,jpeg'</aui:validator>
                                </aui:input>
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
                
                <%-- Groupe de champs : Information utilisateur --%>
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
                            <aui:input id="birthday" name="birthday" cssClass="frm_date" label="modal.user.birthday" placeholder="jj/mm/aaaa" maxlength="10" onInput="checkValues();" onChange="checkValues();"/>
                        </div>
                        
                    </div>
                    
                    <%-- Groupe de champs : Information adresse  --%>
                    <div class="pro-row">
                    
                    	<%-- Champ : Adresse --%>
                        <div class="form-group form-half">
                            <aui:input name="address" label="modal.user.address" maxlength="256" onInput="checkValues();" />
                        </div>
                        
                        <%-- Groupe de champs : (note : utilise pour la sous division d'une meme ligne en plus petit champ) --%>
                        <div class="form-group form-half">
                        
                        	<%-- Champ : Ville --%>
                            <div class="form-city">
                                <aui:input name="city" label="modal.user.city"  maxlength="256" onInput="checkValues();" />
                            </div>
                            
                            <%-- Champ : Code postal --%>
                            <div class="form-code">
                                <aui:input name="postalcode" label="modal.user.postalcode"  maxlength="5" onInput="checkValues();"/>
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
                
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox"  id="<portlet:namespace />legalage" value="legalage">
                        <label for="<portlet:namespace />legalage" class="fontWhite">
                            <liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_legalageSubmitInitiative"/>
                        </label>
                    </div>
                </div>
                <div class="pro-optin form-checkbox" >
                    <div>
                        <input type="checkbox" id="<portlet:namespace />cnil" value="cnil">
                        <label for="<portlet:namespace />cnil" class="fontWhite">
                            <liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_cnil2SubmitInitiative"/>
                        </label>
                    </div>
                </div>
                <div class="pro-info-supp">
                    <p><i><liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_conditionsSubmitInitiative"/></i></p>
                    <p><liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_contactSubmitInitiative"/></p>
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
                    <input id="<portlet:namespace />buttonConfirm" type="submit" class="pro-btn" value=<liferay-ui:message key="button-submit-initiative-ok"/> />
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
    $("#<portlet:namespace />buttonSubmit").click(function(event){
        event.preventDefault();
        var response = validateForm();
        if (response){
            var title = $("#<portlet:namespace />title").val();
            var description = $("#<portlet:namespace />description").val();
            var photo = $("#<portlet:namespace />photo").val();
            var video = $("#<portlet:namespace />video").val();
            var project = $("#<portlet:namespace />project").val();
            var district = $("#<portlet:namespace />quartier").val();
            var thematic = $("#<portlet:namespace />theme").val();
            var place = $("#<portlet:namespace />place").val();
            var lastname = $("#<portlet:namespace />astname").val();
            var firstname = $("#<portlet:namespace />firstname").val();
            var address = $("#<portlet:namespace />address").val();
            var city = $("#<portlet:namespace />city").val();
            var postalCode = $("#<portlet:namespace />postalcode").val();
            var birthday = $("#<portlet:namespace />birthday").val();
            var phone = $("#<portlet:namespace />phone").val();
            var mobile = $("#<portlet:namespace />mobile").val();
            var email = $("#<portlet:namespace />mail").val();
            var saveInfo = $("#<portlet:namespace />saveInfo").is(":checked");
            var inTheNameOf = $("#<portlet:namespace />initiativeInTheNameOf").val();
            
            AUI().use('aui-io-request', function(A) {
                var uploadForm = A.one("#<portlet:namespace />uploadForm");
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
                            <portlet:namespace />email: 		email,
                            <portlet:namespace />inTheNameOf: 	inTheNameOf
                        },
                        on: {
                            complete: function(e) {
                                // var data = this.get('responseData');
                                var data = JSON.parse(e.details[1].responseText);
                                if(data.result){
                                    $("#modalSubmitInitiative").modal('hide');
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
    function resetValues(){
    	// Champs entite
        $("#<portlet:namespace />title").val("");
        $("#<portlet:namespace />description").val("");
        $("#<portlet:namespace />initiativeInTheNameOf").val("");
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