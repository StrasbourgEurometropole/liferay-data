<%@ include file="/project-popup-init.jsp" %>
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
                            <label for="nom"><liferay-ui:message key="modal.user.username"/><span class="required">*</span></label>
                            <input type="text" class="form-control" id="username" placeholder="Dupond"/>
                        </div>
                        <div class="form-group form-triple">
                            <label for="prenom"><liferay-ui:message key="modal.user.firstname"/></label>
                            <input type="text" class="form-control" id="firstname" placeholder="Jean"/>
                        </div>
                        <div class="form-group form-triple">
                            <label for="date"><liferay-ui:message key="modal.user.birthday"/><span class="required">*</span></label>
                            <input type="text" class="form-control frm_date" id="birthday" placeholder="jj/mm/aaaa" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <label for="adresse"><liferay-ui:message key="modal.user.address"/><span class="required">*</span></label>
                            <input type="text" class="form-control" id="address"/>
                        </div>
                        <div class="form-group form-half">
                            <div class="form-city">
                                <label for="city"><liferay-ui:message key="modal.user.city"/><span class="required">*</span></label>
                                <input type="text" class="form-control" id="city"/>
                            </div>
                            <div class="form-code">
                                <label for="code"><liferay-ui:message key="modal.user.postalcode"/><span class="required">*</span></label>
                                <input type="text" class="form-control" id="postalcode"/>
                            </div>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <label for="email"><liferay-ui:message key="modal.user.mail"/><span class="required">*</span></label>
                            <input type="email" class="form-control" id="mail" placeholder="jean.dupond@gmail.com">
                        </div>
                        <div class="form-group form-half">
                            <label for="tel"><liferay-ui:message key="modal.user.phone"/><span class="required">*</span></label>
                            <input type="text" class="form-control" id="phone"/>
                        </div>
                    </div>
                </div>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="legalage" value="optin">
                        <label for="optin"><liferay-ui:message key="modal.legalage"/></label>
                    </div>
                </div>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="cnil" value="optin">
                        <label for="optin-2"><liferay-ui:message key="modal.cnil"/></label>
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

