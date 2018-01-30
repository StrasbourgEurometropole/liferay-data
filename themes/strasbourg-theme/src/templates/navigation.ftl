<nav class="seu-nav-bottom">
  <div class="seu-container" id="seu-main-menu">
    <!-- Entrées principales -->
    <#list nav_items as nav_item>
      <#if nav_item.getName() != 'Accueil'>
        <div class="seu-menu-item-container">
          <a href="${nav_item.getURL()}" class="seu-menu-item"  title="${nav_item.getName()}" <#if nav_item.layout.isTypeURL()>target="_blank"</#if>>${nav_item.getName()}</a>

          <!-- Sous menu niveau 1 -->
          <#if nav_item.hasChildren()>
            <div class="seu-menu-subitem">
              <div class="seu-container">
                <div class="seu-submenu-grid">
                  <#list nav_item.getChildren() as nav_child>
                    <#if nav_child.getName() != 'Raccourcis'>
                      <div class="seu-submenu-item">
                        <a href="${nav_child.getURL()}" class="seu-title" <#if nav_child.layout.isTypeURL()>target="_blank"</#if>>${nav_child.getName()}</a>

                        <!-- Sous menu niveau 2 -->
                        <#if nav_child.hasChildren()>
                          <div class="seu-links">
                             <#list nav_child.getChildren() as nav_subchild>
                                <a class="seu-link" title="${nav_subchild.getName()}" href="${nav_subchild.getURL()}" <#if nav_subchild.layout.isTypeURL()>target="_blank"</#if>>${nav_subchild.getName()}</a>
                              </#list>
                          </div>
                        </#if>
                        
                      </div>
                    </#if>
                  </#list>
                </div>

                <!-- Liens rapides -->
                <div class="seu-submenu-quicklinks">
                  <div class="seu-quicklink-title"></div>
                  <div class="seu-grid">
                    <#list nav_item.getChildren() as nav_child>
                      <#if nav_child.getName() == 'Raccourcis'>
                        <#if nav_child.hasChildren()>
                         <#list nav_child.getChildren() as nav_subchild>
                            <a href="${nav_subchild.getURL()}" class="seu-item <#if nav_subchild.layout.isTypeURL()>seu-external</#if>" title="${nav_subchild.getName()}" <#if nav_subchild.layout.isTypeURL()>target="_blank"</#if>>
                              <div class="seu-visu">
                                <@liferay_theme["layout-icon"] layout=nav_subchild.getLayout() />
                              </div>
                              <div class="seu-title">${nav_subchild.getName()}</div>
                            </a>
                          </#list>
                        </#if>
                      </#if>
                    </#list>
                  </div>
                </div>

              </div>
            </div>
          </#if>

        </div>
      </#if>
    </#list>
  </div>
</nav>