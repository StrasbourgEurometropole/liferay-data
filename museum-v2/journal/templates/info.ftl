<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
  <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
  <#assign homeURL = "/" />
</#if>
<#assign assetEntryLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetEntryLocalService")/>
<#assign journalArticleId = .vars['reserved-article-id'].data>
<#assign journalArticleResourceLocalServiceUtil = staticUtil["com.liferay.journal.service.JournalArticleResourceLocalServiceUtil"]>
<#assign articleResourcePK = journalArticleResourceLocalServiceUtil.getArticleResourcePrimKey(groupId, journalArticleId)/>
<#assign articleEntry = assetEntryLocalService.getEntry("com.liferay.journal.model.JournalArticle", articleResourcePK) />
<#assign assetLinkServiceUtil = staticUtil["com.liferay.asset.kernel.service.AssetLinkLocalServiceUtil"]>
<#assign directLinks = assetLinkServiceUtil.getDirectLinks(articleEntry.getEntryId()) />

<section id="info" class="margin-bottom">
    <div class="content container">
        <h2>
            ${title.getData()}
        </h2>
        <span class="subtitle">${subtitle.getData()}</span>
        <#if directLinks?has_content>
            <#list directLinks as link>
                <#assign curEntry = assetEntryLocalService.getEntry(link.getEntryId2()) />
                <#assign place = curEntry.assetRenderer.place />
                <a href="${homeURL}lieu/-/entity/id/${place.placeId}/${place.getNormalizedAlias(locale)}" class="button1" aria-label="<@liferay_ui.message key="eu.museum.info.all-info" />" title="<@liferay_ui.message key="eu.museum.info.all-info" />"><@liferay_ui.message key="eu.museum.info.all-info" /></a>
            </#list>
        </#if>
        <p class="description">${description.getData()}</p>
    </div>
</section>