<div class="region-nav-tools">
    <div class="block-container-breadcrumb">
        <h2 class="hidden">Vous êtes ici</h2>
        <a href="../" class="back">Précédent</a>
        <ul class="page-depth">
            <#if entries?has_content>
                <#list entries as curEntry>
                    <li>
                        <#if curEntry?is_last>
                            <div class="page active">${curEntry.getTitle()}</div>
                        <#else>
                            <a href="${curEntry.getURL()}">${curEntry.getTitle()}</a>
                        </#if>
                    </li>
                </#list>
            </#if>
        </ul>
    </div>
</div>