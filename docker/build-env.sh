if [ -z "$1" ]
then
    echo "Please, enter a destination path in parameter"
else
    deployPath="$1"

    echo "Create directory tree if not exits"
    mkdir -p \
        $deployPath/esdata \
        $deployPath/liferay/deploy \
        $deployPath/liferay/doclib \
        $deployPath/mysql/lib \
        $deployPath/mysql/log \
        $deployPath/portainer
    
    echo "Change /esdata permission"
    chmod g+rwx $deployPath/esdata
    chgrp 1000 $deployPath/esdata

    echo "Adapt vm.max_map_count host config for ElasticSearch"
    sysctl -w vm.max_map_count=262144
fi