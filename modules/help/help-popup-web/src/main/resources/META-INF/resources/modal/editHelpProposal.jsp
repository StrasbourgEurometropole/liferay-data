<%@ include file="/help-popup-init.jsp" %>

<portlet:resourceURL id="getHelpProposal" var="getHelpProposalURL" >
</portlet:resourceURL>

<portlet:actionURL name="editHelpProposal" var="editHelpProposalURL">
	<portlet:param name="redirectURL" value="${redirectURL}"/>
</portlet:actionURL>
<liferay-ui:error key="user" message="modal.edithelpproposal.error.user-unknow" />
<liferay-ui:error key="title" message="modal.edithelpproposal.error.title" />
<liferay-ui:error key="types" message="modal.edithelpproposal.error.types" />
<liferay-ui:error key="description" message="modal.edithelpproposal.error.description" />
<liferay-ui:error key="helper" message="modal.edithelpproposal.error.helper" />
<liferay-ui:error key="name" message="modal.edithelpproposal.error.name" />
<liferay-ui:error key="localisation" message="modal.edithelpproposal.error.localisation" />
<liferay-ui:error key="extension" message="modal.edithelpproposal.error.extension" />

<!-- MODIFIER UNE PROPOSAITION D'AIDE' -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalEditHelpProposal" tabindex="-1" role="dialog" aria-labelledby="modalEditHelpProposal"
	data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key="modal.edithelpproposal.title"/></h3>
                <button type="button" class="close closefirstmodal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>

            <form id="uploadForm"  method="post" action="${editHelpProposalURL}" enctype="multipart/form-data">
                <div class="pro-wrapper">
                    <h4><liferay-ui:message key="modal.edithelpproposal.information"/></h4>
                    <div class="form-group">
                        <aui:input id="title" name="title" label="modal.submit.help.information.title" required="true" maxlength="256" value=""/>
                    </div>
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
                    <div class="form-group">
                    	<aui:input id="helpproposaldescription" name="helpproposaldescription" type="hidden"/>
                        <aui:input name="squiredescription" type="textarea" required="true" cssClass="form-control form-squire-target" label="modal.submit.help.information.presentation"/>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <aui:select required="true" name="helper" label="modal.submit.help.information.helper">
                                <c:forEach var="helper" items="${helpers}">
                                    <aui:option value="${helper.categoryId}" label="${helper.name}" />
                                </c:forEach>
                            </aui:select>
                        </div>
                        <div class="form-group form-half">
                            <aui:input id="inTheNameOf" name="inTheNameOf" label="modal.submit.help.information.inTheNameOf" required="true" maxlength="400" value="${userConnected.get('first_name')} ${fn:substring(userConnected.get('last_name'),0,1)}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <aui:input id="language" name="language" label="modal.submit.help.information.language" maxlength="256" value=""/>
                    </div>
                    <div class="form-group">
                        <aui:select required="true" name="localisation" label="modal.submit.help.information.territories">
                            <c:forEach var="localisation" items="${localisations}">
                                <c:set var="category" value="${localisation}" scope="request"/>
                                <c:set var="level" value="0" scope="request" />
                                <jsp:include page="/include/category-option.jsp"/>
                            </c:forEach>
                        </aui:select>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-two-tiers">
                        	<div id="helpProposalphotoID">
	                            <span class="browsePicture input-group-btn">
	                                <aui:input name="photo" type="file" label="modal.submit.help.information.picture"
	                                    cssClass="btn btn-default btn-choose">
								        <aui:validator name="acceptFiles">'jpg,png,jpeg'</aui:validator>
	                                </aui:input>
	                                <label style="color:#ff0000;font-weight:bold" id="photoMessageID"><liferay-ui:message key="modal.edithelpproposal.information.picture.edit.red.message.information"/></label>
	                            </span>
                            </div>
                            <div id="editPhotoID">
	                            <span class="input-group-btn">
	                                <aui:input name="editPhoto" type="button" value="Modifier ou supprimer" 
	                                label="modal.edithelpproposal.information.picture.edit.message">
	                                </aui:input>
	                            </span>
                            </div>
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
                <div class="pro-optin form-checkbox" >
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
                <div id="sendalert" class="hidden pro-info-supp alertMessage"><liferay-ui:message key="modal.alert"/></div>
                <!-- Champ cache : ID -->
                <input type="hidden" id="<portlet:namespace />deletePhoto" name="<portlet:namespace />deletePhoto" value="false"/>
                <input type="hidden" id="<portlet:namespace />entryId" name="<portlet:namespace />entryId" value="${entryId}"/>
                <div class="pro-form-submit">
                    <button id="sendHelpProposal" type="button" class="btn btn-default"><liferay-ui:message key="modal.edithelpproposal.submit"/></button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script type="text/javascript">

	var namespaceEditHelpProposal = "<portlet:namespace />";
    
    
    /*
	* Lors du click sur le bouton d'ouverture de la popup
	*/
	$(document).on("click", "[href='#showModalEditHelpProposal']", function(event) {
		event.preventDefault();
		var entryId = $("#"+namespaceEditHelpProposal+"entryId").val();

     	// Chebox de conditions et de sauvegade des informations
        $("#<portlet:namespace />legalage").prop("checked", false);
        $("#<portlet:namespace />security").prop("checked", false);
        $("#<portlet:namespace />security2").prop("checked", false);
        $("#<portlet:namespace />responsability").prop("checked", false);
		
		AUI().use('aui-io-request', function(A) {
            try {
                A.io.request('${getHelpProposalURL}', {
                    method : 'POST',
                    dataType: 'json',
                    data:{
                    	<portlet:namespace/>entryId : entryId
                    },
                    on: {
                    	success: function(e) {
                            var data = this.get('responseData');

                            $("#"+namespaceEditHelpProposal+"title").val(data.title);
                            var types = $("input[id^="+namespaceEditHelpProposal+"type-]");
                            types.each(function(){
                                if($.inArray(Number(this.value),data.types) >= 0)
                                    $(this).prop("checked", true);
                                else
                                    $(this).prop("checked", false);
                            });
                            var iframe = $('.Squire-UI').next('iframe').first()[0];
                            var editor = iframe.contentWindow.editor;
                            editor.setHTML(data.description);
                            $("#"+namespaceEditHelpProposal+"helper").val(data.helperId).change().selectric('refresh');
                            $("#"+namespaceEditHelpProposal+"inTheNameOf").val(data.inTheNameOf);
                            $("#"+namespaceEditHelpProposal+"language").val(data.languages);
                            $("#"+namespaceEditHelpProposal+"localisation").val(data.localisations).change().selectric('refresh');

                            if(data.hasImage) {
                                $("#helpProposalPhotoID").hide();
                                $("#editPhotoID").show();
                                $("#helpProposalPhotoMessageID").show();
                            }else {
                                $("#editPhotoID").hide();
                                $("#helpProposalPhotoMessageID").hide();
                                $("#helpProposalPhotoID").show();
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
    $("#sendHelpProposal").click(function(event){
        var response = validateFormEditHelpProposal();
        if (response){
        	var iframe = $('.Squire-UI').next('iframe').first()[0];
        	var editor = iframe.contentWindow.editor;
        	$("#"+namespaceEditHelpProposal+"helpproposaldescription").val(editor.getHTML());
        	$("#uploadForm").submit();
        }
    });
	
    /*
	* Lors du click sur le bouton de pour modifier ou supprimer la photo
	*/
    $("#"+namespaceEditHelpProposal+"editPhoto").click(function(event){
    	$("#"+namespaceEditHelpProposal+"deletePhoto").val("true");
    	$("#editPhotoID").hide();
    	$("#HelpProposalPhotoID").show();
    });

    function validateFormEditHelpProposal()
    {
        var result = true;

        var title = $("#<portlet:namespace />title").val();
        if (title===null || title===""){
            $("#<portlet:namespace />title").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#<portlet:namespace />title").css({ "box-shadow" : "" });

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

        var iframe = $('.Squire-UI').next('iframe').first()[0];
    	var editor = iframe.contentWindow.editor;
        var helpproposaldescription = editor.getHTML();
        if ($(helpproposaldescription).text()===null || $(helpproposaldescription).text()===""){
            $(iframe).css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $(iframe).css({ "box-shadow" : "" });

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
            $("#sendalert").removeClass("hidden");
        else
            $("#sendalert").addClass("hidden");
        
        return result;
    }
</script>

<style>
    label .required{
        color: red;
        font-size:1em;
    }
</style>