#Cake Manager application

## Get the code

Git:

    git clone https://github.com/vimleshgupta/cake-manager.git
    cd cake-manager

###The project directory structure

This project has two modules:
 1. `cake-manager-service` - It represents back-end micro service which is built on Spring Boot.
 
    Technology stacks:
    1. Java
    2. Spring Boot
    3. MySql (Database)
    4. Gradle (Build tool)
    5. Groovy and Spock Framework (For unit tests)
    
    
 2. `cake-manager-app` - It represents client app which is built on Angular.

    Technology stacks:
     1. AngularJS
     2. HTML
     3. CSS

It follows the following directory structures:
```Gherkin
cake-manager-service               Server - Spring boot micro service
    src
      + main
        + java                      Main production code
        + resources                 Configuration properties
      + test
        + groovy                    Tests
      ...
cake-manager-app                   Client - Angular App
    src
      + app                        Angular components and services
      + environments
        + index.html
      ...
docker-compose.yml                 Docker (Build and run in the docker containers)
```

![image](https://user-images.githubusercontent.com/7358843/151858400-82819720-5588-4b88-aff2-2284e4ae0586.png)

## Docker
At the root level, you will find a `docker-compose.yml` file. A Docker Compose file to build Docker Images for Spring Boot, Angular and MySQL services and run all of them with a single command.
Each module has separate `Dockerfile` which can be used to build and run single application.

#Run the Application
    docker-compose up
    
###Build All Containers
    docker-compose build

###Build Specific Container
You can use service name as `app-service` or `app-client` or `db`

    docker-compose build <service_name>

###Build Specific Container without Cache
    docker-compose build --no-cache service_name

###Remove Containers
To remove the containers, networks, and volumes associated with the containerized environment, use the down command:

    docker-compose down 


#Access the application
The client app runs on 80 port you can access the application with following urls:
1. http://localhost/  - This will show home page with list of cakes.
2. http://localhost/cakes - This will allow you to add a new cake and download all the cakes as json.

The backend service exposed following APIs:
1. GET http://localhost:8080/api/v1/cakes/all - Get the all cakes
2. POST http://localhost:8080/api/v1/cakes/add - Create a new cake