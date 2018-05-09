<footer id="pro-footer">
	<#assign VOID = freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone")>
	<@liferay_portlet["runtime"]
	portletProviderAction=portletProviderAction.VIEW
	portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet" />
</footer>