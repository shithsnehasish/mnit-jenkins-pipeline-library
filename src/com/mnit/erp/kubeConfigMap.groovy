package com.mnit.erp

def call(Map pipelineParams) {

    def fileWrite = libraryResource "configMap.yaml"
	writeFile file: "${WORKSPACE}/${REPO}/configMap.yaml", text: fileWrite
    withCredentials([usernamePassword(credentialsId: '$ENVIRONMENT-k8s-master', passwordVariable: 'pwd', usernameVariable: 'user')]) { 
        sh '''
            cd ${WORKSPACE}/${REPO}/
            sed -i "s;%APP_NAME%;${APP_NAME};" configMap.yaml
            sed -i "s;%NAMESPACE%;${NAMESPACE};" configMap.yaml
            sed -i "s;%HOST_IP%;${HOST_IP};" configMap.yaml
            sed -i "s;%DB_NAME%;${DB_NAME};" configMap.yaml
            sed -i "s;%ENVIRONMENT%;${ENVIRONMENT};" configMap.yaml
            echo $pwd | sudo -S kubectl apply -f configMap.yaml
        '''
    }
}