FROM openjdk:11.0.5-jdk

ENV SOURCES_PATH=./sources
ENV LIFERAY_HOME=/liferay
ENV CATALINA_HOME=$LIFERAY_HOME/tomcat-9.0.17

RUN apt-get -qq update \
    && apt-get -qq install telnet \
    && apt-get -qq clean

ENV LIFERAY_BUNDLE=liferay-dxp-tomcat-7.2.10-dxp-4-20200121112425051.tar.gz
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

#ENV LFR_FIX_PACK=liferay-fix-pack-dxp-1-7210.zip
#COPY $SOURCES_PATH/$LFR_FIX_PACK $LIFERAY_HOME/patching-tool/patches/
#RUN $LIFERAY_HOME/patching-tool/patching-tool.sh install \
#    && rm -rf $LIFERAY_HOME/osgi/state/*

COPY $SOURCES_PATH/run.sh $LIFERAY_HOME/run.sh
RUN chmod 755 $LIFERAY_HOME/run.sh

EXPOSE 8080/tcp 11311/tcp

ENTRYPOINT ["sh", "/liferay/run.sh"]