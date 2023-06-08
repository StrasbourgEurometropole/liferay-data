<!-- BARRE SUPERIEURE NOTIFIANT LA PHASE EN COURS -->

<#assign phaseInDepositPeriod = false />

<#-- Test de l'existance et parcours -->
<#if entries?size != 0 >
	<#list entries as curEntry>

		<#-- Recuperation de l'entite -->
        <#assign phase = curEntry.getAssetRenderer().getBudgetPhase() />

		<#-- Test de la phase active -->
		<#if phase.getIsActive() >

			<#-- Recuperation du label décrivant la periode de la phase active -->
			<#assign livePeriodLabel = phase.getLivePeriodLabel() />
			<#if livePeriodLabel?has_content >

				<div class="pro-page-budget-participatif">
					<div class="pro-timer">

						<p id="phaseText">Il reste 
						<span id="phaseDays"></span> jours, 
						<span id="phaseHours"></span> heures, 
						<span id="phaseMinutes"></span> minutes et 
						<span id="phaseSeconds"></span> secondes

						<#-- Adaptation du champ selon la periode de la phase active -->
						<#switch livePeriodLabel>
						  	<#case "before-begin-deposit">
							    avant le début des dépôts de projets citoyens
							    <#assign referenceDate = phase.beginDate />
							    <#break>
							<#case "before-end-deposit">
							    pour déposer votre projet
							    <#assign phaseInDepositPeriod = true />
							    <#assign referenceDate = phase.endDate />
							    <#break>
							<#case "before-begin-vote">
							    avant le début des votes
							    <#assign referenceDate = phase.beginVoteDate />
							    <#break>
							<#case "before-end-vote">
							    pour voter
							    <#assign referenceDate = phase.endVoteDate />
							    <#break>
						</#switch>

						</p>
						
					</div>
				</div>

				<script type="text/javascript">
					// Variable de reference
					var countDownDate = new Date(${referenceDate?date?string['yyyy']}, ${referenceDate?date?string['MM']}-1, ${referenceDate?date?string['dd']}).getTime();

					// Créactiond e l'intervalle toutes les secondes
					var interval = setInterval(function() {

					    // Récupération du timestamp
					    var now = new Date().getTime();
					    
					    // Récuperation de la distance entre le timestamp et la date de référence
					    var distance = countDownDate - now;
					    
					    // Calculs des différences avec toutes les unités
					    var days = Math.floor(distance / (1000 * 60 * 60 * 24));
					    var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
					    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
					    var seconds = Math.floor((distance % (1000 * 60)) / 1000);
					    
					    // Changement sur l'interface
					    $("#phaseDays").html(days);
					    $("#phaseHours").html(hours);
					    $("#phaseMinutes").html(minutes);
					    $("#phaseSeconds").html(seconds);
					    
					    // Si le temps est passé, message de fin
					    if (distance < 0) {
					        clearInterval(interval);
					        $("#phaseText").html("Il est l'heure ...");
					    }
					}, 1000);
				</script>

			</#if>

		</#if>

	</#list>
</#if>

<script type="text/javascript">
	$(document).ready(function() {
		// Si la phase n'est pas active, on retirer les boutons de depot
		if (${phaseInDepositPeriod?string("false", "true")}) {
			$(".deposit-button").each(function() {
				$(this).remove();
			});
		}
	});
</script>