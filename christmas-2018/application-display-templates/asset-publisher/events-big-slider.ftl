<!-- Gros slider événements -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<header class="mns-header-agenda">
    <#if entries?has_content>
        <div class="owl-carousel owl-theme" id="owl-full">
            <#list entries as curEntry>
                <#if (curEntry?index == 20)>
                    <#break>
                </#if>
                <#assign event = curEntry.getAssetRenderer().getEvent() />
                <div class="item">
                    <figure class="fit-cover">
                        <img src="${event.imageURL}" alt="${event.getTitle(locale)}" width="1600" height="900" class="fit-cover" />
                    </figure>
                    <div class="caption container">
                        <div class="row">
                            <div class="col-xs-12">
                                <span class="mns-slider-date-event">${event.getEventScheduleDisplay(locale)}</span>
                                <h2>${event.getTitle(locale)[0..*50]}<#if (event.getTitle(locale)?length > 50)>...</#if></h2>
                                <p>${event.getDescription(locale)?replace("<[^>]*>", "", "r")[0..*200]}...</p>
                                <a href="${homeURL}event/-/entity/id/${event.eventId}/event.getNormalizedTitle(locale)}" class="basic-link">Découvrir</a>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </#if>
    <div class="container mns-wrapper-bread">
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