<%@ include file="/council-bo-init.jsp" %>

<%-- Composant : Body --%>
<div class="container-fluid-1280 main-content-body council-bo">

	<aui:fieldset-group markupView="lexicon">

		<%-- Titre de la session --%>
		<h1 class="council-title">Conseil municipal 27/05/2020</h1>
		
		<%-- Liste des non connectés --%>
		<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" cssClass="officials-unconnected" label="eu.council.bo.unconnected">
			
			<div id="unconnected-official-001">
				<div class="col-md-3">DEBBACHE Karim</div>
				<div class="col-md-9">Safari 5.1 on Apple iPad (iOS 5.0) (size : 850 x 1420)</div>
			</div>
			<div id="unconnected-official-001">
				<div class="col-md-3">PATRICK Thierry</div>
				<div class="col-md-9">Opera 11.52 (identifying as Firefox 4.0) on Mac OS X 10.7.2 (size : 1132 x 1850)</div>
			</div>
			
		</aui:fieldset>
		
		<%-- Liste des absents --%>
		<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" cssClass="officials-absents" label="eu.council.bo.absents">
			
			<div id="absent-official-001">
				<div class="col-md-3">RAFIKI Jean-Pierre</div>
				<div class="col-md-9">IE 10.0 x86 (platform preview; running in IE 7 mode) on Windows Server 2008 R2 / 7 x64 (size : 1920 x 1080)</div>
			</div>
			
		</aui:fieldset>
		
		<%-- Liste des connectés --%>
		<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" cssClass="officials-connected" label="eu.council.bo.connected">
			
			<div id="connected-official-001">
				<div class="col-md-3">DEPAIN Jérémy</div>
				<div class="col-md-9">Chrome 64.0.4578.6 on Windows 8 32-bit (size :1920 x 1080)</div>
			</div>
			<div id="connected-official-001">
				<div class="col-md-3">RAFA Rhin</div>
				<div class="col-md-9">Chrome 80.0.3987.163 on Windows 10 64-bit (size : 1515 x 1789) </div>
			</div>
			
		</aui:fieldset>
		
	</aui:fieldset-group>

</div>

<liferay-util:html-bottom>
	<script src="/o/councilbo/js/council-bo-view-officials-connection.js" type="text/javascript"></script>
</liferay-util:html-bottom>