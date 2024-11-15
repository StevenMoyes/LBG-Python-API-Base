pipeline {
    environment {
        DOCKER_CREDENTIALS = credentials("DOCKER_CREDENTIALS")
    }
    agent any
    stages {
        stage('Cleanup') {
            steps {
                sh "docker rm -f $(docker ps -aq) || true"
                sh "docker rmi -f $(docker images) || true"
           }
        }
        stage('Build') {
            steps {
                sh "docker build -t lbg"
            }
        }
        stage('Modification') {
            steps {
                sh "echo PORT=5001"
            }
        }
        stage('Run Container') {
            steps {
                sh "docker run -d -p 80:\$PORT -e PORT=\$PORT lbg"
            }
        }
    }
}
