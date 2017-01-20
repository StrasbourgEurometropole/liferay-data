<#setting locale = locale />
<div class="page-header with-image">
    <div class="page-header-image">
        <img src="${layout.expandoBridge.getAttribute('image')}" />
    </div>
    <#if (imageCredit?length > 0)>
        <div class="page-header-image-credit">
            ${imageCredit}
        </div>
    </#if>
    <div class="page-header-info">
        <div class="page-header-title-and-share">
            <div class="page-header-title">
                <h1>${page.getName(locale)}</h1>
            </div>
            <div class="page-header-share">
                <span><@liferay_ui.message key="eu.share-page" /></span>
                <div class="page-header-share-buttons">
                    <div class="facebook"></div>
                    <div class="twitter"></div>
                </div>
            </div>
        </div>
        <div class="page-header-description">
            ${page.getDescription(locale)}
        </div>
    </div>
</div>