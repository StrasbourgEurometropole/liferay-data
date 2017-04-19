<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>

<%@ taglib uri="http://strasbourg.eu/tld/picker" prefix="strasbourg-picker" %>

<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<jsp:useBean id="vocabularyHelper" class="eu.strasbourg.utils.AssetVocabularyHelper" />

<style>
	.approved {
		color: #4CAF50;
	}
	.denied {
		color: #F44336;
	}
	.deletion-requested {
		color: #FF9800;
	}
	.submitted {
		color: #2196F3;
	}
	.draft {
		color: #869CAD;
	}
</style>