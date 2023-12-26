package com.ruoyi.web.controller.sym.service;

import java.util.List;
import com.ruoyi.web.controller.sym.domain.SymRepairRequest;

/**
 * requestService接口
 * 
 * @author sym
 * @date 2023-12-25
 */
public interface ISymRepairRequestService 
{
    /**
     * 查询request
     * 
     * @param id request主键
     * @return request
     */
    public SymRepairRequest selectSymRepairRequestById(Long id);

    /**
     * 查询request列表
     * 
     * @param symRepairRequest request
     * @return request集合
     */
    public List<SymRepairRequest> selectSymRepairRequestList(SymRepairRequest symRepairRequest);

    /**
     * 新增request
     * 
     * @param symRepairRequest request
     * @return 结果
     */
    public int insertSymRepairRequest(SymRepairRequest symRepairRequest);

    /**
     * 修改request
     * 
     * @param symRepairRequest request
     * @return 结果
     */
    public int updateSymRepairRequest(SymRepairRequest symRepairRequest);

    /**
     * 批量删除request
     * 
     * @param ids 需要删除的request主键集合
     * @return 结果
     */
    public int deleteSymRepairRequestByIds(Long[] ids);

    /**
     * 删除request信息
     * 
     * @param id request主键
     * @return 结果
     */
    public int deleteSymRepairRequestById(Long id);

    List<SymRepairRequest> selectRequestList(SymRepairRequest symRepairRequest);

    int insertRequest(SymRepairRequest symRepairRequest);
}
