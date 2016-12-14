<span class="access-by-public-menu-title"><@liferay_ui["message"] key="eu.access-by-public" /></span>
<#if entries?has_content>
  <ul role="nav" class="access-by-public-menu-list">
     <#list entries as curEntry>
        <li class="public-menu-item menu-item">
          <a href="${themeDisplay.pathFriendlyURLPublic}${curEntry.group.friendlyURL}${curEntry.friendlyURL}">${curEntry.getName(locale)}</a>
        </li>
      </#list>
  </ul>
</#if>