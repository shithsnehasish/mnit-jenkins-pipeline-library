package com.mnit.erp

def call(Map pipelineParams) {

  def svcs = pipelineParams.SERVICES
    withCredentials([usernamePassword(credentialsId: 'dev-k8s-master', passwordVariable: 'pwd', usernameVariable: 'usr')]) { 
sh '''
echo "---
apiVersion: networking.k8s.io/v1beta1
kind: Ingress
metadata:
  name: $ENVIRONMENT-$NAMESPACE-ingress
  namespace: $NAMESPACE
  annotations:
    kubernetes.io/ingress.class: nginx
    nginx.ingress.kubernetes.io/ssl-redirect: "false"
    nginx.ingress.kubernetes.io/use-regex: "true"
    nginx.ingress.kubernetes.io/rewrite-target: /$1
spec:
  rules:
  - http:
      paths:" >> ${WORKSPACE}/ingress.yaml
'''
svcs.each {
  env.SVC = it
sh '''
echo "
      - backend:
          serviceName: $ENVIRONMENT-$SVC-svc
          servicePort: 80
        path: /$SVC(/|$)(.*)" >> ${WORKSPACE}/ingress.yaml
'''
}
sh '''
echo "
  - host: $DNS # change the IP address here
    http:
      paths:" >> ${WORKSPACE}/ingress.yaml
'''
svcs.each {
  env.SVC = it
sh '''
echo "
      - backend:
          serviceName: $ENVIRONMENT-$SVC-svc
          servicePort: 80
        path: /$SVC
            " >> ${WORKSPACE}/ingress.yaml
'''
}
sh '''
  echo Curiosity4ERP# | sudo -S kubectl apply -f ${WORKSPACE}/ingress.yaml
'''
    }
}