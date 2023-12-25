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
import com.ruoyi.web.controller.sym.domain.SymRepairRequest;
import com.ruoyi.web.controller.sym.service.ISymRepairRequestService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * requestController
 * 
 * @author sym
 * @date 2023-12-25
 */
@RestController
@RequestMapping("/system/request")
public class SymRepairRequestController extends BaseController
{
    @Autowired
    private ISymRepairRequestService symRepairRequestService;

    /**
     * 查询request列表
     */
    @PreAuthorize("@ss.hasPermi('system:request:list')")
    @GetMapping("/list")
    public TableDataInfo list(SymRepairRequest symRepairRequest)
    {
        startPage();
        List<SymRepairRequest> list = symRepairRequestService.selectSymRepairRequestList(symRepairRequest);
        return getDataTable(list);
    }

    /**
     * 导出request列表
     */
    @PreAuthorize("@ss.hasPermi('system:request:export')")
    @Log(title = "request", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SymRepairRequest symRepairRequest)
    {
        List<SymRepairRequest> list = symRepairRequestService.selectSymRepairRequestList(symRepairRequest);
        ExcelUtil<SymRepairRequest> util = new ExcelUtil<SymRepairRequest>(SymRepairRequest.class);
        util.exportExcel(response, list, "request数据");
    }

    /**
     * 获取request详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:request:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(symRepairRequestService.selectSymRepairRequestById(id));
    }

    /**
     * 新增request
     */
    @PreAuthorize("@ss.hasPermi('system:request:add')")
    @Log(title = "request", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SymRepairRequest symRepairRequest)
    {
        return toAjax(symRepairRequestService.insertSymRepairRequest(symRepairRequest));
    }

    /**
     * 修改request
     */
    @PreAuthorize("@ss.hasPermi('system:request:edit')")
    @Log(title = "request", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SymRepairRequest symRepairRequest)
    {
        return toAjax(symRepairRequestService.updateSymRepairRequest(symRepairRequest));
    }

    /**
     * 删除request
     */
    @PreAuthorize("@ss.hasPermi('system:request:remove')")
    @Log(title = "request", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(symRepairRequestService.deleteSymRepairRequestByIds(ids));
    }
}
