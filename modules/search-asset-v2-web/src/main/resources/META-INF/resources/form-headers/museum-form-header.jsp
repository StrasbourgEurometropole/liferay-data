<%@ include file="/search-asset-init.jsp" %>

<form id="keywords-deported-form">
    <div class="search-asset-fields">
		<div class="keywords-selection">
			<legend>
				<liferay-ui:message key="keywords" />
			</legend>
			<div class="keywords-selection-control">
                <input type="text" name="<portlet:namespace />deported-keywords" id="<portlet:namespace />deported-keywords" value="${dc.keywords}" placeholder='<liferay-ui:message key="eu.search.asset.web.keyword" />''/>
            </div>
            <a href="#" id="submit" class="button1" aria-label="<liferay-ui:message key="search" />" title="<liferay-ui:message key="search" />"><liferay-ui:message key="search" /></a>
        </div>
    </div>
</form>
<script>
	//Validation formulaire de recherche
	(function($) {
	    $(document).ready(function() {
	        $("#keywords-deported-form input[type=text]").on("keyup paste", function() {
	            $(".search-asset-form input[type=hidden][name$=keywords]").val($(this).val());
	        });
	        $('#keywords-deported-form #submit').on('click', function(e) {
	            e.preventDefault();
	            var keywords = $('#keywords-deported-form input[type=text]').val();
	            $('.search-asset-form input[type=hidden][name$=keywords]').val(keywords);
	            $('.search-asset-form form').submit();
	        });
	        $('#keywords-deported-form').on('submit', function(e){
	            e.preventDefault();
	            var keywords = $('#keywords-deported-form input[type=text]').val();
	            $('.search-asset-form input[type=hidden][name$=keywords]').val(keywords);
	            $('.search-asset-form form').submit();
            });
	    });
	})(jQuery);
</script>