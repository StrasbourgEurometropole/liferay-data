<%@ include file="/page-header-init.jsp" %>

<div class="page-header <c:if test="${layout.expandoBridge.getAttribute('image')}">with-image</c:if>">
	<liferay-ddm:template-renderer
	    className="<%= LayoutSet.class.getName() %>"
	    contextObjects="${contextObjects}"
	    displayStyle="${displayStyle}"
	    displayStyleGroupId="${displayStyleGroupId}"
	    entries="${entries}"
		>
		<h1>${page.getName(locale)}</h1>
	</liferay-ddm:template-renderer>
</div>