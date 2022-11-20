#!/usr/bin/env groovy

def gv

pipeline {
    agent any
    environment {
        DEPLOYMENT_SERVER_IP = "192.168.122.101"
        DEPLOYMENT_SERVER_USER= "sofiene"
        SONARQUBE_SERVER_IP ="192.168.122.144"
        SONARQUBE_SERVER_USER="sofiene"
    }
    tools {
        maven 'maven'
    }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("SonarQube Testing and Scan") {
            steps {
                script {
                    gv.sonarScan(${SONARQUBE_SERVER_IP},${SONARQUBE_SERVER_USER})
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    gv.buildImage()
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }

    }

/*
    post {
        success {
            script {
                echo 'removing the old images from the server..'
                gv.cleanUntaggedImages()
                emailext body: 'Your backend pipeline finished the buit and deployment of the project successfully', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: 'Success of digihunt pipeline stages'

            }
        }
        failure {
            script {
                echo 'removing the old images from the server..'
                gv.cleanUntaggedImages()
                emailext body: 'Your backend pipeline failed the built and deployment of the project successfully', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: 'Failure of digihunt pipeline stages'

            }
        }
    }
*/
}
