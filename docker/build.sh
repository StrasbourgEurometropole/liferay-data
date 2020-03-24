#!/bin/bash
# Build script for Liferay 7 DXP using Official Gradle Docker Image
# See https://hub.docker.com/_/gradle/ for more info

cd ..
absolutePath=$(pwd)

# Verify if docker is avalaible
if [ -z "$(which docker)" ]; then
  echo "Docker does NOT seem to be available"
  echo "Please install Docker see https://docs.docker.com/"
  exit 1
fi

# gradle assemble fait la même chose que gradle jar+ gradle war
action="deploy"
if [ "$1" = "clean" ]; then
  action="clean"
  # rm -rf bundles
  rm -rf ./configs/liferay-custom/deploy
fi

echo "Execute gradle $action"

docker run --rm -v "$PWD":$absolutePath -w $absolutePath -u `id -u $USER` -e GRADLE_USER_HOME='gradle/' --name gradle-dxp gradle:4.10.2-jdk gradle $action -PdestJar=./images/liferay-custom/dist -Duser.home=$absolutePath/gradle/

if [ $action = "deploy" ]; then
  cp -r ../bundles/osgi/modules/*.jar ./configs/liferay-custom/deploy
  cp -r ../bundles/deploy/*.war ./configs/liferay-custom/deploy
fi