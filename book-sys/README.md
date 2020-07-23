# 图书管理系统

## 项目目标

+ 复习数据库相关知识：数据库设计、基本CRUD操作，关联查询操作，JDBC操作
+ 复习Servlet处理HTTP请求
+ 复习HTTP相关知识：请求方法、响应状态码、数据类型等
+ 学习项目开发流程

## 开发环境与技术栈

+ Windows/Mac/Linux
+ Maven
+ Servlet
+ MySQL
+ Jackson

## 项目功能

主要业务：管理学校图书信息，记录并管理学生借阅图书信息

+ 用户注册（可屏蔽不实现）
+ 用户登录、会话管理
+ 用户信息管理（可不实现）
+ 班级信息管理（可不实现）
+ 学生信息管理（可不实现）
+ 图书信息管理（可不实现）
+ 图书借阅信息管理（可实现部分）

## 项目演示

### 用户登录

<img src="assets\用户登录.png" style="zoom:60%;float:left;" />

### 用户管理（可不实现）

+ 查询用户列表

![](assets\用户查询.png)

+ 新增用户

![](assets\用户新增.png)

+ 修改用户

![](assets\用户修改.png)

+ 删除用户

![](assets\用户删除.png)

### 班级管理（略，类似用户管理）

### 学生管理（略，类似用户管理）

### 图书管理（略，类似用户管理）

### 图书借阅管理

+ 借阅信息查询

![](assets\借阅查询.png)

+ 新增借阅信息

![](assets\借阅新增.png)

+ 修改借阅信息

![](assets\借阅修改.png)

+ 删除借阅信息

![](assets\借阅删除.png)

## 数据库设计
### 数据库表关系图

![](assets\数据库表关系.png)

以上关系可见：

1. 学生表和班级表为多对一关系
2. 学生表和图书借阅信息表为一对多关系
3. 图书表和图书借阅信息表为一对多关系
4. 由2和3可知，图书表和学生表在借阅的场景下，表现出多对多关系
5. 数据字典表和数据字典标签表为一对多关系

### 字典表说明

数据字典表和数据字典标签表主要用在一些通用的下拉菜单选项。

如班级表中的字段：学生是哪一届的，学生专业

+ 如果单独设计需要单独一张表，实际存放的数据也不会太多，可以考虑设计在整体的一张表。
+ 页面上展示时，这些字段也是下拉选项。
+ 一般在设计上考虑为两张表：数据字典表和数据字典标签表（一对多关系）来保存。两张表都是key、value的形式，字典表是父节点，字典标签表是子节点，下拉菜单通过父节点的key查询出所有关联的子节点，再使用子节点的key、value进行下拉菜单选项的初始化

比如一个下拉菜单，页面元素-接口请求/响应-数据库关系如下：

![](assets/下拉菜单.png)

### 创建数据库及表

```mysql
drop database if exists book;
create database book character set utf8mb4;

use book;

drop table if exists user;
create table user(
    id int primary key auto_increment,
    username varchar(20) not null unique comment '用户账号',
    password varchar(20) not null comment '密码',
    nickname varchar(20) comment '用户昵称',
    email varchar(50) comment '邮箱',
    create_time timestamp default NOW() comment '创建时间'
) comment '用户表';

drop table if exists dictionary;
create table dictionary(
    id int primary key auto_increment,
    dictionary_key varchar(20) not null unique comment '键',
    dictionary_value varchar(20) not null comment '值',
    dictionary_desc varchar(20) comment '备注',
    create_time timestamp default NOW() comment '创建时间'
) comment '数据字典';

drop table if exists dictionary_tag;
create table dictionary_tag(
    id int primary key auto_increment,
    dictionary_tag_key varchar(20) not null comment '键',
    dictionary_tag_value varchar(20) not null comment '值',
    dictionary_tag_desc varchar(20) comment '备注',
    dictionary_id int comment '数据字典id',
    create_time timestamp default NOW() comment '创建时间',
    foreign key (dictionary_id) references dictionary(id)
) comment '数据字典标签';

drop table if exists classes;
create table classes(
    id int primary key auto_increment,
    classes_name varchar(20) not null comment '班级名称',
    classes_graduate_year varchar(20) comment '毕业年份，数据字典000001',
    classes_major varchar(20) comment '专业，数据字典000002',
    classes_desc varchar(50) comment '备注',
    create_time timestamp default NOW() comment '创建时间'
) comment '班级表';

drop table if exists student;
create table student(
    id int primary key auto_increment,
    student_name varchar(20) not null comment '姓名',
    student_no varchar(20) comment '学号',
    id_card varchar(20) comment '身份证号',
    student_email varchar(50) comment '邮箱',
    classes_id int comment '班级id',
    create_time timestamp default NOW() comment '创建时间',
    foreign key (classes_id) references classes(id)
) comment '学生表';

drop table if exists book;
create table book(
   id int primary key auto_increment,
   book_name varchar(50) not null comment '图书名称',
   author varchar(20) comment '作者',
   price decimal(10,2) comment '价格',
   create_time timestamp default NOW() comment '创建时间'
) comment '图书信息';

drop table if exists borrow_record;
create table borrow_record(
   id int primary key auto_increment,
   book_id int comment '图书id',
   student_id int comment '学生id',
   start_time timestamp not null comment '借阅日期',
   end_time timestamp null default null comment '归还日期',
   create_time timestamp default NOW() comment '创建时间'
) comment '图书借阅信息';
```
初始化数据
```mysql
use book;
-- 初始化数据
-- mysql中没有==，是用=号代替==。为了区分=和==，赋值时使用:=
set @username:='abc';
set @password:='123';
set @nickname:='风一样的男子😱';
set @email:='123@qq.com';

set @dictionary_classes_graduate_year='000001';
set @dictionary_classes_major='000002';

set @classes_name:='幼儿园😂';
set @student_name:='小小的梦想🐷';

insert into user(username, nickname, password, email) values (@username, @nickname, @password, @email);
insert into user(username, nickname, password, email) values (concat(@username, '1'), concat(@nickname, '1'), @password, @email);
insert into user(username, nickname, password, email) values (concat(@username, '2'), concat(@nickname, '2'), @password, @email);
insert into user(username, nickname, password, email) values (concat(@username, '3'), concat(@nickname, '3'), @password, @email);
insert into user(username, nickname, password, email) values (concat(@username, '4'), concat(@nickname, '4'), @password, @email);
insert into user(username, nickname, password, email) values (concat(@username, '5'), concat(@nickname, '5'), @password, @email);

## 数据字典：学生毕业年份
insert into dictionary(dictionary_key, dictionary_value, dictionary_desc)values (@dictionary_classes_graduate_year, '毕业年份', '学生毕业的年份');

insert into dictionary_tag(dictionary_tag_key, dictionary_tag_value, dictionary_id)values ('001', '2020届', 1);
insert into dictionary_tag(dictionary_tag_key, dictionary_tag_value, dictionary_id)values ('002', '2021届', 1);
insert into dictionary_tag(dictionary_tag_key, dictionary_tag_value, dictionary_id)values ('003', '2022届', 1);
insert into dictionary_tag(dictionary_tag_key, dictionary_tag_value, dictionary_id)values ('004', '2023届', 1);

## 数据字典：学生专业
insert into dictionary(dictionary_key, dictionary_value, dictionary_desc)values (@dictionary_classes_major, '专业', '学生的专业');
insert into dictionary_tag(dictionary_tag_key, dictionary_tag_value, dictionary_id)values ('001', '中文系', 2);
insert into dictionary_tag(dictionary_tag_key, dictionary_tag_value, dictionary_id)values ('002', '英语系', 2);
insert into dictionary_tag(dictionary_tag_key, dictionary_tag_value, dictionary_id)values ('003', '计算机科学与技术', 2);

insert into classes(classes_name, classes_graduate_year, classes_major, classes_desc) values (concat(@classes_name, '大班'), '000001001', '000002003', '已经4岁，即将成为大人的大班同学，不再是3岁小孩子了呀');
insert into classes(classes_name, classes_graduate_year, classes_major, classes_desc) values (concat(@classes_name, '中班'), '000001002', '000002003', '虽然还是3岁的小孩子，但也开始思考人生了呢');
insert into classes(classes_name, classes_graduate_year, classes_major, classes_desc) values (concat(@classes_name, '小班'), '000001003', '000002003', '挂着鼻涕的跟屁虫');

insert into student(student_name, student_no, id_card, student_email, classes_id) values (concat(@student_name, 'A1'), 's00001', '222222222222222222', @email, 1);
insert into student(student_name, student_no, id_card, student_email, classes_id) values (concat(@student_name, 'A2'), 's00002', '222222222222222223', @email, 1);
insert into student(student_name, student_no, id_card, student_email, classes_id) values (concat(@student_name, 'A3'), 's00003', '222222222222222224', @email, 1);
insert into student(student_name, student_no, id_card, student_email, classes_id) values (concat(@student_name, 'A4'), 's00004', '222222222222222225', @email, 1);
insert into student(student_name, student_no, id_card, student_email, classes_id) values (concat(@student_name, 'A5'), 's00005', '222222222222222226', @email, 1);
insert into student(student_name, student_no, id_card, student_email, classes_id) values (concat(@student_name, 'B1'), 's00006', '222222222222222227', @email, 2);
insert into student(student_name, student_no, id_card, student_email, classes_id) values (concat(@student_name, 'B2'), 's00007', '222222222222222228', @email, 2);
insert into student(student_name, student_no, id_card, student_email, classes_id) values (concat(@student_name, 'B3'), 's00008', '222222222222222229', @email, 2);
insert into student(student_name, student_no, id_card, student_email, classes_id) values (concat(@student_name, 'B4'), 's00009', '222222222222222230', @email, 2);
insert into student(student_name, student_no, id_card, student_email, classes_id) values (concat(@student_name, 'B5'), 's00010', '222222222222222231', @email, 2);

insert into book(book_name, author, price, create_time) values ('高等数学', '马拉司机', 115.2, now());
insert into book(book_name, author, price, create_time) values ('诗和远方', '老湿', 61.5, now());
insert into book(book_name, author, price, create_time) values ('柴米油盐', '家庭妇男', 33, now());
insert into book(book_name, author, price, create_time) values ('▄︻┻┳═一∵∴∷∶∵(∵_,∵)>>>>散弹发射！！', '蚷神', 33, now());

insert into borrow_record(book_id, student_id, start_time, end_time, create_time) values (1, 1, '2020-01-01 14:20:00', '2020-02-01 14:20:00', now());
insert into borrow_record(book_id, student_id, start_time, end_time, create_time) values (1, 2, '2020-03-01 14:20:00', '2020-03-22 14:20:00', now());
insert into borrow_record(book_id, student_id, start_time, end_time, create_time) values (1, 3, '2020-05-01 14:20:00', '2020-05-12 14:20:00', now());
insert into borrow_record(book_id, student_id, start_time, end_time, create_time) values (2, 5, '2020-01-01 14:20:00', '2020-02-03 14:20:00', now());
insert into borrow_record(book_id, student_id, start_time, end_time, create_time) values (2, 7, '2020-02-04 14:20:00', '2020-03-03 14:20:00', now());
insert into borrow_record(book_id, student_id, start_time, end_time, create_time) values (2, 9, '2020-04-01 14:20:00', '2020-06-01 14:20:00', now());
insert into borrow_record(book_id, student_id, start_time, end_time, create_time) values (3, 3, '2020-02-01 14:20:00', '2020-02-21 14:20:00', now());
insert into borrow_record(book_id, student_id, start_time, end_time, create_time) values (3, 6, '2020-04-01 14:20:00', '2020-05-01 14:20:00', now());
insert into borrow_record(book_id, student_id, start_time, end_time, create_time) values (3, 8, '2020-06-01 14:20:00', '2020-06-06 14:20:00', now());
```

## 前后端接口

要实现功能，需要先明确前后端约定好的接口，我们主要实现图书借阅信息管理，所以只实现该业务相关接口。

需要说明的是，接口的定义一般是前后端约定好的，所以也和前端代码息息相关，前端需要什么数据，需要什么格式的数据，也会在接口中体现。

### 查询专业字典

请求

```
GET dict/tag/query?dictionaryKey=000002
```

响应

```json
{
  "success" : true,
  "code" : "200",
  "message" : "操作成功",
  "data" : [ {
    "dictionaryTagKey" : "000002001",
    "dictionaryTagValue" : "中文系"
  }, {
    "dictionaryTagKey" : "000002002",
    "dictionaryTagValue" : "英语系"
  }, {
    "dictionaryTagKey" : "000002003",
    "dictionaryTagValue" : "计算机科学与技术"
  } ]
}
```

### 查询毕业年份字典

请求

```
GET dict/tag/query?dictionaryKey=000001
```

响应

```json
{
  "success" : true,
  "code" : "200",
  "message" : "操作成功",
  "data" : [ {
    "dictionaryTagKey" : "000001001",
    "dictionaryTagValue" : "2020届"
  }, {
    "dictionaryTagKey" : "000001002",
    "dictionaryTagValue" : "2021届"
  }, {
    "dictionaryTagKey" : "000001003",
    "dictionaryTagValue" : "2022届"
  }, {
    "dictionaryTagKey" : "000001004",
    "dictionaryTagValue" : "2023届"
  } ]
}
```

### 查询图书借阅信息

请求

```
GET borrowRecord/query?searchText=&sortOrder=asc&pageSize=7&pageNumber=1
```

响应

```json
{
  "success" : true,
  "code" : "200",
  "message" : "操作成功",
  "total" : 9,
  "data" : [ {
    "id" : 1,
    "startTime" : "2020-01-01 14:20:00",
    "endTime" : "2020-02-01 14:20:00",
    "createTime" : "2020-06-17 15:52:33",
    "book" : {
      "id" : 1,
      "bookName" : "高等数学",
      "author" : "马拉司机",
      "price" : 115.20
    },
    "classes" : {
      "id" : 1,
      "classesName" : "幼儿园\uD83D\uDE02大班",
      "classesGraduateYear" : "000001001",
      "classesMajor" : "000002002",
      "classesDesc" : "已经4岁，即将成为大人的大班同学，不再是3岁小孩子了呀"
    },
    "student" : {
      "id" : 1,
      "studentName" : "小小的梦想\uD83D\uDC37A1",
      "studentNo" : "s00001",
      "idCard" : "222222222222222222",
      "studentEmail" : "123@qq.com"
    }
  }, {
    "id" : 7,
    "startTime" : "2020-02-01 14:20:00",
    "endTime" : "2020-02-21 14:20:00",
    "createTime" : "2020-06-17 15:52:33",
    "book" : {
      "id" : 3,
      "bookName" : "柴米油盐",
      "author" : "家庭妇男",
      "price" : 33.00
    },
    "classes" : {
      "id" : 1,
      "classesName" : "幼儿园\uD83D\uDE02大班",
      "classesGraduateYear" : "000001001",
      "classesMajor" : "000002002",
      "classesDesc" : "已经4岁，即将成为大人的大班同学，不再是3岁小孩子了呀"
    },
    "student" : {
      "id" : 3,
      "studentName" : "小小的梦想\uD83D\uDC37A3",
      "studentNo" : "s00003",
      "idCard" : "222222222222222224",
      "studentEmail" : "123@qq.com"
    }
  } ]
}
```

### 查询班级（数据字典）

使用在下拉菜单中，要求按照数据字典响应格式返回

请求
```
GET http://localhost:8080/classes/queryAsDict
```

响应
```json
{
  "success" : true,
  "code" : "200",
  "message" : "操作成功",
  "data" : [ {
    "dictionaryTagKey" : "1",
    "dictionaryTagValue" : "幼儿园\uD83D\uDE02大班",
    "classesGraduateYear" : "000001001",
    "classesMajor" : "000002003"
  }, {
    "dictionaryTagKey" : "2",
    "dictionaryTagValue" : "幼儿园\uD83D\uDE02中班",
    "classesGraduateYear" : "000001002",
    "classesMajor" : "000002003"
  }, {
    "dictionaryTagKey" : "3",
    "dictionaryTagValue" : "幼儿园\uD83D\uDE02小班",
    "classesGraduateYear" : "000001003",
    "classesMajor" : "000002003"
  } ]
}
```

### 查询图书（数据字典）

使用在下拉菜单中，要求按照数据字典响应格式返回

请求

```
GET book/queryAsDict
```

响应

```json
{
  "success" : true,
  "code" : "200",
  "message" : "操作成功",
  "data" : [ {
    "dictionaryTagKey" : "1",
    "dictionaryTagValue" : "高等数学",
    "author" : "马拉司机",
    "price" : 115.20
  }, {
    "dictionaryTagKey" : "2",
    "dictionaryTagValue" : "诗和远方",
    "author" : "老湿",
    "price" : 61.50
  }, {
    "dictionaryTagKey" : "3",
    "dictionaryTagValue" : "柴米油盐",
    "author" : "家庭妇男",
    "price" : 33.00
  }, {
    "dictionaryTagKey" : "4",
    "dictionaryTagValue" : "▄︻┻┳═一∵∴∷∶∵(∵_,∵)>>>>散弹发射！！",
    "author" : "蚷神",
    "price" : 33.00
  } ]
}
```

### 查询学生（数据字典）

级联下拉菜单：选择班级下拉选项之后，根据选择的班级id查询所有学生。要求按照数据字典响应格式返回

请求

```
GET student/queryAsDict?dictionaryKey=2
```

响应

```json
{
  "success" : true,
  "code" : "200",
  "message" : "操作成功",
  "data" : [ {
    "dictionaryTagKey" : "6",
    "dictionaryTagValue" : "小小的梦想\uD83D\uDC37B1",
    "studentNo" : "s00006",
    "idCard" : "222222222222222227"
  }, {
    "dictionaryTagKey" : "7",
    "dictionaryTagValue" : "小小的梦想\uD83D\uDC37B2",
    "studentNo" : "s00007",
    "idCard" : "222222222222222228"
  } ]
}
```

### 新增图书借阅信息

请求

```
POST borrowRecord/add
Content-Type: application/json

{"studentId":"7","bookId":"3","startTime":"2020-06-10 19:40:56","endTime":""}
```

响应

```json
{
  "success" : true,
  "code" : "200",
  "message" : "操作成功"
}
```

### 删除图书借阅信息

请求

```
GET borrowRecord/delete?ids=2&ids=3
```

响应

```json
{
  "success" : true,
  "code" : "200",
  "message" : "操作成功"
}
```

### 查询图书借阅详情

请求

```
GET borrowRecord/queryById?id=1
```

响应

```json
{
  "success" : true,
  "code" : "200",
  "message" : "操作成功",
  "data" : {
    "id" : 1,
    "startTime" : "2020-01-01 14:20:00",
    "endTime" : "2020-02-01 14:20:00",
    "createTime" : "2020-06-17 15:52:33",
    "book" : {
      "id" : 1,
      "bookName" : "高等数学",
      "author" : "马拉司机",
      "price" : 115.20
    },
    "classes" : {
      "id" : 1,
      "classesName" : "幼儿园\uD83D\uDE02大班",
      "classesGraduateYear" : "000001001",
      "classesMajor" : "000002002",
      "classesDesc" : "已经4岁，即将成为大人的大班同学，不再是3岁小孩子了呀"
    },
    "student" : {
      "id" : 1,
      "studentName" : "小小的梦想\uD83D\uDC37A1",
      "studentNo" : "s00001",
      "idCard" : "222222222222222222",
      "studentEmail" : "123@qq.com"
    }
  }
}
```

### 修改图书借阅信息

请求

```
POST borrowRecord/update
Content-Type: application/json

{"id":"10","studentId":"3","bookId":"4","startTime":"2020-06-11 19:24:46","endTime":"2020-06-18 19:54:49"}
```

响应

```json
{
  "success" : true,
  "code" : "200",
  "message" : "操作成功"
}
```

## 开发环境准备

### Maven环境配置

#### 1. 下载文件

下载上传的settings.xml文件和本地仓库压缩包M2Repository.zip，确保存放在本地某个路径中，之后别移动了。并解压压缩包，解压后是这样的：

<img src="assets\Maven1.png" style="zoom:50%; float:left;" />

#### 2. 打开配置面板

<img src="assets\Maven2.png" style="zoom:50%; float:left;" />

#### 3. IDEA配置Maven

<img src="assets\Maven3.png" style="zoom:50%; float:left;" />

### IDEA其他配置

参考课件 15. IDEA配置.pdf，要求项目及IDEA配置部分内容全部完成。

提前安装lombok插件：

在IDEA File -> settings -> plugins，输入lombok搜索。有时候网络原因，可能搜索不到，退出重新进入再次搜索，多操作几次。搜索到了点击install安装插件，安装完成后重启IDEA。

### 配置项目pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>frank</groupId>
    <artifactId>book-sys</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>

    <dependencies>

        <!-- MySQL数据库JDBC驱动包 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.38</version>
        </dependency>

        <!-- javaee web依赖包：运行时由web容器提供，
            包括：WebSocket 1.1, JSON Binding 1.0, JSON Processing 1.1,
            Servlet 4.0, JavaServer Faces 2.3, Expression Language  3.0,
            JavaServer Pages(JSP) 2.3, JSTL 1.2 -->
        <dependency>
            <groupId>javax</groupId>
            <artifactId>javaee-web-api</artifactId>
            <version>8.0.1</version>
            <scope>provided</scope>
        </dependency>

        <!-- Lombok: 自动插入编辑器并构建工具，以注解的方式，简化一些模版代码的编写，
                    如getter/setter方法、equals/hashcode方法、toString方法等-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.8</version>
            <scope>provided</scope>
        </dependency>

        <!-- JUnit: java的一种测试框架 -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>

        <!-- jackson core: 基础核心包-->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>2.8.9</version>
        </dependency>

        <!--jackson databind: 数据绑定包，提供Java对象与JSON数据格式进行序列化
                   及反序列化的支持 -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.8.9</version>
        </dependency>

        <!-- jackson annotations: 为Jackson数据绑定包提供的注解支持 -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
            <version>2.9.0</version>
        </dependency>

    </dependencies>

    <build>
        <!-- 打包后的包名（和我们部署的项目名一致） -->
        <finalName>bs</finalName>
        <plugins>
            <plugin>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

### 准备前端资源

## 代码设计

### 设计数据库实体类

### 设计http请求基类

主要针对前端表格中，可以输入文本搜索，并根据页码显示列表数据

```java
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseEntity {

    private Integer pageNumber;

    private Integer pageSize;

    private String searchText;

    private String sortOrder;
}

```

### 设计统一响应类

```java
import com.github.pagehelper.Page;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.PrintWriter;
import java.io.StringWriter;

@Getter
@Setter
@ToString
public class ResponseResult {

    private boolean success;

    private String code;

    private String message;

    private Long total;

    private Object data;

    private String stackTrace;
}
```

### 设计工具类

## 可扩展部分

### 功能上其他面板中的功能都可以实现

### session及权限校验可以考虑使用过滤器实现