pipeline{
    agent{
        node{
            label 'windows_slave'
        }
    }

    options{
        timestamps()
    }

    environment{
        APP_CREDS= credentials('automationUser')
        APP_PROD= credentials('production')
    }

    stages{
        stage('PrintUserNamePassword'){
            steps{
                print APP_CREDS_USR
                print APP_CREDS_PSW
            }
        }
        stage('Build'){
            steps{
                bat 'mvn compile'
            }
        }
        stage('Test'){
        if (env.BRANCH_NAME == 'prod'){
            steps{
                bat 'mvn -f pom.xml clean test -P runSanity -DDefaultExecutionMode=LOCAL -DUserName=%APP_PROD_USR% -DPassword=%APP_PROD_PSW%'
            }
          }else{
             steps{
                 bat 'mvn -f pom.xml clean test -P runSanity -DDefaultExecutionMode=LOCAL -DUserName=%APP_CREDS_USR% -DPassword=%APP_CREDS_PSW%'
                }
            }
        }
    }

    post {
        success {
            emailext mimeType: 'text/html',
               body: "Execution Report Attachment Details",
               subject: "Email Report from - '${env.JOB_NAME}' - Build Passed",
               to: 'QAAutomation@winsupplyinc.com',
               attachmentsPattern: '**/ExtentReport.html'
            }
        failure {
             emailext mimeType: 'text/html',
               body: "Execution Report Attachment Details",
               subject: "Email Report from - '${env.JOB_NAME}' - Build Failed",
               to: 'QAAutomation@winsupplyinc.com',
               attachmentsPattern: '**/ExtentReport.html'
        }
    }
}
