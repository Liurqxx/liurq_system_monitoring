# liurq_system_monitoring
利用zookeeper实现的系统监控小案例

>技术栈：Spring Boot 2.x + zookeeper3.6.3 + jdk1.8以上

步骤：
>1：zookeeper-agent是服务信息收集者,启动注册zookeeper一个临时节点,并上传收集到的参数信息

>2：zookeeper-web用于从zookeeper中拉取服务参数信息并展示到页面中
