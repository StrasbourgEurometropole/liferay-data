FROM docker.elastic.co/elasticsearch/elasticsearch-oss:6.8.3

ENV ES_PATH=/usr/share/elasticsearch

USER root

RUN $ES_PATH/bin/elasticsearch-plugin install analysis-icu \
    && $ES_PATH/bin/elasticsearch-plugin install analysis-kuromoji \
    && $ES_PATH/bin/elasticsearch-plugin install analysis-smartcn \
    && $ES_PATH/bin/elasticsearch-plugin install analysis-stempel

USER elasticsearch

EXPOSE 9200 9300