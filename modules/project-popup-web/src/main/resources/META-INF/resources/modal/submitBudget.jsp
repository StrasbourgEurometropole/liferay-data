<%@ include file="/project-popup-init.jsp" %>

<portlet:resourceURL id="submitBudget" var="submitBudgetURL">
</portlet:resourceURL>

<!-- DEPOSER UN NOUVEAU BUDGET -->
<!-- HTML pour la modal de budget -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalBudget" tabindex="-1" role="dialog" aria-labelledby="modalProjet"
	data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key="modal.submitbudget.title"/></h3>
                <button type="button" class="close closefirstmodal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>

            <aui:form name="uploadForm" enctype="multipart/form-data">
                <div class="pro-wrapper">
                    <h4><liferay-ui:message key="modal.submitbudget.information"/></h4>
                    <div class="form-group">
                        <aui:input id="budgettitle" name="title" label="modal.submitbudget.information.title" maxlength="256" required="true" value=""/>
                    </div>
                    <div class="form-group">
                    	<aui:input id="budgetdescription" type="hidden" name="description" value="hidden description"/>
                        <aui:input name="squiredescription" type="textarea" cssClass="form-control form-squire-target" label="modal.submitbudget.information.description"/>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <label for="quartiers"><liferay-ui:message key="modal.submitbudget.information.territoire"/></label>
                            <select id="<portlet:namespace />quartier" name="<portlet:namespace />quartier">
                                <option value="0" selected><liferay-ui:message key="modal.submitbudget.information.territoire.town"/></option>
                                <c:forEach var="quartier" items="${quartiers}">
                                    <option value="${quartier.categoryId}">${quartier.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group form-half">
                            <aui:input id="budgetlieux" name="budgetlieux" label="modal.submitbudget.information.lieu" maxlength="256" value=""/>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <label for="thematiques"><liferay-ui:message key="modal.submitbudget.information.thematique"/></label>
                            <select id="<portlet:namespace />theme" name="<portlet:namespace />theme">
                                <option value="0" selected></option>
                                <c:forEach var="theme" items="${thematics}">
                                    <option value="${theme.categoryId}">${theme.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group form-half">
                            <label for="projets"><liferay-ui:message key="modal.submitbudget.information.projet"/></label>
                            <select id="<portlet:namespace />project" name="<portlet:namespace />project">
                                <option value="0" selected ></option>
                                <c:forEach var="project" items="${projects}">
                                    <option value="${project.categoryId}">${project.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-two-tiers">
                            <span class="browsePicture input-group-btn">
                                <aui:input name="budgetPhoto" type="file" label="modal.submitbudget.information.picture"
                                    cssClass="btn btn-default btn-choose">
							        <aui:validator name="acceptFiles">'jpg,png,jpeg'</aui:validator>
                                </aui:input>
                            </span>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-two-tiers">
                            <aui:input id="budgetVideo" name="budgetVideo" label="modal.submitbudget.information.video" maxlength="256" value=""/>
                        </div>
                    </div>
                </div>
                <div class="pro-wrapper">
                    <h4><liferay-ui:message key="modal.submitbudget.user"/></h4>
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
                            <aui:input id="birthday" name="birthday" cssClass="frm_date" label="modal.user.birthday" required="true" placeholder="jj/mm/aaaa" maxlength="10" onInput="checkValues();" onChange="checkValues();"/>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <aui:input name="address" label="modal.user.address" required="true" maxlength="256" onInput="checkValues();" />
                        </div>
                        <div class="form-group form-half">
                            <div class="form-city">
                                <aui:input name="city" label="modal.user.city" required="true" maxlength="256" onInput="checkValues();" />
                            </div>
                            <div class="form-code">
                                <aui:input name="postalcode" label="modal.user.postalcode" required="true" maxlength="5" onInput="checkValues();"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <aui:input name="mail" label="modal.user.mail" required="true" disabled="true" value="${userConnected.get('email')}"/>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <aui:input name="phone" label="modal.user.phone" maxlength="20" value="" onInput="checkValues();"/>
                        </div>
                        <div class="form-group form-half">
                            <aui:input name="mobile" label="modal.user.mobile" maxlength="20" value="" onInput="checkValues();"/>
                        </div>
                    </div>
                    <div class="form-group form-checkbox" id="checkboxSaveInfo" >
                        <div>
                            <input type="checkbox" id="save-info" value="info">
                            <label for="save-info">
                            	<liferay-ui:message key="modal.save.info"/>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="submit-budget-legalage" value="legalage">
                        <label for="submit-budget-legalage" class="fontWhite">
                            <liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_legalageSubmitBudget"/>
                        </label>
                    </div>
                </div>
                <div class="pro-optin form-checkbox" >
                    <div>
                        <input type="checkbox" id="submit-budget-cnil" value="cnil">
                        <label for="submit-budget-cnil" class="fontWhite">
                            <liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_cnilSubmitBudget"/>
                        </label>
                    </div>
                </div>
                <div id="sendalert" class="hidden pro-info-supp alertMessage"><liferay-ui:message key="modal.alert"/></div>
                <div class="pro-form-submit">
                    <button id="sendBudget" type="submit" class="btn btn-default"><liferay-ui:message key="modal.submitbudget.submit"/></button>
                </div>
            </aui:form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- CONFIRMATION NOUVEAU BUDGET -->
<!-- HTML pour la modal de confirmation de soumission du projet -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalConfirmerBudget" tabindex="-1" role="dialog" aria-labelledby="modalConfirmerBudget">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key='confirm-budget'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>
            <div class="pro-wrapper">
                <h4><liferay-ui:message key='submit-budget-ok'/></h4>
                <div class="centerButtonValidation">
                    <input id="buttonConfirm" type="submit" class="pro-btn" value=<liferay-ui:message key="button-petition-ok"/> />
                </div>
            </div>
        </div>
    </div>
</div>


<!-- ERREUR NOUVELLE BUDGET -->
<!-- HTML pour la modal d'erreur de nouvelle budget -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalErrorBudget" tabindex="-1" role="dialog" aria-labelledby="modalErrorBudget">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key='error-budget'/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>
            <div class="pro-wrapper">
                <h4></h4>
                <div class="centerButtonValidation">
                    <input id="buttonConfirm" type="submit" class="pro-btn-yellow" value=<liferay-ui:message key="button-budget-ok"/> />
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
	var saved_phone = "${userConnected.get('phone')}" != 'null' ? "${userConnected.get('phone')}" : " ";
	var saved_mobile = "${userConnected.get('mobile')}" != 'null' ? "${userConnected.get('mobile')}" : " ";

    $(document).ready(function(){
        $('#modalConfirmerBudget').modal('hide');
        $('#modalErrorBudget').modal('hide');
        $('#checkboxSaveInfo').hide();
    });
    
    $('#buttonDeposer').click(function(event){
        resetValues();
    });

    $("#sendBudget").click(function(event){
        event.preventDefault();
        var response = validateForm();
        if (response){
            var budgetTitleValue = $("#"+namespace+"budgettitle").val();           
            var iframe = $('.Squire-UI').next('iframe').first()[0];
        	var editor = iframe.contentWindow.editor;       	
            var budgetDescriptionValue = editor.getHTML();
            var addressValue = $("#"+namespace+"address").val();
            var cityValue = $("#"+namespace+"city").val();
            var postalcodeValue = $("#"+namespace+"postalcode").val();
            var birthdayValue = $("#"+namespace+"birthday").val();
            var phoneValue = $("#"+namespace+"phone").val();
            var mobileValue = $("#"+namespace+"mobile").val();
            var projectValue = $("#"+namespace+"project").val();
            var quartierValue = $("#"+namespace+"quartier").val();
            var themeValue = $("#"+namespace+"theme").val();
            var budgetlieuxValue = $("#"+namespace+"budgetlieux").val();
            var saveInfoValue = $("#save-info").is(":checked");
            var lastNameValue = $("#"+namespace+"username").val();
            var photoValue = $("#"+namespace+"budgetPhoto").val();
            var videoValue = $("#"+namespace+"budgetVideo").val();
            var firstNameValue = $("#"+namespace+"firstname").val();
            var emailValue = $("#"+namespace+"mail").val();
            AUI().use('aui-io-request', function(A) {
                var uploadForm = A.one("#<portlet:namespace />uploadForm");
                try {
                    A.io.request('${submitBudgetURL}', {
                        method : 'POST',
                        form: {
                            id: uploadForm,
                            upload: true
                        },
                        sync: true,
                        dataType: 'json',
                        data:{
                            <portlet:namespace/>title:budgetTitleValue,
                            <portlet:namespace/>squiredescription:budgetDescriptionValue,
                            <portlet:namespace/>address:addressValue,
                            <portlet:namespace/>city:cityValue,
                            <portlet:namespace/>postalcode:postalcodeValue,
                            <portlet:namespace/>phone:phoneValue,
                            <portlet:namespace/>mobile:mobileValue,
                            <portlet:namespace/>birthday:birthdayValue,
                            <portlet:namespace />project:projectValue,
                            <portlet:namespace />quartier:quartierValue,
                            <portlet:namespace />theme:themeValue,
                            <portlet:namespace />photo:photoValue,
                            <portlet:namespace />video:videoValue,
                            <portlet:namespace />budgetLieux:budgetlieuxValue,
                            <portlet:namespace />saveinfo:saveInfoValue,
                            <portlet:namespace />lastname:lastNameValue,
                            <portlet:namespace />firstname:firstNameValue,
                            <portlet:namespace />email:emailValue
                        },
                        on: {
                            complete: function(e) {
                                // var data = this.get('responseData');
                                var data = JSON.parse(e.details[1].responseText);
                                if(data.result){
                                    $('#modalBudget').modal('hide');
                                    if(data.savedInfo){
                                        saved_dateNaiss = birthdayValue;
                                        saved_city = $("#"+namespace+"city").val();
                                        saved_address = $("#"+namespace+"address").val();
                                        saved_zipCode = $("#"+namespace+"postalcode").val();
                                        if($("#"+namespace+"phone").val() != "")
                                            saved_phone = $("#"+namespace+"phone").val();
                                        if($("#"+namespace+"mobile").val() != "")
                                            saved_mobile = $("#"+namespace+"mobile").val();
                                    }
                                    $('#modalConfirmerBudget').modal('show');
                                    resetValues();
                                }else{
                                    $("#modalErrorBudget h4").text(data.message);
                                    $('#modalErrorBudget').modal('show');
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

    $('#modalConfirmerBudget #buttonConfirm').click(function(event){
        $('#modalConfirmerBudget').modal('hide');
    });

    $('#modalErrorBudget #buttonConfirm').click(function(event){
        $('#modalErrorBudget').modal('hide');
    });

    function resetValues()
    {
        $("#"+namespace+"budgettitle").val("");
        $("#"+namespace+"budgetdescription").val("");
        $("#"+namespace+"budgetlieux").val("");
        $("#"+namespace+"project option[value='0']").prop('selected', true);
        $("#"+namespace+"project").selectric();
        $("#"+namespace+"quartier option[value='0']").prop('selected', true);
        $("#"+namespace+"quartier").selectric();
        $("#"+namespace+"theme option[value='0']").prop('selected', true);
        $("#"+namespace+"theme").selectric();
        $('#checkboxSaveInfo #save-info').prop('checked', false);
        $('#checkboxSaveInfo').hide();
        $("#submit-budget-legalage").prop("checked", false);
        $("#submit-budget-cnil").prop("checked", false);
        $("#"+namespace+"city").val(saved_city);
        $("#"+namespace+"address").val(saved_address);
        $("#"+namespace+"budgetPhoto").val("");
        $("#"+namespace+"budgetVideo").val("");
        $("#"+namespace+"postalcode").val(saved_zipCode);
        $("#"+namespace+"phone").val(saved_phone);
        $("#"+namespace+"mobile").val(saved_mobile);
        $("#"+namespace+"birthday").val(saved_dateNaiss);
        
        var iframe = $('.Squire-UI').next('iframe').first()[0];
    	var editor = iframe.contentWindow.editor;
    	editor.setHTML('');
    }

    function checkValues(){
        if($("#"+namespace+"birthday").val() != saved_dateNaiss || $("#"+namespace+"address").val() != saved_address ||
        $("#"+namespace+"city").val() != saved_city || $("#"+namespace+"postalcode").val() != saved_zipCode ||
        $("#"+namespace+"phone").val() != saved_phone || $("#"+namespace+"mobile").val() != saved_mobile) {
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
        var budgettitle = $("#"+namespace+"budgettitle").val();
        var iframe = $('.Squire-UI').next('iframe').first()[0];
    	var editor = iframe.contentWindow.editor;       	
        var budgetdescription = editor.getHTML();
        var city = $("#"+namespace+"city").val();
        var address = $("#"+namespace+"address").val();
        var postalcode = $("#"+namespace+"postalcode").val();
        var legalage = $("#submit-budget-legalage").is(":checked");
        var cnil = $("#submit-budget-cnil").is(":checked");
        var photo = $("#"+namespace+"budgetPhoto").val();
        var regex = new RegExp("^(([0-8][0-9])|(9[0-5]))[0-9]{3}$");

        if (photo!=null && photo!==""){
            var ext = photo.split(".").pop().toLowerCase();
            if($.inArray(ext, ['png','jpg','jpeg']) == -1) {
            $("#"+namespace+"budgetPhoto").css({ "box-shadow" : "0 0 10px #CC0000" });
                result = false;
            }else $("#"+namespace+"budgetPhoto").css({ "box-shadow" : "" });
        }

        if (budgettitle===null || budgettitle===""){
            $("#"+namespace+"budgettitle").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#"+namespace+"budgettitle").css({ "box-shadow" : "" });

             
        if (budgetdescription===null || budgetdescription===""){
            $("#"+namespace+"budgetdescription").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#"+namespace+"budgetdescription").css({ "box-shadow" : "" });

        if (city===null || city===""){
            $("#"+namespace+"city").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#"+namespace+"city").css({ "box-shadow" : "" });

        if (address===null || address===""){
            $("#"+namespace+"address").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#"+namespace+"address").css({ "box-shadow" : "" });

        if (postalcode===null || postalcode===""){
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