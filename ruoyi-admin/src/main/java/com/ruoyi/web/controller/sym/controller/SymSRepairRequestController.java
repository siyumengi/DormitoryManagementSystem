package com.ruoyi.web.controller.sym.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.web.controller.sym.domain.SymRepairRequest;
import com.ruoyi.web.controller.sym.service.ISymRepairRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * requestController
 * 
 * @author sym
 * @date 2023-12-25
 */
@RestController
@RequestMapping("/system/srequest")
public class SymSRepairRequestController extends BaseController
{
    @Autowired
    private ISymRepairRequestService symRepairRequestService;

    /**
     * 查询request列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SymRepairRequest symRepairRequest)
    {
        startPage();
        List<SymRepairRequest> list = symRepairRequestService.selectRequestList(symRepairRequest);
        return getDataTable(list);
    }

    /**
     * 导出request列表
     */
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
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(symRepairRequestService.selectSymRepairRequestById(id));
    }

    /**
     * 新增request
     */
    @Log(title = "request", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SymRepairRequest symRepairRequest)
    {
        return toAjax(symRepairRequestService.insertRequest(symRepairRequest));
    }

    /**
     * 修改request
     */
    @Log(title = "request", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SymRepairRequest symRepairRequest)
    {
        return toAjax(symRepairRequestService.updateSymRepairRequest(symRepairRequest));
    }

    /**
     * 删除request
     */
    @Log(title = "request", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(symRepairRequestService.deleteSymRepairRequestByIds(ids));
    }
}
