<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
  <#assign homeURL = "/web${layout.group.friendlyURL}" />
<#else>
  <#assign homeURL = "" />
</#if>
<nav class="navbar mns-nav navbar-light">
    <div id="search-bar" class="mns-search-bar">
        <div class="container">
            <div class="row">
                <form action="${homeURL}/recherche" method="get">
                    <input type="hidden" name="p_p_id" value="eu_strasbourg_portlet_search_asset_SearchAssetPortlet" />
                    <input type="text" name="_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_keywords" id="search" placeholder="Rechercher..." />
                </form>
            </div>
        </div>
    </div>
    <div class="container navbar-expand-md mns-navbar-wrapper">
        <div class="sully-navbar-header">
            <a class="navbar-brand" href="${homeURL}"><span class="subtitle">L'été</span> à Strasbourg</a>
            <button type="button" class="mns-top-header-mobile" data-toggle="collapse" data-target="#langages" aria-expanded="false" aria-controls="langages">
                ${locale.language}
                <div id="langages" class="collapse" >            
                        <#assign entity = themeDisplay.getURLCurrent()?keep_after(layout.friendlyURL)?keep_before('\\?','r') />
                        <#if homeURL != "">
                            <a href="/fr${homeURL}${layout.friendlyURL}${entity}" title="Français"  class="${(locale.language =='fr')?then('active','')}">FR</a>
                            <a href="/de${homeURL}${layout.friendlyURL}${entity}" title="Deutsch" class="${(locale.language =='de')?then('active','')}" >DE</a>
                            <a href="/en${homeURL}${layout.friendlyURL}${entity}" title="English" class="${(locale.language =='en')?then('active','')}" >EN</a>
                        <#else>
                            <a href="/fr${layout.friendlyURL}${entity}" title="Français"  class="${(locale.language =='fr')?then('active','')}">FR</a>
                            <a href="/de${layout.friendlyURL}${entity}" title="Deutsch" class="${(locale.language =='de')?then('active','')}" >DE</a>
                            <a href="/en${layout.friendlyURL}${entity}" title="English" class="${(locale.language =='en')?then('active','')}" >EN</a>
                        </#if>
                </div>
            </button>
            <a class="navbar-agenda" href="${homeURL}/agenda"><span></span></a>
            <button type="button" class="navbar-toggler collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span></span>
                <span></span>
                <span></span>
            </button>
        </div>
        <div id="navbar" class="navbar-collapse collapse" style="justify-content: flex-end;">
            <ul class="nav navbar-nav navbar-right">
                <li class="mns-search-bar mns-search-bar-mobile">
                    <div class="row">
                        <form action="${homeURL}/recherche" method="get">
                            <input type="hidden" name="p_p_id" value="eu_strasbourg_portlet_search_asset_SearchAssetPortlet" />
                            <input type="text" name="_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_keywords" id="search" placeholder="Rechercher..." />
                        </form>
                    </div>
                </li>
                <#list nav_items as nav_item>
                    <#if nav_item.hasChildren()>
                        <li class="dropdown">
                            <a href="${nav_item.getURL()}" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${nav_item.getName()}</a>
                            <ul class="dropdown-menu">
                                <ul class="dropdown-submenu">
                                    <#list nav_item.getChildren() as nav_child>
                                        <#if nav_child?counter == (nav_item.getChildren()?size/2)?ceiling + 1>
                                            </ul>
                                            <ul class="dropdown-submenu">
                                        </#if>
                                        <li>
                                            <a href="${nav_child.getURL()}">
                                                ${nav_child.getName()}
                                            </a>
                                        </li>
                                    </#list>
                                </ul>
                            </ul>
                        </li>
                    <#else>
                        <li><a href="${nav_item.getURL()}">${nav_item.getName()}</a></li>
                    </#if>
                </#list>
                <li class="hidden-sm hidden-xs menu-search">
                    <a href="#"><span class="icon-search"></span></a>
                </li>
            </ul>
        </div>
    </div>
</nav>