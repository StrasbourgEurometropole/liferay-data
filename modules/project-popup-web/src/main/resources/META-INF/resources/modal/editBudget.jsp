<%@ include file="/project-popup-init.jsp" %>

<portlet:resourceURL id="getBudget" var="getBudgetURL" >
</portlet:resourceURL>

<portlet:actionURL name="editBudget" var="editBudgetURL">
	<portlet:param name="redirectURL" value="${redirectURL}"/>
</portlet:actionURL>

<!-- MODIFIER UN BUDGET -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalEditBudget" tabindex="-1" role="dialog" aria-labelledby="modalEditBudget"
	data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key="modal.editbudget.title"/></h3>
                <button type="button" class="close closefirstmodal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>

            <form id="uploadForm"  method="post" action="${editBudgetURL}" enctype="multipart/form-data">
                <div class="pro-wrapper">
                    <h4><liferay-ui:message key="modal.editbudget.information"/></h4>
                    <div class="form-group">
                        <aui:input id="budgettitle" name="title" label="modal.editbudget.information.title" maxlength="256" required="true" value=""/>
                    </div>
                    <div class="form-group">
                    	<aui:input id="budgetdescription" name="budgetdescription" type="hidden"/>
                        <aui:input name="squiredescription" type="textarea" required="true" cssClass="form-control form-squire-target" label="modal.submitbudget.information.description"/>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <label for="quartiers"><liferay-ui:message key="modal.editbudget.information.territoire"/></label>
                            <select id="<portlet:namespace />quartier" name="<portlet:namespace />quartier">
                                <option value="0" selected><liferay-ui:message key="modal.editbudget.information.territoire.town"/></option>
                                <c:forEach var="quartier" items="${quartiers}">
                                    <option value="${quartier.categoryId}">${quartier.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group form-half">
                            <aui:input id="budgetlieux" name="budgetlieux" label="modal.editbudget.information.lieu" maxlength="256" value=""/>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <label for="thematiques"><liferay-ui:message key="modal.editbudget.information.thematique"/></label>
                            <select id="<portlet:namespace />theme" name="<portlet:namespace />theme">
                                <option value="0" selected></option>
                                <c:forEach var="theme" items="${thematics}">
                                    <option value="${theme.categoryId}">${theme.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group form-half">
                            <label for="projets"><liferay-ui:message key="modal.editbudget.information.projet"/></label>
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
                        	<div id="budgetPhotoID">
	                            <span class="browsePicture input-group-btn">
	                                <aui:input name="budgetPhoto" type="file" label="modal.editbudget.information.picture"
	                                    cssClass="btn btn-default btn-choose">
								        <aui:validator name="acceptFiles">'jpg,png,jpeg'</aui:validator>
	                                </aui:input>
	                                <label style="color:#ff0000;font-weight:bold" id="budgetPhotoMessageID"><liferay-ui:message key="modal.editbudget.information.picture.edit.red.message.information"/></label>
	                            </span>
                            </div>
                            <div id="editPhotoID">
	                            <span class="input-group-btn">
	                                <aui:input name="editPhoto" type="button" value="Modifier ou supprimer" 
	                                label="modal.editbudget.information.picture.edit.message">
	                                </aui:input>
	                            </span>
                            </div>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-two-tiers">
                            <aui:input id="budgetVideo" name="budgetVideo" label="modal.editbudget.information.video" maxlength="256" value=""/>
                        </div>
                    </div>
                </div>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="edit-budget-legalage" value="legalage">
                        <label for="edit-budget-legalage" class="fontWhite">
                            <liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_legalageEditBudget"/>
                        </label>
                    </div>
                </div>
                <div class="pro-optin form-checkbox" >
                    <div>
                        <input type="checkbox" id="edit-budget-cnil" value="cnil">
                        <label for="edit-budget-cnil" class="fontWhite">
                            <liferay-portlet:runtime portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_cnilEditBudget"/>
                        </label>
                    </div>
                </div>
                <div id="sendalert" class="hidden pro-info-supp alertMessage"><liferay-ui:message key="modal.alert"/></div>
                <!-- Champ cache : ID -->
                <input type="hidden" id="<portlet:namespace />deletePhoto" name="<portlet:namespace />deletePhoto" value="false"/>
                <input type="hidden" id="<portlet:namespace />entryId" name="<portlet:namespace />entryId" value="${entryId}"/>
                <div class="pro-form-submit">
                    <button id="sendBudget" type="submit" class="btn btn-default"><liferay-ui:message key="modal.editbudget.submit"/></button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- CONFIRMATION MODIFICATION BUDGET -->
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


<!-- ERREUR MODIFICATION BUDGET -->
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

    $(document).ready(function(){
        $('#modalConfirmerBudget').modal('hide');
        $('#modalErrorBudget').modal('hide');
    });
    
    
    /*
	* Lors du click sur le bouton d'ouverture de la popup
	*/
	$(document).on("click", "[href='#showModalEditBudget']", function(event) {
		event.preventDefault();
		resetValues();
		var entryId = $("#"+namespace+"entryId").val();
		
		AUI().use('aui-io-request', function(A) {
            try {
                A.io.request('${getBudgetURL}', {
                    method : 'POST',
                    dataType: 'json',
                    data:{
                    	<portlet:namespace/>entryId : entryId
                    },
                    on: {
                    	success: function(e) {
	                        	var data = this.get('responseData');
	                        	
	                        	$("#"+namespace+"budgettitle").val(data.title);
	                        	var iframe = $('.Squire-UI').next('iframe').first()[0];
	                        	var editor = iframe.contentWindow.editor;
	                        	editor.setHTML(data.description);
	                        	//$("#"+namespace+"budgetSummary").val(data.summary);
	                        	$("#"+namespace+"quartier").val(data.quartier).change().selectric('refresh');
	                        	$("#"+namespace+"budgetlieux").val(data.placeText);
	                        	$("#"+namespace+"project").val(data.projectId).change().selectric('refresh');
	                        	$("#"+namespace+"theme").val(data.themeId).change().selectric('refresh');
	                        	$("#"+namespace+"budgetVideo").val(data.videoURL);
	                        	
	                        	if(data.hasImage) {
	                        		$("#budgetPhotoID").hide();
	                        	}else {
	                        		$("#editPhotoID").hide();
	                        		$("#budgetPhotoMessageID").hide();
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
	});

	/*
	* Lors du click sur le bouton de validation du formulaire
	*/
    $("#sendBudget").click(function(event){
        event.preventDefault();
        var response = validateForm();
        if (response){
        	var iframe = $('.Squire-UI').next('iframe').first()[0];
        	var editor = iframe.contentWindow.editor;
        	$("#"+namespace+"budgetdescription").val(editor.getHTML());
        	$("#uploadForm").submit();
        }
    });
	
    /*
	* Lors du click sur le bouton de pour modifier ou supprimer la photo
	*/
    $("#"+namespace+"editPhoto").click(function(event){
    	$("#"+namespace+"deletePhoto").val("true");
    	$("#editPhotoID").hide();
    	$("#budgetPhotoID").show();
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
        $("#edit-budget-legalage").prop("checked", false);
        $("#edit-budget-cnil").prop("checked", false);
        $("#"+namespace+"budgetPhoto").val("");
        $("#"+namespace+"budgetVideo").val("");
        
        var iframe = $('.Squire-UI').next('iframe').first()[0];
    	var editor = iframe.contentWindow.editor;
    	editor.setHTML('');
    }

    function validateForm()
    {
        var result = true;
        var budgettitle = $("#"+namespace+"budgettitle").val();
        var iframe = $('.Squire-UI').next('iframe').first()[0];
    	var editor = iframe.contentWindow.editor;       	
        var budgetdescription = editor.getHTML();
        var photo = $("#"+namespace+"budgetPhoto").val();
        var regex = new RegExp("^(([0-8][0-9])|(9[0-5]))[0-9]{3}$");
        var legalage = $("#edit-budget-legalage").is(":checked");
        var cnil = $("#edit-budget-cnil").is(":checked");

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

        if ($(budgetdescription).text()===null || $(budgetdescription).text()===""){
            $(iframe).css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $(iframe).css({ "box-shadow" : "" });

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