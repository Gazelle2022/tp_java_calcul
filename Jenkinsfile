pipeline {
    agent any
    environment {
        IMAGE_NAME = "tp3-java-app:latest"
        CONTAINER_NAME = "tp3-java-container"
        HOST_PORT = "8081"
        CONTAINER_PORT = "8080"
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Gazelle2022/tp_java_calcul.git'
            }
        }
        stage('Build') {
            steps {
                bat '"C:\\Users\\Ghizlane\\Downloads\\maven-mvnd-1.0.3-windows-amd64\\bin\\mvnd.cmd" -B clean package -DskipTests'
            }
        }
        stage('Test') {
            steps {
                bat '"C:\\Users\\Ghizlane\\Downloads\\maven-mvnd-1.0.3-windows-amd64\\bin\\mvnd.cmd" -B test'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
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
                docker stop %CONTAINER_NAME% || exit 0
                docker rm %CONTAINER_NAME% || exit 0
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
