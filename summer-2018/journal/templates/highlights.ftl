<div class="highlights row">
    <div class="menu-article col-md-5">
        <figure>
            <img src="${main_image.getData()}" alt="${main_image.getAttribute('alt')}" height="725px"/>
        </figure>
        <div class="link-article">
        	<#list image.getSiblings() as cur_image>
        	    <div class="mns-btn ${(cur_image?counter==1)?then('open','')}" onClick="changeDetail()">${cur_image.title.getData()}</div>
            	<div class="article">
                    <div class="picture" style="background-image: url(${cur_image.getData()})">
                    </div>
            	    <div class="detail-article">
                	    <h3>${cur_image.title.getData()}</h3>
                	    <p>${cur_image.description.getData()}</p>
                	    <a href="${cur_image.link.getData()}" class="mns-btn">${cur_image.label.getData()}</a>
            	    </div>
                </div>
        	</#list>
    	</div>
    </div>
</div>

<script>
function changeDetail(){
    $('.highlights .link-article > .mns-btn').each(function() {$(this).removeClass('open');});
    $(event.currentTarget).addClass('open');
}
</script>