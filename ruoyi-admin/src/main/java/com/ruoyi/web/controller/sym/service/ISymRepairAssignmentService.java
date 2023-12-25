package com.ruoyi.web.controller.sym.service;

import java.util.List;
import com.ruoyi.web.controller.sym.domain.SymRepairAssignment;

/**
 * assignmentService接口
 * 
 * @author sym
 * @date 2023-12-25
 */
public interface ISymRepairAssignmentService 
{
    /**
     * 查询assignment
     * 
     * @param id assignment主键
     * @return assignment
     */
    public SymRepairAssignment selectSymRepairAssignmentById(Long id);

    /**
     * 查询assignment列表
     * 
     * @param symRepairAssignment assignment
     * @return assignment集合
     */
    public List<SymRepairAssignment> selectSymRepairAssignmentList(SymRepairAssignment symRepairAssignment);

    /**
     * 新增assignment
     * 
     * @param symRepairAssignment assignment
     * @return 结果
     */
    public int insertSymRepairAssignment(SymRepairAssignment symRepairAssignment);

    /**
     * 修改assignment
     * 
     * @param symRepairAssignment assignment
     * @return 结果
     */
    public int updateSymRepairAssignment(SymRepairAssignment symRepairAssignment);

    /**
     * 批量删除assignment
     * 
     * @param ids 需要删除的assignment主键集合
     * @return 结果
     */
    public int deleteSymRepairAssignmentByIds(Long[] ids);

    /**
     * 删除assignment信息
     * 
     * @param id assignment主键
     * @return 结果
     */
    public int deleteSymRepairAssignmentById(Long id);
}
