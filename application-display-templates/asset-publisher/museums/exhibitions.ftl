<div class="exhibition">
  <div class="exhibition-tabs">
    <#if entries?has_content>
      <#assign x = 0>
      <#list entries as curEntry><a href="#" class="exhibition-tab <#if x == 0>active</#if>" data-entry-id="${curEntry.getClassPK()}">
        ${curEntry.getTitle(locale)}
        <#assign x++>
      </a></#list>
    </#if>
  </div>
  <div class="exhibition-tabs-contents">
    <#if entries?has_content>
      <#assign x = 0>
      <#list entries as curEntry>
        <div class="exhibition-tab-content <#if x == 0>active</#if>" data-entry-id="${curEntry.getClassPK()}">
          <div class="exhibition-tab-content-title">
            ${curEntry.getTitle(locale)}
          </div>
          <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
          <#assign content = docXml.valueOf("//dynamic-element[@name='content']/dynamic-content/text()") />
          ${content}
        </div>
        <#assign x++>
      </#list>
    </#if>
  </div>
</div>
