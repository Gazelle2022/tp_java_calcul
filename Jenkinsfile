pipeline {
    agent any

    environment {
        // TP3 : Docker
        IMAGE_NAME = "tp3-java-app:latest"
        CONTAINER_NAME = "tp3-java-container"
        HOST_PORT = "8081"
        CONTAINER_PORT = "8080"

        // TP4 : SonarQube
        SONAR_PROJECT_KEY = "tp4-java-project"
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

        stage('Test Credential (B3)') {
            steps {
                withCredentials([
                    string(credentialsId: 'github-token', variable: 'GITHUB_TOKEN')
                ]) {
                    bat '''
                    echo Token récupéré mais caché dans les logs
                    '''
                }
            }
        }

        stage('SonarQube Analysis') {
             steps {
                 withSonarQubeEnv('SonarQube') { // le serveur SonarQube configuré
                 withMaven(maven: 'MVN_3.9.12') { // Maven installé via Global Tool Configuration
                   bat 'mvn clean verify sonar:sonar -Dsonar.projectKey=%SONAR_PROJECT_KEY%'
            }
        }
    }
}


        stage('Quality Gate') {
            steps {
                timeout(time: 2, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }

    post {
        success {
            echo "✅ Pipeline terminé avec succès : Docker + SonarQube OK"
        }
        failure {
            echo "❌ Pipeline échoué : vérifier les tests ou la Quality Gate"
        }
    }
}
cd