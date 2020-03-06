/* 所谓的 pipeline 就是软件定义编译过程*/
/* */
/* https://jenkins.io/zh/doc/book/pipeline/syntax/ */
pipeline {

    agent none
    environment {  // 配置环境变量
        GIT_URL = 'ssh://git@10.0.0.57:6022/root/open_store.git'
    }

    tools {  // 引用工具, 这个工具需要在 jenkins 的页面中 配置 maven 环境 ==> Manage jenkins -> Global Tool Configuration -> Maven
        maven 'apache-maven-3.6.3'

    }

    /*
      stages  包含一系列一个或多个 stage 指令, stages 部分是流水线描述的大部分"work" 的位置。
     建议 stages 至少包含一个 stage 指令用于连续交付过程的每个离散部分,比如构建, 测试, 和部署。
    */
    stages {
        stage('Input') {
            input {
                message "Should we continue?"
                ok "Yes, we should."
                parameters {
                    string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
                }
            }
        }


        stage('Pull Code from Git') {
            steps {     // steps 部分必须包含一个或多个步骤。
                git(
                        branch: "master",                                           // 切换到哪个分支
                        url: 'ssh://git@10.0.0.57:6022/root/open_store.git',        // git 仓库地址
                        changelog: true                                              // 是否打印log
                )
            }
        }
        stage('Compile Code') {
            steps {
                echo '开始编译了!!'
                sh 'mvn clean compile package -Dmaven.test.skip=true'
            }
        }

        stage('Deploy') {
            steps {
                echo '开始部署了!!'
                dir('./store-admin') {   //必须到 ./store-admin 目录下,否则运行表错
                    script {  // 忽略编译时报错的方法 参考 https://www.soinside.com/question/UxtwJvAgGqikPPcWSSKwC9
                        try {
                            sh 'docker stop store-admin'
                        } catch (e) {
                            echo '--------------'
                            echo e.message
                            echo '--------------'
                        }
                        try {
                            sh 'docker rm  store-admin'  // 删除容器
                        } catch (e) {
                            echo '##############'
                            echo e.message
                            echo '##############'
                        }
                        try {
                            sh 'docker rmi -f store-admin'  // 强制删除
                        } catch (e) {
                            echo '++++++++++++++'
                            echo e.message
                            echo '++++++++++++++'
                        }
                    }
                    //docker-compose up -d  在后台启动容器

                    sh 'docker build -f Dockerfile -t store-admin .'
                    // -d 表示后台启动 -t 表示分配一个伪终端
                    sh 'docker run -d -t  -p 0.0.0.0:9020:9020 --name store-admin store-admin '
                    // sh 'docker logs -f ContainerID'  -f 表示 实时监控之后的log
                }

            }

        }
    }

    post {   //完成时动作
        always {  // 无论如何都要打印
            echo 'always print'
            echo 'always do it'
        }
        success {
            echo '==> success!'  // 成功后打印
            // sh 'docker logs -f store-admin' // 打印 docker 中的log
        }
        failure { echo '==> failed!' } // 失败时打印

    }
}
