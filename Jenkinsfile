
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
        sh "mvn sonar:sonar -D sonar.host.url=http://172.18.0.3:9001 -D sonar.login=admin -D sonar.password=12345"
        publishHTML (target: [
        reportDir: 'build/reports/jacoco/test/html',
        reportFiles: 'index.html',
        reportName: 'JacocoReport'
])
sh "mvn test"
}
}
stage('SonarQube analysis') {
steps {
withSonarQubeEnv('SonarQubePruebas') {
sh 'mvn sonar:sonar'
}
}
}
}
}
