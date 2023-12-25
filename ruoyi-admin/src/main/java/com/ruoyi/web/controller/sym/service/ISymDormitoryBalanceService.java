package com.ruoyi.web.controller.sym.service;

import java.util.List;
import com.ruoyi.web.controller.sym.domain.SymDormitoryBalance;
import com.ruoyi.web.controller.sym.domain.SymPaymentRecord;

/**
 * balanceService接口
 * 
 * @author sym
 * @date 2023-12-25
 */
public interface ISymDormitoryBalanceService 
{
    /**
     * 查询balance
     * 
     * @param id balance主键
     * @return balance
     */
    public SymDormitoryBalance selectSymDormitoryBalanceById(Long id);

    /**
     * 查询balance列表
     * 
     * @param symDormitoryBalance balance
     * @return balance集合
     */
    public List<SymDormitoryBalance> selectSymDormitoryBalanceList(SymDormitoryBalance symDormitoryBalance);

    /**
     * 新增balance
     * 
     * @param symDormitoryBalance balance
     * @return 结果
     */
    public int insertSymDormitoryBalance(SymDormitoryBalance symDormitoryBalance);

    /**
     * 修改balance
     *
     * @param SymDormitoryBalance symDormitoryBalance
     * @return 结果
     */
    public int updateSymDormitoryBalance(SymDormitoryBalance symDormitoryBalance);
    public int updateBalance(Long num);

    /**
     * 批量删除balance
     * 
     * @param ids 需要删除的balance主键集合
     * @return 结果
     */
    public int deleteSymDormitoryBalanceByIds(Long[] ids);

    /**
     * 删除balance信息
     * 
     * @param id balance主键
     * @return 结果
     */
    public int deleteSymDormitoryBalanceById(Long id);

    List<SymPaymentRecord> searcherList(SymDormitoryBalance symDormitoryBalance);

    SymDormitoryBalance getRecord(Long id);
}
