# How to run applications separately
Run below command from the level of infrastructure package.

## Database for bakery_service
Run this in terminal: </br>
1. `docker build -f bakery_service/database/Dockerfile -t database ./bakery_service/database` </br>
2. `docker run -d -e POSTGRES_DB=bakerydb -e POSTGRES_PASSWORD=<> -e POSTGRES_USER=<> -i --name bakerydb -p 5555:5432 database`

## bakery_service
Run this in terminal: </br>
1. `docker build -f bakery_service/service/Dockerfile -t bakery_service .. ` </br>
2. `docker run -d -e DATABASE_HOST=host.docker.internal -e DATABASE_PORT=5555 -e DATABASE_NAME=bakerydb -e DATABASE_PASSWORD=<>
-e DATABASE_USERNAME=<> -i --name bakery_service -p 8010:8010 bakery_service`

## buns_store
Run this in terminal: </br>
1. `docker build -f buns_store/service/Dockerfile -t buns_store ..`  </br>
2. `docker run -d -i --name buns_store -p 8020:8020 buns_store`


# How to run applications using Docker compose
Run this in terminal: </br> 
1. `docker-compose up -d`  </br>