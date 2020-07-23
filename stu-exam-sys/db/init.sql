drop database if exists stu_exam;
create database stu_exam character set utf8mb4;

use stu_exam;

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

drop table if exists course;
create table course(
   id int primary key auto_increment,
   course_name varchar(20) not null comment '课程名称',
   create_time timestamp default NOW() comment '创建时间'
) comment '课程信息';

drop table if exists exam;
create table exam(
    id int primary key auto_increment,
    exam_name varchar(20) not null comment '考试名称',
    exam_desc varchar(20) comment '考试备注',
    course_id int comment '课程id',
    classes_id int comment '班级id',
    create_time timestamp default NOW() comment '创建时间',
    foreign key (course_id) references course(id),
    foreign key (classes_id) references classes(id)
) comment '考试信息';

drop table if exists exam_record;
create table exam_record(
    id int primary key auto_increment,
    score decimal(4, 1) not null comment '考试成绩',
    student_id int comment '学生id',
    exam_id int comment '考试id',
    exam_record_desc varchar(200) comment '考试记录备注',
    create_time timestamp default NOW() comment '创建时间',
    foreign key (student_id) references student(id),
    foreign key (exam_id) references exam(id)
) comment '学生考试记录';



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

insert into course(course_name) values ('高等数学');
insert into course(course_name) values ('诗和远方');
insert into course(course_name) values ('柴米油盐');

insert into exam(exam_name, exam_desc, course_id, classes_id) values ('2018诗词鉴赏', '吟诗对唱愁愁愁', 1, 1);
insert into exam(exam_name, exam_desc, course_id, classes_id) values ('2019诗词鉴赏', '吟诗对唱愁愁愁', 2, 1);
insert into exam(exam_name, exam_desc, course_id, classes_id) values ('2020诗词鉴赏', '吟诗对唱愁愁愁', 3, 1);
insert into exam(exam_name, exam_desc, course_id, classes_id) values ('2018诗词鉴赏', '吟诗对唱愁愁愁', 1, 2);
insert into exam(exam_name, exam_desc, course_id, classes_id) values ('2019诗词鉴赏', '吟诗对唱愁愁愁', 2, 2);
insert into exam(exam_name, exam_desc, course_id, classes_id) values ('2020诗词鉴赏', '吟诗对唱愁愁愁', 3, 2);

insert into exam_record(score, student_id, exam_id, exam_record_desc) values (60, 1, 1, '没啥好说的，干就完事');
insert into exam_record(score, student_id, exam_id, exam_record_desc) values (61, 2, 1, '没啥好说的，干就完事');
insert into exam_record(score, student_id, exam_id, exam_record_desc) values (70, 3, 1, '没啥好说的，干就完事');
insert into exam_record(score, student_id, exam_id, exam_record_desc) values (71, 4, 1, '没啥好说的，干就完事');
insert into exam_record(score, student_id, exam_id, exam_record_desc) values (72, 5, 1, '没啥好说的，干就完事');
insert into exam_record(score, student_id, exam_id, exam_record_desc) values (73, 6, 4, '没啥好说的，干就完事');
insert into exam_record(score, student_id, exam_id, exam_record_desc) values (80, 7, 4, '没啥好说的，干就完事');
insert into exam_record(score, student_id, exam_id, exam_record_desc) values (81, 8, 4, '没啥好说的，干就完事');
insert into exam_record(score, student_id, exam_id, exam_record_desc) values (82, 9, 4, '没啥好说的，干就完事');
insert into exam_record(score, student_id, exam_id, exam_record_desc) values (83, 10, 4, '没啥好说的，干就完事');
insert into exam_record(score, student_id, exam_id, exam_record_desc) values (84, 1, 2, '没啥好说的，干就完事');
insert into exam_record(score, student_id, exam_id, exam_record_desc) values (90, 2, 2, '没啥好说的，干就完事');
insert into exam_record(score, student_id, exam_id, exam_record_desc) values (91, 3, 2, '没啥好说的，干就完事');
insert into exam_record(score, student_id, exam_id, exam_record_desc) values (92, 4, 2, '没啥好说的，干就完事');
insert into exam_record(score, student_id, exam_id, exam_record_desc) values (93, 5, 2, '没啥好说的，干就完事');