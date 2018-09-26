<%@ include file="/init.jsp" %>

<portlet:actionURL var="saveProfilURL" name="saveProfil">
	<portlet:param name="redirectURL" value="${redirectURL}"/>
	<portlet:param name="cmd" value="saveProfil" />
</portlet:actionURL>

<div class="pro-bloc-dashboard">
    <div class="container pro-user">
        <a href="#pro-onglet-account">
            <figure>
                <img src="assets/images/medias/comm-vincent.jpg" width="40" height="40" alt="Nom de l'utilisateur"/>
            </figure>
            <span><liferay-ui:message key="dashboard.front.profil"/></span>
        </a>
        <span><liferay-ui:message key="dashboard.front.welcome"/><span id="pro-user-name">Marc B.</span></span>
    </div>

    <div class="container pro-wrapper-dashboard">

        <!-- ONGLET ACTIVITE -->
        <div id="pro-onglet-activite">
            <div class="pro-title-dashboard col-xs-12"><h1><liferay-ui:message key="dashboard.front.myactivity"/></h1><span></span></div>

            <div class="col-lg-3 col-sm-6 col-xs-12">
                <a href="#pro-link-listing-projet" class="pro-item pro-item-projet">
                    <div class="pro-item-center">
                        <span class="pro-number">${projectFollowedsCount}</span>
                        <span class="pro-txt"><liferay-ui:message key="dashboard.front.project"/></span>
                    </div>
                </a>
                <a href="#pro-link-listing-event" class="pro-item pro-item-agenda">
                    <div class="pro-item-center">
                        <span class="pro-number">${eventParticipationsCount}</span>
                        <span class="pro-txt"><liferay-ui:message key="dashboard.front.event"/></span>
                    </div>
                    <div class="pro-link-dashboard"><span class="pro-txt"><liferay-ui:message key="dashboard.front.event.goto"/></span></div>
                </a>
            </div>

            <div class="col-lg-3 col-sm-6 col-xs-12">
                <div class="pro-item pro-item-petition">
                    <div class="pro-item-center">
                        <span class="pro-number">${petitionFiledCount+petitionSignedCount}</span>
                        <span class="pro-txt"><liferay-ui:message key="dashboard.front.petition"/></span>
                    </div>
                    <div class="pro-link-dashboard">
                        <a href="#pro-link-listing-petition-signe" class="pro-txt"><strong>${petitionSignedCount}</strong><liferay-ui:message key="dashboard.front.petition.signed"/></a>
                        <a href="#pro-link-listing-petition-depose" class="pro-txt"><strong>${petitionFiledCount}</strong><liferay-ui:message key="dashboard.front.petition.filed"/></a>
                    </div>
                </div>
            </div>

            <div class="col-lg-3 col-sm-6 col-xs-12">
                <div class="pro-item pro-item-initiative">
                    <div class="pro-item-center">
                        <div>
                            <span class="icon-ico-initiative"></span>
                            <span class="pro-txt"><liferay-ui:message key="dashboard.front.initiative"/></span>
                        </div>
                    </div>
                    <div class="pro-bloc-link-dashboard">
                        <a href="#pro-link-listing-initiative-signe" class="pro-txt"><strong>6</strong><span><liferay-ui:message key="dashboard.front.initiative.signed"/></span></a>
                        <a href="#pro-link-listing-initiative-aide" class="pro-txt"><strong>11</strong><span><liferay-ui:message key="dashboard.front.initiative.filed"/></span></a>
                    </div>
                </div>
            </div>

            <div class="col-lg-3 col-sm-6 col-xs-12">
                <div class="pro-item pro-item-budget">
                    <div class="pro-item-center">
                        <p></p>
                    </div>
                    <span class="pro-title"><liferay-ui:message key="dashboard.front.budget"/></span>
                    <div class="pro-link-dashboard">
                        <a href="#pro-link-listing-projet-soumis" class="pro-txt"><strong>2</strong><span><liferay-ui:message key="dashboard.front.budget.voted"/></span></a>
                        <a href="#pro-link-listing-projet-vote" class="pro-txt"><strong>3</strong><span><liferay-ui:message key="dashboard.front.budget.filed"/></span></a>
                    </div>
                    <div class="pro-info-vote"><span><liferay-ui:message key="dashboard.front.budget.reliquat"/></span></div>
                </div>
            </div>
        </div>


        <!-- ONGLET ACCOUNT -->
        <div id="pro-onglet-account" class="pro-hide">
            <div class="pro-title-dashboard col-xs-12"><h2><liferay-ui:message key="dashboard.account.title"/></h2><span></span></div>
            <div class="col-xs-12"><a href="#pro-onglet-activite" class="pro-btn-back"><liferay-ui:message key="dashboard.account.information"/></a></div>

            <form id="form-save-profil" method="post" action="${saveProfilURL}">
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
                                        <p><liferay-ui:message key="dashboard.account.profile.picture"/></p>
                                    </div>
                                    <div class="message is-wrong-image-size">
                                        <p><liferay-ui:message key="dashboard.account.profile.picture.error"/></p>
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