
    
    node {
        stage("Parse Yaml") {
            sh '''
                rm -rf ${WORKSPACE}/*
            '''
            yamlParser()
        }
    }

def yamlParser()
{
    def datas = readYaml text: """\
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
"""
    //def config = new YamlSlurper().parseText(configYaml)
    def connList = datas.connections
    connList.add("WS3")
    datas.connections.each {
    println "Connection name: ${it}"
}
writeYaml file: "test1.yml", data: datas


}
        