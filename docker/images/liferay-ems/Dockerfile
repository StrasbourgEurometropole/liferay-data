# Image sur laquelle se base les opérations suivantes (@see https://hub.docker.com/r/liferay/dxp)
FROM liferay-vanilla

# Chemin vers les éléments "constants" ajoutés à l'image (scripts, fichiers d'install ou autres)
ENV SOURCES_PATH=./sources

# Copie du script permettant d'attendre les autres services avant démarrage du serveur
COPY $SOURCES_PATH/wait-for-it.sh $LIFERAY_HOME/wait-for-it.sh
RUN chmod 755 $LIFERAY_HOME/wait-for-it.sh

# Copie du script de lancement de Liferay utilisant wait-for-it
COPY $SOURCES_PATH/run.sh $LIFERAY_HOME/run.sh
RUN chmod 755 $LIFERAY_HOME/run.sh

# TODO : Installation de Javamelody

# TODO : Vérifier le bon fonctionnement de ClamAV
RUN apk add clamav

# TODO : Création des dossiers d'import

# TODO : Installation des certificats