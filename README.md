## Docker

```shell script
# сборка докер
docker build -t umnei

# запуск докера для dev  
docker run --rm --name umnei -p 8080:8080 -p 8443:8443 -e TZ=Europe/Moscow umnei

```

```shell script
mvn clean package

docker build -t ezhovda/umnei:1.0 -t ezhovda/umnei:latest .

# тестовый запуск локально
docker run --rm --name umnei -p 8080:8080 -p 8443:8443 -e TZ=Europe/Moscow ezhovda/umnei:latest

docker rmi $(docker images -q) # удалить все образы

docker login # для авторизации

docker push ezhovda/umnei:1.0
```
