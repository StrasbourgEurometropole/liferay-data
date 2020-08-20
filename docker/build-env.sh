if [ -z "$1" ]
then
    echo "Please, enter a destination path in parameter"
else
    deployPath="$1"

    echo "1# Create directory tree if not exits"
    mkdir -p \
        $deployPath/esdata \
        $deployPath/liferay/doclib \
        $deployPath/liferay/logs/active/liferay \
        $deployPath/liferay/logs/active/tomcat \
        $deployPath/liferay/logs/backup/liferay \
        $deployPath/liferay/logs/backup/tomcat \
        $deployPath/liferay/javamelody \
        $deployPath/liferay/import/agenda \
        $deployPath/liferay/import/gtfs \
        $deployPath/portainer
    
    echo "2# Change /esdata permission"
    chmod g+rwx $deployPath/esdata
    chgrp 1000 $deployPath/esdata

    echo "3# Adapt vm.max_map_count host config for ElasticSearch"
    sysctl -w vm.max_map_count=262144
fi