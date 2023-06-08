<%@ include file="/council-bo-init.jsp" %>

<%-- Composant : Body --%>
<div class="container-fluid-1280 main-content-body council-bo">

	<aui:fieldset-group markupView="lexicon">

		<c:choose>
		
			<c:when test = "${fn:length(dc.getCouncilSessions()) > 0}">

                <%-- Champ : Session --%>
                <aui:select name="councilSessionIdSelect" label="Choix du conseil">
                    <c:forEach var="council" items="${dc.getCouncilSessions()}">
                        <aui:option value="${council.councilSessionId}"
                            label="${council.getTitle()}"
                             />
                    </c:forEach>
                </aui:select>
                <aui:script>
                    AUI().use('aui-base', function(A){
                        A.one("#<portlet:namespace />councilSessionIdSelect").on('change',function(){
                            currentCouncilSessionId = A.one('#<portlet:namespace />councilSessionIdSelect').get('value');
                            refreshConnectionInformations(currentCouncilSessionId);
                        })
                    });
                </aui:script>

                <%-- Titre de la session --%>
				<h1 class="council-title" id="councilTitle">
				</h1>

				<%-- Liste des non connect�s --%>
				<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" cssClass="officials-unconnected" label="eu.council.bo.unconnected">
					
					<div class="connexion-list" id="unconnected-list"></div>
					
				</aui:fieldset>
				
				<%-- Liste des absents --%>
				<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" cssClass="officials-absents" label="eu.council.bo.absents">
					
					<div class="connexion-list" id="absents-list"></div>
					
				</aui:fieldset>
				
				<%-- Liste des connect�s --%>
				<aui:fieldset collapsed="<%=false%>" collapsible="<%=true%>" cssClass="officials-connected" label="eu.council.bo.connected">
					
					<div class="connexion-list" id="connected-list"></div>
					
				</aui:fieldset>
				
			</c:when>
			
			<c:otherwise>
				<h1 class="council-title">
					<liferay-ui:message key="eu.council.bo.no.session.today" />
				</h1>
         	</c:otherwise>
			
		</c:choose>

	</aui:fieldset-group>

</div>

<liferay-util:html-bottom>
	<script>
		var currentGroupId = ${dc.groupId};
		var currentCouncilSessionId = $('#<portlet:namespace />councilSessionIdSelect').val();
	</script>
</liferay-util:html-bottom>

<liferay-util:html-bottom>
	<script src="/o/councilbo/js/council-bo-view-officials-connection.js" type="text/javascript"></script>
</liferay-util:html-bottom>

<style>
    .form-group.input-select-wrapper {
        padding: 20px 20px 0px 20px;
    }
</style>