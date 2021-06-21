
    
    node {
        stage("Checkout"){
        sh '''
            rm -rf ${WORKSPACE}/*
            git clone https://github.com/shithsnehasish/mnit-jenkins-pipeline-library.git
        '''
        }
        stage("Parse Yaml") {       
            yamlParser()
        }
       
    }

def yamlParser()
{
    sh "cp mnit-jenkins-pipeline-library/test.yml ."
    def datas = readYaml file: "test.yml"
    //def config = new YamlSlurper().parseText(configYaml)
    //println datas.users
    def envMap = datas.users.env
    //println envMap
    envMap.put("COUNTRY","SG")
    envMap.put("APP_TYPE","MS")
    envMap.each{ k, v -> println "${k}:${v}" }
    

    def connList = datas.connections
    connList.add("WS4")
    connList.add("WS5")
    datas.connections.each {
    println "Connection name: ${it}"
}
writeYaml file: "test-updt.yml", data: datas


}
        