package com.ruoyi.web.controller.sym.service;

import com.ruoyi.web.controller.sym.domain.SymStudent;

import java.util.List;

/**
 * studentService接口
 * 
 * @author sym
 * @date 2023-12-18
 */
public interface ISymStudentService 
{
    /**
     * 查询student
     * 
     * @param id student主键
     * @return student
     */
    public SymStudent selectSymStudentById(Long id);

    /**
     * 查询student列表
     * 
     * @param symStudent student
     * @return student集合
     */
    public List<SymStudent> selectSymStudentList(SymStudent symStudent);

    /**
     * 新增student
     * 
     * @param symStudent student
     * @return 结果
     */
    public int insertSymStudent(SymStudent symStudent);

    /**
     * 修改student
     * 
     * @param symStudent student
     * @return 结果
     */
    public int updateSymStudent(SymStudent symStudent);

    /**
     * 批量删除student
     * 
     * @param ids 需要删除的student主键集合
     * @return 结果
     */
    public int deleteSymStudentByIds(Long[] ids);

    /**
     * 删除student信息
     * 
     * @param id student主键
     * @return 结果
     */
    public int deleteSymStudentById(Long id);
}
