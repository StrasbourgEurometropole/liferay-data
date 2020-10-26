#!/bin/bash
echo -e "\n\n \e[35m1# Récupération des variables d'environnements\e[0m"
cat .env
export $(cat .env)

echo -e "\n\n \e[35m2# Lancement de la stack EMS\e[0m"
docker stack deploy -c docker-compose.yml ems-stack --with-registry-auth

echo -e "\n\n \e[35m3# Visualisation des services\e[0m"
docker service ls

echo -e "\n\n"