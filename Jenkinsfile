pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Gazelle2022/tp_java_calcul.git'
            }
        }

        stage('Build') {
            steps {
                bat '"C:\\Users\\Ghizlane\\Downloads\\maven-mvnd-1.0.3-windows-amd64\\bin\\mvnd.cmd" clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                bat '"C:\\Users\\Ghizlane\\Downloads\\maven-mvnd-1.0.3-windows-amd64\\bin\\mvnd.cmd" test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
    }
}
