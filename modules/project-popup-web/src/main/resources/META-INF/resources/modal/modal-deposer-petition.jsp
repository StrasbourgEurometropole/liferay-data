<!-- DEPOSER UNE NOUVELLE PETITION -->
<!-- HTML pour la modal de pétition -->
<div class="pro-modal pro-bloc-pcs-form fade" id="modalPetition" tabindex="-1" role="dialog" aria-labelledby="modalPetition">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="pro-modal-top">
                <h3>Déposer un pétition adressée au maire </h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><span class="icon-multiply"></span></span></button>
            </div>

            <form>
                <div class="pro-wrapper">
                    <h4>Informations sur la pétition</h4>
                    <div class="form-group">
                        <label for="titre">Titre de la pétition <span class="required">*</span></label>
                        <input type="text" class="form-control" id="titre"/>
                    </div>
                    <div class="form-group">
                        <label for="description">Description <span>*</span></label>
                        <textarea id="description" class="form-control" rows="3"></textarea>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-triple">
                            <label for="petition">Pétition déposée au nom de</label>
                            <select id="petition">
                                <option>Nom 1</option>
                                <option>Nom 2</option>
                            </select>
                        </div>
                        <div class="form-group form-triple">
                            <label for="territoire">Territoire concerné</label>
                            <select id="territoire">
                                <option>Thématique 1</option>
                                <option>Thématique 2</option>
                            </select>
                        </div>
                        <div class="form-group form-triple">
                            <label for="thematique">Thématiques</label>
                            <select id="thematique">
                                <option>Thématique 1</option>
                                <option>Thématique 2</option>
                            </select>
                        </div>
                    </div>
                    <div class="pro-txt-form">
                        <p>Pétition à l'attention du Maire de Strasbourg</p>
                    </div>
                </div>
                <div class="pro-wrapper">
                    <h4>Informations sur le pétitionnaire</h4>
                    <div class="pro-row">
                        <div class="form-group form-triple">
                            <label for="nom-3">Nom <span class="required">*</span></label>
                            <input type="text" class="form-control" id="nom-3" placeholder="Dupond"/>
                        </div>
                        <div class="form-group form-triple">
                            <label for="prenom-3">Prénom</label>
                            <input type="text" class="form-control" id="prenom-3" placeholder="Jean"/>
                        </div>
                        <div class="form-group form-triple">
                            <label for="date-3">Date de naissance <span class="required">*</span></label>
                            <input type="text" class="form-control frm_date" id="date-3" placeholder="jj/mm/aaaa" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <label for="adresse-3">Adresse <span class="required">*</span></label>
                            <input type="text" class="form-control" id="adresse-3" />
                        </div>
                        <div class="form-group form-half">
                            <div class="form-city">
                                <label for="city-3">Ville <span class="required">*</span></label>
                                <input type="text" class="form-control" id="city-3" />
                            </div>
                            <div class="form-code">
                                <label for="code-3">Code postal <span class="required">*</span></label>
                                <input type="text" class="form-control" id="code-3" />
                            </div>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <label for="email-3">Adresse mail <span class="required">*</span></label>
                            <input type="email" class="form-control" id="email-3" placeholder="jean.dupond@gmail.com">
                        </div>
                        <div class="form-group form-half">
                            <label for="tel-3">Téléphone</label>
                            <input type="text" class="form-control" id="tel-3"/>
                        </div>
                    </div>
                </div>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="optin-3" value="optin">
                        <label for="optin">Je certifie sur l’honneur être âgé-e de seize ans ou plus, habiter Strasbourg, ne pas être élu-e au Conseil municipal de la ville de Strasbourg, ainsi que l’exactitude des renseignements ci-dessus. Un justificatif pourra être demandé par la Ville lors de la première rencontre.</label>
                    </div>
                </div>
                <div class="pro-optin form-checkbox">
                    <div>
                        <input type="checkbox" id="optin-10" value="optin-3">
                        <label for="optin-10">Je consens XXXX (Conditions CNIL à préciser)</label>
                    </div>
                </div>
                <div class="pro-info-supp">
                    <p><i>Les services de la Ville vous recontacteront par mail ou par téléphone, dans les meilleurs délais avant deux mois, afin de vous indiquer si la pétition est recevable et si elle pourra ainsi être diffusée pour être signée.</i></p>
                    <p>Contact Mission Participation citoyenne : XXXX</p>
                </div>
                <div class="pro-form-submit">
                    <button type="submit" class="btn btn-default">Envoyer la pétition</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
