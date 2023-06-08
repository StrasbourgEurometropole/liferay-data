<!-- Webmag - Edition -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<ul class="hp-kiosque__list">
    <#list entries as curEntry>
        <#assign entry = curEntry.getAssetRenderer().getEdition() />
        <li class="hp-kiosque__card">
            <div class="hp-kiosque__img" style="background-image: url(${entry.imageURL});"></div>
            <div class="hp-kiosque__text">
                <h3 class="hp-kiosque__title" data-dot="2">${entry.getTitle(locale)}</h3>
                <div class="hp-kiosque__ref"></div>
                <div class="hp-kiosque__lead" data-dot="4">${entry.getDescription(locale)}</div>
                <div class="filler"></div>
                <a href="${homeURL}edition/-/entity/id/${entry.editionId}" class="a-btn-main h-inverted icon-right transparent-core unstyled hp-kiosque__see">
                    <span class="flexbox"><i class="mag mag-arrow-right"></i>
                        <span class="btn-text">Visionner</span>
                    </span>
                </a>
                <#if entry.getFileDownloadURL(locale)?has_content>
                    <a href="${entry.getFileDownloadURL(locale)}" class="a-btn-main h-inverted icon-right transparent-core unstyled hp-kiosque__download">
                        <span class="flexbox"><i class="mag mag-arrow-right"></i>
                            <span class="btn-text">Télécharger</span>
                        </span>
                    </a>
                </#if>
            </div>
        </li>
    </#list>
</ul>

<div class="hp-kiosque__more">
    <a href="/editions" class="a-btn-main core h-inverted icon-right">
        <span class="flexbox"><i class="mag mag-arrow-right"></i>
            <span class="btn-text">Toutes les éditions</span>
        </span>
    </a>
</div>