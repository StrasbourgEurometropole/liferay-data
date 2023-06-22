<!-- Recuperation de la localisation de l'utilisateur -->
<#setting locale=locale />

<!-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL="/web${layout.group.friendlyURL}/" />
    <#else>
        <#assign homeURL="/" />
</#if>

<!-- Recuperation du gestionnaire de fichiers Liferay -->
<#assign fileEntryHelper=serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />

<div class="pro-page-pacte">
    <div class="pro-wrapper-title">
        <div class="container">
            <div class="pro-pacte-title">
                <div id="breadcrumb">
                    <span>
                        <span>
                            <a href="${homeURL}">Accueil</a>
                            <span class="breadcrumb_last">Le Pacte Citoyen</span>
                        </span>
                    </span>
                </div>
                <h1>Le Pacte</h1>
                <span>Nul n'est censé l'ignorer</span>

            </div>


        </div>
    </div>
    <div class="pro-wrapper-content">
    <div class="container">
            <div class="row">
                <div class="col-md-6 pro-annexes">
                    <div>
                        <h2 class="pro-title">Annexes à télécharger</h2>
                        <ul>
                            <#list files.getSiblings() as file>

                                <#if file.getData()?has_content>

                                    <#assign fileEntry=fileEntryHelper.getFileEntryByRelativeURL(file.getData()) />
                                    <#assign title=fileEntryHelper.getFileTitle(fileEntry.getFileEntryId(), locale) />
                                    <#assign size=fileEntryHelper.getReadableFileEntrySize(fileEntry.getFileEntryId(),
                                        locale) />

                                    <li>
                                        <a href="${file.getData()}" download title="${title}">
                                            <span>${title}</span>
                                            <div>
                                                <span>Telecharger</span>
                                                <span class="pro-file-size">(PDF - ${size})</span>
                                            </div>
                                        </a>
                                    </li>

                                </#if>

                            </#list>
                        </ul>
                    </div>

                </div>
                <div class="col-md-6 order-1 order-md-12 ">
                    <div class="pro-preambule">
                        <h2 class="pro-title">Préambule</h2>
                        <p>Le présent pacte pour la démocratie à Strasbourg s’inscrit dans la continuité d’une dynamique
                            de participation citoyenne engagée de longue date.</p>
                        <p>Initié et décidé lors du Sommet citoyen d’avril 2017, le pacte pour la démocratie à
                            Strasbourg est l’aboutissement d’une démarche expérimentale et originale. Il résulte
                            d’une co-construction entre citoyens-nes, élus-es et agents-es de la collectivité. Il
                            reflète les principes directeurs qui permettent la mise en œuvre des propositions
                            annexées au présent pacte et réalisées selon le calendrier concerté entre les citoyens-nes
                            et le Maire le 15 mars 2018.</p>
                        <figure class="fit-cover" role="group">
                            <img src="${image.getData()}" width="546" height="170" alt="${image.getAttribute('alt')}" />
                        </figure>
                        <h3>Un dialogue fondé sur la reconnaissance, la bienveillance et l’écoute réciproques</h3>
                        <p>Le pacte garantit le dialogue entre élus-es, agents-es et citoyens-nes ; principe fondamental
                            de la démocratie locale qui seul permet d’aboutir à la prise de décision
                            commune.</p>
                        <p>Ce dialogue repose sur la reconnaissance, la bienveillance et l’écoute réciproques ainsi que
                            sur la reconnaissance de valeurs communes.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="pro-wrapper-pacte">
        <div class="container">
            <div class="row pt-3">
                <div class="col-md-6">
                    <div class="pro-wrapper">
                        <h2>Valeurs</h2>
                        <h3>Liberté, Egalité, Fraternité</h3>
                        <p>La démocratie à Strasbourg est ancrée dans les valeurs de la République, incarnées par sa
                            devise « Liberté, Egalité, Fraternité » et garanties par la Constitution.</p>
                    </div>

                    <div class="pro-wrapper">
                        <h2>Principes</h2>
                        <h3>Fraternité</h3>
                        <p>Afin de construire une société accueillante et ouverte, le pacte consacre la fraternité comme
                            ciment de la diversité, du pluralisme et de la mixité.</p>
                        <p>La fraternité repose sur la solidarité entre toutes et tous.</p>

                        <h3>Respect et transparence</h3>
                        <p>Le pacte est établi sur le respect et la transparence qui favorisent la confiance entre
                            élus-es, agents-es et citoyens-nes afin de mobiliser les énergies au service de
                            l’intérêt général et du bien commun.</p>
                        <p>La confiance se construit sur la loyauté et la sincérité de chacune et chacun, et le respect
                            des décisions communes.</p>

                        <h3>L’éducation comme processus d’humanisation</h3>
                        <p>Le pacte promeut l’éducation en tant que processus d’humanisation et de développement de
                            compétences, de connaissances et de valeurs qui fondent l’unité de la société
                            dans le respect de sa diversité.</p>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="pro-wrapper">
                        <h2>Engagements</h2>
                        <h3>Un engagement mutuel</h3>
                        <p>Le pacte pour la démocratie locale implique notre engagement mutuel, élus-es, agents-es et
                            citoyen-nes de Strasbourg, pour l’intérêt général et le bien commun.</p>

                        <h3>Une implication durable</h3>
                        <p>Pour construire la relation de confiance entre élus-es et citoyens-nes, le pacte suppose
                            l’implication durable de chacune et chacun.</p>

                        <h3>La responsabilité de chacune et chacun</h3>
                        <p>Le pacte implique une éthique de la responsabilité de chacune et chacun.</p>
                        <p>La responsabilité repose sur la prise de conscience de notre capacité à réfléchir et à agir,
                            du poids de nos paroles et des conséquences de nos actes, ainsi que des
                            limites de notre liberté individuelle.</p>
                        <p>L’éthique renvoie à la responsabilité de l’environnement dans lequel nous vivons pour
                            garantir aux générations futures une planète viable.</p>
                    </div>

                    <div class="pro-wrapper">
                        <h2>Effectivité</h2>
                        <p>Adopté en conseil municipal, le pacte a force obligatoire.</p>
                        <p>Le comité d’éthique du pacte pour la démocratie à Strasbourg est chargé d’en assurer le
                            respect.</p>
                        <p>Les citoyens-nes et le conseil municipal sont garants de sa mise en oeuvre.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
        <div class="pro-bloc-accordion" style="margin-bottom: 0px">
        <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-10 col-md-12">
                        <div>
                            <h2>Les articles du Pacte</h2>
                        </div>
                        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

                            <#list articleTitle.getSiblings() as cur_title>

                                <div class="panel panel-default">
                                    <a role="button" data-toggle="collapse" data-parent="#accordion"
                                        href="#collapse${cur_title?index}" aria-expanded="false"
                                        aria-controls="collapse${cur_title?index}" title="Nom de l'article"
                                        class="panel-heading" role="tab" id="heading${cur_title?index}">
                                        <h3 class="panel-title">
                                            <span>
                                                ${cur_title.getData()}
                                            </span>
                                        </h3>
                                    </a>
                                    <div id="collapse${cur_title?index}" class="panel-collapse collapse" role="tabpanel"
                                        aria-labelledby="heading${cur_title?index}">
                                        <div class="panel-body">
                                            ${cur_title.getChild("articleContent").getData()}
                                        </div>
                                    </div>
                                </div>

                            </#list>

                        </div>
                    </div>

                </div>


            </div>
            </div>
    </div>
</div>