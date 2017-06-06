<nav class="nav-primary">
	<div class="center">
    	<a href="#" class="btn-menu">Menu</a>
        <ul class="menu">
       		<#list nav_items as nav_item>
       			<!-- We display all items except homepage until "/medias" appears-->
       			<#if nav_item.getURL()?contains("medias")>
   					<#break>
   				</#if>
       			<#if !nav_item.getURL()?contains("accueil")>
					<#if nav_item.hasChildren()>
						<li class="expanded">
					<#else>
						<li>
					</#if>	
					<a href="${nav_item.getURL()}" ${nav_item.getTarget()}><span>${nav_item.getName()}</span></a>
					<#if nav_item.hasChildren()>
						<div class="submenu">
		                 	<div class="center">
								<ul>
									<#list nav_item.getChildren() as nav_child>
										<li>
											<div class="item">
												<a href="${nav_child.getURL()}" class="item_img"><img width="139" height="96" src="${nav_child.getLayout().getExpandoBridge().getAttribute('image')}" ></a>
												<a class="item_content" href="${nav_child.getURL()}" ${nav_child.getTarget()}>${nav_child.getName()}</a>
											</div>
										</li>
									</#list>
								</ul>
							</div>
						</div>
					</#if>
					</li>
				</#if>
			</#list>
		</ul>
	</div>
</nav>
