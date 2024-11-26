pipeline {
    agent any
    tools {
        maven 'Maven3' // Maven configuré dsdans Jenkins
    }
    environment {
        BRANCH_NAME = 'Dev'
        MAIN_BRANCH = 'main'
    }
    stages {
        stage('Checkout') {
            steps {
                echo "Récupération du code depuis la branche ${BRANCH_NAME}"
                git credentialsId: 'github-creds', branch: "${BRANCH_NAME}", url: 'https://github.com/MatisVivier/Pipeline-Git-Jenkins.git'
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
        stage('Push to Dev') {
            steps {
                echo "Push des modifications sur la branche ${BRANCH_NAME}"
                bat """
                    "C:\\Program Files\\Git\\bin\\git.exe" config user.name "MatisVivier"
                    "C:\\Program Files\\Git\\bin\\git.exe" config user.email "matisvivier2004@gmail.com"
                    "C:\\Program Files\\Git\\bin\\git.exe" add .
                    "C:\\Program Files\\Git\\bin\\git.exe" commit -m "Mise à jour sur ${BRANCH_NAME}"
                    "C:\\Program Files\\Git\\bin\\git.exe" push https://MatisVivier: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@github.com/MatisVivier/Pipeline-Git-Jenkins.git ${BRANCH_NAME}
                """
            }
        }
        stage('Merge and Push to Main') {
            when {
                branch 'dev'  // Cette étape se déclenche uniquement sur la branche dev f
            }
            steps {
                script {
                    if (currentBuild.currentResult == 'SUCCESS') {
                        echo "Pipeline réussie, merging vers main..."
                        bat """
                            "C:\\Program Files\\Git\\bin\\git.exe" fetch origin
                            "C:\\Program Files\\Git\\bin\\git.exe" checkout ${MAIN_BRANCH}
                            "C:\\Program Files\\Git\\bin\\git.exe" merge ${BRANCH_NAME} --no-ff -m "Merge branch ${BRANCH_NAME} into ${MAIN_BRANCH}"
                            "C:\\Program Files\\Git\\bin\\git.exe" push https://MatisVivier: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@github.com/MatisVivier/Pipeline-Git-Jenkins.git ${MAIN_BRANCH}
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