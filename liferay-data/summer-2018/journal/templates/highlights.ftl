<div class="highlights row">
    <div class="menu-article col-md-5">
    	<#list image.getSiblings() as cur_image>
        	<div id="article${cur_image?counter}" class="article ${(cur_image?counter==1)?then('open','')}">
                <div class="picture" style="background-image: url(${cur_image.getData()})">
                </div>
        	    <div class="detail-article">
            	    <h3>${cur_image.title.getData()}</h3>
            	    <p>${cur_image.description.getData()}</p>
                    <#if cur_image.link.getData()?has_content || cur_image.external_link.getData()?has_content>
                        <#if cur_image.link.getData()?has_content>
            	            <a href="${cur_image.link.getFriendlyUrl()}" class="mns-btn">${cur_image.label.getData()}</a>
                        </#if>
                        <#if !cur_image.link.getData()?has_content>
            	            <a href="${cur_image.external_link.getData()}" class="mns-btn">${cur_image.label.getData()}</a>
                        </#if>
                    </#if>
        	    </div>
            </div>
    	</#list>
        <figure>
            <img src="${main_image.getData()}" alt="${main_image.getAttribute('alt')}" height="725px"/>
        </figure>
        </button>
        <div id="link-article" class="link-article collapse">
        	<#list image.getSiblings() as cur_image>
        	    <div class="mns-btn ${(cur_image?counter==1)?then('open','')}" onClick="changeDetail('article${cur_image?counter}')">${cur_image.title.getData()}</div>
        	</#list>
    	</div>
	    <button type="button" class="change-article-mobile" aria-label="<@liferay_ui.message key="eu.change-article" />" data-toggle="collapse" data-target="#link-article" aria-expanded="false" aria-controls="link-article">
    </div>
</div>

<script>
function changeDetail(article){
    $('.open').removeClass('open');
    $('#'+article).addClass('open');
    $(event.currentTarget).addClass('open');
    $('#link-article').collapse('hide');
}

$(':not(.change-article-mobile)').click(function(){
    $('#link-article').collapse('hide');
})
</script>