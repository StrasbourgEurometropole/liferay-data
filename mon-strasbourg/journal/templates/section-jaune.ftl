<section id="personnalisation">
    <div class="content">
        <#assign PortletHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortletHelperService") />
        <#if PortletHelper.showDeleteButtonOnDashboard(themeDisplay, themeDisplay.portletDisplay.id)>
            <button class="delete-wi" data-portlet-id="${themeDisplay.portletDisplay.id}"></button>
        </#if>
    	<div class="picto"></div>
    	<div class="rte">
    		${text.data}
    	</div>
    	<#if buttonURL.data?has_content>
    		<div class="form-group">
    			<div class="content" align="center">
    				<a href="${buttonURL.data}" target="_blank" class="btn-square--bordered--core" title="${buttonText.data}">
    					<span class="flexbox">
    						<span class="btn-text">
    							${buttonText.data}
    						</span>
    						<span class="btn-arrow"></span>
    					</span>
    				</a>
    			</div>
    		</div>
    	</#if>
    </div>
</section>