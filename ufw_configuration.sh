#!/bin/bash

# ufw_configuration.sh
# Affiche toutes les connexions réseau et les statistiques
netstat -an
# Affiche les ports en écoute sur la machine
ss -lnt
# Vérifier si les ports sont utilisés
sudo netstat -tulpn | grep :80
sudo netstat -tulpn | grep :443
sudo netstat -tulpn | grep :8081
sudo netstat -tulpn | grep :5432
sudo netstat -tulpn | grep :30303
sudo netstat -tulpn | grep :4200
# Affiche et arrête les informations sur les connexions réseau
# (processus, utilisateurs, etc.) sur le port 80
sudo lsof -i :80 && sudo fuser -k 80/tcp
# Même démarche sur le port 8000
sudo lsof -i :8000 && sudo fuser -k 8000/tcp
# Même démarche sur le port 8081
sudo lsof -i :8081 && sudo fuser -k 8001/tcp
# Même démarche sur le port 443
sudo lsof -i :443 && sudo fuser -k 443/tcp
# Même démarche sur le port 5432
sudo lsof -i :5432 && sudo fuser -k 5432/tcp
# Même démarche sur le port 30303
sudo lsof -i :30303 && sudo fuser -k 30303/tcp
# Même démarche sur le port 4200
sudo lsof -i :4200 && sudo fuser -k 4200/tcp
# Affiche les services activés au démarrage du système
update-rc.d -n -v show
# Affiche l'état des services avec leurs numéros d ordre et statuts
service --status-all | grep ufw
# Affiche l'état du pare-feu UFW avec les règles actuelles
sudo ufw status verbose
# Ouvrir le port 80/tcp (HTTP) avec UFW
sudo ufw allow 80/tcp comment 'Permettre les connexions HTTP (port 80) pour apache-app'
# Ouvrir le port 443/tcp (HTTPS) avec UFW
sudo ufw allow 443/tcp comment 'Permettre les connexions HTTPS (port 443) en prévision des certificats SSL'
# Ouvrir le port 8000/tcp avec UFW
sudo ufw allow 8000/tcp comment 'Permettre les connexions sur le port 8000 pour oken-cars-wweb'
# Ouvrir le port 8081/tcp avec UFW
sudo ufw allow 8081/tcp comment 'Permettre les connexions sur le port 8081 pour spring-app'
# Ouvrir le port 5432/tcp avec UFW
sudo ufw allow 5432/tcp comment 'Permettre les connexions PostgreSQL (port 5432) pour db_host'
# Ouvrir le port 30303/tcp avec UFW
sudo ufw allow 30303/tcp comment 'Permettre les connexions sur le port 30303 '
# Ouvrir le port 4200/tcp avec UFW
sudo ufw allow 4200/tcp comment 'Permettre les connexions sur le port 4200 pour angular-app'
# Mettre à jour les règles UFW
# Applique les nouvelles règles
sudo ufw --force enable
# Vérifier les règles après la mise à jour
sudo ufw status numbered
