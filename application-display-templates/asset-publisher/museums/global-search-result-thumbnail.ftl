<#setting locale = locale />
<#assign layoutHelper = serviceLocator.findService("eu.strasbourg.utils.api.LayoutHelperService") />
<#assign detailURL = layoutHelper.getJournalArticleLayoutURL(entry.groupId, entry.articleId, themeDisplay) />
<div class="entity-thumbnail news-tumbnail">
  <div class="entity-thumbnail-image">
    <a href="${detailURL}">
      <img src="" />
    </a>
  </div>
  <div class="entity-thumbnail-info">
    <div class="entity-thumbnail-title">
      <a href="${detailURL}">
        <h4>${entry.getTitle(locale)}</h4>
      </a>
    </div>
  </div>
</div>