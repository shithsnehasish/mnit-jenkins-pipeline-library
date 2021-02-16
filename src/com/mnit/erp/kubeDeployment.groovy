package com.mnit.erp

def call(Map pipelineParams) {

    def fileWrite = libraryResource "$APP_NAME/service.yaml"
	writeFile file: "${WORKSPACE}/service.yaml", text: fileWrite
    withCredentials([usernamePassword(credentialsId: "${ENVIRONMENT}-k8s-master", passwordVariable: 'pwd', usernameVariable: 'user')]) { 
        sh '''
            cd ${WORKSPACE}/
            sed -i "s;%APP_NAME%;${APP_NAME};" service.yaml
            sed -i "s;%NAMESPACE%;${NAMESPACE};" service.yaml
            sed -i "s;%CONTAINER_PORT%;${CONTAINER_PORT};" service.yaml
            sed -i "s;%HOST_PORT%;${HOST_PORT};" service.yaml
            sed -i "s;%HOST_IP%;${HOST_IP};" service.yaml
            sed -i "s;%REPO%;${REPO};" service.yaml
            sed -i "s;%REPLICAS%;${REPLICAS};" service.yaml
            sed -i "s;%ENVIRONMENT%;${ENVIRONMENT};" service.yaml
            echo $pwd | sudo -S kubectl apply -f service.yaml
        '''
    }
}