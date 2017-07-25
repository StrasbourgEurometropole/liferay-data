<!DOCTYPE html>
<#include init />
<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
  <title>Accueil - Semaine de l'Entrepreneur Européen</title>
  <link rel="shortcut icon" href="/o/see-theme/images/favicon.ico">
  <link rel="apple-touch-icon" href="/o/see-theme/images/apple-touch-icon.jpg">
  <link rel="apple-touch-icon" sizes="72x72" href="/o/see-theme/images/apple-touch-icon-72x72.jpg">
  <link rel="apple-touch-icon" sizes="114x114" href="/o/see-theme/images/apple-touch-icon-114x114.jpg">
  <link rel="stylesheet" type="text/css" href="/o/see-theme/css/custom-animations.css" />

  <@liferay_util["include"] page=top_head_include />
</head>
<body class="${css_class}">
  <@liferay_ui["quick-access"] contentId="#main-content" />
  <@liferay_util["include"] page=body_top_include />
  <@liferay.control_menu />

  <div class="see">
    <!-- Header langue -->
    <nav class="nav-lang">
      <div class="center">
        <a href="http://www.europtimist.eu/" title="Strasbourg.eu (nouvelle fenêtre)" class="logo-strasbourg" target="_blank"> <img src="/o/see-theme/images/logo_strasbourg.png" alt="Strasbourg.eu"> </a> 
        <ul>
          <li class="active"><a accesskey="1" href="http://www.strasbourgaimesesetudiants.eu/accueil?p_p_id=82&amp;p_p_lifecycle=1&amp;p_p_state=normal&amp;p_p_mode=view&amp;_82_struts_action=%2Flanguage%2Fview&amp;languageId=fr_FR" title="Français">FR</a></li>
          <li><a accesskey="1" href="http://www.strasbourgaimesesetudiants.eu/accueil?p_p_id=82&amp;p_p_lifecycle=1&amp;p_p_state=normal&amp;p_p_mode=view&amp;_82_struts_action=%2Flanguage%2Fview&amp;languageId=de_DE" title="Deutsch">DE</a></li>
        </ul>
        <div class="clearfix"></div>
      </div>
    </nav>

    <section id="content">
      <#if selectable>
        <@liferay_util["include"] page=content_include />
      <#else>
        ${portletDisplay.recycle()}
        ${portletDisplay.setTitle(the_title)}
        <@liferay_theme["wrap-portlet"] page="portlet.ftl" />
        <@liferay_util["include"] page=content_include />
      </#if>
    </section>

    <!-- Menu -->
    <header class="header header-black">
      <div class="header-wrapper">
        <div class="container">
          <div class="col-sm-2 col-xs-12 navigation-header">
            <a href="#" class="logo">
              <img src="/o/see-theme/images/logo-SEE.png" alt="VentCamp" width="119" class="retina-hide">
              <img src="/o/see-theme/images/ventcamp_logo@2x.png" alt="SEE" class="retina-show">
            </a>
            <button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navigation" aria-expanded="false" aria-controls="navigation">
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
          </div>
          <div class="col-sm-10 col-xs-12 navigation-container">
            <div id="navigation" class="navbar-collapse collapse">
              <ul class="navigation-list pull-left light-text">
                <li class="navigation-item"><a href="#presentation" class="navigation-link">Présentation</a></li>
                <li class="navigation-item"><a href="#schedule" class="navigation-link">Programme</a></li>
                <li class="navigation-item"><a href="#testimoniaux" class="navigation-link">Testimoniaux</a></li>
                <li class="navigation-item"><a href="#gallery" class="navigation-link">Galerie</a></li>
                <li class="navigation-item"><a href="#partenaires" class="navigation-link">Partenaires</a></li>
                <li class="navigation-item"><a href="#medias" class="navigation-link">Médias</a></li>
              </ul>
              <a href="#" class="pull-right buy-btn">Programme PDF</a>
            </div>
          </div>
        </div>
      </div>
    </header>

    <!-- Présentation -->
    <section id=presentation class="section align-center">
      <div class="container">
        <h3>Présentation</h3>
        <p class="text-alt"> La Semaine de l'entrepreneur européen, c'est 
          une semaine pour trouver toutes les réponses à la création / reprise d'entreprises sur le territoire de Strasbourg – Ortenau.
        </p>
        <br />
        <div class="col-sm-6">
          <article>
            <h6><span class="highlight">Regular</span> Paragraph</h6>
            <p class="align-left">L'axe "Entrepreneuriat" de la feuille de route Strasbourg éco 2030 couvre la promotion de l'entrepreneuriat. A ce titre, la Semaine de l'entrepreneur européen est une formidable opportunité pour fêter et promouvoir l'entrepreneuriat pour toutes les cibles d'entrepreneurs ; qu'il s'agisse de femmes, d'étudiants, de jeunes, d'entrepreneurs motivés par la création d'une entreprise sociale ou d'une start-up.</p>
          </article>
        </div>
        <div class="col-sm-6">
          <article>
            <p class="align-left">Pour la 6ème année consécutive, la Semaine est portée par l'Eurodistrict Strasbourg-Ortenau et l'Eurométropole de Strasbourg. Adossée au mouvement mondial "Global Entrepreneurship Week", les 16 évènements de la Semaine ont pour objectifs de valoriser la création d'entreprise ; permettre l'accès à l'information et au conseil pour les créateurs / repreneurs; favoriser les contacts informels et les coopérations ; contribuer à l'innovation et à la compétitivité de notre tissu économique…</p>
          </p>
        </article>
      </div>
    </div>
  </section>

  <!-- Chiffres clés-->
  <section id="counters" class="section align-center overlay bg-cover bg5 light-text">
    <div class="container">
      <div class="row counters-wrapper">
        <div class="col-sm-3">
          <div class="counter-block counter-block-no-border">
            <div class="counter-box">
              <div class="counter-content">
                <span class="count" data-from="0" data-to="12">0</span>
                <p class="title">lieux</p>
              </div>
            </div>
          </div>
        </div>
        <div class="col-sm-3">
          <div class="counter-block counter-block-no-border">
            <div class="counter-box">
              <div class="counter-content">
                <span class="count" data-from="0" data-to="6">0</span>
                <p class="title">jours</p>
              </div>
            </div>
          </div>
        </div>
        <div class="col-sm-3">
          <div class="counter-block counter-block-no-border">
            <div class="counter-box">
              <div class="counter-content">
                <span class="count" data-from="0" data-to="25">0</span>
                <p class="title">ateliers</p>
              </div>
            </div>
          </div>
        </div>
        <div class="col-sm-3">
          <div class="counter-block counter-block-no-border">
            <div class="counter-box">
              <div class="counter-content">
                <span class="count" data-from="0" data-to="200">0</span>
                <p class="title">conférences</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>

  <!-- Agenda -->
  <section id="schedule" class="section schedule-section align-center">
    <div class="container">
      <span class="icon section-icon icon-office-21"></span>
      <h3>Programme</h3>
      <p class="text-alt">Une semaine dédiée aux entrepreneurs<br/>  <span class="highlight">européens</span></p>
      <br />
      <br />
      <!-- Schedule start -->
      <div class="schedule">
        <!-- Navigation by day start -->
        <ul class="nav nav-schedule">
          <li class="active">
            <a href="#day1" data-toggle="tab">
              <h5 class="highlight">Lundi</h5>
              <p class="text-alt">13/11/2017</p>
            </a>
          </li>
          <li>
            <a href="#day2" data-toggle="tab">
              <h5 class="highlight">Mardi</h5>
              <p class="text-alt">14/11/2017</p>
            </a>
          </li>
          <li>
            <a href="#day3" data-toggle="tab">
              <h5 class="highlight">Mercredi</h5>
              <p class="text-alt">15/11/2017</p>
            </a>
          </li>
          <li>
            <a href="#day4" data-toggle="tab">
              <h5 class="highlight">Jeudi</h5>
              <p class="text-alt">16/11/2017</p>
            </a>
          </li>
          <li>
            <a href="#day5" data-toggle="tab">
              <h5 class="highlight">Vendredi</h5>
              <p class="text-alt">17/11/2017</p>
            </a>
          </li>
          <li>
            <a href="#day5" data-toggle="tab">
              <h5 class="highlight">Samedi</h5>
              <p class="text-alt">18/11/2017</p>
            </a>
          </li>
          <li>
            <a href="#day5" data-toggle="tab">
              <h5 class="highlight">Dimanche</h5>
              <p class="text-alt">19/11/2017</p>
            </a>
          </li>
        </ul>
        <!-- Navigation by day end -->
        <!-- First level content start -->
        <div class="tab-content">
          <!-- Day 1 content start -->
          <div id="day1" class="tab-pane fade active in">
            <!-- Navigation by auditorium start -->
            <ul class="nav nav-schedule">
              <li class="active"><a href="#day1_auditorium1" data-toggle="tab">Auditorium 1</a></li>
              <li><a href="#day1_auditorium2" data-toggle="tab">Auditorium 2</a></li>
              <li><a href="#day1_auditorium3" data-toggle="tab">Auditorium 3</a></li>
            </ul>
            <!-- Navigation by auditorium start -->
            <!-- Second level content start -->
            <div class="tab-content tab-content-schedule">
              <!-- Auditorium 1 content start -->
              <div id="day1_auditorium1" class="tab-pane fade active in">
                <!-- Accordion start -->
                <div class="panel-group" id="day1_auditorium1_timeline">
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <span class="fa fa-cutlery"></span>
                    </div>
                    <a data-toggle="collapse" data-parent="#day1_auditorium1_timeline" href="#day1_auditorium1_time1" class="schedule-item-toggle">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>2:20 PM</strong>
                      <h6 class="title">How to organize great events<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day1_auditorium1_time1" class="panel-collapse collapse in schedule-item-body">
                      <article>
                        <p class="description">Luctus at accumsan eget ut ante. Cras molestie sollicitudin. Ultricies et eros id quisque auctor. Per mus enim ac lorem integer. Erat netus id. Porta enim quis et elementum amet risus volutpat magna nec ac.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper"></div>
                    <a data-toggle="collapse" data-parent="#day1_auditorium1_timeline" href="#day1_auditorium1_time2" class="schedule-item-toggle collapsed">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>5:40 PM</strong>
                      <h6 class="title">Scaling into crowded space<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day1_auditorium1_time2" class="panel-collapse collapse schedule-item-body">
                      <article>
                        <p class="description">Wisi id tristique. Aliquam orci vulputate. Turpis tempor erat at dictum pellentesque. Neque facilisis cras. Venenatis vel posuere sodales scelerisque leo vel nec enim. Blandit nullam sodales lectus nulla lorem penatibus sed nec eget eros.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day1_auditorium1_timeline" href="#day1_auditorium1_time3" class="schedule-item-toggle collapsed">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>7:00 PM</strong>
                      <h6 class="title">Start your business your eyes around the world<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day1_auditorium1_time3" class="panel-collapse collapse schedule-item-body">
                      <article>
                        <p class="description">Integer in eu. Quis id curabitur tellus est enim. Ut est ultrices. Vitae ad ut nunc quisquam interdum libero neque magna nonummy sapien sit. In sit lacus. Maecenas molestie fusce ut feugiat vestibulum dui mauris.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day1_auditorium1_timeline" href="#day1_auditorium1_time4" class="schedule-item-toggle collapsed">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>7:40 PM</strong>
                      <h6 class="title">Passion and people<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day1_auditorium1_time4" class="panel-collapse collapse schedule-item-body">
                      <article>
                        <p class="description">Lorem vel lorem. Nulla ab justo. Scelerisque penatibus eget. Nunc ultrices nec sed lobortis et. Vitae massa massa pede magna sociosqu viverra tempus justo. Et quis ornare nisl mi ligula suscipit ullamcorper massa ante diam.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                </div>
                <!-- Accordion end -->
              </div>
              <!-- Auditorium 1 content end -->
              <!-- Auditorium 2 content start -->
              <div id="day1_auditorium2" class="tab-pane fade in">
                <!-- Accordion start -->
                <div class="panel-group" id="day1_auditorium2_timeline">
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day1_auditorium2_timeline" href="#day1_auditorium2_time1" class="schedule-item-toggle">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>2:20 PM</strong>
                      <h6 class="title">How to organize great events<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day1_auditorium2_time1" class="panel-collapse collapse in schedule-item-body">
                      <article>
                        <p class="description">Et lacus id. Est hac amet arcu porta condimentum. Ligula facilisis porta elit ipsum non. Vulputate phasellus arcu nonummy eget wisi. Ullamcorper magna in. In semper luctus rhoncus nisl et erat id suspendisse duis praesent.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day1_auditorium2_timeline" href="#day1_auditorium2_time2" class="schedule-item-toggle collapsed">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>5:40 PM</strong>
                      <h6 class="title">Scaling into crowded space<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day1_auditorium2_time2" class="panel-collapse collapse schedule-item-body">
                      <article>
                        <p class="description">Integer ipsum neque eget sem velit. In enim in. Vestibulum cursus dui laoreet ac et. Suspendisse sed est proin nisl ligula vitae magna non. Et vestibulum inceptos. Deserunt in eget et vestibulum velit ut nibh.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                </div>
                <!-- Accordion end -->
              </div>
              <!-- Auditorium 2 content end -->
              <!-- Auditorium 3 content start -->
              <div id="day1_auditorium3" class="tab-pane fade in">
                <!-- Accordion start -->
                <div class="panel-group" id="day1_auditorium3_timeline">
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day1_auditorium3_timeline" href="#day1_auditorium3_time1" class="schedule-item-toggle">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>2:20 PM</strong>
                      <h6 class="title">How to organize great events<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day1_auditorium3_time1" class="panel-collapse collapse in schedule-item-body">
                      <article>
                        <p class="description">Bibendum asperiores proin. Voluptates quam lobortis malesuada dui id ipsum commodo a. Justo con nam euismod arcu malesuada. Pede eget duis. Vel ut donec vestibulum nibh ac dictumst non non. Quam tempus elit id ipsum.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                </div>
                <!-- Accordion end -->
              </div>
              <!-- Auditorium 3 content end -->
            </div>
            <!-- Second level content end -->
          </div>
          <!-- Day 1 content end -->
          <!-- Day 2 content start -->
          <div id="day2" class="tab-pane fade in">
            <!-- Navigation by auditorium start -->
            <ul class="nav nav-schedule">
              <li class="active"><a href="#day2_auditorium1" data-toggle="tab">Auditorium 1</a></li>
              <li><a href="#day2_auditorium2" data-toggle="tab">Auditorium 2</a></li>
              <li><a href="#day2_auditorium3" data-toggle="tab">Auditorium 3</a></li>
            </ul>
            <!-- Navigation by auditorium end -->
            <!-- Second level content start -->
            <div class="tab-content tab-content-schedule">
              <!-- Auditorium 1 content start -->
              <div id="day2_auditorium1" class="tab-pane fade active in">
                <!-- Accordion start -->
                <div class="panel-group" id="day2_auditorium1_timeline">
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day2_auditorium1_timeline" href="#day2_auditorium1_time1" class="schedule-item-toggle">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>2:20 PM</strong>
                      <h6 class="title">How to organize great events<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day2_auditorium1_time1" class="panel-collapse collapse in schedule-item-body">
                      <article>
                        <p class="description">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day2_auditorium1_timeline" href="#day2_auditorium1_time3" class="schedule-item-toggle collapsed">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>7:00 PM</strong>
                      <h6 class="title">Close your eyes around the world<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day2_auditorium1_time3" class="panel-collapse collapse schedule-item-body">
                      <article>
                        <p class="description">Justo volutpat blandit eget quis rutrum quam quis sollicitudin. Vel hymenaeos dui. Montes lobortis sodales. Tempor nulla vestibulum suspendisse volutpat vestibulum. Elit a condimentum massa diam vel in lacus donec. Pede at sit nunc sagittis.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day2_auditorium1_timeline" href="#day2_auditorium1_time4" class="schedule-item-toggle collapsed">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>7:40 PM</strong>
                      <h6 class="title">Passion and people<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day2_auditorium1_time4" class="panel-collapse collapse schedule-item-body">
                      <article>
                        <p class="description">Lectus mollis praesent. Sed nunc sed vestibulum cursus nulla dicta vel faucibus fringilla orci consequat. A duis sapien. Odio et sit. Elementum porttitor porttitor arcu sem sed. Gravida quisque velit tellus nec sed mus consequat.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                </div>
                <!-- Accordion end -->
              </div>
              <!-- Auditorium 1 content end -->
              <!-- Auditorium 2 content start -->
              <div id="day2_auditorium2" class="tab-pane fade in">
                <!-- Accordion start -->
                <div class="panel-group" id="day2_auditorium2_timeline">
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day2_auditorium2_timeline" href="#day2_auditorium2_time1" class="schedule-item-toggle">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>2:20 PM</strong>
                      <h6 class="title">How to organize great events<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day2_auditorium2_time1" class="panel-collapse collapse in schedule-item-body">
                      <article>
                        <p class="description">Mollis dolore magna tempor egestas laoreet in luctus non. Imperdiet mattis vitae. Vel sed aliquam sed turpis venenatis sed et sodales. Nam sit sed ullamcorper venenatis leo. Aliquam amet nec. Suspendisse enim feugiat eu sit.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                </div>
                <!-- Accordion end -->
              </div>
              <!-- Auditorium 2 content end -->
              <!-- Auditorium 3 content start -->
              <div id="day2_auditorium3" class="tab-pane fade in">
                <!-- Accordion start -->
                <div class="panel-group" id="day2_auditorium3_timeline">
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day2_auditorium3_timeline" href="#day2_auditorium3_time1" class="schedule-item-toggle">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>2:20 PM</strong>
                      <h6 class="title">How to organize great events<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day2_auditorium3_time1" class="panel-collapse collapse in schedule-item-body">
                      <article>
                        <p class="description">Non auctor dolor. Massa mollis montes nibh non vel. Eu luctus at. Mauris blandit convallis. In semper purus in quam fringilla elit vitae convallis purus dignissim a. Libero eros sit id sed vitae magni integer.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day2_auditorium3_timeline" href="#day2_auditorium3_time2" class="schedule-item-toggle collapsed">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>5:40 PM</strong>
                      <h6 class="title">Scaling into crowded space<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day2_auditorium3_time2" class="panel-collapse collapse schedule-item-body">
                      <article>
                        <p class="description">Condimentum enim diam. Ipsum lacus tincidunt magna lectus mattis pellentesque morbi magna laoreet suspendisse ut. Dis aliquam lacus mauris maecenas consectetuer. Donec ut nulla. Dui id ornare. Quaerat nibh feugiat dictum vitae ac accumsan ullamcorper.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day2_auditorium3_timeline" href="#day2_auditorium3_time4" class="schedule-item-toggle collapsed">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>7:40 PM</strong>
                      <h6 class="title">Passion and people<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day2_auditorium3_time4" class="panel-collapse collapse schedule-item-body">
                      <article>
                        <p class="description">Nunc gravida lacus nunc aptent eros. Condimentum ligula nonummy. Sit urna pellentesque sem est feugiat duis sollicitudin vestibulum nonummy beatae volutpat dui cras eget. Con vivamus elit. Aliquam nec et. Nunc a tellus nulla quisque.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                </div>
                <!-- Accordion end -->
              </div>
              <!-- Auditorium 3 content end -->
            </div>
            <!-- Second level content end -->
          </div>
          <!-- Day 2 content end -->
          <!-- Day 3 content start -->
          <div id="day3" class="tab-pane fade in">
            <!-- Navigation by auditorium start -->
            <ul class="nav nav-schedule">
              <li class="active"><a href="#day3_auditorium1" data-toggle="tab">Auditorium 1</a></li>
              <li><a href="#day3_auditorium2" data-toggle="tab">Auditorium 2</a></li>
              <li><a href="#day3_auditorium3" data-toggle="tab">Auditorium 3</a></li>
            </ul>
            <!-- Navigation by auditorium end -->
            <!-- Second level content start -->
            <div class="tab-content tab-content-schedule">
              <!-- Auditorium 1 content start -->
              <div id="day3_auditorium1" class="tab-pane fade active in">
                <!-- Accordion start -->
                <div class="panel-group" id="day3_auditorium1_timeline">
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day3_auditorium1_timeline" href="#day3_auditorium1_time1" class="schedule-item-toggle">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>2:20 PM</strong>
                      <h6 class="title">How to organize great events<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day3_auditorium1_time1" class="panel-collapse collapse in schedule-item-body">
                      <article>
                        <p class="description">Duis etiam et. Lorem eleifend aliquam. Dolor aenean sed. Gravida pellentesque duis nam erat pede per velit risus pellentesque aliquet turpis. Nonummy eros odio dapibus arcu tortor elit nonummy adipiscing. Purus leo ultrices blandit facilisis.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day3_auditorium1_timeline" href="#day3_auditorium1_time2" class="schedule-item-toggle collapsed">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>5:40 PM</strong>
                      <h6 class="title">Scaling into crowded space<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day3_auditorium1_time2" class="panel-collapse collapse schedule-item-body">
                      <article>
                        <p class="description">Mauris mi nonummy risus elit quis ut est gravida augue posuere elementum. Vitae dui proin consectetuer donec urna. Sit pede quis. Accumsan ipsum suscipit blandit purus amet. Sodales volutpat et. In ornare per sit ornare.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                </div>
                <!-- Accordion end -->
              </div>
              <!-- Auditorium 1 content end -->
              <!-- Auditorium 2 content start -->
              <div id="day3_auditorium2" class="tab-pane fade in">
                <!-- Accordion start -->
                <div class="panel-group" id="day3_auditorium2_timeline">
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day3_auditorium2_timeline" href="#day3_auditorium2_time1" class="schedule-item-toggle">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>2:20 PM</strong>
                      <h6 class="title">How to organize great events<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day3_auditorium2_time1" class="panel-collapse collapse in schedule-item-body">
                      <article>
                        <p class="description">Nam nulla morbi. Enim velit vel magna nulla dolor. Auctor nisl ut quisque lectus lorem. Urna lacus quisque mi cursus suscipit quis phasellus vulputate. Dapibus consequat nullam elementum taciti massa. Et vitae amet nunc libero.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                </div>
                <!-- Accordion end -->
              </div>
              <!-- Auditorium 2 content end -->
              <!-- Auditorium 3 content start -->
              <div id="day3_auditorium3" class="tab-pane fade in">
                <div class="schedule-item">No schedule yet.</div>
              </div>
              <!-- Auditorium 3 content end -->
            </div>
            <!-- Second level content end -->
          </div>
          <!-- Day 3 content end -->
          <!-- Day 4 content start -->
          <div id="day4" class="tab-pane fade in">
            <!-- Navigation by auditorium start -->
            <ul class="nav nav-schedule">
              <li class="active"><a href="#day4_auditorium1" data-toggle="tab">Auditorium 1</a></li>
              <li><a href="#day4_auditorium2" data-toggle="tab">Auditorium 2</a></li>
              <li><a href="#day4_auditorium3" data-toggle="tab">Auditorium 3</a></li>
            </ul>
            <!-- Navigation by auditorium start -->
            <!-- Second level content start -->
            <div class="tab-content tab-content-schedule">
              <!-- Auditorium 1 content start -->
              <div id="day4_auditorium1" class="tab-pane fade active in">
                <!-- Accordion start -->
                <div class="panel-group" id="day4_auditorium1_timeline">
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <span class="fa fa-cutlery"></span>
                    </div>
                    <a data-toggle="collapse" data-parent="#day4_auditorium1_timeline" href="#day4_auditorium1_time1" class="schedule-item-toggle">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>2:20 PM</strong>
                      <h6 class="title">How to organize great events<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day4_auditorium1_time1" class="panel-collapse collapse in schedule-item-body">
                      <article>
                        <p class="description">Blandit sed placerat justo curabitur libero ligula mauris senectus. Urna mollis in non venenatis eget. Mi lorem orci eu vitae platea. Lobortis et lobortis. Et maecenas sollicitudin. Rhoncus dictum ullamcorper et con wisi ligula tincidunt.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper"></div>
                    <a data-toggle="collapse" data-parent="#day4_auditorium1_timeline" href="#day4_auditorium1_time2" class="schedule-item-toggle collapsed">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>5:40 PM</strong>
                      <h6 class="title">Scaling into crowded space<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day4_auditorium1_time2" class="panel-collapse collapse schedule-item-body">
                      <article>
                        <p class="description">Senectus fusce ut. Montes faucibus corporis. Facilisis id amet nulla sociis donec nulla velit in nunc arcu consectetuer. Eleifend quisque in. Justo ut justo sit aliquam malesuada tellus velit suspendisse. Volutpat aliquam risus non aenean.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day4_auditorium1_timeline" href="#day4_auditorium1_time3" class="schedule-item-toggle collapsed">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>7:00 PM</strong>
                      <h6 class="title">Close your eyes around the world<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day4_auditorium1_time3" class="panel-collapse collapse schedule-item-body">
                      <article>
                        <p class="description">Nibh odio in vivamus pretium vivamus ullamcorper erat erat. Aliquip tortor justo. Nam venenatis leo. Rutrum at tellus. Tincidunt ac minus turpis urna et. At at sit integer in quam nullam placerat scelerisque est ligula.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day4_auditorium1_timeline" href="#day4_auditorium1_time4" class="schedule-item-toggle collapsed">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>7:40 PM</strong>
                      <h6 class="title">Passion and people<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day4_auditorium1_time4" class="panel-collapse collapse schedule-item-body">
                      <article>
                        <p class="description">Tellus tincidunt pede. Quam sed non nec ligula ut primis a et. Lorem nulla arcu parturient sem quam. Ut malesuada sapien. Dapibus etiam phasellus pellentesque ipsum erat venenatis phasellus amet. Faucibus scelerisque metus quis eaque.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                </div>
                <!-- Accordion end -->
              </div>
              <!-- Auditorium 1 content end -->
              <!-- Auditorium 2 content start -->
              <div id="day4_auditorium2" class="tab-pane fade in">
                <!-- Accordion start -->
                <div class="panel-group" id="day4_auditorium2_timeline">
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day4_auditorium2_timeline" href="#day4_auditorium2_time1" class="schedule-item-toggle">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>2:20 PM</strong>
                      <h6 class="title">How to organize great events<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day4_auditorium2_time1" class="panel-collapse collapse in schedule-item-body">
                      <article>
                        <p class="description">Mollis lobortis gravida. Urna aliquam nonummy tortor aliquam dapibus. Imperdiet ac donec. Sit pharetra bibendum vitae fusce ullamcorper sed ad quam. Vel wisi magna massa mus lacus vestibulum vulputate nec. Pellentesque sit arcu pellentesque posuere.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day4_auditorium2_timeline" href="#day4_auditorium2_time2" class="schedule-item-toggle collapsed">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>5:40 PM</strong>
                      <h6 class="title">Scaling into crowded space<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day4_auditorium2_time2" class="panel-collapse collapse schedule-item-body">
                      <article>
                        <p class="description">At nunc eget. Con scelerisque nam in fermentum integer. Aliquet ultricies eget elit autem velit maecenas nunc blandit. In nisl vel vel auctor aliquam. Laoreet justo vestibulum nisl tellus convallis. Ornare mauris pellentesque in nec.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                </div>
                <!-- Accordion end -->
              </div>
              <!-- Auditorium 2 content end -->
              <!-- Auditorium 3 content start -->
              <div id="day4_auditorium3" class="tab-pane fade in">
                <!-- Accordion start -->
                <div class="panel-group" id="day4_auditorium3_timeline">
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day4_auditorium3_timeline" href="#day4_auditorium3_time1" class="schedule-item-toggle">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>2:20 PM</strong>
                      <h6 class="title">How to organize great events<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day4_auditorium3_time1" class="panel-collapse collapse in schedule-item-body">
                      <article>
                        <p class="description">Praesent praesent semper parturient semper nec donec sit enim. Fames diam massa. Ultrices magna exercitationem ultricies ullamcorper volutpat. Est faucibus felis. Consequat et vitae aliquam nibh mi. Ipsum mauris etiam orci venenatis sem maecenas vestibulum.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                </div>
                <!-- Accordion end -->
              </div>
              <!-- Auditorium 3 content end -->
            </div>
            <!-- Second level content end -->
          </div>
          <!-- Day 4 content end -->
          <!-- Day 5 content start -->
          <div id="day5" class="tab-pane fade in">
            <!-- Navigation by auditorium start -->
            <ul class="nav nav-schedule">
              <li class="active"><a href="#day5_auditorium1" data-toggle="tab">Auditorium 1</a></li>
              <li><a href="#day5_auditorium2" data-toggle="tab">Auditorium 2</a></li>
              <li><a href="#day5_auditorium3" data-toggle="tab">Auditorium 3</a></li>
            </ul>
            <!-- Navigation by auditorium end -->
            <!-- Second level content start -->
            <div class="tab-content tab-content-schedule">
              <!-- Auditorium 1 content start -->
              <div id="day5_auditorium1" class="tab-pane fade active in">
                <!-- Accordion start -->
                <div class="panel-group" id="day5_auditorium1_timeline">
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day5_auditorium1_timeline" href="#day5_auditorium1_time1" class="schedule-item-toggle">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>2:20 PM</strong>
                      <h6 class="title">How to organize great events<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day5_auditorium1_time1" class="panel-collapse collapse in schedule-item-body">
                      <article>
                        <p class="description">Vitae tellus donec vivamus quam diam. Neque lorem ut nisl nisl ante ipsum eu accumsan phasellus ligula nisl. Eu mauris exercitationem. Nullam eu quam elementum sit phasellus. Erat tempus eiusmod. Ut volutpat venenatis eu feugiat.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day5_auditorium1_timeline" href="#day5_auditorium1_time3" class="schedule-item-toggle collapsed">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>7:00 PM</strong>
                      <h6 class="title">Close your eyes around the world<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day5_auditorium1_time3" class="panel-collapse collapse schedule-item-body">
                      <article>
                        <p class="description">Odio sed arcu. Lorem tempor mauris. Dolor vitae varius faucibus amet purus etiam vivamus congue sed etiam ullamcorper tellus et interdum. Ut libero interdum. Lacinia vel faucibus. Dis justo dolor enim ante nisl fames a.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day5_auditorium1_timeline" href="#day5_auditorium1_time4" class="schedule-item-toggle collapsed">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>7:40 PM</strong>
                      <h6 class="title">Passion and people<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day5_auditorium1_time4" class="panel-collapse collapse schedule-item-body">
                      <article>
                        <p class="description">Nunc est elementum. Lorem nullam id. Neque lorem sem. Quam nulla pretium augue condimentum lorem. Irure aliquam eget sapien in hendrerit vitae ducimus tincidunt diam wisi diam. Malesuada erat nibh natoque augue etiam semper justo.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                </div>
                <!-- Accordion end -->
              </div>
              <!-- Auditorium 1 content end -->
              <!-- Auditorium 2 content start -->
              <div id="day5_auditorium2" class="tab-pane fade in">
                <!-- Accordion start -->
                <div class="panel-group" id="day5_auditorium2_timeline">
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day5_auditorium2_timeline" href="#day5_auditorium2_time1" class="schedule-item-toggle">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>2:20 PM</strong>
                      <h6 class="title">How to organize great events<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day5_auditorium2_time1" class="panel-collapse collapse in schedule-item-body">
                      <article>
                        <p class="description">Dui maecenas curabitur donec suspendisse ipsum. Vel luctus eros. Porttitor ac repudiandae. Mauris enim risus mauris litora ipsum lectus integer nullam. Sollicitudin mus tortor. Natoque metus etiam euismod bibendum ornare facilisi sit et dui sem.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                </div>
                <!-- Accordion end -->
              </div>
              <!-- Auditorium 2 content end -->
              <!-- Auditorium 3 content start -->
              <div id="day5_auditorium3" class="tab-pane fade in">
                <!-- Accordion start -->
                <div class="panel-group" id="day5_auditorium3_timeline">
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day5_auditorium3_timeline" href="#day5_auditorium3_time1" class="schedule-item-toggle">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>2:20 PM</strong>
                      <h6 class="title">How to organize great events<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day5_auditorium3_time1" class="panel-collapse collapse in schedule-item-body">
                      <article>
                        <p class="description">Orci integer suspendisse. Iaculis libero primis rutrum pede eget at fermentum vel. Sem in turpis quis accumsan malesuada. Turpis id vivamus. Nec enim quasi potenti rhoncus id. Sed vestibulum ut tincidunt amet neque nulla vulputate.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day5_auditorium3_timeline" href="#day5_auditorium3_time2" class="schedule-item-toggle collapsed">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>5:40 PM</strong>
                      <h6 class="title">Scaling into crowded space<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day5_auditorium3_time2" class="panel-collapse collapse schedule-item-body">
                      <article>
                        <p class="description">Suscipit lacus tellus. Varius et at. Eros amet dignissim pede eget augue maecenas in sodales sed sapien nam venenatis lectus iaculis urna nunc dolor. Lobortis class dapibus. Aptent est donec. Ante dolor metus enim mollis.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                  <!-- Lecture start -->
                  <div class="panel schedule-item">
                    <div class="lecture-icon-wrapper">
                      <img src="/o/see-theme/images/speakers/speaker1.jpg" alt="" class="img-responsive">
                    </div>
                    <a data-toggle="collapse" data-parent="#day5_auditorium3_timeline" href="#day5_auditorium3_time4" class="schedule-item-toggle collapsed">
                      <strong class="time highlight"><span class="icon icon-office-24"></span>7:40 PM</strong>
                      <h6 class="title">Passion and people<i class="icon icon-arrows-06"></i></h6>
                    </a>
                    <div id="day5_auditorium3_time4" class="panel-collapse collapse schedule-item-body">
                      <article>
                        <p class="description">Pharetra expedita lorem. Turpis ipsum sodales. Porttitor posuere ipsum vestibulum suspendisse pellentesque. Scelerisque a pellentesque molestie vel ullamcorper dolor scelerisque erat. In vestibulum in suspendisse duis ligula. Urna interdum purus amet mi rutrum maecenas elit.</p>
                        <strong class="highlight speaker-name">Michael Lambert</strong>
                      </article>
                    </div>
                  </div>
                  <!-- Lecture end -->
                </div>
                <!-- Accordion end -->
              </div>
              <!-- Auditorium 3 content end -->
            </div>
            <!-- Second level content end -->
          </div>
          <!-- Day 5 content end -->
        </div>
        <!-- First level content end -->
      </div>
      <!-- Schedule end -->
    </div>
  </section>

  <!-- Programme à télécharger -->
  <section class="section bg-cover overlay bg4 light-text align-center">
    <div class="container">
      <div class="col-sm-12 align-center">
        <a href="#" target="_blank" class="btn btn-outline btn-sm">Téléchargez le programme</a>
      </div>
    </div>
  </section>

  <!-- Testimoniaux-->
  <section id="testimoniaux" class="section align-center" style="padding-top: 25px;">
    <div class="container">
      <span class="icon section-icon icon-chat-messages-04"></span>
      <h3>Testimoniaux</h3>
      <p class="text-alt" style="margin-bottom: 80px;">Ils parlent <span class="highlight">de nous</span></p>
      <div class="row">
        <div class="col-sm-4">
          <div class="testimonial">
            <article class="text-box">Esse maxime tempore maiores laboriosam nobis, aut cum quidem est ab ipsam soluta voluptate totam quibusdam quo neque doloremque, provident asperiores error pariatur.</article>
            <div class="author-block">
              <div class="photo-container" style="background-image: url('/o/see-theme/images/testimonials/client1.jpg')"></div>
              <strong class="name">Jean Meyer</strong>
              <small class="text-alt company">Designer @ Baffle</small>
            </div>
          </div>
        </div>
        <div class="col-sm-4">
          <div class="testimonial">
            <article class="text-box">Illo repellat dolores laudantium quos, velit, reprehenderit veniam accusamus neque laboriosam tenetur aut quaerat, doloribus autem, facere molestiae? Quisquam ducimus, nesciunt mollitia.</article>
            <div class="author-block">
              <div class="photo-container" style="background-image: url('/o/see-theme/images/testimonials/client2.jpg')"></div>
              <strong class="name">Sabine Klein</strong>
              <small class="text-alt company">CTO @ Flopbox</small>
            </div>
          </div>
        </div>
        <div class="col-sm-4">
          <div class="testimonial">
            <article class="text-box">Recusandae minima similique rem. Neque quisquam, blanditiis cum, ex ullam obcaecati beatae nobis quos, officia rerum, exercitationem ducimus totam quibusdam! Quia, illum.</article>
            <div class="author-block">
              <div class="photo-container" style="background-image: url('/o/see-theme/images/testimonials/client3.jpg')"></div>
              <strong class="name">Julie Muller</strong>
              <small class="text-alt company">VP Sales @ Pell Inc.</small>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>

  <!-- Galerie -->
  <section id="gallery" class="section align-center">
    <div class="container">
      <span class="icon section-icon icon-badges-votes-01"></span>
      <h3>Galerie</h3>
      <p class="text-alt">see what we did <span class="highlight">lately</span></p>
      <br/>
      <div class="gallery masonry">
        <span class="masonry-item">
          <a href="#" class="gallery-thumb-link" data-modal-link="gallery-1">
            <img src="/o/see-theme/images/gallery/gallery1-thumb.jpg" alt="">
          </a>
          <div class="modal-window" data-modal="gallery-1">
            <div class="modal-box medium animated" data-animation="zoomIn" data-duration="700">
              <span class="close-btn icon icon-office-52"></span>
              <h5 class="heading-alt">Gallery image 1</h5>
              <br/>
              <img src="/o/see-theme/images/gallery/gallery1.jpg" class="full-width-img" alt="gallery1">
            </div>
          </div>
        </span>
        <span class="masonry-item">
          <a href="#" class="gallery-thumb-link" data-modal-link="gallery-2">
            <img src="/o/see-theme/images/gallery/gallery2-thumb.jpg" alt="">
          </a>
          <div class="modal-window" data-modal="gallery-2">
            <div class="modal-box medium animated" data-animation="zoomIn" data-duration="700">
              <span class="close-btn icon icon-office-52"></span>
              <h5 class="heading-alt">Gallery image 2</h5>
              <br/>
              <img src="/o/see-theme/images/gallery/gallery2.jpg" class="full-width-img" alt="gallery1">
            </div>
          </div>
        </span>
        <span class="masonry-item">
          <a href="#" class="gallery-thumb-link" data-modal-link="gallery-3">
            <img src="/o/see-theme/images/gallery/gallery3-thumb.jpg" alt="">
          </a>
          <div class="modal-window" data-modal="gallery-3">
            <div class="modal-box medium animated" data-animation="zoomIn" data-duration="700">
              <span class="close-btn icon icon-office-52"></span>
              <h5 class="heading-alt">Gallery image 3</h5>
              <br/>
              <img src="/o/see-theme/images/gallery/gallery3.jpg" class="full-width-img" alt="gallery1">
            </div>
          </div>
        </span>
        <span class="masonry-item">
          <a href="#" class="gallery-thumb-link" data-modal-link="gallery-4">
            <img src="/o/see-theme/images/gallery/gallery4-thumb.jpg" alt="">
          </a>
          <div class="modal-window" data-modal="gallery-4">
            <div class="modal-box medium animated" data-animation="zoomIn" data-duration="700">
              <span class="close-btn icon icon-office-52"></span>
              <h5 class="heading-alt">Gallery image 4</h5>
              <br/>
              <img src="/o/see-theme/images/gallery/gallery4.jpg" class="full-width-img" alt="gallery1">
            </div>
          </div>
        </span>
        <span class="masonry-item">
          <a href="#" class="gallery-thumb-link" data-modal-link="gallery-5">
            <img src="/o/see-theme/images/gallery/gallery5-thumb.jpg" alt="">
          </a>
          <div class="modal-window" data-modal="gallery-5">
            <div class="modal-box medium animated" data-animation="zoomIn" data-duration="700">
              <span class="close-btn icon icon-office-52"></span>
              <h5 class="heading-alt">Gallery image 5</h5>
              <br/>
              <img src="/o/see-theme/images/gallery/gallery5.jpg" class="full-width-img" alt="gallery1">
            </div>
          </div>
        </span>
        <span class="masonry-item">
          <a href="#" class="gallery-thumb-link" data-modal-link="gallery-6">
            <img src="/o/see-theme/images/gallery/gallery5-thumb.jpg" alt="">
          </a>
          <div class="modal-window" data-modal="gallery-6">
            <div class="modal-box medium animated" data-animation="zoomIn" data-duration="700">
              <span class="close-btn icon icon-office-52"></span>
              <h5 class="heading-alt">Gallery image 6</h5>
              <br/>
              <img src="/o/see-theme/images/gallery/gallery5.jpg" class="full-width-img" alt="gallery1">
            </div>
          </div>
        </span>
        <span class="masonry-item">
          <a href="#" class="gallery-thumb-link" data-modal-link="gallery-7">
            <img src="/o/see-theme/images/gallery/gallery4-thumb.jpg" alt="">
          </a>
          <div class="modal-window" data-modal="gallery-7">
            <div class="modal-box medium animated" data-animation="zoomIn" data-duration="700">
              <span class="close-btn icon icon-office-52"></span>
              <h5 class="heading-alt">Gallery image 7</h5>
              <br/>
              <img src="/o/see-theme/images/gallery/gallery4.jpg" class="full-width-img" alt="gallery1">
            </div>
          </div>
        </span>
        <span class="masonry-item">
          <a href="#" class="gallery-thumb-link" data-modal-link="gallery-8">
            <img src="/o/see-theme/images/gallery/gallery3-thumb.jpg" alt="">
          </a>
          <div class="modal-window" data-modal="gallery-8">
            <div class="modal-box medium animated" data-animation="zoomIn" data-duration="700">
              <span class="close-btn icon icon-office-52"></span>
              <h5 class="heading-alt">Gallery image 8</h5>
              <br/>
              <img src="/o/see-theme/images/gallery/gallery3.jpg" class="full-width-img" alt="gallery1">
            </div>
          </div>
        </span>
      </div>
    </section>

    <!-- Partenaires -->
    <section id="partenaires" class="section align-center">
      <div class="container">
        <span class="icon section-icon icon-documents-bookmarks-12"></span>
        <h3>Partenaires</h3>
        <p class="text-alt">companies that <span class="highlight">support</span> us</p>
        <br/>
        <br/>
        <div class="sponsors">
          <div class="sponsor big"><img src="/o/see-theme/images/sponsors/sponsor-big-1.png" alt=""></div>
          <div class="sponsor big"><img src="/o/see-theme/images/sponsors/sponsor-big-2.png" alt=""></div>
          <div class="sponsor big"><img src="/o/see-theme/images/sponsors/sponsor-big-3.png" alt=""></div>
          <div class="sponsor big"><img src="/o/see-theme/images/sponsors/sponsor-big-4.png" alt=""></div>
        </div>
      </div>
    </section>

    <!-- News -->
    <section id=medias class="section align-center">
      <div class="container">
        <span class="icon section-icon icon-multimedia-08"></span>
        <h3>Médias</h3>
        <p class="text-alt">Meet our <span class="highlight">professionals</span></p>
        <br>
        <br>
        <div class="col-sm-4">
          <div class="speaker">
            <div class="photo-wrapper"> <iframe width="100%" height="205" src="https://www.youtube.com/embed/KR6LeGFNAXw?rel=0&amp;showinfo=0" frameborder="0" allowfullscreen></iframe></div>
            <h3 class="name">Vidéo de présentation </h3>
            <p class="text-alt"><small>Vidéo</small></p>
            <p class="about">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.
              <br><a href="elements/content.html">Lire la suite...</a>
            </p>
          </div>
        </div>
        <div class="col-sm-4">
          <div class="speaker">
            <div class="photo-wrapper"><img src="http://media.strasbourg.eu/alfresco/d/d/workspace/SpacesStore/769ef006-c489-454c-8997-834ac326696f/Grand_spectacle_cathedrale2017.jpg" alt="John Doe" width="250" class="img-responsive"></div>
            <h3 class="name">Eurometropole Magazine</h3>
            <p class="text-alt"><small>News</small></p>
            <p class="about">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s. <br><a href="/elements/content.html">Lire la suite...</a></p>
          </div>
        </div>
        <div class="col-sm-4">
          <div class="speaker">
            <div class="photo-wrapper"><img src="http://media.strasbourg.eu/alfresco/d/d/workspace/SpacesStore/769ef006-c489-454c-8997-834ac326696f/Grand_spectacle_cathedrale2017.jpg" alt="John Doe" width="250" class="img-responsive"></div>
            <h3 class="name">Eurometropole Magazine</h3>
            <p class="text-alt"><small>News</small></p>
            <p class="about">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s. <br><a href="#">Lire la suite...</a></p>
          </div>
        </div>
      </div>
    </section>

    <!-- Footer -->
    <section class="footer">
      <div class="container">
        <div class="col-md-6">
          <ul class="footer-nav">
            <li class="footer-nav-item"><a href="#">Contact</a></li>
            <li class="footer-nav-item"><a href="#">Mentions Légales</a></li>
          </ul>
        </div>
      </div>
      <div class="footer-base">
        <div class="container">
          <div class="app-logo">
            <a href="http://www.strasbourg.eu" target="_blank">
              <img src="http://www.strasbourgcapousse.eu/app/themes/strasbourgcapousse/build/images/logo-strasbourg.svg" alt="Strasbourg.eu" data-pin-nopin="true">
            </a>
          </div>
        </div>
      </div>
    </section>
  </div>

  <@liferay_util["include"] page=body_bottom_include />
  <@liferay_util["include"] page=bottom_include />
  <!-- inject:js -->
  <script type="text/javascript">
    if(typeof jQuery == 'undefined'){
      document.write('<script type="text/javascript" src="/o/see-theme/js/jquery-2.1.4.min.js?ver=1"></'+'script>');
    }
  </script>
  <script>
    define._amd = define.amd;
    define.amd = false;
  </script>
  <script src="https://maps.googleapis.com/maps/api/js?v=3&libraries=places&key=AIzaSyAZ_4b-Rip0JyK5Ti8yKOxXKjKpjfcBvdM"></script>
  <script type="text/javascript" src="/o/see-theme/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="/o/see-theme/js/jquery.validate.min.js"></script>
  <script type="text/javascript" src="/o/see-theme/js/toastr.min.js"></script>
  <script type="text/javascript" src="/o/see-theme/js/jquery.waypoints.min.js"></script>
  <script type="text/javascript" src="/o/see-theme/js/jquery.plugin.js"></script>
  <script type="text/javascript" src="/o/see-theme/js/jquery.countTo.js"></script>
  <script type="text/javascript" src="/o/see-theme/js/masonry.pkgd.min.js"></script>
  <script type="text/javascript" src="/o/see-theme/js/modal-box.js"></script>
  <script type="text/javascript" src="/o/see-theme/js/ventcamp.js"></script>
  <script>
    define.amd = define._amd;
  </script>
  <!-- endinject -->
</body>
</html>