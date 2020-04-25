<%@ include file="/council-init.jsp"%>

<div class="council-flex council-web">
	<div class="detail-delib seu-container">

        <input type="hidden" id="deliberationId" value=""/>
        <input type="hidden" id="stage" value=""/>

        <%@ include file="/templates/message.jsp"%>
        <%@ include file="/templates/presentation-delib.jsp"%>
        <%@ include file="/templates/result.jsp"%>
        <%@ include file="/templates/vote-form.jsp"%>

	</div>
</div>


<!-- Définition des variables à partager avec le JS -->
<liferay-util:html-top>
	<script>
		var groupId = ${dc.groupId};
		var useSkypeView = ${dc.configuration.useSkypeView()};
		var officialConnectedId = ${dc.officialId};

        //Sélection des input hidden
        var hiddenDelibId = document.getElementById("deliberationId");
        var hiddenDelibStatut = document.getElementById("stage");

		// Sélection des différents éléments
		var frontDelibTitle = document.getElementById("delib-title");
		var frontDelibRefresh = document.getElementById("delib-refresh");
		var frontDelibDescription = document.getElementById("delib-description");
		var frontResultatVote = document.getElementById("resultat-vote");
		var frontResultatGeneral = document.getElementById("resultat-general");
		var frontResultatSpecifique = document.getElementById("resultat-specifique");
		var frontConfirmationVote= document.getElementById("confirmation-vote");
		var frontConfirmationVote= document.getElementById("vote-forms");
		var frontVoteButtonSubmit= document.getElementById("vote-button-submit");

	</script>
</liferay-util:html-top>

<!-- Import des JS -->
<liferay-util:html-bottom>
    <script src="/o/councilweb/js/council-dynamic-view.js" type="text/javascript"></script>
</liferay-util:html-bottom>