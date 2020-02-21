# Migration base de données CE 7.0 > DXP 7.0

Le processus de migration de base de données permet de transformer un dump de base de données CE 7.0 en dump DXP 7.0.

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