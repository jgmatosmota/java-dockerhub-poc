# Use a imagem base do OpenJDK 17
FROM openjdk:17-jdk-slim

# Copie o arquivo .jar para a imagem
COPY target/imagemJava.jar /app/imagemJava.jar

# Defina a porta que a aplicação irá escutar
EXPOSE 8080

# Defina o comando para iniciar a aplicação
CMD ["java", "-jar", "/app/imagemJava.jar"]
