pipeline {
  agent any
  stages {
    stage('createTag') {
      steps {
        git(url: 'https://github.com/KITSJayashishBanerjee/backup', branch: 'master', credentialsId: 'gitlab', poll: true)
      }
    }

  }
}