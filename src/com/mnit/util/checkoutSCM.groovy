package com.mnit.util

def call(Map pipelineParams) {
    SCM_URL="git@github.com:"+pipelineParams.GIT_GROUP+"/"+pipelineParams.REPO+".git"
    echo "Code checkout from SCM Repo"
    checkout([$class: 'GitSCM', branches: [[name: "${pipelineParams.BRANCH}"]],
        doGenerateSubmoduleConfigurations: false,
        extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: "${pipelineParams.REPO}"],
        [$class: 'CheckoutOption', timeout: 10]],
        submoduleCfg: [],
        userRemoteConfigs: [[url: "${SCM_URL}"]]])
    echo "Checkout is completed!"
}
