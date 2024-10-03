# How to run
## Database for bakery_service
Run this in terminal: </br>
1. `docker build -t database .` </br>
2. `docker run -d -e POSTGRES_DB=bakerydb -e POSTGRES_PASSWORD=<> -e POSTGRES_USER=<> -i --name bakerydb -p 5555:5432 database`

## bakery_service
Run this in terminal: </br>
1. `docker build -t bakery_service . ` </br>
2. `docker run -d -e DATABASE_HOST=host.docker.internal -e DATABASE_PORT=5555 -e DATABASE_NAME=bakerydb -e DATABASE_PASSWORD=<> 
-e DATABASE_USERNAME=<> -i --name bakery-service -p 8080:8080 bakery-service `



