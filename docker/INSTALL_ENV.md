# Installation de l'environnement

Décrit l'ensemble des opérations à réaliser pour obtenir un environnent adequat afin de tester l'ensemble des éléments de ce POC.

## Prérequis

Possèder deux machines CentOS avec droits `root`.

## Mise à jour et configuration de la CentOS

Les instructions suivantes sont à faire sur les deux machines.

Mise à jour des paquets :

```shell
$ yum update
```

## Installation de Docker

Les instructions suivantes sont à faire sur les deux machines.

Ajout, téléchargement et installation de la dernière version du package Docker :

```shell
$ curl –fsSL https://get.docker.com/ | sh
```

Lancement du deamon Docker :

```shell
$ systemctl start docker
```

Mettre en place le lancement de Docker au démarrage de la machine :

```shell
$ systemctl enable docker
```

## Installation de Docker-compose

Ajout, téléchargement et installation de la dernière version du package Docker-compose :

```shell
$ curl -L "https://github.com/docker/compose/releases/download/1.23.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
```

Ajout des droits sur l'éxécutable :

```shell
$ chmod +x /usr/local/bin/docker-compose
```

## Mise en place du Swarm

Choisir le noeud destiné à être `Manager` et initialiser le `Swarm` dessus :

```shell
$ docker swarm init
```

## Adapation de l'environnement pour ElasticSearch

ElasticSearch utilise le répertoire `mmapfs` pour stocker ses indices. Ce dernier sur CentOS dispose d'une limite de stockage trop basse pour ElasticSearch et demande un ajustement avec la commande suivante (à faire sur les deux environnements) :

```shell
sysctl -w vm.max_map_count=262144
```

## Création de l'aborescence des stacks

Un shellscript `build-env.sh` a été disposé à la racine du dossier Docker. Il permet de créer les différents dossiers utiles (+droits) à la persitence des données des services qui seront lancés.

Pour lancer ce script, lancer la commande suivante où `DATA_PATH` est le chemin où seront disposés les répertoires sur la machine locale :

```shell
sh buil-env.sh DATA_PATH

--> sh buil-env.sh /data/liferay-vanilla-data
--> sh buil-env.sh /data/liferay-custom-data
```

## `Ready to start !`