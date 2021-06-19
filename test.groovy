
    
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
    def datas = readYaml file: "test1.yml"
    //def config = new YamlSlurper().parseText(configYaml)
    def connList = datas.connections
    connList.add("WS4")
    connList.add("WS4")
    datas.connections.each {
    println "Connection name: ${it}"
}
writeYaml file: "test1.yml", data: datas


}
        