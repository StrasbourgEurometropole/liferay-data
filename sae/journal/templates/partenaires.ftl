<section id="partners">
	<div class="container">
		<div class="content-center">
			<h3>${titre.getData()}</h3>
		</div>
		<ul class="owl-partners owl-carousel owl-theme">
			<#list partenaire.getSiblings() as item>
			<li class="item-partners">
				<a href="${item.getChildren()[0].data}" target="_blank">
				<div class="center-vertical">
					<div class="logo-partners">
						<img alt="${item.data}" class="on" src="${item.getChildren()[2].data}" /><img alt="${item.data}" class="off" src="${item.getChildren()[1].data}" />
					</div>
				</div>
				</a>
			</li>
			</#list>
		</ul>
	</div>
</section>