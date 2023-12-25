package com.ruoyi.web.controller.sym.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.web.controller.sym.domain.SymDormitoryBalance;
import com.ruoyi.web.controller.sym.domain.SymPaymentRecord;
import com.ruoyi.web.controller.sym.service.ISymDormitoryBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * balanceController
 *
 * @author sym
 * @date 2023-12-25
 */
@RestController
@RequestMapping("/system/sbalance")
public class SymDormitorySBalanceController extends BaseController {
    @Autowired
    private ISymDormitoryBalanceService symDormitoryBalanceService;

    /**
     * 查询balance列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SymDormitoryBalance symDormitoryBalance) {
        startPage();
        List<SymPaymentRecord> list = symDormitoryBalanceService.searcherList(symDormitoryBalance);
        return getDataTable(list);
    }

    /**
     * 导出balance列表
     */
    @Log(title = "sbalance", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SymDormitoryBalance symDormitoryBalance) {
        List<SymDormitoryBalance> list = symDormitoryBalanceService.selectSymDormitoryBalanceList(symDormitoryBalance);
        ExcelUtil<SymDormitoryBalance> util = new ExcelUtil<SymDormitoryBalance>(SymDormitoryBalance.class);
        util.exportExcel(response, list, "balance数据");
    }

    /**
     * 获取balance详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(symDormitoryBalanceService.getRecord(id));
    }

    /**
     * 新增balance
     */
    @Log(title = "balance", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SymDormitoryBalance symDormitoryBalance) {
        return toAjax(symDormitoryBalanceService.insertSymDormitoryBalance(symDormitoryBalance));
    }

    /**
     * 修改balance
     */
    @Log(title = "sbalance", businessType = BusinessType.UPDATE)
    @GetMapping(value = "/edit/{amount}")
    public AjaxResult edit(@PathVariable("amount") Long amount) {
        return success(symDormitoryBalanceService.updateBalance(amount));
    }

    /**
     * 删除balance
     */
    @Log(title = "sbalance", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(symDormitoryBalanceService.deleteSymDormitoryBalanceByIds(ids));
    }
}
