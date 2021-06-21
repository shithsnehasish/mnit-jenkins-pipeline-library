
    
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
    def envList = datas.users.env
    //println envMap
    //envMap.put("COUNTRY","SG")
    //envMap.put("APP_TYPE","MS")
    envList.add("COUNTRY: SG")
    envList.add("APP_TYPE: MS")
    envList.each {
        println "ENV Vars ${it}"
    }

writeYaml file: "test-updt.yml", data: datas


}
        