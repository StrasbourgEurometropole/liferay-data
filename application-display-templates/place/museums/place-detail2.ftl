<!-- Détail lieu -->
<#setting locale = locale />
<#setting date_format="d MMMM yyyy">
        <div class="text-box">

            <h1>${entry.getAlias(locale)}</h1>

            <p>
              <#if entry.addressStreet?has_content>
                ${entry.addressStreet} -
              </#if>
              <#if entry.addressComplement?has_content>
                ${entry.addressComplement} -
              </#if>
              <#if entry.addressDistribution?has_content>
                ${entry.addressDistribution} -
              </#if>
              ${entry.addressZipCode} ${entry.getCity(locale)}
            </p>

            <#if entry.phone?has_content>
              <p>
                <strong><@liferay_ui.message key="phone" /></strong><a class="phone" href="${entry.phone}"><strong> ${entry.phone}</strong></a>
              </p>
            </#if>

            <#if entry.mail?has_content || (entry.getFacebookLabel(locale)?has_content && entry.getFacebookURL(locale)?has_content) || (entry.getSiteLabel(locale)?has_content && entry.getSiteURL(locale)?has_content)>
              <div class="align-lign">
                <#if entry.mail?has_content>
                    <a href="#contact-form-section" class="link">
                        <strong><@liferay_ui.message key="contact" /></strong>
                    </a> 
                </#if>
                <#if entry.getFacebookLabel(locale)?has_content && entry.getFacebookURL(locale)?has_content >
                    <a href="${entry.getFacebookURL(locale)}" title="entry.getFacebookLabel(locale)(<@liferay_ui.message key="eu.new-window" />)" target="_blank" class="link">
                        <strong><@liferay_ui.message key="facebook" /></strong>
                    </a> 
                </#if>
                <#if entry.getSiteLabel(locale)?has_content && entry.getSiteURL(locale)?has_content>
                    <a href="${entry.getSiteURL(locale)}" title="${entry.getSiteLabel(locale)} (<@liferay_ui.message key="eu.new-window" />)" target="_blank" class="link">
                        <strong>${entry.getSiteLabel(locale)}</strong>
                    </a>  
                </#if>
              </div>
            </#if>

        </div>

        <div class="container-fluid">

            <div class="row entete-cols">

                <!--<div class="col-width-8">
                    <div class="google-map" data-zoom="13">
                        <div class="marker" data-lat="48.585259" data-lng="7.764706" data-icon="img/design/gmap-markers.png">
                            <strong>${entry.getAlias(locale)}</strong><br/>
                                <#if entry.addressStreet?has_content>
                                    ${entry.addressStreet}<br>
                                </#if>
                                <#if entry.addressComplement?has_content>
                                    ${entry.addressComplement}<br>
                                </#if>
                                <#if entry.addressDistribution?has_content>
                                    ${entry.addressDistribution}<br>
                                </#if>
                                ${entry.addressZipCode} ${entry.getCity(locale)}
                        </div>
                    </div>
                </div>-->

                <div class="col-width-3">
                    <div class="figure" style="background-image: url('${entry.getImageURL()}}');"></div>
                </div>

            </div>

            <div class="row">

                <div class="col-width-8">

                    <!-- Widget Bloc Texte -->
                    <div class="widget-item widget-type-text-no-border">

                        <#if entry.getAccess(locale)?has_content >
                            <span class="htitle"><@liferay_ui.message key="access" /></span>
                            <p>${entry.getAccess(locale)}</p>
                        </#if>

                        <#if entry.getCharacteristics(locale)?has_content >
                            <span class="htitle"><@liferay_ui.message key="eu.confort-and-equipment" /></span>
                            <p>${entry.getCharacteristics(locale)}</p>
                        </#if>

                        <#if entry.getServiceAndActivities(locale)?has_content >
                            <span class="htitle"><@liferay_ui.message key="eu.services-and-activities" /></span>
                            <p>${entry.getServiceAndActivities(locale)}</p>
                        </#if>

                        <#if entry.hasAnyAccessForDisabled() || entry.getAccessForDisabled(locale)?has_content >
                            <span class="htitle"><@liferay_ui.message key="eu.access-for-disabled" /></span>
                            <p>${entry.getAccessForDisabled(locale)}</p>
                              
                            <#if entry.hasAnyAccessForDisabled() >
                                <div>
                                    <#if entry.accessForWheelchair>
                                        <span class="picto-handicap-moteur"><@liferay_ui.message key="eu.access-for-wheelchair" /></span>
                                    </#if>
                                    <#if entry.accessForBlind>
                                        <span class="picto-handicap-aveugle"><@liferay_ui.message key="eu.access-for-blind" /></span>
                                    </#if>
                                    <#if entry.accessForDeaf>
                                        <span class="picto-handicap-sourd"><@liferay_ui.message key="eu.access-for-deaf" /></span>
                                    </#if>
                                    <#if entry.accessForElder>
                                        <span class="picto-handicap-mobilite-reduite"><@liferay_ui.message key="eu.access-for-elder" /></span>
                                    </#if>
                                    <#if entry.accessForDeficient>
                                        <span class="picto-handicap-mental"><@liferay_ui.message key="eu.access-for-deficient" /></span>
                                    </#if>
                                </div>
                            </#if>
                        </#if>

                    </div>

                </div>

                <div class="col-width-3">

                    <div class="aside">

                        <!-- Widget Bloc Texte -->
                        <div class="widget-item widget-type-text">

                            <h4>
                                <span><@liferay_ui.message key="eu.times" /></span>
                                <!-- <a href="#"><@liferay_ui.message key="eu.all-times" /></a>-->
                            </h4>

                            <div class="list-horaires">
	                            <#assign horaires = entry.getHoraire(.now) />
	                            <#list horaires?keys as jour>
                                    <div class="line">
	                                    <span class="name">
	                                        <@liferay_ui.message key="jour-semaine${jour}" />
	                                    </span>
	                                    <span class="time">
	                                        <#assign placeSchedule = horaires[jour] />
	                                        <#list placeSchedule?keys as type >
	                                            <#if type == "schedule" && placeSchedule[type]?has_content >
	                                                <@liferay_ui.message key="${placeSchedule[type]}" />
	                                            <#else>
	                                                <#if type?counter == 3 || type?counter == 5 >
	                                                    <br>
	                                                </#if> 
	                                                <#if type?matches('.from') && placeSchedule[type]?has_content >
	                                                    <@liferay_ui.message key="eu.from" /> ${placeSchedule[type]}
	                                                </#if>
	                                                <#if type?matches('.to') && placeSchedule[type]?has_content >
	                                                    <@liferay_ui.message key="eu.to" /> ${placeSchedule[type]}
	                                                </#if>                                  
	                                            </#if>
	                                        </#list>
	                                    </span>
	                                  </div>
	                            </#list>
                            </div>

                            <#if entry.getExceptionalSchedule(locale)?has_content >
                                <p><strong><@liferay_ui.message key="exceptional-schedule" /></strong></p>
                                <p>${entry.getExceptionalSchedule(locale)}</p>
                            </#if>
                        </div>

                        <!-- Widget Bloc Texte -->
                        <div class="widget-item widget-type-text">

                            <h4>
                                <span><@liferay_ui.message key="prices" /></span>
                                <!--<a href="#"><@liferay_ui.message key="all-prices" /></a>-->
                            </h4>

                            <#if entry.getPrice()?has_content >
                                <p>${entry.getPrice().getPrice(locale)}</p>
                            </#if>

                        </div>

                        <!-- Widget Bloc Lire aussi -->
                        <div class="widget-item widget-type-links">

                            <h4><span><@liferay_ui.message key="read-too" /></span></h4>

                            <ul class="sidebar">
                                <#list entry.getDocumentURLs() as doc>
                                    <li class="item"><a href="${doc}" >${doc}</a></li>
                                </#list>
                            </ul>

                        </div>

                    </div>

                </div>

            </div>

            <!-- Widget Bloc Contenus associés -->
            <div class="widget-item widget-type-associe">

                <h4>
                    <span><@liferay_ui.message key="related-content" /></span>
                </h4>

                <div class="owl-carousel limit-to-height">
                    <#list entry.getDocumentURLs() as doc>
	                    <div class="type-image item">
	                        <figure>
	                            <a href="photos/contenu-1.jpg" data-text="Agrandir" class="zoombox">
	                                <img src="photos/contenu-1.jpg" alt="" />
	                            </a>
	                        </figure>
	                        <span class="title-img">Titre de l'image 1</span>
	                    </div>
                    </#list>

                </div>

            </div>

            <!-- Widget Bloc Agenda -->
            <div class="widget-item widget-type-agenda">

                <h4>
                    <span><@liferay_ui.message key="eu.agenda-and-exposition" /></span>
                </h4>

                <div class="owl-carousel widget" data-cols="4">
                    <#list entry.getDocumentURLs() as doc>
	                    <article class="type-event item" itemscope itemtype="http://schema.org/Article">
	                        <figure>
	                            <a href="#">
	                                <img src="photos/zoologique-1.jpg" alt="" />
	                            </a>
	                            <span class="credits">Crédits de la photographie</span>
	                        </figure>

	                        <header>
	                            <time datetime="2016-07-24">24 juillet 2016</time>
	                            <span class="category">Ateliers</span>
	                            <h5><a href="#">Titre d'une actualité</a></h5>
	                        </header>

	                        <div class="entry-content" itemprop="text">
	                            <p>facilisi lacus ornare ante, ac egestas est urna sit amet arcu. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.</p>
	                        </div>
	                    </article>
                    </#list>
                </div>

            </div>

        </div>

    </article>

</div>