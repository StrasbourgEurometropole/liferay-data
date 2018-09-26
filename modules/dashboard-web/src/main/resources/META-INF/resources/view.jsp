<%@ include file="/init.jsp" %>

<div class="pro-bloc-dashboard">
    <div class="container pro-user">
        <a href="#pro-onglet-account">
            <figure>
                <img src="assets/images/medias/comm-vincent.jpg" width="40" height="40" alt="Nom de l'utilisateur"/>
            </figure>
            <span>Voir mon profil</span>
        </a>
        <span>Bienvenue, <span id="pro-user-name">Marc B.</span></span>
    </div>

    <div class="container pro-wrapper-dashboard">


        <!-- ONGLET ACTIVITE -->
        <div id="pro-onglet-activite">
            <div class="pro-title-dashboard col-xs-12"><h1>Mon activité citoyenne</h1><span></span></div>

            <div class="col-lg-3 col-sm-6 col-xs-12">
                <a href="#pro-link-listing-projet" class="pro-item pro-item-projet">
                    <div class="pro-item-center">
                        <span class="pro-number">10</span>
                        <span class="pro-txt">Projets<br>Suivis</span>
                    </div>
                </a>
                <a href="#pro-link-listing-event" class="pro-item pro-item-agenda">
                    <div class="pro-item-center">
                        <span class="pro-number">15</span>
                        <span class="pro-txt">évènements<br>à venir</span>
                    </div>
                    <div class="pro-link-dashboard"><span class="pro-txt">Voir les évènements suivis</span></div>
                </a>
            </div>

            <div class="col-lg-3 col-sm-6 col-xs-12">
                <div class="pro-item pro-item-petition">
                    <div class="pro-item-center">
                        <span class="pro-number">12</span>
                        <span class="pro-txt">Pétitions</span>
                    </div>
                    <div class="pro-link-dashboard">
                        <a href="#pro-link-listing-petition-signe" class="pro-txt"><strong>8</strong> Pétitions signées</a>
                        <a href="#pro-link-listing-petition-depose" class="pro-txt"><strong>4</strong> Pétitions déposées</a>
                    </div>
                </div>
            </div>

            <div class="col-lg-3 col-sm-6 col-xs-12">
                <div class="pro-item pro-item-initiative">
                    <div class="pro-item-center">
                        <div>
                            <span class="icon-ico-initiative"></span>
                            <span class="pro-txt">Initiatives</span>
                        </div>
                    </div>
                    <div class="pro-bloc-link-dashboard">
                        <a href="#pro-link-listing-initiative-signe" class="pro-txt"><strong>6</strong><span>Initiatives<br>signées</span></a>
                        <a href="#pro-link-listing-initiative-aide" class="pro-txt"><strong>11</strong><span>Initiatives<br>aidées</span></a>
                    </div>
                </div>
            </div>

            <div class="col-lg-3 col-sm-6 col-xs-12">
                <div class="pro-item pro-item-budget">
                    <div class="pro-item-center">
                        <p>Il reste 10 jours, 14 heures et 18 minutes pour voter</p>
                    </div>
                    <span class="pro-title">Budget<br>Participatif</span>
                    <div class="pro-link-dashboard">
                        <a href="#pro-link-listing-projet-soumis" class="pro-txt"><strong>2</strong><span> Projets soumis</span></a>
                        <a href="#pro-link-listing-projet-vote" class="pro-txt"><strong>3</strong><span> Projets votés</span></a>
                    </div>
                    <div class="pro-info-vote"><span>Il vous reste <strong>2 votes</strong></span></div>
                </div>
            </div>
        </div>


        <!-- ONGLET ACCOUNT -->
        <div id="pro-onglet-account" class="pro-hide">
            <div class="pro-title-dashboard col-xs-12"><h2>Mon compte</h2><span></span></div>
            <div class="col-xs-12"><a href="#pro-onglet-activite" class="pro-btn-back">Mes informations</a></div>

            <form method="post" action="/">
                <div class="pro-wrapper col-md-3">
                    <div class="profile">
                        <div class="photo">
                            <input type="file" accept="image/*">
                            <div class="photo__helper">
                                <div class="photo__frame photo__frame--circle">
                                    <img src="assets/images/medias/img-account.png" width="185" height="185" alt="Image" class="pro-img-bg"/>
                                    <canvas class="photo__canvas"></canvas>

                                    <div class="pro-photo-hover">
                                        <span class="icon-ico-user"></span>
                                        <p>Modifier ma photo de profil</p>
                                    </div>
                                    <div class="message is-wrong-image-size">
                                        <p>Votre photo doit être plus large que 350 pixels.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="pro-wrapper col-md-9">
                    <div class="pro-row">
                        <div class="form-group form-third">
                            <label for="nom">Nom <span class="required">*</span></label>
                            <input type="text" class="form-control disabled" id="nom" value="Burton" disabled/>
                        </div>
                        <div class="form-group form-third">
                            <label for="prenom">Prénom <span class="required">*</span></label>
                            <input type="text" class="form-control disabled" id="prenom" value="Marc" disabled/>
                        </div>
                        <div class="form-group form-third">
                            <label for="date">Date de naissance <span class="required">*</span></label>
                            <input type="text" class="form-control frm_date" id="date" placeholder="jj/mm/aaaa"/>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
                            <label for="adresse">Adresse <span class="required">*</span></label>
                            <input type="text" class="form-control" id="adresse" value="31 rue de la Boucherie"/>
                        </div>
                        <div class="form-group form-half">
                            <div class="form-city">
                                <label for="city">Ville <span class="required">*</span></label>
                                <input type="text" class="form-control" id="city" value="Strasbourg"/>
                            </div>
                            <div class="form-code">
                                <label for="code">Code postal <span class="required">*</span></label>
                                <input type="text" class="form-control" id="code" value="67000"/>
                            </div>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-third">
                            <label for="email">Adresse mail <span class="required">*</span></label>
                            <input type="email" class="form-control" id="email" value="marc.burton@gmail.com">
                        </div>
                        <div class="form-group form-third">
                            <label for="tel_fixe">Téléphone Fixe</label>
                            <input type="text" class="form-control" id="tel_fixe"/>
                        </div>
                        <div class="form-group form-third">
                            <label for="tel_mobile">Téléphone mobile</label>
                            <input type="text" class="form-control" id="tel_mobile" placeholder="+33 6 87 52 47 30"/>
                        </div>
                    </div>
                    <div class="pro-form-submit pro-row">
                        <button type="submit" class="btn btn-default">Sauvegarder mon profil</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>