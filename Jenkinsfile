pipeline {
    agent any
    tools {
        maven 'mvnd'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Gazelle2022/tp_java_calcul.git'
            }
        }
        stage('Build') {
            steps {
                bat "${tool 'MVND'}\\mvnd.bat clean package -DskipTests"
            }
        }
        stage('Test') {
            steps {
                bat "${tool 'MVND'}\\mvnd.bat test"
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        stage('Docker Build') {
            steps {
                bat 'docker build -t tp3-java-app:latest .'
            }
        }
        stage('Deploy') {
            steps {
                bat """
                docker stop tp3-java-container
                docker rm tp3-java-container
                docker run -d --name tp3-java-container -p 9091:9090 tp3-java-app:latest
                """
            }
        }
    }
    post {
        success {
            echo "✅ Déploiement terminé"
        }
        failure {
            echo "❌ Erreur dans le pipeline"
        }
    }
}
