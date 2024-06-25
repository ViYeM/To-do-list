# Use a imagem base do OpenJDK
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo JAR para o diretório de trabalho dentro do contêiner
COPY target/roteiro01-0.0.1-SNAPSHOT.jar app.jar

# Comando para executar o aplicativo
ENTRYPOINT ["java", "-jar", "app.jar"]
