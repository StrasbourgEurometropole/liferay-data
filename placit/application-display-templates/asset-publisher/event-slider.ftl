<!-- SLIDER AGENDA -->

<!-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<!-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<section class="pro-bloc-slider pro-slider-event">
    <div class="container">
        <h2>L’agenda</h2>
        <div class="owl-carousel owl-opacify owl-theme owl-cards">
        
            <!-- Parcours des entites de l'asset publisher -->
            <#list entries as curEntry>
            
            
            <!-- Recuperation de l'entite -->
           <#assign entry = curEntry.getAssetRenderer().getEvent() />
            
            <a href="detail-event.html" title="lien de la page" class="item pro-bloc-card-event">
                <div>
                    <div class="pro-header-event">
                        <span class="pro-ico"><span class="icon-ico-debat"></span></span>
                        <span class="pro-time">Le ${entry.firstStartDate?date}</span>
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