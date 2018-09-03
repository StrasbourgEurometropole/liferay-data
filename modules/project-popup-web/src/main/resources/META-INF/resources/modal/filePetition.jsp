<%@ include file="/project-popup-init.jsp" %>
<!-- DEPOSER UNE NOUVELLE PETITION -->
<!-- HTML pour la modal de pétition -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalPetition" tabindex="-1" role="dialog" aria-labelledby="modalPetition">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3><liferay-ui:message key="modal.filepetition.title"/></h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>

            <form>
                <div class="pro-wrapper">
                    <h4><liferay-ui:message key="modal.filepetition.information"/></h4>
                    <div class="form-group">
                        <label for="titre"><liferay-ui:message key="modal.filepetition.information.title"/> <span class="required">*</span></label>
                        <input type="text" class="form-control" id="titre"/>
                    </div>
                    <div class="form-group">
                        <label for="description"><liferay-ui:message key="modal.filepetition.information.description"/> <span>*</span></label>
                        <textarea id="description" class="form-control" rows="3"></textarea>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-triple">
                            <label for="petition"><liferay-ui:message key="modal.filepetition.information.nom"/></label>
                            <select id="petition">
                                <option>Nom 1</option>
                                <option>Nom 2</option>
                            </select>
                        </div>
                        <div class="form-group form-triple">
                            <label for="territoire"><liferay-ui:message key="modal.filepetition.information.territoire"/></label>
                            <select id="territoire">
                                <option>Thématique 1</option>
                                <option>Thématique 2</option>
                            </select>
                        </div>
                        <div class="form-group form-triple">
                            <label for="thematique"><liferay-ui:message key="modal.filepetition.information.thematique"/></label>
                            <select id="thematique">
                                <option>Thématique 1</option>
                                <option>Thématique 2</option>
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
                            <label for="nom-3"><liferay-ui:message key="modal.user.username"/> <span class="required">*</span></label>
                            <input type="text" class="form-control" id="nom-3" placeholder="Dupond"/>
                        </div>
                        <div class="form-group form-triple">
                            <label for="prenom-3"><liferay-ui:message key="modal.user.firstname"/></label>
                            <input type="text" class="form-control" id="prenom-3" placeholder="Jean"/>
                        </div>
                        <div class="form-group form-triple">
                            <label for="date-3"><liferay-ui:message key="modal.user.birthday"/> <span class="required">*</span></label>
                            <input type="text" class="form-control frm_date" id="date-3" placeholder="jj/mm/aaaa" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <label for="adresse-3"><liferay-ui:message key="modal.user.address"/> <span class="required">*</span></label>
                            <input type="text" class="form-control" id="adresse-3" />
                        </div>
                        <div class="form-group form-half">
                            <div class="form-city">
                                <label for="city-3"><liferay-ui:message key="modal.user.city"/> <span class="required">*</span></label>
                                <input type="text" class="form-control" id="city-3" />
                            </div>
                            <div class="form-code">
                                <label for="code-3"><liferay-ui:message key="modal.user.postalcode"/> <span class="required">*</span></label>
                                <input type="text" class="form-control" id="code-3" />
                            </div>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <label for="email-3"><liferay-ui:message key="modal.user.mail"/> <span class="required">*</span></label>
                            <input type="email" class="form-control" id="email-3" placeholder="jean.dupond@gmail.com">
                        </div>
                        <div class="form-group form-half">
                            <label for="tel-3"><liferay-ui:message key="modal.user.phone"/></label>
                            <input type="text" class="form-control" id="tel-3"/>
                        </div>
                    </div>
                </div>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="optin-3" value="optin">
                        <label for="optin"><liferay-ui:message key="modal.legalage"/></label>
                    </div>
                </div>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="optin-10" value="optin-3">
                        <label for="optin-10"><liferay-ui:message key="modal.cnil"/></label>
                    </div>
                </div>
                <div class="pro-info-supp">
                    <p><i><liferay-ui:message key="modal.filepetition.condition"/></i></p>
                    <p><liferay-ui:message key="modal.filepetition.contact"/></p>
                </div>
                <div class="pro-form-submit">
                    <button type="submit" class="btn btn-default"><liferay-ui:message key="modal.filepetition.submit"/></button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
