<div id="nav-menu" class="">
    <button id="trigger-menu">
        <div class="menu-picto"></div>
    </button>
    <div class="menu-list">
        <#if menuNavigation.getSiblings()?has_content>
            <#list menuNavigation.getSiblings() as menu>
                <a href="${menu.link.getData()}" class="nav-item ${menu.classMenu.getData()}">
                    <span class="flexbox">
                        <div class="item-picto" <#if !menu.classMenu.getData()?has_content > style="background-image:url(${menu.illustration.getData()})" </#if>></div>
                        <div class="item-text">${menu.getData()}</div>
                    </span>
                </a>
            </#list>
        </#if>
        <div class="fill"></div>
        <#if !custom.hideItem.data?has_content>
            <a href="${custom.linkCustom.getData()}" class="nav-item custom">
                <span class="flexbox">
                    <div class="item-picto"></div>
                    <div class="item-text">${custom.getData()}</div>
                </span>
            </a>
        </#if>
        <button class="nav-item logout">
            <a href="${disconnection.getData()}">
                <span class="flexbox">
                    <div class="item-picto"></div>
                    <div class="item-text"><@liferay.language key="log-out" /></div>
                </span>
            </a>
        </button>
    </div>
</div>