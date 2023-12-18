package com.ruoyi.web.controller.sym.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.web.controller.sym.domain.SymResidenceHall;
import com.ruoyi.web.controller.sym.mapper.SymResidenceHallMapper;
import com.ruoyi.web.controller.sym.service.ISymResidenceHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * hallService业务层处理
 * 
 * @author sym
 * @date 2023-12-18
 */
@Service
public class SymResidenceHallServiceImpl implements ISymResidenceHallService
{
    @Autowired
    private SymResidenceHallMapper symResidenceHallMapper;

    /**
     * 查询hall
     * 
     * @param id hall主键
     * @return hall
     */
    @Override
    public SymResidenceHall selectSymResidenceHallById(Long id)
    {
        return symResidenceHallMapper.selectSymResidenceHallById(id);
    }

    /**
     * 查询hall列表
     * 
     * @param symResidenceHall hall
     * @return hall
     */
    @Override
    public List<SymResidenceHall> selectSymResidenceHallList(SymResidenceHall symResidenceHall)
    {
        return symResidenceHallMapper.selectSymResidenceHallList(symResidenceHall);
    }

    /**
     * 新增hall
     * 
     * @param symResidenceHall hall
     * @return 结果
     */
    @Override
    public int insertSymResidenceHall(SymResidenceHall symResidenceHall)
    {
        symResidenceHall.setCreateTime(DateUtils.getNowDate());
        return symResidenceHallMapper.insertSymResidenceHall(symResidenceHall);
    }

    /**
     * 修改hall
     * 
     * @param symResidenceHall hall
     * @return 结果
     */
    @Override
    public int updateSymResidenceHall(SymResidenceHall symResidenceHall)
    {
        symResidenceHall.setUpdateTime(DateUtils.getNowDate());
        return symResidenceHallMapper.updateSymResidenceHall(symResidenceHall);
    }

    /**
     * 批量删除hall
     * 
     * @param ids 需要删除的hall主键
     * @return 结果
     */
    @Override
    public int deleteSymResidenceHallByIds(Long[] ids)
    {
        return symResidenceHallMapper.deleteSymResidenceHallByIds(ids);
    }

    /**
     * 删除hall信息
     * 
     * @param id hall主键
     * @return 结果
     */
    @Override
    public int deleteSymResidenceHallById(Long id)
    {
        return symResidenceHallMapper.deleteSymResidenceHallById(id);
    }
}
