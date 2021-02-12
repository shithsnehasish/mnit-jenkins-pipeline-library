package com.mnit.util

def call(Map pipelineParams) {
    SCM_URL="https://github.com/"+pipelineParams.GIT_GROUP+"/"+pipelineParams.REPO
    echo "Code checkout from SCM Repo"
    checkout([$class: 'GitSCM', branches: [[name: "${pipelineParams.BRANCH}"]],
        doGenerateSubmoduleConfigurations: false,
        extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: "${pipelineParams.REPO}"],
        [$class: 'CheckoutOption', timeout: 10]],
        submoduleCfg: [],
        userRemoteConfigs: [[url: "${SCM_URL}"]]])
    echo "Checkout is completed!"
}
