<!-- Revues numériques -->
<#setting locale = locale />
<#if entries?size!=0>
  <div class="numeric-reviews">
    <#assign categoryLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetCategoryLocalService") />
    <#assign categoryId = request.getParameter("categoryId")!0 />
    <#if categoryId?number == 0>
      <h1 class="all-reviews-title">
        <@liferay_ui["message"] key="eu.reviews.all-reviews" />
      </h1>
      <#else>
      <@liferay_portlet.renderURL var="returnURL">
       <@liferay_portlet.param name="categoryId" value="0" />
      </@liferay_portlet.renderURL>
      <#assign category = categoryLocalService.getCategory(categoryId?number) />
      <div class="back-button">
        <a href="${returnURL}"><@liferay_ui.message key="back" /></a>
      </div>
      <h1 class="all-reviews-title">
        ${category.getTitle(locale)}
      </h1>
      <div class="reviews-category-description">
        ${category.getDescription(locale)}
      </div>
    </#if>
    <div class="all-reviews">
      <#list entries as curEntry>
        <div>
          <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
          <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />
          <#assign chapo = docXml.valueOf("//dynamic-element[@name='chapo']/dynamic-content/text()") />
          <#assign image = docXml.valueOf("//dynamic-element[@name='image']/dynamic-content/text()") />
          <#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
          <#assign imageURL ="" />
          <#if image?has_content>
              <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(image) />
          </#if>
          <#assign publishDate = curEntry.getPublishDate() />
          <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
          <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
          <div class="review entity-thumbnail"> 
            <div class="review-image entity-thumbnail-image">
              <a href="${viewURL}">
                <img src="${image}" >
              </a>
            </div>
            <div class="review-info entity-thumbnail-info">
              <div class="review-category entity-thumbnail-parent-title">
                <#assign vocabularyLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetVocabularyLocalService") />
                <#assign assetVocabularyHelper = serviceLocator.findService("eu.strasbourg.utils.api.AssetVocabularyHelperService") />
                <#list curEntry.categories as category>
                  <#assign vocabulary = vocabularyLocalService.getVocabulary(category.vocabularyId) />
                  <#assign href = assetVocabularyHelper.getCategoryProperty(category.categoryId, 'href')?replace('.', '/') />
                  <@liferay_portlet.renderURL var="categoryURL">
                    <@liferay_portlet.param name="categoryId" value="${category.categoryId}" />
                  </@liferay_portlet.renderURL>
                  <#if vocabulary.name?contains('revue')>
                    <#if href?has_content>
                      <a href="${href}">${category.getTitle(locale)}</a>
                    <#else>
                      ${category.getTitle(locale)}
                    </#if>
                  </#if>
                </#list>
              </div>
              <div class="review-title entity-thumbnail-title">
                <h4><a href="${viewURL}">${title}</a></h4>
              </div>
              <div class="review-content">
                ${chapo}
              </div>
            </div>
          </div>
        </div>
      </#list>
    </div>
  </div>
</#if>