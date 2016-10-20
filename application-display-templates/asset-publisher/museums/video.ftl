<div class="video-viewer">
    <h3 class="video-viewer-title">
        <@liferay_ui.message key="eu.videos" />
    </h3>
    <#if entries?has_content>
        <#list entries as curEntry>
            <div class="video-viewer-player">
                <#assign video = curEntry.getAssetRenderer().getVideo() />
                ${video.getPlayer(locale)}
            </div>
        </#list>
    </#if>
</div>
