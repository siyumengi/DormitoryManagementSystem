# 学生
CREATE TABLE `sym_student` (
                               `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学生ID',
                               `student_number` varchar(20) NOT NULL COMMENT '学号',
                               `name` varchar(50) NOT NULL COMMENT '姓名',
                               `gender` enum('男','女') NOT NULL DEFAULT '男' COMMENT '性别',
                               `age` tinyint(4) NOT NULL COMMENT '年龄',
                               `major` varchar(50) NOT NULL COMMENT '专业',
                               `class_name` varchar(50) NOT NULL COMMENT '班级名称',
                               `phone` varchar(20) NOT NULL COMMENT '联系电话',
                               `residence_hall_id` int(11) NOT NULL COMMENT '宿舍楼ID（外键）',
                               `dormitory_room_number` varchar(20) NOT NULL COMMENT '宿舍房间号',
                               `move_in_date` date NOT NULL COMMENT '入住日期',
                               `move_out_date` date DEFAULT NULL COMMENT '退宿日期',
                               `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除标记',
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `student_number_unique` (`student_number`) USING BTREE,
                               KEY `residence_hall_id` (`residence_hall_id`),
                               KEY `dormitory_room_number` (`dormitory_room_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

# 宿舍楼
CREATE TABLE `sym_residence_hall` (
                                      `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '宿舍楼ID',
                                      `name` varchar(50) NOT NULL COMMENT '宿舍楼名称',
                                      `address` varchar(100) NOT NULL COMMENT '宿舍楼地址',
                                      `construction_year` year(4) DEFAULT NULL COMMENT '建造年份',
                                      `facilities` varchar(200) DEFAULT NULL COMMENT '设施信息',
                                      `description` varchar(200) DEFAULT NULL COMMENT '宿舍楼描述',
                                      `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                      `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除标记',
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

# 宿舍
CREATE TABLE `sym_dormitory` (
                                 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '宿舍ID',
                                 `residence_hall_id` int(11) NOT NULL COMMENT '宿舍楼ID（外键）',
                                 `room_number` varchar(20) NOT NULL COMMENT '宿舍房间号',
                                 `size` varchar(20) DEFAULT NULL COMMENT '宿舍面积或大小',
                                 `occupancy_count` smallint(6) DEFAULT NULL COMMENT '实际入住的学生数量',
                                 `status` enum('空闲','已分配','维修中') NOT NULL DEFAULT '空闲' COMMENT '宿舍状态',
                                 `total_bed_count` smallint(6) NOT NULL COMMENT '总床位数量',
                                 `remaining_bed_count` smallint(6) NOT NULL DEFAULT '-1' COMMENT '剩余床位数量',
                                 `description` varchar(200) DEFAULT NULL COMMENT '宿舍描述',
                                 `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除标记',
                                 PRIMARY KEY (`id`),
                                 KEY `residence_hall_id` (`residence_hall_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

# 宿管
CREATE TABLE `sym_dormitory_keeper` (
                                        `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '宿管ID',
                                        `name` varchar(50) NOT NULL COMMENT '宿管姓名',
                                        `gender` enum('男','女') NOT NULL DEFAULT '男' COMMENT '性别',
                                        `phone` varchar(20) NOT NULL COMMENT '联系电话',
                                        `residence_hall_id` int(11) NOT NULL COMMENT '负责的宿舍楼ID（外键）',
                                        `hire_date` date NOT NULL COMMENT '入职日期',
                                        `duties` varchar(200) DEFAULT NULL COMMENT '工作职责',
                                        `supervisor_id` int(11) DEFAULT NULL COMMENT '上级主管ID（外键）',
                                        `description` varchar(200) DEFAULT NULL COMMENT '宿管描述',
                                        `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                        `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                        `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除标记',
                                        PRIMARY KEY (`id`),
                                        KEY `residence_hall_id` (`residence_hall_id`),
                                        KEY `supervisor_id` (`supervisor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `sym_payment_record` (
                                      `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '缴费记录ID',
                                      `student_id` int(11) NOT NULL COMMENT '学生ID（外键）',
                                      `student_name` varchar(50) NOT NULL COMMENT '学生姓名',
                                      `student_contact` varchar(20) NOT NULL COMMENT '学生联系方式',
                                      `residence_hall_id` int(11) NOT NULL COMMENT '宿舍楼ID（外键）',
                                      `room_number` varchar(20) NOT NULL COMMENT '宿舍房间号',
                                      `amount` decimal(10,2) NOT NULL COMMENT '缴费金额',
                                      `payment_status` enum('缴费中','缴费成功','缴费失败') NOT NULL DEFAULT '缴费中' COMMENT '缴费状态',
                                      `payment_time` datetime DEFAULT NULL COMMENT '缴费时间',
                                      `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                      `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除标记',
                                      PRIMARY KEY (`id`),
                                      KEY `student_id` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `sym_dormitory_balance` (
                                         `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '宿舍余额ID',
                                         `dormitory_id` int(11) NOT NULL COMMENT '宿舍ID（外键）',
                                         `room_number` varchar(20) NOT NULL COMMENT '宿舍房间号',
                                         `water_fee` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '水费',
                                         `electricity_fee` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '电费',
                                         `is_overdue` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否欠费（0：不欠费；1：欠费）',
                                         `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                         `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                         `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除标记',
                                         PRIMARY KEY (`id`),
                                         UNIQUE KEY `dormitory_id_unique` (`dormitory_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `sym_repair_request` (
                                      `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '维修请求ID',
                                      `student_id` int(11) NOT NULL COMMENT '学生ID（外键）',
                                      `dormitory_id` int(11) NOT NULL COMMENT '宿舍ID（外键）',
                                      `dormitory_room_number` varchar(20) NOT NULL COMMENT '宿舍房间号',
                                      `repair_description` varchar(200) NOT NULL COMMENT '维修描述',
                                      `status` enum('待处理','已派工','维修中','已完成') NOT NULL DEFAULT '待处理' COMMENT '维修状态',
                                      `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                      PRIMARY KEY (`id`),
                                      KEY `student_id` (`student_id`),
                                      KEY `dormitory_room_number` (`dormitory_room_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `sym_repair_assignment` (
                                         `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '维修派工ID',
                                         `repair_request_id` int(11) NOT NULL COMMENT '维修请求ID（外键）',
                                         `dormitory_keeper_id` int(11) NOT NULL COMMENT '宿管ID（外键）',
                                         `assignment_time` datetime DEFAULT NULL COMMENT '派工时间',
                                         `start_repair_time` datetime DEFAULT NULL COMMENT '开始维修时间',
                                         `finish_repair_time` datetime DEFAULT NULL COMMENT '完成维修时间',
                                         `description` varchar(200) DEFAULT NULL COMMENT '派工描述',
                                         `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                         `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                         PRIMARY KEY (`id`),
                                         KEY `repair_request_id` (`repair_request_id`),
                                         KEY `dormitory_keeper_id` (`dormitory_keeper_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
