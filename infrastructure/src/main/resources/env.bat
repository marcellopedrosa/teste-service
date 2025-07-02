@echo off
echo Definindo variáveis de ambiente...

setx DB_TESTE_SERVICE_URL "jdbc:postgresql://host_do_docker:5444/test_db" /M
setx DB_TESTE_SERVICE_USERNAME "user_db" /M
setx DB_TESTE_SERVICE_PASSWORD "pass_db" /M

setx SPI_REALM "meu-realm" /M
setx SPI_TESTE_SERVICE_CLIENT_ID "teste-service" /M
setx SPI_TESTE_SERVICE_CLIENT_SECRET "01n2ug8OELqcP77NH0KoLzSCMc06OkwG" /M
setx SPI_URI_SERVER "http://localhost.com.br/auth/realms/meu-realm" /M

setx SPRING_CLOUD_EUREKA_HOST "http://localhost" /M
setx SPRING_CLOUD_EUREKA_PORT "8761" /M
setx SPRING_CLOUD_EUREKA_PATH "/eureka" /M

echo Variaveis definidas com sucesso!
pause
