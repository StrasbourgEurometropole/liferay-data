<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.PortalUtil" %>
<%@ page import="com.liferay.portal.kernel.exception.NoSuchLayoutException" %>
<%@ page import="com.liferay.portal.kernel.service.LayoutLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@ page import="com.liferay.portal.kernel.model.LayoutSet" %>
<%@ page import="com.liferay.portal.kernel.model.Layout" %>
<%@ page import="com.liferay.portal.kernel.model.Group" %>
<%@ page import="com.liferay.portal.kernel.service.GroupLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.model.Group" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%! 
	// Definition de l'URL de la page 404 pour tous les sites
	private static final String PAGE_404_URL = "/page_404";
%>

<%
	boolean page404Exists = true;

	// Recuperation directe du layoutSet (toutes les pages) du group (site) de la requete
	LayoutSet layoutSet = (LayoutSet)request.getAttribute(WebKeys.VIRTUAL_HOST_LAYOUT_SET);
	
	try {
		// Recherche dans la liste des layouts (pages) du groupe (site)l'existance de la page 404
	    LayoutLocalServiceUtil.getFriendlyURLLayout(layoutSet.getGroupId(), layoutSet.isPrivateLayout(), PAGE_404_URL);
	} catch (NoSuchLayoutException e) {
	    page404Exists = false;
	} catch (Exception e) {
		page404Exists = false;
	}
%>


<c:choose>
	<%-- Si la page 404 existe, on l'importe pour eviter une double redirection--%>
    <c:when test="<%= page404Exists  %>">
        <%-- If server have internet access, you may remove jsessionID as per requirement --%>
    	<c:redirect url='<%= PortalUtil.getPortalURL(request) + PAGE_404_URL + ";jsessionid=" + session.getId() %>'/>
    	<%-- If server have not internet access, you may use JS redirect by passing this url from window.location.href --%>
    </c:when>
    <%-- Sinon, on va vers l'index --%>
    <c:otherwise>
        <c:redirect url='<%= PortalUtil.getPortalURL(request) + "/;jsessionid=" + session.getId() %>'/>
    </c:otherwise>
</c:choose>
