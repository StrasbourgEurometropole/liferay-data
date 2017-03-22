<nav class="nav-primary">
	<div class="center">
    	<a href="#" class="btn-menu">Menu</a>
        <ul class="menu">
        	<#assign beforeMedia = true />
        	<#list nav_items as nav_item>
       			<#if !nav_item.getURL()?contains("accueil")>
       				<#if nav_item.getURL()?contains("medias")>
						<#assign beforeMedia = false />
					</#if>
					<#if beforeMedia>
						<#if nav_item.hasChildren()>
							<#if nav_item.isSelected()>
								<li class="expanded active">
							<#else>
								<li class="expanded">
							</#if>
						<#else>
							<#if nav_item.isSelected()>
								<li class="active">
							<#else>
								<li>
							</#if>
						</#if>
							<#assign nav_url = nav_item.getURL()> 
							<a href="${nav_url}" ${nav_item.getTarget()}><span>${nav_item.getName()}</span></a>
							
							<#if nav_item.hasChildren()>
								<div class="submenu">
			                 		<ul class="child-menu">
			                 			<#list nav_item.getChildren() as nav_child>
											<li>
												<a href="${nav_child.getURL()}" ${nav_child.getTarget()}>${nav_child.getName()}</a>
											</li>
										</#list>
									</ul>
								</div>
							</#if>
						</li>
					</#if>
				</#if>
			</#list>
		</ul>
	</div>
</nav>
