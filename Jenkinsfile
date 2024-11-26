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
            post {
                success {
                    echo "Les tests ont réussi !"
                }
                failure {
                    echo "Les tests ont échoué. Le pipeline ne poussera pas les changements."
                    currentBuild.result = 'FAILURE'
                    error('Tests échoués')
                }
            }
        }
        stage('Static Analysis') {
            steps {
                echo "Analyse du code avec Checkstyle"
                bat 'mvn checkstyle:check'
            }
            post {
                success {
                    echo "Aucune violation de code détectée."
                }
                failure {
                    echo "Des violations de code ont été détectées. Corrigez les erreurs avant de pousser."
                    currentBuild.result = 'FAILURE'
                    error('Violations détectées')
                }
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
