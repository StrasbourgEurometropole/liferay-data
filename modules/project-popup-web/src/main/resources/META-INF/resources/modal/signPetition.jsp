<%@ include file="/project-popup-init.jsp" %>
<portlet:actionURL var="signPetitionURL" name="signPetition">
	<portlet:param name="redirectURL" value="${redirectURL}"/>
	<portlet:param name="cmd" value="signPetition" />
</portlet:actionURL>
<portlet:actionURL var="signPetitionErrorURL" name="signPetitionError">
	<portlet:param name="redirectURL" value="${redirectURL}"/>
	<portlet:param name="cmd" value="signPetition" />
</portlet:actionURL>

<%-- Composant : definit la liste des messages d'erreur 
(voir methode "validate") --%>
<liferay-ui:error key="unfound-petition-error" message="unfound-petition-error" />
<liferay-ui:error key="unfound-user-error" message="unfound-user-error" />
<liferay-ui:error key="is-banned-error" message="is-banned-error" />
<liferay-ui:error key="pact-unsigned-error" message="pact-unsigned-error" />
<liferay-ui:error key="user-already-sign-error" message="user-already-sign-error" />
<liferay-ui:error key="unfound-sign-error" message="unfound-sign-error" />
<liferay-ui:error key="birthday-error" message="birthday-error" />
<liferay-ui:error key="city-error" message="city-error" />
<liferay-ui:error key="legalage-error" message="legalage-error" />

<!-- HTML pour la modal d'une petition -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalSigner" tabindex="-1" role="dialog" aria-labelledby="modalSigner"
	data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

            <div class="pro-modal-top">
                <h3><liferay-ui:message key="modal.signpetition.title"/></h3>
            	<button type="button" class="close closefirstmodal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>

            </div>
            <form id="form-sign-petition" method="post" action="${signPetitionURL}">
                <div class="pro-wrapper">
                    <div class="pro-txt-intro">
                        <ul style="font-size : 10pt">  
                        	<li><liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_informationSignPetition"/></li>
                        	<li><liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_information2SignPetition"/></li>
                        	<li><liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_information3SignPetition"/></li>
                        </ul>
                        <a href="/mentions-legales" class="pro-link-form"><liferay-ui:message key="modal.signpetition.knowmore"/></a>
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
                            <aui:input id="signbirthday" name="birthday" cssClass="frm_date" label="modal.user.birthday" required="true" maxlength="10" placeholder="jj/mm/aaaa" onInput="checkSignValues();" onChange="checkSignValues();"/>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
				            <aui:input id="signaddress" name="address" label="modal.user.address" required="true" onInput="checkSignValues();"/>
                        </div>
                        <div class="form-group form-half">
                            <div class="form-city">
				                <aui:input id="signcity" name="city" label="modal.user.city" required="true" placeholder="Strasbourg" maxlength="256" onInput="checkSignValues();"/>
                            </div>
                            <div class="form-code">
                                <aui:input id="signpostalcode" name="postalcode" label="modal.user.postalcode" required="true" placeholder="67XXX" maxlength="5" onInput="checkSignValues();"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <aui:input type="email" id="signmail" name="mail" disabled="true" label="modal.user.mail"  required="true" value="${userConnected.get('email')}"/>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <aui:input type="number" id="signphone" name="phone" label="modal.user.phone" placeholder="0611111111" maxlength="20" onInput="checkSignValues();"/>
                        </div>
                        <div class="form-group form-half">
                            <aui:input type="number" id="signmobile" name="mobile" label="modal.user.mobile" placeholder="0611111111" maxlength="20" onInput="checkSignValues();"/>
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
                        <label for="signlegalage"  class="fontWhite">
                            <liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_legalage2SignPetition"/>
                        </label>
                    </div>
                </div>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="signcnil" value="cnil">
                        <label for="signcnil"  class="fontWhite">
                            <liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_cnilSignPetition"/>
                        </label>
                    </div>
                </div>
                <div class="pro-info-supp">
                    <p><i><liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_conditions2SignPetition"/></i></p>
                </div>
                <input type="hidden" name="<portlet:namespace />entryId" value="${entryId}"/>
                <div id="signalert" class="hidden pro-info-supp alertMessage"><liferay-ui:message key="modal.alert"/></div>
                <div id="signalertcity" class="hidden pro-info-supp alertMessage"><liferay-ui:message key="modal.alert.city"/></div>
                <div id="signalertPostalCode" class="hidden pro-info-supp alertMessage"><liferay-ui:message key="modal.alert.postalcode"/></div>
                <div id="signalertLegalage" class="hidden pro-info-supp alertMessage"><liferay-ui:message key="modal.alert.legalage"/></div>
                <div class="pro-form-submit">
                    <button id="sendSign" type="submit" class="btn btn-default"><liferay-ui:message key="modal.signpetition.submit"/></button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

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
    var saved_signphone = "${userConnected.get('phone')}" != 'null' ? "${userConnected.get('phone')}" : "";
	var saved_signmobile = "${userConnected.get('mobile')}" != 'null' ? "${userConnected.get('mobile')}" : "";

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
        var age = getAge(signBirthday);

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

        if(age<16){
            $("#signalertLegalage").removeClass("hidden");
            $("#"+namespaceSign+"signbirthday").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }
        else $("#signalertLegalage").addClass("hidden");

        return result;
    }
</script>