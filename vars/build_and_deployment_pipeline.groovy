def call(Map pipelineParams) {
  env.BRANCH = pipelineParams.BRANCH
  env.REPO = pipelineParams.REPO
  pipeline {
    node {
      stage("Build Provsioning") {
        sh '''
          echo "testing params --"
          echo $REPO
          echo $BRANCH
        '''
      }
      stage("Static Code Analysis") {
        sh '''
          echo "testing params --"
          echo $REPO
          echo $BRANCH
        '''
      }
      stage("Upload Artifacts") {
        sh '''
          echo "testing params --"
          echo $REPO
          echo $BRANCH
        '''
      }
      stage("Deploy") {
        sh '''
          echo "testing params --"
          echo $REPO
          echo $BRANCH
        '''
      }
    }
  }
}
