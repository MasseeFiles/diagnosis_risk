##################### DOCKERFILE diagnosis_risk ####################

#################### STAGE 1 : Construction du projet ##########################

# Definition de l'image de base
FROM maven AS build

# Definition du fichier de travail dans le container
WORKDIR /diagnosis_risk

# Copie du pom et du code source dans le fichier de travail
COPY pom.xml /diagnosis_risk
COPY src /diagnosis_risk/src

# Copier le fichier application.properties dans l'image
COPY src/main/resources/application.properties /diagnosis_risk

# Package de l'appli (sans execution des tests - DskipTests)
RUN mvn clean package -DskipTests



#################### STAGE 2 : Execution de l'appli ####################
FROM openjdk:21-jdk-slim

WORKDIR /diagnosis_risk

# Copie du fichier packagé (jar) vers le fichier de travail
COPY --from=build /diagnosis_risk/target/diagnosis_risk-0.0.1-SNAPSHOT.jar diagnosis_risk.jar

# Exposition du port d'accès à l'appli
EXPOSE 8081

#RUN de l'appli (par defaut au demarrage du container)
ENTRYPOINT ["java", "-Dspring.config.location=classpath:/application.properties", "-jar", "diagnosis_risk.jar"]






