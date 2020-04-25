<%@ include file="/council-init.jsp"%>

<portlet:resourceURL id="submitVotes" var="submitVotesURL">
</portlet:resourceURL>

<!-- AFFICHAE SEULEMENT SI L'ELU A VOTE -->
<div class="confirmation-vote">
    <h3>Votre vote a bien été enregistré. Retrouvez votre récapitulatif ci-dessous.</h3>
</div>

<!-- FORMULAIRE (ICI PRESENT AVEC PROCURATION) -->
<form class="vote-forms">
    <div class="vote-form">
        <div class="vote-title">
            Mon vote
        </div>
        <div class="vote-answers">
            <label class="answer-wrapper">
                <input type="radio" id="pour" name="vote" value="Pour" >
                <span>Pour</span>
            </label>

            <label class="answer-wrapper">
                <input type="radio" id="contre" name="vote" value="Contre">
                <span>Contre</span>
            </label>

            <label class="answer-wrapper">
                <input type="radio" id="abstention" name="vote" value="Abstention">
                <span>Abstention</span>
            </label>
        </div>
    </div>
    <div class="vote-form">
        <div class="vote-title">
            Procuration MARTIN Lucas
        </div>
        <div class="vote-answers">
            <label class="answer-wrapper">
                <input type="radio" id="pour1" name="martin" value="Pour" >
                <span>Pour</span>
            </label>

            <label class="answer-wrapper">
                <input type="radio" id="contre1" name="martin" value="Contre">
                <span>Contre</span>
            </label>

            <label class="answer-wrapper">
                <input type="radio" id="abstention1" name="martin" value="Abstention">
                <span>Abstention</span>
            </label>
        </div>
    </div>
    <div class="vote-form">
        <div class="vote-title">
            Procuration HANTOUIN Bénédicte
        </div>
        <div class="vote-answers">
            <label class="answer-wrapper">
                <input type="radio" id="pour2" name="hantouin" value="Pour" >
                <span>Pour</span>
            </label>

            <label class="answer-wrapper">
                <input type="radio" id="contre2" name="hantouin" value="Contre">
                <span>Contre</span>
            </label>

            <label class="answer-wrapper">
                <input type="radio" id="abstention2" name="hantouin" value="Abstention">
                <span>Abstention</span>
            </label>
        </div>
    </div>
    <div class="btn-validate-vote seu-btn-line">
        <button type="submit" id="sendVotes" class="seu-btn-square seu-filled seu-second" title="Valider">
            <span class="seu-flexbox">
                <span class="seu-btn-text">Valider</span>
            </span>
        </button>
    </div>
</form>

<!-- Import des JS -->
<liferay-util:html-bottom>
    <script src="/o/councilweb/js/vote-form.js" type="text/javascript"></script>
</liferay-util:html-bottom>