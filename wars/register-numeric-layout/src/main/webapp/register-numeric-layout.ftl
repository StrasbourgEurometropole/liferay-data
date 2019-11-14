<div class="container pro-wrapper-registre">
    <div class="row">
        <div class="col-sm-8">
            <div class="pro-tabs pro-p-150">
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
                    <div role="tabpanel" class="tab-pane fade pro-bloc-texte active in propositions portlet-layout row">
                        <div id="numeric-form" class="pro-wrapper-numeric-form col-md-12 portlet-column">
                            ${processor.processColumn("numeric-form", "portlet-column-content")}
                        </div>
                        
						<!-- WRAPPER LISTING PROPOSITIONS -->
                        <div id="formulaires-envoyes" class="pro-wrapper-propositions pro-bloc-texte col-md-12 portlet-column">
                        	${processor.processColumn("formulaires-envoyes", "portlet-column-content")}
                        </div>

                    </div>

                    <!-- Reçus -->
                    <div role="tabpanel" class="tab-pane fade pro-bloc-texte recus portlet-layout row">
                        <div id="courriers" class="col-md-12 portlet-column">
                            ${processor.processColumn("courriers", "portlet-column-content")}
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div id="en-chiffres" class="col-md-12 portlet-column">
                ${processor.processColumn("en-chiffres", "portlet-column-content")}
            </div>
        </div>
    </div>
</div>