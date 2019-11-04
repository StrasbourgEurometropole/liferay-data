<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
  <#assign homeURL = "/web${layout.group.friendlyURL}" />
<#else>
  <#assign homeURL = "" />
</#if>
<nav class="navbar mns-nav">
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
    <div class="container mns-navbar-wrapper">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            </button>
            <#if request.session.getAttribute("publik_logged_in")!false>
              <#assign notificationService = serviceLocator.findService("eu.strasbourg.service.notification.service.UserNotificationStatusLocalService") />
              <div class="nav-account nav-btn">
                <button id="trigger-account-menu" onClick="javascript: location='${layoutHelper.getDashboardURL()}';">
                  <span class="flexbox">
                    <#assign notifCount = notificationService.getUnreadNotificationCount(request.session.getAttribute("publik_internal_id")) />
                    <span class="picto">
                        <#if (notifCount > 0)>
                            <span class="notif-amount">${notifCount}</span>
                        </#if>
                    </span>
                    <a href="${layoutHelper.getDashboardURL()}" style="text-decoration: none; width: auto;" title="<@liferay_ui.message key='eu.dashboard' />" class="connect">
                      <span class="text">${request.session.getAttribute("publik_given_name")}&nbsp;${request.session.getAttribute("publik_family_name")[0..0]}.</span>
                    </a>
                    <span class="arrow" style="display: none;"></span>
                  </span>
                </button>
                <!-- Menu connecté -->
                <@liferay_portlet["runtime"]
                  portletProviderAction=portletProviderAction.VIEW
                  portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
                  instanceId="loggedinmenu"
                  settingsScope="group" />
                </div>
            <#else>
              <a href="${layoutHelper.getPublikLoginURL(portalUtil.getCurrentCompleteURL(request))}" title="<@liferay_ui.message key='eu.login.strasbourg' />" class="connect">
                <span class="flexbox">
                  <span class="picto"></span>
                </span>    
              </a>
            </#if>
            <a class="navbar-brand" href="${homeURL}/">Strasbourg <span class="subtitle"><@liferay_ui.message key='christmas-capital' /></span></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="mns-top-header mns-top-header-mobile">
                    <div>
                        <a href="#" class="mns-w-fixe-2"><span>Pro & Presse</span></a>
                        <#assign entity = themeDisplay.getURLCurrent()?keep_after(layout.friendlyURL)?keep_before('\\?','r') />
                        <#if homeURL != "/">
                            <a href="/fr${homeURL}${layout.friendlyURL}${entity}" title="Français"  class="${(locale.language =='fr')?then('active','')}">FR</a>  
                            <a href="/de${homeURL}${layout.friendlyURL}${entity}" title="Deutsch" class="${(locale.language =='de')?then('active','')}" >DE</a>
                            <a href="/en${homeURL}${layout.friendlyURL}${entity}" title="English" class="${(locale.language =='en')?then('active','')}" >EN</a>
                        <#else>
                            <a href="/fr${layout.friendlyURL}${entity}" title="Français"  class="${(locale.language =='fr')?then('active','')}">FR</a>  
                            <a href="/de${layout.friendlyURL}${entity}" title="Deutsch" class="${(locale.language =='de')?then('active','')}" >DE</a>
                            <a href="/en${layout.friendlyURL}${entity}" title="English" class="${(locale.language =='en')?then('active','')}" >EN</a>
                        </#if>
                    </div>
                </li>
                <li class="mns-search-bar mns-search-bar-mobile">
                    <div class="row">
                        <form action="${homeURL}/recherche" method="get">
                            <input type="hidden" name="p_p_id" value="eu_strasbourg_portlet_search_asset_SearchAssetPortlet" />
                            <input type="text" name="_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_keywords" id="search" placeholder="Rechercher..." />
                        </form>
                    </div>
                </li>
                <li class="hidden-sm hidden-xs menu-accueil">
                    <a href="${homeURL}/">
                        <span class="icon-ico-home"></span>
                    </a>
                </li>
                <#list nav_items as nav_item>
                    <#if nav_item.hasChildren()>
                        <li class="dropdown">
                            <a href="${nav_item.getURL()}" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${nav_item.getName()}</a>
                            <div class="dropdown-menu">
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
                            </div>
                        </li>
                    <#else>
                        <li><a href="${nav_item.getURL()}">${nav_item.getName()}</a></li>
                    </#if>
                </#list>
                <li>
                    <a href="/experientiel" class="mns-btn-yellow"><@liferay_ui.message key='prepare-your-program' /></a>
                </li>
            </ul>
        </div>
    </div>
</nav>