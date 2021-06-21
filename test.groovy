
    
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
    //envMap.put("COUNTRY","SG")
    //envMap.put("APP_TYPE","MS")
    def envList = user.env
    for( envs in envList)
    {
        println envs
    }
    
println datas
writeYaml file: "test-updt.yml", data: datas


}
        