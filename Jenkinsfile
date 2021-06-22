pipeline{
    agent{
        node{
            label 'windows_slave'
        }
    }

    options{
        timestamps()
    }

    stages{
        stage('Build'){
            steps{
                bat 'mvn compile'
            }
        }
        stage('Test'){
            steps{
                bat 'mvn -f pom.xml clean test -P runSanity -DDefaultExecutionMode=LOCAL'
            }
        }
        stage('Email'){
            steps{
               emailext (to: 'QAAutomation@winsupplyinc.com', replyTo: 'QAAutomation@winsupplyinc.com', subject: "Email Report from - '${env.JOB_NAME}' ", body: readFile("test-output/emailable-report.html"), mimeType: 'text/html');
            }
        }
    }
}
