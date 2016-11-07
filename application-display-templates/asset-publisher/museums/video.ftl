<div class="video-viewer">
    <h3 class="video-viewer-title">
        <@liferay_ui.message key="eu.videos" />
        <#assign PortalUtil = staticUtil["com.liferay.portal.kernel.util.PortalUtil"] />
        <!-- Le lien vers la page de toutes les vidéos est défini comme étant la page "/videos", modifier la ligne ci-dessous si besoin -->
        <a href="${PortalUtil.getGroupFriendlyURL(themeDisplay.getLayoutSet(), themeDisplay)}/videos"><@liferay_ui.message key="eu.video.all-videos" /></a>
    </h3>
    <#if entries?has_content>
        <#list entries as curEntry>
            <#assign video = curEntry.getAssetRenderer().getVideo() />
            <@liferay_portlet.renderURL var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
              <@liferay_portlet.param name="classPK" value="${video.getVideoId()}" />
              <@liferay_portlet.param name="returnURL" value="${currentURL}" />
            </@liferay_portlet.renderURL>
            <a href="${detailURL}"><img src="${video.getImageURL()}"></a>
            <h4 class="video-viewer-video-title">
                <a href="${detailURL}">${video.getTitle(locale)}</a>
            </h4>
        </#list>
    </#if>
</div>
