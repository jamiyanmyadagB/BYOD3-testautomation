pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Pull Docker Image') {
            steps {
                sh 'docker pull csv302lpu/grade-runner:v1'
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