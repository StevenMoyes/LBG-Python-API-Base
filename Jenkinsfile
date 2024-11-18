<<<<<<< HEAD
=======
pipeline {
    environment {
        DOCKER_CREDENTIALS = credentials("DOCKER_CREDENTIALS")
        PORT = '5001'
    }
    agent any
    stages {
        stage('Cleanup') {
            steps {
                sh "docker rm -f \$(docker ps -aq) || true"
                sh "docker rmi -f \$(docker images -q) || true"
           }
        }
        stage('Build') {
            steps {
                sh "docker build -t lbg ." 
            }
        }
        stage('Run Container') {
            steps {
                sh "docker run -d -p 80:\$PORT -e PORT=\$PORT lbg"
            }
        }
    }
}
>>>>>>> f3731e0d62bb66c9e3c90295c24fabf7237a3fc1
