package com.ruoyi.web.controller.sym.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.web.controller.sym.domain.SymDormitory;
import com.ruoyi.web.controller.sym.service.ISymDormitoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * dormitoryController
 * 
 * @author sym
 * @date 2023-12-18
 */
@RestController
@RequestMapping("/system/dormitory")
public class SymDormitoryController extends BaseController
{
    @Autowired
    private ISymDormitoryService symDormitoryService;

    /**
     * 查询dormitory列表
     */
    @PreAuthorize("@ss.hasPermi('system:dormitory:list')")
    @GetMapping("/list")
    public TableDataInfo list(SymDormitory symDormitory)
    {
        startPage();
        List<SymDormitory> list = symDormitoryService.selectSymDormitoryList(symDormitory);
        return getDataTable(list);
    }

    /**
     * 导出dormitory列表
     */
    @PreAuthorize("@ss.hasPermi('system:dormitory:export')")
    @Log(title = "dormitory", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SymDormitory symDormitory)
    {
        List<SymDormitory> list = symDormitoryService.selectSymDormitoryList(symDormitory);
        ExcelUtil<SymDormitory> util = new ExcelUtil<SymDormitory>(SymDormitory.class);
        util.exportExcel(response, list, "dormitory数据");
    }

    /**
     * 获取dormitory详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:dormitory:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(symDormitoryService.selectSymDormitoryById(id));
    }

    /**
     * 新增dormitory
     */
    @PreAuthorize("@ss.hasPermi('system:dormitory:add')")
    @Log(title = "dormitory", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SymDormitory symDormitory)
    {
        return toAjax(symDormitoryService.insertSymDormitory(symDormitory));
    }

    /**
     * 修改dormitory
     */
    @PreAuthorize("@ss.hasPermi('system:dormitory:edit')")
    @Log(title = "dormitory", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SymDormitory symDormitory)
    {
        return toAjax(symDormitoryService.updateSymDormitory(symDormitory));
    }

    /**
     * 删除dormitory
     */
    @PreAuthorize("@ss.hasPermi('system:dormitory:remove')")
    @Log(title = "dormitory", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(symDormitoryService.deleteSymDormitoryByIds(ids));
    }
}
