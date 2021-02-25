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
                            <aui:input name="address" label="modal.user.address" required="true" maxlength="256"  value="${userConnected.get('address')}"/>
                        </div>

                        <%-- Groupe de champs : (note : utilise pour la sous division d'une meme ligne en plus petit champ) --%>
                        <div class="form-group form-half">

                        	<%-- Champ : Ville --%>
                            <div class="form-city">
                                <aui:input name="city" label="modal.user.city" required="true" maxlength="256"  value="${userConnected.get('city')}"/>
                            </div>

                            <%-- Champ : Code postal --%>
                            <div class="form-code">
                                <aui:input name="postalcode" label="modal.user.postalcode" required="true" maxlength="5" value="${userConnected.get('zipcode')}"/>
                            </div>

                        </div>

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
                    <h4 style="margin-bottom:0;"><liferay-ui:message key="modal.submit.help.proposal.information"/></h4>
                    <label>
                        <liferay-ui:message key="modal.show.info"/>
                    </label><br><br>
                    
                    <%-- Champ : Titre --%>
                    <div class="form-group">
                        <aui:input id="title" name="title" label="modal.submit.help.information.title" required="true" maxlength="256" value=""/>
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
                        <c:forEach var="type" items="${types}" varStatus="status">
                            <div class="form-checkbox">
                                <input type="checkbox"  id="<portlet:namespace />type-${status.index}" value="${type.categoryId}">
                                <label for="<portlet:namespace />type-${status.index}" class="fontWhite">
                                    ${type.name}
                                </label>
                            </div>
                        </c:forEach>
                        <aui:input type="hidden"  name="types" />
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
                                <option value="0" selected><liferay-ui:message key="modal.submit.help.information.choice"/></option>
                                <c:forEach var="helper" items="${helpers}">
                                    <aui:option value="${helper.categoryId}" label="${helper.name}" />
                                </c:forEach>
                            </aui:select>
                        </div>

                        <%-- Champ : Au nom de --%>
                        <div class="form-group form-half">
                            <aui:input id="inTheNameOf" name="inTheNameOf" label="modal.submit.help.information.inTheNameOf" required="true" maxlength="400" value="${userConnected.get('first_name')} ${fn:substring(userConnected.get('last_name'),0,1)}"/>
                        </div>

                    </div>

                    <%-- Champ : Langue --%>
                    <div class="form-group">
                        <aui:input id="language" name="language" label="modal.submit.help.information.language" maxlength="256" value=""/>
                    </div>
                    	
                    <%-- Champ : Localisation --%>
                    <div class="form-group">
                        <aui:select required="true" name="localisation" label="modal.submit.help.information.territories">
                            <option value="0" selected><liferay-ui:message key="modal.submit.help.information.choice"/></option>
                            <c:forEach var="localisation" items="${localisations}">
                                <c:set var="category" value="${localisation}" scope="request"/>
                                <c:set var="level" value="0" scope="request" />
                                <jsp:include page="/include/category-option.jsp"/>
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
                <p>
                    <liferay-ui:message key='submit-proposal-help-ok-find'/>
                    <a id="lien-detail" href="">
                        <liferay-ui:message key='submit-proposal-help-ok-here'/>
                    </a>
                </p>
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

	/*
	* Lors du chargement de la page
	*/
    $(document).ready(function(){
    	resetValuesHelpProposal();
        $("#<portlet:namespace />modalConfirm").modal('hide');
        $("#<portlet:namespace />modalError").modal('hide');
    });

    /*
	* Lors du click sur le bouton de submit
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
                    if(data.result){
                        $("#modalSubmitHelpProposal").modal('hide');
                        $("#<portlet:namespace />modalConfirm #lien-detail").attr("href", "${homeURL}detail-aide/-/entity/id/" + data.message);
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
        $("input[id^='<portlet:namespace />type']").prop("checked", false);
        $("#<portlet:namespace />presentation").val("");
        $("#<portlet:namespace />helper option[value='0']").prop('selected', true);
        $("#<portlet:namespace />helper").selectric();
        $("#<portlet:namespace />HelpProposalInTheNameOf").val("");
        $("#<portlet:namespace />language").val("");
        $("#<portlet:namespace />localisation option[value='0']").prop('selected', true);
        $("#<portlet:namespace />localisation").selectric();
        $("#<portlet:namespace />photo").val("");

     	// Chebox de conditions et de sauvegade des informations
        $("#<portlet:namespace />legalage").prop("checked", false);
        $("#<portlet:namespace />security").prop("checked", false);
        $("#<portlet:namespace />security2").prop("checked", false);
        $("#<portlet:namespace />responsability").prop("checked", false);
    }

    /*
	* Verifie la conformite des elements avant l'envoie du formulaire
	*/
    function validateFormHelpProposal()
    {
        var result = true;

        var title = $("#<portlet:namespace />title").val();
        if (title===null || title===""){
            $("#<portlet:namespace />title").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#<portlet:namespace />title").css({ "box-shadow" : "" });

        var address = $("#<portlet:namespace />address").val();
        if (address===null || address===""){
            $("#<portlet:namespace />address").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#<portlet:namespace />address").css({ "box-shadow" : "" });

        var city = $("#<portlet:namespace />city").val();
        if (city===null || city===""){
            $("#<portlet:namespace />city").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#<portlet:namespace />city").css({ "box-shadow" : "" });

        var postalcode = $("#<portlet:namespace />postalcode").val();
        var regex = new RegExp("^(([0-8][0-9])|(9[0-5]))[0-9]{3}$");
        if (postalcode===null || postalcode===""){
            $("#<portlet:namespace />postalcode").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else if(!regex.test(postalcode)){
            $("#<portlet:namespace />postalcode").css({ "box-shadow" : "0 0 10px #CC0000" });
            alert("Merci de respecter la syntaxe d'un code postal");
            result = false;
        }else $("#<portlet:namespace />postalcode").css({ "box-shadow" : "" });

        var phoneNumber = $("#<portlet:namespace />phoneNumber").val();
        if (phoneNumber===null || phoneNumber===""){
            $("#<portlet:namespace />phoneNumber").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#<portlet:namespace />phoneNumber").css({ "box-shadow" : "" });

        var types = "";
        $("input[id^='<portlet:namespace />type']").each(function( index ) {
            if(this.checked){
                types += this.value + "-";
            }
        });
        $("#<portlet:namespace />types").val(types);
        if(types===""){
            $("input[id^='<portlet:namespace />type']").closest('.form-group').css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("input[id^='<portlet:namespace />type']").closest('.form-group').css({ "box-shadow" : "" });

        var helper = $("#<portlet:namespace />helper").val();
        if (helper===null || helper==="0"){
            $("#<portlet:namespace />helper").closest('.form-group').css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#<portlet:namespace />helper").closest('.form-group').css({ "box-shadow" : "" });

        var inTheNameOf = $("#<portlet:namespace />inTheNameOf").val();
        if (inTheNameOf===null || inTheNameOf===""){
            $("#<portlet:namespace />inTheNameOf").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#<portlet:namespace />inTheNameOf").css({ "box-shadow" : "" });

        var localisation = $("#<portlet:namespace />localisation").val();
        if (localisation===null || localisation==="0"){
            $("#<portlet:namespace />localisation").closest('.form-group').css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#<portlet:namespace />localisation").closest('.form-group').css({ "box-shadow" : "" });

        var legalage = $("#<portlet:namespace />legalage").is(":checked");
        if (!legalage){
            $("#<portlet:namespace />legalage").closest('div').css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#<portlet:namespace />legalage").closest('div').css({ "box-shadow" : "" });

        var security = $("#<portlet:namespace />security").is(":checked");
        if (!security){
            $("#<portlet:namespace />security").closest('div').css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#<portlet:namespace />security").closest('div').css({ "box-shadow" : "" });

        var security2 = $("#<portlet:namespace />security2").is(":checked");
        if (!security2){
            $("#<portlet:namespace />security2").closest('div').css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#<portlet:namespace />security2").closest('div').css({ "box-shadow" : "" });

        var responsability = $("#<portlet:namespace />responsability").is(":checked");
        if (!responsability){
            $("#<portlet:namespace />responsability").closest('div').css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#<portlet:namespace />responsability").closest('div').css({ "box-shadow" : "" });



        if (!result)
            $("#<portlet:namespace />alert").removeClass("hidden");
        else
            $("#<portlet:namespace />alert").addClass("hidden");
        
        return result;
    }
</script>