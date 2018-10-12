<%@ include file="/project-popup-init.jsp" %>
<portlet:resourceURL id="filePetition" var="filePetitionURL">
</portlet:resourceURL>
<!-- DEPOSER UNE NOUVELLE PETITION -->
<!-- HTML pour la modal de pÃÂ©tition -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalPetition" tabindex="-1" role="dialog" aria-labelledby="modalPetition">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key="modal.filepetition.title"/></h3>
                <button id="closingButton" type="button" class="close" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>

            <form id="form-file-petition">
                <div class="pro-wrapper">
                    <h4><liferay-ui:message key="modal.filepetition.information"/></h4>
                    <div class="form-group">
                        <aui:input id="petitiontitle" name="title" label="modal.filepetition.information.title" required="true" value=""/>
                    </div>
                    <div class="form-group">
                        <aui:input id="petitiondescription" type="textarea" name="description" label="modal.filepetition.information.description" required="true" value=""/>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-triple">
                            <label for="petition"><liferay-ui:message key="modal.filepetition.information.projet"/></label>
                            <select id="<portlet:namespace />project" name="<portlet:namespace />project">
                                <option value="0" selected ></option>
                                <c:forEach var="project" items="${projects}">
                                    <option value="${project.categoryId}">${project.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group form-triple">
                            <label for="territoire"><liferay-ui:message key="modal.filepetition.information.territoire"/></label>
                            <select id="<portlet:namespace />quartier" name="<portlet:namespace />quartier">
                                <option value="0" selected ><liferay-ui:message key="modal.filepetition.information.territoire.town"/></option>
                                <c:forEach var="quartier" items="${quartiers}">
                                    <option value="${quartier.categoryId}">${quartier.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group form-triple">
                            <label for="thematique"><liferay-ui:message key="modal.filepetition.information.thematique"/></label>
                            <select id="<portlet:namespace />theme" name="<portlet:namespace />theme">
                                <option value="0" selected ></option>
                                <c:forEach var="theme" items="${thematics}">
                                    <option value="${theme.categoryId}">${theme.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <aui:input id="petitionlieux" name="consultationPlacesText" label="modal.filepetition.information.lieu" value=""/>
                    </div>
                    <div class="pro-txt-form">
                        <p><liferay-ui:message key="modal.filepetition.information.mayor"/></p>
                    </div>
                </div>
                <div class="pro-wrapper">
                    <h4><liferay-ui:message key="modal.filepetition.user"/></h4>
                    <div class="pro-row">
                        <div class="form-group form-triple">
                            <aui:input name="username" disabled="true" label="modal.user.username" required="true" value="${userConnected.get('last_name')}"/>
                        </div>
                        <div class="form-group form-triple">
                            <aui:input name="firstname" disabled="true" label="modal.user.firstname" required="true" value="${userConnected.get('first_name')}"/>
                        </div>
                        <div class="form-group form-triple">
	                        <c:if test="${userConnected.get('birthdate') ne 'null'}">
	                            <fmt:parseDate pattern="yyyy-MM-dd" value="${userConnected.get('birthdate')}" var="parsedStatusDate" />
					            <fmt:formatDate value="${parsedStatusDate}" var="formattedDate" type="date" pattern="dd/MM/yyyy" />
	                        </c:if>
                            <aui:input id="birthday" name="birthday" cssClass="frm_date" label="modal.user.birthday" required="true" placeholder="jj/mm/aaaa" onInput="checkValues();" onChange="checkValues();"/>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <aui:input id="address" name="address" label="modal.user.address" required="true" onInput="checkValues();"/>
                        </div>
                        <div class="form-group form-half">
                            <div class="form-city">
                                <aui:input id="city" name="city" label="modal.user.city" required="true" placeholder="Strasbourg" onInput="checkValues();"/>
                            </div>
                            <div class="form-code">
                                <aui:input id="postalcode" name="postalcode" label="modal.user.postalcode" required="true" type="number" pattern="[0-9]{5}" placeholder="67XXX" onInput="checkValues();"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <aui:input type="email" name="mail" disabled="true" label="modal.user.mail" required="true" value="${userConnected.get('email')}"/>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <aui:input type="number" id="phone" name="phone" label="modal.user.phone" placeholder="0311111111" onInput="checkValues();"/>
                        </div>
                        <div class="form-group form-half">
                            <aui:input type="number" id="mobile" name="mobile" label="modal.user.mobile" placeholder="0611111111" onInput="checkValues();"/>
                        </div>
                    </div>
                    <div class="form-group form-checkbox" id="checkboxSaveInfo">
                        <div>
                            <input type="checkbox" id="save-info" value="save-info">
                            <label for="save-info"><liferay-ui:message key="modal.save.info"/></label>
                        </div>
                    </div>
                </div>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="file-petition-legalage" value="legalage">
                        <label for="file-petition-legalage" class="fontWhite">
                            <liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_legalage"/>
                        </label>
                    </div>
                </div>
                <div class="pro-optin form-checkbox" >
                    <div>
                        <input type="checkbox" id="file-petition-cnil" value="cnil">
                        <label for="file-petition-cnil" class="fontWhite">
                            <liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_cnil2"/>
                        </label>
                    </div>
                </div>
                <div class="pro-info-supp">
                    <p><i><liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_conditions"/></i></p>
                    <p><liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_contact"/></p>
                </div>
                <div id="sendalert" class="hidden pro-info-supp alertMessage"><liferay-ui:message key="modal.alert"/></div>
                <div class="pro-form-submit">
                    <button id="sendPetition" type="submit" class="btn btn-default"><liferay-ui:message key="modal.filepetition.submit"/></button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- CONFIRMATION NOUVELLE PETITION -->
<!-- HTML pour la modal de confirmation de nouvelle pÃÂ©tition -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalConfirmerPetition" tabindex="-1" role="dialog" aria-labelledby="modalConfirmerPetition">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key='confirm-petition'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>
            <div class="pro-wrapper">
                <h4><liferay-ui:message key='file-petition-ok'/></h4>
                <div class="centerButtonValidation">
                    <input id="buttonConfirm" type="submit" class="pro-btn" value=<liferay-ui:message key="button-petition-ok"/> />
                </div>
            </div>
        </div>
    </div>
</div>


<!-- ERREUR NOUVELLE PETITION -->
<!-- HTML pour la modal d'erreur de nouvelle pÃÂ©tition -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalErrorPetition" tabindex="-1" role="dialog" aria-labelledby="modalErrorPetition">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key='error-petition'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>
            <div class="pro-wrapper">
                <h4></h4>
                <div class="centerButtonValidation">
                    <input id="buttonConfirm" type="submit" class="pro-btn" value=<liferay-ui:message key="button-petition-ok"/> />
                </div>
            </div>
        </div>
    </div>
</div>

<!-- CONFIRMATION QUITTER PETITION -->
<!-- HTML pour la modal de quitter le formulaire de pÃÂ©tition -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalQuitPetition" tabindex="-1" role="dialog" aria-labelledby="modalQuitPetition">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key='quit-petition'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>
            <div class="pro-wrapper">
                <h4><liferay-ui:message key='file-petition-quit'/></h4>
                <div class="centerButtonValidation">
                    <input id="buttonConfirm" type="submit" class="pro-btn" value=<liferay-ui:message key="button-petition-quit"/> />
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

	var namespace = "<portlet:namespace />";
	var saved_address = "${userConnected.get('address')}";
	var saved_zipCode = "${userConnected.get('zipcode')}";
	var saved_city = "${userConnected.get('city')}";
	var saved_dateNaiss = "${formattedDate}";
	var saved_phone = "${userConnected.get('phone')}";
	var saved_mobile = "${userConnected.get('mobile')}";

    $(document).ready(function(){
        $('#modalConfirmerPetition').modal('hide');
        $('#modalErrorPetition').modal('hide');
        $('#checkboxSaveInfo').hide();

        $('#buttonDeposer').click(function(event){
            resetValues();
        });
    });

    $("#sendPetition").click(function(event){
        event.preventDefault();

        var response = validateForm();
        if (response){
            var petitionTitleValue = $("#"+namespace+"petitiontitle").val();
            var petitionDescriptionValue = $("#"+namespace+"petitiondescription").val();
            var birthdayValue = $("#"+namespace+"birthday").val();
            var addressValue = $("#"+namespace+"address").val();
            var cityValue = $("#"+namespace+"city").val();
            var postalcodeValue = $("#"+namespace+"postalcode").val();
            var phoneValue = $("#"+namespace+"phone").val();
            var mobileValue = $("#"+namespace+"mobile").val();
            var projectValue = $("#"+namespace+"project").val();
            var quartierValue = $("#"+namespace+"quartier").val();
            var themeValue = $("#"+namespace+"theme").val();
            var consultationPlacesTextValue = $("#"+namespace+"petitionlieux").val();
            var saveInfoValue = $("#save-info").is(":checked");
            var lastNameValue = $("#"+namespace+"username").val();
            var firstNameValue = $("#"+namespace+"firstname").val();
            var emailValue = $("#"+namespace+"mail").val();
            AUI().use('aui-io-request', function(A) {
                A.io.request('${filePetitionURL}', {
                    method : 'POST',
                    dataType: 'json',
                    data:{
                        <portlet:namespace/>petitiontitle:petitionTitleValue,
                        <portlet:namespace/>petitiondescription:petitionDescriptionValue,
                        <portlet:namespace/>birthday:birthdayValue,
                        <portlet:namespace/>address:addressValue,
                        <portlet:namespace/>city:cityValue,
                        <portlet:namespace/>postalcode:postalcodeValue,
                        <portlet:namespace/>phone:phoneValue,
                        <portlet:namespace/>mobile:mobileValue,
                        <portlet:namespace />project:projectValue,
                        <portlet:namespace />quartier:quartierValue,
                        <portlet:namespace />theme:themeValue,
                        <portlet:namespace />consultationPlacesText:consultationPlacesTextValue,
                        <portlet:namespace />saveinfo:saveInfoValue,
                        <portlet:namespace />lastname:lastNameValue,
                        <portlet:namespace />firstname:firstNameValue,
                        <portlet:namespace />email:emailValue
                    },
                    on: {
                        success: function(e) {
                            var data = this.get('responseData');
                            if(data.result){
                                $('#modalPetition').modal('hide');
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
                                $('#modalConfirmerPetition').modal('show');
                            }else{
                                $("#modalErrorPetition h4").text(data.message);
                                $('#modalErrorPetition').modal('show');
                            }
                        }
                    }
                });
             });
        }
    });

    $('#modalConfirmerPetition #buttonConfirm').click(function(event){
        $('#modalConfirmerPetition').modal('hide');
    });

    $('#modalErrorPetition #buttonConfirm').click(function(event){
        $('#modalErrorPetition').modal('hide');
    });

    function resetValues()
    {
        $("#"+namespace+"petitiontitle").val("");
        $("#"+namespace+"petitiondescription").val("");
        $("#"+namespace+"petitionlieux").val("");
        $("#"+namespace+"project option[value='0']").prop('selected', true);
        $("#"+namespace+"project").selectric();
        $("#"+namespace+"quartier option[value='0']").prop('selected', true);
        $("#"+namespace+"quartier").selectric();
        $("#"+namespace+"theme option[value='0']").prop('selected', true);
        $("#"+namespace+"theme").selectric();
        $('#checkboxSaveInfo #save-info').prop('checked', false);
        $('#checkboxSaveInfo').hide();
        $("#file-petition-legalage").prop("checked", false);
        $("#file-petition-cnil").prop("checked", false);
        $("#"+namespace+"birthday").val(saved_dateNaiss);
        $("#"+namespace+"city").val(saved_city);
        $("#"+namespace+"address").val(saved_address);
        $("#"+namespace+"postalcode").val(saved_zipCode);
        $("#"+namespace+"phone").val(saved_phone);
        $("#"+namespace+"mobile").val(saved_mobile);
    }

    function checkValues(){
        if($("#"+namespace+"birthday").val() != saved_dateNaiss || $("#"+namespace+"address").val() != saved_address ||
        $("#"+namespace+"city").val() != saved_city || $("#"+namespace+"postalcode").val() != saved_zipCode ||
        $("#"+namespace+"phone").val() != saved_phone || $("#"+namespace+"mobile").val() != saved_mobile){
            $('#checkboxSaveInfo #save-info').prop('checked', true);
            $('#checkboxSaveInfo').show();
        }else{
            $('#checkboxSaveInfo #save-info').prop('checked', false);
            $('#checkboxSaveInfo').hide();
        }
    }

    function validateForm()
    {
        var result = true;
        var petitiontitle = $("#"+namespace+"petitiontitle").val();
        var petitiondescription = $("#"+namespace+"petitiondescription").val();
        var birthday = $("#"+namespace+"birthday").val();
        var city = $("#"+namespace+"city").val();
        var address = $("#"+namespace+"address").val();
        var postalcode = $("#"+namespace+"postalcode").val();
        var legalage = $("#file-petition-legalage").is(":checked");
        var cnil = $("#file-petition-cnil").is(":checked");
        var regex = new RegExp("^(([0-8][0-9])|(9[0-5]))[0-9]{3}$");

        if (petitiontitle==null || petitiontitle==""){
            $("#"+namespace+"petitiontitle").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#"+namespace+"petitiontitle").css({ "box-shadow" : "" });

        if (petitiondescription==null || petitiondescription==""){
            $("#"+namespace+"petitiondescription").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#"+namespace+"petitiondescription").css({ "box-shadow" : "" });

        if (birthday==null || birthday==""){
            $("#"+namespace+"birthday").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#"+namespace+"birthday").css({ "box-shadow" : "" });

        if (city==null || city==""){
            $("#"+namespace+"city").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#"+namespace+"city").css({ "box-shadow" : "" });

        if (address==null || address==""){
            $("#"+namespace+"address").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#"+namespace+"address").css({ "box-shadow" : "" });

        if (postalcode==null || postalcode==""){
            $("#"+namespace+"postalcode").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else if(!regex.test(postalcode)){
            $("#"+namespace+"postalcode").css({ "box-shadow" : "0 0 10px #CC0000" });
            alert("Merci de respecter la syntaxe d'un code postal");
            result = false;
        }
        else $("#"+namespace+"postalcode").css({ "box-shadow" : "" });

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