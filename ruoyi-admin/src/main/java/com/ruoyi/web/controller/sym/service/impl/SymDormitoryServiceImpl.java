package com.ruoyi.web.controller.sym.service.impl;

import java.math.BigDecimal;
import java.util.Date;


import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.web.controller.sym.domain.CurrentUser;
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
    private final CurrentUser currentUser = new CurrentUser();

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
    /**
     * 插入宿舍信息到数据库
     * @param symDormitory 待插入的宿舍对象
     * @return 插入的宿舍id
     */
    public int insertSymDormitory(SymDormitory symDormitory) {
        // 设置宿舍的剩余床位数为总床位数
        symDormitory.setRemainingBedCount(symDormitory.getTotalBedCount());
        // 设置宿舍的入住人数为0
        symDormitory.setOccupancyCount(0L);
        // 设置宿舍的状态为"空闲"
        symDormitory.setStatus("空闲");
        // 设置宿舍的描述为"暂无"
        symDormitory.setDescription("暂无");
        // 设置宿舍的创建时间为当前日期
        symDormitory.setCreateTime(DateUtils.getNowDate());

        // 新建宿舍余额对象
        SymDormitoryBalance symDormitoryBalance = new SymDormitoryBalance();
        // 设置宿舍余额的对象的宿舍id为刚插入的宿舍id
        symDormitoryBalance.setDormitoryId(symDormitory.getResidenceHallId());
        // 设置宿舍余额的对象的房间号为刚插入的宿舍的房间号
        symDormitoryBalance.setRoomNumber(symDormitory.getRoomNumber());
        // 设置宿舍余额的对象的水电费为0
        symDormitoryBalance.setWaterFee(new BigDecimal("0"));
        symDormitoryBalance.setElectricityFee(new BigDecimal("0"));
        // 设置宿舍余额的对象的是否过期为0
        symDormitoryBalance.setIsOverdue(0L);

        // 将宿舍余额对象插入到数据库中
        symDormitoryBalanceMapper.insertSymDormitoryBalance(symDormitoryBalance);

        // 将宿舍对象插入到数据库中，并返回插入的宿舍id
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
    /**
     * 根据宿舍ID数组删除多个宿舍
     * @param ids 宿舍ID数组
     * @return 删除的宿舍数量
     */
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
    /**
     * 根据id删除宿舍
     * @param id 宿舍id
     * @return 删除成功返回1，删除失败返回0
     */
    public int deleteSymDormitoryById(Long id) {
        // 删除宿舍时，先判断宿舍是否有人住，如果有人住，则不能删除
        // 1.根据id 查询 住宿人员
        SymDormitory symDormitory = symDormitoryMapper.selectSymDormitoryById(id);
        SymStudent symStudent = new SymStudent();
        symStudent.setResidenceHallId(symDormitory.getResidenceHallId());
        symStudent.setDormitoryRoomNumber(symDormitory.getRoomNumber());
        List<SymStudent> symStudents = symStudentMapper.selectSymStudentList(symStudent);
        // 2.删除 住宿人员信息
        for (SymStudent student : symStudents) {
            symStudentMapper.deleteSymStudentById(student.getId());
        }
        // 3.将 宿舍状态改为 空闲 实际入组人数改为 总床位数 ， 剩余床位数改为 总床位数，宿舍描述清空
        symDormitory.setOccupancyCount(0L);
        symDormitory.setRemainingBedCount(symDormitory.getTotalBedCount());
        symDormitory.setDescription("未入住");
        symDormitory.setStatus("空闲");
        return symDormitoryMapper.updateSymDormitory(symDormitory);
    }


    @Override
    public List<SymStudent> searchlist(SymDormitory symDormitory) {
        // 创建一个 SymStudent 对象
        SymStudent symStudent = new SymStudent();
        // 设置居住楼 ID
        symStudent.setResidenceHallId(currentUser.getResidenceHallId());
        // 设置宿舍房间号
        symStudent.setDormitoryRoomNumber(currentUser.getDormitoryRoomNumber());
        // 根据 symStudent 对象的条件来查询学生列表，并返回结果
        return symStudentMapper.selectSymStudentList(symStudent);

    }
}
