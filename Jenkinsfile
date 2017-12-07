pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        sh '''chmod 755 script/package.sh
cd script/
./package.sh'''
      }
    }
    stage('install') {
      steps {
        sh 'mvn install'
      }
    }
    stage('run') {
      steps {
        sh 'cp /var/lib/jenkins/.m2/repository/com/sackr/eforum-front/1.0.0/eforum-front-1.0.0.war /var/lib/tomcat/webapps/ROOT.war'
      }
    }
  }
}