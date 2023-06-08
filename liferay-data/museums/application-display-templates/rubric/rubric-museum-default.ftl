<!-- Rubrique -->
<#setting locale = locale />
<!--
Application Display Template par défaut utilisé pour le site des musées.
Affichage de l'ensemble des sous-pages, avec l'image si elle existe
Si la sous-page a elle même des sous-pages, affichage de celles-ci
Sinon affichage de la description
-->
<#list entries as currentPage>
  <#if !currentPage.hidden>
    <div class="rubric-page">
      <#if currentPage.expandoBridge.getAttribute('image')?has_content>
        <div class="rubric-page-image">
          <a href="${themeDisplay.pathFriendlyURLPublic}${currentPage.group.friendlyURL}${currentPage.friendlyURL}">       
            <img src="${currentPage.expandoBridge.getAttribute('image')}" />
          </a>
        </div>
      </#if>
      <div class="rubric-page-name">
        <a href="${themeDisplay.pathFriendlyURLPublic}${currentPage.group.friendlyURL}${currentPage.friendlyURL}">
          ${currentPage.getName(locale)}
        </a>
      </div>
      <#assign hasVisibleChildren = false>
      <#if currentPage.children?has_content>
        <#list currentPage.children as currentSubPage>
          <#if !currentSubPage.hidden>
            <#assign hasVisibleChildren = true>
            <#break>
          </#if>
        </#list>
      </#if>
      <#if hasVisibleChildren>
        <ul class="rubric-page-links" role="nav">
          <#list currentPage.children as currentSubPage>
            <#if !currentSubPage.hidden>
              <li>
                <a href="${themeDisplay.pathFriendlyURLPublic}${currentPage.group.friendlyURL}${currentSubPage.friendlyURL}">
                  ${currentSubPage.getName(locale)}
                </a>
              </li>
            </#if>
          </#list>
        </ul>
      <#else>
        <div class="rubric-page-description">
          <p>
            ${currentPage.getDescription(locale)}
          </p>
        </div>
        <div class="rubric-page-read-more">
          <a href="${themeDisplay.pathFriendlyURLPublic}${currentPage.group.friendlyURL}${currentPage.friendlyURL}">
            <@liferay_ui.message key="read-more" />
          </a>
        </div>
      </#if>
    </div>
  </#if>
</#list>