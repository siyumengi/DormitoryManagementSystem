package com.ruoyi.web.controller.sym.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.web.controller.sym.mapper.SymRepairRequestMapper;
import com.ruoyi.web.controller.sym.domain.SymRepairRequest;
import com.ruoyi.web.controller.sym.service.ISymRepairRequestService;

/**
 * requestService业务层处理
 * 
 * @author sym
 * @date 2023-12-25
 */
@Service
public class SymRepairRequestServiceImpl implements ISymRepairRequestService 
{
    @Autowired
    private SymRepairRequestMapper symRepairRequestMapper;

    /**
     * 查询request
     * 
     * @param id request主键
     * @return request
     */
    @Override
    public SymRepairRequest selectSymRepairRequestById(Long id)
    {
        return symRepairRequestMapper.selectSymRepairRequestById(id);
    }

    /**
     * 查询request列表
     * 
     * @param symRepairRequest request
     * @return request
     */
    @Override
    public List<SymRepairRequest> selectSymRepairRequestList(SymRepairRequest symRepairRequest)
    {
        return symRepairRequestMapper.selectSymRepairRequestList(symRepairRequest);
    }

    /**
     * 新增request
     * 
     * @param symRepairRequest request
     * @return 结果
     */
    @Override
    public int insertSymRepairRequest(SymRepairRequest symRepairRequest)
    {
        symRepairRequest.setCreateTime(DateUtils.getNowDate());
        return symRepairRequestMapper.insertSymRepairRequest(symRepairRequest);
    }

    /**
     * 修改request
     * 
     * @param symRepairRequest request
     * @return 结果
     */
    @Override
    public int updateSymRepairRequest(SymRepairRequest symRepairRequest)
    {
        // 维修映射 0-待处理', '1-已派工', '2-维修中', '3-已完成'
        if (symRepairRequest.getStatus().equals("0")) {
            symRepairRequest.setStatus("待处理");
        } else if (symRepairRequest.getStatus().equals("1")) {
            symRepairRequest.setStatus("已派工");
        } else if (symRepairRequest.getStatus().equals("2")) {
            symRepairRequest.setStatus("维修中");
        } else if (symRepairRequest.getStatus().equals("3")) {
            symRepairRequest.setStatus("已完成");
        }

        symRepairRequest.setUpdateTime(DateUtils.getNowDate());
        return symRepairRequestMapper.updateSymRepairRequest(symRepairRequest);
    }

    /**
     * 批量删除request
     * 
     * @param ids 需要删除的request主键
     * @return 结果
     */
    @Override
    public int deleteSymRepairRequestByIds(Long[] ids)
    {
        return symRepairRequestMapper.deleteSymRepairRequestByIds(ids);
    }

    /**
     * 删除request信息
     * 
     * @param id request主键
     * @return 结果
     */
    @Override
    public int deleteSymRepairRequestById(Long id)
    {
        return symRepairRequestMapper.deleteSymRepairRequestById(id);
    }
}
