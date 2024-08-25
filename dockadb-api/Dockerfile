# Use a imagem base do OpenJDK
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho
WORKDIR /app

# Copie o JAR da aplicação para o diretório de trabalho
COPY target/dockadb-api-1.0.0.jar app.jar

COPY .env /app/.env

# Defina o comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

# Exponha a porta que a aplicação vai usar
EXPOSE 8008