<%@ include file="/project-popup-init.jsp" %>
<portlet:actionURL var="signPetition" name="signPetition">
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
            <form id="form-sign-petition" method="post" action="${signPetition}">
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
				            <aui:input name="birthday" label="modal.user.birthday" required="true" placeholder="jj/mm/aaaa"/>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
				            <aui:input name="address" label="modal.user.address" required="true"/>
                        </div>
                        <div class="form-group form-half">
                            <div class="form-city">
				                <aui:input name="city" label="modal.user.city" required="true" placeholder="Strasbourg"/>
                            </div>
                            <div class="form-code">
                                <aui:input name="postalcode" label="modal.user.postalcode" required="true" placeholder="67XXX"/>
                            </div>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                                <aui:input name="mail" disabled="true" label="modal.user.mail"  required="true" value="${userConnected.email}"/>
                        </div>
                        <div class="form-group form-half">
                                <aui:input name="phone" label="modal.user.phone" required="true" placeholder="0611111111"/>
                        </div>
                    </div>
                </div>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="legalage" value="legalage">
                        <label for="legalage"><liferay-ui:message key="modal.legalage"/></label>
                    </div>
                </div>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="cnil" value="cnil">
                        <label for="cnil"><liferay-ui:message key="modal.cnil"/></label>
                    </div>
                </div>
                <div class="pro-info-supp">
                    <p><i><liferay-ui:message key="modal.signpetition.condition"/></i></p>
                </div>
                <div class="pro-form-submit">
                    <button type="submit" class="btn btn-default"><liferay-ui:message key="modal.signpetition.submit"/></button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

