pipeline {
    agent any
    tools {
        maven 'Maven3' // Maven configuré dans Jenkins
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
            echo "Poussée des changements vers le dépôt Git"
            bat '''
                git config user.name "Jenkins"
                git config user.email "jenkins@example.com"
                git add .
                git commit -m "Build successful - Changes pushed by Jenkins"
                git push origin main
            '''
        }
        failure {
            echo "Échec du pipeline. Aucune poussée n'a été effectuée."
        }
        always {
            echo "Nettoyage du workspace"
            cleanWs()
        }
    }
}
