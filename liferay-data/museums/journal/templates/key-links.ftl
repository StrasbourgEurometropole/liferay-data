<#if linkText.getSiblings()?has_content>
    <div class="key-links">
      <#list linkText.getSiblings() as cur_linkText>
          <div class="key-link">
              <a href="${cur_linkText.getChildren()[0].getData()}" target="_blank">
                  ${cur_linkText.getData()}
              </a>
          </div>
      </#list>
  </div>
</#if>