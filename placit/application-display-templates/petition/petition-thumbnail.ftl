<!-- VIGNETTE PETITION -->

<!-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

        <!-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
    <#else>
    <#assign homeURL = "/" />
</#if>
        <!-- Recuperation des thématiques de la Petition -->
<#if entry.getThematicCategories()??>
<#assign petitionThematics = entry.getThematicCategories() />
        </#if>

        <!-- Recuperation du pourcentage des signatures -->
<#if entry.getPourcentageSignature()??>
<#assign petitionPourcentage = entry.getPourcentageSignature() />
        </#if>

        <!-- Recuperation du projet de la Petition -->
<#if entry.getProjectCategory()??>
<#assign petitionProject = entry.getProjectCategory() />
        </#if>

<div class="item pro-bloc-card-petition" data-linkall="a">
<div class="pro-header-petition">
    <figure role="group">
        <#if entry.getImageURL()?has_content>
        <img src="${entry.getImageURL()}" width="40" height="40" alt="Image petition"/>
    </#if>
</figure>

<p>Pétition publiée par :</p>
<p><strong>${entry.getUserName()} adressé à : Ville de Strasbourg</strong></p>
<div class="pro-number-comm">
    <span>${entry.nbApprovedComments}</span>
    <p>Commentaire(s)</p>
</div>
</div>
<div class="pro-content-petition">
<div class="pro-wrapper-meta">
    <div class="pro-statut"><span>${entry.getFrontStatusFR()}</span></div>
    <div class="pro-meta">
        <!-- Liste des quartiers de la Petition -->
        <span>${entry.getDistrictLabel(locale)}</span>
        <!-- Liste des thématiques de la Petition -->
        <#if petitionThematics?? >
        <#list petitionThematics as petitionThematic >
        <span>${petitionThematic.getTitle(locale)}</span>
    </#list>
</#if>
<span>${petitionProject.getTitle(locale)}</span>
</div>
        </div>

<a href="${homeURL}detail-petition/-/entity/id/${entry.petitionId}" title="lien de la page"><h3>${entry.title}</h3></a>
<span class="pro-time">Publiée le <time datetime="${entry.publicationDate?string['dd/MM/yyyy']}">${entry.publicationDate?date?string['dd/MM/yyyy']}</time> / <span class="pro-duree">${entry.getProDureeFR()}</span></span>
        </div>
<div class="pro-footer-petition">
<div class="pro-progress-bar">
    <div class="pro-progress-container">
        <div style="width:${petitionPourcentage}%"></div>
    </div>
    <p class="pro-txt-progress"><strong>${entry.getNombreSignature()}</strong> Signataire(s) sur ${entry.getQuotaSignature()} nécessaires</p>
</div>
</div>
        </div>