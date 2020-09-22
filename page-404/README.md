# Installation des pages 404

## Disposition du fichier 404-error.jsp

Le fichier se place dans l'arborescence suivante :

* bundles
  * tomcat-XXX
    * webapps
      * ROOT
        * html
          * portal
            * `404-error.jsp`

## Configuration du fichier 

Placer la propriété suivante dans le fichier `portal-ext.properties` :

```properties
# Redirect to this resource if the user requested a friendly URL that does
# not exist. Leave it blank to display nothing.
#
# Note: For backward compatibility, this overrides the property
# "layout.show.http.status" for the 404 status code.
#
layout.friendly.url.page.not.found=/html/portal/404-error.jsp
```

## Mise en place d'une page 404

Sur chaque site où la page est désirée, créer une nouvelle page publique ayant comme `friendlyURL` l'addresse `/page_404`.

Placer ensuite les contenus web de votre choix pour illustrer le propos d'une mauvaise recherche.