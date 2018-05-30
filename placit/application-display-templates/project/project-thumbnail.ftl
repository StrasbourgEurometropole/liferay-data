<!-- VIGNETTE PROJET (Recherche d'asset) -->

<!-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<!-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<!-- Recuperation des thématiques du projet -->
<#if entry.getThematicCategories()??>
    <#assign projectThematics = entry.getThematicCategories() />
</#if>

<div class="col-md-4 col-sm-6 col-xs-12">
    <div class="item bloc-card-projet">

        <a href="${homeURL + entry.detailURL}" title="lien de la page">

            <div class="img">
                <figure role="group">
                    <img src='${entry.imageURL}' alt="Image projet" width="360" height="242" class="fit-cover"/>
                </figure>
                <span>Voir le projet</span>
            </div>

            <div class="content">
                <span class="location">${entry.getDistrictCategories(locale)}</span>
                <h3>${entry.title}</h3>
                <div class="pro-wrap-thematique">
                    <!-- Liste des thématiques de la participation -->
                    <#if projectThematics?? >
                        <#list projectThematics as projectThematic >
                            <span>${projectThematic.getTitle(locale)}</span>
                        </#list>
                    </#if>
                </div>
            </div>

        </a>

        <ul>
            <li><a href="${homeURL}participations" title="lien de la page" tabindex="-1">${entry.getParticipations()?size} Participation(s) en cours</a></li>
            <li><a href="${homeURL}agenda" title="lien de la page" tabindex="-1">${entry.getEvents()?size} Événement(s) à venir</a></li>
        </ul>

    </div>
</div>