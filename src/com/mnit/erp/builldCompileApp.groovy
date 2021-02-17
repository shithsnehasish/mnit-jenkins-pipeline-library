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
            cd $REPO
            npm install
            if [ $ENVIRONMENT == 'dev' ]
            then
                #npm run build:dev
                npm run build
            elif [ $ENVIRONMENT == 'uat' ]
            then
                npm run build:uat
            elif [ $ENVIRONMENT == 'prd' ]
            then
                npm run build:prd
            else
                npm run build
            fi

            version=$(jq -r .version package.json)
            sudo -i
            whoami
            cd ${WORKSPACE}/${REPO}
            docker build -t ${DOCKER_REGISTRY}${APP_NAME}:$version .
            docker build -t ${DOCKER_REGISTRY}${APP_NAME}:latest .
            docker push ${DOCKER_REGISTRY}${APP_NAME}:$version
            docker push ${DOCKER_REGISTRY}${APP_NAME}:latest
        '''
    }
    else {
        echo "No build for the APP_TYPE"+pipelineParams.APP_TYPE
    }
}