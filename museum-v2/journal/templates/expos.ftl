<section id="expos" class="margin-bottom">
    <div class="content">
        <#if (image.getData())?? && image.getData() != "">
        	<img alt="${image.getAttribute("alt")?html}" title="${image.getAttribute("alt")?html}" data-fileentryid="${image.getAttribute("fileEntryId")}" src="${image.getData()}" />
        </#if>
        <div class="info">
            <h2>
                ${title.getData()}
            </h2>
            <a href="${button.link.getFriendlyUrl()}" class="button1" aria-label="${button.getData()?html}" title="${button.getData()?html}">${button.getData()}</a>
        </div>
    </div>
</section>