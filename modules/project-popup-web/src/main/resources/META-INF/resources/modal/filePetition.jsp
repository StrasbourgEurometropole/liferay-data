<%@ include file="/project-popup-init.jsp" %>
<portlet:actionURL var="filePetitionURL" name="filePetition">
	<portlet:param name="cmd" value="filePetition" />
</portlet:actionURL>
<!-- DEPOSER UNE NOUVELLE PETITION -->
<!-- HTML pour la modal de pétition -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalPetition" tabindex="-1" role="dialog" aria-labelledby="modalPetition">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key="modal.filepetition.title"/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>

            <form id="form-file-petition" method="post" action="${filePetitionURL}">
                <div class="pro-wrapper">
                    <h4><liferay-ui:message key="modal.filepetition.information"/></h4>
                    <div class="form-group">
                        <aui:input name="title" label="modal.filepetition.information.title" required="true"/>
                    </div>
                    <div class="form-group">
                        <aui:input type="textarea" name="description" label="modal.filepetition.information.description" required="true"/>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-triple">
                            <label for="petition"><liferay-ui:message key="modal.filepetition.information.projet"/></label>
                            <select name="<portlet:namespace />project">
                                <c:forEach var="project" items="${projects}">
                                    <option value="${project.categoryId}">${project.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group form-triple">
                            <label for="territoire"><liferay-ui:message key="modal.filepetition.information.territoire"/></label>
                            <select name="<portlet:namespace />quartier">
                                <c:forEach var="quartier" items="${quartiers}">
                                    <option value="${quartier.categoryId}">${quartier.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group form-triple">
                            <label for="thematique"><liferay-ui:message key="modal.filepetition.information.thematique"/></label>
                            <select name="<portlet:namespace />theme">
                                <c:forEach var="theme" items="${thematics}">
                                    <option value="${theme.categoryId}">${theme.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="pro-txt-form">
                        <p><liferay-ui:message key="modal.filepetition.information.mayor"/></p>
                    </div>
                </div>
                <div class="pro-wrapper">
                    <h4><liferay-ui:message key="modal.filepetition.user"/></h4>
                    <div class="pro-row">
                        <div class="form-group form-triple">
                            <aui:input name="username" disabled="true" label="modal.user.username" required="true" value="${userConnected.lastName}"/>
                        </div>
                        <div class="form-group form-triple">
                            <aui:input name="firstname" disabled="true" label="modal.user.firstname" required="true" value="${userConnected.firstName}"/>
                        </div>
                        <div class="form-group form-triple">
                            <aui:input id="birthday" name="birthday" cssClass="frm_date" label="modal.user.birthday" required="true" placeholder="jj/mm/aaaa"/>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <aui:input id="address" name="address" label="modal.user.address" required="true"/>
                        </div>
                        <div class="form-group form-half">
                            <div class="form-city">
                                <aui:input id="city" name="city" label="modal.user.city" required="true" placeholder="Strasbourg"/>
                            </div>
                            <div class="form-code">
                                <aui:input id="postalcode" name="postalcode" label="modal.user.postalcode" required="true" placeholder="67XXX"/>
                            </div>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <aui:input name="mail" disabled="true" label="modal.user.mail" required="true" value="${userConnected.email}"/>
                        </div>
                        <div class="form-group form-half">
                            <aui:input id="phone" name="phone" label="modal.user.phone" required="true" placeholder="0611111111"/>
                        </div>
                    </div>
                </div>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="file-petition-legalage" value="legalage">
                        <label for="file-petition-legalage"><liferay-ui:message key="modal.legalage"/></label>
                    </div>
                </div>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="file-petition-cnil" value="cnil">
                        <label for="file-petition-cnil"><liferay-ui:message key="modal.cnil"/></label>
                    </div>
                </div>
                <div class="pro-info-supp">
                    <p><i><liferay-ui:message key="modal.filepetition.condition"/></i></p>
                    <p><liferay-ui:message key="modal.filepetition.contact"/></p>
                </div>
                <div id="sendalert" class="hidden pro-info-supp alertMessage"><liferay-ui:message key="modal.alert"/></div>
                <div class="pro-form-submit">
                    <button id="sendPetition" type="submit" class="btn btn-default"><liferay-ui:message key="modal.filepetition.submit"/></button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script type="text/javascript">
    var namespace = "<portlet:namespace />";
    $("#sendPetition").click(function(event){
        event.preventDefault();
    var response = validateForm();
    if (response){
        $("#form-file-petition").submit();
    }
    });



    function validateForm()
    {
        var result = true;
        var birthday = $("#"+namespace+"birthday").val();
        var city = $("#"+namespace+"city").val();
        var address = $("#"+namespace+"address").val();
        var postalcode = $("#"+namespace+"postalcode").val();
        var phone = $("#"+namespace+"phone").val();
        var legalage = $("#file-petition-legalage").is(":checked");
        var cnil = $("#file-petition-cnil").is(":checked");

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
        }else $("#"+namespace+"postalcode").css({ "box-shadow" : "" });

        if (phone==null || phone==""){
            $("#"+namespace+"phone").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#"+namespace+"phone").css({ "box-shadow" : "" });

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