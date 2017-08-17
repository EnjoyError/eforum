eforum - 飞一般的BBS
=========================
![Author](https://img.shields.io/badge/Author-e%E7%A5%9E%7CSpring-brightgreen.svg)
![Porwer](https://img.shields.io/badge/Powered%20by-SpringBoot-red.svg)
![Version](https://img.shields.io/badge/Version-0.1-blue.svg)
* eforum是一个类似BBS的系统。如果你经常泡论坛，那么应该很熟悉。
* 但我不是仅仅想把它做成普通的BBS系统。
* 它应该拥有比普通BBS要绚丽的多的主题。
* 它应该拥有比普通BBS要舒服的多的交互。
* 它应该拥有比普通BBS要丰富的多的功能。
* 它应该拥有比普通BBS要强大的多的性能。
* 它刚诞生不久，但在大家的培养下，它会茁壮成长。
* 我们同样欢迎优秀的你 来为它添砖加瓦!

快速开始
-------------

* 将本项目fork至你自己的仓库，并在你的本机clone你自己fork后的仓库。
* 在你的电脑安装下载好node.js后，并在`eforum\eforum-front\src\main\resources\webjar`目录下
>运行:`npm instal`用于相关依赖模块的安装。  
运行:`npm install webpack -g`用于webpack的安装。  
运行:`webpack`用于静态资源打包。
* 在你的电脑安装下载好mysql后，修改`eforum\eforum-front\src\main\resources\application.properties`文件，设置你自己的数据库信息。
* 最后运行`eforum\eforum-front\src\main\java\org\eforum\FrontApplication.java`文件。
* 在浏览器输入`http://localhost:8081`，注意：端口可能会在更新中变化，具体实际端口号请查看启动日志

后端技术
-------------

* 核心框架: spring-boot
* 安全框架: apache-shiro
* mvc框架: spring mvc
* 数据库连接池: druid
* 持久层框架：spirng-data-jpa(hibernate)
* 接口测试框架: swagger2
* 单元测试框架: junit
* 日志框架: slf4j-log4j2

前端技术
-------------

* css框架: bootstrap
* js框架: jquery
* mvvm框架: angularjs
* 模块管理: webpack

讨论
-------------

* QQ群：209451266
* 作者花名：e神
