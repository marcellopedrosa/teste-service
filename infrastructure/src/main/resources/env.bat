@echo off
echo Definindo variáveis de ambiente...

setx DB_ADMIN_SERVICE_URL "jdbc:postgresql://host_do_docker:5444/test_db" /M
setx DB_ADMIN_SERVICE_USERNAME "user_db" /M
setx DB_ADMIN_SERVICE_PASSWORD "pass_db" /M

setx KEYCLOAK_REALM "meu-realm" /M
setx KEYCLOAK_ADMIN_SERVICE_CLIENT_ID "teste-service" /M
setx KEYCLOAK_ADMIN_SERVICE_CLIENT_SECRET "01n2ug8OELqcP77NH0KoLzSCMc06OkwG" /M
setx KEYCLOAK_URI_SERVER "http://localhost.com.br/auth/realms/meu-realm" /M

setx SPRING_CLOUD_EUREKA_HOST "http://localhost" /M
setx SPRING_CLOUD_EUREKA_PORT "8761" /M
setx SPRING_CLOUD_EUREKA_PATH "/eureka" /M

echo Variaveis definidas com sucesso!
pause
