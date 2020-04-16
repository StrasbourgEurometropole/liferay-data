# Migration base de données CE 7.0 > DXP 7.2

Le processus de migration de base de données permet de transformer un dump de base de données CE 7.0 en dump DXP 7.2.

## Fichiers en entrée :

Les fichiers suivants sont les entrées du process et doivent être déplacés :

* Dans le répertoire `images/mysql-upgrade/sources` :
    * Le dump DB Liferay 7.0 au format `.sql` ou `.dump` 
    * Le script pre-upgrade à jouer avant la migration : `pre-upgrade.sql`
* Dans le répertoire `images/liferay-vanilla/sources` : 
    * Liferay DXP 7.2 SP4 : `liferay-dxp-tomcat-7.2.10-dxp-4-20200121112425051.tar.gz`

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

* Créer l'image ElasticSearch
    * Se placer dans le répertoire `images/elasticsearch-ems`
    * Exécuter la commande suivante :
        ```shell
        $ docker image build -t elasticsearch-ems .
        ```
* Créer l'image Liferay
    * Placer dans le répertoire `images/mysql-ems/sources` :
        * le script `wait-for-it.sh` permettant de tester la disponibilité d'autres noeuds.
        * le certificat `certigna-authority-2015-2025.cer`
        * le certificat `apiDailymotion.cer`
        * le certificat `apiYoutube.cer`
    * Se placer dans le répertoire `images/liferay-ems`
    * Exécuter la commande suivante où :
        ```shell
        $ docker image build -t liferay-ems .
        ```

## Fichiers d'entrées

* elasticsearch-ems dans le répertoire `configs/elasticsearch-ems` :
    * `synonyms.txt` pour la description des synonymes.
* liferay-ems dans le répertoire `configs/liferay-ems` (le dossier se divise ensuite en environnement pour définir des configurations différentes):
    * `deploy` dossier dans lequel placer tous les éléments que l'on souhaite déployer au démarrage de Liferay.
    * `files/tomcat/bin/setenv.sh` pour définir les propriétés de lancement du serveur.
    * `files/portal-ext.properties` pour définir les proprités Liferay et EMS.
    * `files/portal-setup-wizzard.properties` pour définir les proprités de connection à la BDD et d'administration par defaut de Liferay.
    * `files/osgi/configs` dossier dans lequel placer tous les fichiers de config osgi.
    * `files/osgi/war/liferay-javamelody-hook-1.82.0.0.war` librairie javamelody (@see https://github.com/javamelody/javamelody/wiki/LiferayPlugin).
    * `scripts/wait-for-dependencies.sh` script lancé avant le serveur permettant d'attendre les dépendances MySQL et ElasticSearch

## Exécution

Pour lancer la totalité des services, lancer la commande suivante où :
    * `VAR_DATA` est le chemin vers le répertoire de données persistantes.

```shell
$ DATA=VAR_DATA docker-compose up -d

--> $ DATA=/data docker-compose up -d
```

Suivre les logs via la commande :

```shell
$ docker-compose logs -f
```

# Commandes Docker utiles

## Commandes générales sur les conteneurs

1. **Voir l'aide des commandes docker pour les conteneurs** :

    ```shell
    $ docker container -h
    ```

2. **Voir tous les conteneurs lancés** :

    ```shell
    $ docker ps
    ```

3. **Voir tous les conteneurs (même ceux stoppés)** :

    ```shell
    $ docker ps -a
    ```

4. **Rentrer en bash dans un conteneur** en remplaçant :
    * `CONTAINER_ID` par l'ID obtenu via la commande `docker ps`

    ```shell
    $ docker container exec -it CONTAINER_ID bash
    ```

5. **Lancer un conteneur simple** en remplaçant :
    * `IMAGE` le nom de l'image que l'on prend pour base pour lancer le conteneur (voir les images sur un registry comme https://hub.docker.com/)
    * `PORTS` par l'association de ports hôte:conteneur (ex : "80:8080" pour rediriger le port 8080 du conteneur sur le port 80 de l'hôte)

    ```shell
    $ docker container run -p PORTS IMAGE 
    ```

    **notes** : voir les autres options via la commande `docker container run -h` ou sur https://docs.docker.com/engine/reference/commandline/container_run/

6. **Stopper un conteneur** en remplaçant :
    * `CONTAINER_ID` par l'ID obtenu via la commande `docker ps`

    ```shell
    $ docker container stop CONTAINER_ID
    ```

7. **Supprimer un conteneur** en remplaçant :
    * `CONTAINER_ID` par l'ID obtenu via la commande `docker ps`

    ```shell
    $ docker container rm CONTAINER_ID
    ```

    **notes** : peut nécessiter l'option `-f` pour forcer la suppression d'un conteneur déjà démarré. 

8. **Relancer un conteneur** en remplaçant :
    * `CONTAINER_ID` par l'ID obtenu via la commande `docker ps`

    ```shell
    $ docker container restart CONTAINER_ID
    ```

9. **Consulter les logs d'un conteneur** en remplaçant :
    * `CONTAINER_ID` par l'ID obtenu via la commande `docker ps`

    ```shell
    $ docker container logs CONTAINER_ID
    ```

    **notes** : lancer avec l'option `-f` pour les suivre en continu et faire `Ctrl + C` pour arrêter le suivi.

10. **Inspecter les informations du conteneur** en remplaçant :
    * `CONTAINER_ID` par l'ID obtenu via la commande `docker ps`

    ```shell
    $ docker container inspect CONTAINER_ID
    ```

## Commandes spcéifiques sur les conteneurs

1. **Effectuer un dump à partir d'un conteneur MySQL lancé** en remplaçant :
    * `CONTAINER_ID` par l'ID obtenu via la commande `docker ps`
    * `USER_NAME` est le nom d'utilisateur MySQL
    * `USER_PSW` est le mot de passe d'utilisateur MySQL
    * `SCHEMA_NAME` est le nom du schéma à extraire
    * `DEST_PATH` est le chemin de la machine hôte (nom du fichier .sql inclu) où mettre le dump

    ```shell
    $ docker exec CONTAINER_ID /usr/bin/mysqldump -u USER_NAME --password=USER_PSW SCHEMA_NAME > DEST_PATH
    ```

## Commandes générales docker-compose

Les commandes docker-compose sont toujours à lancer dans le répertoire où se situe ledit fichier `docker-compose.yml`

1. **Voir l'aide des commandes docker-compose** :

    ```shell
    $ docker-compose -h
    ```

2. **Lancer tous les "services" d'un docker-compose** :

    **notes** : cette commande va créer tous les conteneurs et réseaux contenus dans le docker-compose.

    ```shell
    $ docker-compose up -d
    ```

3. **Couper tous les "services" d'un docker-compose** :

    **notes** : cette commande va supprimer tous les conteneurs et réseaux contenus dans le docker-compose. La commande `docker-compose kill` possède les mêmes attributions mais effectue l'équivalent d'un `--force` pour les conteneurs récidivistes.

    ```shell
    $ docker-compose down
    ```

4. **Stopper tous les "services" d'un docker-compose** :

    **notes** : cette commande va couper tous les conteneurs et réseaux contenus dans le docker-compose mais ils pourront être relancés là où ils en étaient.

    ```shell
    $ docker-compose stop
    ```

5. **Relancer, après arrêt, tous les "services" d'un docker-compose** :

    **notes** : cette commande ne relancera que les conteneurs ayant été stoppés au préalable, elle ne créera rien de nouveau et ne peut donc pas remplacer la commande `docker-compose up` pour un nouveau lancement.

    ```shell
    $ docker-compose start -d
    ```

9. **Consulter les logs d'un docker-compose** 

    ```shell
    $ docker container logs
    ```

    **notes** : lancer avec l'option `-f` pour les suivre en continu et faire `Ctrl + C` pour arrêter le suivi.

