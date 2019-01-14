pipeline {
  agent any
  stages {
    stage('Build') {
      post {
        failure {
          mail(subject: 'build Failed', body: 'build failed', to: 'fa_chibah@esi.dz')

        }

      }
      steps {
        bat 'gradle build'
        bat 'gradle myJavaDocs'
        archiveArtifacts(artifacts: 'build/libs/*.jar , build/docs/javadoc/*', onlyIfSuccessful: true)
      }
    }
    stage('Mail Notification') {
          steps {
                      mail(subject: 'OK ', body: 'build OK', to: 'fa_chibah@esi.dz')

            }
        }
    stage('Code Analysis') {
      parallel {
        stage('Code Analysis') {
          steps {
            withSonarQubeEnv('sonarqube') {
              bat(script: 'sonar-scanner', returnStatus: true)
            }

            waitForQualityGate true
          }
        }
        stage('Test Reporting') {
          steps {
            jacoco(buildOverBuild: true)
          }
        }
      }
    }
    stage('Deployment') {
     when {
            not {
              changeRequest target: 'master'
            }

          }
      steps {
        bat 'gradle uploadArchives'
      }
    }
    stage('Slack Notification') {
     when {
            not {
              changeRequest target: 'master'
            }

          }
      steps {
        slackSend(channel: 'jenkins', color: '#ffffff', message: 'tree reached slack notification')
      }
    }
  }
}