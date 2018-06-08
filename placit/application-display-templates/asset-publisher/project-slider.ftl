<!-- SLIDER PROJET -->

<!-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<!-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<section id="pro-link-projet" class="pro-bloc-slider">
            <div class="container">
                <h2>Les projets :</h2>
                <form class="pro-form-select">
                    <label for="quartiers" aria-hidden="true" class="hide">Quartiers</label>
                    <select id="quartiers" onchange="this.value;">
                        <option value="pro-projet-all">Tous les quartiers</option>
                        <option value="pro-projet-krutenau">Krutenau</option>
                        <option value="pro-projet-neudorf">Neudorf</option>
                        <option value="pro-projet-wacken">Wacken</option>
                    </select>
                </form>
                <a href="listing-actu.html" class="pro-btn" title="Lien vers la page de tous les projets">Voir tous les projets</a>


                <!-- SlIDER LISTE DES PROJETS - TOUS LES PROJETS -->
                <div id="pro-projet-all" class="owl-carousel owl-opacify owl-theme owl-cards owl-projet">

                    <!-- Parcours des entites de l'asset publisher -->
                    <#list entries as curEntry>

                    <!-- Recuperation de l'entite -->
                    <#assign entry = curEntry.getAssetRenderer().getProject() />

                    <div class="item bloc-card-projet">
                        <a href="${homeURL + entry.detailURL}" title="détail du projet">
                            <div class="img">
                                <figure role="group">
                                    <img src='${entry.imageURL}' alt="Image projet" width="360" height="242" class="fit-cover"/>
                                </figure>
                                <span>Voir le projet</span>
                            </div>
                            <div class="content">
                                <span class="location">${entry.getDistrictCategories(locale)}</span>
                                <h3>${entry.title}</h3>
                            </div>
                        </a>
                        <ul>
                            <li><a href="page-standard.html" title="Participation(s) du projet" tabindex="-1">${entry.getParticipations()?size} Participation(s) en cours</a></li>
                            <li><a href="page-standard.html" title="Événement(s) du projet" tabindex="-1">${entry.getEvents()?size} Événement(s) à venir</a></li>                          
                        </ul>
                    </div>

                    </#list>

                </div>
            </div>
        </section>