<div class="list">
    <#if entries?has_content>
    	<#list entries as navigationEntry>
            <a href="${navigationEntry.getURL()}" aria-label="${navigationEntry.getName()?html}" title="${navigationEntry.getName()?html}" class="nav-thumbnail" style="background-image: url(${navigationEntry.getLayout().getExpandoBridge().getAttribute('image')});">
                <div class="info">
                    <div class="title">
                        <span>${navigationEntry.getName()}</span>
                    </div>
                </div>
            </a>
    	</#list>
    </#if>
</div>