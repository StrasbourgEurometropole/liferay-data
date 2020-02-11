# Migration base de données CE 7.0 > DXP 7.2

Le processus de migration de base de données permet de transformer un dump de base de données CE 7.0 en dump DXP 7.2.

## Fichiers en entrée :

Les fichiers suivants sont les entrées du process et doivent être déplacés :

* Dans le répertoire `images/mysql-upgrade/sources` :
    * Le dump DB Liferay 7.0 au format `.sql` ou `.dump` 
    * Le script pre-upgrade à jouer avant la migration : `pre-upgrade.sql`
* Dans le répertoire `images/liferay-vanilla/sources` : 
    * Liferay DXP 7.2 SP4 : `liferay-dxp-tomcat-7.2.10-dxp-4-20200121112425051.tar.gz`
    * MYSQL Connector : `mysql-connector-java-8.0.19.jar`

## Images

Images à créer :

* mysql-upgrade dans le dossier `images/mysql-upgrade`
* liferay-portal dans le dossier `images/liferay-vanilla`

* Créer l'image MySQL
    * Placer dans le répertoire `images/mysql-upgrade/sources` le fichier dump de la base à migrer.
    * Placer dans le fichier `images/mysql-upgrade/sources/pre-upgrade.sql` les requêtes de netoyage à effectuer avant la migration.
    * Se placer dans le répertoire `images/mysql-upgrade`
    * Exécuter la commande suivante où :
        * `FILE_NAME` est le nom du fichier dump.
        ```shell
        $ docker image build --build-arg DUMP_FILE_NAME=FILE_NAME -t mysql-upgrade:ems .
        ```
* Créer l'image Liferay
    * Se placer dans le répertoire `images/liferay-vanilla`
    * Exécuter la commande suivante où :
        * `LFR_TAG` est le tag de l'image créé (ex : 7.2.10-dxp-sp4-vanilla)
        ```shell
        $ docker image build -t liferay-portal:LFR_TAG .
        ```

## Exécution

### Démarrage de l'image mysql-upgrade

Se placer dans le répertoire dans le répetoire courrant.

Lancer l'image mysql-upgrade via docker-compose.

```shell
$ MYSQL_TAG=ems docker-compose -f dc-lfr-upgrade.yml up -d mysql
```

Attendre que l'import automatique du dump soit fait dans l'image Docker.

Pour cela, vérifier que dans le log soit visible pour la seconde fois le texte `mysqld: ready for connections.` via la commande :

```shell
$ docker-compose -f dc-lfr-upgrade.yml logs -f
```

C'est uniquement lors de la seconde apparition dudit texte que le dump d'entrée aura été totalement importé.

Sortir du log dès le message apparu avec `Ctrl+C`

### Démarrage de l'image liferay-portal et upgrade de la base de données

Se placer dans le répertoire dans le répetoire courrant.

Créer dans le répertoire courant le fichier `output/upgrade.log`.

```shell
$ touch output/upgrade.log
```

Lancer l'image Liferay avec la commande suivante où :
* `TAG` est le tag de l'image créé (ex : 7.2.10-dxp-sp4-vanilla)

```shell
$ LFR_TAG=TAG docker-compose -f dc-lfr-upgrade.yml up -d liferay-portal
```

Suivre l'évolution de l'upgrade via la commande :

```shell
$ docker-compose -f dc-lfr-upgrade.yml logs -f
```

Le script peut mettre plus d'une heure à s'exécuter.

### Extraction du dump de la base migrée

Pour extraire le dump de la base migrée, exécuter la commande suivante en remplaçant `CONTAINER_ID` par l'identifiant du conteneur `mysql-upgrade` retrouvé via `docker ps`.

```shell
$ docker exec CONTAINER_ID /usr/bin/mysqldump -u liferay --password=sully liferay-db > output/migrated-dump.sql
```

Le fichier `migrated-dump.sql`se trouve désormais dans le répertoire `output` du répertoire courant.

# Lancer la stack complète

## Adapation de l'environnement pour ElasticSearch

ElasticSearch utilise le répertoire `mmapfs` pour stocker ses indices. Ce dernier sur CentOS dispose d'une limite de stockage trop basse pour ElasticSearch et demande un ajustement avec la commande suivante (à faire sur les deux environnements) :

```shell
sysctl -w vm.max_map_count=262144
```

## Mise à jour de la configuration

Modifier le fichier `configs/liferay-custom/portal-ext.properties` pour qu'il corresponde à l'environnement sur lequel le déploiement est effectué.

## Création des images

* Créer l'image ElasticSearch
    * Se placer dans le répertoire  `/images/elasticsearch` et lancer `$ docker image build -t elasticsearch:6.8.6-liferay .`
* Créer l'image Liferay Vanilla (Liferay sans modules)
    * Placer dans le répertoire `images/liferay-vanilla/sources` :
        * `liferay-dxp-tomcat-7.2.10.1-sp1-20191009103614075.tar.gz` : Bundle Liferay DXP 7.2 avec serveur Tomcat.
        * `mysql-connector-java-8.0.17.jar` : Connecteur java MySQL.
    * Se placer dans le répertoire `preim-docker-stack/images/liferay-vanilla`
        * Exécuter `$ docker image build -t liferay-portal:7.2.10-dxp-sp1-vanilla --build-arg LFR_ENV=ENVIRONEMENT .` où `ENVIRONNEMENT` vaut `dev`, `prod` ou `preprod` selon l'environnement à créer
    * Se placer dans le répertoire `preim-docker-stack/images/mysql/` et lancer `$ docker image build -t mysql-custom .`
* Créer l'image Liferay Custom
    * Depuis la racine du repository, lancer `build.sh clean` puis `build.sh`
    * Se placer dans le répertoire `preim-docker-stack/images/liferay-custom/` et lancer `$ docker image build -t liferay-portal:7.2.10-dxp-sp1-preim .`

## Lancement de l'environnement

* Préparer l'environnement en lançant la commande `sh buil-env.sh DATA_PATH` où `DATA_PATH` correspond au chemin du file system où les fichiers persistants seront stockés et où il sera possible de déposer des fichiers à destination des conteneurs (pour le déploiement à chaud par exemple). Dans les commandes suivantes, remplacer `DATA_PATH` par la valeur passée en paramètre ici
    * La document library provenant de la version 6.2 de Liferay devra alors être placée dans le répertoire `DATA_PATH/lfr-dl-volume`
* Lancer la stack Portainer
    * Lancer `DATA=DATA_PATH docker stack deploy -c dc-portainer.yml portainer`
* Lancer la stack MySQL / ElasticSearch / Traefik
    * `DATA=DATA_PATH docker stack deploy -c dc-lfr-base.yml liferay-base`
* Lancer la stack Liferay
    * `DATA=DATA_PATH FRONTEND=DNS docker stack deploy -c dc-lfr-portal-custom.yml --prune liferay-portal` où DNS correspond au DNS qui sera utilisé par Liferay
