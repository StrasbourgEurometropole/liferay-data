<%@ include file="/project-popup-init.jsp" %>
<portlet:actionURL var="signPetitionURL" name="signPetition">
	<portlet:param name="redirectURL" value="${redirectURL}"/>
	<portlet:param name="cmd" value="signPetition" />
</portlet:actionURL>
<portlet:actionURL var="signPetitionErrorURL" name="signPetitionError">
	<portlet:param name="redirectURL" value="${redirectURL}"/>
	<portlet:param name="cmd" value="signPetition" />
</portlet:actionURL>

<!-- HTML pour la modal d'une pÃÂ©tition -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalSigner" tabindex="-1" role="dialog" aria-labelledby="modalSigner">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

            <div class="pro-modal-top">
                <h3><liferay-ui:message key="modal.signpetition.title"/></h3>
            	<button id="closingButton2" type="button" class="close" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>

            </div>
            <form id="form-sign-petition" method="post" action="${signPetitionURL}">
                <div class="pro-wrapper">
                    <div class="pro-txt-intro">
                        <p><liferay-ui:message key="modal.signpetition.information"/></p>
                        <a href="#" class="pro-link-form"><liferay-ui:message key="modal.signpetition.knowmore"/></a>
                    </div>
                </div>
                <div class="pro-wrapper">
                    <h4><liferay-ui:message key="modal.signpetition.user"/></h4>
                    <div class="pro-row">
                        <div class="form-group form-triple">
				            <aui:input name="username" disabled="true" label="modal.user.username" required="true" value="${userConnected.get('last_name')}"/>
				            <aui:input type="hidden" name="lastname" value="${userConnected.get('last_name')}"/>
                        </div>
                        <div class="form-group form-triple">
				            <aui:input name="firstname" disabled="true" label="modal.user.firstname" value="${userConnected.get('first_name')}" required="true"/>
                        </div>
                        <div class="form-group form-triple">
	                        <c:if test="${userConnected.get('birthdate') ne 'null'}">
					            <fmt:parseDate pattern="yyyy-MM-dd" value="${userConnected.get('birthdate')}" var="parsedStatusDate" />
	                            <fmt:formatDate value="${parsedStatusDate}" var="formattedDate" type="date" pattern="dd/MM/yyyy" />
	                        </c:if>
                            <aui:input id="signbirthday" name="birthday" cssClass="frm_date" label="modal.user.birthday" required="true" placeholder="jj/mm/aaaa" onInput="checkSignValues();" onChange="checkSignValues();"/>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
				            <aui:input id="signaddress" name="address" label="modal.user.address" required="true" onInput="checkSignValues();"/>
                        </div>
                        <div class="form-group form-half">
                            <div class="form-city">
				                <aui:input id="signcity" name="city" label="modal.user.city" required="true" placeholder="Strasbourg" onInput="checkSignValues();"/>
                            </div>
                            <div class="form-code">
                                <aui:input id="signpostalcode" name="postalcode" label="modal.user.postalcode" required="true" placeholder="67XXX" onInput="checkSignValues();"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <aui:input type="email" id="signmail" name="mail" disabled="true" label="modal.user.mail"  required="true" value="${userConnected.get('email')}"/>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <aui:input type="number" id="signphone" name="phone" label="modal.user.phone" placeholder="0611111111" onInput="checkSignValues();"/>
                        </div>
                        <div class="form-group form-half">
                            <aui:input type="number" id="signmobile" name="mobile" label="modal.user.mobile" placeholder="0611111111" onInput="checkSignValues();"/>
                        </div>
                    </div>
                    <div class="form-group form-checkbox" id="checkboxSignSaveInfo">
                        <div>
                            <input type="checkbox" name="<portlet:namespace />saveinfo" id="signsave-info" value="save-info">
                            <label for="signsave-info"><liferay-ui:message key="modal.save.info"/></label>
                        </div>
                    </div>
                </div>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="signlegalage" value="legalage">
                        <label for="signlegalage"><liferay-ui:message key="modal.legalage"/></label>
                    </div>
                </div>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="signcnil" value="cnil">
                        <label for="signcnil"><liferay-ui:message key="modal.cnil"/></label>
                    </div>
                </div>
                <div class="pro-info-supp">
                    <p><i><liferay-ui:message key="modal.signpetition.condition"/></i></p>
                </div>
                <input type="hidden" name="<portlet:namespace />entryId" value="${entryId}"/>
                <div id="signalert" class="hidden pro-info-supp alertMessage"><liferay-ui:message key="modal.alert"/></div>
                <div class="pro-form-submit">
                    <button id="sendSign" type="submit" class="btn btn-default"><liferay-ui:message key="modal.signpetition.submit"/></button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

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
    var namespaceSign = "<portlet:namespace />";
    $("#sendSign").click(function(event){
        event.preventDefault();
        var response = validateSignForm();
        if (response){
            $("#form-sign-petition").submit();
        }
    });

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

    $(document).ready(function(){
        $('#checkboxSignSaveInfo').hide();
    });

    $("#signButton[data-target='#modalSigner']").click(function(){
        resetSignValues();
    });

    function resetSignValues()
    {
        $('#checkboxSignSaveInfo #signsave-info').prop('checked', false);
        $('#checkboxSignSaveInfo').hide();
        $("#file-petition-legalage").prop("checked", false);
        $("#file-petition-cnil").prop("checked", false);
        $("#"+namespaceSign+"signbirthday").val(saved_signdateNaiss);
        $("#"+namespaceSign+"signcity").val(saved_signcity);
        $("#"+namespaceSign+"signaddress").val(saved_signaddress);
        $("#"+namespaceSign+"signpostalcode").val(saved_signzipCode);
        $("#"+namespaceSign+"signphone").val(saved_signphone);
        $("#"+namespaceSign+"signmobile").val(saved_signmobile);
    }

    function checkSignValues(){
        if($("#"+namespaceSign+"signbirthday").val() != saved_dateNaiss || $("#"+namespaceSign+"signaddress").val() != saved_address ||
        $("#"+namespaceSign+"signcity").val() != saved_city || $("#"+namespaceSign+"signpostalcode").val() != saved_zipCode ||
        $("#"+namespaceSign+"signphone").val() != saved_phone || $("#"+namespaceSign+"signmobile").val() != saved_mobile){
            $('#checkboxSignSaveInfo #signsave-info').prop('checked', true);
            $('#checkboxSignSaveInfo').show();
        }else{
            $('#checkboxSignSaveInfo #signsave-info').prop('checked', true);
            $('#checkboxSignSaveInfo').hide();
        }
    }

    var namespaceSign = "<portlet:namespace />";
    var saved_signaddress = "${userConnected.get('address')}";
    var saved_signzipCode = "${userConnected.get('zipcode')}";
    var saved_signcity = "${userConnected.get('city')}";
    var saved_signdateNaiss = "${formattedDate}";
    var saved_signphone = "${userConnected.get('phone')}";
    var saved_signmobile = "${userConnected.get('mobile')}";

    function validateSignForm()
    {
        var result = true;
        var signBirthday = $("#"+namespaceSign+"signbirthday").val();
        var signCity = $("#"+namespaceSign+"signcity").val();
        var signAddress = $("#"+namespaceSign+"signaddress").val();
        var signPostalcode = $("#"+namespaceSign+"signpostalcode").val();
        var signlegalage = $("#signlegalage").is(":checked");
        var signcnil = $("#signcnil").is(":checked");
        var regex = new RegExp("^(([0-8][0-9])|(9[0-5]))[0-9]{3}$");

        if (signBirthday==null || signBirthday==""){
            $("#"+namespaceSign+"signbirthday").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#"+namespaceSign+"signbirthday").css({ "box-shadow" : "" });

        if (signCity==null || signCity==""){
            $("#"+namespaceSign+"signcity").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#"+namespaceSign+"signcity").css({ "box-shadow" : "" });

        if (signAddress==null || signAddress==""){
            $("#"+namespaceSign+"signaddress").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#"+namespaceSign+"signaddress").css({ "box-shadow" : "" });

        if (signPostalcode==null || signPostalcode==""){
            $("#"+namespaceSign+"signpostalcode").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else if(!regex.test(signPostalcode)){
            $("#"+namespace+"signpostalcode").css({ "box-shadow" : "0 0 10px #CC0000" });
            alert("Merci de respecter la syntaxe d'un code postal");
            result = false;
        }else $("#"+namespaceSign+"signpostalcode").css({ "box-shadow" : "" });

        if (!signlegalage)
            result = false;

        if (!signcnil)
            result = false;

        if (!result)
            $("#signalert").removeClass("hidden");
        else $("#signalert").addClass("hidden");
        if (signCity.toLowerCase()!=="strasbourg"){
            $("#signalertcity").removeClass("hidden");
            $("#"+namespaceSign+"signcity").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        } else $("#signalertcity").addClass("hidden");

        if (signPostalcode!=="67000"
            &&signPostalcode!=="67100"
            &&signPostalcode!=="67200"){
            $("#signalertPostalCode").removeClass("hidden");
            $("#"+namespaceSign+"signpostalcode").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        } else $("#signalertPostalCode").addClass("hidden");

        if(age<16){
            $("#signalertLegalage").removeClass("hidden");
            $("#"+namespaceSign+"signbirthday").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }
        else $("#signalertLegalage").addClass("hidden");

        return result;
    }
</script>