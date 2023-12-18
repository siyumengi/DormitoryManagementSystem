package com.ruoyi.web.controller.sym.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.web.controller.sym.domain.SymResidenceHall;
import com.ruoyi.web.controller.sym.service.ISymResidenceHallService;
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
 * hallController
 * 
 * @author sym
 * @date 2023-12-18
 */
@RestController
@RequestMapping("/system/hall")
public class SymResidenceHallController extends BaseController
{
    @Autowired
    private ISymResidenceHallService symResidenceHallService;

    /**
     * 查询hall列表
     */
    @PreAuthorize("@ss.hasPermi('system:hall:list')")
    @GetMapping("/list")
    public TableDataInfo list(SymResidenceHall symResidenceHall)
    {
        startPage();
        List<SymResidenceHall> list = symResidenceHallService.selectSymResidenceHallList(symResidenceHall);
        return getDataTable(list);
    }

    /**
     * 导出hall列表
     */
    @PreAuthorize("@ss.hasPermi('system:hall:export')")
    @Log(title = "hall", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SymResidenceHall symResidenceHall)
    {
        List<SymResidenceHall> list = symResidenceHallService.selectSymResidenceHallList(symResidenceHall);
        ExcelUtil<SymResidenceHall> util = new ExcelUtil<SymResidenceHall>(SymResidenceHall.class);
        util.exportExcel(response, list, "hall数据");
    }

    /**
     * 获取hall详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:hall:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(symResidenceHallService.selectSymResidenceHallById(id));
    }

    /**
     * 新增hall
     */
    @PreAuthorize("@ss.hasPermi('system:hall:add')")
    @Log(title = "hall", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SymResidenceHall symResidenceHall)
    {
        return toAjax(symResidenceHallService.insertSymResidenceHall(symResidenceHall));
    }

    /**
     * 修改hall
     */
    @PreAuthorize("@ss.hasPermi('system:hall:edit')")
    @Log(title = "hall", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SymResidenceHall symResidenceHall)
    {
        return toAjax(symResidenceHallService.updateSymResidenceHall(symResidenceHall));
    }

    /**
     * 删除hall
     */
    @PreAuthorize("@ss.hasPermi('system:hall:remove')")
    @Log(title = "hall", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(symResidenceHallService.deleteSymResidenceHallByIds(ids));
    }
}
