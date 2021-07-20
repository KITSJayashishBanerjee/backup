pipeline {
  agent any
  stages {
    stage('checkout') {
      steps {
        git(url: 'https://github.com/KITSJayashishBanerjee/backup', branch: 'master', credentialsId: 'gitlab', poll: true)
      }
    }

    stage('build') {
      steps {
        echo 'build code'
      }
    }

  }
}