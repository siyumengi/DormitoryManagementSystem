package com.ruoyi.web.controller.sym.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.web.controller.sym.domain.SymDormitory;
import com.ruoyi.web.controller.sym.domain.SymStudent;
import com.ruoyi.web.controller.sym.service.ISymDormitoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * SdormitoryController
 * 
 * @author sym
 * @date 2023-12-18
 */
@RestController
@RequestMapping("/system/sdormitory")
public class SymSDormitoryController extends BaseController
{
    @Autowired
    private ISymDormitoryService symDormitoryService;

    /**
     * 查询dormitory列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SymDormitory symDormitory)
    {
        startPage();
        List<SymStudent> list = symDormitoryService.searchlist(symDormitory);
        return getDataTable(list);
    }

    /**
     * 导出dormitory列表
     */
    @Log(title = "sdormitory", businessType = BusinessType.EXPORT)
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
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(symDormitoryService.selectSymDormitoryById(id));
    }

    /**
     * 新增dormitory
     */
    @Log(title = "sdormitory", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SymDormitory symDormitory)
    {
        return toAjax(symDormitoryService.insertSymDormitory(symDormitory));
    }

    /**
     * 修改dormitory
     */
    @Log(title = "sdormitory", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SymDormitory symDormitory)
    {
        return toAjax(symDormitoryService.updateSymDormitory(symDormitory));
    }

    /**
     * 删除dormitory
     */
    @Log(title = "sdormitory", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(symDormitoryService.deleteSymDormitoryByIds(ids));
    }
}
