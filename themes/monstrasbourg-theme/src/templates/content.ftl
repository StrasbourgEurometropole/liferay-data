
        <section id="favoris">
    <button class="delete-wi"></button>    
    <h2>Mes derniers favoris</h2>
    <a href="#" class="btn-square--filled--core"><span class="flexbox"><span class="btn-text">Tous les favoris</span><span class="btn-arrow"></span></span></a>
</section>
<section id="actu-agenda">
    <button class="delete-wi"></button>    
    <h2>Mes actus & mon agenda</h2>    
    <div id="slider--mega">
        <div class="container top-line"> 
            <div class="filters">
                <button class="actu-filter actif" data-category="tous">
                    <span class="flexbox">
                        <span class="btn-text">Tous</span>
                    </span>
                </button>
                <button class="actu-filter" data-category="actu">
                    <span class="flexbox">
                        <span class="btn-text">Actus</span>
                    </span>
                </button>
                <button class="actu-filter" data-category="mag">
                    <span class="flexbox">
                        <span class="btn-text">Mag'</span>
                    </span>
                </button>
                <button class="actu-filter" data-category="agenda">
                    <span class="flexbox">
                        <span class="btn-text">Agenda</span>
                    </span>
                </button>
            </div>
        </div>
        <div class="slider-mega-container">
            <div class="slider-overflow">
                <div class="slider">
                </div>
            </div>
            <div class="owl-nav">
                <button class="owl-prev">
                    <span class="picto"></span>
                </button>
                <button class="owl-next">
                    <span class="picto"></span>
                </button>
            </div>
        </div>
        <script type="text/javascript">
            /* Exemple de flux pour populer le slider mega (voir script slider--mega.js)
                Chaque donnÃ©e viendra remplacer un placeholder prÃ©vu dans les templates ci dessous
                Pour bien fonctionner le flux doit faire figurer les remontÃ©es dans l'ordre oÃ¹ elles doivent apparaÃ®tre en front
                Pour les dates, 3 possibiliÃ© :
                    *date unique: "le 24.06", renseigner date_end + date_prefix (peut Ãªtre automatiquement "le")
                    *date "Ã  partir de": reseigner data_end + date_prefix (peut Ãªtre automatiquement "Ã  partir du")
                    *date interval "du X au X" : renseigner date_start, date_end,  date_prefix (peut Ãªtre automatiquement "du") et date_suffix (peut Ãªtre automatiquement "au")
                Important: Laisser Ã©galement les champs vides prÃ©sent dans le json, sinon les placeholders ne seront pas remplacÃ©s
            */
            var mega_source = [
                {
                    "category": "agenda",
                    "title": "SoirÃ©e jazz au restaurant de la chouc'",
                    "lead": "Le ThÃ©Ã¢tre de la Chouc' vous propose une belle soirÃ©e jazz au Restaurant en compagnie d'Erwin Siffer et son trio. Beau mariage entre la gastronomie rÃ©gionale et le jazz !!!",
                    "link": "#",
                    "ville": "Illkirch-Graffenstaden - La Vill'A - Illkirch-Graffenstaden",
                    "date_start": "",
                    "date_end": "24.06",
                    "date_prefix": "Le",
                    "date_suffix": ""
                },
                {
                    "category": "mag",
                    "title": "Faites vos produits mÃ©nagers vous-mÃªmes",
                    "lead": "Avec les produits naturels, quelques ingrÃ©dients suffisent pour rÃ©aliser des choses qui vont sortir du cadre",
                    "link": "http://google.fr",
                    "picture": "http://placehold.it/600x600"
                },
                {
                    "category": "mag",
                    "title": "Faites vos produits mÃ©nagers vous-mÃªmes",
                    "lead": "Avec les produits naturels, quelques ingrÃ©dients suffisent pour rÃ©aliser des choses qui vont sortir du cadre",
                    "link": "http://google.fr",
                    "picture": "http://placehold.it/600x600"
                },
                {
                    "category": "actu",
                    "title": "FÃªte de la musique",
                    "lead": "Comme chaque annÃ©e, chacun est invitÃ© Ã  fÃªter l'arrivÃ©e de l'Ã©tÃ© et Ã  jouer de la musique parce que c'est trop cool",
                    "link": "http://google.fr",
                    "picture": "http://placehold.it/600x600"
                },
                {
                    "category": "mag",
                    "title": "Faites vos produits mÃ©nagers vous-mÃªmes",
                    "lead": "Test de description numÃ©ro 2",
                    "link": "http://google.fr",
                    "picture": "http://placehold.it/600x600",
                    "is_Big": true
                },
                {
                    "category": "agenda",
                    "title": "SoirÃ©e jazz au restaurant de la chouc'",
                    "lead": "Test de description",
                    "link": "#",
                    "ville": "Strasbourg",
                    "date_start": "11.06",
                    "date_end": "24.06",
                    "date_prefix": "Du",
                    "date_suffix": "Au"
                },
                {
                    "category": "agenda",
                    "title": "SoirÃ©e jazz au restaurant de la chouc'",
                    "lead": "Test de description",
                    "link": "#",
                    "ville": "Strasbourg",
                    "date_start": "11.06",
                    "date_end": "24.06",
                    "date_prefix": "Du",
                    "date_suffix": "Au"
                },
                {
                    "category": "agenda",
                    "title": "Festival Folks",
                    "lead": "FOLKS! est une journÃ©e festive et musicale gratuite dÃ©diÃ©e aux habitants dâ€™Ostwald et dâ€™ailleurs. Parce quâ€™Ostwald aime perpÃ©tuer lâ€™Ã¢me de son cÅ“ur de village, la soirÃ©e invite toutes et tout Ã§a tout Ã§a",
                    "link": "#",
                    "ville": "",
                    "date_start": "",
                    "date_end": "24.06",
                    "date_prefix": "Ã  partir du",
                    "date_suffix": ""
                },
                {
                    "category": "mag",
                    "title": "Faites vos produits mÃ©nagers vous-mÃªmes",
                    "lead": "Test de description numÃ©ro 2",
                    "link": "http://google.fr",
                    "picture": "http://placehold.it/300x300"
                },
                {
                    "category": "mag",
                    "title": "Faites vos produits mÃ©nagers vous-mÃªmes",
                    "lead": "Test de description numÃ©ro 2",
                    "link": "http://google.fr",
                    "picture": "http://placehold.it/300x300"
                },
                {
                    "category": "mag",
                    "title": "Faites vos produits mÃ©nagers vous-mÃªmes",
                    "lead": "Test de description numÃ©ro 2",
                    "link": "http://google.fr",
                    "picture": "http://placehold.it/300x300"
                },
                {
                    "category": "mag",
                    "title": "Faites vos produits mÃ©nagers vous-mÃªmes",
                    "lead": "Test de description numÃ©ro 2",
                    "link": "http://google.fr",
                    "picture": "http://placehold.it/300x300"
                },
                {
                    "category": "mag",
                    "title": "Faites vos produits mÃ©nagers vous-mÃªmes",
                    "lead": "Test de description numÃ©ro 2",
                    "link": "http://google.fr",
                    "picture": "http://placehold.it/300x300"
                },
                {
                    "category": "mag",
                    "title": "Faites vos produits mÃ©nagers vous-mÃªmes",
                    "lead": "Test de description numÃ©ro 2",
                    "link": "http://google.fr",
                    "picture": "http://placehold.it/300x300"
                },
                {
                    "category": "mag",
                    "title": "Faites vos produits mÃ©nagers vous-mÃªmes",
                    "lead": "Test de description numÃ©ro 2",
                    "link": "http://google.fr",
                    "picture": "http://placehold.it/300x300"
                },
                {
                    "category": "actu",
                    "title": "Faites vos produits mÃ©nagers vous-mÃªmes",
                    "lead": "Test de description numÃ©ro 2",
                    "link": "http://google.fr",
                    "picture": "http://placehold.it/300x300"
                },
                {
                    "category": "agenda",
                    "title": "Festival Folks",
                    "lead": "FOLKS! est une journÃ©e festive et musicale gratuite dÃ©diÃ©e aux habitants dâ€™Ostwald et dâ€™ailleurs. Parce quâ€™Ostwald aime perpÃ©tuer lâ€™Ã¢me de son cÅ“ur de village, la soirÃ©e invite toutes et tout Ã§a tout Ã§a",
                    "link": "#",
                    "date_start": "",
                    "date_end": "24.06",
                    "date_prefix": "Ã  partir du",
                    "date_suffix": "",
                    "ville": ""
                },
                {
                    "category": "actu",
                    "title": "Faites vos produits mÃ©nagers vous-mÃªmes",
                    "lead": "Test de description numÃ©ro 2",
                    "link": "http://google.fr",
                    "picture": "http://placehold.it/300x300"
                }
            ];
        </script>
        <div id="mega-templates">
            <!--Template Event-->
            <div class="item event">
                <a href="__link__" class="link" title="__title__">
                    <div class="date">
                        <div class="date-sup">
                            <span class="date-prefix">__date_prefix__</span>
                            <span class="date-start">__date_start__</span>
                            <span class="date-suffix">__date_suffix__</span>
                        </div>
                        <div class="date-end">__date_end__</div>
                    </div>
                    <div class="title dotme" data-dot="3">__title__</div>
                    <div class="ville">__ville__</div>
                    <div class="lead dotme" data-dot="7">__lead__</div>
                </a>
            </div>

            <!-- Template actu-->
            <div class="item actu">
                <a href="__link__" class="link" title="__title__">
                    <div class="text">
                        <div class="title dotme" data-dot="3">__title__</div>
                        <div class="lead dotme" data-dot="3">__lead__</div>
                    </div>
                    <div class="picture" style="background-image: url(__picture__)">
                    </div> 
                </a>
            </div>

            <!--Template Mag-->
            <div class="item mag">
                <a href="__link__" class="link" title="__title__">
                    <div class="picture" style="background-image: url(__picture__)">
                    </div>
                    <div class="mag-logo">
                        <div class="mag-text">Mag'</div>
                        <div class="mag-picto"></div>
                    </div>
                    <div class="text">
                        <div class="title dotme" data-dot="3">__title__</div>
                        <div class="lead dotme" data-dot="3">__lead__</div>
                    </div>
                </a>
            </div>
        </div>
        <div class="btn-line">
            <a href="#"  title="Toute l'actualitÃ©">
                <span class="flexbox">
                    <span class="btn-text">Toute l'actu</span>
                    <span class="btn-arrow"></span>
                </span>
            </a>
        </div>
    </div>
</section>
<section id="survey">
    <button class="delete-wi"></button>    
    <h2>Je donne mon avis</h2>
    <div class="survey-subtitle">SANT&eacute; : êtes vous prêt à consulter en télémédecine ?</div>
    <form action="" class="survey-form">

        <div class="form-group">        
            <div class="form-field">
                <div class="form-item">
                    <input name="radios" type="radio" value="1" id="r1">
                    <label for="r1" class="option">Oui</label>
                </div>
                <div class="form-item">
                    <input name="radios" type="radio" value="2" id="r2">
                    <label for="r2" class="option">Non</label>
                </div>
                <div class="form-item">
                    <input name="radios" type="radio" value="3" id="r3">
                    <label for="r3" class="option">Sans opinion</label>
                </div>
            </div>
        </div>
        <button type="submit" class="btn-square--filled--core"><span class="flexbox"><span class="btn-text">Valider</span><span class="btn-arrow"></span></span></button>
    </form>
</section>      <section id="bills">
    <h2>Payer mes factures</h2>
    <form action="" class="toValidate">
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,</p>
        <div class="webform-layout-box">
            <div class="form-group">
                <div class="form-label"><label for="annee">Année<strong class="required">*</strong></label></div>
                <div class="form-field">
                    <input type="number" id="annee" required="" aria-required="true">
                </div>
            </div>

            <div class="form-group">
                <div class="form-label"><label for="bill_id">N° de facture<strong class="required">*</strong></label></div>
                <div class="form-field">
                    <input type="text" id="bill_id" required="" aria-required="true">
                </div>
            </div>
        </div>
        <div class="webform-layout-box">
            <div class="form-group">
                <div class="form-label"><label for="bills_mail">Mail<strong class="required">*</strong></label></div>
                <div class="form-field">
                    <input type="email" id="bills_mail" required="" aria-required="true">
                </div>
            </div>

            <div class="form-group">
                <div class="form-label"><label for="bill_amount">Montant<strong class="required">*</strong></label></div>
                <div class="form-field">
                    <input type="number" value="1000.00" min="0" step="0.01" id="bill_amount" name="bill_amount" />
                </div>
            </div>
        </div>
        <button type="submit" class="btn-square--filled--core"><span class="flexbox"><span class="btn-text">Valider</span><span class="btn-arrow"></span></span></button>
    </form>
</section>