<#setting locale = locale />

<div class="mns-slider-instagram">
    <div class="small-container p-30">
        <div class="owl-carousel owl-instagram owl-opacify owl-theme owl-loaded owl-drag">
            
	        <#list instagram.getSiblings() as textInsta>
	        <div class="item">
	            ${textInsta.data}
	        </div>
	    	</#list>   
	    </div>
	</div>
</div>
                    