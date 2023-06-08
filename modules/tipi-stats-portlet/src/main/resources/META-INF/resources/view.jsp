<%@ include file="/init.jsp"%>


<aui:form method="POST" name="fm" cssClass="config-form">
	<h1><liferay-ui:message key="rt.title" /></h1>
	<liferay-portlet:resourceURL var="waterURL" id="exportWater">
	</liferay-portlet:resourceURL>
	<liferay-portlet:resourceURL var="childhoodURL" id="exportChildhood">
	</liferay-portlet:resourceURL>
	<a href="<%=waterURL %>">Export CSV eau</a>
	<a href="<%=childhoodURL %>">Export CSV enfance</a>
	<div class="clearer"></div>
	<style>	
		#main-content form.config-form a {
		    display: block;
		    padding: 0 0 10px 20px;
		    font-size: 15px;
		}
	</style>
</aui:form>
