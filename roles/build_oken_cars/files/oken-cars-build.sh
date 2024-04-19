#!/bin/sh
echo "==================================="
echo "==== STAGE 1 - SERVER CONFIGURATION ===="
# Check Server Hostname
pwd
hostname
hostnamectl
# Check IP address, Internet and SonarQube console access (IPv4)
ip -4 a | grep ens18
ping -c2 8.8.8.8
ping -c2 10.10.10.20 -p 9000
ping -c2 10.10.10.212
ping -c2 10.10.10.212 -p 8080
# Check user location on the server
pwd
# Check user groups
id oken
# Check installed tools
# Check java version
java --version
# Check maven version
mvn --version
# Check node version
node -v
# Check npm version
npm -v
# Check docker version
docker --version
# Check docker compose version
docker compose version
# Display Path environment variable
echo $PATH
echo "=============================================="
echo "==== STAGE 2 - VERIFY EXISTING REPOSITORY ===="
# Check main files and folders
pwd
ls -lt
echo "====================================="
echo "==== STAGE 3 - GENERATE ARTEFACT ===="
# Change directory
cd spring/
# Check main files and folders
ls -lt
# Generate artefact
mvn clean install -DskipTests
echo "========================================"
echo "==== STAGE 4 - BUILD OKEN CARS APPLICATION ===="
# Go to project's main files and folders
cd  ../
# Check main files and folders
pwd && ls -lt
# Build App
docker compose up -d
echo "======================================"
echo "==== STAGE 5 - CHECK INSTALLATION ===="
# Check user location and main files and folders
pwd && ls -lt && cd
# Check if Docker is loaded
systemctl status docker | grep Loaded
# Check if Docker is activated
systemctl status docker | grep Active
# Check Docker launched images
docker images | grep oken-cars-web-apache
docker images | grep oken-cars-web-angular
docker images | grep oken-cars-web-spring
# Check Docker volumes
docker volume ls
# Check Docker launched containers
docker ps | grep apache
docker ps | grep angular
docker ps | grep spring
# Delete oken-cars app building script
rm oken-cars-web/oken-cars-build.sh
cd
pwd && ls -alt
ls -alt oken-cars-web
