#!/bin/bash

# Lancer Node Exporter
node_exporter/node_exporter

# Lancer le serveur Prometheus
sudo /etc/prometheus/prometheus --config.file=prometheus/prometheus.yml

# Lancer le serveur Jenkins
jenkins

# Lancer le playbook Ansible pour installer les outils
ansible-playbook -i hosts.yml istall_tools.yml

# Lancer les playbook Ansible pour déployer l'application
ansible-playbook -i hosts.yml playbook.yml

