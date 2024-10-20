# How to run
## Database for bakery_service
Run this in terminal: </br>
1. `docker build -t database .` </br>
2. `docker run -d -e POSTGRES_DB=bakerydb -e POSTGRES_PASSWORD=<> -e POSTGRES_USER=<> -i --name bakerydb -p 5555:5432 database`

## bakery_service
Run this in terminal: </br>
1. `docker build -f bakery_service/Dockerfile -t bakery_service .` </br>
2. `docker run -d -e DATABASE_HOST=host.docker.internal -e DATABASE_PORT=5555 -e DATABASE_NAME=bakerydb -e DATABASE_PASSWORD=<> 
-e DATABASE_USERNAME=<> -i --name bakery-service -p 8080:8080 bakery-service 

## buns_store
Run this in terminal: </br>
1. `docker build -f buns_store/Dockerfile -t buns_store .`  </br>
2. `docker run -d -i --name buns_store -p 8060:8070 buns_store`



