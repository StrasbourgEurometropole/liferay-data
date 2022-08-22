<#setting locale = locale />

<!-- Liste de lieux -->
<#assign portletHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortletHelperService") />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
  <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
  <#assign homeURL = "/" />
</#if>

<div class="seu-container rte">
    <div class="seu-wi seu-media seu-wi-lieux">
        <!-- 12 <li> max par ul, chaque ul doit avoir un data-page en relation avec le data-page sur les boutons de la pagination  -->
        <div class="seu-media-container">
            <div class="seu-media-left"><div class="seu-media-picto"></div></div>
            <div class="seu-media-right">
                <div class="seu-media-text">
                    <div class="seu-media-title">${portletHelper.getPortletTitle('places', renderRequest)}</div>
                    <#if entries?has_content>
                        <ul class="seu-wi-lieux-list unstyled" data-page="1">
                            <#list entries as curEntry>
                                <#assign place = curEntry.assetRenderer.place />
                                <li class="seu-wi-lieux-item"><a href="${homeURL}lieu/-/entity/id/${place.placeId}/${place.getNormalizedAlias(locale)}">${place.getAlias(locale)}</a></li>
                            </#list>
                        </ul>
                    </#if>
                </div>
            </div>
            <div class="seu-media-bottom">
                <ul class="seu-pagination unstyled">
                    <li class="seu-pagin-prev disabled seu-pagin-item">
                        <button class="seu-btn-square seu-bordered seu-white" data-action="prev" title="<@liferay_ui.message key="previous" />">
                            <span class="seu-flexbox">
                                <span class="seu-btn-text"><@liferay_ui.message key="previous" /></span>
                                <span class="seu-btn-arrow"></span>
                            </span>
                        </button>
                    </li>
                    <li class="seu-is-active seu-pagin-item">
                        <button data-page="1" title="<@liferay_ui.message key="eu.go-to-page" /> 1">
                            <span class="seu-flexbox">
                                <span class="seu-btn-text">1</span>
                                <span class="seu-btn-arrow"></span>
                            </span>
                        </button>
                    </li>
                    <li class="seu-pagin-next seu-pagin-item">
                        <button class="seu-btn-square seu-bordered seu-white" title="<@liferay_ui.message key="next" />" data-action="next">
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