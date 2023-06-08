<!-- Webmag - Liste diaporama -->
<#setting locale = locale />
<#-- Récupération de DateHelper pour le format date -->
<#assign dateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.DateHelperService") />

<div class="seu-container" style="margin-bottom: 45px;">
    <div class="seu-wi smag-wi-breve seu-type--actu">
        <div class="seu-container">
            <div class="seu-wi-content">
                <div class="seu-wi-grid">
                    <#list entries as curEntry>
                        <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
                        <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>
                        <#assign content = docXml.valueOf("//dynamic-element[@name='content']/dynamic-content/text()") />
                        <#assign image = docXml.valueOf("//dynamic-element[@name='image']/dynamic-content/text()") />
                        <#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
                        <#assign imageURL ="" />
                        <#if image?has_content>
                            <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(image) />
                        </#if>
                        <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
                        <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
                       
                        <#assign id = curEntry.getAssetRenderer().getArticle().getArticleId() />
                        <div class="seu-wi-item seu-actu seu-has-picture">
                            <div class="wi-search-result wi-edition-thumbnail">
                                <div class="seu-result-left seu-result-thumbnail">
                                    <a href="${viewURL}" title="${title}">
                                        <div style="background-image: url(${imageURL});" class="thumbnail-background" ></div>
                                    </a>
                                </div>
                                <div class="seu-result-right">
                                    <a class="seu-result-content" href="${viewURL}" title="${title}">
                                        <h2 class="seu-result-title">${title}</h2>
                                        <div class="seu-result-catcher">
                                            ${content?replace("<[^>]*>", "", "r")[0..*100]}
                                            <#if (content?replace("<[^>]*>", "", "r")?length gt 100)>
                                                ...
                                            </#if>
                                        </div>
                                    </a>
                                    <div class="seu-result-infos">
                                        <div class="seu-result-infos-top">
                                            ${dateHelperService.displayShortDate(curEntry.getModifiedDate()?date, locale)}
                                        </div>
                                    </div>
                                </div>
                            </div> 
                        </div>
                    </#list>
                </div>
            </div>
            <div class="seu-media-bottom" style="margin: 40px 0;">
                <ul class="seu-pagination unstyled">
                    <li class="seu-pagin-prev disabled seu-pagin-item">
                        <button class="seu-btn-square seu-bordered seu-core" data-action="prev" title="<@liferay_ui.message key='previous' />">
                            <span class="seu-flexbox">
                                <span class="seu-btn-text"><@liferay_ui.message key="previous" /></span>
                                <span class="seu-btn-arrow"></span>
                            </span>
                        </button>
                    </li>
                    <li class="seu-is-active seu-pagin-item">
                        <button data-page="1" title="<@liferay_ui.message key='eu.go-to-page' /> 1">
                            <span class="seu-flexbox">
                                <span class="seu-btn-text">1</span>
                            </span>
                        </button>
                    </li>
                    <li class="seu-pagin-next seu-pagin-item">
                        <button class="seu-btn-square seu-bordered seu-core" title="<@liferay_ui.message key='next' />" data-action="next">
                            <span class="seu-flexbox">
                                <span class="seu-btn-text"><@liferay_ui.message key="next" /></span>
                                <span class="seu-btn-arrow"></span>
                            </span>
                        </button>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<script>
if($('.seu-wi.smag-wi-breve.seu-type--actu').length){
    $(document).ready(function(){
        $('.seu-wi.smag-wi-breve.seu-type--actu').each(function(index, widget){
            var wi = buildPagination(widget,15);
            goToPage(wi, 1);
            wi.$widget.find('[data-action="next"]').on('click', function(){
                goToPage(wi, getCurrentPage(wi) + 1);
            });
            wi.$widget.find('[data-action="prev"]').on('click', function(){
                goToPage(wi, getCurrentPage(wi) - 1);
            });
            wi.$widget.find('.seu-pagin-item button[data-page]').on('click', function(){
                var target = $(this).attr('data-page'); 
                goToPage(wi, target);
            })
        });
    });
}
</script>

<style>
        .smag-wi-breve .seu-wi-grid {
            display: none; 
        }
        .smag-wi-breve .seu-wi-grid.seu-visible{
            display: block ;
        }
</style>