    <div class="hp-bgdark">
		${processor.processColumn("hp-banner", "")}
        <div class="smag-container">
            <div class="smag-hp-flexbox-medias">
                <div class="left">
                    ${processor.processColumn("hp-diapo", "")}
                </div>
                <div class="right">
                    ${processor.processColumn("hp-video", "")}
                </div>
            </div>
        </div>
        ${processor.processColumn("hp-agenda", "")}
    </div>

    <section class="smag-container">
        <h2 class="waved-title waved-title--t-white waved-title--w-transparent hp-rencontres__title">Rencontres</h2>
        <div class="smag-hp-flexbox-rencontres">
            <div class="hp-rencontres" data-scroll-animation>
			    ${processor.processColumn("hp-rencontres", "")}
            </div>
            <div class="hp-kiosque" data-scroll-animation>
			    ${processor.processColumn("hp-kiosque", "")}
            </div>
        </div>
    </section>

    ${processor.processColumn("hp-social", "")}