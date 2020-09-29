if [ -z "$1" ]
then
    echo "Please, enter a destination path in parameter\e[0m"
else
    deployPath="$1"

    echo "\e[35m11# Create directory tree if not exits\e[0m"
    mkdir -p \
        $deployPath/liferay/doclib \
        $deployPath/liferay/logs/active/liferay \
        $deployPath/liferay/logs/active/tomcat \
        $deployPath/liferay/logs/backup/liferay \
        $deployPath/liferay/logs/backup/tomcat \
        $deployPath/liferay/javamelody \
        $deployPath/liferay/imports/agenda/import \
        $deployPath/liferay/imports/agenda/batch \
        $deployPath/liferay/imports/gtfs/import \
        $deployPath/liferay/imports/gtfs/batch \
        $deployPath/portainer \
        $deployPath/maintenance

    echo "\e[35m13# Adapt vm.max_map_count host config for ElasticSearch\e[0m"
    sudo sysctl -w vm.max_map_count=262144
fi