<#if locale.language == "en">
    <#assign lang = "en_US" />
    <#assign websiteUrl = "http://www.en.strasbourg.eu" />
<#elseif locale.language == "fr">
    <#assign lang = "fr_FR" />
    <#assign websiteUrl = "http://www.strasbourg.eu" />
<#elseif locale.language == "de">
    <#assign lang = "de_DE" />
    <#assign websiteUrl = "http://www.de.strasbourg.eu" />
</#if>


<div class="footer-top">
	<div class="center" style="
    margin: auto;
	">
		<div class="f-left">	
			<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone") />
			<@liferay_portlet["runtime"]
				defaultPreferences="${freeMarkerPortletPreferences}"
				portletProviderAction=portletProviderAction.VIEW
				portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet" />
			${freeMarkerPortletPreferences.reset()}
		</div>
        <div class="f-right">
        	<div class="social-network">
            	<h3><@liferay_ui.message key="follow-us" /></h3>
                	<ul>
                    	<li>
                        	<a href="https://fr-fr.facebook.com/strasbourg.eu" title="Strasbourg.eu sur Facebook (<@liferay_ui.message key="new-window" />)" target="_blank" class="btn-fb">Facebook</a>
                        </li>
                        <li>
                        	<a href="http://www.dailymotion.com/VilledeStrasbourg" title="Strasbourg.eu sur Dailymotion (<@liferay_ui.message key="new-window" />)" target="_blank" class="btn-dm">Dailymotion</a>
                        </li>
                        <li>
                        	<a href="http://twitter.com/strasbourg" title="Strasbourg.eu sur Twitter (<@liferay_ui.message key="new-window" />)" target="_blank" class="btn-tw">Twitter</a>
                        </li>
                        <li>
                        	<a href="http://www.strasblr.eu/" title="Strasbourg.eu sur Tumblr (<@liferay_ui.message key="new-window" />)" target="_blank" class="btn-tumblr">Strasblr</a>
                        </li>
                        <li>
                        	<a href="https://www.instagram.com/strasbourg_eurometropole/" title="Strasbourg.eu sur Instagram (<@liferay_ui.message key="new-window" />)" target="_blank" class="btn-instagram">Instagram</a>
                        </li>
                   	</ul>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
<div class="closure">
	<div class="center">
		<a href="$websiteUrl" title="Strasbourg.eu (<@liferay_ui.message key="new-window" />)" target="_blank" >Strasbourg.eu</a>
	</div>
</div>