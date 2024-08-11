# How to run
## Database
Run this in terminal: </br>
1. `docker build -t database .` </br>
2. `docker run -d -e POSTGRES_PASSWORD=<> -e POSTGRES_USER=<> -i --name database-container -p 5555:5432 database`



