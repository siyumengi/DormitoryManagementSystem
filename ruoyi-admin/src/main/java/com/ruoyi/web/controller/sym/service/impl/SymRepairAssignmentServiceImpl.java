package com.ruoyi.web.controller.sym.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.web.controller.sym.mapper.SymRepairAssignmentMapper;
import com.ruoyi.web.controller.sym.domain.SymRepairAssignment;
import com.ruoyi.web.controller.sym.service.ISymRepairAssignmentService;

/**
 * assignmentService业务层处理
 * 
 * @author sym
 * @date 2023-12-25
 */
@Service
public class SymRepairAssignmentServiceImpl implements ISymRepairAssignmentService 
{
    @Autowired
    private SymRepairAssignmentMapper symRepairAssignmentMapper;

    /**
     * 查询assignment
     * 
     * @param id assignment主键
     * @return assignment
     */
    @Override
    public SymRepairAssignment selectSymRepairAssignmentById(Long id)
    {
        return symRepairAssignmentMapper.selectSymRepairAssignmentById(id);
    }

    /**
     * 查询assignment列表
     * 
     * @param symRepairAssignment assignment
     * @return assignment
     */
    @Override
    public List<SymRepairAssignment> selectSymRepairAssignmentList(SymRepairAssignment symRepairAssignment)
    {
        return symRepairAssignmentMapper.selectSymRepairAssignmentList(symRepairAssignment);
    }

    /**
     * 新增assignment
     * 
     * @param symRepairAssignment assignment
     * @return 结果
     */
    @Override
    public int insertSymRepairAssignment(SymRepairAssignment symRepairAssignment)
    {
        symRepairAssignment.setCreateTime(DateUtils.getNowDate());
        return symRepairAssignmentMapper.insertSymRepairAssignment(symRepairAssignment);
    }

    /**
     * 修改assignment
     * 
     * @param symRepairAssignment assignment
     * @return 结果
     */
    @Override
    public int updateSymRepairAssignment(SymRepairAssignment symRepairAssignment)
    {
        symRepairAssignment.setUpdateTime(DateUtils.getNowDate());
        return symRepairAssignmentMapper.updateSymRepairAssignment(symRepairAssignment);
    }

    /**
     * 批量删除assignment
     * 
     * @param ids 需要删除的assignment主键
     * @return 结果
     */
    @Override
    public int deleteSymRepairAssignmentByIds(Long[] ids)
    {
        return symRepairAssignmentMapper.deleteSymRepairAssignmentByIds(ids);
    }

    /**
     * 删除assignment信息
     * 
     * @param id assignment主键
     * @return 结果
     */
    @Override
    public int deleteSymRepairAssignmentById(Long id)
    {
        return symRepairAssignmentMapper.deleteSymRepairAssignmentById(id);
    }
}
