<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />


<liferay-util:html-bottom>
	<aui:script>
		$(document).ready(function() {
		    $('body').on('click', '[data-favconfirm]', function(e){
		        e.preventDefault();
		        e.stopPropagation();
		        var href = $(this).attr('href');
		        message = Liferay.Language.get('are-you-sure-you-want-to-delete-this-favorite');
		        var agree = function() {
		        	window.location = href;
		        };
		        var deny = function() {
		        }
		        createPopin(message, agree, deny);
		    });
		});
	</aui:script>
</liferay-util:html-bottom>