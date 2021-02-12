package com.mnit.erp

def call(Map pipelineParams) {
    if(pipelineParams.APP_TYPE == "JAVA")
    {
        sh '''
            cd $REPO
            mvn deploy -P docker -Ddocker.host=${DOCKER_HOST} -Ddocker.registry.name=${DOCKER_REGISTRY} -Dmaven.test.skip=true
        '''
    }
    else if(pipelineParams.APP_TYPE == "NODE")
    {
        sh '''

        '''
    }
    else {
        echo "No build for the APP_TYPE"+pipelineParams.APP_TYPE
    }
}