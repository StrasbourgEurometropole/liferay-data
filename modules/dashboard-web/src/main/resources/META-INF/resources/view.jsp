<%@ include file="/init.jsp" %>

<portlet:actionURL var="saveProfilURL" name="saveProfil">
	<portlet:param name="redirectURL" value="${redirectURL}"/>
	<portlet:param name="cmd" value="saveProfil" />
</portlet:actionURL>

<div class="pro-bloc-dashboard">
    <div class="container pro-user">
        <a href="#pro-onglet-account">
            <figure>
                <img src="/o/plateforme-citoyenne-theme/images/medias/user_female_portrait.png" width="40" height="40" alt="Nom de l'utilisateur"/>
            </figure>
            <span><liferay-ui:message key="dashboard.front.profil"/></span>
        </a>
        <span><liferay-ui:message key="dashboard.front.welcome"/><span id="pro-user-name">${userConnected.get('first_name')} ${userConnected.get('last_name')}</span></span>
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
                        <span class="pro-number">${eventCount}</span>
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
                        <a href="#pro-link-listing-petition-signe" class="pro-txt"><strong>${petitionSignedCount}</strong> <liferay-ui:message key="dashboard.front.petition.signed"/></a>
                        <a href="#pro-link-listing-petition-depose" class="pro-txt"><strong>${petitionFiledCount}</strong> <liferay-ui:message key="dashboard.front.petition.filed"/></a>
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
                                    <img src="/o/plateforme-citoyenne-theme/images/medias/user_female_portrait.png" width="185" height="185" alt="Image" class="pro-img-bg"/>
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
                            <aui:input name="username" disabled="true" label="dashboard.account.profile.username" required="true" value="${userConnected.get('last_name')}"/>
                        </div>
                        <div class="form-group form-third">
                            <aui:input name="username" disabled="true" label="dashboard.account.profile.firstname" required="true" value="${userConnected.get('first_name')}"/>
                        </div>
                        <div class="form-group form-third">
                            <c:if test="${userConnected.get('birthdate') ne 'null'}">
                                <fmt:parseDate pattern="yyyy-MM-dd" value="${userConnected.get('birthdate')}" var="parsedStatusDate" />
                                <fmt:formatDate value="${parsedStatusDate}" var="formattedDate" type="date" pattern="dd/MM/yyyy" />
                            </c:if>
                            <aui:input id="signbirthday" name="birthday" cssClass="frm_date" label="dashboard.account.profile.birthday" required="true" placeholder="jj/mm/aaaa" value="${formattedDate}"/>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-half">
				            <aui:input id="signaddress" name="address" label="dashboard.account.profile.address" required="true" value="${userConnected.get('address')}"/>
                        </div>
                        <div class="form-group form-half">
                            <div class="form-city">
				                <aui:input id="signcity" name="city" label="dashboard.account.profile.city" required="true" placeholder="Strasbourg" value="${userConnected.get('city')}"/>
                            </div>
                            <div class="form-code">
                                <aui:input id="signpostalcode" name="postalcode" label="dashboard.account.profile.postalcode" required="true" placeholder="67XXX" value="${userConnected.get('zipcode')}"/>
                            </div>
                        </div>
                    </div>
                    <div class="pro-row">
                        <div class="form-group form-third">
                        <aui:input type="email" id="signmail" name="mail" disabled="true" label="dashboard.account.profile.mail"  required="true" value="${userConnected.get('email')}"/>
                        </div>
                        <div class="form-group form-third">
                            <aui:input type="number" id="signmobile" name="mobile" label="dashboard.account.profile.phone" placeholder="0311111111" value="${userConnected.get('phone')}"/>
                        </div>
                        <div class="form-group form-third">
                            <aui:input type="number" id="signmobile" name="mobile" label="dashboard.account.profile.mobile" placeholder="0611111111" value="${userConnected.get('mobile')}"/>
                        </div>
                    </div>
                    <div class="pro-form-submit pro-row">
                        <button type="submit" class="btn btn-default"><liferay-ui:message key="dashboard.account.profile.button.save"/></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- LISTING DE TUILES -->
<section id="pro-link-listing-projet" class="pro-bloc-slider">
    <div class="container">
        <h2>Les projets :</h2>
        <form class="pro-form-select">
            <label for="quartiers" aria-hidden="true" class="hide">Quartiers</label>
            <select id="quartiers" onchange="this.value;">
                <option value="pro-projet-all"><liferay-ui:message key="dashboard.thumbnail.project.all"/></option>
                <option value="pro-projet-krutenau"><liferay-ui:message key="dashboard.thumbnail.project.krutenau"/></option>
                <option value="pro-projet-neudorf"><liferay-ui:message key="dashboard.thumbnail.project.neudorf"/></option>
                <option value="pro-projet-wacken"><liferay-ui:message key="dashboard.thumbnail.project.wacken"/></option>
            </select>
        </form>
        <a href="listing-projet.html" class="pro-btn" title=<liferay-ui:message key="dashboard.thumbnail.project.link.title"/> >
            <liferay-ui:message key="dashboard.thumbnail.project.link"/>
        </a>


        <!-- SlIDER LISTE DES PROJETS - TOUS LES PROJETS -->
        <div id="pro-projet-all" class="owl-carousel owl-opacify owl-theme owl-cards owl-projet">
            <div class="item bloc-card-projet">
                <a href="detail-projet.html" title="lien de la page">
                    <div class="img">
                        <figure role="group">
                            <img src='/o/plateforme-citoyenne-theme/images/medias/hp-projet-1.jpg' alt="Image agenda" width="360" height="242" class="fit-cover"/>
                        </figure>
                        <span>Voir le projet</span>
                    </div>
                    <div class="content">
                        <span class="location">Nom du quartier</span>
                        <h3>Titre long du projet</h3>
                    </div>
                </a>
                <ul>
                    <li><a href="page-standard.html" title="lien de la page" tabindex="-1">5 Participation(s) en cours</a></li>
                    <li><a href="page-standard.html" title="lien de la page" tabindex="-1">2 Événement(s) à venir</a></li>
                    <li><a href="page-standard.html" title="lien de la page" tabindex="-1">2 idée(s) citoyenne(s)/Budget participatif</a></li>
                    <li><a href="page-standard.html" title="lien de la page" tabindex="-1">7 Pétition(s) ouverte(s)</a></li>
                    <li><a href="page-standard.html" title="lien de la page" tabindex="-1">34 Initiative(s)</a></li>
                </ul>
            </div>
        </div>
    </div>
</section>
<div class="pro-wrapper-list-dashboard">
<section id="pro-link-listing-event" class="pro-bloc-slider pro-slider-event">
    <div class="container">
        <h2>Mon agenda (${eventCount})</h2>
        <a href="listing-event.html" class="pro-btn" title=<liferay-ui:message key="dashboard.thumbnail.agenda.title"/>>
            <liferay-ui:message key="dashboard.thumbnail.agenda"/>
        </a>

        <div class="owl-carousel owl-opacify owl-theme owl-cards">
            <c:forEach var="event" items="${eventCount}">
                <div class="item pro-bloc-card-event" data-linkall="a">
                    <div>
                        <div class="pro-header-event">
                            <span class="pro-ico"><span class="icon-ico-debat"></span></span>
                            <span class="pro-time">Le <time datetime="2018-01-10">04 décembre 2017 à 11h00</time></span>
                            <p>À : Espace des associations de Strasbourg au centre ville</p>
                            <a href="detail-event.html" title="lien de la page"><h3>Titre de l’Évènement</h3></a>
                        </div>
                        <div class="pro-footer-event">
                            <span class="pro-btn-action">Je participe</span>
                            <span class="pro-number"><strong>37</strong> Participants-es</span>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>

<!-- Tuile des pétitions -->
<section id="pro-link-listing-petition-signe" class="pro-bloc-slider pro-slider-event">
    <div class="container">
        <h2>Mes pétitions signées (8)</h2>
        <a href="listing-petition.html" class="pro-btn" title="Lien vers la page du Listing des pétitions">Toutes les pétitions</a>

        <div class="owl-carousel owl-opacify owl-theme owl-cards">

            <div class="item pro-bloc-card-petition" data-linkall="a">
                <div class="pro-header-petition">
                    <figure role="group">
                        <img src="/o/plateforme-citoyenne-theme/images/medias/comm-mathilde.jpg" width="40" height="40" alt="Arrière plan page standard"/>
                    </figure>
                    <p>Pétition publiée par :</p>
                    <p><strong>Sylvie M.</strong></p>
                </div>
                <div class="pro-content-petition">
                    <a href="detail-petition.html" title="lien de la page"><h3>Titre de la pétition<br>Sur deux lignes</h3></a>
                    <p>Pétition adressée à <u>la ville de Strasbourg</u></p>
                    <span class="pro-time">Publiée le <time datetime="2018-01-10">10/04/2018</time> / <span class="pro-duree">Fin dans 11 jours</span></span>
                </div>
                <div class="pro-footer-petition">
                    <div class="pro-progress-bar">
                        <div class="pro-progress-container">
                            <div style="width:75%"></div>
                        </div>
                        <p class="pro-txt-progress"><strong>1500</strong> Signataire(s) sur 2000 nécessaires</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


<!-- Tuile des pétitions -->
<section id="pro-link-listing-petition-depose" class="pro-bloc-slider pro-slider-event">
    <div class="container">
        <h2>Mes pétitions déposées (4)</h2>
        <a href="listing-petition.html" class="pro-btn" title="Lien vers la page du Listing des pétitions">Toutes les pétitions</a>

        <div class="owl-carousel owl-opacify owl-theme owl-cards">

            <div class="item pro-bloc-card-petition" data-linkall="a">
                <div class="pro-header-petition">
                    <figure role="group">
                        <img src="/o/plateforme-citoyenne-theme/images/medias/comm-mathilde.jpg" width="40" height="40" alt="Arrière plan page standard"/>
                    </figure>
                    <p>Pétition publiée par :</p>
                    <p><strong>Sylvie M.</strong></p>
                </div>
                <div class="pro-content-petition">
                    <a href="detail-petition.html" title="lien de la page"><h3>Titre de la pétition<br>Sur deux lignes</h3></a>
                    <p>Pétition adressée à <u>la ville de Strasbourg</u></p>
                    <span class="pro-time">Publiée le <time datetime="2018-01-10">10/04/2018</time> / <span class="pro-duree">Fin dans 11 jours</span></span>
                </div>
                <div class="pro-footer-petition">
                    <div class="pro-progress-bar">
                        <div class="pro-progress-container">
                            <div style="width:75%"></div>
                        </div>
                        <p class="pro-txt-progress"><strong>1500</strong> Signataire(s) sur 2000 nécessaires</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


<!-- Tuile des initiatives -->
<section id="pro-link-listing-initiative-signe" class="pro-bloc-slider pro-slider-event">
    <div class="container">
        <h2>Mes initiatives signées (6)</h2>
        <a href="listing-initiative.html" class="pro-btn" title="Lien vers la page du Listing des évènements">Toutes les initiatives</a>

        <div class="owl-carousel owl-opacify owl-theme owl-cards">

            <div class="item pro-bloc-card-initiative" data-linkall="a">
                <div class="wrapper-card-initiative">
                    <div>
                        <div class="pro-header-initiative">
                            <figure role="group">
                                <img src="/o/plateforme-citoyenne-theme/images/medias/comm-mathilde.jpg" width="40" height="40" alt="Arrière plan page standard"/>
                            </figure>
                            <p>Initiative publiée par :</p>
                            <p><strong>Sylvie M.</strong></p>
                        </div>
                        <div class="pro-content-initiative">
                            <div class="pro-wrapper-meta">
                                <div class="pro-meta">
                                    <span>Quartier</span>
                                    <span>Thématique</span>
                                    <span>Nom du projet</span>
                                </div>
                            </div>
                            <a href="detail-initiative.html" title="lien de la page"><h3>Titre de l’initiative<br>Sur deux lignes</h3></a>
                            <span class="pro-time">Publiée le <time datetime="2018-01-10">10/04/2018</time></span>
                        </div>
                    </div>
                </div>
                <div class="pro-footer-initiative">
                    <div class="pro-avis">
                        <span>188</span>
                    </div>
                    <p>Citoyens soutiennent cette initiative</p>
                </div>
            </div>
        </div>
    </div>
</section>


<!-- Tuile des initiatives -->
<section id="pro-link-listing-initiative-aide" class="pro-bloc-slider pro-slider-event">
    <div class="container">
        <h2>Mes initiatives aidées (11)</h2>
        <a href="listing-initiative.html" class="pro-btn" title="Lien vers la page du Listing des évènements">Toutes les initiatives</a>

        <div class="owl-carousel owl-opacify owl-theme owl-cards">

            <div class="item pro-bloc-card-initiative" data-linkall="a">
                <div class="wrapper-card-initiative">
                    <div>
                        <div class="pro-header-initiative">
                            <figure role="group">
                                <img src="/o/plateforme-citoyenne-theme/images/medias/comm-mathilde.jpg" width="40" height="40" alt="Arrière plan page standard"/>
                            </figure>
                            <p>Initiative publiée par :</p>
                            <p><strong>Sylvie M.</strong></p>
                        </div>
                        <div class="pro-content-initiative">
                            <div class="pro-wrapper-meta">
                                <div class="pro-meta">
                                    <span>Quartier</span>
                                    <span>Thématique</span>
                                    <span>Nom du projet</span>
                                </div>
                            </div>
                            <a href="detail-initiative.html" title="lien de la page"><h3>Titre de l’initiative<br>Sur deux lignes</h3></a>
                            <span class="pro-time">Publiée le <time datetime="2018-01-10">10/04/2018</time></span>
                        </div>
                    </div>
                </div>
                <div class="pro-footer-initiative">
                    <div class="pro-avis">
                        <span>188</span>
                    </div>
                    <p>Citoyens soutiennent cette initiative</p>
                </div>
            </div>
        </div>
    </div>
</section>


<!-- Tuile des initiatives -->
<section id="pro-link-listing-projet-soumis" class="pro-bloc-slider pro-slider-event">
    <div class="container">
        <h2>Mes idées soumises (2)</h2>
        <a href="listing-budget.html" class="pro-btn" title="Lien vers la page du Listing des budgets">Toutes les idées</a>

        <div class="owl-carousel owl-opacify owl-theme owl-cards">

            <div class="item pro-bloc-card-budget pro-theme-faisable" data-linkall="a">
                <div class="pro-header-budget">
                    <figure role="group">
                        <img src="/o/plateforme-citoyenne-theme/images/medias/comm-mathilde.jpg" width="40" height="40" alt="Arrière plan page standard"/>
                    </figure>
                    <p>Idée déposée par :</p>
                    <p><strong>Sylvie M.</strong></p>
                    <div class="pro-info-top-right">
                        <span class="pro-encart-theme">Faisable</span>
                    </div>
                </div>
                <div class="pro-content-budget">
                    <a href="detail-budget.html" title="lien de la page"><h3>Titre du budget participatif<br>Sur deux lignes</h3></a>
                    <p>Projet adressée à <u>la ville de Strasbourg</u></p>
                    <span class="pro-time">Publiée le <time datetime="2018-01-10">10/04/2018</time></span>
                </div>
                <div class="pro-footer-budget">
                    <p><strong>1500</strong> Citoyens-nes soutiennent cette idée</p>
                </div>
            </div>
        </div>
    </div>
</section>


<!-- Tuile des initiatives -->
<section id="pro-link-listing-projet-vote" class="pro-bloc-slider pro-slider-event">
    <div class="container">
        <h2>Mes projets votés (3)</h2>
        <a href="listing-budget.html" class="pro-btn" title="Lien vers la page du Listing des budgets">Toutes les idées</a>

        <div class="owl-carousel owl-opacify owl-theme owl-cards">

            <div class="item pro-bloc-card-budget  pro-theme-non-faisable" data-linkall="a">
                <div class="pro-header-budget">
                    <figure role="group">
                        <img src="/o/plateforme-citoyenne-theme/images/medias/comm-mathilde.jpg" width="40" height="40" alt="Arrière plan page standard"/>
                    </figure>
                    <p>Idée déposée par :</p>
                    <p><strong>Sylvie M.</strong></p>
                    <div class="pro-info-top-right">
                        <span class="pro-encart-theme">Non faisable</span>
                    </div>
                </div>
                <div class="pro-content-budget">
                    <a href="detail-budget.html" title="lien de la page"><h3>Titre du budget participatif<br>Sur deux lignes</h3></a>
                    <p>Projet adressée à <u>la ville de Strasbourg</u></p>
                    <span class="pro-time">Publiée le <time datetime="2018-01-10">10/04/2018</time></span>
                </div>
                <div class="pro-footer-budget">
                    <p>Ce budget participatif a été étudié et déclaré non-faisable</p>
                </div>
            </div>
        </div>
    </div>
</section>
</div>

<a href="#backtop" class="pro-btn-back-top"><span class="icon-ico-chevron-down"></span></a>