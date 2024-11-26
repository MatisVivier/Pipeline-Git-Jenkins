pipeline {
    agent any
    tools {
        maven 'Maven3' // Maven configuré dans Jenkins
    }
    stages {
        stage('Checkout') {
            steps {
                echo "Récupération du code depuis le dépôt Git"
                git branch: 'main', url: 'git@github.com:MatisVivier/Pipeline-Git-Jenkins.git'
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
        stage('Push Changes') {
            steps {
                script {
                    // Utiliser ssh-agent pour gérer la clé SSH
                    sshagent(['git-ssh-key']) {
                        echo "Pousser les changements vers Git"
                        bat '''
                            git config user.name "MatisVivier"
                            git config user.email "matisvivier2004@gmail.com"
                            git add .
                            git diff-index --quiet HEAD || git commit -m "Build successful - Changes pushed by Jenkins"
                            git push origin main
                        '''
                    }
                }
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
