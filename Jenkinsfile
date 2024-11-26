pipeline {
    agent any
    tools {
        maven 'Maven3' // Maven configuré dans Jenkins
    }
    environment {
        BRANCH_NAME = 'dev' // Remplacer par le nom de ta branche de développement
        MAIN_BRANCH = 'main' // Remplacer par la branche principale
        GIT_PATH = 'C:\\Program Files\\Git\\bin\\git.exe' // Chemin absolu vers Git
    }
    stages {
        stage('Checkout') {
            steps {
                echo "Récupération du code depuis la branche ${BRANCH_NAME}"
                bat "${GIT_PATH} clone --branch ${BRANCH_NAME} https://github.com/MatisVivier/Pipeline-Git-Jenkins.git"
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
                            \"${GIT_PATH}\" config user.name "Jenkins"
                            \"${GIT_PATH}\" config user.email "jenkins@example.com"
                            \"${GIT_PATH}\" fetch origin
                            \"${GIT_PATH}\" checkout ${MAIN_BRANCH}
                            \"${GIT_PATH}\" merge ${BRANCH_NAME} --no-ff -m "Merge branch ${BRANCH_NAME} into ${MAIN_BRANCH}"
                            \"${GIT_PATH}\" push origin ${MAIN_BRANCH}
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
