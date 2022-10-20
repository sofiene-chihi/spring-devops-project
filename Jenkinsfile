#!/usr/bin/env groovy

def gv

pipeline {
    agent any
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

        stage("build image") {
            steps {
                script {
                    gv.buildImage()
                }
            }
        }
        stage("test") {

            steps {
                script{
                    gv.testApp()
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
