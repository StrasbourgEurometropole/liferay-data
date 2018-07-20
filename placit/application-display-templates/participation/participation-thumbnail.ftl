<!-- VIGNETTE PARTICIPATION -->

<!-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<!-- Recuperation de l'URL de "base" du site -->
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

<!-- Recuperation de la couleur hexa correspondant au type de la participation -->
<#assign participationColor = entry.getProjectCategoryColor() />

<!-- Recuperation des thématiques de la participation -->
<#if entry.getThematicCategories()??>
    <#assign participationThematics = entry.getThematicCategories() />
</#if>

<!-- Recuperation du projet de la participation -->
<#if entry.getProjectCategory()??>
    <#assign participationProject = entry.getProjectCategory() />
</#if>

<div class="item pro-bloc-card-participation type-color-hexa-${participationColor}" data-linkall="a">
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
                <span>${entry.nbApprovedComments}</span>
                <p>Commentaire(s)</p>
            </div>
        </div>
        <div class="pro-content-participation">
            <div class="pro-meta">

                <!-- Liste des quartiers de la participation -->
                <span>${entry.getDistrictLabel(locale)}</span>

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
                <h3>${entry.title}</h3>
            </a>
            <span class="pro-time">
                Le <time datetime="${entry.publicationDate?string['dd/MM/yyyy']}">${entry.publicationDate?date?string['dd/MM/yyyy']}</time> / <span class="pro-duree">${proDuree}</span>
            </span>
        </div>

        <!-- Selection du type de template selon le status de la participation -->
        <#if participationStatus == "À venir">
            <div class="pro-footer-participation pro-participation-soon">
                <div class="pro-avis">
                    <span class="pro-like">${entry.nbLikes}</span>
                    <span class="pro-dislike">${entry.nbDislikes}</span>
                </div>
                <a href="${homeURL}detail-participation/-/entity/id/${entry.participationId}#pro-link-commentaire" class="pro-form-style" title="Lien vers la page détail Participation - Lien des commentaires">
                    Bientôt disponible
                </a>
            </div>
        <#elseif participationStatus == "Nouvelle" || participationStatus == "En cours" || participationStatus == "Bientôt terminée" >
            <div class="pro-footer-participation pro-participation-in-progress">
                <div class="pro-avis">
                    <a href="#pro-avis-like-pro" class="pro-like"
                        data-typeid="15" 
                        data-isdislike="false"
                        data-title="${entry.getTitle()}" 
                        data-entityid="${entry.participationId}"
                        data-entitygroupid="${entry.groupId}">
                        ${entry.nbLikes}
                    </a>
                    <a href="#pro-avis-dislike-pro" class="pro-dislike"
                        data-typeid="15" 
                        data-isdislike="true"
                        data-title="${entry.getTitle()}" 
                        data-entityid="${entry.participationId}"
                        data-entitygroupid="${entry.groupId}">
                        ${entry.nbDislikes}
                    </a>
                </div>
                <a href="${homeURL}detail-participation/-/entity/id/${entry.participationId}#pro-link-commentaire" class="pro-form-style" title="Lien vers la page détail Participation - Lien des commentaires">
                    Réagissez...
                </a>
            </div>
        <#elseif participationStatus == "Terminée" >
            <div class="pro-footer-participation pro-participation-deadline">
                <div class="pro-avis">
                    <span class="pro-like">${entry.nbLikes}</span>
                    <span class="pro-dislike">${entry.nbDislikes}</span>
                </div>
                <p>Participation terminée</p>
            </div>
        </#if>

    </div>
</div>

<!-- Cree le style de couleur hexa a la volee pour l'application de la couleur !-->
<#if participationColor?has_content>
    <style style="display: none" >
        .type-color-hexa-${participationColor}>*:before {
            background:#${participationColor};
        }
    </style>
</#if>