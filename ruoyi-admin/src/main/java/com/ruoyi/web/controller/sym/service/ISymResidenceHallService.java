package com.ruoyi.web.controller.sym.service;

import com.ruoyi.web.controller.sym.domain.SymResidenceHall;

import java.util.List;

/**
 * hallService接口
 * 
 * @author sym
 * @date 2023-12-18
 */
public interface ISymResidenceHallService 
{
    /**
     * 查询hall
     * 
     * @param id hall主键
     * @return hall
     */
    public SymResidenceHall selectSymResidenceHallById(Long id);

    /**
     * 查询hall列表
     * 
     * @param symResidenceHall hall
     * @return hall集合
     */
    public List<SymResidenceHall> selectSymResidenceHallList(SymResidenceHall symResidenceHall);

    /**
     * 新增hall
     * 
     * @param symResidenceHall hall
     * @return 结果
     */
    public int insertSymResidenceHall(SymResidenceHall symResidenceHall);

    /**
     * 修改hall
     * 
     * @param symResidenceHall hall
     * @return 结果
     */
    public int updateSymResidenceHall(SymResidenceHall symResidenceHall);

    /**
     * 批量删除hall
     * 
     * @param ids 需要删除的hall主键集合
     * @return 结果
     */
    public int deleteSymResidenceHallByIds(Long[] ids);

    /**
     * 删除hall信息
     * 
     * @param id hall主键
     * @return 结果
     */
    public int deleteSymResidenceHallById(Long id);
}
