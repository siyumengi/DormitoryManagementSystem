package com.ruoyi.web.controller.sym.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.web.controller.sym.domain.SymDormitoryKeeper;
import com.ruoyi.web.controller.sym.mapper.SymDormitoryKeeperMapper;
import com.ruoyi.web.controller.sym.service.ISymDormitoryKeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * keeperService业务层处理
 * 
 * @author sym
 * @date 2023-12-18
 */
@Service
public class SymDormitoryKeeperServiceImpl implements ISymDormitoryKeeperService
{
    @Autowired
    private SymDormitoryKeeperMapper symDormitoryKeeperMapper;

    /**
     * 查询keeper
     * 
     * @param id keeper主键
     * @return keeper
     */
    @Override
    public SymDormitoryKeeper selectSymDormitoryKeeperById(Long id)
    {
        return symDormitoryKeeperMapper.selectSymDormitoryKeeperById(id);
    }

    /**
     * 查询keeper列表
     * 
     * @param symDormitoryKeeper keeper
     * @return keeper
     */
    @Override
    public List<SymDormitoryKeeper> selectSymDormitoryKeeperList(SymDormitoryKeeper symDormitoryKeeper)
    {
        return symDormitoryKeeperMapper.selectSymDormitoryKeeperList(symDormitoryKeeper);
    }

    /**
     * 新增keeper
     * 
     * @param symDormitoryKeeper keeper
     * @return 结果
     */
    @Override
    public int insertSymDormitoryKeeper(SymDormitoryKeeper symDormitoryKeeper)
    {
        symDormitoryKeeper.setCreateTime(DateUtils.getNowDate());
        return symDormitoryKeeperMapper.insertSymDormitoryKeeper(symDormitoryKeeper);
    }

    /**
     * 修改keeper
     * 
     * @param symDormitoryKeeper keeper
     * @return 结果
     */
    @Override
    public int updateSymDormitoryKeeper(SymDormitoryKeeper symDormitoryKeeper)
    {
        symDormitoryKeeper.setUpdateTime(DateUtils.getNowDate());
        return symDormitoryKeeperMapper.updateSymDormitoryKeeper(symDormitoryKeeper);
    }

    /**
     * 批量删除keeper
     * 
     * @param ids 需要删除的keeper主键
     * @return 结果
     */
    @Override
    public int deleteSymDormitoryKeeperByIds(Long[] ids)
    {
        return symDormitoryKeeperMapper.deleteSymDormitoryKeeperByIds(ids);
    }

    /**
     * 删除keeper信息
     * 
     * @param id keeper主键
     * @return 结果
     */
    @Override
    public int deleteSymDormitoryKeeperById(Long id)
    {
        return symDormitoryKeeperMapper.deleteSymDormitoryKeeperById(id);
    }
}
