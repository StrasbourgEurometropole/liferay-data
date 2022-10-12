<section id="list-expo" class="margin-bottom">
    <div class="content">
        <#if (image.getData())?? && image.getData() != "">
        	<img alt="${image.getAttribute("alt")}" title="${image.getAttribute("alt")}" data-fileentryid="${image.getAttribute("fileEntryId")}" src="${image.getData()}" />
        </#if>
        <div class="info">
            <h2>
                <span>${title.getData()}</span>
                ${bigTitle.getData()}
            </h2>
            <a href="${button.link.getFriendlyUrl()}" class="button1" aria-label="${button.getData()}" title="${button.getData()}">${button.getData()}</a>
        </div>
    </div>
</section>