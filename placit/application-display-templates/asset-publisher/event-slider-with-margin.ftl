<!-- SLIDER AGENDA -->

<!-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<!-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<!-- Recuperation de l'id de l'instance du portlet pour separer le metier des portlets doublons -->
<#assign instanceId = themeDisplay.getPortletDisplay().getId() />

<section id="pro-link-evenement" class="pro-bloc-slider pro-slider-event">
    <div class="container">

        <div class="col-lg-10 col-lg-offset-1">
            <h2>L’agenda (${entries?size})</h2>
            <a href="${homeURL}agenda" class="pro-btn" title="Lien vers la page de tout l'agenda">Voir Tout l’agenda</a>
        </div>

        <div class="col-lg-10 col-lg-offset-1">
            <div class="owl-carousel owl-opacify owl-theme owl-cards">
            
                <!-- Parcours des entites de l'asset publisher -->
                <#list entries as curEntry>
                    
                    <!-- Recuperation de l'entite -->
                    <#assign entry = curEntry.getAssetRenderer().getEvent() />
                    
                    <a href="${homeURL}detail-evenement/-/entity/id/${entry.eventId}" title="lien de la page" class="item pro-bloc-card-event">
                        <div>
                            <div class="pro-header-event">
                                <span class="pro-ico"><span class="icon-ico-debat"></span></span>
                                <span class="pro-time"><#if entry.firstStartDate?has_content>Le ${entry.firstStartDate?string("dd MMMM yyyy")}</#if></span>
                                <p>À : ${entry.getPlaceAlias(locale)}</p>
                                <h3 style="display: -webkit-box;-webkit-line-clamp: 2;-webkit-box-orient: vertical;
                                    overflow: hidden;text-overflow: ellipsis;height: 53px">
                                    ${entry.getTitle(locale)}
                                </h3>
                            </div>
                            <div class="pro-footer-event">
                                <#if entry.isFinished() >
                                    <span class="pro-btn-action">
                                        Événement terminé
                                    </span>
                                <#elseif request.session.getAttribute("has_pact_signed")!false >
                                    <span class="pro-btn-action"
                                        name="#Participe-${instanceId}"
                                        data-eventid="${entry.eventId}"
                                        data-groupid="${entry.groupId}">
                                        Je participe
                                    </span>
                                <#else>
                                    <span class="pro-btn-action" name="#Pact-sign">
                                        Je participe
                                    </span>
                                </#if>
                                <span class="pro-number"><strong>${entry.getNbEventParticipations()}</strong> Participant(s)</span>
                            </div>
                        </div>
                    </a>
                    
                </#list>

            </div>
        </div>

    </div>
</section>

<script>
    $(document).ready(function() {
        $("span[name='#Participe-${instanceId}']").each(function() {

            // Sauvegarde de l'élément
            var element = $(this);
            
            // Récupération des attributs du like
            var eventid = $(this).data("eventid");

            // Recherche si l'utilisateur participe a l'evenement
            Liferay.Service(
                '/agenda.eventparticipation/is-user-participates',
                {
                    eventId: eventid
                },
                function(obj) {
                    // En cas de succès, on effectue la modification des éléments visuels
                    // selon la réponse et le type de l'élément
                    if (obj.hasOwnProperty('success')) {
                        if (obj['success'] == 'true') {
                            element.toggleClass('active');
                        }
                    }
                }
            );

        });
    });
</script>