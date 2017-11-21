## updp平台
``updp``(uPanda platform)是一个以微服务运行的平台，项目使用SpringBoot作为基础进行开发。项目成立的初期更多是为了锻炼、沉淀自身技术，将学习所得的互联网新技术运用到当前项目中，并逐步沉淀出一个技术框架，备战互联网产品开发。技多不压身。

## 模块介绍
模块|名称|说明  
----|----|----
updp_common|通用项目|提供通用的DTO、枚举、Exception、工具类等
updp_eureka|服务注册中心|提供服务发现
updp_manage_system|后台管理系统|后台管理界面，提供权限管理、菜单管理、后台业务管理
updp_server_api|API外部接口项目|对外的REST API，且集成Swagger2做REST API DOC
updp_server_system|系统服务项目|系统相关接口服务，如后台用户的登录接口
updp_server_system_api|系统服务外部包项目|系统相关接口服务独立的JAR包

## 技术选型
#### 后端：
技术 |  官网
----|----
Maven|http://maven.apache.org/
Spring Boot|http://projects.spring.io/spring-boot/
Spring Cloud|http://projects.spring.io/spring-cloud/
Spring Framework|http://projects.spring.io/spring-framework/
Spring MVC|http://projects.spring.io/spring-framework/
JPA|http://projects.spring.io/spring-data-jpa/
MyBatis|https://github.com/mybatis/
Mybatis-PageHelper|https://github.com/pagehelper/pagehelper-spring-boot
~~Alibaba Dubbo~~|~~http://dubbo.io/~~
Alibaba Druid|https://github.com/alibaba/druid
Apache Shiro|http://shiro.apache.org/
Thymeleaf|http://www.thymeleaf.org/
Swagger2|https://swagger.io/
#### 前端：
技术 |  官网
----|----
Vue.js|https://cn.vuejs.org/
jQuery|https://jquery.org/
BootStrap|http://getbootstrap.com/
ES2015 (ES6)|
#### 环境：
技术 |  官网
----|----
Java 8|https://www.java.com
Docker|https://www.docker.com/
MySql|https://www.mysql.com/
MongoDB|https://www.mongodb.com/
Redis|https://redis.io/
Zookeeper|https://zookeeper.apache.org/
Nginx|http://nginx.org/

## 运行
#### 1.环境安装

项目需安装``MySQL``、``MongoDB``、``Redis``，搭建``ZooKeeper``，这里建议使用``Docker``直接pull下来比较方便，免去自行安装，几行命令就可以完成。

没有安装过Docker先安装[![](https://d1q6f0aelx0por.cloudfront.net/icons/whale-x-win.png "title")](https://www.docker.com/get-docker)

Docker下载镜像
```
docker pull zookeeper
docker pull mysql
docker pull mongo
docker pull redis
```

Docker创建容器
若端口被占用，自行修改命令行中的端口
```
docker run --name zookeeper -p 2181:2181 -p 2888:2888 -p 3888:3888  -d zookeeper
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1 -d mysql
docker run --name mongodb -p 27017:27017  -d mongo
docker run --name redis -p 6379:6379  -d redis
```

Docker启动容器
```
docker start zookeeper
docker start mysql
docker start mongodb
docker start redis
```


#### 2.下载git代码并编译

提示：执行git命令前，请确保已安装
[![](https://git-scm.com/images/logo.png  "title")](https://git-scm.com/downloads)

2.1 从git下载``Dubbox 2.8.4``并编译到本地Maven库
```
git clone https://github.com/shuvigoss/dubbox.git
cd dubbox
mvn install
```

2.2 从git下载``updp``项目、编译
```
git clone https://gitee.com/upanda/updp.git
cd updp
mvn install
```
2.3 使用Eclipse或IntelliJ IDEA 导入updp Maven项目

2.4 若端口有修改，先自行修改application.yml和dobbo.xml配置文件

#### 3.运行
1.启动
``updp_eureka``com.upanda.updp.eureka.EurekaApplication

2.启动
``updp_server_system``com.upanda.updp.server.App

3.启动
``updp_manage_system``com.upanda.updp.manage.system.App

#### 4.登录
http://127.0.0.1:8001

![image](https://gitee.com/upanda/updp/attach_files/download?i=97346&u=http%3A%2F%2Ffiles.git.oschina.net%2Fgroup1%2FM00%2F01%2FFF%2FPaAvDFnOFoiAc6a_AA4_DdnBuNY638.png%3Ftoken%3Db1c1297bc153be57f0e63d6e27dd3c61%26ts%3D1506678408%26attname%3Dupdp_system_login_page.png)


## 架构图
![image](https://gitee.com/upanda/updp/attach_files/download?i=93981&u=http%3A%2F%2Ffiles.git.oschina.net%2Fgroup1%2FM00%2F01%2FD7%2FPaAvDFmxAvGAH7-wAAB3x0wOkw8923.png%3Ftoken%3D724b4fd0c6f8ff1bef97bf10edf1716c%26ts%3D1506678408%26attname%3Dframework.png)
