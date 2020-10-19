pipeline {
    agent any

    triggers {
        pollSCM '* * * * *'
    }
    stages {
        stage('Build') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew assemble'
                sh './gradlew dockerRun'
            }
        }
        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }
    }
}