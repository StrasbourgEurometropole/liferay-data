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
                    <div role="tabpanel" class="tab-pane fade pro-bloc-texte active in propositions">
                        <div>${processor.processColumn("form", "")}</div>

                        <!-- WRAPPER LISTING PROPOSITIONS -->
                        <div class="pro-wrapper-propositions pro-bloc-texte">
                            <div>${processor.processColumn("formulaires-envoyes", "")}</div>
                        </div>
                    </div>

                    <!-- Reçus -->
                    <div role="tabpanel" class="tab-pane fade pro-bloc-texte recus">
                        <div>${processor.processColumn("courriers", "")}</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div>${processor.processColumn("en-chiffres", "")}</div>
        </div>
    </div>
</div>