<#macro subnavigation prefix>
  <nav class="${prefix}-nav-bottom">
    <div class="${prefix}-container" id="${prefix}-main-menu">
      <!-- Entrées principales -->
      <#list nav_items as nav_item>
        <#if nav_item.getName() != 'Accueil'>
          <div class="${prefix}-menu-item-container">
            <a href="${nav_item.getURL()}" class="${prefix}-menu-item"  title="${nav_item.getName()}" <#if nav_item.layout.isTypeURL()>target="_blank"</#if>>${nav_item.getName()}</a>

            <!-- Sous menu niveau 1 -->
            <#if nav_item.hasChildren()>
              <div class="${prefix}-menu-subitem">
                <div class="${prefix}-container">
                  <div class="${prefix}-submenu-grid">
                    <#list nav_item.getChildren() as nav_child>
                      <#if nav_child.getName() != 'Raccourcis'>
                        <div class="${prefix}-submenu-item">
                          <a href="${nav_child.getURL()}" class="${prefix}-title" <#if nav_child.layout.isTypeURL()>target="_blank"</#if>>${nav_child.getName()}</a>

                          <!-- Sous menu niveau 2 -->
                          <#if nav_child.hasChildren()>
                            <div class="${prefix}-links">
                              <#list nav_child.getChildren() as nav_subchild>
                                  <a class="${prefix}-link" title="${nav_subchild.getName()}" href="${nav_subchild.getURL()}" <#if nav_subchild.layout.isTypeURL()>target="_blank"</#if>>${nav_subchild.getName()}</a>
                                </#list>
                            </div>
                          </#if>
                          
                        </div>
                      </#if>
                    </#list>
                  </div>

                  <!-- Liens rapides -->
                  <div class="${prefix}-submenu-quicklinks">
                    <div class="${prefix}-quicklink-title"></div>
                    <div class="${prefix}-grid">
                      <#list nav_item.getChildren() as nav_child>
                        <#if nav_child.getName() == 'Raccourcis'>
                          <#if nav_child.hasChildren()>
                          <#list nav_child.getChildren() as nav_subchild>
                              <a href="${nav_subchild.getURL()}" class="${prefix}-item <#if nav_subchild.layout.isTypeURL()>${prefix}-external</#if>" title="${nav_subchild.getName()}" <#if nav_subchild.layout.isTypeURL()>target="_blank"</#if>>
                                <div class="${prefix}-visu">
                                  <@liferay_theme["layout-icon"] layout=nav_subchild.getLayout() />
                                </div>
                                <div class="${prefix}-title">${nav_subchild.getName()}</div>
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
</#macro>