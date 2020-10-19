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
        stage('Docker run'){
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew docker dockerRun'
            }
        }
        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }
    }
}