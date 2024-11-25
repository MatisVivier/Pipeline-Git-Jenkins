pipeline {
    agent any
    tools {
        maven 'Maven3' // Maven configuré dans Jenkins
    }
    stages {
        stage('Checkout') {
            steps {
                echo "Récupération du code depuis le dépôt Git"
                git branch: 'main', url: 'https://github.com/ton-depot/ton-projet.git'
            }
        }
        stage('Build') {
            steps {
                echo "Compilation avec Maven"
                bat 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                echo "Exécution des tests unitaires"
                bat 'mvn test'
            }
        }
        stage('Static Analysis') {
            steps {
                echo "Analyse du code avec Checkstyle"
                bat 'mvn checkstyle:check'
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
