pipeline {
    agent any
    stages {
        stage('Init') {
            steps {
                script {
                    if (env.GIT_BRANCH == 'origin/main') {
                        sh '''
                        kubectl create ns prod || echo "prod namespace already exists"
                        '''
                    } else if (env.GIT_BRANCH == 'origin/dev') {
                        sh'''
                        kubectl ns dev || echo "dev namespace already exists"
                        '''
                    } else {
                        sh '''
                        echo "unknown namespace"
                        '''
                    }
                }
           }
        }
        stage('Build') {
            steps {
                sh '''
                docker build -t stevenmoyes/lbg-python-api-base-jenk:latest .
                docker build -t stevenmoyes/lbg-python-api-base-jenk:v${BUILD_NUMBER} .
                '''
            }
        }
        stage('Push') {
            steps {
                sh  '''
                docker push stevenmoyes/lbg-python-api-base-jenk:latest
                docker push stevenmoyes/lbg-python-api-base-jenk:v${BUILD_NUMBER}
                '''
            }
        }
        stage('Run Container') {
            steps {
                script {
                    if (env.GIT_BRANCH == 'origin/main') {
                        sh '''
                        kubectl apply -f ./kubernetes -n prod
                        kubectl set image deployment/web-deployment web-app=stevenmoyes/lbg-python-api-base-jenk:v${BUILD_NUMBER} -n prod
                        '''
                    } else if (env.GIT_BRANCH == 'origin/dev') {
                        sh  '''
                        kubectl apply -f ./kubernetes -n dev
                        kubectl set image deployment/web-deployment web-app=stevenmoyes/lbg-python-api-base-jenk:v${BUILD_NUMBER} -n dev
                        '''
                    } else {
                        sh '''
                        echo "Unrecognised branch"
                        '''
                    }
                }
            }
        }
    }
}