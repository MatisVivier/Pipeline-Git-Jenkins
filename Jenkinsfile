pipeline {
    agent any
    tools {
        maven 'Maven3' // Nom configuré dans "Global Tool Configuration"
    }
    stages {
        stage('Check Maven') {
            steps {
                bat 'mvn -v' // Pour Windows
                // sh 'mvn -v' si vous êtes sur Linux/MacOS
            }
        }
    }
}
