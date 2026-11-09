pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/psvdswathi-ui/pricewise.git'
            }
        }
        stage('Build') {
            steps { bat 'mvn clean package -DskipTests' }
        }
        stage('Test') {
            steps { bat 'mvn test' }
        }
        stage('Docker Build') {
            steps { bat 'docker build -t pricewise:latest .' }
        }
        stage('Deploy to Kubernetes') {
            steps { bat 'kubectl apply -f k8s/' }
        }
    }
}
