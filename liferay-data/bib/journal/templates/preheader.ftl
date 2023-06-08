<nav class="nav-lang">
    <div class="center"> <a href="${logoLink.getData()}" title="Strasbourg${logoLinkTitle.getData()}" class="logo-strasbourg" target="_blank"> <img src="${logo.getData()}" alt="${logoLinkTitle.getData()}"> </a>
        <ul>
            <#if languageLink?has_content && languageLink.getSiblings()?has_content>
              <#list languageLink.getSiblings() as cur_languageLink>
                  <#if cur_languageLink.getData()?has_content>
                     <li class="active">
                          <a href="${cur_languageLink.getData()}">${cur_languageLink.getChildren()[0].getData()}</a>
                      </li>
                  </#if>
              </#list>
            </#if>
        </ul>
        <div class="clearfix"></div>
    </div>
</nav>