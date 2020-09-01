echo -e "\n\n \e[35m1# Mise à jour des variables d'environnements\e[0m"
cat .env
export $(cat .env)

echo -e "\n\n \e[35m2# Construction de l'image Liferay\e[0m"
docker image build --build-arg DIST_PATH_VERSION=$LFR_TAG_VERSION -t $REGISTRY_ADDRESS\liferay-ems:$LFR_TAG_VERSION ./images/liferay-ems

echo -e "\n\n \e[35m3# Push sur le registry de l'image Liferay\e[0m"
docker image push $REGISTRY_ADDRESS\liferay-ems:$LFR_TAG_VERSION

echo -e "\n\n \e[35m4# Lancement de la stack EMS\e[0m"
docker stack deploy -c docker-compose.yml ems-stack --with-registry-auth

echo -e "\n\n \e[35m4# Visualisation des services\e[0m"
docker service ls

echo -e "\n\n"