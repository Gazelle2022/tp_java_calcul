pipeline {
    agent any
    environment {
        IMAGE_NAME = "tp3-java-app:latest"
        CONTAINER_NAME = "tp3-java-container"
        HOST_PORT = "9091"
        CONTAINER_PORT = "9090"
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Gazelle2022/tp_java_calcul.git'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn -B clean package -DskipTests'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn -B test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        stage('Docker Build') {
            steps {
                bat 'docker build -t %IMAGE_NAME% .'
            }
        }
        stage('Deploy (Local Docker)') {
            steps {
                bat """
                docker stop %CONTAINER_NAME%
                docker rm %CONTAINER_NAME%
                docker run -d --name %CONTAINER_NAME% -p %HOST_PORT%:%CONTAINER_PORT% %IMAGE_NAME%
                """
            }
        }
    }
    post {
        success {
            echo "✅ Déploiement local terminé"
        }
        failure {
            echo "❌ Erreur dans le pipeline"
        }
    }
}
