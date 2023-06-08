<#macro subnavigation prefix>
  <!-- Barre de Menu Niveau 1 -->
  <nav class="th-nav-level-1 th-bar-in-front" aria-label="<@liferay.language key='eu.strasbourg.menu.main-navigation' />">
    <ul class="th-menu-level-1">
      <li><button data-overlay-open="th-overlay-nav" class="th-cta-search"></button></li>
      <!-- Entrées principales -->
      <#list nav_items as nav_item>
        <#if nav_item.getName() != 'Accueil'>
          <li>
            <a 
              <#if nav_item.layout.isTypeURL() || !nav_item.hasChildren()>href="${nav_item.getURL()}"</#if> 
              <#if !nav_item.layout.isTypeURL() && nav_item.hasChildren()>role="button" tabindex="0" data-th-menu="${nav_item.getName()?html}"</#if> 
              class="th-level-1" 
              <#if nav_item.layout.isTypeURL()>target="_blank"</#if>
            >
              ${nav_item.getName()}
            </a>
          </li>
        </#if>
      </#list>
    </ul>
  </nav>
  
  <!-- Barre Header - Tablette Portrait 980px -->
  <div class="th-bar-header-touch-device">
    <div class="th-logo-strasbourg">
       <a href="${homeURL}" aria-label="Accueil">
         <svg xmlns="http://www.w3.org/2000/svg" width="590" viewBox="0 0 296.9 58.1" class="${prefix}-scrolled-logo"><g  fill="#505050" ><path d="M195.9 48.2c0-0.2 0.1-0.4 0.2-0.7 0.1-0.2 0.2-0.4 0.4-0.5 0.2-0.2 0.4-0.3 0.6-0.4 0.2-0.1 0.5-0.2 0.8-0.2 0.5 0 0.9 0.2 1.3 0.5 0.3 0.3 0.5 0.7 0.5 1.2H195.9zM200.1 50.8c-0.2 0.3-0.5 0.6-0.8 0.8 -0.3 0.2-0.7 0.3-1.2 0.3 -0.6 0-1-0.2-1.4-0.5 -0.4-0.3-0.6-0.8-0.7-1.3h6.6v-0.9c0-0.7-0.1-1.4-0.3-2s-0.5-1.1-0.9-1.5 -0.8-0.7-1.4-1c-0.5-0.2-1.1-0.3-1.8-0.3 -0.7 0-1.4 0.1-2 0.3 -0.6 0.2-1.2 0.6-1.6 1 -0.5 0.4-0.8 0.9-1.1 1.5 -0.3 0.6-0.4 1.3-0.4 2s0.1 1.4 0.4 2c0.3 0.6 0.6 1.1 1.1 1.5 0.5 0.4 1 0.7 1.6 1 0.6 0.2 1.3 0.3 2 0.3 0.7 0 1.5-0.2 2.2-0.5 0.7-0.3 1.3-0.8 1.7-1.3L200.1 50.8zM188.7 53.8h2.8v-13.5h-2.8V53.8zM180.3 47.5c0.4-0.4 1-0.6 1.7-0.6s1.3 0.2 1.7 0.6c0.4 0.4 0.6 1 0.6 1.7s-0.2 1.3-0.6 1.7c-0.4 0.4-1 0.6-1.7 0.6s-1.3-0.2-1.7-0.6c-0.4-0.4-0.6-1-0.6-1.7S179.9 48 180.3 47.5M177.3 51.2c0.3 0.6 0.6 1.1 1.1 1.5s1 0.7 1.6 1c0.6 0.2 1.3 0.3 2 0.3 0.7 0 1.4-0.1 2-0.3 0.6-0.2 1.2-0.5 1.6-1s0.8-0.9 1.1-1.5c0.3-0.6 0.4-1.3 0.4-2s-0.1-1.4-0.4-2 -0.6-1.1-1.1-1.5 -1-0.7-1.6-1c-0.6-0.2-1.3-0.3-2-0.3 -0.7 0-1.4 0.1-2 0.3 -0.6 0.2-1.2 0.6-1.6 1s-0.8 0.9-1.1 1.5c-0.3 0.6-0.4 1.3-0.4 2S177 50.6 177.3 51.2M169 47.5c0.4-0.4 1-0.6 1.7-0.6s1.3 0.2 1.7 0.6c0.4 0.4 0.6 1 0.6 1.7s-0.2 1.3-0.6 1.7c-0.4 0.4-1 0.6-1.7 0.6s-1.3-0.2-1.7-0.6c-0.4-0.4-0.6-1-0.6-1.7S168.6 48 169 47.5M165.8 58.1h2.8v-5.3h0c0.3 0.4 0.7 0.8 1.3 0.9 0.5 0.2 1.1 0.3 1.7 0.3 0.7 0 1.2-0.1 1.8-0.4 0.5-0.3 1-0.6 1.3-1.1s0.6-1 0.8-1.6c0.2-0.6 0.3-1.2 0.3-1.8 0-0.7-0.1-1.3-0.3-1.9s-0.5-1.1-0.9-1.5c-0.4-0.4-0.9-0.7-1.4-1 -0.6-0.2-1.2-0.3-1.8-0.3 -0.3 0-0.6 0-0.9 0.1 -0.3 0.1-0.6 0.2-0.8 0.3 -0.2 0.1-0.5 0.3-0.6 0.5 -0.2 0.2-0.3 0.3-0.4 0.5h0V44.6h-2.6V58.1zM157.4 47.5c0.4-0.4 1-0.6 1.7-0.6 0.7 0 1.3 0.2 1.7 0.6 0.4 0.4 0.6 1 0.6 1.7s-0.2 1.3-0.6 1.7c-0.4 0.4-1 0.6-1.7 0.6 -0.7 0-1.3-0.2-1.7-0.6 -0.4-0.4-0.6-1-0.6-1.7S157 48 157.4 47.5M154.3 51.2c0.3 0.6 0.6 1.1 1.1 1.5 0.5 0.4 1 0.7 1.6 1 0.6 0.2 1.3 0.3 2 0.3s1.4-0.1 2-0.3c0.6-0.2 1.2-0.5 1.6-1s0.8-0.9 1.1-1.5c0.3-0.6 0.4-1.3 0.4-2s-0.1-1.4-0.4-2c-0.3-0.6-0.6-1.1-1.1-1.5s-1-0.7-1.6-1c-0.6-0.2-1.3-0.3-2-0.3s-1.4 0.1-2 0.3c-0.6 0.2-1.2 0.6-1.6 1 -0.5 0.4-0.8 0.9-1.1 1.5s-0.4 1.3-0.4 2S154.1 50.6 154.3 51.2M146.8 53.8h2.8v-3.8c0-0.5 0-0.9 0.1-1.2 0.1-0.4 0.2-0.7 0.3-1 0.2-0.3 0.4-0.5 0.7-0.6 0.3-0.2 0.7-0.2 1.3-0.2 0.2 0 0.4 0 0.6 0 0.2 0 0.4 0.1 0.6 0.1v-2.6c-0.1 0-0.3-0.1-0.5-0.1 -0.2 0-0.3 0-0.5 0 -0.6 0-1.1 0.1-1.6 0.4 -0.4 0.3-0.8 0.7-1.1 1.3h0V44.6h-2.8V53.8zM145.2 44.6h-2.5v-2.7h-2.8v2.7h-1.8v2.3h1.8v4.4c0 0.5 0.1 0.9 0.3 1.3 0.2 0.4 0.4 0.6 0.7 0.9 0.3 0.2 0.7 0.4 1.1 0.5 0.4 0.1 0.8 0.1 1.3 0.1 0.3 0 0.7 0 1-0.1 0.3 0 0.7-0.1 1-0.2v-2.4c-0.1 0.1-0.3 0.2-0.6 0.2 -0.2 0-0.4 0-0.6 0 -0.3 0-0.5 0-0.7-0.1 -0.2-0.1-0.3-0.2-0.4-0.3 -0.1-0.2-0.1-0.3-0.2-0.5 0-0.2 0-0.4 0-0.7v-3.1h2.5V44.6zM130.8 48.2c0-0.2 0.1-0.4 0.2-0.7 0.1-0.2 0.2-0.4 0.4-0.5 0.2-0.2 0.4-0.3 0.6-0.4 0.2-0.1 0.5-0.2 0.8-0.2 0.5 0 0.9 0.2 1.3 0.5 0.3 0.3 0.5 0.7 0.5 1.2H130.8zM135 50.8c-0.2 0.3-0.5 0.6-0.8 0.8 -0.3 0.2-0.7 0.3-1.2 0.3 -0.6 0-1-0.2-1.4-0.5 -0.4-0.3-0.6-0.8-0.7-1.3h6.6v-0.9c0-0.7-0.1-1.4-0.3-2 -0.2-0.6-0.5-1.1-0.9-1.5 -0.4-0.4-0.8-0.7-1.4-1 -0.5-0.2-1.1-0.3-1.8-0.3 -0.7 0-1.4 0.1-2 0.3 -0.6 0.2-1.2 0.6-1.6 1s-0.8 0.9-1.1 1.5c-0.3 0.6-0.4 1.3-0.4 2s0.1 1.4 0.4 2c0.3 0.6 0.6 1.1 1.1 1.5s1 0.7 1.6 1c0.6 0.2 1.3 0.3 2 0.3 0.7 0 1.5-0.2 2.2-0.5 0.7-0.3 1.3-0.8 1.7-1.3L135 50.8zM112.1 53.8h2.8v-4.6c0-0.3 0-0.6 0.1-0.9 0-0.3 0.1-0.5 0.2-0.7 0.1-0.2 0.3-0.4 0.5-0.5 0.2-0.1 0.5-0.2 0.9-0.2 0.3 0 0.6 0.1 0.7 0.2 0.2 0.2 0.3 0.3 0.4 0.5 0.1 0.2 0.1 0.4 0.1 0.7 0 0.2 0 0.4 0 0.6v4.8h2.8v-4.8c0-0.3 0-0.5 0.1-0.8 0.1-0.3 0.2-0.5 0.3-0.7 0.1-0.2 0.3-0.3 0.5-0.4s0.5-0.2 0.8-0.2c0.5 0 0.8 0.2 1 0.5 0.2 0.3 0.3 0.7 0.3 1.1v5.3h2.8v-5.4c0-0.6-0.1-1.1-0.2-1.6 -0.1-0.5-0.3-0.9-0.5-1.3 -0.2-0.4-0.6-0.6-1-0.8 -0.4-0.2-0.9-0.3-1.6-0.3 -0.7 0-1.3 0.1-1.7 0.4 -0.5 0.3-0.9 0.7-1.2 1.3 -0.3-0.6-0.6-1-1.1-1.2 -0.5-0.3-1.1-0.4-1.7-0.4 -0.3 0-0.7 0-0.9 0.1 -0.3 0.1-0.5 0.2-0.8 0.3 -0.2 0.1-0.4 0.3-0.6 0.5 -0.2 0.2-0.3 0.4-0.4 0.5h0V44.6h-2.7V53.8zM110.1 47.5c-0.2-0.6-0.6-1.1-1-1.5 -0.4-0.4-0.9-0.7-1.5-1 -0.6-0.2-1.2-0.3-1.9-0.3 -0.7 0-1.3 0.1-1.9 0.4 -0.6 0.2-1.1 0.6-1.5 1s-0.7 0.9-1 1.5c-0.2 0.6-0.3 1.2-0.3 1.8 0 0.7 0.1 1.3 0.3 1.8 0.2 0.6 0.6 1.1 1 1.5 0.4 0.4 0.9 0.7 1.5 1 0.6 0.2 1.2 0.3 1.9 0.3 0.7 0 1.3-0.1 1.9-0.3 0.6-0.2 1.1-0.6 1.5-1 0.4-0.4 0.7-0.9 1-1.5 0.2-0.6 0.3-1.2 0.3-1.8C110.5 48.7 110.4 48.1 110.1 47.5M108.9 50.8c-0.2 0.4-0.4 0.8-0.7 1.1s-0.6 0.6-1 0.7c-0.4 0.2-0.9 0.3-1.4 0.3 -0.5 0-1-0.1-1.4-0.3 -0.4-0.2-0.8-0.4-1-0.7 -0.3-0.3-0.5-0.7-0.7-1.1 -0.2-0.4-0.2-0.9-0.2-1.4s0.1-0.9 0.2-1.4c0.2-0.4 0.4-0.8 0.7-1.1 0.3-0.3 0.6-0.6 1-0.7 0.4-0.2 0.9-0.3 1.4-0.3 0.5 0 1 0.1 1.4 0.3 0.4 0.2 0.8 0.4 1 0.7 0.3 0.3 0.5 0.7 0.7 1.1 0.2 0.4 0.2 0.9 0.2 1.4S109.1 50.3 108.9 50.8M95.9 53.8h1.2v-5c0-0.3 0.1-0.6 0.2-0.9 0.1-0.3 0.3-0.6 0.5-0.9 0.2-0.3 0.5-0.5 0.8-0.7 0.3-0.2 0.7-0.3 1.2-0.3 0.3 0 0.6 0 0.7 0.1l0.2-1.2c-0.3-0.1-0.6-0.1-0.9-0.1 -0.4 0-0.7 0.1-1 0.2 -0.3 0.1-0.6 0.2-0.8 0.4 -0.2 0.2-0.4 0.4-0.6 0.6 -0.2 0.2-0.3 0.5-0.4 0.7h0v-1.6h-1.2c0 0.6 0 1.2 0 1.6s0 0.8 0 1.2V53.8zM93.8 53.8c0-0.3 0-0.7-0.1-1 0-0.4 0-0.7 0-0.9v-6.9h-1.2v4.5c0 0.8-0.1 1.4-0.4 1.9 -0.3 0.5-0.5 0.8-0.9 1 -0.3 0.2-0.6 0.4-0.9 0.4 -0.3 0.1-0.5 0.1-0.5 0.1 -0.5 0-0.9-0.1-1.2-0.2s-0.6-0.3-0.7-0.6c-0.2-0.3-0.3-0.6-0.4-1 -0.1-0.4-0.1-0.8-0.1-1.3v-4.9h-1.2v5.6c0 1.1 0.3 2 0.8 2.6 0.5 0.6 1.4 0.9 2.5 0.9 0.3 0 0.6 0 0.9-0.1 0.3-0.1 0.6-0.2 0.8-0.3 0.3-0.1 0.5-0.3 0.7-0.5 0.2-0.2 0.4-0.4 0.5-0.7h0c0 0.2 0 0.5 0 0.7 0 0.2 0 0.5 0 0.7H93.8zM84.3 49.7v-0.6c0-0.6-0.1-1.2-0.3-1.7 -0.2-0.5-0.5-1-0.8-1.4 -0.4-0.4-0.8-0.7-1.3-1s-1.4-0.4-2-0.4c-0.6 0-1 0.1-1.5 0.4 -0.5 0.2-1 0.6-1.4 1 -0.4 0.4-0.7 0.9-0.9 1.5 -0.2 0.6-0.3 1.2-0.3 1.8 0 0.7 0.1 1.3 0.3 1.8 0.2 0.6 0.6 1.1 1 1.5 0.4 0.4 0.9 0.7 1.4 1 0.6 0.2 1.2 0.3 1.8 0.3 0.7 0 1.4-0.1 2-0.4 0.6-0.3 1.2-0.8 1.7-1.4l-1-0.8c-0.3 0.4-0.6 0.8-1.1 1.1s-1.1 0.5-1.7 0.5c-0.4 0-0.8-0.1-1.2-0.2 -0.4-0.2-0.7-0.4-1-0.7 -0.3-0.3-0.5-0.6-0.7-1 -0.2-0.4-0.3-0.8-0.3-1.2H84.3zM77 48.7c0-0.4 0.1-0.7 0.3-1.1 0.2-0.3 0.4-0.6 0.7-0.9 0.3-0.3 0.6-0.5 1-0.6 0.4-0.2 0.8-0.2 1.2-0.2 0.9 0 1.6 0.3 2.1 0.8 0.5 0.5 0.8 1.2 0.8 2H77z"/><polygon points="128.9 41.7 129.6 43.3 135.4 40.7 134.7 39.1 "/><path d="M19.5 9.9C18.2 8 15.8 7.1 13.4 7.1c-2.9 0-5.7 1.3-5.7 4.5 0 7 16.6 3 16.6 15.5 0 7.5-5.9 11.2-12.8 11.2 -4.3 0-8.6-1.3-11.5-4.7l4.7-4.5c1.5 2.3 4.2 3.7 6.9 3.7 2.9 0 6.1-1.6 6.1-4.8 0-7.7-16.6-3.3-16.6-15.8 0-7.2 6.4-10.6 12.9-10.6 3.7 0 7.3 1 10 3.6L19.5 9.9z"/><path d="M24.3 18.7V13.6h4.9V5.7h5.9v8h6.5v5h-6.5V29c0 2.4 0.7 3.9 3.3 3.9 1 0 2.5-0.2 3.2-0.7v5c-1.2 0.6-3.3 0.8-4.7 0.8 -6.3 0-7.8-2.8-7.8-8.4V18.7H24.3z"/><path d="M45.1 13.6h5.9v3.8h0.1c1.3-2.7 3.9-4.3 7-4.3 0.7 0 1.4 0.1 2.1 0.3v5.7c-1-0.2-1.9-0.4-2.9-0.4 -5.6 0-6.4 4.7-6.4 6v12.7h-5.9V13.6z"/><path d="M83.7 33c0 1.7 0.3 2.7 1.2 2.7 1.3 0 1.9-1.6 1.9-3h1c0 3.2-2.3 5.3-5.8 5.3 -2.4 0-4.4-1.1-5.4-3.4 -1.4 2.6-5.1 3.4-7.3 3.4 -4.2 0-8.4-1.9-8.4-6.6 0-5.6 5.4-6.9 10.3-7.3l5.3-0.4v-4.5c0-3.2-0.5-5.2-4.6-5.2 -1.8 0-5 0.4-4.9 1.6 0.1 0.9 2.8 0.8 2.8 3.4 0 1.9-1.2 3.2-3.3 3.2 -2.4 0-3.5-1.6-3.5-3.5 0-3 4-5.7 9.4-5.7 5.6 0 11.4 1.5 11.4 7.7V33zM76.5 24.6l-4.3 0.4c-3.5 0.4-3.8 2.3-3.8 5.1 0 2.8 0.2 6.2 3.8 6.2 2.5 0 4.2-2.6 4.3-5.5V24.6z"/><path d="M104.3 20.2c-1.1-1.6-2.6-2.4-4.6-2.4 -1.6 0-3.5 0.7-3.5 2.5 0 4.2 12.8 0.8 12.8 10 0 5.6-5.4 7.6-10.2 7.6 -3.7 0-6.8-0.9-9.2-3.7l3.9-3.7c1.5 1.7 3.1 2.9 5.6 2.9 1.7 0 4-0.8 4-2.7 0-4.9-12.8-1-12.8-10.1 0-5.3 4.7-7.7 9.5-7.7 3.2 0 6.5 1 8.4 3.6L104.3 20.2z"/><path d="M112.1 0h5.9v16.6h0.2c1.2-1.5 3.6-3.6 8-3.6 6.8 0 11.5 5.4 11.5 12.4 0 7-4.4 12.4-11.7 12.4 -3.3 0-6.4-1.3-8.2-4.1h-0.1v3.6h-5.6V0zM124.8 18.4c-4.3 0-7 3.4-7 7.1 0 3.7 2.6 7.1 7 7.1 4.3 0 7-3.4 7-7.1C131.7 21.8 129.1 18.4 124.8 18.4"/><path d="M153.6 13c7.2 0 12.9 5 12.9 12.4 0 7.5-5.7 12.4-12.9 12.4 -7.2 0-12.9-5-12.9-12.4C140.8 18 146.5 13 153.6 13M153.6 32.6c4.3 0 7-3.4 7-7.1 0-3.7-2.6-7.1-7-7.1 -4.3 0-7 3.4-7 7.1C146.7 29.2 149.3 32.6 153.6 32.6"/><path d="M191.5 37.3h-5.6v-3.8h-0.1c-1.1 2.4-3.3 4.4-7.3 4.4 -6.4 0-8.6-4.5-8.6-9.3V13.6h5.9v12c0 2.6 0.2 7 4.3 7 3.9 0 5.4-2.9 5.4-6v-12.9h5.9V37.3z"/><path d="M195.5 13.6h5.9v3.8h0.1c1.3-2.7 3.9-4.3 7-4.3 0.7 0 1.4 0.1 2.1 0.3v5.7c-1-0.2-1.9-0.4-2.9-0.4 -5.6 0-6.4 4.7-6.4 6v12.7h-5.9V13.6z"/><path d="M228 34.5c6-0.1 9 2.6 9 8.5 0 7.5-5.6 13.3-14.1 13.3 -6.8 0-12.2-3.5-12.2-8 0-2.9 2.8-5.4 5.3-6.1v-0.1c-2.6-0.7-4-3.1-4-6.1 0-5.1 3.7-6.8 6.1-7.1v-0.1c-3.3-1.5-5.1-4.1-5.1-7.5 0-5.6 5.5-9 10.7-9 3.4 0 5.9 0.9 7.7 2.2 1.2-1.4 2.7-2.7 4.7-2.7 1.8 0 4.2 1.1 4.2 3.3 0 1.7-1.1 3.1-3.1 3.1 -1.8 0-2.8-1.4-2.8-2.8 0-1.2 0.4-2 1.7-2.7v-0.1c-2.4 0.3-2.9 1.7-4.1 2.8 1.7 1.5 2.6 4.2 2.6 6.2 0 5.8-5.7 8.8-10.9 8.8 -1 0-1.8-0.1-2.5-0.4 -0.7-0.2-1.3-0.4-2-0.4 -2.3 0-5 1.7-5 3.2 0 1.7 2.1 1.7 3.6 1.7H228zM216.9 42.2c-1.7 1.8-2.6 3.6-2.6 6.3 0.1 2.9 3.1 6.7 8.7 6.7 6.2 0 11.7-4.2 11.7-8.7 0-3.7-2-4.3-8.2-4.3H216.9zM227.6 21.2c-0.1-7.1-1-7.9-3.9-7.9 -3.2 0-3.7 2.7-3.7 7.7 0 7.4 1.3 8.2 3.7 8.2C225.9 29.2 227.5 28.7 227.6 21.2"/><path d="M261.2 13c-7 0-12.2 5.1-12.2 12.5 0 6.9 4.6 12.5 12 12.5 4.4 0 7.8-1.3 10.5-4.9l-3-2.3c-1.9 2.4-4.2 3.6-7.6 3.6 -4.3 0-7.5-3.5-7.8-7.6h19.2v-1.3C272.4 17.6 267.9 13 261.2 13M253.3 23.4c0.5-4 3.4-6.7 7.8-6.7 4.4 0 7 2.7 7.1 6.7H253.3zM296.7 13.7h-3.9V25.2c0 5.4-2.2 9.1-7.4 9.1 -3.7 0-5.4-2.5-5.4-6.4V13.7h-3.9v14.7c0 5.7 2.8 9.7 9 9.7 3.9 0 6.8-2.2 7.9-4.5h0.1c0 1.3 0.1 2.6 0.1 3.9h3.7c0-1.6-0.2-3.4-0.2-5.6V13.7zM243.2 31.6c-1.7 0-3 1.4-3 3 0 1.7 1.5 3 3 3s3-1.4 3-3C246.2 33 244.9 31.6 243.2 31.6"/></g></svg>
       </a>
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