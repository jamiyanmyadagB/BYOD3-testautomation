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
                POM_PATH=$(find . -name "pom.xml" | head -n 1)

                if [ -z "$POM_PATH" ]; then
                    echo "ERROR: pom.xml not found!"
                    exit 1
                fi

                PROJECT_DIR=$(dirname "$POM_PATH")

                echo "Found pom.xml in: $PROJECT_DIR"

                docker run --rm \
                    -v $PWD:/workspace \
                    -w /workspace/$PROJECT_DIR \
                    grade-runner \
                    mvn test
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