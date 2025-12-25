pipeline {
    agent any
    triggers {
        githubPush()
    }
    environment {
        IMAGE_NAME = "tp3-java-app:latest"
        CONTAINER_NAME = "tp3-java-container"
        HOST_PORT = "9091"
        CONTAINER_PORT = "9090"
    }
    stages {
        // tes stages 
    }
    post {
        success { echo "✅ Déploiement local terminé" }
        failure { echo "❌ Erreur dans le pipeline" }
    }
}
