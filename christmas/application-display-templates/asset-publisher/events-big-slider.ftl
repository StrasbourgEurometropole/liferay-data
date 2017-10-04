<!-- Gros slider événements -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<header class="mns-header-agenda">
    <div class="owl-carousel owl-theme" id="owl-full">
        <#list entries as curEntry>
            <#assign event = curEntry.getAssetRenderer().getEvent() />
            <div class="item">
                <a href="${homeURL}event/-/entity/id/${event.eventId}">
                    <figure>
                        <img src="${event.imageURL}" alt="${event.getTitle(locale)}" width="1600" height="900" class="fit-cover" />
                    </figure>
                    <div class="caption">
                        <span class="mns-slider-date-event">${event.getEventScheduleDisplay(locale)}</span>
                        <h2>${event.getTitle(locale)}</h2>
                        <p>${event.getDescription(locale)?replace("<[^>]*>", "", "r")[0..*100]}...</p>
                        <span class="basic-link">Découvrir</span>
                    </div>
                </a>
            </div>
        </#list>
    </div>
    <div class="mns-wrapper-bread">
        <div class="mns-breadcrumbs">
             <#if !layout.ancestors?has_content || layout.ancestors?reverse[0].friendlyURL != '/accueil'>
                <a href="${homeURL}"><@liferay_ui.message key="home" /></a>
            </#if>
            <#list layout.ancestors?reverse as ancestor>
                <a href="${homeURL}${ancestor.friendlyURL?remove_beginning('/')}">${ancestor.getName(locale)}</a>
            </#list>
            <span>${layout.getName(locale)}</span>
        </div>
    </div>
</header>