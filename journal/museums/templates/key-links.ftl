<#if linkText.getSiblings()?has_content>
    <div class="key-links">
      <#list linkText.getSiblings() as cur_linkText>
          <div class="key-link">
              <a href="${cur_number.getChildren()[0].getData()}">
                  ${cur_linkText.getData()}
              </a>
          </div>
      </#list>
  </div>
</#if>