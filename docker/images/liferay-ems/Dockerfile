# Image sur laquelle se base les opérations suivantes (@see https://hub.docker.com/r/liferay/dxp)
FROM liferay/dxp:7.2.10-dxp-4

# Chemin vers les éléments "constants" ajoutés à l'image (scripts, fichiers d'install ou autres)
ENV SOURCES_PATH=./sources

# Changement d'utilisateur pour root (évite le problème de permission sur les fichiers déposés dans le conteneur)
USER root

# Copie du script permettant d'attendre les autres services avant démarrage du serveur
COPY $SOURCES_PATH/wait-for-it.sh /
RUN chmod +x /wait-for-it.sh

# TODO : Installation de Javamelody

# TODO : Vérifier le bon fonctionnement de ClamAV
RUN apk add clamav

# TODO : Création des dossiers d'import

# TODO : Installation des certificats