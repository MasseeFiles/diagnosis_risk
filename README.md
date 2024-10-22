# DIAGNOSIS APP
# _Microservice RISK_

DIAGNOSIS est une application d'aide à la détection du diabète de type 2 comportant 5 microservices (Gateway, View, Patient, Risk et Note). Le microservice RISK a pour rôle de déterminer le niveau de risque diabétique d'un patient particulier en fonction de ses données personnelles et de ses notes médicales. Il expose pour cela des endpoints REST et utilise le webservice client OpenFeign pour communiquer avec les autres microservices.

### Port
Le microservice RISK est exposé sur le port 8085.

### Docker

Le microservice comporte un fichier Dockerfile à la racine du projet pour la création de son image DOCKER.