package com.ruoyi.web.controller.sym.service;

import java.util.List;
import com.ruoyi.web.controller.sym.domain.SymPaymentRecord;

/**
 * recordService接口
 * 
 * @author sym
 * @date 2023-12-25
 */
public interface ISymPaymentRecordService 
{
    /**
     * 查询record
     * 
     * @param id record主键
     * @return record
     */
    public SymPaymentRecord selectSymPaymentRecordById(Long id);

    /**
     * 查询record列表
     * 
     * @param symPaymentRecord record
     * @return record集合
     */
    public List<SymPaymentRecord> selectSymPaymentRecordList(SymPaymentRecord symPaymentRecord);

    /**
     * 新增record
     * 
     * @param symPaymentRecord record
     * @return 结果
     */
    public int insertSymPaymentRecord(SymPaymentRecord symPaymentRecord);

    /**
     * 修改record
     * 
     * @param symPaymentRecord record
     * @return 结果
     */
    public int updateSymPaymentRecord(SymPaymentRecord symPaymentRecord);

    /**
     * 批量删除record
     * 
     * @param ids 需要删除的record主键集合
     * @return 结果
     */
    public int deleteSymPaymentRecordByIds(Long[] ids);

    /**
     * 删除record信息
     * 
     * @param id record主键
     * @return 结果
     */
    public int deleteSymPaymentRecordById(Long id);
}
