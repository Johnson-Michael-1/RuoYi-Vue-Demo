drop table if exists ry-vue.sys_student;
create table sys_student (
  student_id           int(11)         auto_increment    comment '学生编号',
  student_name         varchar(30)     default ''        comment '学生姓名',
  student_sex          char(1)         default '0'       comment '性别（0男 1女 2未知）',
  student_age          int(3)          default null      comment '年龄',
  student_classroom    varchar(50)     default ''        comment '班级',
  student_number       varchar(20)     default ''        comment '学号',
  student_college      varchar(50)     default ''        comment '学院',
  student_major        varchar(60)     default ''        comment '专业',
  enroll_date          datetime                          comment '入学时间',
  student_photo        binary(100)                       comment '人员图片',
  student_hobby        varchar(30)     default ''        comment '爱好（0代码 1音乐 2电影）',
  student_status       char(1)         default '0'       comment '状态（0正常 1停用）',
  primary key (student_id)
) engine=innodb auto_increment=1 comment = '学生信息表';

