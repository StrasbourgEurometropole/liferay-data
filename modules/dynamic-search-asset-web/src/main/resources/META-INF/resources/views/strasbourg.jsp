<%@ include file="/dynamic-search-asset-init.jsp" %>

<portlet:resourceURL id="searchSubmit" var="searchSubmitURL">
</portlet:resourceURL>

<!-- Tablette Portrait + Mobile -->
<div class="th-top-overlay-menu">
    <div class="back back-level-1">
        <span class="title-menu-niv-1"><liferay-ui:message key="eu.strasbourg.dynamic-search-strasbourg-search" /></span>
        <span class="back-txt"><liferay-ui:message key="eu.strasbourg.dynamic-search-strasbourg-back" /></span>
    </div>
    <button data-overlay-close="th-overlay-nav" class="th-cta-menu"></button>
</div>

<div class="th-menu-niveau-2 th-search-form">
    <span class="title-search-form th-hide-tablet-p"><liferay-ui:message key="eu.strasbourg.dynamic-search-strasbourg-search" /></span>
    <form action="/" method="get" class="th-form-search">
        <input type="text" name="th-search" id="th-search" placeholder="..." class="th-input-search" />
        <button type="submit" name="th-form-submit" id="th-form-submit" class="th-form-submit"></button>
    </form>
    <liferay-portlet:runtime
        portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
        instanceId="search-links" />
</div>

<div class="th-search-results">
    <p class="th-nb-results th-hide-tablet-p"></p>
    <p class="th-nb-results th-v-tablet-p"></p>
    <div class="th-all-results">
    </div>
</div>

<liferay-util:html-top>
	<script>
		var homeURL = '${homeURL}';
		var porletNamespace = '<portlet:namespace/>';
		var dynamicSearch = ${dynamicSearch};

		var searchSubmitURL = '${searchSubmitURL}';
	</script>
</liferay-util:html-top>

<liferay-util:html-bottom>
	<script src="/o/dynamicsearchassetweb/js/strasbourg.js"></script>
</liferay-util:html-bottom>