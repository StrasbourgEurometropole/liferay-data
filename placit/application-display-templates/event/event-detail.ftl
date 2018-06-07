<#setting locale = locale />

<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<div class="pro-page-detail">

    <div class="container">

        <div class="col-lg-11 col-lg-offset-1">

            <article>
                <header>
                    <#if entry.firstStartDate?has_content>
                        <span class="pro-time">Début le<time datetime="2018-01-10"> ${entry.firstStartDate?string("dd MMMM yyyy à hh'H'mm")}</time></span>
                    </#if>
                    <p>À : 
                        <#if entry.getPlaceAlias(locale)?has_content>${entry.getPlaceAlias(locale)},</#if>
                        <#if entry.getPlaceAddress(locale)?has_content>${entry.getPlaceAddress(locale)},</#if>
                        <#if entry.getPlaceZipCode()?has_content>${entry.getPlaceZipCode()}</#if>
                        <#if entry.getPlaceCity(locale)?has_content>${entry.getPlaceCity(locale)}</#if>
                    </p>
                    <h1>>${entry.getTitle(locale)}</h1>
                    <div class="pro-meta">
                        <#if entry.getTerritoryLabel(locale)?has_content>
                            <span>${entry.getTerritoryLabel(locale)}</span>
                        </#if>
                        <#if entry.getThemeLabel(locale)?has_content>
                            <span>${entry.getThemeLabel(locale)}</span>
                        </#if>
                        <span>? commentaires</span>
                    </div>

                    <div id="breadcrumb">
                        <span>
                            <span>
                                <a href="${homeURL}">Accueil</a>
                                <a href="${homeURL}agenda">Agenda</a>
                                <span class="breadcrumb_last">${entry.getTitle(locale)}</span>
                            </span>
                        </span>
                    </div>
                </header>


                <div class="row pro-container-detail-event">

                    <div class="col-sm-8">
                        <div class="pro-main-img">
                            <figure role="group">
                                <#if entry.imageURL?has_content>
                                    <img src='${entry.imageURL}' alt="Image agenda" width="880" height="593" class="fit-cover" alt="${entry.getTitle(locale)}"/>
                                </#if>
                            </figure>
                        </div>
                        <div class="row pro-bloc pro-bloc-texte">
                            ${entry.getDescription(locale)}
                        </div>

                        <div class="row pro-small-detail-bloc">
                            <#if entry.hasAnyAccessForDisabled() || entry.getAccessForDisabled(locale)?has_content >
                                <div class="col-sm-6">
                                    <h4>Services aux handicapés</h4>
                                    <ul>
                                        <#if entry.accessForBlind>
                                            <li class="pro-tooltip"><span class="icon-ico-handicap-oeil"></span><span class="tooltiptext">Handicap oeil</span></li>
                                        </#if>
                                        <#if entry.accessForDeficient>
                                            <li class="pro-tooltip"><span class="icon-ico-handicap-visage"></span><span class="tooltiptext">Handicap visage</span></li>
                                        </#if>
                                        <#if entry.accessForDeaf>
                                            <li class="pro-tooltip"><span class="icon-ico-handicap-oreille"></span><span class="tooltiptext">Handicap oreille</span></li>
                                        </#if>    
                                        <#if entry.accessForWheelchair>
                                            <li class="pro-tooltip"><span class="icon-ico-handicap-moteur"></span><span class="tooltiptext">Handicap moteur</span></li>
                                        </#if>
                                    </ul>
                                </div>
                            </#if>
                            <#if entry.getAccess(locale)?has_content >    
                                <div class="col-sm-6">
                                    <h4>Accès</h4>
                                    <p>${entry.getAccess(locale)}</p>
                                </div>
                            </#if>
                        </div>

                    </div>
                    <aside class="col-sm-4">
                        <#if entry.getMercatorY()?has_content >
                            <div class="bloc-iframe maps" data-theme="default" data-lat="${entry.getMercatorY()}" data-lng="${entry.getMercatorX()}" data-marker="true"
                             data-markericon="event" data-zoom="12" data-filter-options="filterMapDetail"></div>
                        </#if>
                        <!--
                        <div class="pro-compteur">
                            <span class="pro-compt">00089</span>
                            <p>Citoyens(nes) participent à l’événement</p>
                            <a href="#Participe" class="pro-btn-action" title="Je partcipe">Je participe</a>
                        </div>
                        -->
                        <div class="pro-contact">
                            <h4>Contact</h4>
                            <p>
                                <#if entry.promoter?has_content><strong>${entry.promoter}</strong><br></#if>
                                <#if entry.email?has_content>${entry.email}<br></#if>
                                <#if entry.websiteURL?has_content>${entry.websiteURL}</#if>
                            </p>
                            <a href="tel:${entry.phone}" title="Numéro de téléphone : ${entry.phone}">${entry.phone}</a>
                        </div>
                        <!--
                        <div class="pro-ticket">
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                            <a href="#Reserv" class="pro-btn-ticket" title="Réservation d'un billet">Reserver un billet</a>
                        </div>
                        -->
                    </aside>
                </div>
            </article>

        </div>
    </div>

    <@liferay_portlet["runtime"]
    portletProviderAction=portletProviderAction.VIEW
    portletName="com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet" />

</div>

<script>
    $(document).ready(function() {
        $('.pro-slider-event>.container>div').each(function() {
          $(this).addClass("col-lg-10 col-lg-offset-1");
        });
    });
</script>