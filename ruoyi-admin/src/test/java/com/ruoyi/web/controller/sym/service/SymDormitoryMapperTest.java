package com.ruoyi.web.controller.sym.service;


import com.ruoyi.web.controller.sym.domain.SymDormitory;
import com.ruoyi.web.controller.sym.mapper.SymDormitoryMapper;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

public class SymDormitoryMapperTest {
    @Resource
    private SymDormitoryMapper mapper;
    @Test
    public void testSelectSymDormitoryByHalideAndRoomNumber() {
        // 创建测试对象

        
        // 创建测试数据
        long id = 100;
        String dormitoryNumber = "101";

        // 调用被测试方法
        SymDormitory result = mapper.selectSymDormitoryByHalideAndRoomNumber(id, dormitoryNumber);
        
        // 断言结果
        assertEquals(expected, result);
    }
}