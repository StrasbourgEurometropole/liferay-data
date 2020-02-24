FROM openjdk:8-jdk

ENV SOURCES_PATH=./sources
ENV LIFERAY_HOME=/liferay
ENV CATALINA_HOME=$LIFERAY_HOME/tomcat-8.0.53

RUN apt-get -qq update \
    && apt-get -qq install telnet \
    && apt-get -qq clean

ENV LIFERAY_BUNDLE=liferay-dxp-digital-enterprise-tomcat-7.0.10.12-sp12-20191014182832691.tar.gz
COPY $SOURCES_PATH/$LIFERAY_BUNDLE /
RUN set -x \
    && mkdir /tmp-liferay \
    && tar xvzf $LIFERAY_BUNDLE -C /tmp-liferay \
    && mv $(ls -d /tmp-liferay/* | head -n 1) $LIFERAY_HOME \
    && rm /$LIFERAY_BUNDLE \
    && rm -rf /tmp-liferay \
    && mkdir -p $LIFERAY_HOME/deploy \
    && mkdir -p /data/liferay/document_library \
    && chmod 755 /data \
    && rm -rf $LIFERAY_HOME/osgi/state \
    && rm -rf $CATALINA_HOME/temp/* \
    && rm -rf $CATALINA_HOME/work/* \
    && mkdir -p $LIFERAY_HOME/osgi/modules \
    && mkdir -p $LIFERAY_HOME/osgi/war

ENV LIFERAY_PATCHING_TOOL=patching-tool-2.0.15.zip
COPY $SOURCES_PATH/$LIFERAY_PATCHING_TOOL /
RUN set -x \
    && unzip -o $LIFERAY_PATCHING_TOOL -d $LIFERAY_HOME \
    && rm /$LIFERAY_PATCHING_TOOL

ENV LFR_FIX_PACK=liferay-fix-pack-de-89-7010.zip
COPY $SOURCES_PATH/$LFR_FIX_PACK $LIFERAY_HOME/patching-tool/patches/
RUN $LIFERAY_HOME/patching-tool/patching-tool.sh install \
    && rm -rf $LIFERAY_HOME/osgi/state/*

ENV MYSQL_CONNECTOR=mysql-connector-java-5.1.47.jar
COPY $SOURCES_PATH/$MYSQL_CONNECTOR $CATALINA_HOME/lib/ext/mysql.jar

COPY $SOURCES_PATH/run.sh $LIFERAY_HOME/run.sh
RUN chmod 755 $LIFERAY_HOME/run.sh

EXPOSE 8080/tcp 11311/tcp

ENTRYPOINT ["sh", "/liferay/run.sh"]