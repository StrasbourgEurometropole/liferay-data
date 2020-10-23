echo -e "\n\n \e[35m1# Récupération des variables d'environnements\e[0m"
cat .env
export $(cat .env)

echo -e "\n\n \e[35m2# Construction de l'image ElasticSearch\e[0m"
if [ "$CUR_ENV" = "RECETTE" ]
then
    docker image build -t $REGISTRY_ADDRESS\elasticsearch-ems ./images/elasticsearch-ems
else
    echo "Step skipped in $CUR_ENV"
fi

echo -e "\n\n \e[35m3# Push sur le registry de l'image ElasticSearch\e[0m"
if [ "$CUR_ENV" = "RECETTE" ]
then
    docker image push $REGISTRY_ADDRESS\elasticsearch-ems
else
    echo "Step skipped in $CUR_ENV"
fi

echo -e "\n\n \e[35m4# Construction de l'image Liferay\e[0m"
if [ "$CUR_ENV" = "RECETTE" ]
then
    docker image build --build-arg DIST_PATH_VERSION=$LFR_TAG_VERSION -t $REGISTRY_ADDRESS\liferay-ems:$LFR_TAG_VERSION ./images/liferay-ems
else
    echo "Step skipped in $CUR_ENV"
fi

echo -e "\n\n \e[35m5# Push sur le registry de l'image Liferay\e[0m"
if [ "$CUR_ENV" = "RECETTE" ]
then
    docker image push $REGISTRY_ADDRESS\liferay-ems:$LFR_TAG_VERSION
else
    echo "Step skipped in $CUR_ENV"
fi

echo -e "\n\n \e[35m6# Pull de l'image Liferay à partir du registry\e[0m"
if [ "$CUR_ENV" = "RECETTE" ]
then
    echo "Step skipped in $CUR_ENV"
else
    docker image pull $REGISTRY_ADDRESS\liferay-ems:$LFR_TAG_VERSION
fi