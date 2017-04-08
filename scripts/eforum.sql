DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `eforum`.`admin` (`id`, `name`, `nick_name`, `password`) VALUES ('1', 'admin', '管理员', 'e10adc3949ba59abbe56e057f20f883e');



DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

INSERT INTO `article` (`id`, `content`, `create_time`, `title`, `description`) VALUES ('1', 'Spring是一个开源框架，Spring是于2003 年兴起的一个轻量级的Java 开发框架。', '2017-03-08 23:14:13', 'spring', 'spring介绍');
INSERT INTO `article` (`id`, `content`, `create_time`, `title`, `description`) VALUES ('2', 'Hibernate是一个开放源代码的对象关系映射框架，它对JDBC进行了非常轻量级的对象封装，它将POJO与数据库表建立映射关系，是一个全自动的orm框架，hibernate可以自动生成SQL语句，自动执行，使得Java程序员可以随心所欲的使用对象编程思维来操纵数据库。', '2017-03-08 23:14:13', 'hibernate', 'hibernate介绍');
INSERT INTO `article` (`id`, `content`, `create_time`, `title`, `description`) VALUES ('3', 'MyBatis 是支持普通 SQL查询，存储过程和高级映射的优秀持久层框架。', '2017-03-08 23:14:13', 'mybatis', 'mybatis介绍');
INSERT INTO `article` (`id`, `content`, `create_time`, `title`, `description`) VALUES ('4', 'Spring Boot是由Pivotal团队提供的全新框架，其设计目的是用来简化新Spring应用的初始搭建以及开发过程。', '2017-03-08 23:14:13', 'springboot', 'springboot介绍');
INSERT INTO `article` (`id`, `content`, `create_time`, `title`, `description`) VALUES ('5', 'Struts2是一个基于MVC设计模式的Web应用框架，它本质上相当于一个servlet，在MVC设计模式中，Struts2作为控制器(Controller)来建立模型与视图的数据交互。', '2017-03-08 23:14:13', 'struts2', 'struts2介绍');
