package com.ruoyi.web.controller.sym.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.web.controller.sym.domain.SymStudent;
import com.ruoyi.web.controller.sym.mapper.SymStudentMapper;
import com.ruoyi.web.controller.sym.service.ISymStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * studentService业务层处理
 * 
 * @author sym
 * @date 2023-12-18
 */
@Service
public class SymStudentServiceImpl implements ISymStudentService
{
    @Autowired
    private SymStudentMapper symStudentMapper;

    /**
     * 查询student
     * 
     * @param id student主键
     * @return student
     */
    @Override
    public SymStudent selectSymStudentById(Long id)
    {
        return symStudentMapper.selectSymStudentById(id);
    }

    /**
     * 查询student列表
     * 
     * @param symStudent student
     * @return student
     */
    @Override
    public List<SymStudent> selectSymStudentList(SymStudent symStudent)
    {
        return symStudentMapper.selectSymStudentList(symStudent);
    }

    /**
     * 新增student
     * 
     * @param symStudent student
     * @return 结果
     */
    @Override
    public int insertSymStudent(SymStudent symStudent)
    {
        symStudent.setCreateTime(DateUtils.getNowDate());
        return symStudentMapper.insertSymStudent(symStudent);
    }

    /**
     * 修改student
     * 
     * @param symStudent student
     * @return 结果
     */
    @Override
    public int updateSymStudent(SymStudent symStudent)
    {
        symStudent.setUpdateTime(DateUtils.getNowDate());
        return symStudentMapper.updateSymStudent(symStudent);
    }

    /**
     * 批量删除student
     * 
     * @param ids 需要删除的student主键
     * @return 结果
     */
    @Override
    public int deleteSymStudentByIds(Long[] ids)
    {
        return symStudentMapper.deleteSymStudentByIds(ids);
    }

    /**
     * 删除student信息
     * 
     * @param id student主键
     * @return 结果
     */
    @Override
    public int deleteSymStudentById(Long id)
    {
        return symStudentMapper.deleteSymStudentById(id);
    }
}
