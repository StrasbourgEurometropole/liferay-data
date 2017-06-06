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
    <a class="btn-comment boutique-popup-link" href="${lien.getData()}" title="Comment commander ?">Comment commander <span>?</span></a>
</p>

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