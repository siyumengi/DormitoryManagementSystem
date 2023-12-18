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


# 随机 学生宿舍楼 四条 数据 ，每个字段 字数不超过 10
INSERT INTO `sym_residence_hall` (`id`, `name`, `address`, `construction_year`, `facilities`, `description`, `create_time`, `update_time`, `is_deleted`)
VALUES
    (1, '宿舍楼1', '北京市海淀区', 2018, '有阳台、有阳台、有阳台、有阳台', '宿舍楼1的描述', '2021-01-01 00:00:00', '2021-01-01 00:00:00', 0),
    (2, '宿舍楼2', '北京市海淀区', 2018, '有阳台、有阳台、有阳台、有阳台', '宿舍楼2的描述', '2021-01-01 00:00:00', '2021-01-01 00:00:00', 0),
    (3, '宿舍楼3', '北京市海淀区', 2018, '有阳台、有阳台、有阳台、有阳台', '宿舍楼3的描述', '2021-01-01 00:00:00', '2021-01-01 00:00:00', 0),
    (4, '宿舍楼4', '北京市海淀区', 2018, '有阳台、有阳台、有阳台、有阳台', '宿舍楼4的描述', '2021-01-01 00:00:00', '2021-01-01 00:00:00', 0);

# 随机 每个宿舍楼不少于 10个寝室，不多于 20个寝室，每个寝室不少于 4个床位，不多于 6个床位
INSERT INTO `sym_dormitory` (`id`, `residence_hall_id`, `room_number`, `size`, `occupancy_count`, `status`, `total_bed_count`, `remaining_bed_count`, `description`, `create_time`, `update_time`, `is_deleted`)
VALUES
    (1, 1, '101', '1000', 10, '空闲', 10, 10, '宿舍101的描述', '2021-01-01 00:00:00', '2021-01-01 00:00:00', 0),
    (2, 1, '102', '1000', 10, '空闲', 10, 10, '宿舍102的描述', '2021-01-01 00:00:00', '2021-01-01 00:00:00', 0),
    (3, 1, '103', '1000', 10, '空闲', 10, 10, '宿舍103的描述', '2021-01-01 00:00:00', '2021-01-01 00:00:00', 0),
    (4, 1, '104', '1000', 10, '空闲', 10, 10, '宿舍104的描述', '2021-01-01 00:00:00', '2021-01-01 00:00:00', 0),
    (5, 2, '201', '1000', 10, '空闲', 10, 10, '宿舍201的描述', '2021-01-01 00:00:00', '2021-01-01 00:00:00', 0),
    (6, 2, '202', '1000', 10, '空闲', 10, 10, '宿舍202的描述', '2021-01-01 00:00:00', '2021-01-01 00:00:00', 0),
    (7, 2, '203', '1000', 10, '空闲', 10, 10, '宿舍203的描述', '2021-01-01 00:00:00', '2021-01-01 00:00:00', 0),
    (8, 2, '204', '1000', 10, '空闲', 10, 10, '宿舍204的描述', '2021-01-01 00:00:00', '2021-01-01 00:00:00', 0),
    (9, 3, '301', '1000', 10, '空闲', 10, 10, '宿舍301的描述', '2021-01-01 00:00:00', '2021-01-01 00:00:00', 0),
    (10, 3, '302', '1000', 10, '空闲', 10, 10, '宿舍302的描述', '2021-01-01 00:00:00', '2021-01-01 00:00:00', 0),
    (11, 3, '303', '1000', 10, '空闲', 10, 10, '宿舍303的描述', '2021-01-01 00:00:00', '2021-01-01 00:00:00', 0),
    (12, 3, '304', '1000', 10, '空闲', 10, 10, '宿舍304的描述', '2021-01-01 00:00:00', '2021-01-01 00:00:00', 0),
    (13, 4, '401', '1000', 10, '空闲', 10, 10, '宿舍401的描述', '2021-01-01 00:00:00', '2021-01-01 00:00:00', 0),
    (14, 4, '402', '1000', 10, '空闲', 10, 10, '宿舍402的描述', '2021-01-01 00:00:00', '2021-01-01 00:00:00', 0),
    (15, 4, '403', '1000', 10, '空闲', 10, 10, '宿舍403的描述', '2021-01-01 00:00:00', '2021-01-01 00:00:00', 0),
    (16, 4, '404', '1000', 10, '空闲', 10, 10, '宿舍404的描述', '2021-01-01 00:00:00', '2021-01-01 00:00:00', 0);

# 生成 每个宿舍楼 两位 宿管员
INSERT INTO sym_dormitory_keeper (name, gender, phone, residence_hall_id, hire_date, duties, supervisor_id, description)
VALUES
    ('宿管1', '男', '13800138000', 1, '2021-01-01', '宿管1的工作职责', 1, '宿管1的描述'),
    ('宿管2', '男', '13800138001', 1, '2021-01-01', '宿管2的工作职责', 1, '宿管2的描述'),
    ('宿管3', '男', '13800138002', 2, '2021-01-01', '宿管3的工作职责', 2, '宿管3的描述'),
    ('宿管4', '男', '13800138003', 2, '2021-01-01', '宿管4的工作职责', 2, '宿管4的描述'),
    ('宿管5', '男', '13800138004', 3, '2021-01-01', '宿管5的工作职责', 3, '宿管5的描述'),
    ('宿管6', '男', '13800138005', 3, '2021-01-01', '宿管6的工作职责', 3, '宿管6的描述'),
    ('宿管7', '男', '13800138006', 4, '2021-01-01', '宿管7的工作职责', 4, '宿管7的描述'),
    ('宿管8', '男', '13800138007', 4, '2021-01-01', '宿管8的工作职责', 4, '宿管8的描述');


