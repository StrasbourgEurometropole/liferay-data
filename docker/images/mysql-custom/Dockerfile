FROM mysql:5.7

ARG DUMP_FILE_NAME
ENV SOURCES_PATH=./sources

COPY $SOURCES_PATH/$DUMP_FILE_NAME /docker-entrypoint-initdb.d