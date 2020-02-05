ARG DUMP_FILE_NAME

FROM mysql:5.7

ENV SOURCES_PATH=./sources

COPY $SOURCES_PATH/$DUMP_FILE_NAME /docker-entrypoint-initdb.d