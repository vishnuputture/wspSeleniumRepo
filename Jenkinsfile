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
            steps{
                bat 'mvn -f pom.xml clean test -P runSanity -DDefaultExecutionMode=LOCAL -DUserName=%APP_CREDS_USR% -DPassword=%APP_CREDS_PSW%'
            }
        }
          stage('Email'){
            steps{
               emailext (to: 'QAAutomation@winsupplyinc.com', replyTo: 'QAAutomation@winsupplyinc.com', subject: "Email Report from - '${env.JOB_NAME}' ", body: readFile("test-output/Extent Result/ExtentReport.html"), mimeType: 'text/html');
            }
        }
    }
}
