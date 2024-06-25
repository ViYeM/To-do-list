FROM openjdk:21-jdk-slim

# Diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo pom.xml e o diretório src para o diretório de trabalho do contêiner
COPY pom.xml .
COPY src ./src

# Instale o Maven e execute a construção do projeto
RUN apt-get update && apt-get install -y maven
RUN mvn clean install

# Exponha a porta que sua aplicação irá usar
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["mvn", "spring-boot:run"]
