<#setting locale = locale />

<#if date.getData()?has_content>
    <#assign dateOffre = dateUtil.parseDate("yyyy-MM-dd", date.getData(), locale)/>  
</#if>

<div class="ops-page-rh">

    <div class="ops-wrapper-content-rh ops-content-wrapper ops-bloc-texte">

        <!-- DETAIL OFFRE RH -->
        <header>
            <div class="ops-meta-card-article">
                <div class="ops-cats">
                    <span class="ops-cat">${postName.getData()}</span>
                </div>
                <#if dateOffre?has_content>
                    <span class="ops-date-article"><@liferay_ui.message key="eu.ops.contest.date" /> : ${dateOffre?string("dd.MM.yyyy")}</time></span>
                </#if>
            </div>
            <h1>${title.getData()}</h1>
        </header>

        <!-- SECTION PRINCIPAL OFFRE RH -->
        <section>

            <!-- RUBRIQUES -->
            <#list rubricTitle.getSiblings() as cur_title>
                <div id="${cur_title.getChild("rubricAnchor").getData()}" class="ops-section-rh ops-bloc-presentation">
                    <div>
                        <h2 class="ops-title-line"><span>${cur_title.getData()}</span></h2>
                    </div>
                    <div class="ops-bloc-texte">
                        ${cur_title.getChild("rubricContent").getData()}
                    </div>
                </div>
            </#list>

        </section>

        <!-- BARRE LATERALE FIXE -->
        <#if rightBlockTitle.getData() != "" >
            <aside>
                <div class="ops-wrapper-sticky">

                    <span class="ops-title-spacing">${rightBlockTitle.getData()}</span>
                    <address>
                        ${rightBlockTitle.getChild("rightBlockContent").getData()}
                    </address>

                    <#assign rightBlockContactTelLabel = rightBlockTitle.getChild("rightBlockContactTelLabel")/>  
                    <#assign rightBlockContactMailLabel = rightBlockTitle.getChild("rightBlockContactMailLabel")/>
                    
                    <#-- Liens de contact -->
                    <#if rightBlockContactTelLabel.getData()?has_content || rightBlockContactMailLabel.getData()?has_content >

                        <span class="ops-title-spacing"><@liferay_ui.message key="eu.ops.contact" /></span>

                        <#if rightBlockContactTelLabel.getData()?has_content >
                            <a href="tel:${rightBlockContactTelLabel.getChild("rightBlockContactTelLink").getData()}">
                                <@liferay_ui.message key="eu.ops.phone" /> : ${rightBlockContactTelLabel.getData()}
                            </a>
                        </#if>

                        <#if rightBlockContactMailLabel.getData()?has_content >
                            <a href="mailto:${rightBlockContactMailLabel.getChild("rightBlockContactMailLink").getData()}">
                                <@liferay_ui.message key="eu.ops.mail" /> : <u>${rightBlockContactMailLabel.getData()}</u>
                            </a>
                        </#if>
                    </#if>
                    
                    <div class="ops-noscrollto">
                        <#list rightBlockTitle.getChild("rightBlockLinkTitle").getSiblings() as cur_linkTitle>
                             <#if cur_linkTitle.getData()?has_content>
                                <a href="${cur_linkTitle.getChild('rightBlockLinkURL').getData()}" 
                                    <#if cur_linkTitle.getChild('rightBlockLinkSwitchColor').getData() == "true"> 
                                        class="ops-btn-inversed"
                                    <#else>
                                        class="ops-btn"
                                    </#if>
                                    <#if cur_linkTitle.getChild("rightBlockLinkBlank").getData() == "true"> target="_blank"</#if>
                                >
                                    ${cur_linkTitle.getData()}
                                </a>
                            </#if>
                        </#list>
                    </div>

                </div>
            </aside>
        </#if>

    </div>

</div>

<style>
    <#--  Cache le titre du portlet liferay  -->
    .portlet-body>div>div.h2 {
        display : none;

    }
</style>