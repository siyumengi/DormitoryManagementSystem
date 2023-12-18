package com.ruoyi.web.controller.sym.mapper;

import com.ruoyi.web.controller.sym.domain.SymDormitory;

import java.util.List;

/**
 * dormitoryMapper接口
 * 
 * @author sym
 * @date 2023-12-18
 */
public interface SymDormitoryMapper 
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
     * 删除dormitory
     * 
     * @param id dormitory主键
     * @return 结果
     */
    public int deleteSymDormitoryById(Long id);

    /**
     * 批量删除dormitory
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSymDormitoryByIds(Long[] ids);
}
