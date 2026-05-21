pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Debug Files') {
            steps {
                sh 'echo "Current Directory:"'
                sh 'pwd'

                sh 'echo "Project Files:"'
                sh 'ls -la'

                sh 'echo "Searching pom.xml:"'
                sh 'find . -name "pom.xml"'
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
                    -v $(pwd):/workspace \
                    -w /workspace \
                    grade-runner \
                    mvn -f grade-tests/pom.xml test
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