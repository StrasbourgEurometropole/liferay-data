<#macro menu items isSubMenu linkOnlyIfNoSubMenu ulClass depth maxDepth>
  <ul class="${ulClass}" role="nav">
    <#list items as item>
      <!-- ${item.layoutId} ${themeDisplay.layout.layoutId}-->
      <#assign item_css_class = "" />
      <#if isSubMenu>
        <#assign item_css_class = "submenu-item" />
      </#if>
      <#if item.hasChildren()>
        <#assign item_css_class = item_css_class + " has-submenu" />
      </#if>
      <li class="menu-item ${item_css_class} <#if item.layoutId == themeDisplay.layout.layoutId>active</#if>">
        <#if !linkOnlyIfNoSubMenu || !item.hasChildren()>
          <a href="${item.getURL()}" class="menu-item-name">
            ${item.getName()}
          </a>
        <#else>
          <a href="javascript:void(0)" class="menu-item-name">
            ${item.getName()}
          </a>
        </#if>
        <#if item.hasChildren() && (depth < maxDepth || maxDepth == 0)>
          <a href="#" class="submenu-toggle"></a>
          <@menu items=item.getChildren() isSubMenu=true linkOnlyIfNoSubMenu=false ulClass="submenu" depth=(depth + 1) maxDepth=maxDepth/>
        </#if>
      </li>
    </#list>
  </ul>
</#macro>
