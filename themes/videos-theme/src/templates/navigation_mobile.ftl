<ul id="menu">
	<#list nav_items as nav_item>
		<#if nav_item.getName() != "Accueil">
			<#if nav_item.isSelected()>
				<li class="selected">
			<#else>
				<li>
			</#if>
					<a href="${nav_item.getURL()}" title="${nav_item.getName()}" ${nav_item.getTarget()}><span>${nav_item.getName()}</span></a>
				</li>
		</#if>
	</#list>
</ul>