package com.ruoyi.web.controller.sym.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.web.controller.sym.mapper.SymPaymentRecordMapper;
import com.ruoyi.web.controller.sym.domain.SymPaymentRecord;
import com.ruoyi.web.controller.sym.service.ISymPaymentRecordService;

/**
 * recordService业务层处理
 * 
 * @author sym
 * @date 2023-12-25
 */
@Service
public class SymPaymentRecordServiceImpl implements ISymPaymentRecordService 
{
    @Autowired
    private SymPaymentRecordMapper symPaymentRecordMapper;

    /**
     * 查询record
     * 
     * @param id record主键
     * @return record
     */
    @Override
    public SymPaymentRecord selectSymPaymentRecordById(Long id)
    {
        return symPaymentRecordMapper.selectSymPaymentRecordById(id);
    }

    /**
     * 查询record列表
     * 
     * @param symPaymentRecord record
     * @return record
     */
    @Override
    public List<SymPaymentRecord> selectSymPaymentRecordList(SymPaymentRecord symPaymentRecord)
    {
        return symPaymentRecordMapper.selectSymPaymentRecordList(symPaymentRecord);
    }

    /**
     * 新增record
     * 
     * @param symPaymentRecord record
     * @return 结果
     */
    @Override
    public int insertSymPaymentRecord(SymPaymentRecord symPaymentRecord)
    {
        symPaymentRecord.setCreateTime(DateUtils.getNowDate());
        return symPaymentRecordMapper.insertSymPaymentRecord(symPaymentRecord);
    }

    /**
     * 修改record
     * 
     * @param symPaymentRecord record
     * @return 结果
     */
    @Override
    public int updateSymPaymentRecord(SymPaymentRecord symPaymentRecord)
    {
        symPaymentRecord.setUpdateTime(DateUtils.getNowDate());
        return symPaymentRecordMapper.updateSymPaymentRecord(symPaymentRecord);
    }

    /**
     * 批量删除record
     * 
     * @param ids 需要删除的record主键
     * @return 结果
     */
    @Override
    public int deleteSymPaymentRecordByIds(Long[] ids)
    {
        return symPaymentRecordMapper.deleteSymPaymentRecordByIds(ids);
    }

    /**
     * 删除record信息
     * 
     * @param id record主键
     * @return 结果
     */
    @Override
    public int deleteSymPaymentRecordById(Long id)
    {
        return symPaymentRecordMapper.deleteSymPaymentRecordById(id);
    }
}
