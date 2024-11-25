pipeline {
    agent any
    tools {
        maven 'Maven3' // Maven configuré dans Global Tool Configuration
    }
    stages {
        stage('Checkout') {
            steps {
                echo "Récupération du code depuis le dépôt Git"
                git branch: 'main', url: 'https://github.com/MatisVivier/Pipeline-Git-Jenkins'
            }
        }
        stage('Build') {
            steps {
                echo "Compilation avec Maven"
                bat 'mvn clean install' // Utilise 'sh' sur Linux/MacOS
            }
        }
        stage('Test') {
            steps {
                echo "Exécution des tests unitaires"
                bat 'mvn test'
            }
        }
        stage('Package') {
            steps {
                echo "Création de l'artefact"
                bat 'mvn package'
            }
        }
    }
    post {
        success {
            echo "Pipeline exécuté avec succès !"
        }
        failure {
            echo "Échec du pipeline. Vérifiez les logs Jenkins."
        }
        always {
            echo "Nettoyage du workspace"
            cleanWs()
        }
    }
}
