<!-- Vignette activité -->
<#setting locale = locale />

<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
  <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
  <#assign homeURL = "/" />
</#if>

<li class="grid-item">
    <div class="item-visu">
        <!-- <button class="item-favoris"></button> -->
        <#if entry.imageURL?has_content>
            <div class="item-background" style="background-image: url(${entry.imageURL});"></div>
        </#if>
    </div>
    <div class="item-right">
        <div class="item-content">
            <a class="item-link" href="${homeURL}${detailPageFriendlyURL?remove_beginning('/')}/-/entity/id/${entry.activityId}" title="${entry.getTitle(locale)}">
                <h3 class="item-title" data-dot="1">${entry.getTitle(locale)}</h3>
                <div class="item-description" data-dot="3">
                    ${entry.getDescription(locale)}
                </div>
            </a>
            <div class="btn-line">
                <#-- Si la recherche a renvoyé des cours pour l'activité, on affiche ceux-ci -->
                <#-- Sinon on affiche l'ensemble des cours de ladite activité -->
                <#-- Car si la recherche a renvoyé des activités sans ses cours, c'est certainement que ce sont des cours qui n'ont pas d'horaires mais qui doivent tout de même être affichés -->
                <#if courses?has_content>
                    <#assign activityCourses = courses />
                <#else>
                    <#assign activityCourses = entry.publishedActivityCourses />
                </#if>
                <#list activityCourses as course>
                    <a href="${homeURL}${courseDetailPageFriendlyURL?remove_beginning('/')}/-/entity/id/${course.activityCourseId}" title="${course.getName(locale)}">
                        <div class="seu-btn-square--filled--second">
                            <span class="seu-flexbox">
                                <span class="seu-btn-text">${course.getName(locale)}</span>
                                <span class="seu-btn-arrow"></span>
                            </span>
                        </div>
                    </a>
                </#list>
            </div>
        </div>
        <div class="item-infos">
            <div class="item-geoloc">
                <strong>
                    <#if (entry.getTypes()?size == 1)>
                        <liferay-ui:message key="type" />
                    </#if>
                    <#if (entry.getTypes()?size > 1)>
                        <liferay-ui:message key="types" />
                    </#if>
                    ${entry.getTypesLabel(locale)}
                </strong>
            </div>
            <#-- ??? pas de public d'activité, le public est sur les cours
            <div class="item-misc">
                <span>
                    <strong>
                        <#if (entry.getPublics()?size == 1)>
                            <@liferay_ui.message key="public" />
                        </#if>
                        <#if (entry.getPublics()?size > 1)>
                            <@liferay_ui.message key="publics" />
                        </#if>
                    </strong>
                    ${entry.getPublicsLabel(locale)}
                </span>
            </div>
            -->
        </div>
    </div>
</li>