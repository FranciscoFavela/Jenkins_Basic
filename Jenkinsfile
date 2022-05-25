
pipeline {
    agent any
    triggers {
     pollSCM('* * * * *')
}
     environment {
        // General Variables for Pipeline
//         PROJECT_ROOT = 'raiz del directorio'
        EMAIL_ADDRESS = 'francisco-javier.favela@atos.net'
        REGISTRY = 'franciscofavelanajera/docker-profiles'
//         BUILD_NUMBER = 'Variable de entorno propia de jenkins'
    }
stages {
    stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

    stage("Compile") {
        steps {
        sh 'mvn -Dmaven.test.failure.ignore=true install'
    }
}
    stage("Clean"){
        steps {
        sh "mvn clean"
        }
    }
    stage("Unit test") {
    steps {
    sh "mvn test"
    }
}
stage("Code coverage") {
    steps {
        sh "mvn sonar:sonar -D sonar.host.url=http://172.18.0.1:9001 -D sonar.login=admin -D sonar.password=12345"
        publishHTML target: [
        reportDir: 'build/reports/jacoco/test/html',
        reportFiles: 'index.html',
        reportName: 'JacocoReport']
    sh "mvn test"
        }
    }
stage('SonarQube analysis') {
    steps {
        withSonarQubeEnv('SonarQube') {
        sh 'mvn sonar:sonar'
    }
    }
}
       stage('Build docker-image') {
        steps {
          sh "docker build -t ${REGISTRY}:${BUILD_NUMBER} ."
        }
      }
      stage('Deploy docker-image') {
        steps {
          // If the Dockerhub authentication stopped, do it again
          sh 'docker login'
          sh "docker push ${REGISTRY}:${BUILD_NUMBER}"
        } }
}
}
