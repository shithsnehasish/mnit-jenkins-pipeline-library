def call(Map pipelineParams) {
  pipeline {
    node {
      stage("test") {
        sh '''
          echo "testing"
        '''
      }
    }
  }
}
