echo -e "\n\n 1# Mise à jour des variables d'environnements"
cat .env
export $(cat .env)

echo -e "\n\n 2# Construction de l'image Liferay"
docker image build --build-arg DIST_PATH_VERSION=$LFR_TAG_VERSION -t $REGISTRY_ADDRESS\liferay-ems:$LFR_TAG_VERSION ./images/liferay-ems

echo -e "\n\n 3# Push sur le registry de l'image Liferay"
docker image push $REGISTRY_ADDRESS\liferay-ems:$LFR_TAG_VERSION

echo -e "\n\n 4# Lancement de la stack EMS"
docker stack deploy -c docker-compose.yml ems-stack --with-registry-auth