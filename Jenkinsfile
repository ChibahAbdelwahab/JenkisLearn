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
    stage('Code Analysis ') {
      parallel {
        stage('Code Analysis ') {
          steps {
            waitForQualityGate true
          }
        }
        stage('Test reporting ') {
          steps {
            jacoco()
          }
        }
      }
    }
    stage('Deployment') {
      steps {
        bat 'gradle uploadArchives'
      }
    }
    stage('Slack Notification ') {
      steps {
        slackSend(message: 'SLack msg ')
      }
    }
  }
}