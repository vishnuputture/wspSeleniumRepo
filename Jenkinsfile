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
                bat 'mvn compile'
            }
        }
        stage('Test'){
            steps{
                bat 'mvn -f pom.xml clean test -P runSanity -DDefaultExecutionMode=LOCAL'
            }
        }
    }
}
