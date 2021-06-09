pipeline{
    agent{
        node{
            label 'master'
        }
    }

    options{
        timestamps()
    }

    stages{
        stage('Build'){
            steps{
                sh 'mvn compile'
            }
        }
        stage('Test'){
            steps{
                sh 'mvn -f pom.xml clean test -P runSanity -DDefaultExecutionMode=LOCAL'
            }
        }
    }
}
