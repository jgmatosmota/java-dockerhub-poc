Não precisa clonar para fazer funcionar!

1 - Executar o comando no bash "sudo wget https://raw.githubusercontent.com/jgmatosmota/java-dockerhub-poc/main/docker-compose.yml"
2 - Executar o docker compose com "sudo docker-compose up"
3 - Executar "sudo docker exec -it javaContainer bash"
4 - Dentro do container do java navegar até o diretório app "cd /app"
5 - Executar o arquivo .jar "java -jar imagemJava.jar"
6 - Agora só navegar no console do java para mexer no banco de dados

IMPORTANTE!
A ec2 deve possuir o docker e o docker-compose devidamente instalados!
- Comandos docker compose "sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose"
- Atribuindo permissões "sudo chmod +x /usr/local/bin/docker-compose"
-Docker "sudo apt-get install docker.io"
