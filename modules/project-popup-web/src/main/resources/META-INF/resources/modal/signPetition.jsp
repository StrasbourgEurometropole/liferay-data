<%@ include file="/project-popup-init.jsp" %>
<portlet:actionURL var="signPetitionURL" name="signPetition">
	<portlet:param name="cmd" value="signPetition" />
</portlet:actionURL>

<!-- HTML pour la modal d'une pétition -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalSigner" tabindex="-1" role="dialog" aria-labelledby="modalSigner">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

            <div class="pro-modal-top">
                <h3><liferay-ui:message key="modal.signpetition.title"/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
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
				            <aui:input name="username" disabled="true" label="modal.user.username" required="true" value="${userConnected.lastName}"/>
                        </div>
                        <div class="form-group form-triple">
				            <aui:input name="firstname" disabled="true" label="modal.user.firstname" value="${userConnected.firstName}" required="true"/>
                        </div>
                        <div class="form-group form-triple">
				            <aui:input id="signbirthday" cssClass="frm_date" name="birthday" label="modal.user.birthday" required="true" placeholder="jj/mm/aaaa"/>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
				            <aui:input id="signaddress" name="address" label="modal.user.address" required="true"/>
                        </div>
                        <div class="form-group form-half">
                            <div class="form-city">
				                <aui:input id="signcity" name="city" label="modal.user.city" required="true" placeholder="Strasbourg"/>
                            </div>
                            <div class="form-code">
                                <aui:input id="signpostalcode" name="postalcode" label="modal.user.postalcode" required="true" placeholder="67XXX"/>
                            </div>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                                <aui:input id="signmail" name="mail" disabled="true" label="modal.user.mail"  required="true" value="${userConnected.email}"/>
                        </div>
                        <div class="form-group form-half">
                                <aui:input id="signphone" name="phone" label="modal.user.phone" required="true" placeholder="0611111111"/>
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

<script type="text/javascript">
    var namespace = "<portlet:namespace />";
    $("#sendSign").click(function(event){
        event.preventDefault();
    var response = validateForm();
    if (response){
        $("#form-sign-petition").submit();
    }
    });



    function validateForm()
    {
        var result = true;
        var birthday = $("#"+namespace+"signbirthday").val();
        var city = $("#"+namespace+"signcity").val();
        var address = $("#"+namespace+"signaddress").val();
        var postalcode = $("#"+namespace+"signpostalcode").val();
        var phone = $("#"+namespace+"signphone").val();
        var signlegalage = $("#signlegalage").is(":checked");
        var signcnil = $("#signcnil").is(":checked");

        if (birthday==null || birthday==""){
            $("#"+namespace+"signbirthday").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#"+namespace+"signbirthday").css({ "box-shadow" : "" });

        if (city==null || city==""){
            $("#"+namespace+"signcity").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#"+namespace+"signcity").css({ "box-shadow" : "" });

        if (address==null || address==""){
            $("#"+namespace+"signaddress").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#"+namespace+"signaddress").css({ "box-shadow" : "" });

        if (postalcode==null || postalcode==""){
            $("#"+namespace+"signpostalcode").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#"+namespace+"signpostalcode").css({ "box-shadow" : "" });

        if (phone==null || phone==""){
            $("#"+namespace+"signphone").css({ "box-shadow" : "0 0 10px #CC0000" });
            result = false;
        }else $("#"+namespace+"signphone").css({ "box-shadow" : "" });

        if (!signlegalage)
            result = false;

        if (!signcnil)
            result = false;

        if (!result)
            $("#signalert").removeClass("hidden");
        else $("#signalert").addClass("hidden");
        return result;
    }
</script>