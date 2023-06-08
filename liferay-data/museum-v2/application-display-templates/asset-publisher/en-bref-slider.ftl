<#setting locale = locale />
<#setting datetime_format="iso">
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<#if entries?has_content>
    <section id="bref" class="margin-bottom">
        <div  class="content container">
            <h2><@liferay_ui.message key="eu.museum.bref" /></h2>
            
                <div class="slider">
                    <div class="swiper">
                        <div class="swiper-wrapper">
                            <#list entries as curEntry>
                                <#if curEntry?has_content && curEntry.getAssetRenderer()?has_content && curEntry.getAssetRenderer().getArticle()?has_content>
                                    <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
                                    <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>
                                    <#assign chapo = docXml.valueOf("//dynamic-element[@name='chapo']/dynamic-content/text()") />
                                    <#assign date = docXml.valueOf("//dynamic-element[@name='date']/dynamic-content/text()") />
                                    <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
                                    <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
                                    <div class="swiper-slide">
                                        <div class="bref-thumbnail">
                                            <p class="title">
                                                ${title}
                                            </p>
                                            <p class="chapo">
                                                ${chapo}
                                            </p>
                                            <p class="date">
                                                <#if date?has_content>
                                                    ${date?date("yyyy-MM-dd")?string("dd/MM/yyyy")}
                                                </#if>
                                            </p>
                                            <div class="paginate">
                                                <span class="first"></span>
                                                <span class="second"></span>
                                            </div>
                                        </div>
                                    </div>
                                </#if>
                            </#list>
                        </div>
                        <div class="swiper-pagination"></div>
                        <div class="swiper-button-prev"></div>
                        <div class="swiper-button-next"></div>
                    </div>
                    
                </div>
        </div>
    </section>
</#if>