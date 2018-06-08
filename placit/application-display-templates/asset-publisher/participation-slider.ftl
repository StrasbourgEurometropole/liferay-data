<!-- SLIDER DE PARTICIPATIONS -->

<!-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<!-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<section id="pro-link-participation" class="pro-bloc-slider pro-slider-participation">
    <div class="container">
        
        <div class="col-lg-10 col-lg-offset-1">
            <h2>Les participations</h2>
        </div>

        <div class="col-lg-10 col-lg-offset-1">
            <div class="owl-carousel owl-opacify owl-theme owl-cards">

                <!-- Parcours des entites de l'asset publisher -->
                <#list entries as curEntry>

                    <!-- Recuperation de l'entite -->
                    <#assign entry = curEntry.getAssetRenderer().getParticipation() />

                    <!-- Recuperation du status de la participation (terminee, bientot, etc.) -->
                    <#assign participationStatus = entry.getParticipationStatus() />

                    <!-- Adaptation du message a afficher dans le 'span-pro-time' -->
                    <#if participationStatus == "soon_arrived">
                        <#assign proDuree = "Début dans " + entry.getTodayPublicationDifferenceDays() + " jour(s)"  />
                    <#elseif participationStatus == "new" || participationStatus == "in_progress" || participationStatus == "soon_finished" >
                        <#assign proDuree = "Fin dans " + entry.getTodayExpirationDifferenceDays() + "jour(s)" />
                    <#elseif participationStatus == "finished" >
                        <#assign proDuree = "Terminée"  />
                    </#if>

                    <!-- Recuperation du type de la participation (information, concertation, etc.) -->
                    <#assign participationType = entry.getTypeCategory().getTitle(locale) />

                    <#switch participationType>
                        <#case "Information">
                            <#assign cssParticipationType = "information" />
                            <#break>
                        <#case "Consultation">
                            <#assign cssParticipationType = "consultation" />
                            <#break>
                        <#case "Co-construction">
                            <#assign cssParticipationType = "co-construire" />
                            <#break>
                        <#case "Concertation">
                            <#assign cssParticipationType = "concertation" />
                            <#break>
                        <#case "Enquête publique">
                            <#assign cssParticipationType = "brun" />
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

                    <div class="item pro-bloc-card-participation pro-theme-${cssParticipationType}" data-linkall="a">
                        <div>
                            <div class="pro-header-participation">
                                <figure role="group">
                                    <!-- Si une image de la participation existe -->
                                    <#if entry.getImageURL()?has_content>
                                        <img src="${entry.getImageURL()}" width="40" height="40" alt="Image participation"/>
                                    </#if>
                                </figure>
                                <p>Concertation publiée par :</p>
                                <p><strong>${entry.getAuthor()}</strong></p>
                            </div>
                            <div class="pro-content-participation">
                                <a href="${homeURL}detail-participation/-/entity/id/${entry.participationId}" title="lien de la page">
                                    <h3>
                                        ${participationTitleFirstPart}
                                        <br>
                                        <#if participationTitleSecondPart?has_content>${participationTitleSecondPart}</#if>
                                    </h3>
                                </a>
                                <span class="pro-time">
                                    Publiée le <time datetime="${entry.publicationDate?string['dd/MM/yyyy']}">${entry.publicationDate?date?string['dd/MM/yyyy']}</time> / <span class="pro-duree">${proDuree}</span>
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

                </#list>

            </div>
        </div>

    </div>
</section>
<style>
    .pro-bloc-slider.pro-slider-participation {
             margin-top: 0px;
    }
</style>