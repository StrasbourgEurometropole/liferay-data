<h1 class="entry-title">Boutique</h1>
<ul class="menu-boutique">

<#list category.getSiblings() as cat>
    <li class="${cat.getData()}">
        <a class="${cat.getData()} <#if cat?counter == 1 > active </#if>" href="#" title="${cat.title.getData()}">${cat.title.getData()}</a>
    </li>

    <style>
        ul.menu-boutique li.${cat.getData()} {
            width: ${largeur.getData()}px;
            height: ${hauteur.getData()}px;
        }
        ul.menu-boutique li a.${cat.getData()} {
            display: block;
            background-position: top left;
            background-image: url(${cat.illustration.getData()});
            background-repeat: no-repeat;
            width: 100%;
            height: 100%;
        }

        ul.menu-boutique li a.${cat.getData()}:hover
        , ul.menu-boutique li a.${cat.getData()}.active {
            background-position: top right
        }
        

        @media all and (max-width: 640px)
        {
            .boutique-content .menu-boutique li a.${cat.getData()},
            .boutique-content .menu-boutique li a.${cat.getData()}:hover,
            .boutique-content .menu-boutique li a.${cat.getData()}.active{
                background-image:url(${cat.illustrationresponsive.getData()});
                background-repeat: no-repeat;
                background-position: 4px 6px;
            }

            ul.menu-boutique li.${cat.getData()} {
                width: 100%;
                height: 100%;
            }
        }
    </style>

</#list>
</ul>
<div class="clearfix">
</div>
<p>
    <a class="btn-comment how-to-order-link" href="#how-to-order-popup" title="Comment commander ?">Comment commander <span>?</span></a>
</p>
<div id="how-to-order-popup" class="white-popup mfp-hide">
    <div class="boutique">
        <script>
            jQuery(document).ready(function() {
                jQuery('.bigger-img').click(function(){
                    var elem = jQuery(this);
                    jQuery('.white-popup-block .viewer').html('<img src="'+elem.attr('href')+'" alt="'+elem.attr('title')+'" />').stop().fadeIn();
                    return false;
                });

                jQuery('.viewer').click(function(){
                    jQuery(this).stop().fadeOut().children().remove();
                });
            });
        </script>
        <div id="custom-content" class="white-popup-block" style="max-width:600px; margin: 20px auto;">
            <div class="instruction">
                <h3>Comment commander <span>?</span></h3>
                <p>
                    <span class="strong">Attention :</span> certains objets vendus par la Fondation de l’Œuvre Notre-Dame ne peuvent pas être envoyés par la Poste, parce qu’ils sont trop fragiles ou trop lourds. Il faudra donc venir les acheter à la Boutique culture ou sur place.
                    <span class="strong important">Contactez-nous pour connaître les modalités</span>
                </p>
            </div>  
            <div class="coordonnees" style="height: auto !important;">
                <div class="center">
                    <div class="col-3 adresse">
                        Fondation de l'Œuvre Notre-Dame <br/>
                        3, place du Château<br/>
                        67076 Strasbourg cedex
                    </div>
                    <div class="col-3 telephone">
                         <a href="tel:0033368985142" style="color: #ffffff;">+33 (0)3 68 98 51 42</a>
                    </div>
                    <div class="col-3 email">
                        <a href="mailto:oeuvre-notre-dame@strasbourg.eu" style="color: #ffffff;">oeuvre-notre-dame@strasbourg.eu</a>
                    </div>
                    <div class="col-1">
                        Les bureaux de la Fondation sont ouverts à la vente de nos produits<br /><span class="strong">de 8h00 à 12h00 et de 14h00 à 17h00.</span><br/><a href="//www.facebook.com/laboutiqueculture.strasbourg" title="La Boutique culture sur Facebook (nouvelle fenêtre)" target="_blank" style="color: #ffffff; text-decoration: underline;">La Boutique culture</a> est ouverte du mardi au samedi de 12h00 à 19h00.
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<div class="categories-titles">
    <#list category.getSiblings() as cat>
        <div class="categorie ${cat.getData()}">
            <h2 class="entry-title">${cat.title.getData()}</h2>
            <style>
                .categories-titles .categorie.${cat.getData()} h2 {
                    background-image: url(${cat.illustrationtitre.getData()});
                }
            </style>
        </div>
    </#list>
</div>