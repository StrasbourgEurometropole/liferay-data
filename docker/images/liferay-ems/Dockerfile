# Image sur laquelle se base les opérations suivantes (@see https://hub.docker.com/r/liferay/dxp)
FROM liferay/dxp:7.2.10-dxp-6

# Chemin vers les éléments "constants" ajoutés à l'image (scripts, fichiers d'install ou autres)
ENV SOURCES_PATH=./sources
ENV DIST_PATH=./dist

# Changement d'utilisateur pour root (évite le problème de permission sur les fichiers déposés dans le conteneur)
USER root

# Création des dossiers utiles
RUN mkdir -p /data/certificates

# Copie du script permettant d'attendre les autres services avant démarrage du serveur
COPY $SOURCES_PATH/wait-for-it.sh /
RUN chmod +x /wait-for-it.sh

# Installation de ClamAV
RUN apk add --upgrade clamav-daemon \
    mkdir /run/clamav && chown clamav:clamav /run/clamav \
    freshclam

# Installation des certificats
COPY $SOURCES_PATH/*.cer /data/certificates/
RUN keytool -keystore /usr/lib/jvm/zulu11/lib/security/cacerts -importcert -alias certigna-authority-2015-2025 \
        -file /data/certificates/certigna-authority-2015-2025.cer -storepass changeit -noprompt \
    && keytool -keystore /usr/lib/jvm/zulu11/lib/security/cacerts -importcert -alias aipDailymotion \
        -file /data/certificates/apiDailymotion.cer -storepass changeit -noprompt \
    && keytool -keystore /usr/lib/jvm/zulu11/lib/security/cacerts -importcert -alias apiYoutube \
        -file /data/certificates/apiYoutube.cer -storepass changeit -noprompt

# Dossier dans ./dist contenant les livrables
ARG DiST_PATH_VERSION

# Copie des livrables dans deploy
COPY $DIST_PATH/$DiST_PATH_VERSION/ /opt/liferay/deploy/