<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="Agence Thuria">
    <title>${the_title} - ${company_name}</title>

    <@liferay_util["include"] page=top_head_include />
    
    <link href="/o/noel-theme/css/icomoon.css" rel="stylesheet">
    <link href="/o/noel-theme/css/owl.carousel.min.css" rel="stylesheet">
    <link href="/o/noel-theme/css/owl.theme.default.min.css" rel="stylesheet">
    <link href="/o/noel-theme/css/selectric.css" rel="stylesheet">
    <link href="/o/noel-theme/css/t_main.css" rel="stylesheet">
    
  </head>


  <body class="${css_class}">

    <@liferay_ui["quick-access"] contentId="#main-content" />

    <@liferay_util["include"] page=body_top_include />

    <@liferay.control_menu />

    <div id="mns-global">
      <div id="layer"></div>
      <!-- Top header bar -->
      <div class="mns-top-header">
        <div>
          <img src="/o/noel-theme/images/logo-strasbourg-eu.png" alt="Logo Strasbourg" />
        </div>
        <div>
          <!--  <a href="#" class="mns-w-fixe-1"><span>Carte interractive</span></a> -->
          <a href="#" class="mns-w-fixe-2"><span>Pro & Presse</span></a>
          <a href="#" class="active">FR</a>
          <a href="#">EN</a>
          <a href="#">DE</a>
        </div>
      </div>
      <nav class="navbar mns-nav">
        <div id="search-bar" class="mns-search-bar">
          <div class="container">
            <div class="row">
              <form action="index.html" method="get">
                <input type="text" name="search" id="search" placeholder="Rechercher..." />
              </form>
            </div>
          </div>
        </div>
        <div class="container mns-navbar-wrapper">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">Strasbourg <span class="subtitle">Capitale de Noël</span></a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
              <div class="mns-top-header mns-top-header-mobile">
                <div>
                  <a href="#" class="mns-w-fixe-2"><span>Pro & Presse</span></a>
                  <a href="#" class="active">FR</a>
                  <a href="#">EN</a>
                  <a href="#">DE</a>
                </div>
              </div>
              <div class="mns-search-bar mns-search-bar-mobile">
                <div class="row">
                  <form action="index.html" method="get">
                    <input type="text" name="search" id="search" placeholder="Rechercher..." />
                  </form>
                </div>
              </div>
              <li class="hidden-sm hidden-xs menu-accueil"><a href="#"><span class="icon-ico-home"></span></a></li>
              <li class="active dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Les marchés de Noël</a>
                <ul class="dropdown-menu">
                  <li><a href="content-std1.html"><span class='icon-chevron-thin-right'></span> Liens de Niveau 2</a></li>
                  <li><a href="content-std1.html"><span class='icon-chevron-thin-right'></span> Liens de Niveau 2</a></li>
                  <li><a href="content-std2.html"><span class='icon-chevron-thin-right'></span> Liens de Niveau 2</a></li>
                  <li><a href="content-std1.html"><span class='icon-chevron-thin-right'></span> Liens de Niveau 2</a></li>
                  <li><a href="content-std2.html"><span class='icon-chevron-thin-right'></span> Liens de Niveau 2</a></li>
                  <li><a href="content-std1.html"><span class='icon-chevron-thin-right'></span> Liens de Niveau 2</a></li>
                  <li><a href="content-std2.html"><span class='icon-chevron-thin-right'></span> Liens de Niveau 2</a></li>
                  <li><a href="content-std2.html"><span class='icon-chevron-thin-right'></span> Liens de Niveau 2</a></li>
                </ul>
              </li>
              <li class="active dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Découvrir</a>
                <ul class="dropdown-menu">
                  <li><a href="content-std1.html"><span class='icon-chevron-thin-right'></span> Liens de Niveau 2</a></li>
                  <li><a href="content-std1.html"><span class='icon-chevron-thin-right'></span> Liens de Niveau 2</a></li>
                  <li><a href="content-std2.html"><span class='icon-chevron-thin-right'></span> Liens de Niveau 2</a></li>
                  <li><a href="content-std1.html"><span class='icon-chevron-thin-right'></span> Liens de Niveau 2</a></li>
                  <li><a href="content-std2.html"><span class='icon-chevron-thin-right'></span> Liens de Niveau 2</a></li>
                  <li><a href="content-std1.html"><span class='icon-chevron-thin-right'></span> Liens de Niveau 2</a></li>
                  <li><a href="content-std2.html"><span class='icon-chevron-thin-right'></span> Liens de Niveau 2</a></li>
                  <li><a href="content-std2.html"><span class='icon-chevron-thin-right'></span> Liens de Niveau 2</a></li>
                </ul>
              </li>
              <li><a href="top10.html">Top10</a></li>
              <li><a href="agenda-list.html">Agenda</a></li>
              <li><a href="contact.html">Infos pratiques</a></li>
              <li class="hidden-sm hidden-xs menu-search"><a href="#"><span class="icon-search"></span></a></li>
            </ul>
          </div>
          <!--/.nav-collapse -->
        </div>
        <!--/.container-fluid -->
      </nav>
      <header class="mns-header" style="background: url(/o/noel-theme/images/bg-homepage.jpg) no-repeat center center /cover;">
        <div class="container mns-center">
          <h2>La magie de noël à Strasbourg</h2>
          <span>Du 04  au 26 décembre 2017</span>
        </div>
      </header>
      <main>
        <div class="small-container mns-filtres row">
          <span class="icon-cross" id="cross-mobile"></span>
          <form action="index.html" method="get">
            <div class="col-sm-4 col-xs-12">
              <label>Je viens...</label>
              <select name="venir" id="venir">
                <option>En couple</option>
                <option>En célibataire</option>
                <option>En famille</option>
                <option>Entre amis</option>
              </select>
            </div>
            <div class="col-sm-4 col-xs-12">
              <label>Pendant...</label>
              <select name="periode" id="periode">
                <option>Ce week-end</option>
                <option>Ce jour-ci</option>
                <option>Ce mois</option>
                <option>Cette année</option>
              </select>
            </div>
            <div class="col-sm-4 col-xs-12">
              <label>Pour un(e)...</label>
              <select name="evenement" id="evenement">
                <option>Noël authentique</option>
                <option>Noël venu d'ailleurs</option>
                <option>Noël artistique et culturel</option>
                <option>Noël spirituel et de partage</option>
                <option>Découvertte de la ville</option>
                <option>Noël nocturne</option>
              </select>
            </div>
            <div class="col-md-12 text-center">
              <input type="submit" class="btn-filtre" value="Trouver" />
            </div>
          </form>
        </div>
        <div class="small-container mns-m-filtres-search row">
          <div class="col-xs-12">
            <span class="label">Je viens...,Pendant...,Pour un(e)... ?</span>
            <span id="search-mobile-filtres" class="btn-filtre">Recherchez</span>
          </div>
        </div>
        <!-- Zone Introduction -->
        <div class="small-container mns-home-intro">
          <p class="title">L’histoire du marché de Noël</p>
          <p><strong>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor </strong>incididunt ut labore et dolore magna aliqua. Exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut lore magna aliqua.</p>
          <a href="content-std2.html" class="link">En savoir plus</a>
        </div>
        <!-- Zone Entrées -->
        <div class="mns-wrapper-home-entry">
          <div class="container mns-home-entry mns-p50">
            <img src="/o/noel-theme/images/deco-big-elipse.png" class="deco-bg" alt="image decoration" />
            <div class="col-lg-6 col-md-8 col-xs-12">
              <h1 class="title">Vivez un noël unique</h1>
              <h2 class="city">à <span>Strasbourg</span></h2>
            </div>
            <div>
              <div class="mns-bloc-entry">
                <div class="mns-elipse-1">
                  <a href="content-std1.html">
                    <img src="/o/noel-theme/images/deco-elipse-or.png" class="deco" alt="image decoration" />
                    <img src="/o/noel-theme/images/deco-stars-grey.png" class="deco-stars" alt="image decoration"/>
                    <img src="/o/noel-theme/images/home-elipse-illuminations.png" alt="" class="mns-image" />
                    <div class="caption">
                      <div>
                        <h3>Les illuminations</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et doloreLorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                        <span>Découvrir</span>
                      </div>
                    </div>
                  </a>
                </div>
                <div class="mns-elipse-2">
                  <a href="marche.html">
                    <img src="/o/noel-theme/images/home-elipse-marches.png" alt="" class="mns-image" />
                    <div class="caption">
                      <div>
                        <h3>Les marchés de noël</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et doloreLorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                        <span>Découvrir</span>
                      </div>
                    </div>
                  </a>
                </div>
                <div class="mns-elipse-3">
                  <a href="top10.html">
                    <img src="/o/noel-theme/images/deco-elipse-beige.png" class="deco" alt="image decoration" />
                    <img src="/o/noel-theme/images/home-elipse-top10.png" alt="" class="mns-image" />
                    <div class="caption">
                      <div>
                        <h3>Le Top 10</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et doloreLorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                        <span>Découvrir</span>
                      </div>
                    </div>
                  </a>
                </div>
                <div class="mns-elipse-4">
                  <a href="contact.html">
                    <img src="/o/noel-theme/images/home-elipse-infos.png" alt="" class="mns-image" />
                    <div class="caption">
                      <div>
                        <h3>Les infos pratiques</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et doloreLorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                        <span>Découvrir</span>
                      </div>
                    </div>
                  </a>
                </div>
                <div class="mns-elipse-5">
                  <a href="content-std1.html">
                    <img src="/o/noel-theme/images/home-elipse-decouvrir.png" alt="" class="mns-image" />
                    <div class="caption">
                      <div>
                        <h3>Découvrir</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et doloreLorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                        <span>Découvrir</span>
                      </div>
                    </div>
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- Zone Agenda -->
        <div class="mns-section-agenda">
          <div class="container">
            <div class="col-xs-12 mns-right">
              <h2>L'agenda</h2>
              <a href="agenda-list.html" class="link">Voir tout l'agenda</a>
            </div>
            <div class="owl-carousel owl-opacify owl-theme col-xs-12" id="owl-agenda">
              <div class="item">
                <div class="mns-bloc-agenda">
                  <a href="agenda-detail.html">
                    <span class="date">Du 16/12 au 26/12</span>
                    <div class="cover" style="background: url(/o/noel-theme/images/home-agenda-1.jpg) no-repeat center center /cover;"></div>
                    <div>
                      <div class="col-xs-6 mns-indic">
                        <img src="/o/noel-theme/images/ico/ico-marker.png" alt="icone" />
                        <span>Lieu</span>
                      </div>
                      <div class="col-xs-6 mns-indic">
                        <img src="/o/noel-theme/images/ico/ico-type.png" alt="icone" />
                        <span>Type</span>
                      </div>
                      <h4>Titre de l’événement sur deux lignes</h4>
                      <span class="basic-link">Découvrir</span>
                    </div>
                  </a>
                </div>
              </div>
              <div class="item">
                <div class="mns-bloc-agenda">
                  <a href="agenda-detail.html">
                    <span class="date">Du 16/12 au 26/12</span>
                    <div class="cover" style="background: url(/o/noel-theme/images/home-agenda-4.jpg) no-repeat center center /cover;"></div>
                    <div>
                      <div class="col-xs-6 mns-indic">
                        <img src="/o/noel-theme/images/ico/ico-marker.png" alt="icone" />
                        <span>Lieu</span>
                      </div>
                      <div class="col-xs-6 mns-indic">
                        <img src="/o/noel-theme/images/ico/ico-type.png" alt="icone" />
                        <span>Type</span>
                      </div>
                      <h4>Titre de l’événement sur deux lignes</h4>
                      <span class="basic-link">Découvrir</span>
                    </div>
                  </a>
                </div>
              </div>
              <div class="item">
                <div class="mns-bloc-agenda">
                  <a href="agenda-detail.html">
                    <span class="date">Du 16/12 au 26/12</span>
                    <div class="cover" style="background: url(/o/noel-theme/images/home-agenda-4.jpg) no-repeat center center /cover;"></div>
                    <div>
                      <div class="col-xs-6 mns-indic">
                        <img src="/o/noel-theme/images/ico/ico-marker.png" alt="icone" />
                        <span>Lieu</span>
                      </div>
                      <div class="col-xs-6 mns-indic">
                        <img src="/o/noel-theme/images/ico/ico-type.png" alt="icone" />
                        <span>Type</span>
                      </div>
                      <h4>Titre de l’événement sur deux lignes</h4>
                      <span class="basic-link">Découvrir</span>
                    </div>
                  </a>
                </div>
              </div>
              <div class="item">
                <div class="mns-bloc-agenda">
                  <a href="agenda-detail.html">
                    <span class="date">Du 16/12 au 26/12</span>
                    <div class="cover" style="background: url(/o/noel-theme/images/home-agenda-4.jpg) no-repeat center center /cover;"></div>
                    <div>
                      <div class="col-xs-6 mns-indic">
                        <img src="/o/noel-theme/images/ico/ico-marker.png" alt="icone" />
                        <span>Lieu</span>
                      </div>
                      <div class="col-xs-6 mns-indic">
                        <img src="/o/noel-theme/images/ico/ico-type.png" alt="icone" />
                        <span>Type</span>
                      </div>
                      <h4>Titre de l’événement sur deux lignes</h4>
                      <span class="basic-link">Découvrir</span>
                    </div>
                  </a>
                </div>
              </div>
              <div class="item">
                <div class="mns-bloc-agenda">
                  <a href="agenda-detail.html">
                    <span class="date">Du 16/12 au 26/12</span>
                    <div class="cover" style="background: url(/o/noel-theme/images/home-agenda-4.jpg) no-repeat center center /cover;"></div>
                    <div>
                      <div class="col-xs-6 mns-indic">
                        <img src="/o/noel-theme/images/ico/ico-marker.png" alt="icone" />
                        <span>Lieu</span>
                      </div>
                      <div class="col-xs-6 mns-indic">
                        <img src="/o/noel-theme/images/ico/ico-type.png" alt="icone" />
                        <span>Type</span>
                      </div>
                      <h4>Titre de l’événement sur deux lignes</h4>
                      <span class="basic-link">Découvrir</span>
                    </div>
                  </a>
                </div>
              </div>
              <div class="item">
                <div class="mns-bloc-agenda">
                  <a href="agenda-detail.html">
                    <span class="date">Du 16/12 au 26/12</span>
                    <div class="cover" style="background: url(/o/noel-theme/images/home-agenda-3.jpg) no-repeat center center /cover;"></div>
                    <div>
                      <div class="col-xs-6 mns-indic">
                        <img src="/o/noel-theme/images/ico/ico-marker.png" alt="icone" />
                        <span>Lieu</span>
                      </div>
                      <div class="col-xs-6 mns-indic">
                        <img src="/o/noel-theme/images/ico/ico-type.png" alt="icone" />
                        <span>Type</span>
                      </div>
                      <h4>Titre de l’événement sur deux lignes</h4>
                      <span class="basic-link">Découvrir</span>
                    </div>
                  </a>
                </div>
              </div>
              <div class="item">
                <div class="mns-bloc-agenda">
                  <a href="agenda-detail.html">
                    <span class="date">Du 16/12 au 26/12</span>
                    <div class="cover" style="background: url(/o/noel-theme/images/home-agenda-4.jpg) no-repeat center center /cover;"></div>
                    <div>
                      <div class="col-xs-6 mns-indic">
                        <img src="/o/noel-theme/images/ico/ico-marker.png" alt="icone" />
                        <span>Lieu</span>
                      </div>
                      <div class="col-xs-6 mns-indic">
                        <img src="/o/noel-theme/images/ico/ico-type.png" alt="icone" />
                        <span>Type</span>
                      </div>
                      <h4>Titre de l’événement sur deux lignes</h4>
                      <span class="basic-link">Découvrir</span>
                    </div>
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- Zone Actualités -->
        <div class="container mns-section-actualites">
          <div class="col-xs-12">
            <h2>L'actualité</h2>
          </div>
          <div class="small-container">
            <div class="row" data-egalize=".mns-bloc-actu > a">
              <div class="col-sm-6">
                <div class="mns-bloc-actu">
                  <a href="actu-detail.html">
                    <div class="mns-bloc-top-img">
                      <img src="/o/noel-theme/images/home-actu-1.jpg" alt="Actualités" />
                    </div>
                    <div class="mns-bloc-content-actu">
                      <span class="publication">Publiée le 04 décembre 2017</span>
                      <h4>Titre de l’actualité</h4>
                      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore...</p>
                      <span class="basic-link">Lire la suite</span>
                    </div>
                  </a>
                </div>
              </div>
              <div class="col-sm-6 col-xs-12">
                <div class="mns-bloc-actu">
                  <a href="actu-detail.html">
                    <div class="mns-bloc-top-img">
                      <img src="/o/noel-theme/images/home-actu-2.jpg" alt="Actualités" />
                    </div>
                    <div class="mns-bloc-content-actu">
                      <span class="publication">Publiée le 04 décembre 2017</span>
                      <h4>Titre de l’actualité</h4>
                      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore...</p>
                      <span class="basic-link">Lire la suite</span>
                    </div>
                  </a>
                </div>
              </div>
              <div class="col-xs-12 mns-right">
                <span><a href="actu-list.html" class="link align-right">Voir toute l’actualité</a></span>
              </div>
            </div>
          </div>
        </div>
        <!-- Zone recherche séjour -->
        <div class="mns-sejour" style="background: url(/o/noel-theme/images/bg-home-marche-noel.jpg) no-repeat center center /cover; background-attachment: fixed;">
          <div class="container">
            <div class="row">
              <div class="caption">
                <h3>Besoin d’un hotel, <span>d’un resto..?</span></h3>
                <p>In condimentum facilisis porta. Sed nec diam eu diam mattis vicilisis porta. Sed nec diam eu diam mattis vicilisis porta. Sed nec diam eu diam mattis viverra. Nulla fringilla, orci ac euismod semper.</p>
                <a href="marche.html" class="mns-btn">Préparez votre séjour</a>
              </div>
            </div>
          </div>
        </div>
        <!-- Zone Testimonials -->
        <div class="mns-testimonial small-container row">
          <img src="/o/noel-theme/images/cote-left.png" alt="image" class="img-cote-left" />
          <div>
            <div class="col-xs-12">
              <div class="owl-carousel owl-theme col-xs-12" id="owl-testi">
                <div class="mns-bloc-testi row">
                  <div class="col-sm-3 col-xs-12">
                    <img src="/o/noel-theme/images/img-testimonial.png" alt="Image Testimonial" />
                  </div>
                  <div class="col-sm-9 col-xs-12 text-content">
                    <h4 class="title">Lorem ipsum dolor sit amet, consectetur adipisicing elit</h4>
                    <p>Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore</p>
                    <p class="info-testi">Léa C. Une Strabourgeoise amoureuse de Noël</p>
                  </div>
                </div>
                <div class="mns-bloc-testi row">
                  <div class="col-sm-3 col-xs-12">
                    <img src="/o/noel-theme/images/img-testimonial.png" alt="Image Testimonial" />
                  </div>
                  <div class="col-sm-9 col-xs-12 text-content">
                    <h4 class="title">Lorem ipsum dolor sit amet, consectetur adipisicing elit</h4>
                    <p>Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore</p>
                    <p class="info-testi">Thomas C. Une Strabourgeoise amoureuse de Noël</p>
                  </div>
                </div>
                <div class="mns-bloc-testi row">
                  <div class="col-sm-3 col-xs-12">
                    <img src="/o/noel-theme/images/img-testimonial.png" alt="Image Testimonial" />
                  </div>
                  <div class="col-sm-9 col-xs-12 text-content">
                    <h4 class="title">Lorem ipsum dolor sit amet, consectetur adipisicing elit</h4>
                    <p>Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore</p>
                    <p class="info-testi">Léa C. Une Strabourgeoise amoureuse de Noël</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <img src="/o/noel-theme/images/cote-left.png" alt="image" class="img-cote-right" />
        </div>
        <!-- Zone Social Wall -->
        <div class="mns-social-wall mns-p50">
          <div class="container">
            <div class="row">
              <div class="col-xs-12 mns-top-social-wall">
                <ul>
                  <li><a href="https://www.facebook.com/Marche.Noel.Strasbourg/" target="_blank"><img src="/o/noel-theme/images/ico/ico-fb-color.png" alt="icone" /></a></li>
                  <li><a href="https://www.instagram.com/" target="_blank"><img src="/o/noel-theme/images/ico/ico-ig-color.png" alt="icone" /></a></li>
                  <li><a href="https://twitter.com/strasbourg?lang=fr" target="_blank"><img src="/o/noel-theme/images/ico/ico-tw-color.png" alt="icone" /></a></li>
                </ul>
                <h3><span>#Noel</span>Strasbourg</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et doloremagna aliqua. Ut enim ad minim</p>
              </div>
              <div class="mns-list-social-wall mns-p50" data-egalize=".mns-bloc-social-wall > a">
                <div class="col-md-3 col-sm-6 col-xs-12">
                  <div class="mns-bloc-social-wall">
                    <a href="https://www.instagram.com/" target="_blank">
                      <div class="include-zoom">
                        <img src="/o/noel-theme/images/img-social-wall-1.jpg" alt="Image réseaux sociaux" class="mns-feed" />
                      </div>
                      <div>
                        <span><img src="/o/noel-theme/images/strasbourg-feed.svg" alt="strasbourg logo social wall" /></span>
                        <span class="account">@Strasbourgeu</span>
                        <p>Lorem ipsum @dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore #Ipsum #Lorem</p>
                      </div>
                    </a>
                  </div>
                </div>
                <div class="col-md-3 col-sm-6 col-xs-12">
                  <div class="mns-bloc-social-wall">
                    <a href="https://www.instagram.com/" target="_blank">
                      <div class="include-zoom">
                        <img src="/o/noel-theme/images/img-social-wall-2.jpg" alt="Image réseaux sociaux" />
                      </div>
                      <div>
                        <span><img src="/o/noel-theme/images/strasbourg-feed.svg" alt="strasbourg logo social wall" /></span>
                        <span class="account">@Strasbourgeu</span>
                        <p>Lorem ipsum @dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore #Ipsum #Lorem</p>
                      </div>
                    </a>
                  </div>
                </div>
                <div class="col-md-3 col-sm-6 col-xs-12">
                  <div class="mns-bloc-social-wall">
                    <a href="https://www.instagram.com/" target="_blank">
                      <div class="include-zoom">
                        <img src="/o/noel-theme/images/img-social-wall-3.jpg" alt="Image réseaux sociaux" />
                      </div>
                      <div>
                        <span><img src="/o/noel-theme/images/strasbourg-feed.svg" alt="strasbourg logo social wall" /></span>
                        <span class="account">@Strasbourgeu</span>
                        <p>Lorem ipsum @dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore #Ipsum #Lorem</p>
                      </div>
                    </a>
                  </div>
                </div>
                <div class="col-md-3 col-sm-6 col-xs-12">
                  <div class="mns-bloc-social-wall">
                    <a href="https://www.instagram.com/" target="_blank">
                      <div class="include-zoom">
                        <img src="/o/noel-theme/images/img-social-wall-4.jpg" alt="Image réseaux sociaux" />
                      </div>
                      <div>
                        <span><img src="/o/noel-theme/images/strasbourg-feed.svg" alt="strasbourg logo social wall" /></span>
                        <span class="account">@Strasbourgeu</span>
                        <p>Lorem ipsum @dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore #Ipsum #Lorem</p>
                      </div>
                    </a>
                  </div>
                </div>
                <div class="col-md-3 col-sm-6 col-xs-12">
                  <div class="mns-bloc-social-wall">
                    <a href="https://www.instagram.com/" target="_blank">
                      <div class="include-zoom">
                        <img src="/o/noel-theme/images/img-social-wall-5.jpg" alt="Image réseaux sociaux" />
                      </div>
                      <div>
                        <span><img src="/o/noel-theme/images/strasbourg-feed.svg" alt="strasbourg logo social wall" /></span>
                        <span class="account">@Strasbourgeu</span>
                        <p>Lorem ipsum @dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore #Ipsum #Lorem</p>
                      </div>
                    </a>
                  </div>
                </div>
                <div class="col-md-3 col-sm-6 col-xs-12">
                  <div class="mns-bloc-social-wall">
                    <a href="https://www.instagram.com/" target="_blank">
                      <div class="include-zoom">
                        <img src="/o/noel-theme/images/img-social-wall-6.jpg" alt="Image réseaux sociaux" />
                      </div>
                      <div>
                        <span><img src="/o/noel-theme/images/strasbourg-feed.svg" alt="strasbourg logo social wall" /></span>
                        <span class="account">@Strasbourgeu</span>
                        <p>Lorem ipsum @dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore #Ipsum #Lorem</p>
                      </div>
                    </a>
                  </div>
                </div>
                <div class="col-md-3 col-sm-6 col-xs-12">
                  <div class="mns-bloc-social-wall">
                    <a href="https://www.instagram.com/" target="_blank">
                      <div class="include-zoom">
                        <img src="/o/noel-theme/images/img-social-wall-7.jpg" alt="Image réseaux sociaux" />
                      </div>
                      <div>
                        <span><img src="/o/noel-theme/images/strasbourg-feed.svg" alt="strasbourg logo social wall" /></span>
                        <span class="account">@Strasbourgeu</span>
                        <p>Lorem ipsum @dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore #Ipsum #Lorem</p>
                      </div>
                    </a>
                  </div>
                </div>
                <div class="col-md-3 col-sm-6 col-xs-12">
                  <div class="mns-bloc-social-wall">
                    <a href="https://www.instagram.com/" target="_blank">
                      <div class="include-zoom">
                        <img src="/o/noel-theme/images/img-social-wall-8.jpg" alt="Image réseaux sociaux" />
                      </div>
                      <div>
                        <span><img src="/o/noel-theme/images/strasbourg-feed.svg" alt="strasbourg logo social wall" /></span>
                        <span class="account">@Strasbourgeu</span>
                        <p>Lorem ipsum @dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore #Ipsum #Lorem</p>
                      </div>
                    </a>
                  </div>
                </div>
                <div class="col-xs-12 text-center">
                  <a href="https://www.instagram.com/" class="large-link" target="_blank">Retrouvez-nous sur instagram</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </main>
      <!-- Footer -->
      <footer class="mns-footer">
        <div class="container">
          <div class="row">
            <div class="col-md-8 col-xs-12">
              <div class="mns-nav-footer">
                <ul>
                  <li><a href="index.html">Accueil</a></li>
                  <li><a href="marche.html">Les marchés de Noël</a></li>
                  <li><a href="content-std1.html">Découvrir</a></li>
                  <li><a href="top10.html">Top 10</a></li>
                  <li><a href="agenda-list.html">Agenda</a></li>
                  <li><a href="contact.html">Infos pratiques</a></li>
                </ul>
              </div>
            </div>
            <div class="col-sm-4 hidden-sm">
              <div class="mns-title">
                <span>Suivez-nous</span>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-4 col-xs-12">
              <div class="mns-first-column">
                <ul>
                  <li><a href="contact.html">Comment venir ?</a></li>
                  <li><a href="contact.html">Où se garer ?</a></li>
                  <li><a href="contact.html">Pour les camping car</a></li>
                  <li><a href="contact.html">Dispositif de sécurité</a></li>
                  <li><a href="contact.html">Télécharger le programme</a></li>
                </ul>
                <p>Ville et Eurométropole de Strasbourg © 2016</p>
              </div>
            </div>
            <div class="col-sm-4 col-xs-12">
              <div class="mns-second-column">
                <h5>préparer votre séjour</h5>
                <span>Office de tourisme de Strasbourg et sa région</span>
                <span>17 place de la Cathédrale</span>
                <span>67082 STRASBOURG CEDEX</span>
                <div class="info-contact">
                  <span><a href="tel:0388522828">Tel. : +33 3 88 52 28 28</a></span>
                  <span><a href="mailto:info@otstrasbourg.fr">Mail : info@otstrasbourg.fr</a></span>
                </div>
              </div>
            </div>
            <div class="col-sm-4 col-xs-12">
              <div class="mns-third-column">
                <ul>
                  <li><a href="https://www.facebook.com/Marche.Noel.Strasbourg/" target="_blank"><span class="icon-facebook-with-circle"></span></a></li>
                  <li><a href="https://twitter.com/strasbourg?lang=fr" target="_blank"><span class="icon-twitter-with-circle"></span></a></li>
                  <li><a href="https://www.instagram.com/" target="_blank"><span class="icon-instagram-with-circle"></span></a></li>
                </ul>
              </div>
            </div>
          </div>
        </div>
        <div class="mns-footer-bottom">
          <div class="container">
            <img src="/o/noel-theme/images/logo-strasbourg-eu-footer.png" alt="Logo Strasbourg.eu" />
          </div>
        </div>
      </footer>
    </div>

    <script type="text/javascript">
      if ($(window).width() >= 1280) {     
        document.write('\x3Cscript type="text/javascript" src="/o/noel-theme/js/jquery.stellar.js">\x3C/script>');
      }
    </script>
    
    <@liferay_util["include"] page=body_bottom_include />
    <@liferay_util["include"] page=bottom_include />
    <script src="/o/noel-theme/js/fastclick.js"></script>
    <script src="/o/noel-theme/js/th_egalize.js"></script>
    <script src="/o/noel-theme/js/jquery.selectric.min.js"></script>
    <script src="/o/noel-theme/js/bootstrap.min.js"></script>
    <script src="/o/noel-theme/js/owl.carousel.min.js"></script>
    <script src="/o/noel-theme/js/t_main.min.js"></script>


  </body>
</html>