package com.ruoyi.web.controller.sym.service.impl;

import java.math.BigDecimal;
import java.util.Date;



import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.web.controller.sym.domain.SymDormitory;
import com.ruoyi.web.controller.sym.domain.SymDormitoryBalance;
import com.ruoyi.web.controller.sym.domain.SymStudent;
import com.ruoyi.web.controller.sym.mapper.SymDormitoryBalanceMapper;
import com.ruoyi.web.controller.sym.mapper.SymDormitoryMapper;
import com.ruoyi.web.controller.sym.mapper.SymStudentMapper;
import com.ruoyi.web.controller.sym.service.ISymDormitoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * dormitoryService业务层处理
 *
 * @author sym
 * @date 2023-12-18
 */
@Service
public class SymDormitoryServiceImpl implements ISymDormitoryService {
    @Autowired
    private SymDormitoryMapper symDormitoryMapper;
    @Resource
    private SymStudentMapper symStudentMapper;

    @Resource
    private SymDormitoryBalanceMapper symDormitoryBalanceMapper;

    /**
     * 查询dormitory
     *
     * @param id dormitory主键
     * @return dormitory
     */
    @Override
    public SymDormitory selectSymDormitoryById(Long id) {
        return symDormitoryMapper.selectSymDormitoryById(id);
    }

    /**
     * 查询dormitory列表
     *
     * @param symDormitory dormitory
     * @return dormitory
     */
    @Override
    public List<SymDormitory> selectSymDormitoryList(SymDormitory symDormitory) {

        return symDormitoryMapper.selectSymDormitoryList(symDormitory);
    }

    /**
     * 新增dormitory
     *
     * @param symDormitory dormitory
     * @return 结果
     */
    @Override
    public int insertSymDormitory(SymDormitory symDormitory) {
        symDormitory.setRemainingBedCount(symDormitory.getTotalBedCount());
        symDormitory.setOccupancyCount(0L);
        symDormitory.setStatus("空闲");
        symDormitory.setDescription("暂无");
        symDormitory.setCreateTime(DateUtils.getNowDate());
        // 新增宿舍时，生成宿舍状态
        SymDormitoryBalance symDormitoryBalance = new SymDormitoryBalance();
        symDormitoryBalance.setDormitoryId(symDormitory.getResidenceHallId());
        symDormitoryBalance.setRoomNumber(symDormitory.getRoomNumber());
        symDormitoryBalance.setWaterFee(new BigDecimal("0"));
        symDormitoryBalance.setElectricityFee(new BigDecimal("0"));
        symDormitoryBalance.setIsOverdue(0L);
        // 添加到数据库
        symDormitoryBalanceMapper.insertSymDormitoryBalance(symDormitoryBalance);


        return symDormitoryMapper.insertSymDormitory(symDormitory);
    }

    /**
     * 修改dormitory
     *
     * @param symDormitory dormitory
     * @return 结果
     */
    @Override
    public int updateSymDormitory(SymDormitory symDormitory) {
        symDormitory.setUpdateTime(DateUtils.getNowDate());
        return symDormitoryMapper.updateSymDormitory(symDormitory);
    }

    /**
     * 批量删除dormitory
     *
     * @param ids 需要删除的dormitory主键
     * @return 结果
     */
    @Override
    public int deleteSymDormitoryByIds(Long[] ids) {
        for (Long id : ids) {
            SymDormitory symDormitory = selectSymDormitoryById(id);
            if (symDormitory.getOccupancyCount() != 0) {
                deleteSymDormitoryById(id);
            }
        }
        return symDormitoryMapper.deleteSymDormitoryByIds(ids);
    }

    /**
     * 删除dormitory信息
     *
     * @param id dormitory主键
     * @return 结果
     */
    @Override
    public int deleteSymDormitoryById(Long id) {
        // 删除宿舍时，先判断宿舍是否有人住，如果有人住，则不能删除
        //1.根据id 查询 住宿人员
        SymDormitory symDormitory = symDormitoryMapper.selectSymDormitoryById(id);
        SymStudent symStudent = new SymStudent();
        symStudent.setResidenceHallId(symDormitory.getResidenceHallId());
        symStudent.setDormitoryRoomNumber(symDormitory.getRoomNumber());
        List<SymStudent> symStudents = symStudentMapper.selectSymStudentList(symStudent);
        //2.删除 住宿人员信息
        for (SymStudent student : symStudents) {
            symStudentMapper.deleteSymStudentById(student.getId());
        }
        //3.将 宿舍状态改为 空闲 实际入组人数改为 总床位数 ， 剩余床位数改为 总床位数，宿舍描述清空
        symDormitory.setOccupancyCount(0L);
        symDormitory.setRemainingBedCount(symDormitory.getTotalBedCount());
        symDormitory.setDescription("未入住");
        symDormitory.setStatus("空闲");
        return symDormitoryMapper.updateSymDormitory(symDormitory);


    }

    @Override
    public List<SymStudent> searchlist(SymDormitory symDormitory) {
        SymStudent symStudent = new SymStudent();
        symStudent.setResidenceHallId(1L);
        symStudent.setDormitoryRoomNumber("101");
        return symStudentMapper.selectSymStudentList(symStudent);
    }
}
