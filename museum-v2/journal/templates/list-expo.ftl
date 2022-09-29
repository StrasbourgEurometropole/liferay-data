<div id="list-expo" class="margin-bottom">
    <div class="content">
        <#if (image.getData())?? && image.getData() != "">
        	<img alt="${image.getAttribute("alt")}" data-fileentryid="${image.getAttribute("fileEntryId")}" src="${image.getData()}" />
        </#if>
        <div class="info">
            <p class="title">${title.getData()}</p>
            <h2 class="big-title">${bigTitle.getData()}</h2>
            <a href="${button.link.getFriendlyUrl()}" title="${button.getData()}">${button.getData()}</a>
        </div>
    </div>
</div>