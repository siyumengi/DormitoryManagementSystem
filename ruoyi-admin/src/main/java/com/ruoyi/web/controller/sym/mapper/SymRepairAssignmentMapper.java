package com.ruoyi.web.controller.sym.mapper;

import java.util.List;
import com.ruoyi.web.controller.sym.domain.SymRepairAssignment;

/**
 * assignmentMapper接口
 * 
 * @author sym
 * @date 2023-12-25
 */
public interface SymRepairAssignmentMapper 
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
     * 删除assignment
     * 
     * @param id assignment主键
     * @return 结果
     */
    public int deleteSymRepairAssignmentById(Long id);

    /**
     * 批量删除assignment
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSymRepairAssignmentByIds(Long[] ids);
}
