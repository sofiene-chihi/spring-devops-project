# Spring Devops project
## Description of the project

This project is created to test the process of a **CI/CD pipeline** using **Jenkins**.

We don't really care about the content of the application more than about the process that our application will go through.
Our application is a simple pet store where we have the owners and their pets.

<p align="center">
  <img src="./assets/images/class_diagram.drawio.png" title="hover text">
</p>

The process of our pipeline will follow these steps : 
* Running the unit tests and scanning the code using the sonarQube
* Building an artifact (JAR in our case) and pushing it to a Nexus repository
* Building the docker image for our application and pushing it to dockerhub
* Deploying the application in a deployment server 

<p align="center">
  <img src="./assets/images/pipeline.drawio.png" title="hover text">
</p>

## Test the application locally
* First thing to do after cloning the application is open it with an IDE and specifying that it's a maven project in order to check for the required dependencies ( used packages ) in the POM.xml file.
* Launch mysql database in a docker container using the db.sh script ( only need to have docker available ) by running 
```
bash db.sh
```
* Once the database container is launched, we can run our application ( the database configuration can be found in application.properties file)
* To run the application unit tests, we run the command
```
mvn test
```

## Pipeline steps in details
  