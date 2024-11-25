pipeline {
    agent any
    tools {
        maven 'Maven3' // Le nom configuré dans Global Tool Configuration
    }
    environment {
        PROJECT_NAME = 'PIPELINE-GIT-JENKINS' // Nom du projet
        DEPLOY_DIR = 'PIPELINE-GIT-JENKINS' // Répertoire de déploiement simulé
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
                echo "Compilation du projet avec Maven"
                bat 'mvn clean install' // Remplace par `sh` si tu es sur Linux/MacOS
            }
        }
        stage('Test') {
            steps {
                echo "Exécution des tests unitaires"
                bat 'mvn test'
            }
        }
        stage('Package') {
            steps {
                echo "Génération du package (JAR ou WAR)"
                bat 'mvn package'
            }
        }
        stage('Static Analysis') {
            steps {
                echo "Analyse de la qualité du code avec SonarQube"
                bat 'mvn sonar:sonar -Dsonar.projectKey=${PROJECT_NAME}'
            }
        }
        stage('Deploy') {
            steps {
                echo "Déploiement simulé"
                script {
                    def artifactPath = findFiles(glob: '**/target/*.jar')[0].path
                    echo "Artefact trouvé : ${artifactPath}"
                    echo "Copie de l'artefact vers le répertoire de déploiement (${DEPLOY_DIR})"
                    bat "mkdir ${DEPLOY_DIR} & copy ${artifactPath} ${DEPLOY_DIR}"
                }
            }
        }
    }
    post {
        success {
            echo "Pipeline exécuté avec succès !"
        }
        failure {
            echo "Le pipeline a échoué. Vérifiez les logs pour plus d'informations."
        }
        always {
            echo "Pipeline terminé. Nettoyage des fichiers temporaires."
            cleanWs() // Supprime les fichiers temporaires du workspace
        }
    }
}
