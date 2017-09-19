<h1>${themeDisplay.getLayout().getName(locale)}</h1>
<#if entries?has_content>
    <ul>
      <#list entries as currentPage>
          <li>
            <a href="${themeDisplay.pathFriendlyURLPublic}${currentPage.group.friendlyURL}${currentPage.friendlyURL}">
                  ${currentPage.getName(locale)}
                </a>
            </li>
      </#list>
  </ul>
</#if>
