
def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t sofienechihi/my-repo:spring-app-1.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push sofienechihi/my-repo:spring-app-1.0'
    }
}

def testApp() {
    echo "testing the application..."
    // sh 'mvn test'
}

def sonarScan() {
    echo "Running sonarQube scan..."
    def runSonar = '"bash runSonarQube.sh"'
    sshagent (credentials: ['jenkins-server']) {
        sh "ssh -o StrictHostKeyChecking=no root@194.195.246.28 ${runSonar}"
    }}

def deployApp() {
    echo 'deploying the application...'
    def composeRun = '"docker-compose up -d"'
    sshagent (credentials: ['dev-server']) {
        sh "ssh -o StrictHostKeyChecking=no root@139.144.79.56 ${composeRun}"
    }
}

def cleanUntaggedImages(){
    def cleanImages = 'docker image prune --force --filter "dangling=true"'
    sshagent (credentials: ['dev-server']) {
        sh "ssh -o StrictHostKeyChecking=no root@139.144.79.56 ${cleanImages}"
    }
}

return this
