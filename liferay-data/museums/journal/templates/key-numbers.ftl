<#if number.getSiblings()?has_content>
    <div class="key-numbers">
      <#list number.getSiblings() as cur_number>
          <div class="key-number">
              <div class="key-number-number">
                  ${cur_number.getData()}
              </div>
              <div class="key-number-text">
                  ${cur_number.getChildren()[0].getData()}
              </div>
          </div>
      </#list>
  </div>
</#if>
