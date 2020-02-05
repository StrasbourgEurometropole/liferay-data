ARG DUMP_FILE_NAME

FROM mysql:5.7

ENV SOURCES_PATH=./sources

COPY $SOURCES_PATH/$DUMP_FILE_NAME /docker-entrypoint-initdb.d
COPY $SOURCES_PATH/pre-upgrade.sql /pre-upgrade.sql

RUN cat /pre-upgrade.sql >> /docker-entrypoint-initdb.d \
    && rm /pre-upgrade.sql