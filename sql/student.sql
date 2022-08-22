DROP TABLE IF EXISTS ry-vue.socket_student;
CREATE TABLE socket_student (
  student_id                INT(11)         AUTO_INCREMENT    COMMENT '学生编号',
  student_name          VARCHAR(30)                        COMMENT '学生姓名',
  student_sex              CHAR(1)         DEFAULT '0'     COMMENT '性别（0男 1女 2未知）',
  student_age             INT(3)                                    COMMENT '年龄',
  student_classroom    VARCHAR(50)                        COMMENT '班级',
  student_number       VARCHAR(20)                        COMMENT '学号',
  student_college        VARCHAR(50)                        COMMENT '学院',
  student_major          VARCHAR(60)                        COMMENT '专业',
  enroll_date               DATE                                     COMMENT '入学时间',
  student_photo         BINARY(100)                          COMMENT '人员图片',
  student_hobby         VARCHAR(30)                        COMMENT '爱好（0代码 1音乐 2电影）',
  student_status         CHAR(1)         DEFAULT '0'      COMMENT '状态（0正常 1停用）',
  PRIMARY KEY (student_id)
) ENGINE=INNODB AUTO_INCREMENT=1 COMMENT = 'WebSocket学生信息表';
