package com.ruoyi.web.controller.sym.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.web.controller.sym.domain.SymDormitoryKeeper;
import com.ruoyi.web.controller.sym.service.ISymDormitoryKeeperService;
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
 * keeperController
 * 
 * @author sym
 * @date 2023-12-18
 */
@RestController
@RequestMapping("/system/keeper")
public class SymDormitoryKeeperController extends BaseController
{
    @Autowired
    private ISymDormitoryKeeperService symDormitoryKeeperService;

    /**
     * 查询keeper列表
     */
    @PreAuthorize("@ss.hasPermi('system:keeper:list')")
    @GetMapping("/list")
    public TableDataInfo list(SymDormitoryKeeper symDormitoryKeeper)
    {
        startPage();
        List<SymDormitoryKeeper> list = symDormitoryKeeperService.selectSymDormitoryKeeperList(symDormitoryKeeper);
        return getDataTable(list);
    }

    /**
     * 导出keeper列表
     */
    @PreAuthorize("@ss.hasPermi('system:keeper:export')")
    @Log(title = "keeper", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SymDormitoryKeeper symDormitoryKeeper)
    {
        List<SymDormitoryKeeper> list = symDormitoryKeeperService.selectSymDormitoryKeeperList(symDormitoryKeeper);
        ExcelUtil<SymDormitoryKeeper> util = new ExcelUtil<SymDormitoryKeeper>(SymDormitoryKeeper.class);
        util.exportExcel(response, list, "keeper数据");
    }

    /**
     * 获取keeper详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:keeper:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(symDormitoryKeeperService.selectSymDormitoryKeeperById(id));
    }

    /**
     * 新增keeper
     */
    @PreAuthorize("@ss.hasPermi('system:keeper:add')")
    @Log(title = "keeper", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SymDormitoryKeeper symDormitoryKeeper)
    {
        return toAjax(symDormitoryKeeperService.insertSymDormitoryKeeper(symDormitoryKeeper));
    }

    /**
     * 修改keeper
     */
    @PreAuthorize("@ss.hasPermi('system:keeper:edit')")
    @Log(title = "keeper", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SymDormitoryKeeper symDormitoryKeeper)
    {
        return toAjax(symDormitoryKeeperService.updateSymDormitoryKeeper(symDormitoryKeeper));
    }

    /**
     * 删除keeper
     */
    @PreAuthorize("@ss.hasPermi('system:keeper:remove')")
    @Log(title = "keeper", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(symDormitoryKeeperService.deleteSymDormitoryKeeperByIds(ids));
    }
}
