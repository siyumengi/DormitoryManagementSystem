package com.ruoyi.web.controller.sym.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.web.controller.sym.domain.SymDormitoryBalance;
import com.ruoyi.web.controller.sym.service.ISymDormitoryBalanceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * balanceController
 * 
 * @author sym
 * @date 2023-12-25
 */
@RestController
@RequestMapping("/system/balance")
public class SymDormitoryBalanceController extends BaseController
{
    @Autowired
    private ISymDormitoryBalanceService symDormitoryBalanceService;

    /**
     * 查询balance列表
     */
    @PreAuthorize("@ss.hasPermi('system:balance:list')")
    @GetMapping("/list")
    public TableDataInfo list(SymDormitoryBalance symDormitoryBalance)
    {
        startPage();
        List<SymDormitoryBalance> list = symDormitoryBalanceService.selectSymDormitoryBalanceList(symDormitoryBalance);
        return getDataTable(list);
    }

    /**
     * 导出balance列表
     */
    @PreAuthorize("@ss.hasPermi('system:balance:export')")
    @Log(title = "balance", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SymDormitoryBalance symDormitoryBalance)
    {
        List<SymDormitoryBalance> list = symDormitoryBalanceService.selectSymDormitoryBalanceList(symDormitoryBalance);
        ExcelUtil<SymDormitoryBalance> util = new ExcelUtil<SymDormitoryBalance>(SymDormitoryBalance.class);
        util.exportExcel(response, list, "balance数据");
    }

    /**
     * 获取balance详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:balance:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(symDormitoryBalanceService.selectSymDormitoryBalanceById(id));
    }

    /**
     * 新增balance
     */
    @PreAuthorize("@ss.hasPermi('system:balance:add')")
    @Log(title = "balance", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SymDormitoryBalance symDormitoryBalance)
    {
        return toAjax(symDormitoryBalanceService.insertSymDormitoryBalance(symDormitoryBalance));
    }

    /**
     * 修改balance
     */
    @PreAuthorize("@ss.hasPermi('system:balance:edit')")
    @Log(title = "balance", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SymDormitoryBalance symDormitoryBalance)
    {
        return toAjax(symDormitoryBalanceService.updateSymDormitoryBalance(symDormitoryBalance));
    }

    /**
     * 删除balance
     */
    @PreAuthorize("@ss.hasPermi('system:balance:remove')")
    @Log(title = "balance", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(symDormitoryBalanceService.deleteSymDormitoryBalanceByIds(ids));
    }
}
