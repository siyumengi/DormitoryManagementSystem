package com.ruoyi.web.controller.sym.service;

import java.util.Date;
import java.util.Random;



import com.ruoyi.web.controller.sym.domain.SymDormitory;
import com.ruoyi.web.controller.sym.domain.SymStudent;
import com.ruoyi.web.controller.sym.mapper.SymDormitoryMapper;
import com.ruoyi.web.controller.sym.mapper.SymStudentMapper;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
public class SymDormitoryMapperTest {
    @Resource
    private SymDormitoryMapper mapper;
    @Resource
    private SymStudentMapper symStudentMapper;

    @Test
    public void testSelectSymDormitoryByHalideAndRoomNumber() {
        // 新增 SymStudent
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                SymStudent symStudent = new SymStudent();
                symStudent.setDormitoryRoomNumber("10" + String.valueOf(j + 1));
                symStudent.setGender("男");
                symStudent.setMajor("计算机");
                symStudent.setName("张三");
                symStudent.setAge(20L);
                Random random = new Random();
                int k = random.nextInt(91) + 10;
                symStudent.setPhone("138001380" + String.valueOf(k));
                symStudent.setStudentNumber("201801010" + String.valueOf(j + i + 1));
                symStudentMapper.insertSymStudent(symStudent);
            }
        }


    }
}