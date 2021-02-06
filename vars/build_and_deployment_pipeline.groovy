def call(Map pipelineParams) {
  env.BRANCH = pipelineParams.BRANCH
  env.REPO = pipelineParams.REPO
  pipeline {
    node {
      stage("test") {
        sh '''
          echo "testing params --"
          echo $REPO
          echo $BRANCH
        '''
      }
    }
  }
}
