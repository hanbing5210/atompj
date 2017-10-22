# 使用前准备
由于spark是基于jvm运行的所以至少需要暗黄jre(Java运行环境)

## 安装spark
1. 准备环境
2. 安装jdk
3. 上传spark安装包(spark是运行在Linux上的程序所以需要将spark安装包上传到Linux机器上(一般是集群))
4. 解压spark并修改配置文件(两个配置文件,第一个配置文件添加了3个配置文件)

    要修改的文件有两个spark-env.sh.template,slaves.template(这两个文件最好是复制出来再修改最终应该存在配置文件是spark-env.sh[运行时的配置],slaves[集群中worker所在的机器,在这里配置后可以通过shell脚本批量启动])
    1. 修改spark-evn.sh
        1. 非高可用
            1.  master的配置:
                export JAVA_HOME=/usr/java/jdk1.8.0_111
                export SPARK_MASTER_IP=node1.edu360.cn
                export SPARK_MASTER_PORT=7077
            2.  worker节点所在的位置
                node2.edu360.cn
                node3.edu360.cn
                node4.edu360.cn
        2. 高可用
            1.  master的配置
                export JAVA_HOME=/usr/java/jdk1.8.0_111
                export SPARK_DAEMON_JAVA_OPTS="-Dspark.deploy.recoveryMode=ZOOKEEPER -Dspark.deploy.zookeeper.url=zk1,zk2,zk3 -Dspark.deploy.zookeeper.dir=/spark"
            2. worker节点同非高可用
5. 将配置好的spark程序文件夹发送到集群的其他机器上(可以使用脚本`for i in {5..8}; do scp -r /bigdata/spark-2.2.0-bin-hadoop2.7/ node-$i:/bigdata; done`)
6. 启动spark(安装目录/sbin/start-all.sh)
7. 通过web页面访问spark管理界面(master所在的机器的地址+8080端口)

---
非高可用和高可用的区别:
配置了高可用的spark集群和非高可用的配置文件差别只在evn
启动时:高可用集群需要启动zookeeper,非高可用不需要

---
在启动时需要指定worker运行时的资源(内存,core)
bin/spark-submit --master master所在的地址(包含端口号,需要徐鹤spark的传输协议) --class 要运行的类 --executor-memory 运行时的内存资源(每个executor单独的内存,需要指定内存单位) --total-executor-cores 运行时core的数量(可以理解成线程数这是APP运行时全部的核数)  要运行的类的jar包(做好写全路径) 要运行的类需要的参数

---
# 提交第一个程序
bin/spark-submit --master spark://node-5:7077 --class org.apache.spark.examples.SparkPi --executor-memory 2048mb --total-executor-cores 12  examples/jars/spark-examples_2.11-2.2.0.jar 1000

----
提交一个spark程序到spark集群会产生哪些进程?
SparkSubmit(Driver)用于提交任务
Executor用于执行真正的计算任务

提交任务可以指定多个master地址,目的是为了提交的任务高可用(shell模式下)
bin/spark-submit --master spark://node-4:7077,node-5:7077 --class org.apache.spark.examples.SparkPi --executor-memory 2048mb --total-executor-cores 12  examples/jars/spark-examples_2.11-2.2.0.jar 1000

Spark Shell (spark的一个命令式交互窗口,里面可以写spark程序,方便学习和测试,同时他也是一个客户端,用于提交spark应用程序)

`/bigdata/spark-2.2.0-bin-hadoop2.7/bin/spark-shell`

上述命令使用的是未指定master地址的方式,使用该方式spark将运行李擦了模式来模拟集群运行

----
Yarn和Spark的StandAlone调度模式对比

 ResouceManager        Master   管理子节点、资源调度、接收任务请求
 NodeManger            Worker   管理当前节点，并管理子进程
 YarnChild             Executor 运行真正的计算逻辑的（Task）
 Client                SparkSubmit  （Client + ApplicaitonMaster）提交app，管理该任务的Executor
 ApplicaitonMaster 					 并将Task提交到（Executor）

 ----
 用idea编写spark程序
创建RDD，然后对RDD进行操作（调用RDD的方法，方法分为两类，一个叫Transformation，一类叫Action）

rdd上的方法和scala原生的方法是有区别的

写好程序，打包上传集群运行

本地模式运行spark程序，setMaster("local[\*]")
