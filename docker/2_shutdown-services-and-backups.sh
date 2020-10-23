echo -e "\n\n \e[35m1# Arrêt des services\e[0m"
docker stack rm ems-stack

echo -e "\n\n \e[35m2# Backup de la BDD\e[0m"
sudo mysqldump --opt $MYSQL_DB > $DB_BACKUPS_PATH/$MYSQL_DB_$(date +%Y%m%d-%H%M).sql