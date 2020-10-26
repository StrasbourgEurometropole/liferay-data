#!/bin/bash
echo -e "\n\n \e[35m1# Récupération des variables d'environnements\e[0m"
cat .env
export $(cat .env)

echo -e "\n\n \e[35m2# Arrêt des services\e[0m"
docker stack rm ems-stack

echo -e "\n\n \e[35m3# Backup de la BDD\e[0m"
echo -e "Export du dump  $DB_BACKUPS_PATH/$MYSQL_DB\_$(date +%Y%m%d-%H%M).sql"
mysqldump -u $MYSQL_USER --password --opt $MYSQL_DB > $DB_BACKUPS_PATH/$MYSQL_DB\_$(date +%Y%m%d-%H%M).sql