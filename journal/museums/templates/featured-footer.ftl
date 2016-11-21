<div class="featured-footer">
    <h2>
        <span>${italicTitle.getData()}</span>
        ${title.getData()}
    </h2>
    <#if links.getSiblings()?has_content>
        <ul>
            <#list links.getSiblings() as cur_link>
                <#assign layoutLocalService = serviceLocator.findService('com.liferay.portal.kernel.service.LayoutLocalService') />
                <#assign pageLayout = layoutLocalService.getLayout(groupId, false, cur_link.getData()?number) />
                <li>
                    <a href="${cur_link.getFriendlyUrl()}">
                        ${pageLayout.getName(locale)}
                    </a>
                </li>
            </#list>
        </ul>
    </#if>
</div>