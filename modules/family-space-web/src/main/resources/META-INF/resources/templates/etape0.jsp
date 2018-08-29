<%@ include file="/family-space-init.jsp" %>

<section id="family-space">
	<c:if test="${dc.showDeleteButton()}">
		<button class="delete-wi" data-portlet-id="${themeDisplay.portletDisplay.id}"></button>
	</c:if>
    <h2>${title}</h2>
	<!-- contenu web -->
    <liferay-portlet:runtime
        portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
        instanceId="family-space" />
	<!-- Etape 0 -->
	<c:if test="${not empty error}">
		<!-- Message d'erreur -->
		<div class="error">${error}</div>
	</c:if>

    <div align="center">
        <a href="${dc.familySpaceURL}" class="btn-square--bordered--core" target="_blank" title="<liferay-ui:message key="access-family-space" /> (<liferay-ui:message key="eu.new-window" />)">
            <span class="flexbox">
                <span class="btn-text"><liferay-ui:message key="access-family-space" /></span>
                <span class="btn-arrow"></span>
            </span>
        </a>
    </div>
</section>