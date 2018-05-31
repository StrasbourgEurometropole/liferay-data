<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>


<liferay-theme:defineObjects />

<portlet:defineObjects />

<jsp:useBean id="LayoutHelper" class="eu.strasbourg.utils.LayoutHelperImpl"/>


<liferay-util:html-bottom>
	<aui:script>
		$(document).ready(function() {
		    $('body').on('click', '[data-dissociateconfirm]', function(e){
		        e.preventDefault();
		        e.stopPropagation();
		        var href = $(this).attr('href');
		        message = Liferay.Language.get('dissociate-validation-text');
		        var agree = function() {
		        	window.location = href;
		        };
		        var deny = function() {
		        }
		        createPopinMediatheque(message, agree, deny);
		    });
		});
	</aui:script>
</liferay-util:html-bottom>
