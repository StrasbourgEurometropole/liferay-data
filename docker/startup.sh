# Export des variables d'environnement
export $(cat .env)

# Construction de l'image Liferay
docker image build --build-arg DIST_PATH_VERSION=$LFR_TAG_VERSION -t $REGISTRY_ADDRESS\liferay-ems:$LFR_TAG_VERSION ./images/liferay-ems

# Push sur le registry de l'image Liferay
docker image push $REGISTRY_ADDRESS\liferay-ems:$LFR_TAG_VERSION

# Lancement de la stack EMS
docker stack deploy -c docker-compose.yml ems-stack --with-registry-auth