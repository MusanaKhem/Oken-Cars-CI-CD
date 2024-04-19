pipeline{
    agent any
    tools {
        git 'git'
        maven 'mvn'
        jdk 'java-17.0.8.1'
    }
    stages{
        stage('Git Checkout SCM') {
            steps{
                checkout changelog: false, poll: true, scm: scmGit(branches: [[name: '*/develop']], extensions: [cleanBeforeCheckout(), changelogToBranch(changelogBase(compareRemote: ' oken-cars-web', compareTarget: '*/develop'))], userRemoteConfigs: [[credentialsId: 'Jenkins_Access_Gitlab_Oken_user', url: 'http://10.10.10.212/oken-consulting/oken-cars-web.git']])
            }

        }
        stage('MVN Invoker') {
            steps{
                maven_invoker invokerBuildDir: 'target/it', reportsFilenamePattern: 'target/invoker-reports/BUILD*.xml'
            }
        }
        stage('SonarQube Analysis') {
            steps{
                withSonarQubeEnv('SonarQube-okenCarsMulti-Token-server4') {
                    sh "mvn clean package sonar:sonar test.skip=true -Dmaven.test.failure.ignore=true"
                }
            }
        }
        stage('Quality Gate') {
            steps{
                waitForQualityGate(webhookSecretId: 'sqa_4bd7f4b4eeb59e0c60342bad486fd837529d3dc0') {
                }
            }
        }
        stage('Delete old repo version') {
            steps{
                sh '''
                    ssh oken@10.10.10.29
                    cd && pwd && hostnamectl
                    docker ps && docker images
                    cp -r oken-cars-web oken-cars-web-31.10.2023
                    docker stop apache-app
                    docker stop angular-app
                    docker stop spring-app
                    docker stop db_host
                    docker rm apache-app
                    docker rm angular-app
                    docker rm spring-app
                    docker rm db_host
                    docker image prune -f
                    docker stop $containerName
                    docker rmi -f oken-cars-web-apache:latest
                    docker rmi -f oken-cars-web-angular:latest
                    docker rmi -f oken-cars-web-spring:latest
                    docker rmi -f postgres:13
                    rm -rf oken-cars-web
                    mkdir oken-cars-web'''
            }
        }
        stage('Analyse Environment') {
            steps{
                sh '''
                    ssh oken@10.10.10.29
                    sudo dpkg --configure -a
                    sudo apt update && sudo apt upgrade
                    pwd
                    hostnamectl
                    sudo ufw status numbered
                    ip -4 a | grep ens18
                    cat /etc/netplan/00-installer-config.yaml | grep dhcp4
                    cat /etc/netplan/00-installer-config.yaml | grep nameservers
                    cat /etc/netplan/00-installer-config.yaml | grep routes
                    ping -c2 oken-cars.oken.lan
                    ping 10.10.10.212
                    ping -c2 8.8.8.8
                    git --version
                    java --version && which java && whereise java
                    mvn --version && which mvn && whereis mvn
                    echo $PATH
                    node -v && which node && whereis node
                    npm -v && which npm && whereis npm
                    docker --version && which docker && whereis docker
                    docker compose version
                    sudo usermod -aG docker oken
                    sudo usermod -aG docker $USER
                    systemctl status docker | grep Loaded
                    systemctl status docker | grep Active
                    docker ps
                    docker images
                    docker volume ls
                    ls -lt'''
            }
        }
        stage('Transfer Oken Cars project from Jenkins to server3') {
            steps{
                sh '''
                    pwd && hostnamectl
                    scp -r ./* oken@10.10.10.29:~/oken-cars-web/'''
            }
        }
        stage('Build Oen-Cars App using shell script') {
            steps{
                sh '''
                      ssh oken@10.10.10.29 && cd && pwd && hostnamectl
                      hostname && ls -lt
                      cd oken-cars-web/ && ls -lt
                      chmod +x oken-cars-build.sh && ./oken-cars-build.sh
                      ls -lt
                      curl oken-cars4.oken.lan/home
                      docker logs
                      docker container inspect apache-app
                      docker container inspect apache-app | grep IPAddress'''
            }
        }
    }
}
