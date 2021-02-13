package com.mnit.erp

def call(Map pipelineParams) {

    withCredentials([usernamePassword(credentialsId: '$ENVIRONMENT-k8s-master', passwordVariable: 'pwd', usernameVariable: 'user')]) { 
        sh '''
            echo $pwd | sudo -S kubectl delete statefulset $ENVIRONMENT-$APP_NAME -n $NAMESPACE
        '''
    }
}