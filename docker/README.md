# Migration environnement CE 7.0 > DXP 7.0

Le processus de migration d'environnement permet de transformer un environnement CE 7.0 en dump DXP 7.0 en appliquant les derniers correctifs de cette dernière.

## Fichiers en entrée :

* Dans le répertoire `images/mysql-custom/sources` :
    * Le dump DB Liferay CE 7.0 au format `.sql` ou `.dump`
* Dans le répertoire `images/liferay-upgrade-70dxp/sources` : 
    * Liferay DXP 7.0 sp10 : `liferay-dxp-digital-enterprise-tomcat-7.0.10.10-sp10-20190128202135661.zip`
    * Liferay Patching-tool 2.0.14 : `patching-tool-2.0.14.zip`
    * Liferay Fixpack 86 : `liferay-fix-pack-de-86-7010.zip`
    * MYSQL Connector : `mysql-connector-java-5.1.47.jar`

## Images

Images à créer :

* mysql-custom dans le dossier `images/mysql-custom`
* liferay-portal dans le dossier `images/liferay-upgrade-70dxp`

* Créer l'image MySQL
    * Placer dans le répertoire `images/mysql-custom/sources` le fichier dump de la base de données.
    * Se placer dans le répertoire `images/mysql-custom`
    * Exécuter la commande suivante où :
        * `FILE_NAME` est le nom du fichier dump.
        ```shell
        $ docker image build --build-arg DUMP_FILE_NAME=FILE_NAME -t mysql-custom:ems-70dxp .
        ```
* Créer l'image Liferay
    * Se placer dans le répertoire `images/liferay-upgrade-70dxp`
    * Exécuter la commande suivante
        ```shell
        $ docker image build -t liferay-portal:ems-70dxp .
        ```

## Création des dossiers de persistance Docker

Lancer la commande suivante permettant de créer les répertoires sur la machine hôtes où seront contenus les données persistantes de l'environnement DXP 7.0 où :
    * `DATA_PATH` : est le chemin du répertoire où seront disposées les données.

```shell
$ sh build-env.sh DATA_PATH

--> $ sh build-env.sh /data/ems-data
```

## Exécution

### Démarrage de l'image mysql-upgrade

Se placer dans le répertoire courant et lancer la commande suivante où :
    * `DATA_PATH` est le chemin vers le répertoire de persistance (cf. point "Création des dossiers de persistance Docker")

```shell
$ DATA=DATA_PATH docker-compose -f dc-70dxp-lfr-upgrade.yml up -d mysql

--> $ DATA=/data/ems-data docker-compose -f dc-70dxp-lfr-upgrade.yml up -d mysql
```

Attendre que le dump de la base de données soit interprété par MySQL avant de passer à l'atape suivante.
Pour cela, suivre les logs et attendre la seconde apparition de la ligne `mysqld: ready for connections.` avec la commande 

```shell
$ docker-compose -fdc-70dxp-lfr-upgrade.yml logs -f.
```

Sortir du log dès le message apparu avec `Ctrl+C`

### Démarrage de la stack Liferay

Se placer dans le répertoire courant et lancer la commande suivante où :
    * `DATA_PATH` est le chemin vers le répertoire de persistance (cf. point "Création des dossiers de persistance Docker")

```shell
$ DATA=DATA_PATH docker-compose -f dc-70dxp-lfr-upgrade.yml up -d liferay-portal

--> $ DATA=/data/ems-data docker-compose -f dc-70dxp-lfr-upgrade.yml up -d liferay-portal
```

Suivre l'évolution de l'upgrade via la commande :

```shell
$ docker-compose -f dc-70dxp-lfr-upgrade.yml logs -f
```

### Extraction du dump migré en 7.0 DXP

En guise de sécurité, il est préférable d'extraire un dump de la base de données dans le cas où la migration 7.2 ne passe pas et par extention, corrompt la bdd.

Pour extraire le dump de la base migrée, exécuter la commande suivante en remplaçant `CONTAINER_ID` par l'identifiant du conteneur `mysql-upgrade` retrouvé via `docker ps`.

```shell
$ docker exec CONTAINER_ID /usr/bin/mysqldump -u liferay --password=sully liferay-db > output/migrated-70dxp-dump.sql
```

Le fichier `migrated-70dxp-dump.sql` se trouve désormais dans le répertoire `output` du répertoire courant.

Couper le conteneur mysql via la commande suivante avec le même `CONTAINER_ID` que précédemment :

```shell
$ docker container rm CONTAINER_ID -f
```

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