<!-- SLIDER PROJET -->

<#if entries?size != 0 >

    <!-- Recuperation de la localisation de l'utilisateur -->
    <#setting locale = locale />
    <#assign quartiers = [] />
    <#assign nomQuartier = "test"/>

    <!-- Recuperation de l'URL de "base" du site -->
    <#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
        <#assign homeURL = "/web${layout.group.friendlyURL}/" />
    <#else>
        <#assign homeURL = "/" />
    </#if>

    <#list entries as curEntry>
        <!-- Recuperation de l'entite -->
        <#assign entry = curEntry.getAssetRenderer().getProject() />
        <#assign districts = entry.getDistrictCategories()/>
        <#list districts as district>

            <#assign nomQuartier = district.getTitle(locale)/>
            <#if quartiers?seq_contains(nomQuartier)>
            <#else>
                <#assign quartiers = quartiers +  [nomQuartier]/>
            </#if>

        </#list>
    </#list>

    <section id="pro-link-projet" class="pro-bloc-slider">
        <div class="container">

            <!-- Entête du listing -->
            <h2>Les projets :</h2>
            <form class="pro-form-select">
                <label for="quartiers" aria-hidden="true" class="hide">Quartiers</label>
                <select id="quartiers" onchange="this.value;">
                    <option value="pro-projet-all">Tous les quartiers</option>
                    <#list quartiers as quartier>
                        <option value="pro-projet-${quartier}">${quartier}</option>
                    </#list>
                </select>
            </form>
            <a href="${homeURL}projets" class="pro-btn" title="Lien vers la page de tous les projets">Tout voir</a>

            <!-- SlIDER LISTE DES PROJETS - TOUS LES PROJETS -->
            <div id="pro-projet-all" class="owl-carousel owl-opacify owl-theme owl-cards owl-projet" >

                <!-- Parcours des entites de l'asset publisher -->
                <#list entries as curEntry>

                    <!-- Recuperation de l'entite -->
                    <#assign entry = curEntry.getAssetRenderer().getProject() />

                    <div class="item bloc-card-projet">
                        <a href="${homeURL + entry.detailURL}" title="détail du projet">
                            <div class="img">
                                <figure role="group">
                                    <img src='${entry.imageURL}?imagePreview=1' loading="lazy" alt="Image projet" width="360" height="242" class="fit-cover"/>
                                </figure>
                                <span>Voir le projet</span>
                            </div>
                            <div class="content">
                                <span class="location">${entry.getDistrictLabel(locale)}</span>
                                <h3>${entry.title}</h3>
                            </div>
                        </a>
                        <ul>
                            <li>
                                <a href="${homeURL + entry.detailURL}#pro-link-participation" title="Participation(s) du projet" tabindex="-1">
                                    ${entry.getParticipations()?size} Participation(s) en cours
                                </a>
                            </li>
                            <li>
                                <a href="${homeURL + entry.detailURL}#pro-link-evenement" title="Événement(s) du projet" tabindex="-1">
                                    ${entry.getEvents()?size} Événement(s) à venir
                                </a>
                            </li>
                        </ul>
                    </div>

                </#list>
            </div>

            <#list quartiers as quartier>

                <div id="pro-projet-${quartier}" class="owl-carousel owl-opacify owl-theme owl-cards owl-projet" >

                    <!-- Parcours des entites de l'asset publisher -->
                    <#list entries as curEntry>

                        <!-- Recuperation de l'entite -->
                        <#assign entry = curEntry.getAssetRenderer().getProject() />
                        <#assign districts = entry.getDistrictCategories()/>
                        
                        <#list districts as district>
                            <#if quartier==district.getTitle(locale)>
                                <div class="item bloc-card-projet">
                                    <a href="${homeURL + entry.detailURL}" title="détail du projet">
                                        <div class="img">
                                            <figure role="group">
                                                <img src='${entry.imageURL}?imagePreview=1' loading="lazy" alt="Image projet" width="360" height="242" class="fit-cover"/>
                                            </figure>
                                            <span>Voir le projet</span>
                                        </div>
                                        <div class="content">
                                            <span class="location">${entry.getDistrictLabel(locale)}</span>
                                            <h3>${entry.title}</h3>
                                        </div>
                                    </a>
                                    <ul>
                                        <li>
                                            <a href="${homeURL + entry.detailURL}#pro-link-participation" title="Participation(s) du projet" tabindex="-1">
                                                ${entry.getParticipations()?size} Participation(s) en cours
                                            </a>
                                        </li>
                                        <li>
                                            <a href="${homeURL + entry.detailURL}#pro-link-evenement" title="Événement(s) du projet" tabindex="-1">
                                                ${entry.getEvents()?size} Événement(s) à venir
                                            </a>
                                        </li>  
                                    </ul>
                                </div>
                            <#else >

                            </#if>
                        </#list>

                    </#list>

                </div>

            </#list>

        </div>
    </section>

<#else>
    <div style="height:25px"><div>
</#if>