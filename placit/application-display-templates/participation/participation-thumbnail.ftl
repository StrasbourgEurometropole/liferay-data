<!-- VIGNETTE PARTICIPATION -->

<#setting locale = locale />

<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
  <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
  <#assign homeURL = "/" />
</#if>

<!-- Recuperation du status de la participation (terminee, bientot, etc.) -->
<#switch entry.getParticipationStatus()>
    <#case "new">
        <#assign participationStatus = "Nouvelle" />
        <#assign proDuree = "Fin dans " + entry.getTodayExpirationDifferenceDays() + " jour(s)" />
        <#break>
    <#case "soon_arrived">
        <#assign participationStatus = "À venir" />
        <#assign proDuree = "Début dans " + entry.getTodayPublicationDifferenceDays() + " jour(s)"  />
        <#break>
    <#case "in_progress">
        <#assign participationStatus = "En cours" />
        <#assign proDuree = "Fin dans " + entry.getTodayExpirationDifferenceDays() + " jour(s)" />
        <#break>
    <#case "soon_finished">
        <#assign participationStatus = "Bientôt terminée" />
        <#assign proDuree = "Fin dans " + entry.getTodayExpirationDifferenceDays() + " jour(s)" />
        <#break>
    <#case "finished">
        <#assign participationStatus = "Terminée" />
        <#assign proDuree = "Terminée"  />
        <#break>
</#switch>

<!-- Recuperation du type de la participation (information, concertation, etc.) -->
<#assign participationType = entry.getTypeCategory().getTitle(locale) />

<#switch participationType>
    <#case "Information">
        <#assign cssParticipationType = "pro-theme-information" />
        <#break>
    <#case "Consultation">
        <#assign cssParticipationType = "pro-theme-consultation" />
        <#break>
    <#case "Co-construction">
        <#assign cssParticipationType = "pro-theme-co-construire" />
        <#break>
    <#case "Concertation">
        <#assign cssParticipationType = "pro-theme-concertation" />
        <#break>
    <#case "Enquête publique">
        <#assign cssParticipationType = "pro-theme-enquete-publique" />
        <#break>
    <#default>
        <#assign cssParticipationType = "" />
        <#break>
</#switch>

<!-- Separation du titre de la participation  en deux parties -->
<#assign participationTitle = entry.title >
<#if participationTitle?length gt 15 && participationTitle?index_of(" ", 15) != -1 >
    <#assign breakIndex = participationTitle?index_of(" ", 15) >
    <#assign participationTitleFirstPart = participationTitle?substring(0, breakIndex) />
    <#assign participationTitleSecondPart = participationTitle?substring(breakIndex, participationTitle?length) />
<#else>
    <#assign participationTitleFirstPart = participationTitle />
</#if>

<!-- Recuperation des quartiers de la participation -->
<#if entry.getDistrictCategories()??>
    <#assign participationDistricts = entry.getDistrictCategories() />
</#if>

<!-- Recuperation des thématiques de la participation -->
<#if entry.getThematicCategories()??>
    <#assign participationThematics = entry.getThematicCategories() />
</#if>

<!-- Recuperation du projet de la participation -->
<#if entry.getProjectCategory()??>
    <#assign participationProject = entry.getProjectCategory() />
</#if>

<div class="item pro-bloc-card-participation ${cssParticipationType}" data-linkall="a">
    <div>
        <div class="pro-header-participation">
            <figure role="group">
                <#if entry.getImageURL()?has_content>
                    <img src="${entry.getImageURL()}" width="40" height="40" alt="Image participation"/>
                </#if>
            </figure>

            <p>Participation publiée par :</p>
            <p><strong>${entry.getAuthor()}</strong></p>
            <div class="pro-number-comm">
                <!--
                <span>37</span>
                <p>Commentaire(s)</p>
                -->
            </div>
        </div>
        <div class="pro-content-participation">
            <div class="pro-meta">

                 <!-- Liste des quartiers de la participation -->
                <#if participationDistricts?? >
                    <!-- si la taille de la liste correspond à tout les quartiers de strasbourg-->
                    <#if participationDistricts?size ==10>
                        <span>Tout les quartiers</span>
                        <#else >
                        <#list participationDistricts as participationDistrict >
                        <span>${participationDistrict.getTitle(locale)}</span>
                        </#list>
                    </#if>
                </#if>

                <!-- Liste des thématiques de la participation -->
                <#if participationThematics?? >
                    <#list participationThematics as participationThematic >
                        <span>${participationThematic.getTitle(locale)}</span>
                    </#list>
                </#if>

                <!-- Type de la participation -->
                <#if participationType??><span>Type : ${participationType}</span></#if>

                <!-- Statut de la participation -->
                <span>${participationStatus}</span>

                <!-- Projet lié à la participation -->
                <span>${participationProject.getTitle(locale)}</span>

            </div>
            <a href="${homeURL}detail-participation/-/entity/id/${entry.participationId}" title="Détail ''">
                <h3>
                    ${participationTitleFirstPart}
                    <br>
                    <#if participationTitleSecondPart?has_content>${participationTitleSecondPart}</#if>
                </h3>
            </a>
            <span class="pro-time">
                Le <time datetime="${entry.publicationDate?string['dd/MM/yyyy']}">${entry.publicationDate?date?string['dd/MM/yyyy']}</time> / <span class="pro-duree">${proDuree}</span>
            </span>
        </div>

        <!-- Selection du type de template selon le status de la participation -->
        <#if participationStatus == "soon_arrived">
            <div class="pro-footer-participation pro-participation-soon">
                <a href="${homeURL}detail-participation/-/entity/id/${entry.participationId}#pro-link-commentaire" class="pro-form-style" title="Lien vers la page détail Participation - Lien des commentaires">
                    Bientôt disponible
                </a>
            </div>
        <#elseif participationStatus == "new" || participationStatus == "in_progress" || participationStatus == "soon_finished" >
            <div class="pro-footer-participation">
                <a href="${homeURL}detail-participation/-/entity/id/${entry.participationId}#pro-link-commentaire" class="pro-form-style" title="Lien vers la page détail Participation - Lien des commentaires">
                    Réagissez...
                </a>
            </div>
        <#elseif participationStatus == "finished" >
            <div class="pro-footer-participation pro-participation-deadline">
                <p>Participation terminée</p>
            </div>
        </#if>

    </div>
</div>