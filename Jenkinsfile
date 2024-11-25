pipeline {
    agent any
    tools {
        maven 'Maven3' // Le nom de l'installation Maven configurée dans "Global Tool Configuration"
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/MatisVivier/Pipeline-Git-Jenkins.git' // Remplace par l'URL de ton dépôt
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean install' // Commande pour Windows
                // Si tu es sur Linux ou MacOS, remplace par :
                // sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test' // Pour Windows
            }
        }
        stage('Package') {
            steps {
                bat 'mvn package' // Génération de l'artefact (ex. JAR ou WAR)
            }
        }
    }
    post {
        success {
            echo 'Build réussi !'
        }
        failure {
            echo 'Le build a échoué.'
        }
    }
}
