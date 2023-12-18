package com.ruoyi.web.controller.sym.service;

import com.ruoyi.web.controller.sym.domain.SymDormitory;

import java.util.List;

/**
 * dormitoryService接口
 * 
 * @author sym
 * @date 2023-12-18
 */
public interface ISymDormitoryService 
{
    /**
     * 查询dormitory
     * 
     * @param id dormitory主键
     * @return dormitory
     */
    public SymDormitory selectSymDormitoryById(Long id);

    /**
     * 查询dormitory列表
     * 
     * @param symDormitory dormitory
     * @return dormitory集合
     */
    public List<SymDormitory> selectSymDormitoryList(SymDormitory symDormitory);

    /**
     * 新增dormitory
     * 
     * @param symDormitory dormitory
     * @return 结果
     */
    public int insertSymDormitory(SymDormitory symDormitory);

    /**
     * 修改dormitory
     * 
     * @param symDormitory dormitory
     * @return 结果
     */
    public int updateSymDormitory(SymDormitory symDormitory);

    /**
     * 批量删除dormitory
     * 
     * @param ids 需要删除的dormitory主键集合
     * @return 结果
     */
    public int deleteSymDormitoryByIds(Long[] ids);

    /**
     * 删除dormitory信息
     * 
     * @param id dormitory主键
     * @return 结果
     */
    public int deleteSymDormitoryById(Long id);
}
