# SpringBootTemplate
Spring Boot template project integrating tutorials for "Building REST Api", "Building Mongo Client" and "Swagger Api Documentation".

Because this is template project there are no fancy run/build scripts just run the main class "SpringBaseRestApiApplication".

Two projects included in packages;
* basic   -> Basic rest api featuring some web controller and api models  
* restful -> Swagger and Mongo integrated spring boot application

This project requires a mongo db running in localhost:27017. 
To run mongo db install docker and run command;

docker run -d  -p 27017:27017 --name mongo -v <some place in local machine to persist mongo data>>:/data/db mongo:4.0-xenial