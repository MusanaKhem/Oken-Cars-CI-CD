# Utilisation d'une image Java 17
FROM openjdk:17-jdk-alpine

# Installation du client PostgreSQL (jdbc, etc.)
RUN apk add --no-cache postgresql-client

# Création du répertoire de l'application
RUN mkdir -p /app

# Changement du répertoire de travail à /opt/app
WORKDIR /opt/app

# Copie de oken-cars-web.jar vers /opt/app/app.jar
# ARG JAR_FILE=target/*.jar
ARG JAR_FILE=target/oken-cars-web.jar

# Compilation du fichier Jar
# cp oken-cars-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# Configuration de l'application pour PostgreSQL
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db_host:5432/okendb
ENV SPRING_DATASOURCE_USERNAME=useroken
ENV SPRING_DATASOURCE_PASSWORD=12345

# Exécution du fichier jar avec ENTRYPOINT
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:30303","-jar","app.jar"]
