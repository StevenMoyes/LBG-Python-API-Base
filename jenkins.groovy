pipeline {
    // Config and stages go here

    //Agent - where the pipeline should run
    agent any

    // Environment
    environment {
        APP_ENV = 'development'
    }

    // Stages
    stages {
        stage('Checkout'){
            steps {
                git url: 'https://github.com/StevenMoyes/LBG-Python-API-Base', branch: 'main'
            }
        }
        stage('Build') {
            steps {
                echo 'Building...'
                sh './build.sh'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing...'
                sh './test.sh'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying'
                sh './deploy.sh'
            }
        }
    }
    post {
        success {
            echo 'Pipeline ran successfully'
        }
        failure {
            echo 'Pipeline failed, please refer to the logs'
        }
    }
}