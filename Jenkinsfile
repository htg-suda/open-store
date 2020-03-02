pipeline {
    agent any
    environment {  // 配置环境变量
        GIT_URL = 'ssh://git@10.0.0.57:6022/root/open_store.git'
    }

    tools {  // 引用工具, 需要在 jenkins 配置 maven 环境 ==> Manage jenkins -> Global Tool Configuration -> Maven
        maven 'apache-maven-3.6.3'
    }

    stages {
        stage('Pull Code from Git') {
            steps {
                git(
                        branch: "master",                                           // 切换到哪个分支
                        url: 'ssh://git@10.0.0.57:6022/root/open_store.git',        // git 仓库地址
                        changelog: true                                              // 是否打印log
                )
            }
        }
        stage('Compile Code') {
            steps {
                sh 'mvn clean compile package -Dmaven.test.skip=true'
            }
        }

        stage('Deploy'){


        }
    }

    post {   //完成时动作
        always {  // 无论如何都要打印
            echo 'always print'
            echo 'always do it'
        }
        success {
            echo '==> success!'  // 成功后打印
            sh 'docker logs -f store-admin' // 打印 docker 中的log
        }
        failure { echo '==> failed!' } // 失败时打印

    }
}
