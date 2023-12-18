package com.ruoyi.web.controller.sym.service;

import com.ruoyi.web.controller.sym.domain.SymDormitoryKeeper;

import java.util.List;

/**
 * keeperService接口
 * 
 * @author sym
 * @date 2023-12-18
 */
public interface ISymDormitoryKeeperService 
{
    /**
     * 查询keeper
     * 
     * @param id keeper主键
     * @return keeper
     */
    public SymDormitoryKeeper selectSymDormitoryKeeperById(Long id);

    /**
     * 查询keeper列表
     * 
     * @param symDormitoryKeeper keeper
     * @return keeper集合
     */
    public List<SymDormitoryKeeper> selectSymDormitoryKeeperList(SymDormitoryKeeper symDormitoryKeeper);

    /**
     * 新增keeper
     * 
     * @param symDormitoryKeeper keeper
     * @return 结果
     */
    public int insertSymDormitoryKeeper(SymDormitoryKeeper symDormitoryKeeper);

    /**
     * 修改keeper
     * 
     * @param symDormitoryKeeper keeper
     * @return 结果
     */
    public int updateSymDormitoryKeeper(SymDormitoryKeeper symDormitoryKeeper);

    /**
     * 批量删除keeper
     * 
     * @param ids 需要删除的keeper主键集合
     * @return 结果
     */
    public int deleteSymDormitoryKeeperByIds(Long[] ids);

    /**
     * 删除keeper信息
     * 
     * @param id keeper主键
     * @return 结果
     */
    public int deleteSymDormitoryKeeperById(Long id);
}
