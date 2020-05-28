<%@ include file="/council-bo-init.jsp" %>

<%-- Composant : Body --%>
<div class="container-fluid-1280 main-content-body council-bo">

	<aui:fieldset-group markupView="lexicon">

		<%-- Titre de la session --%>
		<h1 class="council-title">Conseil municipal 27/05/2020</h1>
		
		<%-- Liste des non connectés --%>
		<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" cssClass="officials-unconnected" label="eu.council.bo.unconnected">
			
			<div class="connexion-list" id="unconnected-list"></div>
			
		</aui:fieldset>
		
		<%-- Liste des absents --%>
		<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" cssClass="officials-absents" label="eu.council.bo.absents">
			
			<div class="connexion-list" id="absents-list"></div>
			
		</aui:fieldset>
		
		<%-- Liste des connectés --%>
		<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" cssClass="officials-connected" label="eu.council.bo.connected">
			
			<div class="connexion-list" id="connected-list"></div>
			
		</aui:fieldset>
		
	</aui:fieldset-group>

</div>

<liferay-util:html-top>
	<script>
		var currentGroupId = ${dc.groupId};
		var currentCouncilSessionId = ${dc.currentCouncilSessionId};
	</script>
</liferay-util:html-top>

<liferay-util:html-bottom>
	<script src="/o/councilbo/js/council-bo-view-officials-connection.js" type="text/javascript"></script>
</liferay-util:html-bottom>