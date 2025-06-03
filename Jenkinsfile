pipeline {
    agent any

    tools {
        maven 'Maven 3.8.1' // hoặc tên Maven bạn đã config trong Jenkins
        jdk 'JDK 17'         // hoặc tên JDK đã config trong Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Mendt7696/System_OrangeHRM.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Report') {
            steps {
                // Nếu dùng Allure
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            junit 'target/surefire-reports/*.xml'
        }
    }
}
