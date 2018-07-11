# 建立数据库
CREATE DATABASE leave_system;
USE leave_system;
# 建立学生表
CREATE TABLE student(
  `student_id` VARCHAR(13) NOT NULL COMMENT '学号',
  `name` VARCHAR(20) COMMENT '姓名',
  `department` VARCHAR(40) COMMENT '院系信息',
  `class_name` VARCHAR(40) COMMENT '班级信息（包括了年级）',
  `yiban_id` VARCHAR(10) COMMENT '易班id',
  `judgeClassId` VARCHAR(12)COMMENT '判断学生是否有转班级的id',
  PRIMARY KEY (student_id),
  KEY (yiban_id)
)ENGINE = InnoDB CHARSET = utf8;

# 建立请假信息表
CREATE TABLE information(
  `id` INT(8) AUTO_INCREMENT,
  `student_id` VARCHAR(13) NOT NULL ,
  `begin_time` DATETIME,
  `end_time` DATETIME,
  `number` INT(4) COMMENT '请假的节数',
  `reason` TEXT COMMENT '请假原因',
  `phone` VARCHAR(12),
  `status` TINYINT COMMENT '请假状态：（-1：拒绝，0：待审核，1：已同意未销假，2：已销假）',
  `file_path` VARCHAR(60) COMMENT '文件路径',
  `code` VARCHAR(10) COMMENT '验证码',
  PRIMARY KEY (id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 CHARSET = utf8;

# token
CREATE TABLE token(
  `token_type` TINYINT NOT NULL COMMENT 'token类型（0：开发者请求的token，1：用户请求的token）',
  `token` VARCHAR(50),
  `add_time` DATETIME COMMENT '添加时间',
  `expire_in` INT(8) DEFAULT 0
)ENGINE = InnoDB CHARSET = utf8;

# 班级表
CREATE TABLE class(
  class_id VARCHAR(12) NOT NULL ,
  teacher_yiban_id VARCHAR(10),
  Dean_yiban_id VARCHAR(10),
  monitor VARCHAR(10),
  PRIMARY KEY (class_id)
)ENGINE = InnoDB CHARSET = utf8;

# 添加索引
ALTER TABLE information ADD KEY (student_id);
# 添加外键约束
ALTER TABLE information ADD CONSTRAINT fk_student_id FOREIGN KEY (student_id)
  REFERENCES student(student_id)