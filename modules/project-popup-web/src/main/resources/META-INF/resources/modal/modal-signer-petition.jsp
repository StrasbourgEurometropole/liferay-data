<!-- HTML pour la modal d'une pétition -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalSigner" tabindex="-1" role="dialog" aria-labelledby="modalSigner">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3>Signer la pétition</h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>

            <form>
                <div class="pro-wrapper">
                    <div class="pro-txt-intro">
                        <p>Lorem ipsum dolor sit amet,consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                            exercitation ullamco laboris</p>
                        <a href="#" class="pro-link-form">En savoir plus</a>
                    </div>
                </div>
                <div class="pro-wrapper">
                    <h4>Informations sur le signataire</h4>
                    <div class="pro-row">
                        <div class="form-group form-triple">
                            <label for="nom">Nom <span class="required">*</span></label>
                            <input type="text" class="form-control" id="nom" placeholder="Dupond"/>
                        </div>
                        <div class="form-group form-triple">
                            <label for="prenom">Prénom</label>
                            <input type="text" class="form-control" id="prenom" placeholder="Jean"/>
                        </div>
                        <div class="form-group form-triple">
                            <label for="date">Date de naissance <span class="required">*</span></label>
                            <input type="text" class="form-control frm_date" id="date" placeholder="jj/mm/aaaa" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <label for="adresse">Adresse <span class="required">*</span></label>
                            <input type="text" class="form-control" id="adresse"/>
                        </div>
                        <div class="form-group form-half">
                            <div class="form-city">
                                <label for="city">Ville <span class="required">*</span></label>
                                <input type="text" class="form-control" id="city"/>
                            </div>
                            <div class="form-code">
                                <label for="code">Code postal <span class="required">*</span></label>
                                <input type="text" class="form-control" id="code"/>
                            </div>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <label for="email">Adresse mail <span class="required">*</span></label>
                            <input type="email" class="form-control" id="email" placeholder="jean.dupond@gmail.com">
                        </div>
                        <div class="form-group form-half">
                            <label for="tel">Téléphone <span class="required">*</span></label>
                            <input type="text" class="form-control" id="tel"/>
                        </div>
                    </div>
                </div>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="optin" value="optin">
                        <label for="optin">Je certifie sur l’honneur être âgé-e de seize ans ou plus, habiter Strasbourg, ainsi que l’exactitude des renseignements ci-dessus. Un justificatif
                            pourra être demandé par la Ville lors du contrôle de recevabilité de la pétition.</label>
                    </div>
                </div>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="optin-2" value="optin">
                        <label for="optin-2">Je consens XXXX (Conditions CNIL à préciser)</label>
                    </div>
                </div>
                <div class="pro-info-supp">
                    <p><i>Les services de la Ville pourront être amené à vous contacter par mail ou par téléphone afin de contrôler la recevabilité des signatures de la pétition.</i></p>
                </div>
                <div class="pro-form-submit">
                    <button type="submit" class="btn btn-default">Signer la pétition</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

