<#setting locale = locale />
<#assign assetEntryLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetEntryLocalService")/>
<#assign journalArticleId = .vars['reserved-article-id'].data>
<#assign journalArticleResourceLocalServiceUtil = staticUtil["com.liferay.journal.service.JournalArticleResourceLocalServiceUtil"]>
<#assign articleResourcePK = journalArticleResourceLocalServiceUtil.getArticleResourcePrimKey(groupId, journalArticleId)/>
<#assign articleEntry = assetEntryLocalService.getEntry("com.liferay.journal.model.JournalArticle", articleResourcePK) />
<#assign assetLinkServiceUtil = staticUtil["com.liferay.asset.kernel.service.AssetLinkLocalServiceUtil"]>
<#assign directLinks = assetLinkServiceUtil.getDirectLinks(articleEntry.getEntryId()) />

<section id="info" class="margin-top margin-bottom">
    <div class="content container">
        <h2>
            ${title.getData()}
        </h2>
        <span class="subtitle">${subtitle.getData()}</span>
        <#if directLinks?has_content>
            <#list directLinks as link>
                <#assign curEntry = assetEntryLocalService.getEntry(link.getEntryId2()) />
                <#assign place = curEntry.assetRenderer.place />
                <#if label.getData()?has_content && label.link?has_content>
                    <button id="btn-info" class="button1" aria-label="${label.getData()?html}" title='${label.getData()?html}'>
                        <span class="points">
                            <span class="trait">
                                <span class="background">
                                    <span>
                                        ${label.getData()}
                                    </span>
                                </span>
                            </span>
                        </span>
                    </button>
                </#if>
            </#list>
        </#if>
        <div class="description">${description.getData()}</div>
    </div>
</section>

<script>
    $("#btn-info").click(function(){
      location.href='http://'+ window.location.host +'${label.link.getFriendlyUrl()}'
    });
</script>