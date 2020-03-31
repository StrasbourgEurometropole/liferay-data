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
        * `LFR_TAG` est le tag de l'image créé (ex : 7.2.10-dxp-fp4-vanilla)
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
* `TAG` est le tag de l'image liNferay créé (ex : 7.2.10-dxp-fp4-vanilla).
* `DATA_PATH` est le chemin vers le répertoire de persistance.

```shell
$ DATA=7.2.10-dxp-fp4-vanilla LFR_TAG=TAG docker-compose -f dc-lfr-upgrade.yml up -d liferay-portal

--> $ DATA=/data/ems-data LFR_TAG=7.2.10-dxp-sp4-vanilla docker-compose -f dc-lfr-upgrade.yml up -d liferay-portal
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

# Lancement d'un environnement EMS complet en DXP 7.2

## Images

Images à créer :

* mysql-ems dans le dossier `images/mysql-ems`
* elasticsearch-ems dans le dossier `images/elasticsearch-ems`
* liferay-ems dans le dossier `images/liferay-ems`

* Créer l'image MySQL
    * Placer dans le répertoire `images/mysql-ems/sources` le fichier dump de la base 7.2 DXP.
    * Se placer dans le répertoire `images/mysql-ems`
    * Exécuter la commande suivante où :
        * `FILE_NAME` est le nom du fichier dump.
        ```shell
        $ docker image build --build-arg DUMP_FILE_NAME=FILE_NAME -t mysql-ems .
        ```
* Créer l'image ElasticSearch
    * Se placer dans le répertoire `images/elasticsearch-ems`
    * Exécuter la commande suivante :
        ```shell
        $ docker image build -t elasticsearch-ems .
        ```
* Créer l'image Liferay Vanilla
    * Placer dans le répertoire `images/liferay-vanilla` :
        * `liferay-dxp-tomcat-7.2.10-dxp-4-20200121112425051.tar.gz` l'archive du bundle Liferay DXP 7.2 avec tomcat
    * Se placer dans le répertoire `images/liferay-vanilla`
    * Exécuter la commande suivante où :
        ```shell
        $ docker image build -t liferay-vanilla .
        ```
* Créer l'image Liferay de l'EMS
    * Se placer dans le répertoire `images/liferay-ems`
    * Exécuter la commande suivante où :
        ```shell
        $ docker image build -t liferay-ems .
        ```

## Fichiers de configuration

* elasticsearch-ems dans le répertoire `configs/elasticsearch-ems` :
    * `elasticsearch.yml` pour les configuration d'elasticsearch.
    * `synonyms.txt` pour la description des synonymes.
* liferay-ems dans le répertoire `configs/liferay-ems` :
    * `deploy` dossier dans lequel placer tous les éléments que l'on souhaite déployer au démarrage de Liferay.
    * `files/tomcat/setenv.sh` pour définir les propriétés de lancement du serveur.
    * `files/portal-ext.properties` pour définir les proprités Liferay et EMS.
    * `files/portal-setup-wizzard.properties` pour définir les proprités de connection à la BDD et d'administration par defaut de Liferay.
    * `files/osgi/configs` dossier dans lequel placer tous les fichiers de config osgi.
    * `scripts/wait-for-dependencies.sh` script lancé avant le serveur permettant d'attendre les dépendances MySQL et ElasticSearch

## Exécution

Pour lancer la totalité des services, lancer la commande suivante où :
    * `VAR_DATA` est le chemin vers le répertoire de données persistantes.

```shell
$ DATA=VAR_DATA docker stack deploy -c docker-compose.yml stack-liferay

--> $ DATA=/data docker stack deploy -c docker-compose.yml stack-liferay
```
