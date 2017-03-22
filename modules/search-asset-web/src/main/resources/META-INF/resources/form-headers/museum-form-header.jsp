<%@ include file="/search-asset-init.jsp" %>

<form id="keywords-deported-form">
	<aui:input type="text" name="deported-keywords" id="deported-keywords" value="${dc.keywords}" />
	<input type="submit" id="submit-search-form" value="<liferay-ui:message key="search" />" />
</form>
<script>
	//Validation formulaire de recherche
	(function($) {
	    $(document).ready(function() {
	        $("#keywords-deported-form input[type=text]").on("keyup paste", function() {
	            $(".search-asset-form input[type=hidden][name$=keywords]").val($(this).val());
	        });
	        $('#keywords-deported-form').on('submit', function(e) {
	            e.preventDefault();
	            var keywords = $('#keywords-deported-form input[type=text]').val();
	            $('.search-asset-form input[type=hidden][name$=keywords]').val(keywords);
	            $('.search-asset-form form').submit();
	        });
	    });
	})(jQuery);
</script>