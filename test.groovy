import groovy.yaml.YamlSlurper
pipeline {
    new environmentVars().call(pipelineParams)
    node(pipelineParams.BUILD_NODE) {
        stage("Parse Yaml") {
            yamlParser()
        }
    }
}

def yamlParser()
{
    def datas = readYaml text: configYaml
    //def config = new YamlSlurper().parseText(configYaml)
    datas.connections.each {
    println "Connection name: ${it}"
}
}

def configYaml = '''\
---
application: "Sample App"
users:
- name: "mrhaki"
  likes:
  - Groovy
  - Clojure
  - Java
- name: "Hubert"
  likes:
  - Apples
  - Bananas
connections:
- "WS1"
- "WS2"
'''
        