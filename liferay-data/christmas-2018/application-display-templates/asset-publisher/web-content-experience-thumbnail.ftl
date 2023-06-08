<#setting locale = locale />

<!-- Vignette contenu web -->
<#assign docXml = saxReaderUtil.read(entry.getContentByLocale(locale)) />
<#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>
<#if !title?has_content><#assign title = entry.getTitle(locale) /></#if>
<#assign catcher = docXml.valueOf("//dynamic-element[@name='catcher']/dynamic-content/text()") />
<#assign text = docXml.valueOf("//dynamic-element[@name='text']/dynamic-content/text()") />
<#assign image = docXml.valueOf("//dynamic-element[@name='image']/dynamic-content/text()") />
<#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
<#assign imageURL ="" />
<#if image?has_content>
    <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(image) />
</#if>
<#assign lieu = docXml.valueOf("//dynamic-element[@name='lieu']/dynamic-content/text()") />


<#assign assetEntryLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetEntryLocalService") />
<#assign assetVocabularyHelper = serviceLocator.findService("eu.strasbourg.utils.api.AssetVocabularyHelperService") />

<#assign asset = assetEntryLocalService.getEntry('com.liferay.journal.model.JournalArticle', entry.resourcePrimKey) >
<#assign typeNoel = assetVocabularyHelper.getAssetEntryCategoriesByVocabulary(asset, "type noel") />
<#assign tauxNoel = assetVocabularyHelper.getAssetEntryCategoriesByVocabulary(asset, "taux noel") />
<#assign dureeNoel = assetVocabularyHelper.getAssetEntryCategoriesByVocabulary(asset, "duree noel") />

<#assign layoutHelper = serviceLocator.findService("eu.strasbourg.utils.api.LayoutHelperService") />
<#assign detailURL = layoutHelper.getJournalArticleLayoutURL(entry.groupId, entry.articleId, themeDisplay) />

<@liferay_portlet.actionURL var="detailURLFilter">
  <@liferay_portlet.param name="userTargetClassId" value="${entry.getClassNameId()}" />
  <@liferay_portlet.param name="userTargetClassPK" value="${entry.getArticleId()}" />
  <@liferay_portlet.param name="userTargetTitle" value="${entry.getTitle(locale)}" />
  <@liferay_portlet.param name="detailURL" value="${detailURL}" />
  <@liferay_portlet.param name="searchLogId" value="${renderRequest.getAttribute('searchLogId')!0}" />
</@liferay_portlet.actionURL>

<div class="col-md-4 col-sm-6 col-xs-12">
    <a href="${detailURLFilter}" class="mns-bloc-exp" style="min-height: 391px;">
      <div class="mns-img">
          <figure class="fit-cover">
              <img src="${imageURL}" width="375" height="280" alt="${title}">
          </figure>
          <!-- <div class="mns-wrap-tag">
            <#list typeNoel as type>
              <span>${type.getName()}</span>
            </#list>
            <#list tauxNoel as taux>
              <span>${taux.getName()}</span>
            </#list>
            <span>${dureeNoel?first .getName()}</span>
          </div> -->
      </div>
      <div class="mns-content">
          <span class="mns-location">${lieu}</span>
          <h3>${title}</h3>
          <span class="icon-chevron-thin-right"></span>
          <span class="basic-link"><@liferay_ui.message key="eu.discover" /></span>
      </div>
    </a>
</div>