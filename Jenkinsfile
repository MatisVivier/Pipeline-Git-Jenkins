pipeline {
    agent any
    tools {
        maven 'Maven3' // Maven configuré dans Jenkins
    }
    environment {
        BRANCH_NAME = 'feature/xyz' // Remplacer par le nom de ta branche de développement
    }
    stages {
        stage('Checkout') {
            steps {
                echo "Récupération du code depuis la branche ${BRANCH_NAME}"
                git branch: "${BRANCH_NAME}", url: 'https://github.com/MatisVivier/Pipeline-Git-Jenkins.git'
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
        stage('Push to Main') {
            when {
                branch 'feature/xyz'  // Cette étape se déclenche uniquement sur la branche feature/xyz
            }
            steps {
                script {
                    // Si la pipeline passe avec succès, push vers main
                    if (currentBuild.result == 'SUCCESS') {
                        echo "Pipeline réussie, pushing vers main..."
                        sh '''
                            git config user.name "Jenkins"
                            git config user.email "jenkins@example.com"
                            git checkout main
                            git merge feature/xyz --no-ff -m "Merging changes from feature/xyz"
                            git push origin main
                        '''
                    } else {
                        echo "Pipeline échouée, pas de push vers main."
                    }
                }
            }
        }
    }
    post {
        success {
            echo "Pipeline exécutée avec succès !"
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
