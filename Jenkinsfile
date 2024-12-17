pipeline {
    agent any
    tools {
        maven 'Maven3' // Maven configuré dans Jenkins
    }
    environment {
        BRANCH_NAME = 'Dev'
        MAIN_BRANCH = 'main'
        GIT_URL = 'https://github.com/MatisVivier/Pipeline-Git-Jenkins.git'
        GIT_CREDENTIALS = 'github-creds' // ID des credentials GitHub dans Jenkins
    }
    stages {
        stage('Checkout') {
            steps {
                echo "Récupération du code depuis la branche ${BRANCH_NAME}"
                git branch: "${BRANCH_NAME}", credentialsId: "${GIT_CREDENTIALS}", url: "${GIT_URL}"
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
                withCredentials([usernamePassword(credentialsId: "${GIT_CREDENTIALS}", usernameVariable: 'GIT_USER', passwordVariable: 'GIT_TOKEN')]) {
                    bat """
                        git config user.name "MatisVivier"
                        git config user.email "matisvivier2004@gmail.com"
                        git add .
                        git commit -m "Mise à jour sur ${BRANCH_NAME}"
                        git push https://${GIT_USER}:${GIT_TOKEN}@github.com/MatisVivier/Pipeline-Git-Jenkins.git ${BRANCH_NAME}
                    """
                }
            }
        }
        stage('Merge to Main') {
            when {
                branch 'Dev' // Déclenche cette étape uniquement sur la branche Dev
            }
            steps {
                echo "Merging ${BRANCH_NAME} vers ${MAIN_BRANCH}"
                withCredentials([usernamePassword(credentialsId: "${GIT_CREDENTIALS}", usernameVariable: 'GIT_USER', passwordVariable: 'GIT_TOKEN')]) {
                    bat """
                        git fetch origin
                        git checkout ${MAIN_BRANCH}
                        git merge ${BRANCH_NAME} --no-ff -m "Merge branch ${BRANCH_NAME} into ${MAIN_BRANCH}"
                        git push https://${GIT_USER}:${GIT_TOKEN}@github.com/MatisVivier/Pipeline-Git-Jenkins.git ${MAIN_BRANCH}
                    """
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
