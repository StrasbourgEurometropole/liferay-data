<#macro subnavigation prefix>
  <!-- Barre de Menu Niveau 1 -->
  <nav class="th-nav-level-1 th-bar-in-front" aria-label="<@liferay.language key='eu.strasbourg.menu.main-navigation' />">
    <ul class="th-menu-level-1">
      <li><button data-overlay-open="th-overlay-nav" class="th-cta-search"></button></li>
      <!-- Entrées principales -->
      <#list nav_items as nav_item>
        <#if nav_item.getName() != 'Accueil'>
          <li>
            <a <#if nav_item.layout.isTypeURL() || !nav_item.hasChildren()>href="${nav_item.getURL()}"</#if> <#if !nav_item.layout.isTypeURL() && nav_item.hasChildren()> data-th-menu="${nav_item.getName()?html}"</#if> class="th-level-1" <#if nav_item.layout.isTypeURL()>target="_blank"</#if>>${nav_item.getName()}</a>
          </li>
        </#if>
      </#list>
    </ul>
  </nav>
  
  <!-- Barre Header - Tablette Portrait 980px -->
  <div class="th-bar-header-touch-device">
    <div class="th-logo-strasbourg">
    </div>
    <div class="th-align-right">
      <div class="th-lang-mobile th-dropdown">
        <button>fr</button>
        <ul>
          <li><a href="https://int.strasbourg.eu/de/" title="Deutsch">de</a></li>
          <li><a href="https://int.strasbourg.eu/en/" title="English">en</a></li>
        </ul>
      </div>
      <#assign layoutHelper = serviceLocator.findService("eu.strasbourg.utils.api.LayoutHelperService") />
      <#if request.session.getAttribute("publik_logged_in")!false>
        <#assign notificationService = serviceLocator.findService("eu.strasbourg.service.notification.service.UserNotificationStatusLocalService") />
        <div class="seu-nav-account seu-nav-btn">
          <button name="trigger-account-menu" class="trigger-account-menu" onClick="javascript: location='${layoutHelper.getDashboardURL()}';">
            <span class="seu-flexbox">
              <#assign notifCount = notificationService.getUnreadNotificationCount(request.session.getAttribute("publik_internal_id")) />
              <span class="seu-picto">
                  <#if (notifCount > 0)>
                      <span class="notif-amount">${notifCount}</span>
                  </#if>
              </span>
              <a href="${layoutHelper.getDashboardURL()}" style="text-decoration: none;" title="Mon tableau de bord">
                <span class="seu-text">${request.session.getAttribute("publik_given_name")?html}&nbsp;${request.session.getAttribute("publik_family_name")[0..0]?html}.</span>
              </a>
              <span class="seu-arrow" style="display: none;"></span>
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
        <a href="${layoutHelper.getPublikLoginURL(portalUtil.getCurrentCompleteURL(request))?html}" class="th-nav-account"><@liferay.language key="eu.login.strasbourg" /></a>
      </#if>
      <button data-overlay-open="th-overlay-nav" class="th-cta-search"></button>
      <div class="th-wrapper-menu">
        <button data-overlay-open="th-overlay-nav" class="th-menu">
          <span></span>
          <span></span>
          <span></span>
          <span></span>
        </button>
      </div>
    </div>
  </div>

  <!-- Overlay de Menu -->
  <div id="th-overlay-nav" class="overlay" data-vheight="100">

      <!-- Tablette Portrait + Mobile -->
      <div class="th-top-overlay-menu">
          <button data-overlay-open="th-overlay-nav" class="th-cta-search"></button>
          <span class="title-top-menu"><@liferay.language key="menu" /></span>
          <div class="th-lang-list">
              <a href="https://www.strasbourg.eu" title="Français" class="th-lang th-fr ${(locale.language == "fr")?then('th-actif','')}">fr</a>
              <a href="https://int.strasbourg.eu/de/" title="Deutsch" class="th-lang th-de ${(locale.language == "de")?then('th-actif','')}" >de</a>
              <a href="https://int.strasbourg.eu/en/" title="English" class="th-lang th-en ${(locale.language == "en")?then('th-actif','')}" >en</a>
          </div>
          <button data-overlay-close="th-overlay-nav" class="th-cta-menu"></button>
      </div>

      <nav class="th-nav-level-1" aria-label="<@liferay.language key='eu.strasbourg.menu.main-navigation' />">
        <ul class="th-menu-level-1">
          <li class="th-has-submenu th-has-overlay-search">
            <button data-overlay-open="th-overlay-nav" class="th-cta-search th-hide-tablet-p"></button>
            <!-- Sous-Menu -->
            <div class="th-submenu th-wrapper-elasticsearch">
              <@liferay_portlet["runtime"]
              portletProviderAction=portletProviderAction.VIEW
              portletName="eu_strasbourg_portlet_dynamic_search_asset_DynamicSearchAssetPortlet"
              instanceId="DynamicResearch"/>
            </div>
          </li>

          <#list nav_items as nav_item>
            <#if nav_item.getName() != 'Accueil'>
              <li <#if !nav_item.layout.isTypeURL() && nav_item.hasChildren()>class="th-has-submenu"</#if>>
                <a href="${nav_item.getURL()}" <#if !nav_item.layout.isTypeURL() && nav_item.hasChildren()>aria-haspopup="true" aria-expanded="false" data-th-menu="${nav_item.getName()?html}"</#if> class="th-level-1" <#if nav_item.layout.isTypeURL()>target="_blank"</#if>>${nav_item.getName()}</a>
                <!-- Sous-Menu -->
                <#if !nav_item.layout.isTypeURL()>
                  <div class="th-submenu" <#if !nav_item.hasChildren()>style="background: green"</#if>>
                    <#if nav_item.hasChildren()>
                        <!-- Tablette Portrait + Mobile -->
                        <div class="th-top-overlay-menu">
                          <button data-overlay-open="th-overlay-nav" class="th-cta-search"></button>
                          <div class="back back-level-1">
                            <span class="title-menu-niv-1">${nav_item.getName()}</span>
                            <span class="back-txt"><@liferay.language key="eu.strasbourg.menu.back" /></span>
                          </div>
                          <button data-overlay-close="th-overlay-nav" class="th-cta-menu"></button>
                        </div>
                        <ul class="th-menu-niveau-2">
                          <#if layoutHelper.hasQuickAccess(nav_item)>
                            <li class="th-hav-level-3">
                              <a href="#" aria-haspopup="true" aria-expanded="false" class="th-level-2 th-acces-rapide"><@liferay.language key="eu.strasbourg.menu.quick-access" /></a>
                              <div class="th-menu-niveau-3-images">
                                <!-- Tablette Portrait + Mobile -->
                                <div class="th-top-overlay-menu">
                                  <button data-overlay-open="th-overlay-nav" class="th-cta-search"></button>
                                  <div class="back back-level-2">
                                    <span class="title-menu-niv-1"><@liferay.language key="eu.strasbourg.menu.quick-access" /></span>
                                    <span class="back-txt"><@liferay.language key="eu.strasbourg.menu.back" /></span>
                                  </div>
                                  <button data-overlay-close="th-overlay-nav" class="th-cta-menu"></button>
                                </div>
                                <!-- Wrapper des images -->
                                <div class="th-wrapper">
                                  <#list nav_item.getChildren() as nav_child>
                                    <#if nav_child.getName() == 'Raccourcis' || nav_child.getName() == 'Accès rapides'>
                                      <#if nav_child.hasChildren()>
                                        <#list nav_child.getChildren() as nav_subchild>
                                          <#assign description = '' />
                                          <#assign introductionAttribute = nav_subchild.layout.expandoBridge.getAttribute('introduction') />
                                          <#list introductionAttribute?keys as key> 
                                            <#if key == locale>
                                              <#assign description = introductionAttribute?values[key_index] />
                                            </#if>
                                          </#list>
                                          <div class="th-menu-image data-wrapper" data-url="${nav_subchild.getURL()}" data-type="${nav_subchild.layout.isTypeURL()?then(1,0)}" data-image="${nav_subchild.layout.expandoBridge.getAttribute('image')}" data-name="${nav_subchild.getName()}" data-description="${description}" ></div>
                                        </#list>
                                      </#if>
                                    </#if>
                                  </#list>
                                </div>
                              </div>
                            </li>
                          </#if>
                          <!-- Sous menu niveau 1 -->
                          <#list nav_item.getChildren() as nav_child>
                            <#if nav_child.getName() != 'Raccourcis' && nav_child.getName() != 'Accès rapides'>
                              <li <#if !nav_child.layout.isTypeURL() && nav_child.hasChildren()>class="th-hav-level-3"</#if>>
                                <div class="th-level-2 data-level-2" data-url="${nav_child.getURL()}" data-type="${nav_child.layout.isTypeURL()?then(1,0)}" data-name="${nav_child.getName()}" ></div>
                                <!-- Sous menu niveau 2 -->
                                <#if nav_child.hasChildren()>
                                  <div class="th-menu-niveau-3">
                                      <!-- Tablette Portrait + Mobile -->
                                      <div class="th-top-overlay-menu">
                                        <button data-overlay-open="th-overlay-nav" class="th-cta-search"></button>
                                        <div class="back back-level-2">
                                          <span class="title-menu-niv-1">${nav_child.getName()}</span>
                                          <span class="back-txt"><@liferay.language key="eu.strasbourg.menu.back" /></span>
                                        </div>
                                        <button data-overlay-close="th-overlay-nav" class="th-cta-menu"></button>
                                      </div>
                                      <div class="th-ssmenu-niveau-2 data-level-3" data-url="${nav_child.getURL()}" data-type="${nav_child.layout.isTypeURL()?then(1,0)}" data-name="${nav_child.getName()}" ></div>
                                      <ul>
                                        <#list nav_child.getChildren() as nav_subchild>
                                          <li class="data-level-3" data-url="${nav_subchild.getURL()}" data-type="${nav_subchild.layout.isTypeURL()?then(1,0)}" data-name="${nav_subchild.getName()}" ></li>
                                        </#list>
                                      </ul>
                                  </div>
                                </#if>
                              </li>
                            </#if>
                          </#list>
                        </ul>
                    </#if>
                  </div>
                </#if>
              </li>
            </#if>
          </#list>

          <li>
            <button data-overlay-close="th-overlay-nav" class="close-overlay">
              <svg xmlns="http://www.w3.org/2000/svg" width="15.556" height="15.556" viewBox="0 0 15.556 15.556">
                <g id="Groupe_22" data-name="Groupe 22" transform="translate(-767.222 -44)">
                  <g id="Groupe_2" data-name="Groupe 2" transform="translate(700 42)">
                    <g id="Groupe_29" data-name="Groupe 29" transform="translate(7)">
                      <rect id="Rectangle_9" data-name="Rectangle 9" width="20" height="2" transform="translate(60.222 16.142) rotate(-45)"/>
                      <rect id="Rectangle_11" data-name="Rectangle 11" width="20" height="2" transform="translate(61.636 2) rotate(45)"/>
                    </g>
                  </g>
                </g>
              </svg>
            </button>
          </li>
        </ul>
      </nav>

      <div class="th-bottom-cta th-v-mobile">
        <#assign layoutHelper = serviceLocator.findService("eu.strasbourg.utils.api.LayoutHelperService") />
        <#if !request.session.getAttribute("publik_logged_in")!false>
          <a href="${layoutHelper.getPublikLoginURL(portalUtil.getCurrentCompleteURL(request))?html}" class="th-nav-account" title="Connexion">
            <span class="th-picto"></span>MonStrasbourg.eu
          </a>
        </#if>

        <a href="/contact" class="th-nav-contact" title="Contact">
          <span class="th-picto"></span><@liferay.language key="contact" />
        </a>
      </div>
  </div>
</#macro>