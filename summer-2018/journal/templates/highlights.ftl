<div class="highlights row">
    <div class="menu-article col-md-5">
    	<#list image.getSiblings() as cur_image>
        	<div id="article${cur_image?counter}" class="article ${(cur_image?counter==1)?then('open','')}">
                <div class="picture" style="background-image: url(${cur_image.getData()})">
                </div>
        	    <div class="detail-article">
            	    <h3>${cur_image.title.getData()}</h3>
                    <p>${cur_image.description.getData()[0..*300]}<#if (cur_image.description.getData()?length > 300)>...</#if></p>
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
        <div class="link-article">
        	<#list image.getSiblings() as cur_image>
        	    <div class="mns-btn ${(cur_image?counter==1)?then('open','')}" onClick="changeDetail('article${cur_image?counter}')">${cur_image.title.getData()}</div>
        	</#list>
    	</div>
    </div>
</div>

<script>
function changeDetail(article){
    $('.open').removeClass('open');
    $('#'+article).addClass('open');
    $(event.currentTarget).addClass('open');
}
</script>