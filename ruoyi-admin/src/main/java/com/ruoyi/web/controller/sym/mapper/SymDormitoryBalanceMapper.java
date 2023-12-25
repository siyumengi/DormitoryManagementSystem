package com.ruoyi.web.controller.sym.mapper;

import java.util.List;
import com.ruoyi.web.controller.sym.domain.SymDormitoryBalance;

/**
 * balanceMapper接口
 * 
 * @author sym
 * @date 2023-12-25
 */
public interface SymDormitoryBalanceMapper 
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
     * @param symDormitoryBalance balance
     * @return 结果
     */
    public int updateSymDormitoryBalance(SymDormitoryBalance symDormitoryBalance);

    /**
     * 删除balance
     * 
     * @param id balance主键
     * @return 结果
     */
    public int deleteSymDormitoryBalanceById(Long id);

    /**
     * 批量删除balance
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSymDormitoryBalanceByIds(Long[] ids);
}
