FROM mysql:8

ARG DUMP_FILE_NAME
ENV SOURCES_PATH ./sources

COPY $SOURCES_PATH/$DUMP_FILE_NAME /docker-entrypoint-initdb.d
COPY $SOURCES_PATH/pre-upgrade.sql /

RUN cat /pre-upgrade.sql >> /docker-entrypoint-initdb.d/$DUMP_FILE_NAME