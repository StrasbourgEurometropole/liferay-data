<!-- DETAIL D'UN EVENEMENT -->

<#-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<#-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<div class="ops-page-detail-concert">

    <header class="ops-header-concert" data-vheight="100 - 100">
        <figure class="fit-cover">
            <#if entry.imageURL?has_content>
                <img src="${entry.imageURL}" width="1600" height="900" alt="${entry.getTitle(locale)}"/>
            </#if>
        </figure>

        <div class="ops-content-wrapper ops-caption-concert">
            <span class="ops-typologie">${entry.getThemeLabel(locale)}</span>
            <h1>${entry.getTitle(locale)}</h1>

            <nav class="ops-scrollto">
                <ul>
                    <li><a href="#ops-representations" class="ops-active">Représentations</a></li>
                    <li><a href="#ops-description">Description</a></li>
                    <li id="distribution-link"><a href="#ops-distribution">Distribution</a></li>
                    <li><a href="#ops-a-voir">À voir</a></li>
                    <li id="audio-link"><a href="#ops-audio">Audio</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <!-- BLOCS - LES PROCHAINS CONCERTS -->
    <div id="ops-representations" class="ops-bloc-slider-concerts">
        <div class="ops-content-wrapper">

            <div class="slick-carousel slick-cards-slider">

                <#-- Parcours des dates de l'event -->
                <#list entry.getCurrentAndFuturePeriods() as period>
                    <div class="ops-item">
                        <time><span>${period.getDisplay(locale, false, true)}</span></time>
                        <div class="ops-horaires">${period.timeDetail}</div>
                        <h3>${entry.getTitle(locale)}</h3>
                        <span class="ops-typologie">${entry.getThemeLabel(locale)}</span>
                    </div>
                </#list>
            </div>

        </div>
    </div>

    <!-- BLOC - INFOS DU CONCERT -->
    <div id="ops-description" class="ops-bloc-infos-concert">
        <div class="ops-content-wrapper">

            <!-- Programme -->
            <#if entry.program?has_content>
                <div class="ops-col-33">
                    <span class="ops-title-infos">Programme</span>
                    <p>${entry.program}</p>
                </div>
            </#if>

            <!-- Distribution -->
            <#if entry.distribution?has_content>
                <div class="ops-col-33">
                    <span class="ops-title-infos">Distribution</span>
                    <p>${entry.distribution}</p>
                </div>
            </#if>

            <!-- Adresse p-->
            <div class="ops-col-33">
                <span class="ops-title-infos">Lieu</span>
                <address><#if entry.getPlaceAlias(locale)?has_content>${entry.getPlaceAlias(locale)}<#else>Strasbourg<br>Opéra national du Rhin</#if></address>
            </div>

        </div>
    </div>


    <!-- BLOC DESCRIPTION -->
    <div class="ops-bloc-description">
        <div class="ops-content-wrapper">
            ${entry.getDescription(locale)}
        </div>
    </div>

    <!-- BLOC MIS EN AVANT MUSICIENS -->
    <@liferay_portlet["runtime"]
    portletProviderAction=portletProviderAction.VIEW
    portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
    instanceId="composer_${entry.eventId}"/>

    <#-- Recuperation des suggéstions de l'event -->
    <#assign suggestions = entry.getSuggestions(request, 10, null) />
	
	<#if suggestions?size gt 0 >
        <!-- BLOC - SLIDER CARDS - NOS PROCHAINS CONCERTS -->
        <div id="ops-a-voir" class="ops-bloc-slider-cards">
            <div class="ops-content-wrapper ops-content-wrapper-large">
                <div>
                    <h3 class="ops-title-line"><span>Cela peut vous intéresser</span></h3>
                </div>

                <div class="slick-carousel slick-cards-slider">

                    <#list suggestions as suggestion>
                        <div class="ops-item">
                            <a href="${homeURL}detail-evenement/-/entity/id/${suggestion.eventId}" class="ops-card ops-card-concert">
                                <div>
                                    <time><span>${suggestion.getEventScheduleDisplay(locale, false, true)}</span></time>
                                    <div class="ops-next-date"></div>
                                    <h3>${suggestion.getTitle(locale)}</h3>
                                    <div class="ops-img">
                                        <figure class="fit-cover">
                                            <#if suggestion.imageURL?has_content>
                                                <img src="${suggestion.imageURL}" width="330" height="170" alt="Nom du concert"/>
                                            </#if>
                                        </figure>
                                        <span class="ops-typologie">${suggestion.getThemeLabel(locale)}</span>
                                    </div>
                                    <div class="ops-content">
                                        <span class="ops-songs"><strong>${suggestion.getComposer()}</strong></span>
                                        <span class="ops-names">${suggestion.getDistribution()}</span>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </#list>
                </div>

                <div class="ops-link-bottom">
                    <a href="${homeURL}agenda" class="ops-btn">Voir l'agenda complet</a>
                </div>
            </div>
        </div>
    </#if>

    <!-- BLOC AUDIO -->
    <@liferay_portlet["runtime"]
    portletProviderAction=portletProviderAction.VIEW
    portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
    instanceId="audio_${entry.eventId}"/>

</div>

<script>
    $(document).ready(function(){
        if($('*').find("#ops-distribution").length == 0)
            $("#distribution-link").hide();

        if($('*').find("#ops-audio").length == 0)
            $("#audio-link").hide();
    });
</script>