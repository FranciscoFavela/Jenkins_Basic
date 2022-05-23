
pipeline {
agent any
triggers {
pollSCM('* * * * *')
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
        sh "./mvnw clean"
        }
    }
    stage("Unit test") {
    steps {
    sh "./mvnw test"
    }
}
stage("Code coverage") {
    steps {
        sh "./mvnw test"
        publishHTML (target: [
        reportDir: 'build/reports/jacoco/test/html',
        reportFiles: 'index.html',
        reportName: 'JacocoReport'
])
sh "./mvnw test"
}
}
stage('SonarQube analysis') {
steps {
withSonarQubeEnv('SonarQubePruebas') {
sh './mvnw sonar:sonar'
}
}
}
}
}
