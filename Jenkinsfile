pipeline {
    agent any

    triggers {
        pollSCM '* * * * *'
    }
    stages {
        stage('Build') {
            steps {
                sh 'chmod +x gradlew'
            }
        }
        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }
        stage('Docker run'){
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew dockerRun'
            }
        }

    }
}