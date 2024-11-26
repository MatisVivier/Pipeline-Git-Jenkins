pipeline {
    agent any
    tools {
        git 'git' // Git configuré dans Jenkins
        maven 'Maven3' // Maven configuré dans Jenkins
    }
    environment {
        BRANCH_NAME = 'Dev' // Remplacer par le nom de ta branche de développement
        MAIN_BRANCH = 'main' // Remplacer par la branche principale
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
        stage('Merge and Push to Main') {
            when {
                branch 'dev'  // Cette étape se déclenche uniquement sur la branche dev
            }
            steps {
                script {
                    // Vérifie si la build est réussie
                    if (currentBuild.currentResult == 'SUCCESS') {
                        echo "Pipeline réussie, merging vers main..."

                        // Configuration de Git avec le chemin absolu
                        bat """
                            "C:\\Program Files\\Git\\bin\\git.exe" config user.name "Jenkins"
                            "C:\\Program Files\\Git\\bin\\git.exe" config user.email "jenkins@example.com"
                            "C:\\Program Files\\Git\\bin\\git.exe" fetch origin
                            "C:\\Program Files\\Git\\bin\\git.exe" checkout ${MAIN_BRANCH}
                            "C:\\Program Files\\Git\\bin\\git.exe" merge ${BRANCH_NAME} --no-ff -m "Merge branch ${BRANCH_NAME} into ${MAIN_BRANCH}"
                            "C:\\Program Files\\Git\\bin\\git.exe" push origin ${MAIN_BRANCH}
                        """
                    } else {
                        echo "Pipeline échouée, pas de merge vers main."
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
