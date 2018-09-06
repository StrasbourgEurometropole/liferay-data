<%@ include file="/project-popup-init.jsp" %>

<portlet:actionURL var="giveInitiativeHelp" name="giveInitiativeHelp">
</portlet:actionURL>

<!-- MODAL PROPOSER MON AIDE -->
        <div class="pro-modal pro-bloc-pcs-form fade" id="modalAide" tabindex="-1" role="dialog" aria-labelledby="modalAide">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="pro-modal-top">
                        <h3>Proposer mon aide</h3>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
                    </div>

                    <form method="post" action="${giveInitiativeHelp}">
                        <div class="pro-wrapper">
                            <div class="pro-row">
                                <div class="form-group form-half">
                                    <label for="nom">Nom <span class="required">*</span></label>
                                    <input type="text" class="form-control" id="nom"/>
                                </div>
                                <div class="form-group form-half pro-form-error">
                                    <label for="prenom">Prénom <span class="required">*</span></label>
                                    <input type="text" class="form-control" id="prenom"/>
                                </div>
                            </div>
                            <div class="pro-row">
                                <div class="form-group form-half">
                                    <label for="email">Adresse mail <span class="required">*</span></label>
                                    <input type="email" class="form-control" id="email">
                                </div>
                                <div class="form-group form-half">
                                    <label for="tel">Téléphone <span class="required">*</span></label>
                                    <input type="text" class="form-control" id="tel"/>
                                </div>
                            </div>
                            <div class="form-checkbox">
                                <span class="label">Quelle(s) aide voulez-vous proposer <span>*</span></span>
                                <div class="checkbox-inline">
                                    <input type="checkbox" id="inlineCheckbox1" value="option1">
                                    <label for="inlineCheckbox1">Du temps</label>
                                </div>
                                <div class="checkbox-inline">
                                    <input type="checkbox" id="inlineCheckbox2" value="option2">
                                    <label for="inlineCheckbox2" class="checkbox-inline">De l'argent</label>
                                </div>
                                <div class="checkbox-inline">
                                    <input type="checkbox" id="inlineCheckbox3" value="option3">
                                    <label for="inlineCheckbox3" class="checkbox-inline">Un lieu</label>
                                </div>
                                <div class="checkbox-inline">
                                    <input type="checkbox" id="inlineCheckbox4" value="option4">
                                    <label for="inlineCheckbox4" class="checkbox-inline">Une expertise</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="message-3">Message <span>*</span></label>
                                <textarea id="message-3" class="form-control" rows="3"></textarea>
                            </div>
                        </div>
                        <div class="pro-optin form-checkbox">
                            <div>
                                <input type="checkbox" id="optin" value="optin">
                                <label for="optin">Je consens XXXX (Conditions CNIL à préciser)</label>
                            </div>
                        </div>
                        <div class="pro-form-submit">
                            <button type="submit" class="btn btn-default">Proposer mon aide</button>
                        </div>
                    </form>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->