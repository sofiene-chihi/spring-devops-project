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
* Launch mysql database in a docker container using the make command ( only need to have docker and docker-compose available ) by running 
```
make run_db
```
* Once the database container is launched, we can run our application ( the database configuration for our app can be found in application.properties file)
* To run the application unit tests, we run the command
```
mvn test
```
## Pipeline steps in details

We'll go throught all the steps of our pipeline from pushing or merging a PR into our master branch to the deployment of our app in a deployment server.

For that purpose, I used 4 VMs running ubuntu server and generated the SSH keys to connect to each one of them.

