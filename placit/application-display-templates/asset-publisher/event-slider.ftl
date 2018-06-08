<!-- SLIDER AGENDA -->

<!-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<!-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<section id="pro-link-evenement" class="pro-bloc-slider pro-slider-event">
    <div class="container">
        <div>
            <h2>L’agenda</h2>
            <a href="${homeURL}agenda" class="pro-btn" title="Lien vers la page de tout l'agenda">Voir Tout l’agenda</a>
        </div>
        <div class="owl-carousel owl-opacify owl-theme owl-cards">
        
            <!-- Parcours des entites de l'asset publisher -->
            <#list entries as curEntry>
            
            
            <!-- Recuperation de l'entite -->
           <#assign entry = curEntry.getAssetRenderer().getEvent() />
            
            <a href="${homeURL}détail-événement/-/entity/id/${entry.eventId}" title="lien de la page" class="item pro-bloc-card-event">
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
                    </div>
                </div>
            </a>
            </#list>
        </div>
    </div>
</section>