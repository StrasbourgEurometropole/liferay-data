<#setting locale = locale />

<!-- Vignette événement -->
<#if isFeatured>
    <#assign cssClass = 'seu-coup-de-coeur' />
<#else>
    <#assign cssClass = '' />
</#if>
<li class="grid-item ${cssClass}">
    <div class="item-visu">
        <!-- <button class="item-favoris"></button> -->
        <div class="item-background" style="background-image: url(${entry.imageURL});"></div>
    </div>
    <div class="item-right">
        <a href="#" class="item-content">
            <#if entry.firstStartDate?date == entry.lastEndDate?date>
                <div class="item-date"><@liferay_ui.message key="eu.event.the" /> <strong>${entry.firstStartDate?date?string.short?replace('/', '.')}</strong></div>
            <#else>
                <div class="item-date"><@liferay_ui.message key="eu.event.from-date" /> <strong>${entry.firstStartDate?date?string.short?replace('/', '.')}</strong> <@liferay_ui.message key="eu.event.to" /> <strong>${entry.lastEndDate?date?string.short?replace('/', '.')}</strong></div>
            </#if>
            <h3 class="item-title" data-dot="2">${entry.getTitle(locale)}</h3>
            <div class="item-categories" data-dot="1">${entry.getTypeLabel(locale)}</div>
        </a>
        <div class="item-infos">
            <div class="item-geoloc">
                <span class="text">${entry.getPlaceAlias(locale)} 
                    <#if entry.getPlaceAddress(locale)?has_content>
                        - ${entry.getPlaceAddress(locale)}
                    </#if>
                    - ${entry.placeZipCode} ${entry.getPlaceCity(locale)}</span>
            </div>
            <!--
            <a href="" class="item-misc">
                <span>Ajouter au calendrier</span>
            </a>
            -->
        </div>
    </div>
</li>
