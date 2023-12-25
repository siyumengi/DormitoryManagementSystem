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
import com.ruoyi.web.controller.sym.domain.SymPaymentRecord;
import com.ruoyi.web.controller.sym.service.ISymPaymentRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * recordController
 * 
 * @author sym
 * @date 2023-12-25
 */
@RestController
@RequestMapping("/system/record")
public class SymPaymentRecordController extends BaseController
{
    @Autowired
    private ISymPaymentRecordService symPaymentRecordService;

    /**
     * 查询record列表
     */
    @PreAuthorize("@ss.hasPermi('system:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(SymPaymentRecord symPaymentRecord)
    {
        startPage();
        List<SymPaymentRecord> list = symPaymentRecordService.selectSymPaymentRecordList(symPaymentRecord);
        return getDataTable(list);
    }

    /**
     * 导出record列表
     */
    @PreAuthorize("@ss.hasPermi('system:record:export')")
    @Log(title = "record", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SymPaymentRecord symPaymentRecord)
    {
        List<SymPaymentRecord> list = symPaymentRecordService.selectSymPaymentRecordList(symPaymentRecord);
        ExcelUtil<SymPaymentRecord> util = new ExcelUtil<SymPaymentRecord>(SymPaymentRecord.class);
        util.exportExcel(response, list, "record数据");
    }

    /**
     * 获取record详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(symPaymentRecordService.selectSymPaymentRecordById(id));
    }

    /**
     * 新增record
     */
    @PreAuthorize("@ss.hasPermi('system:record:add')")
    @Log(title = "record", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SymPaymentRecord symPaymentRecord)
    {
        return toAjax(symPaymentRecordService.insertSymPaymentRecord(symPaymentRecord));
    }

    /**
     * 修改record
     */
    @PreAuthorize("@ss.hasPermi('system:record:edit')")
    @Log(title = "record", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SymPaymentRecord symPaymentRecord)
    {
        return toAjax(symPaymentRecordService.updateSymPaymentRecord(symPaymentRecord));
    }

    /**
     * 删除record
     */
    @PreAuthorize("@ss.hasPermi('system:record:remove')")
    @Log(title = "record", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(symPaymentRecordService.deleteSymPaymentRecordByIds(ids));
    }
}
