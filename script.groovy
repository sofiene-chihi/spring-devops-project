
def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t sofienechihi/my-repo:spring-app-1.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push sofienechihi/my-repo:spring-app-1.0'
    }
}

def pushToNexus() {
    echo "pushing the jar file to Nexus maven-snapshots repo..."
    sh 'mvn clean deploy -Dmaven.test.skip=true'
}

def sonarScan(String serverIp, String serverUser) {
    echo "Running sonarQube scan..."
    def runSonar = '"export MYSQLDB_ROOT_PASSWORD=sofiene MYSQLDB_DATABASE=pet_store MYSQLDB_LOCAL_PORT=3306 MYSQLDB_DOCKER_PORT=3306 && bash runSonarQube.sh"'
    sshagent (credentials: ['sonar-server']) {
        sh "ssh -o StrictHostKeyChecking=no ${serverUser}@${serverIp} ${runSonar}"
    }}

def deployApp(String serverIp, String serverUser) {
    echo 'deploying the application...'
    def composeRun = '"export MYSQLDB_USER=root MYSQLDB_ROOT_PASSWORD=sofiene MYSQLDB_DATABASE=pet_store MYSQLDB_LOCAL_PORT=3306 MYSQLDB_DOCKER_PORT=3306 SPRING_LOCAL_PORT=8080 SPRING_DOCKER_PORT=8080 && docker-compose up -d"'
    sshagent (credentials: ['deployment-server']) {
        sh "ssh -o StrictHostKeyChecking=no ${serverUser}@${serverIp} ${composeRun}"
    }
}

def cleanUntaggedImages(String serverIp, String serverUser){
    def cleanImages = 'docker image prune --force --filter "dangling=true"'
    sshagent (credentials: ['jenkins-server']) {
        sh "ssh -o StrictHostKeyChecking=no ${serverUser}@${serverIp} ${cleanImages}"
    }
}

return this
