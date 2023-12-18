package com.ruoyi.web.controller.sym.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.web.controller.sym.domain.SymDormitory;
import com.ruoyi.web.controller.sym.mapper.SymDormitoryMapper;
import com.ruoyi.web.controller.sym.service.ISymDormitoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * dormitoryService业务层处理
 * 
 * @author sym
 * @date 2023-12-18
 */
@Service
public class SymDormitoryServiceImpl implements ISymDormitoryService
{
    @Autowired
    private SymDormitoryMapper symDormitoryMapper;

    /**
     * 查询dormitory
     * 
     * @param id dormitory主键
     * @return dormitory
     */
    @Override
    public SymDormitory selectSymDormitoryById(Long id)
    {
        return symDormitoryMapper.selectSymDormitoryById(id);
    }

    /**
     * 查询dormitory列表
     * 
     * @param symDormitory dormitory
     * @return dormitory
     */
    @Override
    public List<SymDormitory> selectSymDormitoryList(SymDormitory symDormitory)
    {
        return symDormitoryMapper.selectSymDormitoryList(symDormitory);
    }

    /**
     * 新增dormitory
     * 
     * @param symDormitory dormitory
     * @return 结果
     */
    @Override
    public int insertSymDormitory(SymDormitory symDormitory)
    {
        symDormitory.setCreateTime(DateUtils.getNowDate());
        return symDormitoryMapper.insertSymDormitory(symDormitory);
    }

    /**
     * 修改dormitory
     * 
     * @param symDormitory dormitory
     * @return 结果
     */
    @Override
    public int updateSymDormitory(SymDormitory symDormitory)
    {
        symDormitory.setUpdateTime(DateUtils.getNowDate());
        return symDormitoryMapper.updateSymDormitory(symDormitory);
    }

    /**
     * 批量删除dormitory
     * 
     * @param ids 需要删除的dormitory主键
     * @return 结果
     */
    @Override
    public int deleteSymDormitoryByIds(Long[] ids)
    {
        return symDormitoryMapper.deleteSymDormitoryByIds(ids);
    }

    /**
     * 删除dormitory信息
     * 
     * @param id dormitory主键
     * @return 结果
     */
    @Override
    public int deleteSymDormitoryById(Long id)
    {
        return symDormitoryMapper.deleteSymDormitoryById(id);
    }
}
