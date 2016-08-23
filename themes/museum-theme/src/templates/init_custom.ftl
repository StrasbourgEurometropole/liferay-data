<#macro menu items isSubMenu ulClass depth maxDepth>
  <ul class="${ulClass}" role="nav">
    <#list items as item>
      <#assign item_css_class = "" />
      <#if isSubMenu>
        <#assign item_css_class = "submenu-item" />
      </#if>
      <#if item.hasChildren()>
        <#assign item_css_class = item_css_class + " has-submenu" />
      </#if>
      <li class="menu-item ${item_css_class}">
        <a href="${item.getURL()}" class="menu-item-name">
          ${item.getName()}
        </a>
        <#if item.hasChildren() && (depth < maxDepth || maxDepth == 0)>
          <a href="#" class="submenu-toggle"></a>
          <@menu items=item.getChildren() isSubMenu=true ulClass="submenu" depth=(depth + 1) maxDepth=maxDepth/>
        </#if>
      </li>
    </#list>
  </ul>
</#macro>
