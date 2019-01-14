pipeline {
  agent any
  stages {
    stage('Build') {
      post {
        failure {
          mail(subject: 'build finished', body: 'build failed', to: 'fa_chibah@esi.dz')

        }

        success {
          mail(subject: 'build finished', body: 'build success', to: 'fa_chibah@esi.dz')

        }

      }
      steps {
        bat 'gradle build'
        bat 'gradle myJavaDocs'
        archiveArtifacts(artifacts: 'build/libs/*.jar , build/docs/javadoc/*', onlyIfSuccessful: true)
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
        branch 'master'
      }
      steps {
        bat 'gradle uploadArchives'
      }
    }
    stage('Slack Notification') {
      when {
        branch 'master'
      }
      steps {
        slackSend(channel: 'jenkins', color: '#ffffff', message: 'tree reached slack notification')
      }
    }
  }
}