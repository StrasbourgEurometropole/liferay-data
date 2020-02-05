ARG LFR_TAG

FROM liferay-portal:$LFR_TAG

ENV SOURCES_PATH=./sources
ENV MODULES_PATH=./dist
ENV LIFERAY_HOME=/liferay
ENV CATALINA_HOME=$LIFERAY_HOME/tomcat-9.0.17

#COPY $SOURCES_PATH/libs/ext/* $CATALINA_HOME/lib/ext/
#COPY $SOURCES_PATH/libs/osgi/* $LIFERAY_HOME/osgi/modules/
#COPY $MODULES_PATH/ext/*.jar $LIFERAY_HOME/osgi/marketplace/override/
#COPY $MODULES_PATH/modules/*.jar $LIFERAY_HOME/osgi/modules/
#COPY $MODULES_PATH/themes/*.war $LIFERAY_HOME/osgi/war/