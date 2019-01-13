pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        bat 'gradle build'
        bat 'gradle javadoc'
        archiveArtifacts(artifacts: 'build/libs/*.jar , build/docs/javadoc/*', onlyIfSuccessful: true)
      }
       post {
            failure {
              mail(subject: 'build finished', body: 'build failed', to: 'fa_chibah@esi.dz')
            }
            success {
              mail(subject: 'build finished', body: 'build success', to: 'fa_chibah@esi.dz')
            }
          }
    }
    stage(' Mail Notification ') {
      steps {
        mail(subject: 'Modification', body: 'This is a sample mail for notification')
      }
    }
  }
}