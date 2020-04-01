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

Exposer les ports nécéssaires à l'utilisation de Docker Swarm :

```shell
$ firewall-cmd --permanent --add-port=2376/tcp
$ firewall-cmd --permanent --add-port=2377/tcp
$ firewall-cmd --permanent --add-port=7946/tcp
$ firewall-cmd --permanent --add-port=80/tcp
$ firewall-cmd --permanent --add-port=7946/udp
$ firewall-cmd --permanent --add-port=4789/udp
```

Redémarer le firewall et Docker :

```shell
$ firewall-cmd --reload
$ systemctl restart docker
```

## Installation de Docker-compose

Ajout, téléchargement et installation de la dernière version du package Docker-compose :

```shell
$ curl -L "https://github.com/docker/compose/releases/download/1.25.4/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
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

## Installation des outils utiles (si non présents par défaut)

### Git
```
yum install git
```
### Nano
```
yum install nano
```


## Création de l'aborescence des stacks

Un shellscript `build-env.sh` a été disposé à la racine du dossier Docker. Il permet de créer les différents dossiers utiles (+droits) à la persitence des données des services qui seront lancés.

Pour lancer ce script, lancer la commande suivante où `DATA_PATH` est le chemin où seront disposés les répertoires sur la machine locale :

```shell
sh buil-env.sh DATA_PATH

--> sh buil-env.sh /data/liferay-ems
```

## `Ready to start !`