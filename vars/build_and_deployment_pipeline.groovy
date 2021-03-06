import com.mnit.util.checkoutSCM;
import com.mnit.erp.builldCompileApp;
import com.mnit.erp.environmentVars;
import com.mnit.erp.deployApp;
import com.mnit.util.staticCodeAnalysis;
import com.mnit.util.sendEmail;
import com.mnit.erp.deleteDeployment;
def call(Map pipelineParams) {
  try{
    timeout(time: 60, unit: 'MINUTES') {
      env.BRANCH = pipelineParams.BRANCH
      env.REPO = pipelineParams.REPO
      pipeline {
        new environmentVars().call(pipelineParams)
        node(pipelineParams.BUILD_NODE) {
          stage("Code Checkout") {
            new checkoutSCM().call(pipelineParams)
          }
          stage("Build") {
            new builldCompileApp().call(pipelineParams)
          }
          stage("Static Code Analysis") {
            new staticCodeAnalysis().call(pipelineParams)
          }
          stage("Delete Previous Deployment") {
            new deleteDeployment().call(pipelineParams)
          }  
          stage("Deploy") {
            new deployApp().call(pipelineParams)
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
