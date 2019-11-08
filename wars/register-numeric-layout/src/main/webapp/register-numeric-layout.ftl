<div class="pro-page-registre">
	<div class="pro-tabs pro-p-150 pro-wrapper-registre">
	    <!-- Nav tabs -->
	    <ul class="nav nav-tabs" role="tablist">
	        <li role="presentation" class="active"><a href=".propositions" aria-controls="propositions" role="tab" data-toggle="tab" title="Onglet de
	            propositions" aria-expanded="true">Propositions</a></li>
	        <li role="presentation" class=""><a href=".recus" aria-controls="recus" role="tab" data-toggle="tab" title="Onglet de Reçus par mail/courrier" aria-expanded="false">Reçus
	                par mail/courrier</a></li>
	    </ul>
	
	    <!-- Tab panes -->
	    <div class="tab-content">
	
	        <!-- Propositions -->
	        <div role="tabpanel" class="tab-pane fade pro-bloc-texte active in propositions">
	            ${processor.processColumn("line-1", "")}
	
	            <!-- WRAPPER LISTING PROPOSITIONS -->
	            <div class="pro-wrapper-propositions pro-bloc-texte">
	                ${processor.processColumn("line-2", "")}
	            </div>
	        </div>
	
	        <!-- Reçus -->
	        <div role="tabpanel" class="tab-pane fade pro-bloc-texte recus">
	            ${processor.processColumn("tab-2", "")}
	        </div>
	    </div>
	</div>
</div>
<script>
	$( document ).ready(function() {
	    $('#main-content').addClass('container');
	});
</script>