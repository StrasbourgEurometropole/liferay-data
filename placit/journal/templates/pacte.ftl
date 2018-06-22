<!-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<!-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<!-- Recuperation du gestionnaire de fichiers Liferay -->
<#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />

<div class="pro-page-pacte">
    <div class="container">

        <div class="row">

            <div class="col-xs-12 pro-wrapper-title">
                <h1><span>Le</span>Pacte</h1>
                <span class="pro-surtitre">Nul n'est censé l'ignorer</span>
            </div>

            <div class="pro-wrapper-content">

                <div class="col-xs-12">
                    <div id="breadcrumb">
                        <span>
                            <span>
                                <a href="${homeURL}">Accueil</a>
                                <span class="breadcrumb_last">Le Pacte Citoyen</span>
                            </span>
                        </span>
                    </div>
                </div>

                <div class="col-sm-6">
                    <div class="pro-preambule">
                        <h2 class="pro-title">Préambule</h2>
                        <p>Le présent pacte pour la démocratie à Strasbourg s’inscrit dans la continuité d’une dynamique de participation citoyenne engagée de longue date.</p>
                        <p>Initié et décidé lors du Sommet citoyen d’avril 2017, le pacte pour la démocratie à Strasbourg est l’aboutissement d’une démarche expérimentale et originale. Il résulte
                            d’une co-construction entre citoyens-nes, élus-es et agents-es de la collectivité. Il reflète les principes directeurs qui permettent la mise en œuvre des propositions
                            annexées au présent pacte et réalisées selon le calendrier concerté entre les citoyens-nes et le Maire le 15 mars 2018.</p>
                        <figure class="fit-cover" role="group">
                            <img src="${image.getData()}" width="546" height="170" alt="${image.getAttribute("alt")}"/>
                        </figure>
                        <h3>Un dialogue fondé sur la reconnaissance, la bienveillance et l’écoute réciproques</h3>
                        <p>Le pacte garantit le dialogue entre élus-es, agents-es et citoyens-nes ; principe fondamental de la démocratie locale qui seul permet d’aboutir à la prise de décision
                            commune.</p>
                        <p>Ce dialogue repose sur la reconnaissance, la bienveillance et l’écoute réciproques ainsi que sur la reconnaissance de valeurs communes.</p>
                    </div>
                </div>

                <div class="col-sm-6">
                    <div class="pro-annexes">
                        <h3>Annexes à télécharger</h3>
                        <ul>
                            <#if files?has_content>
                                <#list files.getSiblings() as file>

                                    <#assign fileEntry = fileEntryHelper.getFileEntryByRelativeURL(file.getData()) />    
                                    <#assign title = fileEntryHelper.getFileTitle(fileEntry.getFileEntryId(), locale) />
                                    <#assign size = fileEntryHelper.getReadableFileEntrySize(fileEntry.getFileEntryId(), locale) />

                                    <li>
                                        <a href="${file.getData()}" download title="${title}">
                                            ${title}
                                            <span class="pro-poids">Poids ${size}</span>
                                        </a>
                                    </li>

                                </#list>
                            <#else>
                                Aucune annexe associée pour le moment.
                            </#if>
                        </ul>
                    </div>

                </div>
            </div>


            <div class="pro-wrapper-pacte">
                <div>
                    <div class="pro-wrapper">
                        <span class="pro-big-letter">P</span>
                        <h2>Valeurs</h2>
                        <h3>Liberté, Egalité, Fraternité</h3>
                        <p>La démocratie à Strasbourg est ancrée dans les valeurs de la République, incarnées par sa devise « Liberté, Egalité, Fraternité » et garanties par la Constitution.</p>
                    </div>

                    <div class="pro-wrapper">
                        <span class="pro-big-letter pro-big-letter-a">A</span>
                        <h2>Principes</h2>
                        <h3>Fraternité</h3>
                        <p>Afin de construire une société accueillante et ouverte, le pacte consacre la fraternité comme ciment de la diversité, du pluralisme et de la mixité.</p>
                        <p>La fraternité repose sur la solidarité entre toutes et tous.</p>

                        <h3>Respect et transparence</h3>
                        <p>Le pacte est établi sur le respect et la transparence qui favorisent la confiance entre élus-es, agents-es et citoyens-nes afin de mobiliser les énergies au service de
                            l’intérêt général et du bien commun.</p>
                        <p>La confiance se construit sur la loyauté et la sincérité de chacune et chacun, et le respect des décisions communes.</p>

                        <h3>L’éducation comme processus d’humanisation</h3>
                        <p>Le pacte promeut l’éducation en tant que processus d’humanisation et de développement de compétences, de connaissances et de valeurs qui fondent l’unité de la société
                            dans le respect de sa diversité.</p>
                    </div>
                </div>

                <div>
                    <div class="pro-wrapper">
                        <span class="pro-big-letter">C</span>
                        <h2>Engagements</h2>
                        <h3>Un engagement mutuel</h3>
                        <p>Le pacte pour la démocratie locale implique notre engagement mutuel, élus-es, agents-es et citoyen-nes de Strasbourg, pour l’intérêt général et le bien commun.</p>

                        <h3>Une implication durable</h3>
                        <p>Pour construire la relation de confiance entre élus-es et citoyens-nes, le pacte suppose l’implication durable de chacune et chacun.</p>

                        <h3>La responsabilité de chacune et chacun</h3>
                        <p>Le pacte implique une éthique de la responsabilité de chacune et chacun.</p>
                        <p>La responsabilité repose sur la prise de conscience de notre capacité à réfléchir et à agir, du poids de nos paroles et des conséquences de nos actes, ainsi que des
                            limites de notre liberté individuelle.</p>
                        <p>L’éthique renvoie à la responsabilité de l’environnement dans lequel nous vivons pour garantir aux générations futures une planète viable.</p>
                    </div>

                    <div class="pro-wrapper">
                        <span class="pro-big-letter pro-big-letter-t">T</span>
                        <h2>Effectivité</h2>
                        <p>Adopté en conseil municipal, le pacte a force obligatoire.</p>
                        <p>Le comité d’éthique du pacte pour la démocratie à Strasbourg est chargé d’en assurer le respect.</p>
                        <p>Les citoyens-nes et le conseil municipal sont garants de sa mise en oeuvre.</p>
                    </div>

                    <span class="pro-big-letter pro-big-letter-e">E</span>
                </div>
            </div>


            <div class="pro-bloc-accordion" style="margin-bottom: 0px">
                <div class="col-xs-12"><h2>Les articles du Pacte</h2></div>

                <div class="panel-group col-sm-9" id="accordion" role="tablist" aria-multiselectable="true">
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingOne">
                            <h3 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne" title="Nom de l'article">
                                    Article 1 : le droit de participer
                                </a>
                            </h3>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                            <div class="panel-body">
                                <p>Le pacte garantit le droit pour les citoyens-nes d’agir dans les domaines relevant des compétences de la Ville.</p>
                                <h4>Le droit de pétition citoyenne</h4>
                                <p>Le pacte reconnaît le droit pour les citoyens-nes de déposer une pétition.</p>
                                <p>Si celle-ci recueille le nombre de signatures requis, elle fera l’objet d’un débat et d’un vote en conseil municipal.</p>
                                <p>Les modalités de mise en œuvre des pétitions citoyennes feront l’objet d’une délibération co-construite avec les citoyens-nes dans le cadre du Sommet
                                    citoyen.</p>
                                <h4>Le droit à un budget participatif</h4>
                                <p>La Ville s’engage à mettre en place un budget participatif permettant la réalisation de projets d’initiative citoyenne.</p>
                                <p>Les modalités de mise en œuvre du budget participatif feront l’objet d’une délibération co-construite avec les citoyens-nes dans le cadre du Sommet citoyen.</p>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingTwo">
                            <h3 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo" title="Nom de l'article">
                                    Article 2 : le droit d’agir
                                </a>
                            </h3>
                        </div>
                        <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                            <div class="panel-body">
                                <p>Le pacte garantit le droit pour les citoyens-nes d’agir dans les domaines relevant des compétences de la Ville.</p>
                                <h4>Le droit de pétition citoyenne</h4>
                                <p>Le pacte reconnaît le droit pour les citoyens-nes de déposer une pétition.</p>
                                <p>Si celle-ci recueille le nombre de signatures requis, elle fera l’objet d’un débat et d’un vote en conseil municipal.</p>
                                <p>Les modalités de mise en œuvre des pétitions citoyennes feront l’objet d’une délibération co-construite avec les citoyens-nes dans le cadre du Sommet
                                    citoyen.</p>
                                <h4>Le droit à un budget participatif</h4>
                                <p>La Ville s’engage à mettre en place un budget participatif permettant la réalisation de projets d’initiative citoyenne.</p>
                                <p>Les modalités de mise en œuvre du budget participatif feront l’objet d’une délibération co-construite avec les citoyens-nes dans le cadre du Sommet citoyen.</p>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingThree">
                            <h3 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree" title="Nom de l'article">
                                    Article 3 : le droit d’initiative citoyenne
                                </a>
                            </h3>
                        </div>
                        <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                            <div class="panel-body">
                                <p>Le pacte garantit le droit pour les citoyens-nes d’agir dans les domaines relevant des compétences de la Ville.</p>
                                <h4>Le droit de pétition citoyenne</h4>
                                <p>Le pacte reconnaît le droit pour les citoyens-nes de déposer une pétition.</p>
                                <p>Si celle-ci recueille le nombre de signatures requis, elle fera l’objet d’un débat et d’un vote en conseil municipal.</p>
                                <p>Les modalités de mise en œuvre des pétitions citoyennes feront l’objet d’une délibération co-construite avec les citoyens-nes dans le cadre du Sommet
                                    citoyen.</p>
                                <h4>Le droit à un budget participatif</h4>
                                <p>La Ville s’engage à mettre en place un budget participatif permettant la réalisation de projets d’initiative citoyenne.</p>
                                <p>Les modalités de mise en œuvre du budget participatif feront l’objet d’une délibération co-construite avec les citoyens-nes dans le cadre du Sommet citoyen.</p>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingFour">
                            <h3 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour" title="Nom de l'article">
                                    Article 4 : le droit à l’accès au numérique
                                </a>
                            </h3>
                        </div>
                        <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
                            <div class="panel-body">
                                <p>Le pacte garantit le droit pour les citoyens-nes d’agir dans les domaines relevant des compétences de la Ville.</p>
                                <h4>Le droit de pétition citoyenne</h4>
                                <p>Le pacte reconnaît le droit pour les citoyens-nes de déposer une pétition.</p>
                                <p>Si celle-ci recueille le nombre de signatures requis, elle fera l’objet d’un débat et d’un vote en conseil municipal.</p>
                                <p>Les modalités de mise en œuvre des pétitions citoyennes feront l’objet d’une délibération co-construite avec les citoyens-nes dans le cadre du Sommet
                                    citoyen.</p>
                                <h4>Le droit à un budget participatif</h4>
                                <p>La Ville s’engage à mettre en place un budget participatif permettant la réalisation de projets d’initiative citoyenne.</p>
                                <p>Les modalités de mise en œuvre du budget participatif feront l’objet d’une délibération co-construite avec les citoyens-nes dans le cadre du Sommet citoyen.</p>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingFive">
                            <h3 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive" title="Nom de l'article">
                                    Article 5 : l’Europe de Strasbourg
                                </a>
                            </h3>
                        </div>
                        <div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
                            <div class="before"></div>
                            <div class="panel-body">
                                <p>Le pacte garantit le droit pour les citoyens-nes d’agir dans les domaines relevant des compétences de la Ville.</p>
                                <h4>Le droit de pétition citoyenne</h4>
                                <p>Le pacte reconnaît le droit pour les citoyens-nes de déposer une pétition.</p>
                                <p>Si celle-ci recueille le nombre de signatures requis, elle fera l’objet d’un débat et d’un vote en conseil municipal.</p>
                                <p>Les modalités de mise en œuvre des pétitions citoyennes feront l’objet d’une délibération co-construite avec les citoyens-nes dans le cadre du Sommet
                                    citoyen.</p>
                                <h4>Le droit à un budget participatif</h4>
                                <p>La Ville s’engage à mettre en place un budget participatif permettant la réalisation de projets d’initiative citoyenne.</p>
                                <p>Les modalités de mise en œuvre du budget participatif feront l’objet d’une délibération co-construite avec les citoyens-nes dans le cadre du Sommet citoyen.</p>
                            </div>
                            <div class="after"></div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingSix">
                            <h3 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSix" aria-expanded="false" aria-controls="collapseSix" title="Nom de l'article">
                                    Article 6 : les actrices et les acteurs du pacte
                                </a>
                            </h3>
                        </div>
                        <div id="collapseSix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">
                            <div class="panel-body">
                                <p>Le pacte garantit le droit pour les citoyens-nes d’agir dans les domaines relevant des compétences de la Ville.</p>
                                <h4>Le droit de pétition citoyenne</h4>
                                <p>Le pacte reconnaît le droit pour les citoyens-nes de déposer une pétition.</p>
                                <p>Si celle-ci recueille le nombre de signatures requis, elle fera l’objet d’un débat et d’un vote en conseil municipal.</p>
                                <p>Les modalités de mise en œuvre des pétitions citoyennes feront l’objet d’une délibération co-construite avec les citoyens-nes dans le cadre du Sommet
                                    citoyen.</p>
                                <h4>Le droit à un budget participatif</h4>
                                <p>La Ville s’engage à mettre en place un budget participatif permettant la réalisation de projets d’initiative citoyenne.</p>
                                <p>Les modalités de mise en œuvre du budget participatif feront l’objet d’une délibération co-construite avec les citoyens-nes dans le cadre du Sommet citoyen.</p>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingSeven">
                            <h3 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSeven" aria-expanded="false" aria-controls="collapseSeven" title="Nom de l'article">
                                    Article 7 : exécution du pacte
                                </a>
                            </h3>
                        </div>
                        <div id="collapseSeven" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSeven">
                            <div class="panel-body">
                                <p>Le pacte garantit le droit pour les citoyens-nes d’agir dans les domaines relevant des compétences de la Ville.</p>
                                <h4>Le droit de pétition citoyenne</h4>
                                <p>Le pacte reconnaît le droit pour les citoyens-nes de déposer une pétition.</p>
                                <p>Si celle-ci recueille le nombre de signatures requis, elle fera l’objet d’un débat et d’un vote en conseil municipal.</p>
                                <p>Les modalités de mise en œuvre des pétitions citoyennes feront l’objet d’une délibération co-construite avec les citoyens-nes dans le cadre du Sommet
                                    citoyen.</p>
                                <h4>Le droit à un budget participatif</h4>
                                <p>La Ville s’engage à mettre en place un budget participatif permettant la réalisation de projets d’initiative citoyenne.</p>
                                <p>Les modalités de mise en œuvre du budget participatif feront l’objet d’une délibération co-construite avec les citoyens-nes dans le cadre du Sommet citoyen.</p>
                            </div>
                        </div>
                    </div>

                </div>

            </div>

        </div>
    </div>
</div>