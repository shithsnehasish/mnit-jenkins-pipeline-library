
def call(Map pipelineParams) {
  try{
    timeout(time: 60, unit: 'MINUTES') {
      pipeline {
        env.REPO = pipelineParams.REPO
        node {
          stage("Code Checkout") {
            sh '''
              git clone ${REPO}
            '''
          }
          stage("Build") {
            sh '''
              /opt/apache-maven-3.8.1/mvn clean install
            '''
          }
          stage("Static Code Analysis") {
            sh '''
              echo "Static Code Analysis"
            '''
          }
          stage("Delete Previous Deployment") {
            sh '''
              echo "Delete Previous Deployment"
            '''
          }  
          stage("Deploy") {
            sh '''
              echo "Deployment"
            '''
          }
        }
        if(pipelineParams.EMAIL_TO_LIST?.trim()){   
          echo "email send enabled"   
          //new sendEmail().call(pipelineParams,"SUCCESS")   
        } 

      }
    }
  }catch (err) {
    echo "in catch block" 
    echo "Caught: ${err}" 
    currentBuild.result = 'FAILURE' 
    if(pipelineParams.EMAIL_TO_LIST?.trim()){   
      echo "email send enabled"   
      //sendEmail().call(pipelineParams,"FAILURE")   
    }  
    throw err
  }
}
