pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        bat 'gradle build'
        bat 'gradle javadoc'
        bat 'gradle uploadArchives'
      }
    }
    stage(' Mail Notification ') {
      steps {
        mail(subject: 'Modification', body: 'This is a sample mail for notification')
      }
    }
  }
}