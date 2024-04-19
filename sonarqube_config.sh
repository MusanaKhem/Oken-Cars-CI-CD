# !/bin/bash

####################################################
### Step 1 : Preparer le système Debian / Ubuntu ###
####################################################
# 
sudo apt update && sudo apt upgrade -y
# 
sudo apt install net-tools wget unzip vim curl
# Modifier le fichier de configuration
sudo echo '

# Modification de configuration par scripting
vm.max_map_count=262144
fs.file-max=65536

' >> /etc/sysctl.conf

# Recharger le fichier de configuration du service pour appliquer les changements
sudo sysctl --system
# Redémarrage du service pour reflèter les modifications
sudo service procps restart

#################################################
## Step 2 : Installer Java sur Debian / Ubuntu ##
#################################################
# 
sudo apt install openjdk-17-jdk
# Vérifier la version installée
java -version

###########################################################
## Step 3 : Téléchargement et Configuration de SonarQube ##
###########################################################
# 
wget https://binaries.sonarsource.com/Distribution/sonarqube/sonarqube-9.9.1.69595.zip
# 
unzip sonarqube-*.zip
# 
sudo mv sonarqube-*/ /opt/sonarqube
# Créer un compte utilisateur dans le système Linux
sudo useradd -M -d /opt/sonarqube/ -r -s /bin/bash sonarqube
# Changer le propriétaire du répertoire /opt/sonarqube
sudo chown -R sonarqube: /opt/sonarqube
# Configuration de SonarQube
sudo echo '# DATABASE
# User credentials.
sonar.jdbc.username=sonar
sonar.jdbc.password=KamaKama

#----- PostgreSQL 9.6 or greater
# By default the schema named "public" is used. It can be overridden with the parameter "currentSchema".
#sonar.jdbc.url=jdbc:postgresql://localhost/sonarqube?currentSchema=my_schema
sonar.jdbc.url=jdbc:postgresql://localhost/sonarqube

# WEB SERVER
sonar.web.javaOpts=-Xmx512m -Xms128m -XX:+HeapDumpOnOutOfMemoryError
sonar.web.host=0.0.0.0
sonar.web.port=9000

# ELASTICSEARCH
sonar.search.javaOpts=-Xmx512m -Xms512m -XX:MaxDirectMemorySize=256m -XX:+HeapDumpOnOutOfMemoryError

' > /opt/sonarqube/conf/sonar.properties

# Créer un fichier  SonarQube System
sudo echo '[Unit]
Description=SonarQube service
After=syslog.target network.target

[Service]
Type=forking
ExecStart=/opt/sonarqube/bin/linux-x86-64/sonar.sh start
ExecStop=/opt/sonarqube/bin/linux-x86-64/sonar.sh stop
User=sonarqube
Group=sonarqube
Restart=always
LimitNOFILE=65536
LimitNPROC=4096

[Install]
WantedBy=multi-user.target

' > /etc/systemd/system/sonarqube.service

# 
sudo systemctl daemon-reload
sudo systemctl restart sonarqube
# 
sudo systemctl enable sonarqube
# 
systemctl status sonarqube
# Ajouter une règle de pare-feu au service UFW
sudo ufw allow 9000/tcp comment 'Autoriser le service SonarQube sur le port 9000'
# 
sudo ss -plunt|grep 9000
# Accès à l'interface de Jenkins
echo 'http://localhost:9000'
echo 'http://localhost:9000/sessions/new?return_to=%2F'
# 
