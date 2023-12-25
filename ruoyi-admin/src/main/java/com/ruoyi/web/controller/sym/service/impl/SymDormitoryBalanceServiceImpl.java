package com.ruoyi.web.controller.sym.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.web.controller.sym.domain.SymPaymentRecord;
import com.ruoyi.web.controller.sym.domain.SymStudent;
import com.ruoyi.web.controller.sym.mapper.SymPaymentRecordMapper;
import org.springframework.stereotype.Service;
import com.ruoyi.web.controller.sym.mapper.SymDormitoryBalanceMapper;
import com.ruoyi.web.controller.sym.domain.SymDormitoryBalance;
import com.ruoyi.web.controller.sym.service.ISymDormitoryBalanceService;

import javax.annotation.Resource;

/**
 * balanceService业务层处理
 *
 * @author sym
 * @date 2023-12-25
 */
@Service
public class SymDormitoryBalanceServiceImpl implements ISymDormitoryBalanceService {
    @Resource
    private SymDormitoryBalanceMapper symDormitoryBalanceMapper;

    @Resource
    private SymPaymentRecordMapper symPaymentRecordMapper;

    /**
     * 查询balance
     *
     * @param id balance主键
     * @return balance
     */


    @Override
    public SymDormitoryBalance selectSymDormitoryBalanceById(Long id) {
        return symDormitoryBalanceMapper.selectSymDormitoryBalanceById(id);
    }

    /**
     * 查询balance列表
     *
     * @param symDormitoryBalance balance
     * @return balance
     */
    @Override
    public List<SymDormitoryBalance> selectSymDormitoryBalanceList(SymDormitoryBalance symDormitoryBalance) {
        return symDormitoryBalanceMapper.selectSymDormitoryBalanceList(symDormitoryBalance);
    }

    /**
     * 新增balance
     *
     * @param symDormitoryBalance balance
     * @return 结果
     */
    @Override
    public int insertSymDormitoryBalance(SymDormitoryBalance symDormitoryBalance) {
        symDormitoryBalance.setCreateTime(DateUtils.getNowDate());
        return symDormitoryBalanceMapper.insertSymDormitoryBalance(symDormitoryBalance);
    }

    @Override
    public int updateSymDormitoryBalance(SymDormitoryBalance symDormitoryBalance) {
        symDormitoryBalance.setCreateTime(DateUtils.getNowDate());
        return symDormitoryBalanceMapper.updateSymDormitoryBalance(symDormitoryBalance);
    }

    /**
     * 修改balance
     *
     * @param symDormitoryBalance balance
     * @return 结果
     */
    @Override
    public int updateBalance(Long num) {
        // 查询 宿舍的缴费记录
        SymStudent symStudent = new SymStudent();
        symStudent.setResidenceHallId(1L);
        symStudent.setDormitoryRoomNumber("101");
        SymDormitoryBalance symPaymentRecord = new SymDormitoryBalance();
        symPaymentRecord.setDormitoryId(symStudent.getResidenceHallId());
        symPaymentRecord.setRoomNumber(symStudent.getDormitoryRoomNumber());
        List<SymDormitoryBalance> symPaymentRecords = symDormitoryBalanceMapper.selectSymDormitoryBalanceList(symPaymentRecord);
        SymDormitoryBalance symPaymentRecord1 = symPaymentRecords.get(0);
        // 宿舍是否存在
        if (symPaymentRecord1 == null) {
            throw new RuntimeException("宿舍不存在");
        }

        BigDecimal electricityFee = symPaymentRecord1.getElectricityFee();
        // 两个相加 是否大于零 ，如果 大于零 则 说明 有电费，把 is_overdue 改为 0
         symPaymentRecord1.setElectricityFee(electricityFee.add(new BigDecimal(num)).setScale(2,BigDecimal.ROUND_HALF_UP));
        if (symPaymentRecord1.getElectricityFee().compareTo(BigDecimal.ZERO) > 0) {
            symPaymentRecord1.setIsOverdue(0L);
        }
        // 将这条缴费记录 记入 record 表中
        SymPaymentRecord symPaymentRecord2 = new SymPaymentRecord();
        symPaymentRecord2.setStudentId(12312L);
        symPaymentRecord2.setResidenceHallId(symStudent.getResidenceHallId());
        symPaymentRecord2.setRoomNumber(symStudent.getDormitoryRoomNumber());
        symPaymentRecord2.setAmount(new BigDecimal(num));
        symPaymentRecord2.setStudentName("aaa");
        symPaymentRecord2.setPaymentStatus("缴费成功");
        symPaymentRecord2.setStudentContact("13800138012");
        symPaymentRecord2.setPaymentTime(DateUtils.getNowDate());
        symPaymentRecordMapper.insertSymPaymentRecord(symPaymentRecord2);
        symPaymentRecord.setUpdateTime(DateUtils.getNowDate());
        return symDormitoryBalanceMapper.updateSymDormitoryBalance(symPaymentRecord1);
    }

    /**
     * 批量删除balance
     *
     * @param ids 需要删除的balance主键
     * @return 结果
     */
    @Override
    public int deleteSymDormitoryBalanceByIds(Long[] ids) {
        return symDormitoryBalanceMapper.deleteSymDormitoryBalanceByIds(ids);
    }

    /**
     * 删除balance信息
     *
     * @param id balance主键
     * @return 结果
     */
    @Override
    public int deleteSymDormitoryBalanceById(Long id) {
        return symDormitoryBalanceMapper.deleteSymDormitoryBalanceById(id);
    }

    @Override
    public List<SymPaymentRecord> searcherList(SymDormitoryBalance symDormitoryBalance) {
        // 查询 宿舍的缴费记录
        SymStudent symStudent = new SymStudent();
        symStudent.setResidenceHallId(1L);
        symStudent.setDormitoryRoomNumber("101");
        SymPaymentRecord symPaymentRecord = new SymPaymentRecord();
        symPaymentRecord.setResidenceHallId(symStudent.getResidenceHallId());
        symPaymentRecord.setRoomNumber(symStudent.getDormitoryRoomNumber());
        return symPaymentRecordMapper.selectSymPaymentRecordList(symPaymentRecord);
    }

    @Override
    public SymDormitoryBalance getRecord(Long id) {
        // 查询 宿舍的费用
        SymStudent symStudent = new SymStudent();
        symStudent.setResidenceHallId(1L);
        symStudent.setDormitoryRoomNumber("101");
        SymDormitoryBalance symPaymentRecord = new SymDormitoryBalance();
        symPaymentRecord.setDormitoryId(symStudent.getResidenceHallId());
        symPaymentRecord.setRoomNumber(symStudent.getDormitoryRoomNumber());

        List<SymDormitoryBalance> symPaymentRecords = symDormitoryBalanceMapper.selectSymDormitoryBalanceList(symPaymentRecord);
        if (symPaymentRecords.size() > 0) {
            return symPaymentRecords.get(0);
        }
        throw new RuntimeException("没有查询到缴费记录");
    }
}
