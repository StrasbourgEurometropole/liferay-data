# Image sur laquelle se base les opérations suivantes (@see https://hub.docker.com/r/liferay/dxp)
FROM liferay/dxp:7.2.10-dxp-6

# Chemin vers les éléments "constants" ajoutés à l'image (scripts, fichiers d'install ou autres)
ENV SOURCES_PATH=./sources
ENV KEY_PATH=./activation-key
ENV DIST_PATH=./dist

# Changement d'utilisateur sur root pour les opérations nécéssitants ses droits 
USER root

# Création des dossiers temporaires
RUN mkdir -p /tmp/certificates

# Création du repertoire de données
RUN mkdir /data \
    && chown -R liferay:liferay /data

# Copie du script permettant d'attendre les autres services avant démarrage du serveur
COPY --chown=liferay:liferay $SOURCES_PATH/wait-for-it.sh /
RUN chmod +x /wait-for-it.sh

# Installation de ClamAV
RUN apk add --upgrade clamav-daemon \
    && mkdir /run/clamav && chown clamav:clamav /run/clamav \
    && freshclam \
    && ln -s /usr/bin/clamdscan /usr/bin/clamscan \
    && /usr/sbin/clamd -c /etc/clamav/clamd.conf

# Installation des certificats
COPY --chown=liferay:liferay $SOURCES_PATH/*.cer /tmp/certificates/
RUN keytool -keystore /usr/lib/jvm/zulu11/lib/security/cacerts -importcert -alias certigna-authority-2015-2025 \
        -file /tmp/certificates/certigna-authority-2015-2025.cer -storepass changeit -noprompt \
    && keytool -keystore /usr/lib/jvm/zulu11/lib/security/cacerts -importcert -alias aipDailymotion \
        -file /tmp/certificates/apiDailymotion.cer -storepass changeit -noprompt \
    && keytool -keystore /usr/lib/jvm/zulu11/lib/security/cacerts -importcert -alias apiYoutube \
        -file /tmp/certificates/apiYoutube.cer -storepass changeit -noprompt \
    && keytool -keystore /usr/lib/jvm/zulu11/lib/security/cacerts -importcert -alias ZscalerIntermediateRootCA \
        -file /tmp/certificates/ZscalerIntermediateRootCA.cer -storepass changeit -noprompt \
    && rm -rf /tmp/certificates

# Repositionnement sur l'utilisateur liferay
USER liferay

# Création des dossiers utils pour la persistance et les imports
RUN mkdir -p /data/import/agenda/archives \
    && mkdir -p /data/liferay/document_library

# Dossier dans ./dist contenant les livrables
ARG DIST_PATH_VERSION

# Copie des livrables dans deploy
COPY --chown=liferay:liferay $DIST_PATH/$DIST_PATH_VERSION/ /opt/liferay/deploy/