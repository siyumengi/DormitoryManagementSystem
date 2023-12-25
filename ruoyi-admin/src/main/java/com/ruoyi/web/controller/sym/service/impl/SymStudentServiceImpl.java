package com.ruoyi.web.controller.sym.service.impl;

import java.net.BindException;
import java.util.Date;
import java.util.Map;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.web.exception.GlobalExceptionHandler;
import com.ruoyi.web.controller.sym.domain.SymDormitory;
import com.ruoyi.web.controller.sym.domain.SymStudent;
import com.ruoyi.web.controller.sym.mapper.SymStudentMapper;
import com.ruoyi.web.controller.sym.service.ISymStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * studentService业务层处理
 *
 * @author sym
 * @date 2023-12-18
 */
@Service
public class SymStudentServiceImpl implements ISymStudentService {
    @Resource
    private SymStudentMapper symStudentMapper;

    @Resource
    private SymDormitoryServiceImpl symDormitoryService;

    /**
     * 查询student
     *
     * @param id student主键
     * @return student
     */
    @Override
    public SymStudent selectSymStudentById(Long id) {
        return symStudentMapper.selectSymStudentById(id);
    }

    /**
     * 查询student列表
     *
     * @param symStudent student
     * @return student
     */
    @Override
    public List<SymStudent> selectSymStudentList(SymStudent symStudent) {
        return symStudentMapper.selectSymStudentList(symStudent);
    }

    /**
     * 新增student
     *
     * @param symStudent student
     * @return 结果
     */
    @Override
    public int insertSymStudent(SymStudent symStudent) {
        // 数据校验
        // 1. 校验学号是否重复
        SymStudent symStudent1 = new SymStudent();
        symStudent1.setStudentNumber(symStudent.getStudentNumber());
        List<SymStudent> symStudents = symStudentMapper.selectSymStudentList(symStudent1);
        if (symStudents.size() > 0) {
            throw new ServiceException("该学号已存在");
        }
        // 2. 校验年龄
        if (symStudent.getAge() < 0 || symStudent.getAge() > 100) {
            throw new ServiceException("年龄不合法");
        }
        // 3. 校验联系号码 手机号码正则表达式
        String phone = symStudent.getPhone();
        String regex = "^1[3|4|5|7|8][0-9]\\d{8}$";
        if (!phone.matches(regex)) {
            throw new ServiceException("手机号码不合法");
        }
        // 获取学生宿舍楼ID
        Long residenceHallId = symStudent.getResidenceHallId();
        // 获取学生宿舍房间号
        String dormitoryRoomNumber = symStudent.getDormitoryRoomNumber();
        // 根据学生 宿舍楼ID 和 宿舍房间号  查询是否还有空余床位
        SymDormitory symDormitory = new SymDormitory();
        symDormitory.setResidenceHallId(residenceHallId);
        symDormitory.setRoomNumber(dormitoryRoomNumber);
        List<SymDormitory> symDormitories = symDormitoryService.selectSymDormitoryList(symDormitory);
        // 如果没有该宿舍，返回错误信息
//        if (symDormitories.size() == 0){
//            throw new ServiceException("该宿舍不存在");
//        }
        symDormitory = symDormitories.get(0);
        // 如果没有空余床位，返回错误信息
        if (symDormitory.getRemainingBedCount() == 0) {
            throw new ServiceException("该宿舍已满");
        }
        // 如果有空余床位，将剩余床位数减一 ，实际入住人数加一
        symDormitory.setOccupancyCount(symDormitory.getOccupancyCount() + 1);
        symDormitory.setRemainingBedCount(symDormitory.getRemainingBedCount() - 1);

        if (symDormitory.getRemainingBedCount() == 0) {
            symDormitory.setDescription("已满");
            symDormitory.setStatus("已满");
        }
        int res = symDormitoryService.updateSymDormitory(symDormitory);
        if (res == 0) {
            throw new ServiceException("添加失败");
        }
        return symStudentMapper.insertSymStudent(symStudent);
    }

    /**
     * 修改student
     *
     * @param symStudent student
     * @return 结果
     */
    @Override
    public int updateSymStudent(SymStudent symStudent) {
        symStudent.setUpdateTime(DateUtils.getNowDate());
        return symStudentMapper.updateSymStudent(symStudent);
    }

    /**
     * 批量删除student
     *
     * @param ids 需要删除的student主键
     * @return 结果
     */
    @Override
    public int deleteSymStudentByIds(Long[] ids) {
        for (Long id : ids) {
            int res = deleteSymStudentById(id);
            if (res == 0) {
                throw new ServiceException("删除失败");
            }
        }
        return 1;
    }

    /**
     * 删除student信息
     *
     * @param id student主键
     * @return 结果
     */
    @Override
    public int deleteSymStudentById(Long id) {
        // 1.根据id 查询 学生信息
        SymStudent symStudent = symStudentMapper.selectSymStudentById(id);
        if (symStudent == null) {
            throw new ServiceException("该学生不存在");
        }
        // 2.根据学生信息中的宿舍楼id 和 宿舍房间号 查询宿舍信息
        SymDormitory symDormitory1 = new SymDormitory();
        symDormitory1.setResidenceHallId(symStudent.getResidenceHallId());
        symDormitory1.setRoomNumber(symStudent.getDormitoryRoomNumber());
        SymDormitory symDormitory = symDormitoryService.selectSymDormitoryList(symDormitory1).get(0);
        if (symDormitory == null) {
            throw new ServiceException("该宿舍不存在");
        }
        // 3.将宿舍 实际住宿人数加一，剩余床位数加一 , 如果住宿人数 小于 床铺数，将宿舍状态改为空闲
        symDormitory.setOccupancyCount(symDormitory.getOccupancyCount() - 1);
        symDormitory.setRemainingBedCount(symDormitory.getRemainingBedCount() + 1);
        if (symDormitory.getOccupancyCount() < symDormitory.getTotalBedCount()) {
            symDormitory.setDescription("空闲");
        }
        int res = symDormitoryService.updateSymDormitory(symDormitory);
        if (res == 0) {
            throw new ServiceException("退宿失败");
        }
        res = symStudentMapper.deleteSymStudentById(id);
        if (res == 0) {
            throw new ServiceException("退宿失败");
        }
        return res;
    }
}
