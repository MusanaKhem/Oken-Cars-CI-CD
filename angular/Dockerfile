#stage 1
# Étape 1 : Utiliser l'image Node.js 18.17.0 comme base
FROM node:18.17.0

# Étape 2 : Créer un répertoire /app dans le conteneur Docker
RUN mkdir -p /app

# Étape 3 : Définir /app comme répertoire de travail
WORKDIR /app

# Étape 4 : Copier les fichiers package.json et package-lock.json dans /app
COPY package*.json /app

# Étape 5 : Installer Angular CLI globalement dans le conteneur Docker
RUN npm install -g @angular/cli

# Étape 6 : Installer les dépendances Node.js définies dans package.json
RUN npm install

# Étape 7 : Copier tous les fichiers de l'application dans /app
COPY . .

# Étape 8 : Construire l'application Angular pour la production
RUN npm run build --prod

# Étape 9 : Définir la commande par défaut pour démarrer l'application
CMD [ "npm", "start" ]
