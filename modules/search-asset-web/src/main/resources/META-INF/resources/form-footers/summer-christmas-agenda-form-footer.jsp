<script>
	$('.search-asset-form').addClass('col-sm-3 mns-z-filtres-search');
	$('.search-asset-search-container').addClass('col-md-9 text-center mns-z-list-agenda');
	$('.search-asset-portlet-page').addClass('row');
	$('.search-asset-portlet').addClass('container mns-p-list-agenda');
	$('.search-asset-results > *').addClass('col-md-4 col-sm-6');
	$('.search-asset-form .checkbox input[type=checkbox]').after('<span class="cr"><i class="cr-icon glyphicon glyphicon-ok"></i></span>');
	$('.search-asset-portlet').before('<div class="small-container mns-affiner mns-affiner-m row"><div class="col-xs-12"><span class="mns-more">Affiner votre recherche</span><span class="icon-search-more"></span></div></div>');

	$(document).ready(function() {
	    if(window.location.href.split("?").length > 1){
            $('html,body').animate({scrollTop: $("#result").offset().top - ((environment == 'mobile')?140:160)});
        }
	});
</script>