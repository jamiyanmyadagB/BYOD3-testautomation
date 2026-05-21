pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t grade-runner .'
            }
        }

        stage('Run Tests') {
            steps {
                sh '''
                docker run --rm \
                -v $WORKSPACE:/workspace \
                csv302lpu/grade-runner:v1
                '''
            }
        }
    }

    post {

        always {
            echo 'Pipeline finished.'

            archiveArtifacts artifacts: '**/*', allowEmptyArchive: true
        }

        success {
            echo 'BUILD SUCCESS'
        }

        failure {
            echo 'BUILD FAILED'
        }
    }
}