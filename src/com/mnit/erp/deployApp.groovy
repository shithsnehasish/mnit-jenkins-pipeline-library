package com.mnit.erp

def call(Map pipelineParams) {

    def fileWrite = libraryResource "app-service.yaml"
	writeFile file: "${WORKSPACE}/${REPO}/app-service.yaml", text: fileWrite
    sh '''
        cd ${WORKSPACE}/${REPO}/
        sed -i "s;%APP_NAME%;${APP_NAME};" app-service.yaml
        sed -i "s;%NAMESPACE%;${NAMESPACE};" app-service.yaml
        sed -i "s;%DOCKER_REGISTRY%;${DOCKER_REGISTRY};" app-service.yaml
        sed -i "s;%CONTAINER_PORT%;${CONTAINER_PORT};" app-service.yaml
        sed -i "s;%HOST_PORT%;${HOST_PORT};" app-service.yaml
        sed -i "s;%HOST_IP%;${HOST_IP};" app-service.yaml
        sudo kubectl apply -f app-service.yaml
    '''
}