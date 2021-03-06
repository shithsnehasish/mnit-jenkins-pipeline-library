
    
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
    def usersList = datas.users
    //println envMap
    def envList = usersList.env
    Map envMap = [:]
    envMap.put("COUNTRY","SG")
    envMap.put("APP_TYPE","MS")
    envList.add(envMap)
    println "envList --> "+envList
    for( envs in envList)
    {
        echo "inside loop"
        println envs
    }
    
println datas
writeYaml file: "test-updt.yml", data: datas


}
        