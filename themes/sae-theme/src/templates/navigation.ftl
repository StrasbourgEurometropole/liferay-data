<nav id="main-menu">
  <div class="container">
    <a href="/" class="logo"><img src="${images_folder}/header/logo.png" alt=""></a>
    <ul class="main-nav">
      <#list nav_items as nav_item>
        <#if !nav_item.getURL()?contains("Accueil")>
          <li>
            <a data-sousMenu-rubrique="menuCat${nav_item?counter}" href="${nav_item.getURL()}" class="link-leading <#if nav_item.isSelected()>actif </#if>">${nav_item.getName()}</a>
            <#if nav_item.hasChildren()>
              <ul class="menu-category menuCat${nav_item?counter}">
                <#list nav_item.getChildren() as nav_child>
                  <li><a class="<#if nav_child.isSelected()> actif </#if>" href="${nav_child.getURL()}">${nav_child.getName()}</a> </li>
                </#list>
              </ul>
            </#if>
          </li>
        </#if>
      </#list>
      <li class="item-search" style="display: none;"><button class="search"><img src="${images_folder}/pictos/search.png" alt=""></button></li>
    </ul>
    <div class="nav-rwd">
      <a href="#mmenu" id="burger"><span></span></a>
    </div>
  </div>
</nav>
